package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityOlDiggy extends EntityMob {

    public EntityOlDiggy(World worldIn) {
        super(NEntityTypes.OL_DIGGY, worldIn);
    }

}
