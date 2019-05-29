package com.historydevteam.historymod.gui;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.util.ResourceLocation;

public class GuiKiln extends HMGui {
  private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/kiln.png");

  public GuiKiln(ContainerKiln container) {
    super(container);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    drawBackground(TEXTURES);
  }
}
