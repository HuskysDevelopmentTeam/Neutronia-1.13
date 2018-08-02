package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelLion - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelLion extends ModelBase {
    public ModelRenderer body1;
    public ModelRenderer head;
    public ModelRenderer armright;
    public ModelRenderer armleft;
    public ModelRenderer legleft;
    public ModelRenderer body2;
    public ModelRenderer legright;
    public ModelRenderer snout;
    public ModelRenderer earright;
    public ModelRenderer earleft;
    public ModelRenderer tail;
    public ModelRenderer mane;

    public ModelLion() {
        this.textureWidth = 70;
        this.textureHeight = 50;
        this.earright = new ModelRenderer(this, 40, 18);
        this.earright.setRotationPoint(-0.5F, 9.0F, -5.0F);
        this.earright.addBox(-3.0F, -3.0F, -6.0F, 3, 3, 1, 0.0F);
        this.armleft = new ModelRenderer(this, 0, 13);
        this.armleft.setRotationPoint(1.5F, 15.0F, -7.5F);
        this.armleft.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
        this.body1 = new ModelRenderer(this, 34, 0);
        this.body1.setRotationPoint(-4.0F, 9.0F, -8.0F);
        this.body1.addBox(0.0F, 0.0F, 0.0F, 8, 8, 10, 0.0F);
        this.body2 = new ModelRenderer(this, 42, 18);
        this.body2.setRotationPoint(-4.0F, 9.0F, 2.0F);
        this.body2.addBox(0.0F, 0.0F, 0.0F, 8, 7, 6, 0.0F);
        this.mane = new ModelRenderer(this, 34, 31);
        this.mane.setRotationPoint(-5.0F, 6.0F, -10.0F);
        this.mane.addBox(0.0F, 0.0F, 0.0F, 10, 10, 7, 0.0F);
        this.armright = new ModelRenderer(this, 0, 13);
        this.armright.setRotationPoint(-4.5F, 15.0F, -7.5F);
        this.armright.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 11.0F, -7.0F);
        this.head.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 6, 0.0F);
        this.legright = new ModelRenderer(this, 12, 12);
        this.legright.setRotationPoint(-4.5F, 14.0F, 4.5F);
        this.legright.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.snout = new ModelRenderer(this, 32, 0);
        this.snout.setRotationPoint(1.5F, 14.0F, -9.0F);
        this.snout.addBox(-3.0F, -3.0F, -6.0F, 3, 3, 3, 0.0F);
        this.legleft = new ModelRenderer(this, 12, 12);
        this.legleft.setRotationPoint(1.5F, 14.0F, 4.5F);
        this.legleft.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.tail = new ModelRenderer(this, 0, 25);
        this.tail.setRotationPoint(-0.5F, 10.0F, 7.0F);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.earleft = new ModelRenderer(this, 40, 18);
        this.earleft.setRotationPoint(3.5F, 9.0F, -5.0F);
        this.earleft.addBox(-3.0F, -3.0F, -6.0F, 3, 3, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.earright.render(f5);
        this.armleft.render(f5);
        this.body1.render(f5);
        this.body2.render(f5);
        this.mane.render(f5);
        this.armright.render(f5);
        this.head.render(f5);
        this.legright.render(f5);
        this.snout.render(f5);
        this.legleft.render(f5);
        this.tail.render(f5);
        this.earleft.render(f5);
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
