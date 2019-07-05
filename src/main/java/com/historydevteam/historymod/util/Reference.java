package com.historydevteam.historymod.util;

import com.historydevteam.historymod.itemgroup.HistoryGroup;
import net.minecraft.item.ItemGroup;

public class Reference {

  public static final String MOD_ID = "historymod";
  public static final String NAME = "HistoryMod";
  public static final String VERSION = "1.0";
  public static final String ACCEPTED_VERSIONS = "[1.12.2]";
  public static final String CLIENT_PROXY_CLASS = "com.historydevteam.historymod.proxy.ClientProxy";
  public static final String COMMON_PROXY_CLASS = "com.historydevteam.historymod.proxy.CommonProxy";
  public static final ItemGroup HISTORY_ITEM_GROUP = new HistoryGroup();
  public static final int GUI_TILE_ENTITY = 1;

  public static final int SYNC_BURN_TIME = 0;
  public static final int SYNC_MAX_BURN_TIME = 1;
  public static final int SYNC_CRAFTING_PROGRESS = 2;
}
