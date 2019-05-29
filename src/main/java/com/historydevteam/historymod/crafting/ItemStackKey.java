package com.historydevteam.historymod.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class ItemStackKey {

  private final ResourceLocation registryName;
  private final int count;
  private final int itemDamage;

  public ItemStackKey(ResourceLocation registryName, int count, int itemDamage) {
    this.registryName = registryName;
    this.count = count;
    this.itemDamage = itemDamage;
  }

  public static ItemStackKey of(ItemStack stack) {
    return new ItemStackKey(stack.getItem().getRegistryName(), stack.getCount(), stack.getItemDamage());
  }

  public ItemStack toStack() {
    Item item = Item.REGISTRY.getObject(registryName);
    if (item == null) {
      throw new RuntimeException("Found invalid recipe: Item not found (" + registryName + ")");
    }
    return new ItemStack(item, count, itemDamage);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ItemStackKey)) return false;
    ItemStackKey that = (ItemStackKey) o;
    return count == that.count &&
        itemDamage == that.itemDamage &&
        registryName.equals(that.registryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(registryName, count, itemDamage);
  }
}
