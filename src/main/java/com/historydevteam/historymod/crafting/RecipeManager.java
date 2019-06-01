package com.historydevteam.historymod.crafting;

import com.historydevteam.historymod.crafting.recipes.KilnRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.registry.RegistrySimple;

import static com.historydevteam.historymod.registry.Blocks.FIRED_CLAY_KILN;
import static com.historydevteam.historymod.registry.Blocks.UNFIRED_CLAY_KILN;
import static net.minecraft.init.Items.BRICK;
import static net.minecraft.init.Items.CLAY_BALL;

public class RecipeManager {

  public static final IRegistry<ItemStackKey, KilnRecipe> KILN_RECIPES = new RegistrySimple<>();

  public static void loadRecipes() {
    registerKilnRecipe(stack(UNFIRED_CLAY_KILN), stack(FIRED_CLAY_KILN));
    registerKilnRecipe(stack(CLAY_BALL), stack(BRICK));
  }

  public static void registerKilnRecipe(ItemStack input, ItemStack output) {
    KilnRecipe recipe = new KilnRecipe(ItemStackKey.of(input), ItemStackKey.of(output), 100);
    KILN_RECIPES.putObject(ItemStackKey.of(input), recipe);
  }

  // Shorthands for new ItemStack(...), which is going to be used a lot on recipe

  private static ItemStack stack(Item item) {
    return new ItemStack(item);
  }

  private static ItemStack stack(Block block) {
    return new ItemStack(block);
  }

  private static ItemStack stack(Item item, int count) {
    return new ItemStack(item, count);
  }

  private static ItemStack stack(Block block, int count) {
    return new ItemStack(block, count);
  }
}
