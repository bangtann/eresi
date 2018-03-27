import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Klasa-zadanie wyznaczajaca liczby pierwsze
 * @author Julita Bielaniewicz 227080
 */
public class PrimeCalcObject extends UnicastRemoteObject implements IPrimeCalcObject  {
    int THREATS = 10;
    /**
     * Konstruktor klasy-zadania
     * @throws RemoteException
     */
    public PrimeCalcObject() throws RemoteException {
        super();
    }

    /**
     * Metoda wyznaczajaca liczby pierwsze z zadanego przedzialu
     * @param input Obiekt-parametr zawierajacy przedzial liczb
     * @return Obiekt-wynik zawierający opis wykonania oraz listę liczb pierwszych
     * @throws RemoteException
     */
    @Override
    public PrimeResultType calculate(PrimeInputType input) throws RemoteException {
        int diff = (input.getTo() - input.getFrom())/THREATS;
        PrimeResultType pimeResultType = new PrimeResultType();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREATS; i++) {
            PrimeInputType primeInputType = new PrimeInputType();
            primeInputType.setFrom(input.getFrom() + i*diff);
            primeInputType.setTo(input.getFrom() + (i+1)*diff);
            threads.add(new Thread( () -> {
                _calculate(primeInputType, pimeResultType);
            }));
        }
        try {
            for (Thread t : threads) {
                t.run();
            }
            for (Thread t : threads) {
                t.join();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return pimeResultType;
    }

    /**
     * Metoda wyznaczajaca liczby pierwsze z zadanego przedzialu
     * @param input Obiekt-parametr zawierajacy przedzial liczb
     * @param result Wynik działania
     * @return
     */
    private PrimeResultType _calculate(PrimeInputType input, PrimeResultType result){
        result.setPrimes(new ArrayList<Integer>());
        int from = input.getFrom();
        int to = input.getTo();
        if (from > to) {
            result.setResultDescription("Nieprawidlowy przedzial ["+from+", "+to+"].");
            return result;
        }
        for (int n=from; n <= to; n++) {
            if (isPrime(n))
                result.getPrimes().add(n);
        }
        result.setResultDescription("Znaleziono " + result.getPrimes().size() +
                " liczb pierwszych w przedziale ["+from+", "+to+"].");
        return result;
    }

    /**
     * Metoda sprawdzajaca czy dana liczba jest liczba pierwsza
     * @param n sprawdzana liczba
     * @return prawda gdy liczba jest pierwsza
     */
    private boolean isPrime(int n) {
        if (n <= 1)
            return false;
        int i = 2;
        boolean divisorFound = false;
        while (!divisorFound && i <= n/2) {
            divisorFound = n % i == 0;
            i++;
        }
        return !divisorFound;
    }
}
