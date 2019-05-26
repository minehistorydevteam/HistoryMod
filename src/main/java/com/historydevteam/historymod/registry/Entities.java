package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.entity.EntityThrownSpear;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class Entities {

  public static final EntityEntry THROWN_SPEAR = EntityEntryBuilder.create()
      .entity(EntityThrownSpear.class)
      .tracker(64, 10, true)
      .name("thrown_spear")
      .id("thrown_spear", 0)
      .build();
}
