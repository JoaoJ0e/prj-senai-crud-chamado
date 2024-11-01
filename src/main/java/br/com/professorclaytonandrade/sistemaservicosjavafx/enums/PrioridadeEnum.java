package br.com.professorclaytonandrade.sistemaservicosjavafx.enums;

public enum PrioridadeEnum {
    BAIXA(0, "Baixa"),
    MEDIA(1, "Média"),
    ALTA(2, "Alta");

    private Integer codigo;
    private String descricao;

    private PrioridadeEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PrioridadeEnum toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (PrioridadeEnum PrioridadeEnum : PrioridadeEnum.values()) {
            if (cod.equals(PrioridadeEnum.getCodigo())) {
                return PrioridadeEnum;
            }
        }
        throw new IllegalArgumentException("PrioridadeEnum inválida");
    }
}