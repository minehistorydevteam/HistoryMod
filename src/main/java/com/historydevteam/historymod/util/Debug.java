/*
package com.historydevteam.historymod.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.KeyboardListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ServerChunkProvider;
import net.minecraft.world.storage.SaveFormat;
import net.minecraft.world.storage.WorldSummary;

import javax.swing.plaf.basic.BasicTreeUI;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Debug {

  public static final boolean DEV_ENV = (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

  // To use this, add breakpoint in
  // net.minecraft.inventory.container.WorkbenchContainer#onCraftMatrixChanged
  // and set the breakpoint condition to:
  // "Debug.createRecipe(field_75162_e, player)"
  public static boolean createRecipe(CraftingInventory inv, PlayerEntity player) {
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
          ItemStack item = inv.getStackInSlot(i, j);

          if (!item.isEmpty()) {
            JsonObject obj = deserializeToJsonWithTags(item);

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

      ItemStack handItem = player.getHeldItem(Hand.MAIN_HAND);

      JsonObject obj = new JsonObject();
      obj.addProperty("type", "forge:ore_shaped");
      obj.add("pattern", pattern);
      obj.add("key", key);
      obj.add("result", deserializeToJson(handItem));

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String jsonStr = gson.toJson(obj);

      if () {
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

    if (stack.getDamage() != 0) {
      obj.addProperty("data", stack.getDamage());
    }

    if (stack.getTag() != null) {
      obj.addProperty("nbt", stack.getTag().toString());
    }

    return obj;
  }

  public static JsonObject deserializeToJsonWithTags(ItemStack stack) {
    JsonObject obj = new JsonObject();
    if (stack.isEmpty()) return obj;

    Collection<ResourceLocation> tags = ItemTags.getCollection().getOwningTags(stack.getItem());

    if (tags.isEmpty()) {
      return deserializeToJson(stack);
    }

    obj.addProperty("type", "tag");
    obj.addProperty("ore", tags.iterator().next().toString());
    obj.addProperty("count", stack.getCount());

    return obj;
  }

  public static class DebugCommand extends CommandBase {

    @Override
    public void execute(MinecraftServer server, CommandSource sender, String[] args) throws CommandException {
      if (args.length < 1) {
        sender.sendErrorMessage(new StringTextComponent("Missing order, try /hm-debug regenerate"));
        return;
      }

      if ("regenerate".equals(args[0])) {
        regenChunks(sender, sender.getWorld(), sender.getPos());

      } else if ("ticks".equals(args[0])) {
        int tps = (args.length < 2) ? 20 : Integer.parseInt(args[1]);
        setTickPerSecond(tps);

      } else if ("reload".equals(args[0])) {
        Minecraft.getInstance(). -> {
          Minecraft.getInstance().mouseHelper.ungrabMouse();
          Minecraft.getInstance().func_213237_g().complete(null);

          SaveFormat isaveformat = Minecraft.getInstance().getSaveLoader();
          List<WorldSummary> list;

          try {
            list = isaveformat.getSaveList();
          } catch (AnvilConverterException anvilconverterexception) {
            return;
          }

          Collections.sort(list);
          FMLClient.tryLoadExistingWorld(null, list.get(0));
        });

      } else {

        sender.sendErrorMessage(new StringTextComponent("Unknown order, try /hm-debug regenerate"));
      }
    }
  }

  public static void regenChunks(CommandSource sender, World world, BlockPos pos) {
    ServerChunkProvider prov = (ServerChunkProvider) world.getChunkProvider();
    sender.sendFeedback(new StringTextComponent("Regenerating 5x5 chunks"), true);

    for (int i = -5; i <= 5; i++) {
      for (int j = -5; j <= 5; j++) {
        int chunkX = i + (pos.getX() >> 4);
        int chunkZ = j + (pos.getZ() >> 4);

        Chunk chunk = world.getChunk(chunkX, chunkZ);
        if (chunk != null) {

//          Chunk newTerrain = prov.chunkGenerator.generateChunk(chunkX, chunkZ);
//
//          for (int y = 0; y < 256; y++) {
//            for (int x = 0; x < 16; x++) {
//              for (int z = 0; z < 16; z++) {
//                world.setBlockState(new BlockPos(x + chunkX, y, z + chunkZ), newTerrain.getBlockState(x, y, z));
//              }
//            }
//          }

          chunk.set(false);
          chunk.populate(prov, prov.chunkGenerator);
        }
      }
    }

    sender.sendFeedback(new StringTextComponent("done"), true);
  }

  */
/**
   * Changes the game's tps
   * Please, don't use this outside the dev environment
   *
   * @param tps number of ticks per second
   *//*

  public static void setTickPerSecond(int tps) {
    if (tps < 1) {
      tps = 1;
    }

    try {
      Field timerField = Minecraft.class.getDeclaredField("timer");
      timerField.setAccessible(true);
      Timer timer = (Timer) timerField.get(Minecraft.getInstance());

      Field tickField = Timer.class.getDeclaredField("tickLength");
      tickField.setAccessible(true);
      tickField.set(timer, 1000.0f / tps);

    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
*/
