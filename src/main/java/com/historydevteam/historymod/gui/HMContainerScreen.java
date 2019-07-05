/*
package com.historydevteam.historymod.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class HMContainerScreen<T extends HMContainer> extends ContainerScreen<T> {

  public HMContainerScreen(T inventorySlotsIn, PlayerEntity player) {
    super(inventorySlotsIn, player.inventory, new StringTextComponent("TESTTESTTEST"));
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    // Do nothing
  }


  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    renderHoveredToolTip(mouseX, mouseY);
  }

  public void drawBackground(ResourceLocation texture) {
    GlStateManager.disableLighting();
    GlStateManager.disableFog();
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    getMinecraft().getTextureManager().bindTexture(texture);
    GlStateManager.color4f(1, 1, 1, 1);
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
*/
