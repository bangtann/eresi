import java.rmi.Remote;
import java.rmi.RemoteException;

/***
 * @author Julita Bielaniewicz 227080
 */
public interface CalcObject2 extends Remote {
	public ResultType calculate(InputType inputParam)
			 throws RemoteException; 

}
