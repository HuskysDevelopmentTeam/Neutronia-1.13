package net.hdt.neutronia.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumNetherPlantTypes implements IStringSerializable {

    GRASS(0, "nether_grass"),
    SOUL_SPROUTS(1, "soul_sprouts"),
    NETHER_MUSHROOM(2, "nether_mushroom");

    private String name;
    private int ID;

    EnumNetherPlantTypes(int ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

}
