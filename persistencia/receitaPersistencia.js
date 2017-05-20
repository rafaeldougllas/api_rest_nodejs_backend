const Usuario = require('../models/usuarios');
const Receita = require('../models/receitas');

function ReceitaPersistencia(){

};

ReceitaPersistencia.prototype.getAll = async function(){
  //Traz todas
  var receitas = await Receita.find({});
  return receitas;
};


ReceitaPersistencia.prototype.novaReceita = async function(novaReceita){
  //Cria Nova Receita
  // 1. Procura pelo usuario que esta passando o id
    const usuario = await Usuario.findById(novaReceita.usuarioId);

    //2. Cria uma nova receita
    const receita      = new Receita(novaReceita);
    receita.usuario    = usuario; //Linka a receita com o usuario que a criou
    var receitaNovaObj = await receita.save();

    //3. Adiciona a receita ao array de receitas do usuarioId
    usuario.receitas.push(receita);
    await usuario.save();

    return receitaNovaObj;
};


ReceitaPersistencia.prototype.getReceita = async function(receitaId){
  //Carrega receita pelo Id
  var receitas = await Receita.findById(receitaId);
  return receitas;
};


ReceitaPersistencia.prototype.replaceReceitas = async function(receitaId, receita){
  //Atualiza Receita (Todos Campos)
  const resultado     = await Receita.findByIdAndUpdate(receitaId, receita);
  return resultado;
};


ReceitaPersistencia.prototype.updateReceitas = async function(receitaId, receita){
  //Atualiza Receita (Campos específicos)
  const resultado     = await Receita.findByIdAndUpdate(receitaId, receita);
  return resultado;
};


ReceitaPersistencia.prototype.deleteReceita = async function(receitaId){
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


module.exports = ReceitaPersistencia;
