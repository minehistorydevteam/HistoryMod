package com.historydevteam.historymod.entity;

import com.historydevteam.historymod.registry.Blocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityPebble extends EntityArrow {
    public EntityPebble(World worldIn) {
        super(worldIn);
    }

    public EntityPebble(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Blocks.PEBBLES, 1);
    }

    public EntityPebble(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }
}
