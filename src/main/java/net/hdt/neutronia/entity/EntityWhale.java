package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.world.World;

public class EntityWhale extends EntityWaterMob {

    public EntityWhale(World worldIn) {
        super(NEntityTypes.WHALE, worldIn);
        setSize(0.5F, 0.5F);
    }

    protected void initEntityAI() {
        super.initEntityAI();
    }

}