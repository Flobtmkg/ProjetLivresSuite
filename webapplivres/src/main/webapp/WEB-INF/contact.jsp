
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <link type="text/css" href="/resources/mycss/contact.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/myjs/ecran.js"></script>
    <title>Contact</title>
</head>
<body onresize="changeImage()" onload="changeImage()">
<!-- Necessaire au système d'adaptation d'images automatique controlé en javascript -->
<!-- Affiche toujours l'image de fond en optimisé quelque soit les tailles d'écrans (du smartphone à la 4K) -->
<img id="image4K" class="imagePrechargee" src="/resources/img/livre4k_conforme.jpg">
<!--  -->
<!--  -->
<%@ include file="menu.jsp" %>
<div id="divdefond"></div>
</div>
<div id="ModalValidation" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Confirmation!</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: green" class="fas fa-check-circle fa-5x"></i>
            </div>
            <p class="texterreur">Votre message a bien été envoyée et sera étudée par l'administrateur.</p>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<div id="ModalErreurSend" class="cModal">
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
<div class="container-fluid">
    <div class="col-sm-10 offset-sm-1">
        <h3><br>Vous avez une suggestion à nous faire? vous souhaitez nous contacter? écrivez-nous ici.</h3>
    </div>
    <div id="blockinscription" class="card card-default col-sm-10 offset-sm-1">
        <div class="card-body">
            <form class="form-group" action="newEnvoiEmail" method="post">
                <br>
                <div class="row">
                    <div class="col-sm-5">
                        <legend>Editez votre message</legend>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="form-group col-12 col-sm-8 col-lg-6">
                        <label>Prénom*:</label>
                        <div class="input-group" style="margin:0;padding:0">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-user-circle"></i></span>
                            </div>
                            <input type="text" class="form-control input-sm" placeholder="Prenom*" name="prenom" value="${sessionScope.userGuest.prenomUtilisateur}" pattern="{1,50}" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-12 col-sm-8 col-lg-6">
                        <label>Nom*:</label>
                        <div class="input-group" style="margin:0;padding:0">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-user-circle"></i></span>
                            </div>
                            <input type="text" class="form-control input-sm" placeholder="Nom*" name="nom" value="${sessionScope.userGuest.nomUtilisateur}" pattern="{1,50}" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-12 col-sm-8 col-lg-6">
                        <label>E-mail*:</label>
                        <div class="input-group" style="margin:0;padding:0">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-at"></i></span>
                            </div>
                            <input type="text" class="form-control input-sm" placeholder="e-mail*" name="email" value="${sessionScope.userGuest.emailUtilisateur}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <label>Objet*:</label>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-12">
                        <div class="input-group" style="margin:0;padding:0">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-thumbtack"></i></span>
                            </div>
                            <select name="objet" class="form-control input-sm" required>
                                <option>Remarque/Suggestion</option>
                                <option>Prise de contact</option>
                                <option>Autre sujet</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="input-group col-12" style="padding: 0">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-pencil-alt"></i></span>
                    </div>
                    <textarea maxlength="2000" name="message" rows="6" id="textDescription" class="form-control" placeholder="Votre message ici...*" required></textarea>
                </div>
                <br>
                <div class="row">
                    <button id="valider" type="submit" class="btn btn-success">Envoyer <i class="fas fa-paper-plane"></i></button>
                    <p id="infoobligatoire">Les champs marqués d'une astérisque* sont obligatoires</p>
                </div>
                <br>
            </form>
        </div>
    </div>
</div>

</body>
</html>
