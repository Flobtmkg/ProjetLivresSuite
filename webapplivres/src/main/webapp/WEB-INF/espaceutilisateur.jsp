<%@ page import="webAppProjetLivre.classesTravail.EtatPreReservation" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="./resources/img/livre_icon.png"/>
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
    <!--<link type="text/css" href="./resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
    <!-- bibliothèques pour bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./resources/myjs/ecran.js"></script>
    <!-- système de scrollbar -->
    <link rel="stylesheet" href="./resources/jqueryscrollbar/jquery.mCustomScrollbar.css" />
    <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="./resources/jqueryscrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
    <!-- système de scrollbar -->
    <link type="text/css" href="./resources/mycss/menu.css" rel="stylesheet">
    <link type="text/css" href="./resources/mycss/espaceutilisateur.css" rel="stylesheet">
    <title>Votre espace</title>
</head>
<body onresize="changeImage()" onload="changeImage()">
<!-- Necessaire au système d'adaptation d'images automatique controlé en javascript -->
<!-- Affiche toujours l'image de fond en optimisé quelque soit les tailles d'écrans (du smartphone à la 4K) -->
<img id="image4K" class="imagePrechargee" src="./resources/img/livre4k_conforme.jpg">
<!--  -->
<!--  -->
<%@ include file="menu.jsp" %>
<div id="divdefond"></div>

<!---->
<div id="ModalValidation" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Confirmation!</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: green" class="fas fa-check-circle fa-5x"></i>
            </div>
            <p class="texterreur">Les changements ont bien été effectués.</p>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<!---->
<div id="ModalAnnule" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Confirmation!</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: green" class="fas fa-check-circle fa-5x"></i>
            </div>
            <p class="texterreur">La reservation a été annulée.</p>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<div id="Modalerreur" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Oups!</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: darkred" class="fas fa-exclamation-triangle fa-5x"></i>
            </div>
                <p class="texterreur">Une erreur est survenue. Réessayez plus tard.</p>
        </div>
        <footer>
                <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<!---->
<div id="ModalConfirmationAnnulation" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Confirmation requise</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: darkblue" class="fas fa-question-circle fa-5x"></i>
            </div>
            <p class="texterreur">Confirmez vous l'annulation de la réservation ?</p>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
            <a href="AnnulerReservation?resid=${param.resid}" class="btn btn-primary btn-sm modalMargin"><i class="fas fa-times"></i> Confirmer</a>
        </footer>
    </div>
</div>
<!---->
<!---->
<div id="ModalConfirmationOptionRappel" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Confirmation requise</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: darkblue" class="fas fa-question-circle fa-5x"></i>
            </div>
            <c:if test="${sessionScope.userGuest.optionRappel==true}">
                <p class="texterreur">Confirmez vous vouloir desactiver l'option de rappel ?</p>
            </c:if>
            <c:if test="${sessionScope.userGuest.optionRappel==false}">
                <p class="texterreur">Confirmez vous vouloir activer l'option de rappel ?</p>
            </c:if>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
            <c:if test="${sessionScope.userGuest.optionRappel==true}">
                <a href="defOptionRappel?rappel=false" class="btn btn-primary btn-sm modalMargin"><i class="fas fa-times"></i> Confirmer</a>
            </c:if>
            <c:if test="${sessionScope.userGuest.optionRappel==false}">
                <a href="defOptionRappel?rappel=true" class="btn btn-primary btn-sm modalMargin"><i class="fas fa-times"></i> Confirmer</a>
            </c:if>
        </footer>
    </div>
</div>
<!---->
<div id="Modalmodif" class="cModal">
    <div>
        <header>
            <h2 class="infossurblanc2">Modifez et validez vos informations</h2>
        </header>
        <div>
            <Form action="modifInfosGenerales" method="post">
                <label>Prenom:</label>
                <input type="text" class="form-control" value="${sessionScope.userGuest.prenomUtilisateur}" placeholder="Prenom*" name="prenom" pattern="[a-zA-Z-]{1,50}" required>
                <label>Nom:</label>
                <input type="text" class="form-control" value="${sessionScope.userGuest.nomUtilisateur}" placeholder="Nom*" name="nom" pattern="[a-zA-Z-]{1,50}" required>
                <label>Date de naissance:</label>
                <input type="text" class="form-control" value="${datefr}" placeholder="${maintenant.getDayOfMonth}/${maintenant.getMonth}/${maintenant.getYear}" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])(/0[1-9]|/1[012])/[0-9]{4}" name="dateNaissance" required>
                <label>E-mail:</label>
                <input type="text" class="form-control" value="${sessionScope.userGuest.emailUtilisateur}" placeholder="e-mail*" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
                <div id="lienMdp" >
                    <a href="#Modalmodifmdp" >Pour modifier votre mot de passe cliquez ici</a>
                </div>
                <footer>
                    <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Annuler</a>
                    <input type="submit" class="btn btn-success btn-sm btnvalider" value="Valider">
                </footer>
            </Form>
        </div>
    </div>
</div>
<div id="Modalmodifmdp" class="cModal">
    <div>
        <header>
            <h2 class="infossurblanc2">Modifez et validez vos informations</h2>
        </header>
        <div>
            <Form action="modifInfosSupp" method="post">
                <label>Mot de passe actuel:</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-unlock-alt"></i></span>
                    </div>
                    <input type="password" class="form-control" placeholder="mot de passe actuel*" name="motDePasseA"  pattern="[a-zA-Z0-9._%+-]{7,20}" required>
                </div>
                <label>Nouveau mot de passe: (de 7 à 20 caractères)</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-unlock-alt"></i></span>
                    </div>
                    <input type="password" class="form-control" placeholder="nouveau mot de passe*" name="motDePasseN"  pattern="[a-zA-Z0-9._%+-]{7,20}" required>
                </div>
                <label>Confirmez le nouveau mot de passe:</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-unlock-alt"></i></span>
                    </div>
                    <input type="password" class="form-control" placeholder="nouveau mot de passe*" name="motDePasseN2"  pattern="[a-zA-Z0-9._%+-]{7,20}" required>
                </div>
                <footer>
                    <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Annuler</a>
                    <input type="submit" class="btn btn-success btn-sm btnvalider" value="Valider">
                </footer>
            </Form>
        </div>
    </div>
</div>
<!--->
<!--->
<div id="ModalInfos" class="cModal">
    <div>
        <c:forEach items="${reservationUtilisateur}" var="reservation">
            <c:if test="${reservation.pretReservation.idPret==id}">
                <header>
                    <h2 class="infossurblanc2">Consultation du prêt n°${id}</h2>
                </header>
                <div>
                    <c:if test="${fn:contains(reservationUtilisateurRetard,reservation)}">
                        <p class="paragMargin badText"><i class="fas fa-exclamation-triangle"></i> Cet emprunt est terminé et doit être rendu au plus vite.</p>
                    </c:if>
                    <form>
                        <div>
                            <Legend>Ouvrage:</Legend>
                        </div>
                        <div>
                            <p class="paragMargin">Titre: <a target="_blank" href="redirectionLivre?id=${reservation.livreReservation.idLivre}">${reservation.livreReservation.titreLivre}</a></p>
                        </div>
                        <div>
                            <p class="paragMargin">Auteur: ${reservation.livreReservation.auteurLivre}</p>
                        </div>
                        <div>
                            <Legend>Informations de pret:</Legend>
                        </div>
                        <div>
                            <p class="paragMargin">Date de debut du Prêt: </p>
                            <input type="text" class="form-control inputMargin" readonly="true" value="${reservation.pretReservation.dateDebutPret}">
                        </div>
                        <c:if test="${fn:contains(reservationUtilisateurRetard,reservation)}">
                            <div>
                                <p class="paragMargin">Date de fin du Prêt: </p>
                                <input type="text" class="form-control inputMargin badRow" readonly="true" value="${reservation.pretReservation.dateFinPret}">
                            </div>
                        </c:if>
                        <c:if test="${!fn:contains(reservationUtilisateurRetard,reservation)}">
                            <div>
                                <p class="paragMargin">Date de fin du Prêt: </p>
                                <input type="text" class="form-control inputMargin" readonly="true" value="${reservation.pretReservation.dateFinPret}">
                            </div>
                        </c:if>
                        <div>
                            <p class="paragMargin">Prolongement du pret:</p>
                        </div>
                        <div>
                            <c:if test="${reservation.pretReservation.prolongePret==true}">
                                <input type="text" class="form-control inputMargin" readonly="true" value="Prolongé">
                            </c:if>
                            <c:if test="${reservation.pretReservation.prolongePret!=true}">
                                <input type="text" class="form-control inputMargin" readonly="true" value="non-prolongé">
                            </c:if>
                        </div>
                        <div>
                            <p class="paragMargin">Etat du pret:</p>
                        </div>
                        <div>
                            <c:if test="${reservation.pretReservation.renduPret==true}">
                                <input type="text" class="form-control inputMargin" readonly="true" value="Rendu">
                            </c:if>
                            <c:if test="${reservation.pretReservation.renduPret!=true}">
                                <c:if test="${fn:contains(reservationUtilisateurRetard,reservation)}">
                                    <input type="text" class="form-control inputMargin badRow" readonly="true" value="Non-rendu">
                                </c:if>
                                <c:if test="${!fn:contains(reservationUtilisateurRetard,reservation)}">
                                    <input type="text" class="form-control inputMargin" readonly="true" value="Non-rendu">
                                </c:if>
                            </c:if>
                        </div>
                        <div>
                            <p class="paragMargin">Cote de l'exemplaire:</p>
                            <input type="text" class="form-control inputMargin" readonly="true" value=" ${reservation.exemplaireReservation.coteExemplaire}">
                        </div>
                        <div>
                            <p class="paragMargin">Remarques liées à l'exemplaire: </p>
                            <textarea readonly rows="3" id="textDescription2" class="form-control inputMargin" maxlength="1000" placeholder="...">${reservation.exemplaireReservation.remarqueExemplaire}</textarea>
                        </div>
                        <footer>
                            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-check"></i> OK</a>
                        </footer>
                    </form>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
<!--->
<!--->
<!---->
<div id="ModalActionSupp" class="cModal">
    <div>
        <header>
            <h2 class="infossurblanc2">Gérez vos actions et validez</h2>
        </header>
        <div>
            <Form action="ProlngerPret" method="post">
                <legend>Prolongement des prêts:</legend>
                   <!-- Bug #4 problème reservation post fin de prêt -->
                <c:if test="${!empty reservationUtilisateurProlongeables}">
                    <div class="col-12 ligneheader">
                        <div class="row contenuheader">
                            <label class="col-6 infossurblancheader">Prolonger le Prêt?</label>
                            <label class="col-6 infossurblancheader">Prêt</label>
                        </div>
                    </div>
                    <div class="col-12 mCustomScrollbar table-scroll" data-mcs-theme="light-thin">
                    <c:forEach items="${reservationUtilisateurProlongeables}" var="reservation">
                        <div class="row ligne">
                            <div class="form-check col-6">
                                <input type="checkbox" name="check${reservation.pretReservation.idPret}" class="form-check-input">
                                <p class="form-check-label">Prolonger</p>
                            </div>
                            <p class="col-6 petittextsurblanc">ID n°${reservation.pretReservation.idPret}</p>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty reservationUtilisateurProlongeables}">
                    <div>
                        <p>Vous n'avez aucun prêt prolongeable</p>
                    </div>
                </c:if>
                    </div>
                <!-- Bug #4 problème reservation post fin de prêt -->
                <footer>
                    <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Annuler</a>
                    <c:if test="${!empty reservationUtilisateurProlongeables}">
                        <input type="submit" class="btn btn-success btn-sm btnvalider" value="Valider">
                    </c:if>
                </footer>
            </Form>
        </div>
    </div>
