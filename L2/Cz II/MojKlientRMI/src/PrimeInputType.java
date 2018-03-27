package MojKlientRMI;

import java.io.Serializable;

/**
 * Klasa-parametr opisujaca przedzial liczb
 * @author Imie nazwisko 22xxxx
 */
public class PrimeInputType implements Serializable {
    private static final long serialVersionUID = 103L;
    private int from;
    private int to;

    /**
     * Pierwsza liczba w przedziale
     * @return Pierwsza liczba w przedziale
     */
    public int getFrom() {
        return from;
    }

    /**
     * Ostatnia liczba w przedziale
     * @return Ostatnia liczba w przedziale
     */
    public int getTo() {
        return to;
    }

    /**
     * Ustawia poczatek przedzialu
     * @param from pierwsza liczba w przedziale
     */
    public void setFrom(int from) {
        this.from = from;
    }

    /**
     * Ustawia koniec przedzialu
     * @param to ostatnia liczba w przedziale
     */
    public void setTo(int to) {
        this.to = to;
    }
}