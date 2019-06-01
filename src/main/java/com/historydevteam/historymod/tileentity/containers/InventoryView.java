package com.historydevteam.historymod.tileentity.containers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class InventoryView implements IItemHandler {

  private IItemHandler inventory;
  private int[] inputSlots;
  private int[] outputSlots;

  private List<Integer> slotMap;

  public InventoryView(IItemHandler inventory, int[] inputSlots, int[] outputSlots) {
    this.inventory = inventory;
    this.inputSlots = inputSlots;
    this.outputSlots = outputSlots;
    slotMap = generateSlotMap();
  }

  private List<Integer> generateSlotMap() {
    List<Integer> map = new ArrayList<>();

    for (int inputSlot : inputSlots) {
      if (!map.contains(inputSlot)) {
        map.add(inputSlot);
      }
    }

    for (int outputSlot : outputSlots) {
      if (!map.contains(outputSlot)) {
        map.add(outputSlot);
      }
    }

    return map;
  }

  private int toSlot(int index) {
    return slotMap.get(index);
  }

  public IItemHandler getInventory() {
    return inventory;
  }

  @Override
  public int getSlots() {
    return slotMap.size();
  }

  @Nonnull
  @Override
  public ItemStack getStackInSlot(int slot) {
    return inventory.getStackInSlot(toSlot(slot));
  }

  @Nonnull
  @Override
  public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
    slot = toSlot(slot);
    if (!ArrayUtils.contains(inputSlots, slot)) {
      return stack;
    }
    return inventory.insertItem(slot, stack, simulate);
  }

  @Nonnull
  @Override
  public ItemStack extractItem(int slot, int amount, boolean simulate) {
    slot = toSlot(slot);
    if (!ArrayUtils.contains(outputSlots, slot)) {
      return ItemStack.EMPTY;
    }
    return inventory.extractItem(slot, amount, simulate);
  }

  @Override
  public int getSlotLimit(int slot) {
    return inventory.getSlotLimit(toSlot(slot));
  }
}
