/*
package com.historydevteam.historymod.tileentity.modules;

import com.historydevteam.historymod.tileentity.HMTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

public interface IModule extends INBTSerializable<CompoundNBT> {

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

  default <T> T getCapability(Capability<T> cap, Direction facing) {
    return null;
  }

  default boolean hasCapability(Capability<?> cap, Direction facing) {
    return getCapability(cap, facing) != null;
  }

  default CompoundNBT serializeNBT() {
    // Override is optional, not all modules need to save data
    return new CompoundNBT();
  }

  default void deserializeNBT(CompoundNBT nbt) {
    // Override is optional, not all modules need to load data
  }
}
*/
