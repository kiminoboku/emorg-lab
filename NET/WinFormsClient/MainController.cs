using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using WinFormsClient.WebServices;
using System.Diagnostics;
using System.ServiceModel;

namespace WinFormsClient
{
    class MainController
    {
        private ResearchOrdererClient researchOrdererClient = new ResearchOrdererClient();
        public void mainLoop()
        {
            while (true)
            {
                AbstractOperation[] operations = takeOrder();
                foreach (AbstractOperation operation in operations)
                {
                    switch (operation.operationType)
                    {
                        case OperationType.MANAGE_PERIPHERALS:
                            ManagePeripheralsOperation mpo = (ManagePeripheralsOperation)operation;
                            managePeripherals(mpo);
                            break;
                        default: break;
                    }
                }
            }
        }

        private AbstractOperation[] takeOrder()
        {
            AbstractOperation[] ret = null;
            while (ret == null)
            {
                try
                {
                    if (researchOrdererClient.State != CommunicationState.Opened)
                    {
                        researchOrdererClient = new ResearchOrdererClient();
                    }
                    ret = researchOrdererClient.takeOrder();
                }
                catch (Exception e)
                {
                    Debug.WriteLine(new StackTrace(e));
                    researchOrdererClient.Close();
                }
                finally
                {
                    if (ret == null)
                    {
                        Thread.Sleep(1000);
                    }
                }
            }
            return ret;
        }

        public void managePeripherals(ManagePeripheralsOperation mpo)
        {
            switch (mpo.keyboardStateChange)
            {
                case PeripheralStateChange.TURN_OFF:
                    PeripheralsUtil.KeyboardEnabled = false;
                    break;
                case PeripheralStateChange.TURN_ON:
                    PeripheralsUtil.KeyboardEnabled = true;
                    break;
            }

            switch (mpo.mouseStateChange)
            {
                case PeripheralStateChange.TURN_OFF:
                    PeripheralsUtil.MouseEnabled = false;
                    break;
                case PeripheralStateChange.TURN_ON:
                    PeripheralsUtil.MouseEnabled = true;
                    break;
            }
        }
    }
}
