package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.proyectofinal.controller.HarryapiService;
import com.example.proyectofinal.models.Harry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Principal extends AppCompatActivity {
    EditText id;
    TextView personaje;
    TextView apodo;
    TextView interpretado_por;
    ImageView imagen;
    Button btnBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //Vinculamos con nuestros controles del layout
        id = findViewById(R.id.id);
        personaje = findViewById(R.id.personaje);
        apodo = findViewById(R.id.apodo);
        interpretado_por = findViewById(R.id.interpretado_por);
        imagen = findViewById(R.id.imagen);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(id.getText().toString());
            }
        });
    }
    private void find(String id){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://fedeperin-harry-potter-api.herokuapp.com/")
                //Añadimos el convertidor Gson que estamos usando
                .addConverterFactory(GsonConverterFactory.create()).build();
        HarryapiService harryapiService=retrofit.create(HarryapiService.class);
        Call<Harry> call=harryapiService.find(id);
        call.enqueue(new Callback<Harry>() {
            @Override
            /*Este metodo solo se usara si llegar una respuesta HTTP por parte del servidor */
            public void onResponse(Call<Harry> call, Response<Harry> response) {
                try {
                    if(response.isSuccessful()){
                        Harry h=response.body();
                        //Revisar solo coge la foto de la id 1 y pone la misma para todos
                        String URL_IMG="https://raw.githubusercontent.com/fedeperin/harry-potter-api/main/imagenes/harry_potter.png";
                        //Se asignan los valores obtenidos de nuestra API
                        personaje.setText(h.getPersonaje());
                        apodo.setText(h.getApodo());
                        interpretado_por.setText(h.getInterpretado_por());
                        //Uso de la biblioteca Glide para mostrar nuestra imagen
                        Glide.with(getApplication()).load(URL_IMG).into(imagen);
                    }
                }catch (Exception ex){
                    Toast.makeText(Principal.this,ex.getMessage(),Toast.LENGTH_SHORT);
                }
            }

            @Override
            /*Este solo se lanzara en el caso de que ocurra un error de conexion o una mala sintaxis*/
            public void onFailure(Call<Harry> call, Throwable t) {
                Toast.makeText(Principal.this,"Error de conexion",Toast.LENGTH_SHORT);
            }
        });
    }
    //Metodo para mostrar y ocultar menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }
    //Metodo para asignar las funciones a las opciones del menu
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.item1){
            //Ventana de preferencias
            startActivity(new Intent(this,Preferencias.class));
        }else if(id==R.id.item2){
            //Si se le da a esta opcion se ponen las opciones de preferencias con valores por defecto
            SharedPreferences pref =
                    PreferenceManager.getDefaultSharedPreferences(this);
            Log.i("NavigationDrawer", "Opcion1: " + pref.getBoolean("Opcion1", true));
            Log.i("NavigationDrawer", "Opcion2: " + pref.getString("Opcion2", "España"));
            Log.i("NavigationDrawer", "Opcion3: " + pref.getString("Opcion3", "ESP"));
        }else if(id==R.id.item3){
            //Esta opcion es para reproduccir musica
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.musica);
            mediaPlayer.start();
        }
        return super.onOptionsItemSelected(item);
    }

}