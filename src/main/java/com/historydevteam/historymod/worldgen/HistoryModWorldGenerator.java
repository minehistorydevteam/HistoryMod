package com.historydevteam.historymod.worldgen;

import com.historydevteam.historymod.config.HMConfig;
import com.historydevteam.historymod.config.WorldGenConfig;
import com.historydevteam.historymod.registry.Blocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Arrays;
import java.util.Random;

public class HistoryModWorldGenerator implements IWorldGenerator {

  // Ore WorldGenerators
  private static final WorldGenMinable flint_ore = new WorldGenMinable(Blocks.FLINT_ORE.getDefaultState(), HMConfig.flintOreGen.blocksPerVein);
  private static final WorldGenMinable quartzite_ore = new WorldGenMinable(Blocks.QUARTZITE_ORE.getDefaultState(), HMConfig.quartziteOreGen.blocksPerVein);

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    if (world.provider.getDimensionType() != DimensionType.NETHER && world.provider.getDimensionType() != DimensionType.THE_END) {
      generateVeins(flint_ore, random, world, chunkX, chunkZ, HMConfig.flintOreGen);
      generateVeins(quartzite_ore, random, world, chunkX, chunkZ, HMConfig.quartziteOreGen);
      generateDecorations(Blocks.PEBBLES.getDefaultState(), HMConfig.pebblesPerChunk, random, world, chunkX, chunkZ);
      generateDecorations(Blocks.STICKS.getDefaultState(), HMConfig.sticksPerChunk, random, world, chunkX, chunkZ);
    }
  }

  private void generateVeins(WorldGenMinable generator, Random random, World world, int chunkX, int chunkZ, WorldGenConfig config) {
    for (int i = 0; i < config.veinsPerChunk; i++) {
      // Note this does not need +8 because WorldGenMinable already add it
      int x = chunkX * 16 + random.nextInt(16);
      int y = config.minHeight + random.nextInt(config.maxHeight - config.minHeight);
      int z = chunkZ * 16 + random.nextInt(16);
      generator.generate(world, random, new BlockPos(x, y, z));
    }
  }

  private void generateDecorations(IBlockState ore, float blocksPerChunk, Random random, World world, int chunkX, int chunkZ) {
    int attempts = (int) blocksPerChunk;
    // Allow values smaller than 1
    if(random.nextInt(100) < (blocksPerChunk % 1) * 100){
      attempts++;
    }
    for (int i = 0; i < attempts; i++) {
      // The +8 is needed to avoid cascading world generation
      int x = chunkX * 16 + random.nextInt(16) + 8;
      int z = chunkZ * 16 + random.nextInt(16) + 8;
      int y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
      BlockPos pos = new BlockPos(x, y, z);
      generateSurfaceBlock(world, random, pos, HMConfig.surfaceBaseBlacklist, ore);
    }
  }

  private void generateSurfaceBlock(World worldIn, Random rand, BlockPos blockpos, String[] backlist, IBlockState ore) {
    if (!worldIn.isAirBlock(blockpos)) return;

    BlockPos basePos = blockpos.down(1);
    if (!worldIn.isBlockFullCube(basePos)) return;

    IBlockState state = worldIn.getBlockState(basePos);
    if (state.getBlock() instanceof IFluidBlock) return;

    String base = state.getBlock().getRegistryName().toString();
    if (Arrays.stream(backlist).anyMatch(base::equals)) return;

    worldIn.setBlockState(blockpos, ore, 2);
  }
}
