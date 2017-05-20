const express = require('express');
//const router  = express.Router();
const router    = require('express-promise-router')();
const mongoose = require('mongoose');

const { validateParam, validateBody, schemas } = require('../controller/controller');

const Negocio = require('../negocios/usuarionegocio');
const usuarioNegocio = new Negocio();

router.route('/')
  .get(usuarioNegocio.getAll)
  .post(validateBody(schemas.usuariosSchema, 'usuarioId'), usuarioNegocio.novoUsuario);

router.route('/:usuarioId')
      //So executa a funçao usuariosNegocio.getUsuario, se passar pela validação
      .get(validateParam(schemas.idSchema, 'usuarioId'),usuarioNegocio.getUsuario)
      .put([validateParam(schemas.idSchema,'usuarioId'),
            validateBody(schemas.usuariosSchema)],
            usuarioNegocio.replaceUsuario)
      .patch([validateParam(schemas.idSchema,'usuarioId'),
              validateBody(schemas.usuariosOpcionalSchema)],
              usuarioNegocio.updateUsuario);


router.route('/:usuarioId/receitas')
  .get(validateParam(schemas.idSchema, 'usuarioId'),
                     usuarioNegocio.getReceitasDoUsuario)
  .post([validateParam(schemas.idSchema, 'usuarioId'),
           validateBody(schemas.usuarioReceitasSchema)],
                         usuarioNegocio.novaReceitaUsuario);  


module.exports = router;
