using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;

namespace E_Bond_Trader
{
    [DataContract]
    public class Bond
    {
        [DataMember]
        public string isbn { get; set; }
        [DataMember]
        public string title { get; set; }
        [DataMember]
        public string price { get; set; }

        public Bond(string BondTitle, string bondIsbn, string bondPrice)
        {
            title = BondTitle;
            isbn = bondIsbn;
            price = bondPrice;
        }

        public override string ToString()
        {
            return title + " " + isbn + " " + price;
        }
    }
}
