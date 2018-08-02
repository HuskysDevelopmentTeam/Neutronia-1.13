package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelOwl - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelOwl extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;
    public ModelRenderer shape1_8;
    public ModelRenderer shape1_9;

    public ModelOwl() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1_4 = new ModelRenderer(this, 6, 11);
        this.shape1_4.setRotationPoint(-3.0F, 23.0F, 3.0F);
        this.shape1_4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 0, 0.0F);
        this.shape1_6 = new ModelRenderer(this, 18, 0);
        this.shape1_6.setRotationPoint(2.0F, 17.0F, 1.0F);
        this.shape1_6.addBox(-2.5F, -4.0F, -2.0F, 1, 2, 1, 0.0F);
        this.shape1_8 = new ModelRenderer(this, 6, 11);
        this.shape1_8.setRotationPoint(0.0F, 23.0F, 3.0F);
        this.shape1_8.addBox(0.0F, 0.0F, 0.0F, 3, 1, 0, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 38, 0);
        this.shape1_2.setRotationPoint(-3.0F, 16.0F, 0.0F);
        this.shape1_2.addBox(-1.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.setRotateAngle(shape1_2, 0.27314402793711257F, 0.136659280431156F, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(0.0F, 15.0F, 2.0F);
        this.shape1_1.addBox(-2.5F, -4.0F, -2.0F, 5, 4, 4, 0.0F);
        this.shape1_5 = new ModelRenderer(this, 3, 8);
        this.shape1_5.setRotationPoint(-3.0F, 24.0F, 0.0F);
        this.shape1_5.addBox(0.0F, 0.0F, 0.0F, 3, 0, 3, 0.0F);
        this.shape1_7 = new ModelRenderer(this, 6, 12);
        this.shape1_7.setRotationPoint(0.0F, 23.0F, 4.0F);
        this.shape1_7.addBox(-3.0F, -4.0F, 0.0F, 6, 4, 0, 0.0F);
        this.setRotateAngle(shape1_7, -2.41309222380736F, 0.0F, 0.0F);
        this.shape1 = new ModelRenderer(this, 18, 0);
        this.shape1.setRotationPoint(-3.0F, 15.0F, 0.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 6, 8, 4, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 38, 11);
        this.shape1_3.setRotationPoint(3.0F, 16.0F, 0.0F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.setRotateAngle(shape1_3, 0.27314402793711257F, -0.136659280431156F, 0.0F);
        this.shape1_9 = new ModelRenderer(this, 3, 8);
        this.shape1_9.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.shape1_9.addBox(0.0F, 0.0F, 0.0F, 3, 0, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1_4.render(f5);
        this.shape1_6.render(f5);
        this.shape1_8.render(f5);
        this.shape1_2.render(f5);
        this.shape1_1.render(f5);
        this.shape1_5.render(f5);
        this.shape1_7.render(f5);
        this.shape1.render(f5);
        this.shape1_3.render(f5);
        this.shape1_9.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
