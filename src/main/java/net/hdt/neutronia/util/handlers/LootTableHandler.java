package net.hdt.neutronia.util.handlers;

import net.hdt.neutronia.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {

    public static final ResourceLocation MUMMY = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "mummy"));
    public static final ResourceLocation MUMMY_VILLAGER = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "mummy_villager"));
    public static final ResourceLocation SCUBA_VILLAGER = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "scuba_villager"));
    public static final ResourceLocation DROWNED_SCUBA_VILLAGER = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "drowned_scuba_villager"));
    public static final ResourceLocation SCORPION = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "scorpion"));
    public static final ResourceLocation SHADOW_PHANTOM = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "shadow_phantom"));
    public static final ResourceLocation RED_PHANTOM = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "red_phantom"));
    public static final ResourceLocation ENDER_PHANTOM = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "ender_phantom"));
    public static final ResourceLocation LOST_MINER = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "lost_miner"));
    public static final ResourceLocation FORSAKEN_DIVER = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "forsaken_diver"));
    public static final ResourceLocation ANCHORED = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "anchored"));
    public static final ResourceLocation GREAT_HUNGER = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "great_hunger"));
}
