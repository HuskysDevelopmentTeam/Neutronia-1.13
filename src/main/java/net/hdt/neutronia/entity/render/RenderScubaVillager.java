package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityScubaVillager;
import net.hdt.neutronia.entity.render.model.ModelScubaVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static net.hdt.neutronia.util.Reference.MOD_ID;

public class RenderScubaVillager extends RenderBiped<EntityScubaVillager> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/villagers/professions/scuba_diver.png");

    public RenderScubaVillager(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelScubaVillager(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityScubaVillager entity) {
        return MUMMY_TEXTURE;
    }

}
