const Usuario = require('../models/usuarios');
const Receita = require('../models/receitas');

function UsuarioRepositorio(){

};

UsuarioRepositorio.prototype.getAll = async function(){
  //Traz Todos
  var usuarios =  await Usuario.find({});
  return usuarios;
};

UsuarioRepositorio.prototype.novoUsuario = async function(usuario){
  //Cria Novo Usuario
  const novoUsuario = new Usuario(usuario);
  console.log(novoUsuario);
  var usuario = await novoUsuario.save();
  return usuario;
};

module.exports = UsuarioRepositorio;
