package franklincbc.com.receitas;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import franklincbc.com.receitas.Adaptador.AdaptadorUsuario;
import franklincbc.com.receitas.http.UsuarioGetAllTask;
import franklincbc.com.receitas.models.Usuario;

public class MainActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks{

    LoaderManager mLoaderManager;
    public static final int LOADER_USUARIOS_GETALL = 0;

    ListView listViewUsuario;
    ArrayList<Usuario> mLstUsuarios;
    AdaptadorUsuario mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de Usuários");
        listViewUsuario = (ListView)findViewById(R.id.main_lstUsuario);
        listViewUsuario.setOnItemClickListener(onItemClickListener);

        mLoaderManager = getSupportLoaderManager();

        Bundle args = new Bundle();
        mLoaderManager.initLoader(LOADER_USUARIOS_GETALL, args, MainActivity.this);
        mLstUsuarios = new ArrayList<Usuario>();
        mAdapter = new AdaptadorUsuario(this, mLstUsuarios);
        listViewUsuario.setAdapter(mAdapter);

    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Usuario usuario = mLstUsuarios.get(position);
            Intent it = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
            it.putExtra("usuario", usuario);
            startActivity(it);

        }
    };

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if (id == LOADER_USUARIOS_GETALL){
            return new UsuarioGetAllTask(MainActivity.this, id, args);
        }else {
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

        if (loader.getId() == LOADER_USUARIOS_GETALL) {
            if(data != null){
                ArrayList<Usuario> lstUsuario = (ArrayList<Usuario>) data;
                if (lstUsuario.size()>0){
                    mLstUsuarios.clear();
                    mLstUsuarios.addAll(lstUsuario);
                    mAdapter.notifyDataSetChanged();
                }
            }
            else
            {
                Toast.makeText(this, "Não foi encontrado nenhum usuário cadastrado", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    public void main_fabNovoUsuarioOnClick(View view) {
        Intent it = new Intent(this, CadastroUsuarioActivity.class);
        startActivity(it);
    }
}
