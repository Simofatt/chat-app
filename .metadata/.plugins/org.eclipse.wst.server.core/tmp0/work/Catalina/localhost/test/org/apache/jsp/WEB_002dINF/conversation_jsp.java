/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.85
 * Generated at: 2023-03-24 00:50:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import comm.octest.beans.Utilisateur;
import java.util.List;
import comm.octest.beans.ChatRoom;
import comm.octest.db.Pseudo;
import java.lang.reflect.Array;
import java.util.ArrayList;
import comm.octest.beans.Message;

public final class conversation_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("jar:file:/C:/Users/Simofatt/workspace2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/test/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153377882000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1678212913362L));
    _jspx_dependants.put("/WEB-INF/taglibs.jsp", Long.valueOf(1678214133056L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("java.lang.reflect.Array");
    _jspx_imports_classes.add("comm.octest.beans.Utilisateur");
    _jspx_imports_classes.add("comm.octest.db.Pseudo");
    _jspx_imports_classes.add("comm.octest.beans.Message");
    _jspx_imports_classes.add("comm.octest.beans.ChatRoom");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Les JSPs ne permettent que GET, POST ou HEAD. Jasper permet aussi OPTIONS");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("      \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Document</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("     ");
 

            String email =  (String) session.getAttribute("email");
            Pseudo user = new Pseudo(email);
            ArrayList<Message> my_messages = new ArrayList<Message>();
            ArrayList<String> contacts  =(ArrayList<String>) request.getAttribute("contacts") ;
            for(String c : contacts) { 
            my_messages = (ArrayList<Message>) user.getMessagesWithUser(c);
            }
            

              


        
      out.write("\r\n");
      out.write("           \r\n");
      out.write("   <script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("   window.addEventListener(\"load\", function (event) {\r\n");
      out.write("\r\n");
      out.write("       let pseudo = \"");
      out.print(email);
      out.write("\";\r\n");
      out.write("      \r\n");
      out.write("\r\n");
      out.write("       let ws = new WebSocket(\"ws://localhost:8085/test/chatroom/\" + pseudo);\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("       let txtHistory = document.getElementByClassName(\"message\");\r\n");
      out.write("   \r\n");
      out.write("       txtMessage.focus();\r\n");
      out.write("\r\n");
      out.write("       ws.addEventListener(\"open\", function (evt) {\r\n");
      out.write("           console.log(\"Connection established\");\r\n");
      out.write("       });\r\n");
      out.write("\r\n");
      out.write("       ws.addEventListener(\"message\", function (evt) {\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("           let message_infos = evt.data;\r\n");
      out.write("           let infos_arr = message_infos.split(\",\");\r\n");
      out.write("           if(infos_arr[0] == withClientId){\r\n");
      out.write("               let msgData = infos_arr[2].split(\":\");\r\n");
      out.write("               let typeMsg = msgData[0];\r\n");
      out.write("               if (typeMsg == \"Msg\"){\r\n");
      out.write("                   console.log(\"Receive new message: \" + message_infos);\r\n");
      out.write("                   txtHistory.innerHTML += ' <div class=\"details\"> <p>'+ msgData[1]+ '</p></div> ;\r\n");
      out.write("               }\r\n");
      out.write("\r\n");
      out.write("                 //name.innerHTML += withClientId ;\r\n");
      out.write("                 \r\n");
      out.write("               \r\n");
      out.write("               \r\n");
      out.write("              \r\n");
      out.write("       });\r\n");
      out.write("\r\n");
      out.write("       ws.addEventListener(\"close\", function (evt) {\r\n");
      out.write("           console.log(\"Connection closed\");\r\n");
      out.write("       });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("     \r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("       let btnClose = document.getElementById(\"btnClose\");\r\n");
      out.write("       btnClose.addEventListener(\"click\", function (clickEvent) {\r\n");
      out.write("           ws.close();\r\n");
      out.write("       });\r\n");
      out.write("\r\n");
      out.write("   });\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("  <div class=\"container\">\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("      <nav class=\"menu\">\r\n");
      out.write("        <ul class=\"items\">\r\n");
      out.write("          <li class=\"item\">\r\n");
      out.write("            <i class=\"fa fa-home\" aria-hidden=\"true\"></i>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"item\">\r\n");
      out.write("            <i class=\"fa fa-user\" aria-hidden=\"true\"></i>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"item\">\r\n");
      out.write("            <i class=\"fa fa-pencil\" aria-hidden=\"true\"></i>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"item item-active\">\r\n");
      out.write("            <i class=\"fa fa-commenting\" aria-hidden=\"true\"></i>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"item\">\r\n");
      out.write("            <i class=\"fa fa-file\" aria-hidden=\"true\"></i>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"item\">\r\n");
      out.write("            <i class=\"fa fa-cog\" aria-hidden=\"true\"></i>\r\n");
      out.write("          </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </nav>\r\n");
      out.write("\r\n");
      out.write("      <section class=\"discussions\">\r\n");
      out.write("        <div class=\"discussion search\">\r\n");
      out.write("          <div class=\"title\">\r\n");
      out.write("            <i class=\"fa fa-search\" aria-hidden=\"true\"></i>\r\n");
      out.write("            <p class=\"text\">Discussions</p>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"discussion message-active\">\r\n");
      out.write("          <div class=\"photo\" style=\"background-image: url(https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80);\">\r\n");
      out.write("            <div class=\"online\"></div>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"message\">\r\n");
      out.write("           \r\n");
      out.write("             \r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"timer\">12 sec</div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("      \r\n");
      out.write("      </section>\r\n");
      out.write("      \r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("<style>\r\n");
      out.write("body {\r\n");
      out.write("  padding: 5%;\r\n");
      out.write("  background-color: #F5F5F5;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("/* ===== MENU ===== */\r\n");
      out.write(".menu {\r\n");
      out.write("  float: left;\r\n");
      out.write("  height: 700px;;\r\n");
      out.write("  width: 70px;\r\n");
      out.write("  background: black;\r\n");
      out.write(" \r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".menu .items {\r\n");
      out.write("  list-style:none;\r\n");
      out.write("  margin: auto;\r\n");
      out.write("  padding: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".menu .items .item {\r\n");
      out.write("  height: 70px;\r\n");
      out.write("  border-bottom: 1px solid black;\r\n");
      out.write("  display:flex;\r\n");
      out.write("  justify-content: center;\r\n");
      out.write("  align-items: center;\r\n");
      out.write("  color: black;\r\n");
      out.write("  font-size: 17pt;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("/* === CONVERSATIONS === */\r\n");
      out.write("\r\n");
      out.write(".discussions {\r\n");
      out.write("  width:90%;\r\n");
      out.write("  height: 700px;\r\n");
      out.write("  box-shadow: 0px 8px 10px rgba(0,0,0,0.20);\r\n");
      out.write("  overflow: hidden;\r\n");
      out.write("  background-color: black;\r\n");
      out.write("  display: inline-block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".discussions .discussion {\r\n");
      out.write("  width: 100%;\r\n");
      out.write("  height: 90px;\r\n");
      out.write("  background-color: #FAFAFA;\r\n");
      out.write("  border-bottom: solid 1px #E0E0E0;\r\n");
      out.write("  display:flex;\r\n");
      out.write("  align-items: center;\r\n");
      out.write("  cursor: pointer;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".text {\r\n");
      out.write("  height:38px;\r\n");
      out.write("  width:100%;\r\n");
      out.write("  border:none;\r\n");
      out.write("  font-size: 100%;\r\n");
      out.write("  font-weight: 1000;\r\n");
      out.write("  font-family: 'Montserrat', sans-serif;\r\n");
      out.write("  border: black;\r\n");
      out.write("  padding-left: 550px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(".discussions .message-active {\r\n");
      out.write("  width: 98.5%;\r\n");
      out.write("  height: 90px;\r\n");
      out.write("  background-color: #FFF;\r\n");
      out.write("  border-bottom: solid 1px #E0E0E0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".discussions .discussion .photo {\r\n");
      out.write("    margin-left:20px;\r\n");
      out.write("    display: block;\r\n");
      out.write("    width: 45px;\r\n");
      out.write("    height: 45px;\r\n");
      out.write("    background: #E6E7ED;\r\n");
      out.write("    -moz-border-radius: 50px;\r\n");
      out.write("    -webkit-border-radius: 50px;\r\n");
      out.write("    background-position: center;\r\n");
      out.write("    background-size: cover;\r\n");
      out.write("    background-repeat: no-repeat;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".online {\r\n");
      out.write("  position: relative;\r\n");
      out.write("  top: 30px;\r\n");
      out.write("  left: 35px;\r\n");
      out.write("  width: 13px;\r\n");
      out.write("  height: 13px;\r\n");
      out.write("  background-color: #8BC34A;\r\n");
      out.write("  border-radius: 13px;\r\n");
      out.write("  border: 3px solid #FAFAFA;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".desc-contact {\r\n");
      out.write("  height: 43px;\r\n");
      out.write("  width:50%;\r\n");
      out.write("  white-space: nowrap;\r\n");
      out.write("  overflow: hidden;\r\n");
      out.write("  text-overflow: ellipsis;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".discussions .discussion .name {\r\n");
      out.write("  margin: 0 0 0 20px;\r\n");
      out.write("  font-family:'Montserrat', sans-serif;\r\n");
      out.write("  font-size: 11pt;\r\n");
      out.write("  color:#515151;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".discussions .discussion .message {\r\n");
      out.write("  margin: 6px 0 0 20px;\r\n");
      out.write("  font-family:'Montserrat', sans-serif;\r\n");
      out.write("  font-size: 9pt;\r\n");
      out.write("  color:#515151;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".timer {\r\n");
      out.write("  margin-left: 15%;\r\n");
      out.write("  font-family:'Open Sans', sans-serif;\r\n");
      out.write("  font-size: 11px;\r\n");
      out.write("  padding: 3px 8px;\r\n");
      out.write("  color: #BBB;\r\n");
      out.write("  background-color: #FFF;\r\n");
      out.write("  border: 1px solid #E5E5E5;\r\n");
      out.write("  border-radius: 15px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat {\r\n");
      out.write("  width: calc(65% - 85px);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".header-chat {\r\n");
      out.write("  background-color: #FFF;\r\n");
      out.write("  height: 90px;\r\n");
      out.write("  box-shadow: 0px 3px 2px rgba(0,0,0,0.100);\r\n");
      out.write("  display:flex;\r\n");
      out.write("  align-items: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .header-chat .icon {\r\n");
      out.write("  margin-left: 30px;\r\n");
      out.write("  color:#515151;\r\n");
      out.write("  font-size: 14pt;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .header-chat .name {\r\n");
      out.write("  margin: 0 0 0 20px;\r\n");
      out.write("  text-transform: uppercase;\r\n");
      out.write("  font-family:'Montserrat', sans-serif;\r\n");
      out.write("  font-size: 13pt;\r\n");
      out.write("  color:#515151;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .header-chat .right {\r\n");
      out.write("  position: absolute;\r\n");
      out.write("  right: 40px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .messages-chat {\r\n");
      out.write("  padding: 25px 35px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .messages-chat .message {\r\n");
      out.write("  display:flex;\r\n");
      out.write("  align-items: center;\r\n");
      out.write("  margin-bottom: 8px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .messages-chat .message .photo {\r\n");
      out.write("    display: block;\r\n");
      out.write("    width: 45px;\r\n");
      out.write("    height: 45px;\r\n");
      out.write("    background: #E6E7ED;\r\n");
      out.write("    -moz-border-radius: 50px;\r\n");
      out.write("    -webkit-border-radius: 50px;\r\n");
      out.write("    background-position: center;\r\n");
      out.write("    background-size: cover;\r\n");
      out.write("    background-repeat: no-repeat;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .messages-chat .text {\r\n");
      out.write("  margin: 0 35px;\r\n");
      out.write("  background-color: #f6f6f6;\r\n");
      out.write("  padding: 15px;\r\n");
      out.write("  border-radius: 12px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".text-only {\r\n");
      out.write("  margin-left: 45px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".time {\r\n");
      out.write("  font-size: 10px;\r\n");
      out.write("  color:lightgrey;\r\n");
      out.write("  margin-bottom:10px;\r\n");
      out.write("  margin-left: 85px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".response-time {\r\n");
      out.write("  float: right;\r\n");
      out.write("  margin-right: 40px !important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".response {\r\n");
      out.write("  float: right;\r\n");
      out.write("  margin-right: 0px !important;\r\n");
      out.write("  margin-left:auto; /* flexbox alignment rule */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".response .text {\r\n");
      out.write("  background-color: #e3effd !important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".footer-chat {\r\n");
      out.write("  width: calc(65% - 66px);\r\n");
      out.write("  height: 80px;\r\n");
      out.write("  display:flex;\r\n");
      out.write("  align-items: center;\r\n");
      out.write("  position:absolute;\r\n");
      out.write("  bottom: 0;\r\n");
      out.write("  background-color: transparent;\r\n");
      out.write("  border-top: 2px solid #EEE;\r\n");
      out.write("  \r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .footer-chat .icon {\r\n");
      out.write("  margin-left: 30px;\r\n");
      out.write("  color:#C0C0C0;\r\n");
      out.write("  font-size: 14pt;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .footer-chat .send {\r\n");
      out.write("  color:#fff;\r\n");
      out.write("  background-color: #4f6ebd;\r\n");
      out.write("  position: absolute;\r\n");
      out.write("  right: 50px;\r\n");
      out.write("  padding: 12px 12px 12px 12px;\r\n");
      out.write("  border-radius: 50px;\r\n");
      out.write("  font-size: 14pt;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .footer-chat .name {\r\n");
      out.write("  margin: 0 0 0 20px;\r\n");
      out.write("  text-transform: uppercase;\r\n");
      out.write("  font-family:'Montserrat', sans-serif;\r\n");
      out.write("  font-size: 13pt;\r\n");
      out.write("  color:#515151;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat .footer-chat .right {\r\n");
      out.write("  position: absolute;\r\n");
      out.write("  right: 40px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".write-message {\r\n");
      out.write("  border:none !important;\r\n");
      out.write("  width:60%;\r\n");
      out.write("  height: 50px;\r\n");
      out.write("  margin-left: 20px;\r\n");
      out.write("  padding: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".footer-chat *::-webkit-input-placeholder {\r\n");
      out.write("  color: #C0C0C0;\r\n");
      out.write("  font-size: 13pt;\r\n");
      out.write("}\r\n");
      out.write(".footer-chat input *:-moz-placeholder {\r\n");
      out.write("  color: #C0C0C0;\r\n");
      out.write("  font-size: 13pt;\r\n");
      out.write("}\r\n");
      out.write(".footer-chat input *::-moz-placeholder {\r\n");
      out.write("  color: #C0C0C0;\r\n");
      out.write("  font-size: 13pt;\r\n");
      out.write("  margin-left:5px;\r\n");
      out.write("}\r\n");
      out.write(".footer-chat input *:-ms-input-placeholder {\r\n");
      out.write("  color: #C0C0C0;\r\n");
      out.write("  font-size: 13pt;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".clickable {\r\n");
      out.write("  cursor: pointer;\r\n");
      out.write("}</style>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
