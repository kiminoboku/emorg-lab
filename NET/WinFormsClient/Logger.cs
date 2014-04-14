using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Diagnostics;

namespace WinFormsClient
{
    class Logger
    {
        public static LogLevel logLevel = LogLevel.ALL;
        static Logger()
        {
            fileWriter.AutoFlush = true;
        }

        private static StreamWriter fileWriter = new StreamWriter("log.txt");
        private static void WriteLine(LogLevel level, Object value)
        {
            if (logLevel >= level)
            {
                String obj = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss.fff") + " " + level + " " + value;
                fileWriter.WriteLine(obj);
                System.Diagnostics.Debug.WriteLine(obj);
            }
        }

        public static void Error(Object value, Exception e)
        {
            String message = value.ToString() + "\n" + e + "\n" + new StackTrace(e);
            Error(message);
        }

        public static void Error(Object value)
        {
            WriteLine(LogLevel.ERROR, value);
        }

        public static void Info(Object value)
        {
            WriteLine(LogLevel.INFO, value);
        }

        public static void Debug(Object value)
        {
            WriteLine(LogLevel.DEBUG, value);
        }
    }

    enum LogLevel
    {
        OFF,
        ERROR,
        INFO,
        DEBUG,
        ALL
    }
}
