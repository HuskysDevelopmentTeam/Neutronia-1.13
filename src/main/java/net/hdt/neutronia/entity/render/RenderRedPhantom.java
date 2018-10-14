package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityBloodPhantom;
import net.hdt.neutronia.entity.render.model.ModelPhantom;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRedPhantom extends RenderLiving<EntityBloodPhantom> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/phantom/red_phantom.png");

    public RenderRedPhantom(RenderManager manager) {
        super(manager, new ModelPhantom(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBloodPhantom entity) {
        return SCORP_TEXTURE;
    }

    protected void preRenderCallback(EntityBloodPhantom p_preRenderCallback_1_, float p_preRenderCallback_2_) {
        int lvt_3_1_ = p_preRenderCallback_1_.func_203032_dq();
        float lvt_4_1_ = 1.0F + 0.15F * (float)lvt_3_1_;
        GlStateManager.scalef(lvt_4_1_, lvt_4_1_, lvt_4_1_);
        GlStateManager.translatef(0.0F, 1.3125F, 0.1875F);
    }

    protected void applyRotations(EntityBloodPhantom p_applyRotations_1_, float p_applyRotations_2_, float p_applyRotations_3_, float p_applyRotations_4_) {
        super.applyRotations(p_applyRotations_1_, p_applyRotations_2_, p_applyRotations_3_, p_applyRotations_4_);
        GlStateManager.rotatef(p_applyRotations_1_.rotationPitch, 1.0F, 0.0F, 0.0F);
    }
}
