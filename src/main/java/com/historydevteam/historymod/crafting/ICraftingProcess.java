package com.historydevteam.historymod.crafting;

public interface ICraftingProcess {

  void craft();

  boolean canCraft();

  float getDuration();
}
