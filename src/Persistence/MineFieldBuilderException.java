package Persistence;

public class MineFieldBuilderException extends Exception {

    public MineFieldBuilderException() {
        this("Parámetros de creación Incorrectos");
    }

    public MineFieldBuilderException(String msg) {
        super(msg);
    }
}
