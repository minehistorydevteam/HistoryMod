package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.entity.EntityThrownSpear;
import com.historydevteam.historymod.util.Reference;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class Entities {
  public static void init() {
    CommonRegistry.ENTITIES_TO_REGISTER.add(new EntityEntry(EntityThrownSpear.class, "thrown_spear").setRegistryName(
        Reference.MOD_ID, "thrown_spear"));
  }
}
