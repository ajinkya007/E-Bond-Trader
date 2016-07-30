using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace E_Bond_Trader
{
    public class ClosableTabItem : TabItem
    {
        public void SetHeader(UIElement header)
        {
            // Container for header controls
            var dockPanel = new DockPanel();
            dockPanel.Children.Add(header);

            // Close button to remove the tab
            var closeButton = new UserControl1();
            closeButton.Click +=
                (sender, e) =>
                {
                    var tabControl = Parent as ItemsControl;
                    tabControl.Items.Remove(this);
                };
            dockPanel.Children.Add(closeButton);

            // Set the header
            Header = dockPanel;

        }
    }
}
