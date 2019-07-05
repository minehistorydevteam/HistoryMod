package com.historydevteam.historymod.block;

import net.minecraft.block.Block;
import net.minecraft.util.math.AxisAlignedBB;

public interface ICustomAABBBlock<T extends Block> {
  T setAABB(AxisAlignedBB aabb);
}
