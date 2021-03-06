package com.historydevteam.historymod;

import com.historydevteam.historymod.proxy.CommonProxy;
import com.historydevteam.historymod.registry.Blocks;
import com.historydevteam.historymod.util.Debug;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.HashSet;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class HistoryMod {

  @Instance
  public static HistoryMod instance;

  @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
  public static CommonProxy proxy;

  @EventHandler
  public static void preInit(FMLPreInitializationEvent event) {
    proxy.preInit(event);
  }

  @EventHandler
  public static void init(FMLInitializationEvent event) {
    proxy.init(event);
  }

  @EventHandler
  public static void postInit(FMLPostInitializationEvent event) {
    proxy.postInit(event);
  }

  @EventHandler
  public static void stating(FMLServerStartingEvent event) {
    if (Debug.DEV_ENV) {
      event.registerServerCommand(new Debug.DebugCommand());
    }
  }
}


