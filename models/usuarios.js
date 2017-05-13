const mongoose = require('mongoose');
const Schema   = mongoose.Schema;

const usuariosSchema = new Schema({
  nome: String,
  dataCriacao: String,
  administrador: Number,
  receitas: [{
      type: Schema.Types.ObjectId,
      ref: 'receita'
  }]
});

const Usuario = mongoose.model('usuario', usuariosSchema);
module.exports = Usuario;
