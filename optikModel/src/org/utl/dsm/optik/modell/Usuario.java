package org.utl.dsm.optik.modell;

public class Usuario {
    
    private int idUsuario;
    private String nombre;
    private String contrasenia;
    private String rol;
    private String lasToken;
    private String dateLastToken;
    
    // Getters and setter

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getLasToken() {
        return lasToken;
    }

    public void setLasToken(String lasToken) {
        this.lasToken = lasToken;
    }

    public String getDateLastToken() {
        return dateLastToken;
    }

    public void setDateLastToken(String dateLastToken) {
        this.dateLastToken = dateLastToken;
    }
    
}
