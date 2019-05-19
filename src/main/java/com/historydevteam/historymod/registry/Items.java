package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Items {

  public static final Item STONE_WHEEL = create("stone_wheel", Reference.HISTORY_CREATIVE_TAB);

  public static void init() {
    CommonRegistry.ITEMS_TO_REGISTER.add(STONE_WHEEL);
  }

  private static Item create(String name, CreativeTabs tabs) {
    return new Item().setCreativeTab(tabs).setTranslationKey(name)
        .setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
  }
}
