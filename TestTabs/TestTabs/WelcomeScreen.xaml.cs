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
using System.IO;
using System.Net;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;

namespace TestTabs
{
    /// <summary>
    /// Interaction logic for WelcomeScreen.xaml
    /// </summary>
    public partial class WelcomeScreen : UserControl
    {
        DateTime[] dates = new DateTime[10];
        public WelcomeScreen()
        {
            InitializeComponent();
        }

        private void IDBox_MouseLeftButtonUp(object sender, RoutedEventArgs e)
        {
            IDLabel.Content = "";
        }

        private void passwordBox_GotFocus(object sender, RoutedEventArgs e)
        {
            PasswordLabel.Content = "";
        }

        private void passwordBox_MouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {

        }

        private void GoButton_Click(object sender, RoutedEventArgs e)
        {
            MainWindow win = (MainWindow)Window.GetWindow(this);
            win.ChangeScreen();
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            var client = new WebClient();
            //Stream stream = client.OpenRead();
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(DateTime[]));
           // dates = (DateTime[])serializer.ReadObject(stream);
            foreach(DateTime date in dates)
            {
                MessageBox.Show(date.ToString());
            }
        }
    }
}
