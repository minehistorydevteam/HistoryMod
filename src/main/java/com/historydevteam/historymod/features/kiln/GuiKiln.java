/*
package com.historydevteam.historymod.features.kiln;

import com.historydevteam.historymod.gui.HMContainerScreen;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.util.ResourceLocation;

public class GuiKiln extends HMContainerScreen {
  private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/kiln.png");

  public GuiKiln(ContainerKiln container) {
    super(container);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    drawBackground(TEXTURES);
    TileKiln tile = (TileKiln) ((ContainerKiln) inventorySlots).tile;
    int amount = 0;

    // Fuel bar
    if (tile.moduleFuelSlot.getMaxBurnTime() != 0) {
      amount = (int) Math.ceil(1 + 13 * (float) tile.moduleFuelSlot.getBurnTime() / tile.moduleFuelSlot.getMaxBurnTime());
    }
    drawTexturedModalRect(guiLeft + 56, guiTop + 36 + (14 - amount), 176, (14 - amount), 14, amount);

    // Progress bar
    float percent = tile.moduleCraft.getProcessingPercent();
    amount = (int) (percent * 22);
    drawTexturedModalRect(guiLeft + 80, guiTop + 34, 177, 14, amount, 17);
  }
}
*/
