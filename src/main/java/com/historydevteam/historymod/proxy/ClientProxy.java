package com.historydevteam.historymod.proxy;

import com.historydevteam.historymod.entity.EntityThrownSpear;
import com.historydevteam.historymod.registry.ClientRegistry;
import com.historydevteam.historymod.render.RenderSpear;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
  public ClientProxy() {
    super(new ClientRegistry());
  }

  @Override
  public void preInit(FMLPreInitializationEvent event) {
    super.preInit(event);
    registerRenderers();
  }

  public void registerRenderers() {
    RenderingRegistry.registerEntityRenderingHandler(EntityThrownSpear.class,
        manager1 -> new RenderSpear(manager1, Minecraft.getMinecraft().getRenderItem()));
  }
}
