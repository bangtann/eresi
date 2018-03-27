import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Julita Bielaniewicz 227080
 */
public interface IPrimeCalcObject extends Remote{
    PrimeResultType calculate(PrimeInputType inputParam)
            throws RemoteException;

}
