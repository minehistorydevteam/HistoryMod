package com.historydevteam.historymod;

import com.historydevteam.historymod.proxy.ClientProxy;
import com.historydevteam.historymod.proxy.CommonProxy;
import com.historydevteam.historymod.util.Reference;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;


@Mod(Reference.MOD_ID)
public class HistoryMod {

  public static CommonProxy proxy;
  public static HistoryMod instance;

  public HistoryMod() {
    instance = this;
    proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    proxy.preInit();
   // FMLJavaModLoadingContext.get().getModEventBus().addListener(proxy::init);
  }

}


