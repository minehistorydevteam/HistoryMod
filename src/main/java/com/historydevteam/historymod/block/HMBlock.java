package com.historydevteam.historymod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class HMBlock extends Block implements ICustomAABBBlock<HMBlock>, IHarvestableBlock<HMBlock> {

  private boolean hasModel = false;
  private AxisAlignedBB aabb;

  public HMBlock(Block.Properties properties) {
    super(properties);
  }

  public final HMBlock setHarvestLevel(ToolType toolType, HarvestLevel level) {
    HARVEST_LEVEL.setValue(this, level.value);
    HARVEST_TOOL.setValue(this, toolType);
    return this;
  }

  @Override
  public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return hasModel ? 0 : super.getOpacity(state, worldIn, pos);
  }

  public HMBlock hasModel() {
    hasModel = true;
    return this;
  }

  @Override
  public BlockRenderLayer getRenderLayer() {
    return hasModel ? BlockRenderLayer.CUTOUT : super.getRenderLayer();
  }

  public HMBlock setAABB(AxisAlignedBB aabb) {
    this.aabb = aabb;
    hasModel = true;
    return this;
  }

  @Override
  public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_,
      ISelectionContext p_220053_4_) {
    return aabb == null ? VoxelShapes.fullCube() : VoxelShapes.create(aabb);
  }

}
