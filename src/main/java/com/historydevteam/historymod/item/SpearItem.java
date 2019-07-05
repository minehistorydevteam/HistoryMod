
package com.historydevteam.historymod.item;

import com.google.common.collect.Multimap;
import com.historydevteam.historymod.entity.ThrownSpearEntity;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.UseAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SpearItem extends Item {

  private final float attackDamage;

  public SpearItem() {
    super(new Properties().group(Reference.HISTORY_ITEM_GROUP));
    setRegistryName(Reference.MOD_ID, "spear");
    // 5 hearts
    this.attackDamage = 5.0F;
  }

  @Override
  public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    stack.damageItem(1, attacker, a -> {});
    return true;
  }

  @Override
  public int getItemEnchantability() {
    // Stone enchantability
    return 10;
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot,
      ItemStack stack) {
    Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

    if (slot == EquipmentSlotType.MAINHAND) {
      multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, AttributeModifier.Operation.ADDITION));
      multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, AttributeModifier.Operation.ADDITION));
    }

    return multimap;
  }

  public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity livingEntity, int timeLeft) {
    if (livingEntity instanceof PlayerEntity) {
      PlayerEntity entityplayer = (PlayerEntity) livingEntity;
      ItemStack itemstack = livingEntity.getActiveItemStack();

      int i = this.getMaxItemUseDuration(stack) - timeLeft;
      if (i < 0) return;

      if (entityplayer.abilities.isCreativeMode) {
        if (itemstack.isEmpty()) {
          itemstack = new ItemStack(this);
        }
      }

      double f = getArrowVelocity(i);

      if (f < 0.1D) return;

      if (!worldIn.isRemote) {
        ThrownSpearEntity entity = createThrownSpear(worldIn, itemstack, entityplayer);
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
        worldIn.addEntity(entity);
      }

      worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
          SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
          1.0F / (random.nextFloat() * 0.4F + 1.2F) + (float) f * 0.5F);

      if (!entityplayer.abilities.isCreativeMode) {
        entityplayer.inventory.deleteStack(itemstack);
      }
    }
  }

  private static ThrownSpearEntity createThrownSpear(World worldIn, ItemStack itemstack, PlayerEntity entityplayer) {
    ThrownSpearEntity spear = new ThrownSpearEntity(entityplayer, worldIn);
    if (!entityplayer.abilities.isCreativeMode) itemstack.damageItem(1, entityplayer, a -> {});

    ItemStack itemToPickup = itemstack.copy();
    itemToPickup.setCount(1);
    //spear.setSpearItem(itemToPickup);
    return spear;
  }

  private static double getArrowVelocity(int charge) {
    double d = charge / 20.;
    d = (d * d + d * 2) / 3;

    if (d > 1) {
      d = 1;
    }

    return d;
  }

  public int getMaxItemUseDuration(ItemStack stack) {
    return 72000;
  }

  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.BOW;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    playerIn.setActiveHand(handIn);
    return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
  }

  @Override
  public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
    return toRepair.getItem().equals(this) && repair.getItem().equals(Item.getItemFromBlock(Blocks.COBBLESTONE));
  }
}