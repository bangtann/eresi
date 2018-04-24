using System;
using System.ServiceModel;
using System.ServiceModel.Description;
using WcfServiceContract;

namespace WcfServiceHost2
{
    class Program
    {
        static void Main(string[] args)
        {
            ServiceHost myHost =new ServiceHost(typeof(myCalculator));
            try
            {
                ServiceEndpoint endpoint1 =myHost.Description.Endpoints.Find(typeof(ICalculator));
                ServiceEndpoint endpoint2 =myHost.Description.Endpoints.Find(new Uri("http://localhost:10007/myService/endpoint2"));
                ServiceEndpoint endpoint3 =myHost.Description.Endpoints.Find(new Uri("http://localhost:20007/myService/endpoint3"));
                Uri address4 =new Uri("net.tcp://localhost:30000/myServiceTCP");
                ServiceEndpoint endpoint4 =myHost.AddServiceEndpoint(typeof(ICalculator), new NetTcpBinding(), address4);

                //wyswietl endpointy
                Console.WriteLine("\n---> Endpointy:");

                Console.WriteLine("\nService endpoint {0}:", endpoint1.Name);
                Console.WriteLine("Binding: {0}", endpoint1.Binding.ToString());
                Console.WriteLine("ListenUri: {0}\n", endpoint1.ListenUri.ToString());

                Console.WriteLine("\nService endpoint {0}:", endpoint2.Name);
                Console.WriteLine("Binding: {0}", endpoint2.Binding.ToString());
                Console.WriteLine("ListenUri: {0}\n", endpoint2.ListenUri.ToString());

                Console.WriteLine("\nService endpoint {0}:", endpoint3.Name);
                Console.WriteLine("Binding: {0}", endpoint3.Binding.ToString());
                Console.WriteLine("ListenUri: {0}\n", endpoint3.ListenUri.ToString());

                Console.WriteLine("\nService endpoint {0}:", endpoint4.Name);
                Console.WriteLine("Binding: {0}", endpoint4.Binding.ToString());
                Console.WriteLine("ListenUri: {0}\n", endpoint4.ListenUri.ToString());

                myHost.Open();
                Console.WriteLine("\n--> Serwis 1 jest uruchomiony.");

                ContractDescription cd =ContractDescription.GetContract(typeof(ICalculator));
                Console.WriteLine("Informacje o kontrakcie:");
                Type contractType =cd.ContractType;
                Console.WriteLine("\tContract type: {0}", contractType.ToString());
                string name =cd.Name;
                Console.WriteLine("\tName: {0}", name);
                OperationDescriptionCollection odc =cd.Operations;
                Console.WriteLine("\tOperacje:");
                foreach (OperationDescription od in odc)
                {
                    Console.WriteLine("\t\t" + od.Name);
                }


                Console.WriteLine("\n--> Nacisnij <ENTER> aby zakonczyc.");
                Console.WriteLine();
                Console.ReadLine();
                myHost.Close();
                }
                catch (CommunicationException ce)
                {
                Console.WriteLine("Wystapil wyjatek: {0}", ce.Message);
                myHost.Abort();
                }
        }
    }
}
