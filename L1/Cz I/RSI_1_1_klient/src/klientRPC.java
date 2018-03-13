import org.apache.xmlrpc.XmlRpcClient;

import java.util.Vector;

public class klientRPC {

    public static void main(String[] args) {
        try {
            XmlRpcClient srv = new
                    XmlRpcClient("http://localhost:10013");
            Vector<Integer> params = new Vector<Integer>();
            params.addElement(new Integer(13));
            params.addElement(new Integer(21));


            AC cb = new AC();
            Vector<Integer> params2 = new Vector<Integer>();
            params2.addElement(new Integer(10000));
            srv.executeAsync("mojserwer.asy", params2, cb);

            Object result =
                    srv.execute("mojserwer.echo", params);
            int wynik = ((Integer) result).intValue();
            System.out.println("Wynik: " + wynik);

            System.out.println("Wywolano asynchronicznie");
        } catch (Exception exception) {
            System.err.println("Klient XML-RPC: " +exception);
        }

    }
}
