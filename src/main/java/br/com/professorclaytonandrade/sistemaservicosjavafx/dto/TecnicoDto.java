package br.com.professorclaytonandrade.sistemaservicosjavafx.dto;

import java.time.LocalDate;

public class TecnicoDto {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private Double salario;
    private LocalDate dataCriacao = LocalDate.now();

    public TecnicoDto(){

    }

    public TecnicoDto(Integer id, String nome, String email, String senha, String cpf, Double salario, LocalDate dataCriacao) {
        validarNome(nome);
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.salario = salario;
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validarEmail(email);
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    private static void validarNome(String nome) {
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser vazio");
        } else if (nome.length() < 3) {
            throw new IllegalArgumentException("O nome deve conter pelo menos 3 caracteres.");
        }
    }

    private static void validarEmail(String email) {
        if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("O email não pode ser vazio");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+$")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }

    public static void main(String[] args) {
        TecnicoDto tecnicoDto = new TecnicoDto();
        tecnicoDto.setNome("asa");
        tecnicoDto.setEmail("adfsddasg@sgdfsg");
    }
}
