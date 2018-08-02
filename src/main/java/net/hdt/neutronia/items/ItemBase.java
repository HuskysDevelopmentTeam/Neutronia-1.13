//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.hdt.neutronia.items;

import net.hdt.neutronia.util.TooltipHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

import java.util.List;

import static net.hdt.neutronia.util.Reference.MOD_ID;

public class ItemBase extends Item {

    public ItemBase(String name, ItemGroup itemGroup) {
        super(new Item.Builder().group(itemGroup));
        Item.registerItem(new ResourceLocation(MOD_ID, name), this);
    }

    public static void tooltipIfShift(List<String> tooltip, Runnable r) {
        TooltipHandler.tooltipIfShift(tooltip, r);
    }

    public static void addToTooltip(List<String> tooltip, String s, Object... format) {
        TooltipHandler.addToTooltip(tooltip, s, format);
    }

    public static String local(String s) {
        return TooltipHandler.local(s);
    }

}
