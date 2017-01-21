package com.example.ciuc.shoppingassistant;


import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ViewLists extends Activity{
    public CreateList cl;
    public String[] tablesNames;
    public StringBuffer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        init();
        load();
    }

    private void load() {
        Button load= (Button) findViewById(R.id.loadList);
        load.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(ViewLists.this,buffer.toString(),Toast.LENGTH_LONG).show();
                //showData(buffer.toString());
            }
        });
    }


    private void init() {
        cl=new CreateList();

        Toast.makeText(ViewLists.this,cl.listName,Toast.LENGTH_LONG).show();

        /*
        if(mainA.tableNames!=null||mainA.tableNames.isEmpty()==false){
            tablesNames=mainA.tableNames.split(",");
        }
        Cursor res=cl.dataBase.getAllData();
        if(res==null){
            Toast.makeText(ViewLists.this,"res is null",Toast.LENGTH_LONG).show();
        }else{
            if(res.getCount()==0){
                Toast.makeText(ViewLists.this,"0 lists in database!",Toast.LENGTH_LONG).show();
                return;
            }
            else{
                buffer=new StringBuffer();
                int i=0;
                while(res.moveToNext()){
                    buffer.append("List:"+tablesNames[i]);
                    buffer.append("ID:"+res.getString(0)+"\n");
                    buffer.append("Type:"+res.getString(1)+"\n");
                    buffer.append("Items:"+res.getString(2)+"\n\n");
                    i++;
                }

                //showData(buffer.toString());

            }


        }

        */
    }

    public void showData(String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.show();

    }
}
