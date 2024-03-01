package com.oukhali99.commandmaster.model.command;

import java.util.LinkedList;
import java.util.List;

class CommandStorage {

    private List<Command> commandList;

    CommandStorage() {
        commandList = new LinkedList<>();
    }

    void addCommand(Command command) {
        commandList.add(command);
    }

    List<Command> getCommands() {
        return new LinkedList<>(commandList);
    }

    void removeCommand(Command command) {
        commandList.remove(command);
    }
}
