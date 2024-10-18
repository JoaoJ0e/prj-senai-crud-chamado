package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.TecnicoDto;

import java.time.LocalDate;

public class Tecnico extends Pessoa{
    private Double salario;

    public Tecnico(){
        super();
    }

    public Tecnico(Long id, String nome, String email, String senha, String cpf, Double salario) {
        super(id, nome, email, senha, cpf);
        this.salario = salario;
    }

    public Tecnico(TecnicoDto tecnicoDto) {
        super(
            (tecnicoDto.getId() != null) ? tecnicoDto.getId() : null,
            tecnicoDto.getNome(),
            tecnicoDto.getEmail(),
            tecnicoDto.getSenha(),
            tecnicoDto.getCpf()
        );
        this.dataCriacao = tecnicoDto.getDataCriacao();
        this.salario = tecnicoDto.getSalario();
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}