package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityAxodile;
import net.hdt.neutronia.entity.render.model.ModelAxodile;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAxodile extends RenderLiving<EntityAxodile> {

    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(Reference.MOD_ID + ":textures/entity/axodile_3.png");

    public RenderAxodile(RenderManager manager) {
        super(manager, new ModelAxodile(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAxodile entity) {
        return RESOURCE_LOCATION;
    }
}
