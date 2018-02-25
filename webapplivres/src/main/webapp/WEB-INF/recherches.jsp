<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
        <form action="rechercheComplexe" method="post" id="blockdescription3" class="card offset-sm-0 col-12 form-group">
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
                                <select name="ouOuEt" class="form-control">
                                    <c:if test="${ouOuEt=='ET'}">
                                        <option>OU</option>
                                        <option selected>ET</option>
                                    </c:if>
                                    <c:if test="${ouOuEt!='ET'}">
                                        <option selected>OU</option>
                                        <option>ET</option>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-3 offset-sm-1 col-12 aligneAGauche">
                            <label class="infossurnoir">Type de mots clés: </label>
                            <div class="custom-control custom-checkbox">
                                <div id="blockCheck">
                                    <c:if test="${expressionStricte=='true'}">
                                        <input id="checkboxStrictValue" name="expressionStricte" type="checkbox" class="custom-control-input" value="true" checked>
                                    </c:if>
                                    <c:if test="${expressionStricte!='true'}">
                                        <input id="checkboxStrictValue" name="expressionStricte" type="checkbox" class="custom-control-input" value="true">
                                    </c:if>
                                    <input id="checkboxStrictValue" name="expressionStricte" type="checkbox" class="custom-control-input" value="true">
                                    <label id="labelCheck" class="custom-control-label infossurnoir" for="checkboxStrictValue">Expression exacte</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Titre: </label>
                        <div class="input-group col-12 aligneAGauche">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-quote-left"></i></span>
                            </div>
                            <input name="titre" type="text" class="form-control" placeholder="Titre de l'ouvrage..." value="${titre}" maxlength="100" pattern="[^\x22]+">
                        </div>
                    </div>
                    <div class="col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Auteur: </label>
                        <div class="input-group col-12 aligneAGauche">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-address-book"></i></span>
                            </div>
                            <input name="auteur" type="text" class="form-control" placeholder="Auteur de l'ouvrage..." value="${auteur}" maxlength="100" pattern="[^\x22]+">
                        </div>
                    </div>
                    <div class="col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Editeur: </label>
                        <div class="input-group col-12 aligneAGauche">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-building"></i></span>
                            </div>
                            <input name="editeur" type="text" class="form-control" placeholder="Editeur de l'ouvrage..." value="${editeur}" maxlength="100" pattern="[^\x22]+">
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
                            <select name="leType" class="form-control">
                                    <option>Selectionnez un type</option>
                                    <c:forEach items="${types}" var="chaqueType">
                                        <c:if test="${leType==chaqueType}">
                                            <option selected>${chaqueType}</option>
                                        </c:if>
                                        <c:if test="${leType!=chaqueType}">
                                            <option>${chaqueType}</option>
                                        </c:if>
                                    </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Domaine: </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-object-group"></i></span>
                            </div>
                            <select name="leDomaine" class="form-control">
                                <option>Selectionnez un domaine</option>
                                <c:forEach items="${domaines}" var="chaquedomaine">
                                    <c:if test="${leDomaine==chaquedomaine}">
                                        <option selected>${chaquedomaine}</option>
                                    </c:if>
                                    <c:if test="${leDomaine!=chaquedomaine}">
                                        <option>${chaquedomaine}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-sm-4 col-12 aligneAGauche">
                        <label class="infossurnoir">Thème: </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-tags"></i></span>
                            </div>
                            <select name="leTheme" class="form-control">
                                <option>Selectionnez un thème</option>
                                <c:forEach items="${themes}" var="chaquetheme">
                                    <c:if test="${leTheme==chaquetheme}">
                                        <option selected>${chaquetheme}</option>
                                    </c:if>
                                    <c:if test="${leTheme!=chaquetheme}">
                                        <option>${chaquetheme}</option>
                                    </c:if>
                                </c:forEach>
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
        <c:forEach items="${listeLivre}" var="chaquelivre">
            <!--Elements d'affichage des livres-->
            <form id="blockdescription" class="card offset-sm-1 col-sm-10 col-12 form-group">
                <div id="paneldescription" class="card-body">
                    <legend class="label1">${chaquelivre.titreLivre}<a id="liensDuHaut" target="_blank" href="redirectionLivre?id=${chaquelivre.idLivre}"></a></legend>
                    <!-- imgPath -->
                        <div class="cadrephoto" class="card">
                            <a target="_blank" href="redirectionLivre?id=${chaquelivre.idLivre}"><img class="cadrephoto" src="../resources/img/livre8.png"></a>
                        </div>
                    <!-- imgPath -->
                    <div id="infosDroiteImage" class="col-8">
                        <p class="petittextsurnoir">Auteur: ${chaquelivre.auteurLivre}</p>
                        <p class="petittextsurnoir">Editeur: ${chaquelivre.editeurLivre}</p>
                        <p class="petittextsurnoir">Date de publication: ${chaquelivre.datepublicationLivre}</p>
                        <p class="petittextsurnoir">Indexation: ${chaquelivre.indexationLivre}</p>
                    </div>
                    <div class="input-group col-12">
                        <label class="infossurnoir">Description:</label>
                        <textarea readonly name="description" rows="3" id="textDescription0" class="form-control" maxlength="1000" placeholder="..." disabled>${chaquelivre.descriptionLivre}</textarea>
                    </div>
                </div>
            </form>
        </c:forEach>
    <!---->
    <!---->
    <!---->
    </div>
</body>
</html>
