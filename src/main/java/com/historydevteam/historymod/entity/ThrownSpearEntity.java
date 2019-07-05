package com.historydevteam.historymod.entity;

import com.historydevteam.historymod.registry.Entities;
import com.historydevteam.historymod.registry.Items;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ThrownSpearEntity extends AbstractArrowEntity {

  //private static final DataParameter<ItemStack> SPEAR = EntityDataManager.createKey(ThrownSpearEntity.class, DataSerializers.ITEMSTACK);

  public ThrownSpearEntity(World world) {
    super(Entities.THROWN_SPEAR, world);
  }

  public ThrownSpearEntity(EntityType<? extends ThrownSpearEntity> type, World worldIn) {
    super(type, worldIn);
  }

  public ThrownSpearEntity(double x, double y, double z, World world) {
    super(Entities.THROWN_SPEAR, x, y, z, world);
  }

  public ThrownSpearEntity(LivingEntity shooter, World world) {
    super(Entities.THROWN_SPEAR, shooter, world);
  }

/*  public void setSpearItem(ItemStack item) {
    dataManager.set(SPEAR, item);
  }

  public ItemStack getSpearItem() {
    return dataManager.get(SPEAR).copy();
  }*/

  protected void registerData() {
    super.registerData();
    //this.dataManager.register(SPEAR, ItemStack.EMPTY);
  }

  @Override
  protected ItemStack getArrowStack() {
    return //getSpearItem();
    new ItemStack(Items.SPEAR);
  }

  @Override
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}