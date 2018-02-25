<nav id="barre" class="form-inline navbar navbar-light navbar-expand-sm container-fluid">
    <div class="navbar-header">
        <a href="./accueil" ><img class="navbar-btn rotatelogo" src="../resources/img/livre8.png" width="50" height="46"></a>
    </div>
    <div class="offset-1">
        <ul class="nav navbar-nav">
            <li><a href="rechercheSimple?motCles=" class="liensMenu"><i class="fas fa-search-plus fa-lg"></i> Rechercher livres</a></li>
            <li><a href="./contact" class="liensMenu"><i class="far fa-address-card fa-lg"></i> Contact</a></li>
            <li></li>
        </ul>
    </div>
    <div id="connexion" class="navbar-right">
                <div id="blockconnexion" class="form-group navbar-btn">
                    <c:if test="${sessionScope.userGuest.idUtilisateur==0 || empty sessionScope.userGuest.idUtilisateur}">
                        <a href="#ModalConnexion" class="liensMenu"><i class="fas fa-sign-in-alt fa-lg"></i> Inscription/Connexion</a>
                    </c:if>
                    <c:if test="${sessionScope.userGuest.idUtilisateur!=0 && !empty sessionScope.userGuest.idUtilisateur}">
                        <p class="textMenu">Bonjour ${sessionScope.userGuest.prenomUtilisateur}</p>
                        <a href="./espaceutilisateur"><button id="boutonhome" class="btn btn-xs btn-primary"><span class="fas fa-user fa-lg"></span></button></a>
                    </c:if>
                </div>
    </div>
</nav>
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
    <form action="connexionUtilisateur.action" method="post">
        <header>
            <h2 class="modalHeader">Connexion</h2>
        </header>
        <div class="form-group modalMargin">
            <a href="./inscription"><i class="fas fa-edit"></i> Vous n'avez pas de Compte? Inscrivez-vous!</a>
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
