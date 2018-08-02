package net.hdt.neutronia.world.biome.overworld;

import net.hdt.neutronia.world.biome.NBiomeUtils;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;

public class BasaltBadlandsBiome extends Biome {

    public BasaltBadlandsBiome() {
        super((new BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder<>(DEFAULT_SURFACE_BUILDER, NBiomeUtils.BASALT_SURFACE)).precipitation(RainType.NONE).biomeCategory(Category.DESERT).downfall(0.0F).depth(0.2F).scale(1.0F).temperature(2.0F).waterColor(0x00FF00).waterFogColor(0x00FF00).parent(null));
    }

}