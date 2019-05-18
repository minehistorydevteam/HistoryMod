package com.historydevteam.historymod.registry;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import java.util.Set;

public class CommonRegistry {

  static final Set<Item> ITEMS_TO_REGISTER = new HashSet<>(1);

  @SubscribeEvent
  public void addItems(RegistryEvent.Register<Item> event) {
    ITEMS_TO_REGISTER.forEach(event.getRegistry()::register);
  }
}
