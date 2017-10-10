<?php
session_start();
include 'model/function_BD.php';
include 'model/functions.php';
if (isset($_SESSION['pseudo'])) {
    header('Location:index.php');
}
gotInfos();
if (isset($_REQUEST["mailInscri"]) && isset($_REQUEST["passwordInscri"]) && isset($_REQUEST["pseudoInscri"])) {
            $mailCheck = checkIfNotAlreadyInMail($_REQUEST["mailInscri"]);
            $pseudoCheck = checkIfNotAlreadyInPseudo($_REQUEST["pseudoInscri"]);
            if (isset($mailCheck[0])) {
                echo 'Mail déjà utiliser';
            } else {
                if (isset($pseudoCheck[0])) {
                    echo 'Pseudo déjà utiliser';
                } else {
                    subscribe($_REQUEST["mailInscri"], sha1($_REQUEST["passwordInscri"]), $_REQUEST["pseudoInscri"], $_REQUEST["nom"], $_REQUEST["prenom"], $_REQUEST["description"]);
                    $_SESSION['pseudo'] = $_REQUEST["pseudoInscri"];
                    $_SESSION['mail'] = $_REQUEST["mailInscri"];
                    header('Location:index.php');
                }
            }
        }
?>
<!DOCTYPE html>
<html lang="fr">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Inscription</title>

    <!-- Bootstrap core CSS -->
    <link href="../bootstrap-4.0.0-dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Css de la page !-->
	<link href="css/index.css" rel="stylesheet">

  </head>

  <body>
	<div class="fond">
    <!-- Navigation -->
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
            <a class="nav-link" href="register.php">Inscription <span class="sr-only">(current)</span></a>
          </li>
		  <li class="nav-item">
            <a class="nav-link" href="login.php">Connexion</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#"></a>
          </li>
        </ul>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

		<form class="leformulaire" action="register.php" method="post">
                    <h1 class="display-3">Inscription</h1>
                    <div class="form-group">
                            <label for="email">Adresse email</label>
                            <input name="mailInscri" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Entrez votre adresse email">
                            <small id="emailHelp" class="form-text text-muted">Nous ne partagerons votre email avec personne d'autres</small>
                    </div>
                    <div class="form-group">
                            <label for="motdepasse">Mot de passe</label>
                            <input name="passwordInscri" type="password" class="form-control" id="exampleInputPassword1" placeholder="Entrez votre mot de passe">
                    </div>
                    <div class="form-group">
                            <label for="pseudo">Pseudo</label>
                            <input name="pseudoInscri" type="text" class="form-control" id="exampleInputPassword1" placeholder="Entrez votre pseudo">
                    </div>
                    <div class="form-group">
                            <label for="nom">Nom</label>
                            <input name="nom" type="text" class="form-control" id="exampleInputPassword1" placeholder="Entrez votre nom">
                    </div>
                    <div class="form-group">
                            <label for="prenom">Prénom</label>
                            <input name="prenom" type="text" class="form-control" id="exampleInputPassword1" placeholder="Entrez votre prénom">
                    </div>
                    <div class="form-group">
                            <label for="Description">Description</label>
                            <textarea name="description" class="form-control" id="exampleTextarea" rows="3" placeholder="Entrez une petite description de vous-même :)"></textarea>
                    </div>
                    <?php 
                        CreateListBoxAge();
                        CreateListBoxHeight();
                        CreateListBoxWidth();
                        CreateListBoxGender();
                        //CreateListBoxCities();
                        //CreateListBoxLanguages();
                        createTextBoxJob();
                    ?>
                    <button type="submit" class="btn btn-primary btn-card">Inscription</button>
		</form>
	</div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="footer">
      <div class="container">
        <p class="text-center">Copyright &copy; Target 2017</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="../bootstrap-4.0.0-dist/js/jquery-3.2.1.js"></script>
    <script src="../bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
	</div>
  </body>

</html>
