package franklincbc.com.receitas.http;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import franklincbc.com.receitas.models.Receita;

/**
 * Created by frank on 20/05/2017.
 */

public class UsuarioReceitasGetAllTask extends AsyncTaskLoader<List<Receita>> {

    private String usuarioId;
    Context ctx;

    public UsuarioReceitasGetAllTask(Context context, Integer loaderID, Bundle params) {
        super(context);
        this.ctx = context;
        this.usuarioId = (String) params.getString("usuarioId");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Receita> loadInBackground() {
        List<Receita> lstReceitas = null;
        try {
            lstReceitas = UsuarioHttp.UsuariosReceitasGetAll(usuarioId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return lstReceitas;
    }

}
