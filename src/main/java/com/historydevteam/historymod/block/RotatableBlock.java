package com.historydevteam.historymod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.EnumSet;

public class RotatableBlock extends HMBlock {

  public static final DirectionProperty ORIENTATION = DirectionProperty.create("orientation",
      EnumSet.of(Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH));


  public RotatableBlock(Properties properties) {
    super(properties);

  }

  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(ORIENTATION);
  }

  public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
    return state.with(ORIENTATION, direction.rotate(state.get(ORIENTATION)));
  }

  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return state.with(ORIENTATION, mirrorIn.mirror(state.get(ORIENTATION)));
  }

  public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    BlockState facingState = state.with(ORIENTATION, placer.getHorizontalFacing()); worldIn.setBlockState(pos, facingState);
  }
}
