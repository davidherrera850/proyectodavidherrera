package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.controller.DBHelper;

public class MainActivity extends AppCompatActivity {
    //Creamos las varibales locales que utilizaremos
    TextView et1,et2;
    //Cursor
    private Cursor fila;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.etusuario);
        et2=findViewById(R.id.edtclave);
    }
    public void inicioSesion(View v){
        /*Creamos un objeto de la clase DBHelper e instaciamos el constructor
        y damos el nombre de la base de datos y la version
         */
        DBHelper admin=new DBHelper(this,"instituto",null,1);
        //Abrimos la base de datos como escritura
        SQLiteDatabase db=admin.getWritableDatabase();
        /*Creamos dos variables string y capturamos los datos ingresados
        por el usuario y lo convertimos a string
         */
        String usuario=et1.getText().toString();
        String contrasena=et2.getText().toString();
        /*Inicializamos el cursor y llamamos al objeto de la base de
        datos para realizar un sentencia query donde pasamos las 2 variables
        nombre de usuario y contraseña
         */
        fila=db.rawQuery("select username,clave_user from userstable where username='"+
                usuario+"' and clave_user='"+contrasena+"'",null);
        //Realizamos un try catch para captura de errores
        try {
            //Preguntamos si cursor tiene algun dato
            if(fila.moveToFirst()){
                //Capturamos los valores del cursor y lo almacenamos en variable
                String usua=fila.getString(0);
                String pass=fila.getString(1);
                //Preguntamos si los datos ingresados son iguales
                if(usuario.equals(usua)&&contrasena.equals(pass)){
                    //Si son iguales entonces vamos a otra ventana
                    //Menu es una nueva actividad empty
                    Intent ven=new Intent(this,Principal.class);
                    //Lanzamos la actividad
                    startActivity(ven);
                    //Limpiamos las cajas de texto
                    et1.setText("");
                    et2.setText("");
                }
                else {
                    Toast toast = Toast.makeText(this, "Datos vacios", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            //si la condicion no se cumple entonces envia un mensaje
            else{
                Toast toast=Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_LONG);
                toast.show();
            }
        }catch (Exception e){
            Toast toast=Toast.makeText(this,"Error"+e.getMessage(),Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void Salir(View v){
        finish();
    }

    public void registro(View v){
        //creamos una variables e instanciamos al intent para que me muestra la clase
        Intent registrar=new Intent(this, Registro.class);
        //lanzamos la actividad
        startActivity(registrar);
        finish();
    }

}