package com.historydevteam.historymod.block.machines.kiln.slots;

import com.historydevteam.historymod.block.machines.kiln.TileEntityKiln;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotKilnFuel extends Slot {
	public SlotKilnFuel(IInventory inventory,int index,int x,int y) {
		super(inventory,index,x,y);
	}
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityKiln.isItemFuel(stack);
	}
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
}
