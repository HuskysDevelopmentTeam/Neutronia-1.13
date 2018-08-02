//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.hdt.neutronia.world.gen.surface_builders;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Arrays;
import java.util.Random;

public class RedDesertSurfaceBuilder implements ISurfaceBuilder<SurfaceBuilderConfig> {
    private static final IBlockState WHITE_TERRACOTTA;
    private static final IBlockState ORANGE_TERRACOTTA;
    private static final IBlockState HARDENED_CLAY;
    private static final IBlockState YELLOW_TERRACOTTA;
    private static final IBlockState BROWN_TERRACOTTA;
    private static final IBlockState RED_TERRACOTTA;
    private static final IBlockState LIGHT_GRAY_TERRACOTTA;
    protected IBlockState[] field_202615_a;
    protected long field_202616_b;
    protected NoiseGeneratorPerlin field_202617_c;
    protected NoiseGeneratorPerlin field_202618_d;
    protected NoiseGeneratorPerlin field_202619_e;

    public RedDesertSurfaceBuilder() {
    }

    public void buildSurface(Random p_buildSurface_1_, IChunk p_buildSurface_2_, Biome p_buildSurface_3_, int p_buildSurface_4_, int p_buildSurface_5_, int p_buildSurface_6_, double p_buildSurface_7_, IBlockState p_buildSurface_9_, IBlockState p_buildSurface_10_, int p_buildSurface_11_, long p_buildSurface_12_, SurfaceBuilderConfig p_buildSurface_14_) {
        int lvt_15_1_ = p_buildSurface_4_ & 15;
        int lvt_16_1_ = p_buildSurface_5_ & 15;
        IBlockState lvt_17_1_ = WHITE_TERRACOTTA;
        IBlockState lvt_18_1_ = p_buildSurface_3_.getSurfaceBuilderConfig().getMiddle();
        int lvt_19_1_ = (int)(p_buildSurface_7_ / 3.0D + 3.0D + p_buildSurface_1_.nextDouble() * 0.25D);
        boolean lvt_20_1_ = Math.cos(p_buildSurface_7_ / 3.0D * 3.141592653589793D) > 0.0D;
        int lvt_21_1_ = -1;
        boolean lvt_22_1_ = false;
        int lvt_23_1_ = 0;
        MutableBlockPos lvt_24_1_ = new MutableBlockPos();

        for(int lvt_25_1_ = p_buildSurface_6_; lvt_25_1_ >= 0; --lvt_25_1_) {
            if (lvt_23_1_ < 15) {
                lvt_24_1_.setPos(lvt_15_1_, lvt_25_1_, lvt_16_1_);
                IBlockState lvt_26_1_ = p_buildSurface_2_.getBlockState(lvt_24_1_);
                if (lvt_26_1_.isAir()) {
                    lvt_21_1_ = -1;
                } else if (lvt_26_1_.getBlock() == p_buildSurface_9_.getBlock()) {
                    if (lvt_21_1_ == -1) {
                        lvt_22_1_ = false;
                        if (lvt_19_1_ <= 0) {
                            lvt_17_1_ = Blocks.AIR.getDefaultState();
                            lvt_18_1_ = p_buildSurface_9_;
                        } else if (lvt_25_1_ >= p_buildSurface_11_ - 4 && lvt_25_1_ <= p_buildSurface_11_ + 1) {
                            lvt_17_1_ = WHITE_TERRACOTTA;
                            lvt_18_1_ = p_buildSurface_3_.getSurfaceBuilderConfig().getMiddle();
                        }

                        if (lvt_25_1_ < p_buildSurface_11_ && (lvt_17_1_ == null || lvt_17_1_.isAir())) {
                            lvt_17_1_ = p_buildSurface_10_;
                        }

                        lvt_21_1_ = lvt_19_1_ + Math.max(0, lvt_25_1_ - p_buildSurface_11_);
                        if (lvt_25_1_ >= p_buildSurface_11_ - 1) {
                            if (lvt_25_1_ > p_buildSurface_11_ + 3 + lvt_19_1_) {
                                IBlockState lvt_27_3_;
                                if (lvt_25_1_ >= 64 && lvt_25_1_ <= 127) {
                                    if (lvt_20_1_) {
                                        lvt_27_3_ = HARDENED_CLAY;
                                    } else {
                                        lvt_27_3_ = this.func_202614_a(p_buildSurface_4_, lvt_25_1_, p_buildSurface_5_);
                                    }
                                } else {
                                    lvt_27_3_ = ORANGE_TERRACOTTA;
                                }

                                p_buildSurface_2_.setBlockState(lvt_24_1_, lvt_27_3_, false);
                            } else {
                                p_buildSurface_2_.setBlockState(lvt_24_1_, p_buildSurface_3_.getSurfaceBuilderConfig().getTop(), false);
                                lvt_22_1_ = true;
                            }
                        } else {
                            p_buildSurface_2_.setBlockState(lvt_24_1_, lvt_18_1_, false);
                            Block lvt_27_4_ = lvt_18_1_.getBlock();
                            if (lvt_27_4_ == Blocks.WHITE_TERRACOTTA || lvt_27_4_ == Blocks.ORANGE_TERRACOTTA || lvt_27_4_ == Blocks.MAGENTA_TERRACOTTA || lvt_27_4_ == Blocks.LIGHT_BLUE_TERRACOTTA || lvt_27_4_ == Blocks.YELLOW_TERRACOTTA || lvt_27_4_ == Blocks.LIME_TERRACOTTA || lvt_27_4_ == Blocks.PINK_TERRACOTTA || lvt_27_4_ == Blocks.GRAY_TERRACOTTA || lvt_27_4_ == Blocks.LIGHT_GRAY_TERRACOTTA || lvt_27_4_ == Blocks.CYAN_TERRACOTTA || lvt_27_4_ == Blocks.PURPLE_TERRACOTTA || lvt_27_4_ == Blocks.BLUE_TERRACOTTA || lvt_27_4_ == Blocks.BROWN_TERRACOTTA || lvt_27_4_ == Blocks.GREEN_TERRACOTTA || lvt_27_4_ == Blocks.RED_TERRACOTTA || lvt_27_4_ == Blocks.BLACK_TERRACOTTA) {
                                p_buildSurface_2_.setBlockState(lvt_24_1_, ORANGE_TERRACOTTA, false);
                            }
                        }
                    } else if (lvt_21_1_ > 0) {
                        --lvt_21_1_;
                        if (lvt_22_1_) {
                            p_buildSurface_2_.setBlockState(lvt_24_1_, ORANGE_TERRACOTTA, false);
                        } else {
                            p_buildSurface_2_.setBlockState(lvt_24_1_, this.func_202614_a(p_buildSurface_4_, lvt_25_1_, p_buildSurface_5_), false);
                        }
                    }

                    ++lvt_23_1_;
                }
            }
        }
    }

