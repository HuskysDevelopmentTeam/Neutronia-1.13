package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityFirefly;
import net.hdt.neutronia.entity.render.layer.LayerFireflyGlow;
import net.hdt.neutronia.entity.render.model.ModelFirefly;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static net.hdt.neutronia.util.Reference.MOD_ID;

public class RenderFirefly extends RenderLiving<EntityFirefly>
{
	private static final ResourceLocation FIREFLY_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/firefly.png");
	
	public ModelFirefly model;

    public RenderFirefly(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelFirefly(), 0F);
        this.model = new ModelFirefly();
        this.addLayer(new LayerFireflyGlow(this));
    }

    protected ResourceLocation getEntityTexture(EntityFirefly entity) {
    	return FIREFLY_TEXTURE;
    }
    
    @Override
	public void doRender(EntityFirefly firefly, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float)x, (float)y, (float)z);
        GlStateManager.rotatef(firefly.renderYawOffset, 0F, 1F, 0F);
        GlStateManager.scalef(firefly.getScale(), firefly.getScale(), firefly.getScale());
        bindTexture(FIREFLY_TEXTURE);
        
        GlStateManager.disableLighting();
        int combinedBrightness = firefly.getBrightnessForRender(); // Brightness, sort of. Code borrowed from spiders/endermen	
        int skyLightTimes16 = combinedBrightness % 65536;
        int blockLightTimes16 = combinedBrightness / 65536;
        OpenGlHelper.glMultiTexCoord2f(OpenGlHelper.GL_TEXTURE1, (float)skyLightTimes16, (float)blockLightTimes16);
        GlStateManager.enableLighting();
//        Minecraft.getInstance().entityRenderer.setupFogColor(true);
        model.render(firefly, 0F, 0F, 0F, 0F, 0F, 0.1F);
//        Minecraft.getInstance().entityRenderer.setupFogColor(false);
        this.setLightmap(firefly);
        GlStateManager.enableAlphaTest();
        
        GlStateManager.popMatrix();
        
        this.layerRenderers.get(0).render(firefly, 0F, 0F, partialTicks, firefly.ticksExisted, 0F, 0F, 1F);
    }

}