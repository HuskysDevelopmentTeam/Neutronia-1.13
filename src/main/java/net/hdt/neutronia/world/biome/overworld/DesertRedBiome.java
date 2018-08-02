package net.hdt.neutronia.world.biome.overworld;

import net.hdt.neutronia.init.NFeatures;
import net.hdt.neutronia.world.biome.NBiomeUtils;
import net.hdt.neutronia.world.gen.feature.structures.VillageConfig;
import net.hdt.neutronia.world.gen.feature.structures.VillagePieces;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.DungeonRoomConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;

public class DesertRedBiome extends Biome {

    public DesertRedBiome() {
        super((new BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(NBiomeUtils.RED_DESERT_SURFACE_BUILDER, NBiomeUtils.RED_SAND_SURFACE)).precipitation(RainType.NONE).biomeCategory(Category.DESERT).depth(0.125F).scale(0.05F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent(null));
        this.addStructure(NFeatures.VILLAGE, new VillageConfig(0, VillagePieces.Type.RED_SANDSTONE));
        this.setCarverForStage(GenerationStage.Carving.AIR, createWorldCarverWrapper(CAVE_WORLD_CARVER, new ProbabilityConfig(0.14285715F)));
        this.setCarverForStage(GenerationStage.Carving.AIR, createWorldCarverWrapper(CANYON_WORLD_CARVER, new ProbabilityConfig(0.02F)));
        this.addCommonStructures();
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(Feature.LAKES, new LakesConfig(Blocks.LAVA), LAVA_LAKE, new LakeChanceConfig(80)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, createCompositeFeature(Feature.DUNGEONS, IFeatureConfig.NO_FEATURE_CONFIG, DUNGEON_ROOM, new DungeonRoomConfig(8)));
    }

}
