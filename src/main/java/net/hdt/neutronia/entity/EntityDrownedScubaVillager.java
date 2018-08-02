package net.hdt.neutronia.entity;

import net.hdt.neutronia.util.handlers.LootTableHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;


/**
 * TODO: Fix Texture and Model
 */
public class EntityDrownedScubaVillager extends EntityScubaVillager {

    private static final DataParameter<Boolean> CONVERTING = EntityDataManager.createKey(EntityDrownedScubaVillager.class, DataSerializers.BOOLEAN);

    private int conversionTime;
    private UUID converstionStarter;

    public EntityDrownedScubaVillager(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CONVERTING, Boolean.FALSE);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("ConversionTime", this.isConverting() ? this.conversionTime : -1);

        if (this.converstionStarter != null) {
            compound.setUniqueId("ConversionStarter", this.converstionStarter);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        if (compound.hasKey("ConversionTime", 99) && compound.getInteger("ConversionTime") > -1) {
            this.startConverting(compound.hasUniqueId("ConversionPlayer") ? compound.getUniqueId("ConversionPlayer") : null, compound.getInteger("ConversionTime"));
        }
    }

    @Override
    public void onUpdate() {
        if (!this.world.isRemote && this.isConverting()) {
            int i = this.getConversionProgress();
            this.conversionTime -= i;

            if (this.conversionTime <= 0) {
                this.finishConversion();
            }
        }
        super.onUpdate();
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.GOLDEN_APPLE && this.isPotionActive(MobEffects.WEAKNESS)) {
            if (!player.capabilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            if (!this.world.isRemote) {
                this.startConverting(player.getUniqueID(), this.rand.nextInt(2401) + 3600);
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean canDespawn() {
        return !this.isConverting();
    }

    public boolean isConverting() {
        return this.getDataManager().get(CONVERTING);
    }

    protected void startConverting(@Nullable UUID conversionStarterIn, int conversionTimeIn) {
        this.converstionStarter = conversionStarterIn;
        this.conversionTime = conversionTimeIn;
        this.getDataManager().set(CONVERTING, Boolean.TRUE);
        this.getActivePotionEffects().remove(MobEffects.WEAKNESS);
        this.getActivePotionEffects().add(new PotionEffect(MobEffects.STRENGTH, conversionTimeIn, Math.min(this.world.getDifficulty().getId() - 1, 0)));
        this.world.setEntityState(this, (byte) 16);
    }

    public void handleStatusUpdate(byte id) {
        if (id == 16) {
            if (!this.isSilent()) {
                this.world.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
            }
        } else {
            super.handleStatusUpdate(id);
        }
    }

    protected void finishConversion() {
        EntityScubaVillager entityvillager = new EntityScubaVillager(this.world);
        entityvillager.copyLocationAndAnglesFrom(this);
        this.world.removeEntity(this);
        entityvillager.setNoAI(this.isAIDisabled());

        if (this.hasCustomName()) {
//            entityvillager.setCustomNameTag(this.getCustomNameTag());
//            entityvillager.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
        }

        this.world.spawnEntity(entityvillager);
        entityvillager.getActivePotionEffects().add(new PotionEffect(MobEffects.NAUSEA, 200, 0));
        this.world.playEvent(null, 1027, new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), 0);
    }

    protected int getConversionProgress() {
        int i = 1;

        if (this.rand.nextFloat() < 0.01F) {
            int j = 0;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (int k = (int) this.posX - 4; k < (int) this.posX + 4 && j < 14; ++k) {
                for (int l = (int) this.posY - 4; l < (int) this.posY + 4 && j < 14; ++l) {
                    for (int i1 = (int) this.posZ - 4; i1 < (int) this.posZ + 4 && j < 14; ++i1) {
                        Block block = this.world.getBlockState(blockpos$mutableblockpos.setPos(k, l, i1)).getBlock();

                        if (block == Blocks.IRON_BARS || block instanceof BlockBed) {
                            if (this.rand.nextFloat() < 0.3F) {
                                ++i;
                            }

                            ++j;
                        }
                    }
                }
            }
        }

        return i;
    }

    protected float getSoundPitch() {
        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F;
    }

    /**
     * TODO: FIX NEW SOUNDS
     */

    public SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_VILLAGER_AMBIENT;
    }

    public SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_VILLAGER_HURT;
    }

    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_VILLAGER_DEATH;
    }

    //TODO: ^

    public SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_VILLAGER_STEP;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableHandler.MUMMY_VILLAGER;
    }


    /* ======================================== FORGE START =====================================*/

    protected ItemStack getSkullDrop() {
        return ItemStack.EMPTY;
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);
    }

    /* ======================================== FORGE END =====================================*/
}
