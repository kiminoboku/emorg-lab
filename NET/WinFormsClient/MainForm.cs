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
    /// <summary>
    /// MainForm, invisible (see form properties)
    /// </summary>
    public partial class MainForm : Form
    {
        /// <summary>
        /// Thread responsible for handling orders
        /// </summary>
        private Thread mainThread;
        /// <summary>
        /// Main application controller
        /// </summary>
        private MainController mainController;

        public MainForm()
        {
            //This does all the "invisible" magic
            InitializeComponent();
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            //hide on load (app should be invisible)
            this.Hide();
        }

        /// <summary>
        /// Starts handling research orders when this "invisible app" "showes up" (funny, isn't it?)
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void MainForm_Shown(object sender, EventArgs e)
        {
            //initialize main controller
            mainController = new MainController();

            //start main controller research handling loop in separate thread
            mainThread = new Thread(new ThreadStart(mainController.mainLoop));
            mainThread.Start();
        }
    }
}
