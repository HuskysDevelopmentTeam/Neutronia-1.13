package net.hdt.neutronia.init;

import net.hdt.neutronia.world.biome.nether.SoulsandDesertBiome;
import net.hdt.neutronia.world.biome.overworld.BasaltBadlandsBiome;
import net.hdt.neutronia.world.biome.overworld.DesertBlackBiome;
import net.hdt.neutronia.world.biome.overworld.DesertRedBiome;
import net.minecraft.world.biome.Biome;
import org.dimdev.rift.listener.BiomeAdder;

import java.util.Arrays;
import java.util.Collection;

public class NBiomes implements BiomeAdder {

    private Biome BASALT_BADLANDS = new BasaltBadlandsBiome();
    private Biome RED_DESERT = new DesertRedBiome();
    private Biome BLACK_DESERT = new DesertBlackBiome();
    private Biome SOULSAND_DESERT = new SoulsandDesertBiome();

    @Override
    public void registerBiomes() {
         Biome.register(1857, "neutronia:basalt_badlands", BASALT_BADLANDS);
         Biome.register(1858, "neutronia:red_desert", RED_DESERT);
         Biome.register(1859, "neutronia:black_desert", BLACK_DESERT);
         Biome.register(1860, "neutronia:soulsand_desert", SOULSAND_DESERT);
    }

    @Override
    public Collection<Biome> getOverworldBiomes() {
        return Arrays.asList(BASALT_BADLANDS, RED_DESERT, BLACK_DESERT);
    }

}
