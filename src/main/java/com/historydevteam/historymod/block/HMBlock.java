package com.historydevteam.historymod.block;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class HMBlock extends Block {

  private boolean hasModel = false;
  public AxisAlignedBB aabb = FULL_BLOCK_AABB;

  public HMBlock(Material materialIn) {
    super(materialIn);
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
    setHardness(2.5f);
  }

  public HMBlock setName(String name) {
    setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
    setTranslationKey(Reference.MOD_ID + "." + name);
    return this;
  }

  public HMBlock setHarvestLevel(String tool, HarvestLevel level) {
    setHarvestLevel(tool, level.value);
    return this;
  }

  public HMBlock hasModel() {
    hasModel = true;
    lightOpacity = 0;
    return this;
  }

  public HMBlock setAABB(AxisAlignedBB aabb) {
    this.aabb = aabb;
    return this;
  }

  public HMBlock setLightEmission(int value) {
    setLightLevel(value / 16f);
    return this;
  }

  @Override
  public boolean isFullBlock(IBlockState state) {
    return !hasModel;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return !hasModel;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return !hasModel;
  }

  @Override
  public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
    return !hasModel;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return aabb;
  }

  public enum HarvestLevel {
    HAND(-1),
    WOOD(0),
    STONE(1),
    IRON(2),
    DIAMOND(3),
    COBALT(4),
    LEVEL5(5);

    public final int value;

    HarvestLevel(int i) {
      value = i;
    }
  }
}
