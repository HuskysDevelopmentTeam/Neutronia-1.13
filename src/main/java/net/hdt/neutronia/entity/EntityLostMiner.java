package net.hdt.neutronia.entity;

import net.hdt.neutronia.init.NEntityTypes;
import net.hdt.neutronia.util.handlers.LootTableHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.UUID;

public class EntityLostMiner extends EntityMob {

    protected static final IAttribute SPAWN_REINFORCEMENTS_CHANCE = (new RangedAttribute(null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
    private static final UUID BABY_SPEED_BOOST_ID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier BABY_SPEED_BOOST;
    private static final DataParameter<Boolean> IS_CHILD;
    private static final DataParameter<Boolean> ARMS_RAISED;

    private float minerWidth = -1.0F;
    private float minerHeight;

    public EntityLostMiner(World worldIn) {
        super(NEntityTypes.LOST_MINER, worldIn);
        this.setSize(0.6F, 1.95F);
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
        this.getDataManager().register(IS_CHILD, false);
        this.getDataManager().register(ARMS_RAISED, false);
    }

    public boolean isArmsRaised() {
        return this.getDataManager().get(ARMS_RAISED);
    }

    public void setArmsRaised(boolean armsRaised) {
        this.getDataManager().set(ARMS_RAISED, armsRaised);
    }

    public boolean isChild() {
        return this.getDataManager().get(IS_CHILD);
    }

    protected int getExperiencePoints(EntityPlayer p_getExperiencePoints_1_) {
        if (this.isChild()) {
            this.experienceValue = (int)((float)this.experienceValue * 2.5F);
        }

        return super.getExperiencePoints(p_getExperiencePoints_1_);
    }

    public void setChild(boolean p_setChild_1_) {
        this.getDataManager().set(IS_CHILD, p_setChild_1_);
        if (this.world != null && !this.world.isRemote) {
            IAttributeInstance lvt_2_1_ = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
            lvt_2_1_.removeModifier(BABY_SPEED_BOOST);
            if (p_setChild_1_) {
                lvt_2_1_.applyModifier(BABY_SPEED_BOOST);
            }
        }

        this.setChildSize(p_setChild_1_);
    }

    public void notifyDataManagerChange(DataParameter<?> p_notifyDataManagerChange_1_) {
        if (IS_CHILD.equals(p_notifyDataManagerChange_1_)) {
            this.setChildSize(this.isChild());
        }

        super.notifyDataManagerChange(p_notifyDataManagerChange_1_);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return super.attackEntityFrom(source, amount);
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
    protected void playStepSound(BlockPos pos, IBlockState iBlockState) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.MUMMY;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);

        if (this.rand.nextFloat() < (this.world.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
            int i = this.rand.nextInt(3);

            if (i == 0)
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_PICKAXE));
            else
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Item.getItemFromBlock(Blocks.SAND)));
        }
    }

    public void writeAdditional(NBTTagCompound p_writeAdditional_1_) {
        super.writeAdditional(p_writeAdditional_1_);
        if (this.isChild()) {
            p_writeAdditional_1_.putBoolean("IsBaby", true);
        }
    }

    public void readAdditional(NBTTagCompound p_readAdditional_1_) {
        super.readAdditional(p_readAdditional_1_);
        if (p_readAdditional_1_.getBoolean("IsBaby")) {
            this.setChild(true);
        }
    }

    @Override
    public float getEyeHeight() {
        float f = 1.74F;
        if (this.isChild())
            f = (float) ((double) f - 0.81D);
        return f;
    }

    @Override
    protected boolean canEquipItem(ItemStack stack) {
        return stack.getItem() == Items.DIAMOND_PICKAXE;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata, NBTTagCompound nbtTagCompound) {
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
    }

    protected void func_207301_a(boolean p_207301_1_, boolean p_207301_2_, boolean p_207301_3_, boolean p_207301_4_) {
        this.setCanPickUpLoot(p_207301_1_);
        this.applyAttributeBonuses(this.world.getDifficultyForLocation(new BlockPos(this)).getClampedAdditionalDifficulty());
        this.setChild(p_207301_3_);
        this.setNoAI(p_207301_4_);
    }

    protected void applyAttributeBonuses(float p_applyAttributeBonuses_1_) {
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
        double lvt_2_1_ = this.rand.nextDouble() * 1.5D * (double)p_applyAttributeBonuses_1_;
        if (lvt_2_1_ > 1.0D) {
            this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random zombie-spawn bonus", lvt_2_1_, 2));
        }

        if (this.rand.nextFloat() < p_applyAttributeBonuses_1_ * 0.05F) {
            this.getAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
        }

    }

    public void setChildSize(boolean p_setChildSize_1_) {
        this.multiplySize(p_setChildSize_1_ ? 0.5F : 1.0F);
    }

    protected final void setSize(float p_setSize_1_, float p_setSize_2_) {
        boolean lvt_3_1_ = this.minerWidth > 0.0F && this.minerHeight > 0.0F;
        this.minerWidth = p_setSize_1_;
        this.minerHeight = p_setSize_2_;
        if (!lvt_3_1_) {
            this.multiplySize(1.0F);
        }

    }

    protected final void multiplySize(float p_multiplySize_1_) {
        super.setSize(this.minerWidth * p_multiplySize_1_, this.minerHeight * p_multiplySize_1_);
    }

    public double getYOffset() {
        return this.isChild() ? 0.0D : -0.45D;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (cause.getTrueSource() instanceof EntityCreeper) {
            EntityCreeper entityCreeper = (EntityCreeper) cause.getTrueSource();

            if (entityCreeper.getPowered() && entityCreeper.ableToCauseSkullDrop()) {
                entityCreeper.incrementDroppedSkulls();
                ItemStack itemStack = this.getSkullDrop();

                if (!itemStack.isEmpty())
                    this.entityDropItem(itemStack, 0.0F);
            }
        }
    }

    private ItemStack getSkullDrop() {
        return ItemStack.EMPTY;
    }

    static {
        BABY_SPEED_BOOST = new AttributeModifier(BABY_SPEED_BOOST_ID, "Baby speed boost", 0.5D, 1);
        IS_CHILD = EntityDataManager.createKey(EntityZombie.class, DataSerializers.BOOLEAN);
        ARMS_RAISED = EntityDataManager.createKey(EntityZombie.class, DataSerializers.BOOLEAN);
    }

    public class GroupData implements IEntityLivingData {
        public boolean isChild;

        private GroupData(boolean p_i47328_2_) {
            this.isChild = p_i47328_2_;
        }
    }

}