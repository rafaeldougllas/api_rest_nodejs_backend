const Usuario = require('../models/usuarios');
const Receita = require('../models/receitas');

function UsuarioPersistencia(){

};



UsuarioPersistencia.prototype.getAll = async function(){
  //Traz Todos
  var usuarios =  await Usuario.find({});
  return usuarios;
};




UsuarioPersistencia.prototype.novoUsuario = async function(usuario){
  //Cria Novo Usuario
  const novoUsuario = new Usuario(usuario);
  var usuario = await novoUsuario.save();
  return usuario;
};




UsuarioPersistencia.prototype.getUsuario = async function(usuarioId){
  //Carrega usuário pelo Id
  var usuario = await Usuario.findById(usuarioId);
  return usuario;
};




UsuarioPersistencia.prototype.replaceUsuario = async function(usuarioId, usuario){
  //Atualiza Usuario (Todos Campos)
  const resultado     = await Usuario.findByIdAndUpdate(usuarioId, usuario);
  return resultado;
};




UsuarioPersistencia.prototype.updateUsuario = async function(usuarioId, usuario){
  //Atualiza Usuario (Campos específicos)
  const resultado     = await Usuario.findByIdAndUpdate(usuarioId, usuario);
  return resultado;
};




UsuarioPersistencia.prototype.getReceitasDoUsuario = async function(usuarioId){
  //Carrega Receitas do Usuario
  var usuario = await Usuario.findById(usuarioId).populate('receitas');
  return usuario;
};




UsuarioPersistencia.prototype.novaReceitaUsuario = async function(usuarioId, novaReceita){
  //Cria uma nova Receita
  var novaReceita   = new Receita(novaReceita);
  //Recupera o usuario
  var usuario       = await Usuario.findById(usuarioId);
  //Assina a receita com o usuario que criou
  novaReceita.usuario = usuario;
  //Salva a receita
  await novaReceita.save();
  //Adiciona a receita ao array de receitas no usuario
  usuario.receitas.push(novaReceita);
  //Salva a receita no usuario
  await usuario.save();//Salva Nova Receita do Usuario

  return novaReceita;
};




module.exports = UsuarioPersistencia;
