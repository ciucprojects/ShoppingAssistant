package com.example.ciuc.shoppingassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    public String tableNames="";
    public void updateTablesNames(String name){
        if(tableNames.isEmpty()||tableNames==null){
            tableNames=name;
        }
        else
        {
            tableNames=tableNames+","+name;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCreateListButton();
        setupListsButton();
        setupMapButton();
    }


    private void setupCreateListButton() {
        ImageButton createListButton= (ImageButton) findViewById(R.id.createList);
        createListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent createList= new Intent(MainActivity.this, CreateList.class);
                startActivity(createList);
            }
        });

    }

    private void setupListsButton() {
        ImageButton listButton= (ImageButton) findViewById(R.id.lists);
        listButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent showLists = new Intent(MainActivity.this,ViewLists.class);
                startActivity(showLists);

            }
        });

    }

    private void setupMapButton() {
        ImageButton mapButton= (ImageButton) findViewById(R.id.map);
        mapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent showMap = new Intent(MainActivity.this,Map.class);
                startActivity(showMap);

            }
        });
    }
}
