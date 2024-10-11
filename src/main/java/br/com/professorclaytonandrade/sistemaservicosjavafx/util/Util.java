package br.com.professorclaytonandrade.sistemaservicosjavafx.util;

import br.com.professorclaytonandrade.sistemaservicosjavafx.StartApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Util {
//    public static void janelaModal2(AnchorPane anchorPane, String telaFXML, String titulo) throws IOException {
        //Carrega o arquivo FXML de Cadastro de tecnico
//        Parent parent = FXMLLoader.load(StartApplication.class.getResource(telaFXML));

//        //Criar um novo Stage para popup
//        Stage stagePopup = new Stage();
//        stagePopup.setTitle(titulo);
//
//        //Definir a cena do novo Stage
//        Scene scene = new Scene(parent);
//        stagePopup.setScene(scene);
//
//        //Definir o Stage como modal para que bloqueie interações com a janela principal
//        stagePopup.initModality(Modality.APPLICATION_MODAL);
//        stagePopup.initOwner(anchorPane.getScene().getWindow());
//
//        //Centralizar o Stage na Janela Princiapl
//        stagePopup.centerOnScreen();
//
//        //Impedir redimensionamento da janela
//        stagePopup.setResizable(false);
//
//        //Mostrar o Stage como um Popup
//        stagePopup.showAndWait();
//    }

    public static void janelaModal(AnchorPane anchorPane, String telaFXML, String titulo) throws IOException {
        Parent parent = carregarFXML(telaFXML);
        Stage stagePopup = criarPopupStage(anchorPane, titulo, parent);
        configurarJanela(stagePopup);
        exibirJanela(stagePopup);
    }

    private static void exibirJanela(Stage popupStage){
        popupStage.showAndWait();
    }

    private static void configurarJanela(Stage popupStage){
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.centerOnScreen();
        popupStage.setResizable(false);
    }

    private static Stage criarPopupStage(AnchorPane anchorPane, String titulo, Parent parent) {
        Stage stagePopup = new Stage();
        stagePopup.setTitle(titulo);
        Scene scene = new Scene(parent);
        stagePopup.setScene(scene);
        stagePopup.initModality(Modality.APPLICATION_MODAL);
        stagePopup.initOwner(anchorPane.getScene().getWindow());
        return stagePopup;
    }

    public static Parent carregarFXML(String telaFXML) throws IOException {
        return FXMLLoader.load(StartApplication.class.getResource(telaFXML));
    }
}
