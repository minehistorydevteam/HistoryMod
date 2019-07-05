package com.historydevteam.historymod.render;

import com.historydevteam.historymod.entity.ThrownSpearEntity;
import com.historydevteam.historymod.util.ModelUtilities;
import com.historydevteam.historymod.util.Reference;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.BowItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class RenderSpear extends EntityRenderer<ThrownSpearEntity> implements IModelReloadListener {
  public static final ModelResourceLocation SPEAR_MODEL_LOCATION = new ModelResourceLocation(Reference.MOD_ID + ":thrown_spear", "thrown");
  private int spearModel = -1;

  public RenderSpear(EntityRendererManager renderManager) {
    super(renderManager);
  }

  public void doRender(ThrownSpearEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
    GlStateManager.pushMatrix();
    GlStateManager.translated(x, y, z);
    GlStateManager.enableRescaleNormal();

    // Renders a cube at the model rotation pivot
    ModelUtilities.renderDebugBox(new AxisAlignedBB(0d, 0d, 0d, 0.1d, 0.1d, 0.1d), null);

    GlStateManager.rotated(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90, 0, 1, 0);
    GlStateManager.rotated(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0, 0, 1);
    GlStateManager.rotated(-90, 0, 0, 1);

    float shake = (float) entity.arrowShake - partialTicks;

    if (shake > 0.0F) {
      float angle = -MathHelper.sin(shake * 3.0F) * shake * 0.25f;
      GlStateManager.rotated(angle, 0, 0, 1);
    }

    GlStateManager.translated(-.5, -.5, -.5);
    this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);

    if (this.renderOutlines) {
      GlStateManager.enableColorMaterial();
      GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
    }

    if (spearModel != -1) {
      GlStateManager.callList(spearModel);
    }

    if (this.renderOutlines) {
      GlStateManager.tearDownSolidRenderingTextureCombine();
      GlStateManager.disableColorMaterial();
    }

    GlStateManager.disableRescaleNormal();
    GlStateManager.popMatrix();
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }

  protected ResourceLocation getEntityTexture(ThrownSpearEntity entity) {
    return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
  }

  @Override
  public void onModelRegistryReload() {
    IBakedModel model = Minecraft.getInstance()
        .getItemRenderer()
        .getItemModelMesher()
        .getModelManager()
        .getModel(SPEAR_MODEL_LOCATION);

    if (spearModel != -1) {
      GlStateManager.deleteLists(spearModel, 1);
    }
    spearModel = ModelUtilities.createDisplayList(model, null, null, new Random());
  }
}
