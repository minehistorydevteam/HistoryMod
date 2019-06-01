package com.historydevteam.historymod.gui;

import com.historydevteam.historymod.tileentity.HMTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class HMContainer extends Container {

  protected HMTileEntity tile;
  protected EntityPlayer player;
  protected List<InventoryRegion> regions = new ArrayList<>();

  public HMContainer(EntityPlayer player, HMTileEntity tile) {
    this.tile = tile;
    this.player = player;
  }

  public abstract HMGui createGui();

  public void addPlayerSlots() {
    int playerInvStart = inventorySlots.size();
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 9; x++) {
        addSlotToContainer(new Slot(player.inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
      }
    }
    int hotbarStart = inventorySlots.size();
    for (int x = 0; x < 9; x++) {
      addSlotToContainer(new Slot(player.inventory, x, 8 + x * 18, 142));
    }
    regions.add(new InventoryRegion(playerInvStart, playerInvStart + 26, false));
    regions.add(new InventoryRegion(hotbarStart, hotbarStart + 9, false));
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
    if (index >= 0 && index < inventorySlots.size()) {
      Slot slot = inventorySlots.get(index);

      if (!slot.getHasStack()) {
        return ItemStack.EMPTY;
      }

      List<InventoryRegion> slotRanges = new ArrayList<>();

      for (InventoryRegion region : regions) {
        if (index >= region.start && index <= region.end) {
          slotRanges.add(region);
        }
      }
      ItemStack stack = slot.getStack();

      if (tryMergeItemStack(stack, index, slotRanges)) {
        if (stack.getCount() == 0) {
          slot.putStack(ItemStack.EMPTY);
        }
        slot.onSlotChanged();
      }
    }
    return ItemStack.EMPTY;
  }

  private boolean tryMergeItemStack(ItemStack stack, int index, List<InventoryRegion> regions) {
    for (InventoryRegion region : regions) {
      boolean filtered = region.filter == null || region.filter.apply(stack, index);

      if (filtered && mergeItemStack(stack, region.start, region.end + 1, region.inverseDirection)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return true;
  }
}
