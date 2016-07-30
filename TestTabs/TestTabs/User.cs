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
    public class User
    {
        [DataMember]
        public string id { get; set; }
        [DataMember]
        public string password { get; set; }

        public User(string uid,string pass)
        {
            id = uid;
            password = pass;
        }
    }
}
