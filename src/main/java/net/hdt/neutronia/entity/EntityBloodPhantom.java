package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.hdt.neutronia.util.handlers.LootTableHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityBloodPhantom extends EntityPhantomBase {

    public EntityBloodPhantom(World worldIn) {
        super(NEntityTypes.BLOOD_PHANTOM, worldIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.RED_PHANTOM;
    }

}