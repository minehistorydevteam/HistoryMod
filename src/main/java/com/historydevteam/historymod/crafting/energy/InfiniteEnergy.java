package com.historydevteam.historymod.crafting.energy;

public class InfiniteEnergy implements IEnergySource {
  @Override
  public float getSpeed() {
    return 1F;
  }

  @Override
  public int getMaxEnergyPerTick() {
    return 80;
  }

  @Override
  public void useEnergy(int amount) {
    // Do nothing
  }
}
