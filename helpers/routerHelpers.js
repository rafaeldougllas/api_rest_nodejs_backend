const Joi = require('joi');

module.exports = {
   validateParam: (schema, name) =>{
      return (req, res, next) => {
        const result = Joi.validate({ param: req['params'][name]}, schema);
        if(result.error){
          //Aconteceu um erro
          return res.status(400).json(result.error);
        }else{
          if(!req.value){
             req.value = {};
          }
          if(!req.value['params']){
              req.value['params'] = {};
          }
          req.value['params'][name] = result.value.param;
          next();
        }
      }
   },

   validateBody: (schema) => {
     return (req, res, next) => {
       const result = Joi.validate(req.body, schema);

       if(result.error){
         return res.status(400).json(result.error);
       }else{
         if(!req.value){
           req.value = {};
         }
         if(!req.value['body']){
           req.value['body'] = {};
         }

         req.value['body'] = result.value;
         next();
       }
     }
   },

   schemas: {
       //(PUT)Usado para atualizacao de todos os campos
       usuariosSchema:  Joi.object().keys({
         nome: Joi.string().required(),
         dataCriacao: Joi.string().required(),
         administrador: Joi.number().required()
       }),
       //(PATCH)Usado para atualização de alguns campos
       usuariosOpcionalSchema: Joi.object().keys({
         nome: Joi.string(),
         dataCriacao: Joi.string(),
         administrador: Joi.number()
       }),

       receitasSchema: Joi.object().keys({
         titulo: Joi.string().required(),
         ingredientes: Joi.string().required(),
         modoDePreparo: Joi.string().required(),
         dataCriacao: Joi.string().required(),
         custo: Joi.string().required()
       }),

       //Verifica se o id passado possui os padrões do mongodb:
       //24 caracteres entre números e letras maiusculas e minusculas
       idSchema: Joi.object().keys({
         param: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required()
       })
   }
}
