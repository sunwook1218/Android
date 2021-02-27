var DOC_HANDLER = {
	serverIP:null,	
	waitingTime: 10, // 최소 대기시간(초)
	evt: null,
	invoke:function(params, method, successFN, errorFN){
		if(errorFN == undefined){
			errorFN = DOC_HANDLER.error;
		}
		if(successFN == undefined){
			successFN = DOC_HANDLER.success;
		}
		var ipStr = GW_OpenAPI.serverIP.split("/");
		
		DOC_HANDLER.serverIP =  ipStr[0]+"//"+ipStr[2];
		var url = GW_OpenAPI.serverIP + "/rest/doc/handle";
		
		$.ajax({
			beforeSend: function(req) {
				if (GWPlugin.usePlugin) {
					GWPlugin.showWaitingView(DOC_HANDLER.waitingTime, function(){}, function(){});
				} else {
					$.mobile.showPageLoadingMsg();
				}
			}, //Show spinner
		    complete: function() { }, //Hide spinner //$.mobile.hidePageLoadingMsg();
			type: method,
			url: url,
			success:successFN,
			error:errorFN,
			data: params,
			dataType:"json"
		});
	},
	error:function(code, msg){
		if (GWPlugin.usePlugin) {	
			GWPlugin.closeWaitingView(function(){}, function(){});
		} else {
			$.mobile.hidePageLoadingMsg();
		}
		GW_PROXY.alertErrorMessage(data.code, data.message); 
	},
	success:function(data){				
		if (GWPlugin.usePlugin) {	
			GWPlugin.closeWaitingView(function(){}, function(){});
		} else {
			$.mobile.hidePageLoadingMsg();
		}
		
		if(data.code != undefined){
			GW_PROXY.alertErrorMessage(data.code, data.message); 
			return;
		}
		data.docLink.linkList.forEach(function(item, index, all){
			// MGW-882 [마사회] 여러page의 문서를 초기에 모두 변환하지 않고, 페이지 이동시마다 변환
			if(item.indexOf("http") != 0) {
				data.docLink.linkList[index] = DOC_HANDLER.serverIP + item;
			} else {
				if(data.docType == "synapimage") {
					data.docLink.linkList[index] = item;
				} else {
					data.docLink.linkList[index] = GW_OpenAPI.serverIP + "/rest/doc/proxy?proxyurl=" + item;
				}				
			}
		});
		data.baseUrl = DOC_HANDLER.serverIP;
		var jsonString = JSON.stringify(data);
		switch(data.docType){
			case "image":
			case "html":
			case "text":{
				if(GWPlugin.usePlugin){
					GWPlugin.showImageDocViewer(jsonString);
				}else{
					var jsonObj = {}
					jsonObj["doc_data"] = jsonString;
					var win = window.open("view/attach/doc_viewer.html");
					var setData = function(){
						if(win.isPageLoaded == undefined){
							setTimeout(setData, 200);
						}else{
							win.setDocData(jsonObj);
						}		
					};
					setTimeout(setData, 200);
				}				
				break;
			}
			case "file":{
				var linkPos = "{{" + DOC_HANDLER.evt.clientX + "," + DOC_HANDLER.evt.clientY + "}, {" + "10" + "," + "10" + "}}";
				var fileLink = data.docLink.linkList[0];
				var fileName = data.docName;				
				if(GWPlugin.usePlugin){
					GWPlugin.showLocalViewer(fileLink, fileName, linkPos);
				}else{
					window.open(fileLink);
					
				}
				break;
			}
			case "synap":
			case "synapimage":{
				var fileLink = data.docLink.linkList[0];
				var fileName = data.docName;
				
				if(GWPlugin.usePlugin){
					GWPlugin.showHtmlDocViewer(fileName, fileLink);
				} else {
					window.open(fileLink);
				}
				
				break;
			}			
			case "kaon":{
				var fileLink = data.docLink.linkList[0];
				
				window.open(fileLink);

				break;
			}			

		}
		
	},
	setEvent:function(evt) {
		DOC_HANDLER.evt = evt;
	},
	showAttach:function(link, category, filename){
		var params ={};
		params["openapi_doclink"] = link;
		params["openapi_category"] = category;
		params["openapi_filename"] = filename;

		DOC_HANDLER.invoke(params, "post");
	},
	getApprBody:function(link, category, wordtype, success){
		var params ={};
		params["openapi_doclink"] = link;
		params["openapi_category"] = category;
		params["openapi_wordtype"] = wordtype;
		
		DOC_HANDLER.invoke(params, "post", success);
	},
	getApprSummary:function(link, category, wordtype, success){
		var params ={};
		params["openapi_doclink"] = link;
		params["openapi_category"] = category;
		params["openapi_wordtype"] = wordtype;
		
		DOC_HANDLER.invoke(params, "get", success);
	}
};