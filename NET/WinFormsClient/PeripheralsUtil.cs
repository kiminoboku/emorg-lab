using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.InteropServices;
using System.Windows.Forms;
using System.Diagnostics;

namespace WinFormsClient
{
    class PeripheralsUtil
    {
        private delegate IntPtr LowLevelCallback(int nCode, IntPtr wParam, IntPtr lParam);
        private static IntPtr emptyPointer = new IntPtr(1);
        
        private static IntPtr mouseHookPointer = IntPtr.Zero;
        private static LowLevelCallback mouseCallbackField = mouseCallback;
        public static bool MouseEnabled { get; set; }

        private static IntPtr keyboardHookPointer = IntPtr.Zero;
        private static LowLevelCallback keyboardCallbackField = keyboardCallback;
        public static bool KeyboardEnabled { get; set; }

        static PeripheralsUtil()
        {
            MouseEnabled = true;
            KeyboardEnabled = true;
        }

        private PeripheralsUtil()
        {
        }

        private enum HookType
        {
            //WH_MOUSE_LL
            MOUSE = 14,
            //WH_KEYBOARD_LL
            KEYBOARD = 13
        }

        public static void Init()
        {
            mouseHookPointer = Attach((int)HookType.MOUSE, mouseCallbackField);
            keyboardHookPointer = Attach((int)HookType.KEYBOARD, keyboardCallbackField);
        }

        public static void Cleanup()
        {
            UnhookWindowsHookEx(mouseHookPointer);
            UnhookWindowsHookEx(keyboardHookPointer);
        }

        private static IntPtr Attach(int idHook, LowLevelCallback callback)
        {
            using (Process currentProcess = Process.GetCurrentProcess())
            using (ProcessModule currentModule = currentProcess.MainModule)
            {
                return SetWindowsHookEx(idHook, callback, GetModuleHandle(currentModule.ModuleName), 0);
            }
        }

        private static IntPtr mouseCallback(int nCode, IntPtr wParam, IntPtr lParam)
        {
            if (MouseEnabled)
            {
                return CallNextHookEx(mouseHookPointer, nCode, wParam, lParam);
            }
            else
            {
                return emptyPointer;
            }
        }

        private static IntPtr keyboardCallback(int nCode, IntPtr wParam, IntPtr lParam)
        {
            if (KeyboardEnabled)
            {
                return CallNextHookEx(keyboardHookPointer, nCode, wParam, lParam);
            }
            else
            {
                return emptyPointer;
            }
        }

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
