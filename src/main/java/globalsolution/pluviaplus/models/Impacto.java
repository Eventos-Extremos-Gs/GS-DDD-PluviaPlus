package globalsolution.pluviaplus.models;

public class Impacto extends _BaseEntity {

    private int id_impacto;
    private int id_producao; // FK para produção
    private double co2_economizado_kg;
    private int pessoas_beneficiadas;

    public Impacto() {}

    public Impacto(int id_impacto, int id_producao, double co2_economizado_kg, int pessoas_beneficiadas) {
        this.id_impacto = id_impacto;
        this.id_producao = id_producao;
        this.co2_economizado_kg = co2_economizado_kg;
        this.pessoas_beneficiadas = pessoas_beneficiadas;
    }

    public int getId_impacto() {
        return id_impacto;
    }

    public void setId_impacto(int id_impacto) {
        this.id_impacto = id_impacto;
    }

    public int getId_producao() {
        return id_producao;
    }

    public void setId_producao(int id_producao) {
        this.id_producao = id_producao;
    }

    public double getCo2_economizado_kg() {
        return co2_economizado_kg;
    }

    public void setCo2_economizado_kg(double co2_economizado_kg) {
        this.co2_economizado_kg = co2_economizado_kg;
    }

    public int getPessoas_beneficiadas() {
        return pessoas_beneficiadas;
    }

    public void setPessoas_beneficiadas(int pessoas_beneficiadas) {
        this.pessoas_beneficiadas = pessoas_beneficiadas;
    }

    @Override
    public String toString() {
        return "Impacto{" +
                "id_impacto=" + id_impacto +
                ", id_producao=" + id_producao +
                ", co2_economizado_kg=" + co2_economizado_kg +
                ", pessoas_beneficiadas=" + pessoas_beneficiadas +
                '}';
    }
}