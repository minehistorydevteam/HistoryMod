package com.historydevteam.historymod.proxy;

import com.historydevteam.historymod.registry.CommonRegistry;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

  private final CommonRegistry registry;

  public CommonProxy() {
    this(new CommonRegistry());
  }

  CommonProxy(CommonRegistry registry) {
    this.registry = registry;
  }

  public void preInit() {
    //GameRegistry.registerWorldGenerator(new HistoryModWorldGenerator(), 3);
    MinecraftForge.EVENT_BUS.register(registry);
    //NetworkRegistry.INSTANCE.registerGuiHandler(HistoryMod.instance, new GuiHandler());
  }
}
