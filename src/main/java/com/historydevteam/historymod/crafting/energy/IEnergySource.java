package com.historydevteam.historymod.crafting.energy;

public interface IEnergySource {

  float getSpeed();

  int getMaxEnergyPerTick();

  void useEnergy(int amount);
}
