package com.turnos.utils;

public enum ColorUtils {
        RESET("\u001B[0m"),
        ROJO("\u001B[31m"),
        VERDE("\u001B[32m"),
        AMARILLO("\u001B[33m"),
        AZUL("\u001B[34m"),
        BOLD("\u001B[1m");

        private final String color;

        ColorUtils(String color) {
            this.color = color;
        }

        public String pintar(String texto) {
            return color + texto + RESET.color;
        }

        @Override
        public String toString() {
            return color;
        }
    }
