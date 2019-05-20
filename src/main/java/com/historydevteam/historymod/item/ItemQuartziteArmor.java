package com.historydevteam.historymod.item;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemQuartziteArmor extends ItemArmor {

  public static final ArmorMaterial QUARTZITE_MATERIAL = EnumHelper
      .addArmorMaterial("quartzite", "quartzite", 5, new int[]{1, 2, 3, 1}, 9,
          SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);

  public ItemQuartziteArmor(EntityEquipmentSlot equipmentSlotIn) {
    super(QUARTZITE_MATERIAL, 0, equipmentSlotIn);
    switch(equipmentSlotIn) {
      case FEET:
        setTranslationKey(Reference.MOD_ID + ".quartzite_boots");
        setRegistryName(Reference.MOD_ID, "quartzite_boots");
        break;
      case HEAD:
        setTranslationKey(Reference.MOD_ID + ".quartzite_helmet");
        setRegistryName(Reference.MOD_ID, "quartzite_helmet");
        break;
      case LEGS:
        setTranslationKey(Reference.MOD_ID + ".quartzite_leggings");
        setRegistryName(Reference.MOD_ID, "quartzite_leggings");
        break;
      case CHEST:
        setTranslationKey(Reference.MOD_ID + ".quartzite_chestplate");
        setRegistryName(Reference.MOD_ID, "quartzite_chestplate");
    }
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
  }


  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
    int layer = (slot == EntityEquipmentSlot.LEGS) ? 2 : 1;
    return Reference.MOD_ID + ":textures/models/armor/" + getArmorMaterial().name().toLowerCase() + "_layer_" + layer + ".png";
  }
}
