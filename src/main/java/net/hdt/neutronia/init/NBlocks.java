package net.hdt.neutronia.init;

import net.hdt.neutronia.blocks.BlockNetherGlowingBase;
import net.hdt.neutronia.blocks.BlockOverworldBase;
import net.hdt.neutronia.enums.EnumGlowingNetherBlocks;
import net.hdt.neutronia.enums.EnumNewStoneVariants;
import net.hdt.neutronia.enums.EnumSoulStoneTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import org.dimdev.rift.listener.BlockAdder;

public class NBlocks implements BlockAdder {
    public static Block blackSand;
    private static Block wrautnaut;
    private static Block wrautnautOld;
    private static Block wrautnautPorthole;
    public static final Block[] newStoneVariants = new Block[26];
    public static final Block[] glowingNetherBlocks = new Block[24];
    private static final Block[] soulStone = new Block[3];
    private static Block ash;
    private static Block[] frostedClay = new Block[16];
    private static final Block[] centeredGlazedTerracottaBlocks = new Block[16];

    @Override
    public void registerBlocks() {
        wrautnaut = new BlockOverworldBase(Material.IRON, "wrautnaut", false);
        wrautnautOld = new BlockOverworldBase(Material.IRON, "old_wrautnaut", false);
        wrautnautPorthole = new BlockOverworldBase(Material.IRON, "wrautnaut_porthole", true);
        ash = new BlockOverworldBase(Material.SAND, "ash", false);
        for (EnumGlowingNetherBlocks enumGlowingNetherBlocks : EnumGlowingNetherBlocks.values()) {
            glowingNetherBlocks[enumGlowingNetherBlocks.getMetadata()] = new BlockNetherGlowingBase(Material.GLASS, enumGlowingNetherBlocks.getName());
        }
        for (EnumSoulStoneTypes soulStoneTypes : EnumSoulStoneTypes.values()) {
            soulStone[soulStoneTypes.getMetadata()] = new BlockOverworldBase(Material.ROCK, soulStoneTypes.getName(), false);
        }
        for (EnumNewStoneVariants newStoneVariant : EnumNewStoneVariants.values()) {
            newStoneVariants[newStoneVariant.getMetadata()] = new BlockOverworldBase(Material.ROCK, newStoneVariant.getName(), false);
        }
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
//            frostedClay[dyeColor.func_196059_a()] = new BlockOverworldBase(Material.ROCK, String.format("frozen_%s_terracotta", dyeColor.getName()), true);
            centeredGlazedTerracottaBlocks[dyeColor.getId()] = new BlockOverworldBase(Material.ROCK, String.format("centered_glazed_terracotta_%s", dyeColor.getName()), false);
        }
        blackSand = new BlockFalling(Block.Builder.create(Material.SAND));
        Block.register(new ResourceLocation("neutronia:black_sand"), blackSand);
    }

}