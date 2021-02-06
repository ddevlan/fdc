package me.ohvalsgod.fivedollacheats.module.modules.movement;

import me.ohvalsgod.fivedollacheats.event.EventTarget;
import me.ohvalsgod.fivedollacheats.event.events.PostMotionUpdateEvent;
import me.ohvalsgod.fivedollacheats.event.events.PreMotionUpdateEvent;
import me.ohvalsgod.fivedollacheats.module.AbstractModule;
import me.ohvalsgod.fivedollacheats.module.category.ModuleCategory;
import org.lwjgl.input.Keyboard;

public class Sprint extends AbstractModule {

    public Sprint() {
        super("Sprint", "§eSprint", "Fuckin run nigga!!", "v0.0.1", "Vanilla", Keyboard.KEY_Y, true, ModuleCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }



    @EventTarget
    public void onUpdate(PreMotionUpdateEvent event) {
        if (mc.thePlayer.prevPosX != mc.thePlayer.posX && mc.thePlayer.prevPosZ != mc.thePlayer.posZ && !mc.thePlayer.isSprinting()) {
            mc.thePlayer.setSprinting(true);
        }
    }

    @EventTarget
    public void onUpdate(PostMotionUpdateEvent event) {

    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.setSprinting(false);
    }



}
