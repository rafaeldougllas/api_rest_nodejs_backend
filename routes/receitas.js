const router = require('express-promise-router')();

const ReceitasController = new require('../controllers/receitas');

const {
  validateBody,
  validateParam,
  schemas
} = require('../helpers/routerHelpers');

router.route('/')
  .get(ReceitasController.index)
  .post(validateBody(schemas.receitasSchema),
        ReceitasController.novaReceita);

router.route('/:receitaId')
  .get(validateParam(schemas.idSchema,'receitaId'),
       ReceitasController.getReceita)
  .put([validateParam(schemas.idSchema,'receitaId'),
       validateBody(schemas.putReceitasSchema)],
       ReceitasController.replaceReceitas)
  .patch([validateParam(schemas.idSchema,'receitaId'),
        validateBody(schemas.patchReceitasSchema)],
        ReceitasController.updateReceitas)
  .delete(validateParam(schemas.idSchema,'receitaId'),
          ReceitasController.deleteReceita);

module.exports = router;
