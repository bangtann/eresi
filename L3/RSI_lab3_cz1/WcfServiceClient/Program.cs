using System;
using WcfServiceClient.WcfServiceContract;

namespace WcfServiceClient
{
    class Program
    {
        static void Main(string[] args)
        {
            CalculatorClient client1 = new CalculatorClient("WSHttpBinding_ICalculator");
            CalculatorClient client2 = new CalculatorClient("mojEndpoint2");
            CalculatorClient client3 = new CalculatorClient("mojEndpoint3");
            CalculatorClient client4 = new CalculatorClient("NetTcpBinding_ICalculator");

            double value1 = 18;
            double value2 = 8.5;

            double result = client1.Add(value1, value2);
            Console.WriteLine("Wywoluje dodawanie dla klienta1:\t" + value1 + " + " + value2 + " = " + result);
            result = client1.Multiply(value1, value2);
            Console.WriteLine("Wywoluje mnozenie dla klienta1:\t" + value1 + " * " + value2 + " = " + result);
            result = client1.Sum(value1);
            Console.WriteLine("Wywoluje sumowanie dla klienta1 = " + result);
            result = client1.Sum(value2);
            Console.WriteLine("Wywoluje sumowanie dla klienta1 = " + result);


            result = client2.Substract(value1, value2);
            Console.WriteLine("Wywoluje odejmowanie dla klienta2:\t" + value1 + " - " + value2 + " = " + result);
            result = client2.Sum(value1);
            Console.WriteLine("Wywoluje sumowanie dla klienta2 = " + result);
            result = client2.Sum(value2);
            Console.WriteLine("Wywoluje sumowanie dla klienta2 = " + result);


            result = client3.Add(value1, value2);
            Console.WriteLine("Wywoluje dodawanie dla klienta3:\t" + value1 + " + " + value2 + " = " + result);
            result = client3.Sum(value1);
            Console.WriteLine("Wywoluje sumowanie dla klienta3 = " + result);
            result = client3.Sum(value2);
            Console.WriteLine("Wywoluje sumowanie dla klienta3 = " + result);


            result = client4.Substract(value1, value2);
            Console.WriteLine("Wywoluje odejmowanie dla klienta1:\t" + value1 + " - " + value2 + " = " + result);
            result = client4.Multiply(value1, value2);
            Console.WriteLine("Wywoluje mnozenie dla klienta4 = " + result);
            result = client4.Sum(value1);
            Console.WriteLine("Wywoluje sumowanie dla klienta4 = " + result);
            result = client4.Sum(value2);
            Console.WriteLine("Wywoluje sumowanie dla klienta4 = " + result);
        }
    }
}
