var id_seq = 0;
var DEBUG_MODE = true;
var DEFAULT_PAGE_TRANSITION = "none";

var APP_INFO_SERVER = "";
var APP_INFO_DEVICETYPE = "phone";
var APP_DEFAULT_TIMEOUT = 2000;
var APP_INSTRUCTIONS_USE = "N";


$(function () { // jQuery Mobile is ready	
	document.addEventListener("backbutton", function(){
		if(GWPlugin.usePlugin)
		{
			GWPlugin.onBackKeyPressed(
				function(){}
				,function(){}
				);
		}
	}, true);// ignore android back-button event.
	
	$.mobile.defaultHomeScroll = 0;
	$.mobile.defaultPageTransition=DEFAULT_PAGE_TRANSITION;
	 // Navigation 
	var defaultTheme = "a";
	$.mobile.page.prototype.options.backBtnTheme   	= defaultTheme;
	
	// Page
	$.mobile.page.prototype.options.headerTheme 	= defaultTheme;
	$.mobile.page.prototype.options.footerTheme 	= defaultTheme;
	
	$.mobile.changePage.defaults.allowSamePageTransition = true;
	$.mobile.loadingMessageTextVisible = false;
});


document.addEventListener("HMPPluginReady", getSessionInfo);

function notiLoginSuccess() {
	console.log("notiLoginSuccess!");
	GWPlugin.getSessionInfo(setSessionInfo, function(){
		alert("Error: fail to set sessioninfo.");
	});
}

// APP_INFO_SERVER 를 초기화
function initializeServerInfo(serverUrl) {
    LOG("Initialize Server Url: " + serverUrl);
    APP_INFO_SERVER = serverUrl;
}

//세션 관련
//세션정보 세팅 - Begin
function getSessionInfo(){
	// 커스텀 리소스 추가

	LOG("Loading Javascript from MobileServer...11");
	console.log("APP_INFO_SERVER: " + APP_INFO_SERVER);

    GWPlugin.getAppServerIP(function(ip){
            addExtJavascript("js/mgw.js", function() {
            		PAGE_CONTROLLER.init("view/mgw.html", DEFAULT_PAGE_TRANSITION);
            	});
      }, function(){});

	/*addExtJavascript(APP_INFO_SERVER + "/www/js/mgw.js", function() {
		PAGE_CONTROLLER.init(APP_INFO_SERVER + "/www/view/mgw.html", DEFAULT_PAGE_TRANSITION);
	});*/
};

function setSessionInfo(data){
	LOG("setSessionInfo");
	if(data.serverurl != undefined){
		GW_OpenAPI.serverIP = data.serverurl;
		APP_INFO_SERVER = data.serverurl;
	}
	
	if (data.devicetype == "1")
		APP_INFO_DEVICETYPE = "pad";
	
	// 세션정보 세팅..	
	sessionStorage["deptid"] = data.deptid;
	sessionStorage["empcode"] = data.empcode;
	sessionStorage["id"] = data.id;
	sessionStorage["isadditionalofficer"] = data.isadditionalofficer;
	sessionStorage["isguest"] = data.isguest;
	sessionStorage["key"] = data.key;
	sessionStorage["name"] = data.name;
	sessionStorage["uname"] = data.uname;
	
	GW_OpenAPI.mobileInit(data.config);	
};
//세션 정보 세팅 - End

//외부 JAVASCRIPT 동적 로딩
function addExtJavascript(src, callback) {
	$.ajax({
		url: src,
		timeout: APP_DEFAULT_TIMEOUT,
		success: function(data) {
			if (data.length < 1) {
				GWPlugin.notiLoadingError(function(){}, function(){});
			}
			else {
				var head_tag = document.getElementsByTagName("head")[0];
				var newScript = document.createElement("script");
				newScript.setAttribute("type","text/javascript");
				newScript.innerHTML = data;
				head_tag.appendChild(newScript);
				
				callback();
			}
		},
		error: function() {
			GWPlugin.notiLoadingError(function(){}, function(){});
		}
	});	
}

//외부 CSS 동적 로딩
function addExtStylesheet(src, callback) {
	$.ajax({
		url: src,
		timeout: APP_DEFAULT_TIMEOUT,
		success: function(data) {
			var head_tag = document.getElementsByTagName("head")[0];
			var newCSS = document.createElement("link");
			newCSS.setAttribute("type", "text/css");
			newCSS.setAttribute("rel", "stylesheet");
			newCSS.innerHTML = data;
			head_tag.appendChild(newCSS);
			
			callback();
		},
		error: function() {
			GWPlugin.notiLoadingError(function(){}, function(){});
		}
	});	
}

// 로그
function LOG(msg){
	if(DEBUG_MODE){
		console.log(msg);
	}
}