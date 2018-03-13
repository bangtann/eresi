import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

public class AC implements AsyncCallback {
    @Override
    public void handleResult(Object o, URL url, String s) {
        System.out.println("Wywołanie AC.handleResult");
    }

    @Override
    public void handleError(Exception e, URL url, String s) {
        System.out.println("Wywołanie AC.handleError " + e);
    }
}
