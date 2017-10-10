<?php
session_start();
include 'model/function_BD.php';
include 'model/functions.php';
gotInfos();
$UsersDataBase = getUsers();
?>
<!DOCTYPE html>
<html lang="fr">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Accueil</title>

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
          <li class="nav-item active">
            <a class="nav-link" href="#">Accueil <span class="sr-only">(current)</span></a>
          </li>
          <?php
          if(isset($_SESSION["pseudo"])){
              echo '
                    <li class="nav-item">
                        <a class="nav-link" href="./profil.php?$_SESSION["pseudo"]">Mon profil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="model/deconnexion.php">Déconnexion</a>
                    </li>';
          }
          else{
              echo '<li class="nav-item">
                        <a class="nav-link" href="register.php">Inscription</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.php">Connexion</a>
                    </li>';
          }
          ?>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="text" placeholder="Rechercher un profil">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Rechercher</button>
        </form>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container hidden-lg hidden-md">

      <!-- Jumbotron Header -->
      <header class="jumbotron">
        <h1 class="display-3">Bienvenue !</h1>
        <p class="lead">Target est une application de rencontre  ...</p>
        <?php
                  if (isset($_SESSION["pseudo"])) {
                      echo '';
                  }
                  else{
                      echo '<a href="#" class="btn btn-primary btn-lg btn-card">S\'inscire</a>';
                  }
        ?>
      </header>

      <!-- Page Features -->
      <div class="row text-center hidden-md">
     
        <?php
        foreach ($UsersDataBase as $ficheUser){
        echo '<div class="card randomCard col-lg-3 col-md-6">
                <img class="thumbnail img-responsive ya" src="img/logo1.png"">
                <div class="card-block">
                        <h4 class="card-title">'.$ficheUser["nom"].'  '.$ficheUser["prenom"].'</h4>
                        <p class="card-text">'.$ficheUser["description"].'</p>
                        <hr>';
                    echo '<a href="profil.php?pseudo=' . $ficheUser["login"]. " \"".'class="btn btn-primary btn-card">Voir profil</a>';
                        if(isset($_SESSION["pseudo"])){echo '<a href="#" class="btn btn-primary btn-card">Demande de match</a>';}
                            echo '</div>
        </div>';
        
        }
        ?>
		
      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="footer">
      <div class="container">
        <p class="text-center">Target &copy Tous droits réservés</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="../bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
	</div>
  </body>

</html>
