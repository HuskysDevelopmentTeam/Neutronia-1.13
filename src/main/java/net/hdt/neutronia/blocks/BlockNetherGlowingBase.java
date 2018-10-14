package net.hdt.neutronia.blocks;

import net.minecraft.block.BlockBubbleColumn;
import net.minecraft.block.BlockMagma;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import java.util.Random;

public class BlockNetherGlowingBase extends BlockMod {

    public BlockNetherGlowingBase(Material material, String name) {
        super(material, name, ItemGroup.BUILDING_BLOCKS, false);
    }

    @Override
    public MapColor getMapColor(IBlockState p_getMapColor_1_, IBlockReader p_getMapColor_2_, BlockPos p_getMapColor_3_) {
        return MapColor.NETHERRACK;
    }

    public void onEntityWalk(World p_onEntityWalk_1_, BlockPos p_onEntityWalk_2_, Entity p_onEntityWalk_3_) {
        if (!p_onEntityWalk_3_.isImmuneToFire() && p_onEntityWalk_3_ instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalker((EntityLivingBase)p_onEntityWalk_3_)) {
            p_onEntityWalk_3_.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
        }

        super.onEntityWalk(p_onEntityWalk_1_, p_onEntityWalk_2_, p_onEntityWalk_3_);
    }

    public int getPackedLightmapCoords(IBlockState p_getPackedLightmapCoords_1_, IWorldReader p_getPackedLightmapCoords_2_, BlockPos p_getPackedLightmapCoords_3_) {
        return 15728880;
    }

    public void tick(IBlockState p_tick_1_, World p_tick_2_, BlockPos p_tick_3_, Random p_tick_4_) {
        BlockBubbleColumn.placeBubbleColumn(p_tick_2_, p_tick_3_.up(), true);
    }

    public IBlockState updatePostPlacement(IBlockState p_updatePostPlacement_1_, EnumFacing p_updatePostPlacement_2_, IBlockState p_updatePostPlacement_3_, IWorld p_updatePostPlacement_4_, BlockPos p_updatePostPlacement_5_, BlockPos p_updatePostPlacement_6_) {
        if (p_updatePostPlacement_2_ == EnumFacing.UP && p_updatePostPlacement_3_.getBlock() == Blocks.WATER) {
            p_updatePostPlacement_4_.getPendingBlockTicks().scheduleTick(p_updatePostPlacement_5_, this, this.tickRate(p_updatePostPlacement_4_));
        }

        return super.updatePostPlacement(p_updatePostPlacement_1_, p_updatePostPlacement_2_, p_updatePostPlacement_3_, p_updatePostPlacement_4_, p_updatePostPlacement_5_, p_updatePostPlacement_6_);
    }

    public void randomTick(IBlockState p_randomTick_1_, World p_randomTick_2_, BlockPos p_randomTick_3_, Random p_randomTick_4_) {
        BlockPos lvt_5_1_ = p_randomTick_3_.up();
        if (p_randomTick_2_.getFluidState(p_randomTick_3_).isTagged(FluidTags.WATER)) {
            p_randomTick_2_.playSound((EntityPlayer)null, p_randomTick_3_, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (p_randomTick_2_.rand.nextFloat() - p_randomTick_2_.rand.nextFloat()) * 0.8F);
            if (p_randomTick_2_ instanceof WorldServer) {
                ((WorldServer)p_randomTick_2_).spawnParticle(Particles.LARGE_SMOKE, (double)lvt_5_1_.getX() + 0.5D, (double)lvt_5_1_.getY() + 0.25D, (double)lvt_5_1_.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }

    }

    public int tickRate(IWorldReaderBase p_tickRate_1_) {
        return 20;
    }

    public void onBlockAdded(IBlockState p_onBlockAdded_1_, World p_onBlockAdded_2_, BlockPos p_onBlockAdded_3_, IBlockState p_onBlockAdded_4_) {
        p_onBlockAdded_2_.getPendingBlockTicks().scheduleTick(p_onBlockAdded_3_, this, this.tickRate(p_onBlockAdded_2_));
    }

    public boolean canEntitySpawn(IBlockState p_canEntitySpawn_1_, Entity p_canEntitySpawn_2_) {
        return p_canEntitySpawn_2_.isImmuneToFire();
    }

    public boolean needsPostProcessing(IBlockState p_needsPostProcessing_1_, IBlockReader p_needsPostProcessing_2_, BlockPos p_needsPostProcessing_3_) {
        return true;
    }

}
