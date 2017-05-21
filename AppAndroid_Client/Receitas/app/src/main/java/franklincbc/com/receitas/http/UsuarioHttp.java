package franklincbc.com.receitas.http;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import franklincbc.com.receitas.models.Receita;
import franklincbc.com.receitas.models.Usuario;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by frank on 18/05/2017.
 */

class  UsuarioHttp {

    public static final String URL_BASE = "http://192.168.25.105:3000";
    public static final String URL_USUARIO_GET_ALL        = URL_BASE + "/usuarios2/";
    public static final String URL_NOVO_USUARIO_POST      = URL_BASE + "/usuarios2/";
    public static final String URL_REPLACE_USUARIO_PUT     = URL_BASE + "/usuarios2/%s/";
    public static final String URL_USUARIO_RECEITAS_GET_ALL     = URL_BASE + "/usuarios2/%s/receitas";


    private static OkHttpClient newClientOkHttp() {
        try {
            OkHttpClient.Builder b = new OkHttpClient.Builder();
            b.readTimeout(10000, TimeUnit.MILLISECONDS);
            b.writeTimeout(10000, TimeUnit.MILLISECONDS);
            return b.build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Usuario> UsuariosGetAll(){

        ArrayList<Usuario> lstUsuarios = new ArrayList<>();

        // Abre a conexão com o servidor
        OkHttpClient client = newClientOkHttp();

        //String url = String.format(URL_USUARIO_GETALL);
        Request request = new Request.Builder()
                .url(URL_USUARIO_GET_ALL)
                .build();

        Response response = null;
        try {
            // Realiza a chamada ao servidor
            response = client.newCall(request).execute();
            // response.body retorna o corpo da resposta, que no nosso caso é JSON
            String json = response.body().string();
            //JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = new JSONArray(json);
            //Percorre a Lista de objetos
            for (int i = 0; i < jsonArray.length(); i++) {
                //Ler o objecto JSON
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario usuario = new Usuario();
                usuario.setId(jsonObject.getString("_id"));
                usuario.setNome(jsonObject.getString("nome"));
                usuario.setDataCriacao(jsonObject.getString("dataCriacao"));
                usuario.setAdministrador(jsonObject.getInt("administrador"));

                //Carrega as Receitas
                JSONArray jsonArrayReceitas = jsonArray.getJSONObject(i).getJSONArray("receitas");
                ArrayList<Receita> lstReceitas = new ArrayList<>();
                for (int j = 0; j < jsonArrayReceitas.length(); j++){
                    JSONObject jsonObjectReceita = jsonArrayReceitas.getJSONObject(j);
                    Receita receitas = new Receita();
                    receitas.setId(jsonObjectReceita.getString("_id"));
                    receitas.setTitulo(jsonObjectReceita.getString("titulo"));
                    receitas.setIngredientes(jsonObjectReceita.getString("ingredientes"));
                    receitas.setModoDePreparo(jsonObjectReceita.getString("modoDePreparo"));
                    receitas.setDataCriacao(jsonObjectReceita.getString("dataCriacao"));
                    receitas.setCusto(jsonObjectReceita.getString("custo"));
                    lstReceitas.add(receitas);
                }
                usuario.setLstReceitas(lstReceitas);

                lstUsuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstUsuarios;

    };

    public static Usuario NovoUsuario_Post(Usuario usuario) throws JSONException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Abre a conexão com o servidor
        OkHttpClient client = newClientOkHttp();

        //Monta o Json
        JSONObject json = new JSONObject();
        json.put("nome", usuario.getNome());
        json.put("dataCriacao", usuario.getDataCriacao());
        json.put("administrador", usuario.getAdministrador());

        String jsonString = json.toString();
        RequestBody body = RequestBody.create(JSON, jsonString);

        //String url = String.format(URL_USUARIO_GETALL);
        Request request = new Request.Builder()
                .url(URL_NOVO_USUARIO_POST)
                .post(body)
                .build();

        Response response = null;
        try {
            //Realiza a chamada ao servidor
            response = client.newCall(request).execute();
            //response.body retorna o corpo da resposta, que no nosso caso é JSON
            String jsonRetorno = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonRetorno);

            Usuario usuarioRetorno = new Usuario();
            usuarioRetorno.setId(jsonObject.getString("_id"));
            usuarioRetorno.setNome(jsonObject.getString("nome"));
            usuarioRetorno.setDataCriacao(jsonObject.getString("dataCriacao"));
            usuarioRetorno.setAdministrador(jsonObject.getInt("administrador"));

            return usuarioRetorno;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    };

    public static boolean ReplaceUsuario_PUT(Usuario usuario) throws JSONException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Abre a conexão com o servidor
        OkHttpClient client = newClientOkHttp();

        //Monta o Json
        JSONObject json = new JSONObject();
        json.put("nome", usuario.getNome());
        json.put("dataCriacao", usuario.getDataCriacao());
        json.put("administrador", usuario.getAdministrador());

        String jsonString = json.toString();
        RequestBody body = RequestBody.create(JSON, jsonString);

        String url = String.format(URL_REPLACE_USUARIO_PUT, usuario.getId());

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = null;
        try {
            //Realiza a chamada ao servidor
            response = client.newCall(request).execute();
            //response.body retorna o corpo da resposta, que no nosso caso é JSON
            String jsonRetorno = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonRetorno);
            boolean retorno = jsonObject.getBoolean("success");

            return retorno;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    };


    public static ArrayList<Receita> UsuariosReceitasGetAll(String usuarioId){

        ArrayList<Receita> lstReceitas = new ArrayList<>();

        // Abre a conexão com o servidor
        OkHttpClient client = newClientOkHttp();

        String url = String.format(URL_USUARIO_RECEITAS_GET_ALL, usuarioId);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            // Realiza a chamada ao servidor
            response = client.newCall(request).execute();
            // response.body retorna o corpo da resposta, que no nosso caso é JSON
            String json = response.body().string();
            //JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = new JSONArray(json);
            //Percorre a Lista de objetos
            for (int i = 0; i < jsonArray.length(); i++) {
                //Ler o objecto JSON
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Receita receitas = new Receita();
                receitas.setId(jsonObject.getString("_id"));
                receitas.setTitulo(jsonObject.getString("titulo"));
                receitas.setIngredientes(jsonObject.getString("ingredientes"));
                receitas.setModoDePreparo(jsonObject.getString("modoDePreparo"));
                receitas.setDataCriacao(jsonObject.getString("dataCriacao"));
                receitas.setCusto(jsonObject.getString("custo"));
                lstReceitas.add(receitas);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstReceitas;

    };


}