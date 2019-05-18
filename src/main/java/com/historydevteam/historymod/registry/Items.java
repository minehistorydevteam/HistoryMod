package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.item.ItemStoneWheel;
import net.minecraft.item.Item;

public class Items {

  public static final Item STONE_WHEEL = new ItemStoneWheel();

  public static void init() {
    CommonRegistry.ITEMS_TO_REGISTER.add(STONE_WHEEL);
  }
}
