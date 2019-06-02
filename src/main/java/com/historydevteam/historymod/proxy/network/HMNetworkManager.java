package com.historydevteam.historymod.proxy.network;

import com.historydevteam.historymod.util.IBD;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class HMNetworkManager {

  public static final SimpleNetworkWrapper network = new SimpleNetworkWrapper(Reference.MOD_ID);

  public static void init() {
    network.registerMessage(MessageContainerUpdate.Handler.INSTANCE, MessageContainerUpdate.class, 0, Side.CLIENT);
  }

  public static void sendContainerUpdate(EntityPlayerMP player, IBD data) {
    network.sendTo(new MessageContainerUpdate(data), player);
  }
}
