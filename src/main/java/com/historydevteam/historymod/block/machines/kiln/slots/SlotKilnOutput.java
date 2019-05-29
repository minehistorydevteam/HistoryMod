package com.historydevteam.historymod.block.machines.kiln.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotKilnOutput extends Slot {
  public SlotKilnOutput(EntityPlayer player, IInventory inventory, int index, int x, int y) {
    super(inventory, index, x, y);
  }

  @Override
  public boolean isItemValid(ItemStack stack) {
    return false;
  }

  @Override
  public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
    this.onCrafting(stack);
    super.onTake(thePlayer, stack);
    return stack;
  }

  @Override
  public ItemStack decrStackSize(int amount) {
    return super.decrStackSize(amount);
  }
}
