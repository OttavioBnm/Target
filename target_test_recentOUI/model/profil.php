<?php
session_start();
include 'function_BD.php';
include 'functions.php';
if (isset($_GET['pseudo'])) {
    $SonProfil = checkIfNotAlreadyInPseudo($_GET['pseudo']);
    foreach ($SonProfil as $infos) {
        $nomUser = $infos['nom'];
        $prenomUser = $infos['prenom'];
        $pseudo = $infos['login'];
        $description = $infos['description'];
        $caracteristiques = $infos['caracteristiques'];
    }
} else {
    gotInfos();
    if(isset($_SESSION["pseudo"])){
    $idUser = $_SESSION['idUser'];
    $nomUser = $_SESSION['nom'];
    $prenomUser = $_SESSION['prenom'];
    $pseudo = $_SESSION['pseudo'];
    $description = $_SESSION['description'];
    $caracteristiques = $_SESSION['caracteristiques'];}
}
if (isset($_FILES['icone'])) {
 if ($_FILES['icone']['error'] > 0) $erreur = "Erreur lors du transfert";
$extensions_valides = array( '.jpg' , '.jpeg' , '.gif' , '.png' );
$extension_upload = strtolower(  substr(  strrchr($_FILES['icone']['name'], '.')  ,1)  );
if ( in_array($extension_upload,$extensions_valides) ) echo "Extension correcte";
$nom = "img/profil/".$pseudo."{$extension_upload}";
$resultat = move_uploaded_file($_FILES['icone']['tmp_name'],$nom);
if ($resultat) echo "Transfert réussi";   
}
?>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://bootswatch.com/cosmo/bootstrap.min.css">
<div class="mainbody container-fluid">
    <div class="row">
        <div class="navbar-wrapper col-lg-9">
            <div class="container-fluid">
                <div class="navbar navbar-default navbar-static-top" role="navigation">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span><span
                                    class="icon-bar"></span><span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="./ORqmj" style="margin-right:-8px; margin-top:-5px;">
                                <img alt="Brand" src="https://lut.im/7trApsDX08/GeilMRp1FIm4f2p7.png" width="30px" height="30px">
                            </a>
                            <a class="navbar-brand" href="./ORqmj">Project*</a>
                            <i class="brand_network"><small><small>diaspora* Network</small></small></i>
                        </div>
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                                <li><a href="./ORqmj">Stream</a></li>
                                <li><a href="#">My Activity</a></li>
                                <li><span class="badge badge-important">2</span><a href="#"><i class="fa fa-bell-o fa-lg" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-envelope-o fa-lg" aria-hidden="true"></i></a></li>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <span class="user-avatar pull-left" style="margin-right:8px; margin-top:-5px;">
                                        <img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" class="img-responsive img-circle" title="John Doe" alt="John Doe" width="30px" height="30px">
                                    </span>
                                    <span class="user-name">
                                        John Doe
                                    </span>
                                    <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <div class="navbar-content">
                                                <div class="row">
                                                    <div class="col-md-5">
                                                        <img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" alt="Alternate Text" class="img-responsive" width="120px" height="120px" />
                                                        <p class="text-center small">
                                                            <a href="./3X6zm">Change Photo</a></p>
                                                    </div>
                                                    <div class="col-md-7">
                                                        <span>John Doe</span>
                                                        <p class="text-muted small">
                                                            example@pods.tld</p>
                                                        <div class="divider">
                                                        </div>
                                                        <a href="./56ExR" class="btn btn-default btn-xs"><i class="fa fa-user-o" aria-hidden="true"></i> Profile</a>
                                                        <a href="#" class="btn btn-default btn-xs"><i class="fa fa-address-card-o" aria-hidden="true"></i> Contacts</a>
                                                        <a href="#" class="btn btn-default btn-xs"><i class="fa fa-cogs" aria-hidden="true"></i> Settings</a>
                                                        <a href="#" class="btn btn-default btn-xs"><i class="fa fa-question-circle-o" aria-hidden="true"></i> Help!</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="navbar-footer">
                                                <div class="navbar-footer-content">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-unlock-alt" aria-hidden="true"></i> Change Passowrd</a>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <a href="#" class="btn btn-default btn-sm pull-right"><i class="fa fa-power-off" aria-hidden="true"></i> Sign Out</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="padding-top:50px;"> </div>
        <div class="col-lg-3 col-md-3 hidden-sm hidden-xs">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="media">
                        <div align="center">
                            <img class="thumbnail img-responsive" src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" width="300px" height="300px">
                        </div>
                        <div class="media-body">
                            <hr>
                            <h3><strong>Description</strong></h3>
                            <p><?php echo $description; ?></p>
                            <hr>
                            <h3><strong>Location</strong></h3>
                            <p>Earth</p>
                            <hr>
                            <h3><strong>Gender</strong></h3>
                            <p>Unknown</p>
                            <hr>
                            <h3><strong>Birthday</strong></h3>
                            <p>January 01 1901</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
