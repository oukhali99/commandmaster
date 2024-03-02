package com.oukhali99.commandmaster.model.command;

import com.oukhali99.commandmaster.model.command.argument.CommandArgument;
import com.oukhali99.commandmaster.model.command.argument.TextCommandArgument;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Command {

    @Getter
    private String name;

    @Getter
    private String value;

    private List<CommandArgument> argumentList;

    @Getter
    @Setter
    private File workingDirectory;

    public Command(String name, String value) {
        this.name = name;
        this.value = value;
        argumentList = new LinkedList<>();
        workingDirectory = new File("");
    }

    public void addArgument(CommandArgument argument) {
        argumentList.add(argument);
    }

    public List<CommandArgument> getArguments() {
        return new LinkedList<>(argumentList);
    }

    public String getCombinedValue() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(value);
        for (CommandArgument argument : argumentList) {
            stringBuilder.append(" ");
            stringBuilder.append(argument.getStringValue());
        }

        return stringBuilder.toString();
    }

    public void removeArgument(TextCommandArgument textCommandArgument) {
        argumentList.remove(textCommandArgument);
    }

    public int execute() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd.exe", "/c", "start", "powershell.exe", "-NoExit", "-Command", getCombinedValue()
            );
            processBuilder.directory(workingDirectory);
            Process process = processBuilder.start();

            return process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
