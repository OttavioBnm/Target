<?php
session_start();
include 'model/function_BD.php';
include 'model/functions.php';
if (isset($_SESSION['pseudo'])) {
    header('Location:index.php');
}

if (isset($_REQUEST["emailconnec"]) && isset($_REQUEST["passwordConnec"])) {
            $areYouOneOfUs = checkIn($_REQUEST["emailconnec"], sha1($_REQUEST["passwordConnec"]));
            if (isset($areYouOneOfUs[0])) {
                $_SESSION['mail'] = $_REQUEST["emailconnec"];
                gotInfos();
                header('Location:index.php');
            } else {
                echo 'no';
            }
        }
gotInfos();
?>
<!DOCTYPE html>
<html lang="fr">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Connexion</title>

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
          <li class="nav-item">
            <a class="nav-link" href="register.php">Inscription</a>
          </li>
		  <li class="nav-item active">
            <a class="nav-link" href="login.php">Connexion <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#"></a>
          </li>
        </ul>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

		<form class="leformulaire">
                    <h1 class="display-3">Connexion</h1>
			<div class="form-group">
				<label for="email">Adresse email</label>
				<input name="emailconnec" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Entrez votre adresse email">
				<small id="emailHelp" class="form-text text-muted">Nous ne partagerons votre email avec personne d'autres</small>
			</div>
			<div class="form-group">
				<label for="motdepasse">Mot de passe</label>
				<input name="passwordConnec" type="password" class="form-control" id="exampleInputPassword1" placeholder="Entrez votre mot de passe">
			</div>
			<button type="submit" class="btn btn-primary btn-card">Connexion</button>
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
    <script src="../bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
	</div>
  </body>

</html>
