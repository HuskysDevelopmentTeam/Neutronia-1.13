package net.hdt.neutronia.init;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.hdt.neutronia.items.ItemBandage;
import net.hdt.neutronia.items.ItemBase;
import net.hdt.neutronia.items.ItemEasterEgg;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringEscapeUtils;
import org.dimdev.rift.listener.ItemAdder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.hdt.neutronia.util.Reference.MOD_ID;

public class NItems implements ItemAdder {
    public static Item ANCHOR;
    public static Item ANCIENT_SWORD;

    public static Item BANDAGE;
//    public static Item witherBone, witherBonemeal, stinger, chitin;
    /*public static final Item HEART_OF_THE_NETHER;
    public static final Item HEART_OF_THE_ENE;

    public static final Item OBSIDIAN_AXE, OBSIDIAN_PICKAXE,  OBSIDIAN_SHOVEL, OBSIDIAN_SWORD;

    public static final Item WOODEN_SPEAR;
    public static final Item STONE_SPEAR;
    public static final Item IRON_SPEAR;
    public static final Item GOLDEN_SPEAR;
    public static final Item DIAMOND_SPEAR;*/

    public static Item[] logShields = new Item[6];
    public static Item[] planksShields = new Item[6];
    public static Item easter_egg;

    @Override
    public void registerItems() {
        ANCHOR = new ItemBase("anchor", ItemGroup.COMBAT);
        ANCIENT_SWORD = new ItemSword(ItemTier.GOLD, 3, -2.4F, (new Item.Builder()).group(ItemGroup.COMBAT));
        BANDAGE = new ItemBandage();
        easter_egg = new ItemEasterEgg();
        registerItem("ancient_sword", ANCIENT_SWORD);

        for(EntityType type : NEntityTypes.ENTITIES) {
            Item SPAWN_EGG = new ItemSpawnEgg(type, 0x000000, 0x44E4FF, (new Item.Builder()).group(ItemGroup.MISC));
            if(type == NEntityTypes.MUMMY) {
                SPAWN_EGG = new ItemSpawnEgg(type, 0xC9CE92, 0x444444, (new Item.Builder()).group(ItemGroup.MISC));
            }
            if(type == NEntityTypes.MUMMY_VILLAGER) {
                SPAWN_EGG = new ItemSpawnEgg(type, 0xC9CE92, 0x442f00, (new Item.Builder()).group(ItemGroup.MISC));
            }
            if(type == NEntityTypes.SCORPION) {
                SPAWN_EGG = new ItemSpawnEgg(type, 0x65401, 0x6201209, (new Item.Builder()).group(ItemGroup.MISC));
            }
            if(type == NEntityTypes.BLOOD_PHANTOM) {
                SPAWN_EGG = new ItemSpawnEgg(type, 0x4A2929, 0x799591, (new Item.Builder()).group(ItemGroup.MISC));
            }
            if(type == NEntityTypes.ENDER_PHANTOM) {
                SPAWN_EGG = new ItemSpawnEgg(type, 0x352D56, 0x8C9579, (new Item.Builder()).group(ItemGroup.MISC));
            }
            if(type == NEntityTypes.SHADOW_PHANTOM) {
                SPAWN_EGG = new ItemSpawnEgg(type, 0x101010, 0x101010, (new Item.Builder()).group(ItemGroup.MISC));
            }
            registerItem(String.format("%s_spawn_egg", type.func_210760_d().replaceAll("entity.neutronia.", "")), SPAWN_EGG);
            genSpawnEgg(MOD_ID, String.format("%s_spawn_egg", type.func_210760_d().replaceAll("entity.neutronia.", "")));
        }
    }

    public static void genSpawnEgg(String modId, String itemName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        JsonObject root = new JsonObject();
        root.addProperty("parent", "item/template_spawn_egg");
        String json = gson.toJson(root);
        try {
            FileUtils.writeStringToFile(base.resolve(itemName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", itemName));
        }
    }

    public static void registerItem(String name, Item item) {
        Item.registerItem(new ResourceLocation(MOD_ID, name), item);
    }

}
