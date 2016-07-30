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
using System.Windows;
using System.Windows.Controls;

namespace E_Bond_Trader
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public List<ClosableTabItem> TradingTabs = new List<ClosableTabItem>();
        Bond bond = new Bond("abcd", "12345", "123");
        public MainWindow()
        {
            InitializeComponent();

            ClosableTabItem tab = this.AddTabItem(bond);
            tab.Content = new Screen1();
            MainTab.DataContext = TradingTabs;
            MemoryStream stream = new MemoryStream();
            DataContractJsonSerializer arraySerializer = new DataContractJsonSerializer(typeof(Bond));
            arraySerializer.WriteObject(stream, bond);
            StreamReader reader = new StreamReader(stream);
            string text = reader.ReadToEnd();
            MessageBox.Show(text);
            var cli = new WebClient();
            //cli.Headers[HttpRequestHeader.ContentType] = "application/json";
            cli.Headers.Add(HttpRequestHeader.ContentType, "application/json");
            cli.UploadString(new Uri("http://192.168.99.50:8080/TestDBRest/rest/test"), "POST", "{\"isbn\":15,\"title\":\"Aditya\",\"price\":10}");
            MainTab.SelectedIndex = 0;
            
        }

        public ClosableTabItem AddTabItem(Bond t1)
        {
            int count = TradingTabs.Count;
            ClosableTabItem newTab = new ClosableTabItem();

            newTab.Header = t1.title;
            newTab.Name = t1.title;
            //newTab.HeaderTemplate = MainTab.FindResource("TabHeader") as DataTemplate;

            TradingTabs.Insert(count, newTab);

            return newTab;
        }

        public void CreateNewTab(Bond t1)
        {
            MainTab.DataContext = null;
            ClosableTabItem tab = this.AddTabItem(t1);
            tab.Content = new Screen1();
            MainTab.DataContext = TradingTabs;
            MainTab.SelectedItem = tab;
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {

        }

        private void putButtonClick(object sender, RoutedEventArgs e)
        {
            
        }
    }
}