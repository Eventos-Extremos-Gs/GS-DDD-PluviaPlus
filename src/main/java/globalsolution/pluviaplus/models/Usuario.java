package globalsolution.pluviaplus.models;

public class Usuario extends _BaseEntity {

    private int id_usuario;
    private String nm_usuario;
    private String email;
    private TipoUsuario tp_usuario; // Tecnico, Admin, Pesquisador, Analista

    public Usuario() {}

    public Usuario(int id_usuario, String nm_usuario, String email, TipoUsuario tp_usuario) {
        this.id_usuario = id_usuario;
        this.nm_usuario = nm_usuario;
        this.email = email;
        this.tp_usuario = tp_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuario getTp_usuario() {
        return tp_usuario;
    }

    public void setTp_usuario(TipoUsuario tp_usuario) {
        this.tp_usuario = tp_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nm_usuario='" + nm_usuario + '\'' +
                ", email='" + email + '\'' +
                ", tp_usuario=" + tp_usuario +
                '}';
    }
}
