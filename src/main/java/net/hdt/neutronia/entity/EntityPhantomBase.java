//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.hdt.neutronia.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class EntityPhantomBase extends EntityFlying implements IMob {
    private static final DataParameter<Integer> field_203035_a;
    private Vec3d field_203036_b;
    private BlockPos field_203037_c;
    private EntityPhantomBase.AttackPhase field_203038_bx;

    public EntityPhantomBase(EntityType type, World p_i48793_1_) {
        super(type, p_i48793_1_);
        this.field_203036_b = Vec3d.ZERO;
        this.field_203037_c = BlockPos.ORIGIN;
        this.field_203038_bx = EntityPhantomBase.AttackPhase.CIRCLE;
        this.experienceValue = 5;
        this.setSize(0.9F, 0.5F);
        this.moveHelper = new EntityPhantomBase.MoveHelper(this);
        this.lookHelper = new EntityPhantomBase.LookHelper(this);
    }

    public EntityPhantomBase(World p_i48793_1_) {
        super(EntityType.PHANTOM, p_i48793_1_);
        this.field_203036_b = Vec3d.ZERO;
        this.field_203037_c = BlockPos.ORIGIN;
        this.field_203038_bx = EntityPhantomBase.AttackPhase.CIRCLE;
        this.experienceValue = 5;
        this.setSize(0.9F, 0.5F);
        this.moveHelper = new EntityPhantomBase.MoveHelper(this);
        this.lookHelper = new EntityPhantomBase.LookHelper(this);
    }

    protected EntityBodyHelper createBodyHelper() {
        return new EntityPhantomBase.BodyHelper(this);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityPhantomBase.AIPickAttack());
        this.tasks.addTask(2, new EntityPhantomBase.AISweepAttack());
        this.tasks.addTask(3, new EntityPhantomBase.AIOrbitPoint());
        this.targetTasks.addTask(1, new EntityPhantomBase.AIAttackPlayer());
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(field_203035_a, 0);
    }

    public void func_203034_a(int p_203034_1_) {
        if (p_203034_1_ < 0) {
            p_203034_1_ = 0;
        } else if (p_203034_1_ > 64) {
            p_203034_1_ = 64;
        }

        this.dataManager.set(field_203035_a, p_203034_1_);
        this.func_203033_m();
    }

    public void func_203033_m() {
        int lvt_1_1_ = (Integer)this.dataManager.get(field_203035_a);
        this.setSize(0.9F + 0.2F * (float)lvt_1_1_, 0.5F + 0.1F * (float)lvt_1_1_);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((double)(6 + lvt_1_1_));
    }

    public int func_203032_dq() {
        return (Integer)this.dataManager.get(field_203035_a);
    }

    public float getEyeHeight() {
        return this.height * 0.35F;
    }

    public void notifyDataManagerChange(DataParameter<?> p_notifyDataManagerChange_1_) {
        if (field_203035_a.equals(p_notifyDataManagerChange_1_)) {
            this.func_203033_m();
        }

        super.notifyDataManagerChange(p_notifyDataManagerChange_1_);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote) {
            float lvt_1_1_ = MathHelper.cos((float)(this.getEntityId() * 3 + this.ticksExisted) * 0.13F + 3.1415927F);
            float lvt_2_1_ = MathHelper.cos((float)(this.getEntityId() * 3 + this.ticksExisted + 1) * 0.13F + 3.1415927F);
            if (lvt_1_1_ > 0.0F && lvt_2_1_ <= 0.0F) {
                this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PHANTOM_FLAP, this.getSoundCategory(), 0.95F + this.rand.nextFloat() * 0.05F, 0.95F + this.rand.nextFloat() * 0.05F, false);
            }

            int lvt_3_1_ = this.func_203032_dq();
            float lvt_4_1_ = MathHelper.cos(this.rotationYaw * 0.017453292F) * (1.3F + 0.21F * (float)lvt_3_1_);
            float lvt_5_1_ = MathHelper.sin(this.rotationYaw * 0.017453292F) * (1.3F + 0.21F * (float)lvt_3_1_);
            float lvt_6_1_ = (0.3F + lvt_1_1_ * 0.45F) * ((float)lvt_3_1_ * 0.2F + 1.0F);
            this.world.addParticle(Particles.MYCELIUM, this.posX + (double)lvt_4_1_, this.posY + (double)lvt_6_1_, this.posZ + (double)lvt_5_1_, 0.0D, 0.0D, 0.0D);
            this.world.addParticle(Particles.MYCELIUM, this.posX - (double)lvt_4_1_, this.posY + (double)lvt_6_1_, this.posZ - (double)lvt_5_1_, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }

    }

    public void onLivingUpdate() {
        if (this.func_204609_dp()) {
            this.setFire(8);
        }

        super.onLivingUpdate();
    }

    protected void updateAITasks() {
        super.updateAITasks();
    }

    public IEntityLivingData func_204210_a(DifficultyInstance p_204210_1_, @Nullable IEntityLivingData p_204210_2_, @Nullable NBTTagCompound p_204210_3_) {
        this.field_203037_c = (new BlockPos(this)).up(5);
        this.func_203034_a(0);
        return super.func_204210_a(p_204210_1_, p_204210_2_, p_204210_3_);
    }

    public void readEntityFromNBT(NBTTagCompound p_readEntityFromNBT_1_) {
        super.readEntityFromNBT(p_readEntityFromNBT_1_);
        if (p_readEntityFromNBT_1_.hasKey("AX")) {
            this.field_203037_c = new BlockPos(p_readEntityFromNBT_1_.getInteger("AX"), p_readEntityFromNBT_1_.getInteger("AY"), p_readEntityFromNBT_1_.getInteger("AZ"));
        }

        this.func_203034_a(p_readEntityFromNBT_1_.getInteger("Size"));
    }

    public void writeEntityToNBT(NBTTagCompound p_writeEntityToNBT_1_) {
        super.writeEntityToNBT(p_writeEntityToNBT_1_);
        p_writeEntityToNBT_1_.setInteger("AX", this.field_203037_c.getX());
        p_writeEntityToNBT_1_.setInteger("AY", this.field_203037_c.getY());
        p_writeEntityToNBT_1_.setInteger("AZ", this.field_203037_c.getZ());
        p_writeEntityToNBT_1_.setInteger("Size", this.func_203032_dq());
    }

    public boolean isInRangeToRenderDist(double p_isInRangeToRenderDist_1_) {
        return true;
    }

    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PHANTOM_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_getHurtSound_1_) {
        return SoundEvents.ENTITY_PHANTOM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PHANTOM_DEATH;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.field_203250_E;
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    public boolean canAttackClass(Class<? extends EntityLivingBase> p_canAttackClass_1_) {
        return true;
    }

    static {
        field_203035_a = EntityDataManager.createKey(EntityPhantomBase.class, DataSerializers.VARINT);
    }

    class AIAttackPlayer extends EntityAIBase {
        private int field_203142_b;

        private AIAttackPlayer() {
            this.field_203142_b = 20;
        }

        public boolean shouldExecute() {
            if (this.field_203142_b > 0) {
                --this.field_203142_b;
                return false;
            } else {
                this.field_203142_b = 60;
                AxisAlignedBB lvt_1_1_ = EntityPhantomBase.this.getEntityBoundingBox().grow(16.0D, 64.0D, 16.0D);
                List<EntityPlayer> lvt_2_1_ = EntityPhantomBase.this.world.getEntitiesWithinAABB(EntityPlayer.class, lvt_1_1_);
                if (!lvt_2_1_.isEmpty()) {
                    lvt_2_1_.sort((p_203140_0_, p_203140_1_) -> {
                        return p_203140_0_.posY > p_203140_1_.posY ? -1 : 1;
                    });
                    Iterator var3 = lvt_2_1_.iterator();

                    while(var3.hasNext()) {
                        EntityPlayer lvt_4_1_ = (EntityPlayer)var3.next();
                        if (EntityAITarget.isSuitableTarget(EntityPhantomBase.this, lvt_4_1_, false, false)) {
                            EntityPhantomBase.this.setAttackTarget(lvt_4_1_);
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        public boolean shouldContinueExecuting() {
            return EntityAITarget.isSuitableTarget(EntityPhantomBase.this, EntityPhantomBase.this.getAttackTarget(), false, false);
        }
    }

    class AIPickAttack extends EntityAIBase {
        private int field_203145_b;

        private AIPickAttack() {
        }

        public boolean shouldExecute() {
            return EntityAITarget.isSuitableTarget(EntityPhantomBase.this, EntityPhantomBase.this.getAttackTarget(), false, false);
        }

        public void startExecuting() {
            this.field_203145_b = 10;
            EntityPhantomBase.this.field_203038_bx = EntityPhantomBase.AttackPhase.CIRCLE;
            this.func_203143_f();
        }

        public void resetTask() {
            EntityPhantomBase.this.field_203037_c = EntityPhantomBase.this.world.getTopBlock(Type.MOTION_BLOCKING, EntityPhantomBase.this.field_203037_c).up(10 + EntityPhantomBase.this.rand.nextInt(20));
        }

        public void updateTask() {
            if (EntityPhantomBase.this.field_203038_bx == EntityPhantomBase.AttackPhase.CIRCLE) {
                --this.field_203145_b;
                if (this.field_203145_b <= 0) {
                    EntityPhantomBase.this.field_203038_bx = EntityPhantomBase.AttackPhase.SWOOP;
                    this.func_203143_f();
                    this.field_203145_b = (8 + EntityPhantomBase.this.rand.nextInt(4)) * 20;
                    EntityPhantomBase.this.playSound(SoundEvents.ENTITY_PHANTOM_SWOOP, 10.0F, 0.95F + EntityPhantomBase.this.rand.nextFloat() * 0.1F);
                }
            }

        }

        private void func_203143_f() {
            EntityPhantomBase.this.field_203037_c = (new BlockPos(EntityPhantomBase.this.getAttackTarget())).up(20 + EntityPhantomBase.this.rand.nextInt(20));
            if (EntityPhantomBase.this.field_203037_c.getY() < EntityPhantomBase.this.world.getSeaLevel()) {
                EntityPhantomBase.this.field_203037_c = new BlockPos(EntityPhantomBase.this.field_203037_c.getX(), EntityPhantomBase.this.world.getSeaLevel() + 1, EntityPhantomBase.this.field_203037_c.getZ());
            }

        }
    }

    class AISweepAttack extends EntityPhantomBase.AIMove {
        private AISweepAttack() {
            super();
        }

        public boolean shouldExecute() {
            return EntityPhantomBase.this.getAttackTarget() != null && EntityPhantomBase.this.field_203038_bx == EntityPhantomBase.AttackPhase.SWOOP;
        }

        public void startExecuting() {
        }

        public void updateTask() {
            EntityLivingBase lvt_1_1_ = EntityPhantomBase.this.getAttackTarget();
            EntityPhantomBase.this.field_203036_b = new Vec3d(lvt_1_1_.posX, lvt_1_1_.posY + (double)lvt_1_1_.height * 0.5D, lvt_1_1_.posZ);
            if (EntityPhantomBase.this.getEntityBoundingBox().grow(0.20000000298023224D).intersects(lvt_1_1_.getEntityBoundingBox())) {
                EntityPhantomBase.this.attackEntityAsMob(lvt_1_1_);
                EntityPhantomBase.this.field_203038_bx = EntityPhantomBase.AttackPhase.CIRCLE;
                EntityPhantomBase.this.world.playEvent(1039, new BlockPos(EntityPhantomBase.this), 0);
            } else if (EntityPhantomBase.this.collidedHorizontally || EntityPhantomBase.this.hurtTime > 0) {
                EntityPhantomBase.this.field_203038_bx = EntityPhantomBase.AttackPhase.CIRCLE;
            }

        }
    }

    class AIOrbitPoint extends EntityPhantomBase.AIMove {
        private float field_203150_c;
        private float field_203151_d;
        private float field_203152_e;
        private float field_203153_f;

        private AIOrbitPoint() {
            super();
        }

        public boolean shouldExecute() {
            return EntityPhantomBase.this.getAttackTarget() == null || EntityPhantomBase.this.field_203038_bx == EntityPhantomBase.AttackPhase.CIRCLE;
        }

        public void startExecuting() {
            this.field_203151_d = 5.0F + EntityPhantomBase.this.rand.nextFloat() * 10.0F;
            this.field_203152_e = -4.0F + EntityPhantomBase.this.rand.nextFloat() * 9.0F;
            this.field_203153_f = EntityPhantomBase.this.rand.nextBoolean() ? 1.0F : -1.0F;
            this.func_203148_i();
        }

        public void updateTask() {
            if (EntityPhantomBase.this.rand.nextInt(350) == 0) {
                this.field_203152_e = -4.0F + EntityPhantomBase.this.rand.nextFloat() * 9.0F;
            }

            if (EntityPhantomBase.this.rand.nextInt(250) == 0) {
                ++this.field_203151_d;
                if (this.field_203151_d > 15.0F) {
                    this.field_203151_d = 5.0F;
                    this.field_203153_f = -this.field_203153_f;
                }
            }

            if (EntityPhantomBase.this.rand.nextInt(450) == 0) {
                this.field_203150_c = EntityPhantomBase.this.rand.nextFloat() * 2.0F * 3.1415927F;
                this.func_203148_i();
            }

            if (this.func_203146_f()) {
                this.func_203148_i();
            }

            if (EntityPhantomBase.this.field_203036_b.y < EntityPhantomBase.this.posY && !EntityPhantomBase.this.world.isAirBlock((new BlockPos(EntityPhantomBase.this)).down(1))) {
                this.field_203152_e = Math.max(1.0F, this.field_203152_e);
                this.func_203148_i();
            }

            if (EntityPhantomBase.this.field_203036_b.y > EntityPhantomBase.this.posY && !EntityPhantomBase.this.world.isAirBlock((new BlockPos(EntityPhantomBase.this)).up(1))) {
                this.field_203152_e = Math.min(-1.0F, this.field_203152_e);
                this.func_203148_i();
            }

        }

        private void func_203148_i() {
            if (BlockPos.ORIGIN.equals(EntityPhantomBase.this.field_203037_c)) {
                EntityPhantomBase.this.field_203037_c = new BlockPos(EntityPhantomBase.this);
            }

            this.field_203150_c += this.field_203153_f * 15.0F * 0.017453292F;
            EntityPhantomBase.this.field_203036_b = (new Vec3d(EntityPhantomBase.this.field_203037_c)).add((double)(this.field_203151_d * MathHelper.cos(this.field_203150_c)), (double)(-4.0F + this.field_203152_e), (double)(this.field_203151_d * MathHelper.sin(this.field_203150_c)));
        }
    }

    abstract class AIMove extends EntityAIBase {
        public AIMove() {
            this.setMutexBits(1);
        }

        protected boolean func_203146_f() {
            return EntityPhantomBase.this.field_203036_b.squareDistanceTo(EntityPhantomBase.this.posX, EntityPhantomBase.this.posY, EntityPhantomBase.this.posZ) < 4.0D;
        }
    }

    class LookHelper extends EntityLookHelper {
        public LookHelper(EntityLiving p_i48802_2_) {
            super(p_i48802_2_);
        }

        public void onUpdateLook() {
        }
    }

    class BodyHelper extends EntityBodyHelper {
        public BodyHelper(EntityLivingBase p_i48805_2_) {
            super(p_i48805_2_);
        }

        public void updateRenderAngles() {
            EntityPhantomBase.this.rotationYawHead = EntityPhantomBase.this.renderYawOffset;
            EntityPhantomBase.this.renderYawOffset = EntityPhantomBase.this.rotationYaw;
        }
    }

    class MoveHelper extends EntityMoveHelper {
        private float field_203105_j = 0.1F;

        public MoveHelper(EntityLiving p_i48801_2_) {
            super(p_i48801_2_);
        }

        public void onUpdateMoveHelper() {
            if (EntityPhantomBase.this.collidedHorizontally) {
                EntityPhantomBase.this.rotationYaw += 180.0F;
                this.field_203105_j = 0.1F;
            }

            float lvt_1_1_ = (float)(EntityPhantomBase.this.field_203036_b.x - EntityPhantomBase.this.posX);
            float lvt_2_1_ = (float)(EntityPhantomBase.this.field_203036_b.y - EntityPhantomBase.this.posY);
            float lvt_3_1_ = (float)(EntityPhantomBase.this.field_203036_b.z - EntityPhantomBase.this.posZ);
            double lvt_4_1_ = (double)MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_);
            double lvt_6_1_ = 1.0D - (double)MathHelper.abs(lvt_2_1_ * 0.7F) / lvt_4_1_;
            lvt_1_1_ = (float)((double)lvt_1_1_ * lvt_6_1_);
            lvt_3_1_ = (float)((double)lvt_3_1_ * lvt_6_1_);
            lvt_4_1_ = (double)MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_);
            double lvt_8_1_ = (double)MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_2_1_ * lvt_2_1_);
            float lvt_10_1_ = EntityPhantomBase.this.rotationYaw;
            float lvt_11_1_ = (float)MathHelper.atan2((double)lvt_3_1_, (double)lvt_1_1_);
            float lvt_12_1_ = MathHelper.wrapDegrees(EntityPhantomBase.this.rotationYaw + 90.0F);
            float lvt_13_1_ = MathHelper.wrapDegrees(lvt_11_1_ * 57.295776F);
            EntityPhantomBase.this.rotationYaw = MathHelper.func_203303_c(lvt_12_1_, lvt_13_1_, 4.0F) - 90.0F;
            EntityPhantomBase.this.renderYawOffset = EntityPhantomBase.this.rotationYaw;
            if (MathHelper.func_203301_d(lvt_10_1_, EntityPhantomBase.this.rotationYaw) < 3.0F) {
                this.field_203105_j = MathHelper.func_203300_b(this.field_203105_j, 1.8F, 0.005F * (1.8F / this.field_203105_j));
            } else {
                this.field_203105_j = MathHelper.func_203300_b(this.field_203105_j, 0.2F, 0.025F);
            }

            float lvt_14_1_ = (float)(-(MathHelper.atan2((double)(-lvt_2_1_), lvt_4_1_) * 57.2957763671875D));
            EntityPhantomBase.this.rotationPitch = lvt_14_1_;
            float lvt_15_1_ = EntityPhantomBase.this.rotationYaw + 90.0F;
            double lvt_16_1_ = (double)(this.field_203105_j * MathHelper.cos(lvt_15_1_ * 0.017453292F)) * Math.abs((double)lvt_1_1_ / lvt_8_1_);
            double lvt_18_1_ = (double)(this.field_203105_j * MathHelper.sin(lvt_15_1_ * 0.017453292F)) * Math.abs((double)lvt_3_1_ / lvt_8_1_);
            double lvt_20_1_ = (double)(this.field_203105_j * MathHelper.sin(lvt_14_1_ * 0.017453292F)) * Math.abs((double)lvt_2_1_ / lvt_8_1_);
            EntityPhantomBase.this.motionX += (lvt_16_1_ - EntityPhantomBase.this.motionX) * 0.2D;
            EntityPhantomBase.this.motionY += (lvt_20_1_ - EntityPhantomBase.this.motionY) * 0.2D;
            EntityPhantomBase.this.motionZ += (lvt_18_1_ - EntityPhantomBase.this.motionZ) * 0.2D;
        }
    }

    static enum AttackPhase {
        CIRCLE,
        SWOOP;

        private AttackPhase() {
        }
    }
}
