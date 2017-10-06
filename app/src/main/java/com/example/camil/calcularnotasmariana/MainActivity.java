package com.example.camil.calcularnotasmariana;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




/*
holaaaa

 */

public class MainActivity extends AppCompatActivity {


    private TextView Mat1Resultado, Mat2Resultado, Mat3Resultado;
    private EditText Mat1Not1, Mat1Not2, Mat1Not3, Mat2Not1, Mat2Not2, Mat2Not3, Mat3Not1, Mat3Not2, Mat3Not3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Se inicializan  en el Oncreate las Variables a ejecutar como son los editText y los TextView

        Mat1Resultado = (TextView) findViewById(R.id.Mat1Resultado);
        Mat2Resultado = (TextView) findViewById(R.id.Mat2Resultado);
        Mat3Resultado = (TextView) findViewById(R.id.Mat3Resultado);
        Mat1Not1 = (EditText) findViewById(R.id.Mat1Not1);
        Mat1Not2 = (EditText) findViewById(R.id.Mat1Not2);
        Mat1Not3 = (EditText) findViewById(R.id.Mat1Not3);
        Mat2Not1 = (EditText) findViewById(R.id.Mat2Not1);
        Mat2Not2 = (EditText) findViewById(R.id.Mat2Not2);
        Mat2Not3 = (EditText) findViewById(R.id.Mat2Not3);
        Mat3Not1 = (EditText) findViewById(R.id.Mat3Not1);
        Mat3Not2 = (EditText) findViewById(R.id.Mat3Not2);
        Mat3Not3 = (EditText) findViewById(R.id.Mat3Not3);
//   El SharedPreferences es  utilizado para la persitencia en aplicaciones en este caso lo utilizamos para que nos guarde los datos ingresados en cada EditText y Textview
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Mat1Not1.setText(prefe.getString("nota1M1", "0"));
        Mat1Not2.setText(prefe.getString("nota2M1", "0"));
        Mat1Not3.setText(prefe.getString("nota3M1", "0"));
        Mat1Resultado.setText(prefe.getString("Mat1Resultado", Mat1Resultado.getText().toString()));
        Mat2Not1.setText(prefe.getString("nota1M2", "0"));
        Mat2Not2.setText(prefe.getString("nota2M2", "0"));
        Mat2Not3.setText(prefe.getString("nota3M2", "0"));
        Mat2Resultado.setText(prefe.getString("Mat2Resultado", Mat2Resultado.getText().toString()));
        Mat3Not1.setText(prefe.getString("nota1M3", "0"));
        Mat3Not2.setText(prefe.getString("nota2M3", "0"));
        Mat3Not3.setText(prefe.getString("nota3M3", "0"));
        Mat3Resultado.setText(prefe.getString("Mat3Resultado", Mat3Resultado.getText().toString()));

