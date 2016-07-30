using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;

namespace TestTabs
{
    [DataContract]
    public class Bond
    {
        [DataMember]
        public string title { get; set; }
        [DataMember]
        public string isbn { get; set; }
        [DataMember]
        public string price { get; set; }
        [DataMember]
        public DateTime date { get; set; }
        

        public Bond(string BondTitle, string bondIsbn, string bondPrice)
        {
            title = BondTitle;
            isbn = bondIsbn;
            price = bondPrice;
            date = DateTime.Now;
        }

        public Bond(string BondTitle, string bondIsbn, string bondPrice,DateTime bondDate) : this(BondTitle, bondIsbn, bondPrice)
        {
            date = bondDate;
        }

        public override string ToString()
        {
            return title + " " + isbn + " " + price + " " + date;
        }
    }
}
