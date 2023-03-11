package model;


import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Banco {
    private String nombreBanco;
    private String direccionBanco;
    private ArrayList<Cuenta_banco> listaCuentas;
    
    public Banco() { 
    }
    /**
     * 
     * @param nombreBanco
     * @param dereccionBanco
     * @param listaCuentas 
     */
    public Banco(String nombreBanco, String direccionBanco) {
        this.nombreBanco = nombreBanco;
        this.direccionBanco = direccionBanco;
        this.listaCuentas = new ArrayList<>();
    }

    
    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getDereccionBanco() {
        return direccionBanco;
    }

    public void setDereccionBanco(String dereccionBanco) {
        this.direccionBanco = dereccionBanco;
    }

    public ArrayList<Cuenta_banco> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta_banco> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
    
    /**
     * 
     * @param nombres
     * @param apellidos
     * @param num_cuenta
     * @param tipo_cuenta
     * @param salario_cuenta
     * @return
     * @throws Exception 
     */
    public  String crearCuenta(String nombres, String apellidos, String num_cuenta, String tipo_cuenta, double salario_cuenta) throws Exception {
        
        String salida= "Cuenta creada con exito";
        boolean cuentaEncontrada= verificarCuenta(num_cuenta);
        if(cuentaEncontrada){
            throw new Exception("La cuenta ya existe");
        }else{
            Cuenta_banco nuevaCuenta= new Cuenta_banco(nombres,apellidos,num_cuenta,tipo_cuenta,salario_cuenta);
            listaCuentas.add(nuevaCuenta);
        }
        return salida;
    }
    /**
     * 
     * @param num_cuenta
     * @return 
     */
    private boolean verificarCuenta(String num_cuenta){
        boolean encontrado= false;
        for (Cuenta_banco cuenta : listaCuentas) {
            if(cuenta.verificarNumCuenta(num_cuenta)){
                encontrado= true;
                return encontrado;
            }
        }
        
        return encontrado;
    }
    
    /**
     * 
     * @throws Exception 
     */
        public  void menu() throws Exception{
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
                                       7. Salir""");
            
            
            switch (opc){
                case 1: 
                    String nombre= Funciones.leerCadena("Ingrese su nombre");
                    String apellidos= Funciones.leerCadena("Ingrese su apellidos");
                    String numCuenta= Funciones.leerCadena("Ingrese el numero de cuenta");
                    String tipoCuenta= Funciones.leerCadena("Ingrese el tipo de cuenta: Ahorro o corriente");
                    double saldo= 0.0;
                    try {
                        resultado= crearCuenta(nombre, apellidos, numCuenta, tipoCuenta, saldo);
                    } catch (Exception e) {
                        resultado= e.getMessage();
                    }
                    JOptionPane.showMessageDialog(null, resultado);;
                    break;
                    
                case 2:
                    String num_Cuenta= Funciones.leerCadena("Ingrese el numero de la cuenta de la cual desea consultar");
                    JOptionPane.showMessageDialog(null,consultarCuenta(num_Cuenta).toString());
                    if(consultarCuenta(num_Cuenta)== null){
                        JOptionPane.showMessageDialog(null, "No hay informacion para mostrar");
                    }
                    break;
                    
                case 3:
                    String cuentaRecargar= Funciones.leerCadena("Ingrese el numero de la cuenta a la cual desea consignar: ");
                    double saldoConsignar= Funciones.leerRealGrande("Ingrese el valor que desea consignar");
                    JOptionPane.showMessageDialog(null, consignarSaldo(cuentaRecargar,saldoConsignar));
                    break;
                    
                case 4:
                    String numcuenta2 = Funciones.leerCadena("Ingrese el número de la cuenta de la que desea retirar:  ");
                    double valorRetirar = Funciones.leerRealGrande("Ingrese el valor a retirar: ");
                    String cr = retirarSaldo(numcuenta2, valorRetirar);
                  
                    JOptionPane.showMessageDialog(null, cr);
                    break;
                    
                case 5:
                    String numCuentaInicio= Funciones.leerCadena("Ingrese la cuenta de donde quiere transferir: ");
                    double valorTransfer= Funciones.leerRealGrande("Ingrese el valor que desea transferir: ");
                    String numCuentaDestino= Funciones.leerCadena("Ingrese la cuenta de destino: ");
                    String resultadoTrans= tranferirSueldo(numCuentaInicio, numCuentaDestino, valorTransfer);
                    JOptionPane.showMessageDialog(null, resultadoTrans);
                    break;
                
                case 6: 
                    String cuenta1= Funciones.leerCadena("Ingrese el numero de una cuenta: ");
                    String cuenta2= Funciones.leerCadena("Ingrese el numero de otra cuenta: ");
                    boolean saldoMayor= compararCuentas(cuenta1,cuenta2);
                    if(saldoMayor){
                        JOptionPane.showMessageDialog(null, "La cuenta con mayor saldo es: "+ cuenta1);
                    }else{
                        JOptionPane.showMessageDialog(null, "La cuenta con mayor saldo es: "+ cuenta2);
                    }
                    break;
                
                case 7:
                    JOptionPane.showMessageDialog(null, "Saliendo");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
                    break;
                
            }
            
        }while(opc!=7);
        
    }
    /**
     * Primero se revisa el que la lista de cuentas no este vacia, despues se recorre  la lista para encontrar la cuenta y mostrar la informacion
     * @param num_Cuenta 
     * 
     */
    private Cuenta_banco consultarCuenta(String num_Cuenta) {
        if(!listaCuentas.isEmpty()){
            for (int i = 0; i < listaCuentas.size(); i++) {
                if(listaCuentas.get(i).verificarNumCuenta(num_Cuenta)){
                   return listaCuentas.get(i);
                }
            }
        }
        
           return null;
        
        
    }
    
    /**
     * 
     * @param numcuenta
     * @param saldoConsignar
     * @return 
     * @throws Exception
     */
    private String consignarSaldo(String numcuenta, double saldoConsignar) throws Exception{
            Cuenta_banco cuenta  = new Cuenta_banco();
            String exit = "";

            cuenta = consultarCuenta(numcuenta);
            if(cuenta != null){
                cuenta.consignar(saldoConsignar);
                exit= "Consignacion de saldo exitosa su nuevo saldo es: "+cuenta.getSalario_cuenta();
             }   
            else{
                throw new Exception("La cuenta no existe");
            }
            return exit;
        }
  /**
   * 
   * @param numcuenta
   * @param saldoRetirar
   * @return 
   */  
    private String retirarSaldo(String numcuenta, double saldoRetirar) throws CuentaException{
        String exit= "true";
        Cuenta_banco cuenta= new Cuenta_banco();
        cuenta= consultarCuenta(numcuenta);
        if(cuenta != null){
            try {
                cuenta.retirar(saldoRetirar);
                exit= "Retiro exitoso su nuevo saldo es de: "+cuenta.getSalario_cuenta() ;
            } catch (CuentaException cuentaE) {
                exit= "Excepcion: " + cuentaE.getMessage();
            }
            
        }else{
            throw new CuentaException("No existe una cuenta");
        }


        return exit;
    }
    /**
     * Primero se revisa de que ambas cuentas existen
     * Tambien se comprueba que no se pueda tranferir de una cuenta a si misma
     * Se confirma que no tranfiera mas dinero del que tiene en la cuenta
     * Se actualizan ambos saldos
     * @param numCuentaInicio
     * @param numCuentaDestino
     * @param valorTransfer 
     * @throws Exception
     * @throws HeadlessException
     */
    private String tranferirSueldo(String numCuentaInicio, String numCuentaDestino, double valorTransfer) throws CuentaException{    
        String exit= "";    
        Cuenta_banco cuentaI= new Cuenta_banco();
        Cuenta_banco cuentaF= new Cuenta_banco();
        cuentaI= consultarCuenta(numCuentaInicio);
        cuentaF= consultarCuenta(numCuentaDestino);
        
        if(verificarCuenta(numCuentaInicio) && verificarCuenta(numCuentaDestino)){
            if(cuentaI != null && cuentaF != null){
                if(!cuentaI.equals(cuentaF)){
                    try {
                        cuentaI.retirar(valorTransfer);
                        cuentaF.consignar(valorTransfer);
                        exit= "Transferencia exitosa, consulte los nuevos saldos con la opcion 2 del menu";
                    } catch (CuentaException cuentaE) {
                        exit= "Excepcion" + cuentaE.getMessage(); 
                    }
                }else{
                    throw new CuentaException("Excepcion: la cuenta de destino no puede ser igual a la original");
                }
            }else{
                throw new CuentaException("Excepcion: Una de las dos cuentas esta vacia/null");
            }
        }else{
            throw new CuentaException("Excepcion: Una de las cuentas no esta en la base");
        }
        return exit;
    }
    
    /**
     * 
     * @param cuenta1
     * @param cuenta2
     * @return 
     */
    private boolean compararCuentas(String cuenta1, String cuenta2){
        boolean salida= false;
        Cuenta_banco cuentaI= new Cuenta_banco();
        Cuenta_banco cuentaII= new Cuenta_banco();
        cuentaI= consultarCuenta(cuenta1);
        cuentaII= consultarCuenta(cuenta2);
        
        if (verificarCuenta(cuenta1) && verificarCuenta(cuenta2)  ){
           if(cuentaI.getSalario_cuenta()>= cuentaII.getSalario_cuenta()){
                salida= true;
           }
            
            
        }
        
        return salida;
    }
}

