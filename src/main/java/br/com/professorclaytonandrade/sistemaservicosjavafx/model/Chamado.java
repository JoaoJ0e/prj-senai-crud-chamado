package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.ChamadoDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.enums.PrioridadeEnum;
import br.com.professorclaytonandrade.sistemaservicosjavafx.enums.StatusChamadoEnum;

import java.time.LocalDate;

public class Chamado {
    private Long id;
    private String titulo;
    private String observacoes;
    private PrioridadeEnum prioridade;
    private StatusChamadoEnum status;
    private Long tecnicoId;
    private Long clienteId;
    private LocalDate dataAbertura;
    private LocalDate dataFechamento;

    public Chamado() {
        this.dataAbertura = LocalDate.now();
        this.status = StatusChamadoEnum.ABERTO;
    }

    public Chamado(Long id, String titulo, String observacoes, PrioridadeEnum prioridade,
                   StatusChamadoEnum status, Long tecnicoId, Long clienteId) {
        this.id = id;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.prioridade = prioridade;
        this.status = status;
        this.tecnicoId = tecnicoId;
        this.clienteId = clienteId;
        this.dataAbertura = LocalDate.now();
    }

    public Chamado(ChamadoDto dto) {
        this.id = dto.getId();
        this.titulo = dto.getTitulo();
        this.observacoes = dto.getObservacoes();
        this.prioridade = dto.getPrioridade();
        this.status = dto.getStatus();
        this.tecnicoId = dto.getTecnicoId();
        this.clienteId = dto.getClienteId();
        this.dataAbertura = dto.getDataAbertura();
        this.dataFechamento = dto.getDataFechamento();
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

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
}