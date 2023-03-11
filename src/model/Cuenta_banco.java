
package model;

public class Cuenta_banco extends Cliente {
    private String num_cuenta;
    private String tipo_cuenta;
    private double salario_cuenta;

    public Cuenta_banco() {
    }

    public Cuenta_banco(String num_cuenta, String tipo_cuenta, double salario_cuenta) {
        this.num_cuenta = num_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.salario_cuenta = salario_cuenta;
    }



    public Cuenta_banco(String nombres, String apellidos, String num_cuenta, String tipo_cuenta,
            double salario_cuenta) {
        super(nombres, apellidos);
        this.num_cuenta = num_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.salario_cuenta = salario_cuenta;
    }

   public String getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public double getSalario_cuenta() {
        return salario_cuenta;
    }

    public void setSalario_cuenta(double salario_cuenta) {
        this.salario_cuenta = salario_cuenta;
    }

    public boolean verificarNumCuenta(String cuenta){
   
    return getNum_cuenta().equals(cuenta);
   }
   

    @Override
    public String toString() {
        
        return super.toString() + "\nNumero de la cuenta: "+ num_cuenta+ "\nTipo de cuenta: "+ tipo_cuenta+ "\nSaldo de la cuenta: "+ salario_cuenta  ;
    }

    public boolean verficarSaldo(double saldoRetiro){
    return getSalario_cuenta()<saldoRetiro;
    }

   public void retirar(double saldoRetiro)throws SaldoException {
       
        if(salario_cuenta >= saldoRetiro){
            salario_cuenta= salario_cuenta-saldoRetiro;
        }else{
            throw new SaldoException("No se puede reirar un saldo mayor que el sueldo disponible");
        }

   } 

   public void consignar( double saldoC){
        salario_cuenta = salario_cuenta + saldoC;
    }

}
