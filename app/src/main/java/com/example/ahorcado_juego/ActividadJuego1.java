package com.example.ahorcado_juego;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ActividadJuego1 extends AppCompatActivity {
    private int ids_respuestas[]={
            R.id.respuesta1, R.id.respuesta2,R.id.respuesta3,R.id.respuesta4,R.id.respuesta5,R.id.respuesta6,R.id.respuesta7,
            R.id.respuesta8
    };
    private ImageView[]parts;
    private int sizeParts=7;
    private int currPart;
    private int respuesta_correcta;
    private int actualPregunta;
    private String[] preguntas;
    private TextView text_question;
    private RadioGroup group;
    private int puntuacion;
    TextView puntuaciontxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_juego1);
        parts=new ImageView[sizeParts];
        parts[0]=findViewById(R.id.ahorcado1);
        parts[1]=findViewById(R.id.ahorcado2);
        parts[2]=findViewById(R.id.ahorcado3);
        parts[3]=findViewById(R.id.ahorcado4);
        parts[4]=findViewById(R.id.ahorcado5);
        parts[5]=findViewById(R.id.ahorcado6);
        parts[6]=findViewById(R.id.ahorcado7);

        currPart=0;




        puntuaciontxt=(TextView) findViewById(R.id.puntuaciontxt);
        text_question=findViewById(R.id.text_question);
        group=findViewById(R.id.gruporespuesta);
        Button btn_check=findViewById(R.id.btn_check);
        preguntas=getResources().getStringArray(R.array.pregunta);
        actualPregunta=0;
        mostrarPreguntas();
        puntuacion=0;




      btn_check.setOnClickListener((v -> {
              int id = group.getCheckedRadioButtonId();
              int respuesta =-1;
              for(int i=0; i<ids_respuestas.length;i++) {
                  if (ids_respuestas[i] == id) {
                      respuesta = i;
                  }
              }
              if(respuesta==respuesta_correcta){
                  Toast.makeText(ActividadJuego1.this, getResources().getString(R.string.correctotext), Toast.LENGTH_SHORT).show();


                  if(actualPregunta<preguntas.length-1){
                      actualPregunta++;
                      mostrarPreguntas();
                  }
                  puntuacion++;
                  puntuaciontxt.setText("Puntos: "+Integer.toString(puntuacion));
                  if(puntuacion==preguntas.length){
                     Intent intent = new Intent(this,MainActivity.class);
                     startActivity(intent);
                     Toast.makeText(ActividadJuego1.this, getResources().getString(R.string.Victoria), Toast.LENGTH_SHORT).show();
                  }

              }else {
                  Toast.makeText(ActividadJuego1.this, getResources().getString(R.string.Derrota), Toast.LENGTH_SHORT).show();
                  if(currPart<sizeParts){
                      parts[currPart].setVisibility(View.VISIBLE);
                      currPart++;
                  }
                  if(currPart==sizeParts){
                      btn_check.setEnabled(false);
                  }


              }
      }));
    }
    private void mostrarPreguntas(){
        String q=preguntas[actualPregunta];
        String[] partes=q.split(";");
        text_question.setText(partes[0]);
        group.clearCheck();
        for(int i=0;i<ids_respuestas.length;i++){
            RadioButton rb =findViewById(ids_respuestas[i]);
            String respuesta=partes[i+1];
            if(respuesta.charAt(0)=='*'){
                respuesta_correcta=i;
                respuesta=respuesta.substring(1);

            }
            rb.setText(respuesta);
        }
    }

    public void JugardeNuevo(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}