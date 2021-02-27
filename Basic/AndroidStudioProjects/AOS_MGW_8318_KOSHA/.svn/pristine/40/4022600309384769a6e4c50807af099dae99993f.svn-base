// Define custom plugin Functions
cordova.define("HMGWPlugin", function(require, exports, module) {
	document.addEventListener("HMPHybridReady", addPlugins);
});
var GWPlugin = {
	usePlugin: false	
};

var addPlugins = function(){	
	console.log("HMP hybrid is ready...");
	//BEGIN - Plugins code
	//document.addEventListener("backbutton", function(){}, false);
	//로그인 정보 가져오기
	
	GWPlugin.usePlugin = true;
	
	GWPlugin.getSessionInfo = function(successCallback, errorCallback) {
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'sessioninfo', // 호출 할 액션이름
									[]); // 플러그인에 전달할 파라미터
	};
	
	//사이드 메뉴 열기
	GWPlugin.showSidebarMenu  = function(successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'showSidebarMenu', // 호출 할 액션이름
									[]); // 플러그인에 전달할 파라미터
	};	
	
	// 네비바 타이틀 변경
	GWPlugin.setNavibarTitle = function(title, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'setTitle', // 호출 할 액션이름
				[title]); // 플러그인에 전달할 파라미터
	};
	// 네비바 왼쪽 버튼 설정
	GWPlugin.setNavibarLeftButton = function(data, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'setLeftBarButton', // 호출 할 액션이름
				data); // 플러그인에 전달할 파라미터
	};
	//네비바 오른쪽 버튼 설정
	GWPlugin.setNavibarRightButton = function(data, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'setRightBarButton', // 호출 할 액션이름
				data); // 플러그인에 전달할 파라미터
	};
	
	// 네비바 왼쪽 버튼 상태 변경
	// . buttonNumber : int 형, 상태를 정의할 버튼 갯수, 기존에 전달된 값과 동일해야 함
	// . buttonEnables :  array 형, 해당 버튼들의 상태값에 대한 배열(true, false 값 전달)
	GWPlugin.setStateLeftBarButton = function(buttonNumber, buttonEnables, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'setStateLeftBarButton', // 호출 할 액션이름
				[buttonNumber, buttonEnables]); // 플러그인에 전달할 파라미터
	};
	
	//네비바 오른쪽 버튼 상태 변경
	// . buttonNumber : int 형, 상태를 정의할 버튼 갯수, 기존에 전달된 값과 동일해야 함
	// . buttonEnables :  array 형, 해당 버튼들의 상태값에 대한 배열(true, false 값 전달)
	GWPlugin.setStateRightBarButton = function(buttonNumber, buttonEnables, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'setStateRightBarButton', // 호출 할 액션이름
				[buttonNumber, buttonEnables]); // 플러그인에 전달할 파라미터
	};
	
	// 새편지쓰기, 새게시물쓰기 popup창 열기
	GWPlugin.showPopupViewer = function(data, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'showPopupViewer', // 호출 할 액션이름
				data); // 플러그인에 전달할 파라미터
	};
	
	// 새편지쓰기, 새게시물쓰기 popup창 닫기
	GWPlugin.closePopupViewer = function(functionName, isFullView, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'closePopupViewer', // 호출 할 액션이름
				[functionName, isFullView]); // 플러그인에 전달할 파라미터
	};
	
	// 상세화면에서 앱목록으로 이동
	GWPlugin.popView = function(successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'popView', // 호출 할 액션이름
				[]); // 플러그인에 전달할 파라미터
	};
	
	// 서버 리소스 로드가 완료되었음을 노티
	GWPlugin.notiLoadingCompleted  = function(successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'notiLoadingCompleted', // 호출 할 액션이름
									[]); // 플러그인에 전달할 파라미터
	};	
	
	// 서버 리소스 로드가 오류되었음을 노티
	GWPlugin.notiLoadingError  = function(successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'notiLoadingError', // 호출 할 액션이름
									[]); // 플러그인에 전달할 파라미터
	};	
	
	// 대기화면 표시
	GWPlugin.showWaitingView  = function(minWaitingTime, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(){};
		if(errorCallback == undefined) errorCallback = function(){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'showWaitingView', // 호출 할 액션이름
									[minWaitingTime]); // 플러그인에 전달할 파라미터
	};	
	
	//  대기화면이 닫힘
	GWPlugin.closeWaitingView  = function(successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(){};
		if(errorCallback == undefined) errorCallback = function(){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'closeWaitingView', // 호출 할 액션이름
									[]); // 플러그인에 전달할 파라미터
	};
	
	// TabBar select
	GWPlugin.setSelectedTabBarItem  = function(itemIndex, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(){};
		if(errorCallback == undefined) errorCallback = function(){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'setSelectedTabBarItem', // 호출 할 액션이름
									[itemIndex]); // 플러그인에 전달할 파라미터
	};

	/*
	 *  툴바 설정
	 */
	/*
	 * 샘플 코드: GWPlugin.setToolBarButton(3, ['Btn1', 'Btn2', 'Btn3'], ['javascript:alert(1);', 'javascript:alert(2);', 'javascript:alert(3);']);
	 * 
	 * 샘플 data: [3, ['Btn1', 'Btn2', 'Btn3'], ['javascript:alert(1);', 'javascript:alert(2);', 'javascript:alert(3);']]
	 */
	GWPlugin.setToolBarButton = function(data, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'setToolBarButton', // 호출 할 액션이름
				data); // 플러그인에 전달할 파라미터
	};
	GWPlugin.hideToolBar = function(successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'hideToolBar', // 호출 할 액션이름
				[]); // 플러그인에 전달할 파라미터
	};
	
	/*
	 * 탭바 설정
	 * 
	 * 샘플 코드: GWPlugin.setTabBarButton(3, ['Btn1', 'Btn2', 'Btn3'], ['fileName1', 'fileName2', 'fileName3'],['javascript:alert(1);', 'javascript:alert(2);', 'javascript:alert(3);']);
	 * 
	 */
	GWPlugin.setTabBarButton = function(data, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'setTabBarButton', // 호출 할 액션이름
				data); // 플러그인에 전달할 파라미터
	};
	GWPlugin.hideTabBar = function(successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec(successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'hideTabBar', // 호출 할 액션이름
				[]); // 플러그인에 전달할 파라미터
	};
	
	/*
	 * 팝업메뉴 설정
	 * 
	 * data: 메뉴 Count, 메뉴명 Array, 글자색 Red flag Array, callback function array, 메뉴 Position (for only ipad) 
	 * 샘플 코드: GWPlugin.showPopupMenu(2, ['Menu1', 'Menu2'], [true, false],['javascript:alert(1);', 'javascript:alert(2);'], '{{10,10}, {200,30}}');
	 * 
	 */
	GWPlugin.showPopupMenu = function(data, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'showPopupMenu', // 호출 할 액션이름
				data); // 플러그인에 전달할 파라미터
	};

	/*
	 *   ------------ 이미지 첨부뷰어 - begin
	 */
	GWPlugin.showImageDocViewer = function(params,
			successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'showImageDocViewer', // 호출 할 액션이름
				[params]); // 플러그인에 전달할 파라미터
	};
	GWPlugin.getImageDocViewerData = function(
			successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'getImageDocViewerData', // 호출 할 액션이름
				[]); // 플러그인에 전달할 파라미터
	};
	GWPlugin.closeImageDocViewer = function(
			successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'closeImageDocViewer', // 호출 할 액션이름
				[]); // 플러그인에 전달할 파라미터
	};
	GWPlugin.setDocViewerHeader = function(filename, maxpno,
			successCallback, errorCallback) {		
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'setDocViewerHeader', // 호출 할 액션이름
				[filename, maxpno]); // 플러그인에 전달할 파라미터
	};
	GWPlugin.setDocViewerPageNo = function(pno, 
			successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'setDocViewerPageNo', // 호출 할 액션이름
				[pno]); // 플러그인에 전달할 파라미터
	};
	GWPlugin.showDocViewerHeader = function(
			successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'showDocViewerHeader', // 호출 할 액션이름
				[]); // 플러그인에 전달할 파라미터
	};
	GWPlugin.hideDocViewerHeader = function(
			successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'hideDocViewerHeader', // 호출 할 액션이름
				[]); // 플러그인에 전달할 파라미터
	};
	
	//-----  이미지 첨부 뷰어 - end
	
	GWPlugin.showLocalViewer = function(link, fileName, linkPos,
			successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'showLocalViewer', // 호출 할 액션이름
				[link, fileName, linkPos]); // 플러그인에 전달할 파라미터
	};
	
	GWPlugin.showURLViewer = function(params,
			successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'showURLViewer', // 호출 할 액션이름
				[params]); // 플러그인에 전달할 파라미터
	};
	
	GWPlugin.showURL  = function(url, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'showURL', // 호출 할 액션이름
									[url]); // 플러그인에 전달할 파라미터
	};
	
	GWPlugin.showHtmlDocViewer = function(title, url,
			successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'showHtmlDocViewer', // 호출 할 액션이름
				[title, url]); // 플러그인에 전달할 파라미터
	};
	
	// 메일 상세화면 - 삭제 
	GWPlugin.deleteMail  = function(mailId, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'deleteMail', // 호출 할 액션이름
									[mailId]); // 플러그인에 전달할 파라미터
	};
	
	// 게시물 상세화면 - 삭제 
	GWPlugin.deleteBoardMtrl = function(boardMtrlId, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'deleteBoardMtrl', // 호출 할 액션이름
									[boardMtrlId]); // 플러그인에 전달할 파라미터
	};
	
	// 결재 상세화면 - 처리 (승인, 반송) 
	GWPlugin.completeAppr = function(apprId, apprType, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'completeAppr', // 호출 할 액션이름
									[apprId, apprType]); // 플러그인에 전달할 파라미터
	};
	
	// 의견입력 화면
	GWPlugin.showWriteComment = function(comment, mustOpinion, isApprOpinion, wordType, callback, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'showWriteComment', // 호출 할 액션이름
									[comment, mustOpinion, isApprOpinion, wordType, callback]); // 플러그인에 전달할 파라미터
	};
	
	// 메뉴 ITEM 바로 가기
	GWPlugin.showMenuByID = function(menuId, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'showMenuByID', // 호출 할 액션이름
									[menuId]); // 플러그인에 전달할 파라미터
	};	

	// [영원무역]지시사항 upload를 위하여 결재 승인/반려시 호출 
	GWPlugin.beforeApprApprove = function(apprType, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'beforeApprApprove', // 호출 할 액션이름
									[apprType]); // 플러그인에 전달할 파라미터
	};
	
	// 로그인화면으로 이동
	GWPlugin.showLoginView = function(successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'showLoginView', // 호출 할 액션이름
									[]); // 플러그인에 전달할 파라미터
	};
	
	// 무명게시판 암호확인창 표시
	GWPlugin.showCheckPasswdAnoBoard = function(fn, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
				errorCallback, // 실패 시 호출될 콜백함수
				'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
				'showCheckPasswdAnoBoard', // 호출 할 액션이름
				[fn]); // 플러그인에 전달할 파라미터
	};	
	
	// 조직도 선택 확인리턴 
	GWPlugin.returnOrgSelect = function(category, callbackid, selectedlist, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'returnOrgSelect', // 호출 할 액션이름
									[category, callbackid, selectedlist]); // 플러그인에 전달할 파라미터
	};
	
	// 메일 답장, 전달, 전체답장 선택시 메일쓰기화면 호출 
	GWPlugin.showMailEditorView = function(type, boxid, msgid, selectedlist, isPopup, passwd, successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(msg){};
		if(errorCallback == undefined) errorCallback = function(msg){};
		
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'showMailEditorView', // 호출 할 액션이름
									[type, boxid, msgid, selectedlist, isPopup, passwd]); // 플러그인에 전달할 파라미터
	};	
	
	// 안드로이드에서 BackKey 눌렸을 때 이벤트 전달.  
	GWPlugin.onBackKeyPressed = function(successCallback, errorCallback) {
		if(successCallback == undefined) successCallback = function(){};
		if(errorCallback == undefined) errorCallback = function(){};		
		return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
									errorCallback, // 실패 시 호출될 콜백함수
									'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름 
									'onBackKeyPressed', // 호출 할 액션이름
									[]); // 플러그인에 전달할 파라미터
	};

	// OpenApi ServerIP Get
    GWPlugin.getAppServerIP = function(successCallback, errorCallback) {
        if(successCallback == undefined) successCallback = function(msg){};
        if(errorCallback == undefined) errorCallback = function(msg){};
        return HMPHybrid.core.exec( successCallback, // 성공 시 호출될 콜백 함수
                errorCallback, // 실패 시 호출될 콜백함수
                'HMGWPlugin', // plugins.xml에 등록한 플러그인 이름
                'getAppServerIP', // 호출 할 액션이름
                []); // 플러그인에 전달할 파라미터
    };
	
	
    var evento = document.createEvent('Events');
	evento.initEvent('HMPPluginReady',true,false);
	document.dispatchEvent(evento);
};

