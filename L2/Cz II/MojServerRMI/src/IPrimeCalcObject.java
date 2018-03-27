import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Imie Nazwisko 22xxxx
 */
public interface IPrimeCalcObject extends Remote{
    PrimeResultType calculate(PrimeInputType inputParam)
            throws RemoteException;

}
