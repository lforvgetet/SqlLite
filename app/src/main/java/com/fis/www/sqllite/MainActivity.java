package com.fis.www.sqllite;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Click for insert
        Button insert = (Button)findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.name);
                EditText editId= (EditText)findViewById(R.id.editId);
                AuthorDAO authorDB = new AuthorDAO(getApplicationContext());
                Author author = new Author(name.getText().toString());
                author= authorDB.insert(author);
                editId.setText(String.valueOf(author.getId()), TextView.BufferType.EDITABLE);
            }
        });
        //Click for select
        Button select = (Button)findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                EditText name = (EditText)findViewById(R.id.name);
                EditText editId = (EditText)findViewById(R.id.editId);
                AuthorDAO authorDB = new AuthorDAO(getApplicationContext());
                Author author = authorDB.get(Long.parseLong(editId.getText().toString()));
                name.setText(author.getName(),TextView.BufferType.EDITABLE);
                
            }
        });
        Button loginPage=(Button)findViewById(R.id.loginPage);
        loginPage.setOnClickListener(new Button.OnClickListener(){
            @Override
        public void onClick(View view){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestLoginActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
