package net.hdt.neutronia.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumGlowingNetherBlocks implements IStringSerializable {

    FIERY_STONE(0, "fiery_stone"),
    VOLCANIC_GLOW_ROCK(1, "volcanic_glow_rock"),
    VOLCANIC_ROCK(2, "volcanic_rock"),
    HOT_COBBLESTONE(3, "hot_cobblestone");

    private static final EnumGlowingNetherBlocks[] META_LOOKUP = new EnumGlowingNetherBlocks[values().length];

    static {
        for (EnumGlowingNetherBlocks blockstone$enumtype : values()) {
            META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
        }
    }

    private final int meta;
    private final String name;

    EnumGlowingNetherBlocks(int p_i46384_3_, String p_i46384_5_) {
        this.meta = p_i46384_3_;
        this.name = p_i46384_5_;
    }

    public static EnumGlowingNetherBlocks byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public int getMetadata() {
        return this.meta;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

}
