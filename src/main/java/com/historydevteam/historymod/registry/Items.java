package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.item.ItemSpear;
import com.historydevteam.historymod.item.ItemStoneWheel;
import net.minecraft.item.Item;

public class Items {

  public static final Item STONE_WHEEL = new ItemStoneWheel();
  public static final Item SPEAR = new ItemSpear();

  public static void init() {
    CommonRegistry.ITEMS_TO_REGISTER.add(STONE_WHEEL);
    CommonRegistry.ITEMS_TO_REGISTER.add(SPEAR);
  }
}
