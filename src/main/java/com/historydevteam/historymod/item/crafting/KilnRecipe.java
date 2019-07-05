package com.historydevteam.historymod.item.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class KilnRecipe extends AbstractCookingRecipe {
  public KilnRecipe(IRecipeType<?> typeIn, ResourceLocation idIn, String groupIn,
      Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
    super(typeIn, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return null;
  }
}
