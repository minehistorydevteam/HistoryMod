package com.historydevteam.historymod.item;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nullable;

public class ItemStoneWheel extends Item {

  public ItemStoneWheel() {
    setRegistryName(Reference.MOD_ID, "stone_wheel");
    setTranslationKey("stone_wheel");
  }

  @Nullable
  @Override
  public CreativeTabs getCreativeTab() {
    return Reference.HISTORY_CREATIVE_TAB;
  }
}
