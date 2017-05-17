const Receita = require('../models/receitas');
const Usuario = require('../models/usuarios');


module.exports = {
  index: async (req, res, next) => {
    //Retorna todas as receitas
    const receitas = await Receita.find({});
    res.status(200).json(receitas);
  },

  novaReceita: async (req, res, next) => {
    // 1. Procura pelo usuario que esta passando o id
    const usuario = await Usuario.findById(req.value.body.usuarioId);

    //2. Cria uma nova receita
    const novaReceita = req.value.body;
    const receita     = new Receita(novaReceita);
    receita.usuario   = usuario; //Linka a receita com o usuario que a criou
    await receita.save();

    //3. Adiciona a receita ao array de receitas do usuarioId
    usuario.receitas.push(receita);
    await usuario.save();

    //Envia o json de confirmaçao
    res.status(200).json(receita);

  },

  getReceita: async(req, res, next) => {
    const receitas = await Receita.findById(req.value.params.receitaId);
    res.status(200).json(receitas);
  },

  replaceReceitas: async(req, res, next) => {
    const { receitaId } = req.value.params;
    const novaReceita   = req.value.body;
    const resultado     = await Receita.findByIdAndUpdate(receitaId,novaReceita);
    res.status(200).json({success:true});
  },

  updateReceitas: async(req, res, next) => {
    const { receitaId } = req.value.params;
    const novaReceita   = req.value.body;
    const resultado     = await Receita.findByIdAndUpdate(receitaId,novaReceita);
    res.status(200).json({success:true});
  }
}
