package net.hdt.neutronia.world.gen.feature.structures;

import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.StructureIO;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class VillagePieces {

    public static void registerVillagePieces() {
        StructureIO.registerStructureComponent(VillagePieces.House1.class, "ViBH");
        StructureIO.registerStructureComponent(VillagePieces.Field1.class, "ViDF");
        StructureIO.registerStructureComponent(VillagePieces.Field2.class, "ViF");
        StructureIO.registerStructureComponent(VillagePieces.Torch.class, "ViL");
        StructureIO.registerStructureComponent(VillagePieces.Hall.class, "ViPH");
        StructureIO.registerStructureComponent(VillagePieces.House4Garden.class, "ViSH");
        StructureIO.registerStructureComponent(VillagePieces.WoodHut.class, "ViSmH");
        StructureIO.registerStructureComponent(VillagePieces.Church.class, "ViST");
        StructureIO.registerStructureComponent(VillagePieces.House2.class, "ViS");
        StructureIO.registerStructureComponent(VillagePieces.Start.class, "ViStart");
        StructureIO.registerStructureComponent(VillagePieces.Path.class, "ViSR");
        StructureIO.registerStructureComponent(VillagePieces.House3.class, "ViTRH");
        StructureIO.registerStructureComponent(VillagePieces.Well.class, "ViW");
    }

    public static List<VillagePieces.PieceWeight> getStructureVillageWeightedPieceList(Random p_getStructureVillageWeightedPieceList_0_, int p_getStructureVillageWeightedPieceList_1_) {
        List<VillagePieces.PieceWeight> lvt_2_1_ = Lists.newArrayList();
        lvt_2_1_.add(new VillagePieces.PieceWeight(VillagePieces.House4Garden.class, 4, MathHelper.getInt(p_getStructureVillageWeightedPieceList_0_, 2 + p_getStructureVillageWeightedPieceList_1_, 4 + p_getStructureVillageWeightedPieceList_1_ * 2)));
        lvt_2_1_.add(new VillagePieces.PieceWeight(VillagePieces.Church.class, 20, MathHelper.getInt(p_getStructureVillageWeightedPieceList_0_, 0 + p_getStructureVillageWeightedPieceList_1_, 1 + p_getStructureVillageWeightedPieceList_1_)));
        lvt_2_1_.add(new VillagePieces.PieceWeight(VillagePieces.House1.class, 20, MathHelper.getInt(p_getStructureVillageWeightedPieceList_0_, 0 + p_getStructureVillageWeightedPieceList_1_, 2 + p_getStructureVillageWeightedPieceList_1_)));
        lvt_2_1_.add(new VillagePieces.PieceWeight(VillagePieces.WoodHut.class, 3, MathHelper.getInt(p_getStructureVillageWeightedPieceList_0_, 2 + p_getStructureVillageWeightedPieceList_1_, 5 + p_getStructureVillageWeightedPieceList_1_ * 3)));
        lvt_2_1_.add(new VillagePieces.PieceWeight(VillagePieces.Hall.class, 15, MathHelper.getInt(p_getStructureVillageWeightedPieceList_0_, 0 + p_getStructureVillageWeightedPieceList_1_, 2 + p_getStructureVillageWeightedPieceList_1_)));
        lvt_2_1_.add(new VillagePieces.PieceWeight(VillagePieces.Field1.class, 3, MathHelper.getInt(p_getStructureVillageWeightedPieceList_0_, 1 + p_getStructureVillageWeightedPieceList_1_, 4 + p_getStructureVillageWeightedPieceList_1_)));
        lvt_2_1_.add(new VillagePieces.PieceWeight(VillagePieces.Field2.class, 3, MathHelper.getInt(p_getStructureVillageWeightedPieceList_0_, 2 + p_getStructureVillageWeightedPieceList_1_, 4 + p_getStructureVillageWeightedPieceList_1_ * 2)));
        lvt_2_1_.add(new VillagePieces.PieceWeight(VillagePieces.House2.class, 15, MathHelper.getInt(p_getStructureVillageWeightedPieceList_0_, 0, 1 + p_getStructureVillageWeightedPieceList_1_)));
        lvt_2_1_.add(new VillagePieces.PieceWeight(VillagePieces.House3.class, 8, MathHelper.getInt(p_getStructureVillageWeightedPieceList_0_, 0 + p_getStructureVillageWeightedPieceList_1_, 3 + p_getStructureVillageWeightedPieceList_1_ * 2)));

        lvt_2_1_.removeIf(o -> ((PieceWeight) o).villagePiecesLimit == 0);

        return lvt_2_1_;
    }

    private static int updatePieceWeight(List<VillagePieces.PieceWeight> p_updatePieceWeight_0_) {
        boolean lvt_1_1_ = false;
        int lvt_2_1_ = 0;

        VillagePieces.PieceWeight lvt_4_1_;
        for(Iterator var3 = p_updatePieceWeight_0_.iterator(); var3.hasNext(); lvt_2_1_ += lvt_4_1_.villagePieceWeight) {
            lvt_4_1_ = (VillagePieces.PieceWeight)var3.next();
            if (lvt_4_1_.villagePiecesLimit > 0 && lvt_4_1_.villagePiecesSpawned < lvt_4_1_.villagePiecesLimit) {
                lvt_1_1_ = true;
            }
        }

        return lvt_1_1_ ? lvt_2_1_ : -1;
    }

    private static VillagePieces.Village findAndCreateComponentFactory(VillagePieces.Start p_findAndCreateComponentFactory_0_, VillagePieces.PieceWeight p_findAndCreateComponentFactory_1_, List<StructurePiece> p_findAndCreateComponentFactory_2_, Random p_findAndCreateComponentFactory_3_, int p_findAndCreateComponentFactory_4_, int p_findAndCreateComponentFactory_5_, int p_findAndCreateComponentFactory_6_, EnumFacing p_findAndCreateComponentFactory_7_, int p_findAndCreateComponentFactory_8_) {
        Class<? extends VillagePieces.Village> lvt_9_1_ = p_findAndCreateComponentFactory_1_.villagePieceClass;
        VillagePieces.Village lvt_10_1_ = null;
        if (lvt_9_1_ == VillagePieces.House4Garden.class) {
            lvt_10_1_ = VillagePieces.House4Garden.createPiece(p_findAndCreateComponentFactory_0_, p_findAndCreateComponentFactory_2_, p_findAndCreateComponentFactory_3_, p_findAndCreateComponentFactory_4_, p_findAndCreateComponentFactory_5_, p_findAndCreateComponentFactory_6_, p_findAndCreateComponentFactory_7_, p_findAndCreateComponentFactory_8_);
        } else if (lvt_9_1_ == VillagePieces.Church.class) {
            lvt_10_1_ = VillagePieces.Church.createPiece(p_findAndCreateComponentFactory_0_, p_findAndCreateComponentFactory_2_, p_findAndCreateComponentFactory_3_, p_findAndCreateComponentFactory_4_, p_findAndCreateComponentFactory_5_, p_findAndCreateComponentFactory_6_, p_findAndCreateComponentFactory_7_, p_findAndCreateComponentFactory_8_);
        } else if (lvt_9_1_ == VillagePieces.House1.class) {
            lvt_10_1_ = VillagePieces.House1.createPiece(p_findAndCreateComponentFactory_0_, p_findAndCreateComponentFactory_2_, p_findAndCreateComponentFactory_3_, p_findAndCreateComponentFactory_4_, p_findAndCreateComponentFactory_5_, p_findAndCreateComponentFactory_6_, p_findAndCreateComponentFactory_7_, p_findAndCreateComponentFactory_8_);
        } else if (lvt_9_1_ == VillagePieces.WoodHut.class) {
            lvt_10_1_ = VillagePieces.WoodHut.createPiece(p_findAndCreateComponentFactory_0_, p_findAndCreateComponentFactory_2_, p_findAndCreateComponentFactory_3_, p_findAndCreateComponentFactory_4_, p_findAndCreateComponentFactory_5_, p_findAndCreateComponentFactory_6_, p_findAndCreateComponentFactory_7_, p_findAndCreateComponentFactory_8_);
        } else if (lvt_9_1_ == VillagePieces.Hall.class) {
            lvt_10_1_ = VillagePieces.Hall.createPiece(p_findAndCreateComponentFactory_0_, p_findAndCreateComponentFactory_2_, p_findAndCreateComponentFactory_3_, p_findAndCreateComponentFactory_4_, p_findAndCreateComponentFactory_5_, p_findAndCreateComponentFactory_6_, p_findAndCreateComponentFactory_7_, p_findAndCreateComponentFactory_8_);
        } else if (lvt_9_1_ == VillagePieces.Field1.class) {
            lvt_10_1_ = VillagePieces.Field1.createPiece(p_findAndCreateComponentFactory_0_, p_findAndCreateComponentFactory_2_, p_findAndCreateComponentFactory_3_, p_findAndCreateComponentFactory_4_, p_findAndCreateComponentFactory_5_, p_findAndCreateComponentFactory_6_, p_findAndCreateComponentFactory_7_, p_findAndCreateComponentFactory_8_);
        } else if (lvt_9_1_ == VillagePieces.Field2.class) {
            lvt_10_1_ = VillagePieces.Field2.createPiece(p_findAndCreateComponentFactory_0_, p_findAndCreateComponentFactory_2_, p_findAndCreateComponentFactory_3_, p_findAndCreateComponentFactory_4_, p_findAndCreateComponentFactory_5_, p_findAndCreateComponentFactory_6_, p_findAndCreateComponentFactory_7_, p_findAndCreateComponentFactory_8_);
        } else if (lvt_9_1_ == VillagePieces.House2.class) {
            lvt_10_1_ = VillagePieces.House2.createPiece(p_findAndCreateComponentFactory_0_, p_findAndCreateComponentFactory_2_, p_findAndCreateComponentFactory_3_, p_findAndCreateComponentFactory_4_, p_findAndCreateComponentFactory_5_, p_findAndCreateComponentFactory_6_, p_findAndCreateComponentFactory_7_, p_findAndCreateComponentFactory_8_);
        } else if (lvt_9_1_ == VillagePieces.House3.class) {
            lvt_10_1_ = VillagePieces.House3.createPiece(p_findAndCreateComponentFactory_0_, p_findAndCreateComponentFactory_2_, p_findAndCreateComponentFactory_3_, p_findAndCreateComponentFactory_4_, p_findAndCreateComponentFactory_5_, p_findAndCreateComponentFactory_6_, p_findAndCreateComponentFactory_7_, p_findAndCreateComponentFactory_8_);
        }

        return lvt_10_1_;
    }

    private static VillagePieces.Village generateComponent(VillagePieces.Start p_generateComponent_0_, List<StructurePiece> p_generateComponent_1_, Random p_generateComponent_2_, int p_generateComponent_3_, int p_generateComponent_4_, int p_generateComponent_5_, EnumFacing p_generateComponent_6_, int p_generateComponent_7_) {
        int lvt_8_1_ = updatePieceWeight(p_generateComponent_0_.structureVillageWeightedPieceList);
        if (lvt_8_1_ <= 0) {
            return null;
        } else {
            int lvt_9_1_ = 0;

            while(lvt_9_1_ < 5) {
                ++lvt_9_1_;
                int lvt_10_1_ = p_generateComponent_2_.nextInt(lvt_8_1_);
                Iterator var11 = p_generateComponent_0_.structureVillageWeightedPieceList.iterator();

                while(var11.hasNext()) {
                    VillagePieces.PieceWeight lvt_12_1_ = (VillagePieces.PieceWeight)var11.next();
                    lvt_10_1_ -= lvt_12_1_.villagePieceWeight;
                    if (lvt_10_1_ < 0) {
                        if (!lvt_12_1_.canSpawnMoreVillagePiecesOfType(p_generateComponent_7_) || lvt_12_1_ == p_generateComponent_0_.lastPlaced && p_generateComponent_0_.structureVillageWeightedPieceList.size() > 1) {
                            break;
                        }

                        VillagePieces.Village lvt_13_1_ = findAndCreateComponentFactory(p_generateComponent_0_, lvt_12_1_, p_generateComponent_1_, p_generateComponent_2_, p_generateComponent_3_, p_generateComponent_4_, p_generateComponent_5_, p_generateComponent_6_, p_generateComponent_7_);
                        if (lvt_13_1_ != null) {
                            ++lvt_12_1_.villagePiecesSpawned;
                            p_generateComponent_0_.lastPlaced = lvt_12_1_;
                            if (!lvt_12_1_.canSpawnMoreVillagePieces()) {
                                p_generateComponent_0_.structureVillageWeightedPieceList.remove(lvt_12_1_);
                            }

                            return lvt_13_1_;
                        }
                    }
                }
            }

            MutableBoundingBox lvt_10_2_ = VillagePieces.Torch.findPieceBox(p_generateComponent_0_, p_generateComponent_1_, p_generateComponent_2_, p_generateComponent_3_, p_generateComponent_4_, p_generateComponent_5_, p_generateComponent_6_);
            if (lvt_10_2_ != null) {
                return new VillagePieces.Torch(p_generateComponent_0_, p_generateComponent_7_, p_generateComponent_2_, lvt_10_2_, p_generateComponent_6_);
            } else {
                return null;
            }
        }
    }

    private static StructurePiece generateAndAddComponent(VillagePieces.Start p_generateAndAddComponent_0_, List<StructurePiece> p_generateAndAddComponent_1_, Random p_generateAndAddComponent_2_, int p_generateAndAddComponent_3_, int p_generateAndAddComponent_4_, int p_generateAndAddComponent_5_, EnumFacing p_generateAndAddComponent_6_, int p_generateAndAddComponent_7_) {
        if (p_generateAndAddComponent_7_ > 50) {
            return null;
        } else if (Math.abs(p_generateAndAddComponent_3_ - p_generateAndAddComponent_0_.getBoundingBox().minX) <= 112 && Math.abs(p_generateAndAddComponent_5_ - p_generateAndAddComponent_0_.getBoundingBox().minZ) <= 112) {
            StructurePiece lvt_8_1_ = generateComponent(p_generateAndAddComponent_0_, p_generateAndAddComponent_1_, p_generateAndAddComponent_2_, p_generateAndAddComponent_3_, p_generateAndAddComponent_4_, p_generateAndAddComponent_5_, p_generateAndAddComponent_6_, p_generateAndAddComponent_7_ + 1);
            if (lvt_8_1_ != null) {
                p_generateAndAddComponent_1_.add(lvt_8_1_);
                p_generateAndAddComponent_0_.pendingHouses.add(lvt_8_1_);
                return lvt_8_1_;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static StructurePiece generateAndAddRoadPiece(VillagePieces.Start p_generateAndAddRoadPiece_0_, List<StructurePiece> p_generateAndAddRoadPiece_1_, Random p_generateAndAddRoadPiece_2_, int p_generateAndAddRoadPiece_3_, int p_generateAndAddRoadPiece_4_, int p_generateAndAddRoadPiece_5_, EnumFacing p_generateAndAddRoadPiece_6_, int p_generateAndAddRoadPiece_7_) {
        if (p_generateAndAddRoadPiece_7_ > 3 + p_generateAndAddRoadPiece_0_.terrainType) {
            return null;
        } else if (Math.abs(p_generateAndAddRoadPiece_3_ - p_generateAndAddRoadPiece_0_.getBoundingBox().minX) <= 112 && Math.abs(p_generateAndAddRoadPiece_5_ - p_generateAndAddRoadPiece_0_.getBoundingBox().minZ) <= 112) {
            MutableBoundingBox lvt_8_1_ = VillagePieces.Path.findPieceBox(p_generateAndAddRoadPiece_0_, p_generateAndAddRoadPiece_1_, p_generateAndAddRoadPiece_2_, p_generateAndAddRoadPiece_3_, p_generateAndAddRoadPiece_4_, p_generateAndAddRoadPiece_5_, p_generateAndAddRoadPiece_6_);
            if (lvt_8_1_ != null && lvt_8_1_.minY > 10) {
                StructurePiece lvt_9_1_ = new VillagePieces.Path(p_generateAndAddRoadPiece_0_, p_generateAndAddRoadPiece_7_, p_generateAndAddRoadPiece_2_, lvt_8_1_, p_generateAndAddRoadPiece_6_);
                p_generateAndAddRoadPiece_1_.add(lvt_9_1_);
                p_generateAndAddRoadPiece_0_.pendingRoads.add(lvt_9_1_);
                return lvt_9_1_;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static class Torch extends VillagePieces.Village {
        public Torch() {
        }

        public Torch(VillagePieces.Start p_i45568_1_, int p_i45568_2_, Random p_i45568_3_, MutableBoundingBox p_i45568_4_, EnumFacing p_i45568_5_) {
            super(p_i45568_1_, p_i45568_2_);
            this.setCoordBaseMode(p_i45568_5_);
            this.boundingBox = p_i45568_4_;
        }

        public static MutableBoundingBox findPieceBox(VillagePieces.Start p_findPieceBox_0_, List<StructurePiece> p_findPieceBox_1_, Random p_findPieceBox_2_, int p_findPieceBox_3_, int p_findPieceBox_4_, int p_findPieceBox_5_, EnumFacing p_findPieceBox_6_) {
            MutableBoundingBox lvt_7_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_findPieceBox_3_, p_findPieceBox_4_, p_findPieceBox_5_, 0, 0, 0, 3, 4, 2, p_findPieceBox_6_);
            return StructurePiece.findIntersecting(p_findPieceBox_1_, lvt_7_1_) != null ? null : lvt_7_1_;
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
            }

            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 0, 2, 3, 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 1, 0, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 1, 1, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 1, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.BLACK_WOOL.getDefaultState(), 1, 3, 0, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.EAST, 2, 3, 0, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.NORTH, 1, 3, 1, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.WEST, 0, 3, 0, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.SOUTH, 1, 3, -1, p_addComponentParts_3_);
            return true;
        }
    }

    public static class Field1 extends VillagePieces.Village {
        private IBlockState cropTypeA;
        private IBlockState cropTypeB;
        private IBlockState cropTypeC;
        private IBlockState cropTypeD;

        public Field1() {
        }

        public Field1(VillagePieces.Start p_i45570_1_, int p_i45570_2_, Random p_i45570_3_, MutableBoundingBox p_i45570_4_, EnumFacing p_i45570_5_) {
            super(p_i45570_1_, p_i45570_2_);
            this.setCoordBaseMode(p_i45570_5_);
            this.boundingBox = p_i45570_4_;
            this.cropTypeA = VillagePieces.Field2.func_197529_b(p_i45570_3_);
            this.cropTypeB = VillagePieces.Field2.func_197529_b(p_i45570_3_);
            this.cropTypeC = VillagePieces.Field2.func_197529_b(p_i45570_3_);
            this.cropTypeD = VillagePieces.Field2.func_197529_b(p_i45570_3_);
        }

        protected void writeStructureToNBT(NBTTagCompound p_writeStructureToNBT_1_) {
            super.writeStructureToNBT(p_writeStructureToNBT_1_);
            p_writeStructureToNBT_1_.setTag("CA", NBTUtil.writeBlockState(this.cropTypeA));
            p_writeStructureToNBT_1_.setTag("CB", NBTUtil.writeBlockState(this.cropTypeB));
            p_writeStructureToNBT_1_.setTag("CC", NBTUtil.writeBlockState(this.cropTypeC));
            p_writeStructureToNBT_1_.setTag("CD", NBTUtil.writeBlockState(this.cropTypeD));
        }

        protected void readStructureFromNBT(NBTTagCompound p_readStructureFromNBT_1_, TemplateManager p_readStructureFromNBT_2_) {
            super.readStructureFromNBT(p_readStructureFromNBT_1_, p_readStructureFromNBT_2_);
            this.cropTypeA = NBTUtil.readBlockState(p_readStructureFromNBT_1_.getCompoundTag("CA"));
            this.cropTypeB = NBTUtil.readBlockState(p_readStructureFromNBT_1_.getCompoundTag("CB"));
            this.cropTypeC = NBTUtil.readBlockState(p_readStructureFromNBT_1_.getCompoundTag("CC"));
            this.cropTypeD = NBTUtil.readBlockState(p_readStructureFromNBT_1_.getCompoundTag("CD"));
            if (!(this.cropTypeA.getBlock() instanceof BlockCrops)) {
                this.cropTypeA = Blocks.WHEAT.getDefaultState();
            }

            if (!(this.cropTypeB.getBlock() instanceof BlockCrops)) {
                this.cropTypeB = Blocks.CARROTS.getDefaultState();
            }

            if (!(this.cropTypeC.getBlock() instanceof BlockCrops)) {
                this.cropTypeC = Blocks.POTATOES.getDefaultState();
            }

            if (!(this.cropTypeD.getBlock() instanceof BlockCrops)) {
                this.cropTypeD = Blocks.BEETROOTS.getDefaultState();
            }

        }

        public static VillagePieces.Field1 createPiece(VillagePieces.Start p_createPiece_0_, List<StructurePiece> p_createPiece_1_, Random p_createPiece_2_, int p_createPiece_3_, int p_createPiece_4_, int p_createPiece_5_, EnumFacing p_createPiece_6_, int p_createPiece_7_) {
            MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_createPiece_3_, p_createPiece_4_, p_createPiece_5_, 0, 0, 0, 13, 4, 9, p_createPiece_6_);
            return canVillageGoDeeper(lvt_8_1_) && StructurePiece.findIntersecting(p_createPiece_1_, lvt_8_1_) == null ? new VillagePieces.Field1(p_createPiece_0_, p_createPiece_7_, p_createPiece_2_, lvt_8_1_, p_createPiece_6_) : null;
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
            }

            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_LOG.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 0, 12, 4, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 1, 2, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 4, 0, 1, 5, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 7, 0, 1, 8, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 10, 0, 1, 11, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 0, 0, 0, 8, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 6, 0, 0, 6, 0, 8, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 12, 0, 0, 12, 0, 8, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 0, 11, 0, 0, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 8, 11, 0, 8, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 0, 1, 3, 0, 7, Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 9, 0, 1, 9, 0, 7, Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), false);

            int lvt_6_2_;
            for(lvt_6_2_ = 1; lvt_6_2_ <= 7; ++lvt_6_2_) {
                BlockCrops lvt_7_1_ = (BlockCrops)this.cropTypeA.getBlock();
                int lvt_8_1_ = lvt_7_1_.getMaxAge();
                int lvt_9_1_ = lvt_8_1_ / 3;
                this.setBlockState(p_addComponentParts_1_, this.cropTypeA.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_9_1_, lvt_8_1_)), 1, 1, lvt_6_2_, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, this.cropTypeA.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_9_1_, lvt_8_1_)), 2, 1, lvt_6_2_, p_addComponentParts_3_);
                lvt_7_1_ = (BlockCrops)this.cropTypeB.getBlock();
                int lvt_10_1_ = lvt_7_1_.getMaxAge();
                int lvt_11_1_ = lvt_10_1_ / 3;
                this.setBlockState(p_addComponentParts_1_, this.cropTypeB.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_11_1_, lvt_10_1_)), 4, 1, lvt_6_2_, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, this.cropTypeB.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_11_1_, lvt_10_1_)), 5, 1, lvt_6_2_, p_addComponentParts_3_);
                lvt_7_1_ = (BlockCrops)this.cropTypeC.getBlock();
                int lvt_12_1_ = lvt_7_1_.getMaxAge();
                int lvt_13_1_ = lvt_12_1_ / 3;
                this.setBlockState(p_addComponentParts_1_, this.cropTypeC.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_13_1_, lvt_12_1_)), 7, 1, lvt_6_2_, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, this.cropTypeC.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_13_1_, lvt_12_1_)), 8, 1, lvt_6_2_, p_addComponentParts_3_);
                lvt_7_1_ = (BlockCrops)this.cropTypeD.getBlock();
                int lvt_14_1_ = lvt_7_1_.getMaxAge();
                int lvt_15_1_ = lvt_14_1_ / 3;
                this.setBlockState(p_addComponentParts_1_, this.cropTypeD.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_15_1_, lvt_14_1_)), 10, 1, lvt_6_2_, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, this.cropTypeD.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_15_1_, lvt_14_1_)), 11, 1, lvt_6_2_, p_addComponentParts_3_);
            }

            for(lvt_6_2_ = 0; lvt_6_2_ < 9; ++lvt_6_2_) {
                for(int lvt_7_2_ = 0; lvt_7_2_ < 13; ++lvt_7_2_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_7_2_, 4, lvt_6_2_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, Blocks.DIRT.getDefaultState(), lvt_7_2_, -1, lvt_6_2_, p_addComponentParts_3_);
                }
            }

            return true;
        }
    }

    public static class Field2 extends VillagePieces.Village {
        private IBlockState cropTypeA;
        private IBlockState cropTypeB;

        public Field2() {
        }

        public Field2(VillagePieces.Start p_i45569_1_, int p_i45569_2_, Random p_i45569_3_, MutableBoundingBox p_i45569_4_, EnumFacing p_i45569_5_) {
            super(p_i45569_1_, p_i45569_2_);
            this.setCoordBaseMode(p_i45569_5_);
            this.boundingBox = p_i45569_4_;
            this.cropTypeA = func_197529_b(p_i45569_3_);
            this.cropTypeB = func_197529_b(p_i45569_3_);
        }

        protected void writeStructureToNBT(NBTTagCompound p_writeStructureToNBT_1_) {
            super.writeStructureToNBT(p_writeStructureToNBT_1_);
            p_writeStructureToNBT_1_.setTag("CA", NBTUtil.writeBlockState(this.cropTypeA));
            p_writeStructureToNBT_1_.setTag("CB", NBTUtil.writeBlockState(this.cropTypeB));
        }

        protected void readStructureFromNBT(NBTTagCompound p_readStructureFromNBT_1_, TemplateManager p_readStructureFromNBT_2_) {
            super.readStructureFromNBT(p_readStructureFromNBT_1_, p_readStructureFromNBT_2_);
            this.cropTypeA = NBTUtil.readBlockState(p_readStructureFromNBT_1_.getCompoundTag("CA"));
            this.cropTypeB = NBTUtil.readBlockState(p_readStructureFromNBT_1_.getCompoundTag("CB"));
        }

        private static IBlockState func_197529_b(Random p_197529_0_) {
            switch(p_197529_0_.nextInt(10)) {
            case 0:
            case 1:
                return Blocks.CARROTS.getDefaultState();
            case 2:
            case 3:
                return Blocks.POTATOES.getDefaultState();
            case 4:
                return Blocks.BEETROOTS.getDefaultState();
            default:
                return Blocks.WHEAT.getDefaultState();
            }
        }

        public static VillagePieces.Field2 createPiece(VillagePieces.Start p_createPiece_0_, List<StructurePiece> p_createPiece_1_, Random p_createPiece_2_, int p_createPiece_3_, int p_createPiece_4_, int p_createPiece_5_, EnumFacing p_createPiece_6_, int p_createPiece_7_) {
            MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_createPiece_3_, p_createPiece_4_, p_createPiece_5_, 0, 0, 0, 7, 4, 9, p_createPiece_6_);
            return canVillageGoDeeper(lvt_8_1_) && StructurePiece.findIntersecting(p_createPiece_1_, lvt_8_1_) == null ? new VillagePieces.Field2(p_createPiece_0_, p_createPiece_7_, p_createPiece_2_, lvt_8_1_, p_createPiece_6_) : null;
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
            }

            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_LOG.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 0, 6, 4, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 1, 2, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 4, 0, 1, 5, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 0, 0, 0, 8, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 6, 0, 0, 6, 0, 8, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 0, 5, 0, 0, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 8, 5, 0, 8, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 0, 1, 3, 0, 7, Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), false);

            int lvt_6_2_;
            for(lvt_6_2_ = 1; lvt_6_2_ <= 7; ++lvt_6_2_) {
                BlockCrops lvt_7_1_ = (BlockCrops)this.cropTypeA.getBlock();
                int lvt_8_1_ = lvt_7_1_.getMaxAge();
                int lvt_9_1_ = lvt_8_1_ / 3;
                this.setBlockState(p_addComponentParts_1_, this.cropTypeA.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_9_1_, lvt_8_1_)), 1, 1, lvt_6_2_, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, this.cropTypeA.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_9_1_, lvt_8_1_)), 2, 1, lvt_6_2_, p_addComponentParts_3_);
                lvt_7_1_ = (BlockCrops)this.cropTypeB.getBlock();
                int lvt_10_1_ = lvt_7_1_.getMaxAge();
                int lvt_11_1_ = lvt_10_1_ / 3;
                this.setBlockState(p_addComponentParts_1_, this.cropTypeB.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_11_1_, lvt_10_1_)), 4, 1, lvt_6_2_, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, this.cropTypeB.withProperty(lvt_7_1_.getAgeProperty(), MathHelper.getInt(p_addComponentParts_2_, lvt_11_1_, lvt_10_1_)), 5, 1, lvt_6_2_, p_addComponentParts_3_);
            }

            for(lvt_6_2_ = 0; lvt_6_2_ < 9; ++lvt_6_2_) {
                for(int lvt_7_2_ = 0; lvt_7_2_ < 7; ++lvt_7_2_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_7_2_, 4, lvt_6_2_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, Blocks.DIRT.getDefaultState(), lvt_7_2_, -1, lvt_6_2_, p_addComponentParts_3_);
                }
            }

            return true;
        }
    }

    public static class House2 extends VillagePieces.Village {
        private boolean hasMadeChest;

        public House2() {
        }

        public House2(VillagePieces.Start p_i45563_1_, int p_i45563_2_, Random p_i45563_3_, MutableBoundingBox p_i45563_4_, EnumFacing p_i45563_5_) {
            super(p_i45563_1_, p_i45563_2_);
            this.setCoordBaseMode(p_i45563_5_);
            this.boundingBox = p_i45563_4_;
        }

        public static VillagePieces.House2 createPiece(VillagePieces.Start p_createPiece_0_, List<StructurePiece> p_createPiece_1_, Random p_createPiece_2_, int p_createPiece_3_, int p_createPiece_4_, int p_createPiece_5_, EnumFacing p_createPiece_6_, int p_createPiece_7_) {
            MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_createPiece_3_, p_createPiece_4_, p_createPiece_5_, 0, 0, 0, 10, 6, 7, p_createPiece_6_);
            return canVillageGoDeeper(lvt_8_1_) && StructurePiece.findIntersecting(p_createPiece_1_, lvt_8_1_) == null ? new VillagePieces.House2(p_createPiece_0_, p_createPiece_7_, p_createPiece_2_, lvt_8_1_, p_createPiece_6_) : null;
        }

        protected void writeStructureToNBT(NBTTagCompound p_writeStructureToNBT_1_) {
            super.writeStructureToNBT(p_writeStructureToNBT_1_);
            p_writeStructureToNBT_1_.setBoolean("Chest", this.hasMadeChest);
        }

        protected void readStructureFromNBT(NBTTagCompound p_readStructureFromNBT_1_, TemplateManager p_readStructureFromNBT_2_) {
            super.readStructureFromNBT(p_readStructureFromNBT_1_, p_readStructureFromNBT_2_);
            this.hasMadeChest = p_readStructureFromNBT_1_.getBoolean("Chest");
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
            }

            IBlockState lvt_5_1_ = Blocks.COBBLESTONE.getDefaultState();
            IBlockState lvt_6_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState lvt_7_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
            IBlockState lvt_8_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_PLANKS.getDefaultState());
            IBlockState lvt_9_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState lvt_10_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_LOG.getDefaultState());
            IBlockState lvt_11_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 0, 9, 4, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 0, 9, 0, 6, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 4, 0, 9, 4, 6, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 5, 0, 9, 5, 6, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 5, 1, 8, 5, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 0, 2, 3, 0, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 0, 0, 4, 0, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 1, 0, 3, 4, 0, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 6, 0, 4, 6, lvt_10_1_, lvt_10_1_, false);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 3, 3, 1, p_addComponentParts_3_);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 1, 2, 3, 3, 2, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 4, 1, 3, 5, 3, 3, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 1, 0, 3, 5, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 6, 5, 3, 6, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 5, 1, 0, 5, 3, 0, lvt_11_1_, lvt_11_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 9, 1, 0, 9, 3, 0, lvt_11_1_, lvt_11_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 6, 1, 4, 9, 4, 6, lvt_5_1_, lvt_5_1_, false);
            this.setBlockState(p_addComponentParts_1_, Blocks.LAVA.getDefaultState(), 7, 1, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.LAVA.getDefaultState(), 8, 1, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.IRON_BARS.getDefaultState().withProperty(BlockIronBars.field_196409_a, true).withProperty(BlockIronBars.field_196413_c, true), 9, 2, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.IRON_BARS.getDefaultState().withProperty(BlockIronBars.field_196409_a, true), 9, 2, 4, p_addComponentParts_3_);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 7, 2, 4, 8, 2, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 6, 1, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.FURNACE.getDefaultState().withProperty(BlockFurnace.FACING, EnumFacing.SOUTH), 6, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.FURNACE.getDefaultState().withProperty(BlockFurnace.FACING, EnumFacing.SOUTH), 6, 3, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.field_196505_a, SlabType.DOUBLE), 8, 1, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 2, 2, 6, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 4, 2, 6, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 2, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.OAK_PRESSURE_PLATE.getDefaultState(), 2, 2, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 1, 1, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 2, 1, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_7_1_, 1, 1, 4, p_addComponentParts_3_);
            if (!this.hasMadeChest && p_addComponentParts_3_.isVecInside(new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5)))) {
                this.hasMadeChest = true;
                this.generateChest(p_addComponentParts_1_, p_addComponentParts_3_, p_addComponentParts_2_, 5, 1, 5, LootTableList.CHESTS_VILLAGE_BLACKSMITH);
            }

            int lvt_12_2_;
            for(lvt_12_2_ = 6; lvt_12_2_ <= 8; ++lvt_12_2_) {
                if (this.getBlockStateFromPos(p_addComponentParts_1_, lvt_12_2_, 0, -1, p_addComponentParts_3_).isAir() && !this.getBlockStateFromPos(p_addComponentParts_1_, lvt_12_2_, -1, -1, p_addComponentParts_3_).isAir()) {
                    this.setBlockState(p_addComponentParts_1_, lvt_9_1_, lvt_12_2_, 0, -1, p_addComponentParts_3_);
                    if (this.getBlockStateFromPos(p_addComponentParts_1_, lvt_12_2_, -1, -1, p_addComponentParts_3_).getBlock() == Blocks.GRASS_PATH) {
                        this.setBlockState(p_addComponentParts_1_, Blocks.GRASS_BLOCK.getDefaultState(), lvt_12_2_, -1, -1, p_addComponentParts_3_);
                    }
                }
            }

            for(lvt_12_2_ = 0; lvt_12_2_ < 7; ++lvt_12_2_) {
                for(int lvt_13_1_ = 0; lvt_13_1_ < 10; ++lvt_13_1_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_13_1_, 6, lvt_12_2_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, lvt_5_1_, lvt_13_1_, -1, lvt_12_2_, p_addComponentParts_3_);
                }
            }

            this.spawnVillagers(p_addComponentParts_1_, p_addComponentParts_3_, 7, 1, 1, 1);
            return true;
        }

        protected int chooseProfession(int p_chooseProfession_1_, int p_chooseProfession_2_) {
            return 3;
        }
    }

    public static class House3 extends VillagePieces.Village {
        public House3() {
        }

        public House3(VillagePieces.Start p_i45561_1_, int p_i45561_2_, Random p_i45561_3_, MutableBoundingBox p_i45561_4_, EnumFacing p_i45561_5_) {
            super(p_i45561_1_, p_i45561_2_);
            this.setCoordBaseMode(p_i45561_5_);
            this.boundingBox = p_i45561_4_;
        }

        public static VillagePieces.House3 createPiece(VillagePieces.Start p_createPiece_0_, List<StructurePiece> p_createPiece_1_, Random p_createPiece_2_, int p_createPiece_3_, int p_createPiece_4_, int p_createPiece_5_, EnumFacing p_createPiece_6_, int p_createPiece_7_) {
            MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_createPiece_3_, p_createPiece_4_, p_createPiece_5_, 0, 0, 0, 9, 7, 12, p_createPiece_6_);
            return canVillageGoDeeper(lvt_8_1_) && StructurePiece.findIntersecting(p_createPiece_1_, lvt_8_1_) == null ? new VillagePieces.House3(p_createPiece_0_, p_createPiece_7_, p_createPiece_2_, lvt_8_1_, p_createPiece_6_) : null;
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
            }

            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState lvt_6_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState lvt_7_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
            IBlockState lvt_8_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
            IBlockState lvt_9_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
            IBlockState lvt_10_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_PLANKS.getDefaultState());
            IBlockState lvt_11_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_LOG.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 2, 0, 5, 8, 0, 10, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 1, 7, 0, 4, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 0, 0, 3, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 8, 0, 0, 8, 3, 10, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 0, 7, 2, 0, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 5, 2, 1, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 2, 0, 6, 2, 3, 10, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 0, 10, 7, 3, 10, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 2, 0, 7, 3, 0, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 2, 5, 2, 3, 5, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 4, 1, 8, 4, 1, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 4, 4, 3, 4, 4, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 5, 2, 8, 5, 3, lvt_10_1_, lvt_10_1_, false);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 0, 4, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 0, 4, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 8, 4, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 8, 4, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 8, 4, 4, p_addComponentParts_3_);
            IBlockState lvt_12_1_ = lvt_6_1_;
            IBlockState lvt_13_1_ = lvt_7_1_;
            IBlockState lvt_14_1_ = lvt_9_1_;
            IBlockState lvt_15_1_ = lvt_8_1_;

            int lvt_16_5_;
            int lvt_17_5_;
            for(lvt_16_5_ = -1; lvt_16_5_ <= 2; ++lvt_16_5_) {
                for(lvt_17_5_ = 0; lvt_17_5_ <= 8; ++lvt_17_5_) {
                    this.setBlockState(p_addComponentParts_1_, lvt_12_1_, lvt_17_5_, 4 + lvt_16_5_, lvt_16_5_, p_addComponentParts_3_);
                    if ((lvt_16_5_ > -1 || lvt_17_5_ <= 1) && (lvt_16_5_ > 0 || lvt_17_5_ <= 3) && (lvt_16_5_ > 1 || lvt_17_5_ <= 4 || lvt_17_5_ >= 6)) {
                        this.setBlockState(p_addComponentParts_1_, lvt_13_1_, lvt_17_5_, 4 + lvt_16_5_, 5 - lvt_16_5_, p_addComponentParts_3_);
                    }
                }
            }

            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 4, 5, 3, 4, 10, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 7, 4, 2, 7, 4, 10, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 4, 5, 4, 4, 5, 10, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 6, 5, 4, 6, 5, 10, lvt_10_1_, lvt_10_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 5, 6, 3, 5, 6, 10, lvt_10_1_, lvt_10_1_, false);

            for(lvt_16_5_ = 4; lvt_16_5_ >= 1; --lvt_16_5_) {
                this.setBlockState(p_addComponentParts_1_, lvt_10_1_, lvt_16_5_, 2 + lvt_16_5_, 7 - lvt_16_5_, p_addComponentParts_3_);

                for(lvt_17_5_ = 8 - lvt_16_5_; lvt_17_5_ <= 10; ++lvt_17_5_) {
                    this.setBlockState(p_addComponentParts_1_, lvt_15_1_, lvt_16_5_, 2 + lvt_16_5_, lvt_17_5_, p_addComponentParts_3_);
                }
            }

            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 6, 6, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 7, 5, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_9_1_, 6, 6, 4, p_addComponentParts_3_);

            for(lvt_16_5_ = 6; lvt_16_5_ <= 8; ++lvt_16_5_) {
                for(lvt_17_5_ = 5; lvt_17_5_ <= 10; ++lvt_17_5_) {
                    this.setBlockState(p_addComponentParts_1_, lvt_14_1_, lvt_16_5_, 12 - lvt_16_5_, lvt_17_5_, p_addComponentParts_3_);
                }
            }

            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 0, 2, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 0, 2, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 4, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 5, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 6, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 8, 2, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 8, 2, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 8, 2, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 8, 2, 6, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 2, 7, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 2, 8, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 8, 2, 9, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 2, 2, 6, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 2, 2, 7, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 2, 2, 8, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 2, 2, 9, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 4, 4, 10, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 5, 4, 10, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 6, 4, 10, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 5, 5, 10, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 2, 1, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 2, 2, 0, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.NORTH, 2, 3, 1, p_addComponentParts_3_);
            this.createVillageDoor(p_addComponentParts_1_, p_addComponentParts_3_, p_addComponentParts_2_, 2, 1, 0, EnumFacing.NORTH);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, -1, 3, 2, -1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            if (this.getBlockStateFromPos(p_addComponentParts_1_, 2, 0, -1, p_addComponentParts_3_).isAir() && !this.getBlockStateFromPos(p_addComponentParts_1_, 2, -1, -1, p_addComponentParts_3_).isAir()) {
                this.setBlockState(p_addComponentParts_1_, lvt_12_1_, 2, 0, -1, p_addComponentParts_3_);
                if (this.getBlockStateFromPos(p_addComponentParts_1_, 2, -1, -1, p_addComponentParts_3_).getBlock() == Blocks.GRASS_PATH) {
                    this.setBlockState(p_addComponentParts_1_, Blocks.GRASS_BLOCK.getDefaultState(), 2, -1, -1, p_addComponentParts_3_);
                }
            }

            for(lvt_16_5_ = 0; lvt_16_5_ < 5; ++lvt_16_5_) {
                for(lvt_17_5_ = 0; lvt_17_5_ < 9; ++lvt_17_5_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_17_5_, 7, lvt_16_5_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, lvt_5_1_, lvt_17_5_, -1, lvt_16_5_, p_addComponentParts_3_);
                }
            }

            for(lvt_16_5_ = 5; lvt_16_5_ < 11; ++lvt_16_5_) {
                for(lvt_17_5_ = 2; lvt_17_5_ < 9; ++lvt_17_5_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_17_5_, 7, lvt_16_5_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, lvt_5_1_, lvt_17_5_, -1, lvt_16_5_, p_addComponentParts_3_);
                }
            }

            this.spawnVillagers(p_addComponentParts_1_, p_addComponentParts_3_, 4, 1, 2, 2);
            return true;
        }
    }

    public static class Hall extends VillagePieces.Village {
        public Hall() {
        }

        public Hall(VillagePieces.Start p_i45567_1_, int p_i45567_2_, Random p_i45567_3_, MutableBoundingBox p_i45567_4_, EnumFacing p_i45567_5_) {
            super(p_i45567_1_, p_i45567_2_);
            this.setCoordBaseMode(p_i45567_5_);
            this.boundingBox = p_i45567_4_;
        }

        public static VillagePieces.Hall createPiece(VillagePieces.Start p_createPiece_0_, List<StructurePiece> p_createPiece_1_, Random p_createPiece_2_, int p_createPiece_3_, int p_createPiece_4_, int p_createPiece_5_, EnumFacing p_createPiece_6_, int p_createPiece_7_) {
            MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_createPiece_3_, p_createPiece_4_, p_createPiece_5_, 0, 0, 0, 9, 7, 11, p_createPiece_6_);
            return canVillageGoDeeper(lvt_8_1_) && StructurePiece.findIntersecting(p_createPiece_1_, lvt_8_1_) == null ? new VillagePieces.Hall(p_createPiece_0_, p_createPiece_7_, p_createPiece_2_, lvt_8_1_, p_createPiece_6_) : null;
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
            }

            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState lvt_6_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState lvt_7_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
            IBlockState lvt_8_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
            IBlockState lvt_9_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_PLANKS.getDefaultState());
            IBlockState lvt_10_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_LOG.getDefaultState());
            IBlockState lvt_11_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 2, 0, 6, 8, 0, 10, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 6, 0, 6, p_addComponentParts_3_);
            IBlockState lvt_12_1_ = lvt_11_1_.withProperty(BlockFence.field_196409_a, true).withProperty(BlockFence.field_196413_c, true);
            IBlockState lvt_13_1_ = lvt_11_1_.withProperty(BlockFence.field_196414_y, true).withProperty(BlockFence.field_196411_b, true);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 2, 1, 6, 2, 1, 9, lvt_12_1_, lvt_12_1_, false);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_.withProperty(BlockFence.field_196413_c, true).withProperty(BlockFence.field_196411_b, true), 2, 1, 10, p_addComponentParts_3_);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 8, 1, 6, 8, 1, 9, lvt_12_1_, lvt_12_1_, false);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_.withProperty(BlockFence.field_196413_c, true).withProperty(BlockFence.field_196414_y, true), 8, 1, 10, p_addComponentParts_3_);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 1, 10, 7, 1, 10, lvt_13_1_, lvt_13_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 1, 7, 0, 4, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 0, 0, 3, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 8, 0, 0, 8, 3, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 0, 7, 1, 0, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 5, 7, 1, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 2, 0, 7, 3, 0, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 2, 5, 7, 3, 5, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 4, 1, 8, 4, 1, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 4, 4, 8, 4, 4, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 5, 2, 8, 5, 3, lvt_9_1_, lvt_9_1_, false);
            this.setBlockState(p_addComponentParts_1_, lvt_9_1_, 0, 4, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_9_1_, 0, 4, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_9_1_, 8, 4, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_9_1_, 8, 4, 3, p_addComponentParts_3_);
            IBlockState lvt_14_1_ = lvt_6_1_;
            IBlockState lvt_15_1_ = lvt_7_1_;

            int lvt_18_2_;
            for(int lvt_17_1_ = -1; lvt_17_1_ <= 2; ++lvt_17_1_) {
                for(lvt_18_2_ = 0; lvt_18_2_ <= 8; ++lvt_18_2_) {
                    this.setBlockState(p_addComponentParts_1_, lvt_14_1_, lvt_18_2_, 4 + lvt_17_1_, lvt_17_1_, p_addComponentParts_3_);
                    this.setBlockState(p_addComponentParts_1_, lvt_15_1_, lvt_18_2_, 4 + lvt_17_1_, 5 - lvt_17_1_, p_addComponentParts_3_);
                }
            }

            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 0, 2, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 0, 2, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 8, 2, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 8, 2, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 2, 2, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 3, 2, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 5, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 2, 1, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.OAK_PRESSURE_PLATE.getDefaultState(), 2, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_9_1_, 1, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_14_1_, 2, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 1, 1, 3, p_addComponentParts_3_);
            IBlockState lvt_17_2_ = Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.field_196505_a, SlabType.DOUBLE);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 5, 0, 1, 7, 0, 3, lvt_17_2_, lvt_17_2_, false);
            this.setBlockState(p_addComponentParts_1_, lvt_17_2_, 6, 1, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_17_2_, 6, 1, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 2, 1, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 2, 2, 0, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.NORTH, 2, 3, 1, p_addComponentParts_3_);
            this.createVillageDoor(p_addComponentParts_1_, p_addComponentParts_3_, p_addComponentParts_2_, 2, 1, 0, EnumFacing.NORTH);
            if (this.getBlockStateFromPos(p_addComponentParts_1_, 2, 0, -1, p_addComponentParts_3_).isAir() && !this.getBlockStateFromPos(p_addComponentParts_1_, 2, -1, -1, p_addComponentParts_3_).isAir()) {
                this.setBlockState(p_addComponentParts_1_, lvt_14_1_, 2, 0, -1, p_addComponentParts_3_);
                if (this.getBlockStateFromPos(p_addComponentParts_1_, 2, -1, -1, p_addComponentParts_3_).getBlock() == Blocks.GRASS_PATH) {
                    this.setBlockState(p_addComponentParts_1_, Blocks.GRASS_BLOCK.getDefaultState(), 2, -1, -1, p_addComponentParts_3_);
                }
            }

            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 6, 1, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 6, 2, 5, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.SOUTH, 6, 3, 4, p_addComponentParts_3_);
            this.createVillageDoor(p_addComponentParts_1_, p_addComponentParts_3_, p_addComponentParts_2_, 6, 1, 5, EnumFacing.SOUTH);

            for(lvt_18_2_ = 0; lvt_18_2_ < 5; ++lvt_18_2_) {
                for(int lvt_19_1_ = 0; lvt_19_1_ < 9; ++lvt_19_1_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_19_1_, 7, lvt_18_2_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, lvt_5_1_, lvt_19_1_, -1, lvt_18_2_, p_addComponentParts_3_);
                }
            }

            this.spawnVillagers(p_addComponentParts_1_, p_addComponentParts_3_, 4, 1, 2, 2);
            return true;
        }

        protected int chooseProfession(int p_chooseProfession_1_, int p_chooseProfession_2_) {
            return p_chooseProfession_1_ == 0 ? 4 : super.chooseProfession(p_chooseProfession_1_, p_chooseProfession_2_);
        }
    }

    public static class WoodHut extends VillagePieces.Village {
        private boolean isTallHouse;
        private int tablePosition;

        public WoodHut() {
        }

        public WoodHut(VillagePieces.Start p_i45565_1_, int p_i45565_2_, Random p_i45565_3_, MutableBoundingBox p_i45565_4_, EnumFacing p_i45565_5_) {
            super(p_i45565_1_, p_i45565_2_);
            this.setCoordBaseMode(p_i45565_5_);
            this.boundingBox = p_i45565_4_;
            this.isTallHouse = p_i45565_3_.nextBoolean();
            this.tablePosition = p_i45565_3_.nextInt(3);
        }

        protected void writeStructureToNBT(NBTTagCompound p_writeStructureToNBT_1_) {
            super.writeStructureToNBT(p_writeStructureToNBT_1_);
            p_writeStructureToNBT_1_.setInteger("T", this.tablePosition);
            p_writeStructureToNBT_1_.setBoolean("C", this.isTallHouse);
        }

        protected void readStructureFromNBT(NBTTagCompound p_readStructureFromNBT_1_, TemplateManager p_readStructureFromNBT_2_) {
            super.readStructureFromNBT(p_readStructureFromNBT_1_, p_readStructureFromNBT_2_);
            this.tablePosition = p_readStructureFromNBT_1_.getInteger("T");
            this.isTallHouse = p_readStructureFromNBT_1_.getBoolean("C");
        }

        public static VillagePieces.WoodHut createPiece(VillagePieces.Start p_createPiece_0_, List<StructurePiece> p_createPiece_1_, Random p_createPiece_2_, int p_createPiece_3_, int p_createPiece_4_, int p_createPiece_5_, EnumFacing p_createPiece_6_, int p_createPiece_7_) {
            MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_createPiece_3_, p_createPiece_4_, p_createPiece_5_, 0, 0, 0, 4, 6, 5, p_createPiece_6_);
            return canVillageGoDeeper(lvt_8_1_) && StructurePiece.findIntersecting(p_createPiece_1_, lvt_8_1_) == null ? new VillagePieces.WoodHut(p_createPiece_0_, p_createPiece_7_, p_createPiece_2_, lvt_8_1_, p_createPiece_6_) : null;
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
            }

            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState lvt_6_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_PLANKS.getDefaultState());
            IBlockState lvt_7_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState lvt_8_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_LOG.getDefaultState());
            IBlockState lvt_9_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 1, 3, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 0, 3, 0, 4, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 1, 2, 0, 3, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);
            if (this.isTallHouse) {
                this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 4, 1, 2, 4, 3, lvt_8_1_, lvt_8_1_, false);
            } else {
                this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 5, 1, 2, 5, 3, lvt_8_1_, lvt_8_1_, false);
            }

            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 1, 4, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 2, 4, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 1, 4, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 2, 4, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 0, 4, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 0, 4, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 0, 4, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 3, 4, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 3, 4, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 3, 4, 3, p_addComponentParts_3_);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 0, 0, 3, 0, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 1, 0, 3, 3, 0, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 4, 0, 3, 4, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 1, 4, 3, 3, 4, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 1, 0, 3, 3, lvt_6_1_, lvt_6_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 3, 1, 1, 3, 3, 3, lvt_6_1_, lvt_6_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 0, 2, 3, 0, lvt_6_1_, lvt_6_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 4, 2, 3, 4, lvt_6_1_, lvt_6_1_, false);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 3, 2, 2, p_addComponentParts_3_);
            if (this.tablePosition > 0) {
                this.setBlockState(p_addComponentParts_1_, lvt_9_1_.withProperty(BlockFence.field_196409_a, true).withProperty(this.tablePosition == 1 ? BlockFence.field_196414_y : BlockFence.field_196411_b, true), this.tablePosition, 1, 3, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, Blocks.OAK_PRESSURE_PLATE.getDefaultState(), this.tablePosition, 2, 3, p_addComponentParts_3_);
            }

            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 1, 1, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 1, 2, 0, p_addComponentParts_3_);
            this.createVillageDoor(p_addComponentParts_1_, p_addComponentParts_3_, p_addComponentParts_2_, 1, 1, 0, EnumFacing.NORTH);
            if (this.getBlockStateFromPos(p_addComponentParts_1_, 1, 0, -1, p_addComponentParts_3_).isAir() && !this.getBlockStateFromPos(p_addComponentParts_1_, 1, -1, -1, p_addComponentParts_3_).isAir()) {
                this.setBlockState(p_addComponentParts_1_, lvt_7_1_, 1, 0, -1, p_addComponentParts_3_);
                if (this.getBlockStateFromPos(p_addComponentParts_1_, 1, -1, -1, p_addComponentParts_3_).getBlock() == Blocks.GRASS_PATH) {
                    this.setBlockState(p_addComponentParts_1_, Blocks.GRASS_BLOCK.getDefaultState(), 1, -1, -1, p_addComponentParts_3_);
                }
            }

            for(int lvt_10_1_ = 0; lvt_10_1_ < 5; ++lvt_10_1_) {
                for(int lvt_11_1_ = 0; lvt_11_1_ < 4; ++lvt_11_1_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_11_1_, 6, lvt_10_1_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, lvt_5_1_, lvt_11_1_, -1, lvt_10_1_, p_addComponentParts_3_);
                }
            }

            this.spawnVillagers(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 2, 1);
            return true;
        }
    }

    public static class House1 extends VillagePieces.Village {
        public House1() {
        }

        public House1(VillagePieces.Start p_i45571_1_, int p_i45571_2_, Random p_i45571_3_, MutableBoundingBox p_i45571_4_, EnumFacing p_i45571_5_) {
            super(p_i45571_1_, p_i45571_2_);
            this.setCoordBaseMode(p_i45571_5_);
            this.boundingBox = p_i45571_4_;
        }

        public static VillagePieces.House1 createPiece(VillagePieces.Start p_createPiece_0_, List<StructurePiece> p_createPiece_1_, Random p_createPiece_2_, int p_createPiece_3_, int p_createPiece_4_, int p_createPiece_5_, EnumFacing p_createPiece_6_, int p_createPiece_7_) {
            MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_createPiece_3_, p_createPiece_4_, p_createPiece_5_, 0, 0, 0, 9, 9, 6, p_createPiece_6_);
            return canVillageGoDeeper(lvt_8_1_) && StructurePiece.findIntersecting(p_createPiece_1_, lvt_8_1_) == null ? new VillagePieces.House1(p_createPiece_0_, p_createPiece_7_, p_createPiece_2_, lvt_8_1_, p_createPiece_6_) : null;
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 9 - 1, 0);
            }

            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState lvt_6_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState lvt_7_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
            IBlockState lvt_8_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
            IBlockState lvt_9_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_PLANKS.getDefaultState());
            IBlockState lvt_10_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState lvt_11_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 1, 7, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 0, 8, 0, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 5, 0, 8, 5, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 6, 1, 8, 6, 4, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 7, 2, 8, 7, 3, lvt_5_1_, lvt_5_1_, false);

            int lvt_13_2_;
            for(int lvt_12_1_ = -1; lvt_12_1_ <= 2; ++lvt_12_1_) {
                for(lvt_13_2_ = 0; lvt_13_2_ <= 8; ++lvt_13_2_) {
                    this.setBlockState(p_addComponentParts_1_, lvt_6_1_, lvt_13_2_, 6 + lvt_12_1_, lvt_12_1_, p_addComponentParts_3_);
                    this.setBlockState(p_addComponentParts_1_, lvt_7_1_, lvt_13_2_, 6 + lvt_12_1_, 5 - lvt_12_1_, p_addComponentParts_3_);
                }
            }

            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 0, 0, 1, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 5, 8, 1, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 8, 1, 0, 8, 1, 4, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 2, 1, 0, 7, 1, 0, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 2, 0, 0, 4, 0, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 2, 5, 0, 4, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 8, 2, 5, 8, 4, 5, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 8, 2, 0, 8, 4, 0, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 2, 1, 0, 4, 4, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 2, 5, 7, 4, 5, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 8, 2, 1, 8, 4, 4, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 2, 0, 7, 4, 0, lvt_9_1_, lvt_9_1_, false);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 4, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 5, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 6, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 4, 3, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 5, 3, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 6, 3, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 3, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 3, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 3, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 8, 3, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 2, 2, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 3, 2, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 5, 2, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 6, 2, 5, p_addComponentParts_3_);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 4, 1, 7, 4, 1, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 4, 4, 7, 4, 4, lvt_9_1_, lvt_9_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 3, 4, 7, 3, 4, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
            this.setBlockState(p_addComponentParts_1_, lvt_9_1_, 7, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 7, 1, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 6, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 5, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 4, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 3, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 6, 1, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.OAK_PRESSURE_PLATE.getDefaultState(), 6, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_11_1_, 4, 1, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.OAK_PRESSURE_PLATE.getDefaultState(), 4, 2, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.CRAFTING_TABLE.getDefaultState(), 7, 1, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 1, 1, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 1, 2, 0, p_addComponentParts_3_);
            this.createVillageDoor(p_addComponentParts_1_, p_addComponentParts_3_, p_addComponentParts_2_, 1, 1, 0, EnumFacing.NORTH);
            if (this.getBlockStateFromPos(p_addComponentParts_1_, 1, 0, -1, p_addComponentParts_3_).isAir() && !this.getBlockStateFromPos(p_addComponentParts_1_, 1, -1, -1, p_addComponentParts_3_).isAir()) {
                this.setBlockState(p_addComponentParts_1_, lvt_10_1_, 1, 0, -1, p_addComponentParts_3_);
                if (this.getBlockStateFromPos(p_addComponentParts_1_, 1, -1, -1, p_addComponentParts_3_).getBlock() == Blocks.GRASS_PATH) {
                    this.setBlockState(p_addComponentParts_1_, Blocks.GRASS_BLOCK.getDefaultState(), 1, -1, -1, p_addComponentParts_3_);
                }
            }

            for(lvt_13_2_ = 0; lvt_13_2_ < 6; ++lvt_13_2_) {
                for(int lvt_14_1_ = 0; lvt_14_1_ < 9; ++lvt_14_1_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_14_1_, 9, lvt_13_2_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, lvt_5_1_, lvt_14_1_, -1, lvt_13_2_, p_addComponentParts_3_);
                }
            }

            this.spawnVillagers(p_addComponentParts_1_, p_addComponentParts_3_, 2, 1, 2, 1);
            return true;
        }

        protected int chooseProfession(int p_chooseProfession_1_, int p_chooseProfession_2_) {
            return 1;
        }
    }

    public static class Church extends VillagePieces.Village {
        public Church() {
        }

        public Church(VillagePieces.Start p_i45564_1_, int p_i45564_2_, Random p_i45564_3_, MutableBoundingBox p_i45564_4_, EnumFacing p_i45564_5_) {
            super(p_i45564_1_, p_i45564_2_);
            this.setCoordBaseMode(p_i45564_5_);
            this.boundingBox = p_i45564_4_;
        }

        public static VillagePieces.Church createPiece(VillagePieces.Start p_createPiece_0_, List<StructurePiece> p_createPiece_1_, Random p_createPiece_2_, int p_createPiece_3_, int p_createPiece_4_, int p_createPiece_5_, EnumFacing p_createPiece_6_, int p_createPiece_7_) {
            MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_createPiece_3_, p_createPiece_4_, p_createPiece_5_, 0, 0, 0, 5, 12, 9, p_createPiece_6_);
            return canVillageGoDeeper(lvt_8_1_) && StructurePiece.findIntersecting(p_createPiece_1_, lvt_8_1_) == null ? new VillagePieces.Church(p_createPiece_0_, p_createPiece_7_, p_createPiece_2_, lvt_8_1_, p_createPiece_6_) : null;
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 12 - 1, 0);
            }

            IBlockState lvt_5_1_ = Blocks.COBBLESTONE.getDefaultState();
            IBlockState lvt_6_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState lvt_7_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
            IBlockState lvt_8_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 1, 3, 3, 7, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 5, 1, 3, 9, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 0, 3, 0, 8, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 0, 3, 10, 0, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 1, 0, 10, 3, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 4, 1, 1, 4, 10, 3, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 4, 0, 4, 7, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 4, 0, 4, 4, 4, 7, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 8, 3, 4, 8, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 5, 4, 3, 10, 4, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 5, 5, 3, 5, 7, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 9, 0, 4, 9, 4, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 4, 0, 4, 4, 4, lvt_5_1_, lvt_5_1_, false);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 0, 11, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 4, 11, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 2, 11, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 2, 11, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 1, 1, 6, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 1, 1, 7, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 2, 1, 7, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 3, 1, 6, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 3, 1, 7, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 1, 1, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 2, 1, 6, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 3, 1, 5, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_7_1_, 1, 2, 7, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_8_1_, 3, 2, 7, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 3, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 4, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 4, 3, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 6, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 7, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 4, 6, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 4, 7, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 2, 6, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 2, 7, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 2, 6, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 2, 7, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 3, 6, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 4, 3, 6, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 2, 3, 8, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.SOUTH, 2, 4, 7, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.EAST, 1, 4, 6, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.WEST, 3, 4, 6, p_addComponentParts_3_);
            this.placeTorch(p_addComponentParts_1_, EnumFacing.NORTH, 2, 4, 5, p_addComponentParts_3_);
            IBlockState lvt_9_1_ = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.WEST);

            int lvt_10_2_;
            for(lvt_10_2_ = 1; lvt_10_2_ <= 9; ++lvt_10_2_) {
                this.setBlockState(p_addComponentParts_1_, lvt_9_1_, 3, lvt_10_2_, 3, p_addComponentParts_3_);
            }

            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 2, 1, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 2, 2, 0, p_addComponentParts_3_);
            this.createVillageDoor(p_addComponentParts_1_, p_addComponentParts_3_, p_addComponentParts_2_, 2, 1, 0, EnumFacing.NORTH);
            if (this.getBlockStateFromPos(p_addComponentParts_1_, 2, 0, -1, p_addComponentParts_3_).isAir() && !this.getBlockStateFromPos(p_addComponentParts_1_, 2, -1, -1, p_addComponentParts_3_).isAir()) {
                this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 2, 0, -1, p_addComponentParts_3_);
                if (this.getBlockStateFromPos(p_addComponentParts_1_, 2, -1, -1, p_addComponentParts_3_).getBlock() == Blocks.GRASS_PATH) {
                    this.setBlockState(p_addComponentParts_1_, Blocks.GRASS_BLOCK.getDefaultState(), 2, -1, -1, p_addComponentParts_3_);
                }
            }

            for(lvt_10_2_ = 0; lvt_10_2_ < 9; ++lvt_10_2_) {
                for(int lvt_11_1_ = 0; lvt_11_1_ < 5; ++lvt_11_1_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_11_1_, 12, lvt_10_2_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, lvt_5_1_, lvt_11_1_, -1, lvt_10_2_, p_addComponentParts_3_);
                }
            }

            this.spawnVillagers(p_addComponentParts_1_, p_addComponentParts_3_, 2, 1, 2, 1);
            return true;
        }

        protected int chooseProfession(int p_chooseProfession_1_, int p_chooseProfession_2_) {
            return 2;
        }
    }

    public static class House4Garden extends VillagePieces.Village {
        private boolean isRoofAccessible;

        public House4Garden() {
        }

        public House4Garden(VillagePieces.Start p_i45566_1_, int p_i45566_2_, Random p_i45566_3_, MutableBoundingBox p_i45566_4_, EnumFacing p_i45566_5_) {
            super(p_i45566_1_, p_i45566_2_);
            this.setCoordBaseMode(p_i45566_5_);
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = p_i45566_3_.nextBoolean();
        }

        protected void writeStructureToNBT(NBTTagCompound p_writeStructureToNBT_1_) {
            super.writeStructureToNBT(p_writeStructureToNBT_1_);
            p_writeStructureToNBT_1_.setBoolean("Terrace", this.isRoofAccessible);
        }

        protected void readStructureFromNBT(NBTTagCompound p_readStructureFromNBT_1_, TemplateManager p_readStructureFromNBT_2_) {
            super.readStructureFromNBT(p_readStructureFromNBT_1_, p_readStructureFromNBT_2_);
            this.isRoofAccessible = p_readStructureFromNBT_1_.getBoolean("Terrace");
        }

        public static VillagePieces.House4Garden createPiece(VillagePieces.Start p_createPiece_0_, List<StructurePiece> p_createPiece_1_, Random p_createPiece_2_, int p_createPiece_3_, int p_createPiece_4_, int p_createPiece_5_, EnumFacing p_createPiece_6_, int p_createPiece_7_) {
            MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_createPiece_3_, p_createPiece_4_, p_createPiece_5_, 0, 0, 0, 5, 6, 5, p_createPiece_6_);
            return StructurePiece.findIntersecting(p_createPiece_1_, lvt_8_1_) != null ? null : new VillagePieces.House4Garden(p_createPiece_0_, p_createPiece_7_, p_createPiece_2_, lvt_8_1_, p_createPiece_6_);
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
            }

            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState lvt_6_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_PLANKS.getDefaultState());
            IBlockState lvt_7_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState lvt_8_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_LOG.getDefaultState());
            IBlockState lvt_9_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 0, 0, 4, 0, 4, lvt_5_1_, lvt_5_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 4, 0, 4, 4, 4, lvt_8_1_, lvt_8_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 4, 1, 3, 4, 3, lvt_6_1_, lvt_6_1_, false);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 0, 1, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 0, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 0, 3, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 4, 1, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 4, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 4, 3, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 0, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 0, 2, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 0, 3, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 4, 1, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 4, 2, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_5_1_, 4, 3, 4, p_addComponentParts_3_);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 0, 1, 1, 0, 3, 3, lvt_6_1_, lvt_6_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 4, 1, 1, 4, 3, 3, lvt_6_1_, lvt_6_1_, false);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 4, 3, 3, 4, lvt_6_1_, lvt_6_1_, false);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 0, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196411_b, true).withProperty(BlockGlassPane.field_196414_y, true), 2, 2, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.GLASS_PANE.getDefaultState().withProperty(BlockGlassPane.field_196413_c, true).withProperty(BlockGlassPane.field_196409_a, true), 4, 2, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 1, 1, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 1, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 1, 3, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 2, 3, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 3, 3, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 3, 2, 0, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 3, 1, 0, p_addComponentParts_3_);
            if (this.getBlockStateFromPos(p_addComponentParts_1_, 2, 0, -1, p_addComponentParts_3_).isAir() && !this.getBlockStateFromPos(p_addComponentParts_1_, 2, -1, -1, p_addComponentParts_3_).isAir()) {
                this.setBlockState(p_addComponentParts_1_, lvt_7_1_, 2, 0, -1, p_addComponentParts_3_);
                if (this.getBlockStateFromPos(p_addComponentParts_1_, 2, -1, -1, p_addComponentParts_3_).getBlock() == Blocks.GRASS_PATH) {
                    this.setBlockState(p_addComponentParts_1_, Blocks.GRASS_BLOCK.getDefaultState(), 2, -1, -1, p_addComponentParts_3_);
                }
            }

            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 1, 3, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            if (this.isRoofAccessible) {
                for(int lvt_12_1_ = 0; lvt_12_1_ <= 4; ++lvt_12_1_) {
                    for(int lvt_13_1_ = 0; lvt_13_1_ <= 4; ++lvt_13_1_) {
                        boolean lvt_14_1_ = lvt_12_1_ == 0 || lvt_12_1_ == 4;
                        boolean lvt_15_1_ = lvt_13_1_ == 0 || lvt_13_1_ == 4;
                        if (lvt_14_1_ || lvt_15_1_) {
                            boolean lvt_16_1_ = lvt_12_1_ == 0 || lvt_12_1_ == 4;
                            boolean lvt_17_1_ = lvt_13_1_ == 0 || lvt_13_1_ == 4;
                            IBlockState lvt_18_1_ = lvt_9_1_.withProperty(BlockFence.field_196413_c, lvt_16_1_ && lvt_13_1_ != 0).withProperty(BlockFence.field_196409_a, lvt_16_1_ && lvt_13_1_ != 4).withProperty(BlockFence.field_196414_y, lvt_17_1_ && lvt_12_1_ != 0).withProperty(BlockFence.field_196411_b, lvt_17_1_ && lvt_12_1_ != 4);
                            this.setBlockState(p_addComponentParts_1_, lvt_18_1_, lvt_12_1_, 5, lvt_13_1_, p_addComponentParts_3_);
                        }
                    }
                }
            }

            if (this.isRoofAccessible) {
                IBlockState lvt_10_2_ = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH);
                this.setBlockState(p_addComponentParts_1_, lvt_10_2_, 3, 1, 3, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, lvt_10_2_, 3, 2, 3, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, lvt_10_2_, 3, 3, 3, p_addComponentParts_3_);
                this.setBlockState(p_addComponentParts_1_, lvt_10_2_, 3, 4, 3, p_addComponentParts_3_);
            }

            this.placeTorch(p_addComponentParts_1_, EnumFacing.NORTH, 2, 3, 1, p_addComponentParts_3_);

            for(int lvt_10_3_ = 0; lvt_10_3_ < 5; ++lvt_10_3_) {
                for(int lvt_11_2_ = 0; lvt_11_2_ < 5; ++lvt_11_2_) {
                    this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_11_2_, 6, lvt_10_3_, p_addComponentParts_3_);
                    this.replaceAirAndLiquidDownwards(p_addComponentParts_1_, lvt_5_1_, lvt_11_2_, -1, lvt_10_3_, p_addComponentParts_3_);
                }
            }

            this.spawnVillagers(p_addComponentParts_1_, p_addComponentParts_3_, 1, 1, 2, 1);
            return true;
        }
    }

    public static class Path extends VillagePieces.Road {
        private int length;

        public Path() {
        }

        public Path(VillagePieces.Start p_i45562_1_, int p_i45562_2_, Random p_i45562_3_, MutableBoundingBox p_i45562_4_, EnumFacing p_i45562_5_) {
            super(p_i45562_1_, p_i45562_2_);
            this.setCoordBaseMode(p_i45562_5_);
            this.boundingBox = p_i45562_4_;
            this.length = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
        }

        protected void writeStructureToNBT(NBTTagCompound p_writeStructureToNBT_1_) {
            super.writeStructureToNBT(p_writeStructureToNBT_1_);
            p_writeStructureToNBT_1_.setInteger("Length", this.length);
        }

        protected void readStructureFromNBT(NBTTagCompound p_readStructureFromNBT_1_, TemplateManager p_readStructureFromNBT_2_) {
            super.readStructureFromNBT(p_readStructureFromNBT_1_, p_readStructureFromNBT_2_);
            this.length = p_readStructureFromNBT_1_.getInteger("Length");
        }

        public void buildComponent(StructurePiece p_buildComponent_1_, List<StructurePiece> p_buildComponent_2_, Random p_buildComponent_3_) {
            boolean lvt_4_1_ = false;

            int lvt_5_1_;
            StructurePiece lvt_6_2_;
            for(lvt_5_1_ = p_buildComponent_3_.nextInt(5); lvt_5_1_ < this.length - 8; lvt_5_1_ += 2 + p_buildComponent_3_.nextInt(5)) {
                lvt_6_2_ = this.getNextComponentNN((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, 0, lvt_5_1_);
                if (lvt_6_2_ != null) {
                    lvt_5_1_ += Math.max(lvt_6_2_.getBoundingBox().getXSize(), lvt_6_2_.getBoundingBox().getZSize());
                    lvt_4_1_ = true;
                }
            }

            for(lvt_5_1_ = p_buildComponent_3_.nextInt(5); lvt_5_1_ < this.length - 8; lvt_5_1_ += 2 + p_buildComponent_3_.nextInt(5)) {
                lvt_6_2_ = this.getNextComponentPP((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, 0, lvt_5_1_);
                if (lvt_6_2_ != null) {
                    lvt_5_1_ += Math.max(lvt_6_2_.getBoundingBox().getXSize(), lvt_6_2_.getBoundingBox().getZSize());
                    lvt_4_1_ = true;
                }
            }

            EnumFacing lvt_6_3_ = this.getCoordBaseMode();
            if (lvt_4_1_ && p_buildComponent_3_.nextInt(3) > 0 && lvt_6_3_ != null) {
                switch(EnumFacing.values()[lvt_6_3_.ordinal()]) {
                    case NORTH:
                default:
                    VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
                    break;
                    case SOUTH:
                    VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
                    break;
                    case EAST:
                    VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    break;
                    case WEST:
                    VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }

            if (lvt_4_1_ && p_buildComponent_3_.nextInt(3) > 0 && lvt_6_3_ != null) {
                switch(EnumFacing.values()[lvt_6_3_.ordinal()]) {
                    case NORTH:
                default:
                    VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
                    break;
                    case SOUTH:
                    VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
                    break;
                    case EAST:
                    VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    break;
                    case WEST:
                    VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }

        }

        public static MutableBoundingBox findPieceBox(VillagePieces.Start p_findPieceBox_0_, List<StructurePiece> p_findPieceBox_1_, Random p_findPieceBox_2_, int p_findPieceBox_3_, int p_findPieceBox_4_, int p_findPieceBox_5_, EnumFacing p_findPieceBox_6_) {
            for(int lvt_7_1_ = 7 * MathHelper.getInt(p_findPieceBox_2_, 3, 5); lvt_7_1_ >= 7; lvt_7_1_ -= 7) {
                MutableBoundingBox lvt_8_1_ = MutableBoundingBox.getComponentToAddBoundingBox(p_findPieceBox_3_, p_findPieceBox_4_, p_findPieceBox_5_, 0, 0, 0, 3, 3, lvt_7_1_, p_findPieceBox_6_);
                if (StructurePiece.findIntersecting(p_findPieceBox_1_, lvt_8_1_) == null) {
                    return lvt_8_1_;
                }
            }

            return null;
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.GRASS_PATH.getDefaultState());
            IBlockState lvt_6_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_PLANKS.getDefaultState());
            IBlockState lvt_7_1_ = this.getBiomeSpecificBlockState(Blocks.GRAVEL.getDefaultState());
            IBlockState lvt_8_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            MutableBlockPos lvt_9_1_ = new MutableBlockPos();
            this.boundingBox.minY = 1000;
            this.boundingBox.maxY = 0;

            for(int lvt_10_1_ = this.boundingBox.minX; lvt_10_1_ <= this.boundingBox.maxX; ++lvt_10_1_) {
                for(int lvt_11_1_ = this.boundingBox.minZ; lvt_11_1_ <= this.boundingBox.maxZ; ++lvt_11_1_) {
                    lvt_9_1_.setPos(lvt_10_1_, 64, lvt_11_1_);
                    if (p_addComponentParts_3_.isVecInside(lvt_9_1_)) {
                        int lvt_12_1_ = p_addComponentParts_1_.getTopBlockY(net.minecraft.world.gen.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, lvt_9_1_.getX(), lvt_9_1_.getZ());
                        lvt_9_1_.setPos(lvt_9_1_.getX(), lvt_12_1_, lvt_9_1_.getZ()).move(EnumFacing.DOWN);
                        if (lvt_9_1_.getY() < p_addComponentParts_1_.getSeaLevel()) {
                            lvt_9_1_.setY(p_addComponentParts_1_.getSeaLevel() - 1);
                        }

                        while(lvt_9_1_.getY() >= p_addComponentParts_1_.getSeaLevel() - 1) {
                            IBlockState lvt_13_1_ = p_addComponentParts_1_.getBlockState(lvt_9_1_);
                            Block lvt_14_1_ = lvt_13_1_.getBlock();
                            if (lvt_14_1_ == Blocks.GRASS_BLOCK && p_addComponentParts_1_.isAirBlock(lvt_9_1_.up())) {
                                p_addComponentParts_1_.setBlockState(lvt_9_1_, lvt_5_1_, 2);
                                break;
                            }

                            if (lvt_13_1_.getMaterial().isLiquid()) {
                                p_addComponentParts_1_.setBlockState(new BlockPos(lvt_9_1_), lvt_6_1_, 2);
                                break;
                            }

                            if (lvt_14_1_ == Blocks.SAND || lvt_14_1_ == Blocks.RED_SAND || lvt_14_1_ == Blocks.SANDSTONE || lvt_14_1_ == Blocks.CHISELED_SANDSTONE || lvt_14_1_ == Blocks.CUT_SANDSTONE || lvt_14_1_ == Blocks.RED_SANDSTONE || lvt_14_1_ == Blocks.CHISELED_SANDSTONE || lvt_14_1_ == Blocks.CUT_SANDSTONE) {
                                p_addComponentParts_1_.setBlockState(lvt_9_1_, lvt_7_1_, 2);
                                p_addComponentParts_1_.setBlockState(lvt_9_1_.down(), lvt_8_1_, 2);
                                break;
                            }

                            lvt_9_1_.move(EnumFacing.DOWN);
                        }

                        this.boundingBox.minY = Math.min(this.boundingBox.minY, lvt_9_1_.getY());
                        this.boundingBox.maxY = Math.max(this.boundingBox.maxY, lvt_9_1_.getY());
                    }
                }
            }

            return true;
        }
    }

    public abstract static class Road extends VillagePieces.Village {
        public Road() {
        }

        protected Road(VillagePieces.Start p_i2108_1_, int p_i2108_2_) {
            super(p_i2108_1_, p_i2108_2_);
        }
    }

    public static class Start extends VillagePieces.Well {
        public int terrainType;
        public VillagePieces.PieceWeight lastPlaced;
        public List<VillagePieces.PieceWeight> structureVillageWeightedPieceList;
        public List<StructurePiece> pendingHouses = Lists.newArrayList();
        public List<StructurePiece> pendingRoads = Lists.newArrayList();

        public Start() {
        }

        public Start(int p_i48769_1_, Random p_i48769_2_, int p_i48769_3_, int p_i48769_4_, List<VillagePieces.PieceWeight> p_i48769_5_, VillageConfig p_i48769_6_) {
            super(null, 0, p_i48769_2_, p_i48769_3_, p_i48769_4_);
            this.structureVillageWeightedPieceList = p_i48769_5_;
            this.terrainType = p_i48769_6_.field_202461_a;
            this.structureType = p_i48769_6_.field_202462_b;
            this.func_202579_a(this.structureType);
            this.isZombieInfested = p_i48769_2_.nextInt(50) == 0;
        }
    }

    public static class Well extends VillagePieces.Village {
        public Well() {
        }

        public Well(VillagePieces.Start p_i2109_1_, int p_i2109_2_, Random p_i2109_3_, int p_i2109_4_, int p_i2109_5_) {
            super(p_i2109_1_, p_i2109_2_);
            this.setCoordBaseMode(Plane.HORIZONTAL.random(p_i2109_3_));
            if (this.getCoordBaseMode().getAxis() == Axis.Z) {
                this.boundingBox = new MutableBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
            } else {
                this.boundingBox = new MutableBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
            }

        }

        public void buildComponent(StructurePiece p_buildComponent_1_, List<StructurePiece> p_buildComponent_2_, Random p_buildComponent_3_) {
            VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
            VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
            VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            VillagePieces.generateAndAddRoadPiece((VillagePieces.Start)p_buildComponent_1_, p_buildComponent_2_, p_buildComponent_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
        }

        public boolean addComponentParts(IWorld p_addComponentParts_1_, Random p_addComponentParts_2_, MutableBoundingBox p_addComponentParts_3_, ChunkPos p_addComponentParts_4_) {
            if (this.averageGroundLvl < 0) {
                this.averageGroundLvl = this.getAverageGroundLevel(p_addComponentParts_1_, p_addComponentParts_3_);
                if (this.averageGroundLvl < 0) {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 3, 0);
            }

            IBlockState lvt_5_1_ = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState lvt_6_1_ = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 0, 1, 4, 12, 4, lvt_5_1_, Blocks.WATER.getDefaultState(), false);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 2, 12, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 3, 12, 2, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 2, 12, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, Blocks.AIR.getDefaultState(), 3, 12, 3, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 1, 13, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 1, 14, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 4, 13, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 4, 14, 1, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 1, 13, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 1, 14, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 4, 13, 4, p_addComponentParts_3_);
            this.setBlockState(p_addComponentParts_1_, lvt_6_1_, 4, 14, 4, p_addComponentParts_3_);
            this.fillWithBlocks(p_addComponentParts_1_, p_addComponentParts_3_, 1, 15, 1, 4, 15, 4, lvt_5_1_, lvt_5_1_, false);

            for(int lvt_7_1_ = 0; lvt_7_1_ <= 5; ++lvt_7_1_) {
                for(int lvt_8_1_ = 0; lvt_8_1_ <= 5; ++lvt_8_1_) {
                    if (lvt_8_1_ == 0 || lvt_8_1_ == 5 || lvt_7_1_ == 0 || lvt_7_1_ == 5) {
                        this.setBlockState(p_addComponentParts_1_, lvt_5_1_, lvt_8_1_, 11, lvt_7_1_, p_addComponentParts_3_);
                        this.clearCurrentPositionBlocksUpwards(p_addComponentParts_1_, lvt_8_1_, 12, lvt_7_1_, p_addComponentParts_3_);
                    }
                }
            }

            return true;
        }
    }

    abstract static class Village extends StructurePiece {
        protected int averageGroundLvl = -1;
        private int villagersSpawned;
        protected VillagePieces.Type structureType;
        protected boolean isZombieInfested;

        public Village() {
        }

        protected Village(VillagePieces.Start p_i2107_1_, int p_i2107_2_) {
            super(p_i2107_2_);
            if (p_i2107_1_ != null) {
                this.structureType = p_i2107_1_.structureType;
                this.isZombieInfested = p_i2107_1_.isZombieInfested;
            }

        }

        protected void writeStructureToNBT(NBTTagCompound p_writeStructureToNBT_1_) {
            p_writeStructureToNBT_1_.setInteger("HPos", this.averageGroundLvl);
            p_writeStructureToNBT_1_.setInteger("VCount", this.villagersSpawned);
            p_writeStructureToNBT_1_.setByte("Type", (byte)this.structureType.func_202604_a());
            p_writeStructureToNBT_1_.setBoolean("Zombie", this.isZombieInfested);
        }

        protected void readStructureFromNBT(NBTTagCompound p_readStructureFromNBT_1_, TemplateManager p_readStructureFromNBT_2_) {
            this.averageGroundLvl = p_readStructureFromNBT_1_.getInteger("HPos");
            this.villagersSpawned = p_readStructureFromNBT_1_.getInteger("VCount");
            this.structureType = VillagePieces.Type.func_202603_a(p_readStructureFromNBT_1_.getByte("Type"));
            if (p_readStructureFromNBT_1_.getBoolean("Desert")) {
                this.structureType = VillagePieces.Type.SANDSTONE;
            }

            this.isZombieInfested = p_readStructureFromNBT_1_.getBoolean("Zombie");
        }

        @Nullable
        protected StructurePiece getNextComponentNN(VillagePieces.Start p_getNextComponentNN_1_, List<StructurePiece> p_getNextComponentNN_2_, Random p_getNextComponentNN_3_, int p_getNextComponentNN_4_, int p_getNextComponentNN_5_) {
            EnumFacing lvt_6_1_ = this.getCoordBaseMode();
            if (lvt_6_1_ != null) {
                switch(EnumFacing.values()[lvt_6_1_.ordinal()]) {
                    case NORTH:
                default:
                    return VillagePieces.generateAndAddComponent(p_getNextComponentNN_1_, p_getNextComponentNN_2_, p_getNextComponentNN_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_getNextComponentNN_4_, this.boundingBox.minZ + p_getNextComponentNN_5_, EnumFacing.WEST, this.getComponentType());
                    case SOUTH:
                    return VillagePieces.generateAndAddComponent(p_getNextComponentNN_1_, p_getNextComponentNN_2_, p_getNextComponentNN_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_getNextComponentNN_4_, this.boundingBox.minZ + p_getNextComponentNN_5_, EnumFacing.WEST, this.getComponentType());
                    case EAST:
                    return VillagePieces.generateAndAddComponent(p_getNextComponentNN_1_, p_getNextComponentNN_2_, p_getNextComponentNN_3_, this.boundingBox.minX + p_getNextComponentNN_5_, this.boundingBox.minY + p_getNextComponentNN_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    case WEST:
                    return VillagePieces.generateAndAddComponent(p_getNextComponentNN_1_, p_getNextComponentNN_2_, p_getNextComponentNN_3_, this.boundingBox.minX + p_getNextComponentNN_5_, this.boundingBox.minY + p_getNextComponentNN_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            } else {
                return null;
            }
        }

        @Nullable
        protected StructurePiece getNextComponentPP(VillagePieces.Start p_getNextComponentPP_1_, List<StructurePiece> p_getNextComponentPP_2_, Random p_getNextComponentPP_3_, int p_getNextComponentPP_4_, int p_getNextComponentPP_5_) {
            EnumFacing lvt_6_1_ = this.getCoordBaseMode();
            if (lvt_6_1_ != null) {
                switch(EnumFacing.values()[lvt_6_1_.ordinal()]) {
                    case NORTH:
                default:
                    return VillagePieces.generateAndAddComponent(p_getNextComponentPP_1_, p_getNextComponentPP_2_, p_getNextComponentPP_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_getNextComponentPP_4_, this.boundingBox.minZ + p_getNextComponentPP_5_, EnumFacing.EAST, this.getComponentType());
                    case SOUTH:
                    return VillagePieces.generateAndAddComponent(p_getNextComponentPP_1_, p_getNextComponentPP_2_, p_getNextComponentPP_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_getNextComponentPP_4_, this.boundingBox.minZ + p_getNextComponentPP_5_, EnumFacing.EAST, this.getComponentType());
                    case EAST:
                    return VillagePieces.generateAndAddComponent(p_getNextComponentPP_1_, p_getNextComponentPP_2_, p_getNextComponentPP_3_, this.boundingBox.minX + p_getNextComponentPP_5_, this.boundingBox.minY + p_getNextComponentPP_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    case WEST:
                    return VillagePieces.generateAndAddComponent(p_getNextComponentPP_1_, p_getNextComponentPP_2_, p_getNextComponentPP_3_, this.boundingBox.minX + p_getNextComponentPP_5_, this.boundingBox.minY + p_getNextComponentPP_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            } else {
                return null;
            }
        }

        protected int getAverageGroundLevel(IWorld p_getAverageGroundLevel_1_, MutableBoundingBox p_getAverageGroundLevel_2_) {
            int lvt_3_1_ = 0;
            int lvt_4_1_ = 0;
            MutableBlockPos lvt_5_1_ = new MutableBlockPos();

            for(int lvt_6_1_ = this.boundingBox.minZ; lvt_6_1_ <= this.boundingBox.maxZ; ++lvt_6_1_) {
                for(int lvt_7_1_ = this.boundingBox.minX; lvt_7_1_ <= this.boundingBox.maxX; ++lvt_7_1_) {
                    lvt_5_1_.setPos(lvt_7_1_, 64, lvt_6_1_);
                    if (p_getAverageGroundLevel_2_.isVecInside(lvt_5_1_)) {
                        lvt_3_1_ += p_getAverageGroundLevel_1_.getTopBlock(net.minecraft.world.gen.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, lvt_5_1_).getY();
                        ++lvt_4_1_;
                    }
                }
            }

            if (lvt_4_1_ == 0) {
                return -1;
            } else {
                return lvt_3_1_ / lvt_4_1_;
            }
        }

        protected static boolean canVillageGoDeeper(MutableBoundingBox p_canVillageGoDeeper_0_) {
            return p_canVillageGoDeeper_0_ != null && p_canVillageGoDeeper_0_.minY > 10;
        }

        protected void spawnVillagers(IWorld p_spawnVillagers_1_, MutableBoundingBox p_spawnVillagers_2_, int p_spawnVillagers_3_, int p_spawnVillagers_4_, int p_spawnVillagers_5_, int p_spawnVillagers_6_) {
            if (this.villagersSpawned < p_spawnVillagers_6_) {
                for(int lvt_7_1_ = this.villagersSpawned; lvt_7_1_ < p_spawnVillagers_6_; ++lvt_7_1_) {
                    int lvt_8_1_ = this.getXWithOffset(p_spawnVillagers_3_ + lvt_7_1_, p_spawnVillagers_5_);
                    int lvt_9_1_ = this.getYWithOffset(p_spawnVillagers_4_);
                    int lvt_10_1_ = this.getZWithOffset(p_spawnVillagers_3_ + lvt_7_1_, p_spawnVillagers_5_);
                    if (!p_spawnVillagers_2_.isVecInside(new BlockPos(lvt_8_1_, lvt_9_1_, lvt_10_1_))) {
                        break;
                    }

                    ++this.villagersSpawned;
                    if (this.isZombieInfested) {
                        EntityZombieVillager lvt_11_1_ = new EntityZombieVillager(p_spawnVillagers_1_.getWorld());
                        lvt_11_1_.setLocationAndAngles((double)lvt_8_1_ + 0.5D, (double)lvt_9_1_, (double)lvt_10_1_ + 0.5D, 0.0F, 0.0F);
                        lvt_11_1_.func_204210_a(p_spawnVillagers_1_.getDifficultyForLocation(new BlockPos(lvt_11_1_)), null, null);
                        lvt_11_1_.setProfession(this.chooseProfession(lvt_7_1_, 0));
                        lvt_11_1_.enablePersistence();
                        p_spawnVillagers_1_.spawnEntity(lvt_11_1_);
                    } else {
                        EntityVillager lvt_11_2_ = new EntityVillager(p_spawnVillagers_1_.getWorld());
                        lvt_11_2_.setLocationAndAngles((double)lvt_8_1_ + 0.5D, (double)lvt_9_1_, (double)lvt_10_1_ + 0.5D, 0.0F, 0.0F);
                        lvt_11_2_.setProfession(this.chooseProfession(lvt_7_1_, p_spawnVillagers_1_.getRandom().nextInt(6)));
                        lvt_11_2_.finalizeMobSpawn(p_spawnVillagers_1_.getDifficultyForLocation(new BlockPos(lvt_11_2_)), null, null, false);
                        p_spawnVillagers_1_.spawnEntity(lvt_11_2_);
                    }
                }

            }
        }

        protected int chooseProfession(int p_chooseProfession_1_, int p_chooseProfession_2_) {
            return p_chooseProfession_2_;
        }

        protected IBlockState getBiomeSpecificBlockState(IBlockState p_getBiomeSpecificBlockState_1_) {
            Block lvt_2_1_ = p_getBiomeSpecificBlockState_1_.getBlock();
            if (this.structureType == VillagePieces.Type.SANDSTONE) {
                if (lvt_2_1_.isTagged(BlockTags.LOGS) || lvt_2_1_ == Blocks.COBBLESTONE) {
                    return Blocks.SANDSTONE.getDefaultState();
                }

                if (lvt_2_1_.isTagged(BlockTags.PLANKS)) {
                    return Blocks.CUT_SANDSTONE.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_STAIRS) {
                    return Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.COBBLESTONE_STAIRS) {
                    return Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.GRAVEL) {
                    return Blocks.SANDSTONE.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_PRESSURE_PLATE) {
                    return Blocks.BIRCH_PRESSURE_PLATE.getDefaultState();
                }
            } else if (this.structureType == VillagePieces.Type.SPRUCE) {
                if (lvt_2_1_.isTagged(BlockTags.LOGS)) {
                    return Blocks.SPRUCE_LOG.getDefaultState().withProperty(BlockLog.AXIS, p_getBiomeSpecificBlockState_1_.getValue(BlockLog.AXIS));
                }

                if (lvt_2_1_.isTagged(BlockTags.PLANKS)) {
                    return Blocks.SPRUCE_PLANKS.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_STAIRS) {
                    return Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.OAK_FENCE) {
                    return Blocks.SPRUCE_FENCE.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_PRESSURE_PLATE) {
                    return Blocks.SPRUCE_PRESSURE_PLATE.getDefaultState();
                }
            } else if (this.structureType == VillagePieces.Type.ACACIA) {
                if (lvt_2_1_.isTagged(BlockTags.LOGS)) {
                    return Blocks.ACACIA_LOG.getDefaultState().withProperty(BlockLog.AXIS, p_getBiomeSpecificBlockState_1_.getValue(BlockLog.AXIS));
                }

                if (lvt_2_1_.isTagged(BlockTags.PLANKS)) {
                    return Blocks.ACACIA_PLANKS.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_STAIRS) {
                    return Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.COBBLESTONE) {
                    return Blocks.ACACIA_LOG.getDefaultState().withProperty(BlockLog.AXIS, Axis.Y);
                }

                if (lvt_2_1_ == Blocks.OAK_FENCE) {
                    return Blocks.ACACIA_FENCE.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_PRESSURE_PLATE) {
                    return Blocks.ACACIA_PRESSURE_PLATE.getDefaultState();
                }
            } else if (this.structureType == Type.RED_SANDSTONE) {
                if (lvt_2_1_.isTagged(BlockTags.LOGS) || lvt_2_1_ == Blocks.COBBLESTONE) {
                    return Blocks.RED_SANDSTONE.getDefaultState();
                }

                if (lvt_2_1_.isTagged(BlockTags.PLANKS)) {
                    return Blocks.CUT_RED_SANDSTONE.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_STAIRS) {
                    return Blocks.RED_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.COBBLESTONE_STAIRS) {
                    return Blocks.RED_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.GRAVEL) {
                    return Blocks.RED_SANDSTONE.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_PRESSURE_PLATE) {
                    return Blocks.ACACIA_PRESSURE_PLATE.getDefaultState();
                }
            } else if (this.structureType == Type.BROWN_MUSHROOM) {
                if (lvt_2_1_.isTagged(BlockTags.LOGS) || lvt_2_1_ == Blocks.COBBLESTONE) {
                    return Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().withProperty(BlockHugeMushroom.field_196459_a, true);
                }

                if (lvt_2_1_.isTagged(BlockTags.PLANKS)) {
                    return Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_STAIRS) {
                    return Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.COBBLESTONE_STAIRS) {
                    return Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.GRAVEL) {
                    return Blocks.GRAVEL.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_PRESSURE_PLATE) {
                    return Blocks.SPRUCE_PRESSURE_PLATE.getDefaultState();
                }
            } else if (this.structureType == Type.RED_MUSHROOM) {
                if (lvt_2_1_.isTagged(BlockTags.LOGS) || lvt_2_1_ == Blocks.COBBLESTONE) {
                    return Blocks.RED_MUSHROOM_BLOCK.getDefaultState().withProperty(BlockHugeMushroom.field_196459_a, true);
                }

                if (lvt_2_1_.isTagged(BlockTags.PLANKS)) {
                    return Blocks.RED_MUSHROOM_BLOCK.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_STAIRS) {
                    return Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.COBBLESTONE_STAIRS) {
                    return Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, p_getBiomeSpecificBlockState_1_.getValue(BlockStairs.FACING));
                }

                if (lvt_2_1_ == Blocks.GRAVEL) {
                    return Blocks.GRAVEL.getDefaultState();
                }

                if (lvt_2_1_ == Blocks.OAK_PRESSURE_PLATE) {
                    return Blocks.ACACIA_PRESSURE_PLATE.getDefaultState();
                }
            }

            return p_getBiomeSpecificBlockState_1_;
        }

        protected BlockDoor biomeDoor() {
            if (this.structureType == VillagePieces.Type.ACACIA) {
                return (BlockDoor)Blocks.ACACIA_DOOR;
            } else {
                return this.structureType == VillagePieces.Type.SPRUCE ? (BlockDoor)Blocks.SPRUCE_DOOR : (BlockDoor)Blocks.OAK_DOOR;
            }
        }

        protected void createVillageDoor(IWorld p_createVillageDoor_1_, MutableBoundingBox p_createVillageDoor_2_, Random p_createVillageDoor_3_, int p_createVillageDoor_4_, int p_createVillageDoor_5_, int p_createVillageDoor_6_, EnumFacing p_createVillageDoor_7_) {
            if (!this.isZombieInfested) {
                this.generateDoor(p_createVillageDoor_1_, p_createVillageDoor_2_, p_createVillageDoor_3_, p_createVillageDoor_4_, p_createVillageDoor_5_, p_createVillageDoor_6_, EnumFacing.NORTH, this.biomeDoor());
            }

        }

        protected void placeTorch(IWorld p_placeTorch_1_, EnumFacing p_placeTorch_2_, int p_placeTorch_3_, int p_placeTorch_4_, int p_placeTorch_5_, MutableBoundingBox p_placeTorch_6_) {
            if (!this.isZombieInfested) {
                this.setBlockState(p_placeTorch_1_, Blocks.WALL_TORCH.getDefaultState().withProperty(BlockTorchWall.field_196532_a, p_placeTorch_2_), p_placeTorch_3_, p_placeTorch_4_, p_placeTorch_5_, p_placeTorch_6_);
            }

        }

        protected void replaceAirAndLiquidDownwards(IWorld p_replaceAirAndLiquidDownwards_1_, IBlockState p_replaceAirAndLiquidDownwards_2_, int p_replaceAirAndLiquidDownwards_3_, int p_replaceAirAndLiquidDownwards_4_, int p_replaceAirAndLiquidDownwards_5_, MutableBoundingBox p_replaceAirAndLiquidDownwards_6_) {
            IBlockState lvt_7_1_ = this.getBiomeSpecificBlockState(p_replaceAirAndLiquidDownwards_2_);
            super.replaceAirAndLiquidDownwards(p_replaceAirAndLiquidDownwards_1_, lvt_7_1_, p_replaceAirAndLiquidDownwards_3_, p_replaceAirAndLiquidDownwards_4_, p_replaceAirAndLiquidDownwards_5_, p_replaceAirAndLiquidDownwards_6_);
        }

        protected void func_202579_a(VillagePieces.Type p_202579_1_) {
            this.structureType = p_202579_1_;
        }
    }

    public static class PieceWeight {
        public Class<? extends VillagePieces.Village> villagePieceClass;
        public final int villagePieceWeight;
        public int villagePiecesSpawned;
        public int villagePiecesLimit;

        public PieceWeight(Class<? extends VillagePieces.Village> p_i2098_1_, int p_i2098_2_, int p_i2098_3_) {
            this.villagePieceClass = p_i2098_1_;
            this.villagePieceWeight = p_i2098_2_;
            this.villagePiecesLimit = p_i2098_3_;
        }

        public boolean canSpawnMoreVillagePiecesOfType(int p_canSpawnMoreVillagePiecesOfType_1_) {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }

        public boolean canSpawnMoreVillagePieces() {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }
    }

    public enum Type {
        OAK(0),
        SANDSTONE(1),
        ACACIA(2),
        SPRUCE(3),
        BASALT(4),
        RED_SANDSTONE(5),
        RED_MUSHROOM(6),
        BROWN_MUSHROOM(7);

        private final int field_202605_e;

        Type(int p_i48768_3_) {
            this.field_202605_e = p_i48768_3_;
        }

        public int func_202604_a() {
            return this.field_202605_e;
        }

        public static VillagePieces.Type func_202603_a(int p_202603_0_) {
            VillagePieces.Type[] lvt_1_1_ = values();
            return p_202603_0_ >= 0 && p_202603_0_ < lvt_1_1_.length ? lvt_1_1_[p_202603_0_] : OAK;
        }
    }
}
