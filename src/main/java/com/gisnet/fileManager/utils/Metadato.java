
package com.gisnet.fileManager.utils;

import java.io.Serializable;


public class Metadato implements Serializable{
    private String nombre;
    private String tipo;
    private String valor;

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
