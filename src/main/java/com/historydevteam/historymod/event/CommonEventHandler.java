package com.historydevteam.historymod.event;

import com.historydevteam.historymod.registry.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonEventHandler {
  @SubscribeEvent
  public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
    World w = event.getWorld();
    if(w.isRemote || event.getItemStack().getItem() != Items.STICK) return;
    BlockPos bp = event.getPos();
    BlockState state = w.getBlockState(bp);
    BlockPos up = bp.up();
    if(event.getFace() != Direction.UP || !state.isSolid() || state.getContainer(w, bp) != null || !w.getBlockState(up).isAir(w, up))
      return;
    w.setBlockState(up, Blocks.STICKS.getDefaultState());
    if(event.getEntityPlayer().abilities.isCreativeMode) return;
    ItemStack is = event.getItemStack();
    is.setCount(is.getCount() -1);
  }
}
