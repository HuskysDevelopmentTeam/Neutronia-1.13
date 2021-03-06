package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityPharaohGolem;
import net.hdt.neutronia.entity.render.model.ModelPharaohGolem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static net.hdt.neutronia.util.Reference.MOD_ID;

public class RenderPharaoGolem extends RenderLiving<EntityPharaohGolem> {

    private static final ResourceLocation IRON_GOLEM_TEXTURES = new ResourceLocation(MOD_ID,"textures/entity/golems/pharao_golem.png");

    public RenderPharaoGolem(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPharaohGolem(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPharaohGolem entity)
    {
        return IRON_GOLEM_TEXTURES;
    }


    protected void applyRotations(EntityPharaohGolem entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

        if ((double)entityLiving.limbSwingAmount >= 0.01D)
        {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            GlStateManager.rotatef(6.5F * f2, 0.0F, 0.0F, 1.0F);
        }
    }

}
