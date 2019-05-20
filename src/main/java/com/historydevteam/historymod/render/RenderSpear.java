package com.historydevteam.historymod.render;

import com.historydevteam.historymod.entity.EntityThrownSpear;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class RenderSpear extends Render<EntityThrownSpear> {
  private final RenderItem itemRenderer;

  public RenderSpear(RenderManager renderManagerIn, RenderItem itemRendererIn) {
    super(renderManagerIn);
    this.itemRenderer = itemRendererIn;
  }

  public void doRender(EntityThrownSpear entity, double x, double y, double z, float entityYaw, float partialTicks) {
    GlStateManager.pushMatrix();
    GlStateManager.translate((float)x, (float)y, (float)z);
    GlStateManager.enableRescaleNormal();
    GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
    GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
    this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

    if (this.renderOutlines) {
      GlStateManager.enableColorMaterial();
      GlStateManager.enableOutlineMode(this.getTeamColor(entity));
    }

    this.itemRenderer.renderItem(entity.getSpearItem(), ItemCameraTransforms.TransformType.GROUND);

    if (this.renderOutlines) {
      GlStateManager.disableOutlineMode();
      GlStateManager.disableColorMaterial();
    }

    GlStateManager.disableRescaleNormal();
    GlStateManager.popMatrix();
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }

  protected ResourceLocation getEntityTexture(EntityThrownSpear entity) {
    return TextureMap.LOCATION_BLOCKS_TEXTURE;
  }

}