 //La materia 1 de la nota 1 Se utiliza el metodo TextWatcher ya que maneja estados el antes, en presente y el despues el cual se utiliza para este caso que nos calcule
        // las notas de cada materia sin dar clic a un boton
        Mat1Not1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                    try {
                        //El siguiente if se utiliza para que cuando el Usuario digite un numero mayor a 5 no se lo permita la aplicacion ya que es una app de notas y van de 0 a 5

                        if (Double.parseDouble(Mat1Not1.getText().toString()) > 5) {
                            Mat1Not1.setText("");
                            Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.lasNotasMayoresA5NoExisten), Toast.LENGTH_SHORT);
                            notificacion.show();
                        } else {

                            String nota1 = Mat1Not1.getText().toString();
                            String nota2 = Mat1Not2.getText().toString();
                            String nota3 = Mat1Not3.getText().toString();
                            float nro1 = Float.parseFloat(nota1);

                                // se llama al metodo que Calcula las 3 notas
                                metodoMat1(nota1, nota2, nota3);
                                // se utiliza el SharedPreferences para guardar persistencia cada ves que el usuario digite una nota
                                SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferencias.edit();
                                editor.putString("nota1M1", Mat1Not1.getText().toString());
                                editor.putString("nota2M1", Mat1Not2.getText().toString());
                                editor.putString("nota3M1", Mat1Not3.getText().toString());
                                editor.putString("Mat1Resultado", Mat1Resultado.getText().toString());
                   // hice un CAMBIOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO OOOO OOO OOO OO
                                editor.commit();

                        }
                    }catch (Exception e) {
                        if (Mat1Not1.getText().toString().equals("") && Mat1Not2.getText().toString().equals("")
                                && Mat1Not3.getText().toString().equals("")) {
                            Toast notificacion = Toast.makeText(MainActivity.this, getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                            notificacion.show();
                            Mat1Resultado.setText("");
                        } else if (Mat1Not3.getText().toString().equals("")) {
                            if (!Mat1Resultado.getText().toString().equals("")) {
                                String nota1 = Mat1Not1.getText().toString();
                                String nota2 = Mat1Not2.getText().toString();
                                if (nota1.equals("") || nota2.equals("")) {
                                    Toast notificacion = Toast.makeText(MainActivity.this, getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                                    notificacion.show();
                                } else {
                                    // se llama al metodo que Calcula LAS DOS PRIMERAS notas
                                    metodoMat1corto(nota1, nota2);
                                   }
                            }
                        } else {
                            Toast notificacion = Toast.makeText(MainActivity.this, getResources().getString(R.string.teHaceFaltaDigitarUnaNota), Toast.LENGTH_SHORT);
                            notificacion.show();
                        }
                    }
                }

        });
//La materia 1 de la nota 2 Se utiliza el metodo TextWatcher ya que maneja estados el antes, en presente y el despues el cual se utiliza para este caso que nos calcule
        // las notas de cada materia sin dar clic a un boton

        Mat1Not2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    //El siguiente if se utiliza para que cuando el Usuario digite un numero mayor a 5 no se lo permita la aplicacion ya que es una app de notas y van de 0 a 5


                    if (Double.parseDouble(Mat1Not2.getText().toString()) > 5) {
                        Mat1Not2.setText("");
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.lasNotasMayoresA5NoExisten), Toast.LENGTH_SHORT);
                        notificacion.show();
                    } else {

                        String nota1 = Mat1Not1.getText().toString();
                        String nota2 = Mat1Not2.getText().toString();
                        String nota3 = Mat1Not3.getText().toString();
                        float nro2 = Float.parseFloat(nota2);
                        // se llama al metodo que Calcula las 3 notas

                            metodoMat1(nota1, nota2, nota3);
                        // se utiliza el SharedPreferences para guardar persistencia cada ves que el usuario digite una nota
                            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferencias.edit();
                            editor.putString("nota1M1", Mat1Not1.getText().toString());
                            editor.putString("nota2M1", Mat1Not2.getText().toString());
                            editor.putString("nota3M1", Mat1Not3.getText().toString());
                            editor.putString("Mat1Resultado", Mat1Resultado.getText().toString());
                            editor.commit();

                    }
                }catch (Exception e) {
                    if (Mat1Not1.getText().toString().equals("") && Mat1Not2.getText().toString().equals("")
                            && Mat1Not3.getText().toString().equals("")) {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                        notificacion.show();
                        Mat1Resultado.setText("");
                    } else if (Mat1Not3.getText().toString().equals("")) {
                        if (!Mat1Resultado.getText().toString().equals("")) {
                            String nota1 = Mat1Not1.getText().toString();
                            String nota2 = Mat1Not2.getText().toString();
                            if (nota1.equals("") || nota2.equals("")) {
                                Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                                notificacion.show();
                            } else {
                                // se llama al metodo que Calcula LAS DOS PRIMERAS notas
                                metodoMat1corto(nota1, nota2);


                            }
                        }
                    }
                    else
                    {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarUnaNota), Toast.LENGTH_SHORT);
                        notificacion.show();
                    }
                }
            }

        });
//La materia 1 de la nota 3 Se utiliza el metodo TextWatcher ya que maneja estados el antes, en presente y el despues el cual se utiliza para este caso que nos calcule
        // las notas de cada materia sin dar clic a un boton

        Mat1Not3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    //El siguiente if se utiliza para que cuando el Usuario digite un numero mayor a 5 no se lo permita la aplicacion ya que es una app de notas y van de 0 a 5

                    if (Double.parseDouble(Mat1Not3.getText().toString()) > 5) {
                        Mat1Not3.setText("");
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.lasNotasMayoresA5NoExisten), Toast.LENGTH_SHORT);
                        notificacion.show();
                    } else {

                        String nota1 = Mat1Not1.getText().toString();
                        String nota2 = Mat1Not2.getText().toString();
                        String nota3 = Mat1Not3.getText().toString();

                        // se llama al metodo que Calcula las 3 notas
                            metodoMat1(nota1, nota2, nota3);
                        // se utiliza el SharedPreferences para guardar persistencia cada ves que el usuario digite una nota
                            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferencias.edit();
                            editor.putString("nota1M1", Mat1Not1.getText().toString());
                            editor.putString("nota2M1", Mat1Not2.getText().toString());
                            editor.putString("nota3M1", Mat1Not3.getText().toString());
                            editor.putString("Mat1Resultado", Mat1Resultado.getText().toString());
                            editor.commit();

                    }
                }catch (Exception e) {
                    if (Mat1Not1.getText().toString().equals("") && Mat1Not2.getText().toString().equals("")
                            && Mat1Not3.getText().toString().equals("")) {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                        notificacion.show();
                        Mat1Resultado.setText("");
                    } else if (Mat1Not3.getText().toString().equals("")) {
                        if (!Mat1Resultado.getText().toString().equals("")) {
                            String nota1 = Mat1Not1.getText().toString();
                            String nota2 = Mat1Not2.getText().toString();
                            if (nota1.equals("") || nota2.equals("")) {
                                Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                                notificacion.show();
                            } else {
                                // se llama al metodo que Calcula LAS DOS PRIMERAS notas
                                metodoMat1corto(nota1, nota2);
                                float resultado =  Float.parseFloat(Mat1Resultado.getText().toString());
                                if (resultado < 3) {
                                    float valor =  (3 - resultado);
                                    float valortotal = ((int)(valor/0.4)*100)/100;
                                    Toast notificacion = Toast.makeText(MainActivity.this, getResources().getString(R.string.teHaceFalta)+" " +valortotal +" " +( getResources().getString(R.string.paraPasarLaMateria)), Toast.LENGTH_SHORT);
                                    notificacion.show();

                                }

                            }




                        }
                    }
                    else
                    {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarUnaNota), Toast.LENGTH_SHORT);
                        notificacion.show();
                    }
                }
            }
        });

/*
===============================================================================================================================================
===============================================================================================================================================

//La materia 2 de la nota 1 Se utiliza el metodo TextWatcher ya que maneja estados el antes, en presente y el despues el cual se utiliza para este caso que nos calcule
        // las notas de cada materia sin dar clic a un boton
 */       Mat2Not1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    //El siguiente if se utiliza para que cuando el Usuario digite un numero mayor a 5 no se lo permita la aplicacion ya que es una app de notas y van de 0 a 5

                    if (Double.parseDouble(Mat2Not1.getText().toString()) > 5) {
                        Mat2Not1.setText("");
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.lasNotasMayoresA5NoExisten), Toast.LENGTH_SHORT);
                        notificacion.show();
                    } else {

                    String nota1m2 = Mat2Not1.getText().toString();
                    String nota2m2 = Mat2Not2.getText().toString();
                    String nota3m2 = Mat2Not3.getText().toString();
                        // se llama al metodo que Calcula las 3 notas
                        metodoMat2(nota1m2, nota2m2, nota3m2);
                        // se utiliza el SharedPreferences para guardar persistencia cada ves que el usuario digite una nota
                        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString("nota1M2", Mat2Not1.getText().toString());
                        editor.putString("nota2M2", Mat2Not2.getText().toString());
                        editor.putString("nota3M2", Mat2Not3.getText().toString());
                        editor.putString("Mat2Resultado", Mat2Resultado.getText().toString());
                        editor.commit();

                     }
                }catch (Exception e) {
                    if (Mat2Not1.getText().toString().equals("") && Mat2Not2.getText().toString().equals("")
                            && Mat2Not3.getText().toString().equals("")) {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                        notificacion.show();
                        Mat2Resultado.setText("");
                    } else if (Mat2Not3.getText().toString().equals("")) {
                        if (!Mat2Resultado.getText().toString().equals("")) {
                            String nota1m2 = Mat2Not1.getText().toString();
                            String nota2m2 = Mat2Not2.getText().toString();
                            if (nota1m2.equals("") || nota2m2.equals("")) {
                                Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                                notificacion.show();
                            } else {
                                // se llama al metodo que Calcula LAS DOS PRIMERAS notas
                                metodoMat2corto(nota1m2, nota2m2);


                            }
                        }
                    }
                    else
                    {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarUnaNota), Toast.LENGTH_SHORT);
                        notificacion.show();
                    }
                }
            }

        });

//La materia 2 de la nota 2 Se utiliza el metodo TextWatcher ya que maneja estados el antes, en presente y el despues el cual se utiliza para este caso que nos calcule
        // las notas de cada materia sin dar clic a un boton
        Mat2Not2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    //El siguiente if se utiliza para que cuando el Usuario digite un numero mayor a 5 no se lo permita la aplicacion ya que es una app de notas y van de 0 a 5

                    if (Double.parseDouble(Mat2Not2.getText().toString()) > 5) {
                        Mat2Not2.setText("");
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.lasNotasMayoresA5NoExisten), Toast.LENGTH_SHORT);
                        notificacion.show();
                    } else {

                    String nota1m2 = Mat2Not1.getText().toString();
                    String nota2m2 = Mat2Not2.getText().toString();
                    String nota3m2 = Mat2Not3.getText().toString();

                        // se llama al metodo que Calcula las 3 notas
                        metodoMat2(nota1m2, nota2m2, nota3m2);
                        // se utiliza el SharedPreferences para guardar persistencia cada ves que el usuario digite una nota
                        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString("nota1M2", Mat2Not1.getText().toString());
                        editor.putString("nota2M2", Mat2Not2.getText().toString());
                        editor.putString("nota3M2", Mat2Not3.getText().toString());
                        editor.putString("Mat2Resultado", Mat2Resultado.getText().toString());
                        editor.commit();

                        }
                }catch (Exception e) {
                    if (Mat2Not1.getText().toString().equals("") && Mat2Not2.getText().toString().equals("")
                            && Mat2Not3.getText().toString().equals("")) {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                        notificacion.show();
                        Mat2Resultado.setText("");
                    } else if (Mat2Not3.getText().toString().equals("")) {
                        if (!Mat2Resultado.getText().toString().equals("")) {
                            String nota1m2 = Mat2Not1.getText().toString();
                            String nota2m2 = Mat2Not2.getText().toString();
                            if (nota1m2.equals("") || nota2m2.equals("")) {
                                Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                                notificacion.show();
                            } else {
                                // se llama al metodo que Calcula LAS DOS PRIMERAS notas
                                metodoMat2corto(nota1m2, nota2m2);


                            }
                        }
                    }
                    else
                    {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarUnaNota), Toast.LENGTH_SHORT);
                        notificacion.show();
                    }
                }
            }
        });

//La materia 2 de la nota 3 Se utiliza el metodo TextWatcher ya que maneja estados el antes, en presente y el despues el cual se utiliza para este caso que nos calcule
        // las notas de cada materia sin dar clic a un boton
        Mat2Not3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    //El siguiente if se utiliza para que cuando el Usuario digite un numero mayor a 5 no se lo permita la aplicacion ya que es una app de notas y van de 0 a 5

                    if (Double.parseDouble(Mat2Not3.getText().toString()) > 5) {
                        Mat2Not3.setText("");
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.lasNotasMayoresA5NoExisten), Toast.LENGTH_SHORT);
                        notificacion.show();
                    } else {

                    String nota1m2 = Mat2Not1.getText().toString();
                    String nota2m2 = Mat2Not2.getText().toString();
                    String nota3m2 = Mat2Not3.getText().toString();
                        // se llama al metodo que Calcula las 3 notas
                        metodoMat2(nota1m2, nota2m2, nota3m2);
                        // se utiliza el SharedPreferences para guardar persistencia cada ves que el usuario digite una nota
                        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString("nota1M2", Mat2Not1.getText().toString());
                        editor.putString("nota2M2", Mat2Not2.getText().toString());
                        editor.putString("nota3M2", Mat2Not3.getText().toString());
                        editor.putString("Mat2Resultado", Mat2Resultado.getText().toString());
                        editor.commit();

                            }
                } catch (Exception e) {
                    if (Mat2Not1.getText().toString().equals("") && Mat2Not2.getText().toString().equals("")
                            && Mat2Not3.getText().toString().equals("")) {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                        notificacion.show();
                        Mat2Resultado.setText("");
                    } else if (Mat2Not3.getText().toString().equals("")) {
                        if (!Mat2Resultado.getText().toString().equals("")) {
                            String nota1m2 = Mat2Not1.getText().toString();
                            String nota2m2 = Mat2Not2.getText().toString();
                            if (nota1m2.equals("") || nota2m2.equals("")) {
                                Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                                notificacion.show();
                            } else {
                                // se llama al metodo que Calcula LAS DOS PRIMERAS notas
                                metodoMat2corto(nota1m2, nota2m2);
                                float resultado =  Float.parseFloat(Mat2Resultado.getText().toString());
                                if (resultado < 3) {
                                    float valor =  (3 - resultado);
                                    float valortotal = ((int)(valor/0.4)*100)/100;
                                    Toast notificacion = Toast.makeText(MainActivity.this, getResources().getString(R.string.teHaceFalta)+" " +valortotal +" " +( getResources().getString(R.string.paraPasarLaMateria)), Toast.LENGTH_SHORT);
                                    notificacion.show();

                                }

                            }
                        }
                    }
                    else
                    {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarUnaNota), Toast.LENGTH_SHORT);
                        notificacion.show();
                    }
                }
            }
        });


/*
===============================================================================================================================================
===============================================================================================================================================
 */
//La materia 3 de la nota 1 Se utiliza el metodo TextWatcher ya que maneja estados el antes, en presente y el despues el cual se utiliza para este caso que nos calcule
        // las notas de cada materia sin dar clic a un boton
        Mat3Not1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }


            @Override
            public void afterTextChanged(Editable s) {

                try {
                    //El siguiente if se utiliza para que cuando el Usuario digite un numero mayor a 5 no se lo permita la aplicacion ya que es una app de notas y van de 0 a 5

                    if (Double.parseDouble(Mat3Not1.getText().toString()) > 5) {
                        Mat3Not1.setText("");
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.lasNotasMayoresA5NoExisten), Toast.LENGTH_SHORT);
                        notificacion.show();
                    } else {

                        String nota1m3 = Mat3Not1.getText().toString();
                        String nota2m3 = Mat3Not2.getText().toString();
                        String nota3m3 = Mat3Not3.getText().toString();
                        // se llama al metodo que Calcula las 3 notas
                        metodoMat3(nota1m3, nota2m3, nota3m3);
                        // se utiliza el SharedPreferences para guardar persistencia cada ves que el usuario digite una nota
                        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString("nota1M3", Mat3Not1.getText().toString());
                        editor.putString("nota2M3", Mat3Not2.getText().toString());
                        editor.putString("nota3M3", Mat3Not3.getText().toString());
                        editor.putString("Mat3Resultado", Mat3Resultado.getText().toString());
                        editor.commit();
                    }
                }catch (Exception e) {
                    if (Mat3Not1.getText().toString().equals("") && Mat3Not2.getText().toString().equals("")
                            && Mat3Not3.getText().toString().equals("")) {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                        notificacion.show();
                        Mat3Resultado.setText("");
                    } else if (Mat3Not3.getText().toString().equals("")) {
                        if (!Mat3Resultado.getText().toString().equals("")) {
                            String nota1m3 = Mat3Not1.getText().toString();
                            String nota2m3 = Mat3Not2.getText().toString();
                            if (nota1m3.equals("") || nota2m3.equals("")) {
                                Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                                notificacion.show();
                            } else {
                                // se llama al metodo que Calcula LAS DOS PRIMERAS notas
                                metodoMat3corto(nota1m3, nota2m3);


                            }
                        }
                    }
                    else
                    {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarUnaNota), Toast.LENGTH_SHORT);
                        notificacion.show();
                    }
                }
            }

        });

//La materia 3 de la nota 2 Se utiliza el metodo TextWatcher ya que maneja estados el antes, en presente y el despues el cual se utiliza para este caso que nos calcule
        // las notas de cada materia sin dar clic a un boton
        Mat3Not2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                try {
                    //El siguiente if se utiliza para que cuando el Usuario digite un numero mayor a 5 no se lo permita la aplicacion ya que es una app de notas y van de 0 a 5

                    if (Double.parseDouble(Mat3Not2.getText().toString()) > 5) {
                        Mat3Not2.setText("");
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.lasNotasMayoresA5NoExisten), Toast.LENGTH_SHORT);
                        notificacion.show();
                    } else {

                        String nota1m3 = Mat3Not1.getText().toString();
                        String nota2m3 = Mat3Not2.getText().toString();
                        String nota3m3 = Mat3Not3.getText().toString();
                        // se llama al metodo que Calcula las 3 notas
                            metodoMat3(nota1m3, nota2m3, nota3m3);
                        // se utiliza el SharedPreferences para guardar persistencia cada ves que el usuario digite una nota
                            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferencias.edit();
                            editor.putString("nota1M3", Mat3Not1.getText().toString());
                            editor.putString("nota2M3", Mat3Not2.getText().toString());
                            editor.putString("nota3M3", Mat3Not3.getText().toString());
                            editor.putString("Mat3Resultado", Mat3Resultado.getText().toString());
                            editor.commit();

                    }
                }catch (Exception e) {
                    if (Mat3Not1.getText().toString().equals("") && Mat3Not2.getText().toString().equals("")
                            && Mat3Not3.getText().toString().equals("")) {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                        notificacion.show();
                        Mat3Resultado.setText("");
                    } else if (Mat3Not3.getText().toString().equals("")) {
                        if (!Mat3Resultado.getText().toString().equals("")) {
                            String nota1m3 = Mat3Not1.getText().toString();
                            String nota2m3 = Mat3Not2.getText().toString();
                            if (nota1m3.equals("") || nota2m3.equals("")) {
                                Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                                notificacion.show();
                            } else {
                                // se llama al metodo que Calcula LAS DOS PRIMERAS notas
                                metodoMat3corto(nota1m3, nota2m3);


                            }
                        }
                    }
                    else
                    {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarUnaNota), Toast.LENGTH_SHORT);
                        notificacion.show();
                    }
                }
            }


        });

//La materia 3 de la nota 3 Se utiliza el metodo TextWatcher ya que maneja estados el antes, en presente y el despues el cual se utiliza para este caso que nos calcule
        // las notas de cada materia sin dar clic a un boton
        Mat3Not3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    //El siguiente if se utiliza para que cuando el Usuario digite un numero mayor a 5 no se lo permita la aplicacion ya que es una app de notas y van de 0 a 5

                    if (Double.parseDouble(Mat3Not3.getText().toString()) > 5) {
                        Mat3Not3.setText("");
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.lasNotasMayoresA5NoExisten), Toast.LENGTH_SHORT);
                        notificacion.show();
                    } else {

                        String nota1m3 = Mat3Not1.getText().toString();
                        String nota2m3 = Mat3Not2.getText().toString();
                        String nota3m3 = Mat3Not3.getText().toString();
                        // se llama al metodo que Calcula las 3 notas
                            metodoMat3(nota1m3, nota2m3, nota3m3);
                        // se utiliza el SharedPreferences para guardar persistencia cada ves que el usuario digite una nota
                            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferencias.edit();
                            editor.putString("nota1M3", Mat3Not1.getText().toString());
                            editor.putString("nota2M3", Mat3Not2.getText().toString());
                            editor.putString("nota3M3", Mat3Not3.getText().toString());
                            editor.putString("Mat3Resultado", Mat3Resultado.getText().toString());
                            editor.commit();

                    }
                }catch (Exception e) {
                    if (Mat3Not1.getText().toString().equals("") && Mat3Not2.getText().toString().equals("")
                            && Mat3Not3.getText().toString().equals("")) {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                        notificacion.show();
                        Mat3Resultado.setText("");
                    } else if (Mat3Not3.getText().toString().equals("")) {
                        if (!Mat3Resultado.getText().toString().equals("")) {
                            String nota1m3 = Mat3Not1.getText().toString();
                            String nota2m3 = Mat3Not2.getText().toString();
                            if (nota1m3.equals("") || nota2m3.equals("")) {
                                Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                                notificacion.show();
                            } else {
                                // se llama al metodo que Calcula LAS DOS PRIMERAS notas
                                metodoMat3corto(nota1m3, nota2m3);
                                float resultado =  Float.parseFloat(Mat3Resultado.getText().toString());
                                if (resultado < 3) {
                                    float valor =  (3 - resultado);
                                    if (Double.parseDouble(Mat3Resultado.getText().toString()) > 5) {

                                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.niCon5PasasLaMateria), Toast.LENGTH_SHORT);
                                        notificacion.show();
                                    } else {
                                        float valortotal = ((int) (valor / 0.4) * 100) / 100;
                                        Toast notificacion = Toast.makeText(MainActivity.this, getResources().getString(R.string.teHaceFalta) + " " + valortotal + " " + (getResources().getString(R.string.paraPasarLaMateria)), Toast.LENGTH_SHORT);
                                        notificacion.show();
                                    }

                                }

                            }
                        }
                    }
                    else
                    {
                        Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.teHaceFaltaDigitarDosNotas), Toast.LENGTH_SHORT);
                        notificacion.show();
                    }
                }
            }
        });

    }


    /**
     * Metodo para la Materia 1 en donde se calculan las 3 notas como parametros siguientes
     * @param nota1 nota del primer corte con un 0.3
     * @param nota2  nota del segundo corte con un 0.3
     * @param nota3  nota del tercer corte con un 0.4
     */
    public void metodoMat1(String nota1, String nota2, String nota3) {



        float nro1 = Float.parseFloat(nota1);
        float nro2 = Float.parseFloat(nota2);
        float nro3 = Float.parseFloat(nota3);
        float Materia1 = (float) ((((nro1 + nro2) / 2) * 0.6) + (nro3 * 0.4));
            String resultado = String.valueOf(Materia1);
            Mat1Resultado.setText(resultado);


    }


    // Hice Cambios  JOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO OOOO

    /**
     * Metodo donde calcula los 2 primeros cortes para sacar el estimado a sacar en el 3er corte
     * @param nota1 nota del primer corte con un 0.3
     * @param nota2 nota del segundo corte con un 0.3
     */


    public void metodoMat1corto(String nota1, String nota2) {


        float nro1 = Float.parseFloat(nota1);
        float nro2 = Float.parseFloat(nota2);
        float Materia1 = (float) (((nro1 + nro2) / 2) * 0.6);

            String resultado = String.valueOf(Materia1);
            Mat1Resultado.setText(resultado);



    }




    /**
     * Metodo para la Materia 2 en donde se calculan las 3 notas como parametros siguientes
     * @param nota1Mat2 nota del primer corte con un 0.3
     * @param nota2Mat2 nota del segundo corte con un 0.3
     * @param nota3Mat2 nota del tercer corte con un 0.4
     */

    public void metodoMat2(String nota1Mat2, String nota2Mat2, String nota3Mat2) {

        float nro1m2 = Float.parseFloat(nota1Mat2);
        float nro2m2 = Float.parseFloat(nota2Mat2);
        float nro3m2 = Float.parseFloat(nota3Mat2);
           float Materia1 = (float) ((((nro1m2 + nro2m2) / 2) * 0.6) + (nro3m2 * 0.4));
            String resultado = String.valueOf(Materia1);
            Mat2Resultado.setText(resultado);


    }

    /**
     * Metodo donde calcula los 2 primeros cortes para sacar el estimado a sacar en el 3er corte
     * @param nota1Mat2 nota del primer corte con un 0.3
     * @param nota2Mat2 nota del segundo corte con un 0.3
     */
    public void metodoMat2corto(String nota1Mat2, String nota2Mat2) {

        float nro1m2 = Float.parseFloat(nota1Mat2);
        float nro2m2 = Float.parseFloat(nota2Mat2);


            float Materia2 = (float) (((nro1m2 + nro2m2) / 2) * 0.6);
            String resultado = String.valueOf(Materia2);
            Mat2Resultado.setText(resultado);



    }

    /**
     * Metodo para la Materia 3 en donde se calculan las 3 notas como parametros siguientes
     * @param nota1m3 nota del primer corte con un 0.3
     * @param nota2m3 nota del segundo corte con un 0.3
     * @param nota3m3 nota del tercer corte con un 0.4
     */

    public void metodoMat3(String nota1m3, String nota2m3, String nota3m3) {


        float nro1m3 = Float.parseFloat(nota1m3);
        float nro2m3 = Float.parseFloat(nota2m3);
        float nro3m3 = Float.parseFloat(nota3m3);

            float Materia3 = (float) ((((nro1m3 + nro2m3) / 2) * 0.6) + (nro3m3 * 0.4));
            String resultado = String.valueOf(Materia3);
            Mat3Resultado.setText(resultado);


    }

    /**
     * Metodo donde calcula los 2 primeros cortes para sacar el estimado a sacar en el 3er corte
     * @param nota1m3 nota del primer corte con un 0.3
     * @param nota2m3 nota del segundo corte con un 0.3
     */
    public void metodoMat3corto(String nota1m3, String nota2m3) {

        float nro1m3 = Float.parseFloat(nota1m3);
        float nro2m3 =Float.parseFloat(nota2m3);



            float Materia3 = (float) ((nro1m3*0.3 + nro2m3*0.3));
            String resultado = String.valueOf(Materia3);
            Mat3Resultado.setText(resultado);



    }

    /**
     * Metodo donde se dirije a una nueva ventana donde hay mas informacion
     * @param view en la interfaz grafica con un OnClick
     */
    public void Acerca (View view)
    {
        Intent i =new Intent(this,AcercaDe.class);
        startActivity(i);
    }

    /**
     * Metodo Donde calcula todos los resultados de las materias y saca un ponderado dividiendo entre n materias
     * @param view en la interfaz grafica con un OnClick
     */

    public void CalcularTodo(View view) {

        try {
            String notaResultado1 = Mat1Resultado.getText().toString();
            String notaResultado2 = Mat2Resultado.getText().toString();
            String notaResultado3 = Mat3Resultado.getText().toString();
            float nro1 = Float.parseFloat(notaResultado1);
            float nro2 = Float.parseFloat(notaResultado2);
            float nro3 = Float.parseFloat(notaResultado3);
            float MateriaResultado = ((int)((nro1 + nro2 + nro3) / 3)*100)/100;
            String resultadotodo = String.valueOf(MateriaResultado);
            Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.elResultadoDeLPonderadoEs)+resultadotodo, Toast.LENGTH_SHORT);
            notificacion.show();

        } catch (Exception e) {
            if (Mat1Not1.getText().toString().equals("") && Mat1Not2.getText().toString().equals("")
                    && Mat1Not3.getText().toString().equals("") || (Mat2Not1.getText().toString().equals("")
                    && Mat2Not2.getText().toString().equals("") && Mat2Not3.getText().toString().equals("")) ||
                    (Mat3Not1.getText().toString().equals("") && Mat3Not2.getText().toString().equals("")
                            && Mat3Not3.getText().toString().equals(""))) {
                Toast notificacion = Toast.makeText(MainActivity.this,getResources().getString(R.string.relleneTodosLosCampos), Toast.LENGTH_SHORT);
                notificacion.show();
                Mat3Resultado.setText("");
                Mat2Resultado.setText("");
                Mat1Resultado.setText("");

            }


        }
    }
}
