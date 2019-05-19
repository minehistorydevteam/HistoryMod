package com.historydevteam.historymod.item;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemTatteredClothArmor extends ItemArmor {

  public static final ArmorMaterial TATTERED_CLOTH_MATERIAL = EnumHelper
      .addArmorMaterial("tattered_cloth", "tattered_cloth", 5, new int[]{1, 2, 3, 1}, 9,
          SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);

  public ItemTatteredClothArmor(EntityEquipmentSlot equipmentSlotIn) {
    super(TATTERED_CLOTH_MATERIAL, 0, equipmentSlotIn);
    switch(equipmentSlotIn) {
      case FEET:
        setTranslationKey(Reference.MOD_ID + ".tattered_cloth_boots");
        setRegistryName(Reference.MOD_ID, "tattered_cloth_boots");
        break;
      case HEAD:
        setTranslationKey(Reference.MOD_ID + ".tattered_cloth_helmet");
        setRegistryName(Reference.MOD_ID, "tattered_cloth_helmet");
        break;
      case LEGS:
        setTranslationKey(Reference.MOD_ID + ".tattered_cloth_leggings");
        setRegistryName(Reference.MOD_ID, "tattered_cloth_leggings");
        break;
      case CHEST:
        setTranslationKey(Reference.MOD_ID + ".tattered_cloth_chestplate");
        setRegistryName(Reference.MOD_ID, "tattered_cloth_chestplate");
    }
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
  }


  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
    int layer = (slot == EntityEquipmentSlot.LEGS) ? 2 : 1;
    return Reference.MOD_ID + ":textures/models/armor/" + getArmorMaterial().name().toLowerCase() + "_layer_" + layer + ".png";
  }
}
