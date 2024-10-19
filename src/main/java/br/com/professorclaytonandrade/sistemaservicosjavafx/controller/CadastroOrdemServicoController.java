package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.OrdemServicoDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.service.OrdemServicoService;
import br.com.professorclaytonandrade.sistemaservicosjavafx.util.Mensagens;
import br.com.professorclaytonandrade.sistemaservicosjavafx.util.Util;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class CadastroOrdemServicoController {
    private static final Logger logger = LoggerFactory.getLogger(CadastroOrdemServicoController.class);

    @FXML
    private Button cancelarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private TextField tecnicoIdField;

    @FXML
    private TextField clienteIdField;

    @FXML
    private TextField valorField;

    @FXML
    private TextField idField;

    private OrdemServicoService ordemServicoService;

    private boolean edicao = false;

    public CadastroOrdemServicoController() {
        this.ordemServicoService = new OrdemServicoService(); // Inicializando o serviço;
    }

    @FXML
    public void initialize() {

        ChangeListener<String> campoListener = (observable, oldValue, newValue) ->
                salvarButton.setDisable(!todosCamposPreenchidos());

        idField.setDisable(true); // Campo ID é desativado pois é gerado automaticamente.
        tecnicoIdField.textProperty().addListener(campoListener);
        clienteIdField.textProperty().addListener(campoListener);
        valorField.textProperty().addListener(campoListener);




        // Inicialmente, o botão "Salvar" está desativado.
        salvarButton.setDisable(true);
    }

    @FXML
    void cancelar() {
        fecharJanela();
    }

    @FXML
    void salvar() {
        try {
            salvarAlteracoes();
            Util.exibirAlerta(Alert.AlertType.INFORMATION, Mensagens.TITULO_CONFIRMACAO_SALVAMENTO, null, Mensagens.MSG_INF_SALVAS_COM_SUCESSO);
            limparCampos();
            if (edicao) fecharJanela();
        } catch (IllegalArgumentException e) {
            Util.exibirAlerta(Alert.AlertType.ERROR, Mensagens.TITULO_ERRO_VALIDACAO, null, Mensagens.MSG_ERRO_AO_SALVAR_INFORMACOES + e.getMessage());
        } catch (Exception e) {
            Util.exibirAlerta(Alert.AlertType.ERROR, Mensagens.TITULO_ERRO_AO_SALVAR, null, Mensagens.MSG_ERRO_AO_SALVAR_INFORMACOES + e.getMessage());
        }
    }

    private void salvarAlteracoes() {
        Long id = !idField.getText().trim().isEmpty() ? Long.valueOf(idField.getText().trim()) : null;
        Long tecnicoId = Long.valueOf(tecnicoIdField.getText().trim());
        Long clienteId = Long.valueOf(clienteIdField.getText().trim());
        Double valor = Double.valueOf(valorField.getText().trim());

        OrdemServicoDto ordemServicoDto = new OrdemServicoDto(id, tecnicoId, clienteId, valor);
        if (ordemServicoDto.getId() == null) {
            ordemServicoService.criar(ordemServicoDto); // Adiciona nova ordem de serviço
        } else {
            ordemServicoService.atualizar(ordemServicoDto); // Atualiza ordem de serviço existente
            edicao = true;
        }
    }

    public void setDado(OrdemServicoDto ordemServicoDto) {
        if (ordemServicoDto != null) {
            idField.setText(String.valueOf(ordemServicoDto.getId()));
            tecnicoIdField.setText(String.valueOf(ordemServicoDto.getTecnicoId()));
            clienteIdField.setText(String.valueOf(ordemServicoDto.getClienteId()));
            valorField.setText(String.valueOf(ordemServicoDto.getValor()));
        }
    }

    private void limparCampos() {
        // Limpar todos os campos
        idField.clear();
        tecnicoIdField.clear();
        clienteIdField.clear();
        valorField.clear();
    }

    private boolean todosCamposPreenchidos() {
        return !tecnicoIdField.getText().trim().isEmpty() &&
                !clienteIdField.getText().trim().isEmpty() &&
                !valorField.getText().trim().isEmpty();
    }

    private void fecharJanela() {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
