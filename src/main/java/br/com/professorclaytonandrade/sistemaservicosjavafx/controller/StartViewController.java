package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import br.com.professorclaytonandrade.sistemaservicosjavafx.StartApplication;
import br.com.professorclaytonandrade.sistemaservicosjavafx.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StartViewController {

    @FXML
    private AnchorPane anchorPane;

    private static AnchorPane globalAnchorPane;

    public StartViewController(){

    }

    @FXML
    public void initialize(){
        globalAnchorPane = anchorPane;
    }

     @FXML
    void mostrarCadastroTecnico(ActionEvent event) throws IOException {
         Util.janelaModal(globalAnchorPane, "cadastro-tecnico.fxml", "Cadastro Técnico");
    }

     @FXML
    void mostrarConsultaTecnico(ActionEvent event) {

    }

}
