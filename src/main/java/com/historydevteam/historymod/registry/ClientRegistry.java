package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.item.ISpecialRender;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ClientRegistry extends CommonRegistry {

  @Override
  @SubscribeEvent
  public void addItems(RegistryEvent.Register<Item> itemRegisterEvent) {
    super.addItems(itemRegisterEvent);
    List<Item> items = getItemsToRegister();
    for (Block block : getBlocksToRegister()) {
      // Don't use Blocks.getItemBlock(block), it creates a new Item instance
      items.add(Item.getItemFromBlock(block));
    }

    for (Item item : items) {
      ModelResourceLocation location = (item instanceof ISpecialRender)
          ? ((ISpecialRender) item).getModelResourceLocation()
          : new ModelResourceLocation(item.getRegistryName(), "normal");

      ModelLoader.setCustomModelResourceLocation(item, 0, location);
    }
  }
}
