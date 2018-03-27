package MojServerRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/***
 * @author Imie Nazwisko 22xxxx
 */

public interface CalcObject extends Remote {
	
	public double calculate(double a, double b)
			 throws RemoteException;
} 
