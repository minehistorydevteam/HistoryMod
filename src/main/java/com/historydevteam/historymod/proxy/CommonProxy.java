package com.historydevteam.historymod.proxy;

import com.historydevteam.historymod.registry.CommonRegistry;
import com.historydevteam.historymod.worldgen.HistoryModWorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

  private final CommonRegistry registry;

  public CommonProxy() {
    this(new CommonRegistry());
  }

  CommonProxy(CommonRegistry registry) {
    this.registry = registry;
  }

  public void preInit(FMLPreInitializationEvent event) {
    GameRegistry.registerWorldGenerator(new HistoryModWorldGenerator(), 3);
    MinecraftForge.EVENT_BUS.register(registry);
  }

  public void init(FMLInitializationEvent event) {

  }

  public void postInit(FMLPostInitializationEvent event) {

  }
}
