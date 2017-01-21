package com.example.ciuc.shoppingassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class CreateList extends Activity {

    public RadioButton alimente,imbracaminte,incaltaminte,pet,ingrijire,electrocasnice,electronice,farma,mobilier,auto;
    public EditText lName;
    public EditText itemsList;
    public String listName;
    public String itemsType;
    public String items;
    public int verified=0;
    public MainActivity main;
    public DatabaseHelper dataBase;
    public String tables="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_list);
        init();
        radioButtonChecked();
        backToMain();
    }

    private void init() {
        alimente=(RadioButton)findViewById(R.id.alimente);
        imbracaminte=(RadioButton)findViewById(R.id.imbracaminte);
        incaltaminte=(RadioButton)findViewById(R.id.incaltaminte);
        ingrijire=(RadioButton)findViewById(R.id.ingrijire);
        electronice=(RadioButton)findViewById(R.id.electronice);
        electrocasnice=(RadioButton)findViewById(R.id.electrocasnice);
        auto=(RadioButton)findViewById(R.id.auto);
        farma=(RadioButton)findViewById(R.id.farma);
        mobilier=(RadioButton)findViewById(R.id.mobilier);
        pet=(RadioButton)findViewById(R.id.pet);
        lName=(EditText) findViewById(R.id.listName);
        itemsList=(EditText) findViewById(R.id.items);
        dataBase=new DatabaseHelper(this);
        main=new MainActivity();
    }

    public String setListName() {
        if(lName.getText()==null||lName.getText().toString()==null||lName.getText().toString().isEmpty()){
            Toast.makeText(this,"Type a name for this list!",Toast.LENGTH_LONG).show();
            return null;
        }
        else{
            return lName.getText().toString();
        }
    }

    public String verifyItemsType(){
        if(itemsType==null||itemsType.isEmpty()){
            Toast.makeText(this,"Select a type for this list items!",Toast.LENGTH_LONG).show();
            return null;
        }
        else{
            return  itemsType;
        }
    }

    public String verifyItems(){
        if(itemsList.toString()==null||itemsList.toString().isEmpty()){
            Toast.makeText(this,"Put at least 1 item in the list !",Toast.LENGTH_LONG).show();
            return null;
        }
        else{
            Toast.makeText(this,itemsList.getText().toString(),Toast.LENGTH_LONG).show();
            return  itemsList.getText().toString();
        }
    }

    public void disableFocusAndClick(RadioButton btn1,RadioButton btn2,RadioButton btn3,RadioButton btn4,
                                     RadioButton btn5,RadioButton btn6,RadioButton btn7,RadioButton btn8,RadioButton btn9){
        btn1.setFocusable(false);
        btn2.setFocusable(false);
        btn3.setFocusable(false);
        btn4.setFocusable(false);
        btn5.setFocusable(false);
        btn6.setFocusable(false);
        btn7.setFocusable(false);
        btn8.setFocusable(false);
        btn9.setFocusable(false);
        btn1.setClickable(false);
        btn2.setClickable(false);
        btn3.setClickable(false);
        btn4.setClickable(false);
        btn5.setClickable(false);
        btn6.setClickable(false);
        btn7.setClickable(false);
        btn8.setClickable(false);
        btn9.setClickable(false);
    }

    private void radioButtonChecked() {
        alimente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(imbracaminte,incaltaminte,ingrijire,electrocasnice,electronice,auto,farma,mobilier,pet);
                itemsType="alimente";
            }
        });

        imbracaminte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(alimente,incaltaminte,ingrijire,electrocasnice,electronice,auto,farma,mobilier,pet);
                itemsType="imbracaminte";
            }
        });

        incaltaminte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(imbracaminte,alimente,ingrijire,electrocasnice,electronice,auto,farma,mobilier,pet);
                itemsType="incaltaminte";
            }
        });

        ingrijire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(imbracaminte,incaltaminte,alimente,electrocasnice,electronice,auto,farma,mobilier,pet);
                itemsType="ingrijire";
            }
        });

        electrocasnice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(imbracaminte,incaltaminte,ingrijire,alimente,electronice,auto,farma,mobilier,pet);
                itemsType="electrocasnice";
            }
        });

        electronice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(imbracaminte,incaltaminte,ingrijire,electrocasnice,alimente,auto,farma,mobilier,pet);
                itemsType="electronice";
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(imbracaminte,incaltaminte,ingrijire,electrocasnice,electronice,alimente,farma,mobilier,pet);
                itemsType="auto";
            }
        });

        farma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(imbracaminte,incaltaminte,ingrijire,electrocasnice,electronice,auto,alimente,mobilier,pet);
                itemsType="farma";
            }
        });

        mobilier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(imbracaminte,incaltaminte,ingrijire,electrocasnice,electronice,auto,farma,alimente,pet);
                itemsType="mobiier";
            }
        });

        pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableFocusAndClick(imbracaminte,incaltaminte,ingrijire,electrocasnice,electronice,auto,farma,mobilier,alimente);
                itemsType="pet";
            }
        });

    }

    private void backToMain() {
        Button backButton = (Button) findViewById(R.id.done);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(setListName()==null || verifyItemsType()==null||verifyItems()==null){

                }else{
                    listName=setListName();
                    items=itemsList.getText().toString();
                    verified =1;
                }
                if(verified==1){
                    dataBase.setName(listName);
                    dataBase.createDB();
                    boolean isInserted=dataBase.insertDatat(itemsType,items);
                    if(isInserted==true){
                        Toast.makeText(CreateList.this,"Inserted data/"+listName,Toast.LENGTH_LONG).show();
                        main.updateTablesNames(listName);
                        //main.tableNames;
                       // Toast.makeText(CreateList.this,main.tableNames,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(CreateList.this,"NOT Inserted data",Toast.LENGTH_LONG).show();
                    }

                    Intent showMain = new Intent(CreateList.this, MainActivity.class);
                    startActivity(showMain);
                }


            }
        });
    }



}
