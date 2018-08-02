package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.hdt.neutronia.init.NItems;
import net.hdt.neutronia.util.handlers.LootTableHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityAnchored extends EntityMob implements IRangedAttackMob {

    private boolean field_204718_bx;
    protected final PathNavigateSwimmer navigateSwimmer;
    protected final PathNavigateGround navigateGround;

    public EntityAnchored(World worldIn) {
        super(NEntityTypes.ANCHORED, worldIn);
        this.setSize(0.6F, 1.95F);
        moveHelper = new EntityAnchored.MoveHelper(this);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.navigateSwimmer = new PathNavigateSwimmer(this, worldIn);
        this.navigateGround = new PathNavigateGround(this, worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAnchored.AIGoToWater(this, 1.0D));
        this.tasks.addTask(2, new EntityAnchored.AIAnchorAttack(this, 1.0D, 40, 10.0F));
//        this.tasks.addTask(2, new EntityAnchored.AIAttack(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAnchored.AIGoToBeach(this, 1.0D));
        this.tasks.addTask(6, new EntityAnchored.AISwimUp(this, 1.0D, this.world.getSeaLevel()));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(0, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    /*@Override
    public boolean getCanSpawnHere() {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ))).getBlock() == Blocks.WATER;
    }*/

    public boolean isGrounded() {
        return !isInWater() && world.isAirBlock(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY + 1), MathHelper.floor(posZ))) && world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY - 1), MathHelper.floor(posZ))).getBlock().isCollidable();
    }

    protected PathNavigate createNavigator(World p_createNavigator_1_) {
        return super.createNavigator(p_createNavigator_1_);
    }

    public boolean func_205020_a(IWorld p_205020_1_) {
        Biome lvt_2_1_ = p_205020_1_.getBiome(new BlockPos(this.posX, this.posY, this.posZ));
        if (lvt_2_1_ != Biomes.RIVER && lvt_2_1_ != Biomes.FROZEN_RIVER) {
            return this.rand.nextInt(40) == 0 && this.func_204712_dC() && super.func_205020_a(p_205020_1_);
        } else {
            return this.rand.nextInt(15) == 0 && super.func_205020_a(p_205020_1_);
        }
    }

    private boolean func_204712_dC() {
        return this.getEntityBoundingBox().minY < (double)(this.world.getSeaLevel() - 5);
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public float getBlockPathWeight(BlockPos pos) {
        return world.getBlockState(pos).getMaterial() == Material.WATER ? 10.0F + world.getLight(pos) - 0.5F : super.getBlockPathWeight(pos);
    }

    @Override
    public void onLivingUpdate() {
        if (getEntityWorld().isRemote) {
            if (isInWater()) {
/*                Vec3d vec3d = getLook(0.0F);
                for (int i = 0; i < 2; ++i)
                    getEntityWorld().addParticle(Particles.BUBBLE, posX + (rand.nextDouble() - 0.5D) * (double) width - vec3d.x * 1.5D, posY + rand.nextDouble() * (double) height - vec3d.y * 1.5D, posZ + (rand.nextDouble() - 0.5D) * (double) width - vec3d.z * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);*/
            }
        }

        if (inWater) {
            setAir(300);
        } else if (onGround) {

        }

        super.onLivingUpdate();
    }

    @Override
    public void onUpdate() {
        if(!getEntityWorld().isRemote) {
            if(getAttackTarget() != null && !getEntityWorld().containsAnyLiquid(getAttackTarget().getEntityBoundingBox())) {
                Double distance = getPosition().getDistance((int) getAttackTarget().posX, (int) getAttackTarget().posY, (int) getAttackTarget().posZ);
                if (distance > 1.0F && distance < 6.0F) // && getAttackTarget().getEntityBoundingBox().maxY >= getEntityBoundingBox().minY && getAttackTarget().getEntityBoundingBox().minY <= getEntityBoundingBox().maxY && rand.nextInt(3) == 0)
                    if (isInWater() && getEntityWorld().isAirBlock(new BlockPos((int) posX, (int) posY + 1, (int) posZ))) {
                        double distanceX = getAttackTarget().posX - posX;
                        double distanceZ = getAttackTarget().posZ - posZ;
                        float distanceSqrRoot = MathHelper.sqrt(distanceX * distanceX + distanceZ * distanceZ);
                        motionX = distanceX / distanceSqrRoot * 0.5D * 0.900000011920929D + motionX * 0.70000000298023224D;
                        motionZ = distanceZ / distanceSqrRoot * 0.5D * 0.900000011920929D + motionZ * 0.70000000298023224D;
                        motionY = 0.4D;
                    }
            }
        }
        super.onUpdate();
    }

    @Override
    public void travel(float strafe, float up, float forward) {
        if (this.isServerWorld() && this.isInWater() && this.func_204715_dF()) {
            this.moveRelative(strafe, up, forward, 0.01F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.8999999761581421D;
            this.motionY *= 0.8999999761581421D;
            this.motionZ *= 0.8999999761581421D;
        } else {
            super.travel(strafe, up, forward);
        }
    }

    /**
     * Checks that the entity is not colliding with any blocks / liquids
     */
    /*public boolean isNotColliding()
    {
        return this.world.collideWithEntity(this.getEntityBoundingBox(), this); && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
    }*/

    protected void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPigZombie.class));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    public boolean isPushedByWater() {
        return !this.func_203007_ba();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag) {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();

            if (this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F)
                entityIn.setFire(2 * (int) f);
        }
        return flag;
    }

    /**
     * TODO: Change Sounds...
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    // TODO ^

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, IBlockState blockState) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.FORSAKEN_DIVER;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);

        if (this.rand.nextFloat() < (this.world.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
            int i = this.rand.nextInt(3);

            if (i == 0)
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(NItems.ANCHOR));
            else
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
    }

    @Override
    public float getEyeHeight() {
        return 1.74F;
    }

    @Override
    protected boolean canEquipItem(ItemStack stack) {
        return stack.getItem() == NItems.ANCHOR;
    }

    @Nullable
    @Override
    public IEntityLivingData func_204210_a(DifficultyInstance difficultyInstance, @Nullable IEntityLivingData livingData, @Nullable NBTTagCompound tagCompound) {
        livingData = super.func_204210_a(difficultyInstance, livingData, tagCompound);
        if (this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).isEmpty() && this.rand.nextFloat() < 0.03F) {
            this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(NItems.ANCHOR));
            this.inventoryHandsDropChances[EntityEquipmentSlot.OFFHAND.getIndex()] = 2.0F;
        }

        return livingData;
    }

    private boolean func_204715_dF() {
        if (this.field_204718_bx) {
            return true;
        } else {
            EntityLivingBase lvt_1_1_ = this.getAttackTarget();
            return lvt_1_1_ != null && lvt_1_1_.isInWater();
        }
    }

    private boolean func_204710_dB() {
        Path lvt_1_1_ = this.getNavigator().getPath();
        if (lvt_1_1_ != null) {
            PathPoint lvt_2_1_ = lvt_1_1_.getTarget();
            if (lvt_2_1_ != null) {
                double lvt_3_1_ = this.getDistanceSq((double)lvt_2_1_.x, (double)lvt_2_1_.y, (double)lvt_2_1_.z);
                return lvt_3_1_ < 4.0D;
            }
        }

        return false;
    }

    private boolean func_204714_e(@Nullable EntityLivingBase p_204714_1_) {
        if (p_204714_1_ != null) {
            return !this.world.isDaytime() || p_204714_1_.isInWater();
        } else {
            return false;
        }
    }

    private void func_204713_s(boolean p_204713_1_) {
        this.field_204718_bx = p_204713_1_;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (cause.getTrueSource() instanceof EntityCreeper) {
            EntityCreeper entityCreeper = (EntityCreeper) cause.getTrueSource();

            if (entityCreeper.getPowered() && entityCreeper.ableToCauseSkullDrop()) {
                entityCreeper.incrementDroppedSkulls();
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase entityLivingBase, float v) {
        
    }

    @Override
    public void setSwingingArms(boolean b) {

    }

    static class MoveHelper extends EntityMoveHelper {
        private final EntityAnchored field_204725_i;

        public MoveHelper(EntityAnchored p_i48909_1_) {
            super(p_i48909_1_);
            this.field_204725_i = p_i48909_1_;
        }

        public void onUpdateMoveHelper() {
            EntityLivingBase lvt_1_1_ = this.field_204725_i.getAttackTarget();
            if (this.field_204725_i.func_204715_dF() && this.field_204725_i.isInWater()) {
                if (lvt_1_1_ != null && lvt_1_1_.posY > this.field_204725_i.posY || this.field_204725_i.field_204718_bx) {
                    this.field_204725_i.motionY += 0.002D;
                }

                if (this.action != Action.MOVE_TO || this.field_204725_i.getNavigator().noPath()) {
                    this.field_204725_i.setAIMoveSpeed(0.0F);
                    return;
                }

                double lvt_2_1_ = this.posX - this.field_204725_i.posX;
                double lvt_4_1_ = this.posY - this.field_204725_i.posY;
                double lvt_6_1_ = this.posZ - this.field_204725_i.posZ;
                double lvt_8_1_ = (double)MathHelper.sqrt(lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_);
                lvt_4_1_ /= lvt_8_1_;
                float lvt_10_1_ = (float)(MathHelper.atan2(lvt_6_1_, lvt_2_1_) * 57.2957763671875D) - 90.0F;
                this.field_204725_i.rotationYaw = this.limitAngle(this.field_204725_i.rotationYaw, lvt_10_1_, 90.0F);
                this.field_204725_i.renderYawOffset = this.field_204725_i.rotationYaw;
                float lvt_11_1_ = (float)(this.speed * this.field_204725_i.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                this.field_204725_i.setAIMoveSpeed(this.field_204725_i.getAIMoveSpeed() + (lvt_11_1_ - this.field_204725_i.getAIMoveSpeed()) * 0.125F);
                this.field_204725_i.motionY += (double)this.field_204725_i.getAIMoveSpeed() * lvt_4_1_ * 0.1D;
                this.field_204725_i.motionX += (double)this.field_204725_i.getAIMoveSpeed() * lvt_2_1_ * 0.005D;
                this.field_204725_i.motionZ += (double)this.field_204725_i.getAIMoveSpeed() * lvt_6_1_ * 0.005D;
            } else {
                if (!this.field_204725_i.onGround) {
                    this.field_204725_i.motionY -= 0.008D;
                }

                super.onUpdateMoveHelper();
            }

        }
    }

    /*static class AIAttack extends EntityAIZombieAttack {
        private final EntityAnchored field_204726_g;

        public AIAttack(EntityAnchored p_i48913_1_, double p_i48913_2_, boolean p_i48913_4_) {
            super(p_i48913_1_, p_i48913_2_, p_i48913_4_);
            this.field_204726_g = p_i48913_1_;
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && this.field_204726_g.func_204714_e(this.field_204726_g.getAttackTarget());
        }

        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && this.field_204726_g.func_204714_e(this.field_204726_g.getAttackTarget());
        }
    }*/

    static class AIGoToWater extends EntityAIBase {
        private final EntityCreature field_204730_a;
        private double field_204731_b;
        private double field_204732_c;
        private double field_204733_d;
        private final double field_204734_e;
        private final World field_204735_f;

        public AIGoToWater(EntityCreature p_i48910_1_, double p_i48910_2_) {
            this.field_204730_a = p_i48910_1_;
            this.field_204734_e = p_i48910_2_;
            this.field_204735_f = p_i48910_1_.world;
            this.setMutexBits(1);
        }

        public boolean shouldExecute() {
            if (!this.field_204735_f.isDaytime()) {
                return false;
            } else if (this.field_204730_a.isInWater()) {
                return false;
            } else {
                Vec3d lvt_1_1_ = this.func_204729_f();
                if (lvt_1_1_ == null) {
                    return false;
                } else {
                    this.field_204731_b = lvt_1_1_.x;
                    this.field_204732_c = lvt_1_1_.y;
                    this.field_204733_d = lvt_1_1_.z;
                    return true;
                }
            }
        }

        public boolean shouldContinueExecuting() {
            return !this.field_204730_a.getNavigator().noPath();
        }

        public void startExecuting() {
            this.field_204730_a.getNavigator().tryMoveToXYZ(this.field_204731_b, this.field_204732_c, this.field_204733_d, this.field_204734_e);
        }

        @Nullable
        private Vec3d func_204729_f() {
            Random lvt_1_1_ = this.field_204730_a.getRNG();
            BlockPos lvt_2_1_ = new BlockPos(this.field_204730_a.posX, this.field_204730_a.getEntityBoundingBox().minY, this.field_204730_a.posZ);

            for(int lvt_3_1_ = 0; lvt_3_1_ < 10; ++lvt_3_1_) {
                BlockPos lvt_4_1_ = lvt_2_1_.add(lvt_1_1_.nextInt(20) - 10, 2 - lvt_1_1_.nextInt(8), lvt_1_1_.nextInt(20) - 10);
                if (this.field_204735_f.getBlockState(lvt_4_1_).getBlock() == Blocks.WATER) {
                    return new Vec3d((double)lvt_4_1_.getX(), (double)lvt_4_1_.getY(), (double)lvt_4_1_.getZ());
                }
            }

            return null;
        }
    }

    static class AIGoToBeach extends EntityAIMoveToBlock {
        private final EntityAnchored anchored;

        public AIGoToBeach(EntityAnchored anchored, double p_i48911_2_) {
            super(anchored, p_i48911_2_, 8, 2);
            this.anchored = anchored;
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && !this.anchored.world.isDaytime() && this.anchored.isInWater() && this.anchored.posY >= (double)(this.anchored.world.getSeaLevel() - 3);
        }

        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting();
        }

        protected boolean shouldMoveTo(IWorldReaderBase p_shouldMoveTo_1_, BlockPos p_shouldMoveTo_2_) {
            BlockPos lvt_3_1_ = p_shouldMoveTo_2_.up();
            return (p_shouldMoveTo_1_.isAirBlock(lvt_3_1_) && p_shouldMoveTo_1_.isAirBlock(lvt_3_1_.up())) && p_shouldMoveTo_1_.getBlockState(p_shouldMoveTo_2_).isTopSolid();
        }

        public void startExecuting() {
            this.anchored.func_204713_s(false);
            this.anchored.navigator = this.anchored.navigateGround;
            super.startExecuting();
        }

        public void resetTask() {
            super.resetTask();
        }
    }

    static class AISwimUp extends EntityAIBase {
        private final EntityAnchored field_204736_a;
        private final double field_204737_b;
        private final int field_204738_c;
        private boolean field_204739_d;

        public AISwimUp(EntityAnchored p_i48908_1_, double p_i48908_2_, int p_i48908_4_) {
            this.field_204736_a = p_i48908_1_;
            this.field_204737_b = p_i48908_2_;
            this.field_204738_c = p_i48908_4_;
        }

        public boolean shouldExecute() {
            return !this.field_204736_a.world.isDaytime() && this.field_204736_a.isInWater() && this.field_204736_a.posY < (double)(this.field_204738_c - 2);
        }

        public boolean shouldContinueExecuting() {
            return this.shouldExecute() && !this.field_204739_d;
        }

        public void updateTask() {
            if (this.field_204736_a.posY < (double)(this.field_204738_c - 1) && (this.field_204736_a.getNavigator().noPath() || this.field_204736_a.func_204710_dB())) {
                Vec3d lvt_1_1_ = RandomPositionGenerator.findRandomTargetBlockTowards(this.field_204736_a, 4, 8, new Vec3d(this.field_204736_a.posX, (double)(this.field_204738_c - 1), this.field_204736_a.posZ));
                if (lvt_1_1_ == null) {
                    this.field_204739_d = true;
                    return;
                }

                this.field_204736_a.getNavigator().tryMoveToXYZ(lvt_1_1_.x, lvt_1_1_.y, lvt_1_1_.z, this.field_204737_b);
            }

        }

        public void startExecuting() {
            this.field_204736_a.func_204713_s(true);
            this.field_204739_d = false;
        }

        public void resetTask() {
            this.field_204736_a.func_204713_s(false);
        }
    }

    static class AIAnchorAttack extends EntityAIAttackRanged {
        private final EntityAnchored field_204728_a;

        public AIAnchorAttack(IRangedAttackMob p_i48907_1_, double p_i48907_2_, int p_i48907_4_, float p_i48907_5_) {
            super(p_i48907_1_, p_i48907_2_, p_i48907_4_, p_i48907_5_);
            this.field_204728_a = (EntityAnchored)p_i48907_1_;
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && this.field_204728_a.getHeldItemMainhand().getItem() == NItems.ANCHOR;
        }

        public void startExecuting() {
            super.startExecuting();
            this.field_204728_a.setSwingingArms(true);
        }

        public void resetTask() {
            super.resetTask();
            this.field_204728_a.setSwingingArms(false);
        }
    }

}