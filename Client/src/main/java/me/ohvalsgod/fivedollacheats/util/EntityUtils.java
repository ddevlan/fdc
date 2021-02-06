package me.ohvalsgod.fivedollacheats.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;


public class EntityUtils {

    public static boolean isInFieldOfVision(EntityPlayerSP e1, EntityLiving e2){
        //save Entity 2's original rotation variables
        float rotationYawPrime = e2.rotationYaw;
        float rotationPitchPrime = e2.rotationPitch;
        //make Entity 2 directly face Entity 1
        e2.faceEntity(e1, 360F, 360F);
        //switch values of prime rotation variables with current rotation variables
        float f = e2.rotationYaw;
        float f1 = e2.rotationPitch;
        e2.rotationYaw = rotationYawPrime;
        e2.rotationPitch = rotationPitchPrime;
        rotationYawPrime = f;
        rotationPitchPrime = f1;
        //assuming field of vision consists of everything within X degrees from rotationYaw and Y degrees from rotationPitch, check if entity 2's current rotationYaw and rotationPitch within this X and Y range
        float X = 60F; //this is only a guess, I don't know the actual range
        float Y = 45F; //this is only a guess, I don't know the actual range
        float yawFOVMin = e2.rotationYaw - X >= 0F ? e2.rotationYaw - X : 360F + e2.rotationYaw - X;
        float yawFOVMax = e2.rotationYaw + X < 360F ? e2.rotationYaw + X : -360F + e2.rotationYaw + X;
        //NOTE: I dont recall the range of rotationPitch; 0 to 180?
        float pitchFOVMin = e2.rotationPitch - Y >= 0F ? e2.rotationPitch - Y : 180F + e2.rotationPitch - Y;
        float pitchFOVMax = e2.rotationPitch + Y < 180F ? e2.rotationPitch + Y : -180F + e2.rotationPitch + Y;
        if(rotationYawPrime >= yawFOVMin && rotationYawPrime <= yawFOVMax && rotationPitchPrime >= pitchFOVMin && rotationPitchPrime <= pitchFOVMax && e2.canEntityBeSeen(e1))
            return true;
        else return false;
    }

    public static float[] getEntityRotations(Entity target) {
        final double var4 = target.posX - Minecraft.getMinecraft().thePlayer.posX;
        final double var6 = target.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        final double var8 = target.posY + target.getEyeHeight() / 1.3 - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
        final double var14 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
        final float yaw = (float) (Math.atan2(var6, var4) * 180.0D / Math.PI) - 90.0F;
        final float pitch = (float) -(Math.atan2(var8, var14) * 180.0D / Math.PI);
        return new float[]{yaw, pitch};
    }

}
