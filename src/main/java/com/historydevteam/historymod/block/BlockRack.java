package com.historydevteam.historymod.block;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockRack extends Block {

  public BlockRack() {
    super(Material.ROCK);
    setTranslationKey(Reference.MOD_ID + ".rack");
    setRegistryName(Reference.MOD_ID, "rack");
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
  }
}
