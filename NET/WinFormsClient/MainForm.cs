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
            mainThread = new Thread(new ThreadStart(new MainController().mainLoop));
            mainThread.Start();
        }
    }
}
