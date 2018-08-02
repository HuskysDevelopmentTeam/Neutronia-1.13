package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntitySandDiverAquatic;
import net.hdt.neutronia.entity.render.layer.LayerSanddiverAquaticGlow;
import net.hdt.neutronia.entity.render.model.ModelSanddiver;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSandDiverAquatic extends RenderLiving<EntitySandDiverAquatic> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/sanddiver_aquatic.png");

    public RenderSandDiverAquatic(RenderManager manager) {
        super(manager, new ModelSanddiver(), 0.5F);
        this.addLayer(new LayerSanddiverAquaticGlow(this));
    }

    @Override
    public void doRender(EntitySandDiverAquatic entity, double x, double y, double z, float entityYaw, float partialTicks) {
//        /*GlStateManager.pushMatrix();
//        GlStateManager.enableAlpha();
//        GlStateManager.enableBlend();
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.2F);
//        super.doRender(entity, x, y, z, entityYaw, partialTicks);
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.2F);
//        GlStateManager.disableBlend();
//        GlStateManager.disableAlpha();
//        GlStateManager.popMatrix();*/
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySandDiverAquatic entity) {
        return SCORP_TEXTURE;
    }
}
