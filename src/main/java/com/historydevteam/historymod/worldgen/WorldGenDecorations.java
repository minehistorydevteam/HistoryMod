package com.historydevteam.historymod.worldgen;

import com.historydevteam.historymod.HistoryMod;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenDecorations  extends WorldGenerator {
    private final Block block;

    public WorldGenDecorations(Block blockIn) {
        this.block = blockIn;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && !(HistoryMod.blacklist.contains(worldIn.getBlockState(blockpos).getBlock())))
            {
                worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
                System.out.println("placed x"+blockpos.getX()+", y"+blockpos.getY()+", z"+blockpos.getZ());
            }
        }

        return true;
    }
}
