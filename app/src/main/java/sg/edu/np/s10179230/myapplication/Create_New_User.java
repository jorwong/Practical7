package sg.edu.np.s10179230.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create_New_User extends AppCompatActivity {

    DBhandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__new__user);
        db=new DBhandler(this,null,null,1);
    }

    public void onLoginClick(View v){
        TextView tvuserC=findViewById(R.id.editTextC);
        TextView tvPassC=findViewById(R.id.txtPassC);

        String userNameInput=tvuserC.getText().toString();
        String passInput=tvPassC.getText().toString();

        Pattern patternUser =Pattern.compile("^[A-Za-z]{6,12}$");
        Pattern patternPass=Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).*$");

        Matcher matcherUser=patternUser.matcher(userNameInput);
        Matcher matcherPass=patternPass.matcher(passInput);

        if(matcherPass.matches()||matcherUser.matches()){
            Toast.makeText(getApplicationContext(),"Valid",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"Valid",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor NameUser=getSharedPreferences("hey",MODE_PRIVATE).edit();
            NameUser.putString("UserNameC",userNameInput);
            NameUser.apply();
            SharedPreferences.Editor NamePass=getSharedPreferences("hey",MODE_PRIVATE).edit();
            NamePass.putString("PassC",passInput);
            NamePass.apply();
            Account a=new Account(userNameInput,passInput);
            db.addAccount(a);

            Intent in = new Intent(Create_New_User.this, MainActivity.class);
            startActivity(in);
        }
        else {
            Toast.makeText(getApplicationContext(),"InValid",Toast.LENGTH_SHORT).show();
        }
    }

    public void onCancelClick(View v){
        Intent rettoLogin=new Intent(Create_New_User.this,LoginPage.class);
        startActivity(rettoLogin);
    }

}
