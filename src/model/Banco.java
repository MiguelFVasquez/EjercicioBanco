package model;


import java.awt.HeadlessException;
import java.util.ArrayList;

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
    /**
     * Primero se revisa el que la lista de cuentas no este vacia, despues se recorre  la lista para encontrar la cuenta y mostrar la informacion
     * @param num_Cuenta 
     * 
     */
    public Cuenta_banco consultarCuenta(String num_Cuenta) {
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
    public String consignarSaldo(String numcuenta, double saldoConsignar) throws CuentaException{
            Cuenta_banco cuenta  = new Cuenta_banco();
            String exit = "";

            cuenta = consultarCuenta(numcuenta);
            if(cuenta != null){
                cuenta.consignar(saldoConsignar);
                exit= "Consignacion de saldo exitosa su nuevo saldo es: "+cuenta.getSalario_cuenta();
             }   
            else{
                throw new CuentaException("Cuenta inexistente");
            }
            return exit;
        }
  /**
   * 
   * @param numcuenta
   * @param saldoRetirar
   * @return 
   */  
    public String retirarSaldo(String numcuenta, double saldoRetirar) throws CuentaException, SaldoException{
        String exit= "true";
        Cuenta_banco cuenta= new Cuenta_banco();
        cuenta= consultarCuenta(numcuenta);
        if(cuenta != null){
            try {
                cuenta.retirar(saldoRetirar);
                exit= "Retiro exitoso su nuevo saldo es de: "+cuenta.getSalario_cuenta() ;
            } catch (SaldoException saldoE) {
                exit= "Excepcion: " + saldoE.getMessage();
            }
            
        }else{
            throw new CuentaException("La no cuenta no existe");
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
     * @throws SaldoException
     * @throws Exception
     * @throws HeadlessException
     */
    public String tranferirSueldo(String numCuentaInicio, String numCuentaDestino, double valorTransfer) throws CuentaException, SaldoException{    
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
                    } catch (SaldoException cuentaE) {
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
    public boolean compararCuentas(String cuenta1, String cuenta2){
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


    /**
     * @param listaCuentas
     * @return
     */
    public String mostrarCuentas(ArrayList<Cuenta_banco> listaCuentas){
        StringBuilder sb = new StringBuilder();
    
        listaCuentas.stream()
                   .forEach(s -> sb.append(s).append("\n--------------"));
    
        return sb.toString();
    }
    
    


}

