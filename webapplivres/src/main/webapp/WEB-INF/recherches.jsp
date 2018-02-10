<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../resources/img/livre_icon.png"/>
    <link type="text/css" href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
    <link type="text/css" href="../resources/mycss/menu.css" rel="stylesheet">
    <link type="text/css" href="../resources/mycss/recherches.css" rel="stylesheet">
    <script type="text/javascript" src="../resources/myjs/ecran.js"></script>
    <title>Recherche d'ouvrages</title>
</head>
<body onresize="changeImage()" onload="changeImage()">
    <!-- Necessaire au système d'adaptation d'images automatique controlé en javascript -->
    <!-- Affiche toujours l'image de fond en optimisé quelque soit les tailles d'écrans (du smartphone à la 4K) -->
    <img id="imageHd" class="imagePrechargee" src="../resources/img/livreHD_conforme.jpg">
    <img id="image4K" class="imagePrechargee" src="../resources/img/livre4k_conforme.jpg">
    <!--  -->
    <!--  -->
<%@ include file="menu.jsp" %>
    <!--  -->
    <!--  -->
    <!--  -->
    <div id="divdefond">
    </div>
    <div id="barreDeRecherche" class="container-fluid">
        <!--  -->
        <!--  Formulaire de recherche-->
        <!--  -->
        <form action="sites" method="post" id="blockdescription3" class="panel offset-sm-0 col-12 form-group">
            <div class="offset-sm-0 col-12">
                <div class="form-group col-sm-10 offset-sm-1 col-12 offset-0 aligneAGauche">
                    <legend class="label1">Recherchez:</legend>
                    <div class=" col-12 aligneAGauche">
                        <div class="col-sm-3 col-12 aligneAGauche">
                            <label class="infossurnoir">Mode de recherche: </label>
                            <div class="input-group col-12 aligneAGauche">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-sliders-h"></i></span>
                                </div>
                                <select name="Mode" class="form-control">
                                    <option>OU</option>
                                    <option>ET</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Titre: </label>
                        <div class="input-group col-12 aligneAGauche">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-quote-left"></i></span>
                            </div>
                            <input name="search" type="text" class="form-control" placeholder="Titre de l'ouvrage..." value="${rechsearch}">
                        </div>
                    </div>
                    <div class="col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Auteur: </label>
                        <div class="input-group col-12 aligneAGauche">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-address-book"></i></span>
                            </div>
                            <input name="search" type="text" class="form-control" placeholder="Auteur de l'ouvrage..." value="${rechsearch}">
                        </div>
                    </div>
                    <div class="col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Editeur: </label>
                        <div class="input-group col-12 aligneAGauche">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-building"></i></span>
                            </div>
                            <input name="search" type="text" class="form-control" placeholder="Editeur de l'ouvrage..." value="${rechsearch}">
                        </div>
                    </div>
                </div>
                <div class="form-group col-sm-10 offset-sm-1 col-12 offset-0 aligneAGauche">
                    <div class="form-group col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Type: </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-crop"></i></span>
                            </div>
                            <select name="cotMin" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Domaine: </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-object-group"></i></span>
                            </div>
                            <select name="cotMin" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Thème: </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-tags"></i></span>
                            </div>
                            <select name="cotMin" class="form-control">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group col-sm-10 offset-sm-1 col-12 offset-0 aligneAGauche">
                    <button id="butonRecherche" type="submit" class="btn btn-success col-12"><i class="fas fa-search"></i> Rechercher</button>
                </div>
            </div>
        </form>
        <!--  -->
        <!--  Formulaire de recherche-->
    <!---->
    <!---->
    <!---->
    <!--Boucle de résultats-->
    <s:forEach items="${listeLivre}" var="chaquelivre">
        <!--Elements d'affichage des livres-->
        <s:out value="${chaquelivre.titreLivre}"/>
    </s:forEach>
</body>
</html>
