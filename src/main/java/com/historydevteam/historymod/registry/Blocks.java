package com.historydevteam.historymod.registry;

import java.util.Random;

import com.historydevteam.historymod.block.BlockFlintOre;
import com.historydevteam.historymod.block.BlockModel;
import com.historydevteam.historymod.block.BlockPot;
import com.historydevteam.historymod.block.BlockQuartziteOre;
import com.historydevteam.historymod.block.BlockRack;
import com.historydevteam.historymod.block.BlockRotableModel;
import com.historydevteam.historymod.block.ISpecialItemBlock;
import com.historydevteam.historymod.block.machines.kiln.BlockKiln;
import com.historydevteam.historymod.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
  public static final Block CLAY_KILN = new BlockKiln("clay_kiln");
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