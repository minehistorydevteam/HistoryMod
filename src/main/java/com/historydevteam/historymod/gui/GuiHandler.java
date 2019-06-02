package com.historydevteam.historymod.gui;

import com.historydevteam.historymod.features.kiln.ContainerKiln;
import com.historydevteam.historymod.features.kiln.TileKiln;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

  @Nullable
  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == Reference.GUI_TILE_ENTITY) {
      BlockPos pos = new BlockPos(x, y, z);
      TileEntity tile = world.getTileEntity(pos);

      if (tile instanceof TileKiln) {
        return new ContainerKiln(player, (TileKiln) tile);
      }
    }
    return null;
  }

  @Nullable
  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == Reference.GUI_TILE_ENTITY) {
      Object obj = getServerGuiElement(ID, player, world, x, y, z);

      if (obj instanceof HMContainer) {
        return ((HMContainer) obj).createGui();
      }
    }
    return null;
  }
}
