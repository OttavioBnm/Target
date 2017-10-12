<?php
session_start();
include 'model/function_BD.php';
include 'model/functions.php';
if (isset($_GET['pseudo'])) {
    $SonProfil = checkIfNotAlreadyInPseudo($_GET['pseudo']);
    foreach ($SonProfil as $infos) {
        $nomUser = $infos['nom'];
        $prenomUser = $infos['prenom'];
        $pseudo = $infos['login'];
        $description = $infos['description'];
    }
} else {
    gotInfos();
    if(isset($_SESSION["pseudo"])){
    $idUser = $_SESSION['idUser'];
    $nomUser = $_SESSION['nom'];
    $prenomUser = $_SESSION['prenom'];
    $pseudo = $_SESSION['pseudo'];
    $description = $_SESSION['description'];
    
    }
}
if (isset($_FILES['upload'])) {
 if ($_FILES['upload']['error'] > 0) $erreur = "Erreur lors du transfert";
$extensions_valides = array( '.jpg' , '.jpeg' , '.gif' , '.png' );
$extension_upload = strtolower(  substr(  strrchr($_FILES['upload']['name'], '.')  ,1)  );
if ( in_array($extension_upload,$extensions_valides) ) echo "Extension correcte";
$nom = "img/profil/".$pseudo."{$extension_upload}";
$resultat = move_uploaded_file($_FILES['upload']['tmp_name'],$nom);
if ($resultat) echo "Transfert réussi";   
}
?>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="../bootstrap-4.0.0-dist/css/bootstrap.min.css">
        <link href="css/index.css" rel="stylesheet">
        <!-- CSS des icônes !-->
        <link href="../font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <nav class="navbar navbar-default navbar-toggleable-md fixed-top navbar-inverse">
                    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <a class="navbar-brand" href="index.php">Target</a>

                    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="index.php">Accueil</a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="profil.php?$_SESSION[\"pseudo\"]">Mon profil <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="model/deconnexion.php">Déconnexion</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled" href="#"></a>
                            </li>
                        </ul>
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="text" placeholder="Rechercher un profil">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Rechercher</button>
                        </form>
                    </div>
                </nav>

                <div class="row col-lg-12">
                    <div class="col-lg-4 col-md-6 hidden-sm hidden-xs">
                        <div class="panel panel-default">
                            <div class="panel-body">    
                                <div class="media">
                                    <div class="media-body">
                                        <form action="editProfil.php" method="POST" enctype="multipart/form-data"> 
                                            <div class="card card-profile">
                                                <div align="center">
                                                    <img class="thumbnail img-responsive profil" src="img/profil/<?php echo $pseudo . "png"  ?>" width="300px" height="300px">
                                                </div>
                                            </div>
                                            <input type="file" name="upload">
                                            <hr id="propriete">
                                            <h3><i class="fa fa-id-card-o" aria-hidden="true"></i><strong><input type="text" name="pseudo"></strong></h3>
                                            <hr>
                                            <h3><i class="fa fa-bookmark" aria-hidden="true"></i><strong> Description</strong></h3>
                                            <p><input type="text" name="description"></p>
                                            <hr>
                                            <h3><i class="fa fa-home" aria-hidden="true"></i><strong> Lieu</strong></h3>
                                            <p><input type="text" name="lieu"></p>
                                            <hr>
                                            <h3><i class="fa fa-venus-mars" aria-hidden="true"></i><strong> Genre</strong></h3>
                                            <p><input type="text" name="genre"></p>
                                            <hr>
                                            <h3><i class="fa fa-calendar" aria-hidden="true"></i><strong> Année de naissance</strong></h3>
                                            <p><input type="date" name="dateDeNaissance"></p>
                                            <hr>
                                            <h3><i class="fa fa-globe" aria-hidden="true"></i><strong> Langues</strong></h3>
                                            <p><input type="text" name="langue"></p>
                                            <hr>
                                            <h3><i class="fa fa-arrows-v" aria-hidden="true"></i><strong> Taille</strong></h3>
                                            <p><input type="text" name="taille"></p>
                                            <hr>
                                            <h3><i class="fa fa-balance-scale" aria-hidden="true"></i><strong> Poids</strong></h3>
                                            <p><input type="text" name="poids"></p>
                                            <hr>
                                            <h3><i class="fa fa-handshake-o" aria-hidden="true"></i><strong> Emploi</strong></h3>
                                            <p><input type="text" name="emploi"></p>
                                            <hr>
                                            <h3><i class="fa fa-cog" aria-hidden="true"></i><input type="submit" name="submit"></h3>
                                            <hr>
                                        <form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8 col-md-6 hidden-sm hidden-xs">
                        <div class="post-content post">
                            <div class="panel panel-default">
                                <div class="panel-body matchs">
                                    <h1>Matchs</h1>
                                    <hr>
                                    <table class="table table-striped">
                                        <tr>
                                            <td>
                                                <div class="pull-left">
                                                    <a href="#">
                                                        <img class="media-object img-circle" src="https://diaspote.org/uploads/images/thumb_large_283df6397c4db3fe0344.png" width="100px" height="100px" style="margin-right:8px; margin-top:-5px;">
                                                    </a>
                                                </div>
                                                <h3><a href="#" style="text-decoration:none;"><strong>✪ SтeғOғғιcιel ✪ ツ</strong></a></h3>
                                            </td>
                                            <td>
                                                <p>Description</p>
                                            </td>
                                            <td>
                                                <a><i class="fa fa-check fa-2x" aria-hidden="true"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="pull-left">
                                                    <a href="#">
                                                        <img class="media-object img-circle" src="https://diaspote.org/uploads/images/thumb_large_283df6397c4db3fe0344.png" width="100px" height="100px" style="margin-right:8px; margin-top:-5px;">
                                                    </a>
                                                </div>
                                                <h3><a href="#" style="text-decoration:none;"><strong>✪ SтeғOғғιcιel ✪ ツ</strong></a></h3>
                                            </td>
                                            <td>
                                                <p>Description</p>
                                            </td>
                                            <td>
                                                <a><i class="fa fa-check fa-2x" aria-hidden="true"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="pull-left">
                                                    <a href="#">
                                                        <img class="media-object img-circle" src="https://diaspote.org/uploads/images/thumb_large_283df6397c4db3fe0344.png" width="100px" height="100px" style="margin-right:8px; margin-top:-5px;">
                                                    </a>
                                                </div>
                                                <h3><a href="#" style="text-decoration:none;"><strong>✪ SтeғOғғιcιel ✪ ツ</strong></a></h3>
                                            </td>
                                            <td>
                                                <p>Description</p>
                                            </td>
                                            <td>
                                                <a><i class="fa fa-check fa-2x" aria-hidden="true"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="pull-left">
                                                    <a href="#">
                                                        <img class="media-object img-circle" src="https://diaspote.org/uploads/images/thumb_large_283df6397c4db3fe0344.png" width="100px" height="100px" style="margin-right:8px; margin-top:-5px;">
                                                    </a>
                                                </div>
                                                <h3><a href="#" style="text-decoration:none;"><strong>✪ SтeғOғғιcιel ✪ ツ</strong></a></h3>
                                            </td>
                                            <td>
                                                <p>Description</p>
                                            </td>
                                            <td>
                                                <a><i class="fa fa-check fa-2x" aria-hidden="true"></i></a>
                                            </td>
                                        </tr>
                                    </table>
                                    <h1>Demande en cours</h1>
                                    <hr>
                                    <table class="table table-striped">
                                        <tr>
                                            <td>
                                                <div class="pull-left">
                                                    <a href="#">
                                                        <img class="media-object img-circle" src="https://diaspote.org/uploads/images/thumb_large_283df6397c4db3fe0344.png" width="100px" height="100px" style="margin-right:8px; margin-top:-5px;">
                                                    </a>
                                                </div>
                                                <h3><a href="#" style="text-decoration:none;"><strong>✪ SтeғOғғιcιel ✪ ツ</strong></a></h3>
                                            </td>
                                            <td>
                                                <p>Description</p>
                                            </td>
                                            <td>
                                                <a><i class="fa fa-ban fa-2x" aria-hidden="true"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="pull-left">
                                                    <a href="#">
                                                        <img class="media-object img-circle" src="https://diaspote.org/uploads/images/thumb_large_283df6397c4db3fe0344.png" width="100px" height="100px" style="margin-right:8px; margin-top:-5px;">
                                                    </a>
                                                </div>
                                                <h3><a href="#" style="text-decoration:none;"><strong>✪ SтeғOғғιcιel ✪ ツ</strong></a></h3>
                                            </td>
                                            <td>
                                                <p>Description</p>
                                            </td>
                                            <td>
                                                <a><i class="fa fa-ban fa-2x" aria-hidden="true"></i></a>
                                            </td>
                                        </tr>
                                    </table>
                                    <!--<div class="post-content"></div> !-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>