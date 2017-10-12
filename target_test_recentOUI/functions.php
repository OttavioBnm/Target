<?php
/*
 * Fonction de remplissage, toutes les infos dispo dans le $_Session
 */
function gotInfos() {
    if (isset($_SESSION['mail'])) {
        $infoUser = checkIfNotAlreadyInMail($_SESSION['mail']);
        foreach ($infoUser as $value) {
            $_SESSION['idUser'] = $value['idUser'];
            $_SESSION['nom'] = $value['nom'];
            $_SESSION['pseudo'] = $value['login'];
            $_SESSION['prenom'] = $value['prenom'];
            $_SESSION['mail'] = $value['email'];
            $_SESSION['caracteristiques'] = $value['caracteristiques'];
            $_SESSION['description'] = $value['description'];
        }
    }
}
/*
 * Destrcuteur de session avec redirection
 */
function deco(){
    $_SESSION = array();
session_destroy();
unset($_SESSION);
header('Location:index.php');
}
/*fonction pour pas retaper constament la redirection
 */
function backHome(){
    header('Location:index.php');
}
