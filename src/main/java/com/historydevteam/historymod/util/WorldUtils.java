package com.historydevteam.historymod.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldUtils {

  public static void dropItem(World world, BlockPos pos, ItemStack item, boolean jump) {
    if (item.isEmpty()) {
      return;
    }
    if (world.isRemote) {
      return;
    }
    double d0 = 0.5;
    double d1 = 0.5;
    double d2 = 0.5;

    if (jump) {
      float f = 0.05f;
      d0 = (world.rand.nextFloat() * f) + (1.0f - f) * 0.5;
      d1 = (world.rand.nextFloat() * f) + (1.0f - f) * 0.5;
      d2 = (world.rand.nextFloat() * f) + (1.0f - f) * 0.5;
    }

    EntityItem entityItem = new EntityItem(world, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, item);
    entityItem.setDefaultPickupDelay();
    if (!jump) {
      entityItem.motionX = 0;
      entityItem.motionY = 0;
      entityItem.motionZ = 0;
    }
    world.spawnEntity(entityItem);
  }
}
