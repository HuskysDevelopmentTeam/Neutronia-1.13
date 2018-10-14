package net.hdt.neutronia.blocks;

import net.hdt.neutronia.init.NItems;
import net.hdt.neutronia.tileentities.TileEntityEasterEgg;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockEasterEgg extends BlockMod implements ITileEntityProvider {

    public static final Random random = new Random();

    public BlockEasterEgg() {
        super(Material.DRAGON_EGG, "easter_egg", ItemGroup.SEARCH, false);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader iBlockReader) {
        return new TileEntityEasterEgg(random);
    }

    @Override
    public void onBlockClicked(IBlockState blockState, World world, BlockPos pos, EntityPlayer player) {
        super.onBlockClicked(blockState, world, pos, player);
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof TileEntityEasterEgg) {
                TileEntityEasterEgg eggte = (TileEntityEasterEgg) te;
                ItemStack egg = new ItemStack(NItems.easter_egg);
                NBTTagCompound nbt = eggte.writeColorsToNBT(new NBTTagCompound());
                egg.setTag(nbt);
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), egg));
            }
            world.destroyBlock(pos, false);
        }
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

}
