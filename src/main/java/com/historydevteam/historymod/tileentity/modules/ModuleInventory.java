/*
package com.historydevteam.historymod.tileentity.modules;

import com.historydevteam.historymod.tileentity.containers.Inventory;
import com.historydevteam.historymod.tileentity.containers.InventoryView;
import com.historydevteam.historymod.util.WorldUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

*/
/**
 * This module wraps an inventory adding the following features
 * - Save/loading of the inventory contents
 * - Item drop when the blocks is mined
 * - Provides access to the inventory contents using Capabilities
 * <p>
 * Note: don't include more than 1 inventory module,
 * instead use a bigger inventory with controlled access.
 *//*

public class ModuleInventory extends AbstractModule {

  private Inventory inventory;
  private InventoryView view;

  public ModuleInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public ModuleInventory(Inventory inventory, int[] inputSlots, int[] outputSlots) {
    this.inventory = inventory;
    this.view = new InventoryView(inventory, inputSlots, outputSlots);
  }

  public ItemStackHandler getInventory() {
    return inventory;
  }

  @Override
  public void onBreak() {
    if (getWorld().isRemote) return;
    for (int slot = 0; slot < inventory.getSlots(); slot++) {
      ItemStack stack = inventory.getStackInSlot(slot);
      if (!stack.isEmpty()) {
        WorldUtils.dropItem(getWorld(), getPos(), stack, true);
      }
    }
  }

  @Override
  public <T> T getCapability(Capability<T> cap, Direction facing) {
    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      //noinspection unchecked
      return view != null ? (T) view : (T) inventory;
    }
    return null;
  }

  @Override
  public String getName() {
    return "inventory";
  }

  @Override
  public CompoundNBT serializeNBT() {
    return inventory.serializeNBT();
  }

  @Override
  public void deserializeNBT(CompoundNBT nbt) {
    inventory.deserializeNBT(nbt);
  }
}
*/
