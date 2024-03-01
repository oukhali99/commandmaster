package com.oukhali99.commandmaster.uicontrollers;

import com.gluonhq.charm.glisten.control.CardPane;
import com.oukhali99.commandmaster.config.FXMLLoaderConfig;
import com.oukhali99.commandmaster.model.command.Command;
import com.oukhali99.commandmaster.model.command.CommandService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandListViewController implements ApplicationListener<NewCommandViewController.NewCommandEvent> {

    @FXML
    private CardPane<Parent> commandListCardPane;

    @Autowired
    private CommandService commandService;

    @Value("classpath:/CommandRowView.fxml")
    private Resource commandRowViewResource;

    @Autowired
    private FXMLLoaderConfig fxmlLoaderConfig;

    @FXML
    private void initialize() {
        for (Command command : commandService.getCommands()) {
            try {
                FXMLLoader loader = fxmlLoaderConfig.fxmlLoader(commandRowViewResource.getURL());
                Parent parent = loader.load();

                CommandRowViewController controller = (CommandRowViewController) loader.getController();
                controller.getNameLabel().setText(command.getName());
                controller.getValueLabel().setText(command.getValue());

                commandListCardPane.getItems().add(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void reInitialize() {
        commandListCardPane.getItems().clear();
        initialize();
    }

    @Override
    public void onApplicationEvent(NewCommandViewController.NewCommandEvent event) {
        reInitialize();
    }
}
