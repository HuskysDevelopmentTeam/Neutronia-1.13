package net.hdt.neutronia.mixin.hook.client;

import net.hdt.neutronia.client.gui.GuiModMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public abstract class MixinMainScreen extends GuiScreen {

    @Inject(method = "drawScreen", at = @At("RETURN"))
    private void onDrawScreen(CallbackInfo info) {
        Minecraft minecraft = Minecraft.getMinecraft();
        GuiMainMenu mainMenu = new GuiMainMenu();
        GuiButton modButton = new GuiButton(2, this.width / 2 - 100, 50, I18n.format("menu.mods", new Object[0])) {
            public void func_194829_a(double p_194829_1_, double p_194829_3_) {
                minecraft.displayGuiScreen(new GuiModMenu(MixinMainScreen.this));
            }
        };
        this.addButton(modButton);
        mainMenu.drawString(minecraft.fontRenderer, "Test", 2, 40, -1);
    }

}
