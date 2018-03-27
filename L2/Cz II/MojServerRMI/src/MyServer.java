import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Glowna klasa serwera RMI
 * @author Julita Bielaniewicz 227080
 */
public class MyServer {
    /**
     * Glowna metoda serwera RMI rejestrujaca obiekty obliczeniowe
     * @param args lista adresow poszczegolnych serwisow postaci //host_address/service_name
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("You have to enter RMI object address in the form: " +
                    "//host_address/service_name");
            return;
        }

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        /*try {

            Registry reg = LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        */
        try {
            System.setProperty("java.rmi.server.hostname","192.168.43.183");
            CalcObjImpl implObiektu = new CalcObjImpl();
            java.rmi.Naming.rebind(args[0], implObiektu);
            CalcObjImpl2 implObiektu2 = new CalcObjImpl2();
            java.rmi.Naming.rebind(args[1], implObiektu2);
            PrimeCalcObject primeCalcObject = new PrimeCalcObject();
            java.rmi.Naming.rebind(args[2], primeCalcObject);
            System.out.println("Server is registered now");
        } catch (Exception ex) {
            System.out.println("Server cannot be registered");
            ex.printStackTrace();
        }
    }
}
