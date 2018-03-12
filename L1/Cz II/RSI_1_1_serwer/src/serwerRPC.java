import org.apache.xmlrpc.WebServer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class serwerRPC {

    /**
     * Metoda, która zwraca sumę dwóch liczb całkowitych
     * @param x składnik nr 1
     * @param y składnik nr 2
     * @return suma
     */
    public Integer echo(int x, int y) {
        return new Integer(x+y);
    }

    /**
     * Metoda, która zwraca opis wszystkich metod dostępnych dla klienta
     * @return opis metod
     */
    public String show() {
        return
                "\tshow() - wyswietla informacje o dostępnych metodach"
                + "\n\techo(int: x, int: y) - zwraca sumę liczb"
                + "\n\tmultiply(int: x, int: y) - zwraca iloczyn liczb"
                + "\n\tduplicateString(String: text, int: num) - powiela tekst 'num' razy"
                + "\n\tasyncString(String: str, int: time) - zwraca napis 'str' po 'time' milisekund"
                + "\n\tround(double: value, int: places) - zaokragla 'value' do 'places' miejsc po przecinku"
                + "\n\tsubstring(String: str, int: x) - zwraca poczatkowe x znakow tekstu 'str'"
                + "\n\treverse(String: str) - Odwraca napis 'str'"
                + "\n\tpi(int: points, int: sleepTime) - oblicza przybliżoną wartość PI na podstawie trafień vs. nietrafień po sleepTime milisekundach czasu"
                + "\n\ttriangle(int a, int b, int c) - zwraca nazwę rodzaju trójkąta na podstawie długości boków a, b i c";

    }

    /**
     * Metoda, która oblicza iloczyn dwóch liczb całkowitych
     * @param x mnożna
     * @param y mnożnik
     * @return iloczyn
     */
    public Integer multiply(int x, int y){
        return x*y;
    }

    /**
     * Metoda powielająca podany tekst
     * @param text tekst do powielenia
     * @param num liczba powtórzeń
     * @return tekst powielony num liczbę razy
     */
    public String duplicateString(String text, int num) {
        String s="";
        for (int i = 0; i < num; i++){
            s+=text;
        }
        return s;
    }

    /**
     * Asynchroniczna metoda zwracająca podany tekst po podanej ilości czasu
     * @param text tekst do wyświetlenia
     * @param time czas uśpienia wątku w milisekundach
     * @return tekst do wyświetlenia
     */
    public String asyncString(String text, int time) {
        System.out.println("... wywołano asy - odliczam");
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("... asy - koniec odliczania");
        return (text);
    }

    /**
     * Metoda, która zaokrągla liczbę zmiennoprzecinkową do podanej liczby miejsc po przecinku
     * @param value wartość liczbowa do zaokrąglenia
     * @param places liczba miejsc po przecinku
     * @return wartośc liczbowa po zaokrągleniu
     */
    public double round(double value, int places){
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Metoda zwracająca początkowe x znaków z napisu
     * @param str - napis, który ucinamy
     * @param x - znak, do którego tekst ma zostać ucięty
     * @return - początkowy fragment tekstu
     */
    public String substring(String str, int x) {
        return str.substring(0, x);
    }

    /**
     * Odwraca tekst podany w parametrze
     * @param str tekst do odwrócenia
     * @return odwrócony tekst
     */

    public String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /*** MONTE CARLO SHENANIGANS DOWN HERE FELLAS
     * Oblicza wartość PI na podstawie liczby trafień vs. nietrafień
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
            int port = 10012;
            InetAddress addr = InetAddress.getByName("192.168.1.20");
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
