package br.rodriguesufrpe.anderson.beela;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlterarSenha extends AppCompatActivity {

    private TextView senhaAtualText7;
    private Button alterarSenhaButton10;

    private String senhaAtual, novaSenha, repetirSenha;
    private EditText senhaEditText7, novaSenhaEditText8, repetirSenhaEditText9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        senhaEditText7=(EditText)findViewById(R.id.editText7);
        novaSenhaEditText8=(EditText)findViewById(R.id.editText8);
        repetirSenhaEditText9=(EditText)findViewById(R.id.editText9);


//---------------------------------Alterar Fonte----------------------------------------------------
        Typeface fonte1 = Typeface.createFromAsset(getAssets(), "fonts/Chewy.ttf");
        senhaAtualText7 = (TextView) findViewById(R.id.editText7);
        senhaAtualText7.setTypeface(fonte1);
//--------------------------------------------------------------------------------------------------
        //----------------------------Validacao do clique do botao Alterar Senha----------------------------
        alterarSenhaButton10 = (Button) findViewById(R.id.button10);
        alterarSenhaButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCliqueAlterarSenha();
            }
        });
    }

    //----------------------------------Validacao dos campos----------------------------------------

    public void validarCliqueAlterarSenha(){
        senhaAtual=senhaEditText7.getText().toString().trim();
        novaSenha=novaSenhaEditText8.getText().toString().trim();
        repetirSenha=repetirSenhaEditText9.getText().toString().trim();

        if(ehValidoAlterarSenha()){
            alterarSucessoSenha();
        }
    }

    public void alterarSucessoSenha(){
        // TODO what will go after the valid  input
    }

    public boolean ehValidoAlterarSenha(){
        boolean valido=true;
        if(senhaAtual.isEmpty()){
            senhaEditText7.setError(getString(R.string.campoVazio));
            valido=false;
        }

        if(novaSenha.isEmpty()){
            novaSenhaEditText8.setError(getString(R.string.campoVazio));
            valido=false;
        }

        if(!repetirSenha.equals(novaSenha)){
            repetirSenhaEditText9.setError(getString(R.string.senhasDiferentes));
            valido=false;
        }

        return valido;
    }
//---------------------------------------------------------------------------------------------

}
