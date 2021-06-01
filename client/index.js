var net = require('net');
const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});

var socket = net.connect(8000, 'localhost');

socket.on('data', function (data) {
	console.log('Received: ' + data);
	socket.write(' \n');
	// console.log('ho mandato spazio');
});

socket.on('close', function () {
	console.log('Connection closed');
});

rl.question('', function (msg) {
	socket.write(msg + '\n');
	rl.close();
});
