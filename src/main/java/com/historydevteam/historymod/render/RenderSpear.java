package com.historydevteam.historymod.render;

import com.historydevteam.historymod.entity.EntityThrownSpear;
import com.historydevteam.historymod.util.ModelUtilities;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class RenderSpear extends Render<EntityThrownSpear> implements IModelReloadListener {
  public static final ModelResourceLocation SPEAR_MODEL_LOCATION = new ModelResourceLocation(Reference.MOD_ID + ":thrown_spear", "thrown");
  private int spearModel = -1;

  public RenderSpear(RenderManager renderManager) {
    super(renderManager);
  }

  public void doRender(EntityThrownSpear entity, double x, double y, double z, float entityYaw, float partialTicks) {
    GlStateManager.pushMatrix();
    GlStateManager.translate((float) x, (float) y, (float) z);
    GlStateManager.enableRescaleNormal();

    // Renders a cube at the model rotation pivot
    ModelUtilities.renderDebugBox(new AxisAlignedBB(0d, 0d, 0d, 0.1d, 0.1d, 0.1d), null);

    GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
    GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
    GlStateManager.rotate(-90F, 0.0F, 0.0F, 1.0F);

    float shake = (float) entity.arrowShake - partialTicks;

    if (shake > 0.0F) {
      float angle = -MathHelper.sin(shake * 3.0F) * shake * 0.25f;
      GlStateManager.rotate(angle, 0.0F, 0.0F, 1.0F);
    }

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
    IBakedModel model = Minecraft.getMinecraft()
        .getRenderItem()
        .getItemModelMesher()
        .getModelManager()
        .getModel(SPEAR_MODEL_LOCATION);

    if (spearModel != -1) {
      GlStateManager.glDeleteLists(spearModel, 1);
    }
    spearModel = ModelUtilities.createDisplayList(model, null, null, 0L);
  }
}
