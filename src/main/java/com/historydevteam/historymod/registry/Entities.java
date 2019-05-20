package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.entity.EntityThrownSpear;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class Entities {
  public static void init() {
    CommonRegistry.ENTITIES_TO_REGISTER.add(EntityEntryBuilder.create().entity(EntityThrownSpear.class).tracker(64, 20, true).name("thrown_spear").id("thrown_spear", 0).build());
  }
}
