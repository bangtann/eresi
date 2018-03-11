import org.apache.xmlrpc.WebServer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;

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
     *
     * Metoda, która zwraca opis wszystkich metod dostępnych dla klienta
     * @return opis metod
     */
    public String show() {
        return "\tshow() - wyswietla informacje o dostepnych metodach"
                + "\n\techo(int: x, int: y) - zwraca sumę liczb"
                + "\n\tmultiply(int: x, int: y) - zwraca iloczyn liczb"
                + "\n\tduplicateString(String: text, int: num) - powiela tekst 'num' razy"
                + "\n\tasyncString(String: str, int: time) - zwraca napis 'str' po 'time' milisekund"
                + "\n\tround(double: value, int: places) - zaokragla 'value' do 'places' miejsc po przecinku"
                + "\n\tsubstring(String: str, int: x) - zwraca poczatkowe x znakow tekstu 'str'";
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


    public static void main(String[] args) {
        try {
            System.out.println("Startuje serwer XML-RPC...");
            int port = 10013;
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
