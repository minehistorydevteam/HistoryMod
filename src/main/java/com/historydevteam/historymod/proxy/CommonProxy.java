package com.historydevteam.historymod.proxy;

import com.historydevteam.historymod.registry.Blocks;
import com.historydevteam.historymod.registry.CommonRegistry;
import com.historydevteam.historymod.registry.Entities;
import com.historydevteam.historymod.registry.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

  private final CommonRegistry registry;

  public CommonProxy() {
    this(new CommonRegistry());
  }

  CommonProxy(CommonRegistry registry) {
    this.registry = registry;
  }

  public void preInit(FMLPreInitializationEvent event) {
    Items.init();
    Blocks.init();
    Entities.init();
    MinecraftForge.EVENT_BUS.register(registry);
  }

  public void init(FMLInitializationEvent event) {

  }

  public void postInit(FMLPostInitializationEvent event) {

  }
}
