package com.oukhali99.commandmaster.uicontrollers;

import com.oukhali99.commandmaster.config.FXMLLoaderConfig;
import com.oukhali99.commandmaster.model.command.Command;
import com.oukhali99.commandmaster.model.command.argument.CommandArgument;
import com.oukhali99.commandmaster.model.command.argument.TextCommandArgument;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class CommandViewController implements ApplicationListener<CommandListViewController.SelectedCommandChangedEvent> {

    @FXML
    private VBox argumentsVBox;

    @FXML
    private TextField argumentTextField;
    
    @FXML
    private Parent root;

    @FXML
    private Label combinedCommandLabel;

    @Autowired
    private CommandListViewController commandListViewController;

    @Autowired
    private FXMLLoaderConfig fxmlLoaderConfig;

    @FXML
    private void initialize() {
        refresh();
    }

    @FXML
    private Label workingDirectoryLabel;

    public void refresh() {
        // Set as invisible
        root.setVisible(false);

        Command selectedCommand = commandListViewController.getSelectedComand();
        if (selectedCommand == null) {
            return;
        }

        // Set the combined text
        combinedCommandLabel.setText(selectedCommand.getCombinedValue());

        // Add all the arguments
        argumentsVBox.getChildren().clear();
        for (CommandArgument argument : selectedCommand.getArguments()) {
            argumentsVBox.getChildren().add(argument.getView(fxmlLoaderConfig));
        }

        // Set the working directory label
        workingDirectoryLabel.setText(selectedCommand.getWorkingDirectory().getAbsolutePath());

        root.setVisible(true);
    }

    @Override
    public void onApplicationEvent(CommandListViewController.SelectedCommandChangedEvent event) {
        refresh();
    }

    public void addArgumentAction(ActionEvent actionEvent) {
        Command selectedCommand = commandListViewController.getSelectedComand();
        if (selectedCommand == null) {
            return;
        }

        String argumentString = argumentTextField.getText();
        CommandArgument newArgument = new TextCommandArgument(argumentString);
        selectedCommand.addArgument(newArgument);

        refresh();
    }

    public void onMouseClickedExecute(MouseEvent mouseEvent) {
        Command selectedCommand = commandListViewController.getSelectedComand();
        if (selectedCommand == null) {
            return;
        }

        selectedCommand.execute();
    }

    public void onMouseClickedBrowse(MouseEvent mouseEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        Stage stage = (Stage) workingDirectoryLabel.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory == null) {
            return;
        }

        Command selectedCommand = commandListViewController.getSelectedComand();
        if (selectedCommand == null) {
            return;
        }

        selectedCommand.setWorkingDirectory(selectedDirectory);
        refresh();
    }
}
