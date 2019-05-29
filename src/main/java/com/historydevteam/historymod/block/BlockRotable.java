package com.historydevteam.historymod.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRotable extends HMBlock {

  public BlockRotable(Material materialIn) {
    super(materialIn);
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
  public IBlockState withRotation(IBlockState state, Rotation rot) {
    return state.withProperty(BlockProperties.ORIENTATION, rot.rotate(state.getValue(BlockProperties.ORIENTATION)));
  }

  @Override
  public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
    return state.withRotation(mirrorIn.toRotation(state.getValue(BlockProperties.ORIENTATION)));
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
                              ItemStack stack) {
    IBlockState facingState = state.withProperty(BlockProperties.ORIENTATION, placer.getHorizontalFacing());
    worldIn.setBlockState(pos, facingState);
  }
}
