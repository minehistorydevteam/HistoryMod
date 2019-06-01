package com.historydevteam.historymod.tileentity.modules;

import com.historydevteam.historymod.crafting.energy.IEnergySource;
import com.historydevteam.historymod.tileentity.containers.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;

public class ModuleFuelSlot extends AbstractModule implements IEnergySource {

  private Inventory inventory;
  private int slot;
  private int burnTime;
  private int maxBurnTime;
  private int cooldown;

  public ModuleFuelSlot(Inventory inventory, int slot) {
    this.inventory = inventory;
    this.slot = slot;
  }

  @Override
  public void update() {
    if (getWorld().isRemote) return;

    // If there is no fuel in the slot check every 10 ticks
    if (cooldown > 0) {
      cooldown--;
      return;
    }

    if (maxBurnTime == 0 || burnTime == 0) {
      ItemStack stack = inventory.extractItem(slot, 1, true);
      if (stack.isEmpty()) {
        cooldown = 10;
        return;
      }

      int time = TileEntityFurnace.getItemBurnTime(stack);
      if (time <= 0) {
        cooldown = 10;
        return;
      }

      inventory.extractItem(slot, 1, false);
      // Burning time is stored as RF, which is exactly 10 times more than 1 tick burning fuel
      maxBurnTime = time * 10;
      burnTime = time * 10;
    }
  }

  public int getBurnTime() {
    return burnTime;
  }

  public int getMaxBurnTime() {
    return maxBurnTime;
  }

  @Override
  public float getSpeed() {
    return 1;
  }

  @Override
  public int getMaxEnergyPerTick() {
    return burnTime;
  }

  @Override
  public void useEnergy(int amount) {
    burnTime = Math.max(burnTime - amount, 0);
  }

  @Override
  public String getName() {
    return "fuel_slot";
  }

  @Override
  public NBTTagCompound serializeNBT() {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.setInteger("burnTime", burnTime);
    nbt.setInteger("maxBurnTime", maxBurnTime);
    nbt.setInteger("cooldown", cooldown);
    return nbt;
  }

  @Override
  public void deserializeNBT(NBTTagCompound nbt) {
    burnTime = nbt.getInteger("burnTime");
    maxBurnTime = nbt.getInteger("maxBurnTime");
    cooldown = nbt.getInteger("cooldown");
  }
}
