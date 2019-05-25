package com.historydevteam.historymod.block;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockModel extends Block {

  public AxisAlignedBB aabb;

  public BlockModel(Material materialIn, String name) {
    super(materialIn);
    setTranslationKey(Reference.MOD_ID + "." + name);
    setRegistryName(Reference.MOD_ID, name);
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
  }

  public BlockModel setAABB(AxisAlignedBB aabb) {
    this.aabb = aabb;
    return this;
  }

  public BlockModel setLightEmission(int value) {
    setLightLevel(value / 16f);
    return this;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    if (aabb == null) {
      return FULL_BLOCK_AABB;
    }
    return aabb;
  }

  @Override
  public boolean isFullBlock(IBlockState state) {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }
}
