/*
package com.historydevteam.historymod.tileentity.modules;

import com.historydevteam.historymod.tileentity.containers.Inventory;
import com.historydevteam.historymod.util.Reference;
import com.historydevteam.historymod.util.Sync;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.FurnaceTileEntity;

public class ModuleFuelSlot extends AbstractModule implements IEnergySource {

  private Inventory inventory;
  private int slot;

  @Sync(id = Reference.SYNC_BURN_TIME)
  private int burnTime;
  @Sync(id = Reference.SYNC_MAX_BURN_TIME)
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

      int time = FurnaceTileEntity.func_214001_f().get(stack);
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
  public CompoundNBT serializeNBT() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putInt("burnTime", burnTime);
    nbt.putInt("maxBurnTime", maxBurnTime);
    nbt.putInt("cooldown", cooldown);
    return nbt;
  }

  @Override
  public void deserializeNBT(CompoundNBT nbt) {
    burnTime = nbt.getInt("burnTime");
    maxBurnTime = nbt.getInt("maxBurnTime");
    cooldown = nbt.getInt("cooldown");
  }
}
*/
