package me.ohvalsgod.fivedollacheats.command.commands;

import me.ohvalsgod.fivedollacheats.command.Command;

import java.util.Arrays;
import java.util.List;

public class VoteSpoof extends Command {

    @Override
    public List<String> getAliases() {
        return Arrays.asList(".votespoof");
    }

    @Override
    public String getDescription() {
        return "Vote Spoofer, enter desired server ID and username.";
    }

    @Override
    public String getSyntax() {
        return ".votespoof <server-id> <username>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        String serverId = args[0];
        String username = args[1];


    }

}
