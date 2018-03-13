import org.apache.xmlrpc.WebServer;
public class serwerRPC {

    public Integer echo(int x, int y) {
        return new Integer(x+y);
    }

    public int asy(int x) {
        System.out.println("... wywołano asy - odliczam");
        try {
            Thread.sleep(x);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("... asy - koniec odliczania");
        return (123);
    }

    public static void main(String[] args) {
        try {
            System.out.println("Startuje serwer XML-RPC...");
            int port = 10013;
            WebServer server = new WebServer(port);
            //ponizej tworzy się obiekt swojej klasy serwera i uruchamia się go:
            server.addHandler("mojserwer", new serwerRPC());
            server.start();
            System.out.println("Serwer wystartowal pomyslnie.");
            System.out.println("Nasluchuje na porcie: " + port);
            System.out.println("Aby zatrzymać serwer nacisnij crl+c");
        } catch (Exception exception) {
            System.err.println("Serwer XML-RPC: " + exception);
        }

    }

}
