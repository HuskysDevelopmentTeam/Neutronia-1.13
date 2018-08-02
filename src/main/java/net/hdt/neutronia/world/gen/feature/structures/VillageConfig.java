package net.hdt.neutronia.world.gen.feature.structures;

import net.minecraft.world.gen.feature.IFeatureConfig;

public class VillageConfig implements IFeatureConfig {
    public final int field_202461_a;
    public final VillagePieces.Type field_202462_b;

    public VillageConfig(int p_i48666_1_, VillagePieces.Type p_i48666_2_) {
        this.field_202461_a = p_i48666_1_;
        this.field_202462_b = p_i48666_2_;
    }
}
