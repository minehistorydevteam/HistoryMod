package com.historydevteam.historymod.proxy.network;

import com.historydevteam.historymod.gui.HMContainer;
import com.historydevteam.historymod.util.IBD;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * This message is sent in a GUI container (Server) to update the progress
 * information shown in the GUI (Client)
 * <p>
 * Message path: Server -> Client
 */
public class MessageContainerUpdate implements IMessage {
  private IBD ibd;

  public MessageContainerUpdate() {
  }

  public MessageContainerUpdate(IBD ibd) {
    this.ibd = ibd;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    IBD ibd = new IBD();
    ibd.fromBuffer(buf);
    this.ibd = ibd;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    ibd.toBuffer(buf);
  }

  enum Handler implements IMessageHandler<MessageContainerUpdate, IMessage> {
    INSTANCE;

    @Override
    public IMessage onMessage(MessageContainerUpdate message, MessageContext ctx) {
      if (ctx.side != Side.CLIENT) {
        throw new IllegalStateException("This handler can only accept messages from the server");
      }

      Container cont = Minecraft.getMinecraft().player.openContainer;

      if (cont instanceof HMContainer) {
        ((HMContainer) cont).receiveDataFromServer(message.ibd);
      }

      return null;
    }
  }
}
