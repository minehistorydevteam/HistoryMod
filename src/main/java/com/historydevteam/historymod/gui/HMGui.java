package com.historydevteam.historymod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class HMGui extends GuiContainer {

  public HMGui(Container inventorySlotsIn) {
    super(inventorySlotsIn);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    // Do nothing
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    drawDefaultBackground();
    super.drawScreen(mouseX, mouseY, partialTicks);
    renderHoveredToolTip(mouseX, mouseY);
  }

  public void drawBackground(ResourceLocation texture) {
    GlStateManager.disableLighting();
    GlStateManager.disableFog();
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    mc.getTextureManager().bindTexture(texture);
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

    bufferbuilder.pos(guiLeft, guiTop + ySize, 0.0D)
        .tex(0.0D, ySize / 256.0F)
        .color(255, 255, 255, 255)
        .endVertex();

    bufferbuilder.pos(guiLeft + xSize, guiTop + ySize, 0.0D)
        .tex(xSize / 256.0F, ySize / 256.0F)
        .color(255, 255, 255, 255)
        .endVertex();

    bufferbuilder.pos(guiLeft + xSize, guiTop, 0.0D)
        .tex(xSize / 256.0F, 0)
        .color(255, 255, 255, 255)
        .endVertex();

    bufferbuilder.pos(guiLeft, guiTop, 0.0D)
        .tex(0.0D, 0)
        .color(255, 255, 255, 255)
        .endVertex();

    tessellator.draw();
  }
}
