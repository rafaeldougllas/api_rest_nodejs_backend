const Usuario = require('../models/usuarios');


//Exporta por meio de funçoes
module.exports = {
  //formato async/await (So funciona com node 7^)
  index: async (req, res, next) =>{
    try{
      const usuarios = await Usuario.find({});
      res.status(200).json(usuarios);
    }catch(err){
      next(err);
    }
  },
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
  // index: (req, res, next) =>{
  //   Usuario.find({})
  //     .then(usuarios => {
  //       res.status(200).json(usuarios);
  //     })
  //     .catch(err => {
  //       next(err);
  //     });
  // },



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
  // novoUsuario: (req, res, next) => {
  //   const novoUsuario = new Usuario(req.body);
  //
  //   novoUsuario.save()
  //     .then(usuario => {
  //       res.status(201).json(usuario);
  //     })
  //     .catch(err =>{
  //       next(err);
  //     });
  // }
 //formato async/await (So funciona com node 7^)
  novoUsuario: async (req, res, next) => {
    try{
      const novoUsuario = new usuario(req.body);
      const usuario     = await novoUsuario.save();
      res.status(201).json(usuario);
    }catch(err){
      next(err);
    }
  },

  getUsuario: async (req, res, next) => {

      //const userId = req.params.usuarioId;
      const {usuarioId} = req.params;
      //Funçao do mongoose que busca pelo id
      const usuario = await Usuario.findById(usuarioId);
      res.status(200).json(usuario);

  },
  //Serve para atualizar todos os dados do usuario
  replaceUsuario: async (req, res, next) => {
    //Tem q ter todos os campos de Usuarui
    const { usuarioId } = req.params;
    const novoUsuario   = req.body;

    const resultado = await Usuario.findByIdAndUpdate(usuarioId,novoUsuario);
    res.status(200).json({success: true});

  },
  //Serve para atualizar algum(ns) atributo(s) ou todos
  updateUsuario: async (req, res, next) => {
    const { usuarioId } = req.params;
    const novoUsuario   = req.body;

    const resultado = await Usuario.findByIdAndUpdate(usuarioId,novoUsuario);
    res.status(200).json({success: true});
  }
};

/*
  Nós podemos interagir com o mongoose em 3 maneiras diferentes
  1) Callbacks
  2) Promises
  3)Async/Await
*/
