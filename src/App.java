public class App {
    public static void main(String[] args) {
        String arquivoPessoa = "src/Alunos.csv"; // Caminho do arquivo de entrada
        ControleAlunos controle = new ControleAlunos(arquivoPessoa);
        controle.gerarResumo();
        System.out.println("Arquivo de resumo gerado com sucesso.");
    }
}
