using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using System.Diagnostics;
using System.ServiceModel;
using pl.kiminoboku.emorg;
using System.Xml.Serialization;
using System.Xml;

namespace WinFormsClient
{
    /// <summary>
    /// Main application controller
    /// </summary>
    class MainController
    {
        private XmlSerializer xmlSerializer = new XmlSerializer(typeof(Research));

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
                    XmlTextReader xmlTextReader = new XmlTextReader("http://localhost:8080/");
                    Research research = (Research)xmlSerializer.Deserialize(xmlTextReader);
                    ret = research.operation;
                    if (ret[0].operationType == OperationType.EMPTY)
                    {
                        //force wait
                        ret = null;
                    }
                }
                catch (Exception e) //exception while making request to WS
                {
                    Debug.WriteLine(new StackTrace(e));
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
