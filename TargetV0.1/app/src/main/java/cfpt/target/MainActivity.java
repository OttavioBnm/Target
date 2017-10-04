package cfpt.target;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.app.ActionBar;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Initialisation du bouton "Connexion"
    Button btnConnexion;
    Button btnInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Affectation du bouton
        btnConnexion = (Button) findViewById(R.id.btnConnexion);
        btnInscription = (Button) findViewById(R.id.btnInscription);

        final WebView pageInscription = (WebView) findViewById(R.id.wvInscription);

        btnInscription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Démarrer l'instence (ouvrir la nouvelle fenêtre)
                pageInscription.loadUrl("https://www.google.com");
            }
        });

        btnConnexion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Création d'une nouvelle instence
                Intent intent = new Intent(MainActivity.this, Login.class);

                // Démarrer l'instence (ouvrir la nouvelle fenêtre)
                startActivity(intent);
            }
        });
    }

}
