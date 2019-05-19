package com.historydevteam.historymod.registry;

import com.historydevteam.historymod.item.ItemTatteredClothArmor;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Items {

  public static final Item STONE_WHEEL = create("stone_wheel", Reference.HISTORY_CREATIVE_TAB);
  public static final Item TATTERED_CLOTH = create("tattered_cloth", Reference.HISTORY_CREATIVE_TAB);
  public static final ItemTatteredClothArmor TATTERED_CLOTH_CAP = new ItemTatteredClothArmor(
      EntityEquipmentSlot.HEAD);
  public static final ItemTatteredClothArmor TATTERED_CLOTH_TUNIC = new ItemTatteredClothArmor(
      EntityEquipmentSlot.CHEST);
  public static final ItemTatteredClothArmor TATTERED_CLOTH_PANTS = new ItemTatteredClothArmor(
      EntityEquipmentSlot.LEGS);
  public static final ItemTatteredClothArmor TATTERED_CLOTH_BOOTS = new ItemTatteredClothArmor(
      EntityEquipmentSlot.FEET);

  public static void init() {
    CommonRegistry.ITEMS_TO_REGISTER.add(STONE_WHEEL);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH_CAP);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH_TUNIC);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH_PANTS);
    CommonRegistry.ITEMS_TO_REGISTER.add(TATTERED_CLOTH_BOOTS);
  }

  private static Item create(String name, CreativeTabs tabs) {
    return new Item().setCreativeTab(tabs).setTranslationKey(name)
        .setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
  }
}
