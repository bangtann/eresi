import org.apache.xmlrpc.XmlRpcClient;

import java.util.Scanner;
import java.util.Vector;

public class klientRPC {

    public static void main(String[] args) {
        try {
            int port = 10013;
            String addr = "192.168.1.20";
            XmlRpcClient srv = new XmlRpcClient(addr, port);

            Scanner sc = new Scanner(System.in);
            System.out.println("\nPodaj nazwę metody, którą chcesz wywołać i w nawiasie: typ parametru i po dwukropku jego wartość,"
                    + "gdzie kolejne parametry oddzielone są znakiem \",\""
                    + "\n\tprzykład: aby wywołać metodę func(int x, String name), wpisz:\tfunc(int: 5, String: Adam Kowalski)"
                    +"\nAby zakończyć, wpisz: exit\n");

            boolean exit = false;
            while (!exit) {
                boolean error = false;
                String lineContent = sc.nextLine();

                if(!lineContent.substring(0, 4).equals("exit")) {
                    lineContent = lineContent.replace(" ( ", ",").replace(" (", ",").replace("( ", ",").replace("(", ",");
                    lineContent = lineContent.replace(" ) ",  "").replace(" )",  "").replace(") ",  "").replace(")", "");
                    lineContent = lineContent.replace(" : ", ",").replace(": ", ",").replace(" :", ",").replace(":", ",");
                    lineContent = lineContent.replace(" , ", ",").replace(", ", ",").replace(" ,", ",");
                    String[] paramsArr = lineContent.split(",");



            Vector<Object> params = new Vector<>();
                    for (int i = 1; i < paramsArr.length && !error; i+=2) {
                        switch (paramsArr[i]) {
                            case "int":
                                params.addElement(Integer.valueOf(paramsArr[i+1]));
                                break;
                            case "double":
                                params.addElement(Double.valueOf(paramsArr[i+1]));
                                break;
                            case "String":
                                params.addElement(paramsArr[i+1]);
                                break;
                            default:
                                System.out.println("Błąd: Podano niewłaściwy typ parametru nr "+(i*2-1));
                                error = true;
                                break;
                        }
                    }

                    if (!error) {
                        if(paramsArr[0].equals("asyncString"))
                        {
                            AC cb = new AC();
                            srv.executeAsync("mojserwer." + paramsArr[0], params, cb);
                        }
                        else {
                            String result = srv.execute("mojserwer." + paramsArr[0], params).toString();
                            System.out.println(result);
                        }
                    }
                }
                else {
                    exit = true;
                    System.out.println("Zakończono działanie programu.");
                }
            }
            sc.close();
        }
        catch (Exception e){
            System.err.println("Klient XML-RPC: " + e);
        }
    }
            /* Przykładowe wywołania metod:
	         * show
	         * multiply(int: 5, int: 3)
	         * duplicateString(String: Ala ma kota, int: 4)
	         * asyncString(String: No wreszcie!, int: 5000)
	         * round(double: 1.123456789, int: 3)
	         * round(double: 1.456, int: 2)
	         * substring(String: abcdefg, int: 4)
	         */
}
