package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.block.BlockPot;
import com.historydevteam.historymod.block.ISpecialItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class Blocks {

  public static final BlockPot POT_DARK = new BlockPot(BlockPot.Variant.DARK);
  public static final BlockPot POT_LIGHT = new BlockPot(BlockPot.Variant.LIGHT);

  static ItemBlock getItemBlock(Block block) {
    if(block instanceof ISpecialItemBlock) return ((ISpecialItemBlock)block).getItemBlock();
    else return (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
  }

  public static void init() {
    CommonRegistry.BLOCKS_TO_REGISTER.add(POT_DARK);
    CommonRegistry.BLOCKS_TO_REGISTER.add(POT_LIGHT);
  }
}