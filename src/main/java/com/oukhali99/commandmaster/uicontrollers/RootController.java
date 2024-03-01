package com.oukhali99.commandmaster.uicontrollers;

import com.gluonhq.charm.glisten.control.CardPane;
import com.oukhali99.commandmaster.config.FXMLLoaderConfig;
import com.oukhali99.commandmaster.model.command.Command;
import com.oukhali99.commandmaster.model.command.CommandService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RootController {

    @Value("${spring.application.title}")
    private String title;

    @Value("classpath:/NewCommandView.fxml")
    private Resource newCommandViewResource;

    @FXML
    private CardPane<Command> commandListCardPane;

    @Autowired
    private CommandService commandService;

    @Autowired
    private FXMLLoaderConfig fxmlLoaderConfig;

    @FXML
    private void initialize() {
        for (Command command : commandService.getCommands()) {
            commandListCardPane.getItems().add(command);
        }
    }

    public void newButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader newCommandViewLoader = fxmlLoaderConfig.fxmlLoader(newCommandViewResource.getURL());
            Parent newCommandView = newCommandViewLoader.load();

            Dialog<Void> dialog = new Dialog<>();
            DialogPane dialogPane = new DialogPane();
            dialogPane.setContent(newCommandView);
            dialog.setDialogPane(dialogPane);

            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}