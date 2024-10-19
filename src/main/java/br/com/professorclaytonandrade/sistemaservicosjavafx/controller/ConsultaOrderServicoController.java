package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.OrdemServicoDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.service.OrdemServicoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ConsultaOrderServicoController {

    @FXML
    private TableView<OrdemServicoDto> tableView;

    @FXML
    private TableColumn<OrdemServicoDto, Long> idColumn;

    @FXML
    private TableColumn<OrdemServicoDto, Long> tecnicoIdColumn;

    @FXML
    private TableColumn<OrdemServicoDto, Long> clienteIdColumn;

    @FXML
    private TableColumn<OrdemServicoDto, Double> valorColumn;

    @FXML
    private TableColumn<OrdemServicoDto, LocalDate> dataCriacaoColumn;

    @FXML
    private Button editarButton;

    @FXML
    private Button voltarButton;

    private ObservableList<OrdemServicoDto> ordemServicoList;

    private OrdemServicoService ordemServicoService;

    public ConsultaOrderServicoController() {
        this.ordemServicoService = new OrdemServicoService(); // Inicializando o serviço;
    }

    @FXML
    public void initialize() {
        editarButton.setDisable(true);
        ordemServicoList = FXCollections.observableArrayList(ordemServicoService.listarTodos());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tecnicoIdColumn.setCellValueFactory(new PropertyValueFactory<>("tecnicoId"));
        clienteIdColumn.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
        dataCriacaoColumn.setCellValueFactory(new PropertyValueFactory<>("dataCriacao"));

        tableView.setItems(ordemServicoList);

        // Habilitar o botão de editar quando um técnico for selecionado
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            editarButton.setDisable(newSelection == null); // Habilita o botão se houver seleção
        });
    }

    @FXML
    public void editar() throws IOException {
        OrdemServicoDto ordemServicoSelecionado = tableView.getSelectionModel().getSelectedItem();
        if (ordemServicoSelecionado != null) {
            editarOrdemServico(ordemServicoSelecionado);
        }
    }

    @FXML
    public void deletar() {
        OrdemServicoDto ordemServicoSelecionado = tableView.getSelectionModel().getSelectedItem();
        if (ordemServicoSelecionado != null) {
            ordemServicoService.remover(ordemServicoSelecionado.getId());
            recarregarTabela();
        }
    }

    @FXML
    public void voltar() {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    private void editarOrdemServico(OrdemServicoDto ordemServicoDto) throws IOException {
        boolean editarTécnico = StartViewController.mostrarTelaEditar("cadastro-ordemServico.fxml", "Editar Técnico", ordemServicoDto, new CadastroOrdemServicoController());
        if(editarTécnico)recarregarTabela();
    }

    public void recarregarTabela() {
        ordemServicoList.clear(); // Limpa a lista existente
        ordemServicoList.addAll(ordemServicoService.listarTodos()); // Carrega novamente a lista de técnicos
        tableView.setItems(ordemServicoList); // Atualiza a tabela
    }

}

