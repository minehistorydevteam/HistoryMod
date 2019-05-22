package com.historydevteam.historymod.block;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockRack extends Block {

  public BlockRack() {
    super(Material.ROCK);
    setTranslationKey(Reference.MOD_ID + ".rack");
    setRegistryName(Reference.MOD_ID, "rack");
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
  }
}
