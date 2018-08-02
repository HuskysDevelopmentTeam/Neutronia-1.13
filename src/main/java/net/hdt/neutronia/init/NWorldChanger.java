package net.hdt.neutronia.init;

import net.hdt.neutronia.world.gen.feature.structures.VillageConfig;
import net.hdt.neutronia.world.gen.feature.structures.VillagePieces;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import org.dimdev.rift.listener.WorldChanger;

public class NWorldChanger implements WorldChanger {

    @Override
    public void modifyBiome(int biomeId, String biomeName, Biome biome) {
        VillagePieces.registerVillagePieces();
        if(biomeId == 1858) {
            biome.addStructure(NFeatures.VILLAGE, new VillageConfig(0, VillagePieces.Type.RED_SANDSTONE));
        }
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, NBlocks.newStoneVariants[7].getDefaultState(), 33), Biome.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, NBlocks.newStoneVariants[5].getDefaultState(), 33), Biome.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
        if(biomeId == 8) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createCompositeFeature(Feature.MINABLE, new MinableConfig(BlockMatcher.forBlock(Blocks.NETHERRACK), NBlocks.newStoneVariants[3].getDefaultState(), 33), Biome.NETHER_MAGMA, new FrequencyConfig(4)));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createCompositeFeature(Feature.MINABLE, new MinableConfig(BlockMatcher.forBlock(Blocks.NETHERRACK), NBlocks.glowingNetherBlocks[0].getDefaultState(), 33), Biome.NETHER_MAGMA, new FrequencyConfig(4)));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createCompositeFeature(Feature.MINABLE, new MinableConfig(BlockMatcher.forBlock(Blocks.NETHERRACK), NBlocks.glowingNetherBlocks[1].getDefaultState(), 33), Biome.NETHER_MAGMA, new FrequencyConfig(4)));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createCompositeFeature(Feature.MINABLE, new MinableConfig(BlockMatcher.forBlock(Blocks.NETHERRACK), NBlocks.glowingNetherBlocks[2].getDefaultState(), 33), Biome.NETHER_MAGMA, new FrequencyConfig(4)));
        }
    }

}
