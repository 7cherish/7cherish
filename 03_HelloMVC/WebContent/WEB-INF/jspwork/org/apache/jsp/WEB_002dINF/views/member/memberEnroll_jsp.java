/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.35
 * Generated at: 2018-12-31 07:11:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.kh.member.model.vo.*;

public final class memberEnroll_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1546239526027L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1544431600123L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("com.kh.member.model.vo");
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

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");

	/* Member memberLoggedIn = (Member)request.getAttribute("memberLoggedIn"); */
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	
	// 전송된 쿠키확인
	boolean saveId = false;
	String memberId = "";
	Cookie[] cookies = request.getCookies(); // 배열 타입을 리턴한다.
	// System.out.println("브라우저가 전송한 쿠키목록@header.jsp");
	// System.out.println("-------------------------------------");
	for(Cookie c : cookies){
		String key = c.getName();
		String value = c.getValue();
	// System.out.printf("%s = %s\n", key, value);
		if("saveId".equals(key)){
			saveId = true;
			memberId = value; // abcde
		}
	}
	System.out.println("-------------------------------------");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Hello MVC</title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/css/style.css\" />\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/js/jquery-3.3.1.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("function loginValidate(){\r\n");
      out.write("\tif($(\"#memberId\").val().trim().length == 0){\r\n");
      out.write("\t\talert(\"아이디를 입력하세요.\");\r\n");
      out.write("\t\t$(\"#memberId\").focus();\r\n");
      out.write("\t\treturn false; // 폼 전송 방지\r\n");
      out.write("\t}\r\n");
      out.write("\tif($(\"#password\").val().trim().length == 0){\r\n");
      out.write("\t\talert(\"비밀번호를 입력하세요.\");\r\n");
      out.write("\t\t$(\"#password\").focus();\r\n");
      out.write("\t\treturn false; // 폼 전송 방지\r\n");
      out.write("\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* 마이페이지 이동함수 */\r\n");
      out.write("function goMyPage(){\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t// null인 객체(memberLoggedIn)에 대해 메소드를 실행시키려고 했기 때문에 에러 발생\r\n");
      out.write("\t// 로그인 하기 전에는 memberLoggedIn가 null이기 때문\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("   <div id=\"container\">\r\n");
      out.write("      <header>\r\n");
      out.write("         <h1>Hello MVC</h1>\r\n");
      out.write("         <!--로그인 시작  -->\r\n");
      out.write("         <div class=\"login-container\">\r\n");
      out.write("         ");
if(memberLoggedIn == null){ 
      out.write("\r\n");
      out.write("            <form action=\"");
      out.print(request.getContextPath());
      out.write("/member/login\" method=\"post\" id=\"loginFrm\">\r\n");
      out.write("               <table>\r\n");
      out.write("                  <tr>\r\n");
      out.write("                  <td>\r\n");
      out.write("                     <input type=\"text\" \r\n");
      out.write("                     \t    name=\"memberId\"\r\n");
      out.write("                     \t    id=\"memberId\"\r\n");
      out.write("                     \t    value=\"");
      out.print(memberId );
      out.write("\"\r\n");
      out.write("                     \t    placeholder=\"아이디\" />\r\n");
      out.write("                  </td>\r\n");
      out.write("                  <td></td>\r\n");
      out.write("                  </tr>\r\n");
      out.write("                  <tr>\r\n");
      out.write("                     <td>\r\n");
      out.write("                        <input type=\"password\" \r\n");
      out.write("                        \t   name=\"password\"\r\n");
      out.write("                        \t   id=\"password\"\r\n");
      out.write("                        \t   placeholder=\"비밀번호\" />\r\n");
      out.write("                     </td>\r\n");
      out.write("                     <td>\r\n");
      out.write("                     \t<input type=\"submit\" \r\n");
      out.write("                     \t\t   value=\"로그인\" \r\n");
      out.write("                     \t\t   onclick=\"return loginValidate();\"/>\r\n");
      out.write("                     </td>\r\n");
      out.write("                  </tr>\r\n");
      out.write("                  <tr>\r\n");
      out.write("                  \t<td colspan=\"2\">\r\n");
      out.write("                  \t\t<input type=\"checkbox\" \r\n");
      out.write("                  \t\t\t   name=\"saveId\"\r\n");
      out.write("                  \t\t\t   id=\"saveId\" \r\n");
      out.write("                  \t\t\t   ");
      out.print(saveId?"checked":"" );
      out.write("/>\r\n");
      out.write("                  \t\t<label for=\"saveId\">\r\n");
      out.write("                  \t\t아이디저장\r\n");
      out.write("                  \t\t</label>\r\n");
      out.write("                  \t\t&nbsp;&nbsp;\r\n");
      out.write("                  \t\t<input type=\"button\" \r\n");
      out.write("                  \t\t\t   value=\"회원가입\"\r\n");
      out.write("                  \t\t\t   onclick=\"location.href='");
      out.print(request.getContextPath() );
      out.write("/member/memberEnroll'\" />\r\n");
      out.write("                  \t</td>\r\n");
      out.write("                  </tr>\r\n");
      out.write("               </table>\r\n");
      out.write("         \r\n");
      out.write("         </form>\r\n");
      out.write("         ");
 }
         else {
      out.write("\r\n");
      out.write("         \t<table id=\"logged-in\">\r\n");
      out.write("         \t\t<tr>\r\n");
      out.write("         \t\t\t<td>\r\n");
      out.write("         \t\t\t\t");
      out.print(memberLoggedIn.getMemberName() );
      out.write("님,\r\n");
      out.write("         \t\t\t\t안녕하세요 :)\r\n");
      out.write("         \t\t\t</td>\r\n");
      out.write("         \t\t</tr>\r\n");
      out.write("         \t\t<tr>\r\n");
      out.write("         \t\t\t<td>\r\n");
      out.write("         \t\t\t\t<input type=\"button\" \r\n");
      out.write("         \t\t\t\t\t   value=\"내정보보기\"\r\n");
      out.write("         \t\t\t\t\t   onclick='location.href=\"");
      out.print(request.getContextPath() );
      out.write("/member/memberView?memberId=");
      out.print(memberLoggedIn.getMemberId() );
      out.write("\";' />\r\n");
      out.write("         \t\t\t\t\t\t<!-- // 물음표 기준 왼쪽은 url, 오른쪽은 (사용자)data -->\r\n");
      out.write("         \t\t\t\t&nbsp;\r\n");
      out.write("         \t\t\t\t<input type=\"button\" \r\n");
      out.write("         \t\t\t\t\t   value=\"로그아웃\" \r\n");
      out.write("         \t\t\t\t\t   onclick=\"location.href='");
      out.print(request.getContextPath() );
      out.write("/member/logout'\" />\r\n");
      out.write("         \t\t\t</td>\r\n");
      out.write("         \t\t</tr>\r\n");
      out.write("         \t</table>\r\n");
      out.write("         ");
 } 
      out.write("\r\n");
      out.write("         </div>\r\n");
      out.write("         <!-- 로그인끝 -->\r\n");
      out.write("         <!-- 메인메뉴 시작 -->\r\n");
      out.write("         <!-- nav>ul.main-nav>li*3 -->\r\n");
      out.write("         <nav>\r\n");
      out.write("         \t<ul class=\"main-nav\">\r\n");
      out.write("         \t\t<li><a href=\"#\">Home</a></li>\r\n");
      out.write("         \t\t<li><a href=\"#\">공지사항</a></li>\r\n");
      out.write("         \t\t<li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/board/boardList\">게시판</a></li>\r\n");
      out.write("         \t\t<li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/photo/photoList\">사진게시판</a></li>\r\n");
      out.write("         \t\t\r\n");
      out.write("         \t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t");

						if (memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())) {
					
      out.write("\r\n");
      out.write("\t\t\t\t\t<li><a href=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberList\">회원관리</a></li>\r\n");
      out.write("\t\t\t\t\t");

						}
					
      out.write("\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("         </nav>\r\n");
      out.write("         <!-- 메인메뉴 끝 -->\r\n");
      out.write("         \r\n");
      out.write("      </header>\r\n");
      out.write("      \r\n");
      out.write("      <section id=\"content\">");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("/* 회원가입 유효성검사 */\r\n");
      out.write("function enrollValidate(){\r\n");
      out.write("\t// 아이디 자리수는 4글자 이상 검사\r\n");
      out.write("\tvar $memberId_ = $(\"#memberId_\");\r\n");
      out.write("\tif($memberId_.val().length < 4){\r\n");
      out.write("\t\talert(\"아이디는 4글자 이상 입력하세요.\");\r\n");
      out.write("\t\t$memberId_.select();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 아이디중복검사여부 체크\r\n");
      out.write("\tvar idValid = $(\"#idValid\").val();\r\n");
      out.write("\tif(idValid == 0){\r\n");
      out.write("\t\talert(\"아이디 중복검사해주세요.\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("$(function(){\r\n");
      out.write("\t// 패스워드 일치여부 검사 -> 이벤트핸들러 작성\r\n");
      out.write("\tvar $password_ = $(\"#password_\");\r\n");
      out.write("\tvar $password__ = $(\"#password__\");\r\n");
      out.write("\t$password__.on(\"focusout\", function(){\r\n");
      out.write("\t\tif($password_.val() != $password__.val()){\r\n");
      out.write("\t\t\talert(\"패스워드가 일치하지 않습니다.\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function checkIdDuplicate(){\r\n");
      out.write("\t// 아이디 중복검사폼을 전송\r\n");
      out.write("\tvar memberId = $(\"#memberId_\").val().trim();\r\n");
      out.write("\tif(memberId.length < 4){\r\n");
      out.write("\t\talert(\"아이디는 4글자 이상 가능합니다.\");\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 팝업창을 target으로 폼전송\r\n");
      out.write("\tvar target = \"checkIdDuplicate\";\r\n");
      out.write("\t// 첫 번째 인자 url은 생략, form의 action값이 이를 대신한다.\r\n");
      out.write("\tvar popup = open(\"\", target, \"left=300px, top=100px, height=200px, width=300px\");\r\n");
      out.write("\r\n");
      out.write("\tcheckIdDuplicateFrm.memberId.value = memberId;\r\n");
      out.write("\t\r\n");
      out.write("\t// 폼의 대상을 작성한 popup을 가리키게 한다. 이때 이용하는게 popup창의 이름 => target\r\n");
      out.write("\tcheckIdDuplicateFrm.target = target;\r\n");
      out.write("\tcheckIdDuplicateFrm.submit();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<form action=\"");
      out.print(request.getContextPath() );
      out.write("/member/checkIdDuplicate\"\r\n");
      out.write("\t  method=\"post\"\r\n");
      out.write("\t  name=\"checkIdDuplicateFrm\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"memberId\" />\t  \r\n");
      out.write("</form>\r\n");
      out.write("<section id=\"enroll-container\">\r\n");
      out.write("\t<h2>회원가입 정보 입력</h2>\r\n");
      out.write("\t<form action=\"");
      out.print(request.getContextPath() );
      out.write("/member/memberEnrollEnd\" \r\n");
      out.write("\t\t  method=\"post\"\r\n");
      out.write("\t\t  name=\"memberEnrollFrm\"\r\n");
      out.write("\t\t  onsubmit=\"return enrollValidate();\">\r\n");
      out.write("\t\t  <!-- table>tr>th+td -->\r\n");
      out.write("\t\t  <table>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>아이디</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"text\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"memberId\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"memberId_\"\r\n");
      out.write("\t\t  \t\t\t\t   placeholder=\"4글자 이상 입력하세요.\"\r\n");
      out.write("\t\t  \t\t\t\t   required />\r\n");
      out.write("\t\t  \t\t\t<input type=\"button\"\r\n");
      out.write("\t\t  \t\t\t\t   value=\"중복검사\" \r\n");
      out.write("\t\t  \t\t\t\t   onclick=\"checkIdDuplicate();\" />\r\n");
      out.write("\t\t  \t\t\t<!-- 검사여부 알려주는 태그 -->\r\n");
      out.write("\t\t  \t\t\t<input type=\"hidden\"\r\n");
      out.write("\t\t  \t\t\t\t   name=\"idValid\"\r\n");
      out.write("\t\t  \t\t\t\t   id=\"idValid\"\r\n");
      out.write("\t\t  \t\t\t\t   value=\"0\" />\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>패스워드</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"password\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"password\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"password_\"\r\n");
      out.write("\t\t  \t\t\t\t   required />\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>패스워드 확인</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"password\"\r\n");
      out.write("\t\t  \t\t\t\t   id=\"password__\"\r\n");
      out.write("\t\t  \t\t\t\t   required />\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>이름</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"text\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"memberName\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"memberName\"\r\n");
      out.write("\t\t  \t\t\t\t   required />\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>나이</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"number\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"age\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"age\"\r\n");
      out.write("\t\t  \t\t\t\t   required />\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>이메일</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"email\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"email\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"email\"\r\n");
      out.write("\t\t  \t\t\t\t   placeholder=\"ex)abc@xyz.com\" />\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>휴대폰</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"tel\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"phone\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"phone\"\r\n");
      out.write("\t\t  \t\t\t\t   placeholder=\"ex)01012345678\"\r\n");
      out.write("\t\t  \t\t\t\t   required />\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>주소</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"text\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"address\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"address\" />\r\n");
      out.write("\t\t  \t\t\t\t   <!-- DB에서 not null인건 반드시 required속성 적어줄것! -->\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>성별</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"radio\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"gender\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"gender0\"\r\n");
      out.write("\t\t  \t\t\t\t   value=\"M\" checked />\r\n");
      out.write("\t\t  \t\t\t<label for=\"gender0\">남</label>\r\n");
      out.write("\t\t  \t\t\t<input type=\"radio\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"gender\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"gender1\"\r\n");
      out.write("\t\t  \t\t\t\t   value=\"F\" checked />\r\n");
      out.write("\t\t  \t\t\t<label for=\"gender1\">여</label>\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  \t<tr>\r\n");
      out.write("\t\t  \t\t<th>취미</th>\r\n");
      out.write("\t\t  \t\t<td>\r\n");
      out.write("\t\t  \t\t\t<input type=\"checkbox\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"hobby\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"hobby0\"\r\n");
      out.write("\t\t  \t\t\t\t   value=\"운동\" />\r\n");
      out.write("\t\t  \t\t\t<label for=\"hobby0\">운동</label>\r\n");
      out.write("\t\t  \t\t\t<input type=\"checkbox\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"hobby\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"hobby1\"\r\n");
      out.write("\t\t  \t\t\t\t   value=\"등산\" />\r\n");
      out.write("\t\t  \t\t\t<label for=\"hobby1\">등산</label>\r\n");
      out.write("\t\t  \t\t\t<input type=\"checkbox\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"hobby\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"hobby2\"\r\n");
      out.write("\t\t  \t\t\t\t   value=\"독서\" />\r\n");
      out.write("\t\t  \t\t\t<label for=\"hobby2\">독서</label>\r\n");
      out.write("\t\t  \t\t\t<br />\r\n");
      out.write("\t\t  \t\t\t<input type=\"checkbox\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"hobby\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"hobby3\"\r\n");
      out.write("\t\t  \t\t\t\t   value=\"게임\" />\r\n");
      out.write("\t\t  \t\t\t<label for=\"hobby3\">게임</label>\r\n");
      out.write("\t\t  \t\t\t<input type=\"checkbox\" \r\n");
      out.write("\t\t  \t\t\t\t   name=\"hobby\" \r\n");
      out.write("\t\t  \t\t\t\t   id=\"hobby4\"\r\n");
      out.write("\t\t  \t\t\t\t   value=\"여행\" />\r\n");
      out.write("\t\t  \t\t\t<label for=\"hobby4\">여행</label>\r\n");
      out.write("\t\t  \t\t</td>\r\n");
      out.write("\t\t  \t</tr>\r\n");
      out.write("\t\t  </table>\r\n");
      out.write("\t\t  <input type=\"submit\" value=\"회원가입\" />\r\n");
      out.write("\t\t  <input type=\"reset\" value=\"초기화\" />\r\n");
      out.write("\t</form>\r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" </section>\r\n");
      out.write("      <footer>\r\n");
      out.write("         <p>&lt;Copyright 2017. <strong>KH정보교육원</strong>. All rights reserved.&gt;</p>\r\n");
      out.write("      </footer>\r\n");
      out.write("   </div>\r\n");
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