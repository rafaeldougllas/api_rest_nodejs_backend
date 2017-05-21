package franklincbc.com.receitas.http;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import franklincbc.com.receitas.models.Usuario;

/**
 * Created by frank on 18/05/2017.
 */

public class UsuarioPostNovoUsuarioTask extends AsyncTaskLoader<Usuario> {

    private Usuario usuario = null;
    Context ctx;

    public UsuarioPostNovoUsuarioTask(Context context, Integer loaderID, Bundle params) {
        super(context);
        this.ctx = context;
        this.usuario = (Usuario) params.getSerializable("usuario");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Usuario loadInBackground() {
        try {
            usuario = UsuarioHttp.NovoUsuario_Post(usuario);
            if (usuario == null) {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return usuario;
    }

}
