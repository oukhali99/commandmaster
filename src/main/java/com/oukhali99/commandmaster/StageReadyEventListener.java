package com.oukhali99.commandmaster;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageReadyEventListener implements ApplicationListener<MyApplication.StageReadyEvent> {

    @Value("${spring.application.title}")
    private String title;

    @Value("classpath:/root.fxml")
    private Resource fxml;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(MyApplication.StageReadyEvent event) {
        Stage stage = event.getStage();
        try {
            URL url = fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(applicationContext::getBean);

            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1280, 720);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
