//Dependencias
const express = require('express');
const logger  = require('morgan');


const app     = express();

//Rotas
const usuarios = require('./routes/usuarios');

//Middlewares
app.use(logger('dev'));

//Rotas
app.use('/usuarios', usuarios);


//Captura erros 404 e coloca para o manipulador de erros resolver
app.use((req,res,next) => {
	const err = new Error('Not Found');
	err.status = 404;
	next(err);
});


//Manipulador de erros
app.use((req,res,next) => {
	const error  = app.get('env') === 'development' ? err : {};
	const status = err.status || 500;

	//Resposta ao cliente
	res.status(status).json({
		error:{
			message: error.message
		}
	});
	
	console.error(err);
});

//Inicia o servidor
const port = app.get('port') || 3000;
app.listen(port, () => console.log('Servidor está ouvindo na porta '+port));
