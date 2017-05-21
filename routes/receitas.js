const router = require('express-promise-router')();

const Negocio = require('../negocios/receitaNegocio');
const receitaNegocio = new Negocio();

const {
  validateBody,
  validateParam,
  schemas
} = require('../controller/controller');

router.route('/')
  .get(receitaNegocio.getAll)
  .post(validateBody(schemas.receitasSchema),
        receitaNegocio.novaReceita);

router.route('/:receitaId')
  .get(validateParam(schemas.idSchema,'receitaId'),
       receitaNegocio.getReceita)
  .put([validateParam(schemas.idSchema,'receitaId'),
       validateBody(schemas.putReceitasSchema)],
       receitaNegocio.replaceReceitas)
  .patch([validateParam(schemas.idSchema,'receitaId'),
        validateBody(schemas.patchReceitasSchema)],
        receitaNegocio.updateReceitas)
  .delete(validateParam(schemas.idSchema,'receitaId'),
          receitaNegocio.deleteReceita);

module.exports = router;
