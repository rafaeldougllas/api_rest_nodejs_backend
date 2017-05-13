const express = require('express');
//const router  = express.Router();
const router    = require('express-promise-router')();
const mongoose = require('mongoose');


const usuariosController = require('../controllers/usuarios');
router.route('/')
  .get(usuariosController.index)
  .post(usuariosController.novoUsuario);

router.route('/:usuarioId')
    .get(usuariosController.getUsuario)
    .put(usuariosController.replaceUsuario)
    .patch(usuariosController.updateUsuario);

router.route('/:usuarioId/receitas')
    .get(usuariosController.getReceitasDoUsuario)
    .post(usuariosController.novaReceitaUsuario);



//A chamada acima é a mesma coisa que a abaixo
// router.get('/', (req, res, next) => {
// });
// router.post('/', (req, res, next) => {
// });

module.exports = router;
