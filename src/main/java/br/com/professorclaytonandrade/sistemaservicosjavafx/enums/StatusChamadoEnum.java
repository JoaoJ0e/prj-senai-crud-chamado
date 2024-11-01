package br.com.professorclaytonandrade.sistemaservicosjavafx.enums;

public enum StatusChamadoEnum {
    ABERTO(0, "Aberto"),
    ANDAMENTO(1, "Andamento"),
    ENCERRADO(2, "Encerrado");

    private Integer codigo;
    private String descricao;

    private StatusChamadoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusChamadoEnum toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (StatusChamadoEnum StatusChamadoEnum : StatusChamadoEnum.values()) {
            if (cod.equals(StatusChamadoEnum.getCodigo())) {
                return StatusChamadoEnum;
            }
        }
        throw new IllegalArgumentException("StatusChamadoEnum inválido");
    }
}
