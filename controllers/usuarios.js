const Usuarios = require('../models/usuarios');


//Exporta por meio de funçoes
module.exports = {
  index: (req, res, next) => {
      Usuarios.find({}, (err, usuarios) =>{
          if(err){
            next(err);
          }

          res.status(200).json(usuarios);
      })
      /*
      res.status(200).json({
        message: 'Você requisitou a página inicial'
      });*/
  }
};

/*
  Nós podemos interagir com o mongoose em 3 maneiras diferentes
  1) Callbacks
  2) Promises
  3)Async/Await
*/
