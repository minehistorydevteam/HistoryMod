package com.historydevteam.historymod.block;

import com.historydevteam.historymod.registry.Items;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockQuartziteOre extends Block {

  public BlockQuartziteOre() {
    super(Material.ROCK);
    setTranslationKey(Reference.MOD_ID + ".quartzite_ore");
    setRegistryName(Reference.MOD_ID, "quartzite_ore");
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
    setHarvestLevel("pickaxe",2 /*0 is wood/gold, 1 is stone, 2 is iron, 3 is diamond*/);
  }

  @Override
  public int quantityDropped(IBlockState state, int fortune, Random random) {
    return 4 + random.nextInt(2);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return Items.QUARTZITE;
  }
}
