package me.ohvalsgod.fivedollacheats.event.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.ohvalsgod.fivedollacheats.event.Event;

@Getter
@Setter
@AllArgsConstructor
public class PreMotionUpdateEvent extends Event {

    private float yaw, pitch;
    private boolean onGround, sneaking;
    private double x, y, z;

}
