package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.ChamadoDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.ClienteDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.TecnicoDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.enums.PrioridadeEnum;
import br.com.professorclaytonandrade.sistemaservicosjavafx.service.ChamadoService;
import br.com.professorclaytonandrade.sistemaservicosjavafx.service.ClienteService;
import br.com.professorclaytonandrade.sistemaservicosjavafx.service.TecnicoService;
import br.com.professorclaytonandrade.sistemaservicosjavafx.util.Mensagens;
import br.com.professorclaytonandrade.sistemaservicosjavafx.util.Util;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CadastroChamadoController {
    private static final Logger logger = LoggerFactory.getLogger(CadastroChamadoController.class);

    @FXML
    private TextField idField;

    @FXML
    private TextField tituloField;

    @FXML
    private TextArea observacoesArea;

    @FXML
    private ComboBox<PrioridadeEnum> prioridadeComboBox;

    @FXML
    private ComboBox<TecnicoDto> tecnicoComboBox;

    @FXML
    private ComboBox<ClienteDto> clienteComboBox;

    @FXML
    private Button salvarButton;

    @FXML
    private Button cancelarButton;

    private ChamadoService chamadoService;
    private TecnicoService tecnicoService;
    private ClienteService clienteService;

    public CadastroChamadoController() {
        this.chamadoService = new ChamadoService();
        this.tecnicoService = new TecnicoService();
        this.clienteService = new ClienteService();
    }

    @FXML
    public void initialize() {
        configurarComboBoxes();
        configurarListeners();
        salvarButton.setDisable(true);
    }

    private void configurarComboBoxes() {
        // Configurar ComboBox de Prioridade
        prioridadeComboBox.setItems(FXCollections.observableArrayList(PrioridadeEnum.values()));
        prioridadeComboBox.setCellFactory(p -> new ListCell<PrioridadeEnum>() {
            @Override
            protected void updateItem(PrioridadeEnum item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getDescricao());
                }
            }
        });
        prioridadeComboBox.setButtonCell(prioridadeComboBox.getCellFactory().call(null));

        // Configurar ComboBox de Técnico
        tecnicoComboBox.setItems(FXCollections.observableArrayList(tecnicoService.listarTodos()));
        tecnicoComboBox.setCellFactory(p -> new ListCell<TecnicoDto>() {
            @Override
            protected void updateItem(TecnicoDto item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getNome());
                }
            }
        });
        tecnicoComboBox.setButtonCell(tecnicoComboBox.getCellFactory().call(null));

        // Configurar ComboBox de Cliente
        clienteComboBox.setItems(FXCollections.observableArrayList(clienteService.listarTodos()));
        clienteComboBox.setCellFactory(p -> new ListCell<ClienteDto>() {
            @Override
            protected void updateItem(ClienteDto item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getNome());
                }
            }
        });
        clienteComboBox.setButtonCell(clienteComboBox.getCellFactory().call(null));
    }

    private void configurarListeners() {
        ChangeListener<Object> campoListener = (observable, oldValue, newValue) ->
                salvarButton.setDisable(!todosCamposPreenchidos());

        tituloField.textProperty().addListener(campoListener);
        prioridadeComboBox.valueProperty().addListener(campoListener);
        tecnicoComboBox.valueProperty().addListener(campoListener);
        clienteComboBox.valueProperty().addListener(campoListener);
    }

    @FXML
    void salvar() {
        try {
            ChamadoDto chamadoDto = new ChamadoDto();
            chamadoDto.setTitulo(tituloField.getText().trim());
            chamadoDto.setObservacoes(observacoesArea.getText().trim());
            chamadoDto.setPrioridade(prioridadeComboBox.getValue());
            chamadoDto.setTecnicoId(tecnicoComboBox.getValue().getId());
            chamadoDto.setClienteId(clienteComboBox.getValue().getId());

            chamadoService.criar(chamadoDto);
            Util.exibirAlerta(Alert.AlertType.INFORMATION, Mensagens.TITULO_CONFIRMACAO_SALVAMENTO,
                    null, Mensagens.MSG_INF_SALVAS_COM_SUCESSO);
            limparCampos();
        } catch (IllegalArgumentException e) {
            Util.exibirAlerta(Alert.AlertType.ERROR, Mensagens.TITULO_ERRO_VALIDACAO,
                    null, Mensagens.MSG_ERRO_AO_SALVAR_INFORMACOES + e.getMessage());
        } catch (Exception e) {
            Util.exibirAlerta(Alert.AlertType.ERROR, Mensagens.TITULO_ERRO_AO_SALVAR,
                    null, Mensagens.MSG_ERRO_AO_SALVAR_INFORMACOES + e.getMessage());
        }
    }

    @FXML
    void cancelar() {
        fecharJanela();
    }

    private boolean todosCamposPreenchidos() {
        return !tituloField.getText().trim().isEmpty() &&
                prioridadeComboBox.getValue() != null &&
                tecnicoComboBox.getValue() != null &&
                clienteComboBox.getValue() != null;
    }

    private void limparCampos() {
        idField.clear();
        tituloField.clear();
        observacoesArea.clear();
        prioridadeComboBox.setValue(null);
        tecnicoComboBox.setValue(null);
        clienteComboBox.setValue(null);
    }

    private void fecharJanela() {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}