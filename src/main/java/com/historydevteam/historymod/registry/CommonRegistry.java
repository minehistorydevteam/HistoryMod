package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.List;

public class CommonRegistry {

  @SubscribeEvent
  public void addItems(RegistryEvent.Register<Item> event) {
    List<Item> items = getItemsToRegister();
    for (Block block : getBlocksToRegister()) {
      items.add(Blocks.getItemBlock(block));
    }
    items.forEach(event.getRegistry()::register);
  }

  @SubscribeEvent
  public void addEntities(RegistryEvent.Register<EntityEntry> event) {
    getEntitiesToRegister().forEach(event.getRegistry()::register);
  }

  @SubscribeEvent
  public void addBlocks(RegistryEvent.Register<Block> event) {
    getBlocksToRegister().forEach(event.getRegistry()::register);
  }

  protected List<EntityEntry> getEntitiesToRegister() {
    return RegistryUtil.getObjectsFromStaticFields(Entities.class, EntityEntry.class);
  }

  protected List<Block> getBlocksToRegister() {
    return RegistryUtil.getObjectsFromStaticFields(Blocks.class, Block.class);
  }

  protected List<Item> getItemsToRegister() {
    return RegistryUtil.getObjectsFromStaticFields(Items.class, Item.class);
  }
}