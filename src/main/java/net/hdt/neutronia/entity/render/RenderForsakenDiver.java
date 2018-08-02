package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityForsakenDiver;
import net.hdt.neutronia.entity.render.layer.LayerForsakenDiverHelmet;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelZombie;
import net.minecraft.util.ResourceLocation;

public class RenderForsakenDiver extends RenderBiped<EntityForsakenDiver> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation("neutronia:textures/entity/sea/diver/forsaken_diver.png");

    public RenderForsakenDiver(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5F);
        this.addLayer(new LayerForsakenDiverHelmet(this));
    }

    @Override
    protected boolean setBrightness(EntityForsakenDiver entitylivingbaseIn, float partialTicks, boolean combineTextures) {
        return true;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityForsakenDiver entity) {
        return MUMMY_TEXTURE;
    }

}
