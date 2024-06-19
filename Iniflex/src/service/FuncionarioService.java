package service;

import model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public List<Funcionario> inicializarFuncionarios() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));
        return funcionarios;
    }

    public void removerFuncionarioPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public void imprimirFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("Funcionários:");
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    public void aumentarSalario(List<Funcionario> funcionarios, BigDecimal percentual) {
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(percentual));
    }

    public void imprimirFuncionariosAgrupadosPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(funcionario -> System.out.println("   " + funcionario));
        });
    }

    public void imprimirFuncionariosAniversariantes(List<Funcionario> funcionarios, int... meses) {
        System.out.println("\nFuncionários que fazem aniversário nos meses " + Arrays.toString(meses) + ":");
        List<Integer> listaMeses = Arrays.stream(meses).boxed().collect(Collectors.toList());
        funcionarios.stream()
                .filter(funcionario -> listaMeses.contains(funcionario.getDataNascimento().getMonthValue()))
                .forEach(funcionario -> System.out.println(funcionario.getNome() + " - " + funcionario.getDataNascimentoFormatted()));
    }

    public void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow(NoSuchElementException::new);
        LocalDate hoje = LocalDate.now();
        int idade = hoje.getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("\nFuncionário mais velho:");
        System.out.println("   Nome: " + maisVelho.getNome());
        System.out.println("   Idade: " + idade);
    }

    public void imprimirFuncionariosPorOrdemAlfabetica(List<Funcionario> funcionarios) {
        System.out.println("\nFuncionários ordenados por nome:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(funcionario -> System.out.println(funcionario.getNome()));
    }

    public void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " + totalSalarios);
    }

    public void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        System.out.println("\nSalários mínimos ganhos por cada funcionário:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
        });
    }
}
