package net.hdt.neutronia.client.gui;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiModMenu extends GuiScreen {

    public GuiModMenu(GuiScreen screen) {

    }

    @Override
    protected void initGui() {

    }

    @Override
    public void render(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        Tessellator lvt_2_1_ = Tessellator.getInstance();
        BufferBuilder lvt_3_1_ = lvt_2_1_.getBuffer();
        this.mc.getTextureManager().bindTexture(new ResourceLocation("minecraft:textures/block/stone.png"));
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        lvt_3_1_.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        lvt_3_1_.pos(0.0D, (double)this.height, 0.0D).tex(0.0D, (double)((float)this.height / 32.0F + (float)0)).color(64, 64, 64, 255).endVertex();
        lvt_3_1_.pos((double)this.width, (double)this.height, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)this.height / 32.0F + (float)0)).color(64, 64, 64, 255).endVertex();
        lvt_3_1_.pos((double)this.width, 0.0D, 0.0D).tex((double)((float)this.width / 32.0F), (double)0).color(64, 64, 64, 255).endVertex();
        lvt_3_1_.pos(0.0D, 0.0D, 0.0D).tex(0.0D, (double)0).color(64, 64, 64, 255).endVertex();
        lvt_2_1_.draw();
        lvt_2_1_.getBuffer().reset();

        this.mc.getTextureManager().bindTexture(new ResourceLocation("minecraft:textures/block/dirt.png"));
        lvt_3_1_.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        lvt_3_1_.pos(0.0D, (double)this.height - 20, 0.0D).tex(0.0D, (double)((float)this.height / 64.0F + (float)0)).color(64, 64, 64, 255).endVertex();
        lvt_3_1_.pos((double)this.width, (double)this.height - 20, 0.0D).tex((double)((float)this.width / 64.0F), (double)((float)this.height / 64.0F + (float)0)).color(64, 64, 64, 255).endVertex();
        lvt_3_1_.pos((double)this.width, 20.0D, 0.0D).tex((double)((float)this.width / 64.0F), (double)0).color(64, 64, 64, 255).endVertex();
        lvt_3_1_.pos(0.0D, 20.0D, 0.0D).tex(0.0D, (double)0).color(64, 64, 64, 255).endVertex();
        lvt_2_1_.draw();
        lvt_2_1_.getBuffer().reset();

        drawHorizontalLine(0, this.width, 20, 0xFFFFFF);

        drawHorizontalLine(0, this.width, this.height - 20, 0xFFFFFF);

        drawVerticalLine(0, this.height - 20, 20, 0xFFFFFF);

        drawVerticalLine(this.width - 1, this.height - 20, 20, 0xFFFFFF);

        drawVerticalLine(100, this.height - 20, 20, 0xFFFFFF);

        /*for(int i = 0; i < RiftLoader.instance.getMods().size(); i++) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation("minecraft:textures/block/oak_planks.png"));
            lvt_3_1_.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            lvt_3_1_.pos(0.0D, (double)this.height - 20, 0.0D).tex(0.0D, (double)((float)this.height / 32.0F + (float)0)).color(64, 64, 64, 255).endVertex();
            lvt_3_1_.pos((double)100, (double)this.height - 20, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)this.height / 32.0F + (float)0)).color(64, 64, 64, 255).endVertex();
            lvt_3_1_.pos((double)100, 20.0D, 0.0D).tex((double)((float)this.width / 32.0F), (double)0).color(64, 64, 64, 255).endVertex();
            lvt_3_1_.pos(0.0D, 20.0D, 0.0D).tex(0.0D, (double)0).color(64, 64, 64, 255).endVertex();
            lvt_2_1_.draw();
            drawHorizontalLine(0, 100, 20, 0xFFFFFF);
            drawHorizontalLine(0, 100, 50 * i + 50, 0xFFFFFF);
            drawVerticalLine(0, this.height - 20, 20, 0xFFFFFF);
        }*/

        this.drawString(mc.fontRenderer, "Mod Settings", this.width / 2 - 35, 6, 0xFFFFFF);
    }
}
