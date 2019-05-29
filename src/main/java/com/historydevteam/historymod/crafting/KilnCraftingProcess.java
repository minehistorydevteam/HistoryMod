package com.historydevteam.historymod.crafting;

import com.historydevteam.historymod.crafting.recipes.KilnRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class KilnCraftingProcess implements ICraftingProcess {

  private KilnRecipe cacheRecipe = null;

  private ItemStackHandler inventory;
  private int inputSlot;
  private int outputSlot;

  public KilnCraftingProcess(ItemStackHandler inventory, int inputSlot, int outputSlot) {
    this.inventory = inventory;
    this.inputSlot = inputSlot;
    this.outputSlot = outputSlot;
  }

  @Override
  public void craft() {
    KilnRecipe recipe = getRecipe();
    if (recipe == null) {
      throw new RuntimeException("Recipe was null: " + inventory.serializeNBT());
    }
    inventory.extractItem(inputSlot, recipe.getInput().getCount(), false);
    inventory.insertItem(outputSlot, recipe.getOutput(), false);
  }

  @Override
  public boolean canCraft() {
    KilnRecipe recipe = getRecipe();
    if (recipe == null) return false;
    ItemStack input = getInput();

    if (input.getCount() != inventory.extractItem(inputSlot, recipe.getInput().getCount(), true).getCount()) {
      return false;
    }

    if (!inventory.insertItem(outputSlot, recipe.getOutput(), true).isEmpty()) {
      return false;
    }

    return true;
  }

  @Override
  public float getDuration() {
    KilnRecipe recipe = getRecipe();
    if (recipe == null) return 1;
    return recipe.getDuration();
  }

  private ItemStack getInput() {
    return inventory.extractItem(inputSlot, 1, true);
  }

  private KilnRecipe getRecipe() {
    ItemStack input = getInput();

    if (input.isEmpty()) {
      return null;
    }

    if (cacheRecipe != null && cacheRecipe.getInput().equals(input)) {
      return cacheRecipe;
    }

    ItemStackKey key = ItemStackKey.of(input);
    KilnRecipe recipe = RecipeManager.KILN_RECIPES.getObject(key);

    if (recipe != null) {
      cacheRecipe = recipe;
    }
    return recipe;
  }

}
