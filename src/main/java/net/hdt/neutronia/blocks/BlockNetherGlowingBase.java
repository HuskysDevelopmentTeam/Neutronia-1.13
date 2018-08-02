package net.hdt.neutronia.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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

    /**
     * Called when the given entity walks on this Block
     */
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase) entityIn)) {
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public int getPackedLightmapCoords(IBlockState p_getPackedLightmapCoords_1_, IWorldReader p_getPackedLightmapCoords_2_, BlockPos p_getPackedLightmapCoords_3_) {
        return 15728880;
    }

    public IBlockState func_196271_a(IBlockState p_196271_1_, EnumFacing p_196271_2_, IBlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (p_196271_2_ == EnumFacing.UP && p_196271_3_.getBlock() == Blocks.WATER) {
            p_196271_4_.getPendingBlockTickList().add(p_196271_5_, this, this.tickRate(p_196271_4_));
        }

        return super.func_196271_a(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    public void randomTick(IBlockState p_randomTick_1_, World p_randomTick_2_, BlockPos p_randomTick_3_, Random p_randomTick_4_) {
        BlockPos lvt_5_1_ = p_randomTick_3_.up();
        if (p_randomTick_2_.getFluidState(p_randomTick_3_).isTagged(FluidTags.WATER)) {
            p_randomTick_2_.playSound(null, p_randomTick_3_, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (p_randomTick_2_.rand.nextFloat() - p_randomTick_2_.rand.nextFloat()) * 0.8F);
            if (p_randomTick_2_ instanceof WorldServer) {
                ((WorldServer)p_randomTick_2_).func_195598_a(Particles.LARGE_SMOKE, (double)lvt_5_1_.getX() + 0.5D, (double)lvt_5_1_.getY() + 0.25D, (double)lvt_5_1_.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }

    }

    public int tickRate(IWorldReaderBase p_tickRate_1_) {
        return 20;
    }

    public void onBlockPlace(IBlockState p_onBlockPlace_1_, World p_onBlockPlace_2_, BlockPos p_onBlockPlace_3_, IBlockState p_onBlockPlace_4_) {
        p_onBlockPlace_2_.getPendingBlockTickList().add(p_onBlockPlace_3_, this, this.tickRate(p_onBlockPlace_2_));
    }

    public boolean canEntitySpawn(IBlockState p_canEntitySpawn_1_, Entity p_canEntitySpawn_2_) {
        return p_canEntitySpawn_2_.isImmuneToFire();
    }

    public boolean needsPostProcessing(IBlockState p_needsPostProcessing_1_, IBlockReader p_needsPostProcessing_2_, BlockPos p_needsPostProcessing_3_) {
        return true;
    }

}
