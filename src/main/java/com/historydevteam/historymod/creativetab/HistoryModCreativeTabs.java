package com.historydevteam.historymod.creativetab;

import com.historydevteam.historymod.registry.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HistoryModCreativeTabs extends CreativeTabs {
  public HistoryModCreativeTabs() {
    super("historymod");
  }

  @Override
  @SideOnly(Side.CLIENT)
  public ItemStack createIcon() {
    return new ItemStack(Items.STONE_WHEEL);
  }
}
