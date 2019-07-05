package com.historydevteam.historymod.block;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.Direction;

import java.util.EnumSet;

public class BlockProperties {


  // All facings
  public static final DirectionProperty FACING =
      DirectionProperty.create("facing", EnumSet.allOf(Direction.class));

  public static final BooleanProperty BURNING = BooleanProperty.create("burning");
}
