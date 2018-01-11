package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;

public class ApagarContaAct extends AppCompatActivity {
    private Button botaoApagar;
    private TextView aviso1, aviso2;
    private Toast mensagemApagada;
    private Usuario usuario= LoginAct.getUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_conta);

        botaoApagar = (Button) findViewById(R.id.button9);
        alterarFonte();
        clicarBotaoApagar();

    }

    private void alterarFonte(){
        aviso1 = (TextView) findViewById(R.id.textView5);
        aviso2 = (TextView) findViewById(R.id.textView6);
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        botaoApagar.setTypeface(fonte);
        aviso1.setTypeface(fonte);
        aviso2.setTypeface(fonte);
    }

    private void clicarBotaoApagar(){
        botaoApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarConta();
            }
        });
    }

    public void deletarConta() {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(this);
        bd.delete(usuario);
        PerfilDAO bd1 = new PerfilDAO();
        bd1.getEscrever(this);
        bd1.deletePerfisUsuario(usuario.getId());
        mensagemApagada = Toast.makeText(getApplicationContext(), R.string.contaDeletada, Toast.LENGTH_SHORT);
        mensagemApagada.show();

        //TODO    Precisa desses objetos? (By: Anderson)
//        Usuario usuario = new Usuario();
//        Pessoa pessoa = new Pessoa();

        startActivity(new Intent(ApagarContaAct.this, LoginAct.class));
    }
}
