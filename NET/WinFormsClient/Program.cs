using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.Threading;

namespace WinFormsClient
{
    static class Program
    {
        [STAThread]
        static void Main()
        {
            //Init everything
            Init();
            //Show the "invisible" window
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(MainForm.getInstance());
        }

        /// <summary>
        /// Inits application
        /// </summary>
        static void Init()
        {
            //Init peripherals control
            PeripheralsUtil.Init();
        }
    }
}
