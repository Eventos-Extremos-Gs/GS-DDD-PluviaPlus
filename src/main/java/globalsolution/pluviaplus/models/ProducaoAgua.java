package globalsolution.pluviaplus.models;

import java.time.LocalDate;

public class ProducaoAgua extends _BaseEntity {

    private int id_producao;
    private int id_dispositivo; // FK para dispositivo
    private LocalDate data_producao;
    private double litros_gerados;

    public ProducaoAgua() {}

    public ProducaoAgua(int id_producao, int id_dispositivo, LocalDate data_producao, double litros_gerados) {
        this.id_producao = id_producao;
        this.id_dispositivo = id_dispositivo;
        this.data_producao = data_producao;
        this.litros_gerados = litros_gerados;
    }

    public int getId_producao() {
        return id_producao;
    }

    public void setId_producao(int id_producao) {
        this.id_producao = id_producao;
    }

    public int getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(int id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public LocalDate getData_producao() {
        return data_producao;
    }

    public void setData_producao(LocalDate data_producao) {
        this.data_producao = data_producao;
    }

    public double getLitros_gerados() {
        return litros_gerados;
    }

    public void setLitros_gerados(double litros_gerados) {
        this.litros_gerados = litros_gerados;
    }

    @Override
    public String toString() {
        return "ProducaoAgua{" +
                "id_producao=" + id_producao +
                ", id_dispositivo=" + id_dispositivo +
                ", data_producao=" + data_producao +
                ", litros_gerados=" + litros_gerados +
                '}';
    }
}
