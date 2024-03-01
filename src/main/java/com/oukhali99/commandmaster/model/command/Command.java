package com.oukhali99.commandmaster.model.command;

import lombok.Getter;

@Getter
public class Command {

    private String name;

    private String value;

    public Command(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
