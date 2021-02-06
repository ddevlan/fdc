package me.ohvalsgod.fivedollacheats;

import lombok.Getter;
import me.ohvalsgod.fivedollacheats.command.CommandManager;
import me.ohvalsgod.fivedollacheats.command.commands.Help;
import me.ohvalsgod.fivedollacheats.event.EventHandler;
import me.ohvalsgod.fivedollacheats.event.EventManager;
import me.ohvalsgod.fivedollacheats.module.modules.ModuleManager;
import me.ohvalsgod.fivedollacheats.module.modules.movement.Flight;
import me.ohvalsgod.fivedollacheats.module.modules.movement.Speed;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.opengl.Display;

@Getter
public class FiveDolla {

    public static final Minecraft MINECRAFT = Minecraft.getMinecraft();

    private static FiveDolla instance;

    //  INFO
    private final String name = "Five Dolla Cheats", nameAbrv = "FDC", description = "Cheap cheats that work.";
    private final String[] authors = new String[] {"ddylan"};
    private final String releaseVersion = "(release 0.1)", buildVersion = "(build ${project.version})"; //todo: implement w github
    private final boolean devVersion = true;

    //  EVENTS
    private EventManager eventManager;
    private EventHandler eventHandler;
    private CommandManager commandManager;

    //  MODS
    private ModuleManager moduleManager;

    public FiveDolla() {
        instance = this;

        //  EVENTS
        eventManager = new EventManager();
        eventHandler = new EventHandler();

        //  MODS
        moduleManager = new ModuleManager();

        //  COMMANDS
        commandManager = new CommandManager();
    }

    public static FiveDolla getInstance() {
        return instance;
    }

    public void start() {
        //  INFO
        System.out.println("[" + name + "] Client starting... initialising... ");
        //TODO: version check

        //  EVENTS
        Display.setTitle("Registering events...");
        eventManager.register(eventHandler);

        //  MODS
        moduleManager.init();

        //  COMMANDS
        commandManager.setup();

        Display.setTitle(nameAbrv + " " + (devVersion ? buildVersion : releaseVersion));
    }

    public void stop() {
        //  MODS
        moduleManager.close();

        //  EVENTS
        eventManager.unregister(eventHandler);

        instance = null;
    }

    public boolean inject() {

        if (true) {
            return true;
        }

        return false;
    }

    public static void show(String s){
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§aFDC §7>§r  " + s));
    }

}
