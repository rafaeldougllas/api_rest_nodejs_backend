const Usuario = require('../models/usuarios');
const Receita = require('../models/receitas');

function ReceitaRepositorio(){

};

ReceitaRepositorio.prototype.getAll = async function(){
  //Traz todas
  var receitas = await Receita.find({});
  return receitas;
};


ReceitaRepositorio.prototype.novaReceita = async function(){
  //Cria Nova Receita

};


ReceitaRepositorio.prototype.getReceita = async function(receitaId){
  //Carrega receita pelo Id
  var receitas = await Receita.findById(receitaId);
  return receitas;
};


ReceitaRepositorio.prototype.replaceReceitas = async function(receitaId, receita){
  //Atualiza Receita (Todos Campos)
  const resultado     = await Receita.findByIdAndUpdate(receitaId, receita);
  return resultado;
};


ReceitaRepositorio.prototype.updateReceitas = async function(receitaId, receita){
  //Atualiza Receita (Campos específicos)
  const resultado     = await Receita.findByIdAndUpdate(receitaId, receita);
  return resultado;
};


ReceitaRepositorio.prototype.deleteReceita = async function(receitaId){
  //Apaga a Receita e relação com Usuario
  //recupera a receita
  var receita = await Receita.findById(receitaId);
  if(!receita){
    return false;
  }

  var usuarioId = receita.usuario;
  var usuario   = await Usuario.findById(usuarioId);

  //Deleta a receita
  await receita.remove();

  //Retira essa receita do seu criador
  usuario.receitas.pull(receita);
  await usuario.save();
  return true;

};


module.exports = ReceitaRepositorio;
