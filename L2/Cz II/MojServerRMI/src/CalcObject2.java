import java.rmi.Remote;
import java.rmi.RemoteException;

/***
 * @author Imie Nazwisko 22xxxx
 */

public interface CalcObject2 extends Remote {
	public ResultType calculate(InuptType inputParam) throws RemoteException;
}
