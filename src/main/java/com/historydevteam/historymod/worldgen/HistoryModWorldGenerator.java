package com.historydevteam.historymod.worldgen;

import com.historydevteam.historymod.registry.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class HistoryModWorldGenerator implements IWorldGenerator {

  // Ore config
  private static final WorldGenConfig FLINT_ORE_CONFIG = new WorldGenConfig(2, 9, 48, 80);
  private static final WorldGenConfig QUARTZITE_ORE_CONFIG = new WorldGenConfig(2, 9, 48, 80);

  // Ore WorldGenerators
  private static final WorldGenMinable flint_ore = new WorldGenMinable(Blocks.FLINT_ORE.getDefaultState(), FLINT_ORE_CONFIG.blocksPerVein);
  private static final WorldGenMinable quartzite_ore = new WorldGenMinable(Blocks.QUARTZITE_ORE.getDefaultState(), QUARTZITE_ORE_CONFIG.blocksPerVein);

  // Decoration WorldGenerators
  private static final WorldGenDecorations DECORATION_PEBBLES = new WorldGenDecorations(Blocks.PEBBLES);
  private static final WorldGenDecorations DECORATION_STICKS = new WorldGenDecorations(Blocks.STICKS);

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    if (world.provider.getDimensionType() != DimensionType.NETHER && world.provider.getDimensionType() != DimensionType.THE_END) {
      generateVeins(flint_ore, random, world, chunkX, chunkZ, FLINT_ORE_CONFIG);
      generateVeins(quartzite_ore, random, world, chunkX, chunkZ, QUARTZITE_ORE_CONFIG);
      generateDecorations(DECORATION_PEBBLES, random, world, chunkX, chunkZ);
      generateDecorations(DECORATION_STICKS, random, world, chunkX, chunkZ);
    }
  }

  private void generateVeins(WorldGenMinable generator, Random random, World world, int chunkX, int chunkZ, WorldGenConfig config) {
    for (int i = 0; i < config.veinsPerChunk; i++) {
      int x = chunkX * 16 + 8 + random.nextInt(16);
      int y = config.minHeight + random.nextInt(config.maxHeight - config.minHeight);
      int z = chunkZ * 16 + 8 + random.nextInt(16);
      generator.generate(world, random, new BlockPos(x, y, z));
    }
  }

  private void generateDecorations(WorldGenDecorations generator, Random random, World world, int chunkX, int chunkZ) {

      int x = chunkX * 16 + 8 + random.nextInt(16);
      int z = chunkZ * 16 + 8 + random.nextInt(16);
      BlockPos pos = new BlockPos(x, 0, z);
      pos = pos.add(0, world.getTopSolidOrLiquidBlock( pos ).getY()+1,0);
      generator.generate(world, random, pos);

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
