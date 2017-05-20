const Persistencia = require('../persistencia/ReceitaPersistencia');
const receitaPersistencia = new Persistencia();

function receitaNegocio(){

};


receitaNegocio.prototype.getAll = async (req, res, next) =>{
  try{
    console.log('Receita getAll');
    const receita = await receitaPersistencia.getAll();
    res.status(200).json(receita);
  }catch(err){
    next(err);
  }
};


receitaNegocio.prototype.novaReceita = async (req, res, next) =>{
  try{
    console.log('Receita NovaReceita');
    var novaReceita   = req.value.body;
    const receita = await receitaPersistencia.novaReceita(novaReceita);
    res.status(200).json(receita);
  }catch(err){
    next(err);
  }
};


receitaNegocio.prototype.getReceita = async (req, res, next) =>{
  try{
    console.log('getReceita');
    var receitaId = req.value.params.receitaId;
    var receita = await receitaPersistencia.getReceita(receitaId);
    res.status(200).json(receita);
  }catch(err){
    next(err);
  }
};


receitaNegocio.prototype.replaceReceitas = async (req, res, next) =>{
  try{
    console.log('replaceReceitas');
    var { receitaId } = req.value.params;
    var novaReceita   = req.value.body;

    var resultado = await receitaPersistencia.replaceReceitas(receitaId, novaReceita);
    res.status(200).json({success: true});

  }catch(err){
    next(err);
  }
};


receitaNegocio.prototype.updateReceitas = async (req, res, next) =>{
  try{
    console.log('updateReceitas');
    var { receitaId } = req.value.params;
    var novaReceita   = req.value.body;

    var resultado = await receitaPersistencia.updateReceitas(receitaId, novaReceita);
    res.status(200).json({success: true});

  }catch(err){
    next(err);
  }
};


receitaNegocio.prototype.deleteReceita = async (req, res, next) =>{
  try{
    console.log('deleteReceita');
    var { receitaId } = req.value.params;

    var resultado = await receitaPersistencia.deleteReceita(receitaId);

    if(resultado){
      res.status(200).json({success: true});
    } else {
      return res.status(404).json({error : "Esta receita não existe!"});
    }


  }catch(err){
    next(err);
  }
};




module.exports = receitaNegocio;
