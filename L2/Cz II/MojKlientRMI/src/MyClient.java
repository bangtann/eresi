import java.util.Scanner;

/**
 * Klasa glowna klienta RMI
 * @author Julita Bielaniewicz 227080
 */
public class MyClient {
    /**
     * Glowna metoda klienta RMI zlecajaca obliczenia i przyjmujaca wyniki
     * @param args lista adresow poszczegolnych serwisow postaci //host_address/service_name
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("You have to enter RMI object address " +
                    "in the form of //host_address/service_name");
            return;
        }

        CalcObject zObiekt;
        double wynik;
        String adres1 = args[0];

        CalcObject2 zObiekt2;
        ResultType wynik2;
        InputType inObj;
        inObj = new InputType();
        inObj.x1 = 2.71;
        inObj.x2 = 3.14;
        inObj.operation = "add";
        String adres2 = args[1];

        String primeAddress = args[2];
        IPrimeCalcObject primeCalcObject;

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            zObiekt = (CalcObject) java.rmi.Naming.lookup(adres1);
            zObiekt2 = (CalcObject2) java.rmi.Naming.lookup(adres2);
            primeCalcObject = (IPrimeCalcObject) java.rmi.Naming.lookup(primeAddress);
        } catch (Exception e) {
            System.out.println("Nie można pobrać referencji.");
            e.printStackTrace();
            return;
        }
        System.out.println("Referencje pobrane.");

        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Podaj początek przedziału:");
            int od = sc.nextInt();
            System.out.println("Podaj koniec przedziału:");
            int koniec = sc.nextInt();


            wynik = zObiekt.calculate(1.1, 2.2);
            System.out.println("Wynik zObiekt: " + wynik);

            wynik2 = zObiekt2.calculate(inObj);
            System.out.println("Wynik zObiekt2: "
                    + wynik2.result + " " + wynik2.result_description);

            PrimeResultType primeResult;
            PrimeInputType primeInput = new PrimeInputType();
            primeInput.setFrom(od);
            primeInput.setTo(koniec);
            primeResult = primeCalcObject.calculate(primeInput);
            System.out.println("Wynik primeCalcObject:");
            System.out.println("  resultDescription: " + primeResult.getResultDescription());
            System.out.println("  primes: " + primeResult.getPrimes().toString());

        } catch (Exception e) {
            System.out.println("Blad zdalnego wywolania.");
            e.printStackTrace();
        }
    }
}