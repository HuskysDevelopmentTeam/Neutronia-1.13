package net.hdt.neutronia.entity.ai;

import net.hdt.neutronia.entity.EntityScorp;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIScorpAttack extends EntityAIAttackMelee {
    private final EntityScorp scorp;
    private int extendTailTicks;

    public EntityAIScorpAttack(EntityScorp scorpIn, double speedIn, boolean useLongMemory) {
        super(scorpIn, speedIn, useLongMemory);
        this.scorp = scorpIn;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.extendTailTicks = 0;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.scorp.setTailOut(false);
    }

    @Override
    public void tick() {
        super.tick();
        ++extendTailTicks;

        if (this.extendTailTicks >= 5 && this.attackTick < 10) {
            this.scorp.setTailOut(true);
        } else {
            this.scorp.setTailOut(false);
        }
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double) (5.0F + attackTarget.width);
    }
}
