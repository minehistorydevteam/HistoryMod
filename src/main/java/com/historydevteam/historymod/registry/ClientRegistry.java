package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.item.ISpecialRender;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientRegistry extends CommonRegistry {

  @Override
  @SubscribeEvent
  public void addItems(RegistryEvent.Register<Item> itemRegisterEvent) {
    super.addItems(itemRegisterEvent);
    CommonRegistry.ITEMS_TO_REGISTER.forEach(item -> ModelLoader
        .setCustomModelResourceLocation(item, 0,
            item instanceof ISpecialRender ? ((ISpecialRender) item)
                .getModelResourceLocation() : new ModelResourceLocation(item.getRegistryName(),
                "normal")));
  }
}
