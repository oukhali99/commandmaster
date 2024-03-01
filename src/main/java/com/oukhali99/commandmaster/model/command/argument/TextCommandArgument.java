package com.oukhali99.commandmaster.model.command.argument;

import com.oukhali99.commandmaster.config.FXMLLoaderConfig;
import com.oukhali99.commandmaster.uicontrollers.TextCommandArgumentViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Setter;

import java.io.IOException;

public class TextCommandArgument extends BaseCommandArgument {

    private String option;

    @Setter
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

    @Override
    public Parent getView(FXMLLoaderConfig fxmlLoaderConfig) {
        try {
            FXMLLoader loader = fxmlLoaderConfig.textCommandArgumentViewLoader();
            Parent parent = loader.load();

            TextCommandArgumentViewController controller = (TextCommandArgumentViewController) loader.getController();
            controller.setTextCommandArgument(this);

            return parent;
        } catch (IOException e) {
        }

        return null;
    }
}
