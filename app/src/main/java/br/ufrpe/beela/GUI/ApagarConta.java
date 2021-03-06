package br.ufrpe.beela.GUI;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.beela.GUI.R;
import br.ufrpe.beela.DAO.BDcomandosUsuario;
import br.ufrpe.beela.NEGOCIAÇAO.Usuario;

public class ApagarConta extends AppCompatActivity {
    private TextView textButton9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_conta);

        textButton9 = (TextView) findViewById(R.id.button9);
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        textButton9.setTypeface(fonte1);
        textButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarConta();
            }
        });
    }

    public void deletarConta() {
        BDcomandosUsuario bd = new BDcomandosUsuario(this,"W");
        bd.delete(Login.usuario);
        Toast Sucesso;
        Sucesso = Toast.makeText(getApplicationContext(), "Conta Deletada", Toast.LENGTH_SHORT);
        Sucesso.show();
        Usuario usuario = new Usuario();
        startActivity(new Intent(ApagarConta.this, Login.class));
    }
}
