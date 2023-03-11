package views;

import javax.swing.JOptionPane;

import model.Banco;
import model.Funciones;
public class test {
    
    
    public static void main(String [] args) throws Exception{
     
        menu();
    
    }
    public static  void menu() throws Exception{
        Banco banco= new Banco("Mi banco","Cra 121212");
        int opc;
        String resultado= "";
        do{
            opc = Funciones.leerNumero("""
                                       Ingrese la opcion de los movimientos que desea realizar:  
                                       1. Crear cuenta
                                       2. Consultar informacion
                                       3. Consignar 
                                       4. Retirar
                                       5. Transferir de una cuenta a otra
                                       6. Comparar cuentas
                                       7. Mostrar las cuentas
                                       8. Salir""");
            
            
            switch (opc){
                case 1: 
                    String nombre= Funciones.leerCadena("Ingrese su nombre");
                    String apellidos= Funciones.leerCadena("Ingrese su apellidos");
                    String numCuenta= Funciones.leerCadena("Ingrese el numero de cuenta");
                    String tipoCuenta= Funciones.leerCadena("Ingrese el tipo de cuenta: Ahorro o corriente");
                    double saldo= 0.0;
                    try {
                        resultado= banco.crearCuenta(nombre, apellidos, numCuenta, tipoCuenta, saldo);
                    } catch (Exception e) {
                        resultado= e.getMessage();
                    }
                    JOptionPane.showMessageDialog(null, resultado);;
                    break;
                    
                case 2:
                    String num_Cuenta= Funciones.leerCadena("Ingrese el numero de la cuenta de la cual desea consultar");
                    if(banco.consultarCuenta(num_Cuenta)== null){
                        JOptionPane.showMessageDialog(null, "No hay informacion para mostrar");
                    }else{
                        JOptionPane.showMessageDialog(null,banco.consultarCuenta(num_Cuenta).toString());
                    }
                    break;
                    
                case 3:
                    String cuentaRecargar= Funciones.leerCadena("Ingrese el numero de la cuenta a la cual desea consignar: ");
                    double saldoConsignar= Funciones.leerRealGrande("Ingrese el valor que desea consignar");
                    JOptionPane.showMessageDialog(null, banco.consignarSaldo(cuentaRecargar,saldoConsignar));
                    break;
                    
                case 4:
                    String numcuenta2 = Funciones.leerCadena("Ingrese el número de la cuenta de la que desea retirar:  ");
                    double valorRetirar = Funciones.leerRealGrande("Ingrese el valor a retirar: ");
                    String cr = banco.retirarSaldo(numcuenta2, valorRetirar);
                  
                    JOptionPane.showMessageDialog(null, cr);
                    break;
                    
                case 5:
                    String numCuentaInicio= Funciones.leerCadena("Ingrese la cuenta de donde quiere transferir: ");
                    double valorTransfer= Funciones.leerRealGrande("Ingrese el valor que desea transferir: ");
                    String numCuentaDestino= Funciones.leerCadena("Ingrese la cuenta de destino: ");
                    String resultadoTrans= banco.tranferirSueldo(numCuentaInicio, numCuentaDestino, valorTransfer);
                    JOptionPane.showMessageDialog(null, resultadoTrans);
                    break;
                
                case 6: 
                    String cuenta1= Funciones.leerCadena("Ingrese el numero de una cuenta: ");
                    String cuenta2= Funciones.leerCadena("Ingrese el numero de otra cuenta: ");
                    boolean saldoMayor= banco.compararCuentas(cuenta1,cuenta2);
                    if(saldoMayor){
                        JOptionPane.showMessageDialog(null, "La cuenta con mayor saldo es: "+ cuenta1);
                    }else{
                        JOptionPane.showMessageDialog(null, "La cuenta con mayor saldo es: "+ cuenta2);
                    }
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, banco.mostrarCuentas(banco.getListaCuentas()));
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Saliendo");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
                    break;
                
            }
            
        }while(opc!=7);
        
    }

}


