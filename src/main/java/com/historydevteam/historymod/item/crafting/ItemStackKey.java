package com.historydevteam.historymod.item.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

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
    return new ItemStackKey(stack.getItem().getRegistryName(), stack.getCount(), stack.getDamage());
  }

  public static ItemStackKey asKey(ItemStack stack) {
    return new ItemStackKey(stack.getItem().getRegistryName(), -1, -1);
  }

  public ItemStack toStack() {
    Item item = ForgeRegistries.ITEMS.getValue(registryName);
    if (item == null) {
      throw new RuntimeException("Found invalid recipe: Item not found (" + registryName + ")");
    }
    ItemStack res = new ItemStack(item, count);
    res.setDamage(itemDamage);
    return res;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ItemStackKey)) return false;
    ItemStackKey that = (ItemStackKey) o;
    return (count == that.count || count == -1 || that.count == -1) &&
        (itemDamage == that.itemDamage || itemDamage == -1 || that.itemDamage == -1) &&
        registryName.equals(that.registryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(registryName);
  }
}
