package com.historydevteam.historymod.tileentity.modules;

import com.historydevteam.historymod.tileentity.containers.Inventory;
import com.historydevteam.historymod.util.WorldUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * This module wraps an inventory adding the following features
 * - Save/loading of the inventory contents
 * - Item drop when the block is mined
 * - Provides access to the inventory contents using Capabilities
 */
public class ModuleInventory extends AbstractModule {

  private Inventory inventory;

  public ModuleInventory(Inventory inv) {
    inventory = inv;
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
  public <T> T getCapability(Capability<T> cap, EnumFacing facing) {
    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      //noinspection unchecked
      return (T) inventory;
    }
    return null;
  }

  @Override
  public String getName() {
    return "inventory";
  }

  @Override
  public NBTTagCompound serializeNBT() {
    return inventory.serializeNBT();
  }

  @Override
  public void deserializeNBT(NBTTagCompound nbt) {
    inventory.deserializeNBT(nbt);
  }
}
