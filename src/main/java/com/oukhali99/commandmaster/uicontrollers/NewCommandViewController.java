package com.oukhali99.commandmaster.uicontrollers;

import com.oukhali99.commandmaster.model.command.Command;
import com.oukhali99.commandmaster.model.command.CommandService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class NewCommandViewController {

    @FXML
    private TextField commandValueTextField;

    @FXML
    private Label label;

    @Autowired
    private CommandService commandService;

    public void cancelButtonAction(ActionEvent actionEvent) {
        close();
    }

    public void okButtonAction(ActionEvent actionEvent) {
        String commandValue = commandValueTextField.getText();
        if (commandValue == null) {
            return;
        }

        Command newCommand = new Command(commandValue);
        commandService.addCommand(newCommand);

        close();
    }

    private void close() {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }
}
