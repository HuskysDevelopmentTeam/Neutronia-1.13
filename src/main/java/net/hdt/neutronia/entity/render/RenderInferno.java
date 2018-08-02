package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityInferno;
import net.hdt.neutronia.entity.render.layer.LayerInfernoActiveGlow;
import net.hdt.neutronia.entity.render.model.ModelInferno;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInferno extends RenderLiving<EntityInferno> {
    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/inferno_active.png");

    public RenderInferno(RenderManager manager) {
        super(manager, new ModelInferno(), 0.5F);
        this.addLayer(new LayerInfernoActiveGlow(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInferno entity) {
        return SCORP_TEXTURE;
    }

    @Override
    protected void applyRotations(EntityInferno entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
