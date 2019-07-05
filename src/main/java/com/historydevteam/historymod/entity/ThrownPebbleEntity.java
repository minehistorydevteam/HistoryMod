package com.historydevteam.historymod.entity;

import com.historydevteam.historymod.block.ISpecialItemBlock;
import com.historydevteam.historymod.registry.Blocks;
import com.historydevteam.historymod.registry.Entities;
import com.historydevteam.historymod.util.Reference;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


public class ThrownPebbleEntity extends ProjectileItemEntity{

    @Override
    protected void onImpact(RayTraceResult result) {
        switch(result.getType()) {
            case BLOCK:
                BlockRayTraceResult result1 = ((BlockRayTraceResult)result);
                BlockPos b = result1.getPos().offset(result1.getFace());
                if(world.isAirBlock(b) && result1.getFace() == Direction.UP) {
                    world.setBlockState(b, Blocks.PEBBLES.getDefaultState());
                    remove();
                } else setMotion(new Vec3d(0, 0, 0));
                break;
            case ENTITY:
                Entity e = ((EntityRayTraceResult) result).getEntity();
                e.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 2);
                remove();
        }
    }

    public ThrownPebbleEntity(EntityType<? extends ThrownPebbleEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ThrownPebbleEntity(World world) {
        this(Entities.THROWN_PEBBLE, world);
    }

    public ThrownPebbleEntity(EntityType<? extends ThrownPebbleEntity> type, double x, double y, double z, World worldIn) {
        this(type, worldIn);
    }

    public ThrownPebbleEntity(LivingEntity thrower, World worldIn) {
        super(Entities.THROWN_PEBBLE, thrower, worldIn);
    }

    @Override
    public Item func_213885_i() {
        return ((ISpecialItemBlock) Blocks.PEBBLES).getBlockItem();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        IPacket<?> packet = NetworkHooks.getEntitySpawningPacket(this);
        return packet;
    }
}