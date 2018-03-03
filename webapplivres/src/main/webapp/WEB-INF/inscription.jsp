<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../resources/img/livre_icon.png"/>
    <!--<link type="text/css" href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
    <!-- bibliothèques pour bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
    <link type="text/css" href="../resources/mycss/menu.css" rel="stylesheet">
    <link type="text/css" href="../resources/mycss/inscription.css" rel="stylesheet">
    <script type="text/javascript" src="../resources/myjs/ecran.js"></script>
    <title>Contact</title>
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
</div>
<div id="ModalValidationInscription" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Vous faites désormais parti de nos membres!</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: green" class="fas fa-check-circle fa-5x"></i>
            </div>
            <p class="texterreur">Vous pouvez dès maintenant vous connecter à votre espace membre et découvrir toutes les fonctionnalités du site.</p>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<div id="ModalerreurInscription" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Oups!</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: darkred" class="fas fa-exclamation-triangle fa-5x"></i>
            </div>
            <p class="texterreur">Une erreur est survenue.<br>Verifiez que cette adresse n'est pas déjà inscrite et que la date de naissance est valide.<br>Réessayez plus tard.</p>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<div class="container-fluid">
    <div class="col-sm-10 offset-sm-1">
        <h3><br>Vous souhaitez profiter de tous les services proposés par votre bibliothèque? Inscrivez-vous!</h3>
    </div>
    <div id="blockinscription" class="card card-default col-sm-10 offset-sm-1">
        <div class="card-body">
            <form class="form-group" action="nouvelleInscription.action" method="post">
                <br>
                <div class="row">
                    <div class="col-sm-5">
                        <legend id="titre">Inscription</legend>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 d-none d-sm-block">
                        <label>E-mail*:</label>
                    </div>
                    <div class="col-sm-6 d-none d-sm-block">
                        <label>Mot de passe* (min 7 caractères):</label>
                    </div>
                    <div class="col-sm-6 d-sm-none d-md-none d-lg-none">
                        <label>E-mail* et mot de passe*:</label>
                    </div>
                </div>
                <div class="row form-inline">
                    <div class="col-sm-6">
                        <div class="input-group col-sm-12" style="margin:0;padding:0">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-at"></i></span>
                            </div>
                            <input type="text" class="form-control" placeholder="e-mail*" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="input-group col-sm-12" style="margin:0;padding:0">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-unlock-alt"></i></span>
                            </div>
                            <input type="password" class="form-control" placeholder="mot de passe*" name="motDePasse"  pattern="[a-zA-Z0-9._%+-]{7,20}" required>
                        </div>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6 d-none d-sm-block">
                        <label>Prénom*:</label>
                    </div>
                    <div class="col-sm-6 d-none d-sm-block">
                        <label>Nom*:</label>
                    </div>
                    <div class="col-sm-6 d-sm-none d-md-none d-lg-none">
                        <label>Prénom* et nom*:</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="prénom*" name="prenom" pattern="{1,50}" required>
                    </div>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="nom*" name="nom" pattern="{1,50}" required>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <label>Date de naissance: (format JJ/MM/AAAA)</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <input type="text" id="valeur" class="form-control" placeholder="01/01/2018" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])(/0[1-9]|/1[012])/[0-9]{4}" name="dateNaissance">
                    </div>
                </div>
                <br>
                <div class="">
                    <div class="">
                        <p id="infoobligatoire">Les champs marqués d'une astérisque* sont obligatoires</p>
                    </div>
                    <div class="">
                        <input id="valider" type="submit" class="form-control btn btn-success" value="Valider">
                    </div>
                </div>
                <br>
            </form>
        </div>
    </div>
    <div class="col-sm-10 offset-sm-1">
        <p>Pour nous contacter directement <a href="contact" >contactez nous ici</a></p>
    </div>
</div>

</body>
</html>
