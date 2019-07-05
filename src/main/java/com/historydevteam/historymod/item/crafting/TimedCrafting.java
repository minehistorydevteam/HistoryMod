/*
package com.historydevteam.historymod.item.crafting;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.function.Consumer;

*/
/**
 * Spends time crafting
 *//*

public class TimedCrafting implements INBTSerializable<CompoundNBT> {

  private ICraftingProcess process;
  private Consumer<Float> onWorkingTick;
  private float timer;
  private long lastTick;

  public TimedCrafting(ICraftingProcess process, Consumer<Float> onWorkingTick) {
    this.process = process;
    this.onWorkingTick = onWorkingTick;
  }

  */
/**
   * Indicates if this crafting process did any work in the last 20 ticks
   *//*

  public boolean isCrafting(World world) {
    return world.getGameTime() - lastTick < 20;
  }

  */
/**
   * Tries to do work on this crafting process
   *//*

  public void tick(World world, float speed) {
    if (process.canCraft()) {
      lastTick = world.getGameTime();
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
  public CompoundNBT serializeNBT() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putFloat("timer", timer);
    nbt.putFloat("lastTick", lastTick);
    return nbt;
  }

  @Override
  public void deserializeNBT(CompoundNBT nbt) {
    timer = nbt.getFloat("timer");
    lastTick = nbt.getLong("lastTick");
  }
}
*/
