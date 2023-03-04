
package aplication;


public class Cuenta_bancaria {
    private String nombres;
    private String apellidos;
    private String num_cuenta;
    private String tipo_cuenta;
    private double salario_cuenta;

    public Cuenta_bancaria() {
    }

    public Cuenta_bancaria(String nombres, String apellidos, String num_cuenta, String tipo_cuenta, double salario_cuenta) {
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
        return "cuenta_bancaria{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", num_cuenta=" + num_cuenta + ", tipo_cuenta=" + tipo_cuenta + ", salario_cuenta=" + salario_cuenta + '}';
    }
    
   
    
}
