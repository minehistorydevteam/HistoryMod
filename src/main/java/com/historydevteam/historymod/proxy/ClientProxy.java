package com.historydevteam.historymod.proxy;

import com.historydevteam.historymod.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
  public ClientProxy() {
    super(new ClientRegistry());
  }
}
