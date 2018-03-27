import java.rmi.Remote;
import java.rmi.RemoteException;

/***
 * @author Julita Bielaniewicz 227080
 */
public interface CalcObject extends Remote {
	
	public double calculate(double a, double b)
			 throws RemoteException;
} 
