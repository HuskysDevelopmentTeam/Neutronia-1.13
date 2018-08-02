package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityWellWisher;
import net.hdt.neutronia.entity.render.model.ModelTheWellWisher;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWellWisher extends RenderLiving<EntityWellWisher> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/well_wisher.png");

    public RenderWellWisher(RenderManager manager) {
        super(manager, new ModelTheWellWisher(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWellWisher entity) {
        return SCORP_TEXTURE;
    }
}
