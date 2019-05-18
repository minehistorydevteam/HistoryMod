package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.item.ItemStoneWheel;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonRegistry {

  public static final Item STONE_WHEEL = new ItemStoneWheel();

  @SubscribeEvent
  public void addItems(RegistryEvent.Register<Item> itemRegisterEvent) {
    itemRegisterEvent.getRegistry().register(STONE_WHEEL);
  }
}
