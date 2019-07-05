/*package com.historydevteam.historymod.block;

import com.historydevteam.historymod.util.IOnActivate;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class TileBlock extends HMBlock {

  private boolean hasGui;

  public TileBlock(Properties materialIn) {
    super(materialIn);
  }

  public TileBlock hasGui() {
    hasGui = true;
    return this;
  }

  @Override
  public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluidState) {
    if (world.isRemote) {
      TileEntity tile = world.getTileEntity(pos);
      if (tile instanceof HMTileEntity) {
        ((HMTileEntity) tile).onBreak();
      }
    }
    return super.removedByPlayer(state, world, pos, player, willHarvest, fluidState);
  }

  @Override
  public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState,
      boolean isMoving) {
    TileEntity tile = worldIn.getTileEntity(pos);
    if(tile instanceof HMTileEntity) {
      ((HMTileEntity) tile).onBreak();
    }
    worldIn.removeTileEntity(pos);
  }

  @Override
  public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult result) {
    TileEntity tile = worldIn.getTileEntity(pos);
    if (tile instanceof IOnActivate) {
      if (((IOnActivate) tile).onActivated(playerIn)) {
        return true;
      }
    }

    if (hasGui) {
      if (!worldIn.isRemote) {
        //playerIn.openContainer(HistoryMod.instance, Reference.GUI_TILE_ENTITY, worldIn, pos.getX(), pos.getY(), pos.getZ());
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
}
*/
