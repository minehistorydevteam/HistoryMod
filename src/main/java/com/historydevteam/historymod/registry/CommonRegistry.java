package com.historydevteam.historymod.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class CommonRegistry {

  static final Set<Item> ITEMS_TO_REGISTER = new HashSet<>();
  static final Set<Block> BLOCKS_TO_REGISTER = new HashSet<>();
  static final Set<EntityEntry> ENTITIES_TO_REGISTER = new HashSet<>();

  @SubscribeEvent
  public void addItems(RegistryEvent.Register<Item> event) {
    ITEMS_TO_REGISTER.addAll(transform(BLOCKS_TO_REGISTER, Blocks::getItemBlock));
    ITEMS_TO_REGISTER.forEach(event.getRegistry()::register);
  }

  private static final <I,O> Set<O> transform(Set<I> in, Function<I, O> transformer) {
    Set<O> set = new HashSet<>(in.size());
    in.forEach(item -> set.add(transformer.apply(item)));
    return set;
  }

  @SubscribeEvent
  public void addEntities(RegistryEvent.Register<EntityEntry> event) {
    ENTITIES_TO_REGISTER.forEach(event.getRegistry()::register);
  }

  @SubscribeEvent
  public void addBlocks(RegistryEvent.Register<Block> event) {
    BLOCKS_TO_REGISTER.forEach(event.getRegistry()::register);
  }
}