package com.historydevteam.historymod.network;

import com.historydevteam.historymod.util.IBD;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * This message is sent in a GUI container (Server) to update the progress information shown in the
 * GUI (Client)
 * <p>
 * Message path: Server -> Client
 */
public class MessageContainerUpdate {
  private final IBD ibd;

  public MessageContainerUpdate(IBD ibd) {
    this.ibd = ibd;
  }

  public static void encode(MessageContainerUpdate msg, PacketBuffer buf) {
    msg.ibd.toBuffer(buf);
  }

  public static MessageContainerUpdate decode(PacketBuffer buf) {
    return new MessageContainerUpdate(IBD.fromBuffer(buf));
  }

  public static void handle(MessageContainerUpdate message, Supplier<NetworkEvent.Context> ctx) {
    ctx.get().enqueueWork(() -> {
      Container cont = Minecraft.getInstance().player.openContainer;
//      if(cont instanceof HMContainer) {
//        ((HMContainer) cont).handleServerPacket(message.ibd);
//      }
    });
  }
}
