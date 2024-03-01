package com.oukhali99.commandmaster.model.command.argument;

public class TextCommandArgument extends BaseCommandArgument {

    private String option;

    private String value;

    public TextCommandArgument(String option) {
        this(option, null);
    }

    public TextCommandArgument(String option, String value) {
        this.option = option;
        this.value = value;
    }


    @Override
    public String getStringValue() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(option);
        if (value != null) {
            stringBuilder.append(" ");
            stringBuilder.append(value);
        }

        return stringBuilder.toString();
    }
}
