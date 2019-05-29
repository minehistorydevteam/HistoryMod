package com.historydevteam.historymod.block.machines.kiln;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.historydevteam.historymod.registry.Items;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.Map.Entry;

public class KilnRecipes {
  private static final KilnRecipes INSTANCE = new KilnRecipes();
  private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.create();
  private final Map<ItemStack, Float> experienceList = Maps.newHashMap();

  public static KilnRecipes getInstance() {
    return INSTANCE;
  }

  private KilnRecipes() {
    addSmeltingRecipe(new ItemStack(Items.QUARTZITE), new ItemStack(Items.QUARTZITE), new ItemStack(Items.QUARTZITE), 5.0F);
  }

  public void addSmeltingRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
    if (getSmeltingResult(input1, input2) != ItemStack.EMPTY) return;
    this.smeltingList.put(input1, input2, result);
    this.experienceList.put(result, experience);
  }

  public ItemStack getSmeltingResult(ItemStack input1, ItemStack input2) {
    for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
      if (this.compareItemStacks(input1, entry.getKey())) {
        for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
          if (this.compareItemStacks(input2, ent.getKey())) {
            return ent.getValue();
          }
        }
      }
    }
    return ItemStack.EMPTY;
  }

  private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
    return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
  }

  public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() {
    return this.smeltingList;
  }

  public float getSmeltingExperience(ItemStack stack) {
    for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
      if (this.compareItemStacks(stack, entry.getKey())) {
        return entry.getValue().floatValue();
      }
    }
    return 0.0F;
  }
}
