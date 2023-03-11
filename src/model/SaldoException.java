package model;

public class SaldoException extends Exception{

    public SaldoException(String mensaje) {
        super("No es posible retirar un saldo que no se tiene");
    }
    
}
