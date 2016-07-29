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
    public class Bond
    {
        [DataMember]
        public string IBIN { get; set; }
        [DataMember]
        public string BondName { get; set; }
        [DataMember]
        public DateTime StartDate { get; set; }
        [DataMember]
        public DateTime MaturityDate { get; set; }
        public enum CouponPeriod { Quarterly, Half_Yearly, Annual };
        [DataMember]
        CouponPeriod couponPeriod;
        public Bond(string ibin, string BName, DateTime settleDate,CouponPeriod cp)
        {
            IBIN = ibin;
            BondName = BName;
            MaturityDate = settleDate;
            StartDate = settleDate;
            couponPeriod = cp;
        }

        public override string ToString()
        {
            return IBIN + " " + BondName + " " +  StartDate + " " + MaturityDate + " " + couponPeriod.ToString();
        }
    }
}
