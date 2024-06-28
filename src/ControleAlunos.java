import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControleAlunos {
    private List<Aluno> listaDeAlunos;

    public ControleAlunos(String filePath) {
        listaDeAlunos = new ArrayList<>();
        carregarAlunos(filePath);
    }

    private void carregarAlunos(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Ignora o cabeçalho
                    continue;
                }
                String[] values = line.split(";");
                if (values.length == 3) { // Certifique-se de que haja 3 valores em cada linha
                    int matricula = Integer.parseInt(values[0].trim());
                    String nome = values[1].trim();
                    double nota = Double.parseDouble(values[2].trim().replace(',', '.'));
                    listaDeAlunos.add(new Aluno(matricula, nome, nota));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter número: " + e.getMessage());
        }
    }

    public int quantidadeAlunos() {
        return listaDeAlunos.size();
    }

    public int aprovados() {
        return (int) listaDeAlunos.stream().filter(aluno -> aluno.getNota() >= 6.0).count();
    }

    public int reprovados() {
        return (int) listaDeAlunos.stream().filter(aluno -> aluno.getNota() < 6.0).count();
    }

    public double menorNota() {
        return listaDeAlunos.stream().mapToDouble(Aluno::getNota).min().orElse(Double.NaN);
    }

    public double maiorNota() {
        return listaDeAlunos.stream().mapToDouble(Aluno::getNota).max().orElse(Double.NaN);
    }

    public double mediaNotas() {
        return listaDeAlunos.stream().mapToDouble(Aluno::getNota).average().orElse(Double.NaN);
    }

    public void gerarResumo() {
        String outputPath = "src/Resumo.csv"; // Caminho fixo do arquivo de saída
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.append("Quantidade de alunos;")
                    .append(String.valueOf(quantidadeAlunos())).append("\n");
            writer.append("Quantidade de aprovados;")
                    .append(String.valueOf(aprovados())).append("\n");
            writer.append("Quantidade de reprovados;")
                    .append(String.valueOf(reprovados())).append("\n");
            writer.append("Menor nota;")
                    .append(String.valueOf(menorNota())).append("\n");
            writer.append("Maior nota;")
                    .append(String.valueOf(maiorNota())).append("\n");
            writer.append("Média das notas;")
                    .append(String.valueOf(mediaNotas())).append("\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
        }
    }
}
