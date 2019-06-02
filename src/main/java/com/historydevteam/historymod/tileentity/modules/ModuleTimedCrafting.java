package com.historydevteam.historymod.tileentity.modules;

import com.historydevteam.historymod.crafting.ICraftingProcess;
import com.historydevteam.historymod.crafting.TimedCrafting;
import com.historydevteam.historymod.crafting.energy.IEnergySource;
import com.historydevteam.historymod.util.Reference;
import com.historydevteam.historymod.util.Sync;
import net.minecraft.nbt.NBTTagCompound;

/**
 * This module represents a crafting process that requires energy for a period of time until the process is complete
 */
public class ModuleTimedCrafting extends AbstractModule {

  private TimedCrafting process;
  private IEnergySource energy;
  private float efficiency;
  private int costPerTick;
  private boolean isCrafting;
  @Sync(id = Reference.SYNC_CRAFTING_PROGRESS)
  private float craftingProgress;

  public ModuleTimedCrafting(ICraftingProcess process, IEnergySource energy, float efficiency, int costPerTick) {
    this.process = new TimedCrafting(process, this::onWorkingTick);
    this.energy = energy;
    this.efficiency = efficiency;
    this.costPerTick = costPerTick;
  }

  @Override
  public void update() {
    if (getWorld().isRemote) return;

    float decimalSpeed = Math.min(energy.getSpeed(), energy.getMaxEnergyPerTick() / costPerTick);

    float scale = costPerTick / efficiency;
    int speed = (int) (Math.floor(decimalSpeed * scale) / scale);

    if (speed > 0) {
      process.tick(getWorld(), speed);
    }

    craftingProgress = process.getProcessingPercent();
    boolean working = process.isCrafting(getWorld());
    if (working != isCrafting) {
      isCrafting = working;
      tile.sendUpdateToNearPlayers();
    }
  }

  public void onWorkingTick(float speed) {
    energy.useEnergy((int) (speed * costPerTick / efficiency));
  }

  public float getProcessingPercent() {
    return craftingProgress;
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
