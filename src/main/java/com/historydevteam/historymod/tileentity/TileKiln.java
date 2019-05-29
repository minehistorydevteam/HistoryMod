package com.historydevteam.historymod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

@Tile(name = "kiln")
public class TileKiln extends HMTileEntity implements ITickable {
  private ItemStackHandler inventory = new ItemStackHandler(4);

  public IItemHandler getInventory() {
    return inventory;
  }

  @Override
  public void update() {
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    compound.setTag("inv", inventory.serializeNBT());
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    inventory.deserializeNBT(compound.getCompoundTag("key"));
    super.readFromNBT(compound);
  }
}