package com.historydevteam.historymod.block;

import com.historydevteam.historymod.util.IField;
import com.historydevteam.historymod.util.ReflectionUtil;
import net.minecraft.block.Block;
import net.minecraftforge.common.ToolType;

public interface IHarvestableBlock<T extends Block> {
  IField<Integer> HARVEST_LEVEL = ReflectionUtil.getHandle("harvestLevel", Block.class);
  IField<ToolType> HARVEST_TOOL = ReflectionUtil.getHandle("harvestTool", Block.class);

  static <T extends Block> T setHarvestLevel(ToolType type, HarvestLevel level, T block) {
    HARVEST_LEVEL.setValue(block, level.value);
    HARVEST_TOOL.setValue(block, type);
    return block;
  }

  T setHarvestLevel(ToolType toolType, HarvestLevel harvestLevel);

  enum HarvestLevel {
    HAND(-1), WOOD(0), STONE(1), IRON(2), DIAMOND(3), COBALT(4), LEVEL5(5);

    public final int value;

    HarvestLevel(int i) {
      value = i;
    }
  }
}
