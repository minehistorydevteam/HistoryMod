package com.historydevteam.historymod.network;

import com.historydevteam.historymod.util.IBD;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class HMNetworkManager {

  private static final String PROTOCOL_VERSION = "1.0.0";

  public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
      new ResourceLocation(Reference.MOD_ID, "main"),
      () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
      PROTOCOL_VERSION::equals
  );

  public static void init() {
    INSTANCE.registerMessage(0, MessageContainerUpdate.class, MessageContainerUpdate::encode, MessageContainerUpdate::decode, MessageContainerUpdate::handle);
  }

  public static void sendContainerUpdate(ServerPlayerEntity player, IBD data) {
   // INSTANCE.sendTo(new MessageContainerUpdate(data), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
  }
}
