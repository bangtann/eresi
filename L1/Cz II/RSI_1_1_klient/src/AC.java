import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

public class AC implements AsyncCallback {
    /**
     * Metoda wyświetlająca komunikat z informacją o rezultacie wywołania metody.
     */
    @Override
    public void handleResult(Object result, URL url, String method) {
        System.out.println("Wywołanie metody: " + method + " zwraca w wyniku: " + result.toString());
    }

    /**
     *
     * Metoda wyświetlająca komunikat z informacją o błędzie, który nastąpił w wyniku wywołania metody.
     */
    @Override
    public void handleError(Exception e, URL url, String method) {
        System.out.println("Wywołanie AC.handleError " + e);
    }
}
