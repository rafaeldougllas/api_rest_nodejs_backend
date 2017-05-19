const repositorio = require('../repositorio/usuariorepositorio');
const usuarioRepositorio = new repositorio();

function usuarioNegocio(){

};



usuarioNegocio.prototype.getAll = async (req, res, next) =>{
  try{
    console.log('Call getAll');
    const usuarios = await usuarioRepositorio.getAll();
    res.status(200).json(usuarios);
  }catch(err){
    next(err);
  }
};


usuarioNegocio.prototype.novoUsuario = async (req, res, next) =>{
  try{
    console.log('novoUsuario');
    var body = req.value.body;
    const usuario = await usuarioRepositorio.novoUsuario(body);
    res.status(201).json(usuario);
  }catch(err){
    next(err);
  }
};



usuarioNegocio.prototype.getUsuario = async (req, res, next) =>{
  try{
    console.log('getUsuario');
    var { usuarioId } = req.value.params;
    const usuario = await usuarioRepositorio.getUsuario(usuarioId);
    res.status(200).json(usuario);
  }catch(err){
    next(err);
  }
};




usuarioNegocio.prototype.replaceUsuario = async (req, res, next) =>{
  try{
    console.log('replaceUsuario');
    var { usuarioId } = req.value.params;
    const novoUsuario   = req.value.body;

    const usuario = await usuarioRepositorio.replaceUsuario(usuarioId, novoUsuario);
    res.status(200).json({success: true});

  }catch(err){
    next(err);
  }
};




usuarioNegocio.prototype.updateUsuario = async (req, res, next) =>{
  try{
    console.log('updateUsuario');
    var { usuarioId } = req.value.params;
    const novoUsuario   = req.value.body;

    const usuario = await usuarioRepositorio.updateUsuario(usuarioId, novoUsuario);
    res.status(200).json({success: true});

  }catch(err){
    next(err);
  }
};




usuarioNegocio.prototype.getReceitasDoUsuario = async (req, res, next) =>{
  try{
    console.log('getReceitasDoUsuario');
    var { usuarioId } = req.value.params;
    const usuario = await usuarioRepositorio.getReceitasDoUsuario(usuarioId);
    res.status(200).json(usuario.receitas);
  }catch(err){
    next(err);
  }
};




usuarioNegocio.prototype.novaReceitaUsuario = async (req, res, next) =>{
  try{
    console.log('novaReceitaUsuario');
    //Id do usuario enviado na url
    var { usuarioId } = req.value.params;
    var receita       = req.value.body;
    var receita = await usuarioRepositorio.novaReceitaUsuario(usuarioId, receita);
    res.status(200).json(receita);
  }catch(err){
    next(err);
  }
};


module.exports = usuarioNegocio;
