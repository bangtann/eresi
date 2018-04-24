using System;
using System.ServiceModel;
using System.ServiceModel.Description;
using WcfServiceContract;

namespace WcfServiceHost
{
    class Program
    {
        static void Main(string[] args)
        {
            Uri baseAddress = new Uri("http://localhost:10007/myService");
            ServiceHost myHost = new ServiceHost(typeof(myCalculator), baseAddress);

            try
            {
                WSHttpBinding myBinding = new WSHttpBinding();
                myHost.AddServiceEndpoint(typeof(ICalculator), myBinding, "endpoint1");

                ServiceMetadataBehavior smb = new ServiceMetadataBehavior();
                smb.HttpGetEnabled = true;
                myHost.Description.Behaviors.Add(smb);

                myHost.Open();
                Console.WriteLine("Serwis jest uruchomiony.");
                Console.WriteLine("Nacisnij <ENTER> aby zakonczyc.");
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
