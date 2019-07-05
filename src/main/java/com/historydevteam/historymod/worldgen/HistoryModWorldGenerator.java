package com.historydevteam.historymod.worldgen;

import com.historydevteam.historymod.config.HMConfig;
import com.historydevteam.historymod.registry.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.AbstractChunkProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Arrays;
import java.util.Random;

public class HistoryModWorldGenerator implements IWorldGenerator {

  // Ore WorldGenerators

  //private static OreFeatureConfig o = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.FLINT_ORE.getDefaultState(), HMConfig.flintOreGen.blocksPerVein);
  //private static OreFeatureConfig o2 = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.QUARTZITE_ORE.getDefaultState(), HMConfig.quartziteOreGen.blocksPerVein);

  //private static final OreFeature flint_ore = new OreFeature(a -> o);
  //private static final OreFeature quartzite_ore = new OreFeature(a -> o2);

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, ChunkGenerator chunkGenerator, AbstractChunkProvider chunkProvider) {
    if (world.getDimension().isSurfaceWorld()) {
      //flint_ore.place(world, chunkGenerator, random, BlockPos.ZERO, o);
      //generateVeins(quartzite_ore, random, world, chunkX, chunkZ, HMConfig.quartziteOreGen);
      generateDecorations(Blocks.PEBBLES.getDefaultState(), HMConfig.pebblesPerChunk, random, world, chunkX, chunkZ);
      generateDecorations(Blocks.STICKS.getDefaultState(), HMConfig.sticksPerChunk, random, world, chunkX, chunkZ);
    }
  }



  private void generateDecorations(BlockState ore, float blocksPerChunk, Random random, World world, int chunkX, int chunkZ) {
    int attempts = (int) blocksPerChunk;
    // Allow values smaller than 1
    if(random.nextInt(100) < (blocksPerChunk % 1) * 100){
      attempts++;
    }
    for (int i = 0; i < attempts; i++) {
      // The +8 is needed to avoid cascading world generation
      int x = chunkX * 16 + random.nextInt(16) + 8;
      int z = chunkZ * 16 + random.nextInt(16) + 8;
      int y = world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, x, z);
      BlockPos pos = new BlockPos(x, y, z);
      generateSurfaceBlock(world, random, pos, HMConfig.surfaceBaseBlacklist, ore);
    }
  }

  private void generateSurfaceBlock(World worldIn, Random rand, BlockPos blockpos, String[] backlist, BlockState ore) {
    if (!worldIn.isAirBlock(blockpos)) return;

    BlockPos basePos = blockpos.down(1);
    if (!worldIn.getBlockState(basePos).isSolid()) return;

    BlockState state = worldIn.getBlockState(basePos);
    if (state.getBlock() instanceof IFluidBlock) return;

    String base = state.getBlock().getRegistryName().toString();
    if (Arrays.asList(backlist).contains(base)) return;

    worldIn.setBlockState(blockpos, ore, 2);
  }
}
