package com.historydevteam.historymod.crafting.recipes;

import com.historydevteam.historymod.crafting.ItemStackKey;
import net.minecraft.item.ItemStack;

public class KilnRecipe {

  private final ItemStackKey input;
  private final ItemStackKey output;
  private final int duration;

  public KilnRecipe(ItemStackKey input, ItemStackKey output, int duration) {
    this.input = input;
    this.output = output;
    this.duration = duration;
  }

  public KilnRecipe(ItemStack input, ItemStack output, int duration) {
    this.input = ItemStackKey.of(input);
    this.output = ItemStackKey.of(output);
    this.duration = duration;
  }

  public ItemStack getInput() {
    return input.toStack();
  }

  public ItemStack getOutput() {
    return output.toStack();
  }

  public int getDuration() {
    return duration;
  }
}
