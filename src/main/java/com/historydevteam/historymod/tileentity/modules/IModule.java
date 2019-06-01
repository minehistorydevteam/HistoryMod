package com.historydevteam.historymod.tileentity.modules;

import com.historydevteam.historymod.tileentity.HMTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

public interface IModule extends INBTSerializable<NBTTagCompound> {

  String getName();

  HMTileEntity getTileEntity();

  void setTileEntity(HMTileEntity parent);


  default World getWorld() {
    return getTileEntity().getWorld();
  }

  default BlockPos getPos() {
    return getTileEntity().getPos();
  }

  default void init() {
    // Called after setTileEntity() to do initialization that requires the tile World or BlockPos
  }

  default void update() {
    // Called every tick, only if the TileEntity implements ITickable
  }

  default void onLoad() {
    // Called in the TileEntity onLoad method
  }

  default void onBreak(){
    // Called when the Block is mined
  }

  default <T> T getCapability(Capability<T> cap, EnumFacing facing) {
    return null;
  }

  default boolean hasCapability(Capability<?> cap, EnumFacing facing) {
    return getCapability(cap, facing) != null;
  }

  default NBTTagCompound serializeNBT() {
    // Override is optional, not all modules need to save data
    return new NBTTagCompound();
  }

  default void deserializeNBT(NBTTagCompound nbt) {
    // Override is optional, not all modules need to load data
  }
}
