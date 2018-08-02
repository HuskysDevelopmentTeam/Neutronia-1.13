package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityNecromancer;
import net.hdt.neutronia.entity.render.model.ModelNecro;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static net.hdt.neutronia.util.Reference.MOD_ID;

public class RenderNecro extends RenderBiped<EntityNecromancer> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/villagers/professions/necromancer.png");

    public RenderNecro(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelNecro(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityNecromancer entity) {
        return MUMMY_TEXTURE;
    }

}
