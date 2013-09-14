﻿using System;
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
            Init();
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new MainForm());
            Cleanup();
        }

        static void Init()
        {
            PeripheralsUtil.Init();
        }

        static void Cleanup()
        {
            PeripheralsUtil.Cleanup();
        }
    }
}
