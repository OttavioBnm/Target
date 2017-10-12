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

/*
 * Création de la listbox qui permet de déterminer la taille de l'utilisateur
 */
function CreateListBoxHeight(){
    echo '
        <div class="form-group">
            <label for="taille">Taille :</label>
            <select class="form-control" id="taille" name="taille">';
    for($i = 140; $i <= 210; $i++){
        echo "<option value=\"$i\">$i cm</option>";
    }
    echo '</select>
        </div>';
}

/*
 * Création de la listbox qui permet de déterminer le poids de l'utilisateur
 */
function CreateListBoxWidth(){
    echo '
        <div class="form-group">
            <label for="poids">Poids :</label>
            <select class="form-control" id="poids" name="poids">
            <option value="1"> < 45 kg </option>
            ';
    
    for($i = 45; $i <= 120; $i++){
        echo "<option value=\"$i\">$i kg</option>";
    }
    echo '
        <option value="2"> > 120 kg </option>
        </select>
        </div>';
}

/*
 * Création de la listbox qui permet de déterminer le genre de l'utilisateur
 */
function CreateListBoxGender(){
    echo '
        <div class="form-group">
            <label for="genre">Genre :</label>
            <select class="form-control" id="genre" name="genre">
                <option value="Homme">Homme</option>
                <option value="Femme">Femme</option>
                <option value="Autre">Autre</option>
            </select>
        </div>';
}

/*
 * Création de la listbox qui permet de déterminer l'âge de l'utilisateur
 */
function CreateListBoxAge(){
    echo '
        <div class="form-group">
            <label for="age">Âge :</label>
            <select class="form-control" id="age" name="age">';
    for($i = 18; $i <= 80; $i++){
        echo "<option value=\"$i\">$i ans</option>";
    }
    echo'</select>
        </div>';
}

/*
 * Création de la listbox qui permet de déterminer le lieu de l'utilisateur
 */
function CreateListBoxCities(){
    $ya = getCities();
    echo '<div class="form-group">
            <label for="ville">Villes :</label>
            <select class="form-control" id="ville" name="ville">';
    foreach ($ya as $villes){
        echo "<option value=" . $villes['nomVille'] .">" . $villes['nomVille'] ."</option>";
    }
    echo'</select>
        </div>';
}

/*
 * Création de la listbox qui permet de déterminer la langue de l'utilisateur
 */
function CreateListBoxLanguages(){
    $ya = getLanguages();
    echo '<div class="form-group">
            <label for="langue">Langues :</label>
            <select class="form-control" id="langue" name="langue">';
    foreach ($ya as $languages){
        echo "<option value=" . $languages['nomLangue'] .">" . $languages['nomLangue'] ."</option>";
    }
    echo'</select>
        </div>';
}

function createTextBoxJob(){
    echo "<div class=\"form-group\">
                            <label for=\"statutPro\">Statut professionel</label>
                            <input name=\"statutPro\" type=\"email\" class=\"form-control\" id=\"statutPro\" aria-describedby=\"emailHelp\" placeholder=\"Entrez votre statut professionel\">
                    </div>";
}

