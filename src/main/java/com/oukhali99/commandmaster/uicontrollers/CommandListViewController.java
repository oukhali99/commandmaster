package com.oukhali99.commandmaster.uicontrollers;

import com.gluonhq.charm.glisten.control.CardPane;
import com.oukhali99.commandmaster.config.FXMLLoaderConfig;
import com.oukhali99.commandmaster.model.command.Command;
import com.oukhali99.commandmaster.model.command.CommandService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandListViewController implements ApplicationListener<CommandService.CommandListChangedEvent> {

    @FXML
    private CardPane<Parent> commandListCardPane;

    @Autowired
    private CommandService commandService;

    @Value("classpath:/CommandRowView.fxml")
    private Resource commandRowViewResource;

    @Autowired
    private FXMLLoaderConfig fxmlLoaderConfig;

    @Getter
    private Command selectedComand;

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private void initialize() {
        selectedComand = null;

        for (Command command : commandService.getCommands()) {
            try {
                FXMLLoader loader = fxmlLoaderConfig.fxmlLoader(commandRowViewResource.getURL());
                Parent parent = loader.load();

                parent.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        selectedComand = command;
                        applicationContext.publishEvent(new SelectedCommandChangedEvent(selectedComand));
                    }
                });

                CommandRowViewController controller = (CommandRowViewController) loader.getController();
                controller.setCommand(command);

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
    public void onApplicationEvent(CommandService.CommandListChangedEvent event) {
        reInitialize();
    }

    public static class SelectedCommandChangedEvent extends ApplicationEvent {

        SelectedCommandChangedEvent(Command source) {
            super(source);
        }

        Command getSelectedCommand() {
            return (Command) getSource();
        }

    }

}
