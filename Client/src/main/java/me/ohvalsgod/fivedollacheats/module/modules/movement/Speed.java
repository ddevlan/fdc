package me.ohvalsgod.fivedollacheats.module.modules.movement;

import me.ohvalsgod.fivedollacheats.event.EventTarget;
import me.ohvalsgod.fivedollacheats.event.events.PreMotionUpdateEvent;
import me.ohvalsgod.fivedollacheats.module.AbstractModule;
import me.ohvalsgod.fivedollacheats.module.category.ModuleCategory;
import me.ohvalsgod.fivedollacheats.util.BlockUtils;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class Speed extends AbstractModule {

    private boolean hasJumped;

    public Speed() {
        super("Speed", "§aSpeed", "run nigga", "v0.0.1", "FDC", Keyboard.KEY_C, false, ModuleCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventTarget
    public void onPreMotion(PreMotionUpdateEvent event) {
        double offset = (mc.thePlayer.rotationYaw + 90 + (mc.thePlayer.moveForward > 0 ? (mc.thePlayer.moveStrafing > 0 ? -45 : mc.thePlayer.moveStrafing < 0 ? 45 : 0) : mc.thePlayer.moveForward < 0 ? 180 + (mc.thePlayer.moveStrafing > 0 ? 45 : mc.thePlayer.moveStrafing < 0 ? -45 : 0) : (mc.thePlayer.moveStrafing > 0 ? -90 : mc.thePlayer.moveStrafing < 0 ? 90 : 0))) * Math.PI / 180;

        double x = Math.cos(offset) * 0.25F;
        double z = Math.sin(offset) * 0.25F;
        if (canSpeed(mc.thePlayer.onGround)) {
            mc.thePlayer.motionX += x;
            mc.thePlayer.motionY = 0.0175F;
            mc.thePlayer.motionZ += z;

            if (mc.thePlayer.movementInput.moveStrafe != 0) {
                mc.thePlayer.motionX *= 0.975F;
                mc.thePlayer.motionZ *= 0.975F;
            }

            mc.getTimer().timerSpeed = 1.05F;
            hasJumped = true;
        } else {
            mc.getTimer().timerSpeed = 1.0F;
        }

        if (hasJumped && !mc.thePlayer.onGround && !mc.thePlayer.isOnLadder()) {
            mc.thePlayer.motionY = -0.5F;
            hasJumped = false;
        }
    }

    private boolean canSpeed(boolean groundCheck) {
        boolean moving = mc.thePlayer.movementInput.moveForward != 0;
        boolean strafing = mc.thePlayer.movementInput.moveStrafe != 0;

        moving = moving || strafing;

        boolean sneaking = mc.thePlayer.isSneaking();
        boolean collided = mc.thePlayer.isCollidedHorizontally;

        boolean inLiquid = BlockUtils.isInLiquid(mc.thePlayer);
        boolean onLiquid = BlockUtils.isOnLiquid(mc.thePlayer);
        boolean onIce = BlockUtils.isOnIce(mc.thePlayer);

        return moving && !sneaking && !collided && !inLiquid && !onLiquid && !onIce && groundCheck;
    }

}
