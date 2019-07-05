package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonRegistry {

  protected static final List<Block> BLOCKS_TO_REGISTER = RegistryUtil
      .getObjectsFromStaticFields(Blocks.class, Block.class);

  @SubscribeEvent
  public static void addItems(RegistryEvent.Register<Item> event) {
    List<Item> items = RegistryUtil.getObjectsFromStaticFields(Items.class, Item.class);
    for(Block block : BLOCKS_TO_REGISTER) {
      Item i = Blocks.getBlockItem(block);
      if(!event.getRegistry().containsKey(i.getRegistryName())) items.add(i);
      items.forEach(System.out::println);
    }
    items.forEach(event.getRegistry()::register);
  }

  @SubscribeEvent
  public static void addEntities(RegistryEvent.Register<EntityType<?>> event) {
    RegistryUtil.getObjectsFromStaticFields(Entities.class, EntityType.class)
        .forEach(event.getRegistry()::register);
  }

  @SubscribeEvent
  public static void addBlocks(RegistryEvent.Register<Block> event) {
    BLOCKS_TO_REGISTER.forEach(event.getRegistry()::register);
  }

  @SubscribeEvent
  public static void add(RegistryEvent.Register<CookingRecipeSerializer<AbstractCookingRecipe>> e){}
}