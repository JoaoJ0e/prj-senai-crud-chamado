package br.com.professorclaytonandrade.sistemaservicosjavafx.dto;

import br.com.professorclaytonandrade.sistemaservicosjavafx.enums.PrioridadeEnum;
import br.com.professorclaytonandrade.sistemaservicosjavafx.enums.StatusChamadoEnum;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Chamado;

import java.time.LocalDate;

public class ChamadoDto {
    private Long id;
    private String titulo;
    private String observacoes;
    private PrioridadeEnum prioridade;
    private StatusChamadoEnum status;
    private Long tecnicoId;
    private Long clienteId;
    private LocalDate dataAbertura;
    private LocalDate dataFechamento;

    public ChamadoDto() {
        this.status = StatusChamadoEnum.ABERTO;
        this.dataAbertura = LocalDate.now();
    }

    public ChamadoDto(Long id, String titulo, String observacoes, PrioridadeEnum prioridade,
                      StatusChamadoEnum status, Long tecnicoId, Long clienteId) {
        validarCampos(titulo, tecnicoId, clienteId);
        this.id = id;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.prioridade = prioridade;
        this.status = status;
        this.tecnicoId = tecnicoId;
        this.clienteId = clienteId;
        this.dataAbertura = LocalDate.now();
    }

    public ChamadoDto(Chamado chamado) {
        validarCampos(chamado.getTitulo(), chamado.getTecnicoId(), chamado.getClienteId());
        this.id = chamado.getId();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.prioridade = chamado.getPrioridade();
        this.status = chamado.getStatus();
        this.tecnicoId = chamado.getTecnicoId();
        this.clienteId = chamado.getClienteId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
    }

    private static void validarCampos(String titulo, Long tecnicoId, Long clienteId) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio");
        }
        if (tecnicoId == null) {
            throw new IllegalArgumentException("O técnico deve ser selecionado");
        }
        if (clienteId == null) {
            throw new IllegalArgumentException("O cliente deve ser selecionado");
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public PrioridadeEnum getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeEnum prioridade) {
        this.prioridade = prioridade;
    }

    public StatusChamadoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusChamadoEnum status) {
        this.status = status;
    }

    public Long getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Long tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
}
