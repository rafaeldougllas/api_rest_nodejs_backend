const express = require('express');
const router  = express.Router();
const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost/apireceitas');

const usuariosController = require('../controllers/usuarios');
router.route('/')
  .get(usuariosController.index)
  .post();

//A chamada acima é a mesma coisa que a abaixo
// router.get('/', (req, res, next) => {
// });
// router.post('/', (req, res, next) => {
// });

module.exports = router;
