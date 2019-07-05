package com.historydevteam.historymod.proxy;

import com.historydevteam.historymod.entity.ThrownPebbleEntity;
import com.historydevteam.historymod.registry.CommonRegistry;
import com.historydevteam.historymod.registry.SplashesHacker;
import com.historydevteam.historymod.render.IModelReloadListener;
import com.historydevteam.historymod.render.RenderSpear;
import com.historydevteam.historymod.util.LoreHelper;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverride;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends CommonProxy {

  private static final List<IModelReloadListener> RENDERERS = new ArrayList<>();

  public ClientProxy() {
    super(new CommonRegistry());
  }

  @Override
  public void preInit() {
    super.preInit();
    registerRenderers();
    new SplashesHacker();
    MinecraftForge.EVENT_BUS.register(this);
  }



  public void registerRenderers() {
    // Allow 3d spear model to be loaded
    /*ModelLoader.registerItemVariants(Items.SPEAR,
        new ModelResourceLocation(Reference.MOD_ID + ":spear", "normal"),
        RenderSpear.SPEAR_MODEL_LOCATION);*/

    RenderingRegistry.registerEntityRenderingHandler(ThrownPebbleEntity.class, manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
  }

  @SubscribeEvent
  public void onResourceManagerReloads(ModelBakeEvent e) {
    LoreHelper.INSTANCE.loadLoreFile();
    for (IModelReloadListener renderer : RENDERERS) {
      renderer.onModelRegistryReload();
    }
  }
}
