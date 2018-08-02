package net.hdt.neutronia.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockOverworldBase extends BlockMod{
    public BlockOverworldBase(Material material, String name, boolean isTranslucent) {
        super(material, name, ItemGroup.BUILDING_BLOCKS, isTranslucent);
    }
}
