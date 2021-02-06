package me.ohvalsgod.fivedollacheats.command.commands;

import me.ohvalsgod.fivedollacheats.FiveDolla;
import me.ohvalsgod.fivedollacheats.command.Command;

import java.util.Arrays;
import java.util.List;

public class Help extends Command {

    @Override
    public List<String> getAliases() {
        return Arrays.asList(".help", ".commands");
    }

    @Override
    public String getDescription() {
        return "General help command.";
    }

    @Override
    public String getSyntax() {
        return ".help";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        FiveDolla.show("§aFIVE DOLLA CHEATS (help 1/1)");
        for (Command c : FiveDolla.getInstance().getCommandManager().getCommands()) {
            FiveDolla.show(c.getAliases().get(0) + " - "  +c.getDescription());
        }
    }

}
