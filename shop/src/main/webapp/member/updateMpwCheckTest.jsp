<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shop.member.MemberDTO" %>
<%@ page import="shop.member.MemberDAO" %>

<%--비밀번호 일치 확인 --%>
<%
	request.setCharacterEncoding("UTF-8");
	String mid = request.getParameter("mid");
	String mpw = request.getParameter("mpw");
	
	MemberDAO mdao = new MemberDAO();
	MemberDTO mdto = mdao.getInfo(mid);
	
	//세션아이디에 해당하는 정보가 있고 입력받은 비밀번호 값과 세션아이디의 비밀번호가 일치한다면
	if(mdto != null && mdto.getMpw().equals(mpw)){
%>		<script>
			opener.location.href="/shop/member/memberUpdateSet.jsp";
			window.close();
		</script>
	<%} else{%>
			<script>
				alert("비밀번호가 일치하지 않습니다.");
				window.close();
			</script>
	<%} %>
