package co.edu.uniquindio.tienda.modelo;

import co.edu.uniquindio.tienda.enumerados.TipoUsuario;

public class Usuario extends Persona{

    private String email;
    private String contrasena;

    private TipoUsuario usuario;

    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TipoUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TipoUsuario usuario) {
        this.usuario = usuario;
    }
}
