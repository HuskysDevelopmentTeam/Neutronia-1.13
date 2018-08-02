package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.hdt.neutronia.util.handlers.LootTableHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityShadowPhantom extends EntityPhantomBase {

    public EntityShadowPhantom(World worldIn) {
        super(NEntityTypes.SHADOW_PHANTOM, worldIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.SHADOW_PHANTOM;
    }

}