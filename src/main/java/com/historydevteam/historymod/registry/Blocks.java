package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.block.*;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class Blocks {

  public static final BlockPot POT_DARK = new BlockPot(BlockPot.Variant.DARK);
  public static final BlockPot POT_LIGHT = new BlockPot(BlockPot.Variant.LIGHT);
  public static final BlockFlintOre FLINT_ORE = new BlockFlintOre();
  public static final BlockQuartziteOre QUARTZITE_ORE = new BlockQuartziteOre();
  public static final BlockRack RACK = new BlockRack();

  public static final BlockModel WAX_CANDLE = new BlockModel(Material.WOOD, "wax_candle")
      .setAABB(new AxisAlignedBB(3f / 16f, 0, 3f / 16f, 13f / 16f, 13f / 16f, 13f / 16f))
      .setLightEmission(10);

  public static final BlockModel PEBBLES = new BlockModel(Material.ROCK, "pebbles")
      .setAABB(Block.FULL_BLOCK_AABB.contract(0, 14f / 16f, 0));

  public static final Block STICKS = new BlockModel(Material.WOOD, "sticks")
      .setAABB(Block.FULL_BLOCK_AABB.contract(0, 15f / 16f, 0));

  public static final Block FIREPIT = new BlockRotableModel(Material.WOOD, "firepit");
  public static final Block UNFIRED_CLAY_KILN = new BlockRotableModel(Material.CLAY, "unfired_clay_kiln");
  public static final Block FIRED_CLAY_KILN = new BlockRotableModel(Material.ROCK, "fired_clay_kiln");
  public static final Block PRIMITIVE_WORKBENCH = new BlockModel(Material.WOOD, "primitive_workbench");

  static ItemBlock getItemBlock(Block block) {
    return (block instanceof ISpecialItemBlock)
        ? ((ISpecialItemBlock) block).getItemBlock()
        : (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
  }

  private static Block create(Material material, String name, CreativeTabs tabs) {
    return new Block(material)
        .setCreativeTab(tabs)
        .setTranslationKey(Reference.MOD_ID + "." + name)
        .setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
  }
}