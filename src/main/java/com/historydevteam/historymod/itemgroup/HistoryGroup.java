package com.historydevteam.historymod.itemgroup;

import com.historydevteam.historymod.registry.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HistoryGroup extends ItemGroup {
  public HistoryGroup() {
    super("historymod");
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public ItemStack createIcon() {
    return new ItemStack(Items.STONE_WHEEL);
  }
}
