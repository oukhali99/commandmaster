package com.oukhali99.commandmaster.uicontrollers;

import com.oukhali99.commandmaster.model.command.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class CommandRowViewController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label valueLabel;

    private Command command;

    public void mouseClicked(MouseEvent mouseEvent) {
    }

    public void setCommand(Command command) {
        this.command = command;

        nameLabel.setText(command.getName());
        valueLabel.setText(command.getValue());
    }

}
