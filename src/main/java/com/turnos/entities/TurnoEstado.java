package com.turnos.entities;

public enum TurnoEstado {
    EN_ESPERA("En espera"),
    ATENDIDO("Ya atendido");

    private  final String descripcion;

    TurnoEstado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
