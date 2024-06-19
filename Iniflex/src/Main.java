import model.Funcionario;
import service.FuncionarioService;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();
        List<Funcionario> funcionarios = funcionarioService.inicializarFuncionarios();

        funcionarioService.removerFuncionarioPorNome(funcionarios, "João");
        funcionarioService.imprimirFuncionarios(funcionarios);
        funcionarioService.aumentarSalario(funcionarios, BigDecimal.TEN);
        funcionarioService.imprimirFuncionariosAgrupadosPorFuncao(funcionarios);
        funcionarioService.imprimirFuncionariosAniversariantes(funcionarios, 10, 12);
        funcionarioService.imprimirFuncionarioMaisVelho(funcionarios);
        funcionarioService.imprimirFuncionariosPorOrdemAlfabetica(funcionarios);
        funcionarioService.imprimirTotalSalarios(funcionarios);
        funcionarioService.imprimirSalariosMinimos(funcionarios);
    }
}
