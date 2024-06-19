package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void aumentarSalario(BigDecimal percentual) {
        BigDecimal aumento = salario.multiply(percentual).divide(BigDecimal.valueOf(100));
        salario = salario.add(aumento);
    }

    public String getFuncao() {
        return funcao;
    }

    @Override
    public String toString() {
        return String.format("%s, Salário: %,.2f, Função: %s", super.toString(), salario, funcao);
    }
}
