package com.historydevteam.historymod.crafting;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.function.Consumer;

/**
 * Spends time crafting
 */
public class TimedCrafting implements INBTSerializable<NBTTagCompound> {

  private ICraftingProcess process;
  private Consumer<Float> onWorkingTick;
  private float timer;
  private long lastTick;

  public TimedCrafting(ICraftingProcess process, Consumer<Float> onWorkingTick) {
    this.process = process;
    this.onWorkingTick = onWorkingTick;
  }

  /**
   * Indicates if this crafting process did any work in the last 20 ticks
   */
  public boolean isCrafting(World world) {
    return world.getTotalWorldTime() - lastTick < 20;
  }

  /**
   * Tries to do work on this crafting process
   */
  public void tick(World world, float speed) {
    if (process.canCraft()) {
      lastTick = world.getTotalWorldTime();
      if (timer <= process.getDuration()) {
        timer += speed;
        onWorkingTick.accept(speed);
      }

      while (timer >= process.getDuration() && process.canCraft()) {
        timer -= process.getDuration();
        process.craft();
      }
    }
  }

  public float getProcessingPercent() {
    return Math.min(1, timer / process.getDuration());
  }

  @Override
  public NBTTagCompound serializeNBT() {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.setFloat("timer", timer);
    nbt.setLong("lastTick", lastTick);
    return nbt;
  }

  @Override
  public void deserializeNBT(NBTTagCompound nbt) {
    timer = nbt.getFloat("timer");
    lastTick = nbt.getLong("lastTick");
  }
}
