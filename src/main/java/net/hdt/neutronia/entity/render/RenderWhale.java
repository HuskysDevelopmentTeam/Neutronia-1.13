package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityWhale;
import net.hdt.neutronia.entity.render.model.ModelWhale;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWhale extends RenderLiving<EntityWhale> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/ocean_creatures/whale.png");

    public RenderWhale(RenderManager manager) {
        super(manager, new ModelWhale(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWhale entity) {
        return SCORP_TEXTURE;
    }

    @Override
    protected void applyRotations(EntityWhale entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
