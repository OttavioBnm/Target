package presentation;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.Statement;

import data.connexion.Connect;
import cfpt.target.R;
import data.outils.DBManager;
import metier.HomeManagement;

public class Home extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        cleanLayout();
                        afficheProfil();
                        return true;
                    case R.id.navigation_chat:
                        cleanLayout();
                        setTitle("Chat");
                        return true;
                    case R.id.navigation_Localisation:
                        cleanLayout();
                        setTitle("Localiser");
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().hide();
        afficheProfil();

    }

    private void afficheProfil()
    {
        TableLayout container = (TableLayout)findViewById(R.id.table);
        int[] icones = new int[]{R.drawable.ic_action_name,R.drawable.ic_description,R.drawable.ic_home,R.drawable.ic_genre,R.drawable.ic_calendrier,R.drawable.ic_langue,R.drawable.ic_hauteur,R.drawable.ic_poids,R.drawable.ic_job};
        String[][] profil = new String[13][2];
        TableRow.LayoutParams layout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,200);
        profil = HomeManagement.elementsProfil();
        for (int i = 0; i < profil.length; i++)
        {
            TableRow myRow = new TableRow(this);
            for (int j = 0; j < 2; j+=2)
            {
                if (profil[i][j+1] != null) {
                    ImageView myImage = new ImageView(this);
                    myImage.setLayoutParams(new TableRow.LayoutParams(150, 200));
                    Drawable myDrawable = getResources().getDrawable(icones[i]);
                    myImage.setImageDrawable(myDrawable);
                    myRow.addView(myImage);

                    TextView myText = new TextView(this);
                    myText.setLayoutParams(layout);
                    myText.setTextSize(24);
                    myText.setText(profil[i][j] + ":\n" + profil[i][j + 1]);
                    myRow.addView(myText);
                }
            }
            container.addView(myRow);
        }
    }

    private void cleanLayout(){
        TableLayout container = (TableLayout)findViewById(R.id.table);
        container.removeAllViews();
    }
}
