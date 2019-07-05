package com.historydevteam.historymod.util;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public enum LoreHelper {
  INSTANCE;

  private Map<String, String> itemsLore = Maps.newHashMap();

  public static void setItemLore(ItemStack stack, List<String> lore) {
    if (stack.isEmpty()) return;

    CompoundNBT nbt = new CompoundNBT();
    ListNBT list = new ListNBT();

    for (String l : lore) {
      list.add(new StringNBT(l));
    }

    nbt.put("Lore", list);
    stack.setTagInfo("display", nbt);
  }

  public void loadLoreFile() {

    IResourceManager rm = Minecraft.getInstance().getResourceManager();
    itemsLore.clear();

    try {
      IResource res = rm.getResource(new ResourceLocation(Reference.MOD_ID, "lang/en_us_tooltips.lang"));
      BufferedReader reader = new BufferedReader(new InputStreamReader(res.getInputStream()));

      while (true) {
        String line = reader.readLine();
        if (line == null) break;

        int index = line.indexOf('=');

        if (index < 0 || line.charAt(0) == '#') continue;

        String resourceLocation = line.substring(0, index).trim();
        String lore = line.substring(index + 1).trim();

        ResourceLocation id = new ResourceLocation(resourceLocation);

        itemsLore.put(id.toString(), lore);
      }
      reader.close();

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @SubscribeEvent
  public void updateTooltip(ItemTooltipEvent e) {
    String id = e.getItemStack().getItem().getRegistryName().toString();
    String lore = itemsLore.get(id);

    if (lore != null) {
      setItemLore(e.getItemStack(), Collections.singletonList(lore));
    }
  }
}
