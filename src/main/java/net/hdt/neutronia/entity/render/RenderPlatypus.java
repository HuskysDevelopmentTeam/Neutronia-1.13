package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityPlatypus;
import net.hdt.neutronia.entity.render.model.ModelPlatypus;
import net.hdt.neutronia.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPlatypus extends RenderLiving<EntityPlatypus> {

    public static final ResourceLocation PLATYPUS_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/platypus.png");
    private static final EntityPlatypus platypus = new EntityPlatypus(Minecraft.getMinecraft().world);

    public RenderPlatypus(RenderManager manager) {
        super(manager, new ModelPlatypus(), 0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPlatypus entity) {
        return PLATYPUS_TEXTURE;
    }
}
