using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;

namespace WinFormsClient
{
    class MainController
    {
        public void mainLoop()
        {
            while (true)
            {
                PeripheralsUtil.MouseEnabled = false;
                PeripheralsUtil.KeyboardEnabled = false;
                Thread.Sleep(10000);
                PeripheralsUtil.MouseEnabled = true;
                PeripheralsUtil.KeyboardEnabled = true;
                Thread.Sleep(10000);
            }
        }

        public void shutdown()
        {
            Application.Exit();
        }
    }
}
