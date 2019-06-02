package com.historydevteam.historymod.proxy;

import com.historydevteam.historymod.HistoryMod;
import com.historydevteam.historymod.crafting.RecipeManager;
import com.historydevteam.historymod.gui.GuiHandler;
import com.historydevteam.historymod.proxy.network.HMNetworkManager;
import com.historydevteam.historymod.registry.CommonRegistry;
import com.historydevteam.historymod.tileentity.Tile;
import com.historydevteam.historymod.util.Reference;
import com.historydevteam.historymod.worldgen.HistoryModWorldGenerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

  private final CommonRegistry registry;
  private ASMDataTable asmDataTable;

  public CommonProxy() {
    this(new CommonRegistry());
  }

  CommonProxy(CommonRegistry registry) {
    this.registry = registry;
  }

  public void preInit(FMLPreInitializationEvent event) {
    asmDataTable = event.getAsmData();
    GameRegistry.registerWorldGenerator(new HistoryModWorldGenerator(), 3);
    MinecraftForge.EVENT_BUS.register(registry);
    NetworkRegistry.INSTANCE.registerGuiHandler(HistoryMod.instance, new GuiHandler());
  }

  public void init(FMLInitializationEvent event) {

    // Register all classes with @Tile as TileEntities
    for (ASMDataTable.ASMData target : asmDataTable.getAll(Tile.class.getName())) {
      try {
        String name = (String) target.getAnnotationInfo().get("name");
        Class clazz = Class.forName(target.getClassName());

        //noinspection unchecked
        TileEntity.register(Reference.MOD_ID + ":" + name, clazz);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    // Add recipes
    RecipeManager.loadRecipes();

    // Register packets
    HMNetworkManager.init();
  }

  public void postInit(FMLPostInitializationEvent event) {

  }
}
