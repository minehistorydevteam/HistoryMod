package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.block.BlockFlintOre;
import com.historydevteam.historymod.block.BlockPot;
import com.historydevteam.historymod.block.BlockQuartziteOre;
import com.historydevteam.historymod.block.ISpecialItemBlock;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class Blocks {

  public static final BlockPot POT_DARK = new BlockPot(BlockPot.Variant.DARK);
  public static final BlockPot POT_LIGHT = new BlockPot(BlockPot.Variant.LIGHT);
  public static final BlockFlintOre FLINT_ORE = new BlockFlintOre();
  public static final BlockQuartziteOre QUARTZITE_ORE = new BlockQuartziteOre();

  static ItemBlock getItemBlock(Block block) {
    if(block instanceof ISpecialItemBlock) return ((ISpecialItemBlock)block).getItemBlock();
    else return (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
  }

  public static void init() {
    CommonRegistry.BLOCKS_TO_REGISTER.add(POT_DARK);
    CommonRegistry.BLOCKS_TO_REGISTER.add(POT_LIGHT);
    CommonRegistry.BLOCKS_TO_REGISTER.add(FLINT_ORE);
    CommonRegistry.BLOCKS_TO_REGISTER.add(QUARTZITE_ORE);
  }

  private static Block create(Material material, String name, CreativeTabs tabs) {
    return new Block(material)
        .setCreativeTab(tabs)
        .setTranslationKey(Reference.MOD_ID + "." + name)
        .setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
  }
}