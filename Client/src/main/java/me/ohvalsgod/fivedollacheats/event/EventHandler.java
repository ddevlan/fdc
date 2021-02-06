package me.ohvalsgod.fivedollacheats.event;

import me.ohvalsgod.fivedollacheats.FiveDolla;
import me.ohvalsgod.fivedollacheats.event.events.Event2D;
import me.ohvalsgod.fivedollacheats.event.events.EventUpdate;
import me.ohvalsgod.fivedollacheats.event.events.KeystrokeEvent;
import me.ohvalsgod.fivedollacheats.module.AbstractModule;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.Comparator;

public class EventHandler {

    @EventTarget
    public void onKeyPress(KeystrokeEvent event) {
        AbstractModule module = FiveDolla.getInstance().getModuleManager().getModuleByKey(event.getKey());

        if (module != null) {
            module.toggle();
        }
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        FiveDolla.getInstance().getModuleManager().getActiveModules().sort(Comparator.comparingInt(abstractModule -> abstractModule.getDisplayName().length() + abstractModule.getMode().length()));
    }

    @EventTarget
    public void showOverlay(Event2D event) {
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("$5 CHEATS", 2 , 2, Color.GREEN.getRGB());
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("FIVEDOLLACHEATS.COM", 2 , 12, Color.LIGHT_GRAY.getRGB());

        int ly = 6;
        for (AbstractModule module : FiveDolla.getInstance().getModuleManager().getActiveModules()) {
            String display = module.getDisplayName() + "§8 - §7" + module.getMode();
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(display, (event.getWidth() - 8) - Minecraft.getMinecraft().fontRendererObj.getStringWidth(display), ly, Color.RED.getRGB());
            ly = ly + 10;
        }
    }

}