    public void setSeed(long p_setSeed_1_) {
        if (this.field_202616_b != p_setSeed_1_ || this.field_202615_a == null) {
            this.func_202613_a(p_setSeed_1_);
        }

        if (this.field_202616_b != p_setSeed_1_ || this.field_202617_c == null || this.field_202618_d == null) {
            Random lvt_3_1_ = new SharedSeedRandom(p_setSeed_1_);
            this.field_202617_c = new NoiseGeneratorPerlin(lvt_3_1_, 4);
            this.field_202618_d = new NoiseGeneratorPerlin(lvt_3_1_, 1);
        }

        this.field_202616_b = p_setSeed_1_;
    }

    protected void func_202613_a(long p_202613_1_) {
        this.field_202615_a = new IBlockState[64];
        Arrays.fill(this.field_202615_a, HARDENED_CLAY);
        Random lvt_3_1_ = new SharedSeedRandom(p_202613_1_);
        this.field_202619_e = new NoiseGeneratorPerlin(lvt_3_1_, 1);

        int lvt_4_2_;
        for(lvt_4_2_ = 0; lvt_4_2_ < 64; ++lvt_4_2_) {
            lvt_4_2_ += lvt_3_1_.nextInt(5) + 1;
            if (lvt_4_2_ < 64) {
                this.field_202615_a[lvt_4_2_] = ORANGE_TERRACOTTA;
            }
        }

        lvt_4_2_ = lvt_3_1_.nextInt(4) + 2;

        int lvt_5_2_;
        int lvt_6_3_;
        int lvt_7_4_;
        int lvt_8_4_;
        for(lvt_5_2_ = 0; lvt_5_2_ < lvt_4_2_; ++lvt_5_2_) {
            lvt_6_3_ = lvt_3_1_.nextInt(3) + 1;
            lvt_7_4_ = lvt_3_1_.nextInt(64);

            for(lvt_8_4_ = 0; lvt_7_4_ + lvt_8_4_ < 64 && lvt_8_4_ < lvt_6_3_; ++lvt_8_4_) {
                this.field_202615_a[lvt_7_4_ + lvt_8_4_] = YELLOW_TERRACOTTA;
            }
        }

        lvt_5_2_ = lvt_3_1_.nextInt(4) + 2;

        int lvt_9_2_;
        for(lvt_6_3_ = 0; lvt_6_3_ < lvt_5_2_; ++lvt_6_3_) {
            lvt_7_4_ = lvt_3_1_.nextInt(3) + 2;
            lvt_8_4_ = lvt_3_1_.nextInt(64);

            for(lvt_9_2_ = 0; lvt_8_4_ + lvt_9_2_ < 64 && lvt_9_2_ < lvt_7_4_; ++lvt_9_2_) {
                this.field_202615_a[lvt_8_4_ + lvt_9_2_] = BROWN_TERRACOTTA;
            }
        }

        lvt_6_3_ = lvt_3_1_.nextInt(4) + 2;

        for(lvt_7_4_ = 0; lvt_7_4_ < lvt_6_3_; ++lvt_7_4_) {
            lvt_8_4_ = lvt_3_1_.nextInt(3) + 1;
            lvt_9_2_ = lvt_3_1_.nextInt(64);

            for(int lvt_10_1_ = 0; lvt_9_2_ + lvt_10_1_ < 64 && lvt_10_1_ < lvt_8_4_; ++lvt_10_1_) {
                this.field_202615_a[lvt_9_2_ + lvt_10_1_] = RED_TERRACOTTA;
            }
        }

        lvt_7_4_ = lvt_3_1_.nextInt(3) + 3;
        lvt_8_4_ = 0;

        for(lvt_9_2_ = 0; lvt_9_2_ < lvt_7_4_; ++lvt_9_2_) {
            lvt_8_4_ += lvt_3_1_.nextInt(16) + 4;

            for(int lvt_11_1_ = 0; lvt_8_4_ + lvt_11_1_ < 64 && lvt_11_1_ < 1; ++lvt_11_1_) {
                this.field_202615_a[lvt_8_4_ + lvt_11_1_] = WHITE_TERRACOTTA;
                if (lvt_8_4_ + lvt_11_1_ > 1 && lvt_3_1_.nextBoolean()) {
                    this.field_202615_a[lvt_8_4_ + lvt_11_1_ - 1] = LIGHT_GRAY_TERRACOTTA;
                }

                if (lvt_8_4_ + lvt_11_1_ < 63 && lvt_3_1_.nextBoolean()) {
                    this.field_202615_a[lvt_8_4_ + lvt_11_1_ + 1] = LIGHT_GRAY_TERRACOTTA;
                }
            }
        }

    }

    protected IBlockState func_202614_a(int p_202614_1_, int p_202614_2_, int p_202614_3_) {
        int lvt_4_1_ = (int)Math.round(this.field_202619_e.getValue((double)p_202614_1_ / 512.0D, (double)p_202614_3_ / 512.0D) * 2.0D);
        return this.field_202615_a[(p_202614_2_ + lvt_4_1_ + 64) % 64];
    }

    static {
        WHITE_TERRACOTTA = Blocks.RED_SAND.getDefaultState();
        ORANGE_TERRACOTTA = Blocks.RED_SAND.getDefaultState();
        HARDENED_CLAY = Blocks.RED_SAND.getDefaultState();
        YELLOW_TERRACOTTA = Blocks.RED_SAND.getDefaultState();
        BROWN_TERRACOTTA = Blocks.RED_SANDSTONE.getDefaultState();
        RED_TERRACOTTA = Blocks.RED_SANDSTONE.getDefaultState();
        LIGHT_GRAY_TERRACOTTA = Blocks.RED_SANDSTONE.getDefaultState();
    }
}
