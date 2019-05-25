package com.historydevteam.historymod.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRack extends BlockModel {

  public BlockRack() {
    super(Material.ROCK, "rack");
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, BlockProperties.ORIENTATION);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(BlockProperties.ORIENTATION).getHorizontalIndex();
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState()
        .withProperty(BlockProperties.ORIENTATION, EnumFacing.byHorizontalIndex(meta));
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
                              ItemStack stack) {
    IBlockState facingState = state.withProperty(BlockProperties.ORIENTATION, placer.getHorizontalFacing());
    worldIn.setBlockState(pos, facingState);
  }
}
