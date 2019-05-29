package com.historydevteam.historymod.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;

public class BlockProperties {

  // Horizontal Facing
  public static final IProperty<EnumFacing> ORIENTATION =
      PropertyEnum.create("orientation", EnumFacing.class, EnumFacing.HORIZONTALS);

  // All facings
  public static final IProperty<EnumFacing> FACING =
      PropertyEnum.create("facing", EnumFacing.class, EnumFacing.VALUES);

  public static final PropertyBool BURNING = PropertyBool.create("burning");
}
