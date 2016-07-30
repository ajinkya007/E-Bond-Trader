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
    /// Interaction logic for BondViewerScreen.xaml
    /// </summary>
    public partial class BondViewerScreen : UserControl
    {
        public BondViewerScreen(List<Bond> bondsList)
        {
            InitializeComponent();
            BondsDataGrid.ItemsSource = bondsList;
            BondsDataGrid.Loaded += SetMinWidths;
        }

        private void TradeButton_Click(object sender, RoutedEventArgs e)
        {
            if(BondsDataGrid.SelectedItem == null)
            {
                MessageBox.Show("Please Select a Bond");
            }
            else
            {
                MainWindow win = (MainWindow)Window.GetWindow(this);
                win.CreateNewTradingWindow((Bond)BondsDataGrid.SelectedItem);
                
            }
            
        }

        private void BondsDataGrid_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            if (BondsDataGrid.SelectedItem == null)
            {
                MessageBox.Show("Please Select a Bond");
            }
            else
            {
                MainWindow win = (MainWindow)Window.GetWindow(this);
                win.CreateNewTradingWindow((Bond)BondsDataGrid.SelectedItem);
            }
        }

        public void SetMinWidths(object source, EventArgs e)
        {
            foreach (var column in BondsDataGrid.Columns)
            {
                column.MinWidth = column.ActualWidth;
                column.Width = new DataGridLength(1, DataGridLengthUnitType.Star);
            }
        }
    }
}
