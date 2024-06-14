package arquivo;

public class Horarios {
    private String data;
    private String horario;

    public Horarios (String data, String horario) {
        this.data = data;
        this.horario = horario;
    }

    public String getData() {
        return this.data;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String toString() {
        return this.data + "\t" + this.horario + "\n";
    }
    
}
