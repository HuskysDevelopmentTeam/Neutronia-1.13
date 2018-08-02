package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.world.World;

public class EntityClayGolem extends EntityGolemBase {

    public EntityClayGolem(World worldIn)
    {
        super(NEntityTypes.CLAY_GOLEM, worldIn);
    }

}
