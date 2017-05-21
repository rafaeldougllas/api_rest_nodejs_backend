package franklincbc.com.receitas.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frank on 18/05/2017.
 */

public class Usuario implements Serializable {
    String id;
    String nome;
    String dataCriacao;
    Integer administrador;
    ArrayList<Receita> lstReceitas;

    public Usuario(){
        this.nome = "";
        this.dataCriacao = "";
        this.administrador = 0;
        this.lstReceitas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Integer administrador) {
        this.administrador = administrador;
    }

    public List<Receita> getLstReceitas() {
        return lstReceitas;
    }

    public void setLstReceitas(ArrayList<Receita> lstReceitas) {
        this.lstReceitas.addAll(lstReceitas);
    }

}
