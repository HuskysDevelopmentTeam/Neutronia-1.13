package net.hdt.neutronia.entity.render.layer;

import net.hdt.neutronia.entity.EntityAnchored;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.ModelZombie;
import net.minecraft.util.ResourceLocation;

public class LayerDiverSuit implements LayerRenderer<EntityAnchored> {

    private static final ResourceLocation STRAY_CLOTHES_TEXTURES = new ResourceLocation("neutronia:textures/entity/sea/diver/diver_suit.png");
    private final RenderLivingBase<?> renderer;
    private final ModelZombie layerModel = new ModelZombie(0.25F, false);

    public LayerDiverSuit(RenderLivingBase<?> p_i47183_1_) {
        this.renderer = p_i47183_1_;
    }

    public void render(EntityAnchored entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.layerModel.setModelAttributes(this.renderer.getMainModel());
        this.layerModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.renderer.bindTexture(STRAY_CLOTHES_TEXTURES);
        this.layerModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public boolean shouldCombineTextures() {
        return false;
    }

}