package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.block.*;
import com.historydevteam.historymod.block.machines.kiln.BlockKiln;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

import static com.historydevteam.historymod.registry.Items.QUARTZITE;

public class Blocks {

  public static final Block POT_DARK = new HMBlock(Material.ROCK)
      .setName("pot_dark")
      .hasModel()
      .setAABB(new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75, 0.625D, 0.75D));

  public static final Block POT_LIGHT = new HMBlock(Material.ROCK)
      .setName("pot_light")
      .hasModel()
      .setLightEmission(10)
      .setAABB(new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75, 0.625D, 0.75D));

  public static final Block FLINT_ORE = new BlockFragileOre(Material.ROCK)
      .setDrops(Items.FLINT, 4, 6)
      .setName("flint_ore")
      .setHarvestLevel("pickaxe", HMBlock.HarvestLevel.STONE)
      .setHardness(3.0f);

  public static final Block QUARTZITE_ORE = new BlockFragileOre(Material.ROCK)
      .setDrops(QUARTZITE, 4, 6)
      .setName("quartzite_ore")
      .setHarvestLevel("pickaxe", HMBlock.HarvestLevel.STONE)
      .setHardness(3.0f);

  public static final Block RACK = new BlockRotable(Material.ROCK)
      .setName("rack")
      .hasModel();

  public static final Block WAX_CANDLE = new HMBlock(Material.WOOD)
      .setName("wax_candle")
      .hasModel()
      .setAABB(new AxisAlignedBB(3f / 16f, 0, 3f / 16f, 13f / 16f, 13f / 16f, 13f / 16f))
      .setLightEmission(10);

  public static final Block PEBBLES = new BlockPickable(Material.ROCK)
      .setName("pebbles")
      .setHarvestLevel("pickaxe", HMBlock.HarvestLevel.HAND)
      .hasModel()
      .setAABB(Block.FULL_BLOCK_AABB.contract(0, 14f / 16f, 0));

  public static final Block STICKS = new BlockPickable(Material.WOOD)
      .setDrops(Items.STICK, 1, 1)
      .setName("sticks")
      .setHarvestLevel("pickaxe", HMBlock.HarvestLevel.HAND)
      .hasModel()
      .setAABB(Block.FULL_BLOCK_AABB.contract(0, 15f / 16f, 0));

  public static final Block FIREPIT = new BlockRotable(Material.WOOD)
      .setName("firepit")
      .hasModel();

  public static final Block CLAY_KILN = new BlockKiln("clay_kiln");

  public static final Block PRIMITIVE_WORKBENCH = new HMBlock(Material.WOOD)
      .setName("primitive_workbench")
      .hasModel();

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