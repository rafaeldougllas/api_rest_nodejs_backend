package franklincbc.com.receitas.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import franklincbc.com.receitas.R;
import franklincbc.com.receitas.models.Usuario;

/**
 * Created by frank on 18/05/2017.
 */

public class AdaptadorUsuario extends BaseAdapter {

    ArrayList<Usuario> lstUsuario;
    Activity act;

    public AdaptadorUsuario(Activity act, ArrayList<Usuario> lst){
        this.act = act;
        this.lstUsuario = lst;
    }

    @Override
    public int getCount() {
        return lstUsuario.size();
    }

    @Override
    public Object getItem(int position) {
        return lstUsuario.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.item_lista_usuario, parent, false);
        Usuario usuario = lstUsuario.get(position);

        TextView nome = (TextView) view.findViewById(R.id.item_lista_usuario_textView_nome);
        TextView data = (TextView) view.findViewById(R.id.item_lista_usuario_textView_data);
        TextView adm  = (TextView) view.findViewById(R.id.item_lista_usuario_textView_administrador);
        nome.setText(usuario.getNome());
        data.setText(usuario.getDataCriacao());
        if(usuario.getAdministrador()==1){
            adm.setText("Sim");
        }else{
            adm.setText("Não");
        }
        return view;
    }
}
