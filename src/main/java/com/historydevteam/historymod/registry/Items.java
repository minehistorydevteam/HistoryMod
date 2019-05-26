package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.item.ItemPebble;
import com.historydevteam.historymod.item.ItemQuartziteArmor;
import com.historydevteam.historymod.item.ItemSpear;
import com.historydevteam.historymod.item.ItemTatteredClothArmor;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Items {

  public static final Item STONE_WHEEL = create("stone_wheel", Reference.HISTORY_CREATIVE_TAB);
  public static final Item SPEAR = new ItemSpear();
  public static final Item TATTERED_CLOTH = create("tattered_cloth", Reference.HISTORY_CREATIVE_TAB);

  public static final ItemTatteredClothArmor TATTERED_CLOTH_CAP =
      new ItemTatteredClothArmor(EntityEquipmentSlot.HEAD);

  public static final ItemTatteredClothArmor TATTERED_CLOTH_TUNIC =
      new ItemTatteredClothArmor(EntityEquipmentSlot.CHEST);

  public static final ItemTatteredClothArmor TATTERED_CLOTH_PANTS =
      new ItemTatteredClothArmor(EntityEquipmentSlot.LEGS);

  public static final ItemTatteredClothArmor TATTERED_CLOTH_BOOTS =
      new ItemTatteredClothArmor(EntityEquipmentSlot.FEET);

  public static final Item QUARTZITE = create("quartzite", Reference.HISTORY_CREATIVE_TAB);
  public static final ItemQuartziteArmor QUARTZITE_CAP =
      new ItemQuartziteArmor(EntityEquipmentSlot.HEAD);

  public static final ItemQuartziteArmor QUARTZITE_TUNIC =
      new ItemQuartziteArmor(EntityEquipmentSlot.CHEST);

  public static final ItemQuartziteArmor QUARTZITE_PANTS =
      new ItemQuartziteArmor(EntityEquipmentSlot.LEGS);

  public static final ItemQuartziteArmor QUARTZITE_BOOTS =
      new ItemQuartziteArmor(EntityEquipmentSlot.FEET);

  public static final Item BEADS = create("beads", Reference.HISTORY_CREATIVE_TAB);
  public static final Item PRIMITIVE_AXE = create("primitive_axe", Reference.HISTORY_CREATIVE_TAB);
  public static final Item PRIMITIVE_HAMMER = create("primitive_hammer", Reference.HISTORY_CREATIVE_TAB);
  public static final Item STONE_DAGGER = create("stone_dagger", Reference.HISTORY_CREATIVE_TAB);
  public static final Item ATL_ATL = create("atl_atl", Reference.HISTORY_CREATIVE_TAB);
  public static final Item PEBBLE = new ItemPebble("pebble", Reference.HISTORY_CREATIVE_TAB);

  private static Item create(String name, CreativeTabs tabs) {
    return new Item()
        .setCreativeTab(tabs)
        .setTranslationKey(Reference.MOD_ID + "." + name)
        .setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
  }
}
