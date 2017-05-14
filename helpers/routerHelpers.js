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

   schemas: {
       //Verifica se o id passado possui os padrões do mongodb: 
       //24 caracteres entre números e letras maiusculas e minusculas
       idSchema: Joi.object().keys({
         param: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required()
       })
   }
}
