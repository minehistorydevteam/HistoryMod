package com.historydevteam.historymod.tileentity.modules;

import com.historydevteam.historymod.crafting.ICraftingProcess;
import com.historydevteam.historymod.crafting.TimedCrafting;
import com.historydevteam.historymod.crafting.energy.IEnergySource;
import net.minecraft.nbt.NBTTagCompound;

public class ModuleTimedCrafting extends AbstractModule {

  private TimedCrafting process;
  private IEnergySource energy;
  private float speedMultiplier;
  private int costPerTick;
  private boolean isCrafting;

  public ModuleTimedCrafting(ICraftingProcess process, IEnergySource energy, float speedMultiplier, int costPerTick) {
    this.process = new TimedCrafting(process, this::onWorkingTick);
    this.energy = energy;
    this.speedMultiplier = speedMultiplier;
    this.costPerTick = costPerTick;
  }

  @Override
  public void update() {
    if (getWorld().isRemote) return;

    float decimalSpeed = Math.min(energy.getSpeed(), energy.getMaxEnergyPerTick() / costPerTick);

    float scale = costPerTick / speedMultiplier;
    int speed = (int) (Math.floor(decimalSpeed * scale) / scale);

    if (speed > 0) {
      process.tick(getWorld(), speed);
    }

    boolean working = process.isCrafting(getWorld());
    if (working != isCrafting) {
      isCrafting = working;
      tile.sendUpdateToNearPlayers();
    }
  }

  public void onWorkingTick(float speed) {
    energy.useEnergy((int) (speed * costPerTick / speedMultiplier));
  }

  public float getProcessingPercent() {
    return process.getProcessingPercent();
  }

  @Override
  public String getName() {
    return "timed_crafting";
  }

  @Override
  public NBTTagCompound serializeNBT() {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.setBoolean("isCrafting", isCrafting);
    nbt.setTag("process", process.serializeNBT());
    return nbt;
  }

  @Override
  public void deserializeNBT(NBTTagCompound nbt) {
    isCrafting = nbt.getBoolean("isCrafting");
    process.deserializeNBT(nbt.getCompoundTag("process"));
  }
}
