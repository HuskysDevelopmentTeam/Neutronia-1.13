package net.hdt.neutronia.init;

import net.hdt.neutronia.blocks.BlockEasterEgg;
import net.hdt.neutronia.tileentities.TileEntityEasterEgg;
import net.hdt.neutronia.util.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import org.dimdev.rift.listener.TileEntityTypeAdder;

import java.util.function.Supplier;

public class NTileEntityTypes implements TileEntityTypeAdder {

    public static TileEntityType<TileEntity> EASTER_EGG;

    @Override
    public void registerTileEntityTypes() {
        EASTER_EGG = registerEntityType("easter_egg", (Supplier<TileEntity>) new TileEntityEasterEgg(BlockEasterEgg.random));
    }

    private static TileEntityType<TileEntity> registerEntityType(String name, Supplier<TileEntity> supplier) {
        TileEntityType<TileEntity> type = TileEntityType.registerTileEntityType(Reference.MOD_ID + ":" + name, TileEntityType.Builder.create(supplier));
        return type;
    }

}
