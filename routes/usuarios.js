const express = require('express');
//const router  = express.Router();
const router    = require('express-promise-router')();
const mongoose = require('mongoose');

const usuariosController = require('../controllers/usuarios');

const { validateParam, validateBody, schemas } = require('../helpers/routerHelpers');


router.route('/')
  .get(usuariosController.index)
  .post(validateBody(schemas.usuariosSchema, 'usuarioId'), usuariosController.novoUsuario);

router.route('/:usuarioId')
    //So executa a funçao usuariosController.getUsuario, se passar pela validação
    .get(validateParam(schemas.idSchema, 'usuarioId'),usuariosController.getUsuario)
    .put([validateParam(schemas.idSchema,'usuarioId'),
          validateBody(schemas.usuariosSchema)],
          usuariosController.replaceUsuario)
    .patch([validateParam(schemas.idSchema,'usuarioId'),
            validateBody(schemas.usuariosOpcionalSchema)],
            usuariosController.updateUsuario);

router.route('/:usuarioId/receitas')
    .get(validateParam(schemas.idSchema, 'usuarioId'),
         usuariosController.getReceitasDoUsuario)
    .post([validateParam(schemas.idSchema, 'usuarioId'),
           validateBody(schemas.usuarioReceitasSchema)],
           usuariosController.novaReceitaUsuario);



//A chamada acima é a mesma coisa que a abaixo
// router.get('/', (req, res, next) => {
// });
// router.post('/', (req, res, next) => {
// });

module.exports = router;
