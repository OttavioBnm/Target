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
	
    <!-- CSS de la page !-->
    <link href="css/index.css" rel="stylesheet">
    
    <!-- CSS des icônes !-->
    <link href="../font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">

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
          <li class="nav-item">
            <a class="nav-link" href="register.php">Inscription</a>
          </li>
		  <li class="nav-item">
            <a class="nav-link" href="login.php">Connexion</a>
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

    <!-- Page Content -->
    <div class="container">

      <!-- Page Features -->
        <h1 class="titre">Utilisateurs</h1>
        
        <table class="table table-hover">
            <thead>
              <tr>
                <th>Photo de profil</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Description</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><img src="img/logo1.png"></td>
                <td>Mark</td>
                <td>Otto</td>
                <td>Petite description du profil</td>
                <td>
                    <i class="fa fa-user fa-3x" aria-hidden="true"></i>
                    <i class="fa fa-heart fa-3x" aria-hidden="true"></i>
                </td>
              </tr>
              <tr>
                <td><img src="img/logo1.png"></td>
                <td>Mark</td>
                <td>Otto</td>
                <td>Petite description du profil</td>
                <td>
                    <i class="fa fa-user fa-3x" aria-hidden="true"></i>
                    <i class="fa fa-heart fa-3x" aria-hidden="true"></i>
                </td>
              </tr>
            </tbody>
        </table>

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
