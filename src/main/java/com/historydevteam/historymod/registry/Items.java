package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.item.ItemQuartziteArmor;
import com.historydevteam.historymod.item.ItemSpear;
import com.historydevteam.historymod.item.ItemTatteredClothArmor;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

 public pebble() {
    setRegistryName(Reference.MOD_ID, "pebble");
    setTranslationKey(Reference.MOD_ID + ".pebble");
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
    this.maxStackSize = 16;
    this.setMaxDamage(100);
    // 3 hearts
    this.attackDamage = 3.0F;

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
  public static final Item Pebble = create ("pebble", Reference.HISTORY_CREATIVE_TAB);
  public static final Item ATL_ATL = create("atl_atl", Reference.HISTORY_CREATIVE_TAB);


  public static void init() {
    CommonRegistry.ITEMS_TO_REGISTER.add(STONE_WHEEL);
    CommonRegistry.ITEMS_TO_REGISTER.add(SPEAR);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH_CAP);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH_TUNIC);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH_PANTS);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH_BOOTS);
    CommonRegistry.ITEMS_TO_REGISTER.add(QUARTZITE);
    CommonRegistry.ITEMS_TO_REGISTER.add(QUARTZITE_CAP);
    CommonRegistry.ITEMS_TO_REGISTER.add(QUARTZITE_TUNIC);
    CommonRegistry.ITEMS_TO_REGISTER.add(QUARTZITE_PANTS);
    CommonRegistry.ITEMS_TO_REGISTER.add(QUARTZITE_BOOTS);
    CommonRegistry.ITEMS_TO_REGISTER.add(BEADS);
    CommonRegistry.ITEMS_TO_REGISTER.add(PRIMITIVE_AXE);
    CommonRegistry.ITEMS_TO_REGISTER.add(PRIMITIVE_HAMMER);
    CommonRegistry.ITEMS_TO_REGISTER.add(STONE_DAGGER);
    CommonRegistry.ITEMS_TO_REGISTER.add(ATL_ATL);
    CommonRegistry.ITEMS_TO_REGISTER.add(pebble);
  }

  private static Item create(String name, CreativeTabs tabs) {
    return new Item()
        .setCreativeTab(tabs)
        .setTranslationKey(Reference.MOD_ID + "." + name)
        .setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
  }
}
