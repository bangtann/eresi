using System.Runtime.Serialization;
using System.ServiceModel;

namespace WcfServiceContract
{
    [ServiceContract]
    public interface ICalculator
    {
        [OperationContract]
        double Add(double n1, double n2);
        [OperationContract]
        double Substract(double n1, double n2);
        [OperationContract]
        double Multiply(double n1, double n2);
        [OperationContract]
        double Divide(double n1, double n2);

        [OperationContract]
        double Sum(double n1);
    }

    [DataContract]
    public class CompositeType
    {
        bool boolValue =true;
        string stringValue ="Hello ";

        [DataMember]
        public bool BoolValue
        {
            get { return boolValue; }
            set { boolValue =value; }
        }

        [DataMember]
        public string StringValue
        {
            get { return stringValue; }
            set { stringValue =value; }
        }
    }
}
