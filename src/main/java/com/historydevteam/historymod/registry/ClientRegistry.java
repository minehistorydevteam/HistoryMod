package com.historydevteam.historymod.registry;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class ClientRegistry extends CommonRegistry {

  @Override
  public void addItems(RegistryEvent.Register<Item> itemRegisterEvent) {
    super.addItems(itemRegisterEvent);
    CommonRegistry.ITEMS_TO_REGISTER.forEach(
            item -> ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(
        item.getRegistryName(), "normal")));
  }
}
