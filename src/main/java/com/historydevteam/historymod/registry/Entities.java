package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.entity.ThrownPebbleEntity;
import com.historydevteam.historymod.entity.ThrownSpearEntity;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

@SuppressWarnings("unchecked")
public class Entities {

  public static final EntityType<ThrownSpearEntity> THROWN_SPEAR = (EntityType<ThrownSpearEntity>) EntityType.Builder.<ThrownSpearEntity>create(
      EntityClassification.MISC).immuneToFire().setShouldReceiveVelocityUpdates(true)
      .setTrackingRange(64).setUpdateInterval(1).size(0.5f, 0.5f).setCustomClientFactory((spawnEntity, world) -> new ThrownSpearEntity(world)).build("thrown_spear")
      .setRegistryName(Reference.MOD_ID, "thrown_spear");

  public static final EntityType<ThrownPebbleEntity> THROWN_PEBBLE =
      (EntityType<ThrownPebbleEntity>) EntityType.Builder.<ThrownPebbleEntity>create(
      EntityClassification.MISC).immuneToFire().setShouldReceiveVelocityUpdates(true)
      .setTrackingRange(64).setUpdateInterval(1).size(0.5f, 0.5f).setCustomClientFactory((spawnEntity, world) -> new ThrownPebbleEntity(world)).build("thrown_pebble")
      .setRegistryName(Reference.MOD_ID, "thrown_pebble");
}