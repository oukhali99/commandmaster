package com.oukhali99.commandmaster.model.command;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CommandService {

    private static final String FILE_NAME = "commandStorage.xml";

    @Autowired
    private Marshaller marshaller;

    @Autowired
    private Unmarshaller unmarshaller;

    private CommandStorage commandStorage;

    @PostConstruct
    private void postConstruct() {
        try {
            FileInputStream is = new FileInputStream(FILE_NAME);
            commandStorage = (CommandStorage) this.unmarshaller.unmarshal(new StreamSource(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (commandStorage == null) {
            commandStorage = new CommandStorage();
        }
    }

    @PreDestroy
    private void preDestroy() {
        try {
            FileOutputStream os = new FileOutputStream(FILE_NAME);
            this.marshaller.marshal(commandStorage, new StreamResult(os));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCommand(Command command) {
        commandStorage.addCommand(command);
    }

    public List<Command> getCommands() {
        return commandStorage.getCommands();
    }

}
