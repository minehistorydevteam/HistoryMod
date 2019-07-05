/*
package com.historydevteam.historymod.features.kiln;

import com.historydevteam.historymod.blocks.BlockProperties;
import com.historydevteam.historymod.blocks.RotatableBlock;
import com.historydevteam.historymod.blocks.TileBlock;
import net.minecraft.blocks.Block;
import net.minecraft.blocks.BlockState;
import net.minecraft.blocks.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class KilnBlock extends TileBlock {

  public KilnBlock(Properties material) {
    super(material);
    setDefaultState(getDefaultState().with(BlockProperties.BURNING, false).with(RotatableBlock.ORIENTATION, Direction.NORTH));
  }

  @Nullable
  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new TileKiln();
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    worldIn.setBlockState(pos, state.with(RotatableBlock.ORIENTATION, placer.getHorizontalFacing()));
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(RotatableBlock.ORIENTATION, BlockProperties.BURNING);
  }

  @Override
  public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
    return state.with(RotatableBlock.ORIENTATION, direction.rotate(state.get(RotatableBlock.ORIENTATION)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return state.with(RotatableBlock.ORIENTATION, mirrorIn.mirror(state.get(RotatableBlock.ORIENTATION)));
  }
}
*/
