package me.ohvalsgod.fivedollacheats.command;

import java.util.List;

public abstract class Command {

    public abstract List<String> getAliases();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract void onCommand(String command, String[] args) throws Exception;

}
