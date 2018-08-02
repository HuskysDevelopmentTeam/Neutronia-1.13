package net.hdt.neutronia.world.biome;

import net.hdt.neutronia.init.NBlocks;
import net.hdt.neutronia.world.gen.surface_builders.RedDesertSurfaceBuilder;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class NBiomeUtils {

    public static final SurfaceBuilderConfig RED_SAND_SURFACE = new SurfaceBuilderConfig(Blocks.RED_SAND.getDefaultState(), Blocks.RED_SAND.getDefaultState(), Blocks.RED_SAND.getDefaultState());
    public static final SurfaceBuilderConfig BLACK_SAND_SURFACE = new SurfaceBuilderConfig(NBlocks.blackSand.getDefaultState(), NBlocks.blackSand.getDefaultState(), NBlocks.blackSand.getDefaultState());
    public static final SurfaceBuilderConfig BASALT_SURFACE = new SurfaceBuilderConfig(NBlocks.newStoneVariants[0].getDefaultState(), NBlocks.newStoneVariants[0].getDefaultState(), NBlocks.newStoneVariants[0].getDefaultState());

    public static final ISurfaceBuilder<SurfaceBuilderConfig> RED_DESERT_SURFACE_BUILDER = new RedDesertSurfaceBuilder();

}
