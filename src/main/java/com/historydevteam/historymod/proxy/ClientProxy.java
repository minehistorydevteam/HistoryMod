package com.historydevteam.historymod.proxy;

import com.historydevteam.historymod.entity.EntityThrownSpear;
import com.historydevteam.historymod.registry.ClientRegistry;
import com.historydevteam.historymod.registry.Items;
import com.historydevteam.historymod.render.IModelReloadListener;
import com.historydevteam.historymod.render.RenderSpear;
import com.historydevteam.historymod.util.LoreHelper;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends CommonProxy {

  private static final List<IModelReloadListener> RENDERERS = new ArrayList<>();

  public ClientProxy() {
    super(new ClientRegistry());
  }

  @Override
  public void preInit(FMLPreInitializationEvent event) {
    super.preInit(event);
    registerRenderers();
    MinecraftForge.EVENT_BUS.register(this);
  }

  public void registerRenderers() {
    // Allow 3d spear model to be loaded
    ModelLoader.registerItemVariants(Items.SPEAR,
        new ModelResourceLocation(Reference.MOD_ID + ":spear", "normal"),
        RenderSpear.SPEAR_MODEL_LOCATION);

    RenderingRegistry.registerEntityRenderingHandler(EntityThrownSpear.class, manager -> {
      RenderSpear instance = new RenderSpear(manager);
      RENDERERS.add(instance);
      instance.onModelRegistryReload();
      return instance;
    });
  }

  @SubscribeEvent
  public void onResourceManagerReloads(ModelBakeEvent e) {
    LoreHelper.INSTANCE.loadLoreFile();
    for (IModelReloadListener renderer : RENDERERS) {
      renderer.onModelRegistryReload();
    }
  }
}
