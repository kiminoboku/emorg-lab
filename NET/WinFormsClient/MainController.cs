using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using System.Diagnostics;
using System.ServiceModel;
using System.Xml.Serialization;
using System.Xml;
using System.Configuration;
using System.Net;
using System.IO;
using System.Web;

namespace WinFormsClient
{
    /// <summary>
    /// Main application controller
    /// </summary>
    class MainController
    {
        private static readonly String SERVER_HOST_PROP = "server-host";
        private static readonly String ORDER_SERVICE = "order-service";
        private static readonly String PUT_LOG_SERVICE = "log-service";
        private static readonly int ORDER_QUERY_INTERVAL_MILLIS = int.Parse(ConfigurationManager.AppSettings["order-query-interval-millis"]);

        private XmlSerializer xmlSerializer = new XmlSerializer(typeof(ResearchOrder));

        private String researchLogId;

        /// <summary>
        /// Main loop responsible for processing orders, invoked on application start
        /// </summary>
        public void mainLoop()
        {
            Logger.Debug("Starting mainLoop()");
            while (true)
            {
                //take research order from WS (blocking, so no busy loop in here)
                ResearchOrder researchOrder = takeOrder();
                Logger.Debug("New order received");
                resetSettings();
                researchLogId = researchOrder.researchLogId;
                AbstractOperation[] operations = researchOrder.research.operations;
                //iterate through operations
                foreach (AbstractOperation operation in operations)
                {
                    Logger.Info("Executing operation=" + operation.operationType);
                    //switch through operation type (thanks to enumerated operation type), cast and invoke appriopriate operation method
                    switch (operation.operationType)
                    {
                        case OperationType.MANAGE_PERIPHERALS:
                            ManagePeripheralsOperation mpo = (ManagePeripheralsOperation)operation;
                            managePeripherals(mpo);
                            break;
                        case OperationType.SLEEP:
                            SleepOperation so = (SleepOperation)operation;
                            putLog(OperationType.SLEEP, "Sleep begin");
                            Thread.Sleep(so.sleepTimeSeconds * 1000);
                            putLog(OperationType.SLEEP, "Sleep end");
                            break;
                    }
                }
            }
        }

        private void resetSettings()
        {
            Logger.Debug("Resetting PC settings");
            PeripheralsUtil.KeyboardEnabled = true;
            PeripheralsUtil.MouseEnabled = true;
        }

        private void putLog(OperationType operationType, String details)
        {
            Logger.Debug("Creating request");
            String path = ConfigurationManager.AppSettings[PUT_LOG_SERVICE]
                + "/" + researchLogId
                + "/" + Enum.GetName(typeof(OperationType), operationType)
                + "/" + HttpUtility.UrlEncode(details);
            String requestUri = ConfigurationManager.AppSettings[SERVER_HOST_PROP] + path;
            Logger.Debug("Request URI=" + requestUri);
            HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create(requestUri);
            request.Method = "PUT";
            Logger.Debug("Request object created=" + request);
            using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
            {
                Logger.Debug("Response object created=" + response);
                Logger.Debug("Response code=" + response.StatusCode);
            }
        }

        /// <summary>
        /// Returns research ordered by emorg-server. This method blocks until research order is available
        /// </summary>
        /// <returns>Research order given by emorg-server</returns>
        private ResearchOrder takeOrder()
        {
            //return object reference
            ResearchOrder ret = null;
            while (ret == null)
            {
                try
                {
                    ret = takeRequestObject<ResearchOrder>(ConfigurationManager.AppSettings[ORDER_SERVICE]);
                    if (ret.research == null)
                    {
                        //force wait
                        ret = null;
                    }

                }
                catch (Exception e) //exception while making request to WS
                {
                    Logger.Error("Exception while making request to WS", e);
                }
                finally
                {
                    //if no order is given, wait (we don't want to have busy loop)
                    if (ret == null)
                    {
                        Thread.Sleep(ORDER_QUERY_INTERVAL_MILLIS);
                    }
                }
            }
            return ret;
        }

        private T takeRequestObject<T>(params String[] parameters)
        {
            Logger.Debug("Creating request");
            String path = String.Join("/", parameters);
            String requestUri = ConfigurationManager.AppSettings[SERVER_HOST_PROP] + path;
            Logger.Debug("Request URI=" + requestUri);
            HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create(requestUri);
            request.Method = "GET";
            Logger.Debug("Request object created=" + request);
            using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
            {
                Logger.Debug("Response object created=" + response);
                using (Stream responseStream = response.GetResponseStream())
                {
                    Logger.Debug("Response stream acquired, deserializing...");
                    Object ret = xmlSerializer.Deserialize(responseStream);
                    Logger.Debug("Object deserialized");
                    return (T)ret;
                }
            }
        }

        /// <summary>
        /// Handles manage peripherals server request
        /// </summary>
        /// <param name="mpo">operation containing peripheral devices state changes</param>
        public void managePeripherals(ManagePeripheralsOperation mpo)
        {
            Logger.Info("keyboardStateChange=" + mpo.keyboardStateChange + ", mouseStateChange=" + mpo.mouseStateChange);
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

            putLog(OperationType.MANAGE_PERIPHERALS, "keyboardStateChange=" + mpo.keyboardStateChange + ", mouseStateChange=" + mpo.mouseStateChange);
        }
    }
}
