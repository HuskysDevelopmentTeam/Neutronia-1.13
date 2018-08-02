package net.hdt.neutronia.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumTallNetherPlantTypes implements IStringSerializable {

    TALL_GRASS(0, "tall_nether_grass"),
    TALL_NETHER_MUSHROOM(1, "tall_nether_mushroom");

    private String name;
    private int ID;

    EnumTallNetherPlantTypes(int ID, String name) {
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
