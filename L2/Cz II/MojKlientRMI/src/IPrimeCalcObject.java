package MojKlientRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfejs zdalnego obiektu obliczeniowego
 * @author Imie Nazwisko 22xxxx
 */
public interface IPrimeCalcObject extends Remote{
    PrimeResultType calculate(PrimeInputType inputParam)
        throws RemoteException;
}
