package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityPlatypus extends EntityMob {

    public EntityPlatypus(World worldIn) {
        super(NEntityTypes.PLATYPUS, worldIn);
        setSize(0.5F, 0.5F);
    }

}
