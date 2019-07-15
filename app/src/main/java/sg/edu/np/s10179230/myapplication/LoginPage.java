package sg.edu.np.s10179230.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.view.KeyEvent.KEYCODE_ENTER;

public class LoginPage extends AppCompatActivity  {

    DBhandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        db=new DBhandler(this,null,null,1);
        final TextView tv = findViewById(R.id.txtNewuser);

        View.OnTouchListener onTouchListener= new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    Intent newpage= new Intent(LoginPage.this,Create_New_User.class);
                    startActivity(newpage);
                    return true;

                }
                return false;
            }
        };
        tv.setOnTouchListener(onTouchListener);
    }

    public void onLoginClick(View v){
        TextView tvuser=findViewById(R.id.txtUserName);
        TextView tvPass=findViewById(R.id.txtPassword);

        String userNameInput=tvuser.getText().toString();
        String passInput=tvPass.getText().toString();

        Pattern patternUser =Pattern.compile("^[A-Za-z]{6,12}$");
        Pattern patternPass=Pattern.compile("^+[$&+,:;=?@#|]+[A-Z]+[0-9]$");

        Matcher matcherUser=patternUser.matcher(userNameInput);
        Matcher matcherPass=patternPass.matcher(passInput);

        if(matcherPass.matches()||matcherUser.matches()){
            Toast.makeText(getApplicationContext(),"Valid",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor NameUser=getSharedPreferences("hey",MODE_PRIVATE).edit();
            NameUser.putString("UserNameL",userNameInput);
            NameUser.apply();
            SharedPreferences.Editor NamePass=getSharedPreferences("hey",MODE_PRIVATE).edit();
            NamePass.putString("PassL",passInput);
            NamePass.apply();
            Account a=new Account(userNameInput,passInput);
            db.addAccount(a);
        }
        else {
            Toast.makeText(getApplicationContext(),"InValid",Toast.LENGTH_SHORT).show();
        }
    }



}
