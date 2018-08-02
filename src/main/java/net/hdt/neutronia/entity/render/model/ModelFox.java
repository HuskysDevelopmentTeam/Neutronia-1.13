package net.hdt.neutronia.entity.render.model;

import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelFox - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelFox extends ModelBase {
    public ModelRenderer armleft;
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer legleft;
    public ModelRenderer legright;
    public ModelRenderer armright;
    public ModelRenderer tail;
    public ModelRenderer head;
    public ModelRenderer earright;
    public ModelRenderer earleft;
    public ModelRenderer snout;

    public ModelFox() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.armleft = new ModelRenderer(this, 0, 18);
        this.armleft.setRotationPoint(0.5F, 18.0F, -4.0F);
        this.armleft.addBox(0.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.tail = new ModelRenderer(this, 42, 17);
        this.tail.setRotationPoint(-1.5F, 15.0F, 5.0F);
        this.tail.addBox(0.0F, 0.0F, -1.0F, 3, 8, 3, 0.0F);
        this.snout = new ModelRenderer(this, 0, 10);
        this.snout.setRotationPoint(-0.5F, 15.5F, -7.0F);
        this.snout.addBox(-0.5F, 0.0F, -5.0F, 2, 3, 4, 0.0F);
        this.setRotateAngle(snout, 0.0F, -0.016406094968746697F, 0.0F);
        this.body2 = new ModelRenderer(this, 18, 14);
        this.body2.setRotationPoint(0.0F, 16.0F, 1.0F);
        this.body2.addBox(-3.0F, -2.0F, -3.0F, 6, 8, 6, 0.0F);
        this.setRotateAngle(body2, 1.5707963267948966F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(-1.0F, 15.5F, -7.0F);
        this.head.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.setRotateAngle(head, 0.0F, -0.016406094968746697F, 0.0F);
        this.body1 = new ModelRenderer(this, 21, 0);
        this.body1.setRotationPoint(-0.5F, 16.5F, -3.0F);
        this.body1.addBox(-3.0F, -3.0F, -3.0F, 7, 7, 7, 0.0F);
        this.setRotateAngle(body1, 1.5707963267948966F, 0.0F, 0.0F);
        this.legright = new ModelRenderer(this, 0, 18);
        this.legright.setRotationPoint(-2.5F, 18.0F, 5.0F);
        this.legright.addBox(0.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.earleft = new ModelRenderer(this, 16, 14);
        this.earleft.setRotationPoint(-1.0F, 15.5F, -7.0F);
        this.earleft.addBox(2.0F, -5.0F, 0.0F, 3, 3, 1, 0.0F);
        this.setRotateAngle(earleft, 0.0F, -0.016406094968746697F, 0.0F);
        this.legleft = new ModelRenderer(this, 0, 18);
        this.legleft.setRotationPoint(0.5F, 18.0F, 5.0F);
        this.legleft.addBox(0.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.earright = new ModelRenderer(this, 16, 14);
        this.earright.setRotationPoint(-2.0F, 15.5F, -7.0F);
        this.earright.addBox(-2.0F, -5.0F, 0.0F, 3, 3, 1, 0.0F);
        this.setRotateAngle(earright, 0.0F, -0.016406094968746697F, 0.0F);
        this.armright = new ModelRenderer(this, 0, 18);
        this.armright.setRotationPoint(-2.5F, 18.0F, -4.0F);
        this.armright.addBox(0.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.armleft.render(f5);
        this.tail.render(f5);
        this.snout.render(f5);
        this.body2.render(f5);
        this.head.render(f5);
        this.body1.render(f5);
        this.legright.render(f5);
        this.earleft.render(f5);
        this.legleft.render(f5);
        this.earright.render(f5);
        this.armright.render(f5);
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
