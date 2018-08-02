package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.world.World;

public class EntityGoldGolem extends EntityGolemBase {

    public EntityGoldGolem(World worldIn)
    {
        super(NEntityTypes.GOLD_GOLEM, worldIn);
    }

}