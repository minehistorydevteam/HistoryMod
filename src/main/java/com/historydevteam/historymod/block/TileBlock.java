package com.historydevteam.historymod.block;

import com.historydevteam.historymod.HistoryMod;
import com.historydevteam.historymod.util.IOnActivate;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileBlock extends HMBlock implements ITileEntityProvider {

  private ITileEntityProvider provider;
  private boolean hasGui;

  public TileBlock(Material materialIn, ITileEntityProvider provider) {
    super(materialIn);
    this.provider = provider;
  }

  public TileBlock hasGui() {
    hasGui = true;
    return this;
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    TileEntity tile = worldIn.getTileEntity(pos);
    if (tile instanceof IOnActivate) {
      if (((IOnActivate) tile).onActivated(playerIn)) {
        return true;
      }
    }

    if (hasGui) {
      if (!worldIn.isRemote) {
        playerIn.openGui(HistoryMod.instance, Reference.GUI_TILE_ENTITY, worldIn, pos.getX(), pos.getY(), pos.getZ());
      }
      return true;
    }
    return false;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return provider.createNewTileEntity(worldIn, meta);
  }
}
