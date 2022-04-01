
package com.gisnet.fileManager.utils;

import java.io.Serializable;
import java.util.List;


public class Metadato implements Serializable{
    private String nombre;
    private String tipo;
    private String valor;
    private List<String> opciones;

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    
}
