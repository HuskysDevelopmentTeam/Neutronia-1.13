//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.hdt.neutronia.util;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.util.List;

public final class TooltipHandler {
    public TooltipHandler() {
    }

    public static void tooltipIfShift(List<String> tooltip, Runnable r) {
        if (GuiScreen.isShiftKeyDown()) {
            r.run();
        } else {
            addToTooltip(tooltip, "arl.misc.shiftForInfo");
        }

    }

    public static void addToTooltip(List<String> tooltip, String s, Object... format) {
        s = local(s).replaceAll("&", "§");
        Object[] formatVals = new String[format.length];

        for(int i = 0; i < format.length; ++i) {
            formatVals[i] = local(format[i].toString()).replaceAll("&", "§");
        }

        if (formatVals.length > 0) {
            s = String.format(s, formatVals);
        }

        tooltip.add(s);
    }

    public static String local(String s) {
        return I18n.format(s);
    }

}
