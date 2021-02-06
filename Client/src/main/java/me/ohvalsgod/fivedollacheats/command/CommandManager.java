package me.ohvalsgod.fivedollacheats.command;

import lombok.Getter;
import me.ohvalsgod.fivedollacheats.FiveDolla;
import me.ohvalsgod.fivedollacheats.command.commands.Inject;

import java.util.HashSet;
import java.util.Set;

@Getter
public class CommandManager {

    private Set<Command> commands;

    public CommandManager() {
        commands = new HashSet<>();
    }

    public void setup() {
        commands.add(new Inject());
    }

    public void callCommand(String input) {
        String[] split = input.split(" ");
        String command = split[0];
        String args = input.substring(command.length()).trim();

        for (Command c: getCommands()){
            if (c.getAliases().contains(command.toLowerCase())) {
                try {
                    c.onCommand(args, args.split(" "));
                } catch(Exception e){
                    FiveDolla.show("§4Error: Invalid syntax");
                    FiveDolla.show("§cUsage: " + c.getSyntax());
                }
                return;
            }
        }
        FiveDolla.show("§cCommand not found. Type '.help' to list all commands.");
    }

}
