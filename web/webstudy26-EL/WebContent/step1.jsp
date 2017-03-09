<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.CarDAO"%>
<%@page import="model.CarVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL(Expression Language)</title>
</head>
<body>
<% CarVO car = new CarVO("1","소나타",200);
request.setAttribute("cvo", car);

%>
1. 기존 방식으로 request cvo에서 model명 출력
<% CarVO vo = (CarVO) request.getAttribute("cvo"); %>
모델: <%=vo.getModel() %>
<hr>
2. EL로 request cvo에서 model명 출력
${requestScope.cvo.model}
<hr>
<% ArrayList<CarVO> list = CarDAO.getInstance().getAllCarList();
session.setAttribute("carList", list);
%>
3. 기존 방식으로 세션의 carList에 첫번째 인덱스의 모델과 가격을 출력
<% @SuppressWarnings("unchecked")
ArrayList<CarVO> carList = (ArrayList<CarVO>)session.getAttribute("carList");
CarVO vo1 = carList.get(0);
%>
모델:<%= vo1.getModel() %> 가격:<%= vo1.getPrice() %>
<hr>
4. EL 방식으로 세션의 carList에 첫번째 인덱스의 모델과 가격을 출력<br>
모델 ${sessionScope.carList[0].model } 가격 ${sessionScope.carList[0].price }
<hr>
<% HashMap<String,CarVO> map = new HashMap<String,CarVO>();
map.put("a", new CarVO("1","아우디",100));
session.setAttribute("carMap", map);
%>
5. 기존방식으로 세션의 carMap의 a key의 car model명을 출력
<% @SuppressWarnings("unchecked")
HashMap<String,CarVO> rm = (HashMap<String,CarVO>)session.getAttribute("carMap"); %>
<%= rm.get("a").getModel() %>
<hr>
6. EL 방식으로 세션의 carMap의 a key의 car model명을 출력
${ sessionScope.carMap.a.model}
<hr>
<a href="step2.jsp?memberName=김문일&age=21">step2로 이동</a>
</body>
</html>