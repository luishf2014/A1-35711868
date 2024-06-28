public class Aluno {
    public int matricula;
    public String nome;
    public Double nota;

    public Aluno(int matricula, String nome, Double nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getNota() {
        return this.nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Aluno [matricula=" + matricula + ", nome=" + nome + ", nota=" + nota + "]";
    }

}
