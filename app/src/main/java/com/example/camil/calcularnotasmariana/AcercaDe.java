package com.example.camil.calcularnotasmariana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AcercaDe extends AppCompatActivity {


    private TextView tvFrank,tvJuan,tvVilla,infoFrank,infoJuan,infoVilla;

    private ImageView image_view;
    private ImageView image_view1;
    private ImageView image_view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        tvFrank=(TextView)findViewById(R.id.tvFrank);
        tvJuan =(TextView)findViewById(R.id.tvJuan);
        tvVilla=(TextView)findViewById(R.id.tvVilla);

        image_view = (ImageView) findViewById(R.id.image_view);
        image_view1 = (ImageView) findViewById(R.id.image_view1);
        image_view2 = (ImageView) findViewById(R.id.image_view2);
        infoFrank = (TextView) findViewById(R.id.infoFrank);
        infoJuan = (TextView) findViewById(R.id.infoJuan);
        infoVilla = (TextView) findViewById(R.id.infoVilla);
}


    /**
     * Horario de Frank
     * @param view vista grafica
     */
    public void verHorarioFrank(View view)
    {
        image_view.setImageResource(R.drawable.horariof);
        infoFrank.setText("3015807225");

    }

    /**
     * Horario de Juan
     * @param view vista grafica
     */

    public void verHorarioJuan(View view)
    {
        image_view1.setImageResource(R.drawable.horarioj);
        infoJuan.setText("3173979899");
    }

    /**
     * Horario de Villa
     * @param view vista grafica
     */
    public void verHorarioVilla(View view)
    {
        image_view2.setImageResource(R.drawable.horariov);
        infoVilla.setText("3177728793");
    }


    /**
     * Metodo para salir de la ventana AcercaDe
     * @param view vista grafica
     */
    public void salir (View view)
    {
        finish();
    }
}
