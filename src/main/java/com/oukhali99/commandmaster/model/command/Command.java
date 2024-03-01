package com.oukhali99.commandmaster.model.command;

import lombok.Data;

@Data
public class Command {

    private String value;

    public Command(String value) {
        this.value = value;
    }

}
