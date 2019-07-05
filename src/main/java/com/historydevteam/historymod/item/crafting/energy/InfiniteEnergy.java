package com.historydevteam.historymod.item.crafting.energy;

import net.minecraftforge.energy.IEnergyStorage;

public class InfiniteEnergy implements IEnergyStorage {

  @Override
  public int receiveEnergy(int maxReceive, boolean simulate) {
    return 0;
  }

  @Override
  public int extractEnergy(int maxExtract, boolean simulate) {
    return maxExtract;
  }

  @Override
  public int getEnergyStored() {
    return Integer.MAX_VALUE;
  }

  @Override
  public int getMaxEnergyStored() {
    return Integer.MAX_VALUE;
  }

  @Override
  public boolean canExtract() {
    return true;
  }

  @Override
  public boolean canReceive() {
    return false;
  }
}
