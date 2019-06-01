package com.historydevteam.historymod.tileentity;

import com.historydevteam.historymod.tileentity.containers.Inventory;
import com.historydevteam.historymod.tileentity.modules.ModuleInventory;
import net.minecraft.util.ITickable;

@Tile(name = "kiln")
public class TileKiln extends HMTileEntity implements ITickable {
  public Inventory inventory = new Inventory(3);

  public final ModuleInventory moduleInv = new ModuleInventory(inventory);

  public TileKiln() {
    initModules();
  }

  @Override
  public void update() {
    super.update();
  }
}