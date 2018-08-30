<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link type="text/css" href="/resources/mycss/livre.css" rel="stylesheet">
        <script type="text/javascript" src="/resources/myjs/ecran.js"></script>
        <title>Descriptif du livre ${livreAAfficher.titreLivre} de ${livreAAfficher.auteurLivre}</title>
    </head>
<body onresize="changeImage()" onload="changeImage()">
<!-- Necessaire au système d'adaptation d'images automatique controlé en javascript -->
<!-- Affiche toujours l'image de fond en optimisé quelque soit les tailles d'écrans (du smartphone à la 4K) -->
<img id="image4K" class="imagePrechargee" src="/resources/img/livre4k_conforme.jpg">
<!--  -->
<!--  -->
<%@ include file="menu.jsp" %>
<div id="divdefond"></div>
<!-- Espace de definition des fenêtres modales -->
<div id="ModalValidation" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Confirmation!</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: green" class="fas fa-check-circle fa-5x"></i>
            </div>
            <p class="texterreur">Votre réservation a bien été prise en compte.</p>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<!-- Espace de definition des blocks -->
<div class="container-fluid">
    <div id="cadrephoto" class="offset-sm-1 col-9 col-sm-5 col-lg-3 col-xl-2" style="background: url(/resources/img/livre8.png) center no-repeat; background-size: 100% auto;"></div>
    <div id="blockTransparent"></div>
    <div id="presentation" class="offset-0 offset-sm-1 offset-lg-0 col-lg-6 col-sm-10 col-12">
        <legend class="label2">Livre ID ${livreAAfficher.idLivre}</legend>
        <h2 class="infossurnoir">${livreAAfficher.titreLivre}</h2>
        <h2 class="infossurnoir">${livreAAfficher.auteurLivre}</h2>
        <h5 class="infossurnoir">Editeur: ${livreAAfficher.editeurLivre}</h5>
        <h5 class="infossurnoir">Date de parution: ${livreAAfficher.datepublicationLivre}</h5>
        <h6 class="infossurnoir">Type: <c:forEach items="${informationDesindexee}" var="letype" begin="0" end="0">${letype}</c:forEach></h6>
        <h6 class="infossurnoir">Domaine: <c:forEach items="${informationDesindexee}" var="ledomaine" begin="1" end="1">${ledomaine}</c:forEach></h6>
        <h6 class="infossurnoir">Thèmes: <c:forEach items="${informationDesindexee}" var="letheme" begin="2">#${letheme} </c:forEach></h6>
        <form class="card col-lg-12 col-12 blockdescription2">
            <label class="infossurnoirheader">Exemplaires présents en bibliothèque :</label>
            <div id="secteurscroll">
                <c:if test="${!empty exemplairesDispo}">
                    <c:forEach items="${exemplairesDispo}" var="myexemplaire">
                        <p id="aafficherenligne" class="textgrise">Exemplaire ${myexemplaire.coteExemplaire}. <c:if test="${!empty myexemplaire.remarqueExemplaire}">Remarques: ${myexemplaire.remarqueExemplaire}</c:if></p><br>
                    </c:forEach>
                </c:if>
                <c:if test="${empty exemplairesDispo}">
                    <p id="aafficherenligne" class="textgrise">Aucun exemplaire de ce livre n'est disponible.</p>
                    <c:if test="${!empty premiereDateFinPret}">
                        <p id="aafficherenligne" class="textgrise">Date la plus proche de retour d'un exemplaire : ${premiereDateFinPret}.</p>
                    </c:if>
                    <c:if test="${empty premiereDateFinPret}">
                        <p id="aafficherenligne" class="textgrise">Date la plus proche de retour d'un exemplaire : inconnue.</p>
                    </c:if>

                </c:if>
            </div>
        </form>

        <form class="card col-lg-12 col-12 blockdescription2">
            <label class="infossurnoirheader">Réservations :</label>
            <div>
                <p id="aafficherenligne" class="textgrise">La liste d'attente de cet ouvrage est de : ${nbrAttente + nbrEffectif} / ${nbrEx * 2} réservations.</p>
            </div>
        </form>
        <br>
    </div>
<!--  -->
<!--  -->
<c:if test="${sessionScope.userGuest.idUtilisateur!=0 && !empty sessionScope.userGuest.idUtilisateur && livreprete==false && livreReserve==false && attenteComplete==false}">
    <form action="AjouterReservation" method="post" class="col-12 card" style="padding:0; background-color: unset; border: unset">
        <div class="offset-sm-1 col-sm-10 col-lg-3 col-xl-2 col-12" style="padding:0;">
            <!--<a class="liensBody" href="">Reserver ce livre</a>-->
            <input type="hidden" name="idLivre" value="${livreAAfficher.idLivre}">
            <button type="submit" class="btn btnCustom btnGris col-12"><i class="far fa-bookmark"></i> Reserver ce livre</button>
        </div>
    </form>
</c:if>
<c:if test="${sessionScope.userGuest.idUtilisateur!=0 && !empty sessionScope.userGuest.idUtilisateur && (livreprete==true || livreReserve==true || attenteComplete==true)}">
    <form class="card offset-sm-1 col-sm-10 col-12 form-group blockdescription3">
    <c:if test="${sessionScope.userGuest.idUtilisateur!=0 && !empty sessionScope.userGuest.idUtilisateur && livreprete==true}">
            <p id="aafficherenligne" class="nonreservation">Reservation impossible. Vous avez déjà un prêt en cours pour cet ouvrage.</p>
    </c:if>
    <c:if test="${sessionScope.userGuest.idUtilisateur!=0 && !empty sessionScope.userGuest.idUtilisateur && livreReserve==true}">
            <p id="aafficherenligne" class="nonreservation">Reservation impossible. Vous avez déjà une réservation en cours pour cet ouvrage.</p>
    </c:if>
    <c:if test="${sessionScope.userGuest.idUtilisateur!=0 && !empty sessionScope.userGuest.idUtilisateur && attenteComplete==true}">
            <p id="aafficherenligne" class="nonreservation">Reservation impossible. La liste d'attente et pleine.</p>
    </c:if>
    </form>
</c:if>
<!--  -->
<form class="card offset-sm-1 col-sm-10 col-12 form-group blockdescription">
    <div class="card-body paneldescription">
        <legend class="label1">Description du livre:</legend>
        <div class="input-group col-12">
            <textarea readonly name="description" rows="5" id="textDescription0" class="form-control" maxlength="1000" placeholder="..." disabled>${livreAAfficher.descriptionLivre}</textarea>
        </div>
    </div>
</form>
<!--  -->
<!--  -->
<!--  -->
</div>
</body>
</html>
