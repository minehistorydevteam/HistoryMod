
package com.historydevteam.historymod.item;

import com.historydevteam.historymod.entity.ThrownPebbleEntity;
import com.historydevteam.historymod.util.Reference;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class PebbleItem extends Item {
    public PebbleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemGroup(ItemGroup p_150895_1_, NonNullList<ItemStack> p_150895_2_) {
        if(p_150895_1_.equals(Reference.HISTORY_ITEM_GROUP)) p_150895_2_.add(new ItemStack(this));
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote) {
            ThrownPebbleEntity entity = new ThrownPebbleEntity(playerIn, worldIn);
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 5, 1, 1);
            worldIn.addEntity(entity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this), 1);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}