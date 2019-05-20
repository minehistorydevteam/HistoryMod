package com.historydevteam.historymod.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.input.Keyboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Debug {

  // To use this, add breakpoint in
  // net.minecraft.inventory.ContainerWorkbench#onCraftMatrixChanged
  // and set the breakpoint condition to:
  // "Debug.createRecipe(craftMatrix, player)"
  public static boolean createRecipe(InventoryCrafting inv, EntityPlayer player) {
    BufferedWriter writer = null;

    try {
      List<String> chars = Arrays.asList(
          "A", "B", "C",
          "D", "E", "F",
          "G", "H", "I"
      );

      Map<JsonObject, String> map = Maps.newHashMap();
      String[] slots = new String[]{"", "", "", "", "", "", "", "", ""};

      for (int i = 0; i < inv.getHeight(); i++) {
        for (int j = 0; j < inv.getWidth(); j++) {
          ItemStack item = inv.getStackInRowAndColumn(i, j);

          if (!item.isEmpty()) {
            JsonObject obj = deserializeToJsonWithOreDict(item);

            String c = map.get(obj);
            if (c == null) {
              c = chars.get(map.size());
              map.put(obj, c);
            }

            slots[i + j * 3] = c;
          }
        }
      }

      JsonArray pattern = new JsonArray();

      int start0 = 0;
      int start1 = 0;
      int end0 = 2;
      int end1 = 2;
      if (slots[0].isEmpty() && slots[3].isEmpty() && slots[6].isEmpty()) {
        //ignore first column
        start0++;
      }
      if (slots[2].isEmpty() && slots[5].isEmpty() && slots[8].isEmpty()) {
        //ignore last column
        end0--;
      }
      if (slots[0].isEmpty() && slots[1].isEmpty() && slots[2].isEmpty()) {
        //ignore first row
        start1++;
      }
      if (slots[6].isEmpty() && slots[7].isEmpty() && slots[8].isEmpty()) {
        //ignore last row
        end1--;
      }

      for (int y = start1; y <= end1; y++) {
        StringBuilder line = new StringBuilder();
        for (int x = start0; x <= end0; x++) {
          line.append(slots[x + y * 3]);
        }
        pattern.add(line.toString());
      }

      JsonObject key = new JsonObject();

      for (Map.Entry<JsonObject, String> m : map.entrySet()) {
        key.add(m.getValue(), m.getKey());
      }

      ItemStack handItem = player.getHeldItem(EnumHand.MAIN_HAND);

      JsonObject obj = new JsonObject();
      obj.addProperty("type", "forge:ore_shaped");
      obj.add("pattern", pattern);
      obj.add("key", key);
      obj.add("result", deserializeToJson(handItem));

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String jsonStr = gson.toJson(obj);

      if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
        File folder = new File("../src/main/resources/assets/historymod/recipes");
        String fileName = handItem.getTranslationKey()
            .replace(".name", "");

        fileName = fileName.substring(fileName.lastIndexOf('.') + 1);

        File file = new File(folder, fileName + ".json");

        writer = new BufferedWriter(new FileWriter(file));
        writer.write(jsonStr);

        System.out.println("Saved: " + file.exists() + ", path: " + file.getAbsolutePath());
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  public static JsonObject deserializeToJson(ItemStack stack) {
    JsonObject obj = new JsonObject();
    if (stack.isEmpty()) return obj;

    obj.addProperty("item", stack.getItem().getRegistryName().toString());
    obj.addProperty("count", stack.getCount());

    if (stack.getHasSubtypes()) {
      obj.addProperty("data", stack.getItemDamage());
    }

    if (stack.getTagCompound() != null) {
      obj.addProperty("nbt", stack.getTagCompound().toString());
    }

    return obj;
  }

  public static JsonObject deserializeToJsonWithOreDict(ItemStack stack) {
    JsonObject obj = new JsonObject();
    if (stack.isEmpty()) return obj;

    int[] ids = OreDictionary.getOreIDs(stack);

    if (ids.length < 1) {
      return deserializeToJson(stack);
    }

    String name = OreDictionary.getOreName(ids[0]);
    obj.addProperty("type", "forge:ore_dict");
    obj.addProperty("ore", name);
    obj.addProperty("count", stack.getCount());

    return obj;
  }
}
