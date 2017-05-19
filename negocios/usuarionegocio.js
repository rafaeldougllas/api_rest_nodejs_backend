const repositorio = require('../repositorio/usuariorepositorio');
const usuarioRepositorio = new repositorio();

function usuarioNegocio(){

};

usuarioNegocio.prototype.getAll = async (req, res, next) =>{
  try{
    const usuarios = await usuarioRepositorio.getAll();
    res.status(200).json(usuarios);
  }catch(err){
    next(err);
  }
};

usuarioNegocio.prototype.novoUsuario = async (req, res, next) =>{
  try{
    var body = req.value.body;
    const usuario = await usuarioRepositorio.novoUsuario(body);
    res.status(201).json(usuario);
  }catch(err){
    next(err);
  }
};

module.exports = usuarioNegocio;
