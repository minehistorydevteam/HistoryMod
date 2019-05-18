package com.historydevteam.historymod.item;

import com.google.common.collect.Multimap;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ItemSpear extends ItemBase {

  private final float attackDamage;

  public ItemSpear() {
    setRegistryName(Reference.MOD_ID, "spear");
    setTranslationKey("spear");
    this.maxStackSize = 1;
    this.setMaxDamage(150);
    // 5 hearts
    this.attackDamage = 5.0F;
  }

  @Override
  public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
    stack.damageItem(1, attacker);
    return true;
  }

  @Override
  public int getItemEnchantability() {
    // Stone enchantability
    return 10;
  }

  @Override
  public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
    Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

    if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
      multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
      multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
    }

    return multimap;
  }
}
