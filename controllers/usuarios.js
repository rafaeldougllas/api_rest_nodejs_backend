const Usuario = require('../models/usuarios');


//Exporta por meio de funçoes
module.exports = {
  //formato de Callbacks
  // index: (req, res, next) => {
  //     Usuario.find({}, (err, usuarios) =>{
  //         if(err){
  //           next(err);
  //         }
  //
  //         res.status(200).json(usuarios);
  //     })
  //     /*
  //     res.status(200).json({
  //       message: 'Você requisitou a página inicial'
  //     });*/
  // },
  //formato de Promises
  index: (req, res, next) =>{
    Usuario.find({})
      .then(usuarios => {
        res.status(200).json(usuarios);
      })
      .catch(err => {
        next(err);
      });
  },

  //formato de Callbacks
  // novoUsuario: (req, res, next) => {
  //   const novoUsuario = new Usuario(req.body);
  //
  //   novoUsuario.save((err, usuario) => {
  //       if(err){
  //           next(err);
  //       }
  //       res.status(201).json(usuario);
  //   });
  // }
  //formato do Promises
  novoUsuario: (req, res, next) => {
    const novoUsuario = new Usuario(req.body);

    novoUsuario.save()
      .then(usuario => {
        res.status(201).json(usuario);
      })
      .catch(err =>{
        next(err);
      });
  }
};

/*
  Nós podemos interagir com o mongoose em 3 maneiras diferentes
  1) Callbacks
  2) Promises
  3)Async/Await
*/
