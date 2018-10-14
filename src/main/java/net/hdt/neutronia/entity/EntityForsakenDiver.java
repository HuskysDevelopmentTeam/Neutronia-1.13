package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.hdt.neutronia.init.NItems;
import net.hdt.neutronia.util.handlers.LootTableHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

public class EntityForsakenDiver extends EntityMob {

    public static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityForsakenDiver.class, DataSerializers.BOOLEAN);

    public EntityForsakenDiver(World worldIn) {
        super(NEntityTypes.FORSAKEN, worldIn);
        this.setSize(0.6F, 1.95F);
        moveHelper = new AnglerMoveHelper(this);
        setPathPriority(PathNodeType.WALKABLE, -8.0F);
        setPathPriority(PathNodeType.BLOCKED, -8.0F);
        setPathPriority(PathNodeType.WATER, 16.0F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public boolean canSpawn(IWorld world) {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ))).getBlock() == Blocks.WATER;
    }

    public boolean isGrounded() {
        return !isInWater() && world.isAirBlock(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY + 1), MathHelper.floor(posZ))) && world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY - 1), MathHelper.floor(posZ))).getBlock().isCollidable();
    }

    @Override
    protected PathNavigate createNavigator(World world){
        return new PathNavigateSwimmer(this, world);
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
    public void livingTick() {
        if (getEntityWorld().isRemote) {
            if (isInWater()) {
/*                Vec3d vec3d = getLook(0.0F);
                for (int i = 0; i < 2; ++i)
                    getEntityWorld().spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX + (rand.nextDouble() - 0.5D) * (double) width - vec3d.x * 1.5D, posY + rand.nextDouble() * (double) height - vec3d.y * 1.5D, posZ + (rand.nextDouble() - 0.5D) * (double) width - vec3d.z * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);*/
            }
        }

        if (inWater) {
            setAir(300);
        } else if (onGround) {
            motionY += 0.5D;
            motionX += (double) ((rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
            motionZ += (double) ((rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
            rotationYaw = rand.nextFloat() * 360.0F;
            onGround = false;
            isAirBorne = true;
            if(getEntityWorld().getGameTime() % 5 == 0)
                getEntityWorld().playSound(null, posX, posY, posZ, SoundEvents.ENTITY_GUARDIAN_FLOP, SoundCategory.HOSTILE, 1F, 1F);
            damageEntity(DamageSource.DROWN, 0.5F);
        }

        super.livingTick();
    }

    @Override
    public void tick() {
        if(!getEntityWorld().isRemote) {
            if(getAttackTarget() != null && !getEntityWorld().containsAnyLiquid(getAttackTarget().getBoundingBox())) {
                Double distance = getPosition().getDistance((int) getAttackTarget().posX, (int) getAttackTarget().posY, (int) getAttackTarget().posZ);
                if (distance > 1.0F && distance < 6.0F) // && getAttackTarget().getBoundingBox().maxY >= getBoundingBox().minY && getAttackTarget().getBoundingBox().minY <= getBoundingBox().maxY && rand.nextInt(3) == 0)
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
        super.tick();
    }

    @Override
    public void travel(float strafe, float up, float forward) {
        if (isServerWorld()) {
            if (isInWater()) {
                moveRelative(strafe, up,  forward, 0.1F);
                move(MoverType.SELF, motionX, motionY, motionZ);
                motionX *= 0.8999999761581421D;
                motionY *= 1D;
                motionZ *= 0.8999999761581421D;

                if (getAttackTarget() == null) {
                    motionY -= 0.005D;
                }
            } else {
                super.travel(strafe, up, forward);
            }
        } else {
            super.travel(strafe, up, forward);
        }
    }

    /**
     * Checks that the entity is not colliding with any blocks / liquids
     */
    /*public boolean isNotColliding()
    {
        return this.world.checkNoEntityCollision(this.getBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getBoundingBox()).isEmpty();
    }*/

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.getDataManager().register(ARMS_RAISED, false);
    }

    public boolean isArmsRaised() {
        return this.getDataManager().get(ARMS_RAISED);
    }

    public void setArmsRaised(boolean armsRaised) {
        this.getDataManager().set(ARMS_RAISED, armsRaised);
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    public boolean isPushedByWater()
    {
        return false;
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

    /*@Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, @Nullable IEntityLivingData livingData, @Nullable NBTTagCompound tagCompound) {
        IEntityLivingData p_onInitialSpawn_2_ = super.onInitialSpawn(difficulty, livingdata, nbtTagCompound);
        float lvt_4_1_ = difficulty.getClampedAdditionalDifficulty();

        if (p_onInitialSpawn_2_ == null) {
            p_onInitialSpawn_2_ = new EntityLostMiner.GroupData(this.world.rand.nextFloat() < 0.05F);
        }

        if (p_onInitialSpawn_2_ instanceof EntityZombie.GroupData) {
            EntityZombie.GroupData lvt_5_1_ = (EntityZombie.GroupData)p_onInitialSpawn_2_;
            if (lvt_5_1_.isChild) {
                if ((double)this.world.rand.nextFloat() < 0.05D) {
                    List<EntityChicken> lvt_6_1_ = this.world.getEntitiesWithinAABB(EntityChicken.class, this.getBoundingBox().grow(5.0D, 3.0D, 5.0D), EntitySelectors.IS_STANDALONE);
                    if (!lvt_6_1_.isEmpty()) {
                        EntityChicken lvt_7_1_ = lvt_6_1_.get(0);
                        lvt_7_1_.setChickenJockey(true);
                        this.startRiding(lvt_7_1_);
                    }
                } else if ((double)this.world.rand.nextFloat() < 0.05D) {
                    EntityChicken lvt_6_2_ = new EntityChicken(this.world);
                    lvt_6_2_.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    lvt_6_2_.onInitialSpawn(difficulty, null, null);
                    lvt_6_2_.setChickenJockey(true);
                    this.world.spawnEntity(lvt_6_2_);
                    this.startRiding(lvt_6_2_);
                }
            }

            this.setEquipmentBasedOnDifficulty(difficulty);
            this.setEnchantmentBasedOnDifficulty(difficulty);
        }

        if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
            LocalDate lvt_5_2_ = LocalDate.now();
            int lvt_6_3_ = lvt_5_2_.get(ChronoField.DAY_OF_MONTH);
            int lvt_7_2_ = lvt_5_2_.get(ChronoField.MONTH_OF_YEAR);
            if (lvt_7_2_ == 10 && lvt_6_3_ == 31 && this.rand.nextFloat() < 0.25F) {
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
            }
        }

        this.applyAttributeBonuses(lvt_4_1_);
        return p_onInitialSpawn_2_;
    }*/

    public double getYOffset() {
        return -0.45D;
    }

    static class AnglerMoveHelper extends EntityMoveHelper {
        private final EntityForsakenDiver angler;

        public AnglerMoveHelper(EntityForsakenDiver angler) {
            super(angler);
            this.angler = angler;
        }

        @Override
        public void tick() {
            if (action == Action.MOVE_TO && !angler.getNavigator().noPath()) {
                double d0 = posX - angler.posX;
                double d1 = posY - angler.posY;
                double d2 = posZ - angler.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = (double) MathHelper.sqrt(d3);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
                angler.rotationYaw = limitAngle(angler.rotationYaw, f, 90.0F);
                angler.renderYawOffset = angler.rotationYaw;
                float f1 = (float) (speed * angler.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
                angler.setAIMoveSpeed(angler.getAIMoveSpeed() + (f1 - angler.getAIMoveSpeed()) * 0.125F);
                double d4 = Math.sin((double) (angler.ticksExisted + angler.getEntityId()) * 0.5D) * 0.05D;
                double d5 = Math.cos((double) (angler.rotationYaw * 0.017453292F));
                double d6 = Math.sin((double) (angler.rotationYaw * 0.017453292F));
                angler.motionX += d4 * d5;
                angler.motionZ += d4 * d6;
                d4 = Math.sin((double) (angler.ticksExisted + angler.getEntityId()) * 0.75D) * 0.05D;
                angler.motionY += d4 * (d6 + d5) * 0.25D;
                angler.motionY += (double) angler.getAIMoveSpeed() * d1 * 0.1D;
                EntityLookHelper entitylookhelper = angler.getLookHelper();
                double d7 = angler.posX + d0 / d3 * 2.0D;
                double d8 = (double) angler.getEyeHeight() + angler.posY + d1 / d3;
                double d9 = angler.posZ + d2 / d3 * 2.0D;
                double d10 = entitylookhelper.getLookPosX();
                double d11 = entitylookhelper.getLookPosY();
                double d12 = entitylookhelper.getLookPosZ();

                if (!entitylookhelper.getIsLooking()) {
                    d10 = d7;
                    d11 = d8;
                    d12 = d9;
                }

                angler.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
            } else {
                angler.setAIMoveSpeed(0.0F);
            }
        }
    }

}