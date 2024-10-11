package br.com.professorclaytonandrade.sistemaservicosjavafx;

import br.com.professorclaytonandrade.sistemaservicosjavafx.util.Util;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = Util.carregarFXML("start-view.fxml");
        Scene scene = new Scene(parent);
        stage.setTitle("Sistema de Serviços JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}