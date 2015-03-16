using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;

namespace WinFormsClient
{
    class RunCommandDetail
    {
        public RunCommandOperation commandOperation { get; set; }
        public Process commandProcess { get; set; }
    }
}
