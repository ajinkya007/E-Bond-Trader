using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net;
using System.IO;

namespace E_Bond_Trader
{
    /// <summary>
    /// Interaction logic for Screen1.xaml
    /// </summary>
    public partial class Screen1 : UserControl
    {
        public Screen1()
        {
            InitializeComponent();
        }

        private void getData_Click(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            var data = client.DownloadString("http://192.168.99.50:8080/TestDBRest/rest/test");
            //Stream stream = client.OpenRead(url);
            byte[] byteArray = Encoding.UTF8.GetBytes(data);
            MemoryStream stream = new MemoryStream(byteArray);
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(Bond[]));
            Bond[] other = (Bond[])serializer.ReadObject(stream);
            foreach (Bond test in other)
            {
                MessageBox.Show(test.ToString());
            }
            //Bond t = new Bond("adithya", "120294", "115720");
            MainWindow win = (MainWindow)Window.GetWindow(this);
            win.CreateNewTab(other[0]);
            
        }
    }
}
