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

namespace TestTabs
{
    /// <summary>
    /// Interaction logic for TradeWindow.xaml
    /// </summary>
    public partial class TradeWindow : UserControl
    {
        public TradeWindow(Bond selectedBond)
        {
            InitializeComponent();
            BondISINLabel.Content = selectedBond.isbn;
            BondTitleLabel.Content = selectedBond.title;
            BondPriceLabel.Content = selectedBond.price;
        }
    }
}
