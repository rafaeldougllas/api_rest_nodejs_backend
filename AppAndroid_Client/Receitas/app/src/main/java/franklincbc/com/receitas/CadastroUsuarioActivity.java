package franklincbc.com.receitas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import franklincbc.com.receitas.Adaptador.AdaptadorReceita;
import franklincbc.com.receitas.Adaptador.AdaptadorUsuario;
import franklincbc.com.receitas.http.ReceitaDeleteTask;
import franklincbc.com.receitas.http.UsuarioPostNovoUsuarioTask;
import franklincbc.com.receitas.http.UsuarioPutReplaceUsuarioTask;
import franklincbc.com.receitas.http.UsuarioReceitasGetAllTask;
import franklincbc.com.receitas.models.Receita;
import franklincbc.com.receitas.models.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    EditText edtNome;
    CheckBox chkAdministrador;
    Usuario mUsuario = null;
    Receita receita = null;

    ListView listViewReceitas;
    ArrayList<Receita> mLstReceitas;
    AdaptadorReceita mAdapter;

    LoaderManager mLoaderManager;
    public static final int LOADER_NOVO_USUARIO = 0;
    public static final int LOADER_REPLACE_USUARIO = 1;
    public static final int LOADER_DELETE_RECEITA = 2;
    public static final int LOADER_USUARIO_RECEITA_GET_ALL = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        edtNome          = (EditText)findViewById(R.id.cadastro_usuario_edt_nome);
        chkAdministrador = (CheckBox) findViewById(R.id.cadastro_usuario_chk_administrador);
        listViewReceitas = (ListView)findViewById(R.id.cadastro_usuario_lst_receitas);

        mLstReceitas = new ArrayList<Receita>();
        mAdapter = new AdaptadorReceita(this, mLstReceitas);
        listViewReceitas.setAdapter(mAdapter);
        listViewReceitas.setOnItemClickListener(onItemClickListener);
        listViewReceitas.setOnItemLongClickListener(onItemLongClickListener);

        Intent it = getIntent();
        if(it.getSerializableExtra("usuario")!=null){
            mUsuario =  (Usuario) it.getSerializableExtra("usuario");
            edtNome.setText(mUsuario.getNome());
            if(mUsuario.getAdministrador()== 1){
                chkAdministrador.setChecked(true);
            }else{
                chkAdministrador.setChecked(false);
            }

            //Carrega na lista as Receitas do usuário
            PreencheListViewReceitas();

        }

        mLoaderManager = getSupportLoaderManager();



    }

    public void PreencheListViewReceitas(){
        //Carrega na lista as Receitas do usuário
        for (int i = 0; i < mUsuario.getLstReceitas().size(); i++){
            Receita receita = mUsuario.getLstReceitas().get(i);
            mLstReceitas.add(receita);
        }
        mAdapter.notifyDataSetChanged();
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            receita = (Receita) mLstReceitas.get(position);
            //Intent it = new Intent(CadastroUsuarioActivity.this, "".class);
            //it.putExtra("receita", receita);
            //startActivity(it);

        }
    };

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            receita = (Receita) mLstReceitas.get(position);
            alerta_ApagarReceita();
            return false;
        }
    };

    private void alerta_ApagarReceita() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//Cria o gerador do AlertDialog
        builder.setTitle("Atenção");//define o titulo
        builder.setMessage("Deseja apagar esta receita?");

        //define um botão como positivo
        builder.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Bundle args = new Bundle();
                args.putString("receitaId",receita.getId());
                mLoaderManager.restartLoader(LOADER_DELETE_RECEITA, args, CadastroUsuarioActivity.this);
            }
        });

        //Usa  o botao de negative
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        AlertDialog alerta = builder.create();//cria o AlertDialog
        alerta.show();//Exibe
    }


    public void cadastro_usuario_btn_salvarOnClick(View view) {
        if(mUsuario==null||mUsuario.getId()==null||mUsuario.getId()==""){
            mUsuario = null;
            mUsuario = new Usuario();
           //Inserir um novo
            mUsuario.setNome(edtNome.getText().toString());
            if (chkAdministrador.isChecked()){
                mUsuario.setAdministrador(1);
            } else {
                mUsuario.setAdministrador(2);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = sdf.format(new Date());
            mUsuario.setDataCriacao(currentDate);
            Bundle args = new Bundle();
            mLoaderManager.restartLoader(LOADER_NOVO_USUARIO, args, CadastroUsuarioActivity.this);

        }
        else
        {
            //Alterar
            mUsuario.setNome(edtNome.getText().toString());
            if (chkAdministrador.isChecked()){
                mUsuario.setAdministrador(1);
            } else {
                mUsuario.setAdministrador(2);
            }
            Bundle args = new Bundle();
            mLoaderManager.restartLoader(LOADER_REPLACE_USUARIO, args, CadastroUsuarioActivity.this);
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if (id == LOADER_NOVO_USUARIO){
            args.putSerializable("usuario", mUsuario );
            return new UsuarioPostNovoUsuarioTask(CadastroUsuarioActivity.this, id, args);
        }else if(id == LOADER_REPLACE_USUARIO){
            args.putSerializable("usuario", mUsuario );
            return new UsuarioPutReplaceUsuarioTask(CadastroUsuarioActivity.this, id, args);
        }else if(id == LOADER_DELETE_RECEITA){
            return new ReceitaDeleteTask(CadastroUsuarioActivity.this, id, args);
        } else if(id == LOADER_USUARIO_RECEITA_GET_ALL){
            return new UsuarioReceitasGetAllTask(CadastroUsuarioActivity.this, id, args);
        } else {
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (loader.getId() == LOADER_NOVO_USUARIO) {
            if(data != null){
                Usuario usuario = (Usuario) data;
                finish();
            }
            else
            {
                mUsuario = null;
                Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_LONG).show();
            }
        }

        else if (loader.getId() == LOADER_REPLACE_USUARIO) {
            if(data != null){
                boolean retorno = (boolean) data;
                if(retorno){
                    Toast.makeText(this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Usuario não atualizado", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Erro ao atualizar", Toast.LENGTH_LONG).show();
            }
        }

        else if (loader.getId() == LOADER_DELETE_RECEITA) {
            if(data != null){
                boolean retorno = (boolean) data;
                if(retorno){
                    Toast.makeText(this, "Receita apagada com sucesso", Toast.LENGTH_SHORT).show();
                    Bundle args = new Bundle();
                    args.putString("usuarioId", mUsuario.getId());
                    mLoaderManager.restartLoader(LOADER_USUARIO_RECEITA_GET_ALL, args, CadastroUsuarioActivity.this);
                } else {
                    Toast.makeText(this, "Receita não deletada", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Erro ao deletar", Toast.LENGTH_LONG).show();
            }
        }

        else if (loader.getId() == LOADER_USUARIO_RECEITA_GET_ALL) {
            if(data != null){
                ArrayList<Receita> lstReceitas = (ArrayList<Receita>) data;
                mLstReceitas.clear();
                if(lstReceitas.size()>0){
                    mUsuario.getLstReceitas().clear();
                    mUsuario.setLstReceitas(lstReceitas);
                    PreencheListViewReceitas();
                } else {
                    //
                }
            }
            else
            {
                //
            }
        }



    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    public void cadastro_usuario_fabNovaReceitaOnClick(View view) {
        //CHAMAR TELA DE CADASTRO RECEITA
    }
}
