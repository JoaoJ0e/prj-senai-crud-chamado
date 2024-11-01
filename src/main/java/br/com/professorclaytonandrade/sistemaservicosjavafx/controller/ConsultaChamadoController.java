package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.ChamadoDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.enums.StatusChamadoEnum;
import br.com.professorclaytonandrade.sistemaservicosjavafx.service.ChamadoService;
import br.com.professorclaytonandrade.sistemaservicosjavafx.util.Mensagens;
import br.com.professorclaytonandrade.sistemaservicosjavafx.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class ConsultaChamadoController {
    private static final Logger logger = LoggerFactory.getLogger(ConsultaChamadoController.class);

    @FXML
    private TableView<ChamadoDto> tableView;

    @FXML
    private TableColumn<ChamadoDto, Long> idColumn;

    @FXML
    private TableColumn<ChamadoDto, String> tituloColumn;

    @FXML
    private TableColumn<ChamadoDto, String> prioridadeColumn;

    @FXML
    private TableColumn<ChamadoDto, String> statusColumn;

    @FXML
    private TableColumn<ChamadoDto, String> tecnicoColumn;

    @FXML
    private TableColumn<ChamadoDto, String> clienteColumn;

    @FXML
    private TableColumn<ChamadoDto, LocalDate> dataAberturaColumn;

    @FXML
    private TableColumn<ChamadoDto, LocalDate> dataFechamentoColumn;

    @FXML
    private Button encerrarButton;

    @FXML
    private Button voltarButton;

    private ObservableList<ChamadoDto> chamadoList;
    private ChamadoService chamadoService;

    public ConsultaChamadoController() {
        this.chamadoService = new ChamadoService();
    }

    @FXML
    public void initialize() {
        configurarColunas();
        carregarDados();

        // Habilitar o botão de encerrar apenas para chamados não encerrados
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                encerrarButton.setDisable(newSelection.getStatus() == StatusChamadoEnum.ENCERRADO);
            }
        });
    }

    private void configurarColunas() {
        // Configurar como as colunas devem exibir os dados
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        // Converter enum de prioridade para texto
        prioridadeColumn.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createStringBinding(
                        () -> cellData.getValue().getPrioridade().getDescricao()
                )
        );

        // Converter enum de status para texto
        statusColumn.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createStringBinding(
                        () -> cellData.getValue().getStatus().getDescricao()
                )
        );

        tecnicoColumn.setCellValueFactory(new PropertyValueFactory<>("tecnicoId"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        dataAberturaColumn.setCellValueFactory(new PropertyValueFactory<>("dataAbertura"));
        dataFechamentoColumn.setCellValueFactory(new PropertyValueFactory<>("dataFechamento"));
    }

    private void carregarDados() {
        chamadoList = FXCollections.observableArrayList(chamadoService.listarTodos());
        tableView.setItems(chamadoList);
    }

    @FXML
    public void encerrar() {
        ChamadoDto chamadoSelecionado = tableView.getSelectionModel().getSelectedItem();
        if (chamadoSelecionado != null) {
            boolean confirmacao = Util.exibirAlerta(
                    Alert.AlertType.CONFIRMATION,
                    "Confirmação",
                    null,
                    "Deseja realmente encerrar este chamado?"
            );

            if (confirmacao) {
                if (chamadoService.encerrarChamado(chamadoSelecionado.getId())) {
                    Util.exibirAlerta(
                            Alert.AlertType.INFORMATION,
                            Mensagens.TITULO_CONFIRMACAO_SALVAMENTO,
                            null,
                            "Chamado encerrado com sucesso!"
                    );
                    recarregarTabela();
                }
            }
        }
    }

    @FXML
    public void voltar() {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    public void recarregarTabela() {
        chamadoList.clear();
        chamadoList.addAll(chamadoService.listarTodos());
        tableView.setItems(chamadoList);
    }
}