package com.historydevteam.historymod.util;

import com.historydevteam.historymod.registry.CommonRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Reference {

  public static final String MOD_ID = "historymod";
  public static final String NAME = "HistoryMod";
  public static final String VERSION = "1.0";
  public static final String ACCEPTED_VERSIONS = "[1.12.2]";
  public static final String CLIENT_PROXY_CLASS = "com.historydevteam.historymod.proxy.ClientProxy";
  public static final String COMMON_PROXY_CLASS = "com.historydevteam.historymod.proxy.CommonProxy";
  public static final CreativeTabs HISTORY_CREATIVE_TAB = new CreativeTabs("historymod") {
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack createIcon() {
      return new ItemStack(CommonRegistry.STONE_WHEEL);
    }
  };


}
