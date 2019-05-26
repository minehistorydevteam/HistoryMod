package com.historydevteam.historymod.item;

import com.google.common.collect.Multimap;
import com.historydevteam.historymod.entity.EntityThrownSpear;
import com.historydevteam.historymod.registry.Items;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemSpear extends Item {

  private final float attackDamage;

  public ItemSpear() {
    setRegistryName(Reference.MOD_ID, "spear");
    setTranslationKey(Reference.MOD_ID + ".spear");
    setCreativeTab(Reference.HISTORY_CREATIVE_TAB);
    this.maxStackSize = 64;
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


  public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
    if (entityLiving instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer) entityLiving;
      ItemStack itemstack = entityLiving.getActiveItemStack();

      int i = this.getMaxItemUseDuration(stack) - timeLeft;
      if (i < 0) return;

      if (entityplayer.capabilities.isCreativeMode) {
        if (itemstack.isEmpty()) {
          itemstack = new ItemStack(Items.SPEAR);
        }
      }

      double f = getArrowVelocity(i);

      if (f < 0.1D) return;

      if (!worldIn.isRemote) {
        EntityThrownSpear entity = createThrownSpear(worldIn, itemstack, entityplayer);
        entity.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0, (float) f * 3.0F, 1.0F);

        if (f == 1) {
          entity.setIsCritical(true);
        }

        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

        if (j > 0) {
          entity.setDamage(entity.getDamage() + j * 0.5D + 0.5D);
        }

        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

        if (k > 0) {
          entity.setKnockbackStrength(k);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
          entity.setFire(100);
        }
        worldIn.spawnEntity(entity);
      }

      worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
          SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
          1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + (float) f * 0.5F);

      if (!entityplayer.capabilities.isCreativeMode) {
        entityplayer.inventory.deleteStack(itemstack);
      }
    }

  }

  private static EntityThrownSpear createThrownSpear(World worldIn, ItemStack itemstack, EntityPlayer entityplayer) {
    EntityThrownSpear spear = new EntityThrownSpear(worldIn, entityplayer);
    if (!entityplayer.capabilities.isCreativeMode) itemstack.damageItem(1, entityplayer);

    ItemStack itemToPickup = itemstack.copy();
    itemToPickup.setCount(1);
    spear.setSpearItem(itemToPickup);
    return spear;
  }

  private static double getArrowVelocity(int charge) {
    double d = charge / 20.;
    d = (d * d + d * 2.0F) / 3.;

    if (d > 1) {
      d = 1;
    }

    return d;
  }

  public int getMaxItemUseDuration(ItemStack stack) {
    return 72000;
  }

  @Override
  public EnumAction getItemUseAction(ItemStack stack) {
    return EnumAction.BOW;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,
                                                  EnumHand handIn) {
    playerIn.setActiveHand(handIn);
    return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
  }

  @Override
  public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
    return toRepair.getItem().equals(this) && repair.getItem().equals(Item.getItemFromBlock(Blocks.COBBLESTONE));
  }
}
