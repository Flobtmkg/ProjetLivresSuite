<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../resources/img/livre_icon.png"/>
    <link type="text/css" href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="../resources/mycss/menu.css" rel="stylesheet">
    <link type="text/css" href="../resources/mycss/accueil.css" rel="stylesheet">
    <script type="text/javascript" src="../resources/myjs/ecran.js"></script>
    <title></title>
</head>
<body onload="changeImage()">
<img id="imageHd" class="imagePrechargee" src="../resources/img/livreLargeHD.jpg"></img>
<img id="imagenormale" class="imagePrechargee" src="../resources/img/livreLarge.jpg"></img>
<%@ include file="menu.jsp" %>
<div id="presentation">
    <h1>Vous aimez la lecture?</h1>
    <h2><br><br><br>Nous aussi!</h2>
</div>
</body>
</html>
