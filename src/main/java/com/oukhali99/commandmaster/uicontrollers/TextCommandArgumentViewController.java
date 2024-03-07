package com.oukhali99.commandmaster.uicontrollers;

import com.oukhali99.commandmaster.model.command.Command;
import com.oukhali99.commandmaster.model.command.argument.TextCommandArgument;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextCommandArgumentViewController {

    private TextCommandArgument textCommandArgument;

    @FXML
    private Label label;

    @Autowired
    private CommandListViewController commandListViewController;

    @Autowired
    private CommandViewController commandViewController;

    public void deleteAction(ActionEvent actionEvent) {
        Command selectedCommand = commandListViewController.getSelectedComand();
        if (selectedCommand == null) {
            return;
        }

        selectedCommand.removeArgument(textCommandArgument);
        commandViewController.refresh();
    }

    @FXML
    private void initialize() {
        refresh();
    }

    private void refresh() {
        if (textCommandArgument == null) {
            return;
        }

        label.setText(textCommandArgument.getStringValue());
    }

    public void setTextCommandArgument(TextCommandArgument textCommandArgument) {
        this.textCommandArgument = textCommandArgument;
        refresh();
    }

}
