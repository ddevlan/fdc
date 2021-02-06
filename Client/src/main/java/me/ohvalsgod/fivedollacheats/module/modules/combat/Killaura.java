package me.ohvalsgod.fivedollacheats.module.modules.combat;

import lombok.Getter;
import lombok.Setter;
import me.ohvalsgod.fivedollacheats.FiveDolla;
import me.ohvalsgod.fivedollacheats.event.EventTarget;
import me.ohvalsgod.fivedollacheats.event.events.EventUpdate;
import me.ohvalsgod.fivedollacheats.module.AbstractModule;
import me.ohvalsgod.fivedollacheats.module.category.ModuleCategory;
import me.ohvalsgod.fivedollacheats.util.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.input.Keyboard;

public class Killaura extends AbstractModule {

    @Getter @Setter private double reach = 6, cps = 5;
    @Getter @Setter private boolean invis = true, throughwalls = true;

    private long lastUpdate = System.currentTimeMillis();

    public Killaura() {
        super("Killaura", "§4Killaura", "Hit them niggas through the wall!", "v0.0.1", "Switch", Keyboard.KEY_F, false, ModuleCategory.COMBAT);
    }

    public void attack(Entity target) {
        final ItemStack currentItem = mc.thePlayer.inventory.getCurrentItem();

        final boolean wasSprinting = mc.thePlayer.isSprinting();
        final boolean wasSneaking = mc.thePlayer.isSneaking();
        final boolean wasBlocking = currentItem != null && currentItem.getItem().getItemUseAction(currentItem) == EnumAction.BLOCK &&
                mc.thePlayer.isBlocking();

        if (wasSprinting)
            mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
        if (wasSneaking)
            mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
        if (wasBlocking)
            mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));

        int oldDamage = 0;

        if (currentItem != null) {
            oldDamage = currentItem.getItemDamage();
        }

        float[] rotations = EntityUtils.getEntityRotations(target);
        float[] lastRotations = new float[] {mc.thePlayer.cameraYaw, mc.thePlayer.cameraPitch};

        mc.thePlayer.swingItem();
        mc.playerController.attackEntity(mc.thePlayer,  target);

        if (currentItem != null) {
            currentItem.setItemDamage(oldDamage);

            if (wasSprinting)
                mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
            if (wasSneaking)
                mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
            if (wasBlocking)
                mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(currentItem));
            mc.thePlayer.setSprinting(wasSprinting);
            mc.thePlayer.setSneaking(wasSneaking);
            if (wasBlocking)
                mc.thePlayer.setItemInUse(currentItem, currentItem.getMaxItemUseDuration());
        }

    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (System.currentTimeMillis() - lastUpdate > 1000/cps) {
            EntityLiving target = null;

            for (Entity entity : mc.thePlayer.getEntityWorld().getLoadedEntityList()) {
                if ((entity instanceof EntityPlayer || entity instanceof EntityLiving) && entity != mc.thePlayer) {
                    Entity target$ = (Entity) entity;

                    if (mc.thePlayer.getDistance(target$.posX, target$.posY, target$.posZ) < reach) {
                        if (mc.thePlayer.canEntityBeSeen(target$) || (!mc.thePlayer.canEntityBeSeen(target$) && throughwalls)) {
                            attack(target$);
                            lastUpdate = System.currentTimeMillis();

                            if (getMode().equals("TP")) {

                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
