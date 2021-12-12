package com.example.proyectofinal.models;

public class Harry {
    private int id;
    private String personaje;
    private String apodo;
    private String interpretado_por;
    private String imagen;

    public Harry(int id, String personaje, String apodo, String interpretado_por, String imagen) {
        this.id = id;
        this.personaje = personaje;
        this.apodo = apodo;
        this.interpretado_por = interpretado_por;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getInterpretado_por() {
        return interpretado_por;
    }

    public void setInterpretado_por(String interpretado_por) {
        this.interpretado_por = interpretado_por;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
