<?php
DEFINE("DB_HOST","127.0.0.1");
DEFINE("DB_NAME","target");
DEFINE("DB_USER","root");
DEFINE("DB_PASS","");
/*
 * Connection à la base de données
 */
function getConnexion(){
    static $dbb=null;
    if($dbb==null){
        try{
        $connectionString="mysql:host=".DB_HOST.";dbname=".DB_NAME."";
        $dbb=new PDO($connectionString,DB_USER,DB_PASS, array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8'));
        $dbb->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
        }
        catch(PDOException $e){
            die('Erreur : '. $e->getMessage() );
        }
    }
    return $dbb;
}
/*
 * Requête d'inscription
 */
function subscribe($mail,$pass,$pseudo,$nom,$prenom,$description){     
	$connexion=getConnexion();
    $request=$connexion->prepare("INSERT INTO `utilisateur` (`email`, `password`, `login`, `nom`, `prenom`, `description`) VALUES (:mail,:pass,:login,:nom,:prenom,:description);");
    $request->bindParam(':mail',$mail,PDO::PARAM_STR);
    $request->bindParam(':pass',$pass,PDO::PARAM_STR);
    $request->bindParam(':login',$pseudo,PDO::PARAM_STR);
    $request->bindParam(':nom',$nom,PDO::PARAM_STR);
    $request->bindParam(':prenom',$prenom,PDO::PARAM_STR);
    $request->bindParam(':description',$description,PDO::PARAM_STR);
    $request->execute(); 
    
}
/*
 * Vérification que le mail n'est pas déjà présent
 */
function checkIfNotAlreadyInMail($mail){
    $connexion=getConnexion();
    $request=$connexion->prepare("SELECT * FROM `utilisateur` WHERE `email`=:mail");
    $request->bindParam(':mail',$mail,PDO::PARAM_STR);
    $request->execute();
    $resulat=$request->fetchAll(PDO::FETCH_ASSOC);
    return $resulat;
}
/*
 * Vérification que le pseudo n'est pas déjà présent
 */
function checkIfNotAlreadyInPseudo($pseudo){
    $connexion=getConnexion();
    $request=$connexion->prepare("SELECT * FROM `utilisateur` WHERE `login`=:pseudo");
    $request->bindParam(':pseudo',$pseudo,PDO::PARAM_STR);
    $request->execute();
    $resulat=$request->fetchAll(PDO::FETCH_ASSOC);
    return $resulat;
}
/*
 * Recupère tout les utilisateurs
 */
function getUsers(){
    $connexion=getConnexion();
    $request=$connexion->prepare("SELECT * FROM `utilisateur`");
    $request->execute();
    $resulat=$request->fetchAll(PDO::FETCH_ASSOC);
    return $resulat;
}
/*
 * Requete de connection 
 */
function checkIn($email,$password){
    $connexion=getConnexion();
    $request=$connexion->prepare("SELECT * FROM `utilisateur` WHERE `email`=:email AND `password`=:password");
    $request->bindParam(':email',$email,PDO::PARAM_STR);
    $request->bindParam(':password',$password,PDO::PARAM_STR);
    $request->execute();
    $resulat=$request->fetchAll(PDO::FETCH_ASSOC);
    return $resulat;
}
/*Requete de modification (prend la colone de la table pour modifier les paramètre voulue) /!\ PAS DE PROTECTION EN BIND QUE DU CODE PHP NON ACCESSIBLE PAR L'USER
 */
function modif($id,$nomColone,$changement){
    $connexion=getConnexion();
    $request=$connexion->prepare("UPDATE utilisateur SET ".$nomColone."= :changement WHERE `utilisateur`.`idUser` = :id;");
    $request->bindParam(':changement',$changement,PDO::PARAM_STR);
    $request->bindParam(':id',$id,PDO::PARAM_INT);
    $request->execute();
}
/*Suppression du profil
 */
function suppProfil($id){
    $connexion=getConnexion();
    $request=$connexion->prepare("DELETE FROM utilisateur WHERE `idUser`=:id;");
    $request->bindParam(':id',$id,PDO::PARAM_INT);
    $request->execute();
}

function getCities(){
    $connexion=getConnexion();
    $request=$connexion->prepare("SELECT * FROM `villes`");
    $request->execute();
    $resulat=$request->fetchAll(PDO::FETCH_ASSOC);
    return $resulat;
}

function getLanguages(){
    $connexion=getConnexion();
    $request=$connexion->prepare("SELECT * FROM `langues`");
    $request->execute();
    $resulat=$request->fetchAll(PDO::FETCH_ASSOC);
    return $resulat;
}