/*
 * BEGIN - Define 'var', 'function'
 */

var HMPHybridBase = function(){};
var HMPHybrid;

/*
 * END
 */

var phoneGapLoaded = function(){
	console.log("device is ready...");
	//HMPHybrid code.	   
	HMPHybridBase.prototype.core = cordova;
	HMPHybridBase.prototype.navigator = navigator;	
	//HMPHybridBase.prototype.navigator.capture = navigator.device.capture;
	HMPHybridBase.prototype.device = device;
	HMPHybridBase.prototype.storage = {};
	HMPHybridBase.prototype.storage.localStorage = window.localStorage;
	HMPHybridBase.prototype.storage.openDatabase = window.openDatabase;
	HMPHybridBase.prototype.plugins = window.plugins;
	
	HMPHybrid = new HMPHybridBase();
	
	/*Fire HMPHybrid Ready Event*/		
	var evento = document.createEvent('Events');
	evento.initEvent('HMPHybridReady',true,false);
	document.dispatchEvent(evento);
};

//var scriptLoaded = function(){
	document.addEventListener("deviceready", phoneGapLoaded);
//}