</div>
<!--->
<!--->
<div class="container-fluid">
    <div class="blockduHaut">
        <!-- imgPath -->
            <div class="offset-sm-1 cadrephoto2" style="background: url(/resources/img/user.jpg) center no-repeat; background-size: 100% auto; background-color:rgba(0, 0, 0, 0.6)">
                <img class="photo cadrephoto" style="background: center no-repeat; background-size: 100% auto; background-color:rgba(0, 0, 0, 0)">
            </div>
        <!-- imgPath -->
        <div id="blockTransparent"></div>
        <div id="presentation">
            <h2 class="infossurnoir">${sessionScope.userGuest.prenomUtilisateur} ${sessionScope.userGuest.nomUtilisateur}</h2>
            <h4 class="infossurnoir">${age} ans</h4>
            <h5 class="infossurnoir">${sessionScope.userGuest.emailUtilisateur}</h5>
            <a class="btn btn-secondary btn-sm reglage" title="Paramètres" href="#Modalmodif"><i class="fas fa-cog"></i></a>
            <label class="label1 labelparametre"> Modifier les infos</label>
            <c:if test="${sessionScope.userGuest.optionRappel==true}">
                <div class="label1 custom-control custom-checkbox my-1 mr-sm-2">
                    <input type="checkbox" class="custom-control-input" id="customControlInline" checked>
                    <a href="#ModalConfirmationOptionRappel" class="custom-control-label linkBlanc" style="margin:0" for="customControlInline"> Rappel d'expiration des emprunts à 5 jours</a>
                </div>
            </c:if>
            <c:if test="${sessionScope.userGuest.optionRappel==false}">
                <div class="label1 custom-control custom-checkbox my-1 mr-sm-2">
                    <input type="checkbox" class="custom-control-input" id="customControlInline2">
                    <a href="#ModalConfirmationOptionRappel" class="custom-control-label linkBlanc" style="margin:0" for="customControlInline2"> Rappel d'expiration des emprunts à 5 jours</a>
                </div>
            </c:if>
        </div>
    </div>
    <!--  -->
    <!--  -->
    <!--  -->
    <form class="card offset-sm-1 col-sm-10 col-12 form-group blockdescription">
        <div class="card-body paneldescription">
            <legend class="label1">Vos emprunts:</legend>
            <c:if test="${!empty reservationUtilisateur}">
                <div class="col-12 ligneheader">
                    <div class="row contenuheader">
                        <label class="col-3 infossurnoirheader txtaucentre">Prêt</label>
                        <label class="col-3 infossurnoirheader txtaucentre">Titre du livre</label>
                        <label class="col-3 infossurnoirheader txtaucentre">Début du prêt</label>
                        <label class="col-3 infossurnoirheader txtaucentre">Fin du prêt</label>
                    </div>
                </div>
                <div class="col-12 mCustomScrollbar table-scroll" data-mcs-theme="light-thin">
                    <c:forEach items="${reservationUtilisateur}" var="reservation">
                        <!---->
                        <!-- On test si la réservation est en retard et on affiche en rouge -->
                        <c:if test="${fn:contains(reservationUtilisateurRetard,reservation)}">
                            <div class="row ligne badRow2">
                                <p class="col-3 petittextsurnoir badText txtaucentre"><i class="fas fa-exclamation-triangle"></i> <a class="badText" href="redirectionEspaceUtilisateur?id=${reservation.pretReservation.idPret}#ModalInfos">ID n°${reservation.pretReservation.idPret}</a></p>
                                <p class="col-3 petittextsurnoir txtaucentre">${reservation.livreReservation.titreLivre}</p>
                                <p class="col-3 petittextsurnoir txtaucentre">${reservation.pretReservation.dateDebutPret}</p>
                                <p class="col-3 petittextsurnoir txtaucentre">${reservation.pretReservation.dateFinPret}</p>
                            </div>
                        </c:if>
                        <!-- On test si la réservation n'est pas en retard -->
                        <!---->
                        <c:if test="${!fn:contains(reservationUtilisateurRetard,reservation)}">
                            <div class="row ligne">
                                <p class="col-3 petittextsurnoir txtaucentre"><a class="linkBlanc" href="redirectionEspaceUtilisateur?id=${reservation.pretReservation.idPret}#ModalInfos">ID n°${reservation.pretReservation.idPret}</a></p>
                                <p class="col-3 petittextsurnoir txtaucentre">${reservation.livreReservation.titreLivre}</p>
                                <p class="col-3 petittextsurnoir txtaucentre">${reservation.pretReservation.dateDebutPret}</p>
                                <p class="col-3 petittextsurnoir txtaucentre">${reservation.pretReservation.dateFinPret}</p>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty reservationUtilisateur}">
                <h6 class="infossurnoir"><br>Vous n'avez pas fait d'emprunts pour le moment.</h6>
            </c:if>
            <div id="buttonsupp" class="col-12">
                <a class="btn btn-secondary btn-sm reglage" href="#ModalActionSupp"><i class="fas fa-plus"></i></a>
                <label class="label1 labelparametre"> Prolonger vos prêts</label>
            </div>
        </div>
    </form>
    <!-- -->
    <!-- PreReservations -->
    <!-- -->
    <!-- Def de l'enum-->
    <c:set var="ANNULE" value="<%=EtatPreReservation.ANNULE%>"/>
    <c:set var="ATTENTE" value="<%=EtatPreReservation.ATTENTE%>"/>
    <c:set var="EFFECTIF" value="<%=EtatPreReservation.EFFECTIF%>"/>
    <c:set var="AFFECTIVITEDEP" value="<%=EtatPreReservation.EFFECTIVITE_DEPASSE%>"/>
    <c:set var="TERMINE" value="<%=EtatPreReservation.TERMINE%>"/>
    <!-- -->
    <form class="card offset-sm-1 col-sm-10 col-12 form-group blockdescription">
        <div class="card-body paneldescription">
            <legend class="label1">Vos reservations:</legend>
            <c:if test="${!empty reservationsExt}">
                <div class="col-12 ligneheader">
                    <div class="row contenuheader">
                        <label class="col-1 infossurnoirheader txtaucentre">Reservation</label>
                        <label class="col-2 infossurnoirheader txtaucentre">Ouvrage</label>
                        <label class="col-2 infossurnoirheader txtaucentre">Date de reservation</label>
                        <label class="col-2 infossurnoirheader txtaucentre">Prochain retour</label>
                        <label class="col-2 infossurnoirheader txtaucentre">Etat de la reservation</label>
                        <label class="col-2 infossurnoirheader txtaucentre">Position dans la liste d'attente</label>
                        <label class="col-1 infossurnoirheader txtaucentre">Annulation</label>
                    </div>
                </div>
                <div class="col-12 mCustomScrollbar table-scroll" data-mcs-theme="light-thin">
                    <c:forEach items="${reservationsExt}" var="prereservation">
                        <!---->
                        <!-- On test si la préréservation est dépassée -->
                        <c:if test="${prereservation.state == AFFECTIVITEDEP}">
                            <div class="row ligne badRow2">
                                <p class="col-1 desableText txtaucentre"><i class="fas fa-exclamation-triangle"></i> ID n°${prereservation.reservation.idReservation}</p>
                                <p class="col-2 desableText txtaucentre"><a target="_blank" class="linkBlanc" href="redirectionLivre?id=${prereservation.livrereserve.idLivre}">${prereservation.livrereserve.titreLivre}</a></p>
                                <p class="col-2 desableText txtaucentre">${prereservation.dateResFR}</p>
                                <p class="col-2 desableText txtaucentre">/</p>
                                <p class="col-2 desableText txtaucentre">${prereservation.stateStr}</p>
                                <p class="col-2 desableText txtaucentre">/</p>
                                <div style="margin-left: auto; margin-right: auto">
                                    <!-- <a class="btn btn-secondary btn-sm" href="?resid=${prereservation.reservation.idReservation}#ModalConfirmationAnnulation"><i class="fas fa-times-circle"></i></a> -->
                                    <button class="btn btn-secondary btn-sm" disabled="true"><i class="fas fa-times"></i></button>
                                </div>
                            </div>
                        </c:if>
                        <!-- On test si la préréservation est en attente -->
                        <!---->
                        <c:if test="${prereservation.state == ATTENTE}">
                            <div class="row ligne">
                                <p class="col-1 petittextsurnoir txtaucentre">ID n°${prereservation.reservation.idReservation}</p>
                                <p class="col-2 petittextsurnoir txtaucentre"><a target="_blank" class="linkBlanc" href="redirectionLivre?id=${prereservation.livrereserve.idLivre}">${prereservation.livrereserve.titreLivre}</a></p>
                                <p class="col-2 petittextsurnoir txtaucentre">${prereservation.dateResFR}</p>
                                <p class="col-2 petittextsurnoir txtaucentre">${prereservation.dateProchainRetourPrevu}</p>
                                <p class="col-2 petittextsurnoir txtaucentre">${prereservation.stateStr}</p>
                                <p class="col-2 petittextsurnoir txtaucentre">${prereservation.positionListAttente}</p>
                                <div style="margin-left: auto; margin-right: auto">
                                    <a class="btn btn-secondary btn-sm" href="?resid=${prereservation.reservation.idReservation}#ModalConfirmationAnnulation"><i class="fas fa-times"></i></a>
                                </div>
                            </div>
                        </c:if>
                        <!-- On test si la préréservation est effective -->
                        <!---->
                        <c:if test="${prereservation.state == EFFECTIF}">
                            <div class="row ligne">
                                <p class="col-1 petittextsurnoir txtaucentre">ID n°${prereservation.reservation.idReservation}</p>
                                <p class="col-2 petittextsurnoir txtaucentre"><a target="_blank" class="linkBlanc" href="redirectionLivre?id=${prereservation.livrereserve.idLivre}">${prereservation.livrereserve.titreLivre}</a></p>
                                <p class="col-2 petittextsurnoir txtaucentre">${prereservation.dateResFR}</p>
                                <p class="col-2 petittextsurnoir txtaucentre">/</p>
                                <p class="col-2 petittextsurnoir txtaucentre">${prereservation.stateStr}</p>
                                <p class="col-2 petittextsurnoir txtaucentre">${prereservation.positionListAttente}</p>
                                <div style="margin-left: auto; margin-right: auto">
                                    <a class="btn btn-secondary btn-sm" href="?resid=${prereservation.reservation.idReservation}#ModalConfirmationAnnulation"><i class="fas fa-times"></i></a>
                                </div>
                            </div>
                        </c:if>
                        <!---->
                        <!-- On test si la préréservation est annulée ou termminée -->
                        <c:if test="${prereservation.state == ANNULE || prereservation.state == TERMINE}">
                            <div class="row ligne badRow2">
                                <p class="col-1 desableText txtaucentre">ID n°${prereservation.reservation.idReservation}</p>
                                <p class="col-2 desableText txtaucentre"><a target="_blank" class="linkBlanc" href="redirectionLivre?id=${prereservation.livrereserve.idLivre}">${prereservation.livrereserve.titreLivre}</a></p>
                                <p class="col-2 desableText txtaucentre">${prereservation.dateResFR}</p>
                                <p class="col-2 desableText txtaucentre">/</p>
                                <p class="col-2 desableText txtaucentre">${prereservation.stateStr}</p>
                                <p class="col-2 desableText txtaucentre">/</p>
                                <div style="margin-left: auto; margin-right: auto">
                                    <!-- <a class="btn btn-secondary btn-sm" href="?resid=${prereservation.reservation.idReservation}#ModalConfirmationAnnulation"><i class="fas fa-times-circle"></i></a> -->
                                    <button class="btn btn-secondary btn-sm" disabled="true"><i class="fas fa-times"></i></button>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty reservationsExt}">
                <h6 class="infossurnoir"><br>Vous n'avez pas fait de demandes de reservation pour le moment.</h6>
            </c:if>
        </div>
    </form>
</div>
</body>
</html>
