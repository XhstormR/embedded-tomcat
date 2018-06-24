package com.xhstormr.tomcat

import org.apache.catalina.startup.Tomcat
import java.util.logging.LogManager
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

fun main(args: Array<String>) {
    LogManager.getLogManager().reset()
    System.setProperty("catalina.base", System.getProperty("java.io.tmpdir"))

    val tomcat = Tomcat().apply {
        this.setPort(8080)
        this.connector
    }

    val context = tomcat.addContext("", null)

    val str1 = "Servlet1"
    val str2 = "Servlet2"
    val str3 = "HomeServlet"

    Tomcat.addServlet(context, str1, object : HttpServlet() {
        override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
            resp.writer.println("<h1>1</h1>")
        }
    })
    Tomcat.addServlet(context, str2, object : HttpServlet() {
        override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
            resp.writer.println("<h1>2</h1>")
        }
    })
    Tomcat.addServlet(context, str3, object : HttpServlet() {
        override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
            resp.writer.println("<h1>Home</h1>")
        }
    })

    context.addServletMappingDecoded("/1", str1)
    context.addServletMappingDecoded("/2", str2)
    context.addServletMappingDecoded("/*", str3)

    tomcat.start()
    tomcat.server.await()
}
