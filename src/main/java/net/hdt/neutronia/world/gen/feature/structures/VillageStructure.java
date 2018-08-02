//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.hdt.neutronia.world.gen.feature.structures;

import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class VillageStructure extends Structure<VillageConfig> {

    public VillageStructure() {
    }

    public String getStructureName() {
        return "Village";
    }

    public int func_202367_b() {
        return 8;
    }

    protected boolean isEnabled(IWorld p_isEnabled_1_) {
        return p_isEnabled_1_.getWorldInfo().isMapFeaturesEnabled();
    }

    protected ChunkPos func_211744_a(IChunkGenerator<?> p_211744_1_, Random p_211744_2_, int p_211744_3_, int p_211744_4_, int p_211744_5_, int p_211744_6_) {
        int lvt_7_1_ = p_211744_1_.getChunkGenSettings().getVillageDistance();
        int lvt_8_1_ = p_211744_1_.getChunkGenSettings().func_211729_b();
        int lvt_9_1_ = p_211744_3_ + lvt_7_1_ * p_211744_5_;
        int lvt_10_1_ = p_211744_4_ + lvt_7_1_ * p_211744_6_;
        int lvt_11_1_ = lvt_9_1_ < 0 ? lvt_9_1_ - lvt_7_1_ - 1 : lvt_9_1_;
        int lvt_12_1_ = lvt_10_1_ < 0 ? lvt_10_1_ - lvt_7_1_ - 1 : lvt_10_1_;
        int lvt_13_1_ = lvt_11_1_ / lvt_7_1_;
        int lvt_14_1_ = lvt_12_1_ / lvt_7_1_;
        ((SharedSeedRandom)p_211744_2_).func_202427_a(p_211744_1_.getSeed(), lvt_13_1_, lvt_14_1_, 10387312);
        lvt_13_1_ *= lvt_7_1_;
        lvt_14_1_ *= lvt_7_1_;
        lvt_13_1_ += p_211744_2_.nextInt(lvt_7_1_ - lvt_8_1_);
        lvt_14_1_ += p_211744_2_.nextInt(lvt_7_1_ - lvt_8_1_);
        return new ChunkPos(lvt_13_1_, lvt_14_1_);
    }

    protected boolean func_202372_a(IChunkGenerator<?> p_202372_1_, Random p_202372_2_, int p_202372_3_, int p_202372_4_) {
        ChunkPos lvt_5_1_ = this.func_211744_a(p_202372_1_, p_202372_2_, p_202372_3_, p_202372_4_, 0, 0);
        if (p_202372_3_ == lvt_5_1_.x && p_202372_4_ == lvt_5_1_.z) {
            Biome lvt_6_1_ = p_202372_1_.getBiomeProvider().getBiome(new BlockPos((p_202372_3_ << 4) + 9, 0, (p_202372_4_ << 4) + 9), Biomes.DEFAULT);
            return p_202372_1_.hasStructure(lvt_6_1_, Feature.VILLAGE);
        } else {
            return false;
        }
    }

    protected StructureStart func_202369_a(IWorld p_202369_1_, IChunkGenerator<?> p_202369_2_, SharedSeedRandom p_202369_3_, int p_202369_4_, int p_202369_5_) {
        Biome lvt_6_1_ = p_202369_2_.getBiomeProvider().getBiome(new BlockPos((p_202369_4_ << 4) + 9, 0, (p_202369_5_ << 4) + 9), Biomes.DEFAULT);
        return new VillageStructure.Start(p_202369_1_, p_202369_2_, p_202369_3_, p_202369_4_, p_202369_5_, lvt_6_1_);
    }

    public static class Start extends StructureStart {
        private boolean hasMoreThanTwoComponents;

        public Start() {
        }

        public Start(IWorld p_i48753_1_, IChunkGenerator<?> p_i48753_2_, SharedSeedRandom p_i48753_3_, int p_i48753_4_, int p_i48753_5_, Biome p_i48753_6_) {
            super(p_i48753_4_, p_i48753_5_, p_i48753_6_, p_i48753_3_, p_i48753_1_.getSeed());
            VillageConfig lvt_7_1_ = (VillageConfig)p_i48753_2_.getStructureConfig(p_i48753_6_, Feature.VILLAGE);
            List<VillagePieces.PieceWeight> lvt_8_1_ = VillagePieces.getStructureVillageWeightedPieceList(p_i48753_3_, Objects.requireNonNull(lvt_7_1_).field_202461_a);
            VillagePieces.Start lvt_9_1_ = new VillagePieces.Start(0, p_i48753_3_, (p_i48753_4_ << 4) + 2, (p_i48753_5_ << 4) + 2, lvt_8_1_, lvt_7_1_);
            this.components.add(lvt_9_1_);
            lvt_9_1_.buildComponent(lvt_9_1_, this.components, p_i48753_3_);
            List<StructurePiece> lvt_10_1_ = lvt_9_1_.pendingRoads;
            List lvt_11_1_ = lvt_9_1_.pendingHouses;

            int lvt_12_3_;
            while(!lvt_10_1_.isEmpty() || !lvt_11_1_.isEmpty()) {
                StructurePiece lvt_13_1_;
                if (lvt_10_1_.isEmpty()) {
                    lvt_12_3_ = p_i48753_3_.nextInt(lvt_11_1_.size());
                    lvt_13_1_ = (StructurePiece)lvt_11_1_.remove(lvt_12_3_);
                    lvt_13_1_.buildComponent(lvt_9_1_, this.components, p_i48753_3_);
                } else {
                    lvt_12_3_ = p_i48753_3_.nextInt(lvt_10_1_.size());
                    lvt_13_1_ = (StructurePiece)lvt_10_1_.remove(lvt_12_3_);
                    lvt_13_1_.buildComponent(lvt_9_1_, this.components, p_i48753_3_);
                }
            }

            this.func_202500_a(p_i48753_1_);
            lvt_12_3_ = 0;
            Iterator var15 = this.components.iterator();

            while(var15.hasNext()) {
                StructurePiece lvt_14_1_ = (StructurePiece)var15.next();
                if (!(lvt_14_1_ instanceof VillagePieces.Road)) {
                    ++lvt_12_3_;
                }
            }

            this.hasMoreThanTwoComponents = lvt_12_3_ > 2;
        }

        public boolean isSizeableStructure() {
            return this.hasMoreThanTwoComponents;
        }

        public void writeToNBT(NBTTagCompound p_writeToNBT_1_) {
            super.writeToNBT(p_writeToNBT_1_);
            p_writeToNBT_1_.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        public void readFromNBT(NBTTagCompound p_readFromNBT_1_) {
            super.readFromNBT(p_readFromNBT_1_);
            this.hasMoreThanTwoComponents = p_readFromNBT_1_.getBoolean("Valid");
        }
    }
}
