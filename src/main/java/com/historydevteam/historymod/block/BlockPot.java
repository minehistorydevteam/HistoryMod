package com.historydevteam.historymod.block;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPot extends Block {

  private static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75, 0.625D, 0.75D);


  public enum Variant {
    DARK, LIGHT;

    public String getTranslationKey() {
      return "pot_" + name().toLowerCase();
    }
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }

  @Override
  public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullBlock(IBlockState state) {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  public BlockPot(Variant variant) {
    super(Material.ROCK);
    setTranslationKey(Reference.MOD_ID + "." + variant.getTranslationKey());
    setRegistryName(Reference.MOD_ID, variant.getTranslationKey());
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
  }
}