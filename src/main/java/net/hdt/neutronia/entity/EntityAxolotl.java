package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityAxolotl extends EntityMob {

    public EntityAxolotl(World worldIn) {
        super(NEntityTypes.AXOLOTL, worldIn);
    }

}
