using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.InteropServices;
using System.Windows.Forms;
using System.Diagnostics;

namespace WinFormsClient
{
    /// <summary>
    /// Util class responsible for enabling/disabling peripheral devices (mouse and keyboard)
    /// </summary>
    class PeripheralsUtil
    {
        //callback function definition
        private delegate IntPtr LowLevelCallback(int nCode, IntPtr wParam, IntPtr lParam);
        //pointer used to brake chain of command (bubbling peripheral events)
        private static IntPtr emptyPointer = new IntPtr(1);
        
        //pointer to mouse delegate function
        private static IntPtr mouseHookPointer = IntPtr.Zero;
        //function taking control over mouse events flow
        private static LowLevelCallback mouseCallbackField = mouseCallback;
        //property determining mouse state
        public static bool MouseEnabled { get; set; }

        //pointer to keyboard delegate function
        private static IntPtr keyboardHookPointer = IntPtr.Zero;
        //function taking control over keyboard events flow
        private static LowLevelCallback keyboardCallbackField = keyboardCallback;
        //property determining keyboard state
        public static bool KeyboardEnabled { get; set; }

        //static constructor
        static PeripheralsUtil()
        {
            MouseEnabled = true;
            KeyboardEnabled = true;
        }

        //private constructor (this is an util class)
        private PeripheralsUtil()
        {
        }

        //low-level hook ids
        private enum HookType
        {
            //WH_MOUSE_LL
            MOUSE = 14,
            //WH_KEYBOARD_LL
            KEYBOARD = 13
        }

        /// <summary>
        /// Attaches proxy functions to devices chain of events
        /// </summary>
        public static void Init()
        {
            mouseHookPointer = Attach((int)HookType.MOUSE, mouseCallbackField);
            keyboardHookPointer = Attach((int)HookType.KEYBOARD, keyboardCallbackField);
        }

        /// <summary>
        /// Unregisters proxy functions
        /// </summary>
        public static void Cleanup()
        {
            UnhookWindowsHookEx(mouseHookPointer);
            UnhookWindowsHookEx(keyboardHookPointer);
        }

        /// <summary>
        /// Attaches given delegate function to given id type
        /// </summary>
        /// <param name="idHook"></param>
        /// <param name="callback"></param>
        /// <returns></returns>
        private static IntPtr Attach(int idHook, LowLevelCallback callback)
        {
            using (Process currentProcess = Process.GetCurrentProcess())
            using (ProcessModule currentModule = currentProcess.MainModule)
            {
                return SetWindowsHookEx(idHook, callback, GetModuleHandle(currentModule.ModuleName), 0);
            }
        }

        /// <summary>
        /// Function breaking the chain of mouse events
        /// </summary>
        /// <param name="nCode"></param>
        /// <param name="wParam"></param>
        /// <param name="lParam"></param>
        /// <returns></returns>
        private static IntPtr mouseCallback(int nCode, IntPtr wParam, IntPtr lParam)
        {
            //if enabled
            if (MouseEnabled)
            {
                //proceed with chain of command
                return CallNextHookEx(mouseHookPointer, nCode, wParam, lParam);
            }
            else //disabled
            {
                //do nothing
                return emptyPointer;
            }
        }

        /// <summary>
        /// Function breaking the chain of keyboard events
        /// </summary>
        /// <param name="nCode"></param>
        /// <param name="wParam"></param>
        /// <param name="lParam"></param>
        /// <returns></returns>
        private static IntPtr keyboardCallback(int nCode, IntPtr wParam, IntPtr lParam)
        {
            //if enabled
            if (KeyboardEnabled)
            {
                //proceed with chain of command
                return CallNextHookEx(keyboardHookPointer, nCode, wParam, lParam);
            }
            else //disabled
            {
                //do nothing
                return emptyPointer;
            }
        }

        //Magic low-level functions needed to intercept devices' events
        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern IntPtr SetWindowsHookEx(int idHook, LowLevelCallback lpfn, IntPtr hMod, uint dwThreadId);

        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        [return: MarshalAs(UnmanagedType.Bool)]
        private static extern bool UnhookWindowsHookEx(IntPtr hhk);

        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern IntPtr CallNextHookEx(IntPtr hhk, int nCode, IntPtr wParam, IntPtr lParam);

        [DllImport("kernel32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern IntPtr GetModuleHandle(string lpModuleName);
    }
}
