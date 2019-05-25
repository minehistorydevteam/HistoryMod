package com.historydevteam.historymod.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
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
    return getObjectsFromStaticFields(Entities.class, EntityEntry.class);
  }

  protected List<Block> getBlocksToRegister() {
    return getObjectsFromStaticFields(Blocks.class, Block.class);
  }

  protected List<Item> getItemsToRegister() {
    return getObjectsFromStaticFields(Items.class, Item.class);
  }

  private <T> List<T> getObjectsFromStaticFields(Class file, Class<T> type) {
    List<T> list = new ArrayList<>();

    for (Field field : file.getDeclaredFields()) {
      if (Modifier.isStatic(field.getModifiers()) && type.isAssignableFrom(field.getType())) {
        field.setAccessible(true);
        try {
          //noinspection unchecked
          list.add((T) field.get(null));
        } catch (IllegalAccessException e) {
          System.err.println("Unable to access object in field: " + field.toString());
        }
      }
    }

    return list;
  }
}