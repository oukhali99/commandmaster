package com.oukhali99.commandmaster.model.command;

import com.oukhali99.commandmaster.model.command.argument.CommandArgument;
import com.oukhali99.commandmaster.model.command.argument.TextCommandArgument;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class Command {

    @Getter
    private String name;

    @Getter
    private String value;

    private List<CommandArgument> argumentList;

    public Command(String name, String value) {
        this.name = name;
        this.value = value;
        argumentList = new LinkedList<>();
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
}
