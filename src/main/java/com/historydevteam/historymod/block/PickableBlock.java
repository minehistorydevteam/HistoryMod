package com.historydevteam.historymod.block;

import com.historydevteam.historymod.util.Reference;
import com.historydevteam.historymod.util.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class PickableBlock extends HMBlock implements ISpecialItemBlock {

  private Item i;

  public PickableBlock(Properties properties) {
    super(properties);
  }

  @Override
  public Item getBlockItem() {
    return
        i == null ? new BlockItem(this, new Item.Properties().group(Reference.HISTORY_ITEM_GROUP))
            .setRegistryName(this.getRegistryName()) : i;
  }

  public PickableBlock setItem(Item item) {
    i = item;
    return this;
  }

  @Override
  public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
    if(world.isRemote || !(entity instanceof PlayerEntity)) return;
    PlayerEntity p = (PlayerEntity) entity;
    if(p.inventory.addItemStackToInventory(new ItemStack(this))) {
      world.setBlockState(pos, Blocks.AIR.getDefaultState());
    }
  }

  @Override
  public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos,
      PlayerEntity playerIn, Hand hand, BlockRayTraceResult result) {
    WorldUtils.dropItem(worldIn, pos, new ItemStack(i, 1), false);
    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
    return true;
  }

}
