package com.historydevteam.historymod.render;

import com.historydevteam.historymod.entity.EntityThrownSpear;
import com.historydevteam.historymod.registry.Items;
import com.historydevteam.historymod.util.ModelUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderSpear extends Render<EntityThrownSpear> implements IModelReloadListener {
  private int spearModel = -1;

  public RenderSpear(RenderManager renderManager) {
    super(renderManager);
  }

  public void doRender(EntityThrownSpear entity, double x, double y, double z, float entityYaw, float partialTicks) {
    GlStateManager.pushMatrix();
    GlStateManager.translate((float) x, (float) y, (float) z);
    GlStateManager.enableRescaleNormal();

//    ModelUtilities.renderDebugBox(new AxisAlignedBB(0d, 0d, 0d, 0.1d, 0.1d, 0.1d), null);

    GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
    GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
    GlStateManager.rotate(45F + 180F, 0.0F, 0.0F, 1.0F);

    GlStateManager.translate(-0.5f, -0.5f, -0.5f);
    this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

    if (this.renderOutlines) {
      GlStateManager.enableColorMaterial();
      GlStateManager.enableOutlineMode(this.getTeamColor(entity));
    }

    if (spearModel != -1) {
      GlStateManager.callList(spearModel);
    }

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

  @Override
  public void onModelRegistryReload() {
    ItemStack stack = new ItemStack(Items.SPEAR);
    IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, null, null);

    if (spearModel != -1) {
      GlStateManager.glDeleteLists(spearModel, 1);
    }
    spearModel = ModelUtilities.createDisplayList(model, null, null, 0L);
  }
}
