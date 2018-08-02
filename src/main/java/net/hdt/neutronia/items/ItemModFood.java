package net.hdt.neutronia.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

import static net.hdt.neutronia.util.Reference.MOD_ID;

public class ItemModFood extends ItemFood {

    public ItemModFood(String name, ItemGroup itemGroup, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood, new Item.Builder().group(itemGroup));
        Item.registerItem(new ResourceLocation(MOD_ID, name), this);
    }

    public ItemModFood(String name, ItemGroup itemGroup, int amount, boolean isWolfFood) {
        this(name, itemGroup, amount, 0.6F, isWolfFood);
    }
}