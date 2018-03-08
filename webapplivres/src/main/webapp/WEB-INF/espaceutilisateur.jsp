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
<img id="image4K" class="imagePrechargee" src="../resources/img/livre4k_conforme.jpg">
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
<div id="Modalmodif" class="cModal">
    <div>
        <header>
            <h2 class="infossurblanc">Modifez et validez vos informations</h2>
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
            <h2 class="infossurblanc">Modifez et validez vos informations</h2>
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
<div class="container-fluid">
    <div class="blockduHaut">
        <!-- imgPath -->
            <div class="offset-sm-1 cadrephoto2" style="background: url(../resources/img/user.jpg) center no-repeat; background-size: 100% auto; background-color:rgba(0, 0, 0, 0.6)">
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
        </div>
    </div>
    <!--  -->
    <!--  -->
    <!--  -->
    <form class="card offset-sm-1 col-sm-10 col-12 form-group blockdescription">
        <div class="card-body paneldescription">
            <legend class="label1">Vos emprunts:</legend>
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
                <a class="btn btn-secondary btn-sm reglage" href="${pageContext.request.contextPath}/ajouttopo"><i class="fas fa-plus"></i></a>
                <label class="label1 labelparametre"> Ajouter un topo</label>
            </div>
        </div>
    </form>
</div>
</body>
</html>
