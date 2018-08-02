package net.hdt.neutronia.blocks;

import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class BlockWaterlogged extends BlockMod implements ILiquidContainer {

    public BlockWaterlogged(Material materialIn, String name, ItemGroup itemGroup, boolean isTranslucent) {
        super(materialIn, name, itemGroup, isTranslucent);
    }

    public IFluidState getFluidState(IBlockState blockState) {
        return Fluids.WATER.getStillFluidState(false);
    }

    public boolean canContainFluid(IBlockReader blockReader, BlockPos pos, IBlockState blockState, Fluid fluid) {
        return false;
    }

    public boolean receiveFluid(IWorld world, BlockPos pos, IBlockState blockState, IFluidState fluidState) {
        return false;
    }

    public int getOpacity(IBlockState blockState, IBlockReader blockReader, BlockPos pos) {
        return Blocks.WATER.getDefaultState().getOpacity(blockReader, pos);
    }

}
