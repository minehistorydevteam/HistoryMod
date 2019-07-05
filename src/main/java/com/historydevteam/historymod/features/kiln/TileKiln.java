/*
package com.historydevteam.historymod.features.kiln;

import com.historydevteam.historymod.crafting.KilnCraftingProcess;
import com.historydevteam.historymod.tileentity.HMTileEntity;
import com.historydevteam.historymod.tileentity.Tile;
import com.historydevteam.historymod.tileentity.containers.Inventory;
import com.historydevteam.historymod.tileentity.modules.ModuleFuelSlot;
import com.historydevteam.historymod.tileentity.modules.ModuleInventory;
import com.historydevteam.historymod.tileentity.modules.ModuleTimedCrafting;
import net.minecraft.tileentity.ITickableTileEntity;

@Tile(name = "kiln")
public class TileKiln extends HMTileEntity implements ITickableTileEntity {
  public Inventory inventory = new Inventory(3);

  public final ModuleInventory moduleInv = new ModuleInventory(inventory, new int[]{0, 2}, new int[]{1});
  public final ModuleFuelSlot moduleFuelSlot = new ModuleFuelSlot(inventory, 2);
  public final ModuleTimedCrafting moduleCraft = new ModuleTimedCrafting(
      new KilnCraftingProcess(inventory, 0, 1),
      moduleFuelSlot,
      1f,
      20 // twice as fast as a normal furnace
  );

  public TileKiln() {
    super(Tile);
    initModules();
  }

  @Override
  public void update() {
    super.update();
  }

  @Override
  public void tick() {

  }
}*/
