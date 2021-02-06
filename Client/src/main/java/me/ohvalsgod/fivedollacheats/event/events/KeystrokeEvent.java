package me.ohvalsgod.fivedollacheats.event.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.ohvalsgod.fivedollacheats.event.Event;

@Getter
@AllArgsConstructor
public class KeystrokeEvent extends Event {

    private int key;

}
