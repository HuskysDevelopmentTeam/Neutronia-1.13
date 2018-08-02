package net.hdt.neutronia.world.gen.generators;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.storage.loot.LootTableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Random;

public class DungeonsFeature extends Feature<NoFeatureConfig> {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final EntityType<?>[] SPAWNERTYPES;
    private static final IBlockState field_205189_c;

    public DungeonsFeature() {
    }

    public boolean generate(IWorld p_generate_1_, IChunkGenerator<? extends IChunkGenSettings> p_generate_2_, Random p_generate_3_, BlockPos p_generate_4_, NoFeatureConfig p_generate_5_) {
        int lvt_7_1_ = p_generate_3_.nextInt(2) + 2;
        int lvt_8_1_ = -lvt_7_1_ - 1;
        int lvt_9_1_ = lvt_7_1_ + 1;
        int lvt_12_1_ = p_generate_3_.nextInt(2) + 2;
        int lvt_13_1_ = -lvt_12_1_ - 1;
        int lvt_14_1_ = lvt_12_1_ + 1;
        int lvt_15_1_ = 0;

        int lvt_16_2_;
        int lvt_17_2_;
        int lvt_18_2_;
        BlockPos lvt_19_2_;
        for(lvt_16_2_ = lvt_8_1_; lvt_16_2_ <= lvt_9_1_; ++lvt_16_2_) {
            for(lvt_17_2_ = -1; lvt_17_2_ <= 4; ++lvt_17_2_) {
                for(lvt_18_2_ = lvt_13_1_; lvt_18_2_ <= lvt_14_1_; ++lvt_18_2_) {
                    lvt_19_2_ = p_generate_4_.add(lvt_16_2_, lvt_17_2_, lvt_18_2_);
                    Material lvt_20_1_ = p_generate_1_.getBlockState(lvt_19_2_).getMaterial();
                    boolean lvt_21_1_ = lvt_20_1_.isSolid();
                    if (lvt_17_2_ == -1 && !lvt_21_1_) {
                        return false;
                    }

                    if (lvt_17_2_ == 4 && !lvt_21_1_) {
                        return false;
                    }

                    if ((lvt_16_2_ == lvt_8_1_ || lvt_16_2_ == lvt_9_1_ || lvt_18_2_ == lvt_13_1_ || lvt_18_2_ == lvt_14_1_) && lvt_17_2_ == 0 && p_generate_1_.isAirBlock(lvt_19_2_) && p_generate_1_.isAirBlock(lvt_19_2_.up())) {
                        ++lvt_15_1_;
                    }
                }
            }
        }

        if (lvt_15_1_ >= 1 && lvt_15_1_ <= 5) {
            for(lvt_16_2_ = lvt_8_1_; lvt_16_2_ <= lvt_9_1_; ++lvt_16_2_) {
                for(lvt_17_2_ = 3; lvt_17_2_ >= -1; --lvt_17_2_) {
                    for(lvt_18_2_ = lvt_13_1_; lvt_18_2_ <= lvt_14_1_; ++lvt_18_2_) {
                        lvt_19_2_ = p_generate_4_.add(lvt_16_2_, lvt_17_2_, lvt_18_2_);
                        if (lvt_16_2_ != lvt_8_1_ && lvt_17_2_ != -1 && lvt_18_2_ != lvt_13_1_ && lvt_16_2_ != lvt_9_1_ && lvt_17_2_ != 4 && lvt_18_2_ != lvt_14_1_) {
                            if (p_generate_1_.getBlockState(lvt_19_2_).getBlock() != Blocks.CHEST) {
                                p_generate_1_.setBlockState(lvt_19_2_, field_205189_c, 2);
                            }
                        } else if (lvt_19_2_.getY() >= 0 && !p_generate_1_.getBlockState(lvt_19_2_.down()).getMaterial().isSolid()) {
                            p_generate_1_.setBlockState(lvt_19_2_, field_205189_c, 2);
                        } else if (p_generate_1_.getBlockState(lvt_19_2_).getMaterial().isSolid() && p_generate_1_.getBlockState(lvt_19_2_).getBlock() != Blocks.CHEST) {
                            if (lvt_17_2_ == -1 && p_generate_3_.nextInt(4) != 0) {
                                p_generate_1_.setBlockState(lvt_19_2_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 2);
                            } else {
                                p_generate_1_.setBlockState(lvt_19_2_, Blocks.COBBLESTONE.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            for(lvt_16_2_ = 0; lvt_16_2_ < 2; ++lvt_16_2_) {
                for(lvt_17_2_ = 0; lvt_17_2_ < 3; ++lvt_17_2_) {
                    lvt_18_2_ = p_generate_4_.getX() + p_generate_3_.nextInt(lvt_7_1_ * 2 + 1) - lvt_7_1_;
                    int lvt_19_3_ = p_generate_4_.getY();
                    int lvt_20_2_ = p_generate_4_.getZ() + p_generate_3_.nextInt(lvt_12_1_ * 2 + 1) - lvt_12_1_;
                    BlockPos lvt_21_2_ = new BlockPos(lvt_18_2_, lvt_19_3_, lvt_20_2_);
                    if (p_generate_1_.isAirBlock(lvt_21_2_)) {
                        int lvt_22_1_ = 0;
                        Iterator var23 = Plane.HORIZONTAL.iterator();

                        while(var23.hasNext()) {
                            EnumFacing lvt_24_1_ = (EnumFacing)var23.next();
                            if (p_generate_1_.getBlockState(lvt_21_2_.offset(lvt_24_1_)).getMaterial().isSolid()) {
                                ++lvt_22_1_;
                            }
                        }

                        if (lvt_22_1_ == 1) {
                            p_generate_1_.setBlockState(lvt_21_2_, StructurePiece.func_197528_a(p_generate_1_, lvt_21_2_, Blocks.CHEST.getDefaultState()), 2);
                            TileEntityLockableLoot.func_195479_a(p_generate_1_, p_generate_3_, lvt_21_2_, LootTableList.CHESTS_SIMPLE_DUNGEON);
                            break;
                        }
                    }
                }
            }

            p_generate_1_.setBlockState(p_generate_4_, Blocks.MOB_SPAWNER.getDefaultState(), 2);
            TileEntity lvt_16_4_ = p_generate_1_.getTileEntity(p_generate_4_);
            if (lvt_16_4_ instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner)lvt_16_4_).getSpawnerBaseLogic().func_200876_a(this.func_201043_a(p_generate_3_));
            } else {
                LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", p_generate_4_.getX(), p_generate_4_.getY(), p_generate_4_.getZ());
            }

            return true;
        } else {
            return false;
        }
    }

    private EntityType<?> func_201043_a(Random p_201043_1_) {
        return SPAWNERTYPES[p_201043_1_.nextInt(SPAWNERTYPES.length)];
    }

    static {
        SPAWNERTYPES = new EntityType[]{EntityType.SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER};
        field_205189_c = Blocks.CAVE_AIR.getDefaultState();
    }
}
