package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

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
    void mostrarCadastroTecnico(ActionEvent event) {

    }

     @FXML
    void mostrarConsultaTecnico(ActionEvent event) {

    }

}
