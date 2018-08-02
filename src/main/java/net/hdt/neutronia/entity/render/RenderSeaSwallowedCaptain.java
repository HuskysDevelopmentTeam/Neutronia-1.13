package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntitySeaSwallowedCaptain;
import net.hdt.neutronia.entity.render.model.ModelSeaSwallowedCaptain;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSeaSwallowedCaptain extends RenderLiving<EntitySeaSwallowedCaptain> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/sea_swallowed_captain.png");

    public RenderSeaSwallowedCaptain(RenderManager manager) {
        super(manager, new ModelSeaSwallowedCaptain(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySeaSwallowedCaptain entity) {
        return SCORP_TEXTURE;
    }
}
