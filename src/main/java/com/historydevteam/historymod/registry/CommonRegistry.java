package com.historydevteam.historymod.registry;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonRegistry {

  @SubscribeEvent
  public void addItems(RegistryEvent.Register<Item> itemRegisterEvent) {
    itemRegisterEvent.getRegistry().register(Items.STONE_WHEEL);
  }
}
