package com.historydevteam.historymod.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class ModelUtilities {

  /**
   * Renders a IBakedModel directly
   * <p>
   * For the love of god, don't use this method to render the model every tick, use a DisplayList instead
   *
   * @param model model to render
   * @param state first arg to model.getQuads
   * @param side  second arg to model.getQuads
   * @param rand  third arg to model.getQuads
   */
  public static void renderModel(IBakedModel model, IBlockState state, EnumFacing side, Long rand) {
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder buffer = tessellator.getBuffer();
    List<BakedQuad> storage = model.getQuads(state, side, rand);

    buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
    buffer.setTranslation(0.0, 0.0, 0.0);

    for (BakedQuad quad : storage) {
      buffer.addVertexData(quad.getVertexData());
    }
    tessellator.draw();
  }

  /**
   * Creates a OpenGL display list with a bakedModel
   * The list can be rendered with GlStateManager.callList(list);
   * The list must be deleted with GlStateManager.glDeleteLists(list, 1);
   * <p>
   * For efficiency reasons, you should store the display list and reuse it much as possible,
   * as creating a new one every frame is very costly, even more than direct rendering.
   * <p>
   * A display list is a GPU data structure that stores the model and a some rendering settings,
   * the main reason to use display lists, instead of rendering the model directly, is to avoid data
   * transfer from the RAM to the GPU memory, by keep the data in the GPU all the time the access is much faster.
   *
   * @param model model to render
   * @param state first arg to model.getQuads
   * @param side  second arg to model.getQuads
   * @param rand  third arg to model.getQuads
   * @return the id of the display list
   */
  public static int createDisplayList(IBakedModel model, IBlockState state, EnumFacing side, Long rand) {
    int list = GlStateManager.glGenLists(1);
    GlStateManager.glNewList(list, GL11.GL_COMPILE);
    renderModel(model, state, side, rand);
    GlStateManager.glEndList();

    return list;
  }

  /**
   * Renders the outline of a cube defined with a AxisAlignedBB
   *
   * @param box   the box to render
   * @param color the color to use, if null, white will be used
   */
  public static void renderDebugBox(AxisAlignedBB box, Vec3d color) {
    Tessellator tes = Tessellator.getInstance();
    BufferBuilder t = tes.getBuffer();

    if (color == null) {
      color = new Vec3d(1f, 1f, 1f);
    }

    float r = (float) color.x;
    float g = (float) color.y;
    float b = (float) color.z;
    float a = 1f;

    GlStateManager.bindTexture(0);
    GlStateManager.glLineWidth(2f);
    GlStateManager.enableColorMaterial();
    GlStateManager.enableOutlineMode(0xFF_FF_FF_FF);

    t.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
    t.pos(box.minX, box.minY, box.minZ).color(r, g, b, a).endVertex();
    t.pos(box.maxX, box.minY, box.minZ).color(r, g, b, a).endVertex();

    t.pos(box.minX, box.minY, box.minZ).color(r, g, b, a).endVertex();
    t.pos(box.minX, box.maxY, box.minZ).color(r, g, b, a).endVertex();

    t.pos(box.minX, box.minY, box.minZ).color(r, g, b, a).endVertex();
    t.pos(box.minX, box.minY, box.maxZ).color(r, g, b, a).endVertex();

    t.pos(box.maxX, box.maxY, box.maxZ).color(r, g, b, a).endVertex();
    t.pos(box.minX, box.maxY, box.maxZ).color(r, g, b, a).endVertex();

    t.pos(box.maxX, box.maxY, box.maxZ).color(r, g, b, a).endVertex();
    t.pos(box.maxX, box.minY, box.maxZ).color(r, g, b, a).endVertex();

    t.pos(box.maxX, box.maxY, box.maxZ).color(r, g, b, a).endVertex();
    t.pos(box.maxX, box.maxY, box.minZ).color(r, g, b, a).endVertex();

    t.pos(box.minX, box.maxY, box.minZ).color(r, g, b, a).endVertex();
    t.pos(box.maxX, box.maxY, box.minZ).color(r, g, b, a).endVertex();

    t.pos(box.maxX, box.minY, box.minZ).color(r, g, b, a).endVertex();
    t.pos(box.maxX, box.maxY, box.minZ).color(r, g, b, a).endVertex();

    t.pos(box.minX, box.maxY, box.minZ).color(r, g, b, a).endVertex();
    t.pos(box.minX, box.maxY, box.maxZ).color(r, g, b, a).endVertex();

    t.pos(box.maxX, box.minY, box.maxZ).color(r, g, b, a).endVertex();
    t.pos(box.minX, box.minY, box.maxZ).color(r, g, b, a).endVertex();

    t.pos(box.minX, box.maxY, box.maxZ).color(r, g, b, a).endVertex();
    t.pos(box.minX, box.minY, box.maxZ).color(r, g, b, a).endVertex();

    t.pos(box.maxX, box.minY, box.maxZ).color(r, g, b, a).endVertex();
    t.pos(box.maxX, box.minY, box.minZ).color(r, g, b, a).endVertex();

    tes.draw();

    GlStateManager.disableOutlineMode();
    GlStateManager.disableColorMaterial();
  }
}
