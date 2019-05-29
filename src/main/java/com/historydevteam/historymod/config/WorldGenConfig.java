package com.historydevteam.historymod.config;

import net.minecraftforge.common.config.Config;

public class WorldGenConfig {
  @Config.Name("Veins per chunk")
  public int veinsPerChunk;
  @Config.Name("Block per vein")
  public int blocksPerVein;
  @Config.Name("Min Y level for ore generation")
  public int minHeight;
  @Config.Name("Max Y level for ore generation")
  public int maxHeight;

  public WorldGenConfig(int veinsPerChunk, int blocksPerVein, int minHeight, int maxHeight) {
    this.veinsPerChunk = veinsPerChunk;
    this.blocksPerVein = blocksPerVein;
    this.minHeight = minHeight;
    this.maxHeight = maxHeight;
  }
}
