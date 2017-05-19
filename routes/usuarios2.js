const express = require('express');
//const router  = express.Router();
const router    = require('express-promise-router')();
const mongoose = require('mongoose');

const { validateParam, validateBody, schemas } = require('../helpers/routerHelpers');

const Negocio = require('../negocios/usuarionegocio');
const usuarioNegocio = new Negocio();

router.route('/')
  .get(usuarioNegocio.getAll)
  .post(validateBody(schemas.usuariosSchema, 'usuarioId'), usuarioNegocio.novoUsuario);

module.exports = router;
