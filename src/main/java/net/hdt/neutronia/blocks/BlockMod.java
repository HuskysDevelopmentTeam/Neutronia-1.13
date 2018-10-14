package net.hdt.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;

public class BlockMod extends Block {

    private boolean isTranslucent;

    BlockMod(Material materialIn, String name, ItemGroup itemGroup, boolean isTranslucent) {
        super(Block.Builder.create(materialIn));
        this.isTranslucent = isTranslucent;
        Block.register(new ResourceLocation("neutronia", name), this);
        Item.register(this, itemGroup);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        if(isTranslucent) {
            return BlockRenderLayer.TRANSLUCENT;
        } else {
            return BlockRenderLayer.SOLID;
        }
    }

}