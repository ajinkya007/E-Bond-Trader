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
using System.Windows;
using System.Windows.Controls;
using System.Net;
using System.IO;

namespace TestTabs
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public List<Bond> bonds = new List<Bond>();
        Bond bond1 = new Bond("abcd", "1234", "700");
        Bond bond2 = new Bond("efgh", "5678", "1245");
        Bond bond3 = new Bond("ijkl", "1294", "550");
        public MainWindow()
        {
            InitializeComponent();

            // Create the header
            var header = new TextBlock { Text = "Bond Viewer" };

            // Create the content
            var content = new WelcomeScreen();

            // Create the tab
            var tab = new ClosableTabItem();
            tab.SetHeader(header);
            tab.Content = content;
            bonds.Add(bond1);
            bonds.Add(bond2);
            bonds.Add(bond3);
            // Add to TabControl
            uxTabs.Items.Add(tab);
        }

       
        public void CreateNewTradingWindow(Bond selectedBond)
        {
            // Create the header
            var header = new TextBlock { Text = selectedBond.title };

            // Create the content
            var content = new TradeWindow(selectedBond);

            // Create the tab
            var tab = new ClosableTabItem();
            tab.SetHeader(header);
            tab.Content = content;

            // Add to TabControl
            uxTabs.Items.Add(tab);
            uxTabs.SelectedItem = tab;
            MessageBox.Show(selectedBond.ToString());
        }

        public void ChangeScreen()
        {
            var tab = (ClosableTabItem)uxTabs.SelectedItem;
            tab.Content = new BondViewerScreen(bonds);
        }

    }
}
