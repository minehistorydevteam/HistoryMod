package com.historydevteam.historymod.features.kiln;

import com.historydevteam.historymod.crafting.ItemStackKey;
import com.historydevteam.historymod.crafting.RecipeManager;
import com.historydevteam.historymod.gui.HMContainer;
import com.historydevteam.historymod.gui.HMGui;
import com.historydevteam.historymod.gui.InventoryRegion;
import com.historydevteam.historymod.gui.SlotTakeOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerKiln extends HMContainer {

  public ContainerKiln(EntityPlayer player, TileKiln tile) {
    super(player, tile);
    // Input
    addSlotToContainer(new SlotItemHandler(tile.inventory, 0, 56, 17));
    regions.add(new InventoryRegion(0, 0, false, (stack, slot) ->
        RecipeManager.KILN_RECIPES.getObject(ItemStackKey.asKey(stack)) != null)
    );

    // Output
    addSlotToContainer(new SlotTakeOnly(tile.inventory, 1, 116, 35));
    regions.add(new InventoryRegion(1, 1, false, (stack, slot) -> false));

    // Fuel
    addSlotToContainer(new SlotItemHandler(tile.inventory, 2, 56, 53));
    regions.add(new InventoryRegion(2, 2, false, (stack, slot) ->
        !FurnaceRecipes.instance().getSmeltingResult(stack).isEmpty())
    );

    addPlayerSlots();
  }

  @Override
  public HMGui createGui() {
    return new GuiKiln(this);
  }
}
