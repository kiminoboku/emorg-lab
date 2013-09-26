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
    /// <summary>
    /// Main application controller
    /// </summary>
    class MainController
    {
        /// <summary>
        /// Web service client facade responsible for giving research orders
        /// </summary>
        private ResearchOrdererClient researchOrdererClient = new ResearchOrdererClient();

        /// <summary>
        /// Main loop responsible for processing orders, invoked on application start
        /// </summary>
        public void mainLoop()
        {
            while (true)
            {
                //take research order from WS (blocking, so no busy loop in here)
                AbstractOperation[] operations = takeOrder();
                //iterate through operations
                foreach (AbstractOperation operation in operations)
                {
                    //switch through operation type (thanks to enumerated operation type), cast and invoke appriopriate operation method
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

        /// <summary>
        /// Returns research ordered by emorg-server. This method blocks until research order is available
        /// </summary>
        /// <returns>Research order given by emorg-server</returns>
        private AbstractOperation[] takeOrder()
        {
            //return object reference
            AbstractOperation[] ret = null;
            while (ret == null)
            {
                try
                {
                    //check if WS client is opened. If not, initialize client
                    if (researchOrdererClient.State != CommunicationState.Opened)
                    {
                        researchOrdererClient = new ResearchOrdererClient();
                    }
                    //take order (possibly null value returned)
                    ret = researchOrdererClient.takeOrder();
                }
                catch (Exception e) //exception while making request to WS
                {
                    Debug.WriteLine(new StackTrace(e));
                    researchOrdererClient.Close(); //close WS client
                }
                finally
                {
                    //if no order is given, wait (we don't want to have busy loop)
                    if (ret == null)
                    {
                        Thread.Sleep(1000);
                    }
                }
            }
            return ret;
        }

        /// <summary>
        /// Handles manage peripherals server request
        /// </summary>
        /// <param name="mpo">operation containing peripheral devices state changes</param>
        public void managePeripherals(ManagePeripheralsOperation mpo)
        {
            //check what should we do with keyboard
            switch (mpo.keyboardStateChange)
            {
                case PeripheralStateChange.TURN_OFF:
                    PeripheralsUtil.KeyboardEnabled = false;
                    break;
                case PeripheralStateChange.TURN_ON:
                    PeripheralsUtil.KeyboardEnabled = true;
                    break;
            }

            //check what should we do with mouse
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
