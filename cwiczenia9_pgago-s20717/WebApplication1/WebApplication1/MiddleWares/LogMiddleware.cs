using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.MiddleWares
{
    public class LogMiddleware
    {
        private readonly RequestDelegate next;

        public LogMiddleware(RequestDelegate next)
        {
            this.next = next;
        }
        public async Task Invoke(HttpContext http)
        {
            try
            {
                await next(http);
            }
            catch(Exception ex)
            {
                         using (StreamWriter sw = File.AppendText("log.txt"))
                        {
                            sw.WriteLine(ex.Message);
                        }
                http.Response.StatusCode = 500;
            }
        }
    }
}
