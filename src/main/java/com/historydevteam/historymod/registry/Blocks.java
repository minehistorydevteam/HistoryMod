package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.block.*;
import com.historydevteam.historymod.item.PebbleItem;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.common.ToolType;

public class Blocks {

  public static final Block DARK_POT = new HMBlock(Block.Properties.create(Material.ROCK))
      .setAABB(new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75, 0.625D, 0.75D))
      .hasModel()
      .setRegistryName(Reference.MOD_ID, "dark_pot");

  public static final Block LIGHT_POT = new HMBlock(Block.Properties.create(Material.ROCK).lightValue(10).hardnessAndResistance(3f))
      .setAABB(new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75, 0.625D, 0.75D))
      .hasModel()
      .setRegistryName(Reference.MOD_ID, "light_pot");

  public static final Block FLINT_ORE = IHarvestableBlock.setHarvestLevel(
      ToolType.PICKAXE, HMBlock.HarvestLevel.WOOD, new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f)))
      .setRegistryName(Reference.MOD_ID, "flint_ore");

  public static final Block QUARTZITE_ORE = IHarvestableBlock.setHarvestLevel(
      ToolType.PICKAXE, IHarvestableBlock.HarvestLevel.WOOD, new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f)))
      .setRegistryName(Reference.MOD_ID, "quartzite_ore");

  public static final Block RACK = new RotatableBlock(Block.Properties.create(Material.ROCK))
      .hasModel()
      .setRegistryName(Reference.MOD_ID, "rack");

  public static final Block WAX_CANDLE = new HMBlock(Block.Properties.create(Material.WOOD).lightValue(10))
      .hasModel()
      .setAABB(new AxisAlignedBB(3.5 / 16, 0, 3.5 / 16, 12.5 / 16, 13 / 16d, 12.5 / 16))
      .setRegistryName("wax_candle");

  public static final Block PEBBLES = new PickableBlock(Block.Properties.create(Material.ROCK))
      .setItem(new PebbleItem(new Item.Properties()).setRegistryName(Reference.MOD_ID, "pebbles"))
      .setHarvestLevel(ToolType.PICKAXE, HMBlock.HarvestLevel.HAND)
      .setAABB(VoxelShapes.fullCube().getBoundingBox().contract(0, 14 / 16d, 0))
      .setRegistryName(Reference.MOD_ID, "pebbles");

  public static final Block STICKS = new PickableBlock(Block.Properties.create(Material.WOOD))
      .setItem(Items.STICK)
      .setHarvestLevel(ToolType.PICKAXE, HMBlock.HarvestLevel.HAND)
      .setAABB(VoxelShapes.fullCube().getBoundingBox().contract(0, 15 / 16d, 0))
      .setRegistryName(Reference.MOD_ID, "sticks");

  public static final Block FIREPIT = new RotatableBlock(Block.Properties.create(Material.WOOD))
      .hasModel()
      .setRegistryName(Reference.MOD_ID, "firepit");

  public static final Block UNFIRED_CLAY_KILN = new RotatableBlock(Block.Properties.create(Material.CLAY))
      .setHarvestLevel(ToolType.PICKAXE, HMBlock.HarvestLevel.WOOD)
      .setAABB(new AxisAlignedBB(1 / 8d, 0, 1 / 8d, 1 - (1 / 8d), 1d, 1 - (1/8d)))
      .setRegistryName(Reference.MOD_ID, "unfired_clay_kiln");

/*  public static final Block FIRED_CLAY_KILN = new KilnBlock(Block.Properties.create(Material.ROCK))
      .hasGui()
      .setHarvestLevel(ToolType.PICKAXE, HMBlock.HarvestLevel.STONE)
      .setRegistryName("fired_clay_kiln");*/

  public static final Block PRIMITIVE_WORKBENCH = new HMBlock(Block.Properties.create(Material.WOOD))
      .hasModel()
      .setRegistryName(Reference.MOD_ID, "primitive_workbench");

  static Item getBlockItem(Block block) {
    return (block instanceof ISpecialItemBlock)
        ? ((ISpecialItemBlock) block).getBlockItem()
        : (BlockItem) new BlockItem(block, new Item.Properties().group(Reference.HISTORY_ITEM_GROUP))
        .setRegistryName(block.getRegistryName());
  }
}