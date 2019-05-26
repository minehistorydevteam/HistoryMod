package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.block.*;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class Blocks {

  public static final BlockPot POT_DARK = new BlockPot(BlockPot.Variant.DARK);
  public static final BlockPot POT_LIGHT = new BlockPot(BlockPot.Variant.LIGHT);
  public static final BlockFlintOre FLINT_ORE = new BlockFlintOre();
  public static final BlockQuartziteOre QUARTZITE_ORE = new BlockQuartziteOre();
  public static final BlockRack RACK = new BlockRack();

  public static final BlockModel WAX_CANDLE = new BlockModel(Material.WOOD, "wax_candle")
      .setAABB(new AxisAlignedBB(3f / 16f, 0, 3f / 16f, 13f / 16f, 13f / 16f, 13f / 16f))
      .setLightEmission(10);

  public static final BlockModel PEBBLES = new BlockModel(new Material(MapColor.STONE), "pebbles") {
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      worldIn.destroyBlock(pos, true);
      return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
  }
      .setAABB(Block.FULL_BLOCK_AABB.contract(0, 14f / 16f, 0));

  public static final Block STICKS = new BlockModel(Material.WOOD, "sticks"){
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Items.STICK;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      worldIn.destroyBlock(pos, true);
      return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }


  }
      .setAABB(Block.FULL_BLOCK_AABB.contract(0, 15f / 16f, 0));

  public static final Block FIREPIT = new BlockRotableModel(Material.WOOD, "firepit");
  public static final Block UNFIRED_CLAY_KILN = new BlockRotableModel(Material.CLAY, "unfired_clay_kiln");
  public static final Block FIRED_CLAY_KILN = new BlockRotableModel(Material.ROCK, "fired_clay_kiln");
  public static final Block PRIMITIVE_WORKBENCH = new BlockModel(Material.WOOD, "primitive_workbench");

  static ItemBlock getItemBlock(Block block) {
    if (block instanceof ISpecialItemBlock) {
      return ((ISpecialItemBlock) block).getItemBlock();
    } else {
      return (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
    }
  }

  public static void init() {
    CommonRegistry.BLOCKS_TO_REGISTER.add(POT_DARK);
    CommonRegistry.BLOCKS_TO_REGISTER.add(POT_LIGHT);
    CommonRegistry.BLOCKS_TO_REGISTER.add(FLINT_ORE);
    CommonRegistry.BLOCKS_TO_REGISTER.add(QUARTZITE_ORE);
    CommonRegistry.BLOCKS_TO_REGISTER.add(RACK);
    CommonRegistry.BLOCKS_TO_REGISTER.add(WAX_CANDLE);
    CommonRegistry.BLOCKS_TO_REGISTER.add(PEBBLES);
    CommonRegistry.BLOCKS_TO_REGISTER.add(STICKS);
    CommonRegistry.BLOCKS_TO_REGISTER.add(FIREPIT);
    CommonRegistry.BLOCKS_TO_REGISTER.add(UNFIRED_CLAY_KILN);
    CommonRegistry.BLOCKS_TO_REGISTER.add(FIRED_CLAY_KILN);
    CommonRegistry.BLOCKS_TO_REGISTER.add(PRIMITIVE_WORKBENCH);
  }

  private static Block create(Material material, String name, CreativeTabs tabs) {
    return new Block(material)
        .setCreativeTab(tabs)
        .setTranslationKey(Reference.MOD_ID + "." + name)
        .setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
  }
}