package com.historydevteam.historymod.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockFragileOre extends HMBlock {

  private int min = 1;
  private int max = 1;
  private Item drop = null;

  public BlockFragileOre(Material material) {
    super(material);
  }

  public BlockFragileOre setDrops(Item drop, int min, int max) {
    this.drop = drop;
    this.min = min;
    this.max = max;
    return this;
  }

  @Override
  public int quantityDropped(IBlockState state, int fortune, Random random) {
    int range = max - min;
    if (range > 0) {
      return min + random.nextInt(range);
    } else {
      return min;
    }
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    if (drop == null) {
      return Item.getItemFromBlock(this);
    }
    return drop;
  }
}
