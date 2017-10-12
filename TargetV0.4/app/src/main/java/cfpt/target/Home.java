package cfpt.target;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    setTitle(R.string.title_home);
                    return true;
                case R.id.navigation_chat:
                    mTextMessage.setText("Chat");
                    setTitle("Chat");
                    return true;
                case R.id.navigation_Localisation:
                    mTextMessage.setText("Localiser");
                    setTitle("Localiser");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        afficheProfil();


    }

    /*private String recupererProfile(int idUser){
        String mdp="VIDE";
        String email = "VIDE";
        try {
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT );

            while (res.next()) {
                email = res.getString("email");
                mdp = res.getString("mdp");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return null;
    }*/

    private int getIdUser()
    {
        int id = 0;
        try {
            Statement st = Connect.get().createStatement();
            ResultSet res = st.executeQuery("SELECT idUtilisateur FROM tUtilisateur WHERE email = \""+ Login.getEmailBackup()+"\"");

            while (res.next()) {
                id = res.getInt("idUtilisateur");
                return id;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return id;
        }
        return id;
    }

    private void afficheProfil()
    {
        TableLayout container = (TableLayout)findViewById(R.id.table);
        for (int i = 0; i < 10; i++)
        {
            TableRow myRow = new TableRow(this);
            for (int j = 0; j < 2; j++)
            {
                if (j != 0)
                {
                    TextView myText = new TextView(this);
                    myText.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT));
                    myText.setText("OUAIS" + i);
                    myRow.addView(myText);
                }
                else
                {
                    ImageView myImage = new ImageView(this);
                    myImage.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT));
                    Drawable myDrawable = getResources().getDrawable(R.drawable.ic_home_black_24dp);
                    myImage.setImageDrawable(myDrawable);
                    myRow.addView(myImage);
                }
            }
            container.addView(myRow);
        }

    }
}
