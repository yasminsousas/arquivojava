package arquivo;

public class Paciente {
    private int codigo;
    private String nome;
    private int idade;
    private String tell;

    public Paciente(int codigo, String nome, int idade, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
        this.tell = telefone;
    }

    public int getCodigo() {
    return codigo;
    }

    public String getNome() {
    return this.nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public String getTelefone() {
        return this.tell;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setTelefone(String telefone) {
        this.tell = telefone;
    }

    public String toString() {
        return this.codigo + "\t" + this.nome + "\t" + this.idade + "\t" + this.tell + "\t" +"\n";
    }

}
