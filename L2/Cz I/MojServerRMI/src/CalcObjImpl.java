import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcObjImpl extends UnicastRemoteObject
implements CalcObject {
	private static final long serialVersionUID = 101L; 

	protected CalcObjImpl() throws RemoteException {
		super();
	}

	@Override
	public double calculate(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		double wynik = a + b;
		return wynik; 
	}

}
