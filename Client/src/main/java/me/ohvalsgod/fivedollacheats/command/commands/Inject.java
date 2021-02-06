package me.ohvalsgod.fivedollacheats.command.commands;

import me.ohvalsgod.fivedollacheats.FiveDolla;
import me.ohvalsgod.fivedollacheats.command.Command;

import java.util.Arrays;
import java.util.List;

public class Inject extends Command {

    @Override
    public List<String> getAliases() {
        return Arrays.asList(".inject", ".injection");
    }

    @Override
    public String getDescription() {
        return "Temporary command to inject code from main class into memory.";
    }

    @Override
    public String getSyntax() {
        return ".inject";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        long start = System.currentTimeMillis();
        if (FiveDolla.getInstance().inject()) {
            FiveDolla.show("§aCompleted in " + (System.currentTimeMillis() - start) + "ms");
        } else {
            FiveDolla.show("Nothing to inject.");
        }
    }

}
