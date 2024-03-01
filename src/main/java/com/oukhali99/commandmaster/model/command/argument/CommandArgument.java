package com.oukhali99.commandmaster.model.command.argument;

import com.oukhali99.commandmaster.config.FXMLLoaderConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public interface CommandArgument {

    String getStringValue();

    Parent getView(FXMLLoaderConfig fxmlLoaderConfig);

}
