package com.historydevteam.historymod.tileentity.containers;

import net.minecraftforge.items.ItemStackHandler;

public class Inventory extends ItemStackHandler {

  public Inventory(int size) {
    super(size);
  }

  @Override
  protected void onContentsChanged(int slot) {

  }
}
