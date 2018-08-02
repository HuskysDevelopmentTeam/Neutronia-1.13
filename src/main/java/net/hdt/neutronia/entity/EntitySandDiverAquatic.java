package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntitySandDiverAquatic extends EntityMob {

    public EntitySandDiverAquatic(World worldIn) {
        super(NEntityTypes.SANDDIVER_AQUATIC, worldIn);
    }

}
