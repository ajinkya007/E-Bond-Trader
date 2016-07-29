using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;

namespace EBondTrader
{
    [DataContract]
    public class Trade
    {
        public static int TiD = 10000000;
        [DataMember]
        public int TradeID { get; set; }
        [DataMember]
        public Bond tradedBond { get; set; }
        [DataMember]
        public DateTime tradeDate { get; set; }
        [DataMember]
        public string tradeType { get; set; }

        public Trade(Bond bond, string type)
        {
            TradeID = ++TiD;
            tradedBond = bond;
            tradeDate = DateTime.Now;
            tradeType = type;
        }

    }
}
