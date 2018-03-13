/**
 * @author Kamil Kanclerz 229721
 */

import org.apache.xmlrpc.WebServer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

public class serwerRPC {


    /**
     * Metoda, która zwraca opis wszystkich metod dostępnych dla klienta
     * @return opis metod
     */
    public String show() {
        return
                "\tshow() - wyswietla informacje o dostepnych metodach"
                        + "\n\tpi(int: points, int: sleepTime) - oblicza przyblizona wartosc PI na podstawie trafien vs. nietrafien po sleepTime milisekundach czasu"
                        + "\n\ttriangle(int: a, int: b, int: c) - zwraca nazwe rodzaju trojkata na podstawie dlugosci bokow a, b i c"
                        + "\n\twelcome(String: name, String: country) - zwraca powitanie z uwzglednieniem podanego imienia oraz wyswietla obecna date z nazwa miesiaca w jezyku odpowiadajacym podanemu skrotowi";
    }



    /*** Oblicza wartość PI na podstawie liczby trafień vs. nietrafień
     * @param points ilość punktów
     * @param sleepTime czas uśpienia wątku w milisekundach
     * @return obliczona, zaokrąglona wartość PI
     */

    public double pi(int points, int sleepTime) {
        Random randomGen = new Random();
        int hits = 0;
        double PI = 0;

        for (int i = 1; i <= points; i++) {
            double x = (randomGen.nextDouble()) * 2 - 1.0;
            double y = (randomGen.nextDouble()) * 2 - 1.0;

            if (isInside(x, y)) {
                hits++;
            }
        }

        PI = (4.0 * (hits / (double) points));

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return (0.0);
        }

        return PI;
    }

    /***
     * Pomocnicza funkcja do sprawdzenia, czy punkt jest wewnątrz koła
     * @param x pozycja x
     * @param y pozycja y
     * @return true jeśli punkt jest wewnątrz koła, false jeśli nie jest
     */
    static boolean isInside(double x, double y) {
        double distance = Math.sqrt((x * x) + (y * y));
        return (distance < 1.0);
    }

    /***
     * Funkcja witająca użytkownika z wykorzystaniem podanego imienia oraz podajaca aktualna date i godzine z nazwa miesiaca w jezyku podanym za pomoca skrotu nazwy kraju
     * @param name podane imie uzytkownika
     * @param country skrot nazwy kraju
     * @return powitanie uzytkownika z wykorzystaniem podanego imienia oraz aktualna data i godzina z nazwa miesiaca w jezyku podanym za pomoca skrotu nazwy kraju
     */
    public String welcome(String name, String country){
        Locale location = new Locale(country);
        SimpleDateFormat df = new SimpleDateFormat("hh.mm.ss-dd-MMMM-yyyy", location);
        String date = df.format(Calendar.getInstance().getTime());
        return "Witaj " + name + "\n" + date;
    }

    /**
     * Zwaca rodzaj trójkąta na podstawie podanych długości boków
     * @param a długość boku a
     * @param b długość boku b
     * @param c długość boku c
     * @return Zwraca nazwę rodzaju trójkąta
     */
    public String triangle(int a, int b, int c) {

        Vector<Integer> v = new Vector<>();
        v.add(a);
        v.add(b);
        v.add(c);
        Collections.sort(v);

        a = v.get(0);
        b = v.get(1);
        c = v.get(2);

        if (a <= 0 || b <= 0 || c <= 0) return "Nieprawidlowy";
        if (a >= b + c || c >= b + a || b >= a + c) return "Nieprawidlowy";
        if (a == b && b == c) return "Rownoboczny";
        if (a == b || b == c) return "Rownoramienny";
        if (((a * a) + (b * b)) == (c * c)) return "Prostokatny";
        return "Roznoboczny";
    }




    public static void main(String[] args) {
        try {
            System.out.println("Startuje serwer XML-RPC...");
            int port = 10015;
            InetAddress addr = InetAddress.getByName("192.168.43.208");
            WebServer server = new WebServer(port, addr);

            //poniżej tworzy się obiekt swojej klasy serwera i uruchamia się go:
            server.addHandler("mojserwer", new serwerRPC());
            server.start();
            System.out.println("Serwer wystartował pomyślnie.");
            System.out.println("Nasłuchuje na porcie: " + port);
            System.out.println("Aby zatrzymać serwer naciśnij ctrl+c");
        } catch (Exception exception) {
            System.err.println("Serwer XML-RPC: " + exception);
        }

    }

}