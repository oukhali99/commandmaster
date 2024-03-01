package com.oukhali99.commandmaster.config;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URL;

@Configuration
public class FXMLLoaderConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("classpath:/CommandView/TextCommandArgumentView.fxml")
    private Resource commandArgumentViewResource;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FXMLLoader fxmlLoader(URL url) {
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(applicationContext::getBean);
        return loader;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FXMLLoader textCommandArgumentViewLoader() throws IOException {
        return fxmlLoader(commandArgumentViewResource.getURL());
    }

}
