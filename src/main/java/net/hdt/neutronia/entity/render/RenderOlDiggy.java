package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityOlDiggy;
import net.hdt.neutronia.entity.render.model.ModelOlDiggy;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderOlDiggy extends RenderLiving<EntityOlDiggy> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/ol_diggy.png");

    public RenderOlDiggy(RenderManager manager) {
        super(manager, new ModelOlDiggy(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityOlDiggy entity) {
        return SCORP_TEXTURE;
    }
}
