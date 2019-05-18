package com.historydevteam.historymod.item;

import com.historydevteam.historymod.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nullable;

public class ItemBase extends Item {

    @Override
    public Item setTranslationKey(String key) {
        // Add prefix to avoid collisions with translation keys in other mods
        return super.setTranslationKey(Reference.MOD_ID + "." + key);
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return Reference.HISTORY_CREATIVE_TAB;
    }

}
