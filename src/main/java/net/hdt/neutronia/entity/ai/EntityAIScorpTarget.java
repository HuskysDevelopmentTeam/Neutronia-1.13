package net.hdt.neutronia.entity.ai;

import net.hdt.neutronia.entity.EntityScorp;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class EntityAIScorpTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
    public EntityAIScorpTarget(EntityScorp scorp, Class<T> classTarget) {
        super(scorp, classTarget, true);
    }

    public boolean shouldExecute() {
        return super.shouldExecute();
    }
}
