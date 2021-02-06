package me.ohvalsgod.fivedollacheats.module.modules.movement;

import me.ohvalsgod.fivedollacheats.event.EventTarget;
import me.ohvalsgod.fivedollacheats.event.events.PreMotionUpdateEvent;
import me.ohvalsgod.fivedollacheats.module.AbstractModule;
import me.ohvalsgod.fivedollacheats.module.category.ModuleCategory;
import org.lwjgl.input.Keyboard;

public class Flight extends AbstractModule {

    public Flight() {
        super("Flight", "§bFlight", "Issa bird, issa plane, no nigga it's ohvals!", "v0.0.1", "Vanilla", Keyboard.KEY_B, false, ModuleCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.thePlayer.capabilities.setFlySpeed(1);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.capabilities.allowFlying = false;
        mc.thePlayer.capabilities.isFlying = false;
        mc.thePlayer.capabilities.setFlySpeed(1);
    }

    @EventTarget
    public void motionUpdate(PreMotionUpdateEvent event) {
        mc.thePlayer.capabilities.allowFlying = true;
        mc.thePlayer.capabilities.isFlying = true;
    }

}
