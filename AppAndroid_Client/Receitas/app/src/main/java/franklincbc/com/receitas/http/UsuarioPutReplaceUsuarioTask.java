package franklincbc.com.receitas.http;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import franklincbc.com.receitas.models.Usuario;

/**
 * Created by frank on 19/05/2017.
 */

public class UsuarioPutReplaceUsuarioTask extends AsyncTaskLoader<Boolean> {

    private Usuario usuario = null;
    Context ctx;

    public UsuarioPutReplaceUsuarioTask(Context context, Integer loaderID, Bundle params) {
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
    public Boolean loadInBackground() {
        boolean retorno = false;
        try {
            retorno = UsuarioHttp.ReplaceUsuario_PUT(usuario);
        }catch (Exception e){
            e.printStackTrace();
        }
        return retorno;
    }

}
