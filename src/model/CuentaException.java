package model;

public class CuentaException extends Exception{

    public CuentaException(String mensaje) {
        super("No es posible retirar un saldo que no se tiene");
    }
    
}
