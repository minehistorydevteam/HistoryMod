package com.historydevteam.historymod.config;

import com.historydevteam.historymod.util.Reference;

/**
 * Notes:
 * - Fields must be public and static
 * - Fields can't be final
 * - Fields should be lowercase for consistency
 * <p>
 * Checkout https://mcforge.readthedocs.io/en/latest/config/annotations/ for more info
 */
 //@Config(modid = Reference.MOD_ID)
public class HMConfig {


  //@Config.Name("World Gen: Flint Ore")
  public static WorldGenConfig flintOreGen = new WorldGenConfig(2, 9, 48, 80);
  //@Config.Name("World Gen: Quartzite Ore")
  public static WorldGenConfig quartziteOreGen = new WorldGenConfig(2, 9, 48, 80);

  //@Config.Name("World Gen backlisted surface blocks")
  //@Config.Comment({"List of blocks where a pebble/stick cannot be placed on top"})
  public static String[] surfaceBaseBlacklist = {
      Reference.MOD_ID + ":sticks",
      Reference.MOD_ID + ":pebbles",
      "leaves",
      "leaves2",
      "waterlily",
      "planks",
      "deadbush",
  };

  //@Config.Name("World Gen: Pebbles per chunk")
  public static float pebblesPerChunk = 1f;
  //@Config.Name("World Gen: Sticks per chunk")
  public static float sticksPerChunk = 1f;

}
