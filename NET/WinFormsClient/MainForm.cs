using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Threading;
using System.Diagnostics;

namespace WinFormsClient
{
    public partial class MainForm : Form
    {
        private Thread mainThread;
        private MainController mainController;

        public MainForm()
        {
            InitializeComponent();
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            this.Hide();
        }

        private void MainForm_Shown(object sender, EventArgs e)
        {
            mainController = new MainController();
            mainThread = new Thread(new ThreadStart(mainController.mainLoop));
            mainThread.Start();
        }
    }
}
