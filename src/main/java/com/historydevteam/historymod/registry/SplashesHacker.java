package com.historydevteam.historymod.registry;

import com.google.common.collect.Lists;
import com.historydevteam.historymod.util.ReflectionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.Splashes;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;

import javax.annotation.Nullable;
import java.util.List;

public class SplashesHacker {

  public SplashesHacker() {
    new Thread(() -> {
      while(Minecraft.getInstance().getSplashes() == null) {
        try {
          Thread.sleep(1000);
        } catch(InterruptedException e) {
          e.printStackTrace();
        }
      }
      hackSplashes();
    }, "SplashesHacker").start();
  }

  private static void hackSplashes() {
    try {
      ReflectionUtil.set(Minecraft.class, "splashes", Minecraft.getInstance(), new Splashes(null) {
        @Override
        protected List<String> asyncProduce(IResourceManager p_212854_1_, IProfiler p_212854_2_) {
          return Lists.newArrayList("Hacked by History!");
        }

        @Nullable
        @Override
        public String func_215276_a() {
          return "Hacked by History!";
        }

        @Override
        protected void syncConsume(List<String> p_212853_1_, IResourceManager p_212853_2_, IProfiler p_212853_3_) {}});
    } catch(ReflectiveOperationException e) {
      e.printStackTrace();
    }
  }
}
