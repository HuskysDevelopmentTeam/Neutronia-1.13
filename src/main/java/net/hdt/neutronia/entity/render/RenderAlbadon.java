package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityAlbadon;
import net.hdt.neutronia.entity.render.model.ModelAlbadon;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAlbadon extends RenderLiving<EntityAlbadon> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/albadon.png");

    public RenderAlbadon(RenderManager manager) {
        super(manager, new ModelAlbadon(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAlbadon entity) {
        return SCORP_TEXTURE;
    }
}
