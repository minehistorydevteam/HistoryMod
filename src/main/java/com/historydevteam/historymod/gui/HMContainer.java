/*
package com.historydevteam.historymod.gui;

import com.historydevteam.historymod.proxy.network.HMNetworkManager;
import com.historydevteam.historymod.tileentity.HMTileEntity;
import com.historydevteam.historymod.util.IBD;
import com.historydevteam.historymod.util.IField;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

import java.util.*;
import java.util.List;

public abstract class HMContainer extends Container {

  public HMTileEntity tile;
  protected PlayerEntity player;
  protected List<InventoryRegion> regions = new ArrayList<>();
  protected Map<Integer, Object> lastState = new HashMap<>();

  public HMContainer(PlayerEntity player, HMTileEntity tile, ContainerType<? extends HMContainer> type, int id) {
    super(type, id);
    this.tile = tile;
    this.player = player;
  }

  public abstract HMContainerScreen createGui();

  public void addPlayerSlots() {
    int playerInvStart = inventorySlots.size();
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 9; x++) {
        addSlot(new Slot(player.inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
      }
    }
    int hotbarStart = inventorySlots.size();
    for (int x = 0; x < 9; x++) {
      addSlot(new Slot(player.inventory, x, 8 + x * 18, 142));
    }
    regions.add(new InventoryRegion(playerInvStart, playerInvStart + 26, false));
    regions.add(new InventoryRegion(hotbarStart, hotbarStart + 9, false));
  }

  @Override
  public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
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

  @Override
  public void detectAndSendChanges() {
    if (tile != null && player instanceof ServerPlayerEntity) {
      Map<Integer, IField<Object>> map = tile.getGuiSyncVariables();
      IBD data = new IBD();
      int count = 0;

      for (Map.Entry<Integer, IField<Object>> entry : map.entrySet()) {
        Object lastValue = lastState.get(entry.getKey());
        Object currentValue = entry.getValue().getValue();

        if (!Objects.equals(lastValue, currentValue)) {
          data.setObject(entry.getKey(), currentValue);
          lastState.put(entry.getKey(), currentValue);
          count++;
        }
      }

      if (count > 0) {
        HMNetworkManager.sendContainerUpdate((ServerPlayerEntity) player, data);
      }
    }
    super.detectAndSendChanges();
  }

  public void handleServerPacket(IBD data) {
    // Do nothing by default, subclasses must override
    if (tile != null) {
      Map<Integer, IField<Object>> map = tile.getGuiSyncVariables();

      for (Map.Entry<Integer, IField<Object>> entry : map.entrySet()) {
        data.withObject(entry.getKey(), value -> entry.getValue().setValue(value));
      }
    }
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
  public boolean canInteractWith(PlayerEntity playerIn) {
    return true;
  }
}
*/
