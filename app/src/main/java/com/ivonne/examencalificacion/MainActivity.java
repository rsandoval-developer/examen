package com.ivonne.examencalificacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private EditText alumno;
    private EditText materia;
    private EditText calificacion;
    private Button guardar;
    private String url = "http://192.168.173.1/bd_android/alumno_nive.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alumno = (EditText) findViewById(R.id.alumno);
        materia = (EditText) findViewById(R.id.materia);
        calificacion = (EditText) findViewById(R.id.calificacion);
        guardar = (Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAlumno();
            }
        });
    }

    private void guardarAlumno() {
        AsyncHttpClient cliente = new AsyncHttpClient();
        RequestParams request = new RequestParams();
        request.add("alumno", alumno.getText().toString());
        request.add("materia", materia.getText().toString());
        request.add("calificacion", calificacion.getText().toString());
        cliente.post(url, request, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(MainActivity.this, "Alumno Guardado Correctamente", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Ocurrio un error al guardar el alumno", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
