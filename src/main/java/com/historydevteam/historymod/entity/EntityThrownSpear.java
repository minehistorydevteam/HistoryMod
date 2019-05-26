package com.historydevteam.historymod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityThrownSpear extends EntityArrow {

  private static final DataParameter<ItemStack> SPEAR = EntityDataManager.createKey(
      EntityThrownSpear.class, DataSerializers.ITEM_STACK);

  public EntityThrownSpear(World worldIn) {
    super(worldIn);
  }

  public EntityThrownSpear(World worldIn, double x, double y, double z) {
    super(worldIn, x, y, z);
  }

  public EntityThrownSpear(World worldIn, EntityLivingBase shooter) {
    super(worldIn, shooter);
  }

  public void setSpearItem(ItemStack item) {
    dataManager.set(SPEAR, item);
  }

  public ItemStack getSpearItem() {
    return dataManager.get(SPEAR).copy();
  }

  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(SPEAR, ItemStack.EMPTY);
  }

  @Override
  protected ItemStack getArrowStack() {
    return getSpearItem();
  }
}
