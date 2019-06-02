package com.historydevteam.historymod.features.kiln;

import com.historydevteam.historymod.block.BlockProperties;
import com.historydevteam.historymod.block.TileBlock;
import net.minecraft.block.ITileEntityProvider;
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

public class BlockKiln extends TileBlock implements ITileEntityProvider {

  public BlockKiln(Material material) {
    super(material, (world, meta) -> new TileKiln());
    setDefaultState(blockState.getBaseState()
        .withProperty(BlockProperties.ORIENTATION, EnumFacing.NORTH)
        .withProperty(BlockProperties.BURNING, false));
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    IBlockState facingState = state.withProperty(BlockProperties.ORIENTATION, placer.getHorizontalFacing());
    worldIn.setBlockState(pos, facingState);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, BlockProperties.BURNING, BlockProperties.ORIENTATION);
  }

  @SuppressWarnings("deprecation")
  @Override
  public IBlockState getStateFromMeta(int meta) {
    boolean burning = (meta & 0b1000) > 0;
    EnumFacing dir = EnumFacing.byHorizontalIndex(meta & 0b0111);

    return getDefaultState()
        .withProperty(BlockProperties.ORIENTATION, dir)
        .withProperty(BlockProperties.BURNING, burning);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    EnumFacing dir = state.getValue(BlockProperties.ORIENTATION);
    boolean burning = state.getValue(BlockProperties.BURNING);

    return dir.getHorizontalIndex() | (burning ? 0b1000 : 0b000);
  }

  @SuppressWarnings("deprecation")
  @Override
  public IBlockState withRotation(IBlockState state, Rotation rot) {
    return state.withProperty(BlockProperties.ORIENTATION, rot.rotate(state.getValue(BlockProperties.ORIENTATION)));
  }

  @SuppressWarnings("deprecation")
  @Override
  public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
    return state.withRotation(mirrorIn.toRotation(state.getValue(BlockProperties.ORIENTATION)));
  }
}
