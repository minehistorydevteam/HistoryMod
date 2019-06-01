package com.historydevteam.historymod.gui;

import com.historydevteam.historymod.tileentity.TileKiln;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerKiln extends HMContainer {

  public ContainerKiln(EntityPlayer player, TileKiln tile) {
    super(player, tile);
    addSlotToContainer(new SlotItemHandler(tile.inventory, 0, 8, 55));
    addSlotToContainer(new SlotItemHandler(tile.inventory, 1, 26, 55));
    addSlotToContainer(new SlotItemHandler(tile.inventory, 2, 56, 53));

    addPlayerSlots();
  }

  @Override
  public HMGui createGui() {
    return new GuiKiln(this);
  }
}
