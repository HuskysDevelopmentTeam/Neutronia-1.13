package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.world.World;

public class EntityWoodGolem extends EntityGolemBase {

    public EntityWoodGolem(World worldIn)
    {
        super(NEntityTypes.WOOD_GOLEM, worldIn);
    }

}
