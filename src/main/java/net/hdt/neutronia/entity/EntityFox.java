package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityFox extends EntityTameable
{
    private static final DataParameter<Float> DATA_HEALTH_ID;
    private static final DataParameter<Boolean> BEGGING;
    private static final DataParameter<Integer> COLLAR_COLOR;
    private float headRotationCourse;
    private float headRotationCourseOld;
    private boolean isWet;
    private boolean isShaking;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    public EntityFox(World p_i1696_1_) {
        super(NEntityTypes.FOX, p_i1696_1_);
        this.setSize(0.6F, 0.85F);
        this.setTamed(false);
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityFox.AIAvoidEntity<>(this, EntityLlama.class, 24.0F, 1.5D, 1.5D));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed<>(this, EntityAnimal.class, false, (p_210130_0_) -> p_210130_0_ instanceof EntitySheep || p_210130_0_ instanceof EntityRabbit));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed<>(this, EntityTurtle.class, false, EntityTurtle.field_203029_bx));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, AbstractSkeleton.class, false));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        if (this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    public void setAttackTarget(@Nullable EntityLivingBase p_setAttackTarget_1_) {
        super.setAttackTarget(p_setAttackTarget_1_);
        if (p_setAttackTarget_1_ == null) {
            this.setAngry(false);
        } else if (!this.isTamed()) {
            this.setAngry(true);
        }

    }

    protected void updateAITasks() {
        this.dataManager.set(DATA_HEALTH_ID, this.getHealth());
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(DATA_HEALTH_ID, this.getHealth());
        this.dataManager.register(BEGGING, false);
        this.dataManager.register(COLLAR_COLOR, EnumDyeColor.RED.func_196059_a());
    }

    protected void playStepSound(BlockPos p_playStepSound_1_, IBlockState p_playStepSound_2_) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    public void writeEntityToNBT(NBTTagCompound p_writeEntityToNBT_1_) {
        super.writeEntityToNBT(p_writeEntityToNBT_1_);
        p_writeEntityToNBT_1_.setBoolean("Angry", this.isAngry());
        p_writeEntityToNBT_1_.setByte("CollarColor", (byte)this.getCollarColor().func_196059_a());
    }

    public void readEntityFromNBT(NBTTagCompound p_readEntityFromNBT_1_) {
        super.readEntityFromNBT(p_readEntityFromNBT_1_);
        this.setAngry(p_readEntityFromNBT_1_.getBoolean("Angry"));
        if (p_readEntityFromNBT_1_.hasKey("CollarColor", 99)) {
            this.setCollarColor(EnumDyeColor.func_196056_a(p_readEntityFromNBT_1_.getInteger("CollarColor")));
        }

    }

    protected SoundEvent getAmbientSound() {
        if (this.isAngry()) {
            return SoundEvents.ENTITY_WOLF_GROWL;
        } else if (this.rand.nextInt(3) == 0) {
            return this.isTamed() && this.dataManager.get(DATA_HEALTH_ID) < 10.0F ? SoundEvents.ENTITY_WOLF_WHINE : SoundEvents.ENTITY_WOLF_PANT;
        } else {
            return SoundEvents.ENTITY_WOLF_AMBIENT;
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_getHurtSound_1_) {
        return SoundEvents.ENTITY_WOLF_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_WOLF;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.world.isRemote && this.isWet && !this.isShaking && !this.hasPath() && this.onGround) {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
            this.world.setEntityState(this, (byte)8);
        }

        if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
            this.setAngry(false);
        }

    }

    public void onUpdate() {
        super.onUpdate();
        this.headRotationCourseOld = this.headRotationCourse;
        if (this.isBegging()) {
            this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
        } else {
            this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
        }

        if (this.isInWaterRainOrBubbleColumn()) {
            this.isWet = true;
            this.isShaking = false;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        } else if ((this.isWet || this.isShaking) && this.isShaking) {
            if (this.timeWolfIsShaking == 0.0F) {
                this.playSound(SoundEvents.ENTITY_WOLF_SHAKE, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05F;
            if (this.prevTimeWolfIsShaking >= 2.0F) {
                this.isWet = false;
                this.isShaking = false;
                this.prevTimeWolfIsShaking = 0.0F;
                this.timeWolfIsShaking = 0.0F;
            }

            if (this.timeWolfIsShaking > 0.4F) {
                float lvt_1_1_ = (float)this.getEntityBoundingBox().minY;
                int lvt_2_1_ = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * 3.1415927F) * 7.0F);

                for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
                    float lvt_4_1_ = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float lvt_5_1_ = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    this.world.addParticle(Particles.SPLASH, this.posX + (double)lvt_4_1_, (double)(lvt_1_1_ + 0.8F), this.posZ + (double)lvt_5_1_, this.motionX, this.motionY, this.motionZ);
                }
            }
        }

    }

    public boolean isWolfWet() {
        return this.isWet;
    }

    public float getShadingWhileWet(float p_getShadingWhileWet_1_) {
        return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_getShadingWhileWet_1_) / 2.0F * 0.25F;
    }

    public float getShakeAngle(float p_getShakeAngle_1_, float p_getShakeAngle_2_) {
        float lvt_3_1_ = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_getShakeAngle_1_ + p_getShakeAngle_2_) / 1.8F;
        if (lvt_3_1_ < 0.0F) {
            lvt_3_1_ = 0.0F;
        } else if (lvt_3_1_ > 1.0F) {
            lvt_3_1_ = 1.0F;
        }

        return MathHelper.sin(lvt_3_1_ * 3.1415927F) * MathHelper.sin(lvt_3_1_ * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
    }

    public float getInterestedAngle(float p_getInterestedAngle_1_) {
        return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_getInterestedAngle_1_) * 0.15F * 3.1415927F;
    }

    public float getEyeHeight() {
        return this.height * 0.8F;
    }

    public int getVerticalFaceSpeed() {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    public boolean attackEntityFrom(DamageSource p_attackEntityFrom_1_, float p_attackEntityFrom_2_) {
        if (this.isEntityInvulnerable(p_attackEntityFrom_1_)) {
            return false;
        } else {
            Entity lvt_3_1_ = p_attackEntityFrom_1_.getTrueSource();
            if (this.aiSit != null) {
                this.aiSit.setSitting(false);
            }

            if (lvt_3_1_ != null && !(lvt_3_1_ instanceof EntityPlayer) && !(lvt_3_1_ instanceof EntityArrow)) {
                p_attackEntityFrom_2_ = (p_attackEntityFrom_2_ + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(p_attackEntityFrom_1_, p_attackEntityFrom_2_);
        }
    }

    public boolean attackEntityAsMob(Entity p_attackEntityAsMob_1_) {
        boolean lvt_2_1_ = p_attackEntityAsMob_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));
        if (lvt_2_1_) {
            this.applyEnchantments(this, p_attackEntityAsMob_1_);
        }

        return lvt_2_1_;
    }

    public void setTamed(boolean p_setTamed_1_) {
        super.setTamed(p_setTamed_1_);
        if (p_setTamed_1_) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public boolean processInteract(EntityPlayer p_processInteract_1_, EnumHand p_processInteract_2_) {
        ItemStack lvt_3_1_ = p_processInteract_1_.getHeldItem(p_processInteract_2_);
        Item lvt_4_1_ = lvt_3_1_.getItem();
        if (this.isTamed()) {
            if (!lvt_3_1_.isEmpty()) {
                if (lvt_4_1_ instanceof ItemFood) {
                    ItemFood lvt_5_1_ = (ItemFood)lvt_4_1_;
                    if (lvt_5_1_.isWolfsFavoriteMeat() && this.dataManager.get(DATA_HEALTH_ID) < 20.0F) {
                        if (!p_processInteract_1_.capabilities.isCreativeMode) {
                            lvt_3_1_.shrink(1);
                        }

                        this.heal((float)lvt_5_1_.getHealAmount(lvt_3_1_));
                        return true;
                    }
                } else if (lvt_4_1_ instanceof ItemDye) {
                    EnumDyeColor lvt_5_2_ = ((ItemDye)lvt_4_1_).func_195962_g();
                    if (lvt_5_2_ != this.getCollarColor()) {
                        this.setCollarColor(lvt_5_2_);
                        if (!p_processInteract_1_.capabilities.isCreativeMode) {
                            lvt_3_1_.shrink(1);
                        }

                        return true;
                    }
                }
            }

            if (this.isOwner(p_processInteract_1_) && !this.world.isRemote && !this.isBreedingItem(lvt_3_1_)) {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget(null);
            }
        } else if (lvt_4_1_ == Items.BONE && !this.isAngry()) {
            if (!p_processInteract_1_.capabilities.isCreativeMode) {
                lvt_3_1_.shrink(1);
            }

            if (!this.world.isRemote) {
                if (this.rand.nextInt(3) == 0) {
                    this.setTamedBy(p_processInteract_1_);
                    this.navigator.clearPath();
                    this.setAttackTarget(null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte)7);
                } else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.processInteract(p_processInteract_1_, p_processInteract_2_);
    }

    public void handleStatusUpdate(byte p_handleStatusUpdate_1_) {
        if (p_handleStatusUpdate_1_ == 8) {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        } else {
            super.handleStatusUpdate(p_handleStatusUpdate_1_);
        }

    }

    public float getTailRotation() {
        if (this.isAngry()) {
            return 1.5393804F;
        } else {
            return this.isTamed() ? (0.55F - (this.getMaxHealth() - this.dataManager.get(DATA_HEALTH_ID)) * 0.02F) * 3.1415927F : 0.62831855F;
        }
    }

    public boolean isBreedingItem(ItemStack p_isBreedingItem_1_) {
        Item lvt_2_1_ = p_isBreedingItem_1_.getItem();
        return lvt_2_1_ instanceof ItemFood && ((ItemFood)lvt_2_1_).isWolfsFavoriteMeat();
    }

    public int getMaxSpawnedInChunk() {
        return 8;
    }

    public boolean isAngry() {
        return (this.dataManager.get(TAMED) & 2) != 0;
    }

    public void setAngry(boolean p_setAngry_1_) {
        byte lvt_2_1_ = this.dataManager.get(TAMED);
        if (p_setAngry_1_) {
            this.dataManager.set(TAMED, (byte)(lvt_2_1_ | 2));
        } else {
            this.dataManager.set(TAMED, (byte)(lvt_2_1_ & -3));
        }

    }

    public EnumDyeColor getCollarColor() {
        return EnumDyeColor.func_196056_a(this.dataManager.get(COLLAR_COLOR));
    }

    public void setCollarColor(EnumDyeColor p_setCollarColor_1_) {
        this.dataManager.set(COLLAR_COLOR, p_setCollarColor_1_.func_196059_a());
    }

    public EntityFox createChild(EntityAgeable p_createChild_1_) {
        EntityFox lvt_2_1_ = new EntityFox(this.world);
        UUID lvt_3_1_ = this.getOwnerId();
        if (lvt_3_1_ != null) {
            lvt_2_1_.setOwnerId(lvt_3_1_);
            lvt_2_1_.setTamed(true);
        }

        return lvt_2_1_;
    }

    public void setBegging(boolean p_setBegging_1_) {
        this.dataManager.set(BEGGING, p_setBegging_1_);
    }

    public boolean canMateWith(EntityAnimal p_canMateWith_1_) {
        if (p_canMateWith_1_ == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(p_canMateWith_1_ instanceof EntityFox)) {
            return false;
        } else {
            EntityFox lvt_2_1_ = (EntityFox)p_canMateWith_1_;
            if (!lvt_2_1_.isTamed()) {
                return false;
            } else if (lvt_2_1_.isSitting()) {
                return false;
            } else {
                return this.isInLove() && lvt_2_1_.isInLove();
            }
        }
    }

    public boolean isBegging() {
        return this.dataManager.get(BEGGING);
    }

    public boolean shouldAttackEntity(EntityLivingBase p_shouldAttackEntity_1_, EntityLivingBase p_shouldAttackEntity_2_) {
        if (!(p_shouldAttackEntity_1_ instanceof EntityCreeper) && !(p_shouldAttackEntity_1_ instanceof EntityGhast)) {
            if (p_shouldAttackEntity_1_ instanceof EntityFox) {
                EntityFox lvt_3_1_ = (EntityFox)p_shouldAttackEntity_1_;
                if (lvt_3_1_.isTamed() && lvt_3_1_.getOwner() == p_shouldAttackEntity_2_) {
                    return false;
                }
            }

            if (p_shouldAttackEntity_1_ instanceof EntityPlayer && p_shouldAttackEntity_2_ instanceof EntityPlayer && !((EntityPlayer)p_shouldAttackEntity_2_).canAttackPlayer((EntityPlayer)p_shouldAttackEntity_1_)) {
                return false;
            } else {
                return !(p_shouldAttackEntity_1_ instanceof AbstractHorse) || !((AbstractHorse)p_shouldAttackEntity_1_).isTame();
            }
        } else {
            return false;
        }
    }

    public boolean canBeLeashedTo(EntityPlayer p_canBeLeashedTo_1_) {
        return !this.isAngry() && super.canBeLeashedTo(p_canBeLeashedTo_1_);
    }

    static {
        DATA_HEALTH_ID = EntityDataManager.createKey(EntityFox.class, DataSerializers.FLOAT);
        BEGGING = EntityDataManager.createKey(EntityFox.class, DataSerializers.BOOLEAN);
        COLLAR_COLOR = EntityDataManager.createKey(EntityFox.class, DataSerializers.VARINT);
    }

    class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T> {
        private final EntityFox wolf;

        public AIAvoidEntity(EntityFox p_i47251_2_, Class<T> p_i47251_3_, float p_i47251_4_, double p_i47251_5_, double p_i47251_7_) {
            super(p_i47251_2_, p_i47251_3_, p_i47251_4_, p_i47251_5_, p_i47251_7_);
            this.wolf = p_i47251_2_;
        }

        public boolean shouldExecute() {
            if (super.shouldExecute() && this.closestLivingEntity instanceof EntityLlama) {
                return !this.wolf.isTamed() && this.avoidLlama((EntityLlama)this.closestLivingEntity);
            } else {
                return false;
            }
        }

        private boolean avoidLlama(EntityLlama p_avoidLlama_1_) {
            return p_avoidLlama_1_.getStrength() >= EntityFox.this.rand.nextInt(5);
        }

        public void startExecuting() {
            EntityFox.this.setAttackTarget(null);
            super.startExecuting();
        }

        public void updateTask() {
            EntityFox.this.setAttackTarget(null);
            super.updateTask();
        }
    }
}