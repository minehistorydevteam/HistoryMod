package com.historydevteam.historymod.tileentity;

import com.historydevteam.historymod.tileentity.modules.IModule;
import com.historydevteam.historymod.util.RegistryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.List;

public class HMTileEntity extends TileEntity {
  protected List<IModule> modules;

  protected void initModules(){
    if (modules == null){
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
    // This is because the name of update in ITickable is obfuscated outside the development environment

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
  public void invalidate() {
    super.invalidate();
    if (world.isRemote) {
      onBreak();
    }
  }

  @Override
  protected void setWorldCreate(World worldIn) {
    setWorld(worldIn);
  }

  @Override
  public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
    for (IModule module : modules) {
      if (module.hasCapability(capability, facing)) {
        return true;
      }
    }
    return false;
  }

  @Nullable
  @Override
  public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
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

  /**
   * Send the TileEntity update packet to all player within 64 block radius
   */
  public void sendUpdateToNearPlayers() {
    if (world.isRemote) {
      return;
    }
    SPacketUpdateTileEntity packet = getUpdatePacket();
    if (packet == null) {
      return;
    }
    for (EntityPlayer playerEntity : world.playerEntities) {
      EntityPlayerMP player = (EntityPlayerMP) playerEntity;
      if (getDistanceSq(player.posX, playerEntity.posY, player.posZ) <= (64 * 64)) {
        player.connection.sendPacket(packet);
      }
    }
  }

  public NBTTagCompound saveToPacket() {
    return save();
  }

  public void loadFromPacket(NBTTagCompound nbt) {
    load(nbt);
  }

  @Nullable
  @Override
  public SPacketUpdateTileEntity getUpdatePacket() {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.setTag("packet", saveToPacket());
    super.writeToNBT(nbt);
    return new SPacketUpdateTileEntity(pos, 1, nbt);
  }

  @Override
  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    NBTTagCompound nbt = pkt.getNbtCompound();
    if (nbt.hasKey("packet")) {
      loadFromPacket(nbt.getCompoundTag("packet"));
    } else {
      readFromNBT(nbt);
    }
  }

  @Override
  public NBTTagCompound getUpdateTag() {
    return writeToNBT(new NBTTagCompound());
  }

  public NBTTagCompound save() {
    NBTTagCompound list = new NBTTagCompound();

    for (IModule module : modules) {
      list.setTag(module.getName(), module.serializeNBT());
    }

    return list;
  }

  public void load(NBTTagCompound list) {
    for (IModule module : modules) {
      module.deserializeNBT(list.getCompoundTag(module.getName()));
    }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    compound.setTag("modules", save());
    return super.writeToNBT(compound);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    if (compound.hasKey("modules")) {
      load(compound.getCompoundTag("modules"));
    }

    super.readFromNBT(compound);
  }
}
