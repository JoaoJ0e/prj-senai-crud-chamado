package br.com.professorclaytonandrade.sistemaservicosjavafx.dto;

import br.com.professorclaytonandrade.sistemaservicosjavafx.model.OrdemServico;

import java.time.LocalDate;

public class OrdemServicoDto {

    private Long id;
    private Long tecnicoId;
    private Long clienteId;
    private Double valor;
    private LocalDate dataCriacao;

    public OrdemServicoDto(){
    }

    public OrdemServicoDto(Long id, Long tecnicoId, Long clienteId, Double valor) {
        this.id = id;
        this.tecnicoId = tecnicoId;
        this.clienteId = clienteId;
        this.valor = valor;
        this.dataCriacao = LocalDate.now();
    }

    public OrdemServicoDto(OrdemServico ordemServico) {
        this.id = ordemServico.getId();
        this.tecnicoId = ordemServico.getTecnicoId();
        this.clienteId = ordemServico.getClienteId();
        this.valor = ordemServico.getValor();
        this.dataCriacao = ordemServico.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

}