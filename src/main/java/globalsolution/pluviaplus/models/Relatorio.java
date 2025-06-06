package globalsolution.pluviaplus.models;

import java.time.LocalDate;

public class Relatorio extends _BaseEntity {

    private int id_relatorio;
    private TipoRelatorio tipo;
    private LocalDate data_geracao;
    private int id_usuario;

    public Relatorio() {}

    public Relatorio(int id_relatorio, TipoRelatorio tipo, LocalDate data_geracao, int id_usuario) {
        this.id_relatorio = id_relatorio;
        this.tipo = tipo;
        this.data_geracao = data_geracao;
        this.id_usuario = id_usuario;
    }

    public int getId_relatorio() {
        return id_relatorio;
    }

    public void setId_relatorio(int id_relatorio) {
        this.id_relatorio = id_relatorio;
    }

    public TipoRelatorio getTipo() {
        return tipo;
    }

    public void setTipo(TipoRelatorio tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData_geracao() {
        return data_geracao;
    }

    public void setData_geracao(LocalDate data_geracao) {
        this.data_geracao = data_geracao;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "id_relatorio=" + id_relatorio +
                ", tipo=" + tipo +
                ", data_geracao=" + data_geracao +
                ", id_usuario=" + id_usuario +
                '}';
    }
}
