<%@ taglib prefix="s" uri="/struts-tags" %>
<nav id="barre" class="navbar navbar-light navbar-expand-md container-fluid" role="navigation" onmousemove="editPriorite(false)">
    <div class="navbar-header">
        <a href="./accueil" ><img class="navbar-btn rotatelogo" src="./resources/img/livre8.png" width="50" height="46"></a>
    </div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-collapse collapse" id="navbarSupportedContent">
                <ul class="navbar-nav centerMenu">
                    <li class="nav-item mt-4 mt-md-0 border-bottom-sm-1"><a href="rechercheSimple?motCles=" class="liensMenu"><i class="fas fa-search-plus fa-lg"></i> Rechercher livres</a></li>
                    <li class="nav-item mt-3 mt-md-0"><a href="./contact" class="liensMenu"><i class="far fa-address-card fa-lg"></i> Contact</a></li>
                </ul>
            <div id="connexion" class="navbar-right">
                        <div id="blockconnexion" class="form-group navbar-nav mt-4 mt-md-0 align-items-md-center">
                            <c:if test="${sessionScope.userGuest.idUtilisateur==0 || empty sessionScope.userGuest.idUtilisateur}">
                                <a href="#ModalConnexion" class="nav-item liensMenu"><i class="fas fa-sign-in-alt fa-lg"></i> Inscription/Connexion</a>
                            </c:if>
                            <c:if test="${sessionScope.userGuest.idUtilisateur!=0 && !empty sessionScope.userGuest.idUtilisateur}">
                                <p class="textMenu d-none d-md-block">Bonjour ${sessionScope.userGuest.prenomUtilisateur}</p>
                                <a href="redirectionEspaceUtilisateur"><button id="boutonhome" onmousemove="editPriorite(true)" onmouseout="setTimeout(hideMenu,500)" class="btn btn-xs btn-primary"><span class="fas fa-user fa-lg"></span></button></a>
                            </c:if>
                        </div>
            </div>
    </div>
</nav>
<div id="menuConnexion" onmouseout="setTimeout(hideMenu,500);editPriorite(false)">
    <div class="row">
        <div class="col-md-3 offset-md-9 col-12 offset-0">
            <div class="list-group" id="list-tab" role="tablist" onmousemove="editPriorite(true)">
                <a class="list-group-item list-group-item-action elementdelisteMenu liensMenu" href="redirectionEspaceUtilisateur" role="tab"><i class="fas fa-user-circle"></i> Espace utilisateur</a>
                <a class="list-group-item list-group-item-action elementdelisteMenu liensMenu" href="deconnexion" role="tab"><i class="fas fa-power-off"></i> Déconnexion</a>
            </div>
        </div>
    </div>
</div>
<div id="ModalErreur" class="cModal">
    <div>
        <header>
            <h2 class="modalHeader">Oups!</h2>
        </header>
        <div class="contenuErreur">
            <div class="contenuImageErreur"style="float: left; margin-right:15px;box-sizing: content-box">
                <i style="color: darkred" class="fas fa-exclamation-triangle fa-5x"></i>
            </div>
            <p class="texterreur">Votre adresse e-mail ou votre mot de passe n'ont pas été reconnus.<br>Veuillez réessayer.</p>
        </div>
        <footer>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </div>
</div>
<div id="ModalConnexion" class="cModal">
    <div>
    <form action="connexionUtilisateur" method="post">
        <header>
            <h2 class="modalHeader"><i class="fas fa-sign-in-alt"></i> Connexion</h2>
        </header>
        <div class="form-group modalMargin">
            <a href="inscription"><i class="fas fa-edit"></i> Vous n'avez pas de Compte? Inscrivez-vous!</a>
        </div>
        <div class="form-group modalMargin">
            <label for="inputEmail">E-mail</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-at"></i></span>
                </div>
                <input id="inputEmail" type="email" name="idco" class="form-control col-12" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
            </div>
            <label for="inputPassword">Mot de passe</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-unlock-alt"></i></span>
                </div>
                <input id="inputPassword" type="password" name="mdp" class="form-control col-12" required>
            </div>
        </div>
        <footer>
            <button type="submit" class="btn btn-primary btn-sm modalMargin"><i class="fas fa-power-off"></i> Connexion</button>
            <a href="#fermer" class="btn btn-outline-secondary btn-sm modalMargin"><i class="fas fa-times"></i> Fermer</a>
        </footer>
    </form>
    </div>
</div>
