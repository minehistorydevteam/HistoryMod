package com.historydevteam.historymod.gui;

import com.historydevteam.historymod.tileentity.HMTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class HMContainer extends Container {

  protected HMTileEntity tile;
  protected EntityPlayer player;

  public HMContainer(EntityPlayer player, HMTileEntity tile) {
    this.tile = tile;
    this.player = player;
  }

  public abstract HMGui createGui();

  public void addPlayerSlots() {
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 9; x++) {
        addSlotToContainer(new Slot(player.inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
      }
    }
    for (int x = 0; x < 9; x++) {
      addSlotToContainer(new Slot(player.inventory, x, 8 + x * 18, 142));
    }
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return true;
  }
}
