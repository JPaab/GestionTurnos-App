package com.turnos.entities;

//aqui enum con los dos estados posibles que puede tener un turno
public enum TurnoEstado {
    EN_ESPERA("En espera"),
    ATENDIDO("Ya atendido");

    private  final String descripcion;
    TurnoEstado(String descripcion) {
        this.descripcion = descripcion;
    }

    //aqui enum con los dos estados posibles que puede tener un turno
    public String getDescripcion() {
        return descripcion;
    }
}
