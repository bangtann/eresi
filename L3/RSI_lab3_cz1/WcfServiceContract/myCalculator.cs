using System;
using System.ServiceModel;

namespace WcfServiceContract
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class myCalculator : ICalculator
    {
        double sum = 0;

        public double Add(double n1, double n2)
        {
            double n3 =n1 + n2;
            Console.WriteLine($"Metoda dodawania\nParametry:\n n1 ={n1} n2 ={n2}\nWynik: {n3}");
            return n3;
        }
        public double Substract(double n1, double n2)
        {
            double n3 =n1 - n2;
            Console.WriteLine($"Metoda odejmowania\nParametry:\n n1 ={n1} n2 ={n2}\nWynik: {n3}");
            return n3;
        }
        public double Multiply(double n1, double n2)
        {
            double n3 =n1 * n2;
            Console.WriteLine($"Metoda mnożenia\nParametry:\n n1 ={n1} n2 ={n2}\nWynik: {n3}");
            return n3;
        }

        public double Divide(double n1, double n2)
        {
            double n3 =n1 / n2;
            Console.WriteLine($"Metoda dzielenia\nParametry:\n n1 ={n1} n2 ={n2}\nWynik: {n3}");
            return n3;
        }

        public double Sum(double n1)
        {
            sum += n1;
            return sum;
        }
    }
}
