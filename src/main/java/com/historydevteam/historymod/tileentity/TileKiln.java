package com.historydevteam.historymod.tileentity;

import com.historydevteam.historymod.crafting.KilnCraftingProcess;
import com.historydevteam.historymod.tileentity.containers.Inventory;
import com.historydevteam.historymod.tileentity.modules.ModuleFuelSlot;
import com.historydevteam.historymod.tileentity.modules.ModuleInventory;
import com.historydevteam.historymod.tileentity.modules.ModuleTimedCrafting;
import com.historydevteam.historymod.util.Debug;
import net.minecraft.util.ITickable;

@Tile(name = "kiln")
public class TileKiln extends HMTileEntity implements ITickable {
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
    initModules();
  }

  @Override
  public void update() {
    if (Debug.DEV_ENV) {
      // TODO add proper GUI synchronization,
      // for now just update every tick,
      // but only in debug mode or this could kill a server easily
      sendUpdateToNearPlayers();
    }
    super.update();
  }
}