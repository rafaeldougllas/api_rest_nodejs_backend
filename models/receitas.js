const mongoose = require('mongoose');
const Schema   = mongoose.Schema;

const receitasSchema = new Schema({
  titulo: String,
  ingredientes: String,
  modoDePreparo: String,
  dataCriacao: String,
  custo: String,
  usuario: {
      type: Schema.Types.ObjectId,
      ref: 'usuario'
  }
});

const Receita = mongoose.model('receita', receitasSchema);
module.exports = Receita;
