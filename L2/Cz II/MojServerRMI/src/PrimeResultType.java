package MojServerRMI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa-wynik zawierajaca liste liczb pierwszych
 * @author Imie Nazwisko 22xxxx
 */
public class PrimeResultType implements Serializable {
    private static final long serialVersionUID = 104L;
    private String resultDescription;
    private List<Integer> primes;

    public PrimeResultType(){
        primes = new ArrayList<Integer>();
        resultDescription = "";
    }

    /**
     * Dodatkowe informacje dot. wykonania operacji
     * @return Dodatkowe informacje dot. wykonania operacji
     */
    public String getResultDescription() {
        return resultDescription;
    }

    /**
     * Ustawia dodatkowe informacje dot. wykonania operacji
     * @param resultDescription String zawierajacy dodatkowe informacje
     */
    public void setResultDescription(String resultDescription) {
        this.resultDescription += "\n" + resultDescription;
    }

    /**
     * Lista liczb pierwszych
     * @return Lista liczb pierwszych
     */
    public List<Integer> getPrimes() {
        return primes;
    }

    /**
     * Ustawia liste liczb pierwszych
     * @param primes Lista liczb pierwszych
     */
    public void setPrimes(List<Integer> primes) {
        this.primes.addAll(primes);
    }
}
