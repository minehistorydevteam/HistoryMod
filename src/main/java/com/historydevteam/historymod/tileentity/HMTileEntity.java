/*
package com.historydevteam.historymod.tileentity;

import com.historydevteam.historymod.tileentity.modules.IModule;
import com.historydevteam.historymod.util.IField;
import com.historydevteam.historymod.util.RegistryUtil;
import com.historydevteam.historymod.util.Sync;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HMTileEntity extends TileEntity {
  protected List<IModule> modules;

  public HMTileEntity(TileEntityType<?> tileEntityTypeIn) {
    super(tileEntityTypeIn);
  }

  protected void initModules() {
    if (modules == null) {
      modules = RegistryUtil.getObjectsFromFields(this, IModule.class);
      for (IModule module : modules) {
        module.setTileEntity(this);
      }
      for (IModule module : modules) {
        module.init();
      }
    }
  }

  public void update() {
    // NOTE: It's really important that if you implement ITickable you must override this method,
    // otherwise this method will crash when running outside the development environment
    // This is happens because the name of update in ITickable is obfuscated outside the development environment

    for (IModule module : modules) {
      module.update();
    }
  }

  public void onBreak() {
    for (IModule module : modules) {
      module.onBreak();
    }
  }

  @Override
  public void onLoad() {
    for (IModule module : modules) {
      module.onLoad();
    }
  }

  @Override
  public boolean hasCapability(Capability<?> capability, @Nullable Direction facing) {
    for (IModule module : modules) {
      if (module.hasCapability(capability, facing)) {
        return true;
      }
    }
    return false;
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
    return null;
  }

  @Nullable
  @Override
  public <T> T getCapability(Capability<T> capability, @Nullable Direction facing) {
    for (IModule module : modules) {
      T cap = module.getCapability(capability, facing);
      if (cap != null) {
        return cap;
      }
    }
    return null;
  }

  @Override
  public double getMaxRenderDistanceSquared() {
    return 128 * 128;
  }

  */
/**
   * Send the TileEntity update packet to all player within 64 blocks radius
   *//*

  public void sendUpdateToNearPlayers() {
    if (world.isRemote) {
      return;
    }
    SUpdateTileEntityPacket packet = getUpdatePacket();
    if (packet == null) {
      return;
    }
    for (PlayerEntity playerEntity : world.getPlayers()) {
      ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
      if (getDistanceSq(player.posX, playerEntity.posY, player.posZ) <= (64 * 64)) {
        player.connection.sendPacket(packet);
      }
    }
  }

  public CompoundNBT saveToPacket() {
    return save();
  }

  public void loadFromPacket(CompoundNBT nbt) {
    load(nbt);
  }

  @Nullable
  @Override
  public SUpdateTileEntityPacket getUpdatePacket() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.put("packet", saveToPacket());
    super.write(nbt);
    return new SUpdateTileEntityPacket(pos, 1, nbt);
  }

  @Override
  public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
    CompoundNBT nbt = pkt.getNbtCompound();
    if (nbt.contains("packet")) {
      loadFromPacket(nbt.getCompound("packet"));
    } else {
      read(nbt);
    }
  }

  @Override
  public CompoundNBT getUpdateTag() {
    return write(new CompoundNBT());
  }

  public CompoundNBT save() {
    CompoundNBT list = new CompoundNBT();

    for (IModule module : modules) {
      list.put(module.getName(), module.serializeNBT());
    }

    return list;
  }

  public void load(CompoundNBT list) {
    for (IModule module : modules) {
      module.deserializeNBT(list.getCompound(module.getName()));
    }
  }


  @Override
  public CompoundNBT write(CompoundNBT compound) {
    compound.put("modules", save());
    return super.write(compound);
  }

  @Override
  public void read(CompoundNBT compound) {
    if (compound.contains("modules")) {
      load(compound.getCompound("modules"));
    }

    super.read(compound);
  }

  public Map<Integer, IField<Object>> getGuiSyncVariables() {
    Map<Integer, IField<Object>> map = new HashMap<>();

    for (IModule module : modules) {
      for (Pair<Sync, IField<Object>> pair : RegistryUtil.getVariablesMarkedWithAnnotation(Sync.class, module)) {
        map.put(pair.getKey().id(), pair.getValue());
      }
    }

    return map;
  }
}
*/
