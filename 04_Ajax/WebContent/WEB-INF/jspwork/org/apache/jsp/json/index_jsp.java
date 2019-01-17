/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.35
 * Generated at: 2018-12-28 09:14:27 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.json;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
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
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
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

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>ajax : json</title>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/js/jquery-3.3.1.js\"></script>\r\n");
      out.write("<style>\r\n");
      out.write(".area{\r\n");
      out.write("\tborder: 1px solid red;\r\n");
      out.write("\twidth: 300px;\r\n");
      out.write("\tmin-height: 50px;\r\n");
      out.write("\tmargin: 10px;\r\n");
      out.write("\tpadding: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("table, td{\r\n");
      out.write("\tborder: 1px solid;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h2>ajax : json</h2>\r\n");
      out.write("\t<h3>gson라이브러리의 활용</h3>\r\n");
      out.write("\t\r\n");
      out.write("\t<hr />\r\n");
      out.write("\t<h3>1. 전체회원조회</h3>\r\n");
      out.write("\t<button id=\"btn1\">실행</button>\r\n");
      out.write("\t<div id=\"area1\" class=\"area\"></div>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t$(\"#btn1\").on(\"click\", function(){\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl: \"");
      out.print(request.getContextPath());
      out.write("/gson/selectAll.do\",\r\n");
      out.write("\t\t\ttype: \"get\",\r\n");
      out.write("\t\t\tdataType: \"json\",\r\n");
      out.write("\t\t\tsuccess: function(data){\r\n");
      out.write("\t\t\t\tconsole.log(\"btn1_data@index.jsp=\", data); // data는 이미 javascript배열객체이다.\r\n");
      out.write("\t\t\t\t// json과 javascript객체간 왔다갔다할때 데이터를 변경했었다.\r\n");
      out.write("\t\t\t\t// json <---> javascript\r\n");
      out.write("\t\t\t\t//      <---- JSON.stringify\r\n");
      out.write("\t\t\t\t//      ----> JSON.parse()\r\n");
      out.write("\t\t\t\tvar table = $(\"<table></table>\");\r\n");
      out.write("\t\t\t\tfor(var i in data){\r\n");
      out.write("\t\t\t\t\t// 배열을 for in문으로 돌리면 index가 담긴다.\r\n");
      out.write("\t\t\t\t\tvar user = data[i];\r\n");
      out.write("\t\t\t\t\tvar html = \"<tr><td>\" + user.userId+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+ user.userName +\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+ user.userAddr +\"</td></tr>\";\r\n");
      out.write("\t\t\t\t\ttable.append(html);\r\n");
      out.write("\t\t\t\t} // end of for in\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(\"#area1\").html(table);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}, // end of success\r\n");
      out.write("\t\t\terror: function(jqxhr, textStatus, errorThrow){\r\n");
      out.write("\t\t\t\tconsole.log(\"btn1_ajax처리실패!@index.jsp\");\r\n");
      out.write("\t\t\t\tconsole.log(\"btn1_jqxhr@index.jsp=\", jqxhr);\r\n");
      out.write("\t\t\t\tconsole.log(\"btn1_textStatus@index.jsp=\", textStatus);\r\n");
      out.write("\t\t\t\tconsole.log(\"btn1_errorThrow@index.jsp=\", errorThrow);\r\n");
      out.write("\t\t\t} // end of error\r\n");
      out.write("\t\t}); // end of ajax\r\n");
      out.write("\t}); // end of btn1 click\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<hr />\r\n");
      out.write("\t<!-- 지금 별도로 부여된 회원번호는 없고 리스트에 저장 돼 있으니까 -->\r\n");
      out.write("\t<h3>2. 리스트인덱스로 회원조회</h3>\r\n");
      out.write("\t<input type=\"number\" id=\"input2\" />\r\n");
      out.write("\t<button id=\"btn2\">실행</button>\r\n");
      out.write("\t<div id=\"area2\" class=\"area\"></div>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t$(\"#btn2\").click(function(){\r\n");
      out.write("\t\tvar index = $(\"#input2\").val();\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl: \"");
      out.print(request.getContextPath());
      out.write("/gson/selectOneByIndex.do\",\r\n");
      out.write("\t\t\ttype: \"get\",\r\n");
      out.write("\t\t\tdata: \"index=\" + index, // 직렬화된 문자열가능\r\n");
      out.write("\t\t\t// data: \"index=\" + index +\"&name=\" + name, // 여러 건이라면 이런 식으로\r\n");
      out.write("\t\t\t// data: {index: index}, // 객체로 전달가능\r\n");
      out.write("\t\t\tsuccess: function(data){\r\n");
      out.write("\t\t\t\tconsole.log(\"btn2_data@index.jsp=\", data);\r\n");
      out.write("\t\t\t\tvar table = $(\"<table></table>\");\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif(data!=null){\r\n");
      out.write("\t\t\t\t\tvar user = data;\r\n");
      out.write("\t\t\t\t\tvar html = \"\";\r\n");
      out.write("\t\t\t\t\thtml = \"<tr><td>아이디</td><td>\" + user.userId+\"</td></tr>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>이름</td><td>\" + user.userName + \"</td></tr>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>주소</td><td>\" + user.userAddr + \"</td></tr>\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse{\r\n");
      out.write("\t\t\t\t\tvar html = \"<tr><td>없어</td></tr>\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\ttable.append(html);\r\n");
      out.write("\t\t\t\t$(\"#area2\").html(table);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}, // end of success\r\n");
      out.write("\t\t\terror: function(jqxhr, textStatus, errorThrow){\r\n");
      out.write("\t\t\t\tconsole.log(\"btn2_ajax처리실패!@index.jsp\");\r\n");
      out.write("\t\t\t\tconsole.log(\"btn2_jqxhr@index.jsp=\", jqxhr);\r\n");
      out.write("\t\t\t\tconsole.log(\"btn2_textStatus@index.jsp=\", textStatus);\r\n");
      out.write("\t\t\t\tconsole.log(\"btn2_errorThrow@index.jsp=\", errorThrow);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}); // end of ajax\r\n");
      out.write("\t}); // end of btn2 click\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t<hr />\r\n");
      out.write("\t<h3>@실습문제 : 사용자 정보 등록하기</h3>\r\n");
      out.write("\t<p>\r\n");
      out.write("\t\t입력받은 사용자 정보를 ajax를 통해서 \r\n");
      out.write("\t\t회원목록(UserListSingleton)에 추가하세요. <br />\r\n");
      out.write("\t\t그리고, 추가된 회원전체목록을 리턴해서 출력하세요.\r\n");
      out.write("\t</p>\r\n");
      out.write("\t<input type=\"text\" id=\"userId\" placeholder=\"아이디\" />\r\n");
      out.write("\t<br />\r\n");
      out.write("\t<input type=\"text\" id=\"userName\" placeholder=\"이름\" />\r\n");
      out.write("\t<br />\r\n");
      out.write("\t<input type=\"text\" id=\"userAddr\" placeholder=\"주소\" />\r\n");
      out.write("\t<br />\r\n");
      out.write("\t<button id=\"btn3\">사용자 등록</button>\r\n");
      out.write("\t<div id=\"area3\" class=\"area\"></div>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t// 호출 url : /gson/insertUser.do\r\n");
      out.write("\t$(\"#btn3\").click(function(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl: \"");
      out.print(request.getContextPath());
      out.write("/gson/insertUser.do\",\r\n");
      out.write("\t\t\ttype: \"get\",\r\n");
      out.write("\t\t\tdata: {userId:$(\"#userId\").val(),\r\n");
      out.write("\t\t\t\t   userName:$(\"#userName\").val(),\r\n");
      out.write("\t\t\t\t   userAddr:$(\"#userAddr\").val()},\r\n");
      out.write("\t\t\tsuccess: function(data){\r\n");
      out.write("\t\t\t\tconsole.log(\"btn3_data@index.jsp=\", data);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tvar table = $(\"<table></table>\");\r\n");
      out.write("\t\t\t\tfor(var i in data){\r\n");
      out.write("\t\t\t\t\t// 배열을 for in문으로 돌리면 index가 담긴다.\r\n");
      out.write("\t\t\t\t\tvar user = data[i];\r\n");
      out.write("\t\t\t\t\tvar html = \"<tr><td>\" + user.userId+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+ user.userName +\"</td>\";\r\n");
      out.write("\t\t\t\t\thtml += \"<td>\"+ user.userAddr +\"</td></tr>\";\r\n");
      out.write("\t\t\t\t\ttable.append(html);\r\n");
      out.write("\t\t\t\t} // end of for in\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(\"#area3\").html(table);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("</body>\r\n");
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
