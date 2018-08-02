package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityWellWisher extends EntityMob {

    public EntityWellWisher(World worldIn) {
        super(NEntityTypes.WELL_WISHER, worldIn);
    }

}
