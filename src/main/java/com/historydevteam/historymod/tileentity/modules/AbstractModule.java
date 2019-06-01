package com.historydevteam.historymod.tileentity.modules;

import com.historydevteam.historymod.tileentity.HMTileEntity;

public abstract class AbstractModule implements IModule {

  protected HMTileEntity tile;

  @Override
  public HMTileEntity getTileEntity() {
    return tile;
  }

  @Override
  public void setTileEntity(HMTileEntity parent) {
    tile = parent;
  }
}
