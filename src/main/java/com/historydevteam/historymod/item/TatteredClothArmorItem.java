package com.historydevteam.historymod.item;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class TatteredClothArmorItem extends ArmorItem {

  public static final IArmorMaterial TATTERED_CLOTH_MATERIAL = new IArmorMaterial() {
    @Override
    public int getDurability(EquipmentSlotType slotIn) {
      return 5;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
      switch(slotIn) {
        case HEAD: return 1;
        case CHEST: return 3;
        case LEGS: return 2;
        default: return 1;
      }
    }

    @Override
    public int getEnchantability() {
      return 9;
    }

    @Override
    public SoundEvent getSoundEvent() {
      return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairMaterial() {
      return null;
    }

    @Override
    public String getName() {
      return "tattered_cloth";
    }

    @Override
    public float getToughness() {
      return 0;
    }
  };

  public TatteredClothArmorItem(EquipmentSlotType equipmentSlotIn) {
    super(TATTERED_CLOTH_MATERIAL, equipmentSlotIn, new Properties());
    switch(equipmentSlotIn) {
      case FEET:
        setRegistryName(Reference.MOD_ID, "tattered_cloth_boots");
        break;
      case HEAD:
        setRegistryName(Reference.MOD_ID, "tattered_cloth_helmet");
        break;
      case LEGS:
        setRegistryName(Reference.MOD_ID, "tattered_cloth_leggings");
        break;
      case CHEST:
        setRegistryName(Reference.MOD_ID, "tattered_cloth_chestplate");
    }
  }

  @Override
  public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
    if(group.equals(Reference.HISTORY_ITEM_GROUP)) items.add(new ItemStack(this));
  }

  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
    int layer = (slot == EquipmentSlotType.LEGS) ? 2 : 1;
    return Reference.MOD_ID + ":textures/models/armor/" + getArmorMaterial().getName().toLowerCase() + "_layer_" + layer + ".png";
  }
}
