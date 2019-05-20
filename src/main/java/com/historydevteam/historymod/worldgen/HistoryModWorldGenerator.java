package com.historydevteam.historymod.worldgen;

import com.historydevteam.historymod.registry.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public enum HistoryModWorldGenerator implements IWorldGenerator {
  INSTANCE;

  // Ore config
  private static final WorldGenConfig FLINT_ORE_CONFIG = new WorldGenConfig(20, 9, 0, 64);

  // Ore WorldGenerators
  private static final WorldGenMinable flint_ore = new WorldGenMinable(Blocks.FLINT_ORE.getDefaultState(), FLINT_ORE_CONFIG.blocksPerVein);

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    generateVeins(flint_ore, random, world, chunkX, chunkZ, FLINT_ORE_CONFIG);
  }

  private void generateVeins(WorldGenMinable generator, Random random, World world, int chunkX, int chunkZ, WorldGenConfig config) {
    for (int i = 0; i < config.veinsPerChunk; i++) {
      int x = chunkX * 16 + random.nextInt(16);
      int y = config.minHeight + random.nextInt(config.maxHeight - config.minHeight);
      int z = chunkZ * 16 + random.nextInt(16);
      generator.generate(world, random, new BlockPos(x, y, z));
    }
  }

  private static class WorldGenConfig {
    private int veinsPerChunk;
    private int blocksPerVein;
    private int minHeight;
    private int maxHeight;

    public WorldGenConfig(int veinsPerChunk, int blocksPerVein, int minHeight, int maxHeight) {
      this.veinsPerChunk = veinsPerChunk;
      this.blocksPerVein = blocksPerVein;
      this.minHeight = minHeight;
      this.maxHeight = maxHeight;
    }
  }
}
