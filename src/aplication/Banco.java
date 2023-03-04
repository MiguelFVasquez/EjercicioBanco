package aplication;


import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Banco {
    private String nombreBanco;
    private String direccionBanco;
    private ArrayList<Cuenta_bancaria> listaCuentas;

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

    public ArrayList<Cuenta_bancaria> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta_bancaria> listaCuentas) {
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
    public String crearCuenta(String nombres, String apellidos, String num_cuenta, String tipo_cuenta, double salario_cuenta) throws Exception {
        
        String salida= "Cuenta creada con exito";
        boolean cuentaEncontrada= verificarCuenta(num_cuenta);
        if(cuentaEncontrada){
            throw new Exception("La cuenta ya existe");
        }else{
            Cuenta_bancaria nuevaCuenta= new Cuenta_bancaria(nombres,apellidos,num_cuenta,tipo_cuenta,salario_cuenta);
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
        for (Cuenta_bancaria cuenta : listaCuentas) {
            if(cuenta.getNum_cuenta().equals(num_cuenta)){
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
                    System.out.println(resultado);
                    break;
                    
                case 2:
                    String num_Cuenta= Funciones.leerCadena("Ingrese el numero de la cuenta de la cual desea consultar");
                    consultarCuenta(num_Cuenta);
                    break;
                    
                case 3:
                    String cuentaRecargar= Funciones.leerCadena("Ingrese el numero de la cuenta a la cual desea consignar: ");
                    double saldoConsignar= Funciones.leerRealGrande("Ingrese el valor que desea consignar");
                    JOptionPane.showMessageDialog(null, consignarSaldo(cuentaRecargar,saldoConsignar));
                    break;
                    
                case 4:
                    String result= "";
                    String numcuenta2 = Funciones.leerCadena("Ingrese el número de la cuenta de la que desea retirar:  ");
                    double valorRetirar = Funciones.leerRealGrande("Ingrese el valor a retirar: ");
                    boolean cr = retirarSaldo(numcuenta2, valorRetirar);
                    if(cr){
                        result= "El retiro ha sido exitoso";
                    }else{
                        result= "No se puede retirar una cantidad mayor al saldo disponible";
                    }
                    JOptionPane.showMessageDialog(null, result);
                    break;
                    
                case 5:
                    String numCuentaInicio= Funciones.leerCadena("Ingrese la cuenta de donde quiere transferir: ");
                    double valorTransfer= Funciones.leerRealGrande("Ingrese el valor que desea transferir: ");
                    String numCuentaDestino= Funciones.leerCadena("Ingrese la cuenta de destino: ");
                    tranferirSueldo(numCuentaInicio, numCuentaDestino, valorTransfer);
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
     * 
     * @param num_Cuenta 
     */
    private void consultarCuenta(String num_Cuenta) {
        String salida= "";
        if(!listaCuentas.isEmpty()){
            for (int i = 0; i < listaCuentas.size(); i++) {
                if(listaCuentas.get(i).getNum_cuenta().equals(num_Cuenta)){
                    salida+="Nombre: " +listaCuentas.get(i).getNombres() + " "+listaCuentas.get(i).getApellidos()+ "\n Numero de cuenta: "+listaCuentas.get(i).getNum_cuenta()+
                            "\n Tipo de cuenta "+listaCuentas.get(i).getTipo_cuenta()+ "\n Saldo de la cuenta "+listaCuentas.get(i).getSalario_cuenta();
                }
            }
          
        }else{
            salida= "No hay cuentas para mostar";
        }
        JOptionPane.showMessageDialog(null, salida);
        
    }
    
    /**
     * 
     * @param numcuenta
     * @param saldoConsignar
     * @return 
     */
    public String consignarSaldo(String numcuenta, double saldoConsignar){
            double saldoCuenta = 0;
            String exit = "";
            if(verificarCuenta(numcuenta)==true){
                for (int i = 0; i < listaCuentas.size(); i++) {
                    if(listaCuentas.get(i).getNum_cuenta().equals(numcuenta)){
                        saldoCuenta = listaCuentas.get(i).getSalario_cuenta();
                        saldoCuenta = saldoCuenta + saldoConsignar;
                        listaCuentas.get(i).setSalario_cuenta(saldoCuenta);
                        exit = "Valor consignado con éxito" + "\n" +"Saldo actual: " +saldoCuenta;
                    }
                }
            }
            return exit;
        }
  /**
   * 
   * @param numcuenta
   * @param saldoRetirar
   * @return 
   */  
    public boolean retirarSaldo(String numcuenta, double saldoRetirar){
        double saldoCuenta = 0;
        boolean exit= true;
        if(verificarCuenta(numcuenta)==true){
            for (int i = 0; i < listaCuentas.size(); i++) {
                if(listaCuentas.get(i).getNum_cuenta().equals(numcuenta)){
                    saldoCuenta = listaCuentas.get(i).getSalario_cuenta();
                    if(saldoCuenta<saldoRetirar){
                        exit = false;
                    }
                    else{ 
                        saldoCuenta = saldoCuenta-saldoRetirar;
                        listaCuentas.get(i).setSalario_cuenta(saldoCuenta);
                        exit =true;
                    }

                }
            }
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
     */
    public void tranferirSueldo(String numCuentaInicio, String numCuentaDestino, double valorTransfer) {        
        double saldoCuentaOrigen= 0, nuevoSaldoCuentaOrigen=0;
        double saldoActualCuentaDestino=0, nuevoSaldoCuentaDestino= 0;
        if(verificarCuenta(numCuentaInicio) && verificarCuenta(numCuentaDestino)){
            if(numCuentaInicio.equals(numCuentaDestino)){
                JOptionPane.showMessageDialog(null, "La cuenta de destino no puede ser la misma de origen");
            }else{
                if(retirarSaldo(numCuentaInicio,valorTransfer)){
                    for (int i = 0; i < listaCuentas.size(); i++) {
                        if(listaCuentas.get(i).getNum_cuenta().equals(numCuentaDestino)){
                            saldoActualCuentaDestino= listaCuentas.get(i).getSalario_cuenta();
                            nuevoSaldoCuentaDestino= saldoActualCuentaDestino+valorTransfer;
                            
                            listaCuentas.get(i).setSalario_cuenta(nuevoSaldoCuentaDestino);
                            JOptionPane.showMessageDialog(null,"Tranferencia exitosa, consulte su nuevo saldo en la opcion 2 del menu principal");
                            if(listaCuentas.get(i).getNum_cuenta().equals(numCuentaInicio)){
                                saldoCuentaOrigen= listaCuentas.get(i).getSalario_cuenta();
                                nuevoSaldoCuentaOrigen= saldoCuentaOrigen-valorTransfer;
                                listaCuentas.get(i).setSalario_cuenta(nuevoSaldoCuentaOrigen);
    
                            }
                        }
                                                    
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"No se puede transferir un saldo mayor del que se tiene disponible");
                }
                        
            }
        }else{
            JOptionPane.showMessageDialog(null,"Una de las cuentas no existe");
        }
        
    }
    
    /**
     * 
     * @param cuenta1
     * @param cuenta2
     * @return 
     */
    private boolean compararCuentas(String cuenta1, String cuenta2){
        boolean salida= false;
        double saldoCuenta1=0, saldoCuenta2=0;
        
        if (verificarCuenta(cuenta1) && verificarCuenta(cuenta2)  ){
            for (int i = 0; i < listaCuentas.size(); i++) {
                if(listaCuentas.get(i).getNum_cuenta().equals(cuenta1)){
                    saldoCuenta1= listaCuentas.get(i).getSalario_cuenta();
                }
                if(listaCuentas.get(i).getNum_cuenta().equals(cuenta2)){
                    saldoCuenta2= listaCuentas.get(i).getSalario_cuenta();
                }
            }
            
            if(saldoCuenta1>saldoCuenta2){
                salida= true;
            }
            
            
        }
        
        return salida;
    }
}
