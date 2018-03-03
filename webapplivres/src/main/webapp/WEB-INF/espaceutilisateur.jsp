<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../resources/img/livre_icon.png"/>
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
    <!--<link type="text/css" href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
    <!-- bibliothèques pour bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../resources/myjs/ecran.js"></script>
    <!-- système de scrollbar -->
    <link rel="stylesheet" href="../resources/jqueryscrollbar/jquery.mCustomScrollbar.css" />
    <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="../resources/jqueryscrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
    <!-- système de scrollbar -->
    <link type="text/css" href="../resources/mycss/menu.css" rel="stylesheet">
    <link type="text/css" href="../resources/mycss/espaceutilisateur.css" rel="stylesheet">
    <title>Votre espace</title>
</head>
<body onresize="changeImage()" onload="changeImage()">
<!-- Necessaire au système d'adaptation d'images automatique controlé en javascript -->
<!-- Affiche toujours l'image de fond en optimisé quelque soit les tailles d'écrans (du smartphone à la 4K) -->
<img id="imageHd" class="imagePrechargee" src="../resources/img/livreHD_conforme.jpg">
<img id="image4K" class="imagePrechargee" src="../resources/img/livre4k_conforme.jpg">
<!--  -->
<!--  -->
<%@ include file="menu.jsp" %>
<div id="divdefond"></div>
<div id="ModalValidation" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Confirmation!</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: green" class="fas fa-check-circle fa-5x"></i>
            </div>
            <p class="texterreur">Les changements ont bien été effectués</p>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<div id="ModalNotification" class="cModal">
    <div>
        <header>
            <c:if test="${notificationClick.typeNotification!='Bilan de réservation'}">
                <h2 class="infossurblanc">${notificationClick.typeNotification} de la part de ${notificationClick.utilisateurExpediteur.prenom} ${notificationClick.utilisateurExpediteur.nom}</h2>
            </c:if>
            <c:if test="${notificationClick.typeNotification=='Bilan de réservation'}">
                <h2 class="infossurblanc">${notificationClick.typeNotification} de ${notificationClick.topoNotification.nomTopo}</h2>
            </c:if>
        </header>
        <!--  -->  	<c:if test="${notificationClick.typeNotification=='Demande de réservation'}">
        <p class="textvalidation"><br><a target="_blank" href="utilisateur?user=${notificationClick.utilisateurExpediteur.id}">${notificationClick.utilisateurExpediteur.prenom} ${notificationClick.utilisateurExpediteur.nom}</a> souhaiterait vous emprunter votre topo ${notificationClick.topoNotification.nomTopo}.
            <br>${notificationClick.utilisateurExpediteur.prenom} ${notificationClick.utilisateurExpediteur.nom} aurait besoin de cet ouvrage sur la période du
            <!--  -->
            <!-- Test pour récupérer les dates depuis les paramètres -->
            <c:forTokens items = "${notificationClick.parametreNotification}" delims = "&" var = "date">
                <c:if test="${!empty date1}">
                    <c:set value="${date}" var="date2"/>
                </c:if>
                <c:if test="${empty date1}">
                    <c:set value="${date}" var="date1"/>
                </c:if>
            </c:forTokens>
            <!-- Test pour récupérer les dates depuis les paramètres -->
            <!--  -->
                ${date1} au ${date2}.<br>En acceptant cette demande, une notification de confirmation contenant votre adresse e-mail sera transmise à ${notificationClick.utilisateurExpediteur.prenom} ${notificationClick.utilisateurExpediteur.nom} pour qu'il puisse vous contacter et procéder à l'échange.</p>
        <c:if test="${notificationClick.traitementNotification=='true'}">
            <p><br>Vous avez accepté cette demande.</p>
        </c:if>
        <c:if test="${notificationClick.traitementNotification=='false'}">
            <p><br>Vous avez décliné cette demande.</p>
        </c:if>
    </c:if>
        <!--  -->  	<c:if test="${notificationClick.typeNotification=='Bilan de réservation'}">
        <p class="textvalidation"><br>Votre réservation de ${notificationClick.topoNotification.nomTopo} est arrivée à son terme.
            <br>Nous vous invitons à laisser un commentaire sur cette réservation si vous le souhaitez:</p>
    </c:if>
        <!--  -->  	<c:if test="${notificationClick.typeNotification=='Confirmation de réservation'}">
        <p class="textvalidation"><br><a target="_blank" href="utilisateur?user=${notificationClick.utilisateurExpediteur.id}">${notificationClick.utilisateurExpediteur.prenom} ${notificationClick.utilisateurExpediteur.nom}</a> a accepté votre demande de réservation de ${notificationClick.topoNotification.nomTopo}.
            <br>Vous pouvez dès à présent le contacter à l'adresse <a href="mailto:${notificationClick.parametreNotification}">${notificationClick.parametreNotification}</a> pour réaliser l'échange.
            <br>Vous recevrez à la fin de la période de réservation une notification de bilan vous offrant la possibilité de laisser un commentaire.
            </c:if>
            <!--  -->  	<c:if test="${notificationClick.typeNotification=='Annulation de réservation'}">
        <p class="textvalidation"><br><a target="_blank" href="utilisateur?user=${notificationClick.utilisateurExpediteur.id}">${notificationClick.utilisateurExpediteur.prenom} ${notificationClick.utilisateurExpediteur.nom}</a> n'a pas pu accepter votre demande de réservation de ${notificationClick.topoNotification.nomTopo}.
            <br>Proposez une autre période réservation ou contactez un autre utilisateur possédant cette topo.
            </c:if>
            <footer>
                <form action="espaceutilisateur" method="post">
                    <c:if test="${notificationClick.typeNotification=='Bilan de réservation'}">
                    <c:if test="${!empty notificationClick.traitementNotification}">
        <p><br>Vous avez déjà posté un commentaire pour cette réservation.</p>
        </c:if>
        <c:if test="${empty notificationClick.traitementNotification}">
            <div>
                <textarea name="commentaire" rows="3" id="textDescription2" class="form-control" maxlength="1000" placeholder="Commentez ici..." required></textarea>
            </div>
        </c:if>
        </c:if>
        <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        <c:if test="${notificationClick.typeNotification=='Demande de réservation'}">
            <c:if test="${empty notificationClick.traitementNotification}">
                <input name="choix" type="submit" class="btn btn-success btn-sm btnvalider" value="Accepter">
                <input name="choix" type="submit" class="btn btn-success btn-sm btnvalider" value="Décliner">
            </c:if>
            <c:if test="${!empty notificationClick.traitementNotification}">
                <input name="choix" type="submit" class="btn btn-success btn-sm btnvalider" value="Accepter" disabled>
                <input name="choix" type="submit" class="btn btn-success btn-sm btnvalider" value="Décliner" disabled>
            </c:if>
        </c:if>
        <c:if test="${notificationClick.typeNotification=='Bilan de réservation'}">
            <c:if test="${empty notificationClick.traitementNotification}">
                <input type="submit" class="btn btn-success btn-sm btnvalider" value="Valider">
            </c:if>
            <c:if test="${!empty notificationClick.traitementNotification}">
                <input type="submit" class="btn btn-success btn-sm btnvalider" value="Valider" disabled>
            </c:if>
        </c:if>
        </form>
        </footer>
    </div>
</div>
<div id="Modalerreur" class="cModal">
    <div>
        <header>
            <h2 class="infossurblanc">Oups!</h2>
        </header>
        <p class="textvalidation"><br>Une erreur est survenue. Réessayez plus tard.</p>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<div id="ModalValidationAjoutTopo" class="cModal">
    <div>
        <header>
            <h2 class="infossurblanc">Confirmation</h2>
        </header>
        <p class="textvalidation"><br>La topo a bien été ajoutée.</p>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<div id="ModalerreurConfirmation" class="cModal">
    <div>
        <header>
            <h2 class="infossurblanc">Oups!</h2>
        </header>
        <p class="textvalidation"><br>La plage de date proposée est occupée par une autre réservation, la réservation sera donc annulée.</p>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<div id="Modalmodif" class="cModal">
    <div>
        <header>
            <h2 class="infossurblanc">Modifez et validez vos informations</h2>
        </header>
        <div>
            <Form action="espaceutilisateur" method="post">
                <label>Prenom:</label>
                <input type="text" class="form-control" value="${sessionScope.utilisateurencours.prenom}" placeholder="Prenom*" name="prenom" pattern="[a-zA-Z-]{1,50}" required>
                <label>Nom:</label>
                <input type="text" class="form-control" value="${sessionScope.utilisateurencours.nom}" placeholder="Nom*" name="nom" pattern="[a-zA-Z-]{1,50}" required>
                <label>Votre description:</label>
                <div class="input-group col-12" style="padding: 0">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-pencil-alt"></i></span>
                    </div>
                    <textarea maxlength="1000" name="description" rows="2" class="form-control textDescription" placeholder="Ajoutez ici quelques mots pour vous décrire...">${sessionScope.utilisateurencours.description}</textarea>
                </div>
                <label>Date de naissance:</label>
                <input type="text" class="form-control" value="${datefr}" placeholder="${maintenant.theDay}/${maintenant.theMonth}/${maintenant.theYear}" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])(/0[1-9]|/1[012])/[0-9]{4}" name="dateNaissance" required>
                <label>E-mail:</label>
                <input type="text" class="form-control" value="${sessionScope.utilisateurencours.email}" placeholder="e-mail*" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
                <label>Pays:</label>
                <input type="text" class="form-control" value="${sessionScope.utilisateurencours.pays}" placeholder="Pays" name="pays" pattern="[a-zA-Z-]{1,50}">
                <label>Ville:</label>
                <input type="text" class="form-control" value="${sessionScope.utilisateurencours.ville}" placeholder="Ville" name="ville" pattern="[a-zA-Z-]{1,50}">
                <a id="lienMdp" href="#Modalmodifmdp" >Pour modifier votre mot de passe cliquez ici</a>
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
            <h2 class="infossurblanc">Modifez et validez vos informations</h2>
        </header>
        <div>
            <Form action="espaceutilisateur" method="post">
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
<div class="container-fluid">
    <!-- imgPath -->
        <div class="offset-sm-1 cadrephoto" style="background: url(../resources/img/user2.jpg) center no-repeat; background-size: 100% auto; background-color:rgba(0, 0, 0, 0.6)">
            <a href="#ModalModifImage"><img class="photo cadrephoto" style="background: center no-repeat; background-size: 100% auto; background-color:rgba(0, 0, 0, 0)"></a>
        </div>
    <!-- imgPath -->
    <div id="blockTransparent"></div>
    <div id="presentation">
        <h2 class="infossurnoir">${sessionScope.utilisateurencours.prenom} ${sessionScope.utilisateurencours.nom}</h2>
        <c:if test="${age!='Indéterminé'}">
            <h4 class="infossurnoir">${age} ans</h4>
        </c:if>
        <c:if test="${age=='Indéterminé'}">
            <h4 class="infossurnoir">Âge ${age}</h4>
        </c:if>
        <h5 class="infossurnoir">${sessionScope.utilisateurencours.email}</h5>
        <h5 class="infossurnoir">${sessionScope.utilisateurencours.pays}</h5>
        <h5 class="infossurnoir">${sessionScope.utilisateurencours.ville}</h5>
        <a class="btn btn-default btn-sm reglage" title="Paramètres" href="#Modalmodif"><i class="fas fa-cog"></i></a>
        <label class="label1 labelparametre"> Modifier les infos</label>
    </div>
    <form action="espaceutilisateur" method="post" class="card offset-sm-1 col-sm-10 col-12 form-group blockdescription">
        <div class="card-body paneldescription">
            <legend class="label1">Votre description:</legend>
            <div class="input-group col-12">
                <textarea readonly name="description" rows="2" class="form-control textDescription" maxlength="1000" placeholder="Quelques mots pour vous décrire...">${sessionScope.utilisateurencours.description}</textarea>
            </div>
        </div>
    </form>
    <!--  -->
    <!--  -->
    <!--  -->
    <form class="card offset-sm-1 col-sm-10 col-12 form-group blockdescription">
        <div class="card-body paneldescription">
            <legend class="label1">Vos notifications: <c:if test="${sessionScope.nouvellesNotifications>0}"><span class="badge">${sessionScope.nouvellesNotifications}</span></c:if></legend>
            <c:if test="${!empty sessionScope.notificationDelUtilisateur}">
                <div class="col-12 ligneheader">
                    <div class="row contenuheader">
                        <label class="col-6 infossurnoirheader">De la part de</label>
                        <label class="col-6 infossurnoirheader">Sujet</label>
                    </div>
                </div>
                <div class="col-12 mCustomScrollbar table-scroll" data-mcs-theme="light-thin">
                    <c:forEach items="${sessionScope.notificationDelUtilisateur}" var="notification">
                        <div class="row ligne">
                            <c:if test="${empty notification.utilisateurExpediteur.prenom||empty notification.utilisateurExpediteur.nom}">
                                <p  class="col-6 petittextsurnoir">Automatique</p>
                            </c:if>
                            <c:if test="${!empty notification.utilisateurExpediteur.prenom&&!empty notification.utilisateurExpediteur.nom}">
                                <p  class="col-6 petittextsurnoir"><a target="_blank" href="utilisateur?user=${notification.utilisateurExpediteur.id}" >${notification.utilisateurExpediteur.prenom} ${notification.utilisateurExpediteur.nom}</a></p>
                            </c:if>
                            <p  class="col-6 petittextsurnoir"><a href="espaceutilisateur?mess=${notification.idNotification}#ModalNotification" >${notification.typeNotification} </a><c:if test="${empty notification.traitementNotification}"><span class="badge">1</span></c:if></p>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.notificationDelUtilisateur}">
                <h5 class="infossurnoir">Vous n'avez aucune notification pour le moment.</h5>
            </c:if>
        </div>
    </form>
    <!--  -->
    <!--  -->
    <!--  -->
    <form class="card offset-sm-1 col-sm-10 col-12 form-group blockdescription">
        <div class="card-body paneldescription">
            <legend class="label1">Vos Topos:</legend>
            <c:if test="${!empty sessionScope.topoDelUtilisateur}">
                <div class="col-12 ligneheader">
                    <div class="row contenuheader">
                        <label class="col-6 infossurnoirheader">Titre des topos</label>
                        <label class="col-6 infossurnoirheader">Disponibilité</label>
                    </div>
                </div>
                <div class="col-12 mCustomScrollbar table-scroll" data-mcs-theme="light-thin">
                    <c:forEach items="${sessionScope.topoDelUtilisateur}" var="topo">
                        <div class="row ligne">
                            <p  class="col-6 petittextsurnoir"><a target="_blank" href="topo?topo=${topo.idTopo}" >${topo.nomTopo}</a></p>
                            <c:if test="${topo.dispoTopo==true}">
                                <p  class="col-6 petittextsurnoir">Disponible</p>
                            </c:if>
                            <c:if test="${topo.dispoTopo==false}">
                                <p  class="col-6 petittextsurnoir">Non-disponible</p>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.topoDelUtilisateur}">
                <h5 class="infossurnoir">Vous ne possédez pas de topo pour le moment.</h5>
            </c:if>
            <div id="buttonsupp" class="col-12">
                <a class="btn btn-default btn-sm reglage" href="${pageContext.request.contextPath}/ajouttopo"><i class="fas fa-plus"></i></a>
                <label class="label1 labelparametre"> Ajouter un topo</label>
            </div>
        </div>
    </form>
    <!--  -->
    <!--  -->
    <!--  -->
    <form class="card offset-sm-1 col-sm-10 col-12 form-group blockdescription">
        <div class="card-body paneldescription">
            <legend class="label1">Vos réservations:</legend>
            <c:if test="${!empty sessionScope.reservationDelUtilisateur}">
                <div class="col-12 ligneheader">
                    <div class="row contenuheader">
                        <label class="col-3 infossurnoirheader">Titre des topos</label>
                        <label class="col-3 infossurnoirheader">Date de début</label>
                        <label class="col-3 infossurnoirheader">Date de fin</label>
                        <label class="col-3 infossurnoirheader">Propriétaire</label>
                    </div>
                </div>
                <div class="col-12 mCustomScrollbar table-scroll" data-mcs-theme="light-thin">
                    <c:forEach items="${sessionScope.reservationDelUtilisateur}" var="reservation">
                        <div class="row ligne">
                            <p  class="col-3 petittextsurnoir"><a target="_blank" href="topo?topo=${reservation.topoAssocié.idTopo}" >${reservation.topoAssocié.nomTopo}</a></p>
                            <p  class="col-3 petittextsurnoir">${reservation.datedebutReservationFR}</p>
                            <p  class="col-3 petittextsurnoir">${reservation.datefinReservationFR}</p>
                            <p  class="col-3 petittextsurnoir"><a target="_blank" href="utilisateur?user=${reservation.proprietaireAssocié.id}" >${reservation.proprietaireAssocié.prenom} ${reservation.proprietaireAssocié.nom}</a></p>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.reservationDelUtilisateur}">
                <h5 class="infossurnoir">Vous n'avez fait aucune réservation pour le moment.</h5>
            </c:if>
        </div>
    </form>
</div>
</body>
</html>
