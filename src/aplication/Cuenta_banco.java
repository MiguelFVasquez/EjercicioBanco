
package aplication;


public class Cuenta_banco {
    private String nombres;
    private String apellidos;
    private String num_cuenta;
    private String tipo_cuenta;
    private double salario_cuenta;

    public Cuenta_banco() {
    }

    public Cuenta_banco(String nombres, String apellidos, String num_cuenta, String tipo_cuenta, double salario_cuenta) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.num_cuenta = num_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.salario_cuenta = salario_cuenta;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    @Override
    public String toString() {
        return "Nombre: " + nombres + " " + apellidos + "\n Numero de cuenta: " + num_cuenta + "\n Tipo de cuenta: " + tipo_cuenta + "\n Saldo: " + salario_cuenta;
    }
    
   public boolean verificarNumCuenta(String cuenta){
   
    return getNum_cuenta().equals(cuenta);
   }
   
   
   public boolean verficarSaldo(double saldoRetiro){
    return getSalario_cuenta()<saldoRetiro;
   }


}
