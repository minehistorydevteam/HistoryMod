package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.item.QuartziteArmorItem;
import com.historydevteam.historymod.item.TatteredClothArmorItem;
import com.historydevteam.historymod.item.SpearItem;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class Items {

  public static final Item STONE_WHEEL = create("stone_wheel", Reference.HISTORY_ITEM_GROUP);

  public static final Item SPEAR = new SpearItem();

  public static final Item TATTERED_CLOTH = create("tattered_cloth", Reference.HISTORY_ITEM_GROUP);

  public static final TatteredClothArmorItem TATTERED_CLOTH_CAP =
      new TatteredClothArmorItem(EquipmentSlotType.HEAD);

  public static final TatteredClothArmorItem TATTERED_CLOTH_TUNIC =
      new TatteredClothArmorItem(EquipmentSlotType.CHEST);

  public static final TatteredClothArmorItem TATTERED_CLOTH_PANTS =
      new TatteredClothArmorItem(EquipmentSlotType.LEGS);

  public static final TatteredClothArmorItem TATTERED_CLOTH_BOOTS =
      new TatteredClothArmorItem(EquipmentSlotType.FEET);

  public static final Item QUARTZITE = create("quartzite", Reference.HISTORY_ITEM_GROUP);
  public static final QuartziteArmorItem QUARTZITE_CAP =
      new QuartziteArmorItem(EquipmentSlotType.HEAD);

  public static final QuartziteArmorItem QUARTZITE_TUNIC =
      new QuartziteArmorItem(EquipmentSlotType.CHEST);

  public static final QuartziteArmorItem QUARTZITE_PANTS =
      new QuartziteArmorItem(EquipmentSlotType.LEGS);

  public static final QuartziteArmorItem QUARTZITE_BOOTS =
      new QuartziteArmorItem(EquipmentSlotType.FEET);

  public static final Item BEADS = create("beads", Reference.HISTORY_ITEM_GROUP);
  public static final Item PRIMITIVE_AXE = create("primitive_axe", Reference.HISTORY_ITEM_GROUP);
  public static final Item PRIMITIVE_HAMMER = create("primitive_hammer", Reference.HISTORY_ITEM_GROUP);
  public static final Item STONE_DAGGER = create("stone_dagger", Reference.HISTORY_ITEM_GROUP);
  public static final Item ATL_ATL = create("atl_atl", Reference.HISTORY_ITEM_GROUP);

  private static Item create(String name, ItemGroup tabs) {
    return new Item(new Item.Properties().group(tabs)).setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
  }
}
