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

namespace EBondTrader
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            bonds.Add(new Bond("0001234", "INDIA SOV 1234", DateTime.Today, Bond.CouponPeriod.Annual));
            bonds.Add(new Bond("0001235", "USA SOV 1235", DateTime.Today, Bond.CouponPeriod.Half_Yearly));
            bonds.Add(new Bond("0001236", "UK SOV 1236", DateTime.Today, Bond.CouponPeriod.Quarterly));
        }

        public static List<Bond> bonds = new List<Bond>();
        public static List<Trade> trades = new List<Trade>();
        private void Grid_Loaded(object sender, RoutedEventArgs e)
        {
            IBINList.Items.Clear();
            IBINList.Items.Add("Select Bond IBIN");
            IBINList.SelectedIndex = 0;
            foreach(Bond bond in bonds)
            {
                IBINList.Items.Add(bond.IBIN);
            }
           
        }

        private void Grid_Loaded_1(object sender, RoutedEventArgs e)
        {
            BondsDataGrid.ItemsSource = trades;

        }

        

        private void Grid_Loaded_2(object sender, RoutedEventArgs e)
        {
            ViewBondsGrid.ItemsSource = bonds;
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            int index = IBINList.SelectedIndex - 1;
            if(index != -1)
            {
                Bond selectedBond = bonds[index];
                IBINLabel.Content = selectedBond.IBIN;
                BondNameLabel.Content = selectedBond.BondName;
                SettlementDateLabel.Content = selectedBond.MaturityDate.ToString();
                StartDateLabel.Content = selectedBond.StartDate.ToString();
                CleanPricetextBox.Text = "138.00";
                DirtyPriceTextBox.Text = "143.56";
                YTMLabel.Content = "6.40%";
                AccruedLabel.Content = "5.56";
                SettlementAmountLabel.Content = "140.00";
            }
        }

        private void BuyButton_Click(object sender, RoutedEventArgs e)
        {
            int index = IBINList.SelectedIndex - 1;
            trades.Add(new Trade(bonds[index], "BUY"));
        }

        private void SellButton_Click(object sender, RoutedEventArgs e)
        {
            int index = IBINList.SelectedIndex - 1;
            trades.Add(new Trade(bonds[index], "SELL"));
        }
    }
}
