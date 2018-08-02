<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="/resources/img/livre_icon.png"/>
    <!--<link type="text/css" href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
    <!-- bibliothèques pour bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
    <link type="text/css" href="/resources/mycss/menu.css" rel="stylesheet">
    <link type="text/css" href="/resources/mycss/accueil.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/myjs/ecran.js"></script>
    <title>Services en ligne de votre biliothèque</title>
    </head>
    <body onresize="changeImage()" onload="changeImage()">
    <!-- Necessaire au système d'adaptation d'images automatique controlé en javascript -->
<!-- Affiche toujours l'image de fond en optimisé quelque soit les tailles d'écrans (du smartphone à la 4K) -->
<img id="image4K" class="imagePrechargee" src="/resources/img/livre4k_conforme.jpg">
<!---->
<!---->
<%@ include file="menu.jsp" %>
<div id="presentation" class="container-fluid">
    <s:form class="form-group col-sm-8 offset-sm-2" action="rechercheSimple" method="POST">
        <h1>Envie de lecture?</h1>
        <div id="groupRecherche" class="input-group">
            <input type="search" name="motCles" class="form-control" placeholder="Recherchez un ouvrage, un auteur, un éditeur..." maxlength="100" pattern="[^\x22]+">
            <div class="input-group-append">
                <button type="submit" class="btn btn-secondary btn-lg"><i class="fas fa-search"></i></button>
            </div>
        </div>
        <h4 id="commentairePresentation">Votre bibliothèque vous accueille.</h4>
    </s:form>
</div>
</body>
</html>
