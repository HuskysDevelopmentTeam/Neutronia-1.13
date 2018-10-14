package net.hdt.neutronia.tileentities;

import net.hdt.neutronia.init.NTileEntityTypes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityEasterEgg extends TileEntity {

    private int color0, color1;

    public TileEntityEasterEgg(Random r) {
        super(NTileEntityTypes.EASTER_EGG);
        if (r != null) {
            this.color0 = r.nextInt(0xFFFFFF);
            this.color1 = r.nextInt(0xFFFFFF);
        }
    }

    public TileEntityEasterEgg() {
        super(NTileEntityTypes.EASTER_EGG);
        this.color0 = 0xFFFFFF;
        this.color1 = 0xFFFFFF;
    }

    @Override
    public NBTTagCompound write(NBTTagCompound compound) {
        compound = super.write(compound);
        this.writeColorsToNBT(compound);
        return compound;
    }

    public NBTTagCompound writeColorsToNBT(NBTTagCompound compound) {
        for (int i = 0; i < 2; i++) {
            compound.putInt("color" + i, this.getColor(i));
        }
        return compound;
    }

    @Override
    public void read(NBTTagCompound compound) {
        super.read(compound);
        this.readColorsFromNBT(compound);
    }

    public void readColorsFromNBT(NBTTagCompound compound) {
        for (int i = 0; i < 2; i++) {
            if (compound.contains("color" + i)) {
                this.setColor(i, compound.getInt("color" + i));
            }
        }
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeColorsToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    public int getColor(int index) {
        return index == 0 ? color0 : (index == 1 ? color1 : 0xFFFFFF);
    }

    public void setColor(int index, int color) {
        if (index == 0) {
            this.color0 = color;
        } else if (index == 1) {
            this.color1 = color;
        }
    }

}
