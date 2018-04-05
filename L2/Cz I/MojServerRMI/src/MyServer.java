import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
 
public class MyServer {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You have to enter RMI object address in the form: //host_address/service_name");
            return;
        }
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        Registry reg = null;
        try {
            reg = LocateRegistry.createRegistry(5555);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
        try {
            CalcObjImpl implObiektu = new CalcObjImpl();
            java.rmi.Naming.rebind(args[0], implObiektu);
            CalcObjImpl2 implObiektu2 = new CalcObjImpl2();
            java.rmi.Naming.rebind(args[1], implObiektu2);
            System.out.println("Server is registered now :-)");
            System.out.println("Press Crl+C to stop...");
        	
//        	CalcObjImpl implObiektu = new CalcObjImpl();
//        	reg.rebind("MyServerCalcObject1", implObiektu);
//            CalcObjImpl2 implObiektu2 = new CalcObjImpl2();
//            java.rmi.Naming.rebind(args[1], implObiektu2);
//            System.out.println("Server is registered now :-)");
//            System.out.println("Press Crl+C to stop...");
            
        } catch (Exception e) {
            System.out.println("SERVER CAN'T BE REGISTERED!");
            e.printStackTrace();
            return;
        }
    }
}