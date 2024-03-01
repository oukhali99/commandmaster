package com.oukhali99.commandmaster.uicontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CommandRowViewController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label valueLabel;

}
