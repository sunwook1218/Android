/*
 *  [리소스 관리 ]
 * 
 *  한글 정의 : RES_STRING_KO = {} - res_string_ko.js 참조
 *  영문 정의 : RES_STRING_EN = {} - res_string_en.js 참조
 *  일어 정의 : RES_STRING_JA = {} - res_string_ja.js 참조
 * 
 *  사용법:
 *  	- javascript : MGW_RES.get(id);
 *  		ex) var recvlist = MGW_RES.get("gw_mail_recvlist_label");
 *  
 *  	- html:  $$([id])
 *  		ex) <li id="xxx11232"> $$([gw_mail_recvlist_label]) </li>
 */

var MGW_RES = {
	locale: getLocale().toUpperCase(),
	get: function(id){
		if (startsWith(MGW_RES.locale, 'KO')) {
			return RES_STRING_KO[id];
		} else if (startsWith(MGW_RES.locale, 'EN')) {
			return RES_STRING_EN[id];
		} else if (startsWith(MGW_RES.locale, 'JA')) {
			return RES_STRING_JA[id];
		} else if (startsWith(MGW_RES.locale, 'ZH')) {
			return RES_STRING_ZH_CN[id];
		} else {
			return RES_STRING[id];
		}
	},
	
	replaceResource: function(str){		
		var resAry = str.match(/\$\$\(\[[\w]+\]\)/g);
		
		if(resAry != undefined && resAry != null){
			var value;
			for(var i = 0 ; i < resAry.length ; i++){
				value = MGW_RES.get(resAry[i].substring(4, (resAry[i].length -2)));
				if(value == undefined) value = "null";			
				str = str.replace(resAry[i], value);
			}
		}
		return str;
	}
};var RES_STRING = {	
	gw_product_name: "그룹웨어",
	
	// 18.11.12 tkofs
	menu_show_all:"전체메뉴",
		
	gw_common_loading_label: "불러오는중...",
	gw_common_pulldown_label: "놓으시면 목록을  업데이트합니다.",
	gw_common_pulldown_update_label: "업데이트 하려면 아래로 당기세요",
	gw_common_pullup_label: "놓으시면 목록을 가져옵니다.",
	gw_common_pullup_refresh_label: "더 많은 목록을 보고싶다면 위로 당기세요",

	gw_common_menu_label: "메뉴",
	gw_common_back_label: "이전",
	gw_common_close_label: "닫기",
	gw_common_write_label: "쓰기",
	gw_common_edit_label: "편집",
	gw_common_canceledit_label: "편집취소",
	gw_common_selectall_label: "전체선택",
	gw_common_cancelselect_label: "선택취소",
	gw_common_delete_label: "삭제",
	gw_common_attach_label: "첨부",
	gw_common_username_label: "성명",
	gw_common_email_label: "EMAIL",
	gw_common_companyname_label: "회사명",
	gw_common_phone_label: "전화번호",
	gw_common_search_label: "검색",
	gw_common_search_result_label: "검색결과",
	gw_common_add_label: "추가",
	gw_common_select_label: "선택",
	gw_common_show_label: "보기",
	gw_common_hide_label: "감추기",
	gw_common_ok_label: "확인",
	gw_common_set_label: "설정",
	gw_common_change_label: "변경",
	gw_common_cancel_label: "취소",
	gw_common_modify_label: "수정",
	gw_common_option_label: "옵션",
	gw_common_byte_label: "byte",
	gw_common_kilobyte_label: "KB",
	gw_coomon_date_label: "Date",
	gw_coomon_time_label: "Time",
	gw_common_getmore_label: "더보기..",
	gw_common_group_label: "그룹",
	gw_common_year_label: "년",
	gw_common_month_label: "월",
	gw_common_day_label: "일",
	gw_common_hour_label: "시",
	gw_common_minute_label: "분",
	gw_common_input_password_title: "암호입력",
	gw_common_refresh_label: "새로고침",
	gw_common_piece_label: "개",
	gw_common_save_label: "저장",
	
	gw_mail_mailwrite_label: "편지쓰기",
	gw_mail_mailread_label: "편지읽기",
	gw_mail_recvlist_label: "받은 편지함",
	gw_mail_sendlist_label: "보낸 편지함",
	gw_mail_templist_label: "임시 편지함",
	gw_mail_deletelist_label: "지운 편지함",
	gw_mail_receiver_label: "받는이",
	gw_mail_sender_label: "보낸이",
	gw_mail_cc_label: "참조인",
	gw_mail_confirm_receive_label: "수신확인",
	gw_mail_cancel_label: "회수",
	gw_mail_fly_label: "전달",
	gw_mail_reply_label: "답장",
	gw_mail_replyall_label: "전체답장",
	gw_mail_personalbox_label: "개인 편지함",
	gw_mail_personalbox_path: "개인 편지함",
	gw_mail_entire_state_label: "전체상태",
	gw_mail_bcc_label: "숨은참조",
	gw_mail_ccbcc_lable: "참조,숨은참조",
	gw_mail_urgency_label: "긴급",
	gw_mail_security_label: "보안",
	gw_mail_title_label: "제목",
	gw_mail_send_label: "보내기",
	gw_mail_include_org_attach_label: "기존첨부",
	gw_mail_include_org_message: "원문포함",
	gw_mail_dbmail_sender_label: "송신",
	gw_mail_dbmail_to_label: "수신",
	gw_mail_dbmail_date_label: "날짜",
	gw_mail_dbmail_original_message: "원본 메시지",
	gw_mail_notitle_label: "제목없음",
	gw_mail_choose_samename_label: "동명이인 선택",
	gw_mail_select_recv: "수신자 선택",
	
	gw_schedule_label: "일정",
	gw_schedule_todo_label: "할일",
	gw_schedule_list_label: "일정 목록",
	gw_schedule_equip_list_label: "설비예약 목록",
	gw_schedule_equip_detaillist_label: "설비예약 상세목록",
	gw_schedule_add_label: "일정/설비예약 추가",
	gw_schedule_schadd_label: "일정/설비예약 추가",
	gw_schedule_schmodify_label: "일정/설비예약 수정",
	gw_schedule_sch6add_label: "일정 추가",
	gw_schedule_sch6modify_label: "일정 수정",
	gw_schedule_todo_list_label: "할일 목록",
	gw_schedule_todo_add_label: "할일 추가",
	gw_schedule_todo_modify_label: "할일 수정",
	gw_schedule_search_label: "일정/할일 검색",
	gw_schedule2_search_label: "일정 검색",
	gw_schedule_sch_search_label: "일정 검색결과",
	gw_schedule_todo_search_label: "할일 검색결과",
	gw_schedule_base_date_label: "기준 일 : ",
	gw_schedule_daily_label: "일간",
	gw_schedule_weekly_label: "주간",
	gw_schedule_monthly_label: "월간",
	gw_schedule_monthplan_label: "월간계획",
	gw_schedule_today_label: "오늘",
	gw_schedule_shared_calendar_label: "공유일정",
	gw_schedule_all_calendar_label: "전체달력",
	gw_schedule_my_and_shared_calendar_label: "나의달력 + 공유일정",
	gw_schedule_my_calendar_label: "나의달력",
	gw_schedule_dept_calendar_label: "부서달력",
	gw_schedule_company_calendar_label: "전사달력",
	gw_schedule_user_calendar_label: "사용자달력",
	gw_schedule_calendar_count_label: "건",
	gw_schedule_schedule_view_label: "일정 보기",
	gw_schedule_equipment_view_label: "설비예약 보기",
	gw_schedule_todo_view_label: "할일보기",
	gw_schedule_title_label: "제목",
	gw_schedule_category_label: "유형",
	gw_schedule_owner_label: "소유자",
	gw_schedule_writer_label: "작성자",
	gw_schedule_repeat_label: "반복",
	gw_schedule_term_label: "기간",
	gw_schedule_write_date_label: "작성일",
	gw_schedule_start_date_label: "시작일",
	gw_schedule_end_date_label: "종료일",
	gw_schedule_day_schedule_label: "종일일정",
	gw_schedule_period_label: "기간설정",
	gw_schedule_period_day_1_label: "당일",
	gw_schedule_period_day_2_label: "7일",
	gw_schedule_period_day_3_label: "1개월",
	gw_schedule_period_day_4_label: "3개월",
	gw_schedule_calendar_label: "달력",
	gw_schedule_attendee_label: "공유자",
	gw_schedule_equipment_label: "설비선택",
	gw_schedule_all_equipment_label: "전체설비",
	gw_schedule_content_label: "내용",
	gw_schedule_reserve_equipment_label: "설비예약",
	gw_schedule_alarm_label: "알림",
	gw_schedule_alarm_label_0: "즉시알림",
	gw_schedule_alarm_label_m: "분 전",
	gw_schedule_alarm_label_h: "시간 전",
	gw_schedule_alarm_label_d: "일 전",
	gw_schedule_alarm_label_w: "주 전",
	gw_schedule_alarm_label_M: "달 전",
	gw_schedule_alarm_label_0mail: "메일 즉시 발송",
	gw_schedule_importance_label: "중요도",
	gw_schedule_weight_high: "높음",
	gw_schedule_weight_medium: "중간",
	gw_schedule_weight_low: "낮음",
	gw_schedule_status_label: "상태",
	gw_schedule_progress_label: "진척율",
	gw_schedule_status_finish_label: "완료",
	gw_schedule_status_delay_label: "연기",
	gw_schedule_status_cancel_label: "취소",
	gw_schedule_status_progress_label: "진행",
	gw_schedule_status_notfinish_label: "미완료",
	gw_schedule_not_label: "없음",
	gw_schedule_recur_type0: "반복 없음",
	gw_schedule_recur_type1: "매일(월~일)",
	gw_schedule_recur_type2: "평일(월~금)",
	gw_schedule_recur_type3: "매주",
	gw_schedule_recur_type4: "매월",
	gw_schedule_recur_type5: "매월(요일)",
	gw_schedule_recur_type6: "매년",
	// 추가
	gw_schedule_recur_cycle_weekly: "주마다",
	gw_schedule_recur_cycle_weekly_num: "{{num}}주마다",
	gw_schedule_recur_cycle_type4_day: "매월 {{dd}}",
	gw_schedule_recur_cycle_type4_ord_day_wk: "매월 {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type4_last_wk: "매월 마지막 {{dayWk}}",
	gw_schedule_recur_cycle_type6_date: "매년 {{mmdd}}",
	gw_schedule_recur_cycle_type6_last: "매년 {{mm}} 마지막 날",
	gw_schedule_recur_cycle_type6_ord_day_wk: "매년 {{mm}} {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type6_last_wk: "매년 {{mm}} 마지막 {{dayWk}}",
	gw_schedule_recur_times: "마지막",
	gw_schedule_recur_times0: "첫 번째",
	gw_schedule_recur_times1: "두 번째",
	gw_schedule_recur_times2: "세 번째",
	gw_schedule_recur_times3: "네 번째",
	gw_schedule_recur_times4: "다섯 번째",
	// 추가 -end
	gw_schedule_equipmentgroup_label : "설비그룹",

	gw_schedule_have_label: "있음",
	gw_schedule_ownertype_user: "개인일정",
	gw_schedule_ownertype_dept: "부서일정",
	gw_schedule_security_level_label: "보안설정",
	gw_schedule_security_type_a: "누구나 읽기 가능",
	gw_schedule_security_type_d: "부서원만 읽기 가능",
	gw_schedule_security_type_s: "부서원/직속상하위 부서원 읽기 가능",
	gw_schedule_security_type_o: "소유자만 읽기 가능",
	gw_schedule_add_security_level_label: "공개범위",
	gw_schedule_add_security_type_a: "모두에게",
	gw_schedule_add_security_type_d: "부서원에게",
	gw_schedule_add_security_type_s: "직속상하위 부서원에게",
	gw_schedule_add_security_type_o: "소유자만에게만",
	gw_schedule_select_calendar: "달력선택",
	gw_schedule_select_equipment: "설비선택",
	gw_schedule_select_allcallist: "전체 달력목록",
	gw_schedule_select_allequiplist: "전체 설비목록",
	
	gw_schedule_delete_recur_title: "반복 설정된 일정",
	gw_schedule_delete_recur_option1: "전체 반복일정 삭제",
	gw_schedule_delete_recur_option2: "선택된 일정만 삭제",
	gw_schedule_delete_recur_option4: "선택된 일정을 포함한 이후 일정 삭제",
	gw_schedule_delete_recur_option_select: "삭제 유형을 선택하십시오.",	
	// 추가
	gw_schedule_modify_recur_title: "반복 설정된 일정",
	gw_schedule_modify_recur_option1: "전체 반복일정 수정",
	gw_schedule_modify_recur_option2: "선택된 일정만 수정",
	gw_schedule_modify_recur_option4: "선택된 일정을 포함한 이후 일정 수정",
	// 추가 - e
	gw_board_name_label: "게시판",
	gw_board_recentlist_label: "최근 게시물",
	gw_board_alllist_label: "전체 게시판",
	gw_board_favlist_label: "즐겨찾기 게시판",
	gw_board_comment_label: "의견",
	gw_board_read_label: "게시물 읽기",
	gw_board_postername_label: "게시자",
	gw_board_write_title: "게시물 쓰기",
	gw_board_wirte_label: "게시",
	gw_board_enddate_label: "종료일",
	gw_board_everlast_label: "영구",
	gw_board_preface_label: "머리글",
	gw_board_select_preface: "머리글선택",
	gw_board_title_label: "제목",
	gw_board_write_comment_label: "의견작성",
	gw_board_write_of_comment_label: "댓글쓰기",

	gw_contact_personal_label: "개인주소록",
	gw_contact_dept_label: "부서주소록",	
	gw_contact_group_label: "그룹내용보기",
	gw_contact_personal_info_label: "개인정보",
	gw_contact_org_info_label: "회사정보",
	gw_contact_gender_m: "남자",
	gw_contact_gender_f: "여자",
	gw_contact_search_name: "이름으로 주소록 검색",
	gw_contact_search_email: "EMAIL로 주소록 검색",
	gw_contact_search_orgname: "회사명으로 주소록 검색",
	gw_contact_search_phone: "전화번호로 주소록 검색",
	
	gw_org_subdept_label: "하위부서",
	gw_org_member_label: "구성원",
	gw_org_tree_label: "조직도",
	gw_org_select_label: "조직도 선택",
	gw_org_userinfo_label: "사용자정보",
	gw_org_includesub_label: "하위포함",
	gw_org_search_deptname: "부서이름으로 부서 검색",
	gw_org_search_username: "이름으로 구성원 검색",
	gw_org_search_email: "EMAIL로 구성원 검색",
	gw_org_search_phone: "전화번호로 구성원 검색",
	gw_org_search_mobile: "휴대폰번호로 구성원 검색",
	gw_org_search_empcode: "사원번호로 구성원 검색",
	gw_org_search_posname: "직위명으로 구성원 검색",
	gw_org_search_rankname: "직급명으로 구성원 검색",
	gw_org_search_dutyname: "직책명으로 구성원 검색",
	gw_org_search_business: "담당업무명으로 구성원 검색",	
	gw_org_root_label: "최상위부서",
		
	gw_sign_label: "결재",

	gw_sign_waitlist_label: "결재대기",
	gw_sign_gongramwaitlist_label: "공람대기",
	gw_sign_gongramcompletelist_label: "공람완료",
	gw_sign_nowlist_label: "결재진행",
	gw_sign_receiptwaitlist_label: "접수대기",
	gw_sign_userprocessedlist_label: "개인문서함[결재한문서]",
	gw_sign_mycompletelist_label: "개인문서함[완료문서]",
	gw_sign_completelist_label: "기록물 대장[완료함]",
	gw_sign_dispatchlist_label: "발송처리",
	
	gw_sign_details_label: "결재 본문",
	gw_sign_approve_label: "결재",
	gw_sign_reject_label: "반려",
	gw_sign_junkyul_label: "전결",
	gw_sign_postpone_label: "보류",
	gw_sign_gongram_label: "공람확인",
	gw_sign_confirm_label: "참조확인",
	gw_sign_write_comment_label: "의견작성",
	gw_sign_cancel_write_comment_label: "의견작성취소",
	gw_sign_show_comment_label: "의견보기",
	gw_sign_approve_flow_label: "결재진행상태",
	gw_sign_withdraw_label: "결재회수",
	gw_sign_cancel_label: "결재취소",
	gw_sign_show_summary_label: "요약",
	gw_sign_show_linkdoc_label: "관련문서",
	gw_sign_input_password_title: "결재암호입력",
	gw_sign_dispatch_label: "발송처리",
	
	gw_sign_signlist_type_all_label: "전체",
	gw_sign_signlist_type_1_label: "일반문서",
	gw_sign_signlist_type_3_label: "수신문서",
	gw_sign_signlist_type_2_label: "발신문서",
	gw_sign_signlist_type_7_label: "보고문서",
	gw_sign_signlist_type_5_label: "감사문서",
	gw_sign_signlist_type_4_label: "협조문서",

	gw_sign_receiptlist_type_all_label: "전체",
	gw_sign_receiptlist_type_2_label: "도착",
	gw_sign_receiptlist_type_4_label: "접수",
	gw_sign_receiptlist_type_16_label: "반송",
	gw_sign_receiptlist_type_8192_label: "내부반송",
	gw_sign_floworder_label: "순번",
	gw_sign_approval_label: "처리방법",
	
	gw_settings_label: "설정",
	gw_settings_absence_label: "부재설정",
	gw_settings_userinfo_label: "개인정보설정",
	gw_settings_password_label: "암호설정",
	gw_settings_license_label: "라이센스 정보",
	
	gw_settings_absence_list_label: "부재설정 목록",
	gw_settings_absence_add_label: "추가",
	gw_settings_absence_absadd_label: "부재추가",
	gw_settings_absence_save_label: "저장",
	gw_settings_absence_modify_label: "수정",
	gw_settings_absence_absmodify_label: "부재수정",
	gw_settings_absence_copy_label: "복사",
	gw_settings_absence_set_label: "부재설정",
	gw_settings_absence_remove_label: "부재해제",
	gw_settings_absence_period_label: "기간",
	gw_settings_absence_period_start_label: "부재시작",
	gw_settings_absence_period_end_label: "부재종료",
	gw_settings_absence_period_reason_label: "부재사유",
	gw_settings_absence_period_reason_1_label: "교육",
	gw_settings_absence_period_reason_2_label: "출장",
	gw_settings_absence_period_reason_3_label: "외출",
	gw_settings_absence_period_reason_4_label: "휴가",
	gw_settings_absence_period_reason_5_label: "조퇴",
	gw_settings_absence_period_reason_6_label: "연가",
	gw_settings_absence_period_reason_7_label: "병가",
	gw_settings_absence_period_reason_8_label: "공가",
	gw_settings_absence_period_reason_9_label: "특별휴가",
	gw_settings_absence_period_reason_10_label: "결근",
	gw_settings_absence_period_reason_11_label: "지참",
	gw_settings_absence_period_reason_12_label: "부재",
	gw_settings_absence_period_reason_13_label: "휴직",
	gw_settings_absence_period_reason_14_label: "퇴직",
	gw_settings_absence_mail_label: "메일설정",
	gw_settings_absence_mail_reply_msg_label: "메일 자동응답<BR>메세지",	// tkofs kosmes GW 메세지 통일 부재메세지 -> 
	gw_settings_absence_mail_alt_rcpt_label: "대리 수신자",
	gw_settings_absence_sanc_label: "결재설정",
	gw_settings_absence_sanc_alt_signer_label: "대리 결재자",
	gw_settings_absence_sanc_handling1_label: "결재후 진행문서",
	gw_settings_absence_sanc_handling1_0_label: "결재대기",
	gw_settings_absence_sanc_handling1_1_label: "대리 결재자",
	gw_settings_absence_sanc_handling1_2_label: "결재안함",
	gw_settings_absence_sanc_handling2_label: "결재후 완료문서",
	gw_settings_absence_sanc_handling2_0_label: "결재대기",
	gw_settings_absence_sanc_handling2_1_label: "대리 결재자",
	
	gw_settings_user_alias_label: "별칭",
	gw_settings_user_phone_label: "전화번호",
	gw_settings_user_fax_label: "팩스번호",
	gw_settings_user_mobile_label: "휴대폰번호",
	gw_settings_user_business_label: "담당업무",
	
	gw_settings_password: "암호설정",
	gw_settings_password_cbloginpasswd: "로그인 암호 변경",
	gw_settings_password_txtoldloginpasswd: "이전암호",
	gw_settings_password_txtloginpasswd: "새 암호",
	gw_settings_password_txtloginpasswd_confirm: "암호확인",
	gw_settings_password_rdosanc: "결재암호",
	gw_settings_password_rdosanc_same: "같은 값으로 변경",
	gw_settings_password_rdosanc_cancel: "별도 지정",
	gw_settings_password_cbsancpasswd: "결재암호 변경",
	gw_settings_password_txtoldsancpasswd: "이전암호",
	gw_settings_password_txtsancpasswd: "새 암호",
	gw_settings_password_txtsancpasswd_confirm: "암호확인",
	gw_settings_password_cbsancpasswd_check: "결재시 암호 확인",
	gw_settings_password_txtsancpasswd_check: "로그인암호",
	gw_settings_password_rdosanc_confirm: "확인 여부",
	gw_settings_password_rdosanc_confirm_confirm: "확인",
	gw_settings_password_rdosanc_confirm_cancel: "확인 안함",
	
	gw_msg_common_err: "오류가 발생했습니다.",
	gw_msg_common_confirm_delete: "삭제하시겠습니까?",
	gw_msg_common_no_change: "변경된 사항이 없습니다.",
	gw_msg_common_confirm: "설정하신 값으로 변경하시겠습니까?",
	gw_msg_common_save_confirm: "설정하신 값으로 저장하시겠습니까?",
	gw_msg_common_change_success: "변경 되었습니다.",
	gw_msg_common_nosearchdata: "검색 결과가 없습니다.", 
	gw_msg_common_input_password: "암호를 입력하세요.",
	gw_msg_common_wrong_password: "잘못된 암호입니다.",
	gw_msg_common_nosearch_title: "제목없이 검색",
	gw_msg_common_inputsubject: "제목을 입력하세요.",
	gw_msg_common_toolong_msg: "제목의 길이가 너무 깁니다.",
	gw_msg_common_toolong_summary: "본문의 길이가 너무 깁니다.",
	gw_msg_common_success: "처리되었습니다.",
	gw_msg_common_fail: "실패되었습니다.",
	gw_msg_common_save: "저장되었습니다.",
	gw_msg_mail_nolist: "편지함에 편지가 없습니다.",
	gw_msg_mail_noselect: "선택된 편지가 없습니다.",
	gw_msg_mail_confirm_cancel: "선택한 편지를 회수하시겠습니까?",
	gw_msg_mail_confirm_delete: "선택한 편지를 삭제하시겠습니까?",
	gw_msg_mail_cancel_success: "건의 편지가 회수되었습니다.",
	gw_msg_mail_delete_success: "삭제되었습니다.",
	gw_msg_mail_already_recovered: "이미 회수한 편지입니다.",
	gw_msg_mail_not_read: "읽지 않은 편지 입니다.",
	gw_msg_mail_noReceiver: "수신자 정보가 없습니다.",
	gw_msg_mail_inputsubject: "제목을 입력하세요.",
	gw_msg_mail_inputbody: "내용을 입력하세요.",
	gw_msg_mail_inputto: "받는이가 지정되지 않았습니다.",
	gw_msg_mail_erroradress: "받는사람의(외부) 편지 주소 형식이 잘못되었습니다.",
	gw_msg_mail_smtp: "그룹웨어 포탈에서 인터넷 메일 서버 설정을 먼저 해주십시오.",
	gw_msg_mail_mismatch_org: "일치하는 구성원 또는 부서가 없습니다.",
	gw_msg_mail_write_success: "편지가 발송되었습니다.",
	gw_msg_mail_choose_samename: "동명의 사용자가 있습니다. 선택상자에서 사용자를 지정해 주십시오.",
	gw_msg_mail_invalid_email: "잘못된 이메일 주소입니다.",
	gw_msg_mail_cancel_writeMail: "편지쓰기를 취소하시겠습니까",
	gw_msg_mail_input_receiver: "받는이를 입력하세요.",
	gw_msg_mail_input_cc: "참조인을 입력하세요.",
	gw_msg_mail_input_bcc: "숨은 참조인을 입력하세요.",
	gw_msg_mail_canceled_not_deleted: "이미 회수한 편지입니다.",
	gw_msg_mail_externaldomain_not_sended: "수신자 중 외부도메인을 가진 사용자가 있어 메일을 발송 할 수 없습니다.",
	gw_msg_sign_nolist: "결재함에 문서가 없습니다.",
	gw_msg_sign_external_doc: "비전자 문서입니다.",
	gw_msg_sign_external6_doc: "수기 문서입니다.",
	gw_msg_sign_read_noauth_err: "문서 열람 권한이 없습니다.",
	gw_msg_sign_input_password: "결재암호를 입력하세요.",
	gw_msg_sign_wrong_password: "잘못된 암호입니다.",
	gw_msg_sign_confirm_approve: "결재 하시겠습니까?",
	gw_msg_sign_confirm_reject: "반려 하시겠습니까?",
	gw_msg_sign_confirm_postpone: "문서를 보류하시겠습니까?",
	gw_msg_sign_confirm_gongram: "문서를 공람확인 하시겠습니까?",
	gw_msg_sign_confirm_dispatch: "문서를 발송처리 하시겠습니까?",
	gw_msg_sign_approve_success: "결재 승인이 처리되었습니다.",
	gw_msg_sign_approve_fail: "결재 승인이 실패되었습니다.",
	gw_msg_sign_reject_success: "반려 처리되었습니다.",
	gw_msg_sign_reject_fail: "반려 되지 않았습니다.",
	gw_msg_sign_postpone_success: "문서를 보류처리 하였습니다.",
	gw_msg_sign_postpone_fail: "보류 되지 않았습니다.",
	gw_msg_sign_gongram_success: "문서를 공람확인 하였습니다.",
	gw_msg_sign_gongram_fail: "공람확인 되지 않았습니다.",
	gw_msg_sign_confirm_withdraw: "결재 문서를 회수하시겠습니까?",
	gw_msg_sign_confirm_cancel: "결재 문서를 취소하시겠습니까?",
	gw_msg_sign_cancel_success: "취소 처리되었습니다.",	
	gw_msg_sign_network_err: "네트워크 환경이 불안정하여 결과 확인에 실패하였습니다.",
	gw_msg_sign_agent_err: "모바일 결재 시스템에 오류가 발생하였습니다. 관리자에게 문의 바랍니다.",
	gw_msg_sign_not_support_apprdoc: "모바일에서 지원하지 않는 결재 문서입니다.",
	gw_msg_sign_not_transform_apprdoc: "문서변환 없이 볼 수 없는 결재 문서입니다.",
	gw_msg_sign_limitread_apprlinkdoc: "모바일에서 열람 가능한 최대수를 초과하였습니다.",
	gw_msg_sign_withdraw_opinion: "모바일에서 회수한 문서입니다.",
	gw_msg_board_authread_err: "리스트보기 권한이 없습니다.",
	gw_msg_board_nolist: "게시물이 없습니다.",
	gw_msg_board_nobldata: "등록된 게시판이 없습니다.",
	gw_msg_board_read_noauth_err: "게시물 조회 권한이 없습니다.",
	gw_msg_board_invalid_enddate: "종료일은 오늘 이후만 가능합니다.",
	gw_msg_board_cancel_write: "게시물 쓰기를 취소하시겠습니까?",
	gw_msg_board_comment_nolist: "등록된 의견이 없습니다.",
	gw_msg_board_not_match_passwd_manage: "수정/삭제 암호가 일치하지 않습니다.",
	gw_msg_schedule_invalid_code: "유효하지 않은 코드 : ",
	gw_msg_schedule_schedule_nolist: "등록된 일정이 없습니다.",
	gw_msg_schedule_check_calendar: "달력은 최소 1개이상이 선택되어야합니다.",
	gw_msg_schedule_no_select_calendar: "달력을 선택하세요",
	gw_msg_schedule_equip_nolist: "등록된 설비일정이 없습니다.",
	// 추가
	gw_msg_schedule_check_equip: "예약할 설비를 선택하세요.",
	// 추가 - e
	gw_msg_schedule_todo_nolist: "등록된 할일이 없습니다.",
	gw_msg_schedule_equip_nothing: "등록된 설비가 없습니다.",
	gw_msg_schedule_cancel_add_schedule: "일정 작성을 취소하시겠습니까?",
	gw_msg_schedule_cancel_add_todo: "할일 작성을 취소하시겠습니까?",
	gw_msg_schedule_check_reserve_equip: "설비가 이미 예약되어 있습니다.",
	gw_msg_schedule_invalid_letter_err: "\'\"\\와 같은 문자는 사용할 수 없습니다.",
	gw_msg_schedule_todo_empty_processrate: "진척율을 입력하세요.",
	gw_msg_schedule_todo_progress_invalidvalue: "진척율은 숫자만 입력 가능합니다.",
	gw_msg_schedule_todo_progress_limitvalue: "진척율은 0 ~ 100까지 숫자를 입력 가능합니다.",
	gw_msg_schedule_period_err: "기간 설정을 잘못하였습니다.",
	gw_msg_schedule_period_timegap_err: "종료 일시는 시작 일시보다 30분 이후로 설정하셔야 합니다.",	
	// 추가
	gw_msg_schedule_recur_type3_daywk: "반복 요일을 선택하십시오.",
	// 추가 -end
	gw_msg_contact_nothing: "등록된 주소록이 없습니다.",
	gw_msg_org_member_nolist: "구성원이 없습니다.",
	gw_msg_org_subdept_nolist: "하위부서가 없습니다.",
	gw_msg_absence_not_absmsg: "부재메세지를 입력해주세요",
	gw_msg_absence_toolong_msg: "현재 부재메세지는 한글80자(영문160자) 내에서만 가능합니다.",
	gw_msg_absence_not_period: "부재 기간을 입력해주세요",
	gw_msg_absence_period_err_1: "부재 종료 일시는 부재 시작 일시 이후로 설정하셔야 합니다.",
	gw_msg_absence_period_err_2: "부재 종료 날짜는 현재 날짜 이후로 설정해야 합니다.",
	gw_msg_absence_no_alter_signer: "대리결재자가 지정되지 않았습니다.",
	gw_msg_absence_absence_nolist: "등록된 부재가 없습니다.",
	gw_msg_absence_cancel_add_absence: "부재 작성을 취소하시겠습니까?",
	gw_msg_absence_minimum_one_select: "삭제하기 위해 최소 1개이상이 선택되어야 합니다.",
	gw_msg_password_no_old_password: "이전 로그인 암호를 입력하십시오.",
	gw_msg_password_no_new_password: "새로운 로그인 암호를 입력하십시오.",
	gw_msg_password_no_new_password_confirm: "새 로그인 암호 확인을 위해 새 로그인 암호를 입력하십시오.",
	gw_msg_password_mismatch_new_password: "새 로그인 암호가 동일하게 입력되지 않았습니다. 다시 입력하십시오.",
	gw_msg_password_sameold_new_password: "새 로그인 암호가 이전 로그인 암호와 동일합니다. 다른 암호를 입력하십시오.",
	gw_msg_password_toolong_password: "암호는 최대 30자 이내로 설정하셔야 합니다.",
	gw_msg_password_tooshort_password: "암호는 최소 5자 이상으로 설정하셔야 합니다.",
	gw_msg_password_no_old_sanc_password: "이전 결재 암호를 입력하십시오.",
	gw_msg_password_no_new_sanc_password: "새로운 결재 암호를 입력하십시오.",
	gw_msg_password_no_new_sanc_password_confirm: "새 결재 암호 확인을 위해 새 결재 암호를 입력하십시오.",
	gw_msg_password_mismatch_new_sanc_password: "새 결재 암호가 동일하게 입력되지 않았습니다. 다시 입력하십시오.",
	gw_msg_password_sameold_new_sanc_password: "새 결재 암호가 이전 결재 암호와 동일합니다. 다른 암호를 입력하십시오.",
	gw_msg_password_input_password_for_sanc: "결재시 암호 확인의 변경을 위해 로그인 암호를 입력하십시오.",
	gw_msg_password_tooshort_sanc_password: "결재 암호는 최소 5자 이상으로 설정하셔야 합니다.",
	
	gw_error_system_error: "내부 오류가 발생하였습니다. 관리자에게 문의해주세요.",
	gw_error_invalid_request: "유효하지 않은 요청입니다. 관리자에게 문의해주세요.",
	gw_error_session_expired: "세션이 유효하지 않습니다. 다시 로그인해 주세요.",
	gw_error_access_denied: "인증되지 않은 사용자입니다. 로그인 후 사용해 주세요.",
	gw_error_unauthorized_phone_uid_number: "인증 받지 않은 단말기 입니다. 관리자에게 문의하십시오.",
	gw_error_unregistered_new_multi_device: "등록되지 않은 디바이스입니다.",
	gw_error_unregistered_new_multi_device_guide: "등록되지 않은 디바이스입니다.\n 추가로 디바이스를 등록하려면 아래의 [장비등록] 버튼을 클릭하여 주세요.\n 관리자의 확인을 통해 현재 디바이스의 등록을 처리할 예정입니다.",
	gw_error_appversion_notequal: "모바일 그룹웨어 앱이 업데이트 되었습니다. 앱을 다시 설치하여 주십시오.",	
	gw_error_license_filenotfound: "라이센스 파일이 존재하지 않습니다.",
	gw_error_license_invalid_info: "라이센스 정보가 유효하지 않습니다.",
	gw_error_license_invalid_serverip: "라이센스가 발급되지 않은 서버입니다.",
	gw_error_license_expired: "라이센스 기간이 만료되었습니다.",
	gw_error_license_exceeduser: "접속 사용자 수가 초과되었습니다.",
	gw_error_file_download: "문서 다운로드 오류가 발생하였습니다. 관리자에게 문의해주세요.",
	gw_error_file_transform: "문서 변환 오류가 발생하였습니다. 관리자에게 문의해주세요.",
	gw_error_file_unsupported_type: "다운로드할 수 없는 파일 유형입니다.",
	gw_error_failed_result: "결과 확인에 실패하였습니다.",
	// 추가
	gw_error_duplicate:"중복 오류 입니다."
	// 추가-e
};var RES_STRING_EN = {	
	gw_product_name: "Groupware",

	gw_common_loading_label: "Loading",
	gw_common_pulldown_label: "Release to refresh",
	gw_common_pulldown_update_label: "Pull down to refresh",
	gw_common_pullup_label: "Loading the list",
	gw_common_pullup_refresh_label: "Pull up for more lists",

	gw_common_menu_label: "Menu",
	gw_common_back_label: "Back",
	gw_common_close_label: "Close",
	gw_common_write_label: "Write",
	gw_common_edit_label: "Edit",
	gw_common_canceledit_label: "Cancel Edit",
	gw_common_selectall_label: "Select All",
	gw_common_cancelselect_label: "Cancel Select",
	gw_common_delete_label: "Delete",
	gw_common_attach_label: "Attach",
	gw_common_username_label: "Name",
	gw_common_email_label: "Mail",
	gw_common_companyname_label: "Company",
	gw_common_phone_label: "Phone",
	gw_common_search_label: "Search",
	gw_common_search_result_label: "Search Results",
	gw_common_add_label: "Add",
	gw_common_select_label: "Select",
	gw_common_show_label: "Show",
	gw_common_hide_label: "Hide",
	gw_common_ok_label: "OK",
	gw_common_set_label: "Settings",
	gw_common_change_label: "Change",
	gw_common_cancel_label: "Cancel",
	gw_common_modify_label: "Modify",
	gw_common_option_label: "Option",
	gw_common_byte_label: "byte",
	gw_common_kilobyte_label: "KB",
	gw_coomon_date_label: "Date",
	gw_coomon_time_label: "Time",
	gw_common_getmore_label: "More",
	gw_common_group_label: "Group",
	gw_common_year_label: "Year",
	gw_common_month_label: "Month",
	gw_common_day_label: "Day",
	gw_common_hour_label: "Hour",
	gw_common_minute_label: "Minute",
	gw_common_input_password_title: "Enter Password",
	gw_common_refresh_label: "Refresh",
	gw_common_piece_label: "",
	gw_common_save_label: "Save",
	
	gw_mail_mailwrite_label: "Write Mail",
	gw_mail_mailread_label: "Read Mail",
	gw_mail_recvlist_label: "Inbox",
	gw_mail_sendlist_label: "Outbox",
	gw_mail_templist_label: "Draft",
	gw_mail_deletelist_label: "Recycle",
	gw_mail_receiver_label: "To",
	gw_mail_sender_label: "From",
	gw_mail_cc_label: "CC",
	gw_mail_confirm_receive_label: "CheckReceipt",
	gw_mail_cancel_label: "Retrieve",
	gw_mail_fly_label: "Forward",
	gw_mail_reply_label: "Reply",
	gw_mail_replyall_label: "ReplyAll",
	gw_mail_personalbox_label: "Private Inbox",
	gw_mail_personalbox_path: "Private Inbox",
	gw_mail_entire_state_label: "Status",
	gw_mail_bcc_label: "BCC",
	gw_mail_ccbcc_lable: "CC, BCC",
	gw_mail_urgency_label: "Urgent",
	gw_mail_security_label: "Security",
	gw_mail_title_label: "Subject",
	gw_mail_send_label: "Send",
	gw_mail_include_org_attach_label: "Attachment",
	gw_mail_include_org_message: "Included original body",
	gw_mail_dbmail_sender_label: "Sender",
	gw_mail_dbmail_to_label: "Recipient",
	gw_mail_dbmail_date_label: "Date",
	gw_mail_dbmail_original_message: "Original Message",
	gw_mail_notitle_label: "No Subject",
	gw_mail_choose_samename_label: "Multiple user name exists",
	gw_mail_select_recv: "Receiver",
	
	gw_schedule_label: "Schedule",
	gw_schedule_todo_label: "To-Do",
	gw_schedule_list_label: "Schedules",
	gw_schedule_equip_list_label: "Reservations",
	gw_schedule_equip_detaillist_label: "Reservations",
	gw_schedule_add_label: "Add Schedule",
	gw_schedule_schadd_label: "Add Schedule/Reservation",
	gw_schedule_schmodify_label: "Modify Schedule",
	gw_schedule_sch6add_label: "Add Schedule",
	gw_schedule_sch6modify_label: "Modify Schedule",
	gw_schedule_todo_list_label: "To-Do's",
	gw_schedule_todo_add_label: "Add To-Do",
	gw_schedule_todo_modify_label: "Modify To-Do",
	gw_schedule_search_label: "Search Schedules/To-Do's",
	gw_schedule2_search_label: "Search Schedules",
	gw_schedule_sch_search_label: "Search results",
	gw_schedule_todo_search_label: "Search results",
	gw_schedule_base_date_label: "Baseline : ",
	gw_schedule_daily_label: "Daily",
	gw_schedule_weekly_label: "Weekly",
	gw_schedule_monthly_label: "Monthly",	
	gw_schedule_monthplan_label: "Monthly Plan",
	gw_schedule_today_label: "Today",
	gw_schedule_shared_calendar_label: "Shared Schedule",
	gw_schedule_all_calendar_label: "All Calendars",
	gw_schedule_my_and_shared_calendar_label: "My Calendar and Shared Schedule",
	gw_schedule_my_calendar_label: "My Calendar",
	gw_schedule_dept_calendar_label: "Dept. Calendar",
	gw_schedule_company_calendar_label: "Company Calendar",
	gw_schedule_user_calendar_label: "Custom Calendar",
	gw_schedule_calendar_count_label: "",
	gw_schedule_schedule_view_label: "View Schedules",
	gw_schedule_equipment_view_label: "View Reservations",
	gw_schedule_todo_view_label: "View To-Do's",
	gw_schedule_title_label: "Title",
	gw_schedule_category_label: "Category",
	gw_schedule_owner_label: "Owner",
	gw_schedule_writer_label: "Owner",
	gw_schedule_repeat_label: "Recurrence",
	gw_schedule_term_label: "Period",
	gw_schedule_write_date_label: "Write Date",
	gw_schedule_start_date_label: "Start Date",
	gw_schedule_end_date_label: "End Date",
	gw_schedule_day_schedule_label: "Day schedule",
	gw_schedule_period_label: "Set Period",
	gw_schedule_period_day_1_label: "1 day",
	gw_schedule_period_day_2_label: "7 days",
	gw_schedule_period_day_3_label: "1 month",
	gw_schedule_period_day_4_label: "3 months",
	gw_schedule_calendar_label: "Calendar",
	gw_schedule_attendee_label: "Attendee",
	gw_schedule_equipment_label: "Equip.",
	gw_schedule_all_equipment_label: "All Equipment",
	gw_schedule_content_label: "Description",
	gw_schedule_reserve_equipment_label: "Select Equipment",
	gw_schedule_alarm_label: "Notifications",
	gw_schedule_alarm_label_0: "Instantly",
	gw_schedule_alarm_label_m: "minutes before",
	gw_schedule_alarm_label_h: "hours before",
	gw_schedule_alarm_label_d: "days before",
	gw_schedule_alarm_label_w: "weeks before",
	gw_schedule_alarm_label_M: "months before",
	gw_schedule_alarm_label_0mail: "Sending e-mail now",
	gw_schedule_importance_label: "Priority",
	gw_schedule_weight_medium: "Medium",
	gw_schedule_weight_high: "High",
	gw_schedule_weight_low: "Low",
	gw_schedule_status_label: "Status",
	gw_schedule_progress_label: "Progress Rate",
	gw_schedule_status_finish_label: "Finished",
	gw_schedule_status_delay_label: "Delayed",
	gw_schedule_status_cancel_label: "Canceled",
	gw_schedule_status_progress_label: "In-Progress",
	gw_schedule_status_notfinish_label: "Incomplete",
	gw_schedule_not_label: "Empty",
	gw_schedule_recur_type0: "No repeat",
	gw_schedule_recur_type1: "Everyday(Mon~Sun)",
	gw_schedule_recur_type2: "Weekday(Mon~Fri)",
	gw_schedule_recur_type3: "Every week",
	gw_schedule_recur_type4: "Monthly",
	gw_schedule_recur_type5: "Monthly(Day)",
	gw_schedule_recur_type6: "Every year",
	gw_schedule_recur_cycle_weekly: "Every week",
	gw_schedule_recur_cycle_weekly_num: "Every {{num}} weeks",
	gw_schedule_recur_cycle_type4_day: "Monthly {{dd}}",
	gw_schedule_recur_cycle_type4_ord_day_wk: "Monthly {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type4_last_wk: "Monthly last {{dayWk}}",
	gw_schedule_recur_cycle_type6_date: "Every year {{mmdd}}",
	gw_schedule_recur_cycle_type6_last: "The last day of {{mm}} every year",
	gw_schedule_recur_cycle_type6_ord_day_wk: "Every year {{mm}} {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type6_last_wk: "Every year {{mm}} last {{dayWk}}",
	gw_schedule_recur_times: "last",
	gw_schedule_recur_times0: "first",
	gw_schedule_recur_times1: "second",
	gw_schedule_recur_times2: "third",
	gw_schedule_recur_times3: "fourth",
	gw_schedule_recur_times4: "fifth",
	gw_schedule_equipmentgroup_label : "Equipment group",

	gw_schedule_have_label: "Exists",
	gw_schedule_ownertype_user: "My Schedule",
	gw_schedule_ownertype_dept: "Dept. Schedule",
	gw_schedule_security_level_label: "Level",
	gw_schedule_security_type_a: "Anyone can read",
	gw_schedule_security_type_d: "Only members can read",
	gw_schedule_security_type_s: "Only members/parent,sub dept members can read",
	gw_schedule_security_type_o: "Only owners can read",
	gw_schedule_add_security_level_label: "Access Level",
	gw_schedule_add_security_type_a: "All",
	gw_schedule_add_security_type_d: "Dept. Members Only",
	gw_schedule_add_security_type_s: "Immediate subordinates and superiors only",
	gw_schedule_add_security_type_o: "Owner only",
	gw_schedule_select_calendar: "Select Calendars",
	gw_schedule_select_equipment: "Select Equip.",
	gw_schedule_select_allcallist: "All Calendar list",
	gw_schedule_select_allequiplist: "All Equipment list",
	
	gw_schedule_delete_recur_title: "Recurring schedule",
	gw_schedule_delete_recur_option1: "Delete all recurring events",
	gw_schedule_delete_recur_option2: "Only delete selected events",
	gw_schedule_delete_recur_option4: "Delete future events with selected events",
	gw_schedule_delete_recur_option_select: "Please select a removal type.",	
	gw_schedule_modify_recur_title: "Recurring schedule",
	gw_schedule_modify_recur_option1: "Edit all recurring events",
	gw_schedule_modify_recur_option2: "Edit only selected events",
	gw_schedule_modify_recur_option4: "Edit future events that include selected events",
	
	gw_board_name_label: "Boards",
	gw_board_recentlist_label: "Recent Posts",
	gw_board_alllist_label: "BBS List",
	gw_board_favlist_label: "Favorites",
	gw_board_comment_label: "Comments",
	gw_board_read_label: "Read",
	gw_board_postername_label: "Author",
	gw_board_write_title: "Write",
	gw_board_wirte_label: "Write",
	gw_board_enddate_label: "End Date",
	gw_board_everlast_label: "Permanent",
	gw_board_preface_label: "Preface",
	gw_board_select_preface: "Select Preface",
	gw_board_title_label: "Title",
	gw_board_write_comment_label: "Write Comments",
	gw_board_write_of_comment_label: "Write Of Comments",

	gw_contact_personal_label: "My Contacts",
	gw_contact_dept_label: "Dept. Contacts",	
	gw_contact_group_label: "View group contents",
	gw_contact_personal_info_label: "Personal Info",
	gw_contact_org_info_label: "Company Info",
	gw_contact_gender_m: "Male",
	gw_contact_gender_f: "Female",
	gw_contact_search_name: "Search contacts by name.",
	gw_contact_search_email: "Search contacts by mail address.",
	gw_contact_search_orgname: "Search contacts by company name.",
	gw_contact_search_phone: "Search contacts by phone number.",
	
	gw_org_subdept_label: "Sub-order department",
	gw_org_member_label: "Members",
	gw_org_tree_label: "Org Chart",
	gw_org_select_label: "Select Org",
	gw_org_userinfo_label: "Profile",
	gw_org_includesub_label: "Include sub",
	gw_org_search_deptname: "Search dept. by dept. name.",
	gw_org_search_username: "Search user. member by name.",
	gw_org_search_email: "Search user. member by e-mail address.",
	gw_org_search_phone: "Search user. member by phone number.",
	gw_org_search_mobile: "Search user. member by mobile number.",
	gw_org_search_empcode: "Search user. member by emp code.",
	gw_org_search_posname: "Search user. member by position name.",
	gw_org_search_rankname: "Search user. member by rank name.",
	gw_org_search_dutyname: "Search user. member by duty name.",
	gw_org_search_business: "Search user. member by business.",
	gw_org_root_label: "Root",
		
	gw_sign_label: "Approval",

	gw_sign_waitlist_label: "Approvals Pending",
	gw_sign_gongramwaitlist_label: "View Pending",
	gw_sign_gongramcompletelist_label: "View Records",
	gw_sign_nowlist_label: "Approvals In Progress",
	gw_sign_receiptwaitlist_label: "Standby Receipt",
	gw_sign_userprocessedlist_label: "My Documents [Approvals]",
	gw_sign_mycompletelist_label: "My Documents [Finished]",
	gw_sign_completelist_label: "Records [Finished]",
	gw_sign_dispatchlist_label: "Dispatch",
	
	gw_sign_details_label: "Full body",
	gw_sign_approve_label: "Approve",
	gw_sign_reject_label: "Reject",
	gw_sign_junkyul_label: "Pre-approve",
	gw_sign_postpone_label: "Postpone",
	gw_sign_gongram_label: "Mark as Viewed",
	gw_sign_confirm_label: "Mark as Confirmed",
	gw_sign_write_comment_label: "Write Comment",
	gw_sign_cancel_write_comment_label: "Cancel Comment",
	gw_sign_show_comment_label: "View Comments",
	gw_sign_approve_flow_label: "Processing Status",
	gw_sign_withdraw_label: "Retrieve Approvals",
	gw_sign_cancel_label: "Cancel Approvals",
	gw_sign_show_summary_label: "Summary",
	gw_sign_show_linkdoc_label: "Related Documents",
	gw_sign_input_password_title: "Please enter your password for approval.",
	gw_sign_dispatch_label: "Dispatch",
	
	gw_sign_signlist_type_all_label: "All",
	gw_sign_signlist_type_1_label: "General",
	gw_sign_signlist_type_3_label: "Received",
	gw_sign_signlist_type_2_label: "Sent",
	gw_sign_signlist_type_7_label: "Reports",
	gw_sign_signlist_type_5_label: "Audits",
	gw_sign_signlist_type_4_label: "Requests",

	gw_sign_receiptlist_type_all_label: "All",
	gw_sign_receiptlist_type_2_label: "Arrived",
	gw_sign_receiptlist_type_4_label: "Received",
	gw_sign_receiptlist_type_16_label: "Rejected",
	gw_sign_receiptlist_type_8192_label: "Internally Rejected",
	gw_sign_floworder_label: "Turn",
	gw_sign_approval_label: "Processing Method",
	
	gw_settings_label: "Settings",
	gw_settings_absence_label: "Absences",
	gw_settings_userinfo_label: "Manage Profile",
	gw_settings_password_label: "Passwords",
	gw_settings_license_label: "License Info",

	gw_settings_absence_list_label: "Absences List",
	gw_settings_absence_add_label: "Absences Add",
	gw_settings_absence_absadd_label: "Absent save",
	gw_settings_absence_save_label: "Save",
	gw_settings_absence_modify_label: "Absences Modify",
	gw_settings_absence_absmodify_label: "Absent modify",
	gw_settings_absence_copy_label: "Copy",
	gw_settings_absence_set_label: "Absent",
	gw_settings_absence_remove_label: "Present",
	gw_settings_absence_period_label: "Absent Period",
	gw_settings_absence_period_start_label: "From",
	gw_settings_absence_period_end_label: "To",
	gw_settings_absence_period_reason_label: "Reason",
	gw_settings_absence_period_reason_1_label: "Education/Training",
	gw_settings_absence_period_reason_2_label: "Business Trip",
	gw_settings_absence_period_reason_3_label: "On Leave",
	gw_settings_absence_period_reason_4_label: "Vacation",
	gw_settings_absence_period_reason_5_label: "Early Leave",
	gw_settings_absence_period_reason_6_label: "Annual Leave",
	gw_settings_absence_period_reason_7_label: "Sick Leave",
	gw_settings_absence_period_reason_8_label: "Official Leave",
	gw_settings_absence_period_reason_9_label: "Special Leave",
	gw_settings_absence_period_reason_10_label: "Absent Without Notice",
	gw_settings_absence_period_reason_11_label: "Late",
	gw_settings_absence_period_reason_12_label: "Away",
	gw_settings_absence_period_reason_13_label: "Temporary Retirement",
	gw_settings_absence_period_reason_14_label: "Retired",
	gw_settings_absence_mail_label: "E-Mail Settings",
	gw_settings_absence_mail_reply_msg_label: "Mail<BR>Autoresponder<BR>Message",	// tkofs  Absence Message ->
	gw_settings_absence_mail_alt_rcpt_label: "Delegate Receiver",
	gw_settings_absence_sanc_label: "Approval Settings",
	gw_settings_absence_sanc_alt_signer_label: "Delegate Approval",
	gw_settings_absence_sanc_handling1_label: "Proceed After Approval",
	gw_settings_absence_sanc_handling1_0_label: "Pend Approval",
	gw_settings_absence_sanc_handling1_1_label: "Delegate Approval",
	gw_settings_absence_sanc_handling1_2_label: "Skip Approval",
	gw_settings_absence_sanc_handling2_label: "Complete After Approval",
	gw_settings_absence_sanc_handling2_0_label: "Pend Approval",
	gw_settings_absence_sanc_handling2_1_label: "Skip Approval",
	
	gw_settings_user_alias_label: "Nickname",
	gw_settings_user_phone_label: "Phone",
	gw_settings_user_fax_label: "FAX",
	gw_settings_user_mobile_label: "Mobile",
	gw_settings_user_business_label: "Role",
	
	// 18.11.12 tkofs
	menu_show_all:"Menu all",
	
	gw_settings_password: "Passwords",
	gw_settings_password_cbloginpasswd: "Change Login Password",
	gw_settings_password_txtoldloginpasswd: "Previous Password",
	gw_settings_password_txtloginpasswd: "New Password",
	gw_settings_password_txtloginpasswd_confirm: "Confirm New Password",
	gw_settings_password_rdosanc: "Password for Approval",
	gw_settings_password_rdosanc_same: "Same as Login Password",
	gw_settings_password_rdosanc_cancel: "Different from\nLogin Password",
	gw_settings_password_cbsancpasswd: "Change Password for Approval",
	gw_settings_password_txtoldsancpasswd: "Previous Password",
	gw_settings_password_txtsancpasswd: "New Password",
	gw_settings_password_txtsancpasswd_confirm: "Confirm New Password",
	gw_settings_password_cbsancpasswd_check: "Confirm Password for Approval",
	gw_settings_password_txtsancpasswd_check: "Login Password",
	gw_settings_password_rdosanc_confirm: "Check?",
	gw_settings_password_rdosanc_confirm_confirm: "Check?",
	gw_settings_password_rdosanc_confirm_cancel: "Skip",
	
	gw_msg_common_err: "Error has occurred.",
	gw_msg_common_confirm_delete: "Delete?",
	gw_msg_common_no_change: "No changes.",
	gw_msg_common_confirm: "Save changes?",
	gw_msg_common_save_confirm: "Save settings?",
	gw_msg_common_change_success: "Changed.",
	gw_msg_common_nosearchdata: "No search result found.", 
	gw_msg_common_input_password: "Please enter the password.",
	gw_msg_common_wrong_password: "Wrong password.",
	gw_msg_common_nosearch_title: "Search without subject",
	gw_msg_common_inputsubject: "Please enter the title.",
	gw_msg_common_toolong_msg: "Maximum length for title exceeded.",
	gw_msg_common_toolong_summary: "Maximum length for contents exceeded.",
	gw_msg_common_success: "Processed.",
	gw_msg_common_fail: "Failed.",
	gw_msg_common_save: "Saved successfully.",
	gw_msg_mail_nolist: "No mails.",
	gw_msg_mail_noselect: "No mails selected.",
	gw_msg_mail_confirm_cancel: "Retrieve selected mails?",
	gw_msg_mail_confirm_delete: "Delete selected mails?",
	gw_msg_mail_cancel_success: "Mails were successfully retrieved.",
	gw_msg_mail_delete_success: "Mail deleted.",
	gw_msg_mail_already_recovered: "Mail has already been retrieved.",
	gw_msg_mail_not_read: "Unread mail.",
	gw_msg_mail_noReceiver: "No one has read the mail.",
	gw_msg_mail_inputsubject: "Please enter the title.",
	gw_msg_mail_inputbody: "Please enter the message.",
	gw_msg_mail_inputto: "No recipient.",
	gw_msg_mail_erroradress: "Mail address is in an invalid format.",
	gw_msg_mail_smtp: "Please set the Internet Mail Server from the Groupware Portal.",
	gw_msg_mail_mismatch_org: "No match members or departments.",
	gw_msg_mail_write_success: "Mail is transferred.",
	gw_msg_mail_choose_samename: "Please select the exact user",
	gw_msg_mail_invalid_email: "Wrong mail address.",
	gw_msg_mail_cancel_writeMail: "Do you want to cancel compose mail? ",
	gw_msg_mail_input_receiver: "Please enter the receiver.",
	gw_msg_mail_input_cc: "Please enter the CC.",
	gw_msg_mail_input_bcc: "Please enter the BCC.",
	gw_msg_mail_canceled_not_deleted: "Mail has already been retrieved.",
	gw_msg_mail_externaldomain_not_sended: "There is a user with the external domain of the recipient can not send mail.",
	gw_msg_sign_nolist: "Empty",
	gw_msg_sign_external_doc: "Is a handwritten document.",
	gw_msg_sign_external6_doc: "Is a handwritten document.",
	gw_msg_sign_read_noauth_err: "You are not allowed to open the document.",
	gw_msg_sign_input_password: "Please enter your password for approval.",
	gw_msg_sign_wrong_password: "Wrong password.",
	gw_msg_sign_confirm_approve: "Do you want to approve?",
	gw_msg_sign_confirm_reject: "Do you want to reject?",
	gw_msg_sign_confirm_postpone: "Do you want to postpone?",
	gw_msg_sign_confirm_gongram: "Do you want to mark as viewed?",
	gw_msg_sign_confirm_dispatch: "Do you want to dispatch?",
	gw_msg_sign_approve_success: "Approval successfully processed.",
	gw_msg_sign_approve_fail: "Approval failed.",
	gw_msg_sign_reject_success: "Rejected.",
	gw_msg_sign_reject_fail: "Rejection failed.",
	gw_msg_sign_postpone_success: "Postponed.",
	gw_msg_sign_postpone_fail: "Postpone failed.",
	gw_msg_sign_gongram_success: "Mark as viewed.",
	gw_msg_sign_gongram_fail: "Mark as viewed failed.",
	gw_msg_sign_confirm_withdraw: "Do you want to retrieve the approval?",
	gw_msg_sign_confirm_cancel: "Do you want to cancel the approval?",
	gw_msg_sign_cancel_success: "Canceled.",
	gw_msg_sign_network_err: "Result request has failed.",
	gw_msg_sign_agent_err: "The service is not provided. Contact the administrator.",
	gw_msg_sign_not_support_apprdoc: "The type is not allowed to download.",
	gw_msg_sign_not_transform_apprdoc: "After conversion, it is supported.",
    gw_msg_sign_limitread_apprlinkdoc: "You've exceeded the maximum number that can be viewed on a mobile phone.",
	gw_msg_sign_withdraw_opinion: "This document is withdrawed from mobile.",    
	gw_msg_board_authread_err: "You do not have the authority to view this list.",
	gw_msg_board_nolist: "No posts.",
	gw_msg_board_nobldata: "No boards.",
	gw_msg_board_read_noauth_err: "You are not allowed to open the post.",
	gw_msg_board_invalid_enddate: "The end date has to be after today.",
	gw_msg_board_cancel_write: "Do you want to cancel?",
	gw_msg_board_comment_nolist: "No comments",
	gw_msg_board_not_match_passwd_manage: "Management passwords do not match for.",
	gw_msg_schedule_invalid_code: "Invalid Code : ",
	gw_msg_schedule_schedule_nolist: "Empty",
	gw_msg_schedule_check_calendar: "You need to select the calendar at least one.",
	gw_msg_schedule_no_select_calendar: "Please select a calendar.",
	gw_msg_schedule_equip_nolist: "Empty",
	gw_msg_schedule_check_equip: "Please select a facility to reserve.",
	gw_msg_schedule_todo_nolist: "Empty",
	gw_msg_schedule_equip_nothing: "Empty",
	gw_msg_schedule_cancel_add_schedule: "Do you want to cancel?",
	gw_msg_schedule_cancel_add_todo: "Do you want to cancel?",
	gw_msg_schedule_check_reserve_equip: "Equipment/facility is taken.",
	gw_msg_schedule_invalid_letter_err: "It is not allowed to use as \'\"\\.",
	gw_msg_schedule_todo_empty_processrate: "Please enter the progress rate.",
	gw_msg_schedule_todo_progress_invalidvalue: "Progress rate must be a number.",
	gw_msg_schedule_todo_progress_limitvalue: "Progress rate must be between 0 and 100.",
	gw_msg_schedule_period_err: "The period is wrong.",
	gw_msg_schedule_period_timegap_err: "End date should be set to 30 minutes later than start date.",
	gw_msg_schedule_recur_type3_daywk: "Please select a recurrence day.",
	gw_msg_contact_nothing: "Empty",
	gw_msg_org_member_nolist: "No members.",
	gw_msg_org_subdept_nolist: "No sub-dept.",
	gw_msg_absence_not_absmsg: "Please enter your absence message.",
	gw_msg_absence_toolong_msg: "Absence message may not exceed 80 Korean characters (160 English characters) in length.",
	gw_msg_absence_not_period: "Please enter the absence period",
	gw_msg_absence_period_err_1: "The finishing date must come after the starting date.",
	gw_msg_absence_period_err_2: "The finishing date must be after today.",
	gw_msg_absence_no_alter_signer: "Please select a delegate approver.",
	gw_msg_absence_absence_nolist: "No absence on the list.",
	gw_msg_absence_cancel_add_absence: "Cancel absence register?",
	gw_msg_absence_minimum_one_select: "Select at least one to delete",
	gw_msg_password_no_old_password: "Please enter the previous password.",
	gw_msg_password_no_new_password: "Please enter the new password.",
	gw_msg_password_no_new_password_confirm: "Please confirm the new password.",
	gw_msg_password_mismatch_new_password: "New passwords do not match. Please try again.",
	gw_msg_password_sameold_new_password: "New password is identical to the previous password. Please use another new password.",
	gw_msg_password_toolong_password: "Password must be shorter than 30 characters.",
	gw_msg_password_tooshort_password: "Password must be longer than 5 characters.",
	gw_msg_password_no_old_sanc_password: "Please enter the previous password for approval.",
	gw_msg_password_no_new_sanc_password: "Please enter the new password for approval.",
	gw_msg_password_no_new_sanc_password_confirm: "Please confirm the new password for approval.",
	gw_msg_password_mismatch_new_sanc_password: "New passwords  for approval do not match. Please try again.",
	gw_msg_password_sameold_new_sanc_password: "New password for approval is identical to the previous password. Please use another one.",
	gw_msg_password_input_password_for_sanc: "Please enter your login password to change the password for approval.",
	gw_msg_password_tooshort_sanc_password: "Password must be longer than 5 characters.",
	
	gw_error_system_error: "Internal error.Please contact the administrator.",
	gw_error_invalid_request: "Unavailable request. Contact the administrator",
	gw_error_session_expired: "Session expired. Please login again.",
	gw_error_access_denied: "Unauthorized device.Please contact the administrator.",
	gw_error_unauthorized_phone_uid_number: "Unauthorized device.Please contact the administrator.",
	gw_error_unregistered_new_multi_device: "Your device is not registered.",
	gw_error_unregistered_new_multi_device_guide: "Your device is not registered.\nYou can register additional devices, please click the following [Device Registration] button.\nWe will handle the registration of the current device, the confirmation of the administrator.",
	gw_error_appversion_notequal: "The application is outdated.\nPlease upgrade to the newest version.",	
	gw_error_license_filenotfound: "License file not found.",
	gw_error_license_invalid_info: "Invalid license info.",
	gw_error_license_invalid_serverip: "Unlicensed server.",
	gw_error_license_expired: "License expired.",
	gw_error_license_exceeduser: "No. of concurrent logins exceeded.",
	gw_error_file_download: "Download error occurred. Please contact the administrator.",
	gw_error_file_transform: "Conversion error occurred. Please contact the administrator.",
	gw_error_file_unsupported_type: "The file type is not supported.",
	gw_error_failed_result: "Result checking has failed.",
	gw_error_duplicate:"Duplicate error.",
	gw_success:"Approvals were successfully retrieved.",
	gw_fail:"Approvals were not retrieved.",
	gw_fail_view:"Summary has failed to open.",
	gw_view:"Summary view",
	gw_withdraw:"Retrieve Approvals",
	gw_check_doc:"Limited view documents."
};var RES_STRING_JA = {	
		gw_product_name: "グループウェア",

		gw_common_loading_label: "呼び出し中...",
		gw_common_pulldown_label: "放すとリストをアップデートします。",
		gw_common_pulldown_update_label: "アップデートするには下に引っ張ってください。",
		gw_common_pullup_label: "放すとリストが呼び出されます。",
		gw_common_pullup_refresh_label: "より多くのリストを見るには上に引っ張ってください。",

		gw_common_menu_label: "メニュー",
		gw_common_back_label: "以前",
		gw_common_close_label: "閉じる",
		gw_common_write_label: "作成",
		gw_common_edit_label: "編集",
		gw_common_canceledit_label: "編集キャンセル",
		gw_common_selectall_label: "全体選択",
		gw_common_cancelselect_label: "選択キャンセル",
		gw_common_delete_label: "削除",
		gw_common_attach_label: "添付",
		gw_common_username_label: "姓名",
		gw_common_email_label: "E-MAIL",
		gw_common_companyname_label: "会社名",
		gw_common_phone_label: "電話番号",
		gw_common_search_label: "検索",
		gw_common_search_result_label: "検索結果",
		gw_common_add_label: "追加",
		gw_common_select_label: "選択",
		gw_common_show_label: "表示",
		gw_common_hide_label: "隠す",
		gw_common_ok_label: "確認",
		gw_common_set_label: "設定",
		gw_common_change_label: "変更",
		gw_common_cancel_label: "キャンセル",
		gw_common_modify_label: "修正",
		gw_common_option_label: "オプション",
		gw_common_byte_label: "byte",
		gw_common_kilobyte_label: "KB",
		gw_coomon_date_label: "Date",
		gw_coomon_time_label: "Time",
		gw_common_getmore_label: "さらに見る..",
		gw_common_group_label: "グループ",
		gw_common_year_label: "年",
		gw_common_month_label: "月",
		gw_common_day_label: "日",
		gw_common_hour_label: "時",
		gw_common_minute_label: "分",
		gw_common_input_password_title: "パスワード入力",
		gw_common_refresh_label: "更新",
		gw_common_piece_label: "個",
		gw_common_save_label: "保存",
		
		gw_mail_mailwrite_label: "メール作成",
		gw_mail_mailread_label: "メールを読む",
		gw_mail_recvlist_label: "受信箱",
		gw_mail_sendlist_label: "送信箱",
		gw_mail_templist_label: "一時保管箱",
		gw_mail_deletelist_label: "ゴミ箱",
		gw_mail_receiver_label: "受信者",
		gw_mail_sender_label: "送信者",
		gw_mail_cc_label: "CC",
		gw_mail_confirm_receive_label: "受信確認",
		gw_mail_cancel_label: "回収",
		gw_mail_fly_label: "転送",
		gw_mail_reply_label: "返信",
		gw_mail_replyall_label: "全体返信",
		gw_mail_personalbox_label: "個人メール箱",
		gw_mail_personalbox_path: "個人メール箱",
		gw_mail_entire_state_label: "全体状態",
		gw_mail_bcc_label: "BCC",
		gw_mail_ccbcc_lable: "CC、BCC",
		gw_mail_urgency_label: "緊急",
		gw_mail_security_label: "セキュリティー",
		gw_mail_title_label: "件名",
		gw_mail_send_label: "送信",
		gw_mail_include_org_attach_label: "既存添付",
		gw_mail_include_org_message: "原文含む",
		gw_mail_dbmail_sender_label: "送信",
		gw_mail_dbmail_to_label: "受信",
		gw_mail_dbmail_date_label: "日付",
		gw_mail_dbmail_original_message: "原文メッセージ",
		gw_mail_notitle_label: "件名なし",
		gw_mail_choose_samename_label: "同名ユーザ選択",
		gw_mail_select_recv: "受信者選択",
		
		gw_schedule_label: "日程",
		gw_schedule_todo_label: "業務",
		gw_schedule_list_label: "日程リスト",
		gw_schedule_equip_list_label: "設備予約リスト",
		gw_schedule_equip_detaillist_label: "設備予約詳細リスト",
		gw_schedule_add_label: "日程/設備予約追加",
		gw_schedule_schadd_label: "日程/設備予約追加",
		gw_schedule_schmodify_label: "日程/設備予約修正",
		gw_schedule_sch6add_label: "日程追加",
		gw_schedule_sch6modify_label: "日程修正",
		gw_schedule_todo_list_label: "業務リスト",
		gw_schedule_todo_add_label: "業務追加",
		gw_schedule_todo_modify_label: "業務修正",
		gw_schedule_search_label: "日程/業務検索",
		gw_schedule2_search_label: "日程検索",
		gw_schedule_sch_search_label: "日程検索結果",
		gw_schedule_todo_search_label: "業務検索結果",
		gw_schedule_base_date_label: "基準日 : ",
		gw_schedule_daily_label: "日間",
		gw_schedule_weekly_label: "週間",
		gw_schedule_monthly_label: "月間",
		gw_schedule_monthplan_label: "月間計画",
		gw_schedule_today_label: "今日",
		gw_schedule_shared_calendar_label: "共有日程",
		gw_schedule_all_calendar_label: "全体カレンダー",
		gw_schedule_my_and_shared_calendar_label: "マイカレンダー / + 共有日程",
		gw_schedule_my_calendar_label: "マイカレンダー",
		gw_schedule_dept_calendar_label: "部署カレンダー",
		gw_schedule_company_calendar_label: "全社カレンダー",
		gw_schedule_user_calendar_label: "ユーザカレンダー",
		gw_schedule_calendar_count_label: "件",
		gw_schedule_schedule_view_label: "日程表示",
		gw_schedule_equipment_view_label: "設備予約表示",
		gw_schedule_todo_view_label: "業務表示",
		gw_schedule_title_label: "件名",
		gw_schedule_category_label: "カテゴリー",
		gw_schedule_owner_label: "所有者",
		gw_schedule_writer_label: "作成者",
		gw_schedule_repeat_label: "繰り返し",
		gw_schedule_term_label: "期間",
		gw_schedule_write_date_label: "作成日",
		gw_schedule_start_date_label: "開始日",
		gw_schedule_end_date_label: "終了日",
		gw_schedule_day_schedule_label: "終日の予定表",
		gw_schedule_period_label: "期間設定",
		gw_schedule_period_day_1_label: "当日",
		gw_schedule_period_day_2_label: "7日",
		gw_schedule_period_day_3_label: "1ヶ月",
		gw_schedule_period_day_4_label: "3ヶ月",
		gw_schedule_calendar_label: "カレンダー",
		gw_schedule_attendee_label: "Attendee",
		gw_schedule_equipment_label: "設備",
		gw_schedule_all_equipment_label: "全体設備",
		gw_schedule_content_label: "内容",
		gw_schedule_reserve_equipment_label: "設備予約",
		gw_schedule_alarm_label: "お知らせメール",
		gw_schedule_alarm_label_0: "瞬時に",
		gw_schedule_alarm_label_m: "分前",
		gw_schedule_alarm_label_h: "時間前",
		gw_schedule_alarm_label_d: "日前",
		gw_schedule_alarm_label_w: "週間前",
		gw_schedule_alarm_label_M: "ヶ月前",
		gw_schedule_alarm_label_0mail: "今電子メールを送信",
		gw_schedule_importance_label: "重要度",
		gw_schedule_weight_high: "高",
		gw_schedule_weight_medium: "中",
		gw_schedule_weight_low: "低",
		gw_schedule_status_label: "状態",
		gw_schedule_progress_label: "進捗率",
		gw_schedule_status_finish_label: "完了",
		gw_schedule_status_delay_label: "延期",
		gw_schedule_status_cancel_label: "キャンセル",
		gw_schedule_status_progress_label: "進行",
		gw_schedule_status_notfinish_label: "未完了",
		gw_schedule_not_label: "なし",
		gw_schedule_recur_type0: "繰り返しなし",
		gw_schedule_recur_type1: "毎日(月~日)",
		gw_schedule_recur_type2: "平日(月~金)",
		gw_schedule_recur_type3: "毎週",
		gw_schedule_recur_type4: "毎月",
		gw_schedule_recur_type5: "毎月(曜日)",
		gw_schedule_recur_type6: "毎年",
		gw_schedule_recur_cycle_weekly: "週ごとに",
		gw_schedule_recur_cycle_weekly_num: "{{num}}週間ごとに",
		gw_schedule_recur_cycle_type4_day: "毎月 {{dd}}",
		gw_schedule_recur_cycle_type4_ord_day_wk: "毎月 {{ord}} {{dayWk}}",
		gw_schedule_recur_cycle_type4_last_wk: "Monthly last {{dayWk}}",
		gw_schedule_recur_cycle_type6_date: "毎年{{mmdd}}",
		gw_schedule_recur_cycle_type6_last: "毎年{{mm}}の最後の日",
		gw_schedule_recur_cycle_type6_ord_day_wk: "毎年 {{mm}} {{ord}} {{dayWk}}",
		gw_schedule_recur_cycle_type6_last_wk: "Every year {{mm}} last {{dayWk}}",
		gw_schedule_recur_times: "last",
		gw_schedule_recur_times0: "最初の",
		gw_schedule_recur_times1: "第二",
		gw_schedule_recur_times2: "第三",
		gw_schedule_recur_times3: "第四",
		gw_schedule_recur_times4: "5番目",
		gw_schedule_equipmentgroup_label : "設備グループ",

		gw_schedule_have_label: "あり",
		gw_schedule_ownertype_user: "個人日程",
		gw_schedule_ownertype_dept: "部署日程",
		gw_schedule_security_level_label: "セキュリティー設定",
		gw_schedule_security_type_a: "誰でも閲覧可",
		gw_schedule_security_type_d: "部署員のみ閲覧可",
		gw_schedule_security_type_s: "部署員/直属上下部署員閲覧可",
		gw_schedule_security_type_o: "所有者のみ閲覧可",
		gw_schedule_add_security_level_label: "公開範囲",
		gw_schedule_add_security_type_a: "全員に",
		gw_schedule_add_security_type_d: "部署員に",
		gw_schedule_add_security_type_s: "直属上下部署員に",
		gw_schedule_add_security_type_o: "所有者のみに",
		gw_schedule_select_calendar: "カレンダー選択",
		gw_schedule_select_equipment: "設備選択",
		gw_schedule_select_allcallist: "全てのカレンダー選択",
		gw_schedule_select_allequiplist: "全体設備リスト",
		
		gw_schedule_delete_recur_title: "Recurring schedule",
		gw_schedule_delete_recur_option1: "Delete all recurring events",
		gw_schedule_delete_recur_option2: "Only delete selected events",
		gw_schedule_delete_recur_option4: "Delete future events with selected events",
		gw_schedule_delete_recur_option_select: "Please select a removal type.",		
		gw_schedule_edit_recur_title: "繰り返し設定したスケジュール",
		gw_schedule_edit_recur_option1: "全繰り返しスケジュールの変更",
		gw_schedule_edit_recur_option2: "選択された日程だけを変更",
		gw_schedule_edit_recur_option4: "選択されたスケジュールを含む以降のスケジュールの変更",
		
		gw_board_name_label: "掲示板",
		gw_board_recentlist_label: "最近掲示文",
		gw_board_alllist_label: "全体掲示板",
		gw_board_favlist_label: "お気に入り掲示板",
		gw_board_comment_label: "意見",
		gw_board_read_label: "掲示文閲覧",
		gw_board_postername_label: "掲示者",
		gw_board_write_title: "掲示物作成",
		gw_board_wirte_label: "掲示",
		gw_board_enddate_label: "終了日",
		gw_board_everlast_label: "永久",
		gw_board_preface_label: "ヘッダー",
		gw_board_select_preface: "ヘッダーの選択",
		gw_board_title_label: "件名",
		gw_board_write_comment_label: "意見作成",
		gw_board_write_of_comment_label: "コメントを書く",

		gw_contact_personal_label: "個人アドレス帳",
		gw_contact_dept_label: "部署アドレス帳",	
		gw_contact_group_label: "グループ内容表示",
		gw_contact_personal_info_label: "個人情報",
		gw_contact_org_info_label: "会社情報",
		gw_contact_gender_m: "男",
		gw_contact_gender_f: "女",
		gw_contact_search_name: "名前でアドレス帳検索",
		gw_contact_search_email: "MAILでアドレス帳検索",
		gw_contact_search_orgname: "会社名でアドレス帳検索",
		gw_contact_search_phone: "電話番号でアドレス帳検索",
	
		gw_org_subdept_label: "下位部署",
		gw_org_member_label: "構成員",
		gw_org_tree_label: "組織図",
		gw_org_select_label: "組織図選択",
		gw_org_userinfo_label: "ユーザ情報",
		gw_org_includesub_label: "下位含む",
		gw_org_search_deptname: "部署名で部署検索",
		gw_org_search_username: "名前で構成員検索",
		gw_org_search_email: "MAILで構成員検索",
		gw_org_search_phone: "電話番号で構成員検索",
		gw_org_search_mobile: "携帯で構成員検索",
		gw_org_search_empcode: "従業員番号で構成員検索",
		gw_org_search_posname: "役職名で構成員検索",
		gw_org_search_rankname: "職級名で構成員検索",
		gw_org_search_dutyname: "職責名で構成員検索",
		gw_org_search_business: "担当業務で構成員検索",		
		gw_org_root_label: "最上位部署",
		
		gw_sign_label: "決裁",

		gw_sign_waitlist_label: "決裁待機",
		gw_sign_gongramwaitlist_label: "供覧待機",
		gw_sign_gongramcompletelist_label: "供覧完了",
		gw_sign_nowlist_label: "決裁進行",
		gw_sign_receiptwaitlist_label: "受付待機",
		gw_sign_userprocessedlist_label: "個人文書箱[決裁文書]",
		gw_sign_mycompletelist_label: "個人文書箱[完了文書]",
		gw_sign_completelist_label: "記録物台帳[完了箱]",
		gw_sign_dispatchlist_label: "발송처리",
		
		gw_sign_details_label: "決裁本文",
		gw_sign_approve_label: "決裁",
		gw_sign_reject_label: "返戻",
		gw_sign_junkyul_label: "専決",
		gw_sign_postpone_label: "保留",
		gw_sign_gongram_label: "供覧確認",
		gw_sign_confirm_label: "参照確認",
		gw_sign_write_comment_label: "意見作成",
		gw_sign_cancel_write_comment_label: "意見作成キャンセル",
		gw_sign_show_comment_label: "意見表示",
		gw_sign_approve_flow_label: "決裁進行状態",
		gw_sign_withdraw_label: "決裁回収",
		gw_sign_cancel_label: "決裁キャンセル",
		gw_sign_show_summary_label: "要約",
		gw_sign_show_linkdoc_label: "関連文書",
		gw_sign_input_password_title: "決裁パスワード入力",
		gw_sign_dispatch_label: "発送処理",
		
		gw_sign_signlist_type_all_label: "全体",
		gw_sign_signlist_type_1_label: "一般文書",
		gw_sign_signlist_type_3_label: "受信文書",
		gw_sign_signlist_type_2_label: "発信文書",
		gw_sign_signlist_type_7_label: "報告文書",
		gw_sign_signlist_type_5_label: "監査文書",
		gw_sign_signlist_type_4_label: "協調文書",

		gw_sign_receiptlist_type_all_label: "全体",
		gw_sign_receiptlist_type_2_label: "到着",
		gw_sign_receiptlist_type_4_label: "受付",
		gw_sign_receiptlist_type_16_label: "返送",
		gw_sign_receiptlist_type_8192_label: "内部返送",
		gw_sign_floworder_label: "順番",
		gw_sign_approval_label: "処理方法",
		
		gw_settings_label: "設定",
		gw_settings_absence_label: "不在設定",
		gw_settings_userinfo_label: "個人情報設定",
		gw_settings_password_label: "パスワード設定",
		gw_settings_license_label: "ライセンス情報",

		gw_settings_absence_list_label: "不在リスト",
		gw_settings_absence_add_label: "不在追加",
		gw_settings_absence_save_label: "保存",
		gw_settings_absence_absadd_label: "不在追加",
		gw_settings_absence_modify_label: "不在の変更",
		gw_settings_absence_absmodify_label: "不在修正",
		gw_settings_absence_copy_label: "コピー",
		gw_settings_absence_set_label: "不在設定",
		gw_settings_absence_remove_label: "不在解除",
		gw_settings_absence_period_label: "期間",
		gw_settings_absence_period_start_label: "不在開始",
		gw_settings_absence_period_end_label: "不在終了",
		gw_settings_absence_period_reason_label: "不在理由",
		gw_settings_absence_period_reason_1_label: "教育",
		gw_settings_absence_period_reason_2_label: "出張",
		gw_settings_absence_period_reason_3_label: "外出",
		gw_settings_absence_period_reason_4_label: "休暇",
		gw_settings_absence_period_reason_5_label: "早退",
		gw_settings_absence_period_reason_6_label: "有給休暇",
		gw_settings_absence_period_reason_7_label: "病気休暇",
		gw_settings_absence_period_reason_8_label: "公的休暇",
		gw_settings_absence_period_reason_9_label: "特別休暇",
		gw_settings_absence_period_reason_10_label: "欠勤",
		gw_settings_absence_period_reason_11_label: "時刻",
		gw_settings_absence_period_reason_12_label: "不在",
		gw_settings_absence_period_reason_13_label: "休職",
		gw_settings_absence_period_reason_14_label: "退職",
		gw_settings_absence_mail_label: "メール設定",
		gw_settings_absence_mail_reply_msg_label: "メール<BR>自動応答<BR>メッセージ", // tkofs 不在メッセージ ->
		gw_settings_absence_mail_alt_rcpt_label: "代理受信者",
		gw_settings_absence_sanc_label: "決裁設定",
		gw_settings_absence_sanc_alt_signer_label: "代理決裁者",
		gw_settings_absence_sanc_handling1_label: "決裁後進行文書",
		gw_settings_absence_sanc_handling1_0_label: "決裁待機",
		gw_settings_absence_sanc_handling1_1_label: "代理決裁者",
		gw_settings_absence_sanc_handling1_2_label: "決裁しない",
		gw_settings_absence_sanc_handling2_label: "決裁後完了文書",
		gw_settings_absence_sanc_handling2_0_label: "決裁待機",
		gw_settings_absence_sanc_handling2_1_label: "代理決裁者",
		
		gw_settings_user_alias_label: "ニックネーム",
		gw_settings_user_phone_label: "電話番号",
		gw_settings_user_fax_label: "ファックス",
		gw_settings_user_mobile_label: "携帯番号",
		gw_settings_user_business_label: "担当業務",
		
		gw_settings_password: "パスワード設定",
		gw_settings_password_cbloginpasswd: "ログインパスワード変更",
		gw_settings_password_txtoldloginpasswd: "以前パスワード",
		gw_settings_password_txtloginpasswd: "新しいパスワード",
		gw_settings_password_txtloginpasswd_confirm: "パスワード確認",
		gw_settings_password_rdosanc: "決裁パスワード",
		gw_settings_password_rdosanc_same: "同じ値に変更",
		gw_settings_password_rdosanc_cancel: "別途指定",
		gw_settings_password_cbsancpasswd: "決裁パスワード変更",
		gw_settings_password_txtoldsancpasswd: "以前パスワード",
		gw_settings_password_txtsancpasswd: "新しいパスワード",
		gw_settings_password_txtsancpasswd_confirm: "パスワード確認",
		gw_settings_password_cbsancpasswd_check: "決裁時パスワード確認",
		gw_settings_password_txtsancpasswd_check: "ログインパスワード",
		gw_settings_password_rdosanc_confirm: "確認？",
		gw_settings_password_rdosanc_confirm_confirm: "確認",
		gw_settings_password_rdosanc_confirm_cancel: "確認しない",
		
		gw_msg_common_err: "エラーが発生しました。",
		gw_msg_common_confirm_delete: "削除しますか?",
		gw_msg_common_no_change: "変更事項がありません。",
		gw_msg_common_confirm: "設定値を変更しますか?",
		gw_msg_common_save_confirm: "設定値を保存しますか?",
		gw_msg_common_change_success: "変更されました。",
		gw_msg_common_nosearchdata: "検索結果がありません。 ", 
		gw_msg_common_input_password: "パスワードを入力してください。",
		gw_msg_common_wrong_password: "パスワードが違います。",
		gw_msg_common_nosearch_title: "件名なし検索",
		gw_msg_common_inputsubject: "件名を入力してください。",
		gw_msg_common_toolong_msg: "件名が長いです。",
		gw_msg_common_toolong_summary: "本文が長いです。",
		gw_msg_common_success: "処理されました。",
		gw_msg_common_fail: "失敗されました。",
		gw_msg_common_save: "保存されました。",
		gw_msg_mail_nolist: "受信箱にメールがありません。",
		gw_msg_mail_noselect: "選択したメールがありません。",
		gw_msg_mail_confirm_cancel: "選択したメールを回収しますか?",
		gw_msg_mail_confirm_delete: "選択したメールを削除しますか?",
		gw_msg_mail_cancel_success: "件のメールが回収されました。",
		gw_msg_mail_delete_success: "削除されました。",
		gw_msg_mail_already_recovered: "すでに回収したメールです。",
		gw_msg_mail_not_read: "未読のメールです。",
		gw_msg_mail_noReceiver: "受信者情報がありません。",
		gw_msg_mail_inputsubject: "件名を入力してください。",
		gw_msg_mail_inputbody: "内容を入力してください。",
		gw_msg_mail_inputto: "受信者が指定されていません。",
		gw_msg_mail_erroradress: "受信者の(外部)メールアドレス形式に誤りがあります。",
		gw_msg_mail_smtp: "先ずはグループウェアポータルでウェブメールサーバ設定を行ってください。",
		gw_msg_mail_mismatch_org: "一致するメンバーまたは、部署がありません。",
		gw_msg_mail_write_success: "メールが発送されました。",
		gw_msg_mail_choose_samename: "同名のユーザがいます。ユーザを指定してください。",
		gw_msg_mail_invalid_email: "メールアドレスに誤りがあります。",
		gw_msg_mail_cancel_writeMail: "メール作成をキャンセルしますか?",
		gw_msg_mail_input_receiver: "受信者を入力してください。",
		gw_msg_mail_input_cc: "CCを入力してください。",
		gw_msg_mail_input_bcc: "BCCを入力してください。",
		gw_msg_mail_canceled_not_deleted: "すでに回収したメールです。",
		gw_msg_mail_externaldomain_not_sended: "受信者の外部ドメインを持つユーザーがおり、メールを送信することができません.",
		gw_msg_sign_nolist: "決裁箱に文書がありません。",
		gw_msg_sign_external_doc: "非電子文書です。",
		gw_msg_sign_external6_doc: "手書き文書です。",
		gw_msg_sign_read_noauth_err: "文書閲覧権限がありません。",
		gw_msg_sign_input_password: "決裁パスワードを入力してください。",
		gw_msg_sign_wrong_password: "パスワードが違います。",
		gw_msg_sign_confirm_approve: "決裁を要請しますか?",
		gw_msg_sign_confirm_reject: "返戻を要請しますか?",
		gw_msg_sign_confirm_postpone: "返保留しますか?",
		gw_msg_sign_confirm_gongram: "Do you want to mark as viewed?",	
		gw_msg_sign_confirm_dispatch: "Do you want to dispatch?",
		gw_msg_sign_approve_success: "決裁承認が処理されました。",
		gw_msg_sign_approve_fail: "決裁承認が失敗されました。",
		gw_msg_sign_reject_success: "返戻処理されました。",
		gw_msg_sign_reject_fail: "返戻されませんでした。",
		gw_msg_sign_postpone_success: "保留処理しています.",
		gw_msg_sign_postpone_fail: "保留されていませんでした.",
		gw_msg_sign_gongram_success: "Mark as viewed.",
		gw_msg_sign_gongram_fail: "Mark as viewed failed.",
		gw_msg_sign_confirm_withdraw: "決裁を回収しますか。",
		gw_msg_sign_confirm_cancel: "決裁をキャンセルしますか。",
		gw_msg_sign_cancel_success: "キャンセル処理された.",
		gw_msg_sign_network_err: "ネットワーク環境が不安定な為、決裁要請結果確認に失敗しました。",
		gw_msg_sign_agent_err: "モバイル決裁システムにエラーが発生しました。 管理者に問い合わせしてください。",
		gw_msg_sign_not_support_apprdoc: "モバイルでは支援しない決裁文書です。",
		gw_msg_sign_not_transform_apprdoc: "文書変換なしで閲覧できない決裁文書です。",
		gw_msg_sign_limitread_apprlinkdoc: "モバイルで閲覧可能な最大数を超えました。",
		gw_msg_sign_withdraw_opinion: "モバイルで回収した文書です。",
		gw_msg_board_authread_err: "リストの閲覧権限がありません。",
		gw_msg_board_nolist: "掲示文がありません。",
		gw_msg_board_nobldata: "登録された掲示板がありません。",
		gw_msg_board_read_noauth_err: "掲示文の参照権限がありません。",
		gw_msg_board_invalid_enddate: "終了日は今日以後のみ可能です。",
		gw_msg_board_cancel_write: "掲示文作成をキャンセルしますか?",
		gw_msg_board_comment_nolist: "登録されたコメントはありません",
		gw_msg_board_not_match_passwd_manage: "修正/削除のためのパスワードが一致しません",
		gw_msg_schedule_invalid_code: "有効でないコード : ",
		gw_msg_schedule_schedule_nolist: "登録された日程がありません。",
		gw_msg_schedule_check_calendar: "カレンダーは1個以上を選択してください。",
		gw_msg_schedule_no_select_calendar: "カレンダーを選択してください。",
		gw_msg_schedule_equip_nolist: "登録された設備日程がありません。",
		gw_msg_schedule_check_equip: "予約する設備を選択します。",
		gw_msg_schedule_todo_nolist: "登録された業務がありません。",
		gw_msg_schedule_equip_nothing: "登録された設備がありません。",
		gw_msg_schedule_cancel_add_schedule: "日程作成をキャンセルしますか?",
		gw_msg_schedule_cancel_add_todo: "業務作成をキャンセルしますか?",
		gw_msg_schedule_check_reserve_equip: "設備がすでに予約されています。",
		gw_msg_schedule_invalid_letter_err: "\'\"\\のような文字は使用できません。",
		gw_msg_schedule_todo_empty_processrate: "進捗率を入力してください。",
		gw_msg_schedule_todo_progress_invalidvalue: "進捗率は数字のみ入力できます。",
		gw_msg_schedule_todo_progress_limitvalue: "進捗率は0 ～ 100までの数字で入力できます。",
		gw_msg_schedule_period_err: "期間設定に誤りがあります。",
		gw_msg_schedule_period_timegap_err: "終了日時は開始日時より30分後に設定する必要があります。",
		gw_msg_schedule_recur_type3_daywk: "繰り返し曜日を選択します。",
		gw_msg_contact_nothing: "登録されたアドレス帳がありません。",
		gw_msg_org_member_nolist: "構成員がいません。",
		gw_msg_org_subdept_nolist: "下位部署がありません。",
		gw_msg_absence_not_absmsg: "不在メッセージを入力してください。",
		gw_msg_absence_toolong_msg: "不在メッセージはハングル80字(英文160字)内で入力できます。",
		gw_msg_absence_not_period: "不在期間を入力してください。",
		gw_msg_absence_period_err_1: "不在終了日は不在開始日以後に設定してください。",
		gw_msg_absence_period_err_2: "不在終了日は現在以後に設定してください。",
		gw_msg_absence_no_alter_signer: "代理決裁者が指定されていません。",
		gw_msg_absence_absence_nolist: "登録した不在がありません。",
		gw_msg_absence_cancel_add_absence: "不在作成をキャンセルしますか?",
		gw_msg_absence_minimum_one_select: "削除の為、1個以上を選択してください。",
		gw_msg_password_no_old_password: "以前ログインパスワードを入力してください。",
		gw_msg_password_no_new_password: "新しいログインパスワードを入力してください。",
		gw_msg_password_no_new_password_confirm: "新しいログインパスワード確認の為、新しいログインパスワードを入力してください。",
		gw_msg_password_mismatch_new_password: "新しいログインパスワードが同じに入力されていません。再度入力してください。",
		gw_msg_password_sameold_new_password: "新しいログインパスワードが以前のログインパスワードと同じです。他のパスワードを入力してください。",
		gw_msg_password_toolong_password: "パスワードは最大30文字以内に設定してください。",
		gw_msg_password_tooshort_password: "パスワードは最小5文字以上に設定してください。",
		gw_msg_password_no_old_sanc_password: "以前の決裁パスワードを入力してください。",
		gw_msg_password_no_new_sanc_password: "新しい決裁パスワードを入力してください。",
		gw_msg_password_no_new_sanc_password_confirm: "新しい決裁パスワード確認の為、新しい決裁パスワードを入力してください。",
		gw_msg_password_mismatch_new_sanc_password: "新しい決裁パスワードが同じに入力されていません。 再度入力してください。",
		gw_msg_password_sameold_new_sanc_password: "新しい決裁パスワードが以前の決裁パスワードと同じです。 他のパスワードを入力してください。",
		gw_msg_password_input_password_for_sanc: "決裁時パスワード確認の変更の為、ログインパスワードを入力してください。",
		gw_msg_password_tooshort_sanc_password: "決裁パスワードは最小5文字以上に設定してください。",
		
		gw_error_system_error: "内部エラーが発生しました。 管理者に問い合わせしてください。",
		gw_error_invalid_request: "有効でない要請です。 管理者に問い合わせしてください。",
		gw_error_session_expired: "セッションが有効ではありません。再度ログインしてください。",
		gw_error_access_denied: "認証されていないユーザです。 ログイン後、使用してください。",
		gw_error_unauthorized_phone_uid_number: "認証されていない端末です。管理者に問い合わせしてください。",
		gw_error_unregistered_new_multi_device: "登録されていない端末です。",
		gw_error_unregistered_new_multi_device_guide: "登録されていない端末です。\n端末を追加登録するには下の[端末登録]をクリックしてください。\n管理者確認の上、現在の端末の登録を行います。",
		gw_error_appversion_notequal: "モバイルグループウェアのアプリがアップデートされました。 アプリを再度インストールしてください。",	
		gw_error_license_filenotfound: "ライセンスファイルが存在しません。",
		gw_error_license_invalid_info: "ライセンス情報が有効ではありません。",
		gw_error_license_invalid_serverip: "ライセンスが発行されていないサーバです。",
		gw_error_license_expired: "ライセンス期間が満了しました。",
		gw_error_license_exceeduser: "接続ユーザ数を超えました。",
		gw_error_file_download: "文書ダウンロードエラーが発生しました。管理者に問い合わせしてください。",
		gw_error_file_transform: "文書変換エラーが発生しました。管理者に問い合わせしてください。",
		gw_error_file_unsupported_type: "ダウンロードできないファイル形式です。",
		gw_error_failed_result: "結果確認が失敗されました。",
		gw_error_duplicate:"重複エラーです。",
		gw_success:"決裁文書が回収されました。",
		gw_fail:"決裁文書が回収されませんでした。",
		gw_fail_view:"要約のプレビューに失敗しました。",
		gw_view:"要約のプレビュー表示",
		gw_withdraw:"決裁回収",
		gw_check_doc:"閲覧制限文書"
};var RES_STRING_KO = {	
	gw_product_name: "그룹웨어",

	gw_common_loading_label: "불러오는중...",
	gw_common_pulldown_label: "놓으시면 목록을  업데이트합니다.",
	gw_common_pulldown_update_label: "업데이트 하려면 아래로 당기세요",
	gw_common_pullup_label: "놓으시면 목록을 가져옵니다.",
	gw_common_pullup_refresh_label: "더 많은 목록을 보고싶다면 위로 당기세요",

	gw_common_menu_label: "메뉴",
	gw_common_back_label: "이전",
	gw_common_close_label: "닫기",
	gw_common_write_label: "쓰기",
	gw_common_edit_label: "편집",
	gw_common_canceledit_label: "편집취소",
	gw_common_selectall_label: "전체선택",
	gw_common_cancelselect_label: "선택취소",
	gw_common_delete_label: "삭제",
	gw_common_attach_label: "첨부",
	gw_common_username_label: "성명",
	gw_common_email_label: "EMAIL",
	gw_common_companyname_label: "회사명",
	gw_common_phone_label: "전화번호",
	gw_common_search_label: "검색",
	gw_common_search_result_label: "검색결과",
	gw_common_add_label: "추가",
	gw_common_select_label: "선택",
	gw_common_show_label: "보기",
	gw_common_hide_label: "감추기",
	gw_common_ok_label: "확인",
	gw_common_set_label: "설정",
	gw_common_change_label: "변경",
	gw_common_cancel_label: "취소",
	gw_common_modify_label: "수정",
	gw_common_option_label: "옵션",
	gw_common_byte_label: "byte",
	gw_common_kilobyte_label: "KB",
	gw_coomon_date_label: "Date",
	gw_coomon_time_label: "Time",
	gw_common_getmore_label: "더보기..",
	gw_common_group_label: "그룹",
	gw_common_year_label: "년",
	gw_common_month_label: "월",
	gw_common_day_label: "일",
	gw_common_hour_label: "시",
	gw_common_minute_label: "분",
	gw_common_input_password_title: "암호입력",
	gw_common_refresh_label: "새로고침",
	gw_common_piece_label: "개",
	gw_common_save_label: "저장",
	
	gw_mail_mailwrite_label: "편지쓰기",
	gw_mail_mailread_label: "편지읽기",
	gw_mail_recvlist_label: "받은 편지함",
	gw_mail_sendlist_label: "보낸 편지함",
	gw_mail_templist_label: "임시 편지함",
	gw_mail_deletelist_label: "지운 편지함",
	gw_mail_receiver_label: "받는이",
	gw_mail_sender_label: "보낸이",
	gw_mail_cc_label: "참조인",
	gw_mail_confirm_receive_label: "수신확인",
	gw_mail_cancel_label: "회수",
	gw_mail_fly_label: "전달",
	gw_mail_reply_label: "답장",
	gw_mail_replyall_label: "전체답장",
	gw_mail_personalbox_label: "개인 편지함",
	gw_mail_personalbox_path: "개인 편지함",
	gw_mail_entire_state_label: "전체상태",
	gw_mail_bcc_label: "숨은참조",
	gw_mail_ccbcc_lable: "참조,숨은참조",
	gw_mail_urgency_label: "긴급",
	gw_mail_security_label: "보안",
	gw_mail_title_label: "제목",
	gw_mail_send_label: "보내기",
	gw_mail_include_org_attach_label: "기존첨부",
	gw_mail_include_org_message: "원문포함",
	gw_mail_dbmail_sender_label: "송신",
	gw_mail_dbmail_to_label: "수신",
	gw_mail_dbmail_date_label: "날짜",
	gw_mail_dbmail_original_message: "원본 메시지",
	gw_mail_notitle_label: "제목없음",
	gw_mail_choose_samename_label: "동명이인 선택",
	gw_mail_select_recv: "수신자 선택",
	
	gw_schedule_label: "일정",
	gw_schedule_todo_label: "할일",
	gw_schedule_list_label: "일정 목록",
	gw_schedule_equip_list_label: "설비예약 목록",
	gw_schedule_equip_detaillist_label: "설비예약 상세목록",
	gw_schedule_add_label: "일정/설비예약 추가",
	gw_schedule_schadd_label: "일정/설비예약 추가",
	gw_schedule_schmodify_label: "일정/설비예약 수정",
	gw_schedule_sch6add_label: "일정 추가",
	gw_schedule_sch6modify_label: "일정 수정",
	gw_schedule_todo_list_label: "할일 목록",
	gw_schedule_todo_add_label: "할일 추가",
	gw_schedule_todo_modify_label: "할일 수정",
	gw_schedule_search_label: "일정/할일 검색",
	gw_schedule2_search_label: "일정 검색",
	gw_schedule_sch_search_label: "일정 검색결과",
	gw_schedule_todo_search_label: "할일 검색결과",
	gw_schedule_base_date_label: "기준 일 : ",
	gw_schedule_daily_label: "일간",
	gw_schedule_weekly_label: "주간",
	gw_schedule_monthly_label: "월간",
	gw_schedule_monthplan_label: "월간계획",
	gw_schedule_today_label: "오늘",
	gw_schedule_shared_calendar_label: "공유일정",
	gw_schedule_all_calendar_label: "전체달력",
	gw_schedule_my_and_shared_calendar_label: "나의달력 + 공유일정",
	gw_schedule_my_calendar_label: "나의달력",
	gw_schedule_dept_calendar_label: "부서달력",
	gw_schedule_company_calendar_label: "전사달력",
	gw_schedule_user_calendar_label: "사용자달력",
	gw_schedule_calendar_count_label: "건",
	gw_schedule_schedule_view_label: "일정 보기",
	gw_schedule_equipment_view_label: "설비예약 보기",
	gw_schedule_todo_view_label: "할일보기",
	gw_schedule_title_label: "제목",
	gw_schedule_category_label: "유형",
	gw_schedule_owner_label: "소유자",
	gw_schedule_writer_label: "작성자",
	gw_schedule_repeat_label: "반복",
	gw_schedule_term_label: "기간",
	gw_schedule_write_date_label: "작성일",
	gw_schedule_start_date_label: "시작일",
	gw_schedule_end_date_label: "종료일",
	gw_schedule_day_schedule_label: "종일일정",
	gw_schedule_period_label: "기간설정",
	gw_schedule_period_day_1_label: "당일",
	gw_schedule_period_day_2_label: "7일",
	gw_schedule_period_day_3_label: "1개월",
	gw_schedule_period_day_4_label: "3개월",
	gw_schedule_calendar_label: "달력",
	gw_schedule_attendee_label: "공유자",
	gw_schedule_equipment_label: "설비선택",
	gw_schedule_all_equipment_label: "전체설비",
	gw_schedule_content_label: "내용",
	gw_schedule_reserve_equipment_label: "설비예약",
	gw_schedule_alarm_label: "알림",
	gw_schedule_alarm_label_0: "즉시알림",
	gw_schedule_alarm_label_m: "분 전",
	gw_schedule_alarm_label_h: "시간 전",
	gw_schedule_alarm_label_d: "일 전",
	gw_schedule_alarm_label_w: "주 전",
	gw_schedule_alarm_label_M: "달 전",
	gw_schedule_alarm_label_0mail: "메일 즉시 발송",
	gw_schedule_importance_label: "중요도",
	gw_schedule_weight_high: "높음",
	gw_schedule_weight_medium: "중간",
	gw_schedule_weight_low: "낮음",
	gw_schedule_status_label: "상태",
	gw_schedule_progress_label: "진척율",
	gw_schedule_status_finish_label: "완료",
	gw_schedule_status_delay_label: "연기",
	gw_schedule_status_cancel_label: "취소",
	gw_schedule_status_progress_label: "진행",
	gw_schedule_status_notfinish_label: "미완료",
	gw_schedule_not_label: "없음",
	gw_schedule_recur_type0: "반복 없음",
	gw_schedule_recur_type1: "매일(월~일)",
	gw_schedule_recur_type2: "평일(월~금)",
	gw_schedule_recur_type3: "매주",
	gw_schedule_recur_type4: "매월",
	gw_schedule_recur_type5: "매월(요일)",
	gw_schedule_recur_type6: "매년",
	// 추가
	gw_schedule_recur_cycle_weekly: "주마다",
	gw_schedule_recur_cycle_weekly_num: "{{num}}주마다",
	gw_schedule_recur_cycle_type4_day: "매월 {{dd}}",
	gw_schedule_recur_cycle_type4_ord_day_wk: "매월 {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type4_last_wk: "매월 마지막 {{dayWk}}",
	gw_schedule_recur_cycle_type6_date: "매년 {{mmdd}}",
	gw_schedule_recur_cycle_type6_last: "매년 {{mm}} 마지막 날",
	gw_schedule_recur_cycle_type6_ord_day_wk: "매년 {{mm}} {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type6_last_wk: "매년 {{mm}} 마지막 {{dayWk}}",
	gw_schedule_recur_times: "마지막",
	gw_schedule_recur_times0: "첫 번째",
	gw_schedule_recur_times1: "두 번째",
	gw_schedule_recur_times2: "세 번째",
	gw_schedule_recur_times3: "네 번째",
	gw_schedule_recur_times4: "다섯 번째",
	// 추가 -end
	gw_schedule_equipmentgroup_label : "설비그룹",

	gw_schedule_have_label: "있음",
	gw_schedule_ownertype_user: "개인일정",
	gw_schedule_ownertype_dept: "부서일정",
	gw_schedule_security_level_label: "보안설정",
	gw_schedule_security_type_a: "누구나 읽기 가능",
	gw_schedule_security_type_d: "부서원만 읽기 가능",
	gw_schedule_security_type_s: "부서원/직속상하위 부서원 읽기 가능",
	gw_schedule_security_type_o: "소유자만 읽기 가능",
	gw_schedule_add_security_level_label: "공개범위",
	gw_schedule_add_security_type_a: "모두에게",
	gw_schedule_add_security_type_d: "부서원에게",
	gw_schedule_add_security_type_s: "직속상하위 부서원에게",
	gw_schedule_add_security_type_o: "소유자만에게만",
	gw_schedule_select_calendar: "달력선택",
	gw_schedule_select_equipment: "설비선택",
	gw_schedule_select_allcallist: "전체 달력목록",
	gw_schedule_select_allequiplist: "전체 설비목록",
	
	gw_schedule_delete_recur_title: "반복 설정된 일정",
	gw_schedule_delete_recur_option1: "전체 반복일정 삭제",
	gw_schedule_delete_recur_option2: "선택된 일정만 삭제",
	gw_schedule_delete_recur_option4: "선택된 일정을 포함한 이후 일정 삭제",
	gw_schedule_delete_recur_option_select: "삭제 유형을 선택하십시오.",
	// 추가
	gw_schedule_modify_recur_title: "반복 설정된 일정",
	gw_schedule_modify_recur_option1: "전체 반복일정 수정",
	gw_schedule_modify_recur_option2: "선택된 일정만 수정",
	gw_schedule_modify_recur_option4: "선택된 일정을 포함한 이후 일정 수정",
	// 추가 - e
	gw_board_name_label: "게시판",
	gw_board_recentlist_label: "최근 게시물",
	gw_board_alllist_label: "전체 게시판",
	gw_board_favlist_label: "즐겨찾기 게시판",
	gw_board_comment_label: "의견",
	gw_board_read_label: "게시물 읽기",
	gw_board_postername_label: "게시자",
	gw_board_write_title: "게시물 쓰기",
	gw_board_wirte_label: "게시",
	gw_board_enddate_label: "종료일",
	gw_board_everlast_label: "영구",
	gw_board_preface_label: "머리글",
	gw_board_select_preface: "머리글선택",
	gw_board_title_label: "제목",
	gw_board_write_comment_label: "의견작성",
	gw_board_write_of_comment_label: "댓글쓰기",

	gw_contact_personal_label: "개인주소록",
	gw_contact_dept_label: "부서주소록",	
	gw_contact_group_label: "그룹내용보기",
	gw_contact_personal_info_label: "개인정보",
	gw_contact_org_info_label: "회사정보",
	gw_contact_gender_m: "남자",
	gw_contact_gender_f: "여자",
	gw_contact_search_name: "이름으로 주소록 검색",
	gw_contact_search_email: "EMAIL로 주소록 검색",
	gw_contact_search_orgname: "회사명으로 주소록 검색",
	gw_contact_search_phone: "전화번호로 주소록 검색",
	
	gw_org_subdept_label: "하위부서",
	gw_org_member_label: "구성원",
	gw_org_tree_label: "조직도",
	gw_org_select_label: "조직도 선택",
	gw_org_userinfo_label: "사용자정보",
	gw_org_includesub_label: "하위포함",
	gw_org_search_deptname: "부서이름으로 부서 검색",
	gw_org_search_username: "이름으로 구성원 검색",
	gw_org_search_email: "EMAIL로 구성원 검색",
	gw_org_search_phone: "전화번호로 구성원 검색",
	gw_org_search_mobile: "휴대폰번호로 구성원 검색",
	gw_org_search_empcode: "사원번호로 구성원 검색",
	gw_org_search_posname: "직위명으로 구성원 검색",
	gw_org_search_rankname: "직급명으로 구성원 검색",
	gw_org_search_dutyname: "직책명으로 구성원 검색",
	gw_org_search_business: "담당업무명으로 구성원 검색",
	gw_org_root_label: "최상위부서",
		
	gw_sign_label: "결재",

	gw_sign_waitlist_label: "결재대기",
	gw_sign_gongramwaitlist_label: "공람대기",
	gw_sign_gongramcompletelist_label: "공람완료",
	gw_sign_nowlist_label: "결재진행",
	gw_sign_receiptwaitlist_label: "접수대기",
	gw_sign_userprocessedlist_label: "개인문서함[결재한문서]",
	gw_sign_mycompletelist_label: "개인문서함[완료문서]",
	gw_sign_completelist_label: "기록물 대장[완료함]",
	gw_sign_dispatchlist_label: "발송처리",
	
	gw_sign_details_label: "결재 본문",
	gw_sign_approve_label: "결재",
	gw_sign_reject_label: "반려",
	gw_sign_junkyul_label: "전결",
	gw_sign_postpone_label: "보류",
	gw_sign_gongram_label: "공람확인",
	gw_sign_confirm_label: "참조확인",
	gw_sign_write_comment_label: "의견작성",
	gw_sign_cancel_write_comment_label: "의견작성취소",
	gw_sign_show_comment_label: "의견보기",
	gw_sign_approve_flow_label: "결재진행상태",
	gw_sign_withdraw_label: "결재회수",
	gw_sign_cancel_label: "결재취소",
	gw_sign_show_summary_label: "요약",
	gw_sign_show_linkdoc_label: "관련문서",
	gw_sign_input_password_title: "결재암호입력",
	gw_sign_dispatch_label: "발송처리",
	
	gw_sign_signlist_type_all_label: "전체",
	gw_sign_signlist_type_1_label: "일반문서",
	gw_sign_signlist_type_3_label: "수신문서",
	gw_sign_signlist_type_2_label: "발신문서",
	gw_sign_signlist_type_7_label: "보고문서",
	gw_sign_signlist_type_5_label: "감사문서",
	gw_sign_signlist_type_4_label: "협조문서",

	gw_sign_receiptlist_type_all_label: "전체",
	gw_sign_receiptlist_type_2_label: "도착",
	gw_sign_receiptlist_type_4_label: "접수",
	gw_sign_receiptlist_type_16_label: "반송",
	gw_sign_receiptlist_type_8192_label: "내부반송",
	gw_sign_floworder_label: "순번",
	gw_sign_approval_label: "처리방법",
	
	gw_settings_label: "설정",
	gw_settings_absence_label: "부재설정",
	gw_settings_userinfo_label: "개인정보설정",
	gw_settings_password_label: "암호설정",
	gw_settings_license_label: "라이센스 정보",
	
	gw_settings_absence_list_label: "부재설정 목록",
	gw_settings_absence_add_label: "추가",
	gw_settings_absence_absadd_label: "부재추가",
	gw_settings_absence_save_label: "저장",
	gw_settings_absence_modify_label: "수정",
	gw_settings_absence_absmodify_label: "부재수정",
	gw_settings_absence_copy_label: "복사",
	gw_settings_absence_set_label: "부재설정",
	gw_settings_absence_remove_label: "부재해제",
	gw_settings_absence_period_label: "기간",
	gw_settings_absence_period_start_label: "부재시작",
	gw_settings_absence_period_end_label: "부재종료",
	gw_settings_absence_period_reason_label: "부재사유",
	gw_settings_absence_period_reason_1_label: "교육",
	gw_settings_absence_period_reason_2_label: "출장",
	gw_settings_absence_period_reason_3_label: "외출",
	gw_settings_absence_period_reason_4_label: "휴가",
	gw_settings_absence_period_reason_5_label: "조퇴",
	gw_settings_absence_period_reason_6_label: "연가",
	gw_settings_absence_period_reason_7_label: "병가",
	gw_settings_absence_period_reason_8_label: "공가",
	gw_settings_absence_period_reason_9_label: "특별휴가",
	gw_settings_absence_period_reason_10_label: "결근",
	gw_settings_absence_period_reason_11_label: "지참",
	gw_settings_absence_period_reason_12_label: "부재",
	gw_settings_absence_period_reason_13_label: "휴직",
	gw_settings_absence_period_reason_14_label: "퇴직",
	gw_settings_absence_mail_label: "메일설정",
	gw_settings_absence_mail_reply_msg_label: "메일 자동응답<BR>메세지", // tkofs 부재메세지 ->
	gw_settings_absence_mail_alt_rcpt_label: "대리 수신자",
	gw_settings_absence_sanc_label: "결재설정",
	gw_settings_absence_sanc_alt_signer_label: "대리 결재자",
	gw_settings_absence_sanc_handling1_label: "결재후 진행문서",
	gw_settings_absence_sanc_handling1_0_label: "결재대기",
	gw_settings_absence_sanc_handling1_1_label: "대리 결재자",
	gw_settings_absence_sanc_handling1_2_label: "결재안함",
	gw_settings_absence_sanc_handling2_label: "결재후 완료문서",
	gw_settings_absence_sanc_handling2_0_label: "결재대기",
	gw_settings_absence_sanc_handling2_1_label: "대리 결재자",
	
	gw_settings_user_alias_label: "별칭",
	gw_settings_user_phone_label: "전화번호",
	gw_settings_user_fax_label: "팩스번호",
	gw_settings_user_mobile_label: "휴대폰번호",
	gw_settings_user_business_label: "담당업무",
	
	gw_settings_password: "암호설정",
	gw_settings_password_cbloginpasswd: "로그인 암호 변경",
	gw_settings_password_txtoldloginpasswd: "이전암호",
	gw_settings_password_txtloginpasswd: "새 암호",
	gw_settings_password_txtloginpasswd_confirm: "암호확인",
	gw_settings_password_rdosanc: "결재암호",
	gw_settings_password_rdosanc_same: "같은 값으로 변경",
	gw_settings_password_rdosanc_cancel: "별도 지정",
	gw_settings_password_cbsancpasswd: "결재암호 변경",
	gw_settings_password_txtoldsancpasswd: "이전암호",
	gw_settings_password_txtsancpasswd: "새 암호",
	gw_settings_password_txtsancpasswd_confirm: "암호확인",
	gw_settings_password_cbsancpasswd_check: "결재시 암호 확인",
	gw_settings_password_txtsancpasswd_check: "로그인암호",
	gw_settings_password_rdosanc_confirm: "확인 여부",
	gw_settings_password_rdosanc_confirm_confirm: "확인",
	gw_settings_password_rdosanc_confirm_cancel: "확인 안함",
	
	gw_msg_common_err: "오류가 발생했습니다.",
	gw_msg_common_confirm_delete: "삭제하시겠습니까?",
	gw_msg_common_no_change: "변경된 사항이 없습니다.",
	gw_msg_common_confirm: "설정하신 값으로 변경하시겠습니까?",
	gw_msg_common_save_confirm: "설정하신 값으로 저장하시겠습니까?",
	gw_msg_common_change_success: "변경 되었습니다.",
	gw_msg_common_nosearchdata: "검색 결과가 없습니다.", 
	gw_msg_common_input_password: "암호를 입력하세요.",
	gw_msg_common_wrong_password: "잘못된 암호입니다.",
	gw_msg_common_nosearch_title: "제목없이 검색",
	gw_msg_common_inputsubject: "제목을 입력하세요.",
	gw_msg_common_toolong_msg: "제목의 길이가 너무 깁니다.",
	gw_msg_common_toolong_summary: "본문의 길이가 너무 깁니다.",
	gw_msg_common_success: "처리되었습니다.",
	gw_msg_common_fail: "실패되었습니다.",
	gw_msg_common_save: "저장되었습니다.",
	gw_msg_mail_nolist: "편지함에 편지가 없습니다.",
	gw_msg_mail_noselect: "선택된 편지가 없습니다.",
	gw_msg_mail_confirm_cancel: "선택한 편지를 회수하시겠습니까?",
	gw_msg_mail_confirm_delete: "선택한 편지를 삭제하시겠습니까?",
	gw_msg_mail_cancel_success: "건의 편지가 회수되었습니다.",
	gw_msg_mail_delete_success: "삭제되었습니다.",
	gw_msg_mail_already_recovered: "이미 회수한 편지입니다.",
	gw_msg_mail_not_read: "읽지 않은 편지 입니다.",
	gw_msg_mail_noReceiver: "수신자 정보가 없습니다.",
	gw_msg_mail_inputsubject: "제목을 입력하세요.",
	gw_msg_mail_inputbody: "내용을 입력하세요.",
	gw_msg_mail_inputto: "받는이가 지정되지 않았습니다.",
	gw_msg_mail_erroradress: "받는사람의(외부) 편지 주소 형식이 잘못되었습니다.",
	gw_msg_mail_smtp: "그룹웨어 포탈에서 인터넷 메일 서버 설정을 먼저 해주십시오.",
	gw_msg_mail_mismatch_org: "일치하는 구성원 또는 부서가 없습니다.",
	gw_msg_mail_write_success: "편지가 발송되었습니다.",
	gw_msg_mail_choose_samename: "동명의 사용자가 있습니다. 선택상자에서 사용자를 지정해 주십시오.",
	gw_msg_mail_invalid_email: "잘못된 이메일 주소입니다.",
	gw_msg_mail_cancel_writeMail: "편지쓰기를 취소하시겠습니까",
	gw_msg_mail_input_receiver: "받는이를 입력하세요.",
	gw_msg_mail_input_cc: "참조인을 입력하세요.",
	gw_msg_mail_input_bcc: "숨은 참조인을 입력하세요.",
	gw_msg_mail_canceled_not_deleted: "이미 회수한 편지입니다.",
	gw_msg_mail_externaldomain_not_sended: "수신자 중 외부도메인을 가진 사용자가 있어 메일을 발송 할 수 없습니다.",
	gw_msg_sign_nolist: "결재함에 문서가 없습니다.",
	gw_msg_sign_external_doc: "비전자 문서입니다.",
	gw_msg_sign_external6_doc: "수기 문서입니다.",
	gw_msg_sign_read_noauth_err: "문서 열람 권한이 없습니다.",
	gw_msg_sign_input_password: "결재암호를 입력하세요.",
	gw_msg_sign_wrong_password: "잘못된 암호입니다.",
	gw_msg_sign_confirm_approve: "결재 하시겠습니까?",
	gw_msg_sign_confirm_reject: "반려 하시겠습니까?",
	gw_msg_sign_confirm_postpone: "문서를 보류하시겠습니까?",
	gw_msg_sign_confirm_gongram: "문서를 공람확인 하시겠습니까?",
	gw_msg_sign_confirm_dispatch: "문서를 발송처리 하시겠습니까?",
	gw_msg_sign_approve_success: "결재 승인이 처리되었습니다.",
	gw_msg_sign_approve_fail: "결재 승인이 실패되었습니다.",
	gw_msg_sign_reject_success: "반려 처리되었습니다.",
	gw_msg_sign_reject_fail: "반려 되지 않았습니다.",
	gw_msg_sign_postpone_success: "문서를 보류처리 하였습니다.",
	gw_msg_sign_postpone_fail: "보류 되지 않았습니다.",
	gw_msg_sign_gongram_success: "문서를 공람확인 하였습니다.",
	gw_msg_sign_gongram_fail: "공람확인 되지 않았습니다.",
	gw_msg_sign_confirm_withdraw: "결재 문서를 회수하시겠습니까?",
	gw_msg_sign_confirm_cancel: "결재 문서를 취소하시겠습니까?",
	gw_msg_sign_cancel_success: "취소 처리되었습니다.",
	gw_msg_sign_network_err: "네트워크 환경이 불안정하여 결과 확인에 실패하였습니다.",
	gw_msg_sign_agent_err: "모바일 결재 시스템에 오류가 발생하였습니다. 관리자에게 문의 바랍니다.",
	gw_msg_sign_not_support_apprdoc: "모바일에서 지원하지 않는 결재 문서입니다.",
	gw_msg_sign_not_transform_apprdoc: "문서변환 없이 볼 수 없는 결재 문서입니다.",
	gw_msg_sign_limitread_apprlinkdoc: "모바일에서 열람 가능한 최대수를 초과하였습니다.",
	gw_msg_sign_withdraw_opinion: "모바일에서 회수한 문서입니다.",	
	gw_msg_board_authread_err: "리스트보기 권한이 없습니다.",
	gw_msg_board_nolist: "게시물이 없습니다.",
	gw_msg_board_nobldata: "등록된 게시판이 없습니다.",
	gw_msg_board_read_noauth_err: "게시물 조회 권한이 없습니다.",
	gw_msg_board_invalid_enddate: "종료일은 오늘 이후만 가능합니다.",
	gw_msg_board_cancel_write: "게시물 쓰기를 취소하시겠습니까?",
	gw_msg_board_comment_nolist: "등록된 의견이 없습니다.",
	gw_msg_board_not_match_passwd_manage: "수정/삭제 암호가 일치하지 않습니다.",
	gw_msg_schedule_invalid_code: "유효하지 않은 코드 : ",
	gw_msg_schedule_schedule_nolist: "등록된 일정이 없습니다.",
	gw_msg_schedule_check_calendar: "달력은 최소 1개이상이 선택되어야합니다.",
	gw_msg_schedule_no_select_calendar: "달력을 선택하세요",
	gw_msg_schedule_equip_nolist: "등록된 설비일정이 없습니다.",
	// 추가
	gw_msg_schedule_check_equip: "예약할 설비를 선택하세요.",
	// 추가 - e
	gw_msg_schedule_todo_nolist: "등록된 할일이 없습니다.",
	gw_msg_schedule_equip_nothing: "등록된 설비가 없습니다.",
	gw_msg_schedule_cancel_add_schedule: "일정 작성을 취소하시겠습니까?",
	gw_msg_schedule_cancel_add_todo: "할일 작성을 취소하시겠습니까?",
	gw_msg_schedule_check_reserve_equip: "설비가 이미 예약되어 있습니다.",
	gw_msg_schedule_invalid_letter_err: "\'\"\\와 같은 문자는 사용할 수 없습니다.",
	gw_msg_schedule_todo_empty_processrate: "진척율을 입력하세요.",
	gw_msg_schedule_todo_progress_invalidvalue: "진척율은 숫자만 입력 가능합니다.",
	gw_msg_schedule_todo_progress_limitvalue: "진척율은 0 ~ 100까지 숫자를 입력 가능합니다.",
	gw_msg_schedule_period_err: "기간 설정을 잘못하였습니다.",
	gw_msg_schedule_period_timegap_err: "종료 일시는 시작 일시보다 30분 이후로 설정하셔야 합니다.",
	// 추가
	gw_msg_schedule_recur_type3_daywk: "반복 요일을 선택하십시오.",
	// 추가 -end
	gw_msg_contact_nothing: "등록된 주소록이 없습니다.",
	gw_msg_org_member_nolist: "구성원이 없습니다.",
	gw_msg_org_subdept_nolist: "하위부서가 없습니다.",
	gw_msg_absence_not_absmsg: "부재메세지를 입력해주세요",
	gw_msg_absence_toolong_msg: "현재 부재메세지는 한글80자(영문160자) 내에서만 가능합니다.",
	gw_msg_absence_not_period: "부재 기간을 입력해주세요",
	gw_msg_absence_period_err_1: "부재 종료 일시는 부재 시작 일시 이후로 설정하셔야 합니다.",
	gw_msg_absence_period_err_2: "부재 종료 날짜는 현재 날짜 이후로 설정해야 합니다.",
	gw_msg_absence_no_alter_signer: "대리결재자가 지정되지 않았습니다.",
	gw_msg_absence_absence_nolist: "등록된 부재가 없습니다.",
	gw_msg_absence_cancel_add_absence: "부재 작성을 취소하시겠습니까?",
	gw_msg_absence_minimum_one_select: "삭제하기 위해 최소 1개이상이 선택되어야 합니다.",
	gw_msg_password_no_old_password: "이전 로그인 암호를 입력하십시오.",
	gw_msg_password_no_new_password: "새로운 로그인 암호를 입력하십시오.",
	gw_msg_password_no_new_password_confirm: "새 로그인 암호 확인을 위해 새 로그인 암호를 입력하십시오.",
	gw_msg_password_mismatch_new_password: "새 로그인 암호가 동일하게 입력되지 않았습니다. 다시 입력하십시오.",
	gw_msg_password_sameold_new_password: "새 로그인 암호가 이전 로그인 암호와 동일합니다. 다른 암호를 입력하십시오.",
	gw_msg_password_toolong_password: "암호는 최대 30자 이내로 설정하셔야 합니다.",
	gw_msg_password_tooshort_password: "암호는 최소 5자 이상으로 설정하셔야 합니다.",
	gw_msg_password_no_old_sanc_password: "이전 결재 암호를 입력하십시오.",
	gw_msg_password_no_new_sanc_password: "새로운 결재 암호를 입력하십시오.",
	gw_msg_password_no_new_sanc_password_confirm: "새 결재 암호 확인을 위해 새 결재 암호를 입력하십시오.",
	gw_msg_password_mismatch_new_sanc_password: "새 결재 암호가 동일하게 입력되지 않았습니다. 다시 입력하십시오.",
	gw_msg_password_sameold_new_sanc_password: "새 결재 암호가 이전 결재 암호와 동일합니다. 다른 암호를 입력하십시오.",
	gw_msg_password_input_password_for_sanc: "결재시 암호 확인의 변경을 위해 로그인 암호를 입력하십시오.",
	gw_msg_password_tooshort_sanc_password: "결재 암호는 최소 5자 이상으로 설정하셔야 합니다.",
	
	gw_error_system_error: "내부 오류가 발생하였습니다. 관리자에게 문의해주세요.",
	gw_error_invalid_request: "유효하지 않은 요청입니다. 관리자에게 문의해주세요.",
	gw_error_session_expired: "세션이 유효하지 않습니다. 다시 로그인해 주세요.",
	gw_error_access_denied: "인증되지 않은 사용자입니다. 로그인 후 사용해 주세요.",
	gw_error_unauthorized_phone_uid_number: "인증 받지 않은 단말기 입니다. 관리자에게 문의하십시오.",
	gw_error_unregistered_new_multi_device: "등록되지 않은 디바이스입니다.",
	gw_error_unregistered_new_multi_device_guide: "등록되지 않은 디바이스입니다.\n 추가로 디바이스를 등록하려면 아래의 [장비등록] 버튼을 클릭하여 주세요.\n 관리자의 확인을 통해 현재 디바이스의 등록을 처리할 예정입니다.",
	gw_error_appversion_notequal: "모바일 그룹웨어 앱이 업데이트 되었습니다. 앱을 다시 설치하여 주십시오.",	
	gw_error_license_filenotfound: "라이센스 파일이 존재하지 않습니다.",
	gw_error_license_invalid_info: "라이센스 정보가 유효하지 않습니다.",
	gw_error_license_invalid_serverip: "라이센스가 발급되지 않은 서버입니다.",
	gw_error_license_expired: "라이센스 기간이 만료되었습니다.",
	gw_error_license_exceeduser: "접속 사용자 수가 초과되었습니다.",
	gw_error_file_download: "문서 다운로드 오류가 발생하였습니다. 관리자에게 문의해주세요.",
	gw_error_file_transform: "문서 변환 오류가 발생하였습니다. 관리자에게 문의해주세요.",
	gw_error_file_unsupported_type: "다운로드할 수 없는 파일 유형입니다.",
	gw_error_failed_result: "결과 확인에 실패하였습니다.",
	// 추가
	gw_error_duplicate:"중복 오류 입니다.",
	// 추가-e
	gw_success:"결재문서가 회수되었습니다.",
	gw_fail:"결재문서가 회수되지않았습니다.",
	gw_fail_view:"요약전 보기를 실패하였습니다.",
	gw_view:"요약전 보기",
	gw_withdraw:"결재 회수",
	gw_check_doc:"열람 제한 문서"
};var RES_STRING_ZH_CN = {
	gw_product_name:"组件",
	gw_common_loading_label:"正在导入中…",
	gw_common_pulldown_label:" 拖放下可以更新列表。",
	gw_common_pulldown_update_label:"下拉可以更新。",
	gw_common_pullup_label:"拖放下可以导入新的列表。",
	gw_common_pullup_refresh_label:"上拉可以查看更多列表。",
	gw_common_menu_label:"菜单",
	gw_common_back_label:"以前",
	gw_common_close_label:"关闭",
	gw_common_write_label:"写",
	gw_common_edit_label:"编辑",
	gw_common_canceledit_label:"取消编辑",
	gw_common_selectall_label:"全部选择",
	gw_common_cancelselect_label:"选择取消",
	gw_common_delete_label:"删除",
	gw_common_attach_label:"附件",
	gw_common_username_label:"姓名",
	gw_common_email_label:"电子邮件",
	gw_common_companyname_label:"公司名",
	gw_common_phone_label:"电话号码",
	gw_common_search_label:"搜索",
	gw_common_search_result_label:"搜索结果",
	gw_common_add_label:"添加",
	gw_common_select_label:"选择",
	gw_common_show_label:"查看",
	gw_common_hide_label:"隐藏",
	gw_common_ok_label:"确认",
	gw_common_set_label:"设置",
	gw_common_change_label:"更改",
	gw_common_cancel_label:"取消",
	gw_common_modify_label:"修改",
	gw_common_option_label:"选项",
	gw_common_byte_label:"byte",
	gw_common_kilobyte_label:"KB",
	gw_coomon_date_label:"Date",
	gw_coomon_time_label:"Time",
	gw_common_getmore_label:"更多查看..",
	gw_common_group_label:"组",
	gw_common_year_label:"年",
	gw_common_month_label:"月",
	gw_common_day_label:"日",
	gw_common_hour_label:"时",
	gw_common_minute_label:"分",
	gw_common_input_password_title:"输入密码",
	gw_common_refresh_label:"刷新",
	gw_common_piece_label:"个",
	gw_common_save_label:"保存",
	gw_mail_mailwrite_label:"写信",
	gw_mail_mailread_label:"邮件阅读",
	gw_mail_recvlist_label:"收件箱",
	gw_mail_sendlist_label:"发送邮箱",
	gw_mail_templist_label:"草稿箱",
	gw_mail_deletelist_label:"已删除邮件箱",
	gw_mail_receiver_label:"收件人",
	gw_mail_sender_label:"发件人",
	gw_mail_cc_label:"抄送人",
	gw_mail_confirm_receive_label:"确认收取",
	gw_mail_cancel_label:"回收",
	gw_mail_fly_label:"转发",
	gw_mail_reply_label:"回复",
	gw_mail_replyall_label:"全部回复",
	gw_mail_personalbox_label:"个人邮箱",
	gw_mail_personalbox_path:"个人邮箱",
	gw_mail_entire_state_label:"全部状态",
	gw_mail_bcc_label:"密送",
	gw_mail_ccbcc_lable:"抄送,密送",
	gw_mail_urgency_label:"紧急",
	gw_mail_security_label:"安全",
	gw_mail_title_label:"标题",
	gw_mail_send_label:"发送",
	gw_mail_include_org_attach_label:"现有附件",
	gw_mail_include_org_message:"包括原本",
	gw_mail_dbmail_sender_label:"发送",
	gw_mail_dbmail_to_label:"收取",
	gw_mail_dbmail_date_label:"日期",
	gw_mail_dbmail_original_message:"原本消息",
	gw_mail_notitle_label:"无标题",
	gw_mail_choose_samename_label:"选择同名异人",
	gw_mail_select_recv:"选择收件人",
	gw_schedule_label:"日程",
	gw_schedule_todo_label:"待办事项",
	gw_schedule_list_label:"日程列表",
	gw_schedule_equip_list_label:"设备预订列表",
	gw_schedule_equip_detaillist_label:"设备预订详细列表",
	gw_schedule_add_label:"添加日程/设备预订",
	gw_schedule_schadd_label:"添加日程/设备预订",
	gw_schedule_schmodify_label:"修改日程/设备预订",
	gw_schedule_sch6add_label:"添加日程",
	gw_schedule_sch6modify_label:"修改日程",
	gw_schedule_todo_list_label:"待办事项列表",
	gw_schedule_todo_add_label:"添加待办事项",
	gw_schedule_todo_modify_label:"修改待办事项",
	gw_schedule_search_label:"搜索日程/待办事项",
	gw_schedule2_search_label:"日程搜索",
	gw_schedule_sch_search_label:"日程搜索结果",
	gw_schedule_todo_search_label:"待办事项搜索结果",
	gw_schedule_base_date_label:"基准日 : ",
	gw_schedule_daily_label:"每日",
	gw_schedule_weekly_label:"每周",
	gw_schedule_monthly_label:"每月",
	gw_schedule_monthplan_label:"每月计划",
	gw_schedule_today_label:"今天",
	gw_schedule_shared_calendar_label:"共享日程",
	gw_schedule_all_calendar_label:"全部日历",
	gw_schedule_my_and_shared_calendar_label: "我的日历 + 共享日程",
	gw_schedule_my_calendar_label:"我的日历",
	gw_schedule_dept_calendar_label:"部门日历",
	gw_schedule_company_calendar_label:"公司日历",
	gw_schedule_user_calendar_label:"用户日历",
	gw_schedule_calendar_count_label:"件",
	gw_schedule_schedule_view_label:"日程查看",
	gw_schedule_equipment_view_label:"查看预订设备",
	gw_schedule_todo_view_label:"查看待办事项",
	gw_schedule_title_label:"标题",
	gw_schedule_category_label: "类别",
	gw_schedule_owner_label:"所有者",
	gw_schedule_writer_label:"制作人",
	gw_schedule_repeat_label:"重复",
	gw_schedule_term_label:"期间",
	gw_schedule_write_date_label:"制作日期",
	gw_schedule_start_date_label:"开始日期",
	gw_schedule_end_date_label:"结束日期",
	gw_schedule_day_schedule_label:"一天日程 ",
	gw_schedule_period_label:"期间设置",
	gw_schedule_period_day_1_label:"当日",
	gw_schedule_period_day_2_label:"7天",
	gw_schedule_period_day_3_label:"1个月 ",
	gw_schedule_period_day_4_label:"3个月 ",
	gw_schedule_calendar_label:"日历",
	gw_schedule_attendee_label: "Attendee",
	gw_schedule_equipment_label:"设备",
	gw_schedule_all_equipment_label:"全部设备",
	gw_schedule_content_label:"内容",
	gw_schedule_reserve_equipment_label:"设备预订",
	gw_schedule_alarm_label:"通知",
	gw_schedule_alarm_label_0:"立即通知",
	gw_schedule_alarm_label_m:"分前",
	gw_schedule_alarm_label_h:"时间前",
	gw_schedule_alarm_label_d:"日前",
	gw_schedule_alarm_label_w:"周前",
	gw_schedule_alarm_label_M:"月前",
	gw_schedule_alarm_label_0mail:"立即发送电子邮件",
	gw_schedule_importance_label:"重要程度",
	gw_schedule_weight_high:"高",
	gw_schedule_weight_medium:"中",
	gw_schedule_weight_low:"低",
	gw_schedule_status_label:"状态",
	gw_schedule_progress_label:"进度率",
	gw_schedule_status_finish_label:"完成",
	gw_schedule_status_delay_label:"延期",
	gw_schedule_status_cancel_label:"取消",
	gw_schedule_status_progress_label:"进行",
	gw_schedule_status_notfinish_label:"未完成",
	gw_schedule_not_label:"没有",
	gw_schedule_recur_type0: "没有重复",
	gw_schedule_recur_type1:"每日(周一至周日)",
	gw_schedule_recur_type2:"平日(周一至周五)",
	gw_schedule_recur_type3:"每周",
	gw_schedule_recur_type4:"每月",
	gw_schedule_recur_type5:"每月(星期)",
	gw_schedule_recur_type6: "每年",
	gw_schedule_recur_cycle_weekly: "Every week",
	gw_schedule_recur_cycle_weekly_num: "Every {{num}} weeks",
	gw_schedule_recur_cycle_type4_day: "Monthly {{dd}}",
	gw_schedule_recur_cycle_type4_ord_day_wk: "Monthly {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type4_last_wk: "Monthly last {{dayWk}}",
	gw_schedule_recur_cycle_type6_date: "Every year {{mmdd}}",
	gw_schedule_recur_cycle_type6_last: "The last day of {{mm}} every year",
	gw_schedule_recur_cycle_type6_ord_day_wk: "Every year {{mm}} {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type6_last_wk: "Every year {{mm}} last {{dayWk}}",
	gw_schedule_recur_times: "last",
	gw_schedule_recur_times0: "first",
	gw_schedule_recur_times1: "second",
	gw_schedule_recur_times2: "third",
	gw_schedule_recur_times3: "fourth",
	gw_schedule_recur_times4: "fifth",
	gw_schedule_equipmentgroup_label:"设备组",
	
	gw_schedule_have_label:"有",
	gw_schedule_ownertype_user:"个人日程",
	gw_schedule_ownertype_dept:"部门日程",
	gw_schedule_security_level_label:"安全设置",
	gw_schedule_security_type_a:" 任何人都可以阅读",
	gw_schedule_security_type_d:"只有部门人员才可以阅读",
	gw_schedule_security_type_s:"部门人员/直属上下级部门人员可以阅读",
	gw_schedule_security_type_o:"只有所有者才可以阅读",
	gw_schedule_add_security_level_label:"公开范围",
	gw_schedule_add_security_type_a:"为所有人",
	gw_schedule_add_security_type_d:"为部门人员",
	gw_schedule_add_security_type_s:"为直属上下级部门人员",
	gw_schedule_add_security_type_o:"只所有者",
	gw_schedule_select_calendar:"日历选择",
	gw_schedule_select_equipment:"设备选择",
	gw_schedule_select_allcallist:"全部日历列表",
	gw_schedule_select_allequiplist:"全部设备列表",
	
	gw_schedule_delete_recur_title: "Recurring schedule",
	gw_schedule_delete_recur_option1: "Delete all recurring events",
	gw_schedule_delete_recur_option2: "Only delete selected events",
	gw_schedule_delete_recur_option4: "Delete future events with selected events",
	gw_schedule_delete_recur_option_select: "Please select a removal type.",
	gw_schedule_modify_recur_title: "Recurring schedule",
	gw_schedule_modify_recur_option1: "Edit all recurring events",
	gw_schedule_modify_recur_option2: "Edit only selected events",
	gw_schedule_modify_recur_option4: "Edit future events that include selected events",
	
	gw_board_name_label:"公告板",
	gw_board_recentlist_label:"最近帖子",
	gw_board_alllist_label:"全部公告板",
	gw_board_favlist_label:"收藏夹公告板",
	gw_board_comment_label:"意见",
	gw_board_read_label:"帖子阅读",
	gw_board_postername_label:"发帖者 ",
	gw_board_write_title:"写帖子",
	gw_board_wirte_label:"发布",
	gw_board_enddate_label:"结束日期",
	gw_board_everlast_label:"永久",
	gw_board_preface_label:"页眉",
	gw_board_select_preface:"页眉选择",
	gw_board_title_label:"标题",
	gw_board_write_comment_label:"制作意见",
	gw_board_write_of_comment_label:"写回帖",
	gw_contact_personal_label:"个人地址薄",
	gw_contact_dept_label:"部门地址薄",
	gw_contact_group_label:"组内容查看",
	gw_contact_personal_info_label:"个人信息",
	gw_contact_org_info_label:"公司信息",
	gw_contact_gender_m:"男",
	gw_contact_gender_f:"女",
	gw_contact_search_name:"通过名字搜索地址薄",
	gw_contact_search_email:"通过电子邮件搜索地址薄",
	gw_contact_search_orgname:"通过公司名搜索地址薄",
	gw_contact_search_phone:"通过电话号码搜索地址薄",
	gw_org_subdept_label:"下级部门",
	gw_org_member_label:"成员",
	gw_org_tree_label:"组织结构图",
	gw_org_select_label:"选择组织结构图",
	gw_org_userinfo_label:"用户信息",
	gw_org_includesub_label:"下级包括",
	gw_org_search_deptname:"通过部门名字搜索部门",
	gw_org_search_username:"通过名字搜索成员",
	gw_org_search_email:"通过电子邮件搜索成员",
	gw_org_search_phone:"通过电话号码搜索成员",
	gw_org_search_mobile:"通过手机号码搜索成员",
	gw_org_search_empcode: "员工编号码搜索成员",
	gw_org_search_posname: "帖子名字搜索成员",
	gw_org_search_rankname: "等级名字搜索成员",
	gw_org_search_dutyname: "办公室名字搜索成员",
	gw_org_search_business: "责任字搜索成员",		
	gw_org_root_label:"最上级部门",
	gw_sign_label:"审批",
	gw_sign_waitlist_label:"批准等待",
	gw_sign_gongramwaitlist_label: "等待分发",
	gw_sign_gongramcompletelist_label: "分发完毕",
	gw_sign_nowlist_label:"进行批准",
	gw_sign_receiptwaitlist_label:"等待接收",
	gw_sign_userprocessedlist_label:"个人文件箱[批准的文件]",
	gw_sign_mycompletelist_label:"个人文件箱[完成文件]",
	gw_sign_completelist_label:"记录档案[已完成]",
	gw_sign_dispatchlist_label: "발송처리",
	gw_sign_details_label:"审批正文",
	gw_sign_approve_label:"审批",
	gw_sign_reject_label:"退回",
	gw_sign_junkyul_label:"审批",
	gw_sign_postpone_label:"暂停",
	gw_sign_gongram_label:"共览确认",
	gw_sign_confirm_label:"抄送确认",
	gw_sign_write_comment_label:"制作意见",
	gw_sign_cancel_write_comment_label:"取消制作意见",
	gw_sign_show_comment_label:"查看意见",
	gw_sign_approve_flow_label:"批准进行状态",
	gw_sign_withdraw_label:"撤回批准",
	gw_sign_cancel_label: "取消批准",
	gw_sign_show_summary_label:"摘要",
	gw_sign_show_linkdoc_label:"有关文件",
	gw_sign_input_password_title:"输入审批密码",
	gw_sign_dispatch_label: "调度处理",
	gw_sign_signlist_type_all_label:"全部",
	gw_sign_signlist_type_1_label:"普通文件",
	gw_sign_signlist_type_3_label:"收取文件",
	gw_sign_signlist_type_2_label:"发送文件",
	gw_sign_signlist_type_7_label:"报告文件",
	gw_sign_signlist_type_5_label:"审计文件",
	gw_sign_signlist_type_4_label:"合作文件",
	gw_sign_receiptlist_type_all_label:"全部",
	gw_sign_receiptlist_type_2_label:"到达",
	gw_sign_receiptlist_type_4_label:"接收",
	gw_sign_receiptlist_type_16_label:"返回",
	gw_sign_receiptlist_type_8192_label:"内部返回",
	gw_sign_floworder_label:"序号",
	gw_sign_approval_label:"处理方法",
	gw_settings_label:"设置",
	gw_settings_absence_label:"不在设置",
	gw_settings_userinfo_label:"个人信息设置",
	gw_settings_password_label:"密码设置",
	gw_settings_license_label: "许可证信息",
	gw_settings_absence_list_label:"不在设置 列表",
	gw_settings_absence_add_label:"添加",
	gw_settings_absence_absadd_label:"不在添加",
	gw_settings_absence_save_label:"保存",
	gw_settings_absence_modify_label:"修改",
	gw_settings_absence_absmodify_label:"不在修改",
	gw_settings_absence_copy_label:"复制",
	gw_settings_absence_set_label:"不在设置",
	gw_settings_absence_remove_label:"不在解除",
	gw_settings_absence_period_label:"期间",
	gw_settings_absence_period_start_label:"不在开始",
	gw_settings_absence_period_end_label:"不在结束",
	gw_settings_absence_period_reason_label:"不在理由",
	gw_settings_absence_period_reason_1_label:"教育",
	gw_settings_absence_period_reason_2_label:"出差",
	gw_settings_absence_period_reason_3_label:"外出",
	gw_settings_absence_period_reason_4_label:"休假",
	gw_settings_absence_period_reason_5_label:"早退",
	gw_settings_absence_period_reason_6_label:"年假",
	gw_settings_absence_period_reason_7_label:"病假",
	gw_settings_absence_period_reason_8_label:"公假",
	gw_settings_absence_period_reason_9_label:"特别休假",
	gw_settings_absence_period_reason_10_label:"缺勤",
	gw_settings_absence_period_reason_11_label:"迟到 ",
	gw_settings_absence_period_reason_12_label:"不在",
	gw_settings_absence_period_reason_13_label:"休假",
	gw_settings_absence_period_reason_14_label:"退职",
	gw_settings_absence_mail_label:"邮件设置",
	gw_settings_absence_mail_reply_msg_label:"邮件自动响应<BR>消息",	// tkofs 不在消息
	gw_settings_absence_mail_alt_rcpt_label:"代理收件人",
	gw_settings_absence_sanc_label:"批准设置",
	gw_settings_absence_sanc_alt_signer_label:"代理审批人",
	gw_settings_absence_sanc_handling1_label:"批准后进行文件",
	gw_settings_absence_sanc_handling1_0_label:"待批准",
	gw_settings_absence_sanc_handling1_1_label:"代理审批人",
	gw_settings_absence_sanc_handling1_2_label:"不批准",
	gw_settings_absence_sanc_handling2_label:"批准后完成文件",
	gw_settings_absence_sanc_handling2_0_label:"待批准",
	gw_settings_absence_sanc_handling2_1_label:"代理审批人",
	gw_settings_user_alias_label:"别称",
	gw_settings_user_phone_label:"电话号码",
	gw_settings_user_fax_label:"传真号码",
	gw_settings_user_mobile_label:"手机号码",
	gw_settings_user_business_label:"负责业务",
	gw_settings_password:"密码设置",
	gw_settings_password_cbloginpasswd:"登录密码 更改",
	gw_settings_password_txtoldloginpasswd:"旧密码",
	gw_settings_password_txtloginpasswd:"新密码",
	gw_settings_password_txtloginpasswd_confirm:"确认密码",
	gw_settings_password_rdosanc:"审批密码",
	gw_settings_password_rdosanc_same:"以相同的值变更",
	gw_settings_password_rdosanc_cancel:"分别指定",
	gw_settings_password_cbsancpasswd:"变更审批密码",
	gw_settings_password_txtoldsancpasswd:"旧密码",
	gw_settings_password_txtsancpasswd:"新密码",
	gw_settings_password_txtsancpasswd_confirm:"确认密码",
	gw_settings_password_cbsancpasswd_check:"审批时确认密码",
	gw_settings_password_txtsancpasswd_check:"登录密码",
	gw_settings_password_rdosanc_confirm:"确认与否",
	gw_settings_password_rdosanc_confirm_confirm:"确认",
	gw_settings_password_rdosanc_confirm_cancel:"不确认",
	gw_msg_common_err:"发生错误。",
	gw_msg_common_confirm_delete:"您确定要删除吗?",
	gw_msg_common_no_change:"没有变更事项。",
	gw_msg_common_confirm:"您确定要更改设置的值吗?",
	gw_msg_common_save_confirm:"通过设置的值，是否要保存吗?",
	gw_msg_common_change_success:"已更改。",
	gw_msg_common_nosearchdata:"没有搜索结果。",
	gw_msg_common_input_password:"请输入密码。",
	gw_msg_common_wrong_password:"无效的密码。",
	gw_msg_common_nosearch_title:"没有标题搜索",
	gw_msg_common_inputsubject:"请输入标题。",
	gw_msg_common_toolong_msg:"标题长度太长。",
	gw_msg_common_toolong_summary:"正文长度太长。",
	gw_msg_common_success:"已处理。",
	gw_msg_common_fail:"已失败。",
	gw_msg_common_save:"已保存。",
	gw_msg_mail_nolist:"邮箱里没有邮件。",
	gw_msg_mail_noselect:"没有所选的邮件。",
	gw_msg_mail_confirm_cancel:"您确定要回收所选的邮件吗?",
	gw_msg_mail_confirm_delete:"您确定要删除所选的邮件吗?",
	gw_msg_mail_cancel_success:"件的邮件已经回收了。",
	gw_msg_mail_delete_success:"已保存。",
	gw_msg_mail_already_recovered:"已回收的邮件。 ",
	gw_msg_mail_not_read:"是未读的邮件。 ",
	gw_msg_mail_noReceiver:"没有收件人信息。",
	gw_msg_mail_inputsubject:"请输入标题。",
	gw_msg_mail_inputbody:"请输入内容。",
	gw_msg_mail_inputto:"未指定收件人。",
	gw_msg_mail_erroradress:"收件人（外部）邮件地址形式不正确。",
	gw_msg_mail_smtp:"请先在组件门户网站设置互联网邮件服务器。",
	gw_msg_mail_mismatch_org:"没有匹配的成员或部门。",
	gw_msg_mail_write_success:"已发送邮件。",
	gw_msg_mail_choose_samename:"已存在同名的用户。 请在选择箱指定其他用户。",
	gw_msg_mail_invalid_email:"错误的电子邮件地址。",
	gw_msg_mail_cancel_writeMail:"您确定要取消写信码?",
	gw_msg_mail_input_receiver:"请输入收件人。",
	gw_msg_mail_input_cc:"请输入抄送人。",
	gw_msg_mail_input_bcc:"请输入密送人。。.",
	gw_msg_mail_canceled_not_deleted:"已回收的邮件。 ",
	gw_msg_mail_externaldomain_not_sended:"在收件人中有外部域名的用户，无法发送邮件。",
	gw_msg_sign_nolist:"在审批箱里没有文件。",
	gw_msg_sign_external_doc:"是非电子文件。",
	gw_msg_sign_external6_doc:"是手写文件。",
	gw_msg_sign_read_noauth_err:"没有阅览文件的权限。",
	gw_msg_sign_input_password:"请输入审批密码。",
	gw_msg_sign_wrong_password:"无效的密码。",
	gw_msg_sign_confirm_approve:"您确定要请求批准吗?",
	gw_msg_sign_confirm_reject:"您确定要请求退回吗 ?",
	gw_msg_sign_confirm_postpone:"您确定要暂停文件吗 ?",
	gw_msg_sign_confirm_gongram: "Do you want to mark as viewed?",
	gw_msg_sign_confirm_dispatch: "Do you want to dispatch?",
	gw_msg_sign_approve_success:"已处理批准审批。",
	gw_msg_sign_approve_fail:"批准审批失败。",
	gw_msg_sign_reject_success:"已退回。 ",
	gw_msg_sign_reject_fail:"未被退回。",
	gw_msg_sign_postpone_success:"该文件已被保留。",
	gw_msg_sign_postpone_fail:"未被暂停。",
	gw_msg_sign_gongram_success: "Mark as viewed.",
	gw_msg_sign_gongram_fail: "Mark as viewed failed.",
	gw_msg_sign_confirm_withdraw:"您确定要回收审批文件吗?",
	gw_msg_sign_confirm_cancel: "您确定要取消审批文件吗?",
	gw_msg_sign_cancel_success: "取消已处理。",
	gw_msg_sign_network_err:"网络环境不稳定，无法确认结果。 ",
	gw_msg_sign_agent_err:"在移动审批系统发生错误。 请向管理员咨询。",
	gw_msg_sign_not_support_apprdoc:"在移动不支持的审批文件。",
	gw_msg_sign_not_transform_apprdoc:"没有转换文件，不能查看审批文件。",
	gw_msg_sign_limitread_apprlinkdoc:"已超出可在移动阅览的最大数量。",
	gw_msg_sign_withdraw_opinion: "该文档是从您的手机中检索到的。",
	gw_msg_board_authread_err:"没有查看列表的权限。",
	gw_msg_board_nolist:"没有帖子。",
	gw_msg_board_nobldata:"没有注册的公告板。",
	gw_msg_board_read_noauth_err:"没有查询帖子的权限。",
	gw_msg_board_invalid_enddate:"您只能选择今天以后的结束日期。",
	gw_msg_board_cancel_write:"您确定要取消写帖子吗?",
	gw_msg_board_comment_nolist:"没有注册的意见。",
	gw_msg_board_not_match_passwd_manage:"修改/删除密码不匹配。",
	gw_msg_schedule_invalid_code:"无效的代码: ",
	gw_msg_schedule_schedule_nolist:"没有注册的日程。",
	gw_msg_schedule_check_calendar:"日历必须至少选择一个以上。",
	gw_msg_schedule_no_select_calendar:"请选择日历。",
	gw_msg_schedule_equip_nolist:"没有注册的设备日程。",
	gw_msg_schedule_check_equip: "Please select a facility to reserve.",
	gw_msg_schedule_todo_nolist:"没有注册的待办事项。",
	gw_msg_schedule_equip_nothing:"没有注册的设备。",
	gw_msg_schedule_cancel_add_schedule:"您确定要取消制作日程吗?",
	gw_msg_schedule_cancel_add_todo:"您确定要取消制作待办事项吗?",
	gw_msg_schedule_check_reserve_equip:"已经预订设备。",
	gw_msg_schedule_invalid_letter_err:"不能使用\'\"\\等的字符。",
	gw_msg_schedule_todo_empty_processrate:"请输入进度率。",
	gw_msg_schedule_todo_progress_invalidvalue:"进度率只能输入数字。",
	gw_msg_schedule_todo_progress_limitvalue:"进度率只能输入0~100之间的数字。",
	gw_msg_schedule_period_err:"期间设置错误。",
	gw_msg_schedule_period_timegap_err: "结束日期和时间应设置为比开始日期晚30分钟。",
	gw_msg_schedule_recur_type3_daywk: "Please select a recurrence day.",
	gw_msg_contact_nothing:"没有注册的地址薄。",
	gw_msg_org_member_nolist:"没有成员。",
	gw_msg_org_subdept_nolist:"没有下级部门。",
	gw_msg_absence_not_absmsg:"请输入不在消息。",
	gw_msg_absence_toolong_msg:"不在消息只能输入少于80个韩文字符(160个英文字符) 。",
	gw_msg_absence_not_period:"请输入不在期间。",
	gw_msg_absence_period_err_1:"不在结束日期必须设置不在开始日期以后。",
	gw_msg_absence_period_err_2: "您不能输入结束日期早于今天的不在期间。",
	gw_msg_absence_no_alter_signer:"代理审批人未被指定。",
	gw_msg_absence_absence_nolist:"没有注册的不在。",
	gw_msg_absence_cancel_add_absence:"您确定要取消制作不在吗?",
	gw_msg_absence_minimum_one_select:"如果要删除,应该选择至少一个以上。",
	gw_msg_password_no_old_password:"请输入旧的登录密码。",
	gw_msg_password_no_new_password:"请输入新的登录密码。",
	gw_msg_password_no_new_password_confirm:"请在确认密码里输入新的登录密码。",
	gw_msg_password_mismatch_new_password:"新的登录密码输入不一致。 请重新输入。",
	gw_msg_password_sameold_new_password:"新的登录密码与旧的登录密码相同。 请输入其他密码。",
	gw_msg_password_toolong_password:"设置密码必须最多30个字符。",
	gw_msg_password_tooshort_password:"设置密码必须至少有5个字符以上。。",
	gw_msg_password_no_old_sanc_password:"请输入旧审批密码。",
	gw_msg_password_no_new_sanc_password:"请输入新审批密码。",
	gw_msg_password_no_new_sanc_password_confirm:"清在确认密码里输入新审批密码。",
	gw_msg_password_mismatch_new_sanc_password:"新的审批密码输入不一致。请重新输入。",
	gw_msg_password_sameold_new_sanc_password:"您的新审批密码和旧审批密码相同。请输入其他密码。",
	gw_msg_password_input_password_for_sanc:"审批时，为了变更确认密码’ ，请输入登录密码。",
	gw_msg_password_tooshort_sanc_password:"审批设置密码必须至少有5个字符以上。",
	gw_error_system_error:"内部发生错误。 请向管理员咨询。",
	gw_error_invalid_request:"无效的请求。请向管理员咨询。",
	gw_error_session_expired:"登录超时。 请重新登录。",
	gw_error_access_denied:"未经授权的用户。 请登录后使用。",
	gw_error_unauthorized_phone_uid_number:"未经验证的手机。 请向管理员咨询。",
	gw_error_unregistered_new_multi_device:"未登记的手机。",
	gw_error_unregistered_new_multi_device_guide:"未登记的手机。 要注册一个新的终端就点击下方的[设备注册]按钮。 确认管理员后注册终端。",
	gw_error_appversion_notequal:"移动组件的应用程序已被更新。请重新安装应用程序。",
	gw_error_license_filenotfound:"没有许可证文件。",
	gw_error_license_invalid_info:"许可证信息是无效的。",
	gw_error_license_invalid_serverip:"没有发放许可证的服务器。",
	gw_error_license_expired:"许可证期间已到期。",
	gw_error_license_exceeduser:"已超出可连接的用户数。",
	gw_error_file_download:"下载文件时发生错误。 请向管理员咨询。",
	gw_error_file_transform:"转换文件时发生错误。 请向管理员咨询。",
	gw_error_file_unsupported_type:"不能下载的文件类型。",
	gw_error_failed_result:"无法确认结果。 ",
	gw_error_duplicate:"Duplicate error.",
	gw_success:"已回收批准文件。 ",
	gw_fail:"审批文件未被回收。",
	gw_fail_view:"摘要前查看失败。",
	gw_view:"查看摘要前",
	gw_withdraw:"批准回收",
	gw_check_doc:"阅览限制文件"
};var RES_STRING_ZH_TW = {
	gw_product_name:"组件",
	gw_common_loading_label:"正在导入中…",
	gw_common_pulldown_label:" 拖放下可以更新列表。",
	gw_common_pulldown_update_label:"下拉可以更新。",
	gw_common_pullup_label:"拖放下可以导入新的列表。",
	gw_common_pullup_refresh_label:"上拉可以查看更多列表。",
	gw_common_menu_label:"菜单",
	gw_common_back_label:"以前",
	gw_common_close_label:"关闭",
	gw_common_write_label:"写",
	gw_common_edit_label:"编辑",
	gw_common_canceledit_label:"取消编辑",
	gw_common_selectall_label:"全部选择",
	gw_common_cancelselect_label:"选择取消",
	gw_common_delete_label:"删除",
	gw_common_attach_label:"附件",
	gw_common_username_label:"姓名",
	gw_common_email_label:"电子邮件",
	gw_common_companyname_label:"公司名",
	gw_common_phone_label:"电话号码",
	gw_common_search_label:"搜索",
	gw_common_search_result_label:"搜索结果",
	gw_common_add_label:"添加",
	gw_common_select_label:"选择",
	gw_common_show_label:"查看",
	gw_common_hide_label:"隐藏",
	gw_common_ok_label:"确认",
	gw_common_set_label:"设置",
	gw_common_change_label:"更改",
	gw_common_cancel_label:"取消",
	gw_common_modify_label:"修改",
	gw_common_option_label:"选项",
	gw_common_byte_label:"byte",
	gw_common_kilobyte_label:"KB",
	gw_coomon_date_label:"Date",
	gw_coomon_time_label:"Time",
	gw_common_getmore_label:"更多查看..",
	gw_common_group_label:"组",
	gw_common_year_label:"年",
	gw_common_month_label:"月",
	gw_common_day_label:"日",
	gw_common_hour_label:"时",
	gw_common_minute_label:"分",
	gw_common_input_password_title:"输入密码",
	gw_common_refresh_label:"刷新",
	gw_common_piece_label:"个",
	gw_common_save_label:"保存",
	gw_mail_mailwrite_label:"写信",
	gw_mail_mailread_label:"邮件阅读",
	gw_mail_recvlist_label:"收件箱",
	gw_mail_sendlist_label:"发送邮箱",
	gw_mail_templist_label:"草稿箱",
	gw_mail_deletelist_label:"已删除邮件箱",
	gw_mail_receiver_label:"收件人",
	gw_mail_sender_label:"发件人",
	gw_mail_cc_label:"抄送人",
	gw_mail_confirm_receive_label:"确认收取",
	gw_mail_cancel_label:"回收",
	gw_mail_fly_label:"转发",
	gw_mail_reply_label:"回复",
	gw_mail_replyall_label:"全部回复",
	gw_mail_personalbox_label:"个人邮箱",
	gw_mail_personalbox_path:"个人邮箱",
	gw_mail_entire_state_label:"全部状态",
	gw_mail_bcc_label:"密送",
	gw_mail_ccbcc_lable:"抄送,密送",
	gw_mail_urgency_label:"紧急",
	gw_mail_security_label:"安全",
	gw_mail_title_label:"标题",
	gw_mail_send_label:"发送",
	gw_mail_include_org_attach_label:"现有附件",
	gw_mail_include_org_message:"包括原本",
	gw_mail_dbmail_sender_label:"发送",
	gw_mail_dbmail_to_label:"收取",
	gw_mail_dbmail_date_label:"日期",
	gw_mail_dbmail_original_message:"原本消息",
	gw_mail_notitle_label:"无标题",
	gw_mail_choose_samename_label:"选择同名异人",
	gw_mail_select_recv:"选择收件人",
	gw_schedule_label:"日程",
	gw_schedule_todo_label:"待办事项",
	gw_schedule_list_label:"日程列表",
	gw_schedule_equip_list_label:"设备预订列表",
	gw_schedule_equip_detaillist_label:"设备预订详细列表",
	gw_schedule_add_label:"添加日程/设备预订",
	gw_schedule_schadd_label:"添加日程/设备预订",
	gw_schedule_schmodify_label:"修改日程/设备预订",
	gw_schedule_sch6add_label:"添加日程",
	gw_schedule_sch6modify_label:"修改日程",
	gw_schedule_todo_list_label:"待办事项列表",
	gw_schedule_todo_add_label:"添加待办事项",
	gw_schedule_todo_modify_label:"修改待办事项",
	gw_schedule_search_label:"搜索日程/待办事项",
	gw_schedule2_search_label:"日程搜索",
	gw_schedule_sch_search_label:"日程搜索结果",
	gw_schedule_todo_search_label:"待办事项搜索结果",
	gw_schedule_base_date_label:"基准日 : ",
	gw_schedule_daily_label:"每日",
	gw_schedule_weekly_label:"每周",
	gw_schedule_monthly_label:"每月",
	gw_schedule_monthplan_label:"每月计划",
	gw_schedule_today_label:"今天",
	gw_schedule_shared_calendar_label:"共享日程",
	gw_schedule_all_calendar_label:"全部日历",
	gw_schedule_my_and_shared_calendar_label: "我的日历 + 共享日程",
	gw_schedule_my_calendar_label:"我的日历",
	gw_schedule_dept_calendar_label:"部门日历",
	gw_schedule_company_calendar_label:"公司日历",
	gw_schedule_user_calendar_label:"用户日历",
	gw_schedule_calendar_count_label:"件",
	gw_schedule_schedule_view_label:"日程查看",
	gw_schedule_equipment_view_label:"查看預定設備",
	gw_schedule_todo_view_label:"查看待辦事項",
	gw_schedule_title_label:"标题",
	gw_schedule_category_label: "类别",
	gw_schedule_owner_label:"所有者",
	gw_schedule_writer_label:"制作人",
	gw_schedule_repeat_label:"重复",
	gw_schedule_term_label:"期间",
	gw_schedule_write_date_label:"制作日期",
	gw_schedule_start_date_label:"开始日期",
	gw_schedule_end_date_label:"结束日期",
	gw_schedule_day_schedule_label:"一天日程 ",
	gw_schedule_period_label:"期间设置",
	gw_schedule_period_day_1_label:"当日",
	gw_schedule_period_day_2_label:"7天",
	gw_schedule_period_day_3_label:"1个月 ",
	gw_schedule_period_day_4_label:"3个月 ",
	gw_schedule_calendar_label:"日历",
	gw_schedule_attendee_label: "Attendee",
	gw_schedule_equipment_label:"设备",
	gw_schedule_all_equipment_label:"全部设备",
	gw_schedule_content_label:"内容",
	gw_schedule_reserve_equipment_label:"设备预订",
	gw_schedule_alarm_label:"通知",
	gw_schedule_alarm_label_0:"立即通知",
	gw_schedule_alarm_label_m:"分前",
	gw_schedule_alarm_label_h:"时间前",
	gw_schedule_alarm_label_d:"日前",
	gw_schedule_alarm_label_w:"周前",
	gw_schedule_alarm_label_M:"月前",
	gw_schedule_alarm_label_0mail:"立即發送電子郵件",
	gw_schedule_importance_label:"重要程度",
	gw_schedule_weight_high:"高",
	gw_schedule_weight_medium:"中",
	gw_schedule_weight_low:"低",
	gw_schedule_status_label:"状态",
	gw_schedule_progress_label:"进度率",
	gw_schedule_status_finish_label:"完成",
	gw_schedule_status_delay_label:"延期",
	gw_schedule_status_cancel_label:"取消",
	gw_schedule_status_progress_label:"进行",
	gw_schedule_status_notfinish_label:"未完成",
	gw_schedule_not_label:"没有",
	gw_schedule_recur_type0: "沒有重複",
	gw_schedule_recur_type1:"每日(周一至周日)",
	gw_schedule_recur_type2:"平日(周一至周五)",
	gw_schedule_recur_type3:"每周",
	gw_schedule_recur_type4:"每月",
	gw_schedule_recur_type5:"每月(星期)",
	gw_schedule_recur_type6:"每年",
	gw_schedule_recur_cycle_weekly: "Every week",
	gw_schedule_recur_cycle_weekly_num: "Every {{num}} weeks",
	gw_schedule_recur_cycle_type4_day: "Monthly {{dd}}",
	gw_schedule_recur_cycle_type4_ord_day_wk: "Monthly {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type4_last_wk: "Monthly last {{dayWk}}",
	gw_schedule_recur_cycle_type6_date: "Every year {{mmdd}}",
	gw_schedule_recur_cycle_type6_last: "The last day of {{mm}} every year",
	gw_schedule_recur_cycle_type6_ord_day_wk: "Every year {{mm}} {{ord}} {{dayWk}}",
	gw_schedule_recur_cycle_type6_last_wk: "Every year {{mm}} last {{dayWk}}",
	gw_schedule_recur_times: "last",
	gw_schedule_recur_times0: "first",
	gw_schedule_recur_times1: "second",
	gw_schedule_recur_times2: "third",
	gw_schedule_recur_times3: "fourth",
	gw_schedule_recur_times4: "fifth",
	gw_schedule_equipmentgroup_label:"设备组",
	gw_schedule_have_label:"有",
	gw_schedule_ownertype_user:"个人日程",
	gw_schedule_ownertype_dept:"部门日程",
	gw_schedule_security_level_label:"安全设置",
	gw_schedule_security_type_a:" 任何人都可以阅读",
	gw_schedule_security_type_d:"只有部门人员才可以阅读",
	gw_schedule_security_type_s:"部门人员/直属上下级部门人员可以阅读",
	gw_schedule_security_type_o:"只有所有者才可以阅读",
	gw_schedule_add_security_level_label:"公开范围",
	gw_schedule_add_security_type_a:"为所有人",
	gw_schedule_add_security_type_d:"为部门人员",
	gw_schedule_add_security_type_s:"为直属上下级部门人员",
	gw_schedule_add_security_type_o:"只所有者",
	gw_schedule_select_calendar:"日历选择",
	gw_schedule_select_equipment:"设备选择",
	gw_schedule_select_allcallist:"全部日历列表",
	gw_schedule_select_allequiplist:"全部设备列表",
	
	gw_schedule_delete_recur_title: "Recurring schedule",
	gw_schedule_delete_recur_option1: "Delete all recurring events",
	gw_schedule_delete_recur_option2: "Only delete selected events",
	gw_schedule_delete_recur_option4: "Delete future events with selected events",
	gw_schedule_delete_recur_option_select: "Please select a removal type.",
	gw_schedule_modify_recur_title: "Recurring schedule",
	gw_schedule_modify_recur_option1: "Edit all recurring events",
	gw_schedule_modify_recur_option2: "Edit only selected events",
	gw_schedule_modify_recur_option4: "Edit future events that include selected events",
	
	gw_board_name_label:"公告板",
	gw_board_recentlist_label:"最近帖子",
	gw_board_alllist_label:"全部公告板",
	gw_board_favlist_label:"收藏夹公告板",
	gw_board_comment_label:"意见",
	gw_board_read_label:"閱讀留言",
	gw_board_postername_label:"发帖者 ",
	gw_board_write_title:"写帖子",
	gw_board_wirte_label:"发布",
	gw_board_enddate_label:"结束日期",
	gw_board_everlast_label:"永久",
	gw_board_preface_label:"页眉",
	gw_board_select_preface:"页眉选择",
	gw_board_title_label:"标题",
	gw_board_write_comment_label:"制作意见",
	gw_board_write_of_comment_label:"寫回信",
	gw_contact_personal_label:"个人地址薄",
	gw_contact_dept_label:"部门地址薄",
	gw_contact_group_label:"组内容查看",
	gw_contact_personal_info_label:"个人信息",
	gw_contact_org_info_label:"公司信息",
	gw_contact_gender_m:"男",
	gw_contact_gender_f:"女",
	gw_contact_search_name:"通过名字搜索地址薄",
	gw_contact_search_email:"通过电子邮件搜索地址薄",
	gw_contact_search_orgname:"通过公司名搜索地址薄",
	gw_contact_search_phone:"通过电话号码搜索地址薄",
	gw_org_subdept_label:"下级部门",
	gw_org_member_label:"成员",
	gw_org_tree_label:"组织结构图",
	gw_org_select_label:"选择组织结构图",
	gw_org_userinfo_label:"用户信息",
	gw_org_includesub_label:"下级包括",
	gw_org_search_deptname:"通过部门名字搜索部门",
	gw_org_search_username:"通过名字搜索成员",
	gw_org_search_email:"通过电子邮件搜索成员",
	gw_org_search_phone:"通过电话号码搜索成员",
	gw_org_search_mobile:"通过手机号码搜索成员",
	gw_org_search_empcode: "员工编号码搜索成员",
	gw_org_search_posname: "帖子名字搜索成员",
	gw_org_search_rankname: "等级名字搜索成员",
	gw_org_search_dutyname: "办公室名字搜索成员",
	gw_org_search_business: "责任字搜索成员",	
	gw_org_root_label:"最上级部门",
	gw_sign_label:"审批",
	gw_sign_waitlist_label:"批准等待",
	gw_sign_gongramwaitlist_label: "等待分發",
	gw_sign_gongramcompletelist_label: "分發完畢",
	gw_sign_nowlist_label:"進行批准",
	gw_sign_receiptwaitlist_label:"等待接收",
	gw_sign_userprocessedlist_label:"个人文件箱[批准的文件]",
	gw_sign_mycompletelist_label:"个人文件箱[完成文件]",
	gw_sign_completelist_label:"記錄档案[已完成]",
	gw_sign_dispatchlist_label: "발송처리",
	gw_sign_details_label:"审批正文",
	gw_sign_approve_label:"审批",
	gw_sign_reject_label:"退回",
	gw_sign_junkyul_label:"审批",
	gw_sign_postpone_label:"暂停",
	gw_sign_gongram_label:"共览确认",
	gw_sign_confirm_label:"抄送确认",
	gw_sign_write_comment_label:"制作意见",
	gw_sign_cancel_write_comment_label:"取消製作意見",
	gw_sign_show_comment_label:"查看意见",
	gw_sign_approve_flow_label:"批准进行状态",
	gw_sign_withdraw_label:"撤回批准",
	gw_sign_cancel_label: "取消批准",
	gw_sign_show_summary_label:"摘要",
	gw_sign_show_linkdoc_label:"有关文件",
	gw_sign_input_password_title:"輸入审批密碼",
	gw_sign_dispatch_label: "调度处理",
	gw_sign_signlist_type_all_label:"全部",
	gw_sign_signlist_type_1_label:"普通文件",
	gw_sign_signlist_type_3_label:"收取文件",
	gw_sign_signlist_type_2_label:"发送文件",
	gw_sign_signlist_type_7_label:"报告文件",
	gw_sign_signlist_type_5_label:"审计文件",
	gw_sign_signlist_type_4_label:"合作文件",
	gw_sign_receiptlist_type_all_label:"全部",
	gw_sign_receiptlist_type_2_label:"到达",
	gw_sign_receiptlist_type_4_label:"接收",
	gw_sign_receiptlist_type_16_label:"返回",
	gw_sign_receiptlist_type_8192_label:"内部返回",
	gw_sign_floworder_label:"序号",
	gw_sign_approval_label:"处理方法",
	gw_settings_label:"设置",
	gw_settings_absence_label:"不在设置",
	gw_settings_userinfo_label:"个人信息设置",
	gw_settings_password_label:"密码设置",
	gw_settings_license_label: "許可證信息",
	gw_settings_absence_list_label:"不在设置 列表",
	gw_settings_absence_add_label:"添加",
	gw_settings_absence_absadd_label:"不在添加",
	gw_settings_absence_save_label:"保存",
	gw_settings_absence_modify_label:"修改",
	gw_settings_absence_absmodify_label:"不在修改",
	gw_settings_absence_copy_label:"复制",
	gw_settings_absence_set_label:"不在设置",
	gw_settings_absence_remove_label:"不在解除",
	gw_settings_absence_period_label:"期间",
	gw_settings_absence_period_start_label:"不在开始",
	gw_settings_absence_period_end_label:"不在结束",
	gw_settings_absence_period_reason_label:"不在理由",
	gw_settings_absence_period_reason_1_label:"教育",
	gw_settings_absence_period_reason_2_label:"出差",
	gw_settings_absence_period_reason_3_label:"外出",
	gw_settings_absence_period_reason_4_label:"休假",
	gw_settings_absence_period_reason_5_label:"早退",
	gw_settings_absence_period_reason_6_label:"年假",
	gw_settings_absence_period_reason_7_label:"病假",
	gw_settings_absence_period_reason_8_label:"公假",
	gw_settings_absence_period_reason_9_label:"特别休假",
	gw_settings_absence_period_reason_10_label:"缺勤",
	gw_settings_absence_period_reason_11_label:"迟到 ",
	gw_settings_absence_period_reason_12_label:"不在",
	gw_settings_absence_period_reason_13_label:"休假",
	gw_settings_absence_period_reason_14_label:"退职",
	gw_settings_absence_mail_label:"邮件设置",
	gw_settings_absence_mail_reply_msg_label:"郵件自動回應<BR>消息", // tkofs 不在消息
	gw_settings_absence_mail_alt_rcpt_label:"代理收件人",
	gw_settings_absence_sanc_label:"批准设置",
	gw_settings_absence_sanc_alt_signer_label:"代理审批人",
	gw_settings_absence_sanc_handling1_label:"批准后进行文件",
	gw_settings_absence_sanc_handling1_0_label:"待批准",
	gw_settings_absence_sanc_handling1_1_label:"代理审批人",
	gw_settings_absence_sanc_handling1_2_label:"不批准",
	gw_settings_absence_sanc_handling2_label:"批准后完成文件",
	gw_settings_absence_sanc_handling2_0_label:"待批准",
	gw_settings_absence_sanc_handling2_1_label:"代理审批人",
	gw_settings_user_alias_label:"别称",
	gw_settings_user_phone_label:"电话号码",
	gw_settings_user_fax_label:"传真号码",
	gw_settings_user_mobile_label:"手机号码",
	gw_settings_user_business_label:"负责业务",
	gw_settings_password:"密码设置",
	gw_settings_password_cbloginpasswd:"登录密码 更改",
	gw_settings_password_txtoldloginpasswd:"旧密码",
	gw_settings_password_txtloginpasswd:"新密码",
	gw_settings_password_txtloginpasswd_confirm:"确认密码",
	gw_settings_password_rdosanc:"审批密码",
	gw_settings_password_rdosanc_same:"以相同的值变更",
	gw_settings_password_rdosanc_cancel:"分别指定",
	gw_settings_password_cbsancpasswd:"变更审批密码",
	gw_settings_password_txtoldsancpasswd:"旧密码",
	gw_settings_password_txtsancpasswd:"新密码",
	gw_settings_password_txtsancpasswd_confirm:"确认密码",
	gw_settings_password_cbsancpasswd_check:"审批时确认密码",
	gw_settings_password_txtsancpasswd_check:"登录密码",
	gw_settings_password_rdosanc_confirm:"确认与否",
	gw_settings_password_rdosanc_confirm_confirm:"确认",
	gw_settings_password_rdosanc_confirm_cancel:"不确认",
	gw_msg_common_err:"发生错误。",
	gw_msg_common_confirm_delete:"您确定要删除吗?",
	gw_msg_common_no_change:"没有变更事项。",
	gw_msg_common_confirm:"您确定要更改设置的值吗?",
	gw_msg_common_save_confirm:"通过设置的值，是否要保存吗?",
	gw_msg_common_change_success:"已更改。",
	gw_msg_common_nosearchdata:"没有搜索结果。",
	gw_msg_common_input_password:"请输入密码。",
	gw_msg_common_wrong_password:"无效的密码。",
	gw_msg_common_nosearch_title:"没有标题搜索",
	gw_msg_common_inputsubject:"请输入标题。",
	gw_msg_common_toolong_msg:"标题长度太长。",
	gw_msg_common_toolong_summary:"正文长度太长。",
	gw_msg_common_success:"已处理。",
	gw_msg_common_fail:"已失败。",
	gw_msg_common_save:"已保存。",
	gw_msg_mail_nolist:"邮箱里没有邮件。",
	gw_msg_mail_noselect:"没有所选的邮件。",
	gw_msg_mail_confirm_cancel:"您确定要回收所选的邮件吗?",
	gw_msg_mail_confirm_delete:"您确定要删除所选的邮件吗?",
	gw_msg_mail_cancel_success:"件的郵件已經回收了。",
	gw_msg_mail_delete_success:"已保存。",
	gw_msg_mail_already_recovered:"已回收的邮件。 ",
	gw_msg_mail_not_read:"是未读的邮件。 ",
	gw_msg_mail_noReceiver:"没有收件人信息。",
	gw_msg_mail_inputsubject:"请输入标题。",
	gw_msg_mail_inputbody:"请输入内容。",
	gw_msg_mail_inputto:"未指定收件人。",
	gw_msg_mail_erroradress:"收件人（外部）邮件地址形式不正确。",
	gw_msg_mail_smtp:"请先在组件门户网站设置互联网邮件服务器。",
	gw_msg_mail_mismatch_org:"没有匹配的成员或部门。",
	gw_msg_mail_write_success:"已发送邮件。",
	gw_msg_mail_choose_samename:"已存在同名的用户。 请在选择箱指定其他用户。",
	gw_msg_mail_invalid_email:"错误的电子邮件地址。",
	gw_msg_mail_cancel_writeMail:"您确定要取消写信码?",
	gw_msg_mail_input_receiver:"请输入收件人。",
	gw_msg_mail_input_cc:"请输入抄送人。",
	gw_msg_mail_input_bcc:"请输入密送人。。.",
	gw_msg_mail_canceled_not_deleted:"已回收的邮件。 ",
	gw_msg_mail_externaldomain_not_sended:"在收件人中有外部域名的用户，无法发送邮件。",
	gw_msg_sign_nolist:"在审批箱里没有文件。",
	gw_msg_sign_external_doc:"是非电子文件。",
	gw_msg_sign_external6_doc:"是手写文件。",
	gw_msg_sign_read_noauth_err:"没有阅览文件的权限。",
	gw_msg_sign_input_password:"请输入审批密码。",
	gw_msg_sign_wrong_password:"无效的密码。",
	gw_msg_sign_confirm_approve:"您确定要请求批准吗?",
	gw_msg_sign_confirm_reject:"您确定要请求退回吗 ?",
	gw_msg_sign_confirm_postpone:"您确定要暂停文件吗 ?",
	gw_msg_sign_confirm_gongram: "Do you want to mark as viewed?",
	gw_msg_sign_confirm_dispatch: "Do you want to dispatch?",
	gw_msg_sign_approve_success:"已处理批准审批。",
	gw_msg_sign_approve_fail:"批准审批失败。",
	gw_msg_sign_reject_success:"已退回。 ",
	gw_msg_sign_reject_fail:"未被退回。",
	gw_msg_sign_postpone_success:"該文件已被保留。",
	gw_msg_sign_postpone_fail:"未被暂停。",
	gw_msg_sign_gongram_success: "Mark as viewed.",
	gw_msg_sign_gongram_fail: "Mark as viewed failed.",
	gw_msg_sign_confirm_withdraw:"您确定要回收审批文件吗?",
	gw_msg_sign_confirm_cancel: "您确定要取消审批文件吗?",
	gw_msg_sign_cancel_success: "取消已處理。",
	gw_msg_sign_network_err:"网络环境不稳定，无法确认结果。 ",
	gw_msg_sign_agent_err:"在移动审批系统发生错误。 请向管理员咨询。",
	gw_msg_sign_not_support_apprdoc:"在移动不支持的审批文件。",
	gw_msg_sign_not_transform_apprdoc:"没有转换文件，不能查看审批文件。",
	gw_msg_sign_limitread_apprlinkdoc:"已超出可在移动阅览的最大数量。",
	gw_msg_sign_withdraw_opinion: "該文檔是從您的手機中檢索到的。",	
	gw_msg_board_authread_err:"没有查看列表的权限。",
	gw_msg_board_nolist:"没有帖子。",
	gw_msg_board_nobldata:"没有注册的公告板。",
	gw_msg_board_read_noauth_err:"没有查询帖子的权限。",
	gw_msg_board_invalid_enddate:"您只能选择结今天以后的结束日期。",
	gw_msg_board_cancel_write:"您确定要取消写帖子吗?",
	gw_msg_board_comment_nolist:"没有注册的意见。",
	gw_msg_board_not_match_passwd_manage:"修改/删除密码不匹配。",
	gw_msg_schedule_invalid_code:"无效的代码: ",
	gw_msg_schedule_schedule_nolist:"没有注册的日程。",
	gw_msg_schedule_check_calendar:"日历必须至少选择一个以上。",
	gw_msg_schedule_no_select_calendar:"请选择日历。",
	gw_msg_schedule_equip_nolist:"没有注册的设备日程。",
	gw_msg_schedule_check_equip: "Please select a facility to reserve.",
	gw_msg_schedule_todo_nolist:"没有注册的待办事项。",
	gw_msg_schedule_equip_nothing:"没有注册的设备。",
	gw_msg_schedule_cancel_add_schedule:"您确定要取消制作日程吗?",
	gw_msg_schedule_cancel_add_todo:"您确定要取消制作待办事项吗?",
	gw_msg_schedule_check_reserve_equip:"已经预订设备。",
	gw_msg_schedule_invalid_letter_err:"不能使用\'\"\\等的字符。",
	gw_msg_schedule_todo_empty_processrate:"请输入进度率。",
	gw_msg_schedule_todo_progress_invalidvalue:"进度率只能输入数字。",
	gw_msg_schedule_todo_progress_limitvalue:"进度率只能输入0~100之间的数字。",
	gw_msg_schedule_period_err:"期间设置错误。",
	gw_msg_schedule_period_timegap_err: "结束日期和时间应设置为比开始日期晚30分钟。",
	gw_msg_schedule_recur_type3_daywk: "Please select a recurrence day.",
	gw_msg_contact_nothing:"没有注册的地址薄。",
	gw_msg_org_member_nolist:"没有成员。",
	gw_msg_org_subdept_nolist:"没有下级部门。",
	gw_msg_absence_not_absmsg:"请输入不在消息。",
	gw_msg_absence_toolong_msg:"不在消息只能输入少于80个韩文字符(160个英文字符) 。",
	gw_msg_absence_not_period:"请输入不在期间。",
	gw_msg_absence_period_err_1:"不在结束日期必须设置不在开始日期以后。",
	gw_msg_absence_period_err_2: "您不能输入结束日期早于今天的不在期间。",
	gw_msg_absence_no_alter_signer:"代理审批人未被指定。",
	gw_msg_absence_absence_nolist:"没有注册的不在。",
	gw_msg_absence_cancel_add_absence:"您确定要取消制作不在吗?",
	gw_msg_absence_minimum_one_select:"如果要删除,应该选择至少一个以上。",
	gw_msg_password_no_old_password:"请输入旧的登录密码。",
	gw_msg_password_no_new_password:"请输入新的登录密码。",
	gw_msg_password_no_new_password_confirm:"请在确认密码里输入新的登录密码。",
	gw_msg_password_mismatch_new_password:"新的登录密码输入不一致。 请重新输入。",
	gw_msg_password_sameold_new_password:"新的登录密码与旧的登录密码相同。 请输入其他密码。",
	gw_msg_password_toolong_password:"设置密码必须最多30个字符。",
	gw_msg_password_tooshort_password:"设置密码必须至少有5个字符以上。。",
	gw_msg_password_no_old_sanc_password:"请输入旧审批密码。",
	gw_msg_password_no_new_sanc_password:"请输入新审批密码。",
	gw_msg_password_no_new_sanc_password_confirm:"清在确认密码里输入新审批密码。",
	gw_msg_password_mismatch_new_sanc_password:"新的审批密码输入不一致。请重新输入。",
	gw_msg_password_sameold_new_sanc_password:"您的新审批密码和旧审批密码相同。请输入其他密码。",
	gw_msg_password_input_password_for_sanc:"审批时，为了变更确认密码’ ，请输入登录密码。",
	gw_msg_password_tooshort_sanc_password:"审批设置密码必须至少有5个字符以上。",
	gw_error_system_error:"内部发生错误。 请向管理员咨询。",
	gw_error_invalid_request:"无效的请求。请向管理员咨询。",
	gw_error_session_expired:"登录超时。 请重新登录。",
	gw_error_access_denied:"未经授权的用户。 请登录后使用。",
	gw_error_unauthorized_phone_uid_number:"未经验证的手机。 请向管理员咨询。",
	gw_error_unregistered_new_multi_device:"未登记的手机。",
	gw_error_unregistered_new_multi_device_guide:"未登记的手机。 要注册一个新的终端就点击下方的[设备注册]按钮。 确认管理员后注册终端。",
	gw_error_appversion_notequal:"移动组件的应用程序已被更新。请重新安装应用程序。",
	gw_error_license_filenotfound:"没有许可证文件。",
	gw_error_license_invalid_info:"许可证信息是无效的。",
	gw_error_license_invalid_serverip:"没有发放许可证的服务器。",
	gw_error_license_expired:"许可证期间已到期。",
	gw_error_license_exceeduser:"已超出可连接的用户数。",
	gw_error_file_download:"下载文件时发生错误。 请向管理员咨询。",
	gw_error_file_transform:"转换文件时发生错误。 请向管理员咨询。",
	gw_error_file_unsupported_type:"不能下载的文件类型。",
	gw_error_failed_result:"无法确认结果。 ",
	gw_error_duplicate:"Duplicate error.",
	gw_success:"已回收批准文件。 ",
	gw_fail:"审批文件未被回收。",
	gw_fail_view:"摘要前查看失败。",
	gw_view:"查看摘要前",
	gw_withdraw:"批准回收",
	gw_check_doc:"阅览限制文件"
};// 로컬 off line 테스트시 크롬 시작 옵션 변경: --allow-file-access-from-files
function setPulledString(view_id){
	$('#_TEMPLATE_'+view_id).find('#pulldown').attr('data-iscroll-loading-text', COMMON_PULL_DOWN_LOADING);
	$('#_TEMPLATE_'+view_id).find('#pulldown').attr('data-iscroll-pulled-text', COMMON_PULL_DOWN_PULLED);
	$('#_TEMPLATE_'+view_id).find('#pulldown').html(COMMON_PULL_DOWN_TEXT);
	$('#_TEMPLATE_'+view_id).find('#pullup').attr('data-iscroll-loading-text', COMMON_PULL_UP_LOADING);
	$('#_TEMPLATE_'+view_id).find('#pullup').attr('data-iscroll-pulled-text', COMMON_PULL_UP_PULLED);
	$('#_TEMPLATE_'+view_id).find('#pullup').html(COMMON_PULL_UP_TEXT);
}


function hideHeader() {
	var view = $.mobile.activePage;	
	view.find("[data-role=header]").remove();
}

/**
 * 헤더 타이틀 변경 (TODO HMPPlugin으로 변경)
 * 
 * @param title
 */
function changeTitle(title) {
	var view = $.mobile.activePage;	
	view.find("[data-role=header]").eq(0).find("h1").text(title);
}

/**
 * 헤더 좌측 버튼 추가 (TODO HMPPlugin으로 변경)
 * 
 * @param text
 * @param href
 * @param successFN
 * @param errorFN
 */
function addLeftButton(text, href, successFN, errorFN) {
	
}

/**
 * 헤더 우측 버튼 추가 (TODO HMPPlugin으로 변경)
 * 
 * @param text
 * @param href
 * @param successFN
 * @param errorFN
 */
function addRightButton(text, href, successFN, errorFN) {
	
}

/**
 * 뷰포트 변경 (메일상세, 게시물 상세에서는 scale 변경 가능. 그외는 scale 변경 불가)
 * 
 * @param can_change_scale
 */
function changeViewport(can_change_scale) {
	if (can_change_scale)
		$("meta[name=viewport]").attr("content", "initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0, user-scalable=yes");
	else // default
		$("meta[name=viewport]").attr("content", "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no");
}

function showMenuByID(menuId) {
	if (GWPlugin.usePlugin)  {
		GWPlugin.showMenuByID(menuId, function(){}, function() {});
	}
}

function getFileSizeUnit() {
	return GW_OpenAPI.attach_filesize_unit;
}

// 파일 사이즈 단위 변환
function convertFileSizeUnit(size) {
	var s = [MGW_RES.get("gw_common_byte_label"), MGW_RES.get("gw_common_kilobyte_label")];
	var e = 0;
	
	if (getFileSizeUnit() == "byte") {
		return size + " " + s[e];
	}
	else {
		e = 1;
		return (size/Math.pow(1024, Math.floor(e))).toFixed(0) + "" + s[e];
	}
}

// URL 파라미터 파서
function parseURLParameter(url) {
	var ParameterObject = new Object();
	
	if (url.indexOf("?") == -1) {
		return ParameterObject();
	}
	
	var parameter = url.split("?")[1];
	var paramArr = parameter.split("&");
	for (var i=0; i<paramArr.length; i++) {
		var term = paramArr[i].split("=");
		ParameterObject[term[0]] = term[1];
	}
	
	return ParameterObject;
}

// GWPlugin.showURL()을 통해 URL 열기
function popupLink(link) {
	GWPlugin.showURL(link, function(){}, function(){});
}

// URL이 포함된 경우 GWPlugin.showURL()을 통해 URL 열도록 link 변경
function converPopupLink(contents) {
	$(contents).html($(contents).html().replace(/(<img[^>]*notification[^>]*>)/g, ""));
	var outlinkList = $(contents).find("a");
	$.each(outlinkList, function(i, value) {
		var link = outlinkList.eq(i).attr("href");
		if (link != "" && link != undefined && link.indexOf("bigfile")<0) {
			if (link.indexOf("http", 0) > -1) {
				outlinkList.eq(i).attr("href", "javascript:popupLink('" + link + "');");
			}
		}else if(link != "" && link != undefined && link.indexOf("bigfile")>-1){
			outlinkList.eq(i).attr("href", "javascript:alert('대용량 파일은 모바일에서 \\n다운로드받을수 없습니다.');");
		}
	});
}

function getLocale() {
	if (navigator) {
		if (navigator.language) {
			return navigator.language;
		} else if (navigator.browserLanguage) {
			return navigator.browserLanguage;
		} else if (navigator.systemLanguage) {
			return navigator.systemLanguage;
		} else if (navigator.userLanguage) {
			return navigator.userLanguage;
		}
	} else {
		return "";
	}
} 

// DateBox locale Set
function setDateBoxLang(){
	if (startsWith(MGW_RES.locale, 'KO')) {
		jQuery.extend(jQuery.mobile.datebox.prototype.options, {
		useLang: 'ko'
	});
	} else if (startsWith(MGW_RES.locale, 'EN')) {
		jQuery.extend(jQuery.mobile.datebox.prototype.options, {
		useLang: 'en'
	});
	} else if (startsWith(MGW_RES.locale, 'JA')) {
		jQuery.extend(jQuery.mobile.datebox.prototype.options, {
		useLang: 'ja'
	});
	} else {
		jQuery.extend(jQuery.mobile.datebox.prototype.options, {
		useLang: 'ko'
	});
	}
	// SMTP (DB메일에서만 SMTP 설정 여부 확인) 미리 로딩
	if (GW_OpenAPI.mail_type == "db") {
		getMailSmtp();
	}
}

// 이메일 형식인지 체크
function checkEmailFormat(email)
{
	email = email.replace(" ", "");
	email = trim(email);

	if (email == "")
	 {
	  return false;
	 }
	 else if(email != "") 
	 {
		if (email.split("<").length > 1)
			email = email.split("<")[1];

		 email = email.replace(">", "");
		
		 reg = new RegExp("^[\\w\\-]+(\\.[\\w\\-_]+)*@[\\w\\-]+(\\.[\\w\\-]+)*(\\.[a-zA-Z]{2,3})$", "gi");
		 if (!reg.test(email))
		 {
		   return false;
		 }
	 }
	 return true;
}

function trim(str){
	try {
		str = str.replace(/^\s*/,'').replace(/\s*$/, ''); 
	}catch (e) {
		return str;
	}
	   
   return str;
}

function isValidNumber(value) {
	var numPattern = /([^0-9])/;
    numPattern = value.match(numPattern);
    if(numPattern != null){
        return false;
    }
    return true;
}

/*
 * java.lang.String.endsWith() 구현
 */
function endsWith(str, checker){
	if(str!=null && checker!=null && str.length > checker.length){
		if(str.substr(str.length-checker.length).toUpperCase() == checker.toUpperCase()){
			return true;
		} else {
			return false;
		}
	}
	else {        
		return false;    
	}
}

/*
 * java.lang.String.startsWith() 구현
 */
function startsWith(str, checker){
	if(str!=null && checker!=null && str.length > checker.length){
		if(str.toUpperCase().substr(0,checker.toUpperCase().length) == checker.toUpperCase()){
			return true;
		} else {
			return false;
		}
	}
	else {
		return false;
	}
}
/**
 * locale 별 translator에서 변수를 대체 하기 위한 함수
 * 
 * @param params :
 *            json 형태 ex) "test {{aa}} plan {{bb}}".replaceTransWord({aa:
 *            "pass", "bb":"complete"}) ===> test pass plan complete
 */
String.prototype.replaceTransWord = function(params){
	var orgText = this;
	$.map(params, function(value, key){
		orgText = orgText.replace("{{"+key+"}}", value);
	});
	return orgText;
}
/**
 * 문자 lpad
 * 
 * @param str
 * @param minLen
 *            (optional)
 * @returns {String}
 */
String.prototype.lpad = function(str, minLen){
	var val = this.toString();
	if(!minLen) {
		return str + val;
	} else {
		if(this.length < minLen) {
	    	return str + val;
	    } else {
	    	return val;
	    }
    }
};

// GWPlugin.showSidebarMenu()을 통해 메뉴로 이동
function goMenu() {
	GWPlugin.showSidebarMenu(function(){}, function(){});
}

// GWPlugin.popView()을 통해 상세화면에서 앱목록으로 이동
function goList() {
	hideWebView();
	GWPlugin.popView(function(){}, function(){});
}

function hideWebView() {
	$("body").attr("style", "display:none;");
} 

function showWebView() {
	$("body").removeAttr("style");
}

/**
 * DateBox 설정 (시작일 설정 시 종료일이 시작일 이전으로 셋팅 불가능하도록) 적용 대상 : 일정&할일 추가, 부재 설정
 */
function doSetDatebox() {
	var view = $.mobile.activePage;
	
	var startDt = view.find("#dtstart_date").val().split(".");
	var defaultPickerValue = [startDt[0], (startDt[1]-1), (startDt[2]-1)];
	var presetDate = new Date(defaultPickerValue[0], defaultPickerValue[1], defaultPickerValue[2], 0, 0, 0, 0);
	var todaysDate = new Date();
	var lengthOfDay = 24 * 60 * 60 * 1000;
	var diff = parseInt((((presetDate.getTime() - todaysDate.getTime()) / lengthOfDay)+1)*-1,10);
	    
	view.find("#dtend_date").data('datebox').options.defaultPickerValue = defaultPickerValue;
	view.find("#dtend_date").data('datebox').options.minDays = diff;
}

/**
 * DateBox 설정 (사용자가 시작일 변경시 종료일보다 나중일 경우 종료일을 시작일과 동일하게 변경함.) 적용 대상 : 일정&할일 추가,
 * 부재 설정
 */
$('#dtstart_date').live('change', function() {
	var view = $.mobile.activePage;
	
	var startDt = view.find("#dtstart_date").val().split(".");
	var endDt = view.find("#dtend_date").val().split(".");
	var defaultPickerValue = [startDt[0], (startDt[1]-1), (startDt[2]-1)];
	
	if (!checkPeriodDate(startDt[0], startDt[1], startDt[2], endDt[0], endDt[1], endDt[2])) {
		view.find("#dtend_date").val(view.find("#dtstart_date").val());
		view.find("#txt_dtend_date").val(view.find("#dtstart_date").val());
		startDt = view.find("#dtstart_date").val().split(".");
		endDt = view.find("#dtend_date").val().split(".");
		defaultPickerValue = [startDt[0], (startDt[1]-1), (startDt[2])];
	}
	
	var presetDate = new Date(defaultPickerValue[0], defaultPickerValue[1], defaultPickerValue[2], 0, 0, 0, 0);
	var todaysDate = new Date();
	var lengthOfDay = 24 * 60 * 60 * 1000;
	var diff = parseInt((((presetDate.getTime() - todaysDate.getTime()) / lengthOfDay)+1)*-1,10);
	    
	view.find("#dtend_date").data('datebox').options.defaultPickerValue = defaultPickerValue;
	view.find("#dtend_date").data('datebox').options.minDays = diff;
});

function showEmptyPage() {
	PAGE_CONTROLLER.showPage("empty_page");
}

function showPageLoadingView() {
	if (GWPlugin.usePlugin) {
		GWPlugin.showWaitingView(10, function(){}, function(){});
	} else {
		$.mobile.showPageLoadingMsg();
	}
}

function hidePageLoadingView() {
	if (GWPlugin.usePlugin) {	
		GWPlugin.closeWaitingView(function(){}, function(){});
	} else {
		$.mobile.hidePageLoadingMsg();
	}
}

function replaceNewlineTag(message) {
	while (message.indexOf("\\n") !== -1) {
	   message = message.replace("\\n", "<br>");
	}
	
	return message;
}

// textarea 자동 높이조정
function onFitSizeOfTextArea(obj) {
	var Key=window.event.keyCode;
    var Cnt = EnterCount(obj); 
    
    while(obj.clientHeight < obj.scrollHeight )
    {
    	obj.rows = obj.rows + 1;
    }
    
    if(Key==13){
    	obj.rows = obj.rows + 1;
    } else if(Key==8){
    	if(obj.rows > 1){
    		obj.rows = obj.rows - 1;
    	}
    }
}

function EnterCount(obj){
	var entCount=0;
	for(i=0; i<obj.value.length; i++){
	   if(obj.value.charAt(i)=="\n") {
		   entCount++;
	   }
    }
	return entCount;
}

function closePopupViewer() {
	if (GWPlugin.usePlugin) {
		hideWebView();
		GWPlugin.closePopupViewer("", false);
		return;
	}
	else {
		PAGE_CONTROLLER.goBack();
	}	
}
/**
 * data-role=popup 유형의 창 닫기
 * 
 * @param id
 */
function closeDialog(id){
	$.mobile.activePage.find("#"+id).popup("close");
}
 
function htmlDecode(str) {
	return str.trim().replace(/</gi, '&lt;').replace(/>/gi, '&gt;')
}var SETTING_SECOND = ":00";
/* 달력에 있는 날짜인지 검사한다. */
function checkDate(y, m, d) {
	d2 = new Date(y, m, d).getDate();
	if (d != d2)
		return false;
	
	return true;
}

/* 종료날짜가 시작날짜보다 나중인지 검사한다. */
function checkPeriodDate(fy, fm, fd, ty, tm, td) {
	return checkDateTimePeriod(fy, fm, fd, 0, 0, ty, tm, td, 0, 0);
}

/* 종료시간이 시작시간보다 나중인지 검사한다. */
function checkTimePeriod(fh, fmi, th, tmi) {
	return checkDateTimePeriod(0, 0, 0, fh, fmi, 0, 0, 0, th, tmi);
}

/* 기준 날짜가 시작시간 부터 종료 일자 사이에 있는지 검사한다. */
function checkDatePeriod(fdate, tdate, bdate) {
	var fy = getYear(fdate);
	var fm = getMonth(fdate);
	var fd = getDate(fdate);
	
	var ty = getYear(tdate);
	var tm = getMonth(tdate);
	var td = getDate(tdate);
	
	var by = getYear(bdate);
	var bm = getMonth(bdate);
	var bd = getDate(bdate);
	
	var	fromDate = new Date(fy, fm - 1, fd, 0, 0);
	var	toDate = new Date(ty, tm - 1, td, 24, 0);
	var	baseDate = new Date(by, bm - 1, bd, 12, 0);
	
	if (baseDate >= fromDate) {
		if (baseDate <= toDate) {
			return true;
		}
	} else {
		return false;
	}
		
}

/* 종료날짜(시간포함)가 시작날짜(시간포함)보다 나중인지 검사한다. */
function checkDateTimePeriod(fy, fm, fd, fh, fmi, ty, tm, td, th, tmi, sameTimeCk) {
	var fromTime = new Date(fy, fm - 1, fd, fh, fmi);
	var toTime = new Date(ty, tm - 1, td, th, tmi);

	if (sameTimeCk == "true") {
		if (fromTime < toTime) {
			return	true;
		}
		else {
			return	false;
		}
	}
	else {
		if (fromTime <= toTime) {
			return	true;
		}
		else {
			return	false;
		}
	}
}

/* 종료날짜(시간포함)가 시작날짜(시간포함)보다 TimeGap(MilliSecond) 이 있는지 검사한다. */
function checkDateTimePeriodWithTimeGap(fy, fm, fd, fh, fmi, ty, tm, td, th, tmi, timeGap) {
	var fromTime = new Date(fy, fm - 1, fd, fh, fmi);
	var toTime = new Date(ty, tm - 1, td, th, tmi);
	
	if (toTime - fromTime >= timeGap) {
		return true;
	} else {
		return false;
	}
}

/* 파라미터 값의 날짜가 오늘보다 이전 날짜인 경우 */
function checkPastDateTime(fy, fm, fd, fh, fmi) {
	var fromTime	= new Date(fy, fm - 1, fd, fh, fmi);
	var curTime		= new Date();

	if (fromTime <= curTime) {
		return	false;
	}
	else {
		return	true;
	}
}

function checkEqualDate(fy, fm, fd, ty,tm, td) {
	if (fy != ty || fm != tm || fd != td) {
		return false;
	}
	return true;
}
function checkEqualDateTime(fy, fm, fd, fh, fmi, ty,tm, td, th, tmi) {
	if (checkEqualDate(fy, fm, fd, ty, tm, td) 
		&& checkEqualTime(fh, fmi, th, tmi)) {
		return true;
	}
	else {
		return false;
	}
}

/* 종료날짜가 시작날짜보다 나중인지 검사한다. */
function checkEqualTime(fh, fm, th, tm) {
	if (fh != th || fm != tm) {
		return false;
	}

	return true;
}
/**
 * 해당 일이 마지막 날짜인지 여부 반환
 * 
 * @param strDate
 * @returns {Boolean}
 */
function checkIsLastDate(strDate){
	return strDate === getLastDateStr(strDate);
}

/**
 * 해당 일이 해당달의 마지막 주인지 여부 반환
 * 
 * @param strDate
 * @returns {Boolean}
 */
function checkIsLastWeek(strDate){
	return getMonth(strDate) != getMonth(nextWeek(strDate));
}

// Current Date를 YYYY.MM.DD로 반환
function getToday() {
	var today = new Date();					
	var year = today.getFullYear();
	var month = today.getMonth()+1<10 ? "0"+(today.getMonth()+1) : today.getMonth()+1;
	var day = today.getDate()<10 ? "0"+today.getDate() : today.getDate();
	var todayF = year+"."+month+"."+day;
	
	return todayF;
}

// Current Hour 반환
function getHour() {
	var today = new Date();					
	return today.getHours();
}

// 해당 날짜의 요일 번호를 반환
function getWeekNumber(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);
	
	var	newDate = new Date(y, m - 1, d);
	var weekNumber = newDate.getDay();
	
	// 0 ~ 6 : 일 ~ 월
	return weekNumber;
}
/**
 * 해당 Date가 몇 번째에 속하는지 반환 ex) 2017년 11월 3일 -> 1
 * 
 * @param strDate :
 *            separator 문자를 포함한 yyyy.mm.dd 형식의 string
 * @returns {Number}
 */
function getNumberOfMonth(strDate){
	var arr = parseStrDateToArr(strDate);
    var dt = new Date(arr[0],arr[1]-1,arr[2]);
    return Math.ceil(dt.getDate() / 7);
}
var WEEK_NAME = { // 축약 요일명
		'KO': ['일', '월', '화', '수', '목', '금', '토'],
		'JA': ['日', '月', '火', '水', '木', '金', '土'],
		'EN': ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
}
var WEEK_FULL_NAME = {
		'KO': ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
		'JA': WEEK_NAME['JA'],
		'EN': WEEK_NAME['EN']
}
var MONTH_NAME = {
		'KO': ['월'],
		'JA': ['月'],
		'EN': ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
}
var DAY_NAME = {'KO': '일', 'JA': '日', 'EN': ''}
/**
 * locale 정보 반환
 * 
 * @returns {String}
 */
function getLocaleType(){
	var locale = MGW_RES.locale;
	
	if (startsWith(locale, 'KO')) {
		return 'KO';
	} else if (startsWith(MGW_RES.locale, 'JA') || startsWith(MGW_RES.locale, 'JP')) {
		return 'JA';
	} else {
		return 'EN';
	}
}
// 해당 날짜의 축약 요일명을 반환
function getWeekName(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);
	
	var	date = new Date(y, m - 1, d);
	
	return getWeekNameByWeekName(date.getDay());
}
function getWeekNameByWeekName(weekNumber){
	return WEEK_NAME[getLocaleType()][weekNumber];
}
// 해당 날짜의 전체 요일명 반환
function getWeekFullName(strDate) {
	return WEEK_FULL_NAME[getLocaleType()][getWeekNumber(strDate)];
	
}
// 해당 날짜의 전체 요일명 반환 by Number
function getWeekFullNameByNum(num) {
	return WEEK_FULL_NAME[getLocaleType()][num];
	
}
function getWeektoNum(weekNumber) {
	if (startsWith(MGW_RES.locale, 'JA') || startsWith(MGW_RES.locale, 'JP')) {
		return WEEK_NAME['JA'][weekNumber];
	}
	else {
		return WEEK_NAME['EN'][weekNumber];
	}
}
// Today 요일 반환
function getTodayWeek() {
	var date = new Date();
	return getWeekNameByWeekName(date.getDay());
}
/*
 * 오늘 요일 번호 반환
 */
function getTodayWeekNum() {
	var date = new Date();
	return date.getDay();
}
/**
 * MM.DD의 full text 변환 예) 11.03 => label:11월 3일 11月 3日, November 3
 * 
 * @param strDate :
 *            separator 문자를 포함한 yyyy.mm.dd 형식의 string
 * @returns {JSON} : {label:11월 3일, val:11.3}
 */
function getMMDDFullStrByStrDate(strDate){
	var mm = getMMFullStrByStrDate(strDate),
		dd = getDDFullStrByStrDate(strDate);
	
	return {label: mm.label+" "+dd.label, val: mm.val+"."+dd.val};
}
/**
 * MM의 full text 변환 예) 3 => 3월, 3月, March
 * 
 * @param num
 * @returns {String} : label:3월
 */
function getMMFullStr(num){
	var locale = getLocaleType(), num = parseInt(num);

	if (locale === 'EN'){
		return MONTH_NAME[locale][num];
	} else {
		return (num+1)+MONTH_NAME[locale][0];
	}
}
/**
 * MM의 full text 변환 예) 3 => 3월, 3月, March
 * 
 * @param strDate :
 *            separator 문자를 포함한 yyyy.mm.dd 형식의 string
 * @returns {JSON} : {label:3월, val:2}
 */
function getMMFullStrByStrDate(strDate){
	var arr = parseStrDateToArr(strDate),
		locale = getLocaleType();

	return {label:getMMFullStr(arr[1]-1), val:arr[1]};
}
/**
 * DD의 full text 변환 예) 3 => 3일, 3日
 * 
 * @param num
 * @returns {String}
 */
function getDDFullStr(num){
	return parseInt(num)+DAY_NAME[getLocaleType()];
}
/**
 * DD의 full text 변환 예) 3 => 3일, 3日
 * 
 * @param strDate :
 *            separator 문자를 포함한 yyyy.mm.dd 형식의 string
 * @returns {JSON} : {label:3일, val:3}
 */
function getDDFullStrByStrDate(strDate){
	var arr = parseStrDateToArr(strDate),
		locale = getLocaleType();
	return {label:parseInt(arr[2])+DAY_NAME[locale], val:parseInt(arr[2])};
}
function parseStrDateToArr(strDate){
	return strDate.split(/\D/);
}
/* 현재날짜에 대해 하루전날을 리턴한다. */
/* 패러미터와 리턴값은 모두 YYYY.MM.DD 형식이다. */
function lastDay(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y, m - 1, d - 1);
	return dateToString(newDate);
}

/* 현재날짜에 대해 하루다음날을 리턴한다. */
/* 패러미터와 리턴값은 모두 YYYY.MM.DD 형식이다. */
function nextDay(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y, m - 1, d + 1);
	return dateToString(newDate);
}

/* 현재날짜에 대해 지난주의 동일한 날을 리턴한다. */
/* 패러미터와 리턴값은 모두 YYYY.MM.DD 형식이다. */
function lastWeek(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y, m - 1, d - 7);
	return dateToString(newDate);
}

/* 현재날짜에 대해 다음주의 날을 리턴한다. */
/* 패러미터와 리턴값은 모두 YYYY.MM.DD 형식이다. */
function nextWeek(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y, m - 1, d + 7);
	return dateToString(newDate);
}

/* 현재날짜에 대해 지난달의 동일한 날을 리턴한다. */
/* 패러미터와 리턴값은 모두 YYYY.MM.DD 형식이다. */
function lastMonth(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y, m - 2, d);
	return dateToString(newDate);
}

/* 현재날짜에 대해 다음달의 동일한 날을 리턴한다. */
/* 패러미터와 리턴값은 모두 YYYY.MM.DD 형식이다. */
function nextMonth(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y, m , d);
	return dateToString(newDate);
}

/* 현재날짜에 대해 지난해의 동일한 날을 리턴한다. */
/* 패러미터와 리턴값은 모두 YYYY.MM.DD 형식이다. */
function lastYear(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y - 1, m - 1, d);
	return dateToString(newDate);
}

/* 현재날짜에 대해 다음해의 동일한 날을 리턴한다. */
/* 패러미터와 리턴값은 모두 YYYY.MM.DD 형식이다. */
function nextYear(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);
	var	newDate = new Date(y + 1, m - 1, d);
	return dateToString(newDate);
}

/* YYYY.MM.DD 형식의 스트링에서 int 형식의 년을 리턴한다. */
function getYear(date) {
	return parseInt(date.substr(0, 4));
}

/* YYYY.MM.DD 형식의 스트링에서 int 형식의 월을 리턴한다. */
function getMonth(date) {
	var monthString	= date.substr(5, 2);
	if ('0' == monthString.charAt(0)) {
		monthString = monthString.slice(1);
	}

	return parseInt(monthString);
}

/* YYYY.MM.DD 형식의 스트링에서 int 형식의 날을 리턴한다. */
function getDate(date) {
	var dateString	= date.substr(8, 2);
	if ('0' == dateString.charAt(0)) {
		dateString = dateString.slice(1);
	}

	return parseInt(dateString);
}

/* YYYY.MM.DD HH:MM 형식의 스트링에서 int 형식의 시간을 리턴한다. */
function getHours(date) {
	// YYYY.MM.DD HH:MM 과 YYYY.MM.DD H:MM 두가지 유형이 올수 있음.
	if(date.length == 15) {
		return parseInt(date.substr(11, 1));
	} else if(date.length == 16) {
		return parseInt(date.substr(11, 2));
	} else {
		return 0;
	}
}

/* YYYY.MM.DD HH:MM 형식의 스트링에서 int 형식의 분을 리턴한다. */
function getMinutes(date) {
	// 형식(YYYY.MM.DD H:MM) 루틴 처리
	if(date.length == 15)
		return date.substr(13, 2);
	
	if(date.length == 16)
		return date.substr(14, 2);
}

/* YYYY.MM.DD HH:MM 형식의 String에서 YYYY.MM.DD 형식의 String 리턴한다. */
function getDateString(date) {
	var dateStr = date.split(" ");
	
	return dateStr[0];
}

/* YYYY.MM.DD HH:MM 형식의 스트링에서 String 형식의 시간을 리턴한다. */
function getHoursString(date) {
	var dateString	= date.substr(11, 2);
	
	if (dateString.charAt(1) == ":") {
		dateString = "0" + dateString.charAt(0);
	}

	return dateString;
}

/* YYYY.MM.DD HH:MM 형식의 스트링에서 String 형식의 (0 or 30)분으로 리턴한다. */
function getMinutesString(date, isMinUnit) {
	var dateStr;
	
	// 형식(YYYY.MM.DD H:MM) 루틴 처리
	if(date.length == 15) 
		dateStr = date.substr(13, 2);
	
	if(date.length == 16)
		dateStr = date.substr(14, 2);

	if(isMinUnit == true) {
		return dateStr;
	} else {
		return dateStr >29 ? "30":"00";
	}
}

/* Date 객체로부터 YYYY.MM.DD 형식의 스트링을 리턴한다. */
function dateToString(date) {
	var dateString = "";
	dateString = dateString + (date.getFullYear());
	dateString = dateString + ".";

	var m = (date.getMonth() + 1);
	var mStr = m<10 ? "0"+m : ""+m;
	dateString = dateString + mStr;
	dateString = dateString + ".";

	var d = date.getDate();
	var dStr = d<10?"0"+d:""+d;
	dateString = dateString + dStr;

	return dateString;
}

/* YYYY.MM.DD 형식의 스트링으로부터 Date 객체를 리턴한다. */
function stringToDate(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y, m - 1, d);
	return newDate;
}

/* YYYY.MM.DD HH:MM 형식의 스트링으로부터 Date 객체를 리턴한다. */
function stringToDateTime(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);
	var h = getHours(date);
	var mi = getMinutes(date);

	var	newDate = new Date(y, m - 1, d, h, mi);
	return newDate;
}

/* YYYY.MM.DD 형식의 스트링으로부터 그 주의 첫날에 해당하는 Date를 리턴한다. */
function getFirstDateOfWeek(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y, m - 1, d);
	newDate.setDate(d - newDate.getDay());

	return dateToString (newDate);
}

/* YYYY.MM.DD 형식의 스트링으로부터 그 주의 마지막 날에 해당하는 Date 를 리턴한다. */
function getLastDateOfWeek(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	var	newDate = new Date(y, m - 1, d);
	newDate.setDate(d - newDate.getDay());
	
	newDate.setDate(newDate.getDate() + 6);
	
	return dateToString(newDate);
}

/* YYYY.MM.DD 형식의 스트링으로부터 그 달의 첫날에 해당하는 Date 객체를 리턴한다. */
function getFirstDateOfMonth(date) {
	var y = getYear(date);
	var m = getMonth(date);

	var	newDate = new Date(y, m - 1, 1);
	newDate = dateToString(newDate);
	return newDate;
}

/* YYYY.MM.DD 형식의 스트링으로부터 그 주의 마지막날에 해당하는 Date 객체를 리턴한다. */
function getLastDateOfMonth(date) {
	var y = getYear(date);
	var m = getMonth(date);
	var d = 28;
	for (i=29; i<33; i++) {
		if (new Date(y, m-1, i).getDate() != i) {
			d = i-1;
			break;
		}
	}
	var newDate = new Date(y, m - 1, d);
	newDate = dateToString(newDate);
	return newDate;
}
/*
 * 입력 date 달의 마지막 날짜 yyyy.MM.dd 형식의 string 반환 @param strDate : separator 문자를 포함한
 * yyyy.mm.dd 형식의 string
 */
function getLastDateStr(strDate){
	var arr = parseStrDateToArr(strDate);
    var lastDate = new Date(arr[0],arr[1], 0);
	return dateToString(lastDate);
}
/*
 * 주간 표시 할때 뒤의 자릿수의 간편화를 위해 데이터가 중복되는 부분까지는 제거 시키고 endDate의 날짜를 표시 startDate :
 * 시작일, endDate : 종료일, type : date->날짜만 필터, dateTime->날짜+시+분
 */
function getFilterDate(startDate, endDate, type, dateMode) {
	var sY = getYear(startDate);
	var sM = getMonth(startDate);
	var sD = getDate(startDate);
	
	var eY = getYear(endDate);
	var eM = getMonth(endDate);
	var eD = getDate(endDate);
	
	var newDate = "";	
	if(dateMode == "startDate") {
		if(type=="date") {
			var isCheck = false;
			
			if(sY != eY && !isCheck) {
				newDate += "<span>";
				isCheck = true;
			}
			newDate += "<span class=\"size\">";
			newDate += sY;
			newDate += "</span>";
			newDate += ".";
			
			if((sY != eY || sM != eM) && !isCheck) {
				newDate += "<span>";
				isCheck = true;				
			}
			
			newDate += sM;
			newDate += ".";
			
			if((sY != eY || sM != eM || sD != eD) && !isCheck) {
				newDate += "<span>";
				isCheck = true;				
			}
			
			newDate += sD;
			newDate += "</span>";
		}
		else if(type=="dateTime") {
			var isCheck = false;
			
			if(sY != eY && !isCheck) {
				newDate += "<span>";
				isCheck = true;
			}
			
			newDate += sY;
			newDate += ".";
			
			if((sY != eY || sM != eM) && !isCheck) {
				newDate += "<span>";
				isCheck = true;				
			}
			
			newDate += sM;
			newDate += ".";
			
			if((sY != eY || sM != eM || sD != eD) && !isCheck) {
				newDate += "<span>";
				isCheck = true;				
			}
			
			newDate += sD;
			newDate += " ";
			
			if(!isCheck) {
				newDate += "<span>";
				isCheck = true;
			}
			
			newDate += getHours(startDate);
			newDate += ":";
			newDate += getMinutes(startDate);
			newDate += "</span>";
		}
	}
	else if(dateMode == "endDate") {
		if(type=="date") {
			if(sY != eY) {
				newDate += "<span class=\"size\">";
				newDate += eY;
				newDate += "</span class=\"size\">";
				newDate += ".";
			}
			if(sY != eY || sM != eM) {
				newDate += eM;
				newDate += ".";
			}			
			newDate += getDate(endDate);
		}else if(type=="dateTime") {
			if(sY != eY) {
				newDate += eY;
				newDate += ".";
			}
			if(sY != eY || sM != eM) {
				newDate += eM;
				newDate += ".";
			}
			if(sY != eY || sM != eM || sD != eD) {
				newDate += eD;
				newDate += " ";
			}
			newDate += getHours(endDate);
			newDate += ":";
			newDate += getMinutes(endDate);
		}
	}
	return newDate;
}
/**
 * dateString 형식으로 부터 day +/-
 * 
 * @param dateStr
 * @param num
 * @returns {String}
 */
function getDateStrAddDay(dateStr, num){
	var dateArr = parseStrDateToArr(dateStr);
	var y = parseInt(dateArr[0]), m = parseInt(dateArr[1])-1, d = parseInt(dateArr[2]);
	return getDateStrByYMD(y, m, d+num);
}
/**
 * dateString 형식으로 부터 month +/-
 * 
 * @param dateStr
 * @param num
 * @returns {String}
 */
function getDateStrAddMonth(dateStr, num){
	var dateArr = parseStrDateToArr(dateStr);
	var y = parseInt(dateArr[0]), m = parseInt(dateArr[1])-1, d = parseInt(dateArr[2]);
	return getDateStrByYMD(y, m+num, d);
}
/**
 * dateString 형식으로 부터 year +/-
 * 
 * @param dateStr
 * @param num
 * @returns {String}
 */
function getDateStrAddYear(dateStr, num){
	var dateArr = parseStrDateToArr(dateStr);
	var y = parseInt(dateArr[0]), m = parseInt(dateArr[1])-1, d = parseInt(dateArr[2]);
	return getDateStrByYMD(y+num, m, d);
}
/**
 * 현재 시간으로 부터 hour +/-
 * 
 * @param date
 * @param num
 */
function getHoursAddHour(num){
	var _now = new Date(), _dt = new Date();
	_dt.setHours(_now.getHours() + num);

	return _dt.getHours();
}
/**
 * int 형의 y,m,d를 받아 DateString 으로 반환
 * 
 * @param y
 * @param m
 * @param d
 * @returns {String}
 */
function getDateStrByYMD(y, m, d){
	var newDate = new Date(y, m, d);
	return dateToString(newDate);
}

/*
 * 해당 날짜로부터 더해진 날짜를 구하여 String 반환 date : 해당 날짜, num : 더해질 날짜
 */
function getDatetoDay(date, num) {
	var newDate;
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	for(i=0; i<num; i++) {
		d += 1;
		
		if(!checkDate(y, m-1, d)) {
			m += 1;
			d = 1;
			
			if(!checkDate(y, m-1, d)) {
				y += 1;
				m = 1;
				d = 1;
			}
		}
	}
	newDate = new Date(y, m-1, d);
	newDate = dateToString(newDate);
	
	return newDate;
}

/*
 * 해당 날짜로부터 더해진 날짜를 구하여 String 반환 date : 해당 날짜, num : 더해질 날짜
 */
function getDatetoDay2(date, num) {
	var newDate;
	var y = getYear(date);
	var m = getMonth(date);
	var d = getDate(date);

	for(i=0; i<num; i++) {
		d += 1;
		
		if(!checkDate(y, m-1, d)) {
			m += 1;
			d = 1;
			
			if(!checkDate(y, m-1, d)) {
				y += 1;
				m = 1;
				d = 1;
			}
		}
	}
	newDate = new Date(y, m-1, d);
	
	return newDate;
}

// Year, Month, Date Range 기간 만큼 더해진 날짜 반환
function getPeriod(py, pm, pd) {
	var date = getToday();
	var today = new Date(date.substring(0,4), date.substring(5,7), date.substring(8));
	
	var y = today.getFullYear();
	var m = today.getMonth();
	var d = today.getDate();

	var	newDate = new Date(y - py, m - 1 - pm, d - pd);
	
	var year = newDate.getFullYear();
	var month = newDate.getMonth()+1<10 ? "0" + (newDate.getMonth()+1) : newDate.getMonth() + 1;
	var day = newDate.getDate()<10 ? "0" + newDate.getDate() : newDate.getDate();
	var newDateF = year + "." + month + "." + day;
	
	return	newDateF;
}

// 날짜 포맷 변경 (yyyyMMdd hhmmss -> yyyy.MM.dd hh:mm:ss)
function convertDateFormat(datetime, isList) {
	var dateArr;
	
	try {
		dateArr = datetime.split(" ");
		var year = dateArr[0].substring(0, 4);
		var month = dateArr[0].substring(4, 6);
		var day = dateArr[0].substring(6, 8);
		
		result = year + "." + month + "." + day;
		
		if (isList)
			return result;
		
		if (dateArr.length == 2) {
			var hour = dateArr[1].substring(0, 2);
			var min = dateArr[1].substring(2, 4);
			var sec = dateArr[1].substring(4, 6);
			
			result += " " + hour + ":" + min + ":" + sec;
		}
	} catch(e) {
		return datetime;
	}
	
	return result;
}

/**
 * 날짜 / 시간 가져오기 날짜 - yyyy.MM.dd hh:mm -> yyyy.MM.dd 시간 - yyyy.MM.dd hh:mm -> hh
 * param type ["date" | "time"]
 */
function getAbsenceDate(datetime, type) {
	var dateArr = datetime.split(" ");
	var result = "";
	try {
		if (type == "time") {
			var timeArr = dateArr[1].split(":");
			result = timeArr[0];
		}
		else {
			result = dateArr[0];
		}
	} catch(e) {
		return datetime;
	}
	
	return result;
}

function getMonthDay(newDate) {
	var month = newDate.getMonth()+1<10 ? "0" + (newDate.getMonth()+1) : newDate.getMonth() + 1;
	var day = newDate.getDate()<10 ? "0" + newDate.getDate() : newDate.getDate();

	return month + "." + day;
}

/**
 * 부재설정에서 날짜가 현재 시간보다 지났는지 확인 datetime : yyyy.MM.dd hh:mm return 시간이 지났을 경우
 * false, 시간이 안 지났을 경우 true
 */
function checkAbsenceDateTimeNow(datetime){
	var year = getYear(datetime);
	var month = getMonth(datetime);
	var day = getDate(datetime);
	var hour = getHours(datetime);
	var min = getMinutes(datetime);
	
	var date = new Date(year, month - 1, day, hour, min);	
	var curnow = new Date();
	if(date < curnow)
		return true;
	else
		return false;
}var APP_CONTROLLER = {	
	mainMenuBtn: "MENU",
	backBtn: "BACK",
	success : false,

	setAppControls:function(pageCode){
		APP_CONTROLLER.success = false;
		APP_CONTROLLER.setNavibar(pageCode);
		
		APP_CONTROLLER.hideTabAndToolBar(pageCode);
		
		APP_CONTROLLER.setToolbar(pageCode);
		APP_CONTROLLER.setTabBar(pageCode);
	},
	setNavibar:function(pageCode){
		var data = NAVIBAR_DEF[APP_INFO_DEVICETYPE][pageCode];
		if(data == undefined) return;
		GWPlugin.setNavibarTitle(data.title);
		if(data.left == undefined){
			data.left = [0, [], []];
		}		
		if(data.right == undefined){
			data.right = [0, [], []];
		}
		GWPlugin.setNavibarLeftButton(data.left);
		GWPlugin.setNavibarRightButton(data.right);
		
	},
	setToolbar:function(pageCode){
		var data = TOOLBAR_DEF[pageCode];
		if(data == undefined) {
			return;
		};
		
		if (data[0] == "0") {
			return;
		}
				
		GWPlugin.setToolBarButton(data);
	},
	setTabBar:function(pageCode){
		var data = TABBAR_DEF[pageCode];
		if(data == undefined) {
			return;
		};
		
		if (data[0] == "0") {
			return;
		}
				
		GWPlugin.setTabBarButton(data);
	},
	hideTabAndToolBar: function(pageCode) {
		var toolbar_data = TOOLBAR_DEF[pageCode];
		if(toolbar_data == undefined) {
			GWPlugin.hideToolBar();
		} else if (toolbar_data[0] == "0") {
			GWPlugin.hideToolBar();
		}

		var tabbar_data = TABBAR_DEF[pageCode];
		if(tabbar_data == undefined) {
			GWPlugin.hideTabBar();
		}
		else if (tabbar_data[0] == "0") {
			GWPlugin.hideTabBar();
		}
	}
	
};

var NAVIBAR_DEF = {
	phone: {
		/*
		 * example) code_name:{ title: "HeaderTitle", left: [buttonNumber,
		 * ['buttonTitle1', 'buttonTitle2', ...]', ['functionName1',
		 * 'functionName2', ....]], right: [buttonNumber, ['buttonTitle1',
		 * 'buttonTitle2', ...]', ['functionName1', 'functionName2', ....]], }
		 */		
		mail_details:{
			title:MGW_RES.get("gw_mail_mailread_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:goList();', 'javascript:goMenu();']],
		},	
		mail_write:{
			title:MGW_RES.get("gw_mail_mailwrite_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_sendMail();']], 
			right:[1, [MGW_RES.get("gw_mail_send_label")], ["javascript:sendMail();"]],
		},	
		mail_trackerlist:{
			title:MGW_RES.get("gw_mail_confirm_receive_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		board_commentlist:{
			title:MGW_RES.get("gw_board_comment_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		board_details:{
			title:MGW_RES.get("gw_board_read_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:goList();', 'javascript:goMenu();']],
		},
		board_write: {
			title:MGW_RES.get("gw_board_write_title"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_writeBoard();']], 
			right:[1, [MGW_RES.get("gw_board_wirte_label")], ["javascript:writeBoard();"]],
		},
		contact_details:{
			title:MGW_RES.get("gw_org_userinfo_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:goList();', 'javascript:goMenu();']],
		},
		org_tree: {
			title:MGW_RES.get("gw_org_tree_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_common_search_label")], ["javascript:showSearch(false, 'org');"]],
		},
		org_user: {
			title:MGW_RES.get("gw_org_userinfo_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], 
			      ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		org_select: {
			title:MGW_RES.get("gw_org_select_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_orgSelect();']],
			right:[2, [MGW_RES.get("gw_common_search_label"), MGW_RES.get("gw_common_ok_label")], 
			       ["javascript:showSearchOrgSelect();", "javascript:confirmTargetRecv();"]],
		},
		sch_equipdetail:{
			title:MGW_RES.get("gw_schedule_equipment_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch_equiplist:{
			title:MGW_RES.get("gw_schedule_equip_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[2, [MGW_RES.get("gw_schedule_equipment_label"), MGW_RES.get("gw_common_add_label")], 
			       ["javascript:showSelectEquipment();", "javascript:showSchAddNaviBar();"]],
		},
		sch_equiplistdetail:{
			title:MGW_RES.get("gw_schedule_equip_list_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSchAddNaviBar();"]],
		},
		sch_detaillist:{
			title:"",
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch_schadd:{
			title:MGW_RES.get("gw_schedule_add_label"), 
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSch();']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:checkReserveEquip();"]],
		},
		sch_schdetail:{
			title:MGW_RES.get("gw_schedule_schedule_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch_schlist:{
			title:MGW_RES.get("gw_schedule_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSchAddNaviBar();"]],
		},
		sch_schsearch:{
			title:MGW_RES.get("gw_schedule_search_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_search_label")], ["javascript:searchSch();"]],
		},
		sch_selectequipment:{
			title:MGW_RES.get("gw_schedule_select_equipment"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancelSelectEquipment();']],
			right:[1, [MGW_RES.get("gw_schedule_status_finish_label")], ["javascript:completeSelectEquipment();"]],
		},
		sch_todoadd:{
			title:MGW_RES.get("gw_schedule_todo_add_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSch(\"todo\");']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:addTodo();"]],
		},
		sch_tododetail:{
			title:MGW_RES.get("gw_schedule_todo_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch_todolist:{
			title:MGW_RES.get("gw_schedule_todo_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showTodoAddNaviBar();"]],
		},
		sch6_schlist: {
			title:MGW_RES.get("gw_schedule_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSch6Add();"]],
		},
		sch6_schdetail:{
			title:MGW_RES.get("gw_schedule_schedule_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch6_schadd:{
			title:MGW_RES.get("gw_schedule_schadd_label"), 
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancelSch6();']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:addSch6();"]],
		},
		sch2_equipdetail:{
			title:MGW_RES.get("gw_schedule_equipment_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch2_equiplist:{
			title:MGW_RES.get("gw_schedule_equip_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[2, [MGW_RES.get("gw_schedule_equipment_label"), MGW_RES.get("gw_common_add_label")], 
			       ["javascript:showSelectEquipment2();", "javascript:showSch2AddNaviBar();"]],
		},
		sch2_equiplistdetail:{
			title:MGW_RES.get("gw_schedule_equip_list_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSch2AddNaviBar();"]],
		},
		sch2_detaillist:{
			title:"",
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch2_schadd:{
			title:MGW_RES.get("gw_schedule_add_label"), 
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSch2();']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:checkReserveEquip2();"]],
		},
		sch2_schdetail:{
			title:MGW_RES.get("gw_schedule_schedule_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch2_schlist:{
			title:MGW_RES.get("gw_schedule_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSch2AddNaviBar();"]],
		},
		sch2_schsearch:{
			title:MGW_RES.get("gw_schedule2_search_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_search_label")], ["javascript:searchSch2();"]],
		},
		sch2_selectcalendar:{
			title:MGW_RES.get("gw_schedule_select_calendar"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancelSelectCalendar2();']],
			right:[1, [MGW_RES.get("gw_schedule_status_finish_label")], ["javascript:completeSelectCalendar2();"]],
		},
		sch2_selectequipment:{
			title:MGW_RES.get("gw_schedule_select_equipment"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancelSelectEquipment2();']],
			right:[1, [MGW_RES.get("gw_schedule_status_finish_label")], ["javascript:completeSelectEquipment2();"]],
		},
		sch2_todoadd:{
			title:MGW_RES.get("gw_schedule_todo_add_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSch2(\"todo\");']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:addTodo2();"]],
		},
		sch2_tododetail:{
			title:MGW_RES.get("gw_schedule_todo_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch2_todolist:{
			title:MGW_RES.get("gw_schedule_todo_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showTodo2AddNaviBar();"]],
		},
		search_condition: {
			title:MGW_RES.get("gw_common_search_label"),
			left:[1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']],
		},
		search_result: {
			title:MGW_RES.get("gw_common_search_result_label"),
			left:[1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']],
		},
		settings_absence: {
			title:MGW_RES.get("gw_settings_absence_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_common_ok_label")], ["javascript:setAbsence();"]],
		},
		settings2_absencelist: {
			title:MGW_RES.get("gw_settings_absence_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_settings_absence_add_label")], ["javascript:showSettings2AddNaviBar();"]],
		},
		settings2_absenceadd: {
			title:MGW_RES.get("gw_settings_absence_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSettings2();']], 
// right:[1, [MGW_RES.get("gw_common_save_label")],
// ["javascript:setAbsence2();"]],
		},
		settings_password: {
			title:MGW_RES.get("gw_settings_password_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_common_ok_label")], ["javascript:setPassword();"]],
		},
		settings_userinfo: {
			title:MGW_RES.get("gw_settings_userinfo_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:setUserInfo();"]],
		},
		sign_commentlist:{
			title:MGW_RES.get("gw_sign_show_comment_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_apprflow:{
			title:MGW_RES.get("gw_sign_approve_flow_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_summarydoc:{
			title:MGW_RES.get("gw_sign_show_summary_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_details:{
			title:MGW_RES.get("gw_sign_details_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:goList();', 'javascript:goMenu();']],
		},
		sign_linkdoclist_0:{
			title:MGW_RES.get("gw_sign_show_linkdoc_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_linkdoclist_1:{
			title:MGW_RES.get("gw_sign_show_linkdoc_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_linkdoclist_2:{
			title:MGW_RES.get("gw_sign_show_linkdoc_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_linkdoclist_3:{
			title:MGW_RES.get("gw_sign_show_linkdoc_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_linkdoc_1:{
			title:MGW_RES.get("gw_sign_details_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBackLinkdocList(1);', 'javascript:goMenu();']],
		},
		sign_linkdoc_2:{
			title:MGW_RES.get("gw_sign_details_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBackLinkdocList(2);', 'javascript:goMenu();']],
		},
		sign_linkdoc_3:{
			title:MGW_RES.get("gw_sign_details_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBackLinkdocList(3);', 'javascript:goMenu();']],
		},
		sign_linkdoc_4:{
			title:MGW_RES.get("gw_sign_details_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBackLinkdocList(4);', 'javascript:goMenu();']],
		},
		// 전체메뉴 19.11.12 tkofs
		menu_show_all: {
			title:MGW_RES.get("menu_show_all"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBackLinkdocList(4);', 'javascript:goMenu();']],
		}
		
	},
	pad: {
		/*
		 * example) code_name:{ title: "HeaderTitle", left: ['Left_label',
		 * 'Left_type(1|2|3|...)', 'Left_show(1=show|2=hide)',
		 * 'Left_onclickFunctionString'], right:['Right_label', 'Right_type',
		 * 'Right_show', 'Right_onclickFunctionString'], }
		 */		
		mail_details:{
			title:MGW_RES.get("gw_mail_mailread_label"),
		},	
		mail_write:{
			title:MGW_RES.get("gw_mail_mailwrite_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_sendMail();']], 
			right:[1, [MGW_RES.get("gw_mail_send_label")], ["javascript:sendMail();"]],
		},	
		mail_trackerlist:{
			title:MGW_RES.get("gw_mail_confirm_receive_label"),
			left:[1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']],
		},
		board_commentlist:{
			title:MGW_RES.get("gw_board_comment_label"),
			left:[1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']],
		},
		board_details:{
			title:MGW_RES.get("gw_board_read_label"),
		},
		board_write: {
			title:MGW_RES.get("gw_board_write_title"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_writeBoard();']], 
			right:[1, [MGW_RES.get("gw_board_wirte_label")], ["javascript:writeBoard();"]],
		},
		contact_details:{
			title:MGW_RES.get("gw_org_userinfo_label"),
		},
		org_tree: {
			title:MGW_RES.get("gw_org_tree_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_common_search_label")], ["javascript:showSearch(false, 'org');"]],
		},
		org_user: {
			title:MGW_RES.get("gw_org_userinfo_label"),
			left:[1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']],
		},
		org_select: {
			title:MGW_RES.get("gw_org_select_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_orgSelect();']],
			right:[2, [MGW_RES.get("gw_common_search_label"), MGW_RES.get("gw_common_ok_label")], 
			       ["javascript:showSearchOrgSelect();", "javascript:confirmTargetRecv();"]],
		},
		sch_equipdetail:{
			title:MGW_RES.get("gw_schedule_equipment_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch_equiplist:{
			title:MGW_RES.get("gw_schedule_equip_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[2, [MGW_RES.get("gw_schedule_equipment_label"), MGW_RES.get("gw_common_add_label")], 
			       ["javascript:showSelectEquipment();", "javascript:showSchAddNaviBar();"]],
		},
		sch_equiplistdetail:{
			title:MGW_RES.get("gw_schedule_equip_list_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSchAddNaviBar();"]],
		},
		sch_detaillist:{
			title:"",
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch_schadd:{
			title:MGW_RES.get("gw_schedule_add_label"), 
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSch();']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:checkReserveEquip();"]],
		},
		sch_schdetail:{
			title:MGW_RES.get("gw_schedule_schedule_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch_schlist:{
			title:MGW_RES.get("gw_schedule_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSchAddNaviBar();"]],
		},
		sch_schsearch:{
			title:MGW_RES.get("gw_schedule_search_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_search_label")], ["javascript:searchSch();"]],
		},
		sch_selectequipment:{
			title:MGW_RES.get("gw_schedule_select_equipment"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancelSelectEquipment();']],
			right:[1, [MGW_RES.get("gw_schedule_status_finish_label")], ["javascript:completeSelectEquipment();"]],
		},
		sch_todoadd:{
			title:MGW_RES.get("gw_schedule_todo_add_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSch(\"todo\");']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:addTodo();"]],
		},
		sch_tododetail:{
			title:MGW_RES.get("gw_schedule_todo_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch_todolist:{
			title:MGW_RES.get("gw_schedule_todo_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showTodoAddNaviBar();"]],
		},
		sch6_schlist: {
			title:MGW_RES.get("gw_schedule_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSch6Add();"]],
		},
		sch6_schdetail:{
			title:MGW_RES.get("gw_schedule_schedule_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch6_schadd:{
			title:MGW_RES.get("gw_schedule_schadd_label"), 
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancelSch6();']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:addSch6();"]],
		},
		sch2_equipdetail:{
			title:MGW_RES.get("gw_schedule_equipment_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch2_equiplist:{
			title:MGW_RES.get("gw_schedule_equip_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[2, [MGW_RES.get("gw_schedule_equipment_label"), MGW_RES.get("gw_common_add_label")], 
			       ["javascript:showSelectEquipment2();", "javascript:showSch2AddNaviBar();"]],
		},
		sch2_equiplistdetail:{
			title:MGW_RES.get("gw_schedule_equip_list_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSch2AddNaviBar();"]],
		},
		sch2_detaillist:{
			title:"",
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch2_schadd:{
			title:MGW_RES.get("gw_schedule_add_label"), 
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSch2();']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:checkReserveEquip2();"]],
		},
		sch2_schdetail:{
			title:MGW_RES.get("gw_schedule_schedule_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch2_schlist:{
			title:MGW_RES.get("gw_schedule_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showSch2AddNaviBar();"]],
		},
		sch2_schsearch:{
			title:MGW_RES.get("gw_schedule2_search_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_search_label")], ["javascript:searchSch2();"]],
		},
		sch2_selectcalendar:{
			title:MGW_RES.get("gw_schedule_select_calendar"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancelSelectCalendar2();']],
			right:[1, [MGW_RES.get("gw_schedule_status_finish_label")], ["javascript:completeSelectCalendar2();"]],
		},
		sch2_selectequipment:{
			title:MGW_RES.get("gw_schedule_select_equipment"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancelSelectEquipment2();']],
			right:[1, [MGW_RES.get("gw_schedule_status_finish_label")], ["javascript:completeSelectEquipment2();"]],
		},
		sch2_todoadd:{
			title:MGW_RES.get("gw_schedule_todo_add_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSch2(\"todo\");']], 
			right:[1, [MGW_RES.get("gw_common_save_label")], ["javascript:addTodo2();"]],
		},
		sch2_tododetail:{
			title:MGW_RES.get("gw_schedule_todo_view_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sch2_todolist:{
			title:MGW_RES.get("gw_schedule_todo_list_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']],
			right:[1, [MGW_RES.get("gw_common_add_label")], ["javascript:showTodo2AddNaviBar();"]],
		},
		search_condition: {
			title:MGW_RES.get("gw_common_search_label"),
			left:[1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']],
		},
		search_result: {
			title:MGW_RES.get("gw_common_search_result_label"),
			left:[1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']],
		},
		settings_absence: {
			title:MGW_RES.get("gw_settings_absence_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_common_ok_label")], ["javascript:setAbsence();"]],
		},
		settings2_absencelist: {
			title:MGW_RES.get("gw_settings_absence_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_settings_absence_add_label")], ["javascript:showSettings2AddNaviBar();"]],
		},
		settings2_absenceadd: {
			title:MGW_RES.get("gw_settings_absence_label"),
			left:[1, [MGW_RES.get("gw_common_cancel_label")], ['javascript:cancel_addSettings2();']],
// right:[1, [MGW_RES.get("gw_common_save_label")],
// ["javascript:setAbsence2();"]],
		},
		settings_password: {
			title:MGW_RES.get("gw_settings_password_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_common_ok_label")], ["javascript:setPassword();"]],
		},
		settings_userinfo: {
			title:MGW_RES.get("gw_settings_userinfo_label"),
			left:[1, [APP_CONTROLLER.mainMenuBtn], ['javascript:goMenu();']], 
			right:[1, [MGW_RES.get("gw_common_ok_label")], ["javascript:setUserInfo();"]],
		},
		sign_commentlist:{
			title:MGW_RES.get("gw_sign_show_comment_label"),
			left:[1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']],
		},
		sign_apprflow:{
			title:MGW_RES.get("gw_sign_approve_flow_label"),
			left:[1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']],
		},
		sign_summarydoc:{
			title:MGW_RES.get("gw_sign_show_summary_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_details:{
			title:MGW_RES.get("gw_sign_details_label"),
		},
		sign_linkdoclist_0:{
			title:MGW_RES.get("gw_sign_show_linkdoc_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_linkdoclist_1:{
			title:MGW_RES.get("gw_sign_show_linkdoc_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_linkdoclist_2:{
			title:MGW_RES.get("gw_sign_show_linkdoc_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_linkdoclist_3:{
			title:MGW_RES.get("gw_sign_show_linkdoc_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']],
		},
		sign_linkdoc_1:{
			title:MGW_RES.get("gw_sign_details_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBackLinkdocList(1);', 'javascript:goMenu();']],
		},
		sign_linkdoc_2:{
			title:MGW_RES.get("gw_sign_details_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBackLinkdocList(2);', 'javascript:goMenu();']],
		},
		sign_linkdoc_3:{
			title:MGW_RES.get("gw_sign_details_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBackLinkdocList(3);', 'javascript:goMenu();']],
		},
		sign_linkdoc_4:{
			title:MGW_RES.get("gw_sign_details_label"),
			left:[2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:PAGE_CONTROLLER.goBackLinkdocList(4);', 'javascript:goMenu();']],
		}
	}
	
};

var TOOLBAR_DEF ={
		/*
		 * example) code_name:[Button_Number, ['Button1_Title', 'Button2_title',
		 * ...], ['Button1_function', 'Button2_function',...]]
		 * 
		 * [3, ['Btn1', 'Btn2', 'Btn3'], ['javascript:alert(1);',
		 * 'javascript:alert(2);', 'javascript:alert(3);']]
		 * 
		 */	
	mail_details:[4, 
	             [MGW_RES.get("gw_common_delete_label"), MGW_RES.get("gw_mail_fly_label"), 
	              MGW_RES.get("gw_mail_reply_label"), MGW_RES.get("gw_mail_replyall_label")], 
	             ["btn_tool_delete.png", "btn_tool_delivery.png", "btn_tool_reply.png", "btn_tool_replyall.png"],
	             ["javascript:deleteMail();", "javascript:forwardMail();", "javascript:replyMail();", "javascript:replyAllMail();"]
	],
	board_details:[0, [], [], []
	],
	board_commentlist:[0, [], [], []
	],
	sch_schdetail:[0, [], [], []
	],
	sch_tododetail:[0, [], [], []
	],
	sch6_schdetail:[0, [], [], []
	],
	sch2_schdetail:[0, [], [], []
	],
	sch2_tododetail:[0, [], [], []
	],
	settings2_absencelist:[0, [], [], []
	],
	settings2_absenceadd:[0, [], [], []
	],
	sign_details:[0, [], [], []
	],
	sign_linkdoc_1:[0, [], [], []
	],
	sign_linkdoc_2:[0, [], [], []
	],
	sign_linkdoc_3:[0, [], [], []
	],
	sign_linkdoc_4:[0, [], [], []
	]
};

var TABBAR_DEF = {
	org_select: [4, 
	            [MGW_RES.get("gw_org_tree_label"), MGW_RES.get("gw_contact_personal_label"), 
	             MGW_RES.get("gw_contact_dept_label"), MGW_RES.get("gw_common_group_label")], 
	            ["ico_org.png", "ico_contact_personal.png", "ico_contact_dept.png", "ico_group.png"], 
	            ["javascript:showOrgSelectTab('org')", "javascript:showOrgSelectTab('personal')",
	             "javascript:showOrgSelectTab('department')", "javascript:showOrgSelectTab('group')"]],
	sch_schsearch: [2,
	               [MGW_RES.get("gw_schedule_label"), MGW_RES.get("gw_schedule_todo_label")],
	               ["ico_schedule.png", "ico_todo.png"],
	               ["javascript:selectSearchTab('sch')", "javascript:selectSearchTab('todo')"]],
	sch_schlist: [3,
	               [MGW_RES.get("gw_schedule_daily_label"), MGW_RES.get("gw_schedule_weekly_label"), MGW_RES.get("gw_schedule_monthly_label")],
	               ["ico_daily.png", "ico_weekly.png", "ico_monthly.png"],
	               ["javascript:changeDateMode('DAILY')", "javascript:changeDateMode('WEEKLY')", "javascript:changeDateMode('MONTHLY')"]],  
	sch_equiplist: [3,
	               [MGW_RES.get("gw_schedule_daily_label"), MGW_RES.get("gw_schedule_weekly_label"), MGW_RES.get("gw_schedule_monthly_label")],
	               ["ico_daily.png", "ico_weekly.png", "ico_monthly.png"],
	               ["javascript:changeDateMode('DAILY')", "javascript:changeDateMode('WEEKLY')", "javascript:changeDateMode('MONTHLY')"]],
    sch_equiplistdetail: [3,
                   [MGW_RES.get("gw_schedule_daily_label"), MGW_RES.get("gw_schedule_weekly_label"), MGW_RES.get("gw_schedule_monthly_label")],
                   ["ico_daily.png", "ico_weekly.png", "ico_monthly.png"],
                   ["javascript:changeDateMode('DAILY')", "javascript:changeDateMode('WEEKLY')", "javascript:changeDateMode('MONTHLY')"]],
    sch_todolist: [3,
	              [MGW_RES.get("gw_schedule_daily_label"), MGW_RES.get("gw_schedule_weekly_label"), MGW_RES.get("gw_schedule_monthly_label")],
	              ["ico_daily.png", "ico_weekly.png", "ico_monthly.png"],
	              ["javascript:changeDateMode('DAILY')", "javascript:changeDateMode('WEEKLY')", "javascript:changeDateMode('MONTHLY')"]],
	sch6_schlist: [4,
	              [MGW_RES.get("gw_schedule_daily_label"), MGW_RES.get("gw_schedule_weekly_label"), MGW_RES.get("gw_schedule_monthly_label"), MGW_RES.get("gw_schedule_monthplan_label")],
	              ["ico_daily.png", "ico_weekly.png", "ico_monthly.png", "ico_plan.png"],
	              ["javascript:changeSch6DateTab('daily')", "javascript:changeSch6DateTab('weekly')", "javascript:changeSch6DateTab('monthly')", "javascript:changeSch6DateTab('plan')"]],
    sch2_schlist: [3,
                 [MGW_RES.get("gw_schedule_daily_label"), MGW_RES.get("gw_schedule_weekly_label"), MGW_RES.get("gw_schedule_monthly_label")],
                 ["ico_daily.png", "ico_weekly.png", "ico_monthly.png"],
                 ["javascript:changeDateMode2('DAILY')", "javascript:changeDateMode2('WEEKLY')", "javascript:changeDateMode2('MONTHLY')"]],  
	sch2_equiplist: [3,
	         	   [MGW_RES.get("gw_schedule_daily_label"), MGW_RES.get("gw_schedule_weekly_label"), MGW_RES.get("gw_schedule_monthly_label")],
	         	   ["ico_daily.png", "ico_weekly.png", "ico_monthly.png"],
	         	   ["javascript:changeDateMode2('DAILY')", "javascript:changeDateMode2('WEEKLY')", "javascript:changeDateMode2('MONTHLY')"]],
};/*
	 * PAGE_CONTROLLER
	 * 
	 * 페이지 이동을 컨트롤하는 모듈
	 * 
	 * 1) init(pageArray) : 뷰페이지들을 로딩 pageArray (String[]): 로딩할 뷰페이지의 리스트
	 * 
	 * 2) goBack(isRefresh) : '뒤로 가기' isRefresh (boolean): 이 값을 true로 하면 돌아가는
	 * 페이지의 controlFN() 을 다시 실행해서 페이지를 refresh 하게 된다 3) goMenu() : '메뉴로 가기'
	 * 
	 * 4) showPage(id, controlFN) 함수로 페이지 전환하기 id (String) : 뷰페이지의 ID ,
	 * data-role='page'인 <div> 에 id 속성으로 정의한, controlFN : id 에 해당하는 페이지를 보여주기
	 * 위해, 데이터를 가져오고 데이터를 렌더링하는 함수
	 * 
	 * ex) init() var VIEW_ARRAY =[ "TEST/view/a.html", "TEST/view/b.html",
	 * "TEST/view/c.html", "TEST/view/sample_mail_list.html" ];
	 * $(document).ready(function(){ PAGE_CONTROLLER.init(VIEW_ARRAY); });
	 * 
	 */

// 화면 확대,축소 정의
var zoomablePagesIDs = ["mail_details", "board_details", "sign_details", "sign_linkdoc_1", "sign_linkdoc_2", "sign_linkdoc_3", "sign_linkdoc_4"];
var zoomablePageMeta ="initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0, user-scalable=yes"; 
var fixedPageMeta = "width=device-width, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no";

// iPad Viewport
var zoomablePageMeta_pad ="width=device-width, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0, user-scalable=yes"; 
var fixedPageMeta_pad = "width=device-width, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes";

var PAGE_CONTROLLER = {
	
	viewURL: "",	
	viewStack:[],
	templateString: "_TEMPLATE_",
	viewAttrMap:{},
	viewLocation: "",
	
	pageTransition: null,
	
	init: function(viewURL, pageTransition){ // 뷰페이지들을 로딩하고 템플릿을 으로 변경하여
												// 메인페이지 'body'에 추가
		if(pageTransition != undefined){
			PAGE_CONTROLLER.pageTransition = pageTransition;
		}	
		
		LOG("Loading HTML from MobileServer...");
		
		$(document).load(viewURL, function(view){
			view = MGW_RES.replaceResource(view);
			var page = $(view);
			$("body").append(page); 
			
			LOG("Success Loading HTML from MobileServer!");
			
			if(device.platform == "Android"){// for 안드로이드
				var callBackFN = function(data){
					setSessionInfo(data);
					GWPlugin.notiLoadingCompleted();
				};
				GWPlugin.getSessionInfo(callBackFN, function(){
					alert("Error: fail to set sessioninfo.");
				});				
			}else{
				GWPlugin.notiLoadingCompleted(function(){}, function(){});
			}
			
		}, function() {
			LOG("Fail Loading HTML from MobileServer!");
			GWPlugin.notiLoadingError(function(){}, function(){});
		});	
		 
		/*
		 * 페이지가 화면에 보여질때, 보여질 페이지의 data.isNew 값이 true 인 경우 controlFN을 실행한다.
		 * controlFN 은 통상 GW_PROXY.invokeOpenAPI() 를 실행하고 결과에따라 페이지에 데이터를 렌더링하는
		 * 로직을 포함하도록 한다.
		 */
		$(document).live("pagechange", function (event, ui) {			
			var page = PAGE_CONTROLLER.getCurrentPage(); // 혹은,
															// $.mobile.activePage;
			if(page == undefined) return;
			var pageID = page.attr("id");
			if (GWPlugin.usePlugin) {
				hideHeader();		
				
				if (APP_INFO_DEVICETYPE == "pad") {
					zoomablePageMeta = zoomablePageMeta_pad;
					fixedPageMeta = fixedPageMeta_pad;
				}
			}
			if(PAGE_CONTROLLER.viewAttrMap[pageID].isNew){				
				if(PAGE_CONTROLLER.viewAttrMap[pageID].controlFN != undefined){					
					PAGE_CONTROLLER.viewAttrMap[pageID].controlFN();
				}
				PAGE_CONTROLLER.viewAttrMap[pageID].isNew = false;
			}else{
				LOG(PAGE_CONTROLLER.viewAttrMap[pageID].scrollTop); 
				$.mobile.silentScroll(PAGE_CONTROLLER.viewAttrMap[pageID].scrollTop);
			}
			
			// change viewport
			var curMeta = $("meta[name=viewport]").attr("content");
			if ( jQuery.inArray(pageID, zoomablePagesIDs) != -1 ) {
				$("meta[name=viewport]").attr("content", fixedPageMeta);
				$(page).trigger('create');
				
				$("meta[name=viewport]").attr("content", zoomablePageMeta);
				$(page).trigger('create');
			} else if ( (jQuery.inArray(pageID, zoomablePagesIDs) == -1) && (curMeta != fixedPageMeta) ) {
				$("meta[name=viewport]").attr("content", fixedPageMeta);
				$(page).trigger('create');
			}
		});
	},
	
	cleanViewStack: function(){
		PAGE_CONTROLLER.viewStack = [];
	},
	pushPage: function(page){
		PAGE_CONTROLLER.viewStack.push(page);
		// PAGE_CONTROLLER.listingStack();
	},
	popPage: function(){
		var page = PAGE_CONTROLLER.viewStack.pop();
		// PAGE_CONTROLLER.listingStack();
		return page;
	},
	getPrevPage: function(){
		PAGE_CONTROLLER.popPage();// 현제 페이지 pop
		return PAGE_CONTROLLER.viewStack[PAGE_CONTROLLER.viewStack.length-1]; 
	},
	getCurrentPage: function(){		
		return PAGE_CONTROLLER.viewStack[PAGE_CONTROLLER.viewStack.length-1]; 
	},
	goBack: function(isRefresh){// isRefresh 가 true 인 경우 data.isNew를 true 로 설정하여
								// 해당 페이지의 controlFN 이 실행되도록 한다
		var prevPage = PAGE_CONTROLLER.getPrevPage();
			
		if(prevPage == undefined){
			PAGE_CONTROLLER.goMenu();
		}else{
			var pageID = prevPage.attr("id");
			if(isRefresh){
				prevPage = PAGE_CONTROLLER.refreshPage(prevPage);
			}else{
				PAGE_CONTROLLER.viewAttrMap[pageID].isNew = false;
			}
			{// setting app controls
				if (GWPlugin.usePlugin) {					
					APP_CONTROLLER.setAppControls(pageID);
				}
			}
			$.mobile.changePage(prevPage, {transition: PAGE_CONTROLLER.pageTransition, reverse: true});
		}
	},
	// 관련문서 Back
	goBackLinkdocList: function(PageNum){
		
		if (PageNum == 1) {
			TOOLBAR_DEF.sign_linkdoc_1 = [0, [], [], []];
		} else if (PageNum == 2) {
			TOOLBAR_DEF.sign_linkdoc_2 = [0, [], [], []];
		} else if (PageNum == 3) {
			TOOLBAR_DEF.sign_linkdoc_3 = [0, [], [], []];
		} else if (PageNum == 4) {
			TOOLBAR_DEF.sign_linkdoc_4 = [0, [], [], []];
		}
		
		var prevPage = PAGE_CONTROLLER.getPrevPage();
		
		if (GW_CONTROLLER_SIGN.linkdocIdx > 0)
			GW_CONTROLLER_SIGN.linkdocIdx = GW_CONTROLLER_SIGN.linkdocIdx - 1;
		
		if(prevPage == undefined){
			PAGE_CONTROLLER.goMenu();
		}else{
			var pageID = prevPage.attr("id");
			PAGE_CONTROLLER.viewAttrMap[pageID].isNew = false;
			
			{// setting app controls
				if (GWPlugin.usePlugin) {					
					APP_CONTROLLER.setAppControls(pageID);
				}
			}
			$.mobile.changePage(prevPage, {transition: PAGE_CONTROLLER.pageTransition, reverse: true});
		}
	},
	goMenu: function(){
		if(GWPlugin.usePlugin){
			// $.mobile.changePage("#dummypage");
			GWPlugin.showSidebarMenu();
		}else{
			$.mobile.changePage("#menu", {transition: PAGE_CONTROLLER.pageTransition, reverse: true});// 임시로
																										// 테스트용
		}
		// ToDo: 메뉴로가는 함수 구현
		// 네이티브 앱의 메뉴로 가는? 혹은 메뉴 WEB페이지로 가는
	},
	getNewPage: function(id){
		var tmpPage = $("#" + PAGE_CONTROLLER.templateString + id).clone();
		if($("#"+id).length != 0){// 기존 뷰가 있으면
			var view = $("#"+id);
			view.empty();// 기존뷰의 내용을 삭제하고
			view.append(tmpPage.html());// 탬플릿 뷰의 내용으로 대체
			view.page('destroy').page();
			
			return view;
		}

		tmpPage.attr("id", id);
		tmpPage.attr("data-url", id);
		$("body").append(tmpPage);		
				
		return tmpPage;
	},
	getNewCopyPage: function(template_id, id){
		var tmpPage = $("#" + PAGE_CONTROLLER.templateString + template_id).clone();
				
		if($("#"+template_id + id).length != 0){// 기존 뷰가 있으면 삭제
			$("#"+template_id + id).remove();
		}
		tmpPage.attr("id", template_id + id);
		tmpPage.attr("data-url", template_id + id);
		$("body").append(tmpPage);		
				
		return tmpPage;
	},
	refreshPage: function(page){
		var id = page.attr("id");		
		page = PAGE_CONTROLLER.getNewPage(id);				
		PAGE_CONTROLLER.viewAttrMap[id].isNew = true;
		return page;
	},
	showPage: function(pageID, controlFN){	
		if(controlFN == undefined){
			controlFN = function(){};
		} 
		if(PAGE_CONTROLLER.getCurrentPage() != undefined){			
			PAGE_CONTROLLER.viewAttrMap[PAGE_CONTROLLER.getCurrentPage().attr("id")].scrollTop = $(document).scrollTop();
		}
		var page = PAGE_CONTROLLER.getNewPage(pageID);		
		PAGE_CONTROLLER.viewAttrMap[pageID] = {"controlFN": controlFN, "isNew": true};
		PAGE_CONTROLLER.pushPage(page);
		{// setting app controls
			if (GWPlugin.usePlugin) {
				hideWebView();
				hideHeader();
				APP_CONTROLLER.setAppControls(pageID);
			}
		}
		$.mobile.changePage(page);
		showWebView();
	}, 
	showCopyPage: function(template_id, id, controlFN){
		if(controlFN == undefined){
			controlFN = function(){};
		} 
		if(PAGE_CONTROLLER.getCurrentPage() != undefined){			
			PAGE_CONTROLLER.viewAttrMap[PAGE_CONTROLLER.getCurrentPage().attr("id")].scrollTop = $(document).scrollTop();
		}
		var page = PAGE_CONTROLLER.getNewCopyPage(template_id, id);		
		PAGE_CONTROLLER.viewAttrMap[template_id + id] = {"controlFN": controlFN, "isNew": true};
		PAGE_CONTROLLER.pushPage(page);
		$.mobile.changePage(page);
	}, 
	listingStack: function (){ // 스택에 쌓인 페이지 리스트를 콘솔에 보여주기 위한 함수.
		console.log("Stack Status...(index : page id)");
		for(var i in PAGE_CONTROLLER.viewStack){			
			console.log("index["+ i +"]: " + PAGE_CONTROLLER.viewStack[i].attr("id"));
		} 
	}	
};

var GW_PROXY = {
	ERROR_SYSTEM_ERROR:"1001",
	ERROR_INVALID_REQUEST:"1002",
	ERROR_SESSION_EXPIRED:"1003",
	ERROR_ACCESS_DENIED:"1004",
	ERROR_UNAUTHORIZED_PHONE_UID_NUMBER:"1011",
	ERROR_UNREGISTERED_NEW_MULTI_DEVICE:"1012",	
	ERROR_APPVERSION_NOTEQUAL_ERROR:"1021",	
	ERROR_LICENSE_ERROR_FILENOTFOUND:"1101",
	ERROR_LICENSE_ERROR_INVALIDINFO:"1102",
	ERROR_LICENSE_ERROR_SERVERIP:"1103",
	ERROR_LICENSE_ERROR_EXPIRED:"1104",
	ERROR_LICENSE_ERROR_EXCEEDUSER:"1105",
	ERROR_FILE_DOWNLOAD_ERROR:"1201",
	ERROR_FILE_TRANSFORM_ERROR:"1202",
	ERROR_FILE_UNSUPPORTED_TYPE_ERROR:"1203",	
	ERROR_MDM_APPLICATION_PERMISSTION_ERROR:"1301",
	ERROR_MDM_UNAPPROVED_DEVICE_ERROR:"1302",
	ERROR_MDM_DUPLICATED_DEVICE_ERROR:"1303",
	ERROR_MDM_MULTIDEVICE_ERROR:"1304",
	ERROR_MDM_UNREGISTERED_USER_ERROR:"1305",
	ERROR_MDM_LICENSE_EXCEEDUSER_ERROR:"1306",
	ERROR_MDM_UNREGISTERED_APP_ERROR:"1307",
	ERROR_MDM_UNEXPECTED_ERROR:"1308",
	ERROR_GW_DUPLICATE_ERROR:"99",
	ERROR_GW_INVALID_REQUEST:"10",
		
	invokeOpenAPI: function(category, apiCode, params, successFN, errorFN){
		if(!errorFN){
			errorFN = GW_PROXY.default_errorFN;
		}

		var openapipath_and_type = GW_OpenAPI.getOpenAPIPathAndType(category, apiCode);// OpenAPI
																						// openapipath
																						// 와
																						// request
																						// method
																						// type
																						// 가져오기
																						// {openapipath:???,type:???}
		params["openapipath"] = openapipath_and_type.openapipath;
		params = GW_OpenAPI.adjustParams(category, apiCode, params); // 파라미터
																		// 조정
		LOG("invokeOpenAPI : " + JSON.stringify(params));
		var url;
		if(category == "session"){// 웹에서 로그인하는 경우(임시)
			url = GW_OpenAPI.serverIP + openapipath_and_type.openapipath;			
		} 
		else if (apiCode == "write" || apiCode == "setuserinfo") {
			url = GW_OpenAPI.serverIP + GW_OpenAPI.multipartPATH;
		} 
		else if (apiCode == "apprApprove") {
			url = GW_OpenAPI.serverIP + GW_OpenAPI.apprOpenApiPath + "/approve";
		} 
		else if (apiCode == "apprApproveHtml") {
			url = GW_OpenAPI.serverIP + GW_OpenAPI.apprOpenApiPath + "/approvehtml";
		}
		else if (apiCode == "resolveuser") {
			url = GW_OpenAPI.serverIP + openapipath_and_type.openapipath;
		} 
		else{
			url = GW_RESTAPI.getRestWSURI(category, apiCode);
		}
		
		var successFN2 = function(data){// API가 정상적으로 호출된 경우
			// ToDo: 메지시를 분석하고 에러메시지인지의 여부에따라 처리
			if (data != undefined) {
				var resultCode = data.code;
				var resultMsg = data.message;
			}

			try{
				if(resultCode == undefined){
					resultCode = "0";
					resultMsg = "success";
				}
			} catch(e){
				alert(JSON.stringify(data));
				resultCode = "999";
				resultMsg = "Unknown error";
			}
			
			// SMTP 체크 시 처리
			if(apiCode == "smtp" && data.message != undefined) {
				successFN(data);
			}
			
			// 결재 승인 & 반송 요청 시 실패 처리
			else if (apiCode == "apprApprove" && resultCode == 10) {
				successFN(data);
			}
			// 결재 승인 & 반송 요청 시 실패 처리
			else if (apiCode == "apprApproveHtml" && resultCode == 10) {
				successFN(data);
			}
			// 결재 회수 처리
			else if (apiCode == "withdraw" && resultCode == 0) {
				successFN(data);
			}
			// 결재 암호 확인
			else if (apiCode == "checksancpasswd" && resultCode == "101") {
				hidePageLoadingView();
				alert(MGW_RES.get("gw_msg_common_wrong_password"));
			}		
			// 게시물 보기 권한 처리
			else if (apiCode == "details" && resultCode == 999) {
				hidePageLoadingView();
				alert(MGW_RES.get("gw_msg_board_authread_err"));
				if (GWPlugin.usePlugin)
					goList();
			}
			// 게시물 등록 오류 처리
			else if (apiCode == "writebbs" && resultCode == 999) {
				successFN(data);
			}			
			// 게시물 암호 확인
			else if ((apiCode == "chkpwdBoard" || apiCode == "chkpwdBoardComment") && resultCode == "1") {
				successFN(data);
			}			
			// 6버전 일정 추가 처리
			else if((resultCode == "Success." || resultCode == "fail.") 
					&& (apiCode == "schadd" || apiCode == "schdelete")) {
				successFN(data);
			}
			// tmanager2 할일 상세, 추가, 리스트, 삭제시 처리 로직
			else if((apiCode == "todoadd" || apiCode == "tododetail" || apiCode == "todolist" || apiCode == "tododelete") && resultCode == "200"){
				successFN(data);
			}
			
			// 암호 변경 요청 시 실패 처리
			else if(apiCode == "setpassword" && data.message != undefined) {
				successFN(data);
			}
			// 부재 설정 요청시 실패 처리
			else if((apiCode == "setabsence" && resultCode == 999) || (apiCode == "absenceadd" && resultCode == 999)) {
				successFN(data);
			}
			else if(resultCode != "0"){
				if(errorFN) {
					errorFN(data);
					return;
				}
				// 받은 데이터가 에러메시지인경우
				GW_PROXY.alertErrorMessage(resultCode, resultMsg); // 에러메시지 출력
				// ToDo: 에러인 경우의 후처리
			}
			else{
				// 에러가 아닌경우 정상적인 처리
				successFN(data);
			}
			
			// 결재 승인 or 반송 or 결재첨부 조회시 skip
			if (apiCode != "apprApprove" && apiCode != "apprApproveHtml" && (category != "sign" || apiCode != "attachlist"))
				hidePageLoadingView();// Hide spinner
		};
		$.ajax({
			beforeSend: function(req) {
				// 최초 로딩일때는 skip
				if (apiCode != "callist" && apiCode != "schequiplist" && apiCode != "smtp")	showPageLoadingView();
			}, // Show spinner
		    complete: function() { }, // Hide spinner //hidePageLoadingView();
			type: openapipath_and_type.type,
			url: url,
			async: category!="org",
			success: successFN2,
			error:errorFN,
			data: params,
			dataType:"json"
		});
	},
	invokeDirect: function(link, type, successFN, errorFN){
		link = trim(link);
// LOG("link="+link);
		var params = {};
		params["openapipath"] = link;

		if(!errorFN){
			errorFN = GW_PROXY.default_errorFN;
		}

		var url;
		url = GW_OpenAPI.serverIP + GW_OpenAPI.basePATH;
		
		var successFN2 = function(data){// API가 정상적으로 호출된 경우
			// ToDo: 메지시를 분석하고 에러메시지인지의 여부에따라 처리
			var resultCode = data.code;
			var resultMsg = data.message;

			try{
				if(resultCode == undefined){
					resultCode = "0";
					resultMsg = "success";
				}
			}catch(e){
				alert(JSON.stringify(data));
				resultCode = "999";
				resultMsg = "Unknown error";
			}
			
			if(resultCode != "0"){
				// 받은 데이터가 에러메시지인경우
				GW_PROXY.alertErrorMessage(resultCode, resultMsg); // 에러메시지 출력
				// ToDo: 에러인 경우의 후처리
			}
			else{
				// 에러가 아닌경우 정상적인 처리
				successFN(data);
			}
			hidePageLoadingView();// Hide spinner
		};
		
		$.ajax({
			beforeSend: function(req) {showPageLoadingView();}, // Show spinner
		    complete: function() { }, // Hide spinner //hidePageLoadingView();
		    dataType:"json",
		    type: type,
		    url: url,
		    data: params,
			success: successFN2,
			error:errorFN
		});
	},
	invokeCheckApprStatus: function(apprid, successFN, errorFN){
		apprid = trim(apprid);
		var params = {};
		params["apprid"] = apprid;

		var url = GW_OpenAPI.serverIP + GW_OpenAPI.apprOpenApiPath + "/checkapprstatus";
		var successFN2 = function(data){
			var resultCode = data.code;
			var resultMsg = data.message;

			try{
				if(resultCode == undefined){
					resultCode = "0";
					resultMsg = "SUCCESS";
				}
			} catch(e){
				alert(JSON.stringify(data));
				resultCode = "999";
				resultMsg = "Unknown error";
			}
			
			if(resultCode == "0"){
				successFN(data);
			}
			else{
				GW_PROXY.alertErrorMessage(resultCode, resultMsg);
			}
		};
		var errorFN = function(xhr, opt, msg){
			alert(MGW_RES.get("gw_msg_common_err"));
		};
		
		$.ajax({
			beforeSend: function(req) {},
		    complete: function() { }, 
		    dataType:"json",
		    url: url,
		    data: params,
			success: successFN2,
			error:errorFN
		});
	},
	
	
	alertErrorMessage: function(code, msg){
		hidePageLoadingView();// Hide spinner
		switch(code){		
			case GW_PROXY.ERROR_SYSTEM_ERROR:
				alert(MGW_RES.get("gw_error_system_error"));
				break;
			case GW_PROXY.ERROR_GW_INVALID_REQUEST:
			case GW_PROXY.ERROR_INVALID_REQUEST:
				alert(MGW_RES.get("gw_error_invalid_request"));
				break;
			case GW_PROXY.ERROR_SESSION_EXPIRED:
				alert(MGW_RES.get("gw_error_session_expired"));
				if (GWPlugin.usePlugin) {
					GWPlugin.showLoginView(function() {}, function() {});
				}
				break;
			case GW_PROXY.ERROR_ACCESS_DENIED:
				alert(MGW_RES.get("gw_error_access_denied"));
				if (GWPlugin.usePlugin) {
					GWPlugin.showLoginView(function() {}, function() {});
				}				
				break;
			case GW_PROXY.ERROR_UNAUTHORIZED_PHONE_UID_NUMBER:
				alert(MGW_RES.get("gw_error_unauthorized_phone_uid_number"));
				break;
			case GW_PROXY.ERROR_UNREGISTERED_NEW_MULTI_DEVICE:
				alert(MGW_RES.get("gw_error_unregistered_new_multi_device"));
				break;
			case GW_PROXY.ERROR_APPVERSION_NOTEQUAL_ERROR:
				alert(MGW_RES.get("gw_error_appversion_notequal"));
				break;
			case GW_PROXY.ERROR_LICENSE_ERROR_FILENOTFOUND:
				alert(MGW_RES.get("gw_error_license_filenotfound"));
				break;
			case GW_PROXY.ERROR_LICENSE_ERROR_INVALIDINFO:
				alert(MGW_RES.get("gw_error_license_invalid_info"));
				break;
			case GW_PROXY.ERROR_LICENSE_ERROR_SERVERIP:
				alert(MGW_RES.get("gw_error_license_invalid_serverip"));
				break;
			case GW_PROXY.ERROR_LICENSE_ERROR_EXPIRED:
				alert(MGW_RES.get("gw_error_license_expired"));
				break;
			case GW_PROXY.ERROR_LICENSE_ERROR_EXCEEDUSER:
				alert(MGW_RES.get("gw_error_license_exceeduser"));
				break;
			case GW_PROXY.ERROR_FILE_DOWNLOAD_ERROR:
				alert(MGW_RES.get("gw_error_file_download"));
				break;
			case GW_PROXY.ERROR_FILE_TRANSFORM_ERROR:
				alert(MGW_RES.get("gw_error_file_transform"));
				break;
			case GW_PROXY.ERROR_FILE_UNSUPPORTED_TYPE_ERROR:
				alert(MGW_RES.get("gw_error_file_unsupported_type"));
				break;
			case GW_PROXY.ERROR_GW_DUPLICATE_ERROR:
				alert(MGW_RES.get("gw_error_duplicate"));
				break;
			default:
				alert(MGW_RES.get("gw_msg_common_err"));
				break;		
		}
	},
	default_errorFN : function(xhr, opt, msg){
		hidePageLoadingView();// Hide spinner
		console.log("error" + xhr);
		console.log(opt);
		console.log(msg);
		alert(MGW_RES.get("gw_msg_common_err"));
	} 
};

/**
 * App 목록(메일, 게시판, 결재, 주소록)에서 호출하는 RestAPI 테스트
 */
var GW_RESTAPI = {
	// 메일
	mail_list_PATH: "/rest/openapi/mail/list",
	mail_search_PATH: "/rest/openapi/mail/search",
	mail_personalbox_PATH: "/rest/openapi/mail/personalbox",
	mail_delete_PATH: "/rest/openapi/mail/delete",
	
	// 게시판
	board_list_PATH: "/rest/openapi/board/list",
	board_recent_PATH: "/rest/openapi/board/recentlist",
	board_folder_PATH: "/rest/openapi/board/folder",
	board_favfolder_PATH: "/rest/openapi/board/favfolder",
	
	// 결재
	appr_list_PATH: "/rest/openapi/appr/list",
	
	// 주소록
	contact_list_PATH: "/rest/openapi/contact/list",
	contact_group_PATH: "/rest/openapi/contact/group",
	contact_search_PATH: "/rest/openapi/contact/search",	
	
	// 일정
	schedule_PATH: "/rest/openapi/schedule",

	// 공통
	common_ipcheckrange_PATH: "/rest/common/ipcheckrange",
	common_licenseinfo_PATH: "/rest/common/getlicenseinfo",
	
	getRestWSURI: function(category, apiCode) {
		if (category == "mail") {
			if (apiCode == "recvlist"						// 메일 목록
				|| apiCode == "sendlist" 
				|| apiCode == "deletelist" 
				|| apiCode == "templist" 
				|| apiCode == "personallist")  
			{
				return GW_OpenAPI.serverIP + GW_RESTAPI.mail_list_PATH;	
			} 
			else if (apiCode == "personalbox") {			// 개인편지함 폴더
				return GW_OpenAPI.serverIP + GW_RESTAPI.mail_personalbox_PATH;	
			} 
			else if (apiCode == "deletemail") {				// 메일 삭제
				return GW_OpenAPI.serverIP + GW_RESTAPI.mail_delete_PATH;
			} 
		}
		else if (category == "board") {
			if (apiCode == "recentlist") {				// 최근 게시물 목록
				return GW_OpenAPI.serverIP + GW_RESTAPI.board_recent_PATH;	
			}
			else if (apiCode == "list") {					// 게시물 목록
				return GW_OpenAPI.serverIP + GW_RESTAPI.board_list_PATH;	
			}
			else if (apiCode == "folder") {					// 하위 게시판 목록
				return GW_OpenAPI.serverIP + GW_RESTAPI.board_folder_PATH;	
			}
			else if (apiCode == "favfolder") {				// 즐겨찾기 게시판 목록
				return GW_OpenAPI.serverIP + GW_RESTAPI.board_favfolder_PATH;	
			}
		}
		else if (category == "contact") {
			if (apiCode == "personal" 					// 주소록 목록
				|| apiCode == "department" 
				|| apiCode == "group") 
			{
				return GW_OpenAPI.serverIP + GW_RESTAPI.contact_list_PATH;	
			} 
			else if (apiCode == "grouplist") {				// 주소록 그룹
				return GW_OpenAPI.serverIP + GW_RESTAPI.contact_group_PATH;	
			}
			else if (apiCode == "search_user"				// 주소록 검색
				|| apiCode == "search_dept"
				|| apiCode == "search_group") 
			{
				return GW_OpenAPI.serverIP + GW_RESTAPI.contact_search_PATH;	
			}
		}
		else if (category == "sign") {
			if (apiCode == "waitlist" 						// 결재 목록
				|| apiCode == "gongramwaitlist"
				|| apiCode == "gongramcompletelist"
				|| apiCode == "nowlist"
				|| apiCode == "receiptwaitlist"
				|| apiCode == "userprocessedlist"
				|| apiCode == "mycompletelist"
				|| apiCode == "completelist"
				|| apiCode == "dispatchlist")
			{
				return GW_OpenAPI.serverIP + GW_RESTAPI.appr_list_PATH;	
			} 
		}
		else if (category == "schedule") {					// 일정
			return GW_OpenAPI.serverIP + GW_RESTAPI.schedule_PATH;	
		}
		else if (category == "common") {					// 공통
			if (apiCode == "ipcheckrange") {				// ipcheckrange
				return GW_OpenAPI.serverIP + GW_RESTAPI.common_ipcheckrange_PATH;	
			}
			else if (apiCode == "getlicenseinfo") {			// licenseinfo
				return GW_OpenAPI.serverIP + GW_RESTAPI.common_licenseinfo_PATH;	
			}
		}
		return GW_OpenAPI.serverIP + GW_OpenAPI.basePATH;
		
	}
};

var GW_OpenAPI = {
	loginID: "handy",
	version: "6.7.7",
	serverIP: "http://localhost:8080/mgw",
	basePATH: "/rest/openapi/bypass/json",
	streamPATH: "/rest/openapi/bypass/stream?openapipath=",
	streamUrlPATH: "/rest/openapi/bypass/urlstream?urlpath=",
	multipartPATH: "/rest/openapi/bypass/multipart",
	apprOpenApiPath: "/rest/openapi/appr",
	
	// GW 6의 기본 구성: db, original, hwp6, ver6
	// GW 7의 기본 구성: web, tmanager, tagfree, ver7
	// GW 8의 기본 구성: web, tmanager, html, ver8
	mail_type: "db",			// 1) db, 2) web
	schedule_type: "original",	// 1) original, 2) tmanager
	sign_type: "hwp6",			// 1) hwp6, 2) hwp8, 3) tagfree, 4) html
	contact_type: "contact1",	// 1) contact1 (6,7버전), 3) contact2 (8버전)
	settings_type: "settings1",	// 1) settings1, 2) settings2
	board_onlybody: false,		// 게시물 본문 양식정보 포함 여부 1) true, 2) false
	board_convert_image: true,	// 게시물 본문 내부망 이미지 스트림 변환 여부 1) true, 2) false
	mail_search_use: false,		// 메일 검색 기능 사용 여부 1) true, 2) false
	mail_directinputuser_use: false,	// 메일 수신자 직접 입력 기능 사용 여부 1) true, 2)
										// false
	mail_replyall_use: false,		// 전체 답장 기능 사용 여부 1) true, 2) false
	mail_replace_sendbodytag: true, // 메일 쓰기시 엔터(/\n/g)값 <br/> tag 변경 여부 1)
									// true, false
	appr_linkdoc_use: false,		// 결재 관련 문서 사용 여부 1) true, 2) false
	org_rootdept_use: true,			// 최상위부서 버튼 사용 여부 1) true, 2) false
	appr_approve_input_comment_use: "1",		// 결재 처리할 시 의견 미기입 시 의견창 자동 생성
												// 여부 1) 0:사용안함, 2) 1:반려시,
												// 3)2:승인/반려
	appr_approve_openapi_use: false,			// OpenAPI 결재 처리 여부 1) true, 2)
												// false
	appr_approve_status: false,					// 결재 상태 확인 용 Value
	attach_filesize_unit: "",					// 첨부 파일사이즈 단위
	ip_checkrange_use: false,					// Ip Check Range Valid 여부
	appr_approve_junkyul_use: false,			// 전결 사용 여부 1) true, 2) false
	appr_approve_recall_use: false,				// 결재취소 사용 여부 1) true, 2) false
	doc_transform_viewer_external: false,		// 문서변환 외부뷰어 여부 (true/false)
	org_open_type: "0",							// 조직공개범위 (0 : 전체 조직 조회, 1 : 기관만
												// 조회, 2 : 자기 부서만 조회)
	rep_deptid: "",								// 현재 사용자의 기관부서ID. 상위기관이 없는 경우
												// 루트부서ID

	mobileInit: function(data) {
		GW_OpenAPI.version = data["gw.version"];
		GW_OpenAPI.mail_type = data["mail.type"];
		GW_OpenAPI.schedule_type = data["schedule.type"];
		GW_OpenAPI.sign_type = data["sign.doctype"];
		GW_OpenAPI.contact_type = data["contact.type"];
		GW_OpenAPI.settings_type = data["settings.type"];
		GW_OpenAPI.board_onlybody = data["bbs.dm.onlybody.use"];
		GW_OpenAPI.appr_linkdoc_use = data["appr.linkdoc.use"];
		GW_OpenAPI.appr_approve_input_comment_use = data["appr.approve.input.comment.use"];
		GW_OpenAPI.appr_approve_openapi_use = data["appr.approve.openapi.use"];
		GW_OpenAPI.appr_instructions_use = data["appr.instructions.use"];
		GW_OpenAPI.attach_filesize_unit = data["attach.filesize.unit"];
		GW_OpenAPI.appr_approve_junkyul_use = data["appr.approve.junkyul.use"];
		GW_OpenAPI.appr_approve_recall_use = data["appr.approve.recall.use"];
		GW_OpenAPI.org_open_type = data["session.orgopentype"];
		GW_OpenAPI.rep_deptid = data["session.repdeptid"];
		
		if (data["mail.search.use"] != undefined && data["mail.search.use"] == "Y")
			GW_OpenAPI.mail_search_use = true;
		
		if (data["mail.directinputuser.use"] != undefined && data["mail.directinputuser.use"] == "Y")
			GW_OpenAPI.mail_directinputuser_use = true;
		
		if (data["mail.replyall.use"] != undefined && data["mail.replyall.use"] == "Y")
			GW_OpenAPI.mail_replyall_use = true;
		
		if (data["appr.instructions.use"] != undefined && data["appr.instructions.use"] == "Y" && APP_INSTRUCTIONS_USE == "Y" )
			GW_OpenAPI.appr_instructions_use = true;
		
		if (data["ip.checkrange.use"] != undefined && data["ip.checkrange.use"] == "Y" )
			GW_OpenAPI.ip_checkrange_use = true;
		
		if (data["doc.transform.viewer.external"] != undefined && data["doc.transform.viewer.external"] == "Y" )
			GW_OpenAPI.doc_transform_viewer_external = true;		
		
		// tmanager의 경우만 달력, 장비목록 미리 로딩
		if (GW_OpenAPI.schedule_type == "tmanager") {
			getCalendarList(function(){});
			getEquipmentList(function(){});
		}
		else if (GW_OpenAPI.schedule_type == "tmanager2") {
			// ios javascript object cache현상 처리
			clearSchData();
			
			getCalendar2List(function(){});
			getEquipment2List(function(){});
			getEquipment2GroupList(function(){});
			getSchConfig(function(){});
			getSchCategoryList(function(){});
		}

		// DateBox locale Set
		setDateBoxLang();

		// SMTP (DB메일에서만 SMTP 설정 여부 확인) 미리 로딩
		if (GW_OpenAPI.mail_type == "db") {
			getMailSmtp();
		} else if (GW_OpenAPI.mail_type == "web") {
			getMailConfig(function(){});
		}
		
		// 결재환경설정
		if (GW_OpenAPI.settings_type == "settings2") {
			getApprProfile(function(){});
		}
	},
	adjustParams: function(category, apiCode, orgParams){
		// 카테고리와 그 타입에 따라 분기
		var replaceFN;
		switch(category){
			case "mail" :
				replaceFN = GW_OpenAPI.replaceParams[category][GW_OpenAPI.mail_type]; 
				break;
			case "sign" :
				replaceFN = GW_OpenAPI.replaceParams[category];
				break;
			case "contact" :
				replaceFN = GW_OpenAPI.replaceParams[category][GW_OpenAPI.contact_type];
				break;
			case "schedule" :
				replaceFN = GW_OpenAPI.replaceParams[category][GW_OpenAPI.schedule_type];
				break;
			case "board" :
				replaceFN = GW_OpenAPI.replaceParams[category];
				break;
			case "org" :
				replaceFN = GW_OpenAPI.replaceParams[category];
				break;
			case "settings" :
				replaceFN = GW_OpenAPI.replaceParams[category][GW_OpenAPI.settings_type];
				break;
			default:
				replaceFN = null; // 임시
		}
		if (typeof replaceFN == 'function') {
			return replaceFN(apiCode, orgParams);
		} else {
			return orgParams;
		}
	},
	replaceParams:{	
		// 메일: web, db
		mail:{			 
			web: function(apiCode, params){
				
				if (apiCode == "personallist") {
					params["mailbox"] = "personallist";
				}
				
				/**
				 * 2.1.2 편지 읽기 - "1.웹메일_OPENAPI Page : 6"
				 * /wma/openapi.do?acton=readbymid&boxid=000000002&
				 * msgid=9608962.773.1364783448819.JavaMail.soadm@smtp.dummydomain.com&key=001000008&key=00xwn3BK13
				 */
				if (apiCode == "details") {
					params["acton"] = "readbymid";
				}
				
				/**
				 * 2.1.3 첨부 읽기 - "1.웹메일_OPENAPI Page : 8"
				 * /wma/openapi.do?&acton=partbymid&boxid=000000002&msgid=9608962.773.1364783448819.JavaMail.soadm@smtp.dummydomain.com&seq=1&key=00xwn3BK13
				 */
				if (apiCode == "attach") {
					params["acton"] = "partbymid";
					delete param["filename"];
				}
				/**
				 * 2.1.4 편지 쓰기 - "1.웹메일_OPENAPI Page : 10" 해당 문서 Form Sample 참조
				 */
				if (apiCode == "write") {
					params["openapi"] = "true";
					params["ismassmail"] = "false";
					params["autoarchivesent"] = "true"; // 보낸 편지함 자동 저장여부
														// (디폴트:false)
					
					delete params["Emergency"];
					delete params["Security"];
					delete params["APP"];
					delete params["MSID"];
					// delete params["openapipath"];
				}
				
				/**
				 * 2.1.8 메일 회수 - "1.웹메일_OPENAPI Page : 16"
				 * /wma/openapi.do?acton=cancelbymid&boxid=000000002&msgid=9608962.773.1364783448819.JavaMail.soadm@smtp.dummydomain.com&key=0035D2DGy
				 */
				if (apiCode == "cancel") {
					params["acton"] = "cancelbymid";
				}
				
				/**
				 * 2.1.9 수신 확인 - "1.웹메일_OPENAPI Page : 17"
				 * /wma/openapi.do?acton=trackerlistbymid&boxid=000000002&msgid=msgid=9608962.773.1364783448819.JavaMail.soadm@smtp.dummydomain.com&key=0035D2DGy
				 */
				if (apiCode == "tracker") {
					params["acton"] = "trackerlistbymid";
				}
				
				/**
				 * 2.1.10 편지 삭제 - "1.웹메일_OPENAPI Page : 18"
				 * /wma/openapi.do?acton=delete&boxid=BoxIdValue&mailed=20&purge=false&key=KeyValue
				 */
				if (apiCode == "deletemail") {
					params["purge"] = "false";
				}
				
				/**
				 * 2.2.10 메일 config - "1.웹메일_OPENAPI Page : 30"
				 * /wma/openapi.do?acton=getConfig&key=KeyValue
				 */
				if (apiCode == "mailconfig") {
					params["acton"] = "getConfig";
				}				
				
				return params;
			},
			db: function(apiCode, params){
				/**
				 * 2.1.2 편지 읽기 - "1.메일_OPENAPI Page : 6"
				 * /jsp/openapi/OpenApi.jsp?target=mail&todo=read&SAVEID=SaveIdValue&K=KeyValue
				 */
				if (apiCode == "details") {
					params["target"] = "mail";
					params["todo"] = "read";
				}
				
				/**
				 * 2.1.3 첨부 읽기 - "1.메일_OPENAPI Page : 8"
				 * /jsp/AttView.jsp?APP=1&ID=00000063h&SEQ=0&FILENAME=filenameforsample.xls&K=KeyValue
				 */
				if (apiCode == "attach") {
					params["APP"] = 1;
					param.ID = param.mailid;
				}
				
				/**
				 * 2.1.4 SMTP 설정 여부 - "1.메일_OPENAPI Page : 9"
				 * /jsp/openapi/OpenApi.jsp?target=mail&todo=smtp&K=KeyValue
				 */
				if (apiCode == "smtp") {
					params["target"] = "mail";
					params["todo"] = "smtp";
				}
				
				/**
				 * 2.1.5 편지 쓰기 및 보내기 - "1.메일_OPENAPI Page : 10" 해당 문서 Form
				 * Sample 참조
				 */
				if (apiCode == "write") {
					params["todo"] = "send";
					params["SLET"] = "openapi.MailApi.java";
					
					params["TO"] = params["to"];
					params["TITLE"] = params["subject"];
					params["BODY"] = params["bodytext"];
					params["CC"] = "";
					params["BCC"] = "";
					
					if (params["cc"] != undefined)
						params["CC"] = params["cc"];
					
					if (params["bcc"] != undefined)
						params["BCC"] = params["bcc"];
					
					// delete params["hwto"];
					delete params["to"];
					delete params["subject"];
					delete params["priority"];
					delete params["secure"];
					delete params["bodytext"];
				}
				
				/**
				 * 2.1.7 보낸 편지 회수 - "1.메일_OPENAPI Page : 15"
				 * /jsp/openapi/OpenApi.jsp?target=mail&todo=cancel&SAVEID=SaveIdValue&K=KeyValue
				 */
				if (apiCode == "cancel") {
					params["target"] = "mail";
					params["todo"] = "cancel";
				}
				
				/**
				 * 2.1.8 수신 확인 - "1.메일_OPENAPI Page : 15"
				 * /jsp/openapi/OpenApi.jsp?target=mail&todo=report&SAVEID=00000008d&K=KeyValue
				 */
				if (apiCode == "tracker") {
					params["target"] = "mail";
					params["todo"] = "report";
				}
				
				return params;
			}
		},		
		// 결재 : 한글1, 한글2, TagFree, Pure HTML 로 구분
		sign: function(apiCode, params){
			
// 결재대기: 2010
// 결재진행: 2020
// 발송대기: 4030
// 발송진행: 4020
// 접수대기: 5010
// 접수진행: 5020
// 기록물등록대장: 8010
// 발송처리함: 4010
			
			
			/**
			 * 7.1.1 문서 목록 - "7.결재(6.7.5.9)_OPENAPI Page : 4"
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=display&APPLID=2010&type=0&K=001000106
			 */
			// 결재대기
			if (apiCode == "waitlist"){
				params["type"] = "wait";
			}
			// 공람대기
			else if (apiCode == "gongramwaitlist"){
				params["type"] = "gongram";
			}			
			// 공람완료
			else if (apiCode == "gongramcompletelist"){
				params["type"] = "gongramcomplete";
			}			
			// 결재진행
			else if (apiCode == "nowlist"){
				params["type"] = "now";
			}
			// 접수대기
			else if (apiCode == "receiptwaitlist"){
				params["type"] = "receiptwait";
			}
			// 개인문서함[결재한문서] : GW8
			else if (apiCode == "userprocessedlist"){
				params["type"] = "userprocessed";
			}
			// 개인문서함[완료문서] : GW6 & GW7
			else if (apiCode == "mycompletelist"){
				params["type"] = "mycomplete";
			}
			// 기록물대장[완료함]
			else if (apiCode == "completelist"){
				params["type"] = "complete";
			}
			// 발송처리함
			else if (apiCode == "dispatchlist"){
				params["type"] = "dispatch";
			}			

			/**
			 * 7.1.3 첨부 목록 - "7.결재_OPENAPI Page : 7"
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=displayattachlist&APPLID=5020&APPRID=7158.1000&type=0&K=001000106
			 */
			else if (apiCode == "attachlist"){
				params["target"] = "appr";
				params["todo"] = "displayattachlist";
			}
			
			/**
			 * 7버전 의견목록 :
			 * /handydocs/confhtml/apprcomment_openapi.jsp?APPRID=2010 8버전 의견목록 :
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=apprcomment&type=0&APPRID=J10CB123340000828000&K=00ZmS2APh3
			 */
			else if (apiCode == "commentlist") {
				params["target"] = "appr";
				params["todo"] = "apprcomment";
				params["type"] = "0";
			}
			/**
			 * 8버전 의견상태 :
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=apprflow&type=0&APPRID=J10CB123340000828000&K=00ZmS2APh3
			 */
			else if (apiCode == "apprflow") {
				params["target"] = "appr";
				params["todo"] = "apprflow";
				params["type"] = "0";
			}
			/**
			 * 결재 회수 :
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=docWithdraw&APPRID=12352.65800&K=00nYk1alA1
			 */
			else if (apiCode == "withdraw") {
				params["target"] = "appr";
				params["todo"] = "docWithdraw";
			}
			/**
			 * 요약전 :
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=summaryDoc&type=0&APPRID=9037.45100&WORDTYPE=3
			 */
			else if (apiCode == "summarydoc") {
				params["target"] = "appr";
				params["todo"] = "summaryDoc";
			}
			/**
			 * 관련 문서 List :
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=linkDocList&type=list&APPRID=J10CB132060000401000&K=00uscCKs
			 * 관련 문서 Count :
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=linkDocList&type=count&APPRID=J10CB132060000401000&K=00uscCKs
			 */
			else if (apiCode == "linkdoclist") {
				params["target"] = "appr";
				params["todo"] = "linkDocList";
				// params["type"] = "list";
				// params["type"] = "count";
			}
			/**
			 * html 기안 처리 :
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=apprproc&DOCID=
			 * J10CB130250004928000&K=00nYk1alA1&USERID=001000003
			 */
			else if (apiCode == "apprApprove" || apiCode == "apprApproveHtml") {
				params["USERID"] = sessionStorage["id"];
			}
			/**
			 * 발송처리 권한정보 :
			 * /jsp/openapi/OpenApi.jsp?target=appr&todo=sndngProcAuthList
			 */			
			else if (apiCode == "procauthlist") {
				params["target"] = "appr";
				params["todo"] = "sndngProcAuthList";
			}
			
			return params;
		},		
		// 일정관리: tmanager 인지의 여부로 구분
		schedule:{
			original: function(apiCode, params){
				// 일정 목록
				if(apiCode == "schlist") {
					params["target"] = "cal";
					params["todo"] = "display";
				}
				// 일정상세보기
				else if(apiCode == "schdetail") {
					params["target"] = "cal";
					params["todo"] = "detail";
				}
				// 일정등록
				else if(apiCode == "schadd") {
					params["target"] = "cal";
					params["VIEW"] = "event_openapi_data";
					params["WRITER_ID"] = sessionStorage["id"];
					params["OWNER_ID"] = sessionStorage["id"];
					params["OWNER_DEPT_ID"] = sessionStorage["deptid"];
				}
				// 일정삭제
				else if(apiCode == "schdelete") {
					params["target"] = "cal";
					params["todo"] = "delete";
				}
				// 일정검색
				else if(apiCode == "schsearch") {
					params["target"] = "cal";
					params["todo"] = "search";
				}
				return params;
			},
			tmanager: function(apiCode, params){
				// 일정 목록
				if(apiCode == "schlist") {
					params["method"] = "getHsEventList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "event_openapi_list_data";
					params["EVENT_EQUIP_PAGE"] = "event_page";
				}
				// 일정 카운트
				else if(apiCode == "schcount") {
					params["method"] = "getHsEventCountList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["EVENT_EQUIP_PAGE"] = "event_page";
				}
				// 설비 예약 목록
				else if(apiCode == "equiplist") {
					params["method"] = "getHsEventList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "event_openapi_list_data";
					params["EVENT_EQUIP_PAGE"] = "equipment_page";
				}
				// 설비 예약 카운트
				else if(apiCode == "equipcount") {
					params["method"] = "getHsEventCountList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["EVENT_EQUIP_PAGE"] = "equipment_page";
				}
				// 달력 목록
				else if(apiCode == "callist") {
					params["method"] = "getHsCalendarList";
					params["USERID"] = sessionStorage["id"];
					params["DEPTID"] = sessionStorage["deptid"];
					params["VIEW"] = "calendar_openapi_getlist";
				}
				// 설비 목록
				else if(apiCode == "schequiplist") {
					params["method"] = "getEquipmentList";
					params["USERID"] = sessionStorage["id"];
					params["DEPTID"] = sessionStorage["deptid"];
					params["VIEW"] = "equipment_openapi_list_data";
				}
				// 할일 목록
				else if(apiCode == "todolist") {
					params["method"] = "getHsTodoList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "todo_openapi_list_data";
					params["IS_MAIN_LIST"] = "true";
				}
				// 일정 상세보기
				else if(apiCode == "schdetail") {
					params["method"] = "getHsEvent";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "event_openapi_data";
				}
				// 설비 상세보기
				else if(apiCode == "equipdetail") {
					params["method"] = "getHsEvent";
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "event_openapi_data";
				}
				// 할일 상세보기
				else if(apiCode == "tododetail") {
					params["method"] = "getHsTodo";
					params["VIEW"] = "todo_openapi_data";			
				}
				// 일정 추가
				else if(apiCode == "schadd") {
					params["USER_ID"] = sessionStorage["id"];
					params["USER_NM"] = sessionStorage["uname"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["ORG_USER_ID"] = sessionStorage["id"];
					params["ORG_USER_NM"] = sessionStorage["uname"];
					params["CALENDAR_MNGR"] = "u" + sessionStorage["id"];
					params["ALARM_NOTI_TARGET_IDS"] = "u" + sessionStorage["id"];
					params["VIEW"] = "event_openapi_data";
				}
				// 예약된 장비인지 체크
				else if(apiCode == "checkequip") {
					params["method"] = "getHsEquipmentList";
					params["VIEW"] = "equipment_openapi_list_data";
				}
				// 일정 삭제
				else if(apiCode == "schdelete") {
					params["method"] = "delete";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
				}
				// 할일 추가
				else if(apiCode == "todoadd") {
					params["USER_ID"] = sessionStorage["id"];
					params["USER_NM"] = sessionStorage["uname"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["ORG_USER_ID"] = sessionStorage["id"];
					params["ORG_USER_NM"] = sessionStorage["uname"];
					params["CALENDAR_MNGR"] = "u" + sessionStorage["id"];
					params["ALARM_NOTI_TARGET_IDS"] = "u" + sessionStorage["id"];
					params["VIEW"] = "todo_openapi_data";
				}
				// 할일 삭제
				else if(apiCode == "tododelete") {
					params["method"] = "delete";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
				}
				// 일정&할일 검색
				else if(apiCode == "schsearch") {
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["OWNER_ID"] = sessionStorage["id"];
					params["VIEW"] = "search_openapi_data";
				}
				return params;
			},
			tmanager2: function(apiCode, params){
				// 일정 목록
				if(apiCode == "schlist") {
					params["method"] = "getHsEventList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "event_openapi_list_data";
					params["EVENT_EQUIP_PAGE"] = "event_page";
				}
				// 일정 카운트
				else if(apiCode == "schcount") {
					params["method"] = "getHsEventCountList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["EVENT_EQUIP_PAGE"] = "event_page";
				}
				// 특정달력 일정 목록
				if(apiCode == "schselectedlist") {
					params["method"] = "getSelectedEventList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "event_openapi_list_data";
					params["EVENT_EQUIP_PAGE"] = "event_page";
				}
				// 특정달력 일정 카운트
				else if(apiCode == "schselectedcount") {
					params["method"] = "getSelectedEventCountList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["EVENT_EQUIP_PAGE"] = "event_page";
				}				
				// 설비 예약 목록
				else if(apiCode == "equiplist") {
					params["method"] = "getHsEventList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "event_openapi_list_data";
					params["EVENT_EQUIP_PAGE"] = "equipment_page";
				}
				// 설비 예약 카운트
				else if(apiCode == "equipcount") {
					params["method"] = "getHsEventCountList";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["EVENT_EQUIP_PAGE"] = "equipment_page";
				}
				// 달력 목록
				else if(apiCode == "callist") {
					params["method"] = "getHsCalendarList";
					params["USERID"] = sessionStorage["id"];
					params["DEPTID"] = sessionStorage["deptid"];
					params["VIEW"] = "calendar_openapi_getlist";
				}
				// 설비 목록
				else if(apiCode == "schequiplist") {
					params["method"] = "getEquipmentList";
					params["USERID"] = sessionStorage["id"];
					params["DEPTID"] = sessionStorage["deptid"];
					params["VIEW"] = "equipment_openapi_list_data";
				}
				// 설비그룹 목록
				else if(apiCode == "schequipgrouplist") {
					params["method"] = "getEquipmentGroupList";
					params["USERID"] = sessionStorage["id"];
					params["DEPTID"] = sessionStorage["deptid"];
					params["VIEW"] = "calendargroup_equip_openapi_getlist";
				}
				// 할일 목록
				else if(apiCode == "todolist") {
					params["method"] = "list";
					params["owner_id"] = sessionStorage["id"];
				}
				// 일정 상세보기
				else if(apiCode == "schdetail") {
					params["method"] = "getHsEvent";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "event_openapi_data";
				}
				// 설비 상세보기
				else if(apiCode == "equipdetail") {
					params["method"] = "getHsEvent";
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["VIEW"] = "event_openapi_data";
				}
				// 일정공유자 목록
				else if(apiCode == "attendeelist") {
					params["method"] = "getAttendeeList";
					params["VIEW"] = "attendee_openapi_list_data";
				}				
				// 할일 상세보기
				else if(apiCode == "tododetail") {
					params["method"] = "item";
					params["owner_id"] = sessionStorage["id"];
				}
				// 일정 추가
				else if(apiCode == "schadd") {
					params["USER_ID"] = sessionStorage["id"];
					params["USER_NM"] = sessionStorage["uname"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["ORG_USER_ID"] = sessionStorage["id"];
// params["ORG_USER_NM"] = sessionStorage["uname"];
// params["CALENDAR_MNGR"] = "u" + sessionStorage["id"];
// params["ALARM_NOTI_TARGET_IDS"] = "u" + sessionStorage["id"];
					params["VIEW"] = "event_openapi_update_data";
				}
				// 예약된 장비인지 체크
				else if(apiCode == "checkequip") {
					params["USER_ID"] = sessionStorage["id"];
					params["method"] = "getHsEquipmentList";
					params["VIEW"] = "equipment_openapi_list_data";
				}
				// 일정 삭제
				else if(apiCode == "schdelete") {
					params["method"] = "delete";
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
				}
				// 할일 추가
				else if(apiCode == "todoadd") {
					params["owner_id"] = sessionStorage["id"];
				}
				// 할일 삭제
				else if(apiCode == "tododelete") {
					params["method"] = "delete";
					params["owner_id"] = sessionStorage["id"];
				}
				// 일정&할일 검색
				else if(apiCode == "schsearch") {
					params["USER_ID"] = sessionStorage["id"];
					params["DEPT_ID"] = sessionStorage["deptid"];
					params["OWNER_ID"] = sessionStorage["id"];
					params["VIEW"] = "search_openapi_data";
				}
				// 일정 config
				else if(apiCode == "schconfig") {
					params["method"] = "getConfig";
					params["VIEW"] = "event_openapi_config";
				}	
				// 일정 카테고리 목록 조회
				else if(apiCode == "schcategorylist") {
					params["method"] = "getCategoryList";
					params["VIEW"] = "event_category_xml_list";
				}				
				return params;
			}
			
		},
		// 주소록 : 6,7과 8로 구분
		contact:{
			contact1: function(apiCode, params){
				params["target"] = "contact";
				
				if (apiCode == "personal" || apiCode == "search_user") {
					params["type"] = "user";
				}
				if (apiCode == "department" || apiCode == "search_dept") {
					params["type"] = "dept";
				}
				if (apiCode == "group" || apiCode == "search_group") {
					params["type"] = "group";
				}
								
				/**
				 * 4.1.3 개별 주소 조회 - "4.주소록(6.7.5.9)_OPENAPI Page : 7"
				 * /jsp/openapi/OpenApi.jsp?target=contact&todo=display&id=000000233&K=001000107
				 */
				if (apiCode == "details") {
					params["todo"] = "display";
				}
				
				if (apiCode == "search_user") {
					params["type"] = "user";
				}
				
				return params;
			},
			contact2: function(apiCode, params){
				if (apiCode == "personal" || apiCode == "search_user") {
					params["type"] = "user";
				}
				if (apiCode == "department" || apiCode == "search_dept") {
					params["type"] = "dept";
				}
				if (apiCode == "group" || apiCode == "search_group") {
					params["type"] = "group";
				}
				
				/**
				 * 1.1.5 연락처 열람 - "주소록(8.0)_OPENAPI Page : 5"
				 * /contact/openapi.do?act=contact&contact_id=0000002ge&K=001000107
				 */
				if (apiCode == "details") {
					params["act"] = "contact";
					params["contact_id"] = params["id"];
					
					delete params["id"];
				}
				
				/**
				 * 1.1.6 주소록 그룹 목록 - "주소록(8.0)_OPENAPI Page : 6"
				 * /contact/openapi.do?act=group_list&owner_id=001000107&K=001000107
				 */
				if (apiCode == "grouplist") {
					params["act"] = "group_list";
					params["owner_id"] = sessionStorage["id"];
					params["selected_page"] = params["PNO"];
					
					delete params["PNO"];
				}
							
				return params;
			}
		},
		
		// -------- 6,7,8버전 동일 ----------
		// 게시판
		board: function(apiCode, params) {
			/**
			 * 2.1.3~4 게시물 읽기 - "2.게시판(6.7.5.9_기준)_OPENAPI Page : 8" 상단 게시물 양식정보
			 * 테이블 포함된 본문 :
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=message&todo=view&BMID=0000004h6&BRDID=000001000&K=001000107
			 * 상단 게시물 양식정보 제외 후 본문만 : 양식정보 포함 URI + "&onlybody=true"
			 */
			if (apiCode == "details") {
				params["target"] = "bbs";
				params["acton"] = "message";
				params["todo"] = "view";
				
				if (GW_OpenAPI.board_onlybody == "true"){
					params["onlybody"] = "true";
				}
			}
			
			/**
			 * 2.1.5 게시물 쓰기 - "2.게시판(6.7.5.9_기준)_OPENAPI Page : 12" 해당 문서 Form
			 * Sample 참조
			 */
			if (apiCode == "writebbs") {
				params["acton"] = "message";
				params["todo"] = "write";
				params["target"] = "bbs";
				
			}
			
			/**
			 * 2.1.7 게시물 삭제 - "2.게시판(6.7.5.9_기준)_OPENAPI Page : 18"
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=message&todo=delete&BMID=0000004ph&K=001000107
			 */
			if (apiCode == "deletebbs") {
				params["target"] = "bbs";
				params["acton"] = "message";
				params["todo"] = "delete";
			}
			
			/**
			 * 2.1.8 최근 게시물 조회 - "2.게시판(6.7.5.9_기준)_OPENAPI Page : 19"
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=message&todo=display&type=new&PNO=1&K=001000107
			 */
			if (apiCode == "recentlist") {
				params["target"] = "bbs";
				params["acton"] = "message";
				params["todo"] = "display";
				params["type"] = "new";
			}
			
			/**
			 * 2.1.10 게시물 의견 조회 - "2.게시판(6.7.5.9_기준)_OPENAPI Page : 20"
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=comment&todo=view&BMID=0000000xr&K=00PwDDEa
			 */
			if (apiCode == "comment") {
				params["target"] = "bbs";
				params["acton"] = "comment";
				params["todo"] = "view";
			}
			
			/**
			 * 2.4.4 게시물 암호 확인 - "2.게시판(8.2_기준)_OPENAPI Page : 44"
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=message&todo=chkpwd&PID=0000004h6&PWD=test&K=000q22Gv81
			 */
			if (apiCode == "chkpwdBoard") {
				params["target"] = "bbs";
				params["acton"] = "message";
				params["todo"] = "chkpwd";
			}
			
			/**
			 * 2.4.13 게시물 의견 암호 확인 - "2.게시판(8.2_기준)_OPENAPI Page : 55"
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=comment&todo=chkpwd&PID=000000ldl&PWD=test&K=00PAq2qNA
			 */
			if (apiCode == "chkpwdBoardComment") {
				params["target"] = "bbs";
				params["acton"] = "comment";
				params["todo"] = "chkpwd";
			}			
			
			/**
			 * 2.4.12 게시물 의견 추가 - "2.게시판(8.2_기준)_OPENAPI Page : 53"
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=comment&todo=add&BMID=0000000xr&BRDID=000001000&parcid=000000000&COMMENT=HI&K=00PwDDEa
			 */
			if (apiCode == "commentAdd") {
				params["target"] = "bbs";
				params["acton"] = "comment";
				params["todo"] = "add";
			}
			
			/**
			 * 2.4.13 게시물 의견 변경 - "2.게시판(8.2_기준)_OPENAPI Page : 54"
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=comment&todo=mod&BMID=0000000xr&BRDID=000001000&CID=000000000&COMMENT=HI&K=00PwDDEa
			 */
			if (apiCode == "commentModify") {
				params["target"] = "bbs";
				params["acton"] = "comment";
				params["todo"] = "mod";
			}
			
			/**
			 * 2.4.14 게시물 의견 삭제 - "2.게시판(8.2_기준)_OPENAPI Page : 54"
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=comment&todo=del&BMID=0000000xr&BRDID=000001000&CID=000000000&&K=00PwDDEa
			 */
			if (apiCode == "commentDelete") {
				params["target"] = "bbs";
				params["acton"] = "comment";
				params["todo"] = "del";
			}			
			
			/**
			 * 2.4.15 게시판별 머리글 조회 - "2.게시판(8.2_기준)_OPENAPI Page : 55"
			 * /jsp/openapi/OpenApi.jsp?target=bbs&acton=message&todo=prefaceinfo&BRDID=000001000&K=00PwDDEa
			 */
			if (apiCode == "prefaceinfo") {
				params["target"] = "bbs";
				params["acton"] = "message";
				params["todo"] = "prefaceinfo";
			}
			
			return params;
		},
		// 조직도
		org: function(apiCode, params) {
		
			/**
			 * 3.1.1 부서 트리 - "3.조직도(6.7.5.9_기준)_OPENAPI Page : 4"
			 * /jsp/openapi/OpenApi.jsp?target=org&acton=dept&todo=list&scope=all&base=000000000&K=001000107
			 */
			if (apiCode == "depttree") {
				params["target"] = "org";
				params["acton"] = "dept";
				params["todo"] = "list";
				params["scope"] = "all";
				
				if (params["base"] == undefined)
					params["base"] = sessionStorage["deptid"];
			}
			if (apiCode == "deptpath") {
				params["target"] = "org";
				params["acton"] = "dept";
				params["todo"] = "ascendat";
				
				if (params["base"] == undefined)
					params["base"] = sessionStorage["deptid"];
			}
			/**
			 * 3.1.2 사용자 목록 - "3.조직도(6.7.5.9_기준)_OPENAPI Page : 5"
			 * /jsp/openapi/OpenApi.jsp?target=org&acton=user&todo=list&base=000000104&K=00xwn3BK13
			 */
			if (apiCode == "userlist") {
				params["target"] = "org";
				params["acton"] = "user";
				params["todo"] = "list";
				
				if (params["base"] == undefined)
					params["base"] = sessionStorage["deptid"];
			}
		
			/**
			 * 3.1.5 부서 검색 - "3.조직도(6.7.5.9_기준)_OPENAPI Page : 9"
			 * /jsp/openapi/OpenApi.jsp?target=org&acton=dept&todo=search&name=핸디&K=00tHv2BZu3
			 */
			if (apiCode == "searchdept") {
				params["target"] = "org";
				params["acton"] = "dept";
				params["todo"] = "search";
			}
			
			/**
			 * 3.1.6 사용자 검색 - "3.조직도(6.7.5.9_기준)_OPENAPI Page : 10"
			 * /jsp/openapi/OpenApi.jsp?target=org&acton=user&todo=search&orderby=asc&searchCond=admin&searchField=rs.name&K=00gjn38jj1
			 * 참고 사항
			 */
			if (apiCode == "searchuser") {
				params["target"] = "org";
				params["acton"] = "user";
				params["todo"] = "search";
				params["orderby"] = "asc";
			}
			
			/**
			 * 3.1.8 로그인사용자의 초기화면 카운트 정보 - "3.조직도(6.7.5.9_기준)_OPENAPI Page : 13"
			 * /jsp/openapi/OpenApi.jsp?target=org&acton=init&todo=count&K=00tHv2BZu3
			 */
			if (apiCode == "countinfo") {
				params["target"] = "org";
				params["acton"] = "init";
				params["todo"] = "count";
			}
			
			/**
			 * 3.1.9 로그인사용자의 상위부서 정보 - "3.조직도(6.7.5.9_기준)_OPENAPI Page : 14"
			 * /jsp/openapi/OpenApi.jsp?target=org&acton=dept&todo=ascendat&K=00tHv2BZu3
			 */
			if (apiCode == "parentdeptlist") {
				params["target"] = "org";
				params["acton"] = "dept";
				params["todo"] = "ascendat";
			}
			
			/**
			 * 3.1.10 로그인사용자의 그룹 목록 - "3.조직도(6.7.5.9_기준)_OPENAPI Page : 15"
			 * /jsp/openapi/OpenApi.jsp?target=env&acton=group&todo=list&app=M&K=00xwn3BK13
			 */
			if (apiCode == "grouplist") {
				params["target"] = "org";
				params["acton"] = "group";
				params["todo"] = "list";
				params["app"] = "M";
			}
			
			/**
			 * 3.1.11 겸직자 목록 - "3.조직도(6.7.5.9_기준)_OPENAPI Page : 16"
			 * /jsp/openapi/OpenApi.jsp?target=org&acton=user&todo=otherofficer&K=00xwn3BK13
			 */
			if (apiCode == "officerlist") {
				params["target"] = "org";
				params["acton"] = "user";
				params["todo"] = "otherofficer";
			}
			
			/**
			 * 로그인 암호 확인
			 * /jsp/openapi/OpenApi.jsp?acton=check&target=org&todo=login&pwd=userpwd&key=KeyValue
			 */
			if (apiCode == "checkloginpasswd") {
				params["acton"] = "check";
				params["target"] = "org";
				params["todo"] = "login";
			}
			
			/**
			 * 결재 암호 확인
			 * /jsp/openapi/OpenApi.jsp?acton=check&target=org&todo=sanc&pwd=userpwd&key=KeyValue
			 */
			if (apiCode == "checksancpasswd") {
				params["acton"] = "check";
				params["target"] = "org";
				params["todo"] = "sanc";
			}
			
			return params;
		},
		// 환경설정
		settings: {
			settings1: function(apiCode, params){
				/**
				 * 부재설정 상태 조회
				 */
				if (apiCode == "getabsence") {
					params["action"] = "abs";
					params["target"] = "env";
					params["todo"] = "display";
				}
				/**
				 * 개인정보 조회
				 * /jsp/openapi/OpenApi.jsp?target=env&acton=user&todo=display&id=000000104&K=00xwn3BK13
				 */
				else if (apiCode == "getuserinfo") {
					params["action"] = "user";
					params["target"] = "env";
					params["todo"] = "display";

					if (params["id"] == undefined)
						params["id"] = sessionStorage["id"];
				}
				/**
				 * 부재 설정 / 해제
				 */
				else if (apiCode == "setabsence") {
					params["action"] = "abs";
					params["target"] = "env";
					params["todo"] = "set";
				}
				/**
				 * 개인정보 변경
				 */
				else if (apiCode == "setuserinfo") {
					params["action"] = "user";
					params["target"] = "env";
					params["todo"] = "modinfo";
					params["SLET"] = "openapi.EnvApi.java";
				}
				/**
				 * 암호 변경
				 */
				else if (apiCode == "setpassword") {
					params["action"] = "user";
					params["target"] = "env";
					params["todo"] = "modpwd";
				}
				
				/**
				 * 메일 그룹 목록
				 * /jsp/openapi/OpenApi.jsp?target=env&action=group&todo=list&app=M&id=001002053
				 */
				else if (apiCode == "mailgroup") {
					params["action"] = "group";
					params["target"] = "env";
					params["todo"] = "list";
					params["app"] = "M";
					params["id"] = sessionStorage["id"];
				}
				
				return params;
			},
			settings2: function(apiCode, params){
				/**
				 * 부재설정 상태 조회 (GW 8.2.2이상 전용)
				 */
				if (apiCode == "getabsence") {
					params["action"] = "abs";
					params["target"] = "env";
					params["todo"] = "info";
				}
				/**
				 * 부재목록 (GW 8.2.2이상 전용)
				 */
				else if (apiCode == "absencelist") {
					params["action"] = "abs";
					params["target"] = "env";
					params["todo"] = "list";
				}
				/**
				 * 부재추가 & 수정 (GW 8.2.2이상 전용)
				 */
				else if (apiCode == "absenceadd") {
					params["action"] = "abs";
					params["target"] = "env";
					params["todo"] = "change";
				}
				/**
				 * 부재삭제 (GW 8.2.2이상 전용)
				 */
				else if (apiCode == "absencedelete") {
					params["action"] = "abs";
					params["target"] = "env";
					params["todo"] = "delete";
				}
				/**
				 * 개인정보 조회
				 * /jsp/openapi/OpenApi.jsp?target=env&acton=user&todo=display&id=000000104&K=00xwn3BK13
				 */
				else if (apiCode == "getuserinfo") {
					params["action"] = "user";
					params["target"] = "env";
					params["todo"] = "display";

					if (params["id"] == undefined)
						params["id"] = sessionStorage["id"];
				}
				/**
				 * 부재 설정 / 해제
				 */
				else if (apiCode == "setabsence") {
					params["action"] = "abs";
					params["target"] = "env";
					params["todo"] = "set";
				}
				/**
				 * 개인정보 변경
				 */
				else if (apiCode == "setuserinfo") {
					params["action"] = "user";
					params["target"] = "env";
					params["todo"] = "modinfo";
					params["SLET"] = "openapi.EnvApi.java";
				}
				/**
				 * 암호 변경
				 */
				else if (apiCode == "setpassword") {
					params["action"] = "user";
					params["target"] = "env";
					params["todo"] = "modpwd";
				}
				
				/**
				 * 메일 그룹 목록
				 * /jsp/openapi/OpenApi.jsp?target=env&action=group&todo=list&app=M&id=001002053
				 */
				else if (apiCode == "mailgroup") {
					params["action"] = "group";
					params["target"] = "env";
					params["todo"] = "list";
					params["app"] = "M";
					params["id"] = sessionStorage["id"];
				}
				
				/**
				 * 결재설정
				 * /jsp/openapi/OpenApi.jsp?target=env&action=apprprofile&todo=display&id=001002053
				 */
				else if (apiCode == "apprprofile") {
					params["action"] = "apprprofile";
					params["target"] = "env";
					params["todo"] = "display";
					params["id"] = sessionStorage["id"];
				}				
				
				return params;
			}
		}			
	},
	
	getOpenAPIPathAndType: function(category, apiCode){
		var apiBase;
		switch(category){
			case "mail" :
				apiBase = GW_OpenAPI.apiMap[category][GW_OpenAPI.mail_type]; 
				break;
			case "sign" :
				apiBase = GW_OpenAPI.apiMap[category][GW_OpenAPI.sign_type];
				break;
			case "contact" :
				apiBase = GW_OpenAPI.apiMap[category][GW_OpenAPI.contact_type];
				break;
			case "schedule" :
				apiBase = GW_OpenAPI.apiMap[category][GW_OpenAPI.schedule_type];
				break;
			case "settings" :
				apiBase = GW_OpenAPI.apiMap[category][GW_OpenAPI.settings_type];
				break;
			case "board" :
			case "org" :
			default:
				apiBase = GW_OpenAPI.apiMap[category];	
		}
		
		var requestType = "get";
		var openapipath = "";
		
		
		if (category == "mail") {
			if (apiCode == "recvlist" || apiCode == "sendlist" || apiCode == "deletelist" 
				|| apiCode == "templist" || apiCode == "personallist" || apiCode == "personalbox" 
				|| apiCode == "deletemail")  
			{
				requestType = "post";
			} 
		}
		else if (category == "board") {
			if (apiCode == "recentlist" || apiCode == "list" || apiCode == "folder" || apiCode == "favfolder" 
				|| apiCode == "chkpwdBoard" || apiCode == "chkpwdBoardComment"
				|| apiCode == "commentAdd" || apiCode == "commentModify" || apiCode == "commentDelete") {	
				requestType = "post";
			}
		}
		else if (category == "contact") {
			if (apiCode == "personal" || apiCode == "department" || apiCode == "group" || apiCode == "grouplist") 
			{
				requestType = "post";
			} 
			else if (apiCode == "search_user"
				|| apiCode == "search_dept"
				|| apiCode == "search_group") 
			{
				requestType = "post";
			}
		}
		else if (category == "sign") {
			if (apiCode == "waitlist" || apiCode == "gongramwaitlist" || apiCode == "gongramcompletelist" || apiCode == "nowlist" || apiCode == "receiptwaitlist" || apiCode == "userprocessedlist"
				|| apiCode == "mycompletelist" || apiCode == "completelist" || apiCode == "dispatchlist") 
			{
				requestType = "post";
			} 
		}
		
		if (apiBase[apiCode] == undefined) {
			openapipath = apiBase.uri;
		}
		else {
			if (apiBase[apiCode].type != undefined)
				requestType = apiBase[apiCode].type;
			
			if (apiBase[apiCode].uri != undefined)
				openapipath = apiBase[apiCode].uri;
			else
				openapipath = apiBase.uri;
		}
		
		return {openapipath: openapipath, type: requestType};
	},
	apiMap:{
		// 세션관련: 로그인, 로그아웃
		session:{
			login:{
				uri:"/rest/auth/login",
				type:"post"
			},
			logout:{				
			}
		},
		// 공통: ipcheckrange
		common:{
			ipcheckrange:{
				uri:"/rest/common/ipcheckrange",
				type:"get"
			},
			getlicenseinfo:{
				uri:"/rest/common/getlicenseinfo",
				type:"get"
			}
		},		
		// 메일 : WEB과 DB로 구분
		mail: {			
			web: {
				uri:"/wma/openapi.do", // 메일 Web 디폴트 uri
				
				personallist: {
					type: "post"	// 개인 편지함의 boxid 한글 Value 사용
				},
				attach:{
					type: "post"
				},
				write:{
					uri: "/wma/wmasm.do",
					type: "post"	// 편지 발송 Post 방식
				},
				details: {
					type: "post" 	// 개인 편지함의 boxid 한글 Value 사용
				},
				deletemail: {
					type: "post" 	// 개인 편지함의 boxid 한글 Value 사용
				},
				resolveuser: {
					uri:"/rest/openapi/mail/resolveuser",
					type: "post"
				},
				mailconfig: {
					type: "get"
				}				
			},
			db:{
				uri:"/jsp/openapi/OpenApi.jsp", // 메일 DB 디폴트 uri

				personallist: {
					type: "post"	// 개인 편지함의 boxid 한글 Value 사용
				},
				attach:{
					uri:"/jsp/AttView.jsp?APP=1",
					type: "post"
				},
				write:{
					uri: "/jsp/openapi/OpenApi.jsp?target=mail",
					type: "post"	// 편지 발송 Post 방식
				}
			}
		},
		// 결재
		sign:{
			hwp6:{
				uri:"/jsp/openapi/OpenApi.jsp",
				apprApprove: {
					type: "post"
				}
			},
			hwp8:{
				uri:"/jsp/openapi/OpenApi.jsp",
				apprApprove: {
					type: "post"
				},
				apprApproveHtml: {
					type: "post"
				},
				withdraw: {
					type: "get"
				},
				cancel: {
					uri:"/bms/cz/cb/prc/openApiRecallApproval.act",
					type: "get"
				}				
			},
			tagfree:{
				uri:"/jsp/openapi/OpenApi.jsp",
				apprApprove: {
					type: "post"
				},
				apprApproveHtml: {
					type: "post"
				}				
			},
			html:{
				uri:"/jsp/openapi/OpenApi.jsp",
				apprApprove: {
					type: "post"
				},
				apprApproveHtml: {
					type: "post"
				},				
				withdraw: {
					type: "get"
				},
				cancel: {
					uri:"/bms/cz/cb/prc/openApiRecallApproval.act",
					type: "get"
				}				
			}
		},
		// 일정관리: t-manager 인지의 여부로 구분
		schedule:{
			original:{
				schlist:{
					uri: "/jsp/openapi/OpenApi.jsp",
					type: "get"
				},
				schdetail:{
					uri: "/jsp/openapi/OpenApi.jsp",
					type: "get"
				},
				schadd:{
					uri: "/jsp/openapi/OpenApi.jsp",
					type: "post"
				},
				schdelete:{
					uri: "/jsp/openapi/OpenApi.jsp",
					type: "get"
				},
				schsearch:{
					uri: "/jsp/openapi/OpenApi.jsp",
					type: "post"
				}
			},
			tmanager:{
				/**
				 * 5.1.1 일정 목록 - "일정(7.3.1)_OPENAPI Page : 4"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=event_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241&VIEW=event_openapi_list_data
				 */
				schlist:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.1 일정 목록 - "설비일정(7.3.1)_OPENAPI Page : 4"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=event_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241&VIEW=event_openapi_list_data
				 */
				equiplist:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.2 일정 카운트 - "일정(7.3.x)_OPENAPI Page : x"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=event_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241
				 */
				schcount:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.2 설비 카운트 - "일정(7.3.x)_OPENAPI Page : x"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=equipment_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241
				 */
				equipcount:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.3 일정 상세 보기 - "일정(7.3.1)_OPENAPI Page : 10"
				 * /hscalendar/jsp/event.do?method=getHsEvent&K=00Bfq3rc&USER_ID=001004702&EVENT_ID=00000000000000002461&DEPT_ID=000010117&VIEW=event_openapi_data
				 */
				schdetail:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.8 설비 목록 - "일정(7.3.1)_OPENAPI Page : 24"
				 * /hscalendar/equipment.do?method=getEquipmentList&USER_ID=001004702&K=00Bfq3rc&DEPT_ID=000010117&VIEW=equipment_openapi_list_data
				 */
				schequiplist:{
					uri: "/hscalendar/equipment.do",
					type: "get"
				},
				/**
				 * 5.1.3 설비 상세 보기
				 */
				equipdetail:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.9 할일 목록 - "일정(7.3.1)_OPENAPI Page : 25"
				 * /hscalendar/todo.do?method=getHsTodoList&USER_ID=001004702&K=00Bfq3rc&DEPT_ID=000010117&VIEW=todo_openapi_list_data&START_DT=2009.04.08
				 * 12:00:00&END_DT=2009.04.08
				 * 13:00:00&COMPANY_CALENDAR_IDS=00000000000000001013&TEAM_CALENDAR_IDS=00000000000000001042&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241
				 */
				todolist:{
					uri: "/hscalendar/todo.do",
					type: "get"
				},
				/**
				 * 5.1.3 할일 상세 보기 - "일정(7.3.1)_OPENAPI"
				 * /hscalendar/jsp/event.do?method=getHsEvent&K=00Bfq3rc&USER_ID=001004702&EVENT_ID=00000000000000002461&DEPT_ID=000010117&VIEW=event_openapi_data
				 */
				tododetail:{
					uri: "/hscalendar/todo.do",
					type: "get"
				},
				/**
				 * 5.1.4 일정 등록 - "일정((7.3.1)_OPENAPI Page : 14"
				 * /hscalendar/jsp/event.do?method=add&K=00Bfq3rc&USER_ID=001004702&USER_NM=김미정&DEPT_ID=000010117&ORG_USER_ID=001004702&ORG_USER_NM=김미정&EVENT_ID=&TITLE=12345&START_DT=2009.04.08
				 * 12:00:00&END_DT=2009.04.08
				 * 13:00:00&CALENDAR_ID=00000000000000001094&CALENDAR_TP=4&CALENDAR_DEPT=&CALENDAR_MNGR=u001004702&CALENDAR_OWNER=&CALENDAR_GRANTED=&CATEGORY_ID=&ANNIVERSARY_FG=0&TAG=&EQUIPMENT=&SUMMARY=12345&RECUR_ID=&RECUR_TP=0&RECUR_RULE=&RECUR_CYCLE=1&ATTENDEE_REQUIRE_RESPONSE=&ATTENDEE_REQUIRE=&ATTENDEE_VIEWER_RESPONSE=&ATTENDEE_VIEWER=&ALARM_TITLE=12345&ALARM_MSG=&ALARM_SET=0&ALARM_NOTI_BEFORE_VALUE=&ALARM_NOTI_AFTER_VALUE=&ALARM_MAIL_BEFORE_VALUE=&ALARM_MAIL_AFTER_VALUE=&ALARM_NOTI_TARGET_IDS=u001004702&REPLY_CNT=0&CHECKLUNAR=false&FILENAME=&FILEFOLDER=&FILESIZE=&VIEW=event_openapi_data
				 * 해당 문서 Form Sample 참조
				 */
				schadd:{
					uri: "/hscalendar/event.do",
					type: "post"
				},
				/**
				 * 예약된 설비와 중복 체크
				 */
				checkequip:{
					uri: "/hscalendar/event.do",
					type: "post"
				},
				/**
				 * 5.1.5 일정 삭제 - "일정(7.3.1)_OPENAPI Page : 22"
				 * /hscalendar/jsp/event.do?method=delete&K=00Bfq3rc&USER_ID=001004702&EVENT_ID=00000000000000002466&DEL_OPT=0&SELECT_DT=2009.04.16
				 */
				schdelete:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.10 할일 입력 - "일정(7.3.1)_OPENAPI Page : 27
				 * /hscalendar/todo.do?method=add 해당 문서 입력 파라메터 참조
				 */
				todoadd:{
					uri: "/hscalendar/todo.do",
					type: "post"
				},
				/**
				 * 5.1.13 할일 삭제 - "일정(7.3.1)_OPENAPI Page : 35"
				 * /hscalendar/todo.do?method=deleteOpenApi 해당 문서 입력 파라메터 참조
				 */
				tododelete:{
					uri: "/hscalendar/todo.do",
					type: "get"
				},
				/**
				 * 5.1.2 일정검색 - "일정(7.3.1)_OPENAPI Page : 7"
				 * /hscalendar/jsp/search.do?method=getSearchList&K=00Bfq3rc&OWNER_ID=001004702&START_DT=2009.04.01&END_DT=2009.04.30&TITLE=111&APPL_CD=000300&SELECT_OPTION=3&COMPANY_CALENDAR_IDS=00000000000000001013&TEAM_CALENDAR_IDS=00000000000000001042&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241&EQUIPMENT_IDS=00000000000000001033;00000000000000001271;00000000000000001272&CATEGORY_IDS=&TODO_STATUS=0&TODO_WEIGHT=0&VIEW=search_openapi_data
				 */
				schsearch:{
					uri: "/hscalendar/search.do",
					type: "post"
				},
				/**
				 * 5.1.16 달력 목록 - "일정(7.3.1)_OPENAPI Page : 40"
				 * /hscalendar/calendar.do?method=getHsCalendarList&USER_ID=001004702&K=00Bfq3rc&VIEW=calendar_openapi_getlist
				 */
				callist:{
					uri: "/hscalendar/calendar.do",
					type: "get"
				}
			},
			tmanager2:{
				/**
				 * 5.1.1 일정 목록 - "일정(7.3.1)_OPENAPI Page : 4"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=event_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241&VIEW=event_openapi_list_data
				 */
				schlist:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.1 일정 목록 - "일정(7.3.1)_OPENAPI Page : 4"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=event_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241&VIEW=event_openapi_list_data
				 */
				schselectedlist:{
					uri: "/hscalendar/event.do",
					type: "get"
				},				
				/**
				 * 5.1.1 일정 목록 - "설비일정(7.3.1)_OPENAPI Page : 4"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=event_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241&VIEW=event_openapi_list_data
				 */
				equiplist:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.2 일정 카운트 - "일정(7.3.x)_OPENAPI Page : x"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=event_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241
				 */
				schcount:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.2 일정 카운트 - "일정(7.3.x)_OPENAPI Page : x"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=event_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241
				 */
				schselectedcount:{
					uri: "/hscalendar/event.do",
					type: "get"
				},				
				/**
				 * 5.1.2 설비 카운트 - "일정(7.3.x)_OPENAPI Page : x"
				 * /hscalendar/jsp/event.do?method=getHsEventList&K=00Bfq3rc&USER_ID=001004702&DEPT_ID=000010117&TARGET_START_DATE=2009.3.29&TARGET_END_DATE=2009.5.2&EVENT_EQUIP_PAGE=equipment_page&EQUIPMENT_IDS=&COMPANY_CALENDAR_IDS=00000000000000001013;&TEAM_CALENDAR_IDS=00000000000000001042;&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241
				 */
				equipcount:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.3 일정 상세 보기 - "일정(7.3.1)_OPENAPI Page : 10"
				 * /hscalendar/jsp/event.do?method=getHsEvent&K=00Bfq3rc&USER_ID=001004702&EVENT_ID=00000000000000002461&DEPT_ID=000010117&VIEW=event_openapi_data
				 */
				schdetail:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.8 설비 목록 - "일정(7.3.1)_OPENAPI Page : 24"
				 * /hscalendar/equipment.do?method=getEquipmentList&USER_ID=001004702&K=00Bfq3rc&DEPT_ID=000010117&VIEW=equipment_openapi_list_data
				 */
				schequiplist:{
					uri: "/hscalendar/equipment.do",
					type: "get"
				},
				/**
				 * /** 5.1.8 설비 목록 - "일정(7.3.1)_OPENAPI Page : 24"
				 * /hscalendar/equipment.do?method=getEquipmentList&USER_ID=001004702&K=00Bfq3rc&DEPT_ID=000010117&VIEW=equipment_openapi_list_data
				 */
				schequipgrouplist:{
					uri: "/hscalendar/calendarGroup.do",
					type: "get"
				},
				/**
				 * 5.1.3 설비 상세 보기
				 */
				equipdetail:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.4 일정 공유자 목록
				 */
				attendeelist:{
					uri: "/hscalendar/attendee.do",
					type: "get"
				},				
				/**
				 * 5.1.9 할일 목록 - "일정(7.3.1)_OPENAPI Page : 25"
				 * /hscalendar/todo.do?method=getHsTodoList&USER_ID=001004702&K=00Bfq3rc&DEPT_ID=000010117&VIEW=todo_openapi_list_data&START_DT=2009.04.08
				 * 12:00:00&END_DT=2009.04.08
				 * 13:00:00&COMPANY_CALENDAR_IDS=00000000000000001013&TEAM_CALENDAR_IDS=00000000000000001042&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241
				 */
				todolist:{
					uri: "/workmanager/openapi.wm",
					type: "get"
				},
				/**
				 * 5.1.3 할일 상세 보기 - "일정(7.3.1)_OPENAPI"
				 * /hscalendar/jsp/event.do?method=getHsEvent&K=00Bfq3rc&USER_ID=001004702&EVENT_ID=00000000000000002461&DEPT_ID=000010117&VIEW=event_openapi_data
				 */
				tododetail:{
					uri: "/workmanager/openapi.wm",
					type: "get"
				},
				/**
				 * 5.1.4 일정 등록 - "일정((7.3.1)_OPENAPI Page : 14"
				 * /hscalendar/jsp/event.do?method=add&K=00Bfq3rc&USER_ID=001004702&USER_NM=김미정&DEPT_ID=000010117&ORG_USER_ID=001004702&ORG_USER_NM=김미정&EVENT_ID=&TITLE=12345&START_DT=2009.04.08
				 * 12:00:00&END_DT=2009.04.08
				 * 13:00:00&CALENDAR_ID=00000000000000001094&CALENDAR_TP=4&CALENDAR_DEPT=&CALENDAR_MNGR=u001004702&CALENDAR_OWNER=&CALENDAR_GRANTED=&CATEGORY_ID=&ANNIVERSARY_FG=0&TAG=&EQUIPMENT=&SUMMARY=12345&RECUR_ID=&RECUR_TP=0&RECUR_RULE=&RECUR_CYCLE=1&ATTENDEE_REQUIRE_RESPONSE=&ATTENDEE_REQUIRE=&ATTENDEE_VIEWER_RESPONSE=&ATTENDEE_VIEWER=&ALARM_TITLE=12345&ALARM_MSG=&ALARM_SET=0&ALARM_NOTI_BEFORE_VALUE=&ALARM_NOTI_AFTER_VALUE=&ALARM_MAIL_BEFORE_VALUE=&ALARM_MAIL_AFTER_VALUE=&ALARM_NOTI_TARGET_IDS=u001004702&REPLY_CNT=0&CHECKLUNAR=false&FILENAME=&FILEFOLDER=&FILESIZE=&VIEW=event_openapi_data
				 * 해당 문서 Form Sample 참조
				 */
				schadd:{
					uri: "/hscalendar/event.do",
					type: "post"
				},
				/**
				 * 예약된 설비와 중복 체크
				 */
				checkequip:{
					uri: "/hscalendar/event.do",
					type: "post"
				},
				/**
				 * 5.1.5 일정 삭제 - "일정(7.3.1)_OPENAPI Page : 22"
				 * /hscalendar/jsp/event.do?method=delete&K=00Bfq3rc&USER_ID=001004702&EVENT_ID=00000000000000002466&DEL_OPT=0&SELECT_DT=2009.04.16
				 */
				schdelete:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 5.1.10 할일 입력 - "일정(7.3.1)_OPENAPI Page : 27
				 * /hscalendar/todo.do?method=add 해당 문서 입력 파라메터 참조
				 */
				todoadd:{
					uri: "/workmanager/openapi.wm",
					type: "post"
				},
				/**
				 * 5.1.13 할일 삭제 - "일정(7.3.1)_OPENAPI Page : 35"
				 * /hscalendar/todo.do?method=deleteOpenApi 해당 문서 입력 파라메터 참조
				 */
				tododelete:{
					uri: "/workmanager/openapi.wm",
					type: "get"
				},
				/**
				 * 5.1.2 일정검색 - "일정(7.3.1)_OPENAPI Page : 7"
				 * /hscalendar/jsp/search.do?method=getSearchList&K=00Bfq3rc&OWNER_ID=001004702&START_DT=2009.04.01&END_DT=2009.04.30&TITLE=111&APPL_CD=000300&SELECT_OPTION=3&COMPANY_CALENDAR_IDS=00000000000000001013&TEAM_CALENDAR_IDS=00000000000000001042&PRIVATE_CALENDAR_IDS=00000000000000001094;00000000000000001241&EQUIPMENT_IDS=00000000000000001033;00000000000000001271;00000000000000001272&CATEGORY_IDS=&TODO_STATUS=0&TODO_WEIGHT=0&VIEW=search_openapi_data
				 */
				schsearch:{
					uri: "/hscalendar/search.do",
					type: "post"
				},
				/**
				 * 5.1.16 달력 목록 - "일정(7.3.1)_OPENAPI Page : 40"
				 * /hscalendar/calendar.do?method=getHsCalendarList&USER_ID=001004702&K=00Bfq3rc&VIEW=calendar_openapi_getlist
				 */
				callist:{
					uri: "/hscalendar/calendar.do",
					type: "get"
				},
				/**
				 * 5.2.1 일정 config - "일정(8.2.6)_OPENAPI Page : 111"
				 * /hscalendar/event.do?method=getConfig&K=00Bfq3rc&VIEW=event_openapi_config
				 */
				schconfig:{
					uri: "/hscalendar/event.do",
					type: "get"
				},
				/**
				 * 2.5.17 일정 카테고리 목록 조회 - "컴포넌트(8.3)_OPENAPI Page : 111"
				 * /hscalendar/event.do?method=getCategoryList&K=00Bfq3rc&VIEW=event_category_xml_list
				 */
				schcategorylist:{
					uri: "/hscalendar/event.do",
					type: "get"
				}				
			}
		},
		// 주소록 : 6,7과 8로 구분
		contact:{
			contact1:{
				uri: "/jsp/openapi/OpenApi.jsp",
				
				search_user: {
					type: "post"
				},
				search_dept: {
					type: "post"
				},
				search_group: {
					type: "post"
				}
			},
			contact2:{
				uri: "/contact/openapi.do",
				
				search_user: {
					type: "post"
				},
				search_dept: {
					type: "post"
				},
				search_group: {
					type: "post"
				}
			}
		},
		
		// -------- 6,7,8버전 동일 ----------
		// 게시판
		board:{
			uri:"/jsp/openapi/OpenApi.jsp",
			
			writebbs:{
				uri:"/jsp/openapi/OpenApi.jsp?target=bbs",
				type: "post"
			}
		},
		// 조직도
		org:{
			uri:"/jsp/openapi/OpenApi.jsp",
			
			searchdept: {
				type: "post"
			},
			searchuser: {
				type: "post"
			}
		},
		// 환경설정
		settings:{
			settings1:{
				uri:"/jsp/openapi/OpenApi.jsp",
				
				setabsence: {
					type: "post"
				},
				setuserinfo:{
					type: "post",
					uri: "/jsp/openapi/OpenApi.jsp?target=env"
				},
				setpassword: {
					type: "post"
				}
			},
			settings2:{
				uri:"/jsp/openapi/OpenApi.jsp",
				
				absencelist: {
					type: "post"
				},
				absenceadd: {
					type: "post",
				},
				absencedelete: {
					type: "post",
				},
				setuserinfo:{
					type: "post",
					uri: "/jsp/openapi/OpenApi.jsp?target=env"
				},
				setpassword: {
					type: "post"
				}
			}
		}
	}
};var GW_CONTROLLER_BOARD = {
	sortCommentList : [],
	
	sortBoardComment: function(list) {
		GW_CONTROLLER_BOARD.sortCommentList = [];
		for (var i=list.length-1; i>=0; i--) {
			if (list[i].parcid == "000000000") {
				GW_CONTROLLER_BOARD.sortCommentList.push(list[i]);
			}
		}

		for (var i=0; i<list.length; i++) {
			if (list[i].parcid != "000000000") {
				var index = GW_CONTROLLER_BOARD.findParentComment(list[i].parcid);
				GW_CONTROLLER_BOARD.insertSortCommentList(index, list[i]);
			}
		}

		return GW_CONTROLLER_BOARD.sortCommentList;
	},
	
	insertSortCommentList: function(index, comment) {
		if(index >= 0) {
			GW_CONTROLLER_BOARD.sortCommentList.splice(index + 1, 0, comment);
		}
	},
	
	findParentComment: function(parcid) {
		for (var i=0; i<GW_CONTROLLER_BOARD.sortCommentList.length; i++) {
			var comment = GW_CONTROLLER_BOARD.sortCommentList[i];
			if (comment.cid == parcid) {
				return i;
			}
		}
		return -1;
	}
};

// 게시물 목록
function showBoardList(apiCode, brdId) {
	if (brdId != undefined)
		PAGE_CONTROLLER.showPage("board_list", function() {getBoradList(apiCode, 1, brdId);});	
	else
		PAGE_CONTROLLER.showPage("board_list", function() {getBoradList(apiCode, 1);});
}

// 게시판 폴더 목록
function showBoardFolerList(baseFolder, title) {
	if (baseFolder != undefined)
		PAGE_CONTROLLER.showCopyPage("board_folder", baseFolder, function() {getBoradFolderList(baseFolder, title);});
	else
		PAGE_CONTROLLER.showPage("board_folder", function() {getBoradFolderList();});
}

// 즐겨찾기 목록
function showBoardFavList() {
	PAGE_CONTROLLER.showPage("board_folder", function() {getBoardFavList();});
}

// 게시물 읽기 팝업
function showBoardDetailsPopup(link, commentCnt, deptId) {
	showBoardDetails(link, commentCnt, deptId, true);	
}

// 게시물 읽기
function showBoardDetails(link, commentCnt, deptId, isPopup) {
	// 이전 페이지 모두 Clear
	PAGE_CONTROLLER.cleanViewStack();
	
	if(isPopup == true) {
		NAVIBAR_DEF.phone.board_details.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
		NAVIBAR_DEF.pad.board_details.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
	} else {
		NAVIBAR_DEF.phone.board_details.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], ['javascript:goList();', 'javascript:goMenu();']],
		NAVIBAR_DEF.pad.board_details.left = [0, [], []];
	} 
	
	getBoardDetails(link, commentCnt, deptId);
}

// 게시물 의견
function showBoardComment() {
	var view = $.mobile.activePage;	
	var bmId = view.find("#bmId").val();
	var anonComment = view.find("#anonComment").val();
	var commentAuth = view.find("#commentAuth").val();
	var commentManager = view.find("#commentManager").val();
	
	if(commentAuth == "true") { // 의견입력권한 체크
		TOOLBAR_DEF.board_commentlist = 
			[1, 
            [MGW_RES.get("gw_sign_write_comment_label")], 
            ["btn_tool_opinion.png"],
            ["javascript:boardCommentAction('add');"]];
	} else {
		TOOLBAR_DEF.board_commentlist = [0, [], [], []];
	}
	
	PAGE_CONTROLLER.showPage("board_commentlist", function() {
		getBoardComment(bmId, anonComment, commentAuth, commentManager);
	});
}

// 게시물 삭제
function deleteBoard() {
	var view = $.mobile.activePage;	

	if (confirm(MGW_RES.get("gw_msg_common_confirm_delete"))) {
		if (view.find("#hasPasswd").val() == "true") {
			showCheckPasswdAnoBoard("javascript:checkPasswdAnoBoard('%s', doDeleteBoard);");
		} else {
			doDeleteBoard();
		}
	}
}

function doDeleteBoard() {
	var view = $.mobile.activePage;	
	var param = {};

	param["BMID"] = view.find("#bmId").val();
	var bmId = view.find("#bmId").val();
	
	GW_PROXY.invokeOpenAPI("board", "deletebbs", param, function(data) {
		if (data.status == "0") {
			if (GWPlugin.usePlugin)  {
				GWPlugin.deleteBoardMtrl(bmId, function(){}, function() {});
			}
			else {
				PAGE_CONTROLLER.goBack(true);
				chageEditMode(false);
			}
		}
		else {
			GW_PROXY.alertErrorMessage(data.code, data.message);
			return;
		}
	});
}

function getBoardComment(bmId, anonComment, commentAuth, commentManager) {
	var param = {"BMID": bmId};
	
	GW_PROXY.invokeOpenAPI("board", "comment", param, function(data) {
		renderBoardComment(data, anonComment, commentAuth, commentManager);
	});
}

function getBoradList(apiCode, page_num, brdId) {
	var param = {"pno": page_num};
	
	if (brdId != undefined)
		param["boardid"] = brdId;
	
	GW_PROXY.invokeOpenAPI("board", apiCode, param, function(data) {
		if (brdId != undefined)
			renderBoardList(apiCode, data, brdId);
		else
			renderBoardList(apiCode, data);
	});
}

function getBoradFolderList(baseFolder, title) {
	var param = {};
	
	if (baseFolder != undefined){
		param = {"folderid": baseFolder};
	}
	
	GW_PROXY.invokeOpenAPI("board", "folder", param, function(data) {
		renderBoardFolderList(data, title);
	});
}

function getBoardFavList() {
	GW_PROXY.invokeOpenAPI("board", "favfolder", {}, function(data) {
		renderBoardFolderList(data, MGW_RES.get("gw_board_favlist_label"));
	});
}

function getBoardDetails(link, commentCnt, deptId) {
	var param = {}; 
	var paramArr = parseURLParameter(link);

	param["BRDID"] = paramArr["BRDID"];
	param["BMID"] = paramArr["BMID"];
	if(deptId != "") {
		param["DEPTID"] = deptId;
	}
	
	GW_PROXY.invokeOpenAPI("board", "details", param, function(data) {
		// 툴바 - 삭제, 의견 기능
		TOOLBAR_DEF.board_details = [0, [], [], []];
		// 삭제 가능
		if (data.DELETE == "1") {
			TOOLBAR_DEF.board_details[1].push(MGW_RES.get("gw_common_delete_label"));
			TOOLBAR_DEF.board_details[2].push("btn_tool_delete.png");
			TOOLBAR_DEF.board_details[3].push("javascript:deleteBoard();");
		}
		// 의견 존재 또는 의견작성권한 존재
		if (commentCnt != "0" || data.COMMENTAUTH == "true") {
			TOOLBAR_DEF.board_details[1].push(MGW_RES.get("gw_board_comment_label") + " (" + commentCnt + ")");
			TOOLBAR_DEF.board_details[2].push("btn_tool_opinion.png");
			TOOLBAR_DEF.board_details[3].push("javascript:showBoardComment();");			
		}
		
		if (TOOLBAR_DEF.board_details[1].length > 0) {
			TOOLBAR_DEF.board_details[0] = TOOLBAR_DEF.board_details[1].length;
			NAVIBAR_DEF.phone.board_commentlist.title = MGW_RES.get("gw_board_comment_label") + " (" + commentCnt + ")";
			NAVIBAR_DEF.pad.board_commentlist.title = MGW_RES.get("gw_board_comment_label") + " (" + commentCnt + ")";
		}
		
		PAGE_CONTROLLER.showPage("board_details", function() {
			renderBoardDetails(data, commentCnt);
			changeViewport(true);
		});
	});
}

function renderBoardList(apiCode, data, brdId) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var tmp = [];
	
	if (data == "999") {
		alert(MGW_RES.get("gw_msg_board_read_noauth_err"));
		PAGE_CONTROLLER.goBack();
		return;
	}
	
	var total = data.channel.total;
	var pageSize = data.channel.pagesize;
	var pno = parseInt(data.channel.pageno);
	var auth_reply = false;
	
	if (brdId != undefined)
		view.find("#brdId").val(brdId);
	
	view.find("#apiCode").val(apiCode);
	
	if (data.channel != undefined) {
		// 읽기 권한
		if (data.channel.auth_read == "false") {
			PAGE_CONTROLLER.goBack();
			alert(MGW_RES.get("gw_msg_board_authread_err"));
			return;
		}
		
		// 쓰기 권한
		if (data.channel.auth_write == "true") {
			view.find("[data-role=header]").find("a").eq(1).removeAttr("style");
			view.find("[data-role=header]").find("a").eq(1).attr("href", 
					"javascript:showWriteBoard('" + brdId + "','" + data.channel.title + "'," + data.channel.ANONBOARD + ", '');");
		}
		
		// 의견쓰기 권한
		if (data.channel.auth_reply == "true") {
			auth_reply = true;
		}
		
		if (apiCode == "recentlist") {
			changeTitle(MGW_RES.get("gw_board_recentlist_label") + " (" + data.channel.total + ")", 
					function(){}, function(){alert("Error: fail to changeTitle.");});
		} else { 
			changeTitle(data.channel.title + " (" + data.channel.total + ")", 
					function(){}, function(){alert("Error: fail to changeTitle.");});
			
			view.find("#brdName").val(data.channel.title);
		}
		
		
		if (data.channel.item != undefined) {
			if (data.channel.item.length != undefined) {
				for(var i = 0; i< data.channel.item.length;i++){	
					tmp.push(parseBoardList(data.channel.item[i], auth_reply));
				}
			}
			else {
				tmp.push(parseBoardList(data.channel.item, auth_reply));
			}
		}
		else {
			if (pno == 1) 
				tmp.push("<li><h3>" + MGW_RES.get("gw_msg_board_nolist") + "</h3></li>");
		}
		
		if (tmp.join("") != "") {
			if (pno > 1) {
				list.append(tmp.join(""));
			}
			else {
				list.html(tmp.join(""));
				view.find("#pno").val("1");
			}
			
			list.listview("refresh");
		}
		
		// 더보기
		if (total > parseInt(pno) * pageSize) {
			view.find("#hasMore").val(true);
			view.find("#pno").val(pno + 1);
			view.find("#morelist").removeAttr("style");
		}
		else {
			view.find("#hasMore").val(false);
			view.find("#morelist").attr("style", "display:none");
		}
				
	}
}

function renderBoardFolderList(data, title) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var tmp = [];
	
	if (title != undefined)
		changeTitle(title, function(){}, function(){alert("Error: fail to changeTitle.");});
	else
		changeTitle(MGW_RES.get("gw_board_alllist_label"), function(){}, function(){alert("Error: fail to changeTitle.");});
	
	if (data.length > 0) {
		for(var i = 0; i< data.length; i++){	
			tmp.push(parseBoardFolderList(data[i]));
		}
	}
	else {
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_board_nobldata") + "</h3></li>");
	}
		
	if (tmp.join("") != "") {
		list.html(tmp.join(""));
		list.listview("refresh");
	}
}

function renderBoardDetails(data, commentCnt) {
	var view = $.mobile.activePage;	
	if (view.prop("id") != "board_details") {
		return;
	}
	
	var list = view.find("#list");
	view.find("#attach").attr("style", "display:none;");

	if (data != undefined) {
		view.find("#brdId").val(data.BID);	// 게시판ID
		view.find("#bmId").val(data.MID); 	// 게시물ID
		view.find("#hasPasswd").val(data.HASPASSWD); 		// 게시글 암호존재
															// 여부(무명게시판)
		if (data.MANAGER == "true")
			view.find("#hasPasswd").val("false"); 
		view.find("#anonComment").val(data.ANONCOMMENT); 	// 게시글의 조회/권한 비실명 및
															// 익명 여부
		view.find("#commentAuth").val(data.COMMENTAUTH); 	// 의견작성권한
		view.find("#commentManager").val(data.MANAGER); 	// 게시판관리자
	
		// 툴바 - 삭제, 의견 기능
		// 삭제 가능
		if (data.DELETE == "1") {
			view.find(".ui-bar").find("[name=deleteBtn]").removeAttr("style");
		}
		// 의견 존재 또는 의견작성권한 존재
		if (commentCnt != "0" || data.COMMENTAUTH == "true") {
			view.find(".ui-bar").find("[name=commentBtn]").removeAttr("style");			
		}
		
		if (data.POSTER_ID == undefined || data.POSTER_ID == "" || data.POSTER_ID == "ANONYMOUS") { // 무명게시판
																									// 처리
			list.find("#postername").find(".grouping_left").removeClass("userlabel");
			list.find("#postername").find(".grouping_middle").html("<span class='onlyTxt'>" + data.POSTER_NAME + "</span>");
		} else {
			list.find("#postername").find(".grouping_left").html(MGW_RES.get("gw_board_postername_label"));
			list.find("#postername").find(".grouping_middle").html("<a href=\"javascript:showUserDetails('" + data.POSTER_ID
					+ "');\" class='btn_user blue'><span>" + data.POSTER_NAME + "</span><span class='viewUser'></span></a>");
		}
		
		list.find("#boardname").html(data.BOARDNAME);
		
		var dispTitle = getTitleAddedPreface(data.TITLE, data.PREFACE_NAME);
		if (data.URGENCY == "true")
			list.find(".title").html("[" + MGW_RES.get("gw_mail_urgency_label") + "] " + dispTitle);
		else
			list.find(".title").html(dispTitle);
		
		list.find(".dateinfo").text(convertDateFormat(data.POSTDATESTRING, false));
		
		body = "<div>" + trim(data.BODY) + "</div>";
		
		// removeConntentAttachInfo
		remove_point = body.indexOf("<INPUT TYPE=HIDDEN NAME='FIRSTATTACHVIEW'");
		if(remove_point > 0)
			body = body.substring(0, remove_point);
		
		// tkofs 상세 바디 수정
		body = $(body);
		if (GW_OpenAPI.board_convert_image) {
			
			var images = body.find("img");
			
			$.each(images, function(i, value) {
				var image = images.eq(i);
				var imgSrc = image.attr("src");
				
				if(image.attr("width")!=undefined && image.attr("width") > 300){
					image.attr("width", "100%");
				}else if(image.attr("width")==undefined){
					image.removeAttr('style');
					image.attr("width", "100%");
				}
				if (startsWith(imgSrc, 'http://') || startsWith(imgSrc, 'https://')) {
					imgSrc = GW_OpenAPI.serverIP + GW_OpenAPI.streamUrlPATH + imgSrc;
					image.attr('src', imgSrc);
					image.on('error', function() {$(this).attr('src', 'images/miss_130.gif');});
				}
			});
		} 
		
		var areas = body.find("area");
		
		$.each(areas, function(i, value) {
			areas.eq(i).removeAttr('href');
		});
		
		var tab = body.find("table");
		$.each(tab, function(i, value){
			tab.eq(i).css('width', '100%');
		});
		var ths = body.find('.item_list');
		$.each(ths, function(j, val){
			ths.eq(j).removeAttr("width");
		});
		var tds = body.find("td");
		$.each(tds, function(k, val){
			tds.eq(k).removeAttr("width");
		});
		
		// tkofs bbsinfo 를 하단으로 위치
		var bbsinfoTable = body.find("bbsinfo").children("table").detach();
		$(bbsinfoTable).removeClass('basic_table');
		$(bbsinfoTable).addClass('pivot_table');
		var tempAtag = $(bbsinfoTable).find('a');
		$(tempAtag).parent().append($(tempAtag).html());
		$(tempAtag).remove();
		$(bbsinfoTable).find('th').removeAttr("width");
		var idPrintDiv = body.find("#idPrint");
		$(idPrintDiv).append("<div id=\"bbsinfoDiv\"></div>");
		$(idPrintDiv).find('#bbsinfoDiv').append(bbsinfoTable);
		// 상단 정보성 테이블 안보이게 tkofs
		//$(idPrintDiv).find('.writting').attr("style", "display:none;");
		// 옵션 안보이게 tkofs
		$(idPrintDiv).find('#RC_OPTION').parent().addClass('dpNone');
		$(idPrintDiv).find('#RC_OPTION').parent().attr("style", "display:none;");
		
		list.find(".contents").html(body);
		list.find(".contents").find("input[type=checkbox]").attr("data-role", "none");
		
		if (GW_OpenAPI.board_onlybody == "false"){
			// CallBBSNoteView 제거
			list.find(".contents").find("table").eq(0).find("a").removeAttr("href");
			list.find(".contents").find("table").eq(0).find("a").attr("style", "color:#000; font-weight:normal;");
		}
		
		converPopupLink(list.find(".contents"));
		
		// 첨부
		if (data.attaches != undefined) {
			var tmp = [];
			
			for(var i=0; i<data.attaches.length; i++) {
				var category = "attach";
				var file = data.attaches[i];
				var filename = file.name.replace(/'/g, '').replace(/#/g, '').replace(/%27/g, '').replace(/^\s+|\s+$/g,"");
				var filelink = file.url.trim().replace(/'/g, '').replace(/#/g, '');
				tmp.push("<li><a class='attach_lst' onclick=\"javascript:DOC_HANDLER.setEvent(event);\" href=\"javascript:DOC_HANDLER.showAttach('" + filelink + "', '" + category 
						+ "', '" + filename +"');\" class='btn_txt gray attach'><span style='white-space: pre-line;'>" + file.name + "(");
				tmp.push(convertFileSizeUnit(file.size));
				tmp.push(")</span></a></li>");
			}
			
			list.find("#attach").removeAttr("style");
			list.find("#attachInfo").html(data.attaches.length + MGW_RES.get("gw_common_piece_label"));
			list.find("#attachList").html(tmp.join(""));
		}
		
		// list.listview("refresh");
		list.removeAttr("style");
	}
	
}

function parseBoardFolderList(board) {
	var tmp = [];
	
	tmp.push("<li id='" + board["@id"] +"'>");
	
	if (board["@hasChildren"] == "true") {
		tmp.push("<a href=\"javascript:showBoardFolerList('" + board["@id"] + "', '" + board["@text"] + "')\">");
		tmp.push("<h3>" + board["@text"] + "</h3>");
		tmp.push("</a><a href=\"javascript:showBoardList('list', '" + board["@id"] + "')\">");
	}
	else {
		tmp.push("<a href=\"javascript:showBoardList('list', '" + board["@id"] + "')\">");
		tmp.push("<h3>" + board["@text"] + "</h3>");
	}
	
	tmp.push("</a></li>");
	
	return tmp.join("");
}

function parseBoardList(board) {
	var tmp = [];
	
	tmp.push("<li id='" + board.brdid +"'>");
	tmp.push("<a href=\"javascript:showBoardDetails('" + trim(board.link) + "', '" + board.commentcount + "', '" + sessionStorage["deptid"] + "');\">");
	tmp.push("<h3>" + board.title + "</h3>");
	tmp.push("<span class='info'>" + board.brdname + " | </span>");
	tmp.push("<span class='info'>" + board.author + " | </span>");
	tmp.push("<span class='info'>" + convertDateFormat(board.pubDate, true) + " | </span>");
	
	var status = getBoardStatus(board.status);
	
	// 게시기간
	var period = getBoardPeriod(status.substring(0, 2));
	
	// 게시물 특성
	var properties = getBoardProperties(status.substring(2, status.length));
	
	tmp.push("<span class='info'>" + MGW_RES.get("gw_board_comment_label") + ":" + board.commentcount);
	if (period != "") {
		tmp.push(" | " + period);
	}
	
	if (properties != "") {
		tmp.push(" | " + properties);
	}
	
	tmp.push("</span></a></li>");
	
	return tmp.join("");
}

function getBoardStatus(status) {
	// 예) /img/bbs/BR2.GIF
	var tmp = "";
	tmp = status.split(".GIF")[0];
	var idx = tmp.lastIndexOf("/");
	tmp = tmp.substring(idx+2, tmp.length);
	
	return tmp;
}

function getBoardPeriod(status) {
	var period = status.substring(0, 2);
	
	// TODO: 기간에 따른 이미지 처리해야 함
	if (period == "R0") {
		return "오늘 게시되고 안읽은 게시물";
	}
	else if (period == "R1") {
		return "최근게시물 중 안읽은 게시물";
	}
	else if (period == "R2") {
		return "최근게시물 중 읽은 게시물";
	}
	else if (period == "R3") {
		return "오래된 게시물";
	}
}

function getBoardProperties(status) {
	var tmp = "";
	
	if (status != "") {
		for(var i=0; i<status.length; i++) {
			var key = status.charAt(i);
			if (key == "A") {
				if (tmp != "")	tmp += " | ";
				
				tmp += "첨부";
			}
			if (key == "E") {
				if (tmp != "")	tmp += " | ";
				
				tmp += "긴급";
			}
			if (key == "M") {
				if (tmp != "")	tmp += " | ";
				
				tmp += "수정";
			}
		}
	}
	
	return tmp;
}

function refreshBoardList() {
	var view = $.mobile.activePage;	
	view.find("#hasMore").val(false);
	
	if (view.find("#brdId").val() != "")
		getBoradList(view.find("#apiCode").val(), 1, view.find("#brdId").val());	
	else
		getBoradList(view.find("#apiCode").val(), 1);

}

function getMoreBoardList() {
	var view = $.mobile.activePage;	
	
	if (view.find("#hasMore").val() == "true") {
		// 더보기가 완료 되기전에 중복 호출 방지하기 위해
		view.find("#hasMore").val(false);
		
		if (view.find("#brdId").val() != "")
			getBoradList(view.find("#apiCode").val(), view.find("#pno").val(), view.find("#brdId").val());	
		else
			getBoradList(view.find("#apiCode").val(), view.find("#pno").val());
	
	}
}

function showWriteBoard(brdId, brdName, anoBoard, deptBoard) {
	PAGE_CONTROLLER.showPage("board_write", function(){
		renderWriteBoard(brdId, brdName, anoBoard, deptBoard);
	});
	
	showWebView();
}

function renderWriteBoard(brdId, brdName, anoBoard, deptBoard) {
	// 게시판 쓰기 폼 세팅
	var view = $.mobile.activePage;	
	if (view.prop("id") != "board_write")
		return;
	
	// 게시판 정보
	view.find("#brdName").html(brdName);
	view.find("#brdId").val(brdId);
	view.find("#anoBoard").val(anoBoard);
	view.find("#deptBoard").val(deptBoard);
	
	// 머리글
	getPrefaceInfo(view.find("#brdId").val());
	
	// 종료일
	var opt_year = "<option id='everlast'>" + MGW_RES.get("gw_board_everlast_label") + "</option>";
	var opt_month = appendMonthOption();
	var opt_day = appendDayOption();
	
	opt_year += appendYearOption();

	view.find("select#sel_endYear").html(opt_year);
	view.find("select#sel_endMonth").html(opt_month);
	view.find("select#sel_endDay").html(opt_day);
	
	view.find("select#sel_endYear").live("change", function() {
		if ($(this).find("option:selected").prop("id") == "everlast") {
			view.find("#endDateDiv").attr("style", "display:none");
		} else {
			view.find("#endDateDiv").removeAttr("style");
		}
	});
}

function getPrefaceInfo(brdId) {
	var param = {"BRDID" : brdId};
	GW_PROXY.invokeOpenAPI("board", "prefaceinfo", param, function(data) {
		renderPrefaceInfo(data);
	});
}

function renderPrefaceInfo(data) {
	var view = $.mobile.activePage;
	var tmp = "<option value=''>" + MGW_RES.get("gw_board_select_preface") + "</option>";
	
	if(data.item != undefined) {
		if(data.item.length != undefined) {
			for(var i=0; i<data.item.length; i++) {
				tmp += "<option value='" + data.item[i].id + "'>" + data.item[i].name + "</option>";
			}
		} else {
			tmp += "<option value='" + data.item.id + "'>" + data.item.name + "</option>";
		}
	}
	
	view.find("select#preface").append(tmp);
}

function appendYearOption() {
	var tmp = "";
	var now = new Date();
	var current_year = now.getFullYear();
	
	for (var i=current_year; i <current_year+10; i++) {
		tmp += "<option id='" + i + "'>" + i + "</option>";
	}
	return tmp;
}

function appendMonthOption() {
	var tmp = "";
	
	for (var i=1; i <13; i++) {
		tmp += "<option id='" + i + "'>" + i + "</option>";
	}
	return tmp;
}

function appendDayOption() {
	var tmp = "";
	
	for (var i=1; i <32; i++) {
		tmp += "<option id='" + i + "'>" + i + "</option>";
	}
	return tmp;
}

function cancel_writeBoard() {
	if (confirm(MGW_RES.get("gw_msg_board_cancel_write"))) {
		if (GWPlugin.usePlugin) { // APP, 게시물쓰기popup인 경우
			hideWebView();
			GWPlugin.closePopupViewer("", false);
			return;
		}
		else {
			PAGE_CONTROLLER.goBack();
		}
	}
}

function writeBoard() {
	var view = $.mobile.activePage;	
	if (view.prop("id") != "board_write")
		return;
	
	if (view.find("#anoBoard").val() == "true") {
		showCheckPasswdAnoBoard("javascript:doWriteBoard('%s');");
	} else {
		doWriteBoard("");
	}
}

function showCheckPasswdAnoBoard(fn) {
	if (GWPlugin.usePlugin) {
		GWPlugin.showCheckPasswdAnoBoard(fn);
	}
	else {
		var view = $.mobile.activePage;	
		
		view.find("#checkPasswdAnoBoardDialog #password").val("");
		view.find("#checkPasswdAnoBoardDialog").find("#password").focus();

		view.find("#checkPasswdAnoBoardDialog #checkPasswdAction").val(fn);
		
		view.find("#checkPasswdAnoBoardDialog").popup("open");
	}	
}

function setPasswdAnoBoardDialog() {
	var view = $.mobile.activePage;	
	var passwd = view.find("#checkPasswdAnoBoardDialog #password").val();
	var fn = view.find("#checkPasswdAnoBoardDialog #checkPasswdAction").val();
	fn = fn.replace("%s", passwd);
	view.find("#checkPasswdAnoBoardDialog").popup("close");
	
	$.globalEval(fn);
}

function closePasswdAnoBoardDialog() {
	var view = $.mobile.activePage;	
	view.find("#checkPasswdAnoBoardDialog").popup("close");	
}

function checkPasswdAnoBoard(passwd, callback) {
	var view = $.mobile.activePage;
	var param = {};

	param["PID"] = view.find("#bmId").val();
	param["PWD"] = passwd;
	
	GW_PROXY.invokeOpenAPI("board", "chkpwdBoard", param, function(data) {
		if(data.code != undefined && data.code == "1") {
			callback();
		} else {
			alert(MGW_RES.get("gw_msg_board_not_match_passwd_manage"));
		}
	});
}

function doWriteBoard(passwd) {
	var param = {};
	var view = $.mobile.activePage;	
	if (view.prop("id") != "board_write")
		return;
	
	if (view.find("#progressWriteBoard").val() == "true") {
		return;
	}
	
	// 제목 체크
	if (trim(view.find("#subject").val()) == "") {
		alert(MGW_RES.get("gw_msg_mail_inputsubject"));
		view.find("#subject").focus();
		return;
	}
	// 제목 길이 체크 (제한수 50글자)
	else if (view.find("#subject").val().trim().length > 50) {
		alert(MGW_RES.get("gw_msg_common_toolong_msg"));
		title.focus();
		return;
	}
	
	// 본문 체크
	if (trim(view.find("#boardBody").val()) == "") {
		alert(MGW_RES.get("gw_msg_mail_inputbody"));
		view.find("#boardBody").focus();
		return;
	}
	// 내용 길이 체크 (제한수 500글자)
	else if (view.find("#boardBody").val().trim().length > 500) {
		alert(MGW_RES.get("gw_msg_common_toolong_summary"));
		title.focus();
		return;
	}
	
	// 종료일 체크
	var end_year = "";
	var end_month = "";
	var end_day = "";
	
	if (view.find("select#sel_endYear option:selected").prop("id") != "everlast") {
		end_year = view.find("select#sel_endYear option:selected").val();
		end_month = view.find("select#sel_endMonth option:selected").val();
		end_day = view.find("select#sel_endDay option:selected").val();
		
		if (!checkPastDateTime(end_year, end_month, end_day, 23, 59)) {
			alert(MGW_RES.get("gw_msg_board_invalid_enddate"));
			return;
		}
	} else {
		// 종료일 영구
		end_year = "3000";
		end_month = "01";
		end_day = "01";
	}
	
	// 무명게시판 게시글 암호
	if (view.find("#anoBoard").val() == "true") {
		param["PWD"] = passwd;
		param["RESPONSEVIEW"] = "2";	// 비실명
	}
	
	// 부서게시판
	if (view.find("#deptBoard").val() != "") {
		param["DEPTID"] = view.find("#deptBoard").val();
	}
	
	param["BBSENDYEAR"] = end_year;
	param["BBSENDMONTH"] = end_month;
	param["BBSENDDAY"] = end_day;
	
	param["BRDID"] = view.find("#brdId").val();
	param["RC_PREFACE"] = view.find("select#preface option:selected").val();
	param["SUBJECT"] = view.find("#subject").val();
	param["BODY"] = view.find("#boardBody").val();

	if (view.find("#bbsExpress").attr("checked") == "checked")
		param["BBSEXPRESS"] = "1";

	
	view.find("#progressWriteBoard").val("true");
	
	GW_PROXY.invokeOpenAPI("board", "writebbs", param, function(data) {
		var resultCode = data.status;
		if (resultCode != undefined && resultCode == "0") {
			alert(MGW_RES.get("gw_msg_common_save"));

			if (GWPlugin.usePlugin) { 
				GWPlugin.closePopupViewer("", false);
				return;
			}
			else {
				PAGE_CONTROLLER.goBack(true);
			}
			
		} else {
			if (resultCode == "999")
				alert(data.message);
			else
				alert(MGW_RES.get("gw_msg_common_err"));	
			view.find("#progressWriteBoard").val("false");
			return;
		}
		
	});
}

function renderBoardComment(data, anonComment, commentAuth, commentManager) {
	var view = $.mobile.activePage;	
	if (view.prop("id") != "board_commentlist")
		return;
	
	if(commentAuth == "true") { // 의견입력권한 체크
		view.find("#commentWriteBtn").show();
	} else {
		view.find("#commentWriteBtn").hide();
	}	
	
	var list = view.find("#list");
	var tmp = "";
	
	if (data != undefined) {
		view.find("#brdId").val(data.brdid);
		view.find("#bmId").val(data.mtrlid);
		view.find("#anonComment").val(anonComment);
		view.find("#commentAuth").val(commentAuth);
		view.find("#commentManager").val(commentManager);
	}

	if (data.item != undefined) {
		if (data.item.length != undefined) {
			var sortList = GW_CONTROLLER_BOARD.sortBoardComment(data.item);
			for (var i=0; i<sortList.length; i++) {
				tmp += parseBoardComment(sortList[i]);
			}
		}
		else {
			tmp += parseBoardComment(data.item);
		}
	} else {
		tmp += parseBoardComment(undefined);
	}
	
	if (tmp != "") {
		if (view.prop("id") == "board_commentlist") {
			list.html(tmp);
			list.listview("refresh");
		}
	}
}

function parseBoardComment(comment) {
	var view = $.mobile.activePage;	
	var tmp = [];
	
	if (comment != undefined) {
		tmp.push("<li id='" + comment.cid + "'>");
		tmp.push("<input type='hidden' name='commentContent' value='" + comment.content + "'>");
	
		// 권한체크
		var auth_update_comment = false;
		var auth_delete_comment = false;
		var auth_write_ofcomment = false;
		var passwdFlag = comment.passwdFlag;
		var manager = view.find("#commentManager").val();
		
		// 의견수정
		if (comment.id == sessionStorage["id"])
			auth_update_comment = true;
		if (comment.id == "ANONYMOUS" && passwdFlag == "true")
			auth_update_comment = true;
		// 의견삭제
		if (comment.id == sessionStorage["id"] || manager == "true" || (comment.id == "ANONYMOUS" && passwdFlag == "true"))
			auth_delete_comment = true;
		// 댓글쓰기
		if (comment.parcid == "000000000")
			auth_write_ofcomment = true;	
		
		if (auth_update_comment || auth_delete_comment || auth_write_ofcomment)
			tmp.push("<a href=\"#\" onclick=\"javascript:setPopupMenu(event,'" + comment.cid + "'," + passwdFlag + "," + manager + "," + auth_update_comment + "," + auth_delete_comment + "," + auth_write_ofcomment +");\">");
		
		if (comment.parcid == "000000000")
			tmp.push("<div class='comment'>");
		else 
			tmp.push("<div class='recommentIcon'></div><div class='recomment'>");
	
		tmp.push("<div class='commentInfo'>");
		tmp.push("<span class='username'>" + comment.name +" |" + "</span>"); 
		tmp.push("<span class='regdate'>" + convertDateFormat(comment.date, false) + "</span>");
		tmp.push("<div class='btnArea'>");
		tmp.push("</div></div><div class='content'>" + comment.content.replace(/</gi, '&lt;').replace(/>/gi, '&gt;').replace(/\n/g, "<br/>") + "</div></div>");
		
		if (auth_update_comment || auth_delete_comment || auth_write_ofcomment)
			tmp.push("</a>");
		tmp.push("</li>");
	} else {
		tmp.push("<li>" + MGW_RES.get("gw_msg_board_comment_nolist") + "</li>");		
	}

	return tmp.join("");
}

function setPopupMenu(evt, cid, passwdFlag, manager, auth_update_comment, auth_delete_comment, auth_write_ofcomment) {
	var label = [];
	var delFlag = [];
	var fn = [];
	var linkPos = "";
	var view = $.mobile.activePage;	
	
	linkPos = "{{" + evt.clientX + "," + evt.clientY + "}, {" + "10" + "," + "10" + "}}";

	if (auth_write_ofcomment) {
		label.push(MGW_RES.get("gw_board_write_of_comment_label"));
		delFlag.push(false);
		fn.push("javascript:boardCommentAction('addof', '" + cid + "'," + passwdFlag + "," + manager + ");");
	}	
	if (auth_update_comment) {
		label.push(MGW_RES.get("gw_common_modify_label"));
		delFlag.push(false);
		fn.push("javascript:boardCommentAction('modify', '" + cid + "'," + passwdFlag + "," + manager + ");");
	}
	if (auth_delete_comment) {
		label.push(MGW_RES.get("gw_common_delete_label"));
		delFlag.push(true);
		fn.push("javascript:boardCommentAction('delete', '" + cid + "'," + passwdFlag + "," + manager + ");");
	}

	if (GWPlugin.usePlugin) {
		GWPlugin.showPopupMenu([label.length,
	                   label,
	                   delFlag,
	                   fn,
	                   linkPos]);
	} else {
		var tmp = [];
		if (auth_write_ofcomment) {
			tmp.push("<a href=\"javascript:boardCommentAction('addof', '" + cid + "'," + passwdFlag + "," + manager + ");\" data-role=\"button\" data-theme=\"d\" >" + 
					MGW_RES.get("gw_board_write_of_comment_label") + "</a>");		
		}			
		if (auth_update_comment) {
			tmp.push("<a href=\"javascript:boardCommentAction('modify', '" + cid + "'," + passwdFlag + "," + manager + ");\" data-role=\"button\" data-theme=\"d\" >" + 
					MGW_RES.get("gw_common_modify_label") + "</a>");	
		}
		if (auth_delete_comment) {
			tmp.push("<a href=\"javascript:boardCommentAction('delete', '" + cid + "'," + passwdFlag + "," + manager + ");\" data-role=\"button\" data-theme=\"d\" >" + 
					MGW_RES.get("gw_common_delete_label") + "</a>");
		}
		tmp.push("<a href=\"javascript:boardCommentAction('cancel');\" data-role=\"button\" data-theme=\"d\" >" + 
				MGW_RES.get("gw_common_cancel_label") + "</a>");
		
		view.find("#writeBoardCommentDialog #menuButton").html(tmp.join(""));
		view.find("#writeBoardCommentDialog #menuButton a").button();
		
		view.find("#writeBoardCommentDialog #writeBoardComment").attr("style", "display:none");
		view.find("#writeBoardCommentDialog #menuButton").removeAttr("style");
		
		view.find("#writeBoardCommentDialog").popup("open");
	}
}

function getTitleAddedPreface(title, preface) {
	if(preface == undefined || preface == "")
		return title;
	else
		return "[" + preface + "] " +title;
}

function boardCommentAction(action, cid, passwdFlag, manager) {
	var view = $.mobile.activePage;
	
	view.find("#commentAction").val(action);
	view.find("#cid").val(cid);
	
	switch (action) {
	case 'add': // 의견 추가
		showWriteBoardComment("");
		break;
	case 'addof': // 의견의의견 추가
		showWriteBoardComment("");
		break;
	case 'modify': // 의견 수정
		if (passwdFlag == true) {
			showCheckPasswdAnoBoard("javascript:checkPasswdAnoBoardComment('%s', showWriteBoardComment, '" + cid + "');");
		} else {
			showWriteBoardComment(cid);
		}
		break;
	case 'delete': // 의견 삭제
		if (passwdFlag == true && manager != true) {
			showCheckPasswdAnoBoard("javascript:checkPasswdAnoBoardComment('%s', deleteBoardComment, '" + cid + "');");
		} else {
			deleteBoardComment(cid);
		}		
		if (!GWPlugin.usePlugin)
			view.find("#writeBoardCommentDialog").popup("close");		
		break;
	case 'cancel': // 취소
		if (!GWPlugin.usePlugin)
			view.find("#writeBoardCommentDialog").popup("close");			
		break;			
	}
}

function checkPasswdAnoBoardComment(passwd, callback, cid) {
	var param = {};

	param["PID"] = cid;
	param["PWD"] = passwd;
	
	GW_PROXY.invokeOpenAPI("board", "chkpwdBoardComment", param, function(data) {
		if(data.code != undefined && data.code == "1") {
			callback(cid);
		} else {
			alert(MGW_RES.get("gw_msg_board_not_match_passwd_manage"));
		}
	});
}

// 의견 작성
function showWriteBoardComment(cid) {
	var view = $.mobile.activePage;
	var comment = "";
	if (cid != "") {
		comment = view.find("#list li#" +cid).find("[name=commentContent]").val();
	}

	if (GWPlugin.usePlugin) {
		GWPlugin.showWriteComment(comment, true, false, 0, "javascript:setBoardComment");
	} else {
		view.find("#writeBoardCommentDialog #commentBody").val(comment);
		view.find("#writeBoardCommentDialog").find("#commentBody").focus();
		
		view.find("#writeBoardCommentDialog #writeBoardComment").removeAttr("style");
		view.find("#writeBoardCommentDialog #menuButton").attr("style", "display:none");
		
		view.find("#writeBoardCommentDialog").popup("open");
	}
}

// 의견 저장
function setBoardComment(data) {
	var comment = decodeURIComponent(data);
	if ($.trim(comment) == "") {
		comment = "";
	}
	comment = htmlDecode(comment);
	
	var view = $.mobile.activePage;
	var action = view.find("#commentAction").val();
	var cid = view.find("#cid").val();
	switch (action) {
	case 'add': // 의견 추가
		addBoardComment(comment, "");
		break;
	case 'addof': // 의견의의견 추가
		addBoardComment(comment, cid);
		break;
	case 'modify': // 의견 수정
		modifyBoardComment(comment, cid);
		break;
	}	
}

function saveCloseWriteBoardCommentDialog() {
	var view = $.mobile.activePage;
	var comment = view.find("#writeBoardCommentDialog #commentBody").val();
	view.find("#writeBoardCommentDialog").popup("close");
	setBoardComment(comment);
}

function closeWriteBoardCommentDialog() {
	var view = $.mobile.activePage;	
	view.find("#writeBoardCommentDialog").popup("close");
}

// 의견 추가, 의견의 의견 추가
function addBoardComment(comment, parcid) {
	var view = $.mobile.activePage;	

	if (view.find("#anonComment").val() == "true") {
		showCheckPasswdAnoBoard("javascript:doAddBoardComment('" + comment + "','" + parcid + "','%s');");
	} else {
		doAddBoardComment(comment, parcid, "");
	}	
}

// 의견 추가, 의견의 의견 추가
function doAddBoardComment(comment, parcid, passwd) {
	var view = $.mobile.activePage;	
	var params = {};

	params["BMID"] = view.find("#bmId").val();
	params["BRDID"] = view.find("#brdId").val();
	params["COMMENT"] = comment;
	if(parcid != undefined && parcid != "")
		params["PARCID"] = parcid;
	if(view.find("#anonComment").val() == "true") {
		params["PWD"] = passwd;
	}

	GW_PROXY.invokeOpenAPI("board", "commentAdd", params, function(data) {
		if (data.status == "0") {
			refreshBoardComment();
		}
		else {
			GW_PROXY.alertErrorMessage(data.code, data.message);
			return;
		}
	});
}

// 의견 수정
function modifyBoardComment(comment, cid) {
	var view = $.mobile.activePage;	
	var params = {};

	params["BMID"] = view.find("#bmId").val();
	params["BRDID"] = view.find("#brdId").val();
	params["COMMENT"] = comment;
	params["CID"] = cid;

	GW_PROXY.invokeOpenAPI("board", "commentModify", params, function(data) {
		if (data.status == "0") {
			refreshBoardComment();
		}
		else {
			GW_PROXY.alertErrorMessage(data.code, data.message);
			return;
		}
	});
}

// 의견 삭제
function deleteBoardComment(cid) {
	var view = $.mobile.activePage;	
	var params = {};

	params["BMID"] = view.find("#bmId").val();
	params["BRDID"] = view.find("#brdId").val();
	params["CID"] = cid;

	GW_PROXY.invokeOpenAPI("board", "commentDelete", params, function(data) {
		if (data.status == "0") {
			refreshBoardComment();
		}
		else {
			GW_PROXY.alertErrorMessage(data.code, data.message);
			return;
		}
	});
}

function refreshBoardComment() {
	var view = $.mobile.activePage;	
	var bmId = view.find("#bmId").val();
	var anonComment = view.find("#anonComment").val();
	var commentAuth = view.find("#commentAuth").val();
	var commentManager = view.find("#commentManager").val();
	
	getBoardComment(bmId, anonComment, commentAuth, commentManager);
}
// 주소록 목록 (type: personal=개인, department=부서, group=그룹)
function showContactList(type, group_id, group_name) {
	
	PAGE_CONTROLLER.showPage("contact_list", function() {
		if (group_id == undefined)
			getContactList(false, type, 1);
		else
			getContactList(false, type, 1, group_id, group_name);
	});
	
}

// 주소록 그룹
function showContactGroup() {	
	PAGE_CONTROLLER.showPage("contact_group", function() {getContactGroup(1);});
}

// 주소록 검색
function showContactSearch(popupMode) {
	var view = $.mobile.activePage;	
	showSearch(popupMode, "contact", view.find("#apiCode").val(), view.find("#groupId").val());
}

// 주소록 상세
function showContactDetails(id, apiCode) {
	var param = {"id": id};

	// 이전 페이지 모두 Clear
	PAGE_CONTROLLER.cleanViewStack();
	
	if (apiCode == "user")	apiCode = "personal";
	if (apiCode == "dept")  apiCode = "department";
	
	PAGE_CONTROLLER.showPage("contact_details", function() {
		GW_PROXY.invokeOpenAPI("contact", "details", param, function(data) {
			if ($.mobile.activePage.prop("id") != "contact_details")
				return;
			
			if (apiCode == "group")	apiCode = "personal";				
			
			$.mobile.activePage.find("#apiCode").val(apiCode);

			renderContactDetails(data);
			
		});
	});
}

function showOrgUserDetails(id) {
	NAVIBAR_DEF.phone.org_user.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], 
	                                  ['javascript:goList();', 'javascript:goMenu();']];
	NAVIBAR_DEF.pad.org_user.left = [0, [], []];
		
	PAGE_CONTROLLER.showPage("org_user", function() {getUserDetails(id);});	
}

function getContactList(popupMode, apiCode, page_num, group_id, group_name) {
	var param = {"pno": page_num};
	
	if (group_id != undefined)
		param["groupid"] = group_id;
	
	GW_PROXY.invokeOpenAPI("contact", apiCode, param, function(data) {
		renderContactList(popupMode, false, apiCode, data, group_id, group_name);
	});
}

function getContactGroup(page_num) {
	var param = {"pno": page_num};
	
	GW_PROXY.invokeOpenAPI("contact", "grouplist", param, function(data) {
		changeTitle(MGW_RES.get("gw_contact_group_label"), 
				function(){}, function(){alert("Error: fail to changeTitle.");});

		renderContactGroup(data);
	});
}

function getMoreContactList(popupMode) {
	var view = $.mobile.activePage;	
	
	if (popupMode) {
		var type = view.find("#currentTab").val();
		
		if (type ==  "personal") {
			view.find("#personal_hasMore").val(false);
			getContactList(true, type, view.find("#personal_pno").val());
		} 
		else {
			view.find("#department_hasMore").val(false);
			getContactList(true, type, view.find("#department_pno").val());
		}
	}
	else {
		var type = view.find("#apiCode").val();
		
		if(view.find("#hasMore").val() == "true") {
			view.find("#hasMore").val(false);
			
			if (type != "group")  {
				getContactList(false, type, view.find("#pno").val());
			}
			else {
				getContactList(false, type, view.find("#pno").val(), group_id, group_name);
			}
		}
	}
}

function renderContactDetails(data) {
	var view = $.mobile.activePage;	
	if (view.prop("id") != "contact_details")
		return;
	
	console.log(JSON.stringify(data));
	var personalInfo = view.find(".addrprofilepi");
	var orgInfo = view.find(".addrprofileoi");
	var apiCode = view.find("#apiCode").val();
	
	if (data != undefined) {
		// 개인정보
		if (data.name != null && data.name != "")							personalInfo.find(".first span").text(data.name);
		if (data.gender != null && data.gender != "" && data.gender == "M")	personalInfo.find(".gender").text(MGW_RES.get("gw_contact_gender_m"));
		if (data.gender != null && data.gender != "" && data.gender == "F")	personalInfo.find(".gender").text(MGW_RES.get("gw_contact_gender_f"));	
		
		if (data.homePhone != null && data.homePhone != "") {
			personalInfo.find("#homePhone").html("<span class='tel'>" + data.homePhone + "</span>"
					+ "<a href=\"javascript:popupLink('tel:" + data.homePhone 
					+ "');\" class='call'></a>");
		}
		
		if (data.mobile != null && data.mobile != "") {
			personalInfo.find("#mobile").html("<span class='mobile'>" + data.mobile + "</span>"
					+ "<a href=\"javascript:popupLink('tel:" + data.mobile + "');\" class='call'></a>"
					+ "<a href=\"javascript:popupLink('sms:" + data.mobile + "');\" class='sms'></a>");
		}
		
		if (data.homeFaxNumber != null && data.homeFaxNumber != "")					
			personalInfo.find(".fax").text(data.homeFaxNumber);
		
		if (data.mail != null && data.mail != "") {
			personalInfo.find(".mail").html("<a href=\"javascript:popupLink('mailto:" + data.homepage + "');\">" + data.mail + "</a>");
			// tkofs 
			//personalInfo.find(".mail").html("<a href=\"javascript:moveMailWriteForm('"+ apiCode +"','" + data.id + "','" + data.name + "', '"+ data.mail +"');\">" + data.mail + "</a>");
		}
		
		if (data.homepage != null && data.homepage != "")						
			personalInfo.find(".homepage").text(data.homepage);
		
		if (data.homePostalCode != null && data.homePostalCode != "")
			personalInfo.find(".zipcode").text(data.homePostalCode);
		
		if (data.homePostalAddress != null && data.homePostalAddress != "")
			personalInfo.find(".addr").text(data.homePostalAddress);
	
		
		// 회사정보
		if (data.company != null && data.company != "")
			orgInfo.find(".first span").text(data.company);
		
		if (data.department != null && data.department != "")
			orgInfo.find(".dept").text(data.department);
		
		if (data.position != null && data.position != "")
			orgInfo.find(".position").text(data.position);
		
		if (data.telephoneNumber != null && data.telephoneNumber != "") 
			orgInfo.find("#officePhone").html("<a href=\"javascript:popupLink('tel:" + data.telephoneNumber + "');\" class='call'></a><span class='tel'>" + data.telephoneNumber + "</span>");
		
		if (data.faxNumber != null && data.faxNumber != "")
			orgInfo.find(".fax").text(data.faxNumber);
		
		if (data.postalCode != null && data.postalCode != "")
			orgInfo.find(".zipcode").text(data.postalCode);
		
		if (data.postalAddress != null && data.postalAddress != "")
			orgInfo.find(".addr").text(data.postalAddress);
		
		if (data.memo != null && data.memo != "")
			orgInfo.find(".memo").text(data.memo);
		
	}
	
}

function renderContactList(popupMode, searchMode, apiCode, data, group_id, group_name) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var tmp = [];
	var total = data.total;
	var pageSize = data.pagesize;
	var pageNo = parseInt(data.pageno);
	
	pno = view.find("#pno");
	hasMore = view.find("#hasMore");
	
	if (!searchMode) {
		if (apiCode == "personal") {
			if (popupMode) {
				list = view.find("#list_personal");
				pno = view.find("#personal_pno");
				hasMore = view.find("#personal_hasMore");
			} 
			else {
				view.find("#apiCode").val(apiCode);
				changeTitle(MGW_RES.get("gw_contact_personal_label") + " (" + data.total + ")", 
						function(){}, function(){alert("Error: fail to changeTitle.");});
			}
		} else if(apiCode == "department") { 
			if (popupMode) {
				list = view.find("#list_department");
				pno = view.find("#department_pno");
				hasMore = view.find("#department_hasMore");
			} 
			else {
				view.find("#apiCode").val(apiCode);
				changeTitle(MGW_RES.get("gw_contact_dept_label") + " (" + data.total + ")", 
						function(){}, function(){alert("Error: fail to changeTitle.");});
			}
		} else if (apiCode == "group") {
			view.find("#apiCode").val(apiCode);
			view.find("#groupId").val(group_id);
			
			changeTitle(group_name + " (" + data.total + ")", 
					function(){}, function(){alert("Error: fail to changeTitle.");});
		}
	}
	
	if (data.contact != undefined) {
		if (data.contact.length != undefined) {
			for(var i = 0; i< data.contact.length; i++){	
				tmp.push(parseContactList(data.contact[i], popupMode, apiCode));
			}
		}
		else {
			tmp.push(parseContactList(data.contact, popupMode, apiCode));
		}
	}
	else {
		if (pageNo == 1) {
			if (searchMode)
				tmp.push("<li><h3>" + MGW_RES.get("gw_msg_common_nosearchdata") + "<h3></li>");
			else
				tmp.push("<li><h3>" + MGW_RES.get("gw_msg_contact_nothing") + "<h3></li>");
		}
			
	}
	
	// 더보기
	if (total > parseInt(pageNo) * pageSize) {
		hasMore.val(true);
		pno.val(pageNo + 1);
		view.find("#morelist").removeAttr("style");
	}
	else {
		hasMore.val(false);
		view.find("#morelist").attr("style", "display:none");
	}

	if (tmp.join("") != "") {
		list.append(tmp.join(""));
		list.listview("refresh");
	}
	
	if (popupMode) {
		updateTargetRecvContact(apiCode);
	}
}

function renderContactGroup(data) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var tmp = [];
	
	var total = data.total;
	var pageSize = data.pagesize;
	var pageNo = parseInt(data.pageno);
	
	if (data.group != undefined) {
		if (data.group.length != undefined) {
			for(var i = 0; i< data.group.length; i++){	
				tmp.push(parseContactGroup(data.group[i]));
			}
		}
		else {
			tmp.push(parseContactGroup(data.group));
		}
	}
	else {
		if (pageNo == 1)
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_contact_nothing") + "</h3></li>");
	}
	
	// 더보기
	if (total > parseInt(pageNo) * pageSize) {
		view.find("#hasMore").val(true);
		view.find("#pno").val(pageNo + 1);
		view.find("#morelist").removeAttr("style");
	}
	else {
		view.find("#hasMore").val(false);
		view.find("#morelist").attr("style", "display:none");
	}
	
	if (tmp.join("") != "") {
		list.append(tmp.join(""));
		list.listview("refresh");
	}
}

function parseContactList(data, popupMode, apiCode) {
	var tmp = [];
	
	// LOG(data);
	
	if (popupMode) {
		if (data.mail != undefined) {
			tmp.push("<li style='padding:7px;' ");
			tmp.push("onclick=\"javascript:clickContactCheckbox('" + apiCode + "', '" + data.id + "', '" +data.name + "', '"+data.mail +"');\">");
			tmp.push("<div class='grouping_left_user'>");
			tmp.push("<input type='checkbox' data-role='none' name='check_"+ apiCode +"' id='" + data.id + "' ");
			tmp.push("onclick=\"javascript:clickContactCheckbox('" + apiCode + "', '" + data.id + "', '" +data.name + "', '"+data.mail +"');\" ");
			tmp.push("value='" + data.name + "' email='" + data.mail + "'>");
			tmp.push("</div>");
		}
		else {
			tmp.push("<li>");
			tmp.push("<div class='grouping_left' style='display:none;'></div>");
		}
		
	} else {
		tmp.push("<li>");
		tmp.push("<div class='grouping_left' style='display:none;'></div>");
		
		if (data.memberType == "1") {
			tmp.push("<a href=\"javascript:showOrgUserDetails('" + data.id + "');\">");
		}
		else {
			tmp.push("<a href=\"javascript:showContactDetails('" + data.id + "', '" + apiCode + "');\">");
		}
	}
	
	tmp.push("<div class='grouping_middle'>");
	tmp.push("<img src='images/people_03.gif' width='35px' height='35px'>");
	tmp.push("<div class='userName'>" + data.name + "</div>");
	tmp.push("<span class='etcInfo'>");
	if (data.mail != undefined && data.mail != "") {
		if (data.company != undefined && data.company != "")
			tmp.push("[" + data.company + "] ");
		
		tmp.push(data.mail);
	}
	tmp.push("</span>");
	
	if (popupMode)
		tmp.push("<div class='phoneNum_popup'>");
	else 
		tmp.push("<div class='phoneNum'>");
	
	if (data.telephoneNumber != undefined && data.telephoneNumber != "")
		tmp.push("<span class='userPhone'>" + data.telephoneNumber + "</span>");
	
	if ((data.telephoneNumber != undefined && data.telephoneNumber != "") && (data.mobile != undefined && data.mobile != ""))
		tmp.push("<span class='userDivider'>|</span>");
	
	if (data.mobile != undefined && data.mobile != "")
		tmp.push("<span class='userMobile'>" + data.mobile + "</span>");
	
	tmp.push("</div></div>");
	
	if (!popupMode) 
		tmp.push("</a>");
	
	tmp.push("</li>");
		
	return tmp.join("");
}

function clickContactCheckbox(type, id, name, email) {
	var view = $.mobile.activePage;	
	var pageId = view.prop("id");
	var chk = undefined;
	if (pageId == "org_select") {
		chk = view.find("#list_" + type).find("li").find("[name=check_" + type + "]").filter("#" +id);
	}
	else if (pageId == "search_result") {
		chk = view.find("#list").find("li").find("[name=check_" + type + "]").filter("#" +id);
	}
		
	if (chk == undefined)	return;
	
	if (chk.attr("checked") != "checked") {
		chk.attr("checked", "checked");
		addTargetRecv(type, id, name, email);
	}
	else {
		chk.removeAttr("checked");
		delteTargetRecv('', type, id);
	}
	
}

function parseContactGroup(group) {
	return "<li><a href=\"javascript:showContactList('group', '" + group.id +"', '" + group.name + "');\"><h3>" + group.name + "</h3></a></li>";
}var GW_CONTROLLER_MAIL_SMTP = "0";

var MAILCONFIG_EXTERNALSEND = "externalsend";			// config - 외부메일허용여부
var MAILCONFIG_INTERNALDOMAIN = "internaldomain";		// config - 내부도메인

var GW_CONTROLLER_MAIL = {
		mailConfigLoaded: false,
		mailConfigData: undefined,
		
		initMailConfig: function(mailConfigData) {
			GW_CONTROLLER_MAIL.mailConfigLoaded = true;
			GW_CONTROLLER_MAIL.mailConfigData = mailConfigData;
		}	
	};

// 편지목록
function showMailList(apiCode) {
	PAGE_CONTROLLER.showPage("mail_list", function() {getMailList(apiCode, 1);});	
	
	chageEditMode(false);
	
	var title = MGW_RES.get("gw_mail_recvlist_label");
	if (apiCode == "sendlist")
		title = MGW_RES.get("gw_mail_sendlist_label");
	else if (apiCode == "templist")
		title = MGW_RES.get("gw_mail_templist_label");
	else if (apiCode == "deletelist")
		title = MGW_RES.get("gw_mail_deletelist_label");
	
	changeTitle(title);	
}

// 편지쓰기 화면
function showMailWrite(){
	// 이전 페이지 모두 Clear
	PAGE_CONTROLLER.cleanViewStack();
	PAGE_CONTROLLER.showPage("mail_write", function() {
		checkSmtpMail();
	});
	
	showWebView();
}

// 조직도 상세, 주소록 상세에서 사용자 클릭(편지 바로 보내기)
function moveMailWriteForm(type, id, name, email) {
	if ( GW_OpenAPI.mail_type != "nouse" ) {
		if (GWPlugin.usePlugin) {
			var boxId = "";
			var msgId = "";
			if(email == undefined) {
				email = "";
				mailtype = "user";
			} else {
				mailtype = "personal";
			}

			var selectedList = {"selectedlist":[{"id":id,"type":mailtype,"name":name,"email":email,"recursive":false}]};
			var isPopup = false;
			GWPlugin.showMailEditorView("new", boxId, msgId, JSON.stringify(selectedList), isPopup, "",
					function(){}, function(){});
		}
		else {
			showMailWriteContact(type, id, name, email);
		}
	}
}

// 주소록에서 편지쓰기
function showMailWriteContact(type, id, name, email) {
	showMailWrite();
	
	if (email == "undefined") {
		addTargetTo(type, id, name);
	} else {
		addTargetTo(type, id, name, email);
	}
	
}

// 조직도 상세, 주소록 상세에서 사용자 클릭 - 받은이 지정
function addTargetTo(type, id, name, email, recursive) {
	var view = $.mobile.activePage;
	if (view.prop("id") != "mail_write")
		return;
	
	if (view.find("[name=li_to]").find(".targetUserList").attr("style") != undefined)
		view.find("[name=li_to]").find(".targetUserList").removeAttr("style");
	
	if (recursive != undefined) 
		view.find("[name=li_to]").find(".targetUserList").append(renderTargetRecv("to", type, id, name, email, recursive));
	else
		view.find("[name=li_to]").find(".targetUserList").append(renderTargetRecv("to", type, id, name, email));
}

// 편지 읽기 (상세보기) 팝업
function showMailDetailsPopup(apiCode, link, passwd) {
	showMailDetails(apiCode, link, passwd, true);	
}

// 편지 읽기 (상세보기)
function showMailDetails(apiCode, link, passwd, isPopup) {
	// 이전 페이지 모두 Clear
	PAGE_CONTROLLER.cleanViewStack();
	
	if(isPopup == true) {
		NAVIBAR_DEF.phone.mail_details.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
		NAVIBAR_DEF.pad.mail_details.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
	} else {
		NAVIBAR_DEF.phone.mail_details.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], 
		                                       ['javascript:goList();', 'javascript:goMenu();']];
		NAVIBAR_DEF.pad.mail_details.left = [0, [], []];
	} 
		
	// 보낸 편지만 툴바 버튼 종류가 다름
	if (apiCode == "sendlist") {
		if (GW_OpenAPI.mail_replyall_use) {
			TOOLBAR_DEF.mail_details = 
				 [6, 
	           [MGW_RES.get("gw_common_delete_label"), MGW_RES.get("gw_mail_confirm_receive_label"), MGW_RES.get("gw_mail_cancel_label") ,
	            MGW_RES.get("gw_mail_fly_label"), MGW_RES.get("gw_mail_reply_label"), MGW_RES.get("gw_mail_replyall_label")], 
	           ["btn_tool_delete.png", "btn_tool_receive.png", "btn_tool_return.png", 
	            "btn_tool_delivery.png", "btn_tool_reply.png", "btn_tool_replyall.png"], 
	           ["javascript:deleteMail();", "javascript:showTrackerList();", "javascript:cancelMail();",
	            "javascript:forwardMail('" + passwd + "');", "javascript:replyMail('" + passwd + "');", "javascript:replyAllMail('" + passwd + "');"]];
		}
		else {
			TOOLBAR_DEF.mail_details = 
				 [5, 
	           [MGW_RES.get("gw_common_delete_label"), MGW_RES.get("gw_mail_confirm_receive_label"), MGW_RES.get("gw_mail_cancel_label") ,
	            MGW_RES.get("gw_mail_fly_label"), MGW_RES.get("gw_mail_reply_label")], 
	           ["btn_tool_delete.png", "btn_tool_receive.png", "btn_tool_return.png", 
		            "btn_tool_delivery.png", "btn_tool_reply.png"], 
	           ["javascript:deleteMail();", "javascript:showTrackerList();", "javascript:cancelMail();",
	            "javascript:forwardMail('" + passwd + "');", "javascript:replyMail('" + passwd + "');"]];
		}
		
	}
	else {
		if (GW_OpenAPI.mail_replyall_use) {
			TOOLBAR_DEF.mail_details = 
				 [4, 
	            [MGW_RES.get("gw_common_delete_label"), MGW_RES.get("gw_mail_fly_label"), 
	             MGW_RES.get("gw_mail_reply_label"), MGW_RES.get("gw_mail_replyall_label")], 
	            ["btn_tool_delete.png", "btn_tool_delivery.png", "btn_tool_reply.png", "btn_tool_replyall.png"], 
	            ["javascript:deleteMail();", "javascript:forwardMail('" + passwd + "');", "javascript:replyMail('" + passwd + "');", "javascript:replyAllMail('" + passwd + "');"]];
		}
		else {
			TOOLBAR_DEF.mail_details = 
				 [3, 
	            [MGW_RES.get("gw_common_delete_label"), MGW_RES.get("gw_mail_fly_label"), 
	             MGW_RES.get("gw_mail_reply_label")], 
	            ["btn_tool_delete.png", "btn_tool_delivery.png", "btn_tool_reply.png"],
	            ["javascript:deleteMail();", "javascript:forwardMail('" + passwd + "');", "javascript:replyMail('" + passwd + "');"]];
		}
		
	}
	
	PAGE_CONTROLLER.showPage("mail_details", function() {
		var view = $.mobile.activePage;	
		
		if(isPopup)
			view.find("#isPopup").val("true");
		
		getMailDetails(apiCode, link, passwd);
	});	
}

function showMailDetails_web(apiCode, link, msgId) {
	var view = $.mobile.activePage;	
	
	if (view.find("#editMode").val() == "true") {
		var chk = view.find("#list li#" +msgId).find("[name=checkMail]");
		
		if (chk.attr("checked") != "checked") {
			chk.attr("checked", "checked");
		}
		else {
			chk.removeAttr("checked");
		}
	}
	else {
		PAGE_CONTROLLER.showPage("mail_details", function() {getMailDetails(apiCode, link);});	
	}
}

// 회수
function cancelMail() {
	var view = $.mobile.activePage;	
	var param = {};
	
	if (view.prop("id") != "mail_details")
		return;

	var apiCode = view.find("#apiCode").val();
	var msgId = view.find("#msgId").val();
	var saveId = view.find("#saveId").val();
	
	if (confirm(MGW_RES.get("gw_msg_mail_confirm_cancel"))) {
		if (saveId != "") {
			param["SAVEID"] = saveId;
		}
		else {
			param["boxid"] = getBoxId(apiCode);
			param["msgid"] = msgId;
		}
		
		GW_PROXY.invokeOpenAPI("mail", "cancel", param, function(data) {
			if (data != undefined)
				alert(data + MGW_RES.get("gw_msg_mail_cancel_success"));
		});
	}
	
}

// 수신확인
function showTrackerList() {
	var view = $.mobile.activePage;	
	if (view.prop("id") != "mail_details")
		return;
	
	getTrackerList(view.find("#apiCode").val(), view.find("#msgId").val(), view.find("#saveId").val()); 
}

// 메일 상세보기에서 삭제
function deleteMail() {
	var param = {};
	
	var view = $.mobile.activePage;	
	var link = view.find("#link").val();
	var msgId = "";
	
	if (view.prop("id") != "mail_details")
		return;
	
	// 개인편지함은 boxid에 한글이 포함되므로 invokeDirect를 사용할수 없음
	var paramArr = parseURLParameter(link);
	
	if (GW_OpenAPI.mail_type == "web") {
		param["msgid"] = paramArr["msgid"];
		param["boxid"] = paramArr["boxid"];
		
		msgId = paramArr["msgid"];
	} else {
		param["saveid"] = paramArr["SAVEID"];
		
		msgId = paramArr["SAVEID"];
	}
	
	if (confirm(MGW_RES.get("gw_msg_mail_confirm_delete"))) {
		GW_PROXY.invokeOpenAPI("mail", "deletemail", param, function(data) {
			var resultCode = undefined;
			
			try {
				if (GW_OpenAPI.mail_type == "web") {
					resultCode = data.channel.status;
				}
				else {
					resultCode = data.status;
				}
			} catch (e) {}
			
			
			if (resultCode != undefined && resultCode == "0") {
				if (GWPlugin.usePlugin)  {
					GWPlugin.deleteMail(msgId, function(){}, function() {});
				}
				else {
					PAGE_CONTROLLER.goBack(true);
					chageEditMode(false);
				}
				
			}
			else {
				alert(MGW_RES.get("gw_msg_common_err"));	
				return;
			}
		});
	}
}

// 여러개의 메일 삭제
function deleteMailList() {
	var idx = 0;
	var idList = new Array();
	
	if ($('input[name=checkMail]:checked').length == 0) {
		alert(MGW_RES.get("gw_msg_mail_noselect"));
		return;
	}
	
	if (confirm(MGW_RES.get("gw_msg_mail_confirm_delete"))) {
		$('input[name=checkMail]:checked').each(function(){
			idList[idx++] = $(this).prop("id");
		});	
	
		if (GW_OpenAPI.mail_type == "web")
			deleteWebMailList(idList);
		else
			deleteDBMailList(idList, 0);
	}
}

// DB 메일 멀티 삭제
function deleteDBMailList(idList, idx) {
	var param = {};
	var saveId = idList[idx];
	
	param["SAVEID"] = saveId;
		
	GW_PROXY.invokeOpenAPI("mail", "deletemail", param, function(data) {
		var resultCode = undefined;
		

		if (GW_OpenAPI.mail_type == "web") {
			resultCode = data.channel.status;
		}
		else {
			resultCode = data.status;
		}
		
		if (resultCode != undefined &&  resultCode == "0") {
			idx++;
			
			if (idList.length > idx) {
				deleteDBMailList(idList, idx);
				return;
			}
			else {
				alert(MGW_RES.get("gw_msg_mail_delete_success"));
				refreshMailList();
			}
		}
		else {
			alert(MGW_RES.get("gw_msg_common_err"));	
			return;
		}
	});
}

// Web 메일 멀티 삭제
function deleteWebMailList(idList) {
	var param = {};
	var view = $.mobile.activePage;	
	var apiCode = view.find("#apiCode").val();
	var msgId = idList[0];
	
	for(var i=1; i<idList.length; i++) {
		msgId += "," + idList[i];
	}
	
	param["boxid"] = getBoxId(apiCode);
	param["msgid"] = msgId;
		
	GW_PROXY.invokeOpenAPI("mail", "deletemail", param, function(data) {
		if (data.channel.status == "0") {
			alert(MGW_RES.get("gw_msg_mail_delete_success"));
			refreshMailList();
		}
		else {
			GW_PROXY.alertErrorMessage(data.channel.status, data.channel.message);
			return;
		}
	});
}

// 개인편지함
function showPersonalMailBox() {
	PAGE_CONTROLLER.showPage("mail_personalbox", function() {getPersonalMailBox();});
}

// 개인편지함 목록
function showPersonalMailList(boxId, title) {
	PAGE_CONTROLLER.showPage("mail_personallist", function() {getPersonalMailList(boxId, 1);});	
	chageEditMode(false);
}

// 메일 그룹 목록
function getMailGroupList() {
	GW_PROXY.invokeOpenAPI("settings", "mailgroup", {}, function(data) {
		renderMailGroupList(data);
	});
}

// 편지쓰기
function sendMail() {
	var view = $.mobile.activePage;	
	var param = {};
	
	if (view.prop("id") != "mail_write")
		return;
	
	if (view.find("#progressSendMail").val() == "true") {
		return;
	}
	
	var subject = view.find("#subject");
	var to = view.find("#to");
	var exto = view.find("#exto");
	var cc = view.find("#cc");
	var bcc = view.find("#bcc");
	
	// 제목 체크
	if (trim(subject.val()) == "") {
		alert(MGW_RES.get("gw_msg_mail_inputsubject"));
		subject.focus();
		return;
	}
	
	// 받은이 체크
	if ((trim(to.html()) == "") && (trim(exto.val()) == "")) {
		alert(MGW_RES.get("gw_msg_mail_inputto"));
		exto.focus();
		return;
	}
			
	// 받은이, 참조, 숨은참조
	var str_to = recipients.getIdValueString($(to).find("a"));
	var str_cc = recipients.getIdValueString($(cc).find("a"));
	var str_bcc = recipients.getIdValueString($(bcc).find("a"));
	
	// 받은이, 참조, 숨은 참조에 해당하는 사용자명, 부서명, 직위
	var str_hwto = recipients.getNameString($(to).find("a"));
	var str_hwcc = recipients.getNameString($(cc).find("a"));
	var str_hwbcc = recipients.getNameString($(bcc).find("a"));
	
	// 수신자 직접 입력 (web 메일에서만 가능)
	if (view.find("#resolveUser").val() == "false") {
		checkResolveUser();
		return;
	}
	else
	{
		str_to += internalUserCheck(str_to) + view.find("#exto_ids").val();
		str_cc += internalUserCheck(str_cc) + view.find("#excc_ids").val();
		str_bcc += internalUserCheck(str_bcc) + view.find("#exbcc_ids").val();
		str_hwto += internalUserCheck(str_hwto) + view.find("#exto_names").val();
		str_hwcc += internalUserCheck(str_hwcc) + view.find("#excc_names").val();
		str_hwbcc += internalUserCheck(str_hwbcc) + view.find("#exbcc_names").val();
		
		if (str_to != "")	param["to"] = str_to;
		if (str_cc != "")	param["cc"] = str_cc;
		if (str_bcc != "")	param["bcc"] = str_bcc;
	
		if (str_hwto != "")	param["hwto"] = str_hwto;
		if (str_hwcc != "")	param["hwcc"] = str_hwcc;
		if (str_hwbcc!= "")	param["hwbcc"] = str_hwbcc;
		
		// 전체답장 jsondata
		replyAllJsonData.initData();
		replyAllJsonData.addData(str_to, str_hwto, 0);
		replyAllJsonData.addData(str_cc, str_hwcc, 1);
		param["jsondata"] = JSON.stringify(replyAllJsonData);

		LOG('sendMail param["to"]=' + param["to"]);
		LOG('sendMail param["cc"]=' + param["cc"]);
		LOG('sendMail param["bcc"]=' + param["bcc"]);
		LOG('sendMail param["hwto"]=' + param["hwto"]);
		LOG('sendMail param["hwcc"]=' + param["hwcc"]);
		LOG('sendMail param["hwbcc"]=' + param["hwbcc"]);
		LOG('sendMail param["jsondata"]=' + param["jsondata"]);
		
		// 본문
		var new_body = view.find("#new_body").val();

		if (GW_OpenAPI.mail_replace_sendbodytag == true) {
			new_body = new_body.replace(/\n/g, "<br/>");
		}
		
		
		// 긴급
		if (view.find("#priority").attr("checked") == "checked") {
			param["priority"] = "1";	// 1:높음, 3:보통(디폴트), 5:낮음
			param["Emergency"] = "1";	// 1:true, 0:false(디폴트)
		}
		else {
			param["priority"] = "3";	// 1:높음, 3:보통(디폴트), 5:낮음
			param["Emergency"] = "0";	// 1:true, 0:false(디폴트)
		}
		
		// 보안
		if (view.find("#security").attr("checked") == "checked") {
			param["secure"] = "true";	// 디폴트 false
			param["Security"] = "1";		// 1:true, 0:false(디폴트)
		}
		else {
			param["secure"] = "false";	// 디폴트 false
			param["Security"] = "0";		// 1:true, 0:false(디폴트)
		}
		
		
		// 전달, 답장, 전체답장
		if ((view.find("#apiCode").val() == "reply") || (view.find("#apiCode").val() == "forward") || (view.find("#apiCode").val() == "replyall")) {
			// 메일 정보
			if (view.find("#boxId").val() != "")	param["boxid"] = view.find("#boxId").val();
			if (view.find("#saveId").val() != "")	param["MSID"] = view.find("#saveId").val();
			
			if (view.find("#apiCode").val() == "reply")			param["APP"] = "R";
			if (view.find("#apiCode").val() == "forward")		param["APP"] = "F";	
			
			// 원문
			if (view.find("#includeOrginal").attr("checked") == "checked") {
				var orgBodyHtml = view.find("#list li").filter("[name=li_orginalBody]").find(".orginalBody").html().trim();
				orgBodyHtml = replaceEscapedHtml(orgBodyHtml);
				
				if (GW_OpenAPI.mail_replace_sendbodytag == true) {
					new_body += orgBodyHtml;
				} 
				else {
					new_body += orgBodyHtml.replace(/\n/g, "").replace("<br/>", "");
				}
			}
			// 전달의 경우만 첨부파일 재전송이 가능함
			if (view.find("#apiCode").val() == "forward" && view.find("#partscount").val() != "0") 
			{
				if (GW_OpenAPI.mail_type == "web") {
					param["msgid"] = view.find("#msgId").val();
					param["partscount"] = view.find("#partscount").val();
					
					view.find("#list li").filter("[name=li_attach]").find("[type=checkbox]:checked").each(function() {
						param[$(this).prop("id")] = $(this).val(); 
					});
				}
				else {
					var FATT = "";
					view.find("#list li").filter("[name=li_attach]").find("[type=checkbox]:checked").each(function() {
						FATT += $(this).val() + ";";
					});
					
					if (FATT != "")		param["FATT"] = FATT;
				}
					
			}
			
		}
	
		param["subject"] = subject.val();
		param["bodytext"] = new_body;
		
		view.find("#resolveUser").val("false");
		view.find("#progressSendMail").val("true");

		GW_PROXY.invokeOpenAPI("mail", "write", param, function(data) {
			var resultCode = undefined;
			
			try {
				if (GW_OpenAPI.mail_type == "web") {
					resultCode = data.channel.status;
				}
				else {
					resultCode = data.status;
				}
			} catch (e) {}
			
			if (resultCode != undefined && resultCode == "0") {
				alert(MGW_RES.get("gw_msg_mail_write_success"));
				
				if (GWPlugin.usePlugin) { // APP, 새편지popup인 경우
					GWPlugin.closePopupViewer("", false);
					return;
				}
				else {
					PAGE_CONTROLLER.goBack(false);
				}
				
			} else {
				alert(MGW_RES.get("gw_msg_common_err"));
				view.find("#progressSendMail").val("false");
				return;
			}
		});

	}

}

function internalUserCheck(user) {
	if(user == "" || user == undefined)
		return "";
	else
		return ",";
}
// 전달
function forwardMail(passwd) {
	if ($.mobile.activePage.prop("id") != "mail_details")
		return;
	
	var link = $.mobile.activePage.find("#link").val();

	if (GWPlugin.usePlugin) {
		var paramArr = parseURLParameter(link);	
		var boxId = paramArr["boxid"];
		var isPopup = ($.mobile.activePage.find("#isPopup").val() == "true" ? true : false);
		if(boxId == "" || boxId == undefined) {
			boxId = getBoxId($.mobile.activePage.find("#apiCode").val());
		}
		GWPlugin.showMailEditorView("forward", boxId, paramArr["msgid"], "", isPopup, passwd,
				function(){}, function(){});
	}
	else {
		showMailTransfer("forward", link);
	}
}

// 답장
function replyMail(passwd) {
	if ($.mobile.activePage.prop("id") != "mail_details")
		return;
	
	var link = $.mobile.activePage.find("#link").val();
	
	if (GWPlugin.usePlugin) {
		var paramArr = parseURLParameter(link);	
		var boxId = paramArr["boxid"];
		var isPopup = ($.mobile.activePage.find("#isPopup").val() == "true" ? true : false);
		if(boxId == "" || boxId == undefined) {
			boxId = getBoxId($.mobile.activePage.find("#apiCode").val());
		}
		GWPlugin.showMailEditorView("reply", boxId, paramArr["msgid"], "", isPopup, passwd,
				function(){}, function(){});		
	}
	else {
		showMailTransfer("reply", link);
	}
}

function showMailTransfer(type, link) {
	PAGE_CONTROLLER.showPage("mail_write", function() {
		checkSmtpMail();
		getMailDetails(type, link);
	});
}

// 전체답장
function replyAllMail(passwd) {
	if ($.mobile.activePage.prop("id") != "mail_details")
		return;
	
	var link = $.mobile.activePage.find("#link").val();
	
	if (GWPlugin.usePlugin) {
		var paramArr = parseURLParameter(link);	
		var boxId = paramArr["boxid"];
		var isPopup = ($.mobile.activePage.find("#isPopup").val() == "true" ? true : false);
		if(boxId == "" || boxId == undefined) {
			boxId = getBoxId($.mobile.activePage.find("#apiCode").val());
		}
		GWPlugin.showMailEditorView("replyall", boxId, paramArr["msgid"], "", isPopup, passwd,
				function(){}, function(){});		
	}
	else {
		showMailTransfer("replyall", link);
	}
}

function cancel_sendMail() {
	var view = $.mobile.activePage;	
	if (view.prop("id") != "mail_write")
		return;
	
	if (confirm(MGW_RES.get("gw_msg_mail_cancel_writeMail"))) {
		if (GWPlugin.usePlugin) {
			hideWebView();
			GWPlugin.closePopupViewer("", false);
			return;
		}
		else {
			PAGE_CONTROLLER.goBack();
		}
	}
}


function getMailSmtp() {
	GW_CONTROLLER_MAIL_SMTP = "500";
	
	if (GW_OpenAPI.mail_type == "db") { // SMTP (DB메일에서만 SMTP 설정 여부 확인)
		GW_PROXY.invokeOpenAPI("mail", "smtp", {}, function(data) {
			if (data.status != undefined) {
				GW_CONTROLLER_MAIL_SMTP = data.status;
			}
		});
	}
}

// SMTP가 설정되지 않은 경우 외부 메일 발송 금지
function checkSmtpMail() {
	var view = $.mobile.activePage;	
	
	if (GW_CONTROLLER_MAIL_SMTP != "0") {
		view.find("#exto").attr("readonly", "readonly");
		view.find("#excc").attr("readonly", "readonly");
		view.find("#exbcc").attr("readonly", "readonly");
		
		view.find("#exto").bind("click", function(e) {javascript:openOrgSelect('to');});
		view.find("#excc").bind("click", function(e) {javascript:openOrgSelect('cc');});
		view.find("#exbcc").bind("click", function(e) {javascript:openOrgSelect('bcc');});
	}
}

function getMailList(apiCode, page_num) {
	var param = {"pno": page_num, "mailbox": apiCode};
	
	
	GW_PROXY.invokeOpenAPI("mail", apiCode, param, function(data) {
		renderMailList(apiCode, data);
	});
}

function getPersonalMailList(boxId, page_num) {
	var param = {"pno": page_num, "personalboxid": boxId};
	
	GW_PROXY.invokeOpenAPI("mail", "personallist", param, function(data) {
		var view = $.mobile.activePage;	
		view.find("#boxId").val(boxId);

		renderMailList("personallist", data);
	});
}

function getMailDetails(apiCode, link, passwd) {
	var param = {}; 
	
	// 개인편지함은 boxid에 한글이 포함되므로 invokeDirect를 사용할수 없음
	var paramArr = parseURLParameter(link);
	
	if (GW_OpenAPI.mail_type == "web") {
		param["msgid"] = paramArr["msgid"];
		var boxId = paramArr["boxid"];
		if(boxId == "" || boxId == undefined) {
			boxId = getBoxId(apiCode);
		}		
		param["boxid"] = boxId;
		if(passwd != undefined && passwd != "") {
			param["passwd"] = passwd;
		}
		
		/*
		 * 0 – 본문보기 1 – 답장 2 – 전달 3 – 전체답장
		 */
		if (apiCode == "reply") {
			param["readtype"] = "1";
		} else if (apiCode == "forward") {
			param["readtype"] = "2";
		} else if (apiCode == "replyall") {
			param["readtype"] = "3";
		} else {
			param["readtype"] = "0";
		}		
	} else {
		param["SAVEID"] = paramArr["SAVEID"];
	}
	
	GW_PROXY.invokeOpenAPI("mail", "details", param, function(data) {
		if (apiCode == "reply" || apiCode == "forward" || apiCode == "replyall") {
			renderMailWrite(apiCode, data);
		}
		else {
			if ($.mobile.activePage.prop("id") != "mail_details")
				return;
			
			$.mobile.activePage.find("#link").val(link);
			renderMailDetails(apiCode, data);
		}
	});
}

function getPersonalMailBox() {
	GW_PROXY.invokeOpenAPI("mail", "personalbox", {}, function(data) {
		renderPersonalMailBox(data);
	});
}

function getTrackerList(apiCode, msgId, saveId) {
	var param = {};
	
	if (saveId != "") {
		param["SAVEID"] = saveId;
	}
	else {
		param["boxid"] = getBoxId(apiCode);
		param["msgid"] = msgId;
	}
	
	GW_PROXY.invokeOpenAPI("mail", "tracker", param, function(data) {
		var strTitle = MGW_RES.get("gw_mail_confirm_receive_label");
		
		if (data != undefined) {
			strTitle += " (" + data.readcount + " / " + data.totalcount + ")";
		}
		
		NAVIBAR_DEF.phone.mail_trackerlist.title = strTitle;
		NAVIBAR_DEF.pad.mail_trackerlist.title = strTitle;
		
		PAGE_CONTROLLER.showPage("mail_trackerlist", function() {
			changeTitle(strTitle);
			
			renderTrackerList(data, apiCode, msgId, saveId);
		});
	});
}

function renderMailList(apiCode, data) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var editMode = view.find("#editMode").val();
	var tmp = [];
	
	var total = data.channel.total;
	var pageSize = data.channel.pagesize;
	var pno = parseInt(data.channel.pageno);
	
	if (GW_OpenAPI.mail_type == "web") {
		pno += 1;
	}
	
	view.find("#apiCode").val(apiCode);
	
	if (data.channel.item != undefined) {
		if (data.channel.item.length == undefined) {
			tmp.push(parseMailList(data.channel.item, apiCode, editMode));
		}
		else {
			for(var i = 0; i< data.channel.item.length;i++){	
				tmp.push(parseMailList(data.channel.item[i], apiCode, editMode));
			}
		}
	} else {
		if (pno == 1) {
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_mail_nolist") + "</h3></li>");
		}
	}
	
	if (tmp.join("") != "") {
		if (pno > 1) {
			list.append(tmp.join(""));
		}
		else {
			list.html(tmp.join(""));
			view.find("#pno").val("1");
		}
		
		list.listview("refresh");
	}
	
	// 더보기
	if (total > parseInt(pno) * pageSize) {
		view.find("#hasMore").val(true);
		view.find("#pno").val(pno + 1);
		view.find("#morelist").removeAttr("style");
	}
	else {
		view.find("#hasMore").val(false);
		view.find("#morelist").attr("style", "display:none");
	}
}

function renderMailDetails(apiCode, data) {
	var view = $.mobile.activePage;	
	if (view.prop("id") != "mail_details")
		return;
		
	var list = view.find("#list");
	view.find("#attach").attr("style", "display:none;");
	view.find("#apiCode").val(apiCode);
	
	// 버튼 조정
	if (apiCode == "sendlist") {
		view.find(".ui-bar").find("[name=sendlistBtn]").show();
	}
	else {
		view.find(".ui-bar").find("[name=sendlistBtn]").hide();
	}
	
	// 전체 답장 기능 사용
	if (GW_OpenAPI.mail_replyall_use) {
		view.find(".ui-bar").find("[name=replyallBtn]").show();
	}
	
	var li1 = list.find("li").eq(0);
	var li2 = list.find("li").eq(1);
	var li3 = list.find("li").eq(2);
	var li4 = list.find("li").filter("#attach");
	
	if (data.channel != undefined) {
		var mail = data.channel[0];

		// 메일 정보
		if (mail.msgid != undefined)		view.find("#msgId").val(trim(mail.msgid).replace("\n", ""));
		if (mail.boxid != undefined)		view.find("#boxId").val(mail.boxid);
		if (mail.saveid != undefined)		view.find("#saveId").val(mail.saveid);
		
		// 보낸이
		if (mail.senderid != "") { // 조직도 사용자
			li1.find("div").eq(1).html("<a href=\"javascript:showUserDetails('" + mail.senderid + "');\" class='btn_user blue'><span>" 
					+ trim(mail.sender) + "</span><span class='viewUser'></span></a>");	
		}
		else {
			li1.find("div").eq(1).html("<span class='onlyTxt'>" + mail.sender + "</span>");
		}
		
		// 받은이
		if (mail.to != undefined && mail.to != "")
			li2.find("div").eq(1).html("<span class='onlyTxt'>" + mail.to.trim().replace(/</gi, '&lt;').replace(/>/gi, '&gt;') + "</span>");
		
		// 보낸날짜
		if (GW_OpenAPI.mail_type == "web") 
			list.find(".dateinfo").text(convertDateFormat(mail.sentdateformat, false));
		else
			list.find(".dateinfo").text(convertDateFormat(mail.sentDate, false));
		
		// 긴급, 보안 메일
		if (GW_OpenAPI.mail_type == "web") {
			if (mail.priority == "1") 
				list.find(".title").append("<span class='mail_emergency'></span>");
			
			if (mail.security == "true")
				list.find(".title").append("<span class='mail_security'></span>");
		}
		else {
			if (mail.emergency == "true") 
				list.find(".title").append("<span class='mail_emergency'></span>");
			
			if (mail.security == "true")
				list.find(".title").append("<span class='mail_security'></span>");
		}
		
		// 제목
		list.find(".title").append(mail.title);
		
		// 본문
		var mailBody = "";
		
		if (GW_OpenAPI.mail_type == "web") {
			mailBody = trim(mail.body).replace(/\n/g, "<br/>").replace("\n", "");
		} else {
			mailBody = trim(mail.body).replace("\n", "");
		}
		
		mailBody = replaceEscapedHtml(mailBody);
		list.find(".contents").html(mailBody);
		
		// 본문의 외부 링크 변환
		converPopupLink(list.find(".contents"));

		var images = list.find(".contents").find("img");
		$.each(images, function(i, value) {
			var image = images.eq(i);
			if (image.attr("src") != undefined &&
					(!startsWith(image.attr("src"), 'http://') && !startsWith(image.attr("src"), 'https://')) && image.attr("src").indexOf('/wma/cid') > -1) {
				var cid_path = image.attr("src").substring(image.attr("src").indexOf("/wma/cid"));
				var imgsrc = GW_OpenAPI.serverIP + GW_OpenAPI.streamPATH + cid_path;
				image.attr("src", imgsrc);
			}
		});
		
		// 참조
		if (mail.cc != undefined && mail.cc != "") {
			li3.removeAttr("style");
			li3.find("div").eq(1).html("<span class='onlyTxt'>" + mail.cc.trim().replace(/</gi, '&lt;').replace(/>/gi, '&gt;') + "</span>");
		}
		
		// 첨부
		if (mail.attaches != undefined && (typeof mail.attaches != "string")) {
			var tmp = [];
			for(var i=0; i<mail.attaches.length; i++) {
				var category = "attach";
				var file = mail.attaches[i];
				var filename = file.att_title.replace(/'/g, '').replace(/#/g, '');
				
				if (GW_OpenAPI.mail_type == "web") {
					tmp.push("<li><a class='attach_lst' onclick=\"javascript:DOC_HANDLER.setEvent(event);\" href=\"javascript:DOC_HANDLER.showAttach('"+ trim(file.att_link2) +"','"+ category
							+ "','" + filename+"')\" class='btn_txt gray attach'><span style='white-space: pre-line;'>" + file.att_title + " (");
				}
				else {
					tmp.push("<li><a class='attach_lst' onclick=\"javascript:DOC_HANDLER.setEvent(event);\" href=\"javascript:DOC_HANDLER.showAttach('"+ trim(file.att_link) +"','"+ category
							+ "','" + filename+"')\" class='btn_txt gray attach'><span style='white-space: pre-line;'>" + file.att_title + " (");
				}
				
				if (GW_OpenAPI.mail_type == "web") {
					if (getFileSizeUnit() == "byte") {
						tmp.push((parseInt(file.att_size)*1024) + MGW_RES.get("gw_common_byte_label"));
					}
					else {
						tmp.push(file.att_size + MGW_RES.get("gw_common_kilobyte_label"));
					}
				}
				else {
					tmp.push(convertFileSizeUnit(file.att_size));
				}
							
				tmp.push(")</span></a></li>");
			}
			
			li4.removeAttr("style");
			li4.find("#attachInfo").html(mail.attaches.length + MGW_RES.get("gw_common_piece_label"));
			list.find("#attachList").html(tmp.join(""));
		}
		
		list.removeAttr("style");
	}
	else {
		// TODO
	}
	
}

function toggleAttach(view_id, flag) {
	var view = $.mobile.activePage;	
	if (view.prop("id") != view_id)
		return;
	
	if (flag) {
		view.find("#attachList").removeAttr("style");
		view.find("#list").find("#btnToggleAttach").find("span").eq(0).attr("class", "btn_fd");
		view.find("#list").find("#linkToggleAttach").attr("href", "javascript:toggleAttach('" + view_id + "',false)");
	}
	else {
		view.find("#attachList").attr("style", "display:none;");
		view.find("#list").find("#btnToggleAttach").find("span").eq(0).attr("class", "btn_unfd");
		view.find("#list").find("#linkToggleAttach").attr("href", "javascript:toggleAttach('" + view_id + "',true)");
	}
}

function renderPersonalMailBox(data) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var tmp = "";
	
	for (var i=0; i<data.length; i++) {
		tmp += parsePersonalMailBox(data[i]);
	}
	
	if (tmp != "") {
		list.html(tmp);
		list.listview("refresh");
	}
}

function renderTrackerList(data, apiCode, msgId, saveId) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var tmp = [];
	
	view.find(".trackerState").find(".label").text(MGW_RES.get("gw_mail_entire_state_label") + ": ");
	view.find("#apiCode").val(apiCode);
	view.find("#msgId").val(msgId);
	view.find("#saveId").val(saveId);

	if (data != undefined) {
		if (data.receiver != undefined) {
			if (data.receiver.length == undefined) {
				tmp.push(parseTrackerList(data.receiver));
			}
			else {
				for(var i = 0; i<data.receiver.length;i++){
					tmp.push(parseTrackerList(data.receiver[i]));
				}
			}
		}
		else {
			tmp.push("<li><h3>" +  MGW_RES.get("gw_msg_mail_noReceiver") + "</h3></li>");
		}
	}
	else {
		tmp.push("<li><h3>" +  MGW_RES.get("gw_msg_mail_noReceiver") + "</h3></li>");
	}
	
	list.html(tmp.join(""));
	list.listview("refresh");
}

function renderMailWrite(apiCode, data) {
	var view = $.mobile.activePage;	
	if (view.prop("id") != "mail_write")
		return;
	
	var list = view.find("#list");
	
	if (data.channel != undefined) {
		var mail = data.channel[0];
		
		// 메일 정보
		if (mail.saveid != undefined)	view.find("#saveId").val(mail.saveid);
		if (mail.msgid != undefined)	view.find("#msgId").val(trim(mail.msgid).replace("\n", ""));
		if (mail.boxid != undefined)	view.find("#boxId").val(mail.boxid);

		view.find("#apiCode").val(apiCode);
		
		// 제목
		var title = "";
		if (GW_OpenAPI.mail_type != "web") {
			if (apiCode == "reply") {
				title = "[" + MGW_RES.get("gw_mail_reply_label") + "]";
			}
			else if (apiCode == "forward") {
				title = "[" + MGW_RES.get("gw_mail_fly_label") + "]";
			}
		}
		view.find("#subject").val(title + mail.title);
		
		// 긴급
		if (mail.priority == "1")
			view.find("#priority").attr("checked", "checked");
		
		// 보안
		if (mail.security == "true")
			view.find("#security").attr("checked", "checked");
		
		// 받은이 (답장일 경우)
		if (apiCode == "reply") {
			if (trim(mail.senderid) != '') {
				view.find("#to").append(renderTargetRecv("to", "user", mail.senderid.trim(), mail.sender.trim()));
				view.find("#to").removeAttr("style");
			}
			else {
				view.find("#exto").val(mail.sender.trim());
			}
		}
		
		// 받은이, 참조 (전체답장일 경우)
		if (apiCode == "replyall") {
			var exto = ""; 
			var excc = ""; 
			
			if (mail.to != undefined && mail.to.trim() != "") {
				var toArr = mail.to.split(";");
				for (var i=0; i<toArr.length; i++) {
					replyAllRecipient.parseRecipient(toArr[i]);
					if (replyAllRecipient.signature != '%') {
						view.find("#to").append(renderTargetRecv("to", replyAllRecipient.type, replyAllRecipient.id, replyAllRecipient.name, undefined, replyAllRecipient.recursive));
						view.find("#to").removeAttr("style");
					} else {
						exto += replyAllRecipient.name + ",";
					}
				}
			}

			if (mail.cc != undefined && mail.cc.trim() != "") {
				var ccArr = mail.cc.split(";");
				for (var i=0; i<ccArr.length; i++) {
					replyAllRecipient.parseRecipient(ccArr[i]);
					if (replyAllRecipient.signature != '%') {
						view.find("#cc").append(renderTargetRecv("cc", replyAllRecipient.type, replyAllRecipient.id, replyAllRecipient.name, undefined, replyAllRecipient.recursive));
						view.find("#cc").removeAttr("style");
					} else {
						excc += replyAllRecipient.name + ",";
					}
				}
			}
			
			if(exto != "") exto = exto.substr(0, exto.length-1);
			if(excc != "") excc = excc.substr(0, excc.length-1);
			view.find("#exto").val(exto);
			view.find("#excc").val(excc);

			if (view.find("#cc").css("display") != "none" || excc != "") {
				toggleCC(true);
			}
		}
		
		// 본문
		list.find("[name=li_orginalBody]").removeAttr("style");
		var new_body = "";
		var str_to = "";
		var str_cc = "";
		if (GW_OpenAPI.mail_type != "web") {
			
			// 원본 메시지의 수신
			var toArr = new Array();
			
			if (GW_OpenAPI.mail_type == "web") {
				toArr = mail.to.split(",");
			} else {
				toArr = mail.to.split(";");
			}
			
			for (var i=0; i<toArr.length; i++) {
				if (toArr[i].split("<").length > 1)		str_to += trim(toArr[i].split("<")[0]);
				else									str_to += trim(toArr[i]);
				
				if (i<toArr.length-1)	str_to += ", ";
			}
			
			// 원본 메시지의 참조
			if (mail.cc != undefined) {
				var ccArr = new Array();
				
				if (GW_OpenAPI.mail_type == "web") {
					ccArr = mail.cc.split(",");
				} else {
					ccArr = mail.cc.split(";");
				}
	 			
				for (var i=0; i<ccArr.length; i++) {
					if (ccArr[i].split("<").length > 1)		str_cc += trim(ccArr[i].split("<")[0]);
					else									str_cc += trim(ccArr[i]);
					
					if (i<ccArr.length-1)	str_cc += ", ";
				}
			}
		
			new_body += "<br><br>-----" + MGW_RES.get("gw_mail_dbmail_original_message") + "-----<br>";
			
			new_body += "<font style='font-weight:bold;'>" + MGW_RES.get("gw_mail_dbmail_sender_label") + "</font> : " 
					 + mail.sender.trim().replace('<', '&lt;').replace('>', '&gt;') + "<br>";
			new_body += "<font style='font-weight:bold;'>" + MGW_RES.get("gw_mail_dbmail_to_label") + "</font> : " 
			         + str_to + "<br>";
			
			if (str_cc != "") {
				new_body += "<font style='font-weight:bold;'>" + MGW_RES.get("gw_mail_cc_label") + "</font> : " 
		         + str_cc + "<br>";
			}
			
			
			if (GW_OpenAPI.mail_type == "web") {
				new_body += "<font style='font-weight:bold;'>" + MGW_RES.get("gw_mail_dbmail_date_label") + "</font> : " 
				 + convertDateFormat(mail.sentdateformat, false) + "<br>";
			} else {
				new_body += "<font style='font-weight:bold;'>" + MGW_RES.get("gw_mail_dbmail_date_label") + "</font> : " 
				 + convertDateFormat(mail.sentDate, false) + "<br>";
			}
			
			
			new_body += "<font style='font-weight:bold;'>" + MGW_RES.get("gw_mail_title_label") + "</font> : " 
			         + trim(mail.title).replace('<', '&lt;').replace('>', '&gt;') + "<br><br>";
		}
			
		var mailBody = mail.body.trim(); 
		mailBody= replaceEscapedHtml(mailBody);
		
		list.find(".orginalBody").html(new_body + mailBody.replace(/\n/g, "").replace("<br/>", ""));
		
		var images = list.find(".orginalBody").find("img");
		
		$.each(images, function(i, value) {
			var image = images.eq(i);
			if ((!startsWith(image.attr("src"), 'http://') && !startsWith(image.attr("src"), 'https://')) && image.attr("src").indexOf('/wma/cid') > -1) {
				var cid_path = image.attr("src").substring(image.attr("src").indexOf("/wma/cid"));
				var imgsrc = GW_OpenAPI.serverIP + GW_OpenAPI.streamPATH + cid_path;
				image.attr("src", imgsrc);
			}
		});
		
		
		// 첨부
		if (apiCode == "forward" && mail.attaches != undefined && (typeof mail.attaches != "string")) {
			if (mail.attaches.length > 0) {
				list.find("[name=li_attach]").removeAttr("style");
			
				var tmp = [];
				var category = "attach";
				for(var i=1; i<mail.attaches.length+1; i++) {
					var file = mail.attaches[i-1];
					
					tmp.push("<div><input type='checkbox' data-role='none' ");
					
					if (GW_OpenAPI.mail_type == "web") {
						tmp.push("id='parts" + i + "' name='parts" + i + "' value='" + file.att_order + "' ");
					}
					else {
						tmp.push("id='parts" + i + "' name='parts" + i + "' value='" + file.att_ord + "' ");
					}

					tmp.push("checked='checked'>" + file.att_title + "(");
					if (GW_OpenAPI.mail_type == "web") {
						if (getFileSizeUnit() == "byte") {
							tmp.push((parseInt(file.att_size)*1024) + MGW_RES.get("gw_common_byte_label"));
						}
						else {
							tmp.push(file.att_size + MGW_RES.get("gw_common_kilobyte_label"));
						}
					}
					else {
						tmp.push(convertFileSizeUnit(file.att_size));
					}
					
					tmp.push(")</div>");
					
					if (i == mail.attaches.length) {
						if (GW_OpenAPI.mail_type == "web") {
							view.find("#partscount").val(file.att_order);
						}
						else {
							view.find("#partscount").val(file.att_ord);
						}
					}
				}
				
				list.find("[name=li_attach]").find("div").eq(1).html(tmp.join(""));
			}
		}
	}
}

function parseMailList(mail, apiCode, editMode) {
	var tmp = [];
	
	if (GW_OpenAPI.mail_type == "web")
		tmp.push("<li id='" + trim(mail.msgid).replace("\n", "") + "'>");
	else
		tmp.push("<li id='" + mail.saveid + "'>");
	
	tmp.push("<div class='grouping_left'>");
	tmp.push("<input type='checkbox' data-role='none' name='checkMail'");

	if (GW_OpenAPI.mail_type == "web")
		tmp.push(" id='" + trim(mail.msgid).replace("\n", "") + "' ");
	else
		tmp.push(" id='" + mail.saveid + "' ");
	
	if (editMode == "true")		
		tmp.push(">");
	else						
		tmp.push("style='display:none;'>");
	
	if (mail.status == "5" || mail.status == "11") {
		tmp.push("<span class='recoveryMail'");
	}
	else if (mail.status == "2") {
		tmp.push("<span class='readMail'");
	}
	else {
		tmp.push("<span class='newMail'");
	}
	
	if (editMode == "true")	
		tmp.push(" style='display:none;'/>");
	else 
		tmp.push("/>");
	
	tmp.push("</div>");

	
	// TODO : 보안 메일 암호 확인 부분 - 팝업 UI 구현 시 적용해야 함.
	if ((mail.security == "true") && (apiCode != "templist")) {
		if (GW_OpenAPI.mail_type == "web")
			tmp.push("<a href=\"javascript:openSecurityMail('" + apiCode + "', '" +  mail.link2 + "');\">");
		else 
			tmp.push("<a href=\"javascript:openSecurityMail('" + apiCode + "', '" +  mail.link + "');\">");
	}
	else {
		if (GW_OpenAPI.mail_type == "web")
			tmp.push("<a href=\"javascript:showMailDetails_web('" + apiCode + "', '" +  mail.link2 + "', '" + mail.msgid +"');\">");
		else 
			tmp.push("<a href=\"javascript:showMailDetails_web('" + apiCode + "', '" +  mail.link + "', '" + mail.saveid +"');\">");
		
	}
	
	tmp.push("<div class='grouping_middle'>");
	
	if (apiCode == "recvlist" || apiCode == "personallist") {
		tmp.push("<span class='sender'>" + mail.sender + "</span>");
	}
	else if (apiCode == "deletelist") {
		if (mail.sender != undefined)
			tmp.push("<span class='sender'>" + mail.sender + "</span>");
		else
			tmp.push("<span class='sender'>" + mail.receiver + "</span>");
	}
	else if (apiCode == "sendlist") {
		if (GW_OpenAPI.mail_type == "web")
			tmp.push("<span class='sender'>" + mail.receiver + "</span>");
// else
// tmp.push("<span class='sender'>" + mail.recipient + "</span>");
		
	}
	else if (apiCode == "templist") {
		tmp.push("<span class='sender'>　</span>");
	}
	
	tmp.push("<span class='sentdate");
			
	if (mail.isattachment == "true")	
		tmp.push(" attach'>");
	else								
		tmp.push("'>");
	
	if (GW_OpenAPI.mail_type == "web")
		tmp.push(convertDateFormat(mail.sentdateformat, true) +"</span>");
	else
		tmp.push(convertDateFormat(mail.sentDate, true) +"</span>");
	
	if (mail.title.trim() != "")
		tmp.push("<h3>"+mail.title+"</h3>");	
	else
		tmp.push("<h3>" + MGW_RES.get("gw_mail_notitle_label") + "</h3>");
	
	tmp.push("</div></a></li>");
	
	return tmp.join("");
}

function openSecurityMail(apiCode, link) {
	var view = $.mobile.activePage;	
	
	// 초기화
	view.find("#confirmPasswordDialog #password").val("");
	view.find("#confirmPasswordDialog").find("#password").focus();
	
	view.find("#confirmPasswordDialog #mailApiCode").val(apiCode);
	view.find("#confirmPasswordDialog #mailLink").val(link);
	
	view.find("#confirmPasswordDialog").popup("open");
}

function confirmPassword() {
	var view = $.mobile.activePage;	
	var param = {};
	
	var password = view.find("#confirmPasswordDialog #password").val();
	var apiCode = view.find("#confirmPasswordDialog #mailApiCode").val();
	var link = view.find("#confirmPasswordDialog #mailLink").val();
	
	param["pwd"] = password;
	
	GW_PROXY.invokeOpenAPI("org", "checkloginpasswd", param, function(data) {
		if (data.status == "0") {
			showMailDetails(apiCode, link, password);
		}
		else {
			alert(MGW_RES.get("gw_msg_common_wrong_password"));
			return;
		}
	});
}

function closePasswordDialog() {
	var view = $.mobile.activePage;	
	view.find("#confirmPasswordDialog").popup("close");
}

function parseTrackerList(recv) {
	var tmp = [];
	tmp.push("<li><div class='grouping_left'>");
	
	/**
	 * 수신 상태 STATUS_UNREAD = 1; STATUS_SEEN = 2; STATUS_ANSWERED = 3;
	 * STATUS_DELETED = 4; STATUS_UNSENT = 5; STATUS_INVALID = 6;
	 * STATUS_CANCELED = 7; STATUS_SUCCEED_TO_CANCEL = 8;
	 * STATUS_FAILED_TO_CANCEL = 9; STATUS_DELETED_USER = 10;
	 * STATUS_CANCELED_NOT_DELETED = 11;
	 */
	
	if (recv.status == "5" || recv.status == "7" || recv.status == "11")
		tmp.push("<span class='recoveryMail'></span>");
	else if (recv.status == "2") 
		tmp.push("<span class='readMail'></span>");
	else 
		tmp.push("<span class='newMail'></span>");
	
	tmp.push("</div><div class='grouping_middle'>");
	tmp.push("<h3>" + recv.name.replace('<', '&lt;').replace('>', '&gt;') + "</h3>");
	
	if (recv.readdate == "") {
		if (recv.status == "4") 
			tmp.push("<span class='trackerMsg'>" + MGW_RES.get("gw_msg_mail_already_recovered") + "</span>");
		else if (recv.status == "7" || recv.status == "11") 
			tmp.push("<span class='trackerMsg'>" + MGW_RES.get("gw_msg_mail_canceled_not_deleted") + "</span>");
		else
			tmp.push("<span class='trackerMsg'>" + MGW_RES.get("gw_msg_mail_not_read") + "</span>");
	}
	else {
		tmp.push("<span class='trackerDate'>" + convertDateFormat(recv.readdate, false) + "</span>");
	}

	tmp.push("</div></li>");
	
	return tmp.join("");
}

function parsePersonalMailBox(data) {
	var tmp = "";
	
	if (GW_OpenAPI.mail_type == "web")
		tmp += "<li><a href=\"javascript:showPersonalMailList('" + data["@path"] + "', '" + data["@text"] + "');\">";
	else
		tmp += "<li><a href=\"javascript:showPersonalMailList('" + data["@id"] + ", '" + data["@text"] + "');\">";
	
	tmp += "<h3>" + data["@text"] + "</h3>";
	tmp += "</a></li>";

	if (data.tree != undefined) {
		if (data.tree.length != undefined) {
			for (var i=0; i<data.tree.length; i++) {
				tmp += parsePersonalMailBox(data.tree[i]);
			}
		}
		else {
			tmp += parsePersonalMailBox(data.tree);
		}
	}
	
	return tmp;
}

function refreshMailList() {
	var view = $.mobile.activePage;	
	view.find("#hasMore").val(false);
	getMailList(view.find("#apiCode").val(), 1);
}

function getMoreMailList() {
	var view = $.mobile.activePage;	

	if (view.find("#hasMore").val() == "true") {
		// 더보기가 완료 되기전에 중복 호출 방지하기 위해
		view.find("#hasMore").val(false);
		getMailList(view.find("#apiCode").val(), view.find("#pno").val());
	}
}

function refreshPersonalMailList() {
	var view = $.mobile.activePage;	
	view.find("#hasMore").val(false);
	getPersonalMailList(view.find("#boxId").val(), 1);
}

function getMorePersonalMailList() {
	var view = $.mobile.activePage;	
	if (view.find("#hasMore").val() == "true") {
		// 더보기가 완료 되기전에 중복 호출 방지하기 위해
		view.find("#hasMore").val(false);
		
		getPersonalMailList(view.find("#boxId").val(), view.find("#pno").val());
	}
}

function refreshTrackerList() {
	var view = $.mobile.activePage;	
	getTrackerList(view.find("#apiCode").val(), 
			view.find("#msgId").val(), view.find("#saveId").val());
}

function chageEditMode(flag) {
	var view = $.mobile.activePage;	
	view.find("#editMode").val(flag);
	
	if (flag) {
		view.find(".ui-bar").find("[name=mailReadMode]").hide();
		view.find(".ui-bar").find("[name=mailEditMode]").show();
		view.find(".ui-bar").find("#deselect").hide();
		
		view.find(".grouping_left").find("[name=checkMail]").show();
		view.find(".grouping_left").find("[name=checkMail]").removeAttr('checked');
		view.find(".grouping_left").find("span").hide();
	}
	else {
		view.find(".ui-bar").find("[name=mailEditMode]").hide();
		view.find(".ui-bar").find("[name=mailReadMode]").show();
		
		view.find(".grouping_left").find("[name=checkMail]").hide();
		view.find(".grouping_left").find("span").show();
	}
}

function selectAllMail(flag) {
	var view = $.mobile.activePage;	
	
	if (flag){
		view.find(".ui-bar").find("#selectAll").hide();
		view.find(".ui-bar").find("#deselect").show();
		
		view.find(".grouping_left").find("[name=checkMail]").attr("checked", "checked");
	}
	else {
		view.find(".ui-bar").find("#selectAll").show();
		view.find(".ui-bar").find("#deselect").hide();

		view.find(".grouping_left").find("[name=checkMail]").removeAttr("checked");
	}
}

function getBoxId(apiCode) {
	if (apiCode == "recvlist" || apiCode == "personallist")		return "000000002";
	if (apiCode == "sendlist")									return "000000001";
	if (apiCode == "deletelist") 								return "000000004";
	if (apiCode == "templist")									return "000000005";
}

function toggleCC(flag) {
	var view = $.mobile.activePage;	
	if (flag) {
		view.find("#list").find("[name=li_cc]").show();
		view.find("#list").find("[name=li_bcc]").show();
		view.find("#list").find("#btnToggleCC").find("span").eq(0).attr("class", "btn_fd");
		view.find("#list").find("#btnToggleCC").attr("href", "javascript:toggleCC(false)");
	}
	else {
		view.find("#list").find("[name=li_cc]").hide();
		view.find("#list").find("[name=li_bcc]").hide();
		view.find("#list").find("#btnToggleCC").find("span").eq(0).attr("class", "btn_unfd");
		view.find("#list").find("#btnToggleCC").attr("href", "javascript:toggleCC(true)");
	}
	
}

// 수신자 선택 화면 이동
function openOrgSelect(recvType) {
	var view = $.mobile.activePage;	
	var recvlist = view.find("#" + recvType).html().trim();
	
	NAVIBAR_DEF.phone.org_select.title = MGW_RES.get("gw_mail_select_recv");
	NAVIBAR_DEF.pad.org_select.title = MGW_RES.get("gw_mail_select_recv");
	
	showOrgSelect("mail", recvType, recvlist);
}

// 수신자 직접 입력 체크
function checkResolveUser() {
	var view = $.mobile.activePage;	
	var param = {};
	var items = "";
	var recv_types = [];
	
	var exto = view.find("#exto").val().trim();
	var excc = view.find("#excc").val().trim();
	var exbcc = view.find("#exbcc").val().trim();
	
	var to_email = "";
	var cc_email = "";
	var bcc_email = "";
	
	var bAllowEnternalDomainSend = true;
	// 받은이
	if (exto != "") {
		exto =exto.replace(",", ";");
		var extoArr = exto.split(";");
		for (var i=0; i<extoArr.length+1; i++) {
			if (extoArr[i] != "" && extoArr[i] != undefined) {
				if(bAllowEnternalDomainSend)
					bAllowEnternalDomainSend = checkAllowExternalDomainSend(extoArr[i]);
				
				if(checkEmailFormat(extoArr[i])) { // 이메일 형식
					to_email += extoArr[i] + ",";
				}
				else { // 수신자 검색
					items += extoArr[i] + ",";
					recv_types.push("to");
				}
			}
		}
	}
	
	// 참조
	if (excc != "") {
		excc = excc.replace(",", ";");
		var exccArr = excc.split(";");
		for (var i=0; i<exccArr.length+1; i++) {
			if (exccArr[i] != "" && exccArr[i] != undefined) {
				if(bAllowEnternalDomainSend)
					bAllowEnternalDomainSend = checkAllowExternalDomainSend(exccArr[i]);
				
				if(checkEmailFormat(exccArr[i])) { // 이메일 형식
					cc_email += exccArr[i] + ",";
				}
				else { // 수신자 검색
					items += exccArr[i] + ",";
					recv_types.push("cc");
				}
			}
		}
	}
	
	// 숨은참조
	if (exbcc != "") {
		exbcc = exbcc.replace(",", ";");
		var exbccArr = exbcc.split(";");
		for (var i=0; i<exbccArr.length+1; i++) {
			if (exbccArr[i] != "" && exbccArr[i] != undefined) {
				if(bAllowEnternalDomainSend)
					bAllowEnternalDomainSend = checkAllowExternalDomainSend(exbccArr[i]);
				
				if(checkEmailFormat(exbccArr[i])) { // 이메일 형식
					bcc_email += exbccArr[i] + ",";
				}
				else { // 수신자 검색
					items += exbccArr[i] + ",";
					recv_types.push("bcc");
				}
			}
		}
	}
	
	if(!bAllowEnternalDomainSend) {
		alert(MGW_RES.get("gw_msg_mail_externaldomain_not_sended"));
		return;
	}
	
	// 이메일
	view.find("#exto_ids").val(to_email);
	view.find("#exto_names").val(to_email);
	view.find("#excc_ids").val(cc_email);
	view.find("#excc_names").val(cc_email);
	view.find("#exbcc_ids").val(bcc_email);
	view.find("#exbcc_names").val(bcc_email);
	
	if (items == "") {
		view.find("#resolveUser").val("true");
		sendMail();
	}
	else {
		view.find("#resolveUser").val("false");
		if (GW_OpenAPI.mail_type == "web" && GW_OpenAPI.mail_directinputuser_use) {
			param["items"] = items;
			
			GW_PROXY.invokeOpenAPI("mail", "resolveuser", param, function(data) {
				renderResolveUser(data, recv_types);
			});
		}
		else {
			alert(MGW_RES.get("gw_msg_mail_invalid_email"));
		}
	}
}

function renderResolveUser(data, recv_types) {
	var view = $.mobile.activePage;	
	var tmp = [];

	if (data.channel.item != undefined) {
		if (data.channel.item.length != undefined) {
			for (var i=0; i<data.channel.item.length; i++) {
				tmp.push(parseResolveUser(data.channel.item[i], recv_types[i]));
			}
		}
		else {
			tmp.push(parseResolveUser(data.channel.item, recv_types[0]));
		}
		
		view.find("#resolveUserDialog #resolveUserDiv").html(tmp.join(""));

		var resolveUserList = view.find("#resolveUserDialog #resolveUserDiv").find("tr");
		var isCompleted = true;
		
		$.each(resolveUserList, function(i, value) {
			if (resolveUserList.eq(i).find("input[type=hidden]").length > 0) {
				resolveUserList.eq(i).attr("style", "display:none;");
				isCompleted = isCompleted && true;
			}
			else {
				isCompleted = false;
			}	
		});
		
		if (isCompleted) {
			selectResolveUser(false);
		}
		else {
			// 팝업창의 좌측, 우측 버튼 비활성화
			if (GWPlugin.usePlugin) {	
				GWPlugin.setStateLeftBarButton(1, [false], function(){}, function(){});
				GWPlugin.setStateRightBarButton(1, [false], function(){}, function(){});
			}
			
			
			view.find("#resolveUserDialog").popup("open");
			view.find("#resolveUserDialog").on( "popupafterclose", function( event, ui ) { 
				// 팝업창의 좌측, 우측 버튼 활성화
				if (GWPlugin.usePlugin) {	
					GWPlugin.setStateLeftBarButton(1, [true], function(){}, function(){});
					GWPlugin.setStateRightBarButton(1, [true], function(){}, function(){});
				}
			});
		}
	}
}

function parseResolveUser(item, recv_type) {
	var tmp = [];
	if (item == undefined) {
		// 검색결과 없음
		tmp.push("<tr name='" + recv_type + "'><td>" + item + "</td>");
		tmp.push("<td name='none' style='color:#FF0000;'>" + MGW_RES.get("gw_msg_mail_mismatch_org") + "</td>");
	}
	else {
		if (item.list != undefined) {
			tmp.push("<tr name='" + recv_type + "'><td>" + item.itemname + "</td>");
			tmp.push("<td name='multi'>");
			tmp.push("<select>");
			
			for (var i=0; i<item.list.length; i++) {
				if (item.list[i].parname != undefined) { // 부서
					tmp.push("<option type='dept' id='" + item.list[i].id + "' name='" + item.list[i].parname + "'>" 
							+ convertDeptName(item.list[i].parname) + "</option>");
					
				}
				else { // 사용자
					tmp.push("<option type='user' id='" + item.list[i].id + "' name='" + item.list[i].dept + "'>" 
							+ convertDeptName(item.list[i].dept) + "</option>");
				}
			}
			tmp.push("</select></td>");
		}
		else {
			if (item.userinfo != undefined) { // 사용자
				tmp.push("<tr name='" + recv_type + "'><td>" + item.itemname + "</td>");
				tmp.push("<td name='user'>");
				
				if (item.userinfo.length != undefined) {
					tmp.push("<select>");
					
					for (var i=0; i<item.userinfo.length; i++) {
						tmp.push(parseResolveUserInfo(item.userinfo[i]));
					}
					
					tmp.push("</select>");
				}
				else {
					tmp.push("<input type='hidden' id='"+ item.userinfo.id +"'/>");
				}
				
				tmp.push("</td>");
			}
			else if (item.deptinfo != undefined) { // 부서
				tmp.push("<tr name='" + recv_type + "'><td>" + item.itemname + "</td>");
				tmp.push("<td name='dept'>");
				
				if (item.deptinfo.length != undefined) {
					tmp.push("<select>");
					
					for (var i=0; i<item.deptinfo.length; i++) {
						tmp.push(parseResolveDeptInfo(item.deptinfo[i]));
					}
					
					tmp.push("</select>");
				}
				else {
					tmp.push("<input type='hidden' id='"+ item.deptinfo.id +"'/>");
				}
				
				tmp.push("</td>");
			}
			else {
				tmp.push("<tr name='" + recv_type + "'><td>" + item + "</td>");
				tmp.push("<td name='none' style='color:#FF0000;'>" + MGW_RES.get("gw_msg_mail_mismatch_org") + "</td>");
			}
		}
	}
	tmp.push("</tr>");
	return tmp.join("");
}

function parseResolveUserInfo(userInfo) {
	var tmp = "";
	tmp += "<option type='user' id='" + userInfo.id + "' name='" + userInfo.dept + "'>" + convertDeptName(userInfo.dept) + "</option>";
	return tmp;
}

function parseResolveDeptInfo(deptInfo) {
	var tmp = "";
	tmp += "<option type='dept' id='" + deptInfo.id + "' name='" + deptInfo.name + "'>" + convertDeptName(deptInfo.name) + "</option>";
	return tmp;
}

function convertDeptName(dept) {
	var deptArr = dept.split(".");
	
	if (deptArr.length > 1) {
		return deptArr[deptArr.length-1];
	}
	
	return dept;
}

function renderMailGroupList(data) {
	var view = $.mobile.activePage;	
	var list = view.find("#list_group");
	var tmp = [];
	
	if (data != undefined && data.length > 0) {
		for(var i=0; i<data.length; i++) {
			tmp.push("<li style='padding:7px;' ");
			tmp.push("onclick=\"javascript:clickMailGroupCheckbox('" + data[i].id + "', '" +data[i].name + "');\">");
			tmp.push("<div style='float:left; padding-right:5px; padding-top:5px;'>");
			tmp.push("<input type='checkbox' data-role='none' name='check_group' id='" + data[i].id + "' ");
			tmp.push("onclick=\"javascript:clickMailGroupCheckbox('" + data[i].id + "', '" +data[i].name + "');\" ");
			tmp.push("value='" + data[i].name + "'>");
			tmp.push("</div>");
			tmp.push("<h3>" + data[i].name + "</h3></li>");
		}
	}
	else {
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_contact_nothing") + "</h3></li>");
	}
	
	if (tmp.join("") != "") {
		list.html(tmp.join(""));
		list.listview("refresh");
	}
	
	view.find("#group_loaded").val("true");
	updateTargetRecvGroup();
	
}

function clickMailGroupCheckbox(id, name) {
	var view = $.mobile.activePage;	
	var	chk = view.find("#list_group").find("li").find("[name=check_group]").filter("#" +id);
	
	if (chk == undefined)	
		return;
	
	if (chk.attr("checked") != "checked") {
		chk.attr("checked", "checked");
		addTargetRecv("group", id, name);
	}
	else {
		chk.removeAttr("checked");
		delteTargetRecv("group", id);
	}
}

function selectResolveUser(isClose) {
	var view = $.mobile.activePage;	
	var trlist = view.find("#resolveUserDialog #resolveUserDiv").find("tr");
	
	var exto_ids = "";
	var exto_names = "";
	var excc_ids = "";
	var excc_names = "";
	var exbcc_ids = "";
	var exbcc_names = "";
	
	$.each(trlist, function(i, value) {
		var type = trlist.eq(i).attr("name");
		var name = trlist.eq(i).find("td").eq(0).text();
		var moreInfo = trlist.eq(i).find("td").eq(1);
		var id = "";
		
		if(moreInfo.attr("name") == "none") { // 검색 결과 없음
			view.find("#resolveUser").val("false");
			closeResolveUserDialog();
			
			return;
		}
		else {
			if (moreInfo.find("input[type=hidden]").length > 0) {
				id = moreInfo.find("input[type=hidden]").prop("id");
			}
			else {
				var sameNameCnt = moreInfo.find("option").length;
				var selectOpt =moreInfo.find("option:selected");
				
				if (sameNameCnt > 1) { // 동명이인 사용자가 있는 경우 부서+이름
					if (selectOpt.attr("type") == "dept")
						name = "$" + selectOpt.attr("name") + "." + name;
					else
						name = selectOpt.attr("name") + "." + name;
				}
				id = selectOpt.prop("id");
			}
		}
	
		if (type == "to") {
			exto_ids += id + ",";
			exto_names += name + ",";
		}
		else if (type == "cc") {
			excc_ids += id + ",";
			excc_names += name + ",";
		}
		else if (type == bcc) {
			exbcc_ids += id + ",";
			exbcc_names += name + ",";
		}
	});
	
	view.find("#exto_ids").val(view.find("#exto_ids").val() + exto_ids);
	view.find("#exto_names").val(view.find("#exto_names").val() + exto_names);
	view.find("#excc_ids").val(view.find("#excc_ids").val() + excc_ids);
	view.find("#excc_names").val(view.find("#excc_names").val() + excc_names);
	view.find("#exbcc_ids").val(view.find("#exbcc_ids").val() + exbcc_ids);
	view.find("#exbcc_names").val(view.find("#exbcc_names").val() + exbcc_names);
	
	if (exto_ids != "" || excc_ids != "" || exbcc_ids != "")
		view.find("#resolveUser").val("true");
	else 
		view.find("#resolveUser").val("false");
	
	if (isClose)
		view.find("#resolveUserDialog").popup("close");
	
	sendMail();
}

function closeResolveUserDialog() {
	var view = $.mobile.activePage;	
	view.find("#resolveUser").val("false");
	view.find("#resolveUserDialog").popup("close");
}

var recipients = {
	getIdValueString: function(list){
		var toStr = "";
		
		$.each(list, function(i, value) {
			var id = list.eq(i).prop("id");
			var type = list.eq(i).attr("type");
			var name = list.eq(i).attr("name");
			var email = list.eq(i).attr("email");
			var recursive = list.eq(i).attr("recursive");
			
			if (type == "personal" || type == "department") {
				toStr += name + " <" + email + ">";
				
				if (i < list.length-1)	toStr += ",";
			}
			else {
				toStr += recipients.getIdString(id, type, recursive);
				
				if (i < list.length-1)	toStr += ",";
			}
		});
		
		return toStr;
	},
	getNameString: function(list){
		var toStr = "";
		
		$.each(list, function(i, value) {
			var id = list.eq(i).prop("id");
			var type = list.eq(i).attr("type");
			var name = list.eq(i).attr("name");
			var email = list.eq(i).attr("email");
			var recursive = list.eq(i).attr("recursive");
			
			if (type == "dept" && recursive) {
				toStr += "$+" + name;
				
				if (i < list.length-1)	toStr += ",";
			}
			else if (type == "dept") {
				toStr += "$" + name;
				
				if (i < list.length-1)	toStr += ",";
			}
			else if (type == "pos") {
				toStr += "~" + name;
				
				if (i < list.length-1)	toStr += ",";
			}
			else if (type == "personal" || type == "department") {
				toStr += name + " <" + email + ">";
				
				if (i < list.length-1)	toStr += ",";
			}
			else {
				toStr += name;
				
				if (i < list.length-1)	toStr += ",";
			}
		});
		
		return toStr;
		
	},
	getIdString: function(id, type, recursive){
		if(GW_OpenAPI.mail_type == "web") {
			var USER   = '@';
			var DEPT   = '$';
			var DEPTR  = '$+';
			var USERGROUP = '*';
			var DEPTMGROUP = '*';
			var CONTACTGROUP = '#';
			var EMAIL  = '';
			var PRIADDR = '';
			var DEPTADDR = '';
			var POS    = '~'; 
			var EMP    = '^';
		}
		else {
			var USER   = 'u';
			var DEPT   = 'd';
			var DEPTR  = 'e';
			var USERGROUP = 'g';
			var DEPTMGROUP = 'm';
			var CONTACTGROUP = '';
			var EMAIL  = '';
			var PRIADDR = '';
			var DEPTADDR = '';
			var POS    = '~';
			var EMP    = '^';
		}
		
		if(type == 'user') {
			return USER + id;
		}
		else if(type == 'dept' && recursive){
			return DEPTR + id;
		}
		else if(type == 'dept') {
			return DEPT + id;
		}
		else if(type == 'contactgroup') {
			return CONTACTGROUP + id;
		}
		else if(type == 'group') {
			return USERGROUP + id;
		}
		else if(type == 'pos') {
			return POS + id;
		}
		else {
			return id;
		}
	}
};

var replyAllJsonData = {
	recipients: [],		// 메일작성시 JsonData 포맷이므로 변수명 수정하면 안됨
		
	initData: function() {
		replyAllJsonData.recipients = [];
	},
	addData: function(strId, strName, recipientType){
		var idArr = strId.split(",");
		var nameArr = strName.split(",");

		for (var i=0; i<idArr.length; i++) {
			if (idArr[i] == undefined || idArr[i] == "")
				continue;

			var signatureArr = ["@", "$", "#", "%", "*", "~"];
			var isEmail = true;
			for(var j=0; j<signatureArr.length; j++) {
				if(idArr[i].substr(0, 1) ==  signatureArr[j]) {
					isEmail = false;
					break;
				}
			}
			if(isEmail) 
				idArr[i] = "%" + idArr[i];

			replyAllRecipient.parseRecipient(idArr[i] + ":" + nameArr[i]);
			var obj = undefined;
			if(replyAllRecipient.signature != "%") {
				obj = new replyAllJsonDataItem(replyAllRecipient.id, nameArr[i], recipientType, "", replyAllRecipient.signature);
			} else {
				obj = new replyAllJsonDataItem("", nameArr[i], recipientType, replyAllRecipient.id, replyAllRecipient.signature);
			}
			replyAllJsonData.recipients.push(obj);
		}
	}
}

function replyAllJsonDataItem(id, name, recipientType, email, objectType) {
	this.id = id;
	this.name = name;
	this.recipientType = recipientType;
	this.email = email;
	this.objectType = objectType;
}

var replyAllRecipient = {
	signature : "",
	type : "",
	id : "",
	name : "",
	recursive : undefined,

	parseRecipient: function(recipient) {
		var id = recipient.split(":")[0].trim();
		var name = recipient.split(":")[1].trim();
		if (id.substr(0, 2) == "$+") {
			replyAllRecipient.signature = id.substr(0, 2);
			replyAllRecipient.id = id.substr(2);
			replyAllRecipient.name = name.substr(2);
			replyAllRecipient.recursive = true;
		} else {
			replyAllRecipient.signature = id.substr(0, 1);
			replyAllRecipient.id = id.substr(1);
			if(replyAllRecipient.signature == "$" || replyAllRecipient.signature == "~") 
				replyAllRecipient.name = name.substr(1);
			else 
				replyAllRecipient.name = name;
			replyAllRecipient.recursive = undefined;
		}
		replyAllRecipient.type = replyAllRecipient.getTypeBySignature();
	},

	getTypeBySignature: function(){
		switch (replyAllRecipient.signature) {
		case '@':
			return 'user';
			break;
		case '$':
			return 'dept';
			break;
		case '$+':
			return 'dept';
			break;
		case '#':
			return 'contactgroup';
			break;
		case '%':
			return 'email';
			break;
		case '*':
			return 'group';
			break;
		case '~':
			return 'pos';
			break;
		}		
	}
};

function getMailConfig(callback) {
	if (!GW_CONTROLLER_MAIL.mailConfigLoaded) {
		GW_PROXY.invokeOpenAPI("mail", "mailconfig", {}, function(data) {
			GW_CONTROLLER_MAIL.initMailConfig(data);
			callback();
		});
	} else {
		callback();
	}
}

function getMailConfigValue(key) {
	return GW_CONTROLLER_MAIL.mailConfigData[key];		
}

// 이메일 Domail 체크
function checkAllowExternalDomainSend(email) {
	if("false" == getMailConfigValue(MAILCONFIG_EXTERNALSEND)) return true;
	
	if(!checkEmailFormat(email)) 
		return true;
	
	var parts = email.split('@');
	if (parts.length == 2) {
		if (parts[1] == getMailConfigValue(MAILCONFIG_INTERNALDOMAIN)) {
			return true;
		}
	}
	
	return false;
}
function replaceEscapedHtml(mailBody){
	if((/&lt;\/br&gt;/gi).test(mailBody) || (/&amp;gt;/gi).test(mailBody)) {
		mailBody = mailBody.replace(/&lt;\/br&gt;/gi, "<br/>").replace(/&amp;gt;/gi, ">").replace(/&amp;lt;/gi, "<");
	}
	return mailBody;
}var GW_CONTROLLER_ORG = {
	category: "org", 		// 1)org(조직도), 2)mail(수신자,참조,숨은참조 선택), 3)settings
							// (부재설정), 4)square
	selectMode: false,		// 1)false (디폴트: 조직도 화면), 2)true (조직도 선택 화면)
	callbackId: "",    		// 콜백 엘리먼트 Id
	selectedList: "", 		// 선택된 목록
		
	init: function(category, callbackId, selectedList) {
		GW_CONTROLLER_ORG.category = category;
		
		if (category == "mail" || category == "settings" || category == "square" || category == "schedule") {
			GW_CONTROLLER_ORG.selectMode = true;
			GW_CONTROLLER_ORG.callbackId = callbackId;
			GW_CONTROLLER_ORG.selectedList = selectedList;
		}
		else {
			GW_CONTROLLER_ORG.selectMode = false;
		}
	}
};

// 조직도 화면
function showOrgTree(deptId) {
	hideWebView();
	
	// 이전 페이지 모두 Clear
	PAGE_CONTROLLER.cleanViewStack();
	
	GW_CONTROLLER_ORG.init("org");
	
	if (GW_OpenAPI.org_rootdept_use) {
		NAVIBAR_DEF.phone.org_tree.right = [2, [MGW_RES.get("gw_org_root_label"), MGW_RES.get("gw_common_search_label")], 
			                 			      ["javascript:showOrgTree('root');", "javascript:showSearch(false, 'org');"]];
		NAVIBAR_DEF.pad.org_tree.right = [2, [MGW_RES.get("gw_org_root_label"), MGW_RES.get("gw_common_search_label")], 
			                 			      ["javascript:showOrgTree('root');", "javascript:showSearch(false, 'org');"]];
	}
	
	PAGE_CONTROLLER.showPage("org_tree", function() {getOrgTree(deptId);});
}

// 부서 경로
function getDeptPath(deptId) {
	var param = {};
	var curDeptName = "";
	
	if (deptId == "root") {
		param["base"] = "";
	}
	else if (deptId != undefined) {
		param["base"] = deptId;
	}
	
	GW_PROXY.invokeOpenAPI("org", "deptpath", param, function(data) {
		// alert("################### ");
		// alert("[deptpath] \n"+JSON.stringify(data));
		curDeptName = renderDeptPath(data, deptId);
		getDeptList(deptId, curDeptName);
	});
}

// 하위 부서
function getDeptList(deptId, curDeptName) {
	var param = {};
	
	if (deptId == "root") {
		param["base"] = "";
	}
	else if (deptId != undefined) {
		param["base"] = deptId;
	}
	
	GW_PROXY.invokeOpenAPI("org", "depttree", param, function(data) {
		// alert("[depttree] \n"+JSON.stringify(data));
		renderDeptList(data, curDeptName);
		
		getUserList(deptId, curDeptName);
	});
}

// 구성원
function getUserList(deptId, curDeptName) {
	var param = {};
	
	if (deptId != undefined)
		param["base"] = deptId;
	
	GW_PROXY.invokeOpenAPI("org", "userlist", param, function(data) {
		// alert("[userlist] \n"+JSON.stringify(data));
		renderUserList(data, curDeptName);
	});
}

// 조직도선택 화면 from App
function showOrgSelectForApp(category, callbackId, jsonSelectedList) {
	LOG("jsonSelectedList=" + jsonSelectedList);

	hideWebView();
	PAGE_CONTROLLER.cleanViewStack();

	var htmlSelectedList = convertSelectedListJsonToHtml(callbackId, jsonSelectedList);
	showOrgSelect(category, callbackId, htmlSelectedList);
}

function convertSelectedListJsonToHtml(callbackId, jsonSelectedList) {
	var tmp = [];

	var item = $.parseJSON(jsonSelectedList).selectedlist;
	for(var i = 0; i< item.length;i++){	
		if (item[i].recursive != undefined) 
			tmp.push(renderTargetRecv(callbackId, item[i].type, item[i].id, item[i].name, item[i].email, item[i].recursive));
		else
			tmp.push(renderTargetRecv(callbackId, item[i].type, item[i].id, item[i].name, item[i].email));
	}

	return tmp.join("");
}

// 조직도선택 화면
function showOrgSelect(category, callbackId, selectedList, deptId) {
	GW_CONTROLLER_ORG.init(category, callbackId, selectedList);
	
	if (GWPlugin.usePlugin) {
		GWPlugin.setSelectedTabBarItem(0, function(){}, function(){});
	}
	
	if (startsWith(GW_OpenAPI.version, '8')) {
		if (category == "mail") {
			TABBAR_DEF.org_select = [3, 
			                        [MGW_RES.get("gw_org_tree_label"), MGW_RES.get("gw_contact_personal_label"), 
			                         MGW_RES.get("gw_contact_dept_label")], 
			                        ["ico_org.png", "ico_contact_personal.png", "ico_contact_dept.png"], 
			                        ["javascript:showOrgSelectTab('org')", "javascript:showOrgSelectTab('personal')",
			                         "javascript:showOrgSelectTab('department')"]];
		} else if (category == "square" || category == "settings" || category == "schedule") {
			TABBAR_DEF.org_select = [0, [], [], []];			
		}
	}
	
	PAGE_CONTROLLER.showPage("org_select", function() {getOrgTree(deptId); });
	var view = $.mobile.activePage;
	if (selectedList != "") {
		view.find(".targetUserList").html(selectedList);
		view.find(".targetUserDiv").removeAttr("style");
		updateTargetRecvOrg();
	}
	else {
		view.find(".targetUserDiv").attr("style", "display:none");
	}

	if (category == "settings" || category == "square" || category == "schedule") {
		// 네비바 Hide
		view.find("[data-role=navbar]").attr("style", "display:none");
	}
}

// 수신자선택_네비바 선택에 따른 탭페이지 변화
function showOrgSelectTab(tab) {
	if (tab == "group") {
		selectGroupTab();
		
		// 검색 버튼 비활성화
		if (GWPlugin.usePlugin) 
			GWPlugin.setStateRightBarButton(2, [false, true], function(){}, function(){});
	}
	else {
		// 검색 버튼 활성화
		if (GWPlugin.usePlugin) 
			GWPlugin.setStateRightBarButton(2, [true, true], function(){}, function(){});
	}
	
	if (tab == "org")				selectOrgTab();
	if (tab == "personal")			selectPersonalTab();
	if (tab == "department")		selectDepartmentTab();
}

function selectOrgTab() {
	var view = $.mobile.activePage;
	
	view.find("#currentTab").val("org");
	
	// 조직도 이외 페이지 Hide
	view.find("#list_personal").attr("style", "display:none;");
	view.find("#list_department").attr("style", "display:none;");
	view.find("#list_group").attr("style", "display:none;");
	view.find(".pathBarArea").removeAttr("style");
	view.find("#morelist").attr("style", "display:none;");
	
	// 조직도 페이지만 Show
	view.find("#list").removeAttr("style");
	
	// 검색
	view.find("#searchBtn").show();
}

function showSearchOrgSelect() {
	var view = $.mobile.activePage;
	var currentTab = view.find("#currentTab").val();
	
	GW_CONTROLLER_ORG.selectedList = view.find(".targetUserList").html();
	
	if (currentTab == "org"){
		showSearch(GW_CONTROLLER_ORG.selectMode, view.find("#currentTab").val());
	}
	else { // 주소록
		showSearch(GW_CONTROLLER_ORG.selectMode, "contact" , view.find("#currentTab").val());
	}
	
}

function selectPersonalTab() {
	var view = $.mobile.activePage;
	
	view.find("#currentTab").val("personal");
	
	// init
	if (view.find("#personal_loaded").val() == "false") {
		getContactList(true, "personal", 1);
		view.find("#personal_loaded").val("true");
	}
	
	// 더보기 버튼
	if (view.find("#personal_hasMore").val() == "true") {
		view.find("#morelist").removeAttr("style");
	}
	else {
		view.find("#morelist").attr("style", "display:none;");
	}
	
	// 개인주소록 페이지 Show
	view.find("#list_personal").removeAttr("style");
	
	// 개인주소록 이외 페이지 Hide
	view.find("#list").attr("style", "display:none;");
	view.find("#list_department").attr("style", "display:none;");
	view.find("#list_group").attr("style", "display:none;");
	view.find(".pathBarArea").attr("style", "display:none;");
	
	// 검색
	view.find("#searchBtn").show();
}


function selectDepartmentTab() {
	var view = $.mobile.activePage;
	
	view.find("#currentTab").val("department");
	
	// init
	if (view.find("#department_loaded").val() == "false") {
		getContactList(true, "department", 1);
		view.find("#department_loaded").val("true");
	}
	
	// 더보기 버튼
	if (view.find("#department_hasMore").val() == "true") {
		view.find("#morelist").removeAttr("style");
	}
	else {
		view.find("#morelist").attr("style", "display:none;");
	}
	
	// 부서주소록 페이지 Show
	view.find("#list_department").removeAttr("style");

	// 부서주소록 이외 페이지 Hide
	view.find("#list_personal").attr("style", "display:none;");
	view.find("#list").attr("style", "display:none;");
	view.find("#list_group").attr("style", "display:none;");
	view.find(".pathBarArea").attr("style", "display:none;");
	
	// 검색
	view.find("#searchBtn").show();
}

function selectGroupTab() {
	var view = $.mobile.activePage;
	
	view.find("#currentTab").val("group");
	
	// init
	if (view.find("#group_loaded").val() == "false") {
		getMailGroupList();
	}
	
	// 그룹 페이지 Show
	view.find("#list_group").removeAttr("style");
	
	// 그룹 이외 페이지 Hide
	view.find("#list_personal").attr("style", "display:none;");
	view.find("#list_department").attr("style", "display:none;");
	view.find("#list").attr("style", "display:none;");
	view.find(".pathBarArea").attr("style", "display:none;");
	view.find("#morelist").attr("style", "display:none;");
	
	// 그룹에서는 검색 제공하지 않음
	view.find("#searchBtn").hide();
}

// 조직도 상세
function showOrgDetails(evt, type, id, name, email, deptcode) {
	var view = $.mobile.activePage;	
	
	if (GW_CONTROLLER_ORG.selectMode) {
		if (type == "dept" && evt.clientX > 200) { // 하위부서 이동
			getDeptPath(id, name);
		}
		else {
			var chk = view.find("#list li").find("[name=check_" + type + "]").filter("#" +id);
			
			if (chk.attr("checked") != "checked") {
				if (GW_CONTROLLER_ORG.category == "settings") {
					uncheckUser();
				}
				
				chk.attr("checked", "checked");
				addTargetRecv(type, id, name, email);
			}
			else {
				chk.removeAttr("checked");
				delteTargetRecv(GW_CONTROLLER_ORG.callbackId, type, id);
			}
		}
	}
	else {
		if (type == "dept") { // 하위부서 이동
			getDeptList(id, name);
		}
		else if (type == "user") { // 사용자 상세 화면 이동
			showUserDetails(id);
		}
	}
}

function showUserDetailsPopup(id) {
	showUserDetails(id, true);	
}

function showUserDetails(id, isPopup) {
	if(isPopup == true) {
		NAVIBAR_DEF.phone.org_user.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
		NAVIBAR_DEF.pad.org_user.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
	} else {
		NAVIBAR_DEF.phone.org_user.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], 
			                 			      ['javascript:PAGE_CONTROLLER.goBack();', 'javascript:goMenu();']];
		NAVIBAR_DEF.pad.org_user.left = [1, [APP_CONTROLLER.backBtn], ['javascript:PAGE_CONTROLLER.goBack();']];
	}
	
	PAGE_CONTROLLER.showPage("org_user", function() {
		var view = $.mobile.activePage;	
		
		if(isPopup)
			view.find("#isPopup").val("true");
		
		getUserDetails(id);
	});	
}

function getUserDetails(id) {
	var param = {"id": id};
	
	GW_PROXY.invokeOpenAPI("settings", "getuserinfo", param, function(data) {
		renderUserDetails(data);
	});
}

function renderUserDetails(data) {
	var view = $.mobile.activePage;
	if(view.prop("id") != "org_user")
		return;
	console.log(JSON.stringify(data));
	var isPopup = ($.mobile.activePage.find("#isPopup").val() == "true" ? true : false);
	if (data != undefined) {
		if (data.name != "") {
			// kosmes 이메일 클릭시 메일 앱 띄우기로
			view.find("#userName").html("<a style='color:#2489ce;' href=\"javascript:popupLink('mailto:" + data.homepage + "');\">" + data.name + "</a>");
			/*if(isPopup) {
				view.find("#userName").html("<a style='color:#2489ce;' href=\"#\">" + data.name + "</a>");				
			} else {
				view.find("#userName").html("<a style='color:#2489ce;' href=\"javascript:moveMailWriteForm('user','"+ data.id + "','" + data.name + "');\">" + data.name + "</a>");				
			}*/
		}
		
		if (data.department != "")			view.find("#deptName").html(data.department);
		if (data.position != "")			view.find("#gradeName").html(data.position);
		
		if (data.telephoneNumber != "")	{
			var tag = "";
			tag += "<a href=\"javascript:popupLink('tel:" + data.telephoneNumber + "');\" class=\"call\"></a>";
			tag += "<span class='phone'>" + data.telephoneNumber + "</span>";

			view.find("#li_officePhone").html(tag);
		}
		
		if (data.mobile != "") {
			var tag = ""; 
			tag += "<a href=\"javascript:popupLink('tel:" + data.mobile + "');\" class=\"call\"></a>";
			tag += "<a href=\"javascript:popupLink('sms:" + data.mobile + "');\" class=\"sms\"></a>";
			tag += "<span class=\"mobile\">" + data.mobile + "</span>";
			view.find("#li_mobile").html(tag);
		}
		
		// kosmes 이메일 클릭시 메일 앱 띄우기로
		/*if (data.mail != "") {
			if(isPopup == true) {
				view.find("#li_email span").html("<a style='color:#2489ce;' href=\"#\">" + data.mail + "</a>");				
			} else {
				view.find("#li_email span").html("<a style='color:#2489ce;' href=\"javascript:moveMailWriteForm('user','" + data.id + "','" + data.name + "');\">" + data.mail + "</a>");				
			}
		}*/
		if (data.homepage != "") {
			view.find("#li_email span").html("<a style='color:#2489ce;' href=\"javascript:popupLink('mailto:" + data.homepage + "');\">" + data.homepage + "</a>");
		}
		
		if (data.faxNumber != "") {
			view.find("#li_officeFax span").text(data.faxNumber);
		}
		
		if (data.business != "") {
			view.find("#li_job span").text(data.business);
		}
		
		if (data.photo != undefined) {
			var imgSrc = GW_OpenAPI.serverIP + GW_OpenAPI.streamPATH + data.photo.replace(/(^\s*)/, "");
			var category = "userpic";
			
			view.find("#a_userPic").attr("onclick", "javascript:DOC_HANDLER.setEvent(event)");
			view.find("#a_userPic").attr("href", "javascript:DOC_HANDLER.showAttach('" + data.id + "', '" + category + "', '" + data.id + "')");
			view.find(".userInfoImg").attr("src", imgSrc);
		}
	}
}

function getOrgTree(deptId) {
	getDeptPath(deptId);
}

function renderDeptList(data, curDeptName) {
	var view = $.mobile.activePage;
	
	if(view.prop("id") != "org_tree" && view.prop("id") != "org_select" && view.prop("id").indexOf("search_result") < 0) 
		return "";
	
	var list = view.find("#list");
	var tmp = [];
	
	if (curDeptName != undefined) {
		tmp.push("<li data-role='list-divider' class='list_divider'><span>" + curDeptName + " - " + MGW_RES.get("gw_org_subdept_label"));
	}
		
	if (data.length > 0) {
		if (curDeptName != undefined)	tmp.push("(" + data.length + ")</span>");
		
		if (GW_CONTROLLER_ORG.selectMode && (GW_CONTROLLER_ORG.category == "mail" || GW_CONTROLLER_ORG.category == "schedule")) {
			tmp.push("<span class='includeSubDept'><input id='includeSubDept' type='checkbox' data-role='none'>");
			tmp.push("<label for='includeSubDept'>"+ MGW_RES.get("gw_org_includesub_label") + "</label></span>");
		}
		
		tmp.push("</li>");
		
		for (var i=0; i<data.length; i++) {
			tmp.push("<li>");

			if (GW_CONTROLLER_ORG.selectMode && (GW_CONTROLLER_ORG.category == "mail" || GW_CONTROLLER_ORG.category == "square" || GW_CONTROLLER_ORG.category == "schedule")) {
				tmp.push("<div class='grouping_left'>");
				tmp.push("<input type='checkbox' data-role='none' name='check_dept' ");
				tmp.push("onclick=\"javascript:clickDeptCheckbox(this, '"+data[i]["@deptcode"]+"');\"");
				tmp.push("id='" + data[i]["@id"] + "' value='" + data[i]["@text"] + "'>");
				tmp.push("</div>");
				
				tmp.push("<a href='#' onclick=\"javascript:showOrgDetails(event, 'dept', '" + data[i]["@id"] + "', '" + data[i]["@text"] + "', '"+data[i]["@deptcode"]+"')\">");
			}
			else {
				tmp.push("<a href=\"javascript:getOrgTree('" + data[i]["@id"] + "')\">");
			}
			
			tmp.push("<h3>" + data[i]["@text"] + "</h3>");
			
			// tmp.push("<span class='ui-li-count'>" + data[i]["@userCount"] +
			// "</span>");
			tmp.push("</a></li>");
		}
	}
	else {
		tmp.push("(0)</li>");
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_org_subdept_nolist") + "</h3></li>");
	}

	if(view.prop("id") != "org_tree" && view.prop("id") != "org_select" && view.prop("id").indexOf("search_result") < 0) 
		return "";
	
	list.html(tmp.join(""));
	list.listview("refresh");
}

function clickDeptCheckbox(obj, deptcode) {
	var view = $.mobile.activePage;
	var id = $(obj).prop("id");
	var name = $(obj).attr("value");

	if ($(obj).attr("checked") == "checked") {
		if (view.find("#includeSubDept").attr("checked") == "checked")
			addTargetRecv("dept", id, name, undefined, true, deptcode);
		else
			addTargetRecv("dept", id, name, undefined, false, deptcode);
	}
	else {
		delteTargetRecv(GW_CONTROLLER_ORG.callbackId, "dept", id);
	}

}

function renderSearchDeptResult(data) {
	var view = $.mobile.activePage;
	var list = view.find("#list");
	var tmp = [];

	if (data.length > 0) {
		for (var i=0; i<data.length; i++) {
			tmp.push("<li>");

			if (GW_CONTROLLER_ORG.selectMode && (GW_CONTROLLER_ORG.category == "mail" || GW_CONTROLLER_ORG.category == "square" || GW_CONTROLLER_ORG.category == "schedule")) {
				tmp.push("<div class='grouping_left'>");
				tmp.push("<input type='checkbox' data-role='none' name='check_dept' ");
				tmp.push("onclick=\"javascript:clickDeptCheckbox(this);\"");
				tmp.push("id='" + data[i].id + "' value='" + data[i].name + "'>");
				tmp.push("</div>");
			}
			
			tmp.push("<a href=\"javascript:showSearchSubDept('" + data[i].id + "')\">");
			tmp.push("<h3>" + data[i].name + "</h3>");
			
			// tmp.push("<span class='ui-li-count'>" + data[i].userCount +
			// "</span>");
			tmp.push("</a></li>");
		}
	}
	else {
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_common_nosearchdata") + "</h3></li>");
	}
	
	list.html(tmp.join(""));
	list.listview("refresh");
}

function renderSearchUserResult(data) {
	renderUserList(data);
}

function showSearchSubDept(deptId) {
	PAGE_CONTROLLER.showCopyPage("search_result", deptId);

	var view = $.mobile.activePage;
	view.find("#list").removeClass("contactList");
	
	if (GW_CONTROLLER_ORG.selectMode)	
		view.find("[data-role=header] a").eq(1).removeAttr("style");
	
	changeTitle("");
		
	getOrgTree(deptId);
	
	if (GW_CONTROLLER_ORG.selectMode) {
		if (GW_CONTROLLER_ORG.selectedList != "") {
			view.find(".targetUserList").html(GW_CONTROLLER_ORG.selectedList);
			view.find(".targetUserDiv").removeAttr("style");
			updateTargetRecvOrg();
		}
	}
}

function renderDeptPath(data, deptId) {
	var view = $.mobile.activePage;
	if(view.prop("id") != "org_tree" && view.prop("id") != "org_select" && view.prop("id").indexOf("search_result") < 0) 
		return "";
	
	var pathBar = view.find(".pathBar");
	var totalCnt = 0;
	var tmp = [];
	var curDeptName = "";
	
	// 권한별 조직열람
	var viewDeptId = "";
	var viewIndex = data.length;

	if(GW_OpenAPI.org_open_type == "1") {			// 기관만 조회
		viewDeptId = GW_OpenAPI.rep_deptid;
	} else if(GW_OpenAPI.org_open_type == "2") {	// 본인부서만 조회
		viewDeptId = sessionStorage["deptid"];
	}
	if(GW_OpenAPI.org_open_type == "1" || GW_OpenAPI.org_open_type == "2") {
		$(data).each(function(idx, deptData){ 
			if(viewDeptId == deptData.id) {
				viewIndex = idx + 1;
			}
		});
		
		data = data.slice(0, viewIndex);
	}
	
	view.find("#list li").remove(); // listview 초기화
	
	// 최상위 부서 Path
	if (deptId == "root") {
		tmp.push("<a href='javascript:getOrgTree(undefined)' class='pathHome'></a>");
		tmp.push("<a class='depthAlon'>" + MGW_RES.get("gw_org_root_label") + "</a>");
	} 
	// 부서 Path
	else {
		if (data.length != undefined)
			totalCnt = data.length;
		
		tmp.push("<a href='javascript:getOrgTree(undefined)' class='pathHome'></a>");
		
		if (totalCnt > 2)
			tmp.push("<a>...</a>");
		
		for (var i=totalCnt-1; i>-1; i--){
			if (totalCnt == 1) {
				tmp.push("<a class='depthAlon' href=\"javascript:getOrgTree('" + data[i].id + "')\">" + convertDeptName(data[i].name) + "</>");
				
				curDeptName = convertDeptName(data[i].name);
			}
			else if (totalCnt > 1 && i < 2) {
				if (i%2 == 0) {
					tmp.push("<a class='depth02' href=\"javascript:getOrgTree('" + data[i].id + "')\">" + convertDeptName(data[i].name) + "</a>");
	
					curDeptName = convertDeptName(data[i].name);
				}
				else if (i%2 == 1) {
					tmp.push("<a class='depth01' href=\"javascript:getOrgTree('" + data[i].id + "')\">" + convertDeptName(data[i].name) + "</a>");
				}
			}
		}
	}

	pathBar.html(tmp.join(""));
	return curDeptName;
}

function renderUserList(data, curDeptName) {
	var view = $.mobile.activePage;
	if(view.prop("id") != "org_tree" && view.prop("id") != "org_select" && view.prop("id").indexOf("search_result") < 0) 
		return "";
	
	var list = view.find("#list");
	var tmp = [];
		
	if (curDeptName != undefined)
		tmp.push("<li data-role='list-divider'>" + curDeptName + " - " + MGW_RES.get("gw_org_member_label"));
	
	if (data.user != undefined) {
		if (data.user.length == undefined) {
			if (curDeptName != undefined)
				tmp.push("(1)</li>");
			
			tmp.push(parseUserList(data.user));
		}
		else if (data.user.length > 0) {
			if (curDeptName != undefined)
				tmp.push("(" + data.user.length + ")</li>");
			
			for (var i=0; i<data.user.length; i++) {
				tmp.push(parseUserList(data.user[i]));
			}
		}
	}
	else {
		if (curDeptName != undefined)
			tmp.push("(0)</li>");
		
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_org_member_nolist") + "</h3></li>");
	}
	
	if(view.prop("id") != "org_tree" && view.prop("id") != "org_select" && view.prop("id").indexOf("search_result") < 0) 
		return "";
	
	list.append(tmp.join(""));
	list.listview("refresh");
	
	// 이전 페이지에서 전달된 수신자 목록이 있는 경우
	if (GW_CONTROLLER_ORG.selectedList != "")
		view.find(".targetUserList").html(GW_CONTROLLER_ORG.selectedList);
	
	updateTargetRecvOrg();
	
	GW_CONTROLLER_ORG.selectedList = "";
}

function parseUserList(user) {
	// LOG(user);
	
	var tmp = [];
	// alert("user.picpath : " + user.picpath);

	var tempMailKosmes = user.homepage; // kosmes user.mail -> u7 tkofs 
	if (GW_CONTROLLER_ORG.selectMode) {	// user.mail -> user.u7 kosmes 경우 tkofs
		tmp.push("<li id='" + user.id + "' style='padding:7px;' onclick=\"javascript:showOrgDetails(event, 'user', '" + user.id + "', '" + user.name +"', '"+ tempMailKosmes + "')\">");
		tmp.push("<div class='grouping_left_user'>");
		tmp.push("<input type='checkbox' data-role='none' name='check_user' ");
		tmp.push("onclick=\"javascript:showOrgDetails(event, 'user', '" + user.id + "', '" + user.name +"')\" ");
		tmp.push("id='" + user.id + "', value='" + user.name +"'></div>");
	}
	else {
		tmp.push("<li id='" + user.id + "'>");
		tmp.push("<div class='grouping_left' style='display:none;'></div>");
		tmp.push("<a href=\"javascript:showOrgDetails(event, 'user', '" + user.id + "', '" + user.name +"', '"+ tempMailKosmes + "')\">");
	}
	
	var imgSrc = GW_OpenAPI.serverIP + GW_OpenAPI.streamPATH + user.picpath.trim();
	
	tmp.push("<div class='grouping_middle'>");
	tmp.push("<img src='" + imgSrc + "' onerror=\"this.src='images/people_03.gif'\" width='35px' height='35px'>");
	tmp.push("<div class='userName'>" + user.name + "</div>");
	tmp.push("<span class='position'>" + user.position + "</span>");
	
	if (GW_CONTROLLER_ORG.selectMode)
		tmp.push("<div class='phoneNum_popup'>");
	else
		tmp.push("<div class='phoneNum'>");
	
	
	if (user.telephoneNumber != undefined && user.telephoneNumber != "")
		tmp.push("<span class='userPhone'>" + user.telephoneNumber + "</span>");
	
	if ((user.telephoneNumber != undefined && user.telephoneNumber != "") && (user.mobile != undefined && user.mobile.trim() != ""))
		tmp.push("<span class='userDivider'>|</span>");
	
	if (user.mobile != undefined && user.mobile != "")
		tmp.push("<span class='userMobile'>" + user.mobile + "</span>");
	
	tmp.push("</div></div>");
	
	if (!GW_CONTROLLER_ORG.selectMode)
		tmp.push("</a>");
	
	tmp.push("</li>");
	
	return tmp.join("");
}

function loadUserProfile(user) {
	
}

function uncheckUser() {
	var view = $.mobile.activePage;
	view.find(".targetUserList").html("");

	var list = $("[data-role=page]#org_select").find("input[name=check_user]");
	$.each(list, function(i, value) {
		// var id = list.eq(i).prop("id");
		list.eq(i).removeAttr("checked");
		// delteTargetRecv(GW_CONTROLLER_ORG.callbackId, "user", id);
	});
	
}

// 조직도 선택
function addTargetRecv(type, id, name, email, recursive, deptcode) {
	var view = $.mobile.activePage;
	
	if (view.find(".targetUserDiv").attr("style") != undefined)
		view.find(".targetUserDiv").removeAttr("style");
	
	if(recursive == undefined)
		recursive = false;
	
	// if (recursive != undefined && recursive)
		view.find(".targetUserList").append(renderTargetRecv(GW_CONTROLLER_ORG.callbackId, type, id, name, email, recursive, deptcode));
	// else
	// view.find(".targetUserList").append(renderTargetRecv(GW_CONTROLLER_ORG.callbackId,
	// type, id, name, email));
}

function renderTargetRecv(callbackId, type, id, name, email, recursive, deptcode) {
	var tmp = [];

	if(id == "") {
		tmp.push("<a href='#'");
	} else {
		tmp.push("<a href=\"javascript:delteTargetRecv('" + callbackId + "', '" + type +"','" + id + "')\"");
	}
	tmp.push(" class='btn_user gray' type='" + type + "' id='" + id + "' name='" + name + "'");
	
	if(email != undefined && email != "")	tmp.push(" email='" + email + "'");
	if(recursive != undefined  && (recursive == true || recursive == "true"))	tmp.push(" recursive='true'");
	if(deptcode != undefined && deptcode != "")	tmp.push(" deptcode='" + deptcode + "'");
	
	tmp.push("><span>");
	
	if (type == "dept")	tmp.push("$");
	if(recursive != undefined  && (recursive == true || recursive == "true"))	tmp.push("+");
		
	tmp.push(name);
	
	console.log("email : "+email);
	if (email != 'undefined' && email != undefined && email != "")	tmp.push(" &lt;" + email + "&gt;");
	
	if(id != "") {
		tmp.push("</span><span class='deleteUser'></span></a>");
	}
	
	return tmp.join("");
}

// 조직도 선택 취소
function delteTargetRecv(callbackId, type, id) {
	var view = $.mobile.activePage;
	var pageId = view.prop("id");
	
	// 조직도 사용자
	if (type == "user") 
		view.find("#list li").find("[name=check_user]").filter("#" + id).removeAttr("checked");
	
	// 조직도 부서
	if (type == "dept")
		view.find("#list li").find("[name=check_dept]").filter("#" + id).removeAttr("checked");
		
	// 개인주소록
	if (type == "personal") {
		if (pageId == "search_result")
			view.find("#list li").find("[name=check_personal]").filter("#" + id).removeAttr("checked");
		else
			view.find("#list_personal li").find("[name=check_personal]").filter("#" + id).removeAttr("checked");
	}	
	
	// 부서주소록
	if (type == "department") {
		if (pageId == "search_result")
			view.find("#list li").find("[name=check_department]").filter("#" + id).removeAttr("checked");
		else
			view.find("#list_department li").find("[name=check_department]").filter("#" + id).removeAttr("checked");
	}
	
	// 메일 그룹
	if (type == "group") {
		view.find("#list_group li").find("[name=check_group]").filter("#" + id).removeAttr("checked");
	}
	
	if (pageId == "mail_write" || pageId == "settings_absence") {
		view.find("#" + callbackId).find("[type=" + type + "]").filter("#" + id).remove();
		
		if (view.find("#" + callbackId).html().trim() == "") {
			view.find("#" + callbackId).attr("style", "display:none");
		}
	}
	else {
		view.find(".targetUserList").find("[type=" + type + "]").filter("#" + id).remove();
		
		if (view.find(".targetUserList").html().trim() == "") {
			view.find(".targetUserDiv").attr("style", "display:none");
		}	
	}
		
}

// 조직도선택에서 조직도 탭 체크박스 상태 업데이트
function updateTargetRecvOrg() {
	var view = $.mobile.activePage;
	var li = view.find("#list li");
	
	var deptList = view.find(".targetUserList").find("[type=dept]");
	var userList = view.find(".targetUserList").find("[type=user]");

	$.each(deptList, function(i, value) {
		var id = deptList.eq(i).prop("id");
		var node = li.find("[name=check_dept]").filter("#" + id);
		
		if (node != undefined) {
			node.attr('checked','checked');
		}
	});
	
	$.each(userList, function(i, value) {
		var id = userList.eq(i).prop("id");
		var node = li.find("[name=check_user]").filter("#" + id);
				
		if (node != undefined) {
			node.attr('checked','checked');
		}
	});
	
}

// 조직도선택에서 주소록 탭 체크박스 상태 업데이트
function updateTargetRecvContact(type) {
	if (type != "personal" && type != "department")
		return;
	
	var view = $.mobile.activePage;
	var pageId = view.prop("id");
	var li = undefined;
	
	if (pageId == "search_result")
		li = view.find("#list").find("li");
	else
		li = view.find("#list_" + type).find("li");
	
	if (li == undefined) 	return;
	
	var contactList = view.find(".targetUserList").find("[type=" + type + "]");
	$.each(contactList, function(i, value) {
		var id = contactList.eq(i).prop("id");
		var node = li.find("[name=check_" + type + "]").filter("#" +id);
		
		if (node != undefined) {
			node.attr('checked','checked');
		}
	});
}

function updateTargetRecvGroup() {
	var view = $.mobile.activePage;
	var li = view.find("#list_group").find("li");
	
	var groupList = view.find(".targetUserList").find("[type=group]");
	$.each(groupList, function(i, value) {
		var id = groupList.eq(i).prop("id");
		var node = li.find("[name=check_group]").filter("#" +id);
		
		if (node != undefined) {
			node.attr('checked','checked');
		}
	});
}

// 조직도선택화면에서 [확인]버튼 클릭
function confirmTargetRecv() {
	var view = $.mobile.activePage;	
	var targetUserList = view.find(".targetUserList").html();
    
	LOG("GW_CONTROLLER_ORG.category : " + GW_CONTROLLER_ORG.category + ", GW_OpenAPI.settings_type : " + GW_OpenAPI.settings_type + ", GW_CONTROLLER_ORG.callbackId : " + GW_CONTROLLER_ORG.callbackId);
	
	if (GW_CONTROLLER_ORG.category == "mail" || GW_CONTROLLER_ORG.category == "square") {
		if(GWPlugin.usePlugin) {
			hideWebView();
			GWPlugin.returnOrgSelect(GW_CONTROLLER_ORG.category, GW_CONTROLLER_ORG.callbackId, convertSelectedListHtmlToJson());	
			return;
		} else {
			if (targetUserList != "") {
				$("[data-role=page]#mail_write").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).removeAttr("style");
			} else { 
				$("[data-role=page]#mail_write").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).attr("style", "display:none;");
			}
			$("[data-role=page]#mail_write").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).html(targetUserList);
		}
	}
	else if (GW_CONTROLLER_ORG.category == "schedule") {
		if (targetUserList != "") {
			$("[data-role=page]#sch2_schadd").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).removeAttr("style");
		} else { 
			$("[data-role=page]#sch2_schadd").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).attr("style", "display:none;");
		}
		$("[data-role=page]#sch2_schadd").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).html(targetUserList);
	}
	else if (GW_CONTROLLER_ORG.category == "settings") {
		if(GW_OpenAPI.settings_type == "settings1"){
			$("[data-role=page]#settings_absence").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).html(targetUserList);
			
			if (targetUserList != "") {
				$("[data-role=page]#settings_absence").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).show();
			}
			else {
				$("[data-role=page]#settings_absence").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).hide();
			}
		}else if(GW_OpenAPI.settings_type == "settings2"){
			$("[data-role=page]#settings2_absenceadd").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).html(targetUserList);
			
			if (targetUserList != "") {
				$("[data-role=page]#settings2_absenceadd").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).show();
			}
			else {
				$("[data-role=page]#settings_absenceadd").find(".targetUserList").filter("#" + GW_CONTROLLER_ORG.callbackId).hide();
			}
		}
	}
	
	PAGE_CONTROLLER.goBack();
}

function convertSelectedListHtmlToJson() {
	var view = $.mobile.activePage;	
	var list = view.find(".targetUserList").find("a");	
	var tmp = [];

	$.each(list, function(i, value) {
		var id = list.eq(i).prop("id");
		var type = list.eq(i).attr("type");
		var name = list.eq(i).attr("name");
		var email = list.eq(i).attr("email");
		if(email == undefined) email = "";
		var recursive = list.eq(i).attr("recursive");
		if(recursive == undefined) recursive = "false";
		var deptcode = list.eq(i).attr("deptcode");
		if(deptcode == undefined) deptcode = "";
		tmp.push('{"id":"' + id + '","type":"' + type + '","name":"' + name + '","email":"' + email + '","recursive":' + recursive + ',"deptcode":"' + deptcode + '"}');
	});
	
	return '{"selectedlist":[' + tmp.join(",") + ']}';
}

function cancel_orgSelect() {
	var view = $.mobile.activePage;	
	if (view.prop("id") != "org_select")
		return;
	
	if((GW_CONTROLLER_ORG.category == "mail" || GW_CONTROLLER_ORG.category == "square") && GWPlugin.usePlugin) {
		hideWebView();
		GWPlugin.closePopupViewer("", false);
		return;
	}
	else {
		PAGE_CONTROLLER.goBack();
	}
}/***************************************************************************
	 * UI Script START for DefaultValue(기본변수 설정) *
	 **************************************************************************/
var apiCode_schList = "schlist";			// 일정 목록
var apiCode_equipList = "equiplist";		// 설비예약 목록
var apiCode_todoList = "todolist";			// 할일 목록
var apiCode_schDetail = "schdetail";
var apiCode_equipDetail = "equipdetail";
var apiCode_todoDetail = "tododetail";
var apiCode_schCount = "schcount";
var apiCode_equipCount = "equipcount";
var apiCode_schEquipList = "schequiplist";	// 설비 목록
var apiCode_calList = "callist";			// 달력 목록
var apiCode_attendeeList = "attendeelist";	// 일정공유자 목록

var GW_CONTROLLER_SCHEDULE = {
	calendarLoaded: false,
	equipmentLoaded: false,
	calendarData: undefined,
	equipmentData: undefined,
	deptCalendarIds: "",
	myCalendarIds: "",
	companyCalendarIds: "",
	selectAllEquipment: true,
	selectedEquipmentIds: "",
	
	initCalendar: function(calendarData) {
		GW_CONTROLLER_SCHEDULE.calendarLoaded = true;
		GW_CONTROLLER_SCHEDULE.calendarData = calendarData;
		
		GW_CONTROLLER_SCHEDULE.companyCalendarIds = "";
		GW_CONTROLLER_SCHEDULE.deptCalendarIds = "";
		GW_CONTROLLER_SCHEDULE.myCalendarIds = "";
		
		if (calendarData.length != 0) {
			for(var i=0; i<calendarData.length; i++) {
				switch(calendarData[i].owner_tp) {
				case '1':
					GW_CONTROLLER_SCHEDULE.companyCalendarIds += calendarData[i].calendar_id + ";";
					break;
				case '2':
					GW_CONTROLLER_SCHEDULE.deptCalendarIds += calendarData[i].calendar_id + ";";
					break;
				case '4':
					GW_CONTROLLER_SCHEDULE.myCalendarIds += calendarData[i].calendar_id + ";";
					break;
				}
			}
		}
	}, 
	initEquipment: function(equipmentData) {
		GW_CONTROLLER_SCHEDULE.equipmentLoaded = true;
		GW_CONTROLLER_SCHEDULE.equipmentData = equipmentData;
		
		GW_CONTROLLER_SCHEDULE.selectAllEquipment = true;
		GW_CONTROLLER_SCHEDULE.selectedEquipmentIds = "";
	}
	
};
/*******************************************************************************
 * UI Script END for DefaultValue *
 ******************************************************************************/



/*******************************************************************************
 * UI Script START for COMMON(공통 사용 스크립트) *
 ******************************************************************************/
function showSchList(apiCode, baseDate) {
	if (baseDate == undefined || baseDate == "") {
		baseDate = getToday();
	}
	
	if (GWPlugin.usePlugin) {
		GWPlugin.setSelectedTabBarItem(0, function(){}, function(){});
	}
	
	if (apiCode == "schlist") {
		PAGE_CONTROLLER.showPage("sch_" + apiCode, function() {getSchList(apiCode, 0, "DAILY", "ALL", baseDate);});
	} 
	else if (apiCode == "equiplist") {
		PAGE_CONTROLLER.showPage("sch_" + apiCode, function() {getSchEquipList(apiCode, 0, "DAILY", baseDate);});
	} 
	else if (apiCode == "todolist") {
		PAGE_CONTROLLER.showPage("sch_" + apiCode, function() {getSchTodoList(apiCode, 0, "DAILY", "ALL", baseDate);});
	}
}

function showSchAdd(isModify) {
	if (isModify == undefined || isModify == false) {
		popupSchAddView();
	}
	else {
		var view = $.mobile.activePage;
		var eventId = view.find("#event_id").val();
		
		if (GWPlugin.usePlugin) {
			GWPlugin.showPopupViewer(["javascript:popupSchAddView('" + eventId + "');"], function(){}, function(){});
		}
		else {
			popupSchAddView(eventId);
		}
	}
}

function showSchAddNaviBar() {
	NAVIBAR_DEF.phone.sch_schadd.title = MGW_RES.get("gw_schedule_schadd_label");
	NAVIBAR_DEF.pad.sch_schadd.title = MGW_RES.get("gw_schedule_schadd_label");
	
	GWPlugin.showPopupViewer(["javascript:popupSchAddView();"], function(){}, function(){});
}

function popupSchAddView(eventId) {
	// 일정 추가
	if (eventId == undefined) {
		NAVIBAR_DEF.phone.sch_schadd.title = MGW_RES.get("gw_schedule_schadd_label");
		NAVIBAR_DEF.pad.sch_schadd.title = MGW_RES.get("gw_schedule_schadd_label");
		
		PAGE_CONTROLLER.showPage("sch_schadd", function() {getSchAdd();});
	}
	// 일정 수정
	else {
		NAVIBAR_DEF.phone.sch_schadd.title = MGW_RES.get("gw_schedule_schmodify_label");
		NAVIBAR_DEF.pad.sch_schadd.title = MGW_RES.get("gw_schedule_schmodify_label");
		
		if (eventId != undefined && eventId != "") {
			PAGE_CONTROLLER.showPage("sch_schadd", function() {getSchAdd(eventId);});
		}
		else {
			alert(MGW_RES.get("gw_msg_common_err"));
		}
	}
}

function showTodoAdd(isModify) {
	if (isModify == undefined || isModify == false) {
		popupTodoAddView();
	}
	else {
		var view = $.mobile.activePage;
		var todoId = view.find("#todo_id").val();
		
		if (GWPlugin.usePlugin) {
			GWPlugin.showPopupViewer(["javascript:popupTodoAddView('" + todoId + "');"], function(){}, function(){});
		}
		else {
			popupTodoAddView(todoId);
		}
	}
}

function showTodoAddNaviBar() {
	NAVIBAR_DEF.phone.sch_schadd.title = MGW_RES.get("gw_schedule_todo_add_label");
	NAVIBAR_DEF.pad.sch_schadd.title = MGW_RES.get("gw_schedule_todo_add_label");
	
	GWPlugin.showPopupViewer(["javascript:popupTodoAddView();"], function(){}, function(){});
}

function popupTodoAddView(todoId) {
	// 할일 추가
	if (todoId == undefined) {
		NAVIBAR_DEF.phone.sch_todoadd.title = MGW_RES.get("gw_schedule_todo_add_label");
		NAVIBAR_DEF.pad.sch_todoadd.title = MGW_RES.get("gw_schedule_todo_add_label");
		
		PAGE_CONTROLLER.showPage("sch_todoadd", function() {getTodoAdd();});
	} 
	else {
		NAVIBAR_DEF.phone.sch_todoadd.title = MGW_RES.get("gw_schedule_todo_modify_label");
		NAVIBAR_DEF.pad.sch_todoadd.title = MGW_RES.get("gw_schedule_todo_modify_label");
		
		PAGE_CONTROLLER.showPage("sch_todoadd", function() {getTodoAdd(todoId);});
	}
}

function showSchSearchTab() {
	if (GWPlugin.usePlugin) {
		GWPlugin.setSelectedTabBarItem(0, function(){}, function(){});
	}
	
	PAGE_CONTROLLER.showPage("sch_schsearch", function() {getSchSearchTab();});
}

/*
 * 월간 달력에서 건수 검색 시 일간으로 이동 callDate : 검색하고자 하는 달 day : 검색하고자 하는 일
 */
function linkMonthToDay(callDate, day){
	var view = $.mobile.activePage;
	view.find("#DAILY").attr("class", "ui-btn-active");
	var dateMode = view.find("#dateMode").val();;
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = getYear(callDate) + "." + getMonth(callDate) + "." + day;
	var apiCode = view.find("#apiCode").val();
	
	getSchList(apiCode, 0, dateMode, calMode, baseDate);	
}

/*
 * 월간 달력에서 일간 별로 조회 callDate : 검색하고자 하는 달 day : 검색하고자 하는 일
 */
function monthToDayList(callDate, day){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();;
	var calMode = view.find("#calMode option:selected").val();
	var newDate = new Date(getYear(callDate), getMonth(callDate)-1, day);
	var baseDate = dateToString(newDate);
	var apiCode = view.find("#apiCode").val();
	var id = view.find("#equipmentIDS").val();
	
	if (apiCode == "schlist") {
		NAVIBAR_DEF.phone.sch_detaillist.title = "[" + getMonthDay(newDate) + "] " + MGW_RES.get("gw_schedule_list_label");
		NAVIBAR_DEF.pad.sch_detaillist.title = "[" + getMonthDay(newDate) + "] " + MGW_RES.get("gw_schedule_list_label");
		
		PAGE_CONTROLLER.showPage("sch_detaillist", function() {
			changeTitle("[" + getMonthDay(newDate) + "] " + MGW_RES.get("gw_schedule_list_label"));
			getSchList(apiCode, 0, dateMode, calMode, baseDate, true);});
	} 
	else if (apiCode == "equiplist") {
		NAVIBAR_DEF.phone.sch_detaillist.title = "[" + getMonthDay(newDate) + "] " + MGW_RES.get("gw_schedule_equip_list_label");
		NAVIBAR_DEF.pad.sch_detaillist.title = "[" + getMonthDay(newDate) + "] " + MGW_RES.get("gw_schedule_equip_list_label");
		
		PAGE_CONTROLLER.showPage("sch_detaillist", function() {
			changeTitle("[" + getMonthDay(newDate) + "] " + MGW_RES.get("gw_schedule_equip_list_label"));
			getSchEquipList(apiCode, 0, dateMode, baseDate, "DETAIL", id, true);});
	}
}

/*
 * 상세보기 할때 사용되는 스크립트 일정과 설비는 scheduleDetail에서 분기된다. eventId : 해당 일정(설비)의 event
 * ID값 monthMode : 월간에서 일정이나 설비의 상세보기 할 경우 사용된다.
 */
function schDetail(eventId, monthMode, ownerId){
	var view = $.mobile.activePage;
	var apiCode = view.find("#apiCode").val();
	
	if (monthMode == "schedule" || monthMode == "sch_detaillist_sch" || apiCode == "schlist") {
		getSchDetail(apiCode, eventId, ownerId);
	}
	else if (monthMode == "equipment" || monthMode == "sch_detaillist_equip" || apiCode == "equiplistdetail") {
		getSchEquipDetail(apiCode, eventId, ownerId);
	}
}

function showDetailList(currentTab, selectOption, schData) {
	var title = MGW_RES.get("gw_schedule_sch_search_label") + " (" + schData.length + MGW_RES.get("gw_schedule_calendar_count_label") +")";
	if (currentTab == "todo") {
		title = MGW_RES.get("gw_schedule_todo_search_label") + " (" + schData.length + MGW_RES.get("gw_schedule_calendar_count_label") +")";
	}
	
	NAVIBAR_DEF.phone.sch_detaillist.title = title;
	NAVIBAR_DEF.pad.sch_detaillist.title = title;
	
	PAGE_CONTROLLER.showPage("sch_detaillist", function(){
		changeTitle(title);
		
		// selectOption value 1:설비, 2:달력, 3:전부
		if (selectOption == "1") {
			renderDetailList(currentTab, schData, undefined, GW_CONTROLLER_SCHEDULE.equipmentData);
		} 
		else if (selectOption == "2") {
			renderDetailList(currentTab, schData, GW_CONTROLLER_SCHEDULE.calendarData, undefined);
		}
		else if (selectOption == "3") {
			renderDetailList(currentTab, schData, GW_CONTROLLER_SCHEDULE.calendarData, GW_CONTROLLER_SCHEDULE.equipmentData);
		}
	});
}

function todoDetail(todoId, ownerId, calTitle, calColor, apiCode){
	getSchTodoDetail(todoId, ownerId, calTitle, calColor);
}

/*
 * 일간, 주간, 월간 변경 시 period : 일간(DAILY), 주간(WEEKLY), 월간(MONTHLY) 파라미터값 표시
 */
function changeDateMode(period){
	var view = $.mobile.activePage;
	
	if (period == undefined)
		period = view.find("#dateMode").val();
	
	view.find("#dateMode").val(period);
	
	var dateMode = period;
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = view.find("#baseDate").text();
	var apiCode = view.find("#apiCode").val();
	
	if (apiCode == "schlist") {
		getSchList(apiCode, 0, dateMode, calMode, baseDate);
	}
	else if (apiCode == "equiplist") {
		getSchEquipList(apiCode, 0, dateMode, baseDate);
	}
	else if (apiCode == "todolist") {
		getSchTodoList(apiCode, 0, dateMode, calMode, baseDate);
	}
}

/*
 * 달력 이동 시 '오늘'버튼으로 되돌아 가는 FN
 */
function todayDate(){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = new Date();
	var apiCode = view.find("#apiCode").val();
	var id = view.find("#equipmentIDS").val();
	baseDate = dateToString(baseDate);
	
	if (apiCode == "schlist") {
		getSchList(apiCode, 0, dateMode, calMode, baseDate);
	}
	else if (apiCode == "equiplist") {
		getSchEquipList(apiCode, 0, dateMode, baseDate);
	}
	else if (apiCode == "equiplistdetail") {
		getSchEquipList(apiCode, 0, dateMode, baseDate, "DETAIL", id);
	}
	else if (apiCode == "todolist") {
		getSchTodoList(apiCode, 0, dateMode, calMode, baseDate);
	}
}
/*******************************************************************************
 * UI Script END for COMMON *
 ******************************************************************************/



/*******************************************************************************
 * UI Script START for Schedule(일정에 사용되는 스크립트) *
 ******************************************************************************/
/*
 * 달력모드가 변경 되었을 때 처리 FN (일간, 주간, 월간)
 */
function changeCalMode(){
	var view = $.mobile.activePage;	
	var dateMode = view.find("#dateMode").val();;	
	var calId = view.find("#calMode option:selected").val();
	var baseDate = view.find("#baseDate").text();
	var apiCode = view.find("#apiCode").val();
	
	if (apiCode == "schlist") {
		getSchList(apiCode, 0, dateMode, calId, baseDate);
	}
	else if (apiCode == "todolist") {
		getSchTodoList(apiCode, 0, dateMode, calId, baseDate);
	}
}

/*
 * 전날 검색 '<' 버튼 FN
 */
function lastDate(){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();;
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = view.find("#baseDate").text();
	var apiCode = view.find("#apiCode").val();
	var id = view.find("#equipmentIDS").val();
	
	if (dateMode == "DAILY")	{
		baseDate = lastDay(baseDate);
	}
	else if (dateMode == "WEEKLY") {
		baseDate = lastWeek(baseDate);
	}
	else if (dateMode == "MONTHLY") {
		baseDate = lastMonth(baseDate);
	}
	
	if (apiCode == "schlist") {
		getSchList(apiCode, 0, dateMode, calMode, baseDate);
	}
	else if (apiCode == "equiplist") {
		getSchEquipList(apiCode, 0, dateMode, baseDate);
	}
	else if (apiCode == "equiplistdetail") {
		getSchEquipList(apiCode, 0, dateMode, baseDate, "DETAIL", id);
	}
	else if (apiCode == "todolist") {
		getSchTodoList(apiCode, 0, dateMode, calMode, baseDate);
	}
}

/*
 * 다음날 검색 '>' 버튼 FN
 */
function nextDate(){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();;
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = view.find("#baseDate").text();
	var apiCode = view.find("#apiCode").val();
	var id = view.find("#equipmentIDS").val();
	
	if (dateMode == "DAILY") {
		baseDate = nextDay(baseDate);
	}
	else if (dateMode == "WEEKLY") {
		baseDate = nextWeek(baseDate);
	}
	else if (dateMode == "MONTHLY") {
		baseDate = nextMonth(baseDate);
	}
	
	if (apiCode == "schlist") {
		getSchList(apiCode, 0, dateMode, calMode, baseDate);
	}
	else if (apiCode == "equiplist") {
		getSchEquipList(apiCode, 0, dateMode, baseDate);
	}
	else if (apiCode == "equiplistdetail") {
		getSchEquipList(apiCode, 0, dateMode, baseDate, "DETAIL", id);
	}
	else if (apiCode == "todolist") {
		getSchTodoList(apiCode, 0, dateMode, calMode, baseDate);
	}
}

/*******************************************************************************
 * UI Script END for Schedule *
 ******************************************************************************/



/*******************************************************************************
 * UI Script START for Equipment(설비에 사용되는 스크립트) *
 ******************************************************************************/	
function detailList(id){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();;
	var baseDate = view.find("#baseDate").text();
	var apiCode = "equiplistdetail";
	
	PAGE_CONTROLLER.showPage("sch_" + apiCode, function() {getSchEquipList(apiCode, 0, dateMode, baseDate, "DETAIL", id)});
	
	view = $.mobile.activePage;
	view.find("#equipmentIDS").val(id);
}

/*******************************************************************************
 * UI Script END for Equipment *
 ******************************************************************************/

	

/*******************************************************************************
 * #Schedule List START('일간', '주간', '월간' 일정목록 스크립트) *
 ******************************************************************************/
/*
 * 일정과 설비쪽 목록을 출력할 때 사용하는 FN apiCode : 보고자 하는 목록의 api 코드값 (일정->schlist,
 * 설비->equiplist, 할일->todolist) 각 페이지 div에 id값으로 명시 됨 page_num : 페이지 네이션 기능이 있을때
 * 사용 현재로는 api기능 미제공으로 기본값으로 0을 삽입 dateMode : 일간(DAILY),주간(WEEKLY),월간(MONTHLY)으로
 * 구분; calMode : 전체달력(ALL), 나의달력(MY), 부서달력(DEPT), 전사달력(COMPANY), 사용자달력(USER)으로
 * 구분; callDate : 요청된 날 표기 (ex : 2012.11.12)
 * 
 */
function getSchList(apiCode, page_num, dateMode, calId, callDate, dayMode) {
	var baseDate = callDate;
	var startDate = baseDate;
	var endDate = baseDate;
	var curDate = "";
	var param = {};
	var calMode = "ALL";
	var companyCalendarIds = "";
	var teamCalendarIds = "";
	var privateCalendarIds = "";
	var noCheckValue = "00000000000000000000";

	getCalendarList(
		function() 
		{
			// 달력별 Id 셋팅
			if (GW_CONTROLLER_SCHEDULE.calendarData.length != 0) {
				for(var i=0; i<GW_CONTROLLER_SCHEDULE.calendarData.length; i++) {
					if (GW_CONTROLLER_SCHEDULE.calendarData[i].calendar_id == calId)
						calMode = GW_CONTROLLER_SCHEDULE.calendarData[i].owner_tp;
				}
			}

			// 달력 필터에 따른 param value 셋팅 (calMode value 0:전체,1:전사,2:부서,4:개인)
			if (calMode == "1") {
				companyCalendarIds = calId;
				teamCalendarIds = noCheckValue;
				privateCalendarIds = noCheckValue;
			}
			else if (calMode == "2") {
				companyCalendarIds = noCheckValue;
				teamCalendarIds = calId;
				privateCalendarIds = noCheckValue;
			}
			else if (calMode == "4") {
				companyCalendarIds = noCheckValue;
				teamCalendarIds = noCheckValue;
				privateCalendarIds = calId;
			} else {
				companyCalendarIds = GW_CONTROLLER_SCHEDULE.companyCalendarIds;
				teamCalendarIds = GW_CONTROLLER_SCHEDULE.deptCalendarIds;
				privateCalendarIds = GW_CONTROLLER_SCHEDULE.myCalendarIds;
			}
			
			param["COMPANY_CALENDAR_IDS"] = companyCalendarIds;
			param["TEAM_CALENDAR_IDS"] = teamCalendarIds;
			param["PRIVATE_CALENDAR_IDS"] = privateCalendarIds;
				
			if (dateMode == "DAILY") {
				param["TARGET_START_DATE"] = startDate;
				param["TARGET_END_DATE"] = endDate;
				curDate = "<span>" + getYear(startDate)	+ "</span>." + getMonth(startDate) + "."
							+ getDate(startDate) + "(" + getWeekName(startDate) + ")";
			}
			else if (dateMode == "WEEKLY") {
				startDate = getFirstDateOfWeek(startDate);
				endDate = getLastDateOfWeek(endDate);
				param["TARGET_START_DATE"] = startDate;
				param["TARGET_END_DATE"] = endDate;
				// YYYY.M.D(요일)~?.D(요일) 형식
				curDate = getFilterDate(startDate, endDate, "date", "startDate") + "(" + getWeekName(startDate)
							+ ")~" + "<span>" + getFilterDate(startDate, endDate, "date", "endDate") + "</span>" + "(" + getWeekName(endDate) + ")";
			}
			else if (dateMode == "MONTHLY") {
				startDate = getFirstDateOfMonth(startDate);
				endDate = getLastDateOfMonth(endDate);
				param["TARGET_START_DATE"] = startDate;
				param["TARGET_END_DATE"] = endDate;
				curDate = "<span>" + getYear(startDate) + "</span>." + getMonth(startDate);
			}
			
			var view = $.mobile.activePage;
			
			view.find("#divList").attr("class","divListNormal");
			view.find("#divTitle").attr("class","divListNormal");
			view.find("#baseDate").html(baseDate);		// 기준일
			view.find("#curDate").html(curDate);

			if (apiCode == "schlist") {
				if (dateMode == "MONTHLY" && !dayMode) {
					GW_PROXY.invokeOpenAPI("schedule", apiCode_schCount, param, function(schData) {
						renderSchList(apiCode, page_num, schData, GW_CONTROLLER_SCHEDULE.calendarData, curDate, baseDate, dateMode, startDate, endDate, dayMode);
					});
				}
				else {
					GW_PROXY.invokeOpenAPI("schedule", apiCode_schList, param, function(schData) {
						renderSchList(apiCode, page_num, schData, GW_CONTROLLER_SCHEDULE.calendarData, curDate, baseDate, dateMode, startDate, endDate, dayMode);
					});
				}
			}
		}
	);
	
}

function getCalendarList(callback) {
	if (!GW_CONTROLLER_SCHEDULE.calendarLoaded) {
		GW_PROXY.invokeOpenAPI("schedule", apiCode_calList, {}, function(data) {
			GW_CONTROLLER_SCHEDULE.initCalendar(data);
			renderCalendarList(data);

			callback();
		});
	}
	else {
		renderCalendarList(GW_CONTROLLER_SCHEDULE.calendarData);
		callback();
	}
}

function getEquipmentList(callback) {
	if (!GW_CONTROLLER_SCHEDULE.equipmentLoaded) {
		GW_PROXY.invokeOpenAPI("schedule", apiCode_schEquipList, {}, function(data) {
			GW_CONTROLLER_SCHEDULE.initEquipment(data);
			callback();
		});
	}
	else {
		callback();
	}
}

function renderCalendarList(data) {
	var view = $.mobile.activePage;
	var tmp = "";
	
	if (view.find("#isLoadedCalendar").val() == "true")
		return;
	
	if (data.length != 0) {
		for(var i=0; i<data.length; i++) {
			tmp += "<option name='" + data[i].owner_tp + "' value='" + data[i].calendar_id + "'>" + data[i].title + "</option>";
		}
	}
	
	if (tmp != "") {
		view.find("#calMode").append(tmp);
		view.find("#isLoadedCalendar").val("true");
	}
}

function renderEquipmentList(data) {
	var view = $.mobile.activePage;
	var tmp = "";
	
	if (view.find("#isLoadedEquipment").val() == "true")
		return;
	
	if (data.length != 0) {
		for(var i=0; i<data.length; i++) {
			tmp += "<option name='" + data[i].calendar_id + "' value='" + data[i].calendar_id + "'>" + data[i].title + "</option>";
		}
	}
	
	if (tmp != "") {
		view.find("#selEquipment").append(tmp);
		view.find("#isLoadedEquipment").val("true");
	}
}

/*
 * apiCode : 보고자 하는 목록의 api 코드값 (일정->schlist) 각 페이지 div에 id값으로 명시 됨 page_num :
 * 페이지 네이션 기능이 있을때 사용 현재로는 api기능 미제공으로 기본값으로 0을 삽입 data : api호출을 통해 가져온 목록 리스트
 * data calData : 달력 데이터 curDate : 현재 표기될 날짜(일간, 주간, 월간 별로 변경됨) baseDate : 기준일
 * dateMode : 일간(DAILY), 주간(WEEKLY), 월간(MONTHLY) startDate : 검색 시작일 endDate : 검색
 * 종료일
 */
function renderSchList(apiCode, page_num, data, calData, curDate, baseDate, dateMode, startDate, endDate, dayMode){
	var view = $.mobile.activePage;
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");
	view.find("#baseDate").html(baseDate);		// 기준일
	
	/* 기존데이터 초기화 */
	divList.html("");
	list.html("");
			
	/* 일간 UI Render */
	if (dateMode == "DAILY") {
		if (data.length != 0) {
			for(var i = 0; i< data.length;i++){// 리스트에 메일 렌더
				var item = data[i];
				var tmp = [];
				
				tmp.push("<li id=\"" + item.event_id +"\" >");
				if (item.parent_event_id == "00000000000000000000") {
					tmp.push("<a href=\"javascript:schDetail('" + item.event_id + "', " + undefined + ", '" + item.owner_id + "');\">");
				}
				else {
					tmp.push("<a href=\"javascript:schDetail('" + item.parent_event_id + "', " + undefined + ", '" + item.owner_id + "');\">");
				}
				tmp.push("<div class='calendarName'>");
				
				if (calData.length != 0) {
					for(var j=0; j<calData.length; j++) {
						if (item.calendar_id == calData[j].calendar_id) {
							tmp.push("<font color='" + calData[j].ui_attr + "'>[" + calData[j].title + "]</font></div>");
						}
						else if (item.calendar_id == calData[j][0]) {
							tmp.push("<font color='" + calData[j][2] + "'>[" + calData[j][1] + "]</font></div>");
						}
					}
				}
				
				tmp.push("<h3 class='title'>" + item.title + "</h3>");
				tmp.push("<div class='dateInfo'>" + item.start_date + " ~ " + item.end_date + "</div>");
				tmp.push("</a></li>");
				list.append(tmp.join(""));
			}
		}
		else {
			var tmp = [];
			
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
			list.html(tmp.join(""));
		}
		
		list.listview("refresh");
	}
	
	/* 주간 UI Render */
	else if (dateMode == "WEEKLY") {
		var tmp = [];
		var temp = 0;
		var titleName = new Array();
		var titleCnt = new Array();
		
		var n;
		var isCheck;
		// 하루 기준 milliseconds
		var oneDay = (1000*60*60*24);
		
		tmp.push("<table class='dateArea' border='0' cellspacing='0' cellpadding='0'>");
		divList.attr("class", "divList");
		
		if (data.length != 0) {
			for(i=0; i<7; i++) {
				titleCnt[i] = 0;
				titleName[i] = new Array;
			}
			
			for(i=0; i<7; i++) {
				n = titleCnt[i];
				tempToDay = getDatetoDay(startDate, i);
				
				for(var j=temp; j<data.length; j++) {
					isCheck = true;
					temp = j;
					var item = data[j];
					
					if (getDate(getDatetoDay(startDate, i)) == getDate(item.start_date)) {	// 해당
																							// 날짜
																							// 데이터만
																							// 추출
						titleName[i][n] = new Array();
						
						if (item.parent_event_id == "00000000000000000000") {
							titleName[i][n][0] = item.event_id;
						} 
						else {
							titleName[i][n][0] = item.parent_event_id;
						}
						titleName[i][n][1] = item.title;
						titleName[i][n][2] = item.calendar_id;
						titleName[i][n++][3] = item.owner_id;
						
						if (temp == data.length - 1) {
							temp++;
						}
					}
					// 일정 시작날짜가 현재일보다 빠른경우
					else if (!checkDateTimePeriod(getYear(tempToDay), getMonth(tempToDay), getDate(tempToDay),0,0,
							getYear(item.start_date), getMonth(item.start_date), getDate(item.start_date),0,0))	{
						
						if (getMonth(item.start_date) != getMonth(item.end_date) ||
								getYear(item.start_date) != getYear(item.end_date) ||
								getDate(getLastDateOfWeek(item.end_date)) != getDate(endDate)) {
							for(k=i; k<7; k++) {
								titleName[k][titleCnt[k]] = new Array();
								
								if (item.parent_event_id == "00000000000000000000") {
									titleName[k][titleCnt[k]][0] = item.event_id;
								}
								else {
									titleName[k][titleCnt[k]][0] = item.parent_event_id;
								}
								titleName[k][titleCnt[k]][1]  = item.title;
								titleName[k][titleCnt[k]][2]  = item.calendar_id;
								titleName[k][titleCnt[k]++][3]  = item.owner_id;
							}
						}
						else if (getDate(item.start_date) != getDate(item.end_date)) {
							var loopDate;
							
							if (!checkDatePeriod(startDate, endDate, item.start_date)) {
								loopDate = getDate(item.end_date) - getDate(startDate) + i + 1;
							}
							else {
								loopDate = getDate(item.end_date) - getDate(item.start_date) + (i);
							}
							// 다음날이 0시 00 분일 경우 표시되어서는 안된다.
							if(item.end_date.lastIndexOf("0:00") != -1)
								loopDate--;									
							for(k=i; k<loopDate; k++) {
								titleName[k][titleCnt[k]] = new Array();
								
								if (item.parent_event_id == "00000000000000000000") {
									titleName[k][titleCnt[k]][0] = item.event_id;
								}
								else {
									titleName[k][titleCnt[k]][0] = item.parent_event_id;
								}
								titleName[k][titleCnt[k]][1] = item.title;
								titleName[k][titleCnt[k]][2]  = item.calendar_id;
								titleName[k][titleCnt[k]++][3]  = item.owner_id;
							}		
						}
						if (temp == data.length-1) {
							temp++;
						}
						isCheck = false;
					}
					else { 
						break;
					}
					
					if (isCheck) {
						if (getMonth(item.start_date) != getMonth(item.end_date)
								|| getYear(item.start_date) != getYear(item.end_date)
								|| getDate(getLastDateOfWeek(item.end_date)) != getDate(endDate)) {
							// 월이 변경되는 경우에 체크해주는 것이 필요
							var base_cnt = parseInt((new Date(getYear(item.end_date), getMonth(item.end_date)-1, getDate(item.end_date), getHours(item.end_date), getMinutes(item.end_date)) - new Date(getYear(item.start_date), getMonth(item.start_date)-1, getDate(item.start_date), getHours(item.start_date), getMinutes(item.start_date)))/oneDay,10);
							var inc_cnt = 0;
							for(k=i+1; k<7; k++) {
								if(base_cnt <= inc_cnt)
									break;
								titleName[k][titleCnt[k]] = new Array();
								if (item.parent_event_id == "00000000000000000000") {
									titleName[k][titleCnt[k]][0] = item.event_id;
								}
								else {
									titleName[k][titleCnt[k]][0] = item.parent_event_id;
								}
								titleName[k][titleCnt[k]][1]  = item.title;
								titleName[k][titleCnt[k]][2]  = item.calendar_id;
								titleName[k][titleCnt[k]++][3]  = item.owner_id;
								inc_cnt++;
							}
						}
						else if (getDate(item.start_date) != getDate(item.end_date)) {
							var tmp_date=0;
							// 다음날이 0시 00 분일 경우 표시되어서는 안된다.
							if(item.end_date.lastIndexOf("0:00") != -1)
								tmp_date=1;
							for(k=i+1; k<getDate(item.end_date)-getDate(item.start_date)+(i+1)-tmp_date; k++) {
								titleName[k][titleCnt[k]] = new Array();
								
								if (item.parent_event_id == "00000000000000000000") {
									titleName[k][titleCnt[k]][0] = item.event_id;
								}
								else {
									titleName[k][titleCnt[k]][0] = item.parent_event_id;
								}
								titleName[k][titleCnt[k]][1]  = item.title;
								titleName[k][titleCnt[k]][2]  = item.calendar_id;
								titleName[k][titleCnt[k]++][3]  = item.owner_id;
							}
						}
					}
				}
			}
		}

		for(i=0; i<7; i++) {
			tmp.push("<tr>");
			tmp.push("<th scope='row' class='" + getWeektoNum(i) + "'>"
					+ getDate(getDatetoDay(startDate, i))
					+ "<span class='dateWeek'>" + getWeektoNum(i)
					+ "</span></th>");
			tmp.push("<td");
			
			if (dateToString(new Date()) == dateToString(new Date(
					getYear(baseDate), getMonth(baseDate) - 1, getDate(startDate) + i))
					&& checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
				tmp.push(" class='selectDay'");
			}
			
			tmp.push(">");
			
			if (titleName[i] != undefined) {
				for(j=0; j<titleName[i].length; j++) {
					tmp.push("<a href=\"javascript:schDetail('" + titleName[i][j][0] + "', " + undefined + ", '" + titleName[i][j][3] + "');\">");
					tmp.push("<div><p></p>");
					tmp.push("<span class='title'>");
					
					for(k=0; k<calData.length; k++) {
						// 달력 정보 표시
						if (titleName[i][j][2] == calData[k].calendar_id) {
							tmp.push("<font color='" + calData[k].ui_attr + "'>["+ calData[k].title +"]</font>");
						}
						// 설비 정보 표시
						else if (titleName[i][j][2] == calData[k][0]) {
							tmp.push("<font color='" + calData[k][2] + "'>["+ calData[k][1] +"]</font>");
						}
					}
					tmp.push(titleName[i][j][1] + "</span></div>");
					tmp.push("</a>");
				}
			}
			
			tmp.push("</td>");
			tmp.push("</tr>");
		}
		tmp.push("</table>");
		divList.html(tmp.join(""));
		
		list.listview("refresh");
	}
	else if (dateMode == "MONTHLY") {
		if (dayMode == true) {
			var tmp = [];	
			var isFindData = false;
			
			if (data != undefined) {
				for(var i = 0; i<data.length;i++) {
					var item = data[i];
					
					if (checkDatePeriod(item.start_date, item.end_date, baseDate)) {
						tmp.push("<li id='" + item.event_id +"'>");
						
						if (apiCode == "schlist") {
							tmp.push("<a href=\"javascript:schDetail('" + item.event_id + "', 'schedule', '" + item.owner_id + "');\">");
						}
						if (apiCode == "equiplistdetail") {
							if (item.parent_event_id == "00000000000000000000") {
								tmp.push("<a href=\"javascript:schDetail('" + item.event_id + "', 'equipment', '" + item.owner_id + "');\">");
							}
							else {
								tmp.push("<a href=\"javascript:schDetail('" + item.parent_event_id + "', 'equipment', '" + item.owner_id + "');\">");
							}
						}
						tmp.push("<div class='calendarName'>");
						
						for(j=0; j<calData.length; j++) {
							// 달력 정보 표시
							if (item.calendar_id == calData[j].calendar_id) {
								tmp.push("<font color='" + calData[j].ui_attr + "'>["+ calData[j].title +"]</font></span>");
							}
							// 설비 정보 표시
							else if (item.calendar_id == calData[j][0]) {
								tmp.push("<font color='" + calData[j][2] + "'>["+ calData[j][1] +"]</font></span>");
							}
						}
						
						tmp.push("</div><h3 class='title'>"+item.title+"</h3>");
						tmp.push("<div class='dateInfo'>"
								+ getFilterDate(item.start_date, item.end_date, "dateTime", "startDate")
								+ " ~ <span>"
								+ getFilterDate(item.start_date, item.end_date,	"dateTime", "endDate") + "</span></div>");
						tmp.push("</a></li>");					
						isFindData = true;
					}				
				}
				
				if (isFindData == false) {
					tmp = [];
					
					tmp.push("<li><h3>" + "[" + view.find("#baseDate").text() + "] " + MGW_RES.get("gw_msg_schedule_schedule_nolist")+"</h3></li>");
					list.html(tmp.join(""));
					list.listview("refresh");
				}
				else {
					list.append(tmp.join(""));
					list.listview("refresh");
				}
			}
			else {
				var tmp = [];
				
				tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
				list.html(tmp.join(""));
				list.listview("refresh");
			}
		}	
		else {
			divList.attr("class","divList");
			var temp = 0;
			var tmp = [];
			
			tmp.push("<table class='calMonth'><tr>");
			tmp.push("<tr>");
			
			for(var i=0; i<7; i++) {
				tmp.push("<th class='" + getWeektoNum(i) + "'>" + getWeektoNum(i) + "</th>");
			}
			
			tmp.push("</tr>");
			tmp.push("<tbody>");
			tmp.push("<tr>");
			
			for(var i=0; i<getWeekNumber(startDate); i++) {
				tmp.push("<td></td>");
			}
			
			for(var i=0; i<getDate(endDate); i++) {
				var cnt = 0;

				if (data.length > temp) {
					if (getDate(data[temp].start_dt) == i + 1)	{
						tmp.push("<td onclick=\"javascript:monthToDayList('" + view.find("#baseDate").text() + "', '" + (i+1) + "');\"");
					}
					else {
						tmp.push("<td");
					}
				}
				else {
					tmp.push("<td");
				}
				
				if (getDate(dateToString(new Date())) == i+1 && checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
					tmp.push(" class='selectDay'");
				}
				
				tmp.push(">");
				
				// 토요일
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 0) {
					tmp.push("<span class='sat'>");
				}
				// 일요일
				else if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("<span class='sun'>");
				}
				// 평일
				else {
					tmp.push("<span>");
				}
				
				if (data != undefined && data.length > temp) {
					// 건수표시
					if (getDate(data[temp].start_dt) == i+1) {
						tmp.push((i+1)
							+ "</span><a href=\"javascript:;\" class='schcount'>"
							+ data[temp++].count
							+ MGW_RES.get("gw_schedule_calendar_count_label")
							+ "</a>");
					}
					else {
						tmp.push((i+1) + "</span>");
					}
				}
				else {
					tmp.push((i+1) + "</span>");
				}
				tmp.push("</td>");
				
				// 주변경
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("</tr><tr>");
				}
			}
			tmp.push("</tr><tbody>");
			divList.html(tmp.join(""));
		}
		
	}
}

function getSchEquipList(apiCode, page_num, dateMode, callDate, equipMode, equip_IDS, dayMode) {
	var baseDate = callDate;
	var startDate = baseDate;
	var endDate = baseDate;
	var curDate = "";
	var param = {};
	var calendarTitleList = new Array();
	var equipTitleList = new Array();
	
	var view = $.mobile.activePage;
	view.find("#dateMode").val(dateMode);
	view.find("#baseDate").val(baseDate);
	
	getCalendarList(function() {
		var data = GW_CONTROLLER_SCHEDULE.calendarData;
		
		param["COMPANY_CALENDAR_IDS"] = "";
		param["TEAM_CALENDAR_IDS"] = "";
		param["PRIVATE_CALENDAR_IDS"] = "";
		param["EQUIPMENT_IDS"] = "";

		var index = 0;
		for(var i=0; i<data.length; i++) {
			switch(data[i].owner_tp) {
			case '1':	// 전사
				param["COMPANY_CALENDAR_IDS"] += data[i].calendar_id+";";
				calendarTitleList[index] = new Array();
				calendarTitleList[index][0] = data[i].calendar_id;
				calendarTitleList[index++][1] = data[i].title;
				break;
			case '2':	// 부서
				param["TEAM_CALENDAR_IDS"] += data[i].calendar_id+";";
				calendarTitleList[index] = new Array();
				calendarTitleList[index][0] = data[i].calendar_id;
				calendarTitleList[index++][1] = data[i].title;
				break;
			case '3':	// 그룹 달력은 openApi에 등록되어있지 않음
				break;
			case '4':	// 개인
				// 나의 달력
				param["PRIVATE_CALENDAR_IDS"] += data[i].calendar_id+";";
				calendarTitleList[index] = new Array();
				calendarTitleList[index][0] = data[i].calendar_id;
				calendarTitleList[index++][1] = data[i].title;
				break;
			}
		}
			
		if (dateMode == "DAILY") {
			param["TARGET_START_DATE"] = startDate;
			param["TARGET_END_DATE"] = endDate;
			curDate = "<span>" + getYear(startDate) + "</span>." + getMonth(startDate) + "." + getDate(startDate) + "(" + getWeekName(startDate) + ")";
		}
		else if (dateMode == "WEEKLY") {
			startDate = getFirstDateOfWeek(startDate);
			endDate = getLastDateOfWeek(endDate);
					
			param["TARGET_START_DATE"] = startDate;
			param["TARGET_END_DATE"] = endDate;
			// YYYY.M.D(요일)~?.D(요일) 형식
			curDate = getFilterDate(startDate, endDate, "date", "startDate") + "(" + getWeekName(startDate)
						+ ")~" + "<span>" + getFilterDate(startDate, endDate, "date", "endDate") + "</span>" + "(" + getWeekName(endDate) + ")";
		}
		else if (dateMode == "MONTHLY") {
			startDate = getFirstDateOfMonth(startDate);
			endDate = getLastDateOfMonth(endDate);
					
			param["TARGET_START_DATE"] = startDate;
			param["TARGET_END_DATE"] = endDate;
				
			curDate = "<span>" + getYear(startDate) + "</span>." + getMonth(startDate);
		}
			
		view.find("#curDate").html(curDate);
		var equipData = GW_CONTROLLER_SCHEDULE.equipmentData;
	
		// 각 설비의 타이틀명들을 equipTitleList에 저장
		index = 0;
		for(var i=0; i<equipData.length; i++) {
			if (GW_CONTROLLER_SCHEDULE.selectAllEquipment == true) {
				param["EQUIPMENT_IDS"] += equipData[i].calendar_id + ";";
				equipTitleList[index] = new Array();
				equipTitleList[index][0] = equipData[i].calendar_id;
				equipTitleList[index][1] = equipData[i].title;
				equipTitleList[index][2] = equipData[i].ui_attr;
				equipTitleList[index++][3] = 0;
			}
			else {
				if (GW_CONTROLLER_SCHEDULE.selectedEquipmentIds != "") {
					var idArr =  GW_CONTROLLER_SCHEDULE.selectedEquipmentIds.split(";");
					for (var j=0; j<idArr.length; j++) {
						if (idArr[j] != "" && idArr[j] == equipData[i].calendar_id) {
							param["EQUIPMENT_IDS"] += equipData[i].calendar_id + ";";
							equipTitleList[index] = new Array();
							equipTitleList[index][0] = equipData[i].calendar_id;
							equipTitleList[index][1] = equipData[i].title;
							equipTitleList[index][2] = equipData[i].ui_attr;
							equipTitleList[index++][3] = 0;
						}	
					}
				}
			}
		}
		
		if (param["EQUIPMENT_IDS"] == "") {
			if (dateMode == "MONTHLY" && !dayMode) {
				renderSchEquipList(apiCode, page_num, undefined, equipTitleList, curDate, baseDate, dateMode, startDate, endDate, dayMode);
			}
			else {
				renderSchEquipList(apiCode, page_num, undefined, equipTitleList, curDate, baseDate, dateMode, startDate, endDate, dayMode);
			}
		}
		else {
			var equipParam = JSON.parse( JSON.stringify(param) );
			GW_PROXY.invokeOpenAPI("schedule", apiCode_equipList, param, function(equipListData) {
				if (dateMode == "MONTHLY" && !dayMode) {
					GW_PROXY.invokeOpenAPI("schedule", apiCode_equipCount, equipParam, function(equipCountData) {
						renderSchEquipList(apiCode, page_num, equipCountData, equipTitleList, curDate, baseDate, dateMode, startDate, endDate, dayMode);
					});
				}
				else {
					renderSchEquipList(apiCode, page_num, equipListData, equipTitleList, curDate, baseDate, dateMode, startDate, endDate, dayMode);
				}
			});
		}
		
	});
		
}

function renderSchEquipList(apiCode, page_num, data, calData, curDate, baseDate, dateMode, startDate, endDate, dayMode) {
var view = $.mobile.activePage;
	
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");
	view.find("#baseDate").html(baseDate);		// 기준일
	
	/* 기존데이터 초기화 */
	divList.html("");
	list.html("");
	
	/* 일간 UI Render */
	if (dateMode == "DAILY") {
		if (data != undefined && data.length != 0) {
			for(var i = 0; i< data.length;i++){// 리스트에 메일 렌더
				var item = data[i];
				var tmp = [];
				
				tmp.push("<li id=\"" + item.event_id +"\" >");
				if (item.parent_event_id == "00000000000000000000") {
					tmp.push("<a href=\"javascript:schDetail('" + item.event_id + "', 'sch_detaillist_equip', '" + item.owner_id + "');\">");
				}
				else {
					tmp.push("<a href=\"javascript:schDetail('" + item.parent_event_id + "', 'sch_detaillist_equip', '" + item.owner_id + "');\">");
				}
				tmp.push("<div class='calendarName'>");
				
				if (calData.length != 0) {
					for(var j=0; j<calData.length; j++) {
						if (item.calendar_id == calData[j].calendar_id) {
							tmp.push("<font color='" + calData[j].ui_attr + "'>[" + calData[j].title + "]</font></div>");
						}
						else if (item.calendar_id == calData[j][0]) {
							tmp.push("<font color='" + calData[j][2] + "'>[" + calData[j][1] + "]</font></div>");
						}
					}
				}
				
				tmp.push("<h3 class='title'>" + item.title + "</h3>");
				tmp.push("<div class='dateInfo'>" + item.start_date + " ~ " + item.end_date + "</div>");
				tmp.push("</a></li>");
				list.append(tmp.join(""));
			}
		}
		else {
			var tmp = [];
			
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_equip_nothing") + "</h3></li>");
			list.html(tmp.join(""));
		}
		
		list.listview("refresh");
	}
	else if (dateMode == "WEEKLY") {
		var tmp = [];
		var temp = 0;
		var titleName = new Array();
		var titleCnt = new Array();
		
		var n;
		var isCheck;
		// 하루 기준 milliseconds
		var oneDay = (1000*60*60*24);
		
		tmp.push("<table class='dateArea' border='0' cellspacing='0' cellpadding='0'>");
		divList.attr("class", "divList");
		
		if (data != undefined && data.length != 0) {
			for(i=0; i<7; i++) {
				titleCnt[i] = 0;
				titleName[i] = new Array;
			}
			
			for(i=0; i<7; i++) {
				n = titleCnt[i];
				tempToDay = getDatetoDay(startDate, i);
				
				for(var j=temp; j<data.length; j++) {
					isCheck = true;
					temp = j;
					var item = data[j];
					
					if (getDate(getDatetoDay(startDate, i)) == getDate(item.start_date)) {	// 해당
																							// 날짜
																							// 데이터만
																							// 추출
						titleName[i][n] = new Array();
						
						if (item.parent_event_id == "00000000000000000000") {
							titleName[i][n][0] = item.event_id;
						} 
						else {
							titleName[i][n][0] = item.parent_event_id;
						}
						titleName[i][n][1] = item.title;
						titleName[i][n][2] = item.calendar_id;
						titleName[i][n++][3] = item.owner_id;
						
						if (temp == data.length - 1) {
							temp++;
						}
					}
					// 일정 시작날짜가 현재일보다 빠른경우
					else if (!checkDateTimePeriod(getYear(tempToDay), getMonth(tempToDay), getDate(tempToDay),0,0,
							getYear(item.start_date), getMonth(item.start_date), getDate(item.start_date),0,0))	{
						
						if (getMonth(item.start_date) != getMonth(item.end_date) ||
								getYear(item.start_date) != getYear(item.end_date) ||
								getDate(getLastDateOfWeek(item.end_date)) != getDate(endDate)) {
							for(k=i; k<7; k++) {
								titleName[k][titleCnt[k]] = new Array();
								
								if (item.parent_event_id == "00000000000000000000") {
									titleName[k][titleCnt[k]][0] = item.event_id;
								}
								else {
									titleName[k][titleCnt[k]][0] = item.parent_event_id;
								}
								titleName[k][titleCnt[k]][1]  = item.title;
								titleName[k][titleCnt[k]][2]  = item.calendar_id;
								titleName[k][titleCnt[k]++][3]  = item.owner_id;
							}
						}
						else if (getDate(item.start_date) != getDate(item.end_date)) {
							var loopDate;
							
							if (!checkDatePeriod(startDate, endDate, item.start_date)) {
								loopDate = getDate(item.end_date) - getDate(startDate) + i + 1;
							}
							else {
								loopDate = getDate(item.end_date) - getDate(item.start_date) + (i);
							}
							// 다음날이 0시 00 분일 경우 표시되어서는 안된다.
							if(item.end_date.lastIndexOf("0:00") != -1)
								loopDate--;								
							for(k=i; k<loopDate; k++) {
								titleName[k][titleCnt[k]] = new Array();
								
								if (item.parent_event_id == "00000000000000000000") {
									titleName[k][titleCnt[k]][0] = item.event_id;
								}
								else {
									titleName[k][titleCnt[k]][0] = item.parent_event_id;
								}
								titleName[k][titleCnt[k]][1] = item.title;
								titleName[k][titleCnt[k]][2]  = item.calendar_id;
								titleName[k][titleCnt[k]++][3]  = item.owner_id;
							}		
						}
						if (temp == data.length-1) {
							temp++;
						}
						isCheck = false;
					}
					else { 
						break;
					}
					
					if (isCheck) {
						if (getMonth(item.start_date) != getMonth(item.end_date)
								|| getYear(item.start_date) != getYear(item.end_date)
								|| getDate(getLastDateOfWeek(item.end_date)) != getDate(endDate)) {
							// 월이 변경되는 경우에 체크해주는 것이 필요
							var base_cnt = parseInt((new Date(getYear(item.end_date), getMonth(item.end_date)-1, getDate(item.end_date), getHours(item.end_date), getMinutes(item.end_date)) - new Date(getYear(item.start_date), getMonth(item.start_date)-1, getDate(item.start_date), getHours(item.start_date), getMinutes(item.start_date)))/oneDay,10);
							var inc_cnt = 0;
							for(k=i+1; k<7; k++) {
								if(base_cnt <= inc_cnt)
									break;
								titleName[k][titleCnt[k]] = new Array();
								if (item.parent_event_id == "00000000000000000000") {
									titleName[k][titleCnt[k]][0] = item.event_id;
								}
								else {
									titleName[k][titleCnt[k]][0] = item.parent_event_id;
								}
								titleName[k][titleCnt[k]][1]  = item.title;
								titleName[k][titleCnt[k]][2]  = item.calendar_id;
								titleName[k][titleCnt[k]++][3]  = item.owner_id;
								inc_cnt++;
							}
						}
						else if (getDate(item.start_date) != getDate(item.end_date)) {
							var tmp_date=0;
							// 다음날이 0시 00 분일 경우 표시되어서는 안된다.
							if(item.end_date.lastIndexOf("0:00") != -1)
								tmp_date=1;
							for(k=i+1; k<getDate(item.end_date)-getDate(item.start_date)+(i+1)-tmp_date; k++) {
								titleName[k][titleCnt[k]] = new Array();
								
								if (item.parent_event_id == "00000000000000000000") {
									titleName[k][titleCnt[k]][0] = item.event_id;
								}
								else {
									titleName[k][titleCnt[k]][0] = item.parent_event_id;
								}
								titleName[k][titleCnt[k]][1]  = item.title;
								titleName[k][titleCnt[k]][2]  = item.calendar_id;
								titleName[k][titleCnt[k]++][3]  = item.owner_id;
							}
						}
					}
				}
			}
		}

		for(i=0; i<7; i++) {
			tmp.push("<tr>");
			tmp.push("<th scope='row' class='" + getWeektoNum(i) + "'>"
					+ getDate(getDatetoDay(startDate, i))
					+ "<span class='dateWeek'>" + getWeektoNum(i)
					+ "</span></th>");
			tmp.push("<td");
			
			if (dateToString(new Date()) == dateToString(new Date(
					getYear(baseDate), getMonth(baseDate) - 1, getDate(startDate) + i))
					&& checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
				tmp.push(" class='selectDay'");
			}
			
			tmp.push(">");
			
			if (titleName[i] != undefined) {
				for(j=0; j<titleName[i].length; j++) {
					tmp.push("<a href=\"javascript:schDetail('" + titleName[i][j][0] + "', 'sch_detaillist_equip', '" + titleName[i][j][3] + "');\">");
					tmp.push("<div><p></p>");
					tmp.push("<span class='title'>");
					
					for(var k=0; k<calData.length; k++) {
						// 달력 정보 표시
						if (titleName[i][j][2] == calData[k].calendar_id) {
							tmp.push("<font color='" + calData[k].ui_attr + "'>["+ calData[k].title +"]</font>");
						}
						// 설비 정보 표시
						else if (titleName[i][j][2] == calData[k][0]) {
							tmp.push("<font color='" + calData[k][2] + "'>["+ calData[k][1] +"]</font>");
						}
					}
					tmp.push(titleName[i][j][1] + "</span></div>");
					tmp.push("</a>");
				}
			}
			
			tmp.push("</td>");
			tmp.push("</tr>");
		}
		tmp.push("</table>");
		divList.html(tmp.join(""));
		
		list.listview("refresh");
	}
	else if (dateMode == "MONTHLY") {
		if (dayMode == true) {
			var tmp = [];	
			var isFindData = false;
			
			if (data != undefined) {
				for(var i = 0; i<data.length;i++) {
					var item = data[i];
					
					if (checkDatePeriod(item.start_date, item.end_date, baseDate)) {
						tmp.push("<li id='" + item.event_id +"'>");
						
						if (item.parent_event_id == "00000000000000000000") {
							tmp.push("<a href=\"javascript:schDetail('" + item.event_id + "', 'equipment', '" + item.owner_id + "');\">");
						}
						else {
							tmp.push("<a href=\"javascript:schDetail('" + item.parent_event_id + "', 'equipment', '" + item.owner_id + "');\">");
						}
						tmp.push("<div class='calendarName'>");
						
						for(j=0; j<calData.length; j++) {
							// 달력 정보 표시
							if (item.calendar_id == calData[j].calendar_id) {
								tmp.push("<font color='" + calData[j].ui_attr + "'>["+ calData[j].title +"]</font></span>");
							}
							// 설비 정보 표시
							else if (item.calendar_id == calData[j][0]) {
								tmp.push("<font color='" + calData[j][2] + "'>["+ calData[j][1] +"]</font></span>");
							}
						}
						
						tmp.push("</div><h3 class='title'>"+item.title+"</h3>");
						tmp.push("<div class='dateInfo'>"
								+ getFilterDate(item.start_date, item.end_date, "dateTime", "startDate")
								+ " ~ <span>"
								+ getFilterDate(item.start_date, item.end_date,	"dateTime", "endDate") + "</span></div>");
						tmp.push("</a></li>");					
						isFindData = true;
					}				
				}
				
				if (isFindData == false) {
					tmp = [];
					
					tmp.push("<li><h3>" + "[" + view.find("#baseDate").text() + "] " + MGW_RES.get("gw_msg_schedule_schedule_nolist")+"</h3></li>");
					list.html(tmp.join(""));
					list.listview("refresh");
				}
				else {
					list.append(tmp.join(""));
					list.listview("refresh");
				}
				
				
			}
			else {
				var tmp = [];
				
				tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
				list.html(tmp.join(""));
				list.listview("refresh");
			}
		}	
		else {
			divList.attr("class","divList");
			var temp = 0;
			var tmp = [];
			
			tmp.push("<table class='calMonth'><tr>");
			tmp.push("<tr>");
			
			for(var i=0; i<7; i++) {
				tmp.push("<th class='" + getWeektoNum(i) + "'>" + getWeektoNum(i) + "</th>");
			}
			
			tmp.push("</tr>");
			tmp.push("<tbody>");
			tmp.push("<tr>");
			
			for(var i=0; i<getWeekNumber(startDate); i++) {
				tmp.push("<td></td>");
			}
			
			for(var i=0; i<getDate(endDate); i++) {
				var cnt = 0;

				if (data != undefined && data.length > temp) {
					if (getDate(data[temp].start_dt) == i + 1)	{
						tmp.push("<td onclick=\"javascript:monthToDayList('" + view.find("#baseDate").text() + "', '" + (i+1) + "');\"");
					}
					else {
						tmp.push("<td");
					}
				}
				else {
					tmp.push("<td");
				}
				
				if (getDate(dateToString(new Date())) == i+1 && checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
					tmp.push(" class='selectDay'");
				}
				
				tmp.push(">");
				
				// 토요일
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 0) {
					tmp.push("<span class='sat'>");
				}
				// 일요일
				else if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("<span class='sun'>");
				}
				// 평일
				else {
					tmp.push("<span>");
				}
				
				
				if (data != undefined && data.length > temp) {
					// 건수표시
					if (getDate(data[temp].start_dt) == i+1) {
						tmp.push((i+1)
							+ "</span><a href=\"javascript:;\" class='schcount'>"
							+ data[temp++].count
							+ MGW_RES.get("gw_schedule_calendar_count_label")
							+ "</a>");
					}
					else {
						tmp.push((i+1) + "</span>");
					}
				}
				else {
					tmp.push((i+1) + "</span>");
				}
				tmp.push("</td>");
				
				// 주변경
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("</tr><tr>");
				}
			}
			tmp.push("</tr><tbody>");
			divList.html(tmp.join(""));
		}
	}
	
}

function getSchTodoList(apiCode, page_num, dateMode, calId, callDate) {
	var baseDate = callDate;
	var startDate = baseDate;
	var endDate = baseDate;
	var curDate = "";
	var param = {};
	var calMode = "ALL";
	var companyCalendarIds = "";
	var teamCalendarIds = "";
	var privateCalendarIds = "";
	var noCheckValue = "00000000000000000000";
	
	getCalendarList(function() {
		// 달력별 Id 셋팅
		if (GW_CONTROLLER_SCHEDULE.calendarData.length != 0) {
			for(var i=0; i<GW_CONTROLLER_SCHEDULE.calendarData.length; i++) {
				if (GW_CONTROLLER_SCHEDULE.calendarData[i].calendar_id == calId)
					calMode = GW_CONTROLLER_SCHEDULE.calendarData[i].owner_tp;
			}
		}
	
		// 달력 필터에 따른 param value 셋팅 (calMode value 0:전체,1:전사,2:부서,4:개인)
		if (calMode == "1") {
			companyCalendarIds = calId;
			teamCalendarIds = noCheckValue;
			privateCalendarIds = noCheckValue;
		}
		else if (calMode == "2") {
			companyCalendarIds = noCheckValue;
			teamCalendarIds = calId;
			privateCalendarIds = noCheckValue;
		}
		else if (calMode == "4") {
			companyCalendarIds = noCheckValue;
			teamCalendarIds = noCheckValue;
			privateCalendarIds = calId;
		} else {
			companyCalendarIds = GW_CONTROLLER_SCHEDULE.companyCalendarIds;
			teamCalendarIds = GW_CONTROLLER_SCHEDULE.deptCalendarIds;
			privateCalendarIds = GW_CONTROLLER_SCHEDULE.myCalendarIds;
		}
		
		param["COMPANY_CALENDAR_IDS"] = companyCalendarIds;
		param["TEAM_CALENDAR_IDS"] = teamCalendarIds;
		param["PRIVATE_CALENDAR_IDS"] = privateCalendarIds;
			
		if (dateMode == "DAILY") {
			param["START_DT"] = startDate;
			param["END_DT"] = endDate;
			curDate = "<span>" + getYear(startDate) + "</span>." + getMonth(startDate) + "." + getDate(startDate) + "(" + getWeekName(startDate) + ")";
		}
		else if (dateMode == "WEEKLY") {
			startDate = getFirstDateOfWeek(startDate);
			endDate = getLastDateOfWeek(endDate);
			
			param["START_DT"]=startDate;
			param["END_DT"]=endDate;
			
			// YYYY.M.D(요일)~?.D(요일) 형식
			curDate = getFilterDate(startDate, endDate, "date", "startDate") + "(" + getWeekName(startDate)
						+ ")~" + "<span>" + getFilterDate(startDate, endDate, "date", "endDate") + "</span>" + "(" + getWeekName(endDate) + ")";
		}
		else if (dateMode == "MONTHLY") {
			startDate = getFirstDateOfMonth(startDate);
			endDate = getLastDateOfMonth(endDate);
				
			param["START_DT"]=startDate;
			param["END_DT"]=endDate;
				
			curDate = "<span>" + getYear(startDate) + "</span>." + getMonth(startDate);
		}
		
		GW_PROXY.invokeOpenAPI("schedule", apiCode_todoList, param, function(todoData) {
			renderSchTodoList(apiCode, page_num, todoData, GW_CONTROLLER_SCHEDULE.calendarData, curDate, baseDate, dateMode, startDate, endDate);
		});
	});
}

function renderSchTodoList(apiCode, page_num, todoData, calData, curDate, baseDate, dateMode, startDate, endDate){
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	var calTitle = ""; 
	var calColor = "";
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");
	view.find("#baseDate").html(baseDate);
	view.find("#curDate").html(curDate);
	
	divList.html("");
	list.html("");
	
	if (todoData.length != 0) {
		for(var i = 0; i< todoData.length;i++) {		
			var item = todoData[i];
			var tmp = [];
			
			tmp.push("<li id=\"" + item.todo_id +"\" ><div class='grouping_left'>");
			
			if (item.todo_st == 2) {
				tmp.push("<div class='todo_st_end'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_status_finish_label") + "</div>");
			}
			else if (item.todo_st == 3) {
				tmp.push("<div class='todo_st_delay'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_status_delay_label") + "</div>");
			}
			else if (item.todo_st == 4) {
				tmp.push("<div class='todo_st_cancel'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_status_cancel_label") + "</div>");
			}
			else {
				tmp.push("<div class='todo_st_ing'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_status_progress_label") + "</div>");
			}
			
			if (item.weight == 1) {
				tmp.push("<div class='todo_weight_h'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_weight_high") + "</div>");
			}
			else if (item.weight == 2) {
				tmp.push("<div class='todo_weight_m'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_weight_medium") + "</div>");
			}
			else {
				tmp.push("<div class='todo_weight_l'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_weight_low") + "</div>");
			}
			
			if (calData.length != 0) {
				for(j=0; j<calData.length; j++) {
					if (item.calendar_id == calData[j].calendar_id) {
						calTitle = calData[j].title;
						calColor = calData[j].ui_attr;
						calColor = calColor.replace("#", "");
					}
				}
			}
			
			tmp.push("</div><a href=\"javascript:todoDetail('" + item.todo_id + "', '" + item.owner_id + "', '" + calTitle + "', '" + calColor + "');\">");
			tmp.push("<h3>");
			tmp.push("<font color='" + calColor + "'>["+ calTitle +"] " + "</font>" + item.title);
			tmp.push("</h3></a>");
			tmp.push("</li>");
			list.append(tmp.join(""));
		}
	}
	else {
		var tmp = [];
		tmp.push("<li><h3>"+MGW_RES.get("gw_msg_schedule_todo_nolist")+"</h3></li>");
		list.html(tmp.join(""));
	}
	
	list.listview("refresh");
}
/*******************************************************************************
 * Schedule List END *
 ******************************************************************************/



/*******************************************************************************
 * Schedule Detail START *
 ******************************************************************************/
// 일정 상세보기
function getSchDetail(apiCode, eventId, ownerId) {
	var param = {};
	getCalendarList(function() {
		var calData = GW_CONTROLLER_SCHEDULE.calendarData;
		param = {};
		param["USER_ID"] = ownerId;
		param["EVENT_ID"] = eventId;

		GW_PROXY.invokeOpenAPI("schedule", apiCode_schDetail, param, function(schData) {
			if (schData[0].owner_id == sessionStorage["id"]) {
				TOOLBAR_DEF.sch_schdetail =
					[2, [MGW_RES.get("gw_common_modify_label"), MGW_RES.get("gw_common_delete_label")], 
					["btn_tool_approval.png", "btn_tool_delete.png"],
					["javascript:showSchAdd(true);", "javascript:deleteSch();"]];
			}
			
			PAGE_CONTROLLER.showPage("sch_schdetail", function() {
				renderSchDetail(apiCode, schData, calData);
			});
		});
	});
}

function renderSchDetail(apiCode, schData, calData) {
	var view = $.mobile.activePage;	
	if ($.mobile.activePage.prop("id") != "sch_schdetail")
		return;
	
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");
	item = schData[0];
	
	if (item.owner_id == sessionStorage["id"]) {
		view.find("#headerBtn").show();
	}

	view.find("#event_id").val(item.event_id);
	view.find("#start_dt").val(item.start_date);
	
	list.find("#li_title").html(schData[0].title);
	
	if (schData[0].owner_id != "") { // 조직도 사용자
		list.find("#li_writer").html("<a href=\"javascript:showUserDetails('" + schData[0].owner_id + "')\" class='btn_user blue'><span>" 
				+ schData[0].owner_name + "</span><span class='viewUser'></span></a>");	
	}
	else {
		list.find("#li_writer").html(schData[0].owner_name);	
	}
	
	if (item.recurring_flag == 0) {
		list.find("li_repeat").html(MGW_RES.get("gw_schedule_repeat_label") + " " + MGW_RES.get("gw_schedule_not_label"));
	}
	else if (item.recurring_flag == 1) {
		list.find("li_repeat").html(MGW_RES.get("gw_schedule_repeat_label") + " " + MGW_RES.get("gw_schedule_have_label"));
	}

	list.find("#li_term").html(getFilterDate(item.start_date, item.end_date, "dateTime", "startDate")
							+ "(" + getWeekName(item.start_date) + ") ~ " + "<span>" 
							+ getFilterDate(item.start_date, item.end_date, "dateTime", "endDate") 
							+ "</span>" + "(" + getWeekName(item.end_date) + ")");
	var calender_title = "";
	for(var j=0; j<calData.length; j++) {
		if (item.calendar_id == calData[j].calendar_id) {
			calender_title += calData[j].title;
		}
	}
	list.find("#li_calendar").html(calender_title);
	
	if (item.equipment_flag == 0) {
		list.find("#li_equipment").html(MGW_RES.get("gw_schedule_not_label"));
	}
	else if (item.equipment_flag == 1) {
		list.find("#li_equipment").html(MGW_RES.get("gw_schedule_have_label"));
	}

	list.find("#li_content").html(replaceNewlineTag(schData[0].description));
}

// 설비 상세보기
function getSchEquipDetail(apiCode, eventId, ownerId) {
	var param = {};
	var view = $.mobile.activePage;
	var calendarTitleList = new Array();
	var equipTitleList = new Array();
	var parCalendarIdList = new Array();
	
	param["USER_ID"] = ownerId;
	param["EVENT_ID"] = eventId;
	
	GW_PROXY.invokeOpenAPI("schedule", apiCode_equipDetail, param, function(data) {
		var calData = GW_CONTROLLER_SCHEDULE.calendarData;
		var equipData = GW_CONTROLLER_SCHEDULE.equipmentData;
		var isParEquip = true;
		var isEquip = false;
		var index = 0;
			
		for(var i=0; i<equipData.length; i++) {
			if (equipData[i].equipment_sort_fg == 1) {
				parCalendarIdList[index++] = equipData[i].calendar_id;
			}
		}
				
		// 각 설비의 타이틀명들을 equipTitleList에 저장
		index = 0;
				
		for(var i=0; i<equipData.length; i++) {
			isParEquip = true;
			isEquip = false;
				
			for(var j=0; j<parCalendarIdList.length; j++) {
				if (parCalendarIdList[j] == equipData[i].calendar_id) {
					isParEquip = false;
				}
				if (parCalendarIdList[j] == equipData[i].par_calendar_id) {
					isEquip = true;
				}
			}
					
			if (isParEquip && isEquip) {
				equipTitleList[index] = new Array();
				equipTitleList[index][0] = equipData[i].calendar_id;
				equipTitleList[index][1] = equipData[i].title;
				equipTitleList[index][2] = equipData[i].ui_attr;
				equipTitleList[index++][3] = 0;
			}
		}
		
		if (calData.length != 0) {
			for(var i=0; i<calData.length; i++) {
				if (data[0].calendar_id == calData[i].calendar_id) {
					calendarTitleList[0] = new Array();
					calendarTitleList[0][0] = calData[i].calendar_id;
					calendarTitleList[0][1] = calData[i].title;
					calendarTitleList[0][2] = calData[i].ui_attr;
					break;
				}
			}
			// 달력 정보를 가져오지 못했을 경우
			if (calendarTitleList.length == 0) {
				calendarTitleList[0] = new Array();
				calendarTitleList[0][0] = calData[0].calendar_id;
				calendarTitleList[0][1] = calData[0].title;
				calendarTitleList[0][2] = calData[0].ui_attr;
			}
		}
		
		if (data[0].owner_id == sessionStorage["id"]) {
			TOOLBAR_DEF.sch_equipdetail =
				[2, [MGW_RES.get("gw_common_modify_label"), MGW_RES.get("gw_common_delete_label")], 
				["btn_tool_approval.png", "btn_tool_delete.png"],
				["javascript:showSchAdd(true);", "javascript:deleteSch();"]];
		}
		
		PAGE_CONTROLLER.showPage("sch_equipdetail", function() {
			renderSchEquipDetail(apiCode, data, equipTitleList, calendarTitleList);
		});
	
	});
	
}

function renderSchEquipDetail(apiCode, data, equipTitleList, calendarTitleList) {
	var view = $.mobile.activePage;
	if (view.prop("id") != "sch_equipdetail")
		return;
	
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");
	item = data[0];
	
	if (item.owner_id == sessionStorage["id"]) {
		view.find("#headerBtn").show();
	}
	
	view.find("#event_id").val(item.event_id);
	view.find("#start_dt").val(item.start_date);
	
	var tmp = [];
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_title_label") + "</div><div class='grouping_middle'>" + item.title);	
	tmp.push("</div></li>");
	tmp.push("<li>");
	
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_writer_label") + "</div><div class='grouping_middle'>");	
	
	if (item.owner_id != "") { // 조직도 사용자
		tmp.push("<a href=\"javascript:showUserDetails('" + item.owner_id + "')\" class='btn_user blue'><span>" 
				+ item.owner_name + "</span><span class='viewUser'></span></a>");	
	}
	else {
		tmp.push(item.owner_name);	
	}
	
	tmp.push("</div></li>");
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_repeat_label") + "</div><div class='grouping_middle'>");
	
	if (item.recurring_flag == 0) {
		tmp.push(MGW_RES.get("gw_schedule_not_label"));
	}
	else if (item.recurring_flag == 1) {
		tmp.push(MGW_RES.get("gw_schedule_have_label"));
	}
	tmp.push("</div></li>");
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>"
			+ MGW_RES.get("gw_schedule_term_label") + "</div><div class='grouping_middle'>"
			+ item.start_date + "("
			+ getWeekName(item.start_date) + ") ~ " 
			+ item.end_date
			+ "(" + getWeekName(item.end_date) + ")");
	tmp.push("</div></li>");
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>"
			+ MGW_RES.get("gw_schedule_calendar_label") + "</div><div class='grouping_middle'>"
			+ calendarTitleList[0][1]);
	tmp.push("</div></li>");
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_equipment_label") + "</div><div class='grouping_middle'>");
	
	if (item.equipment_flag == 1) {
		for(var i=0; i<item.equipment_list.length; i++) {
			for(var j=0; j<equipTitleList.length; j++) {
				if (item.equipment_list[i].e_calendar_id == equipTitleList[j][0]) {
					if (i == 0) {
						tmp.push(equipTitleList[j][1]);
					}
					else {
						tmp.push(", " + equipTitleList[j][1]);
					}
				}
			}
		}
	}
	
	tmp.push("</div></li>");
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_content_label") + "</div><div class='grouping_middle'>");
	tmp.push("<div class='description'>" + replaceNewlineTag(item.description) + "</div>");
	tmp.push("<div></li>");
	
	if ($.mobile.activePage.prop("id") != "sch_equipdetail")
		return;
	
	list.html(tmp.join(""));	
	list.listview("refresh");
}

// 할일 상세보기
function getSchTodoDetail(todoId, ownerId, calTitle, calColor) {
	var param = {};
	
	param["USER_ID"] = ownerId;
	param["TODO_ID"] = todoId;
	
	GW_PROXY.invokeOpenAPI("schedule", apiCode_todoDetail, param, function(data) {
		item = data[0];
		
		if (item.owner_id == sessionStorage["id"]) {
			TOOLBAR_DEF.sch_tododetail =
				[2, [MGW_RES.get("gw_common_modify_label"), MGW_RES.get("gw_common_delete_label")], 
				["btn_tool_approval.png", "btn_tool_delete.png"],
				["javascript:showTodoAdd(true);", "javascript:deleteTodo();"]];
		}
		
		PAGE_CONTROLLER.showPage("sch_tododetail", function() {
			renderSchTodoDetail(data, calTitle, calColor);
		});
	});
}

function renderSchTodoDetail(data, calTitle, calColor) {	
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");
	item = data[0];
	
	if (item.owner_id == sessionStorage["id"]) {
		view.find("#headerBtn").show();
	}
	
	view.find("#todo_id").val(item.todo_id);
	view.find("#start_dt").val(item.start_dt);
	
	var tmp = [];
	// 제목
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_title_label") + "</div><div class='grouping_middle'>" + data[0].title);	
	tmp.push("</div></li>");
	// 작성자
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_writer_label") + "</div><div class='grouping_middle'>");
	
	if (data[0].owner_id != "") { // 조직도 사용자
		tmp.push("<a href=\"javascript:showUserDetails('" + data[0].owner_id + "')\" class='btn_user blue'><span>" 
				+ data[0].owner_nm + "</span><span class='viewUser'></span></a>");	
	}
	else {
		tmp.push(data[0].owner_nm);	
	}
	
	tmp.push("</div></li>");
	// 반복
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_repeat_label") + "</div><div class='grouping_middle'>");
	
	if (item.recurrence_fg == 0) {
		tmp.push(MGW_RES.get("gw_schedule_repeat_label") + " " + MGW_RES.get("gw_schedule_not_label"));
	}
	else if (item.recurrence_fg == 1) {
		tmp.push(MGW_RES.get("gw_schedule_repeat_label") + " " + MGW_RES.get("gw_schedule_have_label"));
	}
	tmp.push("</div></li>");
	// 기간
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_term_label") + "</div><div class='grouping_middle'>" 
			+ item.start_dt + "(" + getWeekName(item.start_dt) + ") ~ " + "<span>" 
			+ item.end_dt + "</span>" + "(" + getWeekName(item.end_dt) + ")");	
	tmp.push("</div></li>");
	// 달력
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_calendar_label") + "</div><div class='grouping_middle'>");
	tmp.push(calTitle);
	tmp.push("</div></li>");
	// 중요도
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_importance_label") + "</div><div class='grouping_middle'>");
	if (item.weight == "1") {
		tmp.push(MGW_RES.get("gw_schedule_weight_high"));
	}
	else if (item.weight == "2") {
		tmp.push(MGW_RES.get("gw_schedule_weight_medium"));
	}
	else if (item.weight == "3") {
		tmp.push(MGW_RES.get("gw_schedule_weight_low"));
	}
	tmp.push("</div></li>");
	// 상태
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_status_label") + "</div><div class='grouping_middle'>");
	tmp.push("[");
	if (item.todo_st == "1") {
		tmp.push(MGW_RES.get("gw_schedule_status_progress_label"));
	}
	else if (item.todo_st == "2") {
		tmp.push(MGW_RES.get("gw_schedule_status_finish_label"));
	}
	else if (item.todo_st == "3") {
		tmp.push(MGW_RES.get("gw_schedule_status_delay_label"));
	}
	else if (item.todo_st == "4") {
		tmp.push(MGW_RES.get("gw_schedule_status_cancel_label"));
	}
	tmp.push("]");
	// 진척률
	tmp.push(" " +  MGW_RES.get("gw_schedule_progress_label") + " " + item.progress + "%");
	tmp.push("</div></li>");
	// 내용
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_content_label") + "</div><div class='grouping_middle'>");
	tmp.push("<div class='description'>" + replaceNewlineTag(data[0].description) + "</div>");
	tmp.push("</div></li>");
	
	list.html(tmp.join(""));	
	list.listview("refresh");
}
/*******************************************************************************
 * Schedule Detail END *
 ******************************************************************************/



/*******************************************************************************
 * Schedule / Todo Add&Edit&Delete START *
 ******************************************************************************/
function getSchAdd(eventId) {
	var param = {};
	
	getCalendarList(function() {
		var calData = GW_CONTROLLER_SCHEDULE.calendarData;
				
		getEquipmentList(function() {
			// 일정 수정
			if (eventId != undefined && eventId != "") {
				changeTitle(MGW_RES.get("gw_schedule_schmodify_label"));
				
				param["EVENT_ID"] = eventId;
				
				GW_PROXY.invokeOpenAPI("schedule", apiCode_schDetail, param, function(schData) {
					renderSchAdd(calData, GW_CONTROLLER_SCHEDULE.equipmentData, schData);
				});
			}
			// 일정 추가
			else {
				renderSchAdd(calData, GW_CONTROLLER_SCHEDULE.equipmentData);
			}
		});
	});
}

// calData:달력 목록, equipData:설비 목록, schData:일정/설비정보(수정용)
function renderSchAdd(calData, equipData, schData) {
	var view = $.mobile.activePage;
	
	if (view.prop("id") != "sch_schadd")
		return;
	
	var selectCal = view.find("#calendar_id");
	var selectdtStartTime = view.find("#dtstart_hour");
	var selectdtEndTime = view.find("#dtend_hour");
	
	view.find("#dtstart_date").val(getToday());
	view.find("#dtend_date").val(getToday());
	view.find("#txt_dtstart_date").val(getToday());
	view.find("#txt_dtend_date").val(getToday());
	
	doSetDatebox();

	for(var i = 0; i < 24; i++){
		var time = "0";
		var startTime = "0";
		var endTime = "0";
		var tempTime = getHour();
		var nowTime = tempTime < 10 ? "0"+tempTime : tempTime;
		
		time = i < 10 ? "0"+i : i;
		startTime = nowTime;
		endTime = nowTime + 1;
		
		if (time == startTime) {
			selectdtStartTime.append("<option value='" + time + "' selected='selected'>" + time +  MGW_RES.get("gw_common_hour_label") + "</option>");
		} 
		else {
			selectdtStartTime.append("<option value='" + time + "'>" + time +  MGW_RES.get("gw_common_hour_label") + "</option>");
		}
		
		if (time == endTime) {
			selectdtEndTime.append("<option value='" + time + "' selected='selected'>" + time +  MGW_RES.get("gw_common_hour_label") + "</option>");
		}
		else {
			selectdtEndTime.append("<option value='" + time + "'>" + time +  MGW_RES.get("gw_common_hour_label") + "</option>");
		}
	}
	
	// 달력 목록 랜더링
	if (calData.length != 0) {
		for(var i=0; i<calData.length; i++) {
			if (calData[i].write_auth == 1) {
				selectCal.append("<option value='" + calData[i].calendar_id + "'>" + calData[i].title + "</option>");
			}
		}
	}
	
	// 설비 목록 랜더링
	var tmp = [];
	tmp.push("<ul>");
	
	if (equipData.length != 0) {
		for(var i=0; i<equipData.length; i++) {
			// 상태(1:정상), 설비(0:설비,1:설비분류) 인 경우만 설비 목록에 표시
			if (equipData[i].calendar_st == "1" && equipData[i].equipment_sort_fg == "0") {
				tmp.push("<li><label><input type='checkbox' id='equipment' data-role='none' name='" 
						+ equipData[i].calendar_id 
						+ "' value='" + equipData[i].calendar_id + "'/>" 
						+ equipData[i].title + "</label></li>");
			}
		}
	} 
	else {
		tmp.push("<li><label>" + MGW_RES.get("gw_msg_schedule_equip_nothing") + "</label></li>");
	}
	
	tmp.push("</ul>");
	view.find("#equipmentlist").html(tmp.join(""));
	
	// 일정 수정 시 기존 데이터 반영
	if (schData != undefined) {
		var description = replaceNewlineTag(schData[0].summary);
		
		view.find("#isUpdate").val(true);
		view.find("#event_id").val(schData[0].event_id);
		view.find("#recur_id").val(schData[0].recurrence.recur_id);
		view.find("#title").val(schData[0].title);
		
		if (description != "")
			view.find("#summary").val(description.split("<br>").join("\n"));
		
		view.find("#dtstart_date").val(getDateString(schData[0].start_date));
		view.find("#dtstart_hour").val(getHoursString(schData[0].start_date));
		view.find("#dtstart_time").val(getMinutesString(schData[0].start_date));
		
		view.find("#dtend_date").val(getDateString(schData[0].end_date));
		view.find("#dtend_hour").val(getHoursString(schData[0].end_date));
		view.find("#dtend_time").val(getMinutesString(schData[0].end_date));
		
		view.find("#calendar_id").val(schData[0].calendar_id);
		
		if (typeof schData[0].equipment_list == "string") {
			view.find("#rdoEquipN").attr("checked", "checked");
			toggleEquip(false);
		}
		else {
			for(i=0; i<schData[0].equipment_list.length; i++) {
				view.find(".grouping_middle").find("[name=" + schData[0].equipment_list[i].e_calendar_id + "]").attr("checked", "checked");
			}
		}
	}
}

function getTodoAdd(todoId) {
	var param = {};
	
	getCalendarList(function() {
		var calData = GW_CONTROLLER_SCHEDULE.calendarData;
		
		// 할일 수정
		if (todoId != undefined && todoId != "") {
			changeTitle(MGW_RES.get("gw_schedule_todo_modify_label"));
			
			param["TODO_ID"] = todoId;
			param["USER_ID"] = sessionStorage["id"];
			
			GW_PROXY.invokeOpenAPI("schedule", apiCode_todoDetail, param, function(todoData) {
				renderTodoAdd(calData, todoData);
			});
		}
		// 할일 추가
		else {
			renderTodoAdd(calData);
		}
	});
}

// calData:달력 목록, equipList:설비예약 목록
function renderTodoAdd(calData, todoData) {
	var view = $.mobile.activePage;
	if (view.prop("id") != "sch_todoadd")
			return
	
	var selectCal = view.find("#calendar_id");
	var list = view.find("#list");
	view.find("#dtstart_date").val(getToday());
	view.find("#dtend_date").val(getToday());
	view.find("#txt_dtstart_date").val(getToday());
	view.find("#txt_dtend_date").val(getToday());
	
	doSetDatebox();
	var tmp = [];
	
	if (calData.length != 0) {
		for(var i=0; i<calData.length; i++) {
			if (calData[i].write_auth == 1) {
				selectCal.append("<option value='" + calData[i].calendar_id + "'>" + calData[i].title + "</option>");
			}
		}
	}
	
	// 할일 수정 시 기존 데이터 반영
	if (todoData != undefined) {
		var description = replaceNewlineTag(todoData[0].description);
		
		view.find("#isUpdate").val(true);
		view.find("#todo_id").val(todoData[0].todo_id);
		view.find("#recur_id").val(todoData[0].recurrence.recur_id);
		view.find("#title").val(todoData[0].title);
		
		if (description != "")
			view.find("#summary").val(description.split("<br>").join("\n"));
		
		view.find("#dtstart_date").val(todoData[0].start_dt);
		view.find("#dtend_date").val(todoData[0].end_dt);
		view.find("#calendar_id").val(todoData[0].calendar_id);
		view.find("#weight").val(todoData[0].weight);
		view.find("#status").val(todoData[0].todo_st);
		view.find("#progress").val(todoData[0].progress);
	}
}

function addSch(isUpdate, isEquip) {
	var view = $.mobile.activePage;
	var param = {};
	var title = view.find("#title");
	var summary = view.find("#summary");
	var calendar_id = view.find("#calendar_id");
	var equipments_id = "";
	var tempStartDate = view.find("#dtstart_date").val().split(".");
	var tempEndDate = view.find("#dtend_date").val().split(".");
	
	if (view.find("#progressAddsch").val() == "true") {
		return;
	}
	
	// 제목 입력 체크
	if (title.val().trim() == "") {
		alert(MGW_RES.get("gw_msg_common_inputsubject"));
		title.focus();
		return;
	}
	// 제목 길이 체크 (제한수 30글자)
	else if (title.val().trim().length > 30) {
		alert(MGW_RES.get("gw_msg_common_toolong_msg"));
		title.focus();
		return;
	}
	// 본문 길이 체크 (제한수 500글자)
	else if (summary.val().trim().length > 500) {
		alert(MGW_RES.get("gw_msg_common_toolong_summary"));
		summary.focus();
		return;
	}
	// 제목 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter(title.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		title.focus();
		return;
	}
	// 본문 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter(summary.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		summary.focus();
		return;
	}
	// 시작일이 종료일 보다 나중인지 체크
	else if (!checkDateTimePeriod(tempStartDate[0], tempStartDate[1], tempStartDate[2], view.find("#dtstart_hour").val(), view.find("#dtstart_time").val(),
			tempEndDate[0], tempEndDate[1], tempEndDate[2], view.find("#dtend_hour").val(), view.find("#dtend_time").val(), 'true')) {
		alert(MGW_RES.get("gw_msg_schedule_period_err"));
		return;
	}
	
	if (view.find("#rdoEquipY").is(':checked')) {
		$('input[id=equipment]:checked').each(function(){
			equipments_id += $(this).prop("value") + ";";
		});
	}
	
	var start_dt = view.find("#dtstart_date").val() + " " + view.find("#dtstart_hour").val() + ":" + view.find("#dtstart_time").val() + ":00";
	var end_dt = view.find("#dtend_date").val() + " " + view.find("#dtend_hour").val() + ":" + view.find("#dtend_time").val() + ":00";
	
	param["method"] = (isUpdate == "true") ? "update" : "add";
	param["TITLE"] = title.val().trim();
	param["EVENT_ID"] = (isUpdate == "true") ? view.find("#event_id").val() : "";
	param["START_DT"] =  start_dt;
	param["END_DT"] = end_dt;
	param["EQUIPMENT"] = equipments_id;
	param["CALENDAR_ID"] = calendar_id.val();
	param["CALENDAR_TP"] = "4";
	param["CALENDAR_DEPT"] = "";
	param["CALENDAR_OWNER"] = "";
	param["CALENDAR_GRANTED"] = "";
	param["CATEGORY_ID"] = "";
	param["ANNIVERSARY_FG"] = "0";
	param["TAG"] = "";
	param["SUMMARY"] = summary.val().trim();
	param["ATTENDEE_REQUIRE_RESPONSE"] = "";
	param["ATTENDEE_REQUIRE"] = "";
	param["ATTENDEE_VIEWER_RESPONSE"] = "";
	param["ATTENDEE_VIEWER"] = "";
	param["RECUR_ID"] = (isUpdate == "true") ? view.find("#recur_id").val() : "";;
	param["RECUR_TP"] = "0";
	param["RECUR_RULE"] = "";
	param["RECUR_CYCLE"] = "1";
	param["ALARM_TITLE"] = title.val().trim();
	param["ALARM_MSG"] = "";
	param["ALARM_SET"] = "0";
	param["ALARM_NOTI_BEFORE_VALUE"] = "";
	param["ALARM_NOTI_AFTER_VALUE"] = "";
	param["ALARM_MAIL_BEFORE_VALUE"] = "";
	param["ALARM_MAIL_AFTER_VALUE"] = "";
	param["REPLY_CNT"] = "0";
	param["CHECKLUNAR"] = false;
	param["FILENAME"] = null;
	param["FILEFOLDER"] = null;
	param["FILESIZE"] = null;
	
	view.find("#progressAddsch").val("true");
	
	GW_PROXY.invokeOpenAPI("schedule", "schadd", param, function(data) {
		if (data.length > 0) {
			alert(MGW_RES.get("gw_msg_common_success"));
			
			if (GWPlugin.usePlugin) {	
				if (isEquip) {
					GWPlugin.closePopupViewer("javascript:showSchList('" + apiCode_equipList + "', '" + view.find("#dtstart_date").val() + "');", true);
				}
				else {
					GWPlugin.closePopupViewer("javascript:showSchList('" + apiCode_schList + "', '" + view.find("#dtstart_date").val() + "');", true);
				}
			}
			else {
				if (isEquip) {
					showSchList(apiCode_equipList, view.find("#dtstart_date").val());
				}
				else {
					showSchList(apiCode_schList, view.find("#dtstart_date").val());
				}
			}
		} 
	});
}

function checkReserveEquip() {
	var view = $.mobile.activePage;
	var isUpdate = view.find("#isUpdate").val();
	var param = {};
	
	if (view.prop("id") != "sch_schadd")
		return;
	
	if (view.find("#rdoEquipN").is(':checked')) {
		addSch(isUpdate, false);
		return;
	}
	else {
		var equipments_id = "";
		
		if ($('input[id=equipment]:checked').length == 0) {
			addSch(isUpdate, false);
			return;
		}
		
		$('input[id=equipment]:checked').each(function(){
			equipments_id += $(this).prop("value") + ";";
		});
		
		var start_dt = view.find("#dtstart_date").val() + " " + view.find("#dtstart_hour").val() + ":" + view.find("#dtstart_time").val() + ":00";
		var end_dt = view.find("#dtend_date").val() + " " + view.find("#dtend_hour").val() + ":" + view.find("#dtend_time").val() + ":00";
		
		param["EQUIPMENT"] = equipments_id;
		param["TARGET_START_DATE"] = start_dt;
		param["TARGET_END_DATE"] = end_dt;
		param["RECUR_TP"] = "0";
		param["RECUR_RULE"] = "";
		param["RECUR_CYCLE"] = "1";
		param["EVENT_ID"] = (isUpdate == "true") ? view.find("#event_id").val() : "";
		
		GW_PROXY.invokeOpenAPI("schedule", "checkequip", param, function(data) {
			if (data == null) {
				addSch(isUpdate, true);
				return;
			} else {
				var equipName = "";
				var equipIdList = data.e_calendar_id;
				
				if (typeof equipIdList == "string") {
					equipName = "[" + getEquipName(GW_CONTROLLER_SCHEDULE.equipmentData, equipIdList) + "]";
				}
				else {
					if (equipIdList.length == undefined) {
						equipName = "[" + getEquipName(GW_CONTROLLER_SCHEDULE.equipmentData, equipIdList) + "]";
					}
					else {
						for(var i=0; i<equipIdList.length; i++) {
							equipName += "[" + getEquipName(GW_CONTROLLER_SCHEDULE.equipmentData, equipIdList[i]) + "]";
						}
					}
				}
				alert(equipName + " " + MGW_RES.get("gw_msg_schedule_check_reserve_equip"));
			}
		});
	}
}

function deleteSch() {
	var view = $.mobile.activePage;	
	var param = {};
	
	if (!(view.prop("id") == "sch_schdetail" || view.prop("id") == "sch_equipdetail"))
		return;
	
	var startDt = view.find("#start_dt").val();
	var startDtStr = getDateString(startDt);
	var eventId = view.find("#event_id").val();
	
	param["EVENT_ID"] = eventId;
	param["SELECT_DT"] = startDtStr;
	param["DEL_OPT"] = "1";
	
	if (confirm(MGW_RES.get("gw_msg_common_confirm_delete"))) {
		GW_PROXY.invokeOpenAPI("schedule", "schdelete", param, function(data) {
			if (data.status == "0" || data.status == "success") {
				alert(MGW_RES.get("gw_msg_mail_delete_success"));
				PAGE_CONTROLLER.goBack(true);
			}
		});
	}
}

function addTodo() {
	var view = $.mobile.activePage;
	
	if (view.prop("id") != "sch_todoadd")
		return;
	
	var param = {};
	var isUpdate = view.find("#isUpdate").val();
	var title = view.find("#title");
	var summary = view.find("#summary");
	var progress = view.find("#progress");
	var calendar_id = view.find("#calendar_id");
	
	var tempStartDate = view.find("#dtstart_date").val().split(".");
	var tempEndDate = view.find("#dtend_date").val().split(".");
	
	if (view.find("#progressAddtodo").val() == "true") {
		return;
	}
	
	// 제목 입력 체크
	if (title.val().trim() == "") {
		alert(MGW_RES.get("gw_msg_common_inputsubject"));
		title.focus();
		return;
	}
	// 제목 길이 체크 (제한수 30글자)
	else if (title.val().trim().length > 30) {
		alert(MGW_RES.get("gw_msg_common_toolong_msg"));
		title.focus();
		return;
	}
	// 본문 길이 체크 (제한수 500글자)
	else if (summary.val().trim().length > 500) {
		alert(MGW_RES.get("gw_msg_common_toolong_summary"));
		summary.focus();
		return;
	}
	// 제목 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter(title.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		title.focus();
		return;
	}
	// 본문 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter(summary.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		summary.focus();
		return;
	}
	// 상태(진행) 진행률 입력 체크
	else if (view.find("#status").val() == "1" && progress.val().trim() == "") {
		alert(MGW_RES.get("gw_msg_schedule_todo_empty_processrate"));
		progress.focus();
		return;
	}
	// 상태 진행률 유효한 숫자 체크 (숫자만 입력 가능)
	else if (!isValidNumber(progress.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_todo_progress_invalidvalue"));
		progress.focus();
		return;
	}
	// 상태 진행률 체크 (입력 범위 : 0~100)
	else if (parseInt(progress.val().trim()) < 0 || parseInt(progress.val().trim()) > 100) {
		alert(MGW_RES.get("gw_msg_schedule_todo_progress_limitvalue"));
		progress.focus();
		return;
	}
	// 시작일이 종료일 보다 나중인지 체크
	else if (!checkPeriodDate(tempStartDate[0], tempStartDate[1], tempStartDate[2],
			tempEndDate[0], tempEndDate[1], tempEndDate[2])) {
		alert(MGW_RES.get("gw_msg_schedule_period_err"));
		return;
	}
	
	var start_dt = view.find("#dtstart_date").val();
	var end_dt = view.find("#dtend_date").val();
	
	param["method"] = (isUpdate == "true") ? "update" : "add";
	param["TITLE"] = title.val().trim();
	param["TODO_ID"] = (isUpdate == "true") ? view.find("#todo_id").val() : "";
	param["START_DT"] = start_dt;
	param["END_DT"] = end_dt;
	param["CALENDAR_ID"] = calendar_id.val();
	param["WEIGHT"] = view.find("#weight").val();
	param["STATUS"] = view.find("#status").val();
	param["PROGRESS"] = progress.val().trim();
	param["DELAY_DT"] = "";
	param["DESCRIPTION"] = summary.val().trim();
	param["CALENDAR_TP"] = "4";
	param["CALENDAR_DEPT"] = "";
	param["CALENDAR_OWNER"] = "";
	param["CALENDAR_GRANTED"] = "";
	param["CATEGORY_ID"] = "";
	param["ANNIVERSARY_FG"] = "0";
	param["TAG"] = "";
	param["ATTENDEE_REQUIRE_RESPONSE"] = "";
	param["ATTENDEE_REQUIRE"] = "";
	param["ATTENDEE_VIEWER_RESPONSE"] = "";
	param["ATTENDEE_VIEWER"] = "";
	param["RECUR_ID"] = (isUpdate == "true") ? view.find("#recur_id").val() : "";;
	param["RECUR_TP"] = "0";
	param["RECUR_RULE"] = "";
	param["RECUR_CYCLE"] = "1";
	param["ALARM_TITLE"] = title.val().trim();
	param["ALARM_MSG"] = "";
	param["ALARM_HOUR"] = "0";
	param["ALARM_MINUTE"] = "0";
	param["ALARM_SET"] = "0";
	param["ALARM_NOTI_BEFORE_VALUE"] = "";
	param["ALARM_NOTI_AFTER_VALUE"] = "";
	param["ALARM_MAIL_BEFORE_VALUE"] = "";
	param["ALARM_MAIL_AFTER_VALUE"] = "";
	param["FILENAME"] = null;
	param["FILEFOLDER"] = null;
	param["FILESIZE"] = null;
	param["REPLY_CNT"] = "0";
	
	view.find("#progressAddtodo").val("true");
	
	GW_PROXY.invokeOpenAPI("schedule", "todoadd", param, function(data) {
		if (data.length > 0) {
			alert(MGW_RES.get("gw_msg_common_success"));

			if (GWPlugin.usePlugin) {
					GWPlugin.closePopupViewer("javascript:showSchList('" + apiCode_todoList + "', '" + view.find("#dtstart_date").val() + "');", true);
				}
				else {
					showSchList(apiCode_todoList, view.find("#dtstart_date").val());
				}
			}

	});
}

function deleteTodo() {
	var view = $.mobile.activePage;	
	var param = {};
	
	if (view.prop("id") != "sch_tododetail")
		return;
	
	var startDt = view.find("#start_dt").val();
	var todoId = view.find("#todo_id").val();
	
	param["TODO_ID"] = todoId;
	param["SELECT_DT"] = startDt;
	param["DEL_OPT"] = "1";
	
	if (confirm(MGW_RES.get("gw_msg_common_confirm_delete"))) {
		GW_PROXY.invokeOpenAPI("schedule", "tododelete", param, function(data) {
			if (data.status == "0" || data.status == "success") {
				alert(MGW_RES.get("gw_msg_mail_delete_success"));
				PAGE_CONTROLLER.goBack(true);
			}
		});
	}
}

function toggleEquip(flag) {
	var view = $.mobile.activePage;	
	if (flag) {
		view.find("#equipmentlist").removeAttr("style");
	}
	else {
		view.find("#equipmentlist").attr("style", "display:none;");
	}
}

function cancel_addSch(type) {
	var cancelMsg; 
	if (type=="todo")
		cancelMsg = MGW_RES.get("gw_msg_schedule_cancel_add_todo");
	else 
		cancelMsg = MGW_RES.get("gw_msg_schedule_cancel_add_schedule");
	
	if (confirm(cancelMsg)) {
		if (GWPlugin.usePlugin) { // APP, popup인 경우
			GWPlugin.closePopupViewer("", false);
		}
		else {
			PAGE_CONTROLLER.goBack();
		}
	}
}

function isValidLetter(src) {
	if ( src.indexOf("'") >= 0 ){
		return false;
	}
	else if ( src.indexOf('"') >= 0 ){
		return false;
	}
	else if ( src.indexOf('\\') >= 0 ){
		return false;
	}
	
	return true;
}
/*******************************************************************************
 * Schedule / Todo Add&Edit&Delete END *
 ******************************************************************************/



/*******************************************************************************
 * Schedule / Todo Search Function Start
 ******************************************************************************/
function getSchSearchTab() {
	var param = {};
	
	if ($.mobile.activePage.prop("id") != "sch_schsearch") {
		return;
	}
	
	getCalendarList(function() {
		getEquipmentList(function() {
			renderSchSearch(GW_CONTROLLER_SCHEDULE.calendarData, GW_CONTROLLER_SCHEDULE.equipmentData);
		});
	});
}

function renderSchSearch(calData, equipData) {
	var view = $.mobile.activePage;
	if (view.prop("id") != "sch_schsearch") {
		return;
	}
	
	var list = view.find("#list");
	var calOption = view.find("#calendar_id");
	var equipOption = view.find("#equipment_id");
	
	var equipmentIds = "";
	var companyCalendarIds = "";
	var teamCalendarIds = "";
	var privateCalendarIds = "";
	
	view.find("#dtstart_date").val(getToday());
	view.find("#dtend_date").val(getToday());
	view.find("#txt_dtstart_date").val(getToday());
	view.find("#txt_dtend_date").val(getToday());
	
	// 달력
	if (calData.length != 0) {
		for(var i=0; i<calData.length; i++) {
			calOption.append("<option value='" + calData[i].owner_tp + "'>" + calData[i].title + "</option>");
			
			switch(calData[i].owner_tp) {
			case '1':
				companyCalendarIds += calData[i].calendar_id + ";";
				break;
			case '2':
				teamCalendarIds += calData[i].calendar_id + ";";
				break;
			case '4':
				privateCalendarIds += calData[i].calendar_id + ";";
				break;
			}
		}
	}
	view.find("#companyCalendarIds").val(companyCalendarIds);
	view.find("#teamCalendarIds").val(teamCalendarIds);
	view.find("#privateCalendarIds").val(privateCalendarIds);
	
	// 설비
	for(var i=0; i<equipData.length; i++) {
		if (equipData[i].calendar_st == "1" && equipData[i].equipment_sort_fg == "0") {
			equipOption.append("<option value='" + equipData[i].calendar_id + "'>" + equipData[i].title + "</option>");
			equipmentIds += equipData[i].calendar_id + ";";
		}
	}
	
	view.find("#equipmentIds").val(equipmentIds);
	selectSearchTab('sch');
}

function selectSearchTab(tab) {
	var view = $.mobile.activePage;
	var currentTab = view.find("#currentTab").val();
	
	var searchOption = view.find("#list").find("[name=searchOption]");
	var equipOption = view.find("#list").find("[name=equipOption]");
	var calOption = view.find("#list").find("[name=calOption]");
	var statusOption = view.find("#list").find("[name=statusOption]");
	var weightOption = view.find("#list").find("[name=weightOption]");
	
	if (currentTab == tab)
		return;
	
	if (tab == "todo") {
		view.find("#currentTab").val("todo");
		view.find("#schTab").removeClass("ui-btn-active");
		view.find("#todoTab").addClass("ui-btn-active");
		
		view.find("#calTitle").text(MGW_RES.get("gw_schedule_all_calendar_label"));
		searchOption.hide();
		calOption.show();
		equipOption.hide();
		
		statusOption.show();
		weightOption.show();
	}
	else {
		view.find("#currentTab").val("sch");
		view.find("#todoTab").removeClass("ui-btn-active");
		view.find("#schTab").addClass("ui-btn-active");
		
		view.find("#calTitle").text("");
		searchOption.show();
		equipOption.hide();
		calOption.hide();
		
		statusOption.hide();
		weightOption.hide();
	}
	
	view.find("#select_option").val('3');
	view.find("#calendar_id").val('0');
	view.find("#equipment_id").val('allequip');
	view.find("#status").val('0');
	view.find("#weight").val('0');
}


function searchSch() {
	var view = $.mobile.activePage;
	var param = {};
	var noCheckValue = "00000000000000000000";
	var calendarTitleList = new Array();
	var currentTab = view.find("#currentTab").val();
	var title = view.find("#title");
	// 사용자가 선택한 옵션, 설비, 달력 Value
	var selectOption = view.find("#select_option").val();
	var calendar_id = view.find("#calendar_id").val();
	var equipment_id = view.find("#equipment_id").val();
	// 전체 설비, 전체 달력에 해당하는 Value
	var equipmentIds = view.find("#equipmentIds").val();
	var companyCalendarIds = view.find("#companyCalendarIds").val();
	var teamCalendarIds = view.find("#teamCalendarIds").val();
	var privateCalendarIds = view.find("#privateCalendarIds").val();
	var method = "";
	var applCode = "";
	
	// 제목 입력 체크
	if (title.val().trim() == "" && !view.find("#notitle").is(':checked')) {
		alert(MGW_RES.get("gw_msg_common_inputsubject"));
		title.focus();
		return;
	}
	// 제목 길이 체크 (제한수 30글자)
	else if (title.val().trim().length > 30) {
		alert(MGW_RES.get("gw_msg_common_toolong_msg"));
		title.focus();
		return;
	}
	// 제목 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter(title.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		title.focus();
		return;
	}
	
	// 제목
	if (view.find("#notitle").is(':checked')) {
		param["TITLE"] = "";
	} 
	else {
		param["TITLE"] = title.val().trim();
	}
	
	// 시작일, 종료일
	var start_dt = view.find("#dtstart_date").val();
	var end_dt = view.find("#dtend_date").val();
	
	// 일정, 할일 탭에 따른 기본 데이터 설정
	if (currentTab == "todo") {
		method = "getSearchList";
		applCode = "000200";
		selectOption = "2";
	} 
	else {
		method = "getSearchList";
		applCode = "000300";
	}
	
	/*
	 * 사용자 선택 옵션에 따른 데이터 설정 selectOption value 1:설비, 2:달력, 3:전부
	 */
	if (selectOption == "1") {
		companyCalendarIds = noCheckValue;
		teamCalendarIds = noCheckValue;
		privateCalendarIds = noCheckValue;
		
		// 전체 설비가 아닌 경우
		if (equipment_id != "allequip") {
			equipmentIds = equipment_id;
		}
	}
	else if (selectOption == "2") {
		equipmentIds = noCheckValue;
		
		// calendar_id value 0:전체,1:전사,2:부서,4:개인
		if (calendar_id == "1") {
			teamCalendarIds = noCheckValue;
			privateCalendarIds = noCheckValue;
		} 
		else if (calendar_id == "2") {
			companyCalendarIds = noCheckValue;
			privateCalendarIds = noCheckValue;
		} 
		else if (calendar_id == "4") {
			companyCalendarIds = noCheckValue;
			teamCalendarIds = noCheckValue;
		}
	}
	
	param["method"] = method;
	param["START_DT"] = start_dt;
	param["END_DT"] = end_dt;
	param["CATEGORY_IDS"] = "";
	param["APPL_CD"] = applCode;
	param["SELECT_OPTION"] = selectOption;
	
	param["EQUIPMENT_IDS"] = (currentTab=="sch")?equipmentIds : "0";
	param["COMPANY_CALENDAR_IDS"] = companyCalendarIds;
	param["TEAM_CALENDAR_IDS"] = teamCalendarIds;
	param["PRIVATE_CALENDAR_IDS"] = privateCalendarIds;
	
	param["TODO_STATUS"] = (currentTab=="todo")?view.find("#status").val() : "0";
	param["TODO_WEIGHT"] = (currentTab=="todo")?view.find("#weight").val() : "0";
	
	GW_PROXY.invokeOpenAPI("schedule", "schsearch", param, function(schData) {
		showDetailList(currentTab, selectOption, schData);
	});
}

function renderDetailList(currentTab, schData, calData, equipData) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");

	view.find("#divList").attr("class","divListNormal");
	view.find("#divTitle").attr("class","divListNormal");
	
	if (schData.length != 0) {
		for(var i = 0; i< schData.length;i++){		
			var item = schData[i];
			var tmp = [];
			
			tmp.push("<li>");
			
			if (calData != undefined) {
				for(var j=0; j<calData.length; j++) {
					if (item.calendar_id == calData[j].calendar_id) {
						if (currentTab == "sch") {
							if (item.parent_event_id == "00000000000000000000") {
								tmp.push("<a href=\"javascript:schDetail('" + item.searchId + "', 'sch_detaillist_sch', '" + item.owner_id + "');\">");
							}
							else {
								tmp.push("<a href=\"javascript:schDetail('" + item.parent_event_id + "', 'sch_detaillist_sch', '" + item.owner_id + "');\">");
							}
							tmp.push("<div class='grouping_middle'>");
							tmp.push("<div class='calendarName'>");
						}
						else if (currentTab == "todo") {
							var calColor = calData[j].ui_attr.replace("#", "");
							
							if (item.parent_event_id == "00000000000000000000") {
								tmp.push("<a href=\"javascript:todoDetail('" + item.searchId + "', '" + item.owner_id + "', '" + calData[j].title + "', '" + calColor + "', 'sch_detaillist');\">");
							}
							else {
								tmp.push("<a href=\"javascript:todoDetail('" + item.parent_event_id + "', '" + item.owner_id + "', '" + calData[j].title + "', '" + calColor + "', 'sch_detaillist');\">");
							}
							tmp.push("<div class='grouping_middle'>");
							tmp.push("<div class='calendarName'>");
						}
							
						tmp.push("<font color='" + calData[j].ui_attr + "'>[" + MGW_RES.get("gw_schedule_calendar_label") + "] " + calData[j].title +"</font></div>");
					}
				}
			}
			if (equipData != undefined) {
				for(var j=0; j<equipData.length; j++) {
					if (item.calendar_id == equipData[j].calendar_id) {
						if (currentTab == "sch") {
							if (item.parent_event_id == "00000000000000000000") {
								tmp.push("<a href=\"javascript:schDetail('" + item.searchId + "', 'sch_detaillist_equip', '" + item.owner_id + "');\">");
							}
							else {
								tmp.push("<a href=\"javascript:schDetail('" + item.parent_event_id + "', 'sch_detaillist_equip', '" + item.owner_id + "');\">");
							}
							tmp.push("<div class='grouping_middle'>");
							tmp.push("<div class='calendarName'>");
						}
						
						tmp.push("<font color='" + equipData[j].ui_attr + "'>[" + MGW_RES.get("gw_schedule_equipment_label") + "] " + equipData[j].title +"</font></div>");
					}
				}
			}
			
			tmp.push("<h3 class='title'>"+item.title+"</h3>");
			tmp.push("<div class='dateInfo'>" + item.start_dt + " ~ " + item.end_dt + "</div>");
			tmp.push("</div></a></li>");
			list.append(tmp.join(""));
		}
	}
	else {
		var tmp = [];
		
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
		list.html(tmp.join(""));
	}
	
	list.listview("refresh");
}


// 검색 - 기간 설정
function setPeriod(range) {
	var view = $.mobile.activePage;
	var startDate = view.find("#dtstart_date");
	var endDate = view.find("#dtend_date");
	var today = getToday();
	var newDate;
	
	switch(range) {
		case '1':	// 당일
			newDate = today;
			break;
		case '2':	// 7일
			newDate = getPeriod(0, 0, 7);
			break;
		case '3':	// 1달
			newDate = getPeriod(0, 1, 0);
			break;
		case '4':	// 3달
			newDate = getPeriod(0, 3, 0);
			break;
	}
	
	startDate.val(newDate);
	endDate.val(today);
}

function toggleOption(option) {
	var view = $.mobile.activePage;
	var equipOption = view.find("#list").find("#equipment_id");
	var calOption = view.find("#list").find("#calendar_id");
	
	switch (option) {
	case '1': // 설비
		equipOption.show();
		calOption.hide();
		break;
	case '2': // 달력
		equipOption.hide();
		calOption.show();
		break;
	case '3': // 전체
		equipOption.hide();
		calOption.hide();
		break;
	}
}

// 검색 - 제목없음 토글
function toggleNoTitle() {
	var view = $.mobile.activePage;
	var notitle = view.find("#notitle");
	var title = view.find("#title");
	
	if (notitle.is(':checked')) {
		title.attr("disabled", true);
	}
	else {
	    title.attr("disabled", false);
	}
}
/*******************************************************************************
 * Schedule / Todo Search Function End
 ******************************************************************************/



/*******************************************************************************
 * Equipment / Calendar Function Start
 ******************************************************************************/
// 설비 목록에서 설비 분류 제거 후 설비 str, id, color Array 반환
function getAllEquipList(data) {
	var equipTitleList = new Array();
	var parCalendarIdList = new Array();
	
	// 상위 트리인지 분석
	var isParEquip = true;
	var isEquip = false;
	var index = 0;
		
	for(var i=0; i<data.length; i++) {
		if (data[i].equipment_sort_fg == 1) {
			parCalendarIdList[index++] = data[i].calendar_id;
		}
	}
			
	// 각 설비의 타이틀명들을 equipTitleList에 저장
	index = 0;
			
	for(var i=0; i<data.length; i++) {
		isParEquip = true;
		isEquip = false;
		
		var item = data[i];
			
		for(j=0; j<parCalendarIdList.length; j++) {
			if (parCalendarIdList[j] == data[i].calendar_id) {
				isParEquip = false;
			}
			if (parCalendarIdList[j] == data[i].par_calendar_id) {
				isEquip = true;
			}
		}
				
		if (isParEquip && isEquip) {
			equipTitleList[index] = new Array();
			equipTitleList[index][0] = data[i].calendar_id;
			equipTitleList[index][1] = data[i].title;
			equipTitleList[index][2] = data[i].ui_attr;
			equipTitleList[index++][3] = 0;
		}
	}
	return equipTitleList;
}

function getEquipName(equipData, calendar_id) {
	for(var i=0; i<equipData.length; i++) {
		if (equipData[i].calendar_id == calendar_id){
			return equipData[i].title;
		}
	}
}
/*******************************************************************************
 * Equipment / Calendar Function End
 ******************************************************************************/
// 일정추가/설비예약
$("#sch_schadd #set_dtstart_date").live("click", function() {
	$("#sch_schadd #dtstart_date").datebox("open");
});
$("#sch_schadd #set_dtend_date").live("click", function() {
	$("#sch_schadd #dtend_date").datebox("open");
});

// 할일추가
$("#sch_todoadd #set_dtstart_date").live("click", function() {
	$("#sch_todoadd #dtstart_date").datebox("open");
});
$("#sch_todoadd #set_dtend_date").live("click", function() {
	$("#sch_todoadd #dtend_date").datebox("open");
});

// 일정검색
$("#sch_schsearch #set_dtstart_date").live("click", function() {
	$("#sch_schsearch #dtstart_date").datebox("open");
});
$("#sch_schsearch #set_dtend_date").live("click", function() {
	$("#sch_schsearch #dtend_date").datebox("open");
});

function changeEndDate() {
	var txt_dtend_date = $.mobile.activePage.find("#txt_dtend_date");
	var dtend_date =  $.mobile.activePage.find("#dtend_date");
	
	if (txt_dtend_date != undefined && dtend_date != undefined) {
		txt_dtend_date.val(dtend_date.val());
	}
}

function changeStartDate() {
	var txt_dtstart_date = $.mobile.activePage.find("#txt_dtstart_date");
	var dtstart_date =  $.mobile.activePage.find("#dtstart_date");
	
	if (txt_dtstart_date != undefined && dtstart_date != undefined) {
		txt_dtstart_date.val(dtstart_date.val());
	}
}

function showSelectEquipment() {
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();	
	var baseDate = view.find("#baseDate").text();
	
	PAGE_CONTROLLER.showPage("sch_selectequipment", function() {
		var view = $.mobile.activePage;
		var list = view.find("#list");
		var tmp = [];

		view.find("#dateMode").val(dateMode);
		view.find("#baseDate").val(baseDate);
		
		tmp.push("<li onclick=\"javascript:selectAllEquipment();\"><div class='grouping_left'>");
		tmp.push("<input type='checkbox' data-role='none' id='checkAll' onclick='javascript:selectAllEquipment();'");
		
		if (GW_CONTROLLER_SCHEDULE.selectAllEquipment)
			tmp.push(" checked='checked'");
		
		tmp.push("></div><div class='grouping_middle'><h3>");
		tmp.push(MGW_RES.get("gw_schedule_select_allequiplist"));
		tmp.push("</h3></div></li>");
		
		var equipData = GW_CONTROLLER_SCHEDULE.equipmentData;
		for(var i=0; i<equipData.length; i++) {			
			tmp.push("<li onclick=\"javascript:selectEquipment('" + equipData[i].calendar_id + "');\"><div class='grouping_left'>");
			tmp.push("<input type='checkbox' data-role='none' name='chkEquipment' id='" + equipData[i].calendar_id + "'");
			tmp.push(" onclick=\"javascript:selectEquipment('" + equipData[i].calendar_id + "');\"");
			
			if (GW_CONTROLLER_SCHEDULE.selectAllEquipment) {
				tmp.push(" checked='checked'");
			} else {
				var idArr =  GW_CONTROLLER_SCHEDULE.selectedEquipmentIds.split(";");
				for (var j=0; j<idArr.length; j++) {
					if (idArr[j] != "" && idArr[j] == equipData[i].calendar_id) {
						tmp.push(" checked='checked'");
					}	
				}
			}
			
			tmp.push(">");
					
			if (equipData[i].ui_attr != undefined)
				tmp.push("<div class='equip_color' style='background:" + equipData[i].ui_attr + "'></div>");
			
			tmp.push("</div><div class='grouping_middle'><h3>");
			tmp.push(equipData[i].title);
			tmp.push("</h3></div></li>");
			
		}
		
		list.html(tmp.join(""));
		list.listview("refresh");
	});	
}

function selectAllEquipment() {
	var view = $.mobile.activePage;
	var c = view.find('#checkAll').attr('checked');

	if (c != 'checked'){
		view.find('#checkAll').attr('checked', 'checked');
		view.find('input[name=chkEquipment]').attr('checked', 'checked');
	}
	else {	
		view.find('#checkAll').removeAttr('checked');
		view.find('input[name=chkEquipment]').removeAttr('checked');
	}
}

function selectEquipment(id) {
	var view = $.mobile.activePage;
	var chk = view.find("#list li").find("[name=chkEquipment]").filter("#" +id);
	
	if (chk.attr("checked") != "checked") {
		chk.attr("checked", "checked");
	}
	else {
		chk.removeAttr("checked");
	}
	
	view.find('#checkAll').removeAttr('checked');
}

function completeSelectEquipment() {
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();
	var baseDate = view.find("#baseDate").val();

	if (view.find('#checkAll').attr('checked') == "checked") {
		GW_CONTROLLER_SCHEDULE.selectAllEquipment = true;
	}
	else {
		GW_CONTROLLER_SCHEDULE.selectAllEquipment = false;
		GW_CONTROLLER_SCHEDULE.selectedEquipmentIds = "";
		
		view.find("#list li").find("[name=chkEquipment]:checked").each(function(){
			GW_CONTROLLER_SCHEDULE.selectedEquipmentIds += $(this).prop("id") + ";";
		});
	}
	
	PAGE_CONTROLLER.showPage("sch_equiplist", function() {getSchEquipList("equiplist", 0, dateMode, baseDate);});
	
}

function cancelSelectEquipment() {
	PAGE_CONTROLLER.goBack();
}/***************************************************************************
	 * UI Script START for DefaultValue(기본변수 설정) *
	 **************************************************************************/
var apiCode_schEquipGroupList = "schequipgrouplist";	// 설비그룹 목록
var apiCode_schConfig = "schconfig";					// 일정 config
var apiCode_schSelectedList = "schselectedlist";		// 특정달력 일정 목록
var apiCode_schSelectedCount = "schselectedcount";		// 특정달력 일정 카운트
var apiCode_schCategoryList = "schcategorylist";		// 일정 카테고리 목록

var SCHCONFIG_ALARM_PERIOD = "alarm_period";				// config - 알람rule
var SHARED_CALENDAR_ID = "99999999999999999999";			// 공유일정 calendar id
var MY_SHARED_CALENDAR_ID = "MY_SHARED";					// 나의달력 + 공유일정

var GW2_CONTROLLER_SCHEDULE = {
	calendarLoaded: false,
	equipmentLoaded: false,
	equipmentGroupLoaded: false,
	calendarData: undefined,
	equipmentData: undefined,
	equipmentGroupData: undefined,
	calendarIds: "",
	deptCalendarIds: "",
	myCalendarId: "",
	myCalendarTitle: "",
	myCalendarColor: "",
	companyCalendarIds: "",
	selectAllEquipment: true,
	selectedEquipmentIds: "",
	selectAllCalendar: true,
	selectedCalendarIds: "",
	selectedCalendarIdOfList: "",
	schConfigLoaded: false,
	schConfigData: undefined,
	schCategoryLoaded: false,
	schCategoryData: undefined,	
	
	initCalendar: function(calendarData) {
		GW2_CONTROLLER_SCHEDULE.calendarLoaded = true;
		GW2_CONTROLLER_SCHEDULE.calendarData = calendarData;
		
		if (calendarData.length != 0) {
			for(var i=0; i<calendarData.length; i++) {
				GW2_CONTROLLER_SCHEDULE.calendarIds += calendarData[i].calendar_id + ";";
				
				// 나의달력 체크
				if(calendarData[i].default_calendar_fg == "1" && calendarData[i].mngr_id == sessionStorage["id"]) {
					GW2_CONTROLLER_SCHEDULE.myCalendarId = calendarData[i].calendar_id;
					GW2_CONTROLLER_SCHEDULE.myCalendarTitle = calendarData[i].title;
					GW2_CONTROLLER_SCHEDULE.myCalendarColor = calendarData[i].ui_attr;
				}
			}
		}
		GW2_CONTROLLER_SCHEDULE.selectAllCalendar = false;
		GW2_CONTROLLER_SCHEDULE.selectedCalendarIds = "";
		GW2_CONTROLLER_SCHEDULE.selectedCalendarIdOfList = "ALL";
	}, 
	initEquipment: function(equipmentData) {
		GW2_CONTROLLER_SCHEDULE.equipmentLoaded = true;
		GW2_CONTROLLER_SCHEDULE.equipmentData = equipmentData;
		// LOG(equipmentData);
		GW2_CONTROLLER_SCHEDULE.selectAllEquipment = true;
		GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds = "";
	},
	initEquipmentGroup: function(equipmentGroupData) {
		GW2_CONTROLLER_SCHEDULE.equipmentGroupLoaded = true;
		GW2_CONTROLLER_SCHEDULE.equipmentGroupData = equipmentGroupData;
		// LOG(equipmentGroupData);
	},
	initSchConfig: function(schConfigData) {
		GW2_CONTROLLER_SCHEDULE.schConfigLoaded = true;
		GW2_CONTROLLER_SCHEDULE.schConfigData = schConfigData;
	},
	clearSchData: function() {
		GW2_CONTROLLER_SCHEDULE.calendarLoaded = false;
		GW2_CONTROLLER_SCHEDULE.calendarData = undefined;
		GW2_CONTROLLER_SCHEDULE.calendarIds = "";
		GW2_CONTROLLER_SCHEDULE.equipmentLoaded = false;
		GW2_CONTROLLER_SCHEDULE.equipmentData = undefined;	
		GW2_CONTROLLER_SCHEDULE.equipmentGroupLoaded = false;
		GW2_CONTROLLER_SCHEDULE.equipmentGroupData = undefined;	
		GW2_CONTROLLER_SCHEDULE.schConfigLoaded = false;
		GW2_CONTROLLER_SCHEDULE.schConfigData = undefined;
		GW2_CONTROLLER_SCHEDULE.schCategoryLoaded = false;
		GW2_CONTROLLER_SCHEDULE.schCategoryData = undefined;		
	},
	initSchCategory: function(schCategoryData) {
		GW2_CONTROLLER_SCHEDULE.schCategoryLoaded = true;
		GW2_CONTROLLER_SCHEDULE.schCategoryData = schCategoryData;
	}	
};
/*******************************************************************************
 * UI Script END for DefaultValue *
 ******************************************************************************/



/*******************************************************************************
 * UI Script START for COMMON(공통 사용 스크립트) *
 ******************************************************************************/
function showSch2List(apiCode, baseDate) {
	if (baseDate == undefined || baseDate == "") {
		baseDate = getToday();
	}
	
	if (GWPlugin.usePlugin) {
		GWPlugin.setSelectedTabBarItem(0, function(){}, function(){});
	}
	
	if (apiCode == "schlist") {
		PAGE_CONTROLLER.showPage("sch2_" + apiCode, function() {getSch2List(apiCode, 0, "DAILY", GW2_CONTROLLER_SCHEDULE.selectedCalendarIdOfList, baseDate);});
	} 
	else if (apiCode == "equiplist") {
		PAGE_CONTROLLER.showPage("sch2_" + apiCode, function() {getSchEquip2List(apiCode, 0, "DAILY", baseDate);});
	} 
}

function showTodo2List(apiCode){
	PAGE_CONTROLLER.showPage("sch2_" + apiCode, function() {getSchTodo2List(apiCode);});
}

function showSch2Add(isModify) {
	var view = $.mobile.activePage;
	var baseDate = view.find("#baseDate").text();
	
	if (isModify == undefined || isModify == false) {
		popupSch2AddView(undefined, baseDate);
	} else {
		var eventId = view.find("#event_id").val();
		if (GWPlugin.usePlugin) {
			GWPlugin.showPopupViewer(["javascript:popupSch2AddView('" + eventId + "','"+baseDate+"');"], function(){}, function(){});
		}
		else {
			popupSch2AddView(eventId, baseDate);
		}
	}
}


function showSch2AddNaviBar() {
	var view = $.mobile.activePage;
	var baseDate = view.find("#baseDate").text();
	
	NAVIBAR_DEF.phone.sch_schadd.title = MGW_RES.get("gw_schedule_schadd_label");
	NAVIBAR_DEF.pad.sch_schadd.title = MGW_RES.get("gw_schedule_schadd_label");
	
	GWPlugin.showPopupViewer(["javascript:popupSch2AddView( undefined,'"+baseDate+"');"], function(){}, function(){});
}

function popupSch2AddView(eventId, baseDate) {
	// 일정 추가
	if (eventId == undefined) {
		NAVIBAR_DEF.phone.sch2_schadd.title = MGW_RES.get("gw_schedule_schadd_label");
		NAVIBAR_DEF.pad.sch2_schadd.title = MGW_RES.get("gw_schedule_schadd_label");
		
		PAGE_CONTROLLER.showPage("sch2_schadd", function() {getSch2Add(eventId, baseDate);});
	}
	// 일정 수정
	else {
		NAVIBAR_DEF.phone.sch2_schadd.title = MGW_RES.get("gw_schedule_schmodify_label");
		NAVIBAR_DEF.pad.sch2_schadd.title = MGW_RES.get("gw_schedule_schmodify_label");
		
		if (eventId != undefined && eventId != "") {
			PAGE_CONTROLLER.showPage("sch2_schadd", function() {getSch2Add(eventId, baseDate);});
		}
		else {
			alert(MGW_RES.get("gw_msg_common_err"));
		}
	}
}

function showTodo2Add(isModify) {
	if (isModify == undefined || isModify == false) {
		popupTodo2AddView();
	}
	else {
		var view = $.mobile.activePage;
		var todoId = view.find("#todo_id").val();
		
		if (GWPlugin.usePlugin) {
			GWPlugin.showPopupViewer(["javascript:popupTodo2AddView('" + todoId + "');"], function(){}, function(){});
		}
		else {
			popupTodo2AddView(todoId);
		}
	}
}

function showTodo2AddNaviBar() {
	NAVIBAR_DEF.phone.sch_schadd.title = MGW_RES.get("gw_schedule_todo_add_label");
	NAVIBAR_DEF.pad.sch_schadd.title = MGW_RES.get("gw_schedule_todo_add_label");
	
	GWPlugin.showPopupViewer(["javascript:popupTodo2AddView();"], function(){}, function(){});
}

function popupTodo2AddView(todoId) {
	// 할일 추가
	if (todoId == undefined) {
		NAVIBAR_DEF.phone.sch2_todoadd.title = MGW_RES.get("gw_schedule_todo_add_label");
		NAVIBAR_DEF.pad.sch2_todoadd.title = MGW_RES.get("gw_schedule_todo_add_label");
		
		PAGE_CONTROLLER.showPage("sch2_todoadd", function() {getTodo2Add();});
	} 
	else {
		NAVIBAR_DEF.phone.sch2_todoadd.title = MGW_RES.get("gw_schedule_todo_modify_label");
		NAVIBAR_DEF.pad.sch2_todoadd.title = MGW_RES.get("gw_schedule_todo_modify_label");
		
		PAGE_CONTROLLER.showPage("sch2_todoadd", function() {getTodo2Add(todoId);});
	}
}

function showSch2SearchTab() {
	if (GWPlugin.usePlugin) {
		GWPlugin.setSelectedTabBarItem(0, function(){}, function(){});
	}
	
	PAGE_CONTROLLER.showPage("sch2_schsearch", function() {getSch2SearchTab();});
}

/*
 * 월간 달력에서 건수 검색 시 일간으로 이동 callDate : 검색하고자 하는 달 day : 검색하고자 하는 일
 */
function linkMonthToDay2(callDate, day){
	var view = $.mobile.activePage;
	view.find("#DAILY").attr("class", "ui-btn-active");
	var dateMode = view.find("#dateMode").val();;
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = getYear(callDate) + "." + getMonth(callDate) + "." + day;
	var apiCode = view.find("#apiCode").val();
	
	getSch2List(apiCode, 0, dateMode, calMode, baseDate);	
}

/*
 * 월간 달력에서 일간 별로 조회 callDate : 검색하고자 하는 달 day : 검색하고자 하는 일 beforeSelectDay : 바로
 * 이전에 검색한 일
 */
function monthToDayList2(callDate, day){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();;
	var calMode = view.find("#calMode option:selected").val();
	var newDate = new Date(getYear(callDate), getMonth(callDate)-1, day);
	var baseDate = dateToString(newDate);
	var apiCode = view.find("#apiCode").val();
	var id = view.find("#equipmentIDS").val();
	var beforeSelectDay = view.find("#beforeSelectDay").val();
	var today = getDate(dateToString(new Date()));
	var month = getMonth(dateToString(new Date()));
	
	// 오늘 : today(노란색)
	// 선택한 날 : selectDay(파란색)
	// 오늘날짜 선택하면 selectDay, 다시 다른 날짜 선택하면 오늘날짜는 today로 변경

	if( beforeSelectDay == "0" ){
		view.find("#"+day).attr("class","selectDay");
		view.find("#beforeSelectDay").val(day);
		beforeSelectDay = day;
	}
	else{
		if( beforeSelectDay == today && baseDate.substring(5,7) == month )
			view.find("#"+beforeSelectDay).attr("class","today");
		else
			view.find("#"+beforeSelectDay).removeAttr('class');

		view.find("#"+day).attr("class","selectDay");
		view.find("#beforeSelectDay").val(day);
		beforeSelectDay = day;
	}
	
	if (apiCode == "schlist")
		getSch2List(apiCode, 0, dateMode, calMode, baseDate, true);
	else if (apiCode == "equiplist")
		getSchEquip2List(apiCode, 0, dateMode, baseDate, "DETAIL", id, true);

	
// if (apiCode == "schlist") {
// NAVIBAR_DEF.phone.sch_detaillist.title = "[" + getMonthDay(newDate) + "] " +
// MGW_RES.get("gw_schedule_list_label");
// NAVIBAR_DEF.pad.sch_detaillist.title = "[" + getMonthDay(newDate) + "] " +
// MGW_RES.get("gw_schedule_list_label");
//		
// PAGE_CONTROLLER.showPage("sch_detaillist", function() {
// changeTitle("[" + getMonthDay(newDate) + "] " +
// MGW_RES.get("gw_schedule_list_label"));
// getSch2List(apiCode, 0, dateMode, calMode, baseDate, true);});
// }
// else if (apiCode == "equiplist") {
// NAVIBAR_DEF.phone.sch_detaillist.title = "[" + getMonthDay(newDate) + "] " +
// MGW_RES.get("gw_schedule_equip_list_label");
// NAVIBAR_DEF.pad.sch_detaillist.title = "[" + getMonthDay(newDate) + "] " +
// MGW_RES.get("gw_schedule_equip_list_label");
//		
// PAGE_CONTROLLER.showPage("sch_detaillist", function() {
// changeTitle("[" + getMonthDay(newDate) + "] " +
// MGW_RES.get("gw_schedule_equip_list_label"));
// getSchEquip2List(apiCode, 0, dateMode, baseDate, "DETAIL", id, true);});
// }
}

/*
 * 상세보기 할때 사용되는 스크립트 일정과 설비는 scheduleDetail에서 분기된다. eventId : 해당 일정(설비)의 event
 * ID값 monthMode : 월간에서 일정이나 설비의 상세보기 할 경우 사용된다.
 */
function sch2Detail(eventId, monthMode, ownerId, selectDate){
	var view = $.mobile.activePage;
	var apiCode = view.find("#apiCode").val();
	view.find("#baseDate").val(selectDate);
	
	if (monthMode == "schedule" || monthMode == "sch_detaillist_sch" || apiCode == "schlist") {
		getSch2Detail(apiCode, eventId, ownerId);
	}
	else if (monthMode == "equipment" || monthMode == "sch_detaillist_equip" || apiCode == "equiplistdetail") {
		getSchEquip2Detail(apiCode, eventId, ownerId);
	}
}
function showDetailList2(currentTab, selectOption, param) {
	PAGE_CONTROLLER.showPage("sch_detaillist", function(){
		GW_PROXY.invokeOpenAPI("schedule", "schsearch", param, function(schData) {
			var title = MGW_RES.get("gw_schedule_sch_search_label") + " (" + schData.length + MGW_RES.get("gw_schedule_calendar_count_label") +")";
			if (currentTab == "todo") {
				title = MGW_RES.get("gw_schedule_todo_search_label") + " (" + schData.length + MGW_RES.get("gw_schedule_calendar_count_label") +")";
			}
			
			NAVIBAR_DEF.phone.sch_detaillist.title = title;
			NAVIBAR_DEF.pad.sch_detaillist.title = title;
			
			changeTitle(title);
			
			// selectOption value 1:설비, 2:달력, 3:전부, 4:공유일정
			if (selectOption == "1") {
				renderDetailList2(currentTab, schData, undefined, GW2_CONTROLLER_SCHEDULE.equipmentData);
			} 
			else if (selectOption == "2") {
				renderDetailList2(currentTab, schData, GW2_CONTROLLER_SCHEDULE.calendarData, undefined);
			}
			else if (selectOption == "3") {	// 전체일경우 달력+공유일정만 검색하므로 설비는 보낼필요 없다
				renderDetailList2(currentTab, schData, GW2_CONTROLLER_SCHEDULE.calendarData, undefined);
			}
			else if (selectOption == "4") {
				renderDetailList2(currentTab, schData, undefined, undefined);
			}
		});
	});
}

function todo2Detail(todoId, ownerId, calTitle, calColor, apiCode){
	getSchTodo2Detail(todoId, ownerId, calTitle, calColor);
}

/*
 * 일간, 주간, 월간 변경 시 period : 일간(DAILY), 주간(WEEKLY), 월간(MONTHLY) 파라미터값 표시
 */
function changeDateMode2(period){
	var view = $.mobile.activePage;
	
	if (period == undefined)
		period = view.find("#dateMode").val();
	
	view.find("#dateMode").val(period);
	
	var dateMode = period;
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = view.find("#baseDate").text();
	var apiCode = view.find("#apiCode").val();
	
	if (apiCode == "schlist") {
		getSch2List(apiCode, 0, dateMode, calMode, baseDate);
	}
	else if (apiCode == "equiplist") {
		getSchEquip2List(apiCode, 0, dateMode, baseDate);
	}
	else if (apiCode == "todolist") {
		getSchTodo2List(apiCode, 0, dateMode, calMode, baseDate);
	}
}

/*
 * 달력 이동 시 '오늘'버튼으로 되돌아 가는 FN
 */
function todayDate2(){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = new Date();
	var apiCode = view.find("#apiCode").val();
	var id = view.find("#equipmentIDS").val();
	baseDate = dateToString(baseDate);
	
	if (apiCode == "schlist") {
		getSch2List(apiCode, 0, dateMode, calMode, baseDate);
	}
	else if (apiCode == "equiplist") {
		getSchEquip2List(apiCode, 0, dateMode, baseDate);
	}
	else if (apiCode == "equiplistdetail") {
		getSchEquip2List(apiCode, 0, dateMode, baseDate, "DETAIL", id);
	}
	else if (apiCode == "todolist") {
		getSchTodo2List(apiCode, 0, dateMode, calMode, baseDate);
	}
}
/*******************************************************************************
 * UI Script END for COMMON *
 ******************************************************************************/



/*******************************************************************************
 * UI Script START for Schedule(일정에 사용되는 스크립트) *
 ******************************************************************************/
/*
 * 달력모드가 변경 되었을 때 처리 FN (일간, 주간, 월간)
 */
function changeCalMode2(){
	var view = $.mobile.activePage;	
	var dateMode = view.find("#dateMode").val();
	var calId = view.find("#calMode option:selected").val();
	GW2_CONTROLLER_SCHEDULE.selectedCalendarIdOfList = calId;
	var baseDate = view.find("#baseDate").text();
	var apiCode = view.find("#apiCode").val();
	LOG(view + ", " + dateMode + ", " +calId + ", " + baseDate + ", " + apiCode); 
	if (apiCode == "schlist") {
		getSch2List(apiCode, 0, dateMode, calId, baseDate);
	}
	else if (apiCode == "todolist") {
		getSchTodo2List(apiCode, 0, dateMode, calId, baseDate);
	}
}

/*
 * 전날 검색 '<' 버튼 FN
 */
function lastDate2(){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();;
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = view.find("#baseDate").text();
	var apiCode = view.find("#apiCode").val();
	var id = view.find("#equipmentIDS").val();
	
	if (dateMode == "DAILY")	{
		baseDate = lastDay(baseDate);
	}
	else if (dateMode == "WEEKLY") {
		baseDate = lastWeek(baseDate);
	}
	else if (dateMode == "MONTHLY") {
		baseDate = lastMonth(baseDate);
	}
	
	if (apiCode == "schlist") {
		getSch2List(apiCode, 0, dateMode, calMode, baseDate);
	}
	else if (apiCode == "equiplist") {
		getSchEquip2List(apiCode, 0, dateMode, baseDate);
	}
	else if (apiCode == "equiplistdetail") {
		getSchEquip2List(apiCode, 0, dateMode, baseDate, "DETAIL", id);
	}
	else if (apiCode == "todolist") {
		getSchTodo2List(apiCode, 0, dateMode, calMode, baseDate);
	}
}

/*
 * 다음날 검색 '>' 버튼 FN
 */
function nextDate2(){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();;
	var calMode = view.find("#calMode option:selected").val();
	var baseDate = view.find("#baseDate").text();
	var apiCode = view.find("#apiCode").val();
	var id = view.find("#equipmentIDS").val();
	
	if (dateMode == "DAILY") {
		baseDate = nextDay(baseDate);
	}
	else if (dateMode == "WEEKLY") {
		baseDate = nextWeek(baseDate);
	}
	else if (dateMode == "MONTHLY") {
		baseDate = nextMonth(baseDate);
	}
	
	if (apiCode == "schlist") {
		getSch2List(apiCode, 0, dateMode, calMode, baseDate);
	}
	else if (apiCode == "equiplist") {
		getSchEquip2List(apiCode, 0, dateMode, baseDate);
	}
	else if (apiCode == "equiplistdetail") {
		getSchEquip2List(apiCode, 0, dateMode, baseDate, "DETAIL", id);
	}
	else if (apiCode == "todolist") {
		getSchTodo2List(apiCode, 0, dateMode, calMode, baseDate);
	}
}

/*******************************************************************************
 * UI Script END for Schedule *
 ******************************************************************************/



/*******************************************************************************
 * UI Script START for Equipment(설비에 사용되는 스크립트) *
 ******************************************************************************/	
function detailList2(id){
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();;
	var baseDate = view.find("#baseDate").text();
	var apiCode = "equiplistdetail";
	
	PAGE_CONTROLLER.showPage("sch_" + apiCode, function() {getSchEquip2List(apiCode, 0, dateMode, baseDate, "DETAIL", id);});
	
	view = $.mobile.activePage;
	view.find("#equipmentIDS").val(id);
}

/*******************************************************************************
 * UI Script END for Equipment *
 ******************************************************************************/

	

/*******************************************************************************
 * #Schedule List START('일간', '주간', '월간' 일정목록 스크립트) *
 ******************************************************************************/
/*
 * 일정과 설비쪽 목록을 출력할 때 사용하는 FN apiCode : 보고자 하는 목록의 api 코드값 (일정->schlist,
 * 설비->equiplist, 할일->todolist) 각 페이지 div에 id값으로 명시 됨 page_num : 페이지 네이션 기능이 있을때
 * 사용 현재로는 api기능 미제공으로 기본값으로 0을 삽입 dateMode : 일간(DAILY),주간(WEEKLY),월간(MONTHLY)으로
 * 구분; calMode : 전체달력(ALL), 나의달력(MY), 부서달력(DEPT), 전사달력(COMPANY), 사용자달력(USER)으로
 * 구분; callDate : 요청된 날 표기 (ex : 2012.11.12)
 * 
 */
function getSch2List(apiCode, page_num, dateMode, calId, callDate, dayMode) {
	var baseDate = callDate;
	var startDate = baseDate;
	var endDate = baseDate;
	var curDate = "";
	var param = {};
	param["CALENDAR_IDS"] = "";
	getCalendar2List(
		function() 
		{
			// 달력별 Id 셋팅
			if (GW2_CONTROLLER_SCHEDULE.calendarData.length != 0) {
				for(var i=0; i<GW2_CONTROLLER_SCHEDULE.calendarData.length; i++) {
					if(calId == GW2_CONTROLLER_SCHEDULE.calendarData[i].calendar_id)
						param["CALENDAR_IDS"] += GW2_CONTROLLER_SCHEDULE.calendarData[i].calendar_id;
					else if(calId == "ALL"){
						param["CALENDAR_IDS"] = GW2_CONTROLLER_SCHEDULE.calendarIds;
						break;
					} else if(calId == SHARED_CALENDAR_ID){
						param["CALENDAR_IDS"] = SHARED_CALENDAR_ID;
						break;
					} else if(calId == MY_SHARED_CALENDAR_ID){
						param["CALENDAR_IDS"] = GW2_CONTROLLER_SCHEDULE.myCalendarId + ";" + SHARED_CALENDAR_ID;
						break;
					}
					LOG(calId + ", " + GW2_CONTROLLER_SCHEDULE.calendarData[i].calendar_id);
				}
			}
				
			if (dateMode == "DAILY") {
				param["TARGET_START_DATE"] = startDate;
				param["TARGET_END_DATE"] = endDate;
				curDate = "<span>" + getYear(startDate)	+ "</span>." + getMonth(startDate) + "."
							+ getDate(startDate) + "(" + getWeekName(startDate) + ")";
			}
			else if (dateMode == "WEEKLY") {
				startDate = getFirstDateOfWeek(startDate);
				endDate = getLastDateOfWeek(endDate);
				param["TARGET_START_DATE"] = startDate;
				param["TARGET_END_DATE"] = endDate;
				// YYYY.M.D(요일)~?.D(요일) 형식
				curDate = getFilterDate(startDate, endDate, "date", "startDate") + "(" + getWeekName(startDate)
							+ ")~" + "<span>" + getFilterDate(startDate, endDate, "date", "endDate") + "</span>" + "(" + getWeekName(endDate) + ")";
			}
			else if (dateMode == "MONTHLY") {
				startDate = getFirstDateOfMonth(startDate);
				endDate = getLastDateOfMonth(endDate);
				param["TARGET_START_DATE"] = startDate;
				param["TARGET_END_DATE"] = endDate;
				curDate = "<span>" + getYear(startDate) + "</span>." + getMonth(startDate);
			}
			
			var view = $.mobile.activePage;
			
			view.find("#divList").attr("class","divListNormal");
			view.find("#divTitle").attr("class","divListNormal");
			view.find("#baseDate").html(baseDate);		// 기준일
			view.find("#curDate").html(curDate);

			if (apiCode == "schlist") {
				if (dateMode == "MONTHLY" && !dayMode) {
					GW_PROXY.invokeOpenAPI("schedule", (calId == "ALL" || calId == SHARED_CALENDAR_ID || calId == MY_SHARED_CALENDAR_ID ? apiCode_schCount : apiCode_schSelectedCount), param, function(schData) {
						renderSch2List(apiCode, page_num, schData, GW2_CONTROLLER_SCHEDULE.calendarData, curDate, baseDate, dateMode, startDate, endDate, dayMode);
					});
				}
				else {
					GW_PROXY.invokeOpenAPI("schedule", (calId == "ALL" || calId == SHARED_CALENDAR_ID || calId == MY_SHARED_CALENDAR_ID ? apiCode_schList : apiCode_schSelectedList), param, function(schData) {
						renderSch2List(apiCode, page_num, schData, GW2_CONTROLLER_SCHEDULE.calendarData, curDate, baseDate, dateMode, startDate, endDate, dayMode);
					});
				}
			}
		}
	);
	
}

function getCalendar2List(callback) {
	if (!GW2_CONTROLLER_SCHEDULE.calendarLoaded) {
		GW_PROXY.invokeOpenAPI("schedule", apiCode_calList, {}, function(data) {
			GW2_CONTROLLER_SCHEDULE.initCalendar(data);
			renderCalendar2List(data);

			callback();
		});
	}
	else {
		renderCalendar2List(GW2_CONTROLLER_SCHEDULE.calendarData);
		callback();
	}
}

function getEquipment2List(callback) {
	if (!GW2_CONTROLLER_SCHEDULE.equipmentLoaded) {
		GW_PROXY.invokeOpenAPI("schedule", apiCode_schEquipList, {}, function(data) {
			LOG("initEquipment()호출");

			// 데이터가 없을 경우 null이 return됨.
			if(data == null)
				data = undefined;
			GW2_CONTROLLER_SCHEDULE.initEquipment(data);
			callback();
		});
	}
	else {
		callback();
	}
}

function getEquipment2GroupList(callback) {
	if (!GW2_CONTROLLER_SCHEDULE.equipmentGroupLoaded) {
		GW_PROXY.invokeOpenAPI("schedule", apiCode_schEquipGroupList, {}, function(data) {
			LOG("initEquipmentGroup()호출");
			GW2_CONTROLLER_SCHEDULE.initEquipmentGroup(data);
			callback();
		});
	}
	else {
		callback();
	}
}

function renderCalendar2List(data) {
	var view = $.mobile.activePage;
	var tmp = "";
	
	if (view.find("#isLoadedCalendar").val() == "true")
		return;
	
	if (data.length != 0) {
		for(var i=0; i<data.length; i++) {
			var managerName = "";
			if (data[i].calendar_id != "1") 
				managerName = "(" + data[i].mngr_nm + ")";
			var selected = "";
			if(data[i].calendar_id == GW2_CONTROLLER_SCHEDULE.selectedCalendarIdOfList) {
				selected = "selected";
			}
			tmp += "<option name='" + data[i].owner_tp + "' value='" + data[i].calendar_id + "'" + selected + ">" + htmlDecode(data[i].title) + managerName + "</option>";
		}
	}
	
	if (tmp != "") {
		view.find("#calMode").append(tmp);
		view.find("#isLoadedCalendar").val("true");
	}
}

function renderEquipment2List(data) {
	var view = $.mobile.activePage;
	var tmp = "";
	
	if (view.find("#isLoadedEquipment").val() == "true")
		return;
	
	if (data.length != 0) {
		for(var i=0; i<data.length; i++) {
			tmp += "<option name='" + data[i].calendar_id + "' value='" + data[i].calendar_id + "'>" + htmlDecode(data[i].title) + "</option>";
		}
	}
	
	if (tmp != "") {
		view.find("#selEquipment").append(tmp);
		view.find("#isLoadedEquipment").val("true");
	}
}

/*
 * apiCode : 보고자 하는 목록의 api 코드값 (일정->schlist) 각 페이지 div에 id값으로 명시 됨 page_num :
 * 페이지 네이션 기능이 있을때 사용 현재로는 api기능 미제공으로 기본값으로 0을 삽입 data : api호출을 통해 가져온 목록 리스트
 * data calData : 달력 데이터 curDate : 현재 표기될 날짜(일간, 주간, 월간 별로 변경됨) baseDate : 기준일
 * dateMode : 일간(DAILY), 주간(WEEKLY), 월간(MONTHLY) startDate : 검색 시작일 endDate : 검색
 * 종료일
 * 
 * 공유일정은 그룹웨어에서도 하드코딩되어있다. 명칭 : 공유일정 색상값 : #0372FB calendarId :
 * 99999999999999999999
 */
function renderSch2List(apiCode, page_num, data, calData, curDate, baseDate, dateMode, startDate, endDate, dayMode){
	var view = $.mobile.activePage;
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");
	view.find("#baseDate").html(baseDate);		// 기준일
	
	/* 기존데이터 초기화 */
	list.html("");

	/* 일간 UI Render */
	if (dateMode == "DAILY") {
		/* DAILY, WEEKLY일때만 divList 기존데이터 초기화 */
		divList.html("");
		var selectDate = startDate;
		if (data.length != 0) {
			for(var i = 0; i< data.length;i++){// 리스트에 메일 렌더
				var item = data[i];
				var tmp = [];

				tmp.push("<li id=\"" + item.event_id +"\" >");
				tmp.push("<a href=\"javascript:sch2Detail('"+ item.event_id + "'," + undefined + ", '" + item.owner_id + "','"+selectDate+"');\">");
				tmp.push("<div class='calendarName'>");
				
				if (calData.length != 0) {
					for(var j=0; j<calData.length; j++) {
						if (item.calendar_id == calData[j].calendar_id) {
							tmp.push("<font color='" + calData[j].ui_attr + "'>[" + htmlDecode(calData[j].title) + "]</font></div>");
						}
						else if (item.calendar_id == calData[j][0]) {
							tmp.push("<font color='" + calData[j][2] + "'>[" + calData[j][1] + "]</font></div>");
						}
						else if(item.calendar_id == SHARED_CALENDAR_ID){
							tmp.push("<font color='#0372FB'>[" + MGW_RES.get("gw_schedule_shared_calendar_label") + "]</font></div>");
							
							break;
						}
					}
				}
				
				tmp.push("<h3 class='title'>" + htmlDecode(item.title) + "</h3>");
				
				tmp.push(schRepeatCtrl.renderSchTermDetailForList(item.start_date, item.end_date));
				tmp.push("</a></li>");
				list.append(tmp.join(""));
			}
		}
		else {
			var tmp = [];
			
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
			list.html(tmp.join(""));
		}
		
		list.listview("refresh");
	}
	
	/* 주간 UI Render */
	else if (dateMode == "WEEKLY") {
		/* DAILY, WEEKLY일때만 divList 기존데이터 초기화 */
		divList.html("");

		var tmp = [];
		var temp = 0;
		var titleName = new Array();
		var titleCnt = new Array();
		
		var n;
		var isCheck;
		// 하루 기준 milliseconds
		var oneDay = (1000*60*60*24);
		
		tmp.push("<table class='dateArea' border='0' cellspacing='0' cellpadding='0'>");
		divList.attr("class", "divList");

		// 일주일을 배열에 담는다
		var weekArr = [startDate];
		while(true) {
			var nextDay = new Date(Date.parse(stringToDate(weekArr[weekArr.length-1])) + 1 * oneDay);
			weekArr.push(dateToString(nextDay));
			if (dateToString(nextDay) == endDate) break;
		}

		for(var i=0; i<weekArr.length; i++) {
			titleCnt = [];
			for(var j=0; j<data.length; j++) {
				item = data[j];
				var checkEndDate = item.end_date;

				// 종료일의 시간이 0시 00 분일 경우 1초전으로 바꿈
				if(checkEndDate.lastIndexOf(" 0:00") > -1) {
					checkEndDate = dateToString(new Date(Date.parse(stringToDate(item.end_date)) - 1000));
				}

				// 검색주간의 날짜가 일정의 시작시간 부터 종료시간 사이에 있는지 검사
				if(checkDatePeriod(item.start_date, checkEndDate, weekArr[i])) {
					var dataArr = [item.event_id, item.title, item.calendar_id, item.owner_id, item.start_date];
					titleCnt.push(dataArr);
				}
			}
			titleName[i] = titleCnt;
		}

		for(i=0; i<7; i++) {
			var selectDate = weekArr[i];
			if(i == 6){
				satOrsun = "Sat";
			}
			else if(i == 0){
				satOrsun = "Sun";
			}
			else {
				satOrsun = "";
			}
			tmp.push("<tr>");
			tmp.push("<th scope='row' class='" + satOrsun + "'>"
					+ getDate(getDatetoDay(startDate, i))
					+ "<span class='dateWeek'>" + getWeektoNum(i)
					+ "</span></th>");
			tmp.push("<td");
			
			if (dateToString(new Date()) == dateToString(new Date(
					getYear(baseDate), getMonth(baseDate) - 1, getDate(startDate) + i))
					&& checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
				tmp.push(" class='selectDay'");
			}
			
			tmp.push(">");
			
			if (titleName[i] != undefined) {
				for(j=0; j<titleName[i].length; j++) {
					tmp.push("<a href=\"javascript:sch2Detail('"+ titleName[i][j][0] + "', " + undefined + ", '" + titleName[i][j][3] + "','"+selectDate+"');\">");
					tmp.push("<div><p></p>");
					tmp.push("<span class='title'>");
					
					// 일정 시작 시간 표시
					if (titleName[i][j][4].substring(10).lastIndexOf(" 0:00") != -1)	// 종일일정
						tmp.push(MGW_RES.get("gw_schedule_day_schedule_label"));
					else
						tmp.push(titleName[i][j][4].substring(11));
					
					for(k=0; k<calData.length; k++) {
						// 달력 정보 표시
						if (titleName[i][j][2] == calData[k].calendar_id) {
							tmp.push("<font color='" + calData[k].ui_attr + "'>["+ htmlDecode(calData[k].title) +"]</font>");
						}
						// 설비 정보 표시
						else if (titleName[i][j][2] == calData[k][0]) {
							tmp.push("<font color='" + calData[k][2] + "'>["+ calData[k][1] +"]</font>");
						}
						else if(titleName[i][j][2] == SHARED_CALENDAR_ID){
							tmp.push("<font color='#0372FB'>[" + MGW_RES.get("gw_schedule_shared_calendar_label") + "]</font>");
							break;
						}
					}
					tmp.push(htmlDecode(titleName[i][j][1]) + "</span></div>");
					tmp.push("</a>");
				}
			}
			
			tmp.push("</td>");
			tmp.push("</tr>");
		}
		tmp.push("</table>");
		divList.html(tmp.join(""));
		
		list.listview("refresh");
	}
	else if (dateMode == "MONTHLY") {
		if (dayMode == true) {
			var tmp = [];	
			var isFindData = false;
			var selectDate = baseDate;
			
			if (data != undefined) {
				for(var i = 0; i<data.length;i++) {
					var item = data[i];
					
					if (checkDatePeriod(item.start_date, item.end_date, baseDate) && item.end_date.lastIndexOf(getDate(baseDate) + " 0:00") == -1) {						
						tmp.push("<li id='" + item.event_id +"'>");
						
						if (apiCode == "schlist") {
							tmp.push("<a href=\"javascript:sch2Detail('"+ item.event_id + "', 'schedule', '" + item.owner_id + "','"+selectDate+"');\">");
						}
						if (apiCode == "equiplistdetail") {
							tmp.push("<a href=\"javascript:sch2Detail('"+ item.event_id + "', 'equipment', '" + item.owner_id + "','"+selectDate+"');\">");
						}
						tmp.push("<div class='calendarName'>");
						
						for(j=0; j<calData.length; j++) {
							// 달력 정보 표시
							if (item.calendar_id == calData[j].calendar_id) {
								tmp.push("<font color='" + calData[j].ui_attr + "'>["+ htmlDecode(calData[j].title) +"]</font></span>");
							}
							// 설비 정보 표시
							else if (item.calendar_id == calData[j][0]) {
								tmp.push("<font color='" + calData[j][2] + "'>["+ calData[j][1] +"]</font></span>");
							}
							else if(item.calendar_id == SHARED_CALENDAR_ID){
								tmp.push("<font color='#0372FB'>[" + MGW_RES.get("gw_schedule_shared_calendar_label") + "]</font>");
								break;
							}
						}
						
						tmp.push("</div><h3 class='title'>"+htmlDecode(item.title)+"</h3>");
						tmp.push(schRepeatCtrl.renderSchTermDetailForList(item.start_date, item.end_date));
						tmp.push("</a></li>");					
						isFindData = true;
					}				
				}
				
				if (isFindData == false) {
					tmp = [];
					
					tmp.push("<li><h3>" + "[" + view.find("#baseDate").text() + "] " + MGW_RES.get("gw_msg_schedule_schedule_nolist")+"</h3></li>");
					list.html(tmp.join(""));
					list.listview("refresh");
				}
				else {
					list.append(tmp.join(""));
					list.listview("refresh");
					}
			}
			else {
				var tmp = [];
				
				tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
				list.html(tmp.join(""));
				list.listview("refresh");
			}
		}	
		else {
			divList.attr("class","divList");
			var temp = 0;
			var tmp = [];
			
			tmp.push("<table class='calMonth'><tr>");
			tmp.push("<tr>");
			
			for(var i=0; i<7; i++) {
				if(i == 6){
					satOrsun = "Sat";
				}
				else if(i == 0){
					satOrsun = "Sun";
				}
				else {
					satOrsun = "";
				}
				tmp.push("<th class='" + satOrsun + "'>" + getWeektoNum(i) + "</th>");
			}
			
			tmp.push("</tr>");
			tmp.push("<tbody>");
			tmp.push("<tr>");
			
			for(var i=0; i<getWeekNumber(startDate); i++) {
				tmp.push("<td></td>");
			}
			
			for(var i=0; i<getDate(endDate); i++) {
				if (data.length > temp) {
					if (getDate(data[temp].start_dt) == i + 1)	{
						LOG(view.find("#baseDate").text() + "', '" + (i+1) );
						tmp.push("<td id='" + (i+1) + "' onclick=\"javascript:monthToDayList2('" + view.find("#baseDate").text() + "', '" + (i+1) + "');\"");
					}
					else {
						tmp.push("<td");
					}
				}
				else {
					tmp.push("<td");
				}
				
				if (getDate(dateToString(new Date())) == i+1 && checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
					tmp.push(" class='today'");
				}
				
				tmp.push(">");
				
				// 토요일
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 0) {
					tmp.push("<span class='sat'>");
				}
				// 일요일
				else if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("<span class='sun'>");
				}
				// 평일
				else {
					tmp.push("<span>");
				}
				
				if (data != undefined && data.length > temp) {
					// 건수표시
					if (getDate(data[temp].start_dt) == i+1) {
						tmp.push((i+1)
							+ "</span><a href=\"javascript:;\" class='schcount'>"
							+ data[temp++].count
							+ MGW_RES.get("gw_schedule_calendar_count_label")
							+ "</a>");
					}
					else {
						tmp.push((i+1) + "</span>");
					}
				}
				else {
					tmp.push((i+1) + "</span>");
				}
				tmp.push("</td>");
				
				// 주변경
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("</tr><tr>");
				}
				if(getDate(baseDate) == (i+1)) {
					monthToDayList2(baseDate,baseDate.substring(8));
				}
			}
			tmp.push("</tr><tbody>");
			divList.html(tmp.join(""));
		}
		
	}
}

function getSchEquip2List(apiCode, page_num, dateMode, callDate, equipMode, equip_IDS, dayMode) {
	var baseDate = callDate;
	var startDate = baseDate;
	var endDate = baseDate;
	var curDate = "";
	var param = {};
	var equipTitleList = new Array();
	
	var view = $.mobile.activePage;
	view.find("#dateMode").val(dateMode);
	view.find("#baseDate").val(baseDate);
	
	getCalendar2List(function() {
		var data = GW2_CONTROLLER_SCHEDULE.calendarData;
		
		getEquipment2List(function() {
			param["CALENDAR_IDS"] = "";
			param["EQUIPMENT_IDS"] = "";
	
			var index = 0;
			for(var i=0; i<data.length; i++) {
				param["CALENDAR_IDS"] += data[i].calendar_id+";";
			}
				
			if (dateMode == "DAILY") {
				param["TARGET_START_DATE"] = startDate;
				param["TARGET_END_DATE"] = endDate;
				curDate = "<span>" + getYear(startDate) + "</span>." + getMonth(startDate) + "." + getDate(startDate) + "(" + getWeekName(startDate) + ")";
			}
			else if (dateMode == "WEEKLY") {
				startDate = getFirstDateOfWeek(startDate);
				endDate = getLastDateOfWeek(endDate);
						
				param["TARGET_START_DATE"] = startDate;
				param["TARGET_END_DATE"] = endDate;
				// YYYY.M.D(요일)~?.D(요일) 형식
				curDate = getFilterDate(startDate, endDate, "date", "startDate") + "(" + getWeekName(startDate)
							+ ")~" + "<span>" + getFilterDate(startDate, endDate, "date", "endDate") + "</span>" + "(" + getWeekName(endDate) + ")";
			}
			else if (dateMode == "MONTHLY") {
				startDate = getFirstDateOfMonth(startDate);
				endDate = getLastDateOfMonth(endDate);
						
				param["TARGET_START_DATE"] = startDate;
				param["TARGET_END_DATE"] = endDate;
					
				curDate = "<span>" + getYear(startDate) + "</span>." + getMonth(startDate);
			}
				
			view.find("#curDate").html(curDate);
			var equipData = GW2_CONTROLLER_SCHEDULE.equipmentData;
		
			// 각 설비의 타이틀명들을 equipTitleList에 저장
			index = 0;
			for(var i=0; i<equipData.length; i++) {
				if (GW2_CONTROLLER_SCHEDULE.selectAllEquipment == true) {
					param["EQUIPMENT_IDS"] += equipData[i].calendar_id + ";";
					equipTitleList[index] = new Array();
					equipTitleList[index][0] = equipData[i].calendar_id;
					equipTitleList[index][1] = htmlDecode(equipData[i].title);
					equipTitleList[index][2] = equipData[i].ui_attr;
					equipTitleList[index++][3] = 0;
				}
				else {
					if (GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds != "") {
						var idArr =  GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds.split(";");
						for (var j=0; j<idArr.length; j++) {
							if (idArr[j] != "" && idArr[j] == equipData[i].calendar_id) {
								param["EQUIPMENT_IDS"] += equipData[i].calendar_id + ";";
								equipTitleList[index] = new Array();
								equipTitleList[index][0] = equipData[i].calendar_id;
								equipTitleList[index][1] = htmlDecode(equipData[i].title);
								equipTitleList[index][2] = equipData[i].ui_attr;
								equipTitleList[index++][3] = 0;
							}	
						}
					}
				}
			}
			
			if (param["EQUIPMENT_IDS"] == "") {
				if (dateMode == "MONTHLY" && !dayMode) {
					renderSchEquipList(apiCode, page_num, undefined, equipTitleList, curDate, baseDate, dateMode, startDate, endDate, dayMode);
				}
				else {
					renderSchEquipList(apiCode, page_num, undefined, equipTitleList, curDate, baseDate, dateMode, startDate, endDate, dayMode);
				}
			}
			else {
				var equipParam = JSON.parse( JSON.stringify(param) );
				GW_PROXY.invokeOpenAPI("schedule", apiCode_equipList, param, function(equipListData) {
					if (dateMode == "MONTHLY" && !dayMode) {
						GW_PROXY.invokeOpenAPI("schedule", apiCode_equipCount, equipParam, function(equipCountData) {
							renderSchEquip2List(apiCode, page_num, equipCountData, equipTitleList, curDate, baseDate, dateMode, startDate, endDate, dayMode);
						});
					}
					else {
						renderSchEquip2List(apiCode, page_num, equipListData, equipTitleList, curDate, baseDate, dateMode, startDate, endDate, dayMode);
					}
				});
			}
		});
	});
		
}

function renderSchEquip2List(apiCode, page_num, data, calData, curDate, baseDate, dateMode, startDate, endDate, dayMode) {
var view = $.mobile.activePage;
	
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");
	view.find("#baseDate").html(baseDate);		// 기준일
	
	/* 기존데이터 초기화 */
	list.html("");
	
	/* 일간 UI Render */
	if (dateMode == "DAILY") {
		// LOG(data);
		
		/* DAILY, WEEKLY 일 때만 divList 기존데이터 초기화 */
		divList.html("");
		
		if (data != undefined && data.length != 0) {
			for(var i = 0; i< data.length;i++){// 리스트에 메일 렌더
				var item = data[i];
				var tmp = [];
				
				tmp.push("<li id=\"" + item.event_id +"\" >");
				tmp.push("<a href=\"javascript:sch2Detail('" + item.event_id + "', 'sch_detaillist_equip', '" + item.owner_id + "','"+baseDate+"');\">");
				tmp.push("<div class='calendarName'>");
				
				if (calData.length != 0) {
					for(var j=0; j<calData.length; j++) {
						if (item.calendar_id == calData[j].calendar_id) {
							tmp.push("<font color='" + calData[j].ui_attr + "'>[" + htmlDecode(calData[j].title) + "]</font></div>");
						}
						else if (item.calendar_id == calData[j][0]) {
							tmp.push("<font color='" + calData[j][2] + "'>[" + calData[j][1] + "]</font></div>");
						}
						else if(item.calendar_id == SHARED_CALENDAR_ID){
							tmp.push("<font color='#0372FB'>[" + MGW_RES.get("gw_schedule_shared_calendar_label") + "]</font>");
							break;
						}
					}
				}
				
				tmp.push("<h3 class='title'>" + htmlDecode(item.title) + "</h3>");
				tmp.push(schRepeatCtrl.renderSchTermDetailForList(item.start_date, item.end_date));
				tmp.push("</a></li>");
				list.append(tmp.join(""));
			}
		}
		else {
			var tmp = [];
			
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_equip_nothing") + "</h3></li>");
			list.html(tmp.join(""));
		}
		
		list.listview("refresh");
	}
	else if (dateMode == "WEEKLY") {
		// LOG(data);
		
		/* DAILY, WEEKLY 일 때만 divList 기존데이터 초기화 */
		divList.html("");
		
		var tmp = [];
		var temp = 0;
		var titleName = new Array();
		var titleCnt = new Array();
		
		var n;
		var isCheck;
		// 하루 기준 milliseconds
		var oneDay = (1000*60*60*24);
		
		tmp.push("<table class='dateArea' border='0' cellspacing='0' cellpadding='0'>");
		divList.attr("class", "divList");
		
		// 일주일을 배열에 담는다
		var weekArr = [startDate];
		while(true) {
			var nextDay = new Date(Date.parse(stringToDate(weekArr[weekArr.length-1])) + 1 * oneDay);
			weekArr.push(dateToString(nextDay));
			if (dateToString(nextDay) == endDate) break;
		}

		for(var i=0; i<weekArr.length; i++) {
			titleCnt = [];
			for(var j=0; j<data.length; j++) {
				item = data[j];
				var checkEndDate = item.end_date;

				// 종료일의 시간이 0시 00 분일 경우 1초전으로 바꿈
				if(checkEndDate.lastIndexOf(" 0:00") > -1) {
					checkEndDate = dateToString(new Date(Date.parse(stringToDate(item.end_date)) - 1000));
				}

				// 검색주간의 날짜가 일정의 시작시간 부터 종료시간 사이에 있는지 검사
				if(checkDatePeriod(item.start_date, checkEndDate, weekArr[i])) {
					var dataArr = [item.event_id, item.title, item.calendar_id, item.owner_id, item.start_date];
					titleCnt.push(dataArr);
				}
			}
			titleName[i] = titleCnt;
		}
		
		for(i=0; i<7; i++) {
			var selectDate = weekArr[i];
			tmp.push("<tr>");
			tmp.push("<th scope='row' class='" + getWeektoNum(i) + "'>"
					+ getDate(selectDate)
					+ "<span class='dateWeek'>" + getWeektoNum(i)
					+ "</span></th>");
			tmp.push("<td");
			
			if (dateToString(new Date()) == dateToString(new Date(
					getYear(baseDate), getMonth(baseDate) - 1, getDate(startDate) + i))
					&& checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
				tmp.push(" class='selectDay'");
			}
			
			tmp.push(">");
			
			if (titleName[i] != undefined) {
				for(j=0; j<titleName[i].length; j++) {
					tmp.push("<a href=\"javascript:sch2Detail('"+titleName[i][j][0]+"','sch_detaillist_equip','"+titleName[i][j][3]+"','"+selectDate+"');\">");
					tmp.push("<div><p></p>");
					tmp.push("<span class='title'>");
					

					// 일정 시작 시간 표시
					if (titleName[i][j][4].substring(10).lastIndexOf(" 0:00") != -1)	// 종일일정
						tmp.push(MGW_RES.get("gw_schedule_day_schedule_label"));
					else
						tmp.push(titleName[i][j][4].substring(11));
					
					for(var k=0; k<calData.length; k++) {
						// 달력 정보 표시
						if (titleName[i][j][2] == calData[k].calendar_id) {
							tmp.push("<font color='" + calData[k].ui_attr + "'>["+ htmlDecode(calData[k].title) +"]</font>");
						}
						// 설비 정보 표시
						else if (titleName[i][j][2] == calData[k][0]) {
							tmp.push("<font color='" + calData[k][2] + "'>["+ calData[k][1] +"]</font>");
						}
						else if(titleName[i][j][2] == SHARED_CALENDAR_ID){
							tmp.push("<font color='#0372FB'>[" + MGW_RES.get("gw_schedule_shared_calendar_label") + "]</font>");
							break;
						}
					}
					tmp.push(titleName[i][j][1] + "</span></div>");
					tmp.push("</a>");
				}
			}
			
			tmp.push("</td>");
			tmp.push("</tr>");
		}
		tmp.push("</table>");
		divList.html(tmp.join(""));
		
		list.listview("refresh");
	}
	else if (dateMode == "MONTHLY") {
		if (dayMode == true) {
			var tmp = [];	
			var isFindData = false;
			var selectDate = baseDate;
			// LOG(baseDate);
			if (data != undefined) {
				for(var i = 0; i<data.length;i++) {
					var item = data[i];
					if (checkDatePeriod(item.start_date, item.end_date, baseDate) && item.end_date.lastIndexOf(getDate(baseDate) + " 0:00") == -1) {
						tmp.push("<li id='" + item.event_id +"'>");
						
						tmp.push("<a href=\"javascript:sch2Detail('"+ item.event_id + "', 'equipment', '" + item.owner_id + "','"+selectDate+"');\">");
						tmp.push("<div class='calendarName'>");
						
						for(j=0; j<calData.length; j++) {
							// 달력 정보 표시
							if (item.calendar_id == calData[j].calendar_id) {
								tmp.push("<font color='" + calData[j].ui_attr + "'>["+ htmlDecode(calData[j].title) +"]</font></span>");
							}
							// 설비 정보 표시
							else if (item.calendar_id == calData[j][0]) {
								tmp.push("<font color='" + calData[j][2] + "'>["+ calData[j][1] +"]</font></span>");
							}
							else if(item.calendar_id == SHARED_CALENDAR_ID){
								tmp.push("<font color='#0372FB'>[" + MGW_RES.get("gw_schedule_shared_calendar_label") + "]</font>");
								break;
							}
						}
						
						tmp.push("</div><h3 class='title'>"+htmlDecode(item.title)+"</h3>");
						tmp.push(schRepeatCtrl.renderSchTermDetailForList(item.start_date, item.end_date));
						tmp.push("</a></li>");					
						isFindData = true;
					}				
				}
				
				if (isFindData == false) {
					tmp = [];
					
					tmp.push("<li><h3>" + "[" + view.find("#baseDate").text() + "] " + MGW_RES.get("gw_msg_schedule_schedule_nolist")+"</h3></li>");
					list.html(tmp.join(""));
					list.listview("refresh");
				}
				else {
					list.append(tmp.join(""));
					list.listview("refresh");
				}
				
				
			}
			else {
				var tmp = [];
				
				tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
				list.html(tmp.join(""));
				list.listview("refresh");
			}
		}	
		else {
			divList.attr("class","divList");
			var temp = 0;
			var tmp = [];
			
			tmp.push("<table class='calMonth'><tr>");
			tmp.push("<tr>");
			
			for(var i=0; i<7; i++) {
				tmp.push("<th class='" + getWeektoNum(i) + "'>" + getWeektoNum(i) + "</th>");
			}
			
			tmp.push("</tr>");
			tmp.push("<tbody>");
			tmp.push("<tr>");
			
			for(var i=0; i<getWeekNumber(startDate); i++) {
				tmp.push("<td></td>");
			}
			
			for(var i=0; i<getDate(endDate); i++) {
				if (data != undefined && data.length > temp) {
					if (getDate(data[temp].start_dt) == i + 1)	{
						tmp.push("<td id='" + (i+1) + "' onclick=\"javascript:monthToDayList2('" + view.find("#baseDate").text() + "', '" + (i+1) + "');\"");
					}
					else {
						tmp.push("<td");
					}
				}
				else {
					tmp.push("<td");
				}
				
				if (getDate(dateToString(new Date())) == i+1 && checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
					tmp.push(" class='today'");
				}
				
				tmp.push(">");
				
				// 토요일
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 0) {
					tmp.push("<span class='sat'>");
				}
				// 일요일
				else if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("<span class='sun'>");
				}
				// 평일
				else {
					tmp.push("<span>");
				}
				
				
				if (data != undefined && data.length > temp) {
					// 건수표시
					if (getDate(data[temp].start_dt) == i+1) {
						tmp.push((i+1)
							+ "</span><a href=\"javascript:;\" class='schcount'>"
							+ data[temp++].count
							+ MGW_RES.get("gw_schedule_calendar_count_label")
							+ "</a>");
					}
					else {
						tmp.push((i+1) + "</span>");
					}
				}
				else {
					tmp.push((i+1) + "</span>");
				}
				tmp.push("</td>");
				
				// 주변경
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("</tr><tr>");
				}
				monthToDayList2(baseDate,baseDate.substring(8));
			}
			tmp.push("</tr><tbody>");
			divList.html(tmp.join(""));
		}
	}
	
}

function getSchTodo2List(apiCode) {	
	var param = {};
	var page_num = 1;
	GW_PROXY.invokeOpenAPI("schedule", apiCode_todoList, param, function(todoData) {
		renderSchTodo2List(apiCode, page_num, todoData);
	});
}

function renderSchTodo2List(apiCode, page_num, todoData){
	// LOG(todoData);
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	
	if (todoData.items.length != 0 && todoData.items != "\n") {
		for(var i = 0; i< todoData.items.length;i++) {		
			var item = todoData.items[i];
			var tmp = [];
			
			tmp.push("<li id=\"" + item.task_id +"\" ><div class='grouping_left'>");
			
			if (item.importance_degree == 1) {
				tmp.push("<div class='todo_weight_h'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_weight_high") + "</div>");
			}
			else if (item.importance_degree == 3) {
				tmp.push("<div class='todo_weight_m'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_weight_medium") + "</div>");
			}
			else {
				tmp.push("<div class='todo_weight_l'></div><div class='todo_icon_label'>" + MGW_RES.get("gw_schedule_weight_low") + "</div>");
			}
						
			tmp.push("</div><a href=\"javascript:todo2Detail('" + item.task_id + "', '" + item.owner_id + "');\">");
			tmp.push("<h3>");
			tmp.push(htmlDecode(item.title));
			tmp.push("</h3></a>");
			tmp.push("</li>");
			list.append(tmp.join(""));
		}
	}
	else {
		var tmp = [];
		tmp.push("<li><h3>"+MGW_RES.get("gw_msg_schedule_todo_nolist")+"</h3></li>");
		list.html(tmp.join(""));
	}
	
	list.listview("refresh");
}
/*******************************************************************************
 * Schedule List END *
 ******************************************************************************/



/*******************************************************************************
 * Schedule Detail START *
 ******************************************************************************/
function renderDataInSch2Detail(item, attendeeList){
	var view = $.mobile.activePage;
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");

	view.find("#event_id").val(item.event_id);
	view.find("#start_dt").val(item.start_date);
	view.find("#recur_type").val(item.recur_type);
	
	list.find("#li_title").html(htmlDecode(item.title));
	list.find("#li_category").html(getSchCategoryValue(item.category_code));
	
	if (item.owner_id != "") { // 조직도 사용자
		list.find("#li_writer").html("<a href=\"javascript:showUserDetails('" + item.owner_id + "')\" class='btn_user blue'><span>" 
				+ item.owner_name + "</span><span class='viewUser'></span></a>");	
	}
	else {
		list.find("#li_writer").html(item.owner_name);	
	}
	// 반복 일정
	schRepeatCtrl.renderRecurTypeForDetail(item.recur_type, item.recur_rule, item.recur_cycle);
	// 기간
	schRepeatCtrl.renderSchTermDetail(item.start_date, item.end_date, list.find("#li_term"));
	
	var calender_title = "";
	if(typeof item.calendar_list === 'object') {
		for(var j=0; j<item.calendar_list.length; j++) {
			if(j == item.calendar_list.length - 1)
				calender_title += "<font color='" + item.calendar_list[j].ui_attr+ "'>" + htmlDecode(item.calendar_list[j].title) + "</font>";
			else
				calender_title += "<font color='" + item.calendar_list[j].ui_attr+ "'>" + htmlDecode(item.calendar_list[j].title) + "</font>, ";
		}	
	}
	list.find("#li_calendar").html(calender_title);
	
	var equipment_title = "";
	if (item.equipment_list.length == 0 || item.equipment_list == undefined || item.equipment_list == "\n") {
		equipment_title = MGW_RES.get("gw_schedule_not_label");
	}else{
		for(var j=0; j<item.equipment_list.length; j++) {
			if(j == item.equipment_list.length - 1)
				equipment_title += "<font color='" + item.equipment_list[j].ui_attr+ "'>" + htmlDecode(item.equipment_list[j].title) + "</font>";
			else
				equipment_title += "<font color='" + item.equipment_list[j].ui_attr+ "'>" + htmlDecode(item.equipment_list[j].title) + "</font>, ";
		}
	}	
	list.find("#li_equipment").html(equipment_title);
	
	// 공유자
	if (attendeeList.length != 0) {
		var attendeeNameArr = [];
		$(attendeeList).each(function(idx, attendee){
			attendeeNameArr[idx] = attendee.attendee_nm;
		})
		list.find("#li_attendee").html(attendeeNameArr.join(", "));
	}
	
	// 일정 상세보기 - 알림 데이터
	if (item.alarm_type == "0") {
		list.find("#li_alarm").html(MGW_RES.get("gw_schedule_not_label"));
	}else{
		var alarmRuleConfig = getSchConfigValue(SCHCONFIG_ALARM_PERIOD);
		var alarmRuleConfigArr = alarmRuleConfig.split(",");
		var orgAlarmRuleDataArr = item.alarm_rule.split(";");
		var newAlarmRuleDataArr = [];
		for(var i=0; i<orgAlarmRuleDataArr.length; i++){
			for(var j=0; j<alarmRuleConfigArr.length; j++){
				if(orgAlarmRuleDataArr[i] == alarmRuleConfigArr[j]) {
					newAlarmRuleDataArr.push(displayAlarmRule(orgAlarmRuleDataArr[i]));
				}
			}
		}
		list.find("#li_alarm").html(newAlarmRuleDataArr.join(", "));
	}
	
	list.find("#li_content").html(replaceNewlineTag(item.description));
}
// 일정 상세보기
function getSch2Detail(apiCode, eventId, ownerId) {
	var view = $.mobile.activePage;	
	var baseDate = view.find("#baseDate").val();
	var staticView = baseDate? false:true;
	var param = {};
	getCalendar2List(function() {
		var calData = GW2_CONTROLLER_SCHEDULE.calendarData;
		param = {};
		param["USER_ID"] = ownerId;
		param["EVENT_ID"] = eventId;

		GW_PROXY.invokeOpenAPI("schedule", apiCode_schDetail, param, function(schData) {
			TOOLBAR_DEF.sch2_schdetail = [0, [], [], []];

			// modify_auth가 있는 사용자는 해당 달력의 관리자로 설정되어 있으므로, owner_id 체크하지 않음.
			// write_auth만 있는 사용자는 공유자이면서 쓰기 권한만 있는 것이므로, 자신이 등록한 일정만 수정할 수
			// 있어야함. 그래서 owner_id를 체크.
			if( 1 == schData[0].calendar_list[0].modify_auth || 
					(1 == schData[0].calendar_list[0].write_auth && schData[0].owner_id == sessionStorage["id"]) ){
				TOOLBAR_DEF.sch2_schdetail =
					[2, [MGW_RES.get("gw_common_modify_label"), MGW_RES.get("gw_common_delete_label")], 
					["btn_tool_approval.png", "btn_tool_delete.png"],
					["javascript:showSch2Add(true);", "javascript:deleteSch2();"]];
			}
			
			GW_PROXY.invokeOpenAPI("schedule", apiCode_attendeeList, param, function(attendeeData) {
				PAGE_CONTROLLER.showPage("sch2_schdetail", function() {
					renderSch2Detail(apiCode, schData, calData, attendeeData.attendee_list, baseDate, staticView);
				});
			});
		});
	});
}
function renderSch2Detail(apiCode, schData, calData, attendeeList, baseDate, staticView) {
	// LOG(schData);
	// LOG(calData);
	var view = $.mobile.activePage;	
	if ($.mobile.activePage.prop("id") != "sch2_schdetail") {
		return;
	}
	var item = schData[0];
	view.find("#baseDate").val(baseDate);
	
	if (GWPlugin.usePlugin) {
		if(staticView) {
			GWPlugin.hideToolBar(function(){}, function(){alert('Error in hiding toolbar.')});
		}
	} else {
		// modify_auth가 있는 사용자는 해당 달력의 관리자로 설정되어 있으므로, owner_id 체크하지 않음.
		// write_auth만 있는 사용자는 공유자이면서 쓰기 권한만 있는 것이므로, 자신이 등록한 일정만 수정할 수 있어야함.
		// 그래서 owner_id를 체크.
		if(!staticView && (1 == item.calendar_list[0].modify_auth || 
				(1 == item.calendar_list[0].write_auth && item.owner_id == sessionStorage["id"])) ){
			view.find("#headerBtn").show();
		}	
	}
	renderDataInSch2Detail(item, attendeeList);
}

function displayAlarmRule(alarmRuleData) {
	if(alarmRuleData == "0mail") {
		return MGW_RES.get("gw_schedule_alarm_label_0mail");
	} else {
		var alarmUnitArr = ["m", "h", "d", "w", "M"];
		for(var i=0; i<alarmUnitArr.length; i++) {
			var index = alarmRuleData.lastIndexOf(alarmUnitArr[i]);
			if(index > -1) {
				var value = alarmRuleData.substring(0, index);
				if(value == "0") {
					return MGW_RES.get("gw_schedule_alarm_label_0");
				} else {
					return value + MGW_RES.get("gw_schedule_alarm_label_" + alarmUnitArr[i]);
				}
			}
		}
	}
}

// 설비 상세보기
function getSchEquip2Detail(apiCode, eventId, ownerId) {
	var view = $.mobile.activePage;	
	var param = {};
	var calendarTitleList = new Array();
	var equipTitleList = new Array();
	var parCalendarIdList = new Array();
	
	param["USER_ID"] = ownerId;
	param["EVENT_ID"] = eventId;
	
	GW_PROXY.invokeOpenAPI("schedule", apiCode_equipDetail, param, function(data) {
		var calData = GW2_CONTROLLER_SCHEDULE.calendarData;
		var equipData = GW2_CONTROLLER_SCHEDULE.equipmentData;
		var isParEquip = true;
		var isEquip = false;
		var index = 0;
		
		for(var i=0; i<equipData.length; i++) {
			if (equipData[i].equipment_sort_fg == 1) {
				parCalendarIdList[index++] = equipData[i].calendar_id;
			}
		}
				
		// 각 설비의 타이틀명들을 equipTitleList에 저장
		index = 0;
				
		for(var i=0; i<equipData.length; i++) {
			isParEquip = true;
			isEquip = false;
				
			for(var j=0; j<parCalendarIdList.length; j++) {
				if (parCalendarIdList[j] == equipData[i].calendar_id) {
					isParEquip = false;
				}
				if (parCalendarIdList[j] == equipData[i].par_calendar_id) {
					isEquip = true;
				}
			}
					
			if (isParEquip && isEquip) {
				equipTitleList[index] = new Array();
				equipTitleList[index][0] = equipData[i].calendar_id;
				equipTitleList[index][1] = htmlDecode(equipData[i].title);
				equipTitleList[index][2] = equipData[i].ui_attr;
				equipTitleList[index++][3] = 0;
			}
		}
		
		if (calData.length != 0) {
			for(var i=0; i<calData.length; i++) {
				if (data[0].calendar_id == calData[i].calendar_id) {
					calendarTitleList[0] = new Array();
					calendarTitleList[0][0] = calData[i].calendar_id;
					calendarTitleList[0][1] = htmlDecode(calData[i].title);
					calendarTitleList[0][2] = calData[i].ui_attr;
					break;
				}
			}
			// 달력 정보를 가져오지 못했을 경우
			if (calendarTitleList.length == 0) {
				calendarTitleList[0] = new Array();
				calendarTitleList[0][0] = calData[0].calendar_id;
				calendarTitleList[0][1] = htmlDecode(calData[0].title);
				calendarTitleList[0][2] = calData[0].ui_attr;
			}
		}
		
		TOOLBAR_DEF.sch2_equipdetail = [0, [], [], []];
		if (data[0].owner_id == sessionStorage["id"]) {
			TOOLBAR_DEF.sch2_equipdetail =
				[2, [MGW_RES.get("gw_common_modify_label"), MGW_RES.get("gw_common_delete_label")], 
				["btn_tool_approval.png", "btn_tool_delete.png"],
				["javascript:showSch2Add(true);", "javascript:deleteSch2();"]];
		}
		
		GW_PROXY.invokeOpenAPI("schedule", apiCode_attendeeList, param, function(attendeeData) {
			PAGE_CONTROLLER.showPage("sch2_equipdetail", function() {
				var baseDate = view.find("#baseDate").val();
				renderSchEquip2Detail(apiCode, data, equipTitleList, calendarTitleList, attendeeData.attendee_list, baseDate);
			});
		});
	});
	
}

function renderSchEquip2Detail(apiCode, schData, equipTitleList, calendarTitleList, attendeeList, baseDate) {
	var view = $.mobile.activePage;
	if (view.prop("id") != "sch2_equipdetail") {
		return;
	}
		
	var item = schData[0];
	var staticView = baseDate? false:true;
	view.find("#baseDate").val(baseDate);
	
	if (GWPlugin.usePlugin) {
		if(staticView) {
			GWPlugin.hideToolBar(function(){}, function(){alert('Error in hiding toolbar.')});
		}
	} else {
		// modify_auth가 있는 사용자는 해당 달력의 관리자로 설정되어 있으므로, owner_id 체크하지 않음.
		// write_auth만 있는 사용자는 공유자이면서 쓰기 권한만 있는 것이므로, 자신이 등록한 일정만 수정할 수 있어야함.
		// 그래서 owner_id를 체크.
		if(!staticView && (item.owner_id == sessionStorage["id"])){
			view.find("#headerBtn").show();
		}	
	}
	renderDataInSch2Detail(item, attendeeList);
}

// 할일 상세보기
function getSchTodo2Detail(todoId, ownerId) {
	var param = {};
	
	param["task_id"] = todoId;
	
	GW_PROXY.invokeOpenAPI("schedule", apiCode_todoDetail, param, function(data) {
		// LOG(data);
		item = data[0];
		
		if (item.owner_id == sessionStorage["id"]) {
			TOOLBAR_DEF.sch2_tododetail =
				[2, [MGW_RES.get("gw_common_modify_label"), MGW_RES.get("gw_common_delete_label")], 
				["btn_tool_approval.png", "btn_tool_delete.png"],
				["javascript:showTodo2Add(true);", "javascript:deleteTodo2();"]];
		}
		
		PAGE_CONTROLLER.showPage("sch2_tododetail", function() {
			renderSchTodo2Detail(data);
		});
	});
}

function renderSchTodo2Detail(data) {	
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	
	item = data[0];
	
	if (item.owner_id == sessionStorage["id"]) {
		view.find("#headerBtn").show();
	}
	
	view.find("#todo_id").val(item.task_id);
	view.find("#calendar_id").val(item.calendar_id);
	var tmp = [];
	// 제목
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_content_label") + "</div><div class='grouping_middle'>" + htmlDecode(item.title));	
	tmp.push("</div></li>");
	// 작성자
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_writer_label") + "</div><div class='grouping_middle'>");
	
	if (item.owner_id != "") { // 조직도 사용자
		tmp.push("<a href=\"javascript:showUserDetails('" + item.owner_id + "')\" class='btn_user blue'><span>" 
				+ item.writer_name + "</span><span class='viewUser'></span></a>");	
	}
	else {
		tmp.push(item.writer_name);	
	}	
	tmp.push("</div></li>");
	
	// 중요도
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_importance_label") + "</div><div class='grouping_middle'>");
	if (item.importance_degree == "1") {
		tmp.push("<font color='red'>" + MGW_RES.get("gw_schedule_weight_high") + "</font>");
	}
	else if (item.importance_degree == "3") {
		tmp.push(MGW_RES.get("gw_schedule_weight_medium"));
	}
	else if (item.importance_degree == "5") {
		tmp.push("<font color='green'>" + MGW_RES.get("gw_schedule_weight_low") + "</font>");
	}
	tmp.push("</div></li>");
	
	list.html(tmp.join(""));	
	list.listview("refresh");
}
/*******************************************************************************
 * Schedule Detail END *
 ******************************************************************************/



/*******************************************************************************
 * Schedule / Todo Add&Edit&Delete START *
 ******************************************************************************/
function getSch2Add(eventId, baseDate) {
	var param = {};
	getCalendar2List(function() {
		var calData = GW2_CONTROLLER_SCHEDULE.calendarData;
				
		getEquipment2List(function() {
			// 일정 수정
			if (eventId != undefined && eventId != "") {
				changeTitle(MGW_RES.get("gw_schedule_schmodify_label"));

				param["EVENT_ID"] = eventId;
				
				GW_PROXY.invokeOpenAPI("schedule", apiCode_schDetail, param, function(schData) {
					GW_PROXY.invokeOpenAPI("schedule", apiCode_attendeeList, param, function(attendeeData) {
						renderSch2Add(calData, GW2_CONTROLLER_SCHEDULE.equipmentData, schData, attendeeData.attendee_list, baseDate);
					});					
					
				});
			}
			// 일정 추가
			else {
				renderSch2Add(calData, GW2_CONTROLLER_SCHEDULE.equipmentData, undefined, undefined, baseDate);
			}
		});
	});
}

// calData:달력 목록, equipData:설비 목록, schData:일정/설비정보(수정용)
function renderSch2Add(calData, equipData, schData, attendeeList, baseDate) {
	var view = $.mobile.activePage;
	// LOG(schData);
	if (view.prop("id") != "sch2_schadd")
		return;
	
	var selectdtStartTime = view.find("#dtstart_hour");
	var selectdtEndTime = view.find("#dtend_hour");
	var toDay = "";
	if(baseDate == undefined || baseDate == "") {
		toDay = getToday();
	} else {
		toDay = baseDate;
	}
	view.find("#dtstart_date").val(toDay);
	view.find("#dtend_date").val(toDay);
	view.find("#txt_dtstart_date").val(toDay);
	view.find("#txt_dtend_date").val(toDay);

	doSetDatebox();
	
	var nowHour = getHour()+"";
	var afterOneHour = getHoursAddHour(1)+"";
	var nowTime = nowHour.lpad("0", 2);
	var endTime = afterOneHour.lpad("0", 2);
	
	for(var i = 0; i < 24; i++){
		var time = i+"";
		time = time.lpad("0", 2);
		
		selectdtStartTime.append($("<option>", { value: time, html: time +  MGW_RES.get("gw_common_hour_label"), selected: (time == nowTime)}));
		selectdtEndTime.append($("<option>", { value: time, html: time +  MGW_RES.get("gw_common_hour_label"), selected: (time == endTime)}));
	}
	
	// 일정 유형 options 생성
	$(GW2_CONTROLLER_SCHEDULE.schCategoryData).each(function(idx, data){ 
		view.find("#category").append($("<option>", { value: data.category_code, html: data.category_name }));
	});
	
	// 반복 일정 options 생성
	schRepeatCtrl.renderOptionsRecurType();
	
	var tmp = [];
	tmp.push("<ul>");
	// 달력 목록 랜더링
	if (calData.length == 0) 
		tmp.push("<li><label>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</label></li>");
	tmp.push("</ul>");
	view.find("#calendarlist").html(tmp.join(""));
	
	// 설비 목록 랜더링
	var equipGroupData = GW2_CONTROLLER_SCHEDULE.equipmentGroupData;
	var isEquipCheck = false;
	tmp = [];
	tmp.push("<ul class='equipTree'>");
	// LOG(equipGroupData);


	// 설비그룹
	if (equipGroupData != null && equipGroupData.length != 0 && equipGroupData != undefined) {
		for(var i=0; i<equipGroupData.length; i++){
			tmp.push("<li><label><input type='checkbox' id='equipmentGroup' data-role='none' name='equipmentGroup' value='");
			tmp.push(equipGroupData[i].group_id);
			tmp.push("'onclick=\"javascript:selectAllGroupBox('" + equipGroupData[i].group_id + "');\"/><span>" + equipGroupData[i].title + "(" + MGW_RES.get("gw_schedule_equipmentgroup_label") + ")</span></label></li>");
			for(var j=0; j<equipGroupData[i].calendar_group_member_list.length; j++){
				for(var k=0; k<equipData.length; k++){
					if(equipData[k].calendar_id == equipGroupData[i].calendar_group_member_list[j].calendar_id){
						tmp.push("<li>");
						if(j == equipGroupData[i].calendar_group_member_list.length-1)
							tmp.push("<img src='./images/L.gif'/>");
						else
							tmp.push("<img src='./images/T.gif'/>");
						tmp.push("<label><input type='checkbox' id='equipment' data-role='none' name='equipment' value='");
						tmp.push(equipGroupData[i].calendar_group_member_list[j].calendar_id);
						tmp.push("'onclick=\"javascript:selectGroupBox('" + equipGroupData[i].calendar_group_member_list[j].calendar_id + "');\">");
						tmp.push("<div class='equip_color' style='background:" + equipData[k].ui_attr + "'></div>");
						tmp.push("<span class='equip_name'>" + htmlDecode(equipData[k].title) + "</span>");
						tmp.push("</label><br>");					
						isEquipCheck = true;
						tmp.push("</li>");
					}						
				}
			}
		}
	}
	
	// 설비분류/설비
	if (equipData != null && equipData.length != 0 && equipData != undefined) {
		var tempEquipDataSortArr = new Array();
		var tempEquipDataArr = new Array();
		
		for(var i=0; i<equipData.length; i++) {
			if (equipData[i].calendar_st == "1") {
				var isMeberGroupCheck = false;
				for(var j=0; j<equipGroupData.length; j++){
					for(var k=0; k<equipGroupData[j].calendar_group_member_list.length; k++){
						if(equipGroupData[j].calendar_group_member_list[k].calendar_id == equipData[i].calendar_id){
							isMeberGroupCheck = true;
							break;
						}
					}
					if(isMeberGroupCheck)
						break;
				}
				if(!isMeberGroupCheck && equipData[i].owner_tp != '5'){
					if(equipData[i].equipment_sort_fg == "1") {
						tempEquipDataSortArr.push(equipData[i]);
					} else {
						tempEquipDataArr.push(equipData[i]);
					}
				}
			}
		}
		
		for(var i=0; i<tempEquipDataSortArr.length; i++){
			tmp.push("<li><label><input type='checkbox' id='equipmentSort' data-role='none' name='equipmentSort' value='");
			tmp.push(tempEquipDataSortArr[i].calendar_id);
			tmp.push("'onclick=\"javascript:selectAllEquipSortBox('" + tempEquipDataSortArr[i].calendar_id + "');\"/><span>" + htmlDecode(tempEquipDataSortArr[i].title) + "</span></label></li>");
			
			var childEquipDataArr = new Array();
			for(var j=0; j<tempEquipDataArr.length; j++){
				if(tempEquipDataArr[j].par_calendar_id == tempEquipDataSortArr[i].calendar_id){
					childEquipDataArr.push(tempEquipDataArr[j]);
				}						
			}
			
			for(var j=0; j<childEquipDataArr.length; j++){
				tmp.push("<li>");
				if(j == childEquipDataArr[j].length-1)
					tmp.push("<img src='./images/L.gif'/>");
				else
					tmp.push("<img src='./images/T.gif'/>");
				tmp.push("<label><input type='checkbox' id='equipmentSortChild' data-role='none' name='equipmentSortChild' value='");
				tmp.push(childEquipDataArr[j].calendar_id);
				tmp.push("'onclick=\"javascript:selectEquipSortBox('" + childEquipDataArr[j].calendar_id + "');\">");
				tmp.push("<div class='equip_color' style='background:" + childEquipDataArr[j].ui_attr + "'></div>");
				tmp.push("<span class='equip_name'>" + htmlDecode(childEquipDataArr[j].title) + "</span>");
				tmp.push("</label><br>");						
				isEquipCheck = true;
				tmp.push("</li>");				
			}
		}		
		
	}else if(isEquipCheck){
		tmp.push("<li><label>" + MGW_RES.get("gw_msg_schedule_equip_nothing") + "</label></li>");
	}	
	tmp.push("</ul>");
	view.find("#equipmentlist").html(tmp.join(""));
	tmp = [];
	view.find("#rdoEquipY").attr("checked", "checked");
	
	// 일정알림 설정
	var alarmRuleConfig = getSchConfigValue(SCHCONFIG_ALARM_PERIOD);
	var alarmRuleConfigArr = alarmRuleConfig.split(",");
	for(i=0; i<alarmRuleConfigArr.length; i++){
		var findtag = "#alarm_rule";
		if("0mail" == alarmRuleConfigArr[i]) {
			findtag = "#alarm_rule_0mail";
		}
		view.find(findtag).append("<input type='checkbox' name='checkNoti' id='checkNoti' data-role='none' value='" + alarmRuleConfigArr[i] + "'></input><span>" + displayAlarmRule(alarmRuleConfigArr[i]) + "</span>");
	}
	
	// 일정 수정 시 기존 데이터 반영
	if (schData != undefined) {
		// LOG(schData);
		var item = schData[0];
		var description = replaceNewlineTag(item.description);
		
		view.find("#isUpdate").val(true);
		view.find('#baseDate').val(baseDate);
		view.find("#event_id").val(item.event_id);
		view.find("#title").val(item.title);
		view.find("#category").val(item.category_code);
		
		if (description != "") {
			view.find("#summary").val(description.split("<br>").join("\n"));
		}
		
		// 종일일정 체크
		var end_dt = getDateString(item.end_date);
		var end_hour = getHoursString(item.end_date);
		if(isDaySchedule(item.start_date, item.end_date)) {
			view.find("#cbDaySchedule").attr("checked", true);
			checkDaySchedule(true);
			
			end_dt = lastDay(end_dt);
			end_hour = "23";
		}
		
		view.find("#dtstart_date").val(getDateString(item.start_date));
		view.find("#txt_dtstart_date").val(getDateString(item.start_date));
		view.find("#dtstart_hour").val(getHoursString(item.start_date));
		view.find("#dtstart_time").val(getMinutesString(item.start_date, true));
		
		view.find("#dtend_date").val(end_dt);
		view.find("#txt_dtend_date").val(end_dt);
		view.find("#dtend_hour").val(end_hour);
		view.find("#dtend_time").val(getMinutesString(item.end_date, true));
		
		// 반복 일정 - dtstart_date 셋팅 후에 동작하도록 순서를 뒤로 함
		view.find('#recur_type').val(schRepeatCtrl.getDataForSelectRecurType(item.recur_type));
		schRepeatCtrl.setDataOptionsForRecurType(item.recur_type, item.recur_rule, item.recur_cycle, true);
		schRepeatCtrl.setAddedStartDate(item.start_date);
		
		// 공유자
		if (attendeeList.length != 0) {
			var targetUserList = view.find("[name=li_attendee]").find(".targetUserList");
			LOG("targetUserList=" + targetUserList);
			
			if (targetUserList.attr("style") != undefined)
				targetUserList.removeAttr("style");
			
			$(attendeeList).each(function(idx, attendee){
				targetUserList.append(renderTargetRecv("schedule", 'user', attendee.attendee_id, attendee.attendee_nm, undefined));
			})
		}
		
		// 일정 수정 시 기존 알림 데이터 체크박스 표시
		if(item.alarm_type == "1"){
			var alarm_time = "";
			alarm_time = item.alarm_rule.split(";");
			for(i=0; i<alarm_time.length; i++){
				$('[name=checkNoti]').filter('[value='+ alarm_time[i] +']').prop('checked', true);
			}
		}
		// TODO 수정
		if(typeof item.calendar_list === 'object') {
			var calList = [], calIds = [];
			for(i=0; i<item.calendar_list.length; i++) {
				calList.push("<font color='" + item.calendar_list[i].ui_attr + "'>[" + htmlDecode(item.calendar_list[i].title) + "]</font>");
				calIds.push(item.calendar_list[i].calendar_id);
			}
			tmp.push(calList.join(','));
			view.find("#calendarIds").val(calIds.join(';'));
		}
		
		isEquipCheck = false;
		if (typeof item.equipment_list == "string") {
			view.find("#rdoEquipN").attr("checked", "checked");
			toggleEquip2(false);
		}
		else {
			var equipDataLen = equipData.length;
			if (equipData != null && equipDataLen != 0 && equipData != undefined && !isEquipCheck) {
				for(var i=0; i<equipDataLen; i++) {
					// 상태(1:정상), 설비(0:설비,1:설비분류) 인 경우만 설비 목록에 표시
					var equip = equipData[i];
					if (equip.calendar_st == "1" && equip.equipment_sort_fg == "0") {
						for(var j=0; j<item.equipment_list.length; j++) {
							var itemEquip = item.equipment_list[j];
							if(itemEquip.calendar_id == equip.calendar_id) {
								$('input:checkbox[name=equipment], input:checkbox[name=equipmentSort], input:checkbox[name=equipmentSortChild]')
									.filter('[value='+ itemEquip.calendar_id +']').prop('checked', true);
							}
						}
					}
				}
			}
		}
		view.find("#calendarlist").html(tmp.join(""));
		// selectAllCalendar를 모두 체크하기 위한 로직
		var calCnt=0;
		for(var i=0; i<calData.length; i++)
			if (calData[i].write_auth == 1) calCnt++;
		if(item.calendar_list.length == calCnt) {
			GW2_CONTROLLER_SCHEDULE.selectAllCalendar = true;
		} else {
			GW2_CONTROLLER_SCHEDULE.selectAllCalendar = false;
			GW2_CONTROLLER_SCHEDULE.selectedCalendarIds = view.find("#calendarIds").val();
		}
	}else{
		// 나의달력 디폴트로 선택
		if(GW2_CONTROLLER_SCHEDULE.myCalendarId != "") {
			tmp.push("<font color='" + GW2_CONTROLLER_SCHEDULE.myCalendarColor + "'>[" + GW2_CONTROLLER_SCHEDULE.myCalendarTitle + "]</font>");	
			view.find("#calendarlist").html(tmp.join(""));
			
			view.find("#calendarIds").val(GW2_CONTROLLER_SCHEDULE.myCalendarId);
			GW2_CONTROLLER_SCHEDULE.selectedCalendarIds = GW2_CONTROLLER_SCHEDULE.myCalendarId;
		} else {
			view.find("#calendarlist").html(MGW_RES.get("gw_msg_schedule_no_select_calendar"));
		}
	}
// $('input:radio[name=equipment]').filter('[value=G00000000000000000093]').prop('checked',
// true);
}

function getTodo2Add(todoId) {
	var param = {};
	
	getCalendar2List(function() {
		// 할일 수정
		if (todoId != undefined && todoId != "") {
			changeTitle(MGW_RES.get("gw_schedule_todo_modify_label"));
			
			param["task_id"] = todoId;
			
			GW_PROXY.invokeOpenAPI("schedule", apiCode_todoDetail, param, function(todoData) {
				renderTodo2Add(todoData);
			});
		}
		// 할일 추가
		else {
			renderTodo2Add();
		}
	});
}

// calData:달력 목록, equipList:설비예약 목록
function renderTodo2Add(todoData) {
	var view = $.mobile.activePage;
	if (view.prop("id") != "sch2_todoadd")
		return;
	
	// 할일 수정 시 기존 데이터 반영
	if (todoData != undefined) {
		var description = replaceNewlineTag(todoData[0].title);
		
		view.find("#isUpdate").val(true);
		view.find("#calendar_id").val(todoData[0].calendar_id);
		view.find("#todo_id").val(todoData[0].task_id);
		
		if (description != "")
			view.find("#subject").val(description.split("<br>").join("\n").trim());
		
		view.find("#weight").val(todoData[0].importance_degree);
	}
}

function addSch2(isUpdate, isEquip) {
	var view = $.mobile.activePage;
	var param = {};
	var title = view.find("#title");
	var category_code = view.find("#category");
	var summary = view.find("#summary");
	var calendar_id = view.find("#calendarIds").val();
	var equipments_id = "";
// var equipments_group_id = "";
	var tempStartDate = view.find("#dtstart_date").val().split(".");
	var tempEndDate = view.find("#dtend_date").val().split(".");
	var attendeeRequire = scheduleAttendee.getIdValueString($(view.find("#attendee")).find("a"));;
	var alarm_type = "0";
	var alarm_rule = "";
	var isDaySchedule = false;
	
	// 일정반복
	var recurData = schRepeatCtrl.getDataSetRecur();

	if (view.find("#progressAddsch").val() == "true") {
		return;
	}
	
	if (view.find("#cbDaySchedule").is(':checked')) {
		isDaySchedule = true;
	}
	
	// 제목 입력 체크
	if (title.val().trim() == "") {
		alert(MGW_RES.get("gw_msg_common_inputsubject"));
		title.focus();
		return;
	}
	// 제목 길이 체크 (제한수 30글자)
	else if (title.val().trim().length > 30) {
		alert(MGW_RES.get("gw_msg_common_toolong_msg"));
		title.focus();
		return;
	}
	// 본문 길이 체크 (제한수 500글자)
	else if (summary.val().trim().length > 500) {
		alert(MGW_RES.get("gw_msg_common_toolong_summary"));
		summary.focus();
		return;
	}
	// 제목 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter2(title.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		title.focus();
		return;
	}
	// 본문 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter2(summary.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		summary.focus();
		return;
	}
	// 주간일 경우 요일선택 체크
	else if(recurData.recurType === "3" && !recurData.recurRule) {
		alert(MGW_RES.get("gw_msg_schedule_recur_type3_daywk"));
		return;
	}
	// 시작일이 종료일 보다 나중인지 체크
	else if (!checkDateTimePeriod(tempStartDate[0], tempStartDate[1], tempStartDate[2], view.find("#dtstart_hour").val(), view.find("#dtstart_time").val(),
			tempEndDate[0], tempEndDate[1], tempEndDate[2], view.find("#dtend_hour").val(), view.find("#dtend_time").val(), 'true')) {
		alert(MGW_RES.get("gw_msg_schedule_period_err"));
		return;
	}
	// 시작과 종료 시간은 최소 30분의 차이가 있는지 체크
	else if (recurData.recurType === "0") {
		if (!checkDateTimePeriodWithTimeGap(tempStartDate[0], tempStartDate[1], tempStartDate[2], view.find("#dtstart_hour").val(), view.find("#dtstart_time").val(),
				tempEndDate[0], tempEndDate[1], tempEndDate[2], view.find("#dtend_hour").val(), view.find("#dtend_time").val(), 1800000)) {
			alert(MGW_RES.get("gw_msg_schedule_period_timegap_err"));
			return;
		}
	}
	else if (recurData.recurType != "0") {
		if (!checkDateTimePeriodWithTimeGap(tempStartDate[0], tempStartDate[1], tempStartDate[2], view.find("#dtstart_hour").val(), view.find("#dtstart_time").val(),
				tempStartDate[0], tempStartDate[1], tempStartDate[2], view.find("#dtend_hour").val(), view.find("#dtend_time").val(), 1800000)) {
			alert(MGW_RES.get("gw_msg_schedule_period_timegap_err"));
			return;
		}
	}
	// 일정 등록시, 달력 선택 체크
	else if(view.find("#rdoEquipN").is(':checked') && calendar_id == ""){
		alert(MGW_RES.get("gw_msg_schedule_check_calendar"));
		return;
	}
	
	if(view.find("#rdoEquipY").is(':checked')){
		equipments_id = schEquipmentCtrl.getCheckedEquipments();
		if(!equipments_id){
			alert(MGW_RES.get("gw_msg_schedule_check_equip"));
			return;
		}
	}
	
	// 알림 시간 체크박스에 체크한 것들 alarm_rule에 저장
	view.find('[name=checkNoti]:checked').each(function(){
		alarm_type = "1";
		alarm_rule = alarm_rule + $(this).val() + ";";
	});	
	var start_dt = view.find("#dtstart_date").val() + " " + view.find("#dtstart_hour").val() + ":" + view.find("#dtstart_time").val() + ":00";
	var end_dt = view.find("#dtend_date").val() + " " + view.find("#dtend_hour").val() + ":" + view.find("#dtend_time").val() + ":00";
	
	if(isDaySchedule) {
		start_dt = start_dt.substring(0, 10) + " " + "00:00:00";
		end_dt = nextDay(end_dt);
		end_dt = end_dt.substring(0, 10) + " " + "00:00:00";
	}
	
	param["method"] = isUpdate? "update" : "add";
	if(isUpdate) {
		param["UPDATE_OPT"] = view.find('#schRecurModifyDialog input:radio[name=rdoSchRecurModifyOption]:checked').val()||"0";
		var hhmmdd = schRepeatCtrl.getAddedStartDate().split(/\s/)[1]+SETTING_SECOND;
		param["SELECT_DT"] = view.find('#baseDate').val() + " " + hhmmdd;
	}
	param["TITLE"] = title.val().trim();
	param["CATEGORY_CODE"] = category_code.val();
	param["EVENT_ID"] = isUpdate ? view.find("#event_id").val() : "";
	param["START_DT"] =  start_dt;
	param["END_DT"] = end_dt;
	param["ANNIVERSARY_FG"] = "0";
	param["EQUIPMENT_GROUP"] = equipments_id;
	param["EQUIPMENT"] = equipments_id;
	param["CALENDAR_ID"] = calendar_id;
	param["DESCRIPTION"] = summary.val().trim();
	param["RECUR_TYPE"] = recurData.recurType;
	param["RECUR_RULE"] = recurData.recurRule;
	param["RECUR_CYCLE"] = recurData.recurCycle;
	param["ATTENDEE_REQUIRE"] = attendeeRequire;
	param["ALARM_TYPE"] = alarm_type;
	param["ALARM_RULE"] = alarm_rule;
	param["APPLICATION_ID"] = "Z0000000000000000005";
	param["REPLY_CNT"] = "0";
	param["CHECKLUNAR"] = "False";
	param["ORGFILENAME"] = null;
	param["SYSFILENAME"] = null;
	param["DELSEQ"] = null;
	
	view.find("#progressAddsch").val("true");

	GW_PROXY.invokeOpenAPI("schedule", "schadd", param, function(data) {
		if (data == [] || data.status == "0") {
			alert(MGW_RES.get("gw_msg_common_success"));
			
			if (GWPlugin.usePlugin) {	
				if (isEquip) {
					GWPlugin.closePopupViewer("javascript:showSch2List('" + apiCode_equipList + "', '" + view.find("#dtstart_date").val() + "');", true);
				}
				else {
					GWPlugin.closePopupViewer("javascript:showSch2List('" + apiCode_schList + "', '" + view.find("#dtstart_date").val() + "');", true);
				}
			}
			else {
				if (isEquip) {
					showSch2List(apiCode_equipList, view.find("#dtstart_date").val());
				}
				else {
					showSch2List(apiCode_schList, view.find("#dtstart_date").val());
				}
			}
			GW2_CONTROLLER_SCHEDULE.selectedCalendarIds = "";
		} 
	}, function(data){
		if(isEquip && data.code == GW_PROXY.ERROR_GW_DUPLICATE_ERROR){
			alert(MGW_RES.get("gw_msg_schedule_check_reserve_equip"));
		} else {
			alert(MGW_RES.get("gw_msg_common_err"));
		}
	});
}
// 저장/수정
function checkReserveEquip2(isUpdate){
	var view = $.mobile.activePage;
	var isUpdate = view.find("#isUpdate").val()||isUpdate||false;
	// 수정이고, 원데이터가 반복 이면
	if(isUpdate && schRepeatCtrl.getAddedRecurType() != "0") {
		// 반복일때 show edit dialog
		var dialog = view.find("#schRecurModifyDialog"); 
		dialog.popup("open");
		dialog.find("input:radio[name=rdoSchRecurModifyOption]:first").attr("checked", true);
	} else {
		doSaveCheckReserveEquip2(isUpdate);
	}
}
function saveSchRecurModifyDialog(){
	doSaveCheckReserveEquip2(true);
}
function closeSchRecurModifyDialog(){
	closeDialog("schRecurModifyDialog");
}
function doSaveCheckReserveEquip2(isUpdate) {
	var view = $.mobile.activePage;
	var isDaySchedule = false;
	var param = {};
	
	if (view.prop("id") != "sch2_schadd"){
		return;
	}
	
	if (view.find("#cbDaySchedule").is(':checked')) {
		isDaySchedule = true;
	}
	if (view.find("#rdoEquipN").is(':checked')) {
		addSch2(isUpdate, false);
		return;
	}
	else {
		var equipments_id = schEquipmentCtrl.getCheckedEquipments();
		if (!equipments_id) {
			addSch2(isUpdate, false);
			return;
		}
		
		var start_dt = view.find("#dtstart_date").val() + " " + view.find("#dtstart_hour").val() + ":" + view.find("#dtstart_time").val() + ":00";
		var end_dt = view.find("#dtend_date").val() + " " + view.find("#dtend_hour").val() + ":" + view.find("#dtend_time").val() + ":00";
		if(isDaySchedule) {
			start_dt = start_dt.substring(0, 10) + " " + "00:00:00";
			end_dt = end_dt.substring(0, 10) + " " + "00:00:00";
		}
		
		// 일정반복
		var recurData = schRepeatCtrl.getDataSetRecur();
		param["EQUIPMENT"] = equipments_id;
		param["TARGET_START_DATE"] = start_dt;
		param["TARGET_END_DATE"] = end_dt;
		param["RECUR_TYPE"] = recurData.recurType;
		param["RECUR_RULE"] = recurData.recurRule;
		param["RECUR_CYCLE"] = recurData.recurCycle;
		param["EVENT_ID"] = isUpdate ? view.find("#event_id").val() : "";
		if(isUpdate) {
			param["UPDATE_OPT"] = view.find('#schRecurModifyDialog input:radio[name=rdoSchRecurModifyOption]:checked').val()||"0";
			var hhmmdd = schRepeatCtrl.getAddedStartDate().split(/\s/)[1]+SETTING_SECOND;
			param["SELECT_DT"] = view.find('#baseDate').val() + " " + hhmmdd;
		}
		GW_PROXY.invokeOpenAPI("schedule", "checkequip", param, function(data) {
			// LOG(data);
			var equipIdList = data||null;
			
			if (!equipIdList || equipIdList.length == 0) {
				addSch2(isUpdate, true);
				return;
			} else {
				var equipName = "";
				// 8.3.12 변경되면서 아래 케이스는 발생하지 않을것으로 보이나, 케이스 추적불가로 남겨둠
				if (typeof equipIdList == "string") {
					equipName = "[" + getEquip2Name(GW2_CONTROLLER_SCHEDULE.equipmentData, equipIdList) + "]";
				}
				else {
					if (equipIdList.length == undefined) {
						equipName = "[" + getEquip2Name(GW2_CONTROLLER_SCHEDULE.equipmentData, equipIdList.calendar_id) + "]";
					}
					else {
						for(var i=0; i<equipIdList.length; i++) {
							equipName += "[" + getEquip2Name(GW2_CONTROLLER_SCHEDULE.equipmentData, equipIdList[i].calendar_id) + "]";
						}
					}
				}
				alert(equipName + " " + MGW_RES.get("gw_msg_schedule_check_reserve_equip"));
			}
		});
		
	}
}
function deleteSch2() {
	var view = $.mobile.activePage;	
	var param = {};
	
	if (!(view.prop("id") == "sch2_schdetail" || view.prop("id") == "sch2_equipdetail"))
		return;
	
	var recurType = view.find("#recur_type").val();
	
	// 반복없음
	if(recurType == "0") {
		callDeleteSch2("0");
	} else {
		var dialog = view.find("#selectSchDeleteRecurDialog"); 
		dialog.popup("open");
		dialog.find("input:radio[name=rdoSchDeleteRecurOption]:first").attr("checked", true);
	}
}

function callDeleteSch2(delOpt) {
	var view = $.mobile.activePage;	
	var param = {};
	
	if (!(view.prop("id") == "sch2_schdetail" || view.prop("id") == "sch2_equipdetail"))
		return;	

	var startDt = view.find("#baseDate").val() + " 00:00:00";
	var eventId = view.find("#event_id").val();

	param["EVENT_ID"] = eventId;
	param["SELECT_DT"] = startDt;
	param["DEL_OPT"] = delOpt;
	
	if (confirm(MGW_RES.get("gw_msg_common_confirm_delete"))) {
		GW_PROXY.invokeOpenAPI("schedule", "schdelete", param, function(data) {
			if (data.status == "0" || data.status == "success") {
				alert(MGW_RES.get("gw_msg_mail_delete_success"));
				PAGE_CONTROLLER.goBack(true);
			}
		});
	}	
}

function setSchDeleteRecurOption() {
	var view = $.mobile.activePage;	
	var delOpt = view.find('input:radio[name="rdoSchDeleteRecurOption"]:checked').val();
	if(delOpt == undefined) {
		alert(MGW_RES.get("gw_schedule_delete_recur_option_select"));
	} else {
		callDeleteSch2(delOpt);		
	}
}

function closeSelectSchDeleteRecurDialog() {
	closeDialog("selectSchDeleteRecurDialog");
}

function addTodo2() {
	var view = $.mobile.activePage;
	
	if (view.prop("id") != "sch2_todoadd")
		return;
	
	var param = {};
	var isUpdate = view.find("#isUpdate").val()||false;
	var subject = view.find("#subject");
	
	if (view.find("#progressAddtodo").val() == "true") {
		return;
	}
	// 공백 확인
	if (subject.val().trim() == "") {
		alert(MGW_RES.get("gw_msg_mail_inputbody"));
		subject.focus();
		return;
	}
	// 내용 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter2(subject.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		subject.focus();
		return;
	}
	// 제목 길이 체크 (제한수 50글자)
	else if (subject.val().trim().length > 50) {
		alert(MGW_RES.get("gw_msg_common_toolong_summary"));
		title.focus();
		return;
	}
	
	param["method"] = isUpdate ? "update" : "create";
	param["subject"] = subject.val().trim();
	param["task_application_at"] = 1;
	param["importance_degree"] = view.find("#weight").val();
	param["calednar_id"] = "";
	param["writer_id"] = "";
	param["thema"] = "";
	param["content"] = "";
	param["progress_rate"] = 0;
	param["begin_date"] = "";
	param["end_date"] = "";
	param["task_id"] = isUpdate ? view.find("#todo_id").val() : "";
	param["calendar_id"] = isUpdate ? view.find("#calendar_id").val() : "";
	
	view.find("#progressAddtodo").val("true");
	
	GW_PROXY.invokeOpenAPI("schedule", "todoadd", param, function(data) {
		if (data.status == "200") {
			alert(MGW_RES.get("gw_msg_common_success"));

			if (GWPlugin.usePlugin) {
					GWPlugin.closePopupViewer("javascript:showTodo2List('" + apiCode_todoList + "', '" + view.find("#dtstart_date").val() + "');", true);
			}
			else {
				showSch2List(apiCode_todoList, view.find("#dtstart_date").val());
			}
		}

	});
}

function deleteTodo2() {
	var view = $.mobile.activePage;	
	var param = {};
	
	if (view.prop("id") != "sch2_tododetail")
		return;
	
	var calendar_id = view.find("#calendar_id").val();
	var taskId = view.find("#todo_id").val();
	
	param["task_ids"] = taskId;
	param["calendar_id"] = calendar_id;
// param["DEL_OPT"] = "1";
	
	if (confirm(MGW_RES.get("gw_msg_common_confirm_delete"))) {
		GW_PROXY.invokeOpenAPI("schedule", "tododelete", param, function(data) {
			LOG(data);
			if (data.status == "200" || data.status == "success") {
				alert(MGW_RES.get("gw_msg_mail_delete_success"));
				PAGE_CONTROLLER.goBack(true);
			}
		});
	}
}

function toggleEquip2(flag) {
	var view = $.mobile.activePage;	
	if (flag) {
		view.find("#equipmentlist").removeAttr("style");
	}
	else {
		view.find("#equipmentlist").attr("style", "display:none;");
	}
}

function cancel_addSch2(type) {
	var cancelMsg; 
	if (type=="todo")
		cancelMsg = MGW_RES.get("gw_msg_schedule_cancel_add_todo");
	else 
		cancelMsg = MGW_RES.get("gw_msg_schedule_cancel_add_schedule");
	
	if (confirm(cancelMsg)) {
		GW2_CONTROLLER_SCHEDULE.selectAllCalendar = false;
		GW2_CONTROLLER_SCHEDULE.selectedCalendarIds = "";
		if (GWPlugin.usePlugin) { // APP, popup인 경우
			GWPlugin.closePopupViewer("", false);
		}
		else {
			PAGE_CONTROLLER.goBack();
		}
	}
}

function isValidLetter2(src) {
	if ( src.indexOf("'") >= 0 ){
		return false;
	}
	else if ( src.indexOf('"') >= 0 ){
		return false;
	}
	else if ( src.indexOf('\\') >= 0 ){
		return false;
	}
	
	return true;
}
/*******************************************************************************
 * Schedule / Todo Add&Edit&Delete END *
 ******************************************************************************/



/*******************************************************************************
 * Schedule / Todo Search Function Start
 ******************************************************************************/
function getSch2SearchTab() {
	if ($.mobile.activePage.prop("id") != "sch2_schsearch") {
		return;
	}
	
	getCalendar2List(function() {
		getEquipment2List(function() {
			renderSch2Search(GW2_CONTROLLER_SCHEDULE.calendarData, GW2_CONTROLLER_SCHEDULE.equipmentData);
		});
	});
}

function renderSch2Search(calData, equipData) {
	var view = $.mobile.activePage;
	if (view.prop("id") != "sch2_schsearch") {
		return;
	}
	
	// 일정 유형 options 생성
	$(GW2_CONTROLLER_SCHEDULE.schCategoryData).each(function(idx, data){ 
		view.find("#category").append($("<option>", { value: data.category_code, html: data.category_name }));
	});
	
	var calOption = view.find("#calendar_id");
	var equipOption = view.find("#equipment_id");
	
	var equipmentIds = "";
	var calendarIds = "";
	
	view.find("#dtstart_date").val(getToday());
	view.find("#dtend_date").val(getToday());
	view.find("#txt_dtstart_date").val(getToday());
	view.find("#txt_dtend_date").val(getToday());
	
	// 달력
	if (calData.length != 0) {
		for(var i=0; i<calData.length; i++) {
			calOption.append("<option value='" + calData[i].calendar_id + "'>" + htmlDecode(calData[i].title) + "</option>");
			calendarIds += calData[i].calendar_id + ";";
		}
	}
	view.find("#calendarIds").val(calendarIds);
	
	// 설비
	if(equipData != undefined) {
		for(var i=0; i<equipData.length; i++) {
			if (equipData[i].calendar_st == "1" && equipData[i].equipment_sort_fg == "0") {
				equipOption.append("<option value='" + equipData[i].calendar_id + "'>" + htmlDecode(equipData[i].title) + "</option>");
				equipmentIds += equipData[i].calendar_id + ";";
			}
		}
	}
	// LOG("calendarIDS : " + calendarIds + "\nequipmentIDS : " + equipmentIds);
	view.find("#equipmentIds").val(equipmentIds);
	selectSearchTab2('sch');
}

function selectSearchTab2(tab) {
	var view = $.mobile.activePage;
	var currentTab = view.find("#currentTab").val();
	
	var searchOption = view.find("#list").find("[name=searchOption]");
	var equipOption = view.find("#list").find("[name=equipOption]");
	var calOption = view.find("#list").find("[name=calOption]");
	var statusOption = view.find("#list").find("[name=statusOption]");
	var weightOption = view.find("#list").find("[name=weightOption]");
	
	if (currentTab == tab)
		return;
	
	if (tab == "todo") {
		view.find("#currentTab").val("todo");
		view.find("#schTab").removeClass("ui-btn-active");
		view.find("#todoTab").addClass("ui-btn-active");
		
		view.find("#calTitle").text(MGW_RES.get("gw_schedule_all_calendar_label"));
		searchOption.hide();
		calOption.show();
		equipOption.hide();
		
		statusOption.show();
		weightOption.show();
	}
	else {
		view.find("#currentTab").val("sch");
		view.find("#todoTab").removeClass("ui-btn-active");
		view.find("#schTab").addClass("ui-btn-active");
		
		view.find("#calTitle").text("");
		searchOption.show();
		equipOption.hide();
		calOption.hide();
		
		statusOption.hide();
		weightOption.hide();
	}
	
	view.find("#select_option").val('3');
	view.find("#calendar_id").val('0');
	view.find("#equipment_id").val('allequip');
	view.find("#status").val('0');
	view.find("#weight").val('0');
}
function searchSch2() {
	var view = $.mobile.activePage;
	var param = {};
	var noCheckValue = "00000000000000000000";
	var currentTab = view.find("#currentTab").val();
	var title = view.find("#title");
	var category_code = view.find("#category").val();
	// 사용자가 선택한 옵션, 설비, 달력 Value
	var selectOption = view.find("#select_option").val();
	var calendar_id = view.find("#calendar_id").val();
	var equipment_id = view.find("#equipment_id").val();
	// 전체 설비, 전체 달력에 해당하는 Value
	var calendarIds = view.find("#calendarIds").val();
	var equipmentIds = view.find("#equipmentIds").val();
	var method = "";
	
	// 제목 입력 체크
	if (title.val().trim() == "" && !view.find("#notitle").is(':checked')) {
		alert(MGW_RES.get("gw_msg_common_inputsubject"));
		title.focus();
		return;
	}
	// 제목 길이 체크 (제한수 30글자)
	else if (title.val().trim().length > 30) {
		alert(MGW_RES.get("gw_msg_common_toolong_msg"));
		title.focus();
		return;
	}
	// 제목 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter2(title.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		title.focus();
		return;
	}
	
	// 제목
	if (view.find("#notitle").is(':checked')) {
		param["TITLE"] = "";
	} 
	else {
		param["TITLE"] = title.val().trim();
	}
	
	// 시작일, 종료일
	var start_dt = view.find("#dtstart_date").val();
	var end_dt = view.find("#dtend_date").val();
	
	// 일정, 할일 탭에 따른 기본 데이터 설정
	if (currentTab == "todo") {
		method = "getSearchList";
		selectOption = "2";
	} 
	else {
		method = "getSearchList";
	}
	
	/*
	 * 사용자 선택 옵션에 따른 데이터 설정 selectOption value 1:설비, 2:달력, 3:전체, 4:공유일정
	 */
	if (selectOption == "1") {
		calendarIds = noCheckValue;
		// 전체 설비가 아닌 경우
		if (equipment_id != "allequip") {
			equipmentIds = equipment_id;
		}
	}
	else if (selectOption == "2") {
		if(calendar_id != "0"){
			calendarIds = calendar_id;
		}
		equipmentIds = noCheckValue;
		
	}
	
	param["CATEGORY_CODE"] = category_code;
	param["method"] = method;
	param["START_DT"] = start_dt;
	param["END_DT"] = end_dt;
	param["CATEGORY"] = "";
	param["SELECT_OPTION"] = selectOption;
	
	param["EQUIPMENT_IDS"] = equipmentIds;
	param["CALENDAR_IDS"] = calendarIds;
	
	showDetailList2("sch", selectOption, param);
}

function renderDetailList2(currentTab, schData, calData, equipData) {
	// LOG(schData);
	// LOG(calData);
	// LOG(equipData);
	var view = $.mobile.activePage;	
	var list = view.find("#list");

	view.find("#divList").attr("class","divListNormal");
	view.find("#divTitle").attr("class","divListNormal");
	
	if (schData.length != 0) {
		for(var i = 0; i< schData.length;i++){		
			var item = schData[i];
			var tmp = [];
			
			tmp.push("<li>");
			
			if (calData != undefined) {
				for(var j=0; j<calData.length; j++) {
					if(item.calendar_id == SHARED_CALENDAR_ID){
						tmp.push("<a href=\"javascript:sch2Detail('" + item.searchId + "', 'sch_detaillist_sch', '" + item.owner_id + "');\">");
						tmp.push("<div class='grouping_middle'>");
						tmp.push("<div class='calendarName'>");
						tmp.push("<font color='#0372FB'>[" + MGW_RES.get("gw_schedule_calendar_label") + "] " + MGW_RES.get("gw_schedule_shared_calendar_label") +"</font></div>");
						break;
					}
					if (item.calendar_id == calData[j].calendar_id) {
						if (currentTab == "sch") {
							tmp.push("<a href=\"javascript:sch2Detail('" + item.searchId + "', 'sch_detaillist_sch', '" + item.owner_id + "');\">");
							tmp.push("<div class='grouping_middle'>");
							tmp.push("<div class='calendarName'>");
						}
						else if (currentTab == "todo") {
							var calColor = calData[j].ui_attr.replace("#", "");
							
							tmp.push("<a href=\"javascript:todo2Detail('" + item.searchId + "', '" + item.owner_id + "', '" + calData[j].title + "', '" + calColor + "', 'sch_detaillist');\">");
							tmp.push("<div class='grouping_middle'>");
							tmp.push("<div class='calendarName'>");
						}
						tmp.push("<font color='" + calData[j].ui_attr + "'>[" + MGW_RES.get("gw_schedule_calendar_label") + "] " + htmlDecode(calData[j].title) +"</font></div>");
					}
				}
			}
			if (equipData != undefined) {
				for(var j=0; j<equipData.length; j++) {
					if(item.calendar_id == SHARED_CALENDAR_ID){
						tmp.push("<a href=\"javascript:sch2Detail('" + item.searchId + "', 'sch_detaillist_equip', '" + item.owner_id + "');\">");
						tmp.push("<div class='grouping_middle'>");
						tmp.push("<div class='calendarName'>");
						tmp.push("<font color='#0372FB'>[" + MGW_RES.get("gw_schedule_calendar_label") + "] " + MGW_RES.get("gw_schedule_shared_calendar_label") +"</font></div>");
						break;
					}
					if (item.calendar_id == equipData[j].calendar_id) {
						if (currentTab == "sch") {
							tmp.push("<a href=\"javascript:sch2Detail('" + item.searchId + "', 'sch_detaillist_equip', '" + item.owner_id + "');\">");
							tmp.push("<div class='grouping_middle'>");
							tmp.push("<div class='calendarName'>");
						}
						
						tmp.push("<font color='" + equipData[j].ui_attr + "'>[" + MGW_RES.get("gw_schedule_equipment_label") + "] " + htmlDecode(equipData[j].title) +"</font></div>");
					}
				}
			}
			if(equipData == undefined && calData == undefined){	// selectOption
																// = 4 (공유일정)
				/*
				 * 공유일정은 그룹웨어에서도 하드코딩되어있다. 명칭 : 공유일정 색상값 : #0372FB calendarId :
				 * 99999999999999999999
				 */
				tmp.push("<a href=\"javascript:sch2Detail('" + item.searchId + "', 'sch_detaillist_sch', '" + item.owner_id + "');\">");
				tmp.push("<div class='grouping_middle'>");
				tmp.push("<div class='calendarName'>");
				tmp.push("<font color='#0372FB'>[" + MGW_RES.get("gw_schedule_calendar_label") + "] " + MGW_RES.get("gw_schedule_shared_calendar_label") +"</font></div>");
			}
			
			tmp.push("<h3 class='title'>"+htmlDecode(item.title)+"</h3>");
			tmp.push("<div class='dateInfo'>" + item.start_dt + " ~ " + item.end_dt + "</div>");
			tmp.push("</div></a></li>");
			list.append(tmp.join(""));
		}
	}
	else {
		var tmp = [];
		
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
		list.html(tmp.join(""));
	}
	
	list.listview("refresh");
}


// 검색 - 기간 설정
function setPeriod2(range) {
	var view = $.mobile.activePage;
	var startDate = view.find("#dtstart_date");
	var endDate = view.find("#dtend_date");
	var today = getToday();
	var newDate = "";
	
	switch(range) {
		case '1':	// 당일
			newDate = today;
			break;
		case '2':	// 7일
			newDate = getPeriod(0, 0, 7);
			break;
		case '3':	// 1달
			newDate = getPeriod(0, 1, 0);
			break;
		case '4':	// 3달
			newDate = getPeriod(0, 3, 0);
			break;
	}
	
	startDate.val(newDate);
	endDate.val(today);
}

function toggleOption2(option) {
	var view = $.mobile.activePage;
	var equipOption = view.find("#list").find("#equipment_id");
	var calOption = view.find("#list").find("#calendar_id");
	
	switch (option) {
	case '1': // 설비
		equipOption.show();
		calOption.hide();
		break;
	case '2': // 달력
		equipOption.hide();
		calOption.show();
		break;
	case '3': // 전체
		equipOption.hide();
		calOption.hide();
		break;
	case '4': // 공유 일정
		equipOption.hide();
		calOption.hide();
		break;
	}
}

// 검색 - 제목없음 토글
function toggleNoTitle2() {
	var view = $.mobile.activePage;
	var notitle = view.find("#notitle");
	var title = view.find("#title");
	
	if (notitle.is(':checked')) {
		title.attr("disabled", true);
	}
	else {
	    title.attr("disabled", false);
	}
}
/*******************************************************************************
 * Schedule / Todo Search Function End
 ******************************************************************************/



/*******************************************************************************
 * Equipment / Calendar Function Start
 ******************************************************************************/
// 설비 목록에서 설비 분류 제거 후 설비 str, id, color Array 반환
function getAllEquip2List(data) {
	var equipTitleList = new Array();
	var parCalendarIdList = new Array();
	
	// 상위 트리인지 분석
	var isParEquip = true;
	var isEquip = false;
	var index = 0;
		
	for(var i=0; i<data.length; i++) {
		if (data[i].equipment_sort_fg == 1) {
			parCalendarIdList[index++] = data[i].calendar_id;
		}
	}
			
	// 각 설비의 타이틀명들을 equipTitleList에 저장
	index = 0;
			
	for(var i=0; i<data.length; i++) {
		isParEquip = true;
		isEquip = false;
		
		for(var j=0; j<parCalendarIdList.length; j++) {
			if (parCalendarIdList[j] == data[i].calendar_id) {
				isParEquip = false;
			}
			if (parCalendarIdList[j] == data[i].par_calendar_id) {
				isEquip = true;
			}
		}
				
		if (isParEquip && isEquip) {
			equipTitleList[index] = new Array();
			equipTitleList[index][0] = data[i].calendar_id;
			equipTitleList[index][1] = htmlDecode(data[i].title);
			equipTitleList[index][2] = data[i].ui_attr;
			equipTitleList[index++][3] = 0;
		}
	}
	return equipTitleList;
}

function getEquip2Name(equipData, calendar_id) {
	for(var i=0; i<equipData.length; i++) {
		if (equipData[i].calendar_id == calendar_id){
			return equipData[i].title;
		}
	}
}
/*******************************************************************************
 * Equipment / Calendar Function End
 ******************************************************************************/
// 일정추가/설비예약
$("#sch2_schadd #set_dtstart_date").live("click", function() {
	$("#sch2_schadd #dtstart_date").datebox("open");
});
$("#sch2_schadd #set_dtend_date").live("click", function() {
	$("#sch2_schadd #dtend_date").datebox("open");
});

// 할일추가
$("#sch_todoadd #set_dtstart_date").live("click", function() {
	$("#sch_todoadd #dtstart_date").datebox("open");
});
$("#sch_todoadd #set_dtend_date").live("click", function() {
	$("#sch_todoadd #dtend_date").datebox("open");
});

// 일정검색
$("#sch2_schsearch #set_dtstart_date").live("click", function() {
	$("#sch2_schsearch #dtstart_date").datebox("open");
});
$("#sch2_schsearch #set_dtend_date").live("click", function() {
	$("#sch2_schsearch #dtend_date").datebox("open");
});

function changeEndDate2() {
	var txt_dtend_date = $.mobile.activePage.find("#txt_dtend_date");
	var dtend_date =  $.mobile.activePage.find("#dtend_date");
	
	if (txt_dtend_date != undefined && dtend_date != undefined) {
		txt_dtend_date.val(dtend_date.val());
	}
}

function changeStartDate2() {
	var view = $.mobile.activePage;
	var txt_dtstart_date = view.find("#txt_dtstart_date");
	var dtstart_date =  view.find("#dtstart_date");
	
	if (txt_dtstart_date != undefined && dtstart_date != undefined) {
		txt_dtstart_date.val(dtstart_date.val());
	}
	
	setAutoChangeEndDateAndTime();
	schRepeatCtrl.changeRecurType(view.find('#recur_type'));
}
function changeStartHour2() {
	setAutoChangeEndDateAndTime();
}

function setAutoChangeEndDateAndTime() {
	var view = $.mobile.activePage;
	
	var startDateString = view.find("#dtstart_date").val() + " " + view.find("#dtstart_hour").val() + ":" + view.find("#dtstart_time").val();
	var endDateString = view.find("#dtend_date").val() + " " + view.find("#dtend_hour").val() + ":" + view.find("#dtend_time").val();

	if(stringToDateTime(endDateString).getTime() < stringToDateTime(startDateString).getTime()) {
		var endDate = new Date(Date.parse(stringToDateTime(startDateString)) + 1000 * 60 * 60);
		LOG("setAutoChangeEndDateAndTime : endDate=" + dateToStringWithTime(endDate));
		
		view.find("#dtend_date").val(dateToString(endDate));
		view.find("#txt_dtend_date").val(dateToString(endDate));
		view.find("#dtend_hour").val(("00" + endDate.getHours()).slice(-2));
		view.find("#dtend_time").val(("00" + endDate.getMinutes()).slice(-2));		
	}
}

function dateToStringWithTime(d) {
	return	d.getFullYear() + "." +
	("00" + (d.getMonth() + 1)).slice(-2) + "." + 
	("00" + d.getDate()).slice(-2) + " " + 
    ("00" + d.getHours()).slice(-2) + ":" + 
    ("00" + d.getMinutes()).slice(-2);	
}
function changeRecurType(obj){
	schRepeatCtrl.changeRecurType(obj);
}

/**
 * 달력 멀티 체크 리스트
 */
function showSelectCalendar2() {
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();	
	var baseDate = view.find("#baseDate").text();
	
	PAGE_CONTROLLER.showPage("sch2_selectcalendar", function() {
		var view = $.mobile.activePage;
		var list = view.find("#list");
		var tmp = [];

		view.find("#dateMode").val(dateMode);
		view.find("#baseDate").val(baseDate);
		
		tmp.push("<li onclick=\"javascript:selectAllCalendar2();\"><div class='grouping_left'>");
		tmp.push("<input type='checkbox' data-role='none' id='checkAll' onclick='javascript:selectAllCalendar2();'");
		
		if (GW2_CONTROLLER_SCHEDULE.selectAllCalendar)
			tmp.push(" checked='checked'");
		
		tmp.push("></div><div class='grouping_middle'><h3>");
		tmp.push(MGW_RES.get("gw_schedule_select_allcallist"));
		tmp.push("</h3></div></li>");
		var calendarData = GW2_CONTROLLER_SCHEDULE.calendarData;
		for(var i=0; i<calendarData.length; i++) {
			if(calendarData[i].write_auth != 0){
				tmp.push("<li onclick=\"javascript:selectCalendar2('" + calendarData[i].calendar_id + "');\"><div class='grouping_left'>");
				tmp.push("<input type='checkbox' data-role='none' name='chkCalendar' id='" + calendarData[i].calendar_id + "'");
				tmp.push(" onclick=\"javascript:selectCalendar2('" + calendarData[i].calendar_id + "');\"");
				
				if (GW2_CONTROLLER_SCHEDULE.selectAllCalendar) {
					tmp.push(" checked='checked'");
				} else {
					var idArr =  GW2_CONTROLLER_SCHEDULE.selectedCalendarIds.split(";");
					for (var j=0; j<idArr.length; j++) {
						if (idArr[j] != "" && idArr[j] == calendarData[i].calendar_id) {
							tmp.push(" checked='checked'");
						}	
					}
				}
				
				tmp.push(">");
						
				if (calendarData[i].ui_attr != undefined)
					tmp.push("<div class='equip_color' style='background:" + calendarData[i].ui_attr + "'></div>");
				
				tmp.push("</div><div class='grouping_middle'><h3>");
				tmp.push(htmlDecode(calendarData[i].title));
				tmp.push("</h3></div></li>");
			}
		}
		
		list.html(tmp.join(""));
		list.listview("refresh");
	});	
}

function selectAllCalendar2() {
	var view = $.mobile.activePage;
	var c = view.find('#checkAll').attr('checked');

	if (c != 'checked'){
		view.find('#checkAll').attr('checked', 'checked');
		view.find('input[name=chkCalendar]').attr('checked', 'checked');
	}
	else {	
		view.find('#checkAll').removeAttr('checked');
		view.find('input[name=chkCalendar]').removeAttr('checked');
	}
}

function selectCalendar2(id) {
	var view = $.mobile.activePage;
	var chk = view.find("#list li").find("[name=chkCalendar]").filter("#" +id);
	
	if (chk.attr("checked") != "checked") {
		chk.attr("checked", "checked");
	}
	else {
		chk.removeAttr("checked");
	}
	
	view.find('#checkAll').removeAttr('checked');
}

function completeSelectCalendar2() {
	var view = $.mobile.activePage;
	var calendarData = GW2_CONTROLLER_SCHEDULE.calendarData;

	if (view.find('#checkAll').attr('checked') == "checked") {
		GW2_CONTROLLER_SCHEDULE.selectAllCalendar = true;
	}
	else {
		GW2_CONTROLLER_SCHEDULE.selectAllCalendar = false;
		GW2_CONTROLLER_SCHEDULE.selectedCalendarIds = "";
		
		view.find("#list li").find("[name=chkCalendar]:checked").each(function(){
			GW2_CONTROLLER_SCHEDULE.selectedCalendarIds += $(this).prop("id") + ";";
		});
	}
	
	cancelSelectCalendar2();
	view = $.mobile.activePage;
	
	var tmp = [];
	var calendarIds = "";
	
	for(var i=0; i<calendarData.length; i++) {
		if (GW2_CONTROLLER_SCHEDULE.selectAllCalendar) {
			if(calendarData[i].write_auth == 1){
				if(i==0)
					tmp.push("<font color='" + calendarData[i].ui_attr + "'>[" + htmlDecode(calendarData[i].title) + "]</font>");
				else
					tmp.push(", <font color='" + calendarData[i].ui_attr + "'>[" + htmlDecode(calendarData[i].title) + "]</font>");
				calendarIds += calendarData[i].calendar_id + ";";
			}			
		}else{
			var idArr =  GW2_CONTROLLER_SCHEDULE.selectedCalendarIds.split(";");
			for (var j=0; j<idArr.length; j++) {
				if (idArr[j] != "" && idArr[j] == calendarData[i].calendar_id) {
					if(j==0)
						tmp.push("<font color='" + calendarData[i].ui_attr + "'>[" + htmlDecode(calendarData[i].title) + "]</font>");
					else
						tmp.push(", <font color='" + calendarData[i].ui_attr + "'>[" + htmlDecode(calendarData[i].title) + "]</font>");
				}	
			}
		}
	}
	if (GW2_CONTROLLER_SCHEDULE.selectAllCalendar)
		view.find("#calendarIds").val(calendarIds);
	else
		view.find("#calendarIds").val(GW2_CONTROLLER_SCHEDULE.selectedCalendarIds);
	view.find("#calendarlist").html(tmp.join(""));
	if(view.find("#calendarIds").val() == "")
		view.find("#calendarlist").html(MGW_RES.get("gw_msg_schedule_no_select_calendar"));
};

function cancelSelectCalendar2() {
	PAGE_CONTROLLER.goBack();
};

function openAttendeeSelect() {
	var view = $.mobile.activePage;
	var recvlist = view.find("#attendee").html().trim();
	showOrgSelect("schedule", "attendee", recvlist);
}

var scheduleAttendee = {
		getIdValueString: function(list){
			var toStr = "";
			
			$.each(list, function(i, value) {
				var id = list.eq(i).prop("id");
				var type = list.eq(i).attr("type");
				var recursive = list.eq(i).attr("recursive");
				
				toStr += recipients.getIdString(id, type, recursive);
				if (i < list.length-1)	toStr += ";";
			});
			
			return toStr;
		},
		getIdString: function(id, type, recursive){
			var DEPT   = '$';
			var DEPTR  = '$+';
			
			if(type == 'dept' && recursive){
				return DEPTR + id;
			}
			else if(type == 'dept') {
				return DEPT + id;
			}
			else {
				return id;
			}
		}
	};

/**
 * 설비 멀티 체크 리스트
 */
function showSelectEquipment2() {
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();	
	var baseDate = view.find("#baseDate").text();
	
	PAGE_CONTROLLER.showPage("sch2_selectequipment", function() {
		var view = $.mobile.activePage;
		var equipGroupData = GW2_CONTROLLER_SCHEDULE.equipmentGroupData;
		var equipData = GW2_CONTROLLER_SCHEDULE.equipmentData;
		
		view.find("#dateMode").val(dateMode);
		view.find("#baseDate").val(baseDate);
		
		var tmp = [];
		tmp.push("<ul class='equipTree'>");
		
		// 설비그룹
		if (equipGroupData != null && equipGroupData.length != 0 && equipGroupData != undefined) {
			for(var i=0; i<equipGroupData.length; i++){
				tmp.push("<li><label><input type='checkbox' id='equipmentGroup' data-role='none' name='equipmentGroup' value='");
				tmp.push(equipGroupData[i].group_id);
				tmp.push("'onclick=\"javascript:selectAllGroupBox('" + equipGroupData[i].group_id + "');\"/><span>" + equipGroupData[i].title + "(" + MGW_RES.get("gw_schedule_equipmentgroup_label") + ")</span></label></li>");
				for(var j=0; j<equipGroupData[i].calendar_group_member_list.length; j++){
					for(var k=0; k<equipData.length; k++){
						if(equipData[k].calendar_id == equipGroupData[i].calendar_group_member_list[j].calendar_id){
							tmp.push("<li>");
							if(j == equipGroupData[i].calendar_group_member_list.length-1)
								tmp.push("<img src='./images/L.gif'/>");
							else
								tmp.push("<img src='./images/T.gif'/>");
							tmp.push("<label><input type='checkbox' id='equipment' data-role='none' name='equipment' value='");
							tmp.push(equipGroupData[i].calendar_group_member_list[j].calendar_id);
							tmp.push("'onclick=\"javascript:selectGroupBox('" + equipGroupData[i].calendar_group_member_list[j].calendar_id + "');\">");
							tmp.push("<div class='equip_color' style='background:" + equipData[k].ui_attr + "'></div>");
							tmp.push("<span class='equip_name'>" + htmlDecode(equipData[k].title) + "</span>");							
							tmp.push("</label><br>");								
							isEquipCheck = true;
							tmp.push("</li>");
						}						
					}
				}
			}
		}
		
		// 설비분류/설비
		if (equipData != null && equipData.length != 0 && equipData != undefined) {
			var tempEquipDataSortArr = new Array();
			var tempEquipDataArr = new Array();
			
			for(var i=0; i<equipData.length; i++) {
				if (equipData[i].calendar_st == "1") {
					var isMeberGroupCheck = false;
					for(var j=0; j<equipGroupData.length; j++){
						for(var k=0; k<equipGroupData[j].calendar_group_member_list.length; k++){
							if(equipGroupData[j].calendar_group_member_list[k].calendar_id == equipData[i].calendar_id){
								isMeberGroupCheck = true;
								break;
							}
						}
						if(isMeberGroupCheck)
							break;
					}
					if(!isMeberGroupCheck && equipData[i].owner_tp != '5'){
						if(equipData[i].equipment_sort_fg == "1") {
							tempEquipDataSortArr.push(equipData[i]);
						} else {
							tempEquipDataArr.push(equipData[i]);
						}
					}
				}
			}
			
			for(var i=0; i<tempEquipDataSortArr.length; i++){
				tmp.push("<li><label><input type='checkbox' id='equipmentSort' data-role='none' name='equipmentSort' value='");
				tmp.push(tempEquipDataSortArr[i].calendar_id);
				tmp.push("'onclick=\"javascript:selectAllEquipSortBox('" + tempEquipDataSortArr[i].calendar_id + "');\"/><span>" + htmlDecode(tempEquipDataSortArr[i].title) + "</span></label></li>");
				
				var childEquipDataArr = new Array();
				for(var j=0; j<tempEquipDataArr.length; j++){
					if(tempEquipDataArr[j].par_calendar_id == tempEquipDataSortArr[i].calendar_id){
						childEquipDataArr.push(tempEquipDataArr[j]);
					}						
				}
				
				for(var j=0; j<childEquipDataArr.length; j++){
					tmp.push("<li>");
					if(j == childEquipDataArr[j].length-1)
						tmp.push("<img src='./images/L.gif'/>");
					else
						tmp.push("<img src='./images/T.gif'/>");
					tmp.push("<label><input type='checkbox' id='equipmentSortChild' data-role='none' name='equipmentSortChild' value='");
					tmp.push(childEquipDataArr[j].calendar_id);
					tmp.push("'onclick=\"javascript:selectEquipSortBox('" + childEquipDataArr[j].calendar_id + "');\">");
					tmp.push("<div class='equip_color' style='background:" + childEquipDataArr[j].ui_attr + "'></div>");
					tmp.push("<span class='equip_name'>" + htmlDecode(childEquipDataArr[j].title) + "</span>");
					tmp.push("</label><br>");					
					isEquipCheck = true;
					tmp.push("</li>");				
				}
			}		
			
		}else if(isEquipCheck){
			tmp.push("<li><label>" + MGW_RES.get("gw_msg_schedule_equip_nothing") + "</label></li>");
		}			
		
		tmp.push("</ul>");
		view.find("#equipmentlist").html(tmp.join(""));
		
		if (GW2_CONTROLLER_SCHEDULE.selectAllEquipment) {
			view.find('#checkAll').attr('checked', 'checked');
			view.find('input[name=equipmentGroup]').attr('checked', 'checked');
			view.find('input[name=equipment]').attr('checked', 'checked');
			view.find('input[name=equipmentSort]').attr('checked', 'checked');
			view.find('input[name=equipmentSortChild]').attr('checked', 'checked');
		} else {
			var idArr =  GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds.split(";");
			for (var i=0; i<idArr.length; i++) {
				view.find('input:checkbox[name=equipmentGroup]').filter('[value=' + idArr[i] + ']').each(function(){
					$(this).prop('checked', 'checked');	
				});
				view.find('input:checkbox[name=equipment]').filter('[value=' + idArr[i] + ']').each(function(){
					$(this).prop('checked', 'checked');	
				});
				view.find('input:checkbox[name=equipmentSort]').filter('[value=' + idArr[i] + ']').each(function(){
					$(this).prop('checked', 'checked');	
				});
				view.find('input:checkbox[name=equipmentSortChild]').filter('[value=' + idArr[i] + ']').each(function(){
					$(this).prop('checked', 'checked');	
				});					
			}
		}		
	});	
};

function selectAllEquipment2() {
	var view = $.mobile.activePage;
	var c = view.find('#checkAll').attr('checked');

	if (c != 'checked'){
		view.find('input[name=equipmentGroup]').removeAttr('checked');
		view.find('input[name=equipment]').removeAttr('checked');
		view.find('input[name=equipmentSort]').removeAttr('checked');
		view.find('input[name=equipmentSortChild]').removeAttr('checked');	
	}
	else {	
		view.find('input[name=equipmentGroup]').attr('checked', 'checked');
		view.find('input[name=equipment]').attr('checked', 'checked');
		view.find('input[name=equipmentSort]').attr('checked', 'checked');
		view.find('input[name=equipmentSortChild]').attr('checked', 'checked');
	}
};

function completeSelectEquipment2() {
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();
	var baseDate = view.find("#baseDate").val();

	if (view.find('#checkAll').attr('checked') == "checked") {
		GW2_CONTROLLER_SCHEDULE.selectAllEquipment = true;
	}
	else {
		GW2_CONTROLLER_SCHEDULE.selectAllEquipment = false;
		GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds = "";
		
		view.find("#list li").find("[name=equipmentGroup]:checked").each(function(){
			GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds += $(this).prop("value") + ";";
		});
		view.find("#list li").find("[name=equipment]:checked").each(function(){
			GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds += $(this).prop("value") + ";";
		});	
		view.find("#list li").find("[name=equipmentSort]:checked").each(function(){
			GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds += $(this).prop("value") + ";";
		});	
		view.find("#list li").find("[name=equipmentSortChild]:checked").each(function(){
			GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds += $(this).prop("value") + ";";
		});	
	}

	PAGE_CONTROLLER.showPage("sch2_equiplist", function() {getSchEquip2List("equiplist", 0, dateMode, baseDate);});
	
};

function cancelSelectEquipment2() {
	PAGE_CONTROLLER.goBack();
};

/**
 * 설비예약 추가 시 설비그룹 멀티체크
 */
// 설비그룹 전체체크박스 클릭 시 : 설비그룹에 포함된 하위설비들 전체 체크, 전체 해제
function selectAllGroupBox(equipAllBoxID) {
	var view = $.mobile.activePage;
	var equipGroupData = GW2_CONTROLLER_SCHEDULE.equipmentGroupData;
	var checkAll = true;

	for(var i=0; i<equipGroupData.length; i++){
		if(equipGroupData[i].group_id == equipAllBoxID){
			for(var j=0; j<equipGroupData[i].calendar_group_member_list.length; j++){
				var calendarID = equipGroupData[i].calendar_group_member_list[j].calendar_id;
				if(view.find('input:checkbox[name=equipmentGroup]').filter('[value=' + equipAllBoxID + ']').is(":checked")){
					view.find('input:checkbox[name=equipment]').filter('[value=' + calendarID + ']').each(function(){
						$(this).prop('checked', true);	
					});
				}
				else{
					view.find('input:checkbox[name=equipment]').filter('[value=' + calendarID + ']').each(function(){
						$(this).prop('checked', false);
					});
					checkAll = false;
				}				
			}
		}
	}
	
	if(!checkAll) {
		view.find('#checkAll').removeAttr('checked');		
	}
}

// 설비그룹에 포함된 하위설비 체크박스 클릭 시 - 전체체크 돼 있을 때 하위설비 체크해제하면 전체체크박스도 체크해제
function selectGroupBox(equipBoxID) {
	var view = $.mobile.activePage;
	var equipGroupData = GW2_CONTROLLER_SCHEDULE.equipmentGroupData;
	var checkAll = true;

	for(var i=0; i<equipGroupData.length; i++){	
		for(var j=0; j<equipGroupData[i].calendar_group_member_list.length; j++){
			if(equipGroupData[i].calendar_group_member_list[j].calendar_id == equipBoxID){
				if(!view.find('input:checkbox[name=equipment]').filter('[value=' + equipBoxID + ']').is(":checked")) {
					view.find('input:checkbox[name=equipmentGroup]').filter('[value=' + equipGroupData[i].group_id + ']').prop('checked', false);
					checkAll = false;
				}
			}
		}
	}
	
	if(!checkAll) {
		view.find('#checkAll').removeAttr('checked');		
	}
}

// 설비분류 전체체크박스 클릭 시 : 설비분류에 포함된 하위설비들 전체 체크, 전체 해제
function selectAllEquipSortBox(equipSortCalendarID) {
	var view = $.mobile.activePage;
	var equipData = GW2_CONTROLLER_SCHEDULE.equipmentData;
	var sortChecked = $('input:checkbox[name=equipmentSort]').filter('[value=' + equipSortCalendarID + ']').is(":checked");

	for(var i=0; i<equipData.length; i++){
		if(equipData[i].par_calendar_id == equipSortCalendarID){
			view.find('input:checkbox[name=equipmentSortChild]').filter('[value=' + equipData[i].calendar_id + ']').each(function(){
				$(this).prop('checked', sortChecked);	
			});
		}
	}
	
	if(!sortChecked) {
		view.find('#checkAll').removeAttr('checked');		
	}	
}

// 설비분류에 포함된 하위설비 체크박스 클릭 시 - 전체체크 돼 있을 때 하위설비 체크해제하면 전체체크박스도 체크해제
function selectEquipSortBox(equipCalendarID) {
	var view = $.mobile.activePage;
	var equipData = GW2_CONTROLLER_SCHEDULE.equipmentData;
	var equipChecked = $('input:checkbox[name=equipmentSortChild]').filter('[value=' + equipCalendarID + ']').is(":checked");
	if(equipChecked) return;
	
	for(var i=0; i<equipData.length; i++){
		if(equipData[i].calendar_id == equipCalendarID){
			view.find('input:checkbox[name=equipmentSort]').filter('[value=' + equipData[i].par_calendar_id + ']').each(function(){
				$(this).prop('checked', equipChecked);	
			});			
		}
	}
	if(!equipChecked) {
		view.find('#checkAll').removeAttr('checked');		
	}	
}

function getSchConfig(callback) {
	if (!GW2_CONTROLLER_SCHEDULE.schConfigLoaded) {
		GW_PROXY.invokeOpenAPI("schedule", apiCode_schConfig, {}, function(data) {
			GW2_CONTROLLER_SCHEDULE.initSchConfig(data);
			callback();
		});
	} else {
		callback();
	}
}

function getSchConfigValue(key) {
	return GW2_CONTROLLER_SCHEDULE.schConfigData[0][key];		
}

function getSchCategoryList(callback) {
	if (!GW2_CONTROLLER_SCHEDULE.schCategoryLoaded) {
		GW_PROXY.invokeOpenAPI("schedule", apiCode_schCategoryList, {}, function(data) {
			GW2_CONTROLLER_SCHEDULE.initSchCategory(data);
			callback();
		});
	} else {
		callback();
	}
}

function getSchCategoryValue(key) {
	var data = GW2_CONTROLLER_SCHEDULE.schCategoryData;
	for(var i = 0; i< data.length; i++) {
		if(data[i].category_code === key) {
			return data[i].category_name;
		}
	}
	return "";
}

function clearSchData() {
	GW2_CONTROLLER_SCHEDULE.clearSchData();
}

function toogleDaySchedule(obj) {
	var isDisabled = true;
	
	if (obj.checked) {
		isDisabled = true;
	}
	else {
		isDisabled = false;
	}
	
	checkDaySchedule(isDisabled);
}

function checkDaySchedule(isDisabled) {
	var view = $.mobile.activePage;
	
	view.find("#dtstart_hour").attr("disabled", isDisabled);
	view.find("#dtstart_time").attr("disabled", isDisabled);
	view.find("#dtend_hour").attr("disabled", isDisabled);
	view.find("#dtend_time").attr("disabled", isDisabled);
}

function isDaySchedule(startDate, endDate) {
	if(startDate.lastIndexOf(" 0:00") > -1 && endDate.lastIndexOf(" 0:00") > -1) {
		return true;
	}

	return false;
}

function showSch6List(baseDate) {
	PAGE_CONTROLLER.cleanViewStack();
	
	if (baseDate == undefined || baseDate == "") {
		baseDate = getToday();
	}
	if (GWPlugin.usePlugin) {
		GWPlugin.setSelectedTabBarItem(0, function(){}, function(){});
	}
	PAGE_CONTROLLER.showPage("sch6_schlist", function() {getSch6List("daily", baseDate)});
}

function showSch6Detail(sId) {
	getSch6Detail(sId);
}

function showSch6Add(isModify) {
	if (isModify == undefined || isModify == false) {
		if (GWPlugin.usePlugin) {
			GWPlugin.showPopupViewer(["javascript:popupSch6AddView();"], function(){}, function(){});
		}
		else {
			popupSch6AddView();
		}
	}
	else {
		var view = $.mobile.activePage;
		var sId = view.find("#sid").val();
		
		if (GWPlugin.usePlugin) {
			GWPlugin.showPopupViewer(["javascript:popupSch6AddView('" + sId + "');"], function(){}, function(){});
		}
		else {
			popupSch6AddView(sId);
		}
	}
}

function showSch6AddNaviBar() {
	GWPlugin.showPopupViewer(["javascript:popupSch6AddView();"], function(){}, function(){});
}

function popupSch6AddView(sId) {
	// 일정 추가
	if (sId == undefined || sId == "") {
		NAVIBAR_DEF.phone.sch6_schadd.title = MGW_RES.get("gw_schedule_sch6add_label");
		NAVIBAR_DEF.pad.sch6_schadd.title = MGW_RES.get("gw_schedule_sch6add_label");
		
		PAGE_CONTROLLER.showPage("sch6_schadd", function() {getSch6Add();});
	}
	// 일정 수정
	else {
		NAVIBAR_DEF.phone.sch6_schadd.title = MGW_RES.get("gw_schedule_sch6modify_label");
		NAVIBAR_DEF.pad.sch6_schadd.title = MGW_RES.get("gw_schedule_sch6modify_label");
		
		PAGE_CONTROLLER.showPage("sch6_schadd", function() {getSch6Add(sId);});
	}
}

/*
 * 일정 DateType Tab 변경 period : 일간(daily), 주간(weekly), 월간(monthly), 월간계획(plan)
 */
function changeSch6DateTab(period) {
	var view = $.mobile.activePage;
	
	var dateMode = period;
	var baseDate = view.find("#baseDate").val();
	
	getSch6List(dateMode, baseDate);
}

/*
 * 달력 이동 시 '오늘'버튼으로 되돌아 가는 FN
 */
function sch6Today() {
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();
	var baseDate = getToday();
	
	getSch6List(dateMode, baseDate);
}


/*******************************************************************************
 * UI Script START for Schedule
 ******************************************************************************/
/*
 * 전날 검색 '<' 버튼 FN
 */
function sch6LastDate() {
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();
	var baseDate = view.find("#baseDate").val();
	
	if (dateMode == "daily") {
		baseDate = lastDay(baseDate);
	}
	else if (dateMode == "weekly") {
		baseDate = lastWeek(baseDate);
	}
	else if (dateMode == "monthly" || dateMode == "plan") {
		baseDate = lastMonth(baseDate);
	}
	
	getSch6List(dateMode, baseDate);
}

/*
 * 다음날 검색 '>' 버튼 FN
 */
function sch6NextDate() {
	var view = $.mobile.activePage;
	var dateMode = view.find("#dateMode").val();
	var baseDate = view.find("#baseDate").val();
	
	if (dateMode == "daily") {
		baseDate = nextDay(baseDate);
	}
	else if (dateMode == "weekly") {
		baseDate = nextWeek(baseDate);
	}
	else if (dateMode == "monthly" || dateMode == "plan") {
		baseDate = nextMonth(baseDate);
	}
	
	getSch6List(dateMode, baseDate);
}

function cancelSch6() {
	if (confirm(MGW_RES.get("gw_msg_schedule_cancel_add_schedule"))) {
		if (GWPlugin.usePlugin) { // APP, popup인 경우
			GWPlugin.closePopupViewer("", false);
		}
		else {
			PAGE_CONTROLLER.goBack();
		}
	}
}
/*******************************************************************************
 * UI Script END for Schedule *
 ******************************************************************************/



/*******************************************************************************
 * #Schedule List START('일간', '주간', '월간' 일정목록 스크립트) *
 ******************************************************************************/
/*
 * 일정 목록을 출력할 때 사용하는 FN apiCode : 보고자 하는 목록의 api 코드값 (일정->schlist) dateMode :
 * 일간(daily),주간(weekly),월간(monthly),월간계획(plan) 으로 구분; callDate : 요청된 날 표기 (ex :
 * 2012.11.12)
 */
function getSch6List(dateMode, baseDate) {
	var params = {};
	var curDate;
	var startDate;
	var endDate;
	
	params["mode"] = dateMode;
	params["date"] = baseDate;
	
	if (dateMode == "daily") {
		curDate = "<span>" + getYear(baseDate)	+ "</span>." + getMonth(baseDate) + "."
					+ getDate(baseDate) + "(" + getWeekName(baseDate) + ")";
	}
	else if (dateMode == "weekly") {
		startDate = getFirstDateOfWeek(baseDate);
		endDate = getLastDateOfWeek(baseDate);
		
		curDate = getFilterDate(startDate, endDate, "date", "startDate") + "(" + getWeekName(startDate)
					+ ")~" + "<span>" + getFilterDate(startDate, endDate, "date", "endDate") + "</span>" + "(" + getWeekName(endDate) + ")";
	}
	else {
		startDate = getFirstDateOfMonth(baseDate);
		endDate = getLastDateOfMonth(baseDate);
		
		curDate = "<span>" + getYear(startDate) + "</span>." + getMonth(startDate);
	}
	
	GW_PROXY.invokeOpenAPI("schedule", "schlist", params, function(schData) {
		renderSch6List(schData, dateMode, baseDate, curDate);
	});
}

/*
 * schData : api호출을 통해 가져온 목록 리스트 data dateMode : 일간(daily), 주간(weekly),
 * 월간(monthly), 월간계획(plan) baseDate : 기준일
 */
function renderSch6List(schData, dateMode, baseDate, curDate) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	var divList = view.find("#divList");
	var divTitle = view.find("#divTitle");
	var startDate;
	var endDate;
	
	divList.attr("class","divListNormal");
	divTitle.attr("class","divListNormal");
	view.find("#curDate").html(curDate);
	view.find("#baseDate").val(baseDate);
	view.find("#dateMode").val(dateMode);
	
	divList.html("");
	list.html("");
	
	/* 일간 UI Render */
	if (dateMode == "daily") {
		if (schData.length != 0) {
			for(var i = 0; i< schData.length;i++){// 리스트에 메일 렌더
				var item = schData[i];
				var tmp = [];
				
				tmp.push("<li>");
				tmp.push("<a href=\"javascript:showSch6Detail('" + item.sid + "');\">");
				tmp.push("<div class='title'>" + item.summary + "</div>");
				tmp.push("<div class='dateInfo'>" + item.dtstart + " ~ " + item.dtend + " (" + item.ownerName + ")" + "</div>");
				tmp.push("</a></li>");
				
				list.append(tmp.join(""));
			}
		}
		else {
			var tmp = [];
			
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
			list.html(tmp.join(""));
		}
	}
	
	/* 주간 UI Render */
	else if (dateMode == "weekly") {
		var tmp = [];
		
		if (schData.length != 0) {
			startDate = getFirstDateOfWeek(baseDate);
			endDate = getLastDateOfWeek(baseDate);
			
			divList.attr("class", "divList");
			tmp.push("<table class='dateArea' border='0' cellspacing='0' cellpadding='0'>");
			
			for(i=0; i<7; i++) {
				tmp.push("<tr>");
				tmp.push("<th scope='row' class='" + getWeektoNum(i) + "'>"
						+ getDate(getDatetoDay(startDate, i))
						+ "<span class='dateWeek'>" + getWeektoNum(i)
						+ "</span></th>");
				tmp.push("<td");
				
				if (dateToString(new Date()) == dateToString(new Date(
						getYear(baseDate), getMonth(baseDate) - 1, getDate(startDate) + i))
						&& checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
					tmp.push(" class='today'");
				}
 				else if (baseDate == dateToString(new Date(getYear(baseDate),
						getMonth(baseDate) - 1, getDate(startDate) + i))
						&& checkDatePeriod(startDate, endDate, baseDate)) {
					tmp.push(" class='selectDay'");
 				}
				tmp.push(">");
				
				for(var j=0; j<schData.length; j++) {		
					var item = schData[j];
					var baseDt = getDatetoDay(startDate, i);
					var itemDt = item.dtstamp.trim();
					
					if (baseDt < itemDt) {
						break;
					}
					else if (baseDt == itemDt) {
						tmp.push("<a href=\"javascript:showSch6Detail('" + item.sid + "');\">");
						tmp.push("<div>");
						tmp.push("<span class='title'>");
						tmp.push(item.summary + "</span>" + " (" + item.ownerName + ")" + "</div>");
						tmp.push("</a>");
					}
				}
				tmp.push("</td>");
				tmp.push("</tr>");
			}
			tmp.push("</table>");
			divList.html(tmp.join(""));
		}
		else {
			var tmp = [];
			
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
			list.html(tmp.join(""));
		}
	}
	
	/* 월간 UI Render */	
	else if (dateMode == "monthly") {
		if (schData.length != 0) {
			startDate = getFirstDateOfMonth(baseDate);
			endDate = getLastDateOfMonth(baseDate);
			
			divList.attr("class", "divList");
			var tmp = [];
				
			tmp.push("<table class='calMonth'>");
			tmp.push("<tr><tr>");
			
			// 요일
			for(var i=0; i<7; i++) {
				tmp.push("<th class='" + getWeektoNum(i) + "'>" + getWeektoNum(i) + "</th>");
			}
				
			tmp.push("</tr>");
			tmp.push("<tbody>");
			tmp.push("<tr>");
				
			for(var i=0; i<getWeekNumber(startDate); i++) {
				tmp.push("<td></td>");
			}
				
			for(var i=0; i<getDate(endDate); i++) {
				var cnt = 0;
				var checkDt = false;
				
				tmp.push("<td");
				if (getDate(dateToString(new Date())) == i+1 && checkDatePeriod(startDate, endDate, dateToString(new Date()))) {
					tmp.push(" class='today'");
				}
				else if (getDate(baseDate) == i+1 && checkDatePeriod(startDate, endDate, baseDate)) {
					tmp.push(" class='selectDay'");
				}
				tmp.push(">");
					
				// 토요일
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 0) {
					tmp.push("<span class='sat'>");
				}
				// 일요일
				else if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("<span class='sun'>");
				}
				// 평일
				else {
					tmp.push("<span>");
				}
				
				tmp.push((i+1) + "</span>");
				
				for(var j=0; j<schData.length; j++) {		
					var item = schData[j];
					var baseDt = getDatetoDay(startDate, i);
					var baseDay = getDate(getDatetoDay(startDate, i));
					var itemDay = getDate(getDatetoDay(item.dtstamp.trim(), 0)); 
					
					if (baseDay == itemDay) {
						checkDt = true;
						cnt++;
						
						if (schData.length <= j+1) {
							tmp.push("<a href=\"javascript:showSch6List('" + baseDt + "');\" class='schcount'>"
								+ cnt
								+ MGW_RES.get("gw_schedule_calendar_count_label")
								+ "</a>");
							break;
						}
					} else if(checkDt) {
						if (baseDay < itemDay) {
							tmp.push("<a href=\"javascript:showSch6List('" + baseDt + "');\" class='schcount'>"
									+ cnt
									+ MGW_RES.get("gw_schedule_calendar_count_label")
									+ "</a>");
							break;
						}
					}
				}
				tmp.push("</td>");
					
				// 주변경
				if (getWeekNumber(dateToString(new Date(getYear(startDate), getMonth(startDate)-1, i+1))) == 6) {
					tmp.push("</tr><tr>");
				}
			}
			tmp.push("</tr><tbody>");
			divList.html(tmp.join(""));
		}
		else {
			var tmp = [];
			
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
			list.html(tmp.join(""));
		}
	}
	
	/* 월간계획 UI Render */
	else if (dateMode == "plan") {
		if (schData.length != 0) {
			for(var i=0; i<schData.length; i++){		
				var item = schData[i];
				var status = item.statusImage.toLowerCase();
				var dtstart = item.dtstart.split(" ");
				var dtend = item.dtend.split(" ");
				var tmp = [];
				
				tmp.push("<li>");
				tmp.push("<a href=\"javascript:showSch6Detail('" + item.sid + "');\">");
				
				if (status == undefined || status == "") {}
				else if (status.indexOf("notcomplete") > 0) {
					tmp.push("<div class='notcomplete'>[" + MGW_RES.get("gw_schedule_status_notfinish_label") + "]</div>");
				}
				else if (status.indexOf("complete") > 0) {
					tmp.push("<div class='complete'>[" + MGW_RES.get("gw_schedule_status_finish_label") + "]</div>");
				}
				
				tmp.push("<div class='title'>" + item.summary + "</div>");
				tmp.push("<div class='dateInfo'>" + dtstart[0] + " ~ " + dtend[0] + " (" + item.ownerName + ")" + "</div>");
				tmp.push("</a></li>");
				
				list.append(tmp.join(""));
			}
		}
		else {
			var tmp = [];
			
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
			list.html(tmp.join(""));
		}
		list.listview("refresh");
	}
	
	else {
		var tmp = [];
			
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_schedule_schedule_nolist") + "</h3></li>");
		list.html(tmp.join(""));
	}
	list.listview("refresh");
}
/*******************************************************************************
 * Schedule List END *
 ******************************************************************************/



/*******************************************************************************
 * Schedule Detail START *
 ******************************************************************************/
// 일정 상세보기
function getSch6Detail(sId) {
	var params = {};
	var isAuth = false;
	
	if (sId == undefined) {
		alert(gw_msg_common_err);
		return;
	}
	
	params["sid"] = sId;
	
	GW_PROXY.invokeOpenAPI("schedule", "schdetail", params, function(schData) {
	
		if (schData[0].organizer["@cn"] == sessionStorage["id"]) {
			isAuth = true;
			TOOLBAR_DEF.sch6_schdetail =
				[2, [MGW_RES.get("gw_common_modify_label"), MGW_RES.get("gw_common_delete_label")], 
				["btn_tool_approval.png", "btn_tool_delete.png"],
				["javascript:showSch6Add('true');", "javascript:deleteSch6();"]];
		}
		
		PAGE_CONTROLLER.showPage("sch6_schdetail", function() {
			if (isAuth) {
				var view = $.mobile.activePage;
				view.find("#headerBtn").show();
			}
			renderSch6Detail(schData);
		});
	});
}

function renderSch6Detail(schData) {
	var view = $.mobile.activePage;
	if ($.mobile.activePage.prop("id") != "sch6_schdetail")
		return;
	
	var securityMsg = "";
	var list = view.find("#list");
	
	view.find("#sid").val(schData[0].sid);
	view.find("#dtstart").val(schData[0].dtstart);
	
	var tmp = [];
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_mail_title_label") + "</div><div class='grouping_middle'>" + schData[0].summary);	
	tmp.push("</div></li>");
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_owner_label") + "</div><div class='grouping_middle'>" + schData[0].ownerName);	
	tmp.push("</div></li>");
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_writer_label") + "</div><div class='grouping_middle'>");
	
	if (schData[0].organizer["@cn"] != "") { // 조직도 사용자
		tmp.push("<a href=\"javascript:showUserDetails('" + schData[0].organizer["@cn"] + "')\" class='btn_user blue'><span>" 
				+ schData[0].organizer["#text"] + "</span><span class='viewUser'></span></a>");	
	}
	else {
		tmp.push(schData[0].organizer["#text"]);	
	}
	
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_write_date_label") + "</div><div class='grouping_middle'>" + schData[0].created);	
	tmp.push("</div></li>");
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_term_label") + "</div><div class='grouping_middle'>" + schData[0].dtstart + " ~ " + schData[0].dtend);	
	tmp.push("</div></li>");
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_content_label") + "</div><div class='grouping_middle'>" + schData[0].description);	
	tmp.push("</div></li>");
	
	switch (schData[0].security) {
	case 'A':
		securityMsg =  MGW_RES.get("gw_schedule_security_type_a");
		break;
	case 'D':
		securityMsg =  MGW_RES.get("gw_schedule_security_type_d");
		break;
	case 'S':
		securityMsg =  MGW_RES.get("gw_schedule_security_type_s");
		break;
	case 'O':
		securityMsg =  MGW_RES.get("gw_schedule_security_type_o");
		break;
	}
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>" + MGW_RES.get("gw_schedule_security_level_label") + "</div><div class='grouping_middle'>" + securityMsg);	
	tmp.push("</div></li>");
	
	list.html(tmp.join(""));	
	list.listview("refresh");
}
/*******************************************************************************
 * Schedule Detail END *
 ******************************************************************************/



/*******************************************************************************
 * Schedule Add&Edit&Delete START *
 ******************************************************************************/
function getSch6Add(sId) {
	var params = {};

	// 일정 수정
	if (sId != undefined && sId != "") {
		params["sid"] = sId;
		
		GW_PROXY.invokeOpenAPI("schedule", "schdetail", params, function(schData) {
			renderSch6Add(schData);
		});
	}
	// 일정 추가
	else {
		renderSch6Add();
	}
}

function renderSch6Add(schData) {
	var view = $.mobile.activePage;
	
	if (view.prop("id") != "sch6_schadd")
		return;
	
	var selectdtStartTime = view.find("#dtstart_hour");
	var selectdtEndTime = view.find("#dtend_hour");
	
	view.find("#dtstart_date").val(getToday());
	view.find("#dtend_date").val(getToday());
	view.find("#txt_dtstart_date").val(getToday());
	view.find("#txt_dtend_date").val(getToday());
	doSetDatebox();
	
	for(var i = 0; i < 24; i++){
		var time = "0";
		var startTime = "0";
		var endTime = "0";
		var tempTime = getHour();
		var nowTime = tempTime < 10 ? "0"+tempTime : tempTime;
		
		time = i < 10 ? "0"+i : i;
		startTime = nowTime;
		endTime = nowTime + 1;
		
		if (time == startTime) {
			selectdtStartTime.append("<option value='" + time + "' selected='selected'>" + time +  MGW_RES.get("gw_common_hour_label") + "</option>");
		} 
		else {
			selectdtStartTime.append("<option value='" + time + "'>" + time +  MGW_RES.get("gw_common_hour_label") + "</option>");
		}
		
		if (time == endTime) {
			selectdtEndTime.append("<option value='" + time + "' selected='selected'>" + time +  MGW_RES.get("gw_common_hour_label") + "</option>");
		}
		else {
			selectdtEndTime.append("<option value='" + time + "'>" + time +  MGW_RES.get("gw_common_hour_label") + "</option>");
		}
	}
	
	// 일정 수정 시 기존 데이터 반영
	if (schData != undefined) {
		
		view.find("#title").val(schData[0].summary);
		view.find("#description").text(schData[0].description);
		
		view.find("#dtstart_date").val(getDateString(schData[0].dtstart));
		view.find("#dtstart_hour").val(getHoursString(schData[0].dtstart));
		view.find("#dtstart_time").val(getMinutesString(schData[0].dtstart));
		
		view.find("#dtend_date").val(getDateString(schData[0].dtend));
		view.find("#dtend_hour").val(getHoursString(schData[0].dtend));
		view.find("#dtend_time").val(getMinutesString(schData[0].dtend));
		
		view.find("#txt_dtstart_date").val(getDateString(schData[0].dtstart));
		view.find("#txt_dtend_date").val(getDateString(schData[0].dtend));
		
		if (schData[0].ownerType == "D") {
			view.find("#ownertypedept").attr("checked", "checked");
		} 
		else {
			view.find("#ownertypeuser").attr("checked", "checked");
		}
		
		view.find("#security").val(schData[0].security);
			
		view.find("#isUpdate").val(true);
		view.find("#sid").val(schData[0].sid);
	}
}

function addSch6() {
	var view = $.mobile.activePage;
	var isUpdate = view.find("#isUpdate").val();
	
	if (view.prop("id") != "sch6_schadd")
		return;
	
	var params = {};
	var sid = (isUpdate == "true") ? view.find("#sid").val() : ""; 
	var title = view.find("#title");
	var description = view.find("#description");
	
	var tempStartDate = view.find("#dtstart_date").val().split(".");
	var tempEndDate = view.find("#dtend_date").val().split(".");
	
	if (view.find("#progressAddsch").val() == "true") {
		return;
	}
	
	// 제목 입력 체크
	if (title.val().trim() == "") {
		alert(MGW_RES.get("gw_msg_common_inputsubject"));
		title.focus();
		return;
	}
	// 제목 길이 체크 (제한수 30글자)
	else if (title.val().trim().length > 30) {
		alert(MGW_RES.get("gw_msg_common_toolong_msg"));
		title.focus();
		return;
	}
	// 본문 길이 체크 (제한수 500글자)
	else if (description.val().trim().length > 500) {
		alert(MGW_RES.get("gw_msg_common_toolong_summary"));
		description.focus();
		return;
	}
	// 제목 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter(title.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		title.focus();
		return;
	}
	// 본문 유효한 문자열 체크 (' " \ 사용 불가)
	else if (!isValidLetter(description.val().trim())) {
		alert(MGW_RES.get("gw_msg_schedule_invalid_letter_err"));
		description.focus();
		return;
	}
	// 시작일이 종료일 보다 나중인지 체크
	else if (!checkDateTimePeriod(tempStartDate[0], tempStartDate[1], tempStartDate[2], view.find("#dtstart_hour").val(), view.find("#dtstart_time").val(),
			tempEndDate[0], tempEndDate[1], tempEndDate[2], view.find("#dtend_hour").val(), view.find("#dtend_time").val(), 'true')) {
		alert(MGW_RES.get("gw_msg_schedule_period_err"));
		return;
	}
	
	params["todo"] = (isUpdate == "true") ? "update" : "insert";
	params["sid"] = sid;
	params["TITLE"] = title.val().trim();
	params["BODY"] = description.val().trim();
	if (view.find("#ownertypeuser").is(':checked')) {
		params["OWNER_TYPE"] = "U";
	}
	else { 
		params["OWNER_TYPE"] = "D";
	}
	
	params["SEC"] = view.find("#security").val();
	params["RES_LINKAGE_F"] = "N";
	params["SHARE_F"] = "N";
	params["FORM_ID"] = "000000000";
	params["SCH_TYPE"] = "S";
	params["ANNIVERS_F"] = "N";
	params["REPEAT_F"] = "N";
	params["REPEAT_TYPE"] = "N";
	
	params["FROMYEAR"] = tempStartDate[0];
	params["FROMMONTH"] = tempStartDate[1];
	params["FROMDATE"] = tempStartDate[2];
	params["FROMHOUR"] = view.find("#dtstart_hour").val();
	params["FROMMINUTE"] = view.find("#dtstart_time").val();
	
	params["TOYEAR"] =  tempEndDate[0];
	params["TOMONTH"] = tempEndDate[1];
	params["TODATE"] = tempEndDate[2];
	params["TOHOUR"] = view.find("#dtend_hour").val();
	params["TOMINUTE"] = view.find("#dtend_time").val();
	
// params["date"] = getToday();
	
	params["CHECKLUNAR"] = false;
	params["FILENAME"] = null;
	params["FILEFOLDER"] = null;
	params["FILESIZE"] = null;
	params["REPLY_CNT"] = "0";
	
	view.find("#progressAddsch").val("true");
	
	GW_PROXY.invokeOpenAPI("schedule", "schadd", params, function(data) {
		if (data.code == "Success.") {
			alert(MGW_RES.get("gw_msg_common_success"));
			if (GWPlugin.usePlugin) {	
				GWPlugin.closePopupViewer("javascript:showSch6List('" + view.find("#dtstart_date").val() + "');", true);
			}
			else {
				showSch6List(view.find("#dtstart_date").val());
			}
		} 
		else {
			alert(MGW_RES.get("gw_msg_common_err"));
			
			if (GWPlugin.usePlugin) {
				GWPlugin.closePopupViewer("", false);
			}
			else {
				PAGE_CONTROLLER.goBack();
			}
		}
	});
}


function deleteSch6() {
	var view = $.mobile.activePage;	
	var params = {};
	
	if (!(view.prop("id") == "sch6_schdetail"))
		return;
	
	var sId = view.find("#sid").val();
	
	params["sid"] = sId;
	params["mode"] = "ALL";
	
	if (confirm(MGW_RES.get("gw_msg_common_confirm_delete"))) {
		GW_PROXY.invokeOpenAPI("schedule", "schdelete", params, function(data) {
			if (data.status == "Success.") {
				alert(MGW_RES.get("gw_msg_mail_delete_success"));
				PAGE_CONTROLLER.goBack(true);
			}
		});
	}
}

function isValidLetter(src) {
	if ( src.indexOf("'") >= 0 ){
		return false;
	}
	else if ( src.indexOf('"') >= 0 ){
		return false;
	}
	else if ( src.indexOf('\\') >= 0 ){
		return false;
	}
	
	return true;
}
/*******************************************************************************
 * Schedule Add&Edit&Delete END *
 ******************************************************************************/

// 일정추가
$("#sch6_schadd #set_dtstart_date").live("click", function() {
	$("#sch6_schadd #dtstart_date").datebox("open");
});
$("#sch6_schadd #set_dtend_date").live("click", function() {
	$("#sch6_schadd #dtend_date").datebox("open");
});

function changeSch6StartDate() {
	var txt_dtstart_date = $.mobile.activePage.find("#txt_dtstart_date");
	var dtstart_date =  $.mobile.activePage.find("#dtstart_date");
	
	if (txt_dtstart_date != undefined && dtstart_date != undefined) {
		txt_dtstart_date.val(dtstart_date.val());
	}
}

function changeSch6EndDate() {
	var txt_dtend_date = $.mobile.activePage.find("#txt_dtend_date");
	var dtend_date =  $.mobile.activePage.find("#dtend_date");
	
	if (txt_dtend_date != undefined && dtend_date != undefined) {
		txt_dtend_date.val(dtend_date.val());
	}
}/**
	 * 일정 > 설비에 관한 함수 모음 this에 할당된 함수는 외부에서 사용 가능함.
	 */
function scheduleEquipmentCtrl(){
	// 선택된 설비 목록 리턴 {String}
	this.getCheckedEquipments = getCheckedEquipments;
	
	function getCheckedEquipments(){
		var view = $.mobile.activePage,
			equipsListObj = view.find('#equipmentlist');
			equips = $.merge($.merge(equipsListObj.find('#equipment:checked'), equipsListObj.find('#equipmentSort:checked')),
							 equipsListObj.find('#equipmentSortChild:checked')),
			equipIds = [];
		if (!equips || equips.length == 0) {
			return null;
		}
		equips.each(function(){
			equipIds.push($(this).prop("value"));
		});
		return equipIds.join(';')+";";
	}
}
var schEquipmentCtrl = new scheduleEquipmentCtrl();/**
													 * 일정 > 반복 일정 등록에 관한 함수 모음
													 * this에 할당된 함수는 외부에서 사용
													 * 가능함.
													 */
function scheduleRepeatCtrl(){
	// 반복 일정 type 선택 options 생성
	this.renderOptionsRecurType = renderOptionsRecurType;
	// 반복 type 선택 시 이벤트
	this.changeRecurType = changeRecurType;
	// recur_type 에 따른 옵션 항목 선택 상태 처리
	this.setDataOptionsForRecurType = setDataOptionsForRecurType;
	// 반복 type - 상세 정보
	this.renderRecurTypeForDetail = renderRecurTypeForDetail;
	// 기간 - 상세 정보
	this.renderSchTermDetail = renderSchTermDetail;
	// 기간 - 상세 정보 for 리스트
	this.renderSchTermDetailForList = renderSchTermDetailForList;
	// 반복 선택 값 set 리턴
	this.getDataSetRecur = getDataSetRecur;
	// select box 선택 형 recur_type 리턴
	this.getDataForSelectRecurType = getDataForSelectRecurType;
	// 반복일 경우, 이전 recur_type 리턴
	this.getAddedRecurType = getAddedRecurType;
	// 반복일 경우, 이전 start_date 셋팅
	this.setAddedStartDate = setAddedStartDate;
	// 반복일 경우, 이전 start_date 리턴
	this.getAddedStartDate = getAddedStartDate;
	
	var RECUR_TYPE = {
		"NONE":"0",			// 반복없음
		"EVERY_D":"1",	// 매일(월~일)
		"WEEK_D":"2",		// 평일(월~금)
		"EVERY_W":"3",	// 매주
		"EVERY_M":"4",	// 매월(일)
		"EVERY_M_DW":"5",	// 매월(요일)
		"EVERY_Y":"6",	// 매년(일)
		"EVERY_Y_DW":"7", 	// 매년(요일)
		"EVERY_Y_LAST":"8" 	// 매년(월말)
	}
	var addedRecurType, addedStartDate;
	
	function renderOptionsRecurType(){ 
		var view = $.mobile.activePage, 
			recurTypes = 
				[{recur_type:RECUR_TYPE.NONE, 	html: MGW_RES.get("gw_schedule_recur_type0")},
			     {recur_type:RECUR_TYPE.EVERY_D,html: MGW_RES.get("gw_schedule_recur_type1")},
			     {recur_type:RECUR_TYPE.WEEK_D, html: MGW_RES.get("gw_schedule_recur_type2")},
			     {recur_type:RECUR_TYPE.EVERY_W,html: MGW_RES.get("gw_schedule_recur_type3")},
			     {recur_type:RECUR_TYPE.EVERY_M,html: MGW_RES.get("gw_schedule_recur_type4")},
			     {recur_type:RECUR_TYPE.EVERY_Y,html: MGW_RES.get("gw_schedule_recur_type6")}]
		
		$(recurTypes).each(function(idx, data){ 
			view.find("#recur_type").append($("<option>", { value: data.recur_type, html: data.html }));
		});
	}
	function changeRecurType(_this){
		console.debug('changeRecurType ', _this, $(_this).val());
		renderSchdRecurRuleOptions($(_this).val());
	}
	function setDataOptionsForRecurType(recur_type, recur_rule, recur_cycle, isUpdate){
		renderSchdRecurRuleOptions(recur_type, recur_rule, recur_cycle, isUpdate);
		this.addedRecurType = recur_type||null;
	}
	function getAddedRecurType(){
		return this.addedRecurType;
	}
	function setAddedStartDate(startDate) {
		this.addedStartDate = startDate;
	}
	function getAddedStartDate() {
		return this.addedStartDate;
	}
	function renderRecurTypeForDetail(recur_type, recur_rule, recur_cycle){
		var view = $.mobile.activePage, _html=[];
		switch (recur_type) {
		case RECUR_TYPE.EVERY_D:
			_html.push(MGW_RES.get("gw_schedule_recur_type1"));
			break;
		case RECUR_TYPE.WEEK_D:
			_html.push(MGW_RES.get("gw_schedule_recur_type2"));
			break;
		case RECUR_TYPE.EVERY_W: // 매주 : ex) 매주 1주마다 월,화,수
			_html.push(MGW_RES.get("gw_schedule_recur_type3")+ " ")
			_html.push(MGW_RES.get("gw_schedule_recur_cycle_weekly_num").replaceTransWord({num:recur_cycle})+ " ");
				
			var arr = recur_rule.split(';'), wkNames = [];
				$(arr).each(function(idx, val){
					wkNames.push(getWeekNameByWeekName(val));	
				})
				
				_html.push(wkNames.join(','));
			break;
		case RECUR_TYPE.EVERY_M: // 매월(일) : ex) 매월 1일
			_html.push(MGW_RES.get("gw_schedule_recur_cycle_type4_day").replaceTransWord({dd:getDDFullStr(recur_rule)}));
			break;
		case RECUR_TYPE.EVERY_M_DW: // 매월(요일) : ex) 매월 첫번째 수요일
			var arr = recur_rule.split(".");
			if(arr[0] === "-1") {
				arr[0] = "";
			}			
			var data = {	ord:MGW_RES.get("gw_schedule_recur_times"+arr[0]),
							dayWk:getWeekFullNameByNum(arr[1])	};
			_html.push(MGW_RES.get("gw_schedule_recur_cycle_type4_ord_day_wk").replaceTransWord(data));
			break;
		case RECUR_TYPE.EVERY_Y: // 매년(일) : ex) 매년 11월 1일
			_html.push(MGW_RES.get("gw_schedule_recur_cycle_type6_date").replaceTransWord({mmdd:getMMDDFullStrByStrDate("0000." + recur_rule).label}));
			break;
		case RECUR_TYPE.EVERY_Y_DW: // 매년(요일) : ex) 매년 11월 첫번째 수요일
			var arr = recur_rule.split(".");
			if(arr[1] === "-1") {
				arr[1] = "";
			}
			var data = { 	mm:getMMFullStrByStrDate("0000." + arr[0] + ".00").label,
						ord:MGW_RES.get("gw_schedule_recur_times"+arr[1]),
						dayWk:getWeekFullNameByNum(arr[2])	};
			_html.push(MGW_RES.get("gw_schedule_recur_cycle_type6_ord_day_wk").replaceTransWord(data));
			break;
		case RECUR_TYPE.EVERY_Y_LAST: // 매년(월말) : ex) 매년 11월 마지막날
			_html.push(MGW_RES.get("gw_schedule_recur_cycle_type6_last").replaceTransWord({mm: getMMFullStr(parseInt(recur_rule)-1)}) 
			);
			break;
		default: // 반복없음
			_html.push(MGW_RES.get("gw_schedule_recur_type0"));
			break;
		}
		view.find("#li_repeat").html(_html.join(''));
	}
	function isSameDay(startDate, endDate){
		return getDateStrAddDay(startDate, 1) == getDateString(endDate);
	}
	function renderSchTermDetail(startDate, endDate, targetObj){
		if(!startDate || !endDate){
			return false;
		}
		var isDaySch = isDaySchedule(startDate, endDate);
		var _html = "<span>";
		if(isDaySch){
			_html += getDateString(startDate) + "(" + getWeekName(startDate) + ") ";
			if(!isSameDay(startDate, endDate)) {
				var realEndDate = getDateStrAddDay(endDate, -1);
				_html += "~ "+ realEndDate + "(" + getWeekName(realEndDate) + ")";
			}
			_html += "</span>";
			_html += $("<span>", {html:MGW_RES.get("gw_schedule_day_schedule_label"),style:"padding-left: 5px;"})[0].outerHTML;
		} else {
			_html += getFilterDate(startDate, endDate, "dateTime", "startDate");
			_html += "(" + getWeekName(startDate) + ") ~ ";
			_html += getFilterDate(startDate, endDate, "dateTime", "endDate");
			_html += "</span>" + "(" + getWeekName(endDate) + ")";
		}
		if(targetObj) {
			targetObj.html(_html);
		} else {
			return _html;
		}
	}
	function renderSchTermDetailForList(startDate, endDate, targetObj){
		var isDaySch = isDaySchedule(startDate, endDate);
		var _html = "<div class='dateInfo'>";
		if(isDaySch){
			_html += getDateString(startDate);
			if(!isSameDay(startDate, endDate)) {
				_html += " ~ " + getDateStrAddDay(endDate, -1);
			}
			_html += " " + MGW_RES.get("gw_schedule_day_schedule_label");
		} else {
			_html += getFilterDate(startDate, endDate, "dateTime", "startDate") + " ~ " + getFilterDate(startDate, endDate,	"dateTime", "endDate");
		}
		_html += "</div>";
		if(targetObj) {
			targetObj.html(_html);
		} else {
			return _html;
		}
	}
	// 반복 type 에 따른 반복 룰 태그 생성
	function renderSchdRecurRuleOptions(recur_type, recur_rule, recur_cycle, isUpdate){
		var view = $.mobile.activePage,
			recurCycleArea = view.find('#recur_cycle_area'),
			recur_rule3 = recur_rule?recur_rule.split(';').map(Number):[],
			recur_cycle = recur_cycle?recur_cycle:1;
		var setEndDate = function(endDate){
				view.find("#dtend_date").val(endDate);
				view.find("#txt_dtend_date").val(endDate);
			}		
		
		recurCycleArea.empty();

		switch (recur_type) {
		case RECUR_TYPE.EVERY_W:
			recurCycleArea.show();
			recurCycleArea.append($('#tmp_recurTypeEveryWeeks').html());
			
			for (var recurCycle = 1; recurCycle <= 10; recurCycle++) {
				view.find("#recurCycle").append($("<option>", {value:recurCycle, html:recurCycle, selected:recurCycle==recur_cycle}));
			}
			for (var dayOfWk = 0; dayOfWk <= 6; dayOfWk++) {
				view.find("#recurCycleDayOfWeek").append($("<input>", {type:"checkbox",name:"repeat_rule","data-role":"none",value:dayOfWk, checked:(recur_rule3.indexOf(dayOfWk)> -1)}))
				.append($("<span>", {html:getWeekNameByWeekName(dayOfWk)}));
			}
			if(!isUpdate) {
				var endDate = getDateStrAddDay(view.find("#dtstart_date").val(), 7);
				setEndDate(endDate);
			}
			
			break;
		case RECUR_TYPE.EVERY_M:
		case RECUR_TYPE.EVERY_M_DW:
			recurCycleArea.show();
			renderRadioRecurCycleMonth(recur_type, recur_rule);
			if(!isUpdate) {
				var endDate = getDateStrAddMonth(view.find("#dtstart_date").val(), 1);
				setEndDate(endDate);
			}
			break;
		case RECUR_TYPE.EVERY_Y:
		case RECUR_TYPE.EVERY_Y_DW:
		case RECUR_TYPE.EVERY_Y_LAST:
			recurCycleArea.show();
			renderRadioRecurCycleYear(recur_type, recur_rule);
			if(!isUpdate) {
				var endDate = getDateStrAddYear(view.find("#dtstart_date").val(), 1);
				setEndDate(endDate);
			}
			break;		
		default:
			recurCycleArea.hide();
			break;
		}
	}
	/**
	 * 매월 선택 시, 시작일 달력기준으로 radio 버튼 생성
	 */
	function renderRadioRecurCycleMonth(recur_type, recur_rule){
		var view = $.mobile.activePage,
			recurCycleArea = view.find('#recur_cycle_area'),
			els = getDataRecurCycleMonth();
		$(els).each(function(idx, data){
			recurCycleArea.append($("<label>"))
			.find('label:eq('+idx+')').append($("<input>", {type:"radio",name:"month_day","data-role":"none",value:data.val,"data-type":data.type,checked:(recur_rule==data.val)}))
			.append($("<span>", {html:data.label}));
		});
	}
	/**
	 * 매월 선택 시, 시작일 달력기준으로 데이터 생성
	 */
	function getDataRecurCycleMonth(){
		var view = $.mobile.activePage,
			strDate = view.find("#dtstart_date").val(),
			dd = getDDFullStrByStrDate(strDate),
			ordWeek = getNumberOfMonth(strDate)-1;

		var data = 
			[{
	        	label:MGW_RES.get("gw_schedule_recur_cycle_type4_day").replaceTransWord({dd:dd.label}), 
	        	val:dd.val,
	        	type:RECUR_TYPE.EVERY_M
	        },
	        {
	        	label:MGW_RES.get("gw_schedule_recur_cycle_type4_ord_day_wk")
	        			.replaceTransWord({ord: MGW_RES.get("gw_schedule_recur_times"+ordWeek),dayWk:getWeekFullName(strDate)}),
	        	val:ordWeek+"."+getWeekNumber(strDate),
	        	type:RECUR_TYPE.EVERY_M_DW
			}];
		
		if(checkIsLastWeek(strDate)) {
			data.push({
	        	label:MGW_RES.get("gw_schedule_recur_cycle_type4_last_wk")
    			.replaceTransWord({dayWk:getWeekFullName(strDate)}),
		    	val:"-1."+getWeekNumber(strDate),
		    	type:RECUR_TYPE.EVERY_M_DW
			});
		}
		
		return data;		
	}
	/**
	 * 매년 선택 시, 시작일 달력기준으로 radio 버튼 생성
	 */
	function renderRadioRecurCycleYear(recur_type, recur_rule){
		var view = $.mobile.activePage,
			recurCycleArea = view.find('#recur_cycle_area'),
			els = getDataRecurCycleYear();
		$(els).each(function(idx, data){
			recurCycleArea.append($("<label>"))
			.find('label:eq('+idx+')').append($("<input>", {type:"radio",name:"month_day","data-role":"none",value:data.val,"data-type":data.type,checked:(recur_rule==data.val)}))
			.append($("<span>", {html:data.label}));
		});
	}
	/**
	 * 매년 선택 시, 시작일 달력기준으로 데이터 생성
	 */
	function getDataRecurCycleYear(){
		var view = $.mobile.activePage,
			strDate = view.find("#dtstart_date").val(),
			mm = getMMFullStrByStrDate(strDate),
			mmdd = getMMDDFullStrByStrDate(strDate),
			ordWeek = getNumberOfMonth(strDate)-1,
			isLastDate = checkIsLastDate(strDate);
		var data = [];
		data.push({
			label:MGW_RES.get("gw_schedule_recur_cycle_type6_date").replaceTransWord({mmdd: mmdd.label}), 
			val:mmdd.val,
			type:RECUR_TYPE.EVERY_Y
		});
		if(isLastDate){
			data.push({
				label:MGW_RES.get("gw_schedule_recur_cycle_type6_last").replaceTransWord({mm: mm.label}), 
				val:mm.val,
				type:RECUR_TYPE.EVERY_Y_LAST
			});
		}
		data.push({
			label:MGW_RES.get("gw_schedule_recur_cycle_type6_ord_day_wk")
    		.replaceTransWord({mm:getMMFullStrByStrDate(strDate).label, ord:MGW_RES.get("gw_schedule_recur_times"+ordWeek),dayWk:getWeekFullName(strDate)}),
	    	val: mm.val+"."+ordWeek+"."+getWeekNumber(strDate),
	    	type:RECUR_TYPE.EVERY_Y_DW
		});
		if(checkIsLastWeek(strDate)) {
			data.push({
				label:MGW_RES.get("gw_schedule_recur_cycle_type6_last_wk")
	    		.replaceTransWord({mm:getMMFullStrByStrDate(strDate).label, dayWk:getWeekFullName(strDate)}),
		    	val: mm.val+".-1."+getWeekNumber(strDate),
		    	type:RECUR_TYPE.EVERY_Y_DW
			});
		}		
		return data;
	}
	function getDataSetRecur(){
		var view = $.mobile.activePage,
			recurType = view.find('#recur_type').val(), 
			recurRule = "", 
			recurCycle = "";
		switch (recurType) {
		case RECUR_TYPE.EVERY_W: // 매주
			recurRule = view.find('#recurCycleDayOfWeek input[name=repeat_rule]:checked').map(function() {
			      return $(this).val();
		    }).get().join(";");
			recurCycle = view.find('#recurCycle').val();
			break;
		case RECUR_TYPE.EVERY_M: // 매월
		case RECUR_TYPE.EVERY_Y: // 매년
			var _checkedObj = view.find('input[name=month_day]:checked');
			recurType = _checkedObj.data('type');
			recurRule = _checkedObj.val();
			break;
		default:
			break;
		}
		return {recurType:recurType,recurRule:recurRule,recurCycle:recurCycle};
	}
	function getDataForSelectRecurType(addedRecurType){
		
		switch (addedRecurType) {
		case RECUR_TYPE.EVERY_M_DW:
			addedRecurType = RECUR_TYPE.EVERY_M
			break;
		case RECUR_TYPE.EVERY_Y_DW:
		case RECUR_TYPE.EVERY_Y_LAST:
			addedRecurType = RECUR_TYPE.EVERY_Y
			break;
		default:
			break;
		}
		return addedRecurType;
	}
}
var schRepeatCtrl = new scheduleRepeatCtrl();/**
												 * 검색 조건 화면
												 * 
												 * @param popupMode
												 *            1)true:체크박스 포함,
												 *            2)false:체크박스 미포함
												 * @param category
												 *            1)org:조직도 검색,
												 *            2)contact:주소록 검색
												 * @param apiCode
												 *            1)personal:개인주소록,
												 *            2)department:부서주소록,
												 *            3)group:그룹주소록
												 * @param groupId
												 *            apiCode가 group인 경우
												 *            groupId를 전달
												 */
function showSearch(popupMode, category, apiCode, groupId) {
	PAGE_CONTROLLER.showPage("search_condition", function() {
		var view = $.mobile.activePage;	
		
		if (category != undefined)		view.find("#category").val(category);
		if (apiCode != undefined)		view.find("#apiCode").val(apiCode);
		if (popupMode != undefined) 	view.find("#popupMode").val(popupMode);
		if (groupId != undefined)		view.find("#groupId").val(groupId);
	});
}


// 검색조건 생성
function inputSearchCondition(obj) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	
	var category = view.find("#category").val();
	var apiCode = view.find("#apiCode").val();
	var popupMode = view.find("#popupMode").val();
	
	var keyWord = htmlDecode($.trim($(obj).val()));
	if (keyWord) {
		if (category == "org") { // 조직도 검색
			list.find("li").remove();
			
			list.append("<li><a href=\"javascript:searchUser(" + popupMode + ", 'rs.name', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_username") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchUser(" + popupMode + ", 'rs.emp_code', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_empcode") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchUser(" + popupMode + ", 'p.pos_name', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_posname") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchUser(" + popupMode + ", 'r.rank_name', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_rankname") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchUser(" + popupMode + ", 'd.duty_name', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_dutyname") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchDept(" + popupMode + ", '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_deptname") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchUser(" + popupMode + ", 'rs.business', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_business") +  "</h3></a></li>");	
			list.append("<li><a href=\"javascript:searchUser(" + popupMode + ", 'rs.phone', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_phone") +  "</h3></a></li>");			
			list.append("<li><a href=\"javascript:searchUser(" + popupMode + ", 'rs.mobile_phone', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_mobile") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchUser(" + popupMode + ", 'rs.e_mail', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_org_search_email") +  "</h3></a></li>");
			
			list.listview("refresh");
		}
		else if (category == "contact") { // 주소록 검색
			list.find("li").remove();
			list.append("<li><a href=\"javascript:searchContact(" + popupMode + ", '" 
					+ apiCode + "', 'txtName', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_contact_search_name") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchContact(" + popupMode + ", '" 
					+ apiCode + "', 'txtEmail', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_contact_search_email") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchContact(" + popupMode + ", '" 
					+ apiCode + "', 'txtOfficeName', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_contact_search_orgname") +  "</h3></a></li>");
			list.append("<li><a href=\"javascript:searchContact(" + popupMode + ", '" 
					+ apiCode + "', 'txtTelephone', '" + keyWord + "');\"><h3>\"" 
					+ keyWord + "\" " +  MGW_RES.get("gw_contact_search_phone") +  "</h3></a></li>");

			list.listview("refresh");
		}
	}
	else {
		list.find("li").remove();
		list.listview("refresh");
	}
}

function checkSearchCondition(obj) {
	var view = $.mobile.activePage;	
	var list = view.find("#list");
	
	var keyWord = $.trim($(obj).val());
	if (!keyWord) {
		list.find("li").remove();
		list.listview("refresh");
	}
}

// 주소록 검색
function searchContact(popupMode, type, searchKey, searchValue, pageNum) {
	var param = {};
	var groupId = "";
	var apiCode = "";
	
	if (pageNum == undefined || pageNum == "")
		pageNum = 1;
	
	if (type == "personal") {
		apiCode = "search_user";	
	}
	else if (type == "department") {
		apiCode = "search_dept";
	}
	else if (type == "group") {
		apiCode = "search_group";
		groupId = $.mobile.activePage.find("#groupId").val();
		param["groupid"] = groupId;
	}
	
	param["selected_page"] = pageNum;
	param["searchkey"] = searchKey;
	param["searchvalue"] = searchValue;
	
	// 검색조건 화면 삭제
	PAGE_CONTROLLER.popPage();
	
	if (popupMode) {
		NAVIBAR_DEF.phone.search_result.right = [1, [MGW_RES.get("gw_common_ok_label")], ["javascript:finishSearch();"]];
		NAVIBAR_DEF.pad.search_result.right = [1, [MGW_RES.get("gw_common_ok_label")], ["javascript:finishSearch();"]];
	
		// LOG(NAVIBAR_DEF.phone.search_result.right);
	}
	else {
		NAVIBAR_DEF.phone.search_result.right = [0, [0], [0]];
		NAVIBAR_DEF.pad.search_result.right = [0, [0], [0]];
	}
	
	GW_PROXY.invokeOpenAPI("contact", apiCode, param, function(data) {
		var totalCnt = data.total;
		var strTitle = "'" + searchValue + "' " + MGW_RES.get("gw_common_search_result_label") + " (" 
						+ totalCnt + MGW_RES.get("gw_schedule_calendar_count_label")  + ")";
		
		NAVIBAR_DEF.phone.search_result.title = strTitle;
		NAVIBAR_DEF.pad.search_result.title = strTitle;
		
		PAGE_CONTROLLER.showPage("search_result");

		var view = $.mobile.activePage;
		view.find("#list").removeClass("orgTree");
		view.find("#groupId").val(groupId);
		view.find("#popupMode").val(popupMode);
		view.find("#apiCode").val(apiCode);
		view.find("#searchKey").val(searchKey);
		view.find("#searchValue").val(searchValue);
		
		changeTitle(strTitle);
		renderContactList(popupMode, true, type, data);
		
		if (popupMode && GW_CONTROLLER_ORG.category == "mail") {	
			view.find("[data-role=header] a").eq(1).removeAttr("style");
			
			if (GW_CONTROLLER_ORG.selectedList != "") {
				view.find(".targetUserList").html(GW_CONTROLLER_ORG.selectedList);
				view.find(".targetUserDiv").removeAttr("style");
				
				if (type == "department")
					updateTargetRecvContact("department");
				else
					updateTargetRecvContact("personal");
			}
			
			if (trim(view.find(".targetUserList").html()) != "")
				view.find(".targetUserDiv").removeAttr("style");
		}
	});
}

// 조직도 부서 검색
function searchDept(popupMode, searchValue) {
	var param = {"name": searchValue};
	
	// 검색조건 화면 삭제
	PAGE_CONTROLLER.popPage();
	
	if (popupMode) {
		NAVIBAR_DEF.phone.search_result.right = [1, [MGW_RES.get("gw_common_ok_label")], ["javascript:finishSearch();"]];
		NAVIBAR_DEF.pad.search_result.right = [1, [MGW_RES.get("gw_common_ok_label")], ["javascript:finishSearch();"]];
	}
	else {
		NAVIBAR_DEF.phone.search_result.right = [0, [0], [0]];
		NAVIBAR_DEF.pad.search_result.right = [0, [0], [0]];
	}
	
	GW_PROXY.invokeOpenAPI("org", "searchdept", param, function(data) {
		var strTitle = "'" + searchValue + "' " + MGW_RES.get("gw_common_search_result_label") + " (" + data.length 
					       + MGW_RES.get("gw_schedule_calendar_count_label") +  ")";

		NAVIBAR_DEF.phone.search_result.title = strTitle;
		NAVIBAR_DEF.pad.search_result.title = strTitle;
		
		PAGE_CONTROLLER.showPage("search_result");
		
		var view = $.mobile.activePage;	
		view.find("#list").removeClass("contactList");
		changeTitle(strTitle);
		renderSearchDeptResult(data);
		
		if (popupMode && GW_CONTROLLER_ORG.category == "mail") {	
			view.find("[data-role=header] a").eq(1).removeAttr("style");
			
			if (GW_CONTROLLER_ORG.selectedList != "") {
				view.find(".targetUserList").html(GW_CONTROLLER_ORG.selectedList);
				view.find(".targetUserDiv").removeAttr("style");
				updateTargetRecvOrg();
			}
			
			if (trim(view.find(".targetUserList").html()) != "")
				view.find(".targetUserDiv").removeAttr("style");
		}
		
	});
}

// 조직도 구성원 검색
function searchUser(popupMode, searchKey, searchValue) {
	var param = {"searchField": searchKey, "name": searchValue, "searchCond": searchValue};
	
	// 검색조건 화면 삭제
	PAGE_CONTROLLER.popPage();

	if (popupMode) {
		NAVIBAR_DEF.phone.search_result.right = [1, [MGW_RES.get("gw_common_ok_label")], ["javascript:finishSearch();"]];
		NAVIBAR_DEF.pad.search_result.right = [1, [MGW_RES.get("gw_common_ok_label")], ["javascript:finishSearch();"]];
	}
	else {
		NAVIBAR_DEF.phone.search_result.right = [0, [0], [0]];
		NAVIBAR_DEF.pad.search_result.right = [0, [0], [0]];
	}
	
	GW_PROXY.invokeOpenAPI("org", "searchuser", param, function(data) {
		var strTitle = "'" + searchValue + "' " + MGW_RES.get("gw_common_search_result_label") + " (" + data["@totalcount"] 
					       + MGW_RES.get("gw_schedule_calendar_count_label") + ")"
		
        NAVIBAR_DEF.phone.search_result.title = strTitle;
		NAVIBAR_DEF.pad.search_result.title = strTitle;
							
		PAGE_CONTROLLER.showPage("search_result");
		
		var view = $.mobile.activePage;	
		view.find("#list").removeClass("contactList");
		changeTitle(strTitle);
		
		if (data["@totalcount"] == "0") {
			view.find("#list").append("<li><h3>" + MGW_RES.get("gw_msg_common_nosearchdata") + "</h3></li>");
			view.find("#list").listview("refresh");
		}
		else {
			renderSearchUserResult(data);
		}
		
		if (popupMode) {	
			view.find("[data-role=header] a").eq(1).removeAttr("style");
			
			if (GW_CONTROLLER_ORG.selectedList != "") {
				view.find(".targetUserList").html(GW_CONTROLLER_ORG.selectedList);
				view.find(".targetUserDiv").removeAttr("style");
				updateTargetRecvOrg();
			}
			
			if (trim(view.find(".targetUserList").html()) != "") {
				view.find(".targetUserDiv").removeAttr("style");
			}
		}
		
	});
}

function getMoreSearchContact() {
	var view = $.mobile.activePage;
	
	var popupMode = view.find("#popupMode").val();
	var apiCode = view.find("#apiCode").val();
	var searchKey = view.find("#searchKey").val();
	var searchValue = view.find("#searchValue").val();
	var pageNum = view.find("#pno").val();

	var param = {};
	var groupId = "";

	if (apiCode == "personal") {
		apiCode = "search_user";
	}
	else if (apiCode == "department") {
		apiCode = "search_dept";
	}
	else if (apiCode == "group") {
		apiCode = "search_group";
		groupId = $.mobile.activePage.find("#groupId").val();
		param["groupid"] = groupId;
	}

	param["selected_page"] = pageNum;
	param["searchkey"] = searchKey;
	param["searchvalue"] = searchValue;
	
	GW_PROXY.invokeOpenAPI("contact", apiCode, param, function(data) {
		renderContactList(popupMode, true, apiCode, data);
	});
	
}

function finishSearch() {
	var view = $.mobile.activePage;
	GW_CONTROLLER_ORG.selectedList = view.find(".targetUserList").html();
	
	PAGE_CONTROLLER.goBack();
	view = $.mobile.activePage;
	view.find(".targetUserList").html(GW_CONTROLLER_ORG.selectedList);
	
	if (trim(view.find(".targetUserList").html()) != "") {
		view.find(".targetUserDiv").removeAttr("style");
	}
	
	// Update List checkbox
	updateTargetRecvContact("personal");
	updateTargetRecvContact("department");
	updateTargetRecvOrg();
}

// 그룹웨어8(contact2)에서만 사용
function convertContactSerchKey(orgKey) {
	if (orgKey == "txtName")		return "0";
	if (orgKey == "txtOfficeName")	return "1";
	if (orgKey == "txtTelephone")	return "2";
	if (orgKey == "txtEmail")		return "3";
} // 부재 설정
function showSettingsAbsence() {
	PAGE_CONTROLLER.cleanViewStack();
	PAGE_CONTROLLER.showPage("settings_absence",  function(){getSettingsAbsence()});
}

// 개인정보 보기
function showSettingsUserInfo() {
	PAGE_CONTROLLER.cleanViewStack();
	PAGE_CONTROLLER.showPage("settings_userinfo",  function(){getSettingsUserInfo()});
}

// 암호 설정 보기
function showSettingsPassword() {
	PAGE_CONTROLLER.cleanViewStack();
	PAGE_CONTROLLER.showPage("settings_password",  function(){getSettingsPassword()});
}

// 라이센스 보기
function showSettingsLicense() {
	PAGE_CONTROLLER.cleanViewStack();
	PAGE_CONTROLLER.showPage("settings_licenseinfo",  function(){getSettingsLicenseInfo()});
}

function setAbsence() {
	var view = $.mobile.activePage;
	var params = {};
	
	var absent1 = view.find("#absent1");
	var dtstartDate = view.find("#dtstart_date");
	var dtstartTime = view.find("#dtstart_time");
	var dtendDate = view.find("#dtend_date");
	var dtendTime = view.find("#dtend_time");
	var reasonCode = view.find("#reason_code");
	var mailReplyText = view.find("#mail_reply_text");
	var sancHandling1 = view.find("#sanc_handling1");
	var sancHandling2 = view.find("#sanc_handling2");
	var mail_alt_rcpt_id = view.find("#mail_alt_rcpt_id");
	var sanc_alt_signer_id = view.find("#sanc_alt_signer_id");
	
	if (absent1[0].checked == true) {
		var sancAltSignerId = absenceRecipients.getIdValueString($(sanc_alt_signer_id).find("a"));
		
		// 부재 사유 입력 체크
		// tkofs kosmes 부재필수값해제
		/* if (mailReplyText.val().trim() == "") {
			alert(MGW_RES.get("gw_msg_absence_not_absmsg"));
			mailReplyText.focus();
			return;
		}*/
		// 부재 사유 길이 체크 (제한수 160 글자)
		// else
		if (mailReplyText.val().trim().length > 160) {
			alert(MGW_RES.get("gw_msg_absence_toolong_msg"));
			mailReplyText.focus();
			return;
		}
		else if ((sancHandling1.val() == "1" || sancHandling2.val() == "1") && 
				sancAltSignerId == "") {
		
			alert(MGW_RES.get("gw_msg_absence_no_alter_signer"));
			return;
		}
	}

	if (confirm(MGW_RES.get("gw_msg_common_confirm"))) {
		var mailAltRcptId = absenceRecipients.getIdValueString($(mail_alt_rcpt_id).find("a"));
		var sancAltSignerId = absenceRecipients.getIdValueString($(sanc_alt_signer_id).find("a"));
		
		params["dtstart"] = dtstartDate.val() + " " + dtstartTime.val() + ":00";
		params["dtend"] = dtendDate.val() + " " + dtendTime.val() + ":00";
		params["reason_code"] = reasonCode.val();
		params["mail_reply_text"] = mailReplyText.val();
		params["sanc_handling1"] = sancHandling1.val();
		params["sanc_handling2"] = sancHandling2.val();
		params["mail_alt_rcpt_id"] = mailAltRcptId;
		params["sanc_alt_signer_id"] = sancAltSignerId;
		
		if (absent1[0].checked == true) {
			params["absent"] = "1";
		} 
		else {
			params["absent"] = "0";
		}
		
		GW_PROXY.invokeOpenAPI("settings", "setabsence", params, function(data) {
			if (data.status == "0") {
				alert(MGW_RES.get("gw_msg_common_change_success"));
				PAGE_CONTROLLER.goBack(true);
			} else if (data.status == "999") {
				alert(data.message);
			}
		});
	}
}

// 개인정보 변경
function setUserInfo() {
	var view = $.mobile.activePage;
	var params = {};

	if (confirm(MGW_RES.get("gw_msg_common_confirm"))) {
		params["ALIAS"] = view.find("#alias").val();
		params["PHONE"] = view.find("#phone").val();
		params["FAX"] = view.find("#fax").val();
		params["MOBILEPHONE"] = view.find("#mobile").val();
		params["BUSINESS"] = view.find("#business").val();
		
		GW_PROXY.invokeOpenAPI("settings", "setuserinfo", params, function(data) {
			if (data.status == "0") {
				alert(MGW_RES.get("gw_msg_common_change_success"));
				PAGE_CONTROLLER.goBack(true);
			}
		});
	}
}

function toogle(obj, id) {
	var view = $.mobile.activePage;
	var ctrls = document.getElementById(id).getElementsByTagName("input");
	
	if (!obj.checked) {
		view.find("#" + id).find("li").addClass("disable");
		view.find("#" + id).find("li").eq(0).removeClass("disable");
	}
	else {
		view.find("#" + id).find("li").removeClass("disable");
	}
	
	for(i=0; i<ctrls.length; i++) {
        if (ctrls[i].name != obj.name) {
        	view.find("#" + ctrls[i].id).attr("disabled", !obj.checked);
        }
    }
}

function toggleAbsence(obj) {
	var view = $.mobile.activePage;
	var isDisabled = true;
	
	view.find("#dtstart_date").datebox(obj);
	view.find("#dtend_date").datebox(obj);
	
	if (obj == "enable") {
		isDisabled = false;
		
		if (view.find("#list_mail_absence").find(".targetUserList").html().trim() != "")
			view.find("#list_mail_absence").find(".targetUserList").show();
		
		if (view.find("#list_sanc_absence").find(".targetUserList").html().trim() != "")
			view.find("#list_sanc_absence").find(".targetUserList").show();
	}
	else {
		view.find("#list_mail_absence").find(".targetUserList").hide();
		view.find("#list_sanc_absence").find(".targetUserList").hide();
	}
	
	view.find("#dtstart_time").attr("disabled", isDisabled);
	view.find("#dtend_time").attr("disabled", isDisabled);
	view.find("#txt_dtstart_date").attr("disabled", isDisabled);
	view.find("#txt_dtend_date").attr("disabled", isDisabled);
	
	view.find("#reason_code").attr("disabled", isDisabled);
	view.find("#mail_reply_text").attr("disabled", isDisabled);
	view.find("#sanc_handling1").attr("disabled", isDisabled);
	view.find("#sanc_handling2").attr("disabled", isDisabled);
}

// 암호 설정
function setPassword() {
	var view = $.mobile.activePage;	
	var params = {};
	
	var cbLoginPasswd = view.find("#cbLoginPasswd");
	var txtOldLoginPasswd = view.find("#txtOldLoginPasswd");
	var txtLoginPasswd = view.find("#txtLoginPasswd");
	var txtLoginPasswdConfirm = view.find("#txtLoginPasswdConfirm");
	var cbSancPasswd = view.find("#cbSancPasswd");
	var txtOldSancPasswd = view.find("#txtOldSancPasswd");
	var txtSancPasswd = view.find("#txtSancPasswd");
	var txtSancPasswdConfirm = view.find("#txtSancPasswdConfirm");
	var cbSancPasswdCheck = view.find("#cbSancPasswdCheck");
	var txtSancPasswdCheck = view.find("#txtSancPasswdCheck");
	
	if (!cbLoginPasswd.is(':checked') && 
		!cbSancPasswd.is(':checked') &&
		!cbSancPasswdCheck.is(':checked')) {
		alert(MGW_RES.get("gw_msg_common_no_change"));
		return;
	}
	
	if (cbLoginPasswd.is(':checked')) {
		if (txtOldLoginPasswd.val().length == 0) {
			alert(MGW_RES.get("gw_msg_password_no_old_password"));
			return;
		}
		else if (txtLoginPasswd.val().length == 0) {
			alert(MGW_RES.get("gw_msg_password_no_new_password"));
			return;
		}
		else if (txtLoginPasswdConfirm.val().length == 0) {
			alert(MGW_RES.get("gw_msg_password_no_new_password_confirm"));
			return;
		}
		else if (txtLoginPasswd.val() != txtLoginPasswdConfirm.val()) {
			alert(MGW_RES.get("gw_msg_password_mismatch_new_password"));
			return;
		}
		else if (txtLoginPasswd.val() == txtOldLoginPasswd.val()) {
			alert(MGW_RES.get("gw_msg_password_sameold_new_password"));
			return;
		}
		else if (txtLoginPasswd.val().length < 5) {
			alert(MGW_RES.get("gw_msg_password_tooshort_password"));
			return;
		}
		else if (txtLoginPasswd.val().length > 30) {
			alert(MGW_RES.get("gw_msg_password_toolong_password"));
			return;
		}
		
		params["cbLoginPasswd"] = "1";
		params["txtOldLoginPasswd"] = txtOldLoginPasswd.val();
		params["txtLoginPasswd"] = txtLoginPasswd.val();
		params["txtLoginPasswdConfirm"] = txtLoginPasswdConfirm.val();
		if (view.find("#rdoSanc1").is(':checked')) {
			params["rdoSanc"] = "1";
		}
		else { 
			params["rdoSanc"] = "0";
		}
	}
	
	if (cbSancPasswd.is(':checked')) {
		if (txtOldSancPasswd.val().length == 0) {
			alert(MGW_RES.get("gw_msg_password_no_old_sanc_password"));
			return;
		}
		else if (txtSancPasswd.val().length == 0) {
			alert(MGW_RES.get("gw_msg_password_no_new_sanc_password"));
			return;
		}
		else if (txtSancPasswdConfirm.val().length == 0) {
			alert(MGW_RES.get("gw_msg_password_no_new_sanc_password_confirm"));
			return;
		}
		else if (txtSancPasswd.val() != txtSancPasswdConfirm.val()) {
			alert(MGW_RES.get("gw_msg_password_mismatch_new_sanc_password"));
			return;
		}
		else if (txtOldSancPasswd.val() == txtSancPasswd.val()) {
			alert(MGW_RES.get("gw_msg_password_sameold_new_sanc_password"));
			return;
		}
		else if (txtSancPasswd.val().length < 5) {
			alert(MGW_RES.get("gw_msg_password_tooshort_sanc_password"));
			return;
		}
		else if (txtSancPasswd.val().length > 30) {
			alert(MGW_RES.get("gw_msg_password_toolong_password"));
			return;
		}
		
		params["cbSancPasswd"] = "1";
		params["txtOldSancPasswd"] = txtOldSancPasswd.val();
		params["txtSancPasswd"] = txtSancPasswd.val();
		params["txtSancPasswdConfirm"] = txtSancPasswdConfirm.val();
	}
	
	if (cbSancPasswdCheck.is(':checked')) {
		if (txtSancPasswdCheck.val().length == 0) {
			alert(MGW_RES.get("gw_msg_password_input_password_for_sanc"));
			return;
		}
		
		params["cbSancPasswdCheck"] = "1";
		params["txtSancPasswdCheck"] = txtSancPasswdCheck.val();
		if (view.find("#rdoSancConfirm1").is(':checked')) {
			params["rdoSancConfirm"] = "1";
		}
		else {
			params["rdoSancConfirm"] = "0";
		}
	}
	
	GW_PROXY.invokeOpenAPI("settings", "setpassword", params, function(data) {
		if (data.status == "0") {
			alert(MGW_RES.get("gw_msg_common_change_success"));
			PAGE_CONTROLLER.goBack(true);
		} 
		else {
			alert(data.message);
		}
	});
}

function getSettingsAbsence() {
	GW_PROXY.invokeOpenAPI("settings", "getabsence", {}, function(data) {
		renderSettingAbsence(data);
	});
}

function getSettingsUserInfo() {
	GW_PROXY.invokeOpenAPI("settings", "getuserinfo", {}, function(data) {
		renderSettingUserInfo(data);
	});
}

function getSettingsPassword() {
	GW_PROXY.invokeOpenAPI("settings", "getuserinfo", {}, function(data) {
		renderSettingPassword(data);
	});
}

function getSettingsLicenseInfo() {
	GW_PROXY.invokeOpenAPI("common", "getlicenseinfo", {}, function(data) {
		renderSettingLicenseInfo(data);
	});
}

function renderSettingAbsence(data) {
	var view = $.mobile.activePage;
	if ($.mobile.activePage.prop("id") != "settings_absence")
		return;
	
	var selectReason = view.find("#reason_code");
	var selectdtStartTime = view.find("#dtstart_time");
	var selectdtEndTime = view.find("#dtend_time");
	
	// 부재 사유 설정
	if (data.reasons != undefined) {
		for(var i=0; i<data.reasons.length; i++) {
			if (data.reasons[i]["@selected"] == "true") {
				selectReason.append('<option value='+data.reasons[i]["@code"]+' selected=\"selected\">'+data.reasons[i]["#text"]+'</option>');
			}
			else { 
				selectReason.append('<option value='+data.reasons[i]["@code"]+'>'+data.reasons[i]["#text"]+'</option>');
			}
		}
	} 
	else {
		selectReason.append("<option value='01'>" + MGW_RES.get("gw_settings_absence_period_reason_1_label") + "</option>");
		selectReason.append("<option value='02'>" + MGW_RES.get("gw_settings_absence_period_reason_2_label") + "</option>");
		selectReason.append("<option value='03'>" + MGW_RES.get("gw_settings_absence_period_reason_3_label") + "</option>");
		selectReason.append("<option value='04'>" + MGW_RES.get("gw_settings_absence_period_reason_4_label") + "</option>");
		selectReason.append("<option value='05'>" + MGW_RES.get("gw_settings_absence_period_reason_5_label") + "</option>");
		selectReason.append("<option value='06'>" + MGW_RES.get("gw_settings_absence_period_reason_6_label") + "</option>");
		selectReason.append("<option value='07'>" + MGW_RES.get("gw_settings_absence_period_reason_7_label") + "</option>");
		selectReason.append("<option value='08'>" + MGW_RES.get("gw_settings_absence_period_reason_8_label") + "</option>");
		selectReason.append("<option value='09'>" + MGW_RES.get("gw_settings_absence_period_reason_9_label") + "</option>");
		selectReason.append("<option value='10'>" + MGW_RES.get("gw_settings_absence_period_reason_10_label") + "</option>");
		selectReason.append("<option value='11'>" + MGW_RES.get("gw_settings_absence_period_reason_11_label") + "</option>");
		selectReason.append("<option value='12'>" + MGW_RES.get("gw_settings_absence_period_reason_12_label") + "</option>");
		selectReason.append("<option value='13'>" + MGW_RES.get("gw_settings_absence_period_reason_13_label") + "</option>");
		selectReason.append("<option value='14'>" + MGW_RES.get("gw_settings_absence_period_reason_14_label") + "</option>");
	}
	
	var tempTime = getHour();
	var nowTime = tempTime < 10 ? "0"+tempTime : tempTime;
	
	// 시간 셋팅
	for(var i = 0; i < 24; i++){
		var timeVal = i<10 ? "0"+i : i;
		var timeStr = i<10 ? "0"+i+":00" : i+":00";
		
		selectdtStartTime.append("<option value='" + timeVal + "'>" + timeStr + "</option>");
		selectdtEndTime.append("<option value='" + timeVal + "'>" + timeStr + "</option>");
	}
	
	// 부재 설정된 경우
	if (data.absent == "1") {
		var dtstart = getAbsenceDate(data.dtstart, "time");
		var dtend = getAbsenceDate(data.dtend, "time");
		
		view.find("#absent1").attr("checked", true);
		toggleAbsence('enable');
		
		// 설정된 날짜 셋팅
		if (data.dtstart != undefined) {
			var date1 = getAbsenceDate(data.dtstart, "date");
			
			view.find("#dtstart_date").val(date1);
			view.find("#txt_dtstart_date").val(date1);
		}
		if (data.dtend != undefined) {
			var date2 = getAbsenceDate(data.dtend, "date");
			
			view.find("#dtend_date").val(date2);
			view.find("#txt_dtend_date").val(date2);
		}
		
		view.find("#list_mail_absence").find(".grouping_middle").show();
		view.find("#list_sanc_absence").find(".grouping_middle").show();
		
		// 설정된 시간 셋팅
		selectdtStartTime.val(dtstart);
		selectdtEndTime.val(dtend);
		
		if (data.mail != undefined && data.mail.alternateRecipient["@id"] != "") {
			var targetMailUser = view.find(".targetUserList").filter("#mail_alt_rcpt_id");
			var id = data.mail.alternateRecipient["@id"];
			var name = data.mail.alternateRecipient["#text"];
			
			if (targetMailUser.attr("style") != undefined)
				targetMailUser.removeAttr("style");
			targetMailUser.append(renderTargetRecv(GW_CONTROLLER_ORG.callbackId, 'user', id, name, undefined));
		}
		
		view.find("#mail_reply_text").val(data.mail.replyText);
		
		if (data.sanction != undefined && data.sanction.alternateSigner["@id"] != "") {
			var targetSancUser = view.find(".targetUserList").filter("#sanc_alt_signer_id");
			var id = data.sanction.alternateSigner["@id"];
			var name = data.sanction.alternateSigner["#text"];
			
			if (targetSancUser.attr("style") != undefined)
				targetSancUser.removeAttr("style");
			
			targetSancUser.append(renderTargetRecv(GW_CONTROLLER_ORG.callbackId, 'user', id, name, undefined));
		}
		
		var sancHandling1 = view.find("#sanc_handling1");
		var sancHandling2 = view.find("#sanc_handling2");
		
		// 결재후 진행문서
		switch(data.sanction.handling1.trim()) {
			case "hold" :
				sancHandling1[0].selectedIndex = 0;
				break;
			case "delegate" :
				sancHandling1[0].selectedIndex = 1;
				break;
			case "pass" :
				sancHandling1[0].selectedIndex = 2;
				break;
		}
		// 결재후 완료문서
		switch(data.sanction.handling2.trim()) {
			case "hold" :
				sancHandling2[0].selectedIndex = 0;
				break;
			case "delegate" :
				sancHandling2[0].selectedIndex = 1;
				break;
		}
	}
	else {
		view.find("#dtstart_date").datebox("disable");
		view.find("#dtend_date").datebox("disable");
		view.find("#absent2").attr("checked", true);
		// 오늘 날짜 & 시간 셋팅
		view.find("#dtstart_date").val(getToday());
		view.find("#dtend_date").val(getToday());
		view.find("#txt_dtstart_date").val(getToday());
		view.find("#txt_dtend_date").val(getToday());
		
		selectdtStartTime.val(nowTime);
		selectdtEndTime.val(parseInt(nowTime) + 1);
		doSetDatebox();
	}
	view.find("#list_set_absence").find("li").eq(1).find(".grouping_middle").show();
	view.find("#list_set_absence").find("li").eq(2).find(".grouping_middle").show();
}

function renderSettingUserInfo(data) {
	console.log(JSON.stringify(data))
	var view = $.mobile.activePage;
	if ($.mobile.activePage.prop("id") != "settings_userinfo")
		return;
	
	var profile = view.find("#profile");
	var tmp = [];
	
	if (data.alias != undefined)
		view.find("#alias").val(data.alias);
	
	if (data.telephoneNumber != undefined)
		view.find("#phone").val(data.telephoneNumber);
	
	if (data.faxNumber != undefined)
		view.find("#fax").val(data.faxNumber);
	
	if (data.mobile != undefined)
		view.find("#mobile").val(data.mobile);
	
	if (data.business != undefined)
		view.find("#business").text(data.business);
	
	var imgSrc = GW_OpenAPI.serverIP + GW_OpenAPI.streamPATH + data.photo.replace(/(^\s*)/, "");

	tmp.push("<div class='profileHeader'>");
	tmp.push("<img src='" + imgSrc + "' onerror=\"this.src='images/people_03.gif'\" width=\"80px\" height=\"110px\" class='profileImg'>");
	tmp.push("<h2>" + data.name + "</h2> ");
	tmp.push("<span>" + data.department + "</span><span>" + data.position + "</span><span>" + data.homepage + "</span>");
	tmp.push("</div>");
	
	profile.html(tmp.join(""));
}

function renderSettingPassword(data) {
	var view = $.mobile.activePage;
	if ($.mobile.activePage.prop("id") != "settings_password")
		return;
	
	if (data.sancPasswdChk == "true") {
		view.find("#rdoSancConfirm1").attr("checked", "checked");
	}
	else {
		view.find("#rdoSancConfirm2").attr("checked", "checked");
	}
}

function renderSettingLicenseInfo(data) {
	var view = $.mobile.activePage;
	if ($.mobile.activePage.prop("id") != "settings_licenseinfo")
		return;
	
	if (data.message != undefined)
		view.find("#licenseinfo").text(data.message);
}

// 수신자 선택 화면 이동
function openUserSelect(recvType) {
	var view = $.mobile.activePage;
	
	if (view.find("#absent1").is(':checked')) {
		var recvlist = view.find("#" + recvType).html().trim();
		
		showOrgSelect("settings", recvType, recvlist);
	}
}

var absenceRecipients = {
	getIdValueString: function(list){
		var id = "";
					
		$.each(list, function(i, value) {
			id = list.eq(i).prop("id");
		});
		
		return id;
	}
};

$("#settings_absence #set_dtstart_date").live("click", function() {
	if ($.mobile.activePage.find("#absent2").attr("checked") == "checked")
		return;
	
	$("#settings_absence #dtstart_date").datebox("open");
});

$("#settings_absence #set_dtend_date").live("click", function() {
	if ($.mobile.activePage.find("#absent2").attr("checked") == "checked")
		return;
	
	$("#settings_absence #dtend_date").datebox("open");
});
/**
 * Settings2 ApiCode 목록 getabsence : 부재조회 absencelist : 부재목록 absenceadd : 부재추가
 * absencedelete : 부재삭제
 */
// 부재 설정
function showSettings2Absence() {
// PAGE_CONTROLLER.cleanViewStack();
	LOG(TOOLBAR_DEF.settings2_absenceadd);
	TOOLBAR_DEF.settings2_absencelist =
		[1, [MGW_RES.get("gw_common_delete_label")], 
		["btn_tool_delete.png"],
		["javascript:deleteSettings2('list');"]];
	PAGE_CONTROLLER.showPage("settings2_absencelist",  function(){getSettings2AbsenceList();});
}

/*
 * 부재 추가/수정
 */
function showSettings2Add(isModify, absId){
	LOG("show absId : " + absId);
	var view = $.mobile.activePage;
	view.find("#absId").val(absId);
	if (isModify == undefined || isModify == false) {
		popupSettings2AddView();
	}
	else {
		if (GWPlugin.usePlugin) {
			GWPlugin.showPopupViewer(["javascript:popupSettings2AddView('" + absId + "');"], function(){}, function(){});
		}
		else {
			popupSettings2AddView(absId);
		}
	}
// PAGE_CONTROLLER.showPage("settings2_absenceadd",
// function(){getSettings2Absence();});
}

function showSettings2AddNaviBar() {
	NAVIBAR_DEF.phone.settings2_absenceadd.title = MGW_RES.get("gw_settings_absence_absadd_label");
	NAVIBAR_DEF.pad.settings2_absenceadd.title = MGW_RES.get("gw_settings_absence_absadd_label");
	
	GWPlugin.showPopupViewer(["javascript:popupSettings2AddView();"], function(){}, function(){});
}

function popupSettings2AddView(absId) {
	LOG("popup absId : " + absId);
	TOOLBAR_DEF.settings2_absenceadd = [0, [], [], []];
	// 부재 추가
	if (absId == undefined) {
		NAVIBAR_DEF.phone.settings2_absenceadd.title = MGW_RES.get("gw_settings_absence_absadd_label");
		NAVIBAR_DEF.pad.settings2_absenceadd.title = MGW_RES.get("gw_settings_absence_absadd_label");
		
		PAGE_CONTROLLER.showPage("settings2_absenceadd", function() {renderSetting2Absence({});});
	}
	// 부재 수정
	else {
		TOOLBAR_DEF.settings2_absenceadd =
			[1, [MGW_RES.get("gw_common_delete_label")], 
			["btn_tool_delete.png"],
			["javascript:deleteSettings2('add');"]];
// APP_CONTROLLER.setToolbar("settings2_absenceadd");
		NAVIBAR_DEF.phone.settings2_absenceadd.title = MGW_RES.get("gw_settings_absence_absmodify_label");
		NAVIBAR_DEF.pad.settings2_absenceadd.title = MGW_RES.get("gw_settings_absence_absmodify_label");
		
		if (absId != undefined && absId != "") {
			PAGE_CONTROLLER.showPage("settings2_absenceadd", function() {getSettings2Absence(absId);});
		}
		else {
			alert(MGW_RES.get("gw_msg_common_err"));
		}
	}
}

/*
 * 부재 저장
 */
function setAbsence2() {
	var view = $.mobile.activePage;
	var params = {};
	LOG(view.prop("id"));
	
// var absent1 = view.find("#absent1");
	var dtstartDate = view.find("#dtstart_date");
	var dtstartTime = view.find("#dtstart_time");
	var dtendDate = view.find("#dtend_date");
	var dtendTime = view.find("#dtend_time");
	var reasonCode = view.find("#reason_code");
	var mailReplyText = view.find("#mail_reply_text");
	var sancHandling1 = view.find("#sanc_handling1");
	var sancHandling2 = view.find("#sanc_handling2");
	var mail_alt_rcpt_id = view.find("#mail_alt_rcpt_id");
	var sanc_alt_signer_id = view.find("#sanc_alt_signer_id");
	var absId = view.find("#absId").val();
	var isCopy = view.find("#isCopy").val();
	var isModify = view.find("#isModify").val();
	
// if (absent1[0].checked == true) {
	var sancAltSignerId = absenceRecipients.getIdValueString($(sanc_alt_signer_id).find("a"));
	
	// 부재 사유 입력 체크 tkofs kosmes 부재사유 해제
	/*if (mailReplyText.val().trim() == "") {
		alert(MGW_RES.get("gw_msg_absence_not_absmsg"));
		mailReplyText.focus();
		return;
	}*/
	// 부재 사유 길이 체크 (제한수 160 글자)
	//else 
	if (mailReplyText.val().trim().length > 160) {
		alert(MGW_RES.get("gw_msg_absence_toolong_msg"));
		mailReplyText.focus();
		return;
	}
	else if ((sancHandling1.val() == "1" || sancHandling2.val() == "1") && 
			sancAltSignerId == "") {
	
		alert(MGW_RES.get("gw_msg_absence_no_alter_signer"));
		return;
	}
// }
	var saveMessage = MGW_RES.get("gw_msg_common_save_confirm");
	if(isModify == "true")
		saveMessage = MGW_RES.get("gw_msg_common_confirm");
	else if(isModify == "fasle")
		saveMessage = MGW_RES.get("gw_msg_common_save_confirm");
	if (confirm(saveMessage)) {
		var mailAltRcptId = absenceRecipients.getIdValueString($(mail_alt_rcpt_id).find("a"));
		var sancAltSignerId = absenceRecipients.getIdValueString($(sanc_alt_signer_id).find("a"));
		
		params["dtstart"] = dtstartDate.val() + " " + dtstartTime.val() + ":00";
		params["dtend"] = dtendDate.val() + " " + dtendTime.val() + ":00";
		params["reason_code"] = reasonCode.val();
		params["mail_reply_text"] = mailReplyText.val();
		params["sanc_handling1"] = sancHandling1.val();
		params["sanc_handling2"] = sancHandling2.val();
		params["mail_alt_rcpt_id"] = mailAltRcptId;
		params["sanc_alt_signer_id"] = sancAltSignerId;
		
		if(absId != "" && isCopy == "false")
			params["absid"] = absId;
		
		params["absent"] = "1";
// if (absent1[0].checked == true) {
// params["absent"] = "1";
// }
// else {
// params["absent"] = "0";
// }
		
		GW_PROXY.invokeOpenAPI("settings", "absenceadd", params, function(data) {
			LOG(data);
			if (data.status == "0") {
				alert(MGW_RES.get("gw_msg_common_success"));
// PAGE_CONTROLLER.goBack(true);
				if (GWPlugin.usePlugin) {
					GWPlugin.closePopupViewer("javascript:showSettings2Absence();", true);
				}else{
					PAGE_CONTROLLER.goBack(true);
				}
			} else if (data.status == "999") {
				alert(data.message);
			}
		});
	}
}

/*
 * 부재 목록
 */
function getSettings2AbsenceList() {
	GW_PROXY.invokeOpenAPI("settings", "absencelist", {}, function(data) {
		LOG(data);
		renderSetting2AbsenceList(data);
	});
}

/*
 * 부재 조회
 */
function getSettings2Absence(absId) {
	var param = {};
	param["absid"] = absId;
	GW_PROXY.invokeOpenAPI("settings", "getabsence", param, function(data) {
		renderSetting2Absence(data);
	});
}

/*
 * 부재 목록 Rendering
 */
function renderSetting2AbsenceList(data) {
	var view = $.mobile.activePage;
	var list = view.find("#list");
	list.html("");
	var tmp = [];
	LOG("length : " + data.length);
	// 목록
	if (data.length != 0) {
		for(var i=0; i<data.length; i++){
			var item = data[i];
			var absid = item.absid.replace("|", "");
			var sanction = "";
			if(item.sanction[0]["#text"] != undefined)
				sanction = item.sanction[0]["#text"];
			tmp.push("<li><div class='grouping_left' onclick='javascript:toggle2Absence(\"" + absid + "\");'><input type='checkbox' id='" + absid + "' data-role='none' name='checkAbsence' onclick='javascript:toggle2Absence(\"" + absid + "\");' value='" + item.absid + "'></div>");
			tmp.push("<a href='javascript:showSettings2Add(true, \"" + item.absid + "\");'><div class='grouping_middle'>");
			tmp.push("<p><strong>" + MGW_RES.get("gw_settings_absence_period_label") + "</strong> : " + item.dtstart + " ~ " + item.dtend + "</p>");
			tmp.push("<p><strong>" + MGW_RES.get("gw_settings_absence_period_reason_label") + "</strong> : " + MGW_RES.get("gw_settings_absence_period_reason_" + parseInt(item.reasons[0]["@code"], 10) + "_label") + "</p>");
			tmp.push("<p><strong>" + MGW_RES.get("gw_settings_absence_sanc_alt_signer_label") + "</strong> : " + sanction + "</p>");
			tmp.push("<p><strong>" + MGW_RES.get("gw_settings_absence_mail_reply_msg_label") + "</strong> : " + item.mail[0] + "</p>");
			tmp.push("</div></a></li>");
		}
	}
	else {
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_absence_absence_nolist") + "</h3></li>");
	}
	list.append(tmp.join(""));
	list.listview("refresh");
}

/*
 * 부재 조회 Rendering
 */
function renderSetting2Absence(data) {
	var view = $.mobile.activePage;
	LOG(data);
		
	if ($.mobile.activePage.prop("id") != "settings2_absenceadd")
		return;
	
	var selectReason = view.find("#reason_code");
	var selectdtStartTime = view.find("#dtstart_time");
	var selectdtEndTime = view.find("#dtend_time");
	var absId = view.find("#absId");
	var isModify = view.find("#isModify");
	
	if(data.absent != 0)
		isModify.val("true");
	
	// 부재 id값 설정
	if(data.absid != undefined)
		absId.val(data.absid);

	// 부재 사유 설정
	if (data.reasons != undefined) {
		for(var i=0; i<data.reasons.length; i++) {
			if (data.reasons[i]["@selected"] == "true") {
				selectReason.append('<option value='+data.reasons[i]["@code"]+' selected=\"selected\">'+MGW_RES.get("gw_settings_absence_period_reason_" + parseInt(data.reasons[i]["@code"], 10) + "_label")+'</option>');
			}
			else { 
				selectReason.append('<option value='+data.reasons[i]["@code"]+'>'+MGW_RES.get("gw_settings_absence_period_reason_" + parseInt(data.reasons[i]["@code"], 10) + "_label")+'</option>');
			}
		}
	} 
	else {
		selectReason.append("<option value='01'>" + MGW_RES.get("gw_settings_absence_period_reason_1_label") + "</option>");
		selectReason.append("<option value='02'>" + MGW_RES.get("gw_settings_absence_period_reason_2_label") + "</option>");
		selectReason.append("<option value='03'>" + MGW_RES.get("gw_settings_absence_period_reason_3_label") + "</option>");
		selectReason.append("<option value='04'>" + MGW_RES.get("gw_settings_absence_period_reason_4_label") + "</option>");
		selectReason.append("<option value='05'>" + MGW_RES.get("gw_settings_absence_period_reason_5_label") + "</option>");
		selectReason.append("<option value='06'>" + MGW_RES.get("gw_settings_absence_period_reason_6_label") + "</option>");
		selectReason.append("<option value='07'>" + MGW_RES.get("gw_settings_absence_period_reason_7_label") + "</option>");
		selectReason.append("<option value='08'>" + MGW_RES.get("gw_settings_absence_period_reason_8_label") + "</option>");
		selectReason.append("<option value='09'>" + MGW_RES.get("gw_settings_absence_period_reason_9_label") + "</option>");
		selectReason.append("<option value='10'>" + MGW_RES.get("gw_settings_absence_period_reason_10_label") + "</option>");
		selectReason.append("<option value='11'>" + MGW_RES.get("gw_settings_absence_period_reason_11_label") + "</option>");
		selectReason.append("<option value='12'>" + MGW_RES.get("gw_settings_absence_period_reason_12_label") + "</option>");
		selectReason.append("<option value='13'>" + MGW_RES.get("gw_settings_absence_period_reason_13_label") + "</option>");
		selectReason.append("<option value='14'>" + MGW_RES.get("gw_settings_absence_period_reason_14_label") + "</option>");
	}
	
	var tempTime = getHour();
	var nowTime = tempTime < 10 ? "0"+tempTime : tempTime;
	
	// 시간 셋팅
	for(var i = 0; i < 24; i++){
		var timeVal = i<10 ? "0"+i : i;
		var timeStr = i<10 ? "0"+i+":00" : i+":00";
		
		selectdtStartTime.append("<option value='" + timeVal + "'>" + timeStr + "</option>");
		selectdtEndTime.append("<option value='" + timeVal + "'>" + timeStr + "</option>");
	}
	
	// 부재 설정된 경우
	if (data.absent == "1") {
		var dtstart = getAbsenceDate(data.dtstart, "time");
		var dtend = getAbsenceDate(data.dtend, "time");
		
// view.find("#absent1").attr("checked", true);
// toggleAbsence('enable');
		
		// 설정된 날짜 셋팅
		if (data.dtstart != undefined) {
			var date1 = getAbsenceDate(data.dtstart, "date");
			
			view.find("#dtstart_date").val(date1);
			view.find("#txt_dtstart_date").val(date1);
		}
		if (data.dtend != undefined) {
			var date2 = getAbsenceDate(data.dtend, "date");
			
			view.find("#dtend_date").val(date2);
			view.find("#txt_dtend_date").val(date2);
		}
		
		view.find("#list_mail_absence").find(".grouping_middle").show();
		view.find("#list_sanc_absence").find(".grouping_middle").show();
		
		// 설정된 시간 셋팅
		selectdtStartTime.val(dtstart);
		selectdtEndTime.val(dtend);
		
		if (data.mail != undefined && data.mail.alternateRecipient["@id"] != "") {
			var targetMailUser = view.find(".targetUserList").filter("#mail_alt_rcpt_id");
			var id = data.mail.alternateRecipient["@id"];
			var name = data.mail.alternateRecipient["#text"];
			
			if (targetMailUser.attr("style") != undefined)
				targetMailUser.removeAttr("style");
			targetMailUser.append(renderTargetRecv(GW_CONTROLLER_ORG.callbackId, 'user', id, name, undefined));
		}
		
		view.find("#mail_reply_text").val(data.mail.replyText);
		
		if (data.sanction != undefined && data.sanction.alternateSigner["@id"] != "") {
			var targetSancUser = view.find(".targetUserList").filter("#sanc_alt_signer_id");
			var id = data.sanction.alternateSigner["@id"];
			var name = data.sanction.alternateSigner["#text"];
			
			if (targetSancUser.attr("style") != undefined)
				targetSancUser.removeAttr("style");
			
			targetSancUser.append(renderTargetRecv(GW_CONTROLLER_ORG.callbackId, 'user', id, name, undefined));
		}
		
		var sancHandling1 = view.find("#sanc_handling1");
		var sancHandling2 = view.find("#sanc_handling2");
		
		// 결재후 진행문서
		switch(data.sanction.handling1.trim()) {
			case "hold" :
				sancHandling1[0].selectedIndex = 0;
				break;
			case "delegate" :
				sancHandling1[0].selectedIndex = 1;
				break;
			case "pass" :
				sancHandling1[0].selectedIndex = 2;
				break;
		}
		// 결재후 완료문서
		switch(data.sanction.handling2.trim()) {
			case "hold" :
				sancHandling2[0].selectedIndex = 0;
				break;
			case "delegate" :
				sancHandling2[0].selectedIndex = 1;
				break;
		}
		if(checkAbsenceDateTimeNow(data.dtend)){
			disabledAbsence2();
			view.find("#isUserSelect").val("false");
		}else{
			if(GWPlugin.usePlugin){
				NAVIBAR_DEF.phone.settings2_absenceadd.right=[1, [MGW_RES.get("gw_settings_absence_save_label")], ["javascript:setAbsence2();"]];
				NAVIBAR_DEF.pad.settings2_absenceadd.right=[1, [MGW_RES.get("gw_settings_absence_save_label")], ["javascript:setAbsence2();"]];
				APP_CONTROLLER.setNavibar("settings2_absenceadd");
			}
		}
	}else {
// view.find("#dtstart_date").datebox("disable");
// view.find("#dtend_date").datebox("disable");
// view.find("#absent2").attr("checked", true);
		// 오늘 날짜 & 시간 셋팅
		view.find("#dtstart_date").val(getToday());
		view.find("#dtend_date").val(nextWeek(getToday()));
		view.find("#txt_dtstart_date").val(getToday());
		view.find("#txt_dtend_date").val(nextWeek(getToday()));
		
		selectdtStartTime.val(nowTime + 1);
		selectdtEndTime.val(nowTime + 1);
		doSetDatebox();
		if(GWPlugin.usePlugin){
			NAVIBAR_DEF.phone.settings2_absenceadd.right=[1, [MGW_RES.get("gw_settings_absence_save_label")], ["javascript:setAbsence2();"]];
			NAVIBAR_DEF.pad.settings2_absenceadd.right=[1, [MGW_RES.get("gw_settings_absence_save_label")], ["javascript:setAbsence2();"]];
			APP_CONTROLLER.setNavibar("settings2_absenceadd");
		}
	}
	view.find("#list_set_absence").find("li").eq(1).find(".grouping_middle").show();
	view.find("#list_set_absence").find("li").eq(2).find(".grouping_middle").show();
}

/**
 * 부재 삭제 list에서 삭제시에는 체크박스를 기준으로 삭제 하며, 조회 화면에서 삭제 시 조회된 내용만 삭제한다.
 */
function deleteSettings2(type) {
	var view = $.mobile.activePage;
	var param = {};
	var absenceIds = "";
	var absenceCnt = 0;
	LOG(view.prop("id"));
	if (!(view.prop("id") == "settings2_absencelist") && !(view.prop("id") == "settings2_absenceadd"))
		return;
	// 리스트 삭제 시
	if (type == "list"){
		view.find("#list li").find("[name=checkAbsence]:checked").each(function(){
	// GW2_CONTROLLER_SCHEDULE.selectedEquipmentIds += $(this).prop("value") +
	// ";";
			if(!absenceCnt)
				absenceIds += $(this).prop("value");
			else
				absenceIds += "," + $(this).prop("value");
			
			absenceCnt++;
		});
		// 삭제 가능 여부 확인
		if(absenceCnt == 0){
			alert(MGW_RES.get("gw_msg_absence_minimum_one_select"));
			return;
		}
		
		param["absid"] = absenceIds;
		
		if (confirm(absenceCnt + MGW_RES.get("gw_common_piece_label") + " " + MGW_RES.get("gw_msg_common_confirm_delete"))) {
			GW_PROXY.invokeOpenAPI("settings", "absencedelete", param, function(data) {
				LOG(data);
				if (data.status == "0" || data.status == "success") {
					alert(MGW_RES.get("gw_msg_mail_delete_success"));
					getSettings2AbsenceList();
				}
			});
		}
	}
	
	else if(type == "add"){
		var absId = view.find("#absId").val();
		param["absid"] = absId;
		LOG(absId);
		
		if (confirm(MGW_RES.get("gw_msg_common_confirm_delete"))) {
			GW_PROXY.invokeOpenAPI("settings", "absencedelete", param, function(data) {
				LOG(data);
				if (data.status == "0" || data.status == "success") {
					alert(MGW_RES.get("gw_msg_mail_delete_success"));
					if (GWPlugin.usePlugin) { // APP, popup인 경우
						NAVIBAR_DEF.phone.settings2_absenceadd.right=[0,[],[]];
						NAVIBAR_DEF.pad.settings2_absenceadd.right=[0,[],[]];
						TOOLBAR_DEF.settings2_absenceadd = [0, [], [], []];
						LOG(TOOLBAR_DEF.settings2_absenceadd);
						APP_CONTROLLER.setNavibar("settings2_absenceadd");
						GWPlugin.closePopupViewer("javascript:showSettings2Absence();", true);
					}
					else {
						PAGE_CONTROLLER.goBack();
					}
				}
			});
		}
	}
}

// 수신자 선택 화면 이동
function openUserSelect2(recvType) {
	var view = $.mobile.activePage;
	
	if(view.find("#isUserSelect").val() == "true"){
		var recvlist = view.find("#" + recvType).html().trim();
		showOrgSelect("settings", recvType, recvlist);
	}	
}

/*
 * 부재 추가/수정 popup 취소
 */
function cancel_addSettings2() {	
	if (confirm(MGW_RES.get("gw_msg_absence_cancel_add_absence"))) {
		if (GWPlugin.usePlugin) { // APP, popup인 경우
			NAVIBAR_DEF.phone.settings2_absenceadd.right=[0,[],[]];
			NAVIBAR_DEF.pad.settings2_absenceadd.right=[0,[],[]];
			TOOLBAR_DEF.settings2_absenceadd = [0, [], [], []];
			APP_CONTROLLER.setNavibar("settings2_absenceadd");
			GWPlugin.closePopupViewer("", false);
		}
		else {
			PAGE_CONTROLLER.goBack();
		}
	}
}

/*
 * 부재 목록 체크박스
 */
function toggle2Absence(absid){
	var view = $.mobile.activePage;
	if (view.find('#' + absid).attr('checked') != "checked") {
		view.find('#' + absid).attr("checked", "checked");
	}else{
		view.find('#' + absid).removeAttr("checked");
	}
}

$("#settings2_absenceadd #set_dtstart_date").live("click", function() {
	if ($("#settings2_absenceadd #isUserSelect").val() == "false")
		return;
	
	$("#settings2_absenceadd #dtstart_date").datebox("open");
});

$("#settings2_absenceadd #set_dtend_date").live("click", function() {
	if ($("#settings2_absenceadd #isUserSelect").val() == "false")
		return;
	
	$("#settings2_absenceadd #dtend_date").datebox("open");
});

function disabledAbsence2() {
	var view = $.mobile.activePage;
	var disabled = "disabled";
	
	view.find("#dtstart_date").datebox(false);
	view.find("#dtend_date").datebox(false);
	
	
	if (view.find("#list_mail_absence").find(".targetUserList").html().trim() != "")
		view.find("#list_mail_absence").find(".targetUserList").show();
	
	if (view.find("#list_sanc_absence").find(".targetUserList").html().trim() != "")
		view.find("#list_sanc_absence").find(".targetUserList").show();
	
	view.find("#saveMailUrl").val(view.find("#list_mail_absence").find(".targetUserList").find('a').attr("href"));
	view.find("#saveSancUrl").val(view.find("#list_sanc_absence").find(".targetUserList").find('a').attr("href"));
	view.find("#list_mail_absence").find(".targetUserList").find('a').removeAttr("href");
	view.find("#list_sanc_absence").find(".targetUserList").find('a').removeAttr("href");
	
	view.find("#dtstart_time").attr("disabled", disabled);
	view.find("#dtend_time").attr("disabled", disabled);
	view.find("#txt_dtstart_date").attr("disabled", disabled);
	view.find("#txt_dtend_date").attr("disabled", disabled);
	
	view.find("#reason_code").attr("disabled", disabled);
	view.find("#mail_reply_text").attr("disabled", disabled);
	view.find("#sanc_handling1").attr("disabled", disabled);
	view.find("#sanc_handling2").attr("disabled", disabled);
	
	if(GWPlugin.usePlugin){
		NAVIBAR_DEF.phone.settings2_absenceadd.right=[1, [MGW_RES.get("gw_settings_absence_copy_label")], ["javascript:enabledAbsence2();"]];
		NAVIBAR_DEF.pad.settings2_absenceadd.right=[1, [MGW_RES.get("gw_settings_absence_copy_label")], ["javascript:enabledAbsence2();"]];
		APP_CONTROLLER.setNavibar("settings2_absenceadd");
	}else{
		view.find("#absenceButton").text(MGW_RES.get("gw_settings_absence_copy_label"));
		view.find("#absenceButton").attr("href", "javascript:enabledAbsence2();");
// view.find('#absenceButton').button("refresh");
	}
}

function enabledAbsence2() {
	var view = $.mobile.activePage;
	var selectdtStartTime = view.find("#dtstart_time");
	var selectdtEndTime = view.find("#dtend_time");
	var tempTime = getHour();
	var nowTime = tempTime < 10 ? "0"+tempTime : tempTime;
	view.find("#isCopy").val("true");
	
	view.find("#dtstart_date").datebox(true);
	view.find("#dtend_date").datebox(true);
	
	view.find("#dtstart_date").val(getToday());
	view.find("#dtend_date").val(nextWeek(getToday()));
	view.find("#txt_dtstart_date").val(getToday());
	view.find("#txt_dtend_date").val(nextWeek(getToday()));
	
	selectdtStartTime.val(nowTime + 1);
	selectdtEndTime.val(nowTime + 1);
	doSetDatebox();
// if (view.find("#list_mail_absence").find(".targetUserList").html().trim() !=
// "")
	view.find("#list_mail_absence").find(".targetUserList").show();
	
// if (view.find("#list_sanc_absence").find(".targetUserList").html().trim() !=
// "")
	view.find("#list_sanc_absence").find(".targetUserList").show();
	
// view.find("#list_mail_absence").find(".targetUserList").hide();
// view.find("#list_sanc_absence").find(".targetUserList").hide();
		
	view.find("#list_mail_absence").find(".targetUserList").find('a').attr("href", view.find("#saveMailUrl").val());
	view.find("#list_sanc_absence").find(".targetUserList").find('a').attr("href", view.find("#saveSancUrl").val());
	
	view.find("#dtstart_time").removeAttr("disabled");
	view.find("#dtend_time").removeAttr("disabled");
	view.find("#txt_dtstart_date").removeAttr("disabled");
	view.find("#txt_dtend_date").removeAttr("disabled");
	
	view.find("#reason_code").removeAttr("disabled");
	view.find("#mail_reply_text").removeAttr("disabled");
	view.find("#sanc_handling1").removeAttr("disabled");
	view.find("#sanc_handling2").removeAttr("disabled");
	
	view.find("#isUserSelect").val("true");
	if(GWPlugin.usePlugin){
		NAVIBAR_DEF.phone.settings2_absenceadd.title = MGW_RES.get("gw_settings_absence_absadd_label");
		NAVIBAR_DEF.pad.settings2_absenceadd.title = MGW_RES.get("gw_settings_absence_absadd_label");
		NAVIBAR_DEF.phone.settings2_absenceadd.right=[1, [MGW_RES.get("gw_settings_absence_save_label")], ["javascript:setAbsence2();"]];
		NAVIBAR_DEF.pad.settings2_absenceadd.right=[1, [MGW_RES.get("gw_settings_absence_save_label")], ["javascript:setAbsence2();"]];
		APP_CONTROLLER.setNavibar("settings2_absenceadd");
	}else{
		view.find("#absenceButton").html(MGW_RES.get("gw_settings_absence_save_label"));
		view.find("#absenceButton").attr("href", "javascript:setAbsence2();");
// view.find('#absenceButton *[data-role=button]' ).button('refresh');
	}
	view.find("#isModify").val("false");
}var GW_CONTROLLER_SIGN = {
	linkdocIdx: 0,
	apprProfileData: undefined,
	apprProfileLoaded: false,
	
	initApprProfileg: function(apprProfileData) {
		GW_CONTROLLER_SIGN.apprProfileLoaded = true;
		GW_CONTROLLER_SIGN.apprProfileData = apprProfileData;
	}
};

function getApprProfile(callback) {
	if (!GW_CONTROLLER_SIGN.apprProfileLoaded) {
		GW_PROXY.invokeOpenAPI("settings", "apprprofile", {}, function(data) {
			GW_CONTROLLER_SIGN.initApprProfileg(data);
			callback();
		});
	} else {
		callback();
	}

}

function getApprProfileValue(key) {
	return (GW_CONTROLLER_SIGN.apprProfileData[key] == undefined ? false : GW_CONTROLLER_SIGN.apprProfileData[key]);
}

// 결재목록
function showSignList(apiCode) {
	var succfn = function() {
		getSignList(apiCode, 1);
	};
	PAGE_CONTROLLER.showPage("sign_list", succfn);

	var title = MGW_RES.get("gw_sign_waitlist_label");
	
	if (apiCode == "nowlist") {
		title = MGW_RES.get("gw_sign_nowlist_label");
	} else if (apiCode == "receiptwaitlist") {
		title = MGW_RES.get("gw_sign_receiptwaitlist_label");
	} else if (apiCode == "gongramwaitlist") {
		title = MGW_RES.get("gw_sign_gongramwaitlist_label");
	} else if (apiCode == "gongramcompletelist") {
		title = MGW_RES.get("gw_sign_gongramcompletelist_label");
	} else if (apiCode == "userprocessedlist") {
		title = MGW_RES.get("gw_sign_userprocessedlist_label");
	} else if (apiCode == "completelist") {
		title = MGW_RES.get("gw_sign_completelist_label");
	} else if (apiCode == "mycompletelist") {
		title = MGW_RES.get("gw_sign_mycompletelist_label");
	} else if (apiCode == "dispatchlist") {
		title = MGW_RES.get("gw_sign_dispatchlist_label");
	}

	changeTitle(title);
}

// 결재 본문 (상세보기) 팝업
function showSignDetailsPopup(link, applId, apprId, participantApprtype, apprStatus,
		attachCnt, commentCk, postponeCk, externalDoc, summaryDoc, wordtype, additionalOfficeUserId, participantId, linkdocIdx) {
	showSignDetails(link, applId, apprId, participantApprtype, apprStatus,
			attachCnt, commentCk, postponeCk, externalDoc, summaryDoc, wordtype, additionalOfficeUserId, participantId, linkdocIdx, "", "", "", true);	
}

// 결재 본문 (상세보기)
function showSignDetails(link, applId, apprId, participantApprtype, apprStatus,
		attachCnt, commentCk, postponeCk, externalDoc, summaryDoc, wordtype, additionalOfficeUserId, participantId, linkdocIdx, 
		examId, examDeptId, enforceType, isPopup) {
	if(isPopup == true) {
		NAVIBAR_DEF.phone.sign_details.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
		NAVIBAR_DEF.pad.sign_details.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
	} else {
		NAVIBAR_DEF.phone.sign_details.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], 
		                                       ['javascript:goList();', 'javascript:goMenu();']];
		NAVIBAR_DEF.pad.sign_details.left = [0, [], []];
	} 	
	
	GW_CONTROLLER_SIGN.linkdocIdx = parseInt(linkdocIdx||0);
	if (GW_CONTROLLER_SIGN.linkdocIdx == 0 || GW_CONTROLLER_SIGN.linkdocIdx == NaN || GW_CONTROLLER_SIGN.linkdocIdx == undefined) {	
		
		if(isPopup == true) {
			NAVIBAR_DEF.phone.sign_linkdoc_1.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
			NAVIBAR_DEF.pad.sign_linkdoc_1.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
			NAVIBAR_DEF.phone.sign_linkdoc_2.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
			NAVIBAR_DEF.pad.sign_linkdoc_2.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
			NAVIBAR_DEF.phone.sign_linkdoc_3.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
			NAVIBAR_DEF.pad.sign_linkdoc_3.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];			
			NAVIBAR_DEF.phone.sign_linkdoc_4.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];
			NAVIBAR_DEF.pad.sign_linkdoc_4.left = [1, [MGW_RES.get("gw_common_close_label")], ['javascript:closePopupViewer();']];			
			
		} else {
			NAVIBAR_DEF.phone.sign_linkdoc_1.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn], 
			                                       ['javascript:PAGE_CONTROLLER.goBackLinkdocList(1);', 'javascript:goMenu();']];
			NAVIBAR_DEF.pad.sign_linkdoc_1.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn],
			                                       ['javascript:PAGE_CONTROLLER.goBackLinkdocList(1);', 'javascript:goMenu();']];
			NAVIBAR_DEF.phone.sign_linkdoc_2.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn],
			                                         ['javascript:PAGE_CONTROLLER.goBackLinkdocList(2);', 'javascript:goMenu();']];
			NAVIBAR_DEF.pad.sign_linkdoc_2.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn],
			                                       ['javascript:PAGE_CONTROLLER.goBackLinkdocList(2);', 'javascript:goMenu();']];
			NAVIBAR_DEF.phone.sign_linkdoc_3.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn],
			                                         ['javascript:PAGE_CONTROLLER.goBackLinkdocList(3);', 'javascript:goMenu();']];
			NAVIBAR_DEF.pad.sign_linkdoc_3.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn],
			                                       ['javascript:PAGE_CONTROLLER.goBackLinkdocList(3);', 'javascript:goMenu();']];
			NAVIBAR_DEF.phone.sign_linkdoc_4.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn],
			                                         ['javascript:PAGE_CONTROLLER.goBackLinkdocList(4);', 'javascript:goMenu();']];
			NAVIBAR_DEF.pad.sign_linkdoc_4.left = [2, [APP_CONTROLLER.backBtn, APP_CONTROLLER.mainMenuBtn],
			                                       ['javascript:PAGE_CONTROLLER.goBackLinkdocList(4);', 'javascript:goMenu();']];			
			
		} 
		
		// 결재 툴바 버튼 초기화
		TOOLBAR_DEF.sign_details = [0, [], [], []];
		if(GW_OpenAPI.appr_linkdoc_use){
			TOOLBAR_DEF.sign_linkdoc_1 = [0, [], [], []];
			TOOLBAR_DEF.sign_linkdoc_2 = [0, [], [], []];
			TOOLBAR_DEF.sign_linkdoc_3 = [0, [], [], []];
			TOOLBAR_DEF.sign_linkdoc_4 = [0, [], [], []];
		}
	}
	
	var type = getApplId(applId);
	var externalDocFlag = false;
	var summaryDocFlag = false;
	var withdrawApprFlag = true;
	var linkdocFlag = false;
	
	// 전자문서(externalDoc : 0), 비전자 문서(externalDoc : 1 or 2)
	if (externalDoc == "1" || externalDoc == "2" || externalDoc == 1 || externalDoc == 2) {
		externalDocFlag = true;
	}
	// 요약전 있음(summaryDoc: 1)
	if (summaryDoc == "1" || summaryDoc == 1) {
		summaryDocFlag = true;
	}

	if (externalDoc == -1 || summaryDoc == -1 || externalDoc == undefined || summaryDoc == undefined || externalDoc == "" || summaryDoc == "") {
		withdrawApprFlag = false;
	}

	if (commentCk == true || commentCk == "true") {
		commentCk = true;
	} else {
		commentCk = false;
	}

	if (postponeCk == true || postponeCk == "true") {
		postponeCk = true;
	} else {
		postponeCk = false;
	}
	
// console.log("type[" + type + "] participantApprtype[" + participantApprtype +
// "] apprStatus[" + apprStatus + "]");
// console.log("externalDoc[" + externalDoc + "] summaryDocFlag[" + summaryDoc +
// "] wordtype[" + wordtype + "] commentCk[" + commentCk + "]");
// console.log("externalDocFlag[" + externalDocFlag + "] summaryDocFlag[" +
// summaryDocFlag + "] withdrawApprFlag[" + withdrawApprFlag + "]");
	
	var renderSignBodyFN = function(useLinkDoc, showApproveBtn) {
		linkdocFlag = useLinkDoc;
		
		NAVIBAR_DEF.phone.sign_details.right=[0, [], []];
		NAVIBAR_DEF.pad.sign_details.right=[0, [], []];
		
		// 관련문서
		if (GW_CONTROLLER_SIGN.linkdocIdx > 0) {
			if (commentCk)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_comment_label"), "btn_tool_opinion.png", "javascript:showSignCommentList();");
			if (summaryDocFlag)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_summary_label"), "btn_tool_summary.png", "javascript:showSummaryDoc();");
			if (linkdocFlag)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_linkdoc_label"), "btn_tool_linkdoc.png", "javascript:showLinkDocList();");
			addSignToolbarBtn(MGW_RES.get("gw_sign_approve_flow_label"), "btn_tool_consult.png", "javascript:showApprfolw();");
		}
		// 결재대기/공람대기함 버튼 셋팅
		else if (type == "2010" || type == "3020") {
			// 참조
			if (participantApprtype == "4112") {
				if (showApproveBtn) {
					addSignToolbarBtn(MGW_RES.get("gw_sign_confirm_label"), "btn_tool_approval.png", "javascript:apprApprove('confirm', false);");
				}
				if(!GWPlugin.usePlugin)		// web test!
					addSignToolbarBtn(MGW_RES.get("gw_sign_write_comment_label"), "btn_tool_opinion.png", "javascript:toggleWriteComment(true, false);");
				else if(GW_OpenAPI.appr_approve_input_comment_use == "0" || GW_OpenAPI.appr_approve_input_comment_use == "1")
					addSignToolbarBtn(MGW_RES.get("gw_sign_write_comment_label"), "btn_tool_opinion.png", "javascript:toggleWriteComment(true, false);");
				if (commentCk)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_comment_label"), "btn_tool_opinion.png", "javascript:showSignCommentList();");
				if (summaryDocFlag)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_summary_label"), "btn_tool_summary.png", "javascript:showSummaryDoc();");
				if (linkdocFlag)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_linkdoc_label"), "btn_tool_linkdoc.png", "javascript:showLinkDocList();");
				addSignToolbarBtn(MGW_RES.get("gw_sign_approve_flow_label"), "btn_tool_consult.png", "javascript:showApprfolw();");
			}
			else if (participantApprtype == "6912") {
				if (showApproveBtn) {
					addSignToolbarBtn(MGW_RES.get("gw_sign_gongram_label"), "btn_tool_approval.png", "javascript:apprApprove('gongram', false);");
				}
				if (commentCk)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_comment_label"), "btn_tool_opinion.png", "javascript:showSignCommentList();");
				if (summaryDocFlag)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_summary_label"), "btn_tool_summary.png", "javascript:showSummaryDoc();");
				if (linkdocFlag)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_linkdoc_label"), "btn_tool_linkdoc.png", "javascript:showLinkDocList();");
				addSignToolbarBtn(MGW_RES.get("gw_sign_approve_flow_label"), "btn_tool_consult.png", "javascript:showApprfolw();");
			}
			else if (apprStatus == "256") {
				if (showApproveBtn) {
					addSignToolbarBtn(MGW_RES.get("gw_sign_confirm_label"), "btn_tool_approval.png", "javascript:apprApprove('confirm', false);");
				}
				if (commentCk)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_comment_label"), "btn_tool_opinion.png", "javascript:showSignCommentList();");
				if (summaryDocFlag)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_summary_label"), "btn_tool_summary.png", "javascript:showSummaryDoc();");
				if (linkdocFlag)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_linkdoc_label"), "btn_tool_linkdoc.png", "javascript:showLinkDocList();");
				addSignToolbarBtn(MGW_RES.get("gw_sign_approve_flow_label"), "btn_tool_consult.png", "javascript:showApprfolw();");
				
			}
			else {
				if (showApproveBtn) {
					addSignToolbarBtn(MGW_RES.get("gw_sign_approve_label"), "btn_tool_approval.png", "javascript:apprApprove('approve', false);");
					addSignToolbarBtn(MGW_RES.get("gw_sign_reject_label"), "btn_tool_return.png", "javascript:apprApprove('reject', false);");
					// 결재, 대결만 전결 가능
					if(GW_OpenAPI.appr_approve_junkyul_use == "true" &&	(participantApprtype == "4097" || participantApprtype == "4100")) {
						addSignToolbarBtn(MGW_RES.get("gw_sign_junkyul_label"), "btn_tool_approval.png", "javascript:apprApprove('junkyul', false);");
					}
				}
				if (postponeCk)
					addSignToolbarBtn(MGW_RES.get("gw_sign_postpone_label"), "btn_tool_receive.png", "javascript:apprApprove('postpone', false);");
				if(!GWPlugin.usePlugin)		// web test!
					addSignToolbarBtn(MGW_RES.get("gw_sign_write_comment_label"), "btn_tool_opinion.png", "javascript:toggleWriteComment(true, false);");
				else if(GW_OpenAPI.appr_approve_input_comment_use == "0" || GW_OpenAPI.appr_approve_input_comment_use == "1")
					addSignToolbarBtn(MGW_RES.get("gw_sign_write_comment_label"), "btn_tool_opinion.png", "javascript:toggleWriteComment(true, false);");
				if (commentCk)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_comment_label"), "btn_tool_opinion.png", "javascript:showSignCommentList();");
				if (summaryDocFlag)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_summary_label"), "btn_tool_summary.png", "javascript:showSummaryDoc();");
				if (linkdocFlag)
					addSignToolbarBtn(MGW_RES.get("gw_sign_show_linkdoc_label"), "btn_tool_linkdoc.png", "javascript:showLinkDocList();");
				addSignToolbarBtn(MGW_RES.get("gw_sign_approve_flow_label"), "btn_tool_consult.png", "javascript:showApprfolw();");
				if (GWPlugin.usePlugin && GW_OpenAPI.appr_instructions_use != undefined && GW_OpenAPI.appr_instructions_use == true) {
					NAVIBAR_DEF.phone.sign_details.right=[1, ["COMMENT"], []];
					NAVIBAR_DEF.pad.sign_details.right=[1, ["COMMENT"], []];
					APP_CONTROLLER.setNavibar("sign_details");
				}				
			}
		}
		// 결재 진행함 버튼 셋팅
		else if (type == "2020") {
			if (commentCk)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_comment_label"), "btn_tool_opinion.png", "javascript:showSignCommentList();");
			if (summaryDocFlag)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_summary_label"), "btn_tool_summary.png", "javascript:showSummaryDoc();");
			if (linkdocFlag)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_linkdoc_label"), "btn_tool_linkdoc.png", "javascript:showLinkDocList();");
			addSignToolbarBtn(MGW_RES.get("gw_sign_approve_flow_label"), "btn_tool_consult.png", "javascript:showApprfolw();");
			if (withdrawApprFlag)
				addSignToolbarBtn(MGW_RES.get("gw_sign_withdraw_label"), "btn_tool_collect.png", "javascript:withdrawAppr();");
			if(GW_OpenAPI.appr_approve_recall_use == "true")
				addSignToolbarBtn(MGW_RES.get("gw_sign_cancel_label"), "btn_tool_collect.png", "javascript:cancelAppr();");
		}
		// 접수 대기함 버튼 셋팅
		else if (type == "5010") {
			if (commentCk)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_comment_label"), "btn_tool_opinion.png", "javascript:showSignCommentList();");
			addSignToolbarBtn(MGW_RES.get("gw_sign_approve_flow_label"), "btn_tool_consult.png", "javascript:showApprfolw();");
		}
		// 발송 처리함 버튼 셋팅
		else if (type == "4010") {
			addSignToolbarBtn(MGW_RES.get("gw_sign_dispatch_label"), "btn_tool_approval.png", "javascript:apprApprove('dispatch', false);");
		}		
		// 기타 버튼 셋팅
		else {
			if (commentCk)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_comment_label"), "btn_tool_opinion.png", "javascript:showSignCommentList();");
			if (summaryDocFlag)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_summary_label"), "btn_tool_summary.png", "javascript:showSummaryDoc();");
			if (linkdocFlag)
				addSignToolbarBtn(MGW_RES.get("gw_sign_show_linkdoc_label"), "btn_tool_linkdoc.png", "javascript:showLinkDocList();");
			addSignToolbarBtn(MGW_RES.get("gw_sign_approve_flow_label"), "btn_tool_consult.png", "javascript:showApprfolw();");
		}
		
		var pageName = "";
		
		if (GW_CONTROLLER_SIGN.linkdocIdx == 0)
			pageName = "sign_details";
		else
			pageName = "sign_linkdoc_" + GW_CONTROLLER_SIGN.linkdocIdx;
		
		PAGE_CONTROLLER.showPage(pageName, function() {
			getSignDetails(link, type, apprId, participantApprtype, apprStatus, attachCnt, commentCk, postponeCk, externalDocFlag, 
					summaryDocFlag, wordtype, additionalOfficeUserId, participantId, linkdocFlag, examId, examDeptId, enforceType);
		});
	};

	var checkIpBeforeRenderSignBody = function(useLinkDoc) {
		if( GW_OpenAPI.ip_checkrange_use ) {
			GW_PROXY.invokeOpenAPI("common", "ipcheckrange", {}, function(data) {
				if( data.message == "false" )
					renderSignBodyFN(useLinkDoc, false);
				else 
					renderSignBodyFN(useLinkDoc, true);
			});	
		} else {
			renderSignBodyFN(useLinkDoc, true);
		}
	};

	if(GW_OpenAPI.appr_linkdoc_use == true || GW_OpenAPI.appr_linkdoc_use == "true"){
		getLinkDocCount(apprId, checkIpBeforeRenderSignBody);
	} 
	else {
		checkIpBeforeRenderSignBody(false);
	}
}

function addSignToolbarBtn(label, imgsrc, fn) {
	// console.log("addSignToolbarBtn label[" + label + "] imgsrc[" + imgsrc+ "]
	if (GW_CONTROLLER_SIGN.linkdocIdx == 0) {
		TOOLBAR_DEF.sign_details[0] = TOOLBAR_DEF.sign_details[0] + 1;
		TOOLBAR_DEF.sign_details[1].push(label);
		TOOLBAR_DEF.sign_details[2].push(imgsrc);
		TOOLBAR_DEF.sign_details[3].push(fn);
	} else if (GW_CONTROLLER_SIGN.linkdocIdx == 1) {
		TOOLBAR_DEF.sign_linkdoc_1[0] = TOOLBAR_DEF.sign_linkdoc_1[0] + 1;
		TOOLBAR_DEF.sign_linkdoc_1[1].push(label);
		TOOLBAR_DEF.sign_linkdoc_1[2].push(imgsrc);
		TOOLBAR_DEF.sign_linkdoc_1[3].push(fn);
	} else if (GW_CONTROLLER_SIGN.linkdocIdx == 2) {
		TOOLBAR_DEF.sign_linkdoc_2[0] = TOOLBAR_DEF.sign_linkdoc_2[0] + 1;
		TOOLBAR_DEF.sign_linkdoc_2[1].push(label);
		TOOLBAR_DEF.sign_linkdoc_2[2].push(imgsrc);
		TOOLBAR_DEF.sign_linkdoc_2[3].push(fn);
	} else if (GW_CONTROLLER_SIGN.linkdocIdx == 3) {
		TOOLBAR_DEF.sign_linkdoc_3[0] = TOOLBAR_DEF.sign_linkdoc_3[0] + 1;
		TOOLBAR_DEF.sign_linkdoc_3[1].push(label);
		TOOLBAR_DEF.sign_linkdoc_3[2].push(imgsrc);
		TOOLBAR_DEF.sign_linkdoc_3[3].push(fn);
	} else if (GW_CONTROLLER_SIGN.linkdocIdx == 4) {
		TOOLBAR_DEF.sign_linkdoc_4[0] = TOOLBAR_DEF.sign_linkdoc_4[0] + 1;
		TOOLBAR_DEF.sign_linkdoc_4[1].push(label);
		TOOLBAR_DEF.sign_linkdoc_4[2].push(imgsrc);
		TOOLBAR_DEF.sign_linkdoc_4[3].push(fn);
	} else {
		TOOLBAR_DEF.sign_details[0] = TOOLBAR_DEF.sign_details[0] + 1;
		TOOLBAR_DEF.sign_details[1].push(label);
		TOOLBAR_DEF.sign_details[2].push(imgsrc);
		TOOLBAR_DEF.sign_details[3].push(fn);
	}
}

// 의견 보기
function showSignCommentList() {
	var view = $.mobile.activePage;
	var apprid = view.find("#apprid").val();

	var succfn = function() {
		getSignCommentList(apprid);
	};
	PAGE_CONTROLLER.showPage("sign_commentlist", succfn);
}

// 결재진행 상태 조회
function showApprfolw() {
	var view = $.mobile.activePage;
	var apprid = view.find("#apprid").val();

	var succfn = function() {
		getApprfolw(apprid);
	};
	PAGE_CONTROLLER.showPage("sign_apprflow", succfn);
}

// 요약전
function showSummaryDoc() {
	var view = $.mobile.activePage;
	var apprid = view.find("#apprid").val();
	var wordtype = view.find("#wordtype").val();

	PAGE_CONTROLLER.showPage("sign_summarydoc", function() {
		getSummaryDoc(apprid, wordtype);
	});
}

// 관련문서 목록
function showLinkDocList() {
	if (GW_CONTROLLER_SIGN.linkdocIdx > 4) {
		alert(MGW_RES.get("gw_msg_sign_limitread_apprlinkdoc"));
		return;
	}
	
	var view = $.mobile.activePage;
	var apprid = view.find("#apprid").val();
	var applid = view.find("#applid").val();
	var pageName = "sign_linkdoclist_" + GW_CONTROLLER_SIGN.linkdocIdx;
	
	PAGE_CONTROLLER.showPage(pageName, function() {
		getLinkDocList(apprid, applid);
	});
}


// 의견 저장
function setSignComment(data, flag) {
	var view = $.mobile.activePage;
	var comment = decodeURIComponent(data);
	if ($.trim(comment) == "") {
		comment = "";
	}
	view.find("#commentBody").val(comment);
	view.find("#intoDocumentCommentFlag").val(flag);
	if(GW_OpenAPI.appr_approve_status == true) {
		apprApprove(view.find("#apprValue").val(), true);
	}
}

// 의견 작성
function toggleWriteComment(flag, bDoApprApprove) {
	var view = $.mobile.activePage;
	var hasComment = view.find("#hasComment").val();
	view.find("#writeCommentFlag").val(flag);

	var wordType = view.find("#wordtype").val();

	GW_OpenAPI.appr_approve_status = bDoApprApprove;

	if (GWPlugin.usePlugin) {
		view = $.mobile.activePage;
		var comment = view.find("#commentBody").val();
		GWPlugin.showWriteComment(comment, false, true, wordType, "javascript:setSignComment");
	} else {
		if (flag) {
			view.find("#comment").show();
			view.find("#header").find("#commentWriteBtn").find("span").text(MGW_RES.get("gw_sign_cancel_write_comment_label"));
			view.find("#header").find("#commentWriteBtn").attr("href", "javascript:toggleWriteComment(false, false)");
		} else {
			view.find("#comment").hide();
			view.find("#header").find("#commentWriteBtn").find("span").text(MGW_RES.get("gw_sign_write_comment_label"));
			view.find("#header").find("#commentWriteBtn").attr("href", "javascript:toggleWriteComment(true, false)");
		}
	}
}

// 결재 승인 & 반송 전처리
function apprApprove(apprtype, bAfterWriteComment) {
	var view = $.mobile.activePage;
	LOG(apprtype);
	
	if(!bAfterWriteComment) {
		var confirmMessage = "";
		if(apprtype == "approve" || apprtype == "junkyul") 
			confirmMessage = "gw_msg_sign_confirm_approve";
		else if(apprtype == "reject")
			confirmMessage = "gw_msg_sign_confirm_reject";
		else if(apprtype == "gongram")
			confirmMessage = "gw_msg_sign_confirm_gongram";
		else if(apprtype == "postpone")
			confirmMessage = "gw_msg_sign_confirm_postpone";
		else if(apprtype == "dispatch")
			confirmMessage = "gw_msg_sign_confirm_dispatch";		

		if(confirmMessage != "") {
			if(!confirm(MGW_RES.get(confirmMessage))) 
				return;
		}

		if( ((GW_OpenAPI.appr_approve_input_comment_use == "1" && apprtype == "reject") || GW_OpenAPI.appr_approve_input_comment_use == "2") &&
			apprtype != "dispatch" &&
			GWPlugin.usePlugin ) {
			view.find("#apprValue").val(apprtype);
			toggleWriteComment(true, true);
			return;
		}
	}

	if (GWPlugin.usePlugin && GW_OpenAPI.appr_instructions_use != undefined && GW_OpenAPI.appr_instructions_use == true) {
		GWPlugin.beforeApprApprove(apprtype, function() {}, function() {});
	} else {
		doApprApprove(apprtype, "");
	}	
}

// 결재 승인 & 반송
function doApprApprove(apprtype, attachfilename) {
	if (GWPlugin.usePlugin && GW_OpenAPI.appr_instructions_use != undefined && GW_OpenAPI.appr_instructions_use == true) {
		GW_PROXY.invokeOpenAPI("settings", "getuserinfo", {}, function(data) {
			invokeApprApprove(apprtype, attachfilename, data.position + ".pdf");
		});		
	} else {
		invokeApprApprove(apprtype, "", "");
	}
}

function invokeApprApprove(apprtype, attachfilename, attachfilerename) {
	var view = $.mobile.activePage;
	var params = {};
	LOG(apprtype);
	LOG("wordtype : " + view.find("#wordtype").val());
	
	params["apprid"] = view.find("#apprid").val();
	params["additionalOfficeUserId"] = view.find("#additionalOfficeUserId").val();
	params["participantId"] = view.find("#participantId").val();

	if (view.find("#writeCommentFlag").val() == "true") {
		params["cmnt"] = view.find("#commentBody").val();
		params["intodoccmnt"] = view.find("#intoDocumentCommentFlag").val();
	} else {
		params["cmnt"] = "";
		params["intodoccmnt"] = "false";
	}
	
	params["attachfilename"] = attachfilename;
	params["attachfilerename"] = attachfilerename;
	
	if(apprtype == "dispatch") {
		params["examId"] = view.find("#examId").val();
		params["examDeptId"] = view.find("#examDeptId").val();
		params["enforceType"] = view.find("#enforceType").val();
	}
	
	// html 기안 시 openapi로 결재를 처리함 (GW openAPI version : v8.2.2 이상)
	if(view.find("#wordtype").val() == "7"
			&& ((typeof GW_OpenAPI.appr_approve_openapi_use == "string" && GW_OpenAPI.appr_approve_openapi_use == "true")
				|| (typeof GW_OpenAPI.appr_approve_openapi_use == "boolean" && GW_OpenAPI.appr_approve_openapi_use))){
		if(apprtype == "approve" || apprtype == "junkyul" || apprtype == "confirm" || apprtype == "gongram")
			params["CMD"] = "procDoc";
		else if(apprtype == "reject")
			params["CMD"] = "rejectDoc";
		else
			params["CMD"] = "postponeDoc";
		// params["NOPOST"] = "Y"; //NOPOST : 디버깅 시 활성화
		
		GW_PROXY.invokeOpenAPI("sign", "apprApproveHtml", params, function(data) {
			LOG(data);

			var successMessage = "";
			var errorMessage = "";
			if(apprtype == "approve" || apprtype == "junkyul") {
				successMessage = "gw_msg_sign_approve_success";
				errorMessage = "gw_msg_sign_approve_fail";
			} else if(apprtype == "reject") {
				successMessage = "gw_msg_sign_reject_success";
				errorMessage = "gw_msg_sign_reject_fail";
			} else if(apprtype == "gongram") {
				successMessage = "gw_msg_sign_gongram_success";
				errorMessage = "gw_msg_sign_gongram_fail";
			} else if(apprtype == "postpone") {
				successMessage = "gw_msg_sign_postpone_success";
				errorMessage = "gw_msg_sign_postpone_fail";
			} else {
				successMessage = "gw_msg_common_success";
				errorMessage = "gw_msg_common_fail";
			}

			if(data.channel.result["#text"] == "success"){
				hidePageLoadingView();
				alert(MGW_RES.get(successMessage));

				if (GWPlugin.usePlugin) {
					GWPlugin.completeAppr(view.find("#apprid").val(), apprtype, function() {}, function() {});
				} else {
					PAGE_CONTROLLER.goBack(true);
				}
			} else {
				// Error Result
				hidePageLoadingView();
				if(data.channel.errormessage == "" || data.channel.errormessage == undefined)
					alert(MGW_RES.get(errorMessage));
				else
					alert(data.channel.errormessage);
			}
		});
	} else {
		params["apprtype"] = apprtype;
		params["apprstatus"] = view.find("#apprstatus").val();
		params["async"] = "true";
		
		GW_PROXY.invokeOpenAPI("sign", "apprApprove", params, function(data) {
			checkApprStatus(0, apprtype, view.find("#apprid").val());
		});
	}	
}

// 결재 상태 체크
function checkApprStatus(time, apprtype, apprid) {
	// 3000 * 23 = 69초 Waiting
	var apprCheckTime = 23;

	// console.log("checkApprStatus time[" + time + "] apprtype[" + apprtype +
	// "] apprid[" + apprid + "]");

	if (time < apprCheckTime) {
		time++;
		setTimeout(function() {
			getApprStatus(time, apprtype, apprid);
		}, 3000);
	} else {
		hidePageLoadingView();
		alert(MGW_RES.get("gw_msg_sign_network_err"));
	}
}

// 결재 상태 체크
function getApprStatus(time, apprtype, apprid) {
	GW_PROXY.invokeCheckApprStatus(apprid, function(data) {

		// console.log("getApprStatus data[" + data.message + "] time[" + time +
		// "] apprtype[" + apprtype + "] apprid[" + apprid + "]");
		var successMessage = "";
		var errorMessage = "";
		if(apprtype == "approve" || apprtype == "junkyul") {
			successMessage = "gw_msg_sign_approve_success";
			errorMessage = "gw_msg_sign_approve_fail";
		} else if(apprtype == "reject") {
			successMessage = "gw_msg_sign_reject_success";
			errorMessage = "gw_msg_sign_reject_fail";
		} else if(apprtype == "gongram") {
			successMessage = "gw_msg_sign_gongram_success";
			errorMessage = "gw_msg_sign_gongram_fail";
		} else if(apprtype == "postpone") {
			successMessage = "gw_msg_sign_postpone_success";
			errorMessage = "gw_msg_sign_postpone_fail";
		} else {
			successMessage = "gw_msg_common_success";
			errorMessage = "gw_msg_common_fail";
		}

		if (data.message == "SUCCESS") {
			hidePageLoadingView();
			alert(MGW_RES.get(successMessage));

			if (GWPlugin.usePlugin) {
				GWPlugin.completeAppr(apprid, apprtype, function() {}, function() {});
			} else {
				PAGE_CONTROLLER.goBack(true);
			}
		} else if (data.message == "FAIL") {
			hidePageLoadingView();
			alert(MGW_RES.get(errorMessage));
		} else if (data.message == "AGENT_ERROR" || data.message == "NONE") {
			hidePageLoadingView();
			alert(MGW_RES.get("gw_msg_sign_agent_err"));
		} else {
			checkApprStatus(time, apprtype, apprid);
		}
	});
}

// 결재 회수
function withdrawAppr() {
	var view = $.mobile.activePage;

// if (view.prop("id") != "sign_details")
// return;

	var param = {};
	var apprid = view.find("#apprid").val();

	if (confirm(MGW_RES.get("gw_msg_sign_confirm_withdraw"))) {
		if (apprid != "") {
			param["APPRID"] = apprid;
			param["type"] = "0";
		}

		// GW8.2 결재 회수 의견
		param["OPINION"] = encodeURIComponent(MGW_RES.get("gw_msg_sign_withdraw_opinion"));

		GW_PROXY.invokeOpenAPI("sign", "withdraw", param, function(data) {
			if (data.channel.item != undefined) {
				alert(data.channel.item[0]["#text"]);

				if (GWPlugin.usePlugin) {
					GWPlugin.popView(function(){}, function(){});
				} else {
					PAGE_CONTROLLER.goBack(true);
				}
			} else {
				alert(MGW_RES.get("gw_error_failed_result"));
			}
		});
	}
}

// 결재 취소
function cancelAppr() {
	var view = $.mobile.activePage;

	var param = {};
	var apprid = view.find("#apprid").val();

	if (confirm(MGW_RES.get("gw_msg_sign_confirm_cancel"))) {
		if (apprid != "") {
			param["APPRID"] = apprid;
			param["USERID"] = sessionStorage["id"];
		}

		GW_PROXY.invokeOpenAPI("sign", "cancel", param, function(data) {
			if(data.channel.item.result["#text"] == "fail"){
				var errormessage = data.channel.item.errormessage.replace(/\\n/g, "\n");
				alert(errormessage);
			} else {
				alert(MGW_RES.get("gw_msg_sign_cancel_success"));
			}
		});
	}
}

function changeSignType() {
	var view = $.mobile.activePage;
	var signType = view.find("#signType option:selected").val();

	getSignList(view.find("#apiCode").val(), 1);
}

function changeProcAuthList() {
	var view = $.mobile.activePage;
	var auth = view.find("#procAuthList option:selected").val();

	getSignList(view.find("#apiCode").val(), 1, auth);
}

function getSignList(apiCode, page_num, dispatchAuth) {
	var view = $.mobile.activePage;
	var signType = view.find("#signType").val();
	var params = {};

	params["pno"] = page_num;

	if (apiCode == "nowlist" || apiCode == "waitlist") {
		params["apprtype"] = signType;
	}

	if (apiCode == "dispatchlist") {
		if(dispatchAuth == undefined) {
			GW_PROXY.invokeOpenAPI("sign", "procauthlist", {}, function(authData) {
				if(authData.channel.isRole === "true") {
					var tmp = "";
					var defaultAuth = "";
					var auth = "";
					
					defaultAuth = authData.channel.defaultAuthInfo.deptID + "|" + authData.channel.defaultAuthInfo.fldrID;
					tmp = "<option name='" + defaultAuth + "' value='" + defaultAuth + "'" + "selected" + ">" + authData.channel.defaultAuthInfo.deptFullName + "</option>";
	
					$.each(authData.channel.authInfoList, function(i, authInfoData) {
						if(authData.channel.defaultAuthInfo.deptID != authInfoData.deptID) {
							auth = authInfoData.deptID + "|" + authInfoData.fldrID;
							tmp += "<option name='" + auth + "' value='" + auth + "'" + ">" + authInfoData.deptFullName + "</option>";
						}
					});
					
					if (tmp != "") {
						view.find("#procAuthList").append(tmp);
						view.find("#procAuthList").selectmenu("refresh");
						view.find("#headerProcAuthList").removeAttr("style");
					}
					
					params["authDeptId"] = defaultAuth.split("|")[0];
					params["authFldrId"] = defaultAuth.split("|")[1];
					GW_PROXY.invokeOpenAPI("sign", apiCode, params, function(data) {
						renderSignList(apiCode, data, defaultAuth.split("|")[0]);
					});				
				}
			});
		} else {
			params["authDeptId"] = dispatchAuth.split("|")[0];
			params["authFldrId"] = dispatchAuth.split("|")[1];
			GW_PROXY.invokeOpenAPI("sign", apiCode, params, function(data) {
				renderSignList(apiCode, data, dispatchAuth.split("|")[0]);
			});			
		}
	} else {
		GW_PROXY.invokeOpenAPI("sign", apiCode, params, function(data) {
			renderSignList(apiCode, data);
		});
	}
}

function getSignDetails(link, applid, apprid, participantapprtype, apprstatus,
		attachCnt, commentCk, postponeCk, externalDocFlag, summaryDocFlag, wordtype, additionalOfficeUserId, participantId, linkdocFlag, examId, examDeptId, enforceType) {
	var view = $.mobile.activePage;
	LOG("link : " + link + ", applid : " + applid + ", apprid : " + apprid + ", participantapprtype : " + participantapprtype + ", apprstatus : " + apprstatus
			 + ", attachCnt : " + attachCnt + ", commentCk : " + commentCk + ", postponeCk : " + postponeCk + ", externalDocFlag : " + externalDocFlag 
			 + ", summaryDocFlag : " + summaryDocFlag + ", wordtype : " + wordtype + ", additionalOfficeUserId : " + additionalOfficeUserId
			 + ", participantId : " + participantId + ", linkdocFlag : " + linkdocFlag
			 + ", examId : " + examId + ", examDeptId : " + examDeptId + ", enforceType : " + enforceType);
// if ($.mobile.activePage.prop("id") != "sign_details")
// return;

	getApprProfile(function() {
		var category = "apprbody";
		var params = {};
		params["APPRID"] = apprid;

		view.find("#apprid").val(apprid);
		view.find("#applid").val(applid);
		view.find("#apprstatus").val(apprstatus);
		view.find("#wordtype").val(wordtype);
		if(additionalOfficeUserId == undefined || additionalOfficeUserId == "undefined" || additionalOfficeUserId == "") {
			additionalOfficeUserId = sessionStorage["id"];
		}
		view.find("#additionalOfficeUserId").val(additionalOfficeUserId);
		view.find("#participantId").val(participantId);
		view.find("#examId").val(examId);
		view.find("#examDeptId").val(examDeptId);
		view.find("#enforceType").val(enforceType);

		// WebPage, 결재, 첨부 버튼 셋팅
		view.find("#header").removeAttr("style");
		view.find("#attach").attr("style", "display:none;");
		
		// 관련문서 버튼 셋팅
		if (linkdocFlag) {
			view.find("#header").removeAttr("style");
			view.find("#linkdocBtn").show();
		} 
		else if (applid == "2010" || applid == "3020") {
			if (participantapprtype == "4112") {
				if (GW_OpenAPI.sign_type == "html" || GW_OpenAPI.sign_type == "hwp8")
					view.find("#confirmBtn").show();
				else
					view.find("#approveBtn").show();
			} 
			else if (participantapprtype == "6912") {
				view.find("#gongramBtn").show();
			} 
			else if (apprstatus == "256") {
				view.find("#confirmBtn").show();
			} 
			else {
				view.find("#approveBtn").show();
				view.find("#rejectBtn").show();
				if(GW_OpenAPI.appr_approve_junkyul_use == "true") {
					view.find("#junkyulBtn").show();
				}			
				if (postponeCk)
					view.find("#postponeBtn").show();
				view.find("#commentWriteBtn").show();
			}
			view.find("#linkdocBtn").hide();
		} 
		else if (applid == "2020") {
			view.find("#withdrawBtn").show();
			if(GW_OpenAPI.appr_approve_recall_use == "true") {
				view.find("#cancelBtn").show();
			}
			view.find("#linkdocBtn").hide();
			
		}else if (applid == "4010") {
			view.find("#dispatchBtn").show();
		}
		
		// 결재 의견 버튼 셋팅
		if (commentCk) {
			view.find("#hasComment").val("true");
			view.find("#header").removeAttr("style");
			view.find("#commentBtn").show();
		} 
		else {
			view.find("#hasComment").val("false");
			view.find("#commentBtn").hide();
		}

		// 요약전 버튼 셋팅
		if (summaryDocFlag) {
			view.find("#header").removeAttr("style");
			view.find("#summaryBtn").show();
		} 
		else {
			view.find("#summaryBtn").hide();
		}
		
		// 결재 첨부
		if (attachCnt == undefined || attachCnt == "" || attachCnt == -1 || attachCnt > 0) {
			params["APPLID"] = applid;

			GW_PROXY.invokeOpenAPI("sign", "attachlist", params, function(data1) {
				renderSignDetailsAttach(data1);
			});
		}

		// 결재/문서함 의견보기 옵션 처리
		var apprProfileOpinion = false;
		var showSignCommentListCallback = undefined;
		if(applid == "2010" || applid == "3020") {
			apprProfileOpinion = getApprProfileValue("appr").opinion;
		} else {
			apprProfileOpinion = getApprProfileValue("doc").opinion;
		}
		if(commentCk == true && apprProfileOpinion == "1") {
			showSignCommentListCallback = showSignCommentList;
		}
		
		// 비전자문서 처리
		// - 접수대기에서 등록, 문서함에서 등록시 wordtype = 8 (비전자문서)
		// - 접수대기에서 접수한 경우 wordtype = 7 로 변경됨
		if ((externalDocFlag && GW_OpenAPI.sign_type == "hwp6") || (externalDocFlag && wordtype == "7") || wordtype == "8") {
			renderSignDetailsBody(undefined, externalDocFlag, wordtype, showSignCommentListCallback);

			if (GWPlugin.usePlugin) {
				GWPlugin.closeWaitingView(function() {}, function() {});
			} else {
				$.mobile.hidePageLoadingMsg();
			}
		}
		// 전자문서 처리
		else {
			if(GW_OpenAPI.doc_transform_viewer_external == true && wordtype == "3") {
				renderSignDetailsBody(undefined, externalDocFlag, wordtype, showSignCommentListCallback, true, link.trim());
			} else {
				DOC_HANDLER.getApprBody(link.trim(), category, wordtype, function(data2) {
					if (data2 != undefined) {
						renderSignDetailsBody(data2, externalDocFlag, wordtype, showSignCommentListCallback);
					} else {
						// 문서변환 or 오류 발생시 결재본문 영역에 출력하도록 변경
						renderSignDetailsBody(data2, externalDocFlag, wordtype, showSignCommentListCallback);
					}

					if (GWPlugin.usePlugin) {
						GWPlugin.closeWaitingView(function() {}, function() {});
					} else {
						$.mobile.hidePageLoadingMsg();
					}
				});			
			}
		}
	});
}

function getSignCommentList(apprid) {
	var view = $.mobile.activePage;
	var params = {};
	params["APPRID"] = apprid;

	GW_PROXY.invokeOpenAPI("sign", "commentlist", params, function(data) {
		if (data.channel.comment != undefined || data.channel.recallComment != undefined) {
			renderSignCommnetList(data.channel.comment, data.channel.recallComment);
		} else {
			alert(MGW_RES.get("gw_error_invalid_request"));
			PAGE_CONTROLLER.goBack();
			return;
		}
	});
}

function getApprfolw(apprid) {
	var params = {};
	params["APPRID"] = apprid;

	GW_PROXY.invokeOpenAPI("sign", "apprflow", params, function(data) {
		if (data != undefined) {
			renderApprflow(data);
		} else {
			alert(MGW_RES.get("gw_error_invalid_request"));
			PAGE_CONTROLLER.goBack();
			return;
		}
	});
}

// 요약전 문서 Openapi 호출
function getSummaryDoc(apprid, wordtype) {
	var link = "/jsp/openapi/OpenApi.jsp?target=appr&todo=summaryDoc&type=0&APPRID=" + apprid + "&WORDTYPE=" + wordtype;
	var category = "apprsummary";

	DOC_HANDLER.getApprSummary(link.trim(), category, wordtype, function(data) {

		if (data != undefined) {
			renderSummaryDocBody(data);
		} else {
			alert(MGW_RES.get("gw_error_invalid_request"));
		}

		if (GWPlugin.usePlugin) {
			GWPlugin.closeWaitingView(function() {}, function() {});
		} else {
			$.mobile.hidePageLoadingMsg();
		}
	});
}

// 관련문서 Count 호출 Openapi
function getLinkDocCount(apprid, successFN) {
	var params = {};
	params["APPRID"] = apprid;
	params["type"] = "count";

	GW_PROXY.invokeOpenAPI("sign", "linkdoclist", params, function(data) {
		if (data != undefined) {
			if (data.channel.count > 0) {
				successFN(true);
			} else{
				successFN(false);
			}
		} else {
			alert(MGW_RES.get("gw_error_invalid_request"));
			successFN(false);
		}
	});
}

// 관련문서 목록 호출 Openapi
function getLinkDocList(apprid, applid) {
	var params = {};
	params["APPRID"] = apprid;
	params["type"] = "list";

	GW_PROXY.invokeOpenAPI("sign", "linkdoclist", params, function(data) {
		if (data != undefined) {
			GW_CONTROLLER_SIGN.linkdocIdx = GW_CONTROLLER_SIGN.linkdocIdx + 1;
			renderLinkDocList(data, applid);
		} else {
			alert(MGW_RES.get("gw_error_invalid_request"));
			return false;
		}
	});
}

function renderSignList(apiCode, data, authDeptId) {
	var view = $.mobile.activePage;
	var list = view.find("#list");
	var header = view.find("#header");
	var tmp = [];
	var tmp2 = [];
	var total = data.channel.total; // 전체 페이지 수
	var pageno = data.channel.pageno; // 현재 페이지 수
	var size = data.channel.size; // 한페이지에 보여주는 페이지 수

	view.find("#apiCode").val(apiCode);

	// 결재 목록 필터 처리
	if ((apiCode == "nowlist" || apiCode == "waitlist")
			&& GW_OpenAPI.sign_type != "html") {
		header.removeAttr("style");
	}

	if (data.channel.item != undefined) {
		if (data.channel.item.length == undefined) {
			tmp.push(parseSignList(data.channel.item, apiCode, authDeptId));
		} else {
			for ( var i = 0; i < data.channel.item.length; i++) {
				tmp.push(parseSignList(data.channel.item[i], apiCode, authDeptId));
			}
		}
	} else {
		if (pageno == 1) {
			tmp.push("<li><h3>" + MGW_RES.get("gw_msg_sign_nolist")
					+ "</h3></li>");
		}
	}

	if (tmp.join("") != "") {
		if (pageno > 1) {
			list.append(tmp.join(""));
		} else {
			list.html(tmp.join(""));
			view.find("#pageno").val("1");
		}

		list.listview("refresh");
	}

	// 더보기
	if (total > parseInt(pageno) * size) {
		view.find("#hasMore").val(true);
		view.find("#pageno").val(parseInt(pageno) + 1);
		view.find("#morelist").removeAttr("style");
	} else {
		view.find("#hasMore").val(false);
		view.find("#morelist").attr("style", "display:none");
	}
}

function parseSignList(sign, apiCode, authDeptId) {
	var tmp = [];
	var applid = "";
	var commentCk = "";
	var postponeCk = "";

	if (sign.statusimageEx != undefined && 
			(sign.statusimageEx.indexOf("pinion.gif") > 0 || sign.statusimageEx.indexOf("s8.gif") >= 0)) {
		commentCk = "true";
		// } else if (sign.statustooltip != undefined &&
		// sign.statustooltip.indexOf("견") > 0) {
		// commentCk = "true";
	} else {
		commentCk = "false";
	}

	// 보류 - 기안부서이고 참조자는 제외
	if (sign.statusimage != undefined && sign.statusimage.substr(0, 1) == "n") {
		postponeCk = "true";
	} else {
		postponeCk = "false";
	}

	applid = getApplId(apiCode);

	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>");
	tmp.push("<span class='apprstatustext'>" + sign.apprstatustext + "</span>");
	tmp.push("</div>");
	
	// 8버전용 열람 제한(authLink 처리 해야 함)
	if (GW_OpenAPI.sign_type == "html" && sign.docViewAuth == "false") {
		tmp.push("<a href=\"javascript:alert(\'"
				+ MGW_RES.get("gw_msg_sign_read_noauth_err") + "\')\">");
	}
	// 보안문서 암호 확인
	else if (sign.security != undefined && sign.security == "1") {
		tmp.push("<a href=\"javascript:openSecuritySign('" + apiCode + "', '"
				+ sign.link + "', '" + applid + "', '" + sign.guid + "', '"
				+ sign.participantapprtype + "', '" + sign.apprstatus + "', '"
				+ sign.attachcnt + "', '" + commentCk + "', '" + postponeCk + "', '"
				+ sign.externalDocFlag + "', '" + sign.summaryDocFlag + "', '"
				+ sign.wordtype + "', '" + sign.additionalOfficeUserId + "')\">");
	} else {
		tmp.push("<a href=\"javascript:showSignDetails('" + sign.link + "', '"
				+ applid + "', '" + sign.guid + "', '"
				+ sign.participantapprtype + "', '" + sign.apprstatus + "', '"
				+ sign.attachcnt + "', '" + commentCk + "', '" + postponeCk + "', '"
				+ sign.externalDocFlag + "', '" + sign.summaryDocFlag + "', '"
				+ sign.wordtype + "', '" + sign.additionalOfficeUserId + "', '"
				+ sign.participantid + "', "
				+ GW_CONTROLLER_SIGN.linkdocIdx + ", '"
				+ sign.examid + "', '"
				+ authDeptId + "', '"
				+ sign.enforcetype + "')\">");
	}

	tmp.push("<div class='grouping_middle'>");
	tmp.push("<span class='drafter'>" + sign.drafter + "</span>");

	if (sign.participantapprtypetext != undefined) {
		tmp.push("<span class='participantapprtypetext'>["
				+ sign.participantapprtypetext + "]</span>");
	}
	tmp.push("<span class='draftDate");
	if (sign.attachcnt == 0)
		tmp.push("'>");
	else
		tmp.push(" attach'>");

	tmp.push(sign.draftDate + "</span>");
	tmp.push("<h3>" + sign.title + "</h3>");
	tmp.push("</div></a></li>");

	return tmp.join("");
}

// 관련문서
function parseLinkDocList(num, sign, applid) {
	var tmp = [];
	var commentCk = "";
	var postponeCk = "";

	if (sign.statusimageEx != undefined && 
			(sign.statusimageEx.indexOf("pinion.gif") > 0 || sign.statusimageEx.indexOf("s8.gif") >= 0)) {
		commentCk = "true";
		// } else if (sign.statustooltip != undefined &&
		// sign.statustooltip.indexOf("견") > 0) {
		// commentCk = "true";
	} else {
		commentCk = "false";
	}


	// 보류 - 기안부서이고 참조자는 제외
	if (sign.statusimage != undefined && sign.statusimage.substr(0, 1) == "n") {
		postponeCk = "true";
	} else {
		postponeCk = "false";
	}

	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>");
	tmp.push("<span class='apprstatustext'>" + num + "</span>");
	tmp.push("</div>");

	// 8버전용 열람 제한(authLink 처리 해야 함)
	if (GW_OpenAPI.sign_type == "html" && sign.docViewAuth == "false") {
		tmp.push("<a href=\"javascript:alert(\'"
				+ MGW_RES.get("gw_msg_sign_read_noauth_err") + "\')\">");
	}
	// 보안문서 암호 확인
	else if (sign.security != undefined && sign.security == "1") {
		tmp.push("<a href=\"javascript:openSecuritySign('" + apiCode + "', '"
				+ sign.link + "', '" + applid + "', '" + sign.guid + "', '"
				+ sign.participantapprtype + "', '" + sign.apprstatus + "', '"
				+ sign.attachcnt + "', '" + commentCk + "', '" + postponeCk + "', '"
				+ sign.externalDocFlag + "', '" + sign.summaryDocFlag + "', '"
				+ sign.wordtype + "', '" + sign.additionalOfficeUserId + "')\">");
	} else {
		tmp.push("<a href=\"javascript:showSignDetails('" + sign.link + "', '"
				+ applid + "', '" + sign.guid + "', '"
				+ sign.participantapprtype + "', '" + sign.apprstatus + "', '"
				+ sign.attachcnt + "', '" + commentCk + "', '" + postponeCk + "', '"
				+ sign.externalDocFlag + "', '" + sign.summaryDocFlag + "', '"
				+ sign.wordtype + "', '" + sign.additionalOfficeUserId + "', '"
				+ sign.participantid + "', "
				+ GW_CONTROLLER_SIGN.linkdocIdx + ", '', '', '')\">");
	}

	tmp.push("<div class='grouping_middle'>");
	tmp.push("<span class='drafter'>" + sign.drafter + "</span>");
	tmp.push("<span class='draftDate");
	
	if (sign.attachcnt == 0)
		tmp.push("'>");
	else
		tmp.push(" attach'>");

	tmp.push("</span>");
	
	tmp.push("<h3>" + sign.title + "</h3>");
	tmp.push("</div></a></li>");

	return tmp.join("");
}

// 관련문서 목록
function renderLinkDocList(data, applid) {
	var view = $.mobile.activePage;
	
	var list = view.find("#list");
	var header = view.find("#header");
	var tmp = [];
	var tmp2 = [];
	var total = data.channel.total; // 전체 페이지 수
	var pageno = data.channel.pageno; // 현재 페이지 수
	var size = data.channel.size; // 한페이지에 보여주는 페이지 수

	view.find("#applid").val(applid);

	if (data.channel.item != undefined) {
		if (data.channel.item.length == undefined) {
			tmp.push(parseLinkDocList("1", data.channel.item, apiCode));
		} else {
			for (var i = 0; i < data.channel.item.length; i++) {
				tmp.push(parseLinkDocList(i + 1, data.channel.item[i], applid));
			}
		}
	} else {
		tmp.push("<li><h3>" + MGW_RES.get("gw_msg_sign_nolist") + "</h3></li>");
	}

	if (tmp.join("") != "") {
		if (pageno > 1) {
			list.append(tmp.join(""));
		} else {
			list.html(tmp.join(""));
			view.find("#pageno").val("1");
		}

		list.listview("refresh");
	}

	// 더보기
	if (total > parseInt(pageno) * size) {
		view.find("#hasMore").val(true);
		view.find("#pageno").val(parseInt(pageno) + 1);
		view.find("#morelist").removeAttr("style");
	} else {
		view.find("#hasMore").val(false);
		view.find("#morelist").attr("style", "display:none");
	}
}

function renderSignCommnetList(comment, recallComment) {
	var view = $.mobile.activePage;
	var list = view.find("#list");
	var tmp = [];

	if (comment != undefined) {
		if (comment.length != undefined) {
			for ( var i = 0; i < comment.length; i++) {
				tmp.push(parseCommnetList(comment[i]));
			}
		} else {
			tmp.push(parseCommnetList(comment));
		}
	}

	if (recallComment != undefined) {
		if (recallComment.length != undefined) {
			for ( var i = 0; i < recallComment.length; i++) {
				tmp.push(parseCommnetList(recallComment[i]));
			}
		} else {
			tmp.push(parseCommnetList(recallComment));
		}
	}
	
	if (tmp.join("") != "") {
		list.append(tmp.join(""));
		list.listview("refresh");
	}
}

function renderApprflow(data) {
	var view = $.mobile.activePage;
	if (view.prop("id") != "sign_apprflow")
		return;

	var list = view.find("#list");
	var tmp = "";

	if (data.channel.participant != undefined) {
		if (data.channel.participant.length != undefined) {
			for ( var i = 0; i < data.channel.participant.length; i++) {
				tmp += parseApprflow(data.channel.participant[i]);
			}
		} else {
			tmp += parseApprflow(data.channel.participant);
		}
	}

	if (tmp != "") {
		if (view.prop("id") == "sign_apprflow") {
			list.html(tmp);
			list.listview("refresh");
		}
	}
}

function parseApprflow(apprflow) {
	var tmp = [];
	tmp.push("<li>");
	tmp.push("<div class='grouping_left'>");
	tmp.push(apprflow.approvalStatus);
	tmp.push("</div>");
	tmp.push("<div class='grouping_middle'>");
	tmp.push(apprflow.signername + "[" + apprflow.signerpos + "]");

	tmp.push("<div class='moreInfo'>");
	tmp.push(MGW_RES.get("gw_sign_floworder_label") + ":");
	tmp.push(apprflow.ord);
	tmp.push(" | ");

	tmp.push(MGW_RES.get("gw_sign_approval_label") + ":");
	tmp.push(apprflow.approvaltype);

	if (apprflow.approvaldate != undefined)
		tmp.push(" | " + apprflow.approvaldate);

	tmp.push("</div>");
	tmp.push("</div></li>");

	return tmp.join("");
}

function renderSignDetailsAttach(data) {
	var view = $.mobile.activePage;
// if ($.mobile.activePage.prop("id") != "sign_details")
// return;

	var list = view.find("#list");

	if (data.channel != undefined) {
		var tmp = [];
		var attaches = data.channel.item;

		// 첨부
		if (attaches != undefined) {
			var tmp = [];
			var category = "apprattach";
			var attact_cnt = 0;

			for ( var i = 0; i < attaches.length; i++) {
				var file = attaches[i];
				var filename = file.filename.replace(/'/g, '').replace(/#/g, '');
				var filelink = file.link.trim().replace(/'/g, '').replace(/#/g, '');

				/**
				 * type = attach : 첨부파일 type = body : 결재본문
				 */
				if (attaches[i].type == "attach") {
					attact_cnt++;
					tmp.push("<li><a class='attach_lst' onclick=\"javascript:DOC_HANDLER.setEvent(event);\" href=\"javascript:DOC_HANDLER.showAttach('"
                    							+ filelink
                    							+ "','"
                    							+ category
                    							+ "','"
                    							+ filename
                    							+ "')\" class='btn_txt gray attach'><span style='white-space: pre-line;'>"
                    							+ file.filename
                    							+ "(");
                    					tmp.push(convertFileSizeUnit(file.size));
                    					tmp.push(")</span></a></li>");
				}

				// console.log("renderSignDetailsAttach attact_cnt[" +
				// attact_cnt + "]");
			}

			if (attact_cnt > 0) {
				list.find("#attach").removeAttr("style");
				list.find("#attachInfo").html(attact_cnt + MGW_RES.get("gw_common_piece_label"));
				list.find("#attachList").html(tmp.join(""));
			}
		}
	}
}

function showSignDetailsExternalViewer(link, wordtype) {
	DOC_HANDLER.getApprBody(link.trim(), "apprbody", wordtype, function(data) {
		if (GWPlugin.usePlugin) {
			GWPlugin.closeWaitingView(function() {}, function() {});
		} else {
			$.mobile.hidePageLoadingMsg();
		}
		
		if(data != undefined && 
				(data.docType == "kaon" || data.docType == "synapimage")){
			var fileLink = data.docLink.linkList[0];
			window.open(fileLink);
		} else {
			alert(MGW_RES.get("gw_msg_sign_not_transform_apprdoc"));
		}
	});
}

function renderSignDetailsBody(signBodyData, externalDocFlag, wordtype, showSignCommmentListCallback, externalViewFlag, link) {
	var view = $.mobile.activePage;
// if ($.mobile.activePage.prop("id") != "sign_details")
// return;
	var signBody = view.find(".contents");

	// 비전자 문서 (결재방지정 문서는 wordtype이 콘트롤양식으로 변환되었으므로 비전자문서처리 안함)
	// - 접수대기에서 등록, 문서함에서 등록시 wordtype = 8 (비전자문서)
	// - 접수대기에서 접수한 경우 wordtype = 7 로 변경됨
	if ((externalDocFlag && GW_OpenAPI.sign_type == "hwp6") || (externalDocFlag && wordtype == "7") || wordtype == "8") {
		var tmp = [];
		if (GW_OpenAPI.sign_type == "hwp6") {
			tmp.push(MGW_RES.get("gw_msg_sign_external6_doc"));
		} else {
			tmp.push(MGW_RES.get("gw_msg_sign_external_doc"));
		}
		signBody.html(tmp.join(""));
	} 	
	// 한글 기안 문서에서 문서변환 설정이 안된 경우
	else if ((GW_OpenAPI.sign_type == "hwp6" || wordtype == "3") && (signBodyData == undefined || signBodyData.docType == "file")) {
		var tmp = [];
		
		if(externalViewFlag) {
			tmp.push("<a href=\"javascript:showSignDetailsExternalViewer('" + link + "','" + wordtype + "');\" data-role=\"button\" data-theme=\"d\" >" + 
					MGW_RES.get("gw_sign_details_label") + "</a>");			
		} else {
			tmp.push(MGW_RES.get("gw_msg_sign_not_transform_apprdoc"));
		}
		signBody.html(tmp.join(""));
	}
	else if (signBodyData.code == "1001" || signBodyData.code == "1201"
			|| signBodyData.code == "1202" || signBodyData.code == "1203") {
		var tmp = [];
		tmp.push(MGW_RES.get("gw_msg_sign_not_support_apprdoc"));
		signBody.html(tmp.join(""));
	}
	// MHT 문서 처리
	else if (wordtype != "3") {
		var downLink = DOC_HANDLER.serverIP + signBodyData.docLink.linkList[0];
		var downUrlArr = downLink.split("/");
		var downUrl = "";

		for ( var i = 0; i < downUrlArr.length - 1; i++) {
			downUrl += downUrlArr[i] + "/";
		}
		$.get(downLink, function(body) {
			var docBody = $(body);
			var images = docBody.find("img");

			$.each(images, function(i, value) {
				var image = images.eq(i);
				var imgSrc = image.attr("src");

				if (!startsWith(imgSrc, 'http://') && !startsWith(imgSrc, 'https://')) {
					imgSrc = downUrl + imgSrc;
					image.attr("src", imgSrc);
				}
			});

			signBody.html(docBody);
		});
	}
	// HWP 문서 처리
	else {
		var IMAGE_RELOAD_INTERVAL = 2000;// micro second
		var MAX_IMAGE_RELOAD_COUNT = 30;

		signBodyData.docLink.linkList.forEach(function(item, index, all) {
			// MGW-882 [마사회] 여러page의 문서를 초기에 모두 변환하지 않고, 페이지 이동시마다 변환
			if(item.indexOf("http") != 0) {
				item = DOC_HANDLER.serverIP + item;
			} else {
				item = GW_OpenAPI.serverIP + "/rest/doc/proxy?proxyurl=" + item;
			}
			
			if(signBodyData.docType == "synap") {
				var tmp = [];
				tmp.push("<iframe width='100%' height='900px' src='" + item + "' scrolling='yes' frameborder='0' />");
				signBody.append(tmp.join(""));
				
			} else {
				var img_id = "_SIGN_IMAGE_" + index; // 필요한 경우 태그를 찾아내기 위해 ID
														// 부여
	
				var image = new Image();
				image.id = img_id;
				image.style.width = "100%";
				signBody.append(image);
				signBody.append("<br>");
	
				image.src = item; // 이미지 로딩
	
				var reloadedCnt = 0;
				var reloadFunction = function() {
					this.src = item;
				};
	
				image.onload = function() {// 이미지 로딩 성공 처리
					$(image).unbind("click", reloadFunction);
				};
	
				image.onerror = function() { // 이미지 로딩 실패 처리
					if (reloadedCnt < MAX_IMAGE_RELOAD_COUNT) { // MAX_IMAGE_RELOAD_COUNT
																// 횟수 만큼 리로딩 시도
						setTimeout(function() {
							reloadedCnt++;
							image.src = item;
						}, IMAGE_RELOAD_INTERVAL);
					} else {
						image.onerror = function() {
						}; // 에러 처리 함수는 해제
						// ToDo: 서버에 터치 시 '이미지를 다시 로딩한다'는 의미를 갖는 이미지를 추가하고, 해당
						// 이미지
						// URL로 변경
						image.src = 'touch_to_reload_image.jpg';
						$(image).bind("click", reloadFunction);
					}
				};
	
				// var downLink = DOC_HANDLER.serverIP +
				// signBodyData.docLink.linkList[0];
				// var tmp = [];
				// tmp.push("<img src='" + downLink + "'>");
				// signBody.append(tmp.join(""));
			}
		});
	}

	if(showSignCommmentListCallback != undefined) {
		showSignCommmentListCallback();
	}
}

function renderSummaryDocBody(data) {
	var view = $.mobile.activePage;

	if ($.mobile.activePage.prop("id") != "sign_summarydoc")
		return;

	var signBody = view.find(".contents");

	if (data.code == "1001" || data.code == "1201" || data.code == "1202" || data.code == "1203") {
		var tmp = [];
		tmp.push(MGW_RES.get("gw_msg_sign_not_support_apprdoc"));
		signBody.html(tmp.join(""));
	}
	// 한글 기안 문서에서 문서변환 설정이 안된 경우
	else if (data.docType == "file") {
		var tmp = [];
		tmp.push(MGW_RES.get("gw_msg_sign_not_transform_apprdoc"));
		signBody.html(tmp.join(""));
	}
	// WORDTYPE : 7 (html)
	else if (data.docType == "html") {
		var downLink = DOC_HANDLER.serverIP + data.docLink.linkList[0];
		var downUrlArr = downLink.split("/");
		var downUrl = "";

		for ( var i = 0; i < downUrlArr.length - 1; i++) {
			downUrl += downUrlArr[i] + "/";
		}

		$.get(downLink, function(body) {
			var docBody = $(body);
			var images = docBody.find("img");

			$.each(images, function(i, value) {
				var image = images.eq(i);
				var imgSrc = image.attr("src");

				if (!startsWith(imgSrc, 'http://')
						&& !startsWith(imgSrc, 'https://')) {
					imgSrc = downUrl + imgSrc;
					image.attr("src", imgSrc);
				}
			});

			signBody.html(docBody);
		});
	}
	// WORDTYPE : 3 (hwp)
	else {
		var IMAGE_RELOAD_INTERVAL = 2000;// micro second
		var MAX_IMAGE_RELOAD_COUNT = 30;

		data.docLink.linkList.forEach(function(item, index, all) {
			// MGW-882 [마사회] 여러page의 문서를 초기에 모두 변환하지 않고, 페이지 이동시마다 변환
			if(item.indexOf("http") != 0) {
				item = DOC_HANDLER.serverIP + item;
			} else {
				item = GW_OpenAPI.serverIP + "/rest/doc/proxy?proxyurl=" + item;
			}

			if(data.docType == "synap") {
				var tmp = [];
				tmp.push("<iframe width='100%' height='900px' src='" + item + "' scrolling='no' frameborder='0' />");
				signBody.append(tmp.join(""));
			
			} else {
				var img_id = "_SIGN_IMAGE_" + index; // 필요한 경우 태그를 찾아내기 위해 ID
														// 부여
	
				var image = new Image();
				image.id = img_id;
				image.style.width = "100%";
				signBody.append(image);
				signBody.append("<br>");
	
				image.src = item; // 이미지 로딩
	
				var reloadedCnt = 0;
				var reloadFunction = function() {
					this.src = item;
				};
	
				image.onload = function() {// 이미지 로딩 성공 처리
					$(image).unbind("click", reloadFunction);
				};
	
				image.onerror = function() { // 이미지 로딩 실패 처리
					if (reloadedCnt < MAX_IMAGE_RELOAD_COUNT) { // MAX_IMAGE_RELOAD_COUNT
																// 횟수 만큼 리로딩 시도
						setTimeout(function() {
							reloadedCnt++;
							image.src = item;
						}, IMAGE_RELOAD_INTERVAL);
					} else {
						image.onerror = function() {
						}; // 에러 처리 함수는 해제
						// ToDo: 서버에 터치 시 '이미지를 다시 로딩한다'는 의미를 갖는 이미지를 추가하고, 해당
						// 이미지
						// URL로 변경
						image.src = 'touch_to_reload_image.jpg';
						$(image).bind("click", reloadFunction);
					}
				};
			}
		});
	}
}

function refreshSignList() {
	var view = $.mobile.activePage;
	getSignList(view.find("#apiCode").val(), 1);
}

function getMoreSignList() {
	var view = $.mobile.activePage;

	if (view.find("#hasMore").val() == "true") {
		// 더보기가 완료 되기전에 중복 호출 방지하기 위해
		view.find("#hasMore").val(false);
		getSignList(view.find("#apiCode").val(), view.find("#pageno").val());
	}
}

function parseCommnetList(comment) {
	var tmp = [];
	var name = "";
	var dept = "";
	var position = "";
	var date = "";
	var type = "";

	if (comment.userInfo.length > 0) {
		var infoArr = comment.userInfo.trim().split("/");

		for ( var j = 0; j < infoArr.length; j++) {
			if (startsWith(infoArr[j], "U=")) {
				name = infoArr[j].replace("U=", "");
			} else if (startsWith(infoArr[j], "D=")) {
				date = infoArr[j].replace("D=", "");
			} else if (startsWith(infoArr[j], "O=")) {
				dept = infoArr[j].replace("O=", "");
			} else if (startsWith(infoArr[j], "U=")) {
				position = infoArr[j].replace("U=", "");
			} else if (startsWith(infoArr[j], "T=")) {
				type = infoArr[j].replace("T=", "");
			}
		}
		tmp.push("<li>");
		tmp.push("<div class='userName'>" + name + "<span class='commentDate'>" + date + "</span></div>");
		tmp.push("<div class='info'>" + comment.text.trim().replace(/\\n/g, "<br>") + "</div>");
		tmp.push("</li>");

		return tmp.join("");
	} else {
		return;
	}
}

function openSecuritySign(apiCode, link, applid, guid, participantapprtype,
		apprstatus, attachcnt, commentCk, postponeCk, externalDocFlag, summaryDocFlag, wordtype, additionalOfficeUserId) {
	var view = $.mobile.activePage;

	// 초기화
	view.find("#confirmSignPasswordDialog #password").val("");
	view.find("#confirmSignPasswordDialog").find("#password").focus();

	view.find("#confirmSignPasswordDialog #signApiCode").val(apiCode);
	view.find("#confirmSignPasswordDialog #link").val(link);
	view.find("#confirmSignPasswordDialog #applid").val(applid);
	view.find("#confirmSignPasswordDialog #guid").val(guid);
	view.find("#confirmSignPasswordDialog #participantapprtype").val(participantapprtype);
	view.find("#confirmSignPasswordDialog #apprstatus").val(apprstatus);
	view.find("#confirmSignPasswordDialog #attachcnt").val(attachcnt);
	view.find("#confirmSignPasswordDialog #commentCk").val(commentCk);
	view.find("#confirmSignPasswordDialog #postponeCk").val(postponeCk);	
	view.find("#confirmSignPasswordDialog #externalDocFlag").val(externalDocFlag);
	view.find("#confirmSignPasswordDialog #summaryDocFlag").val(summaryDocFlag);
	view.find("#confirmSignPasswordDialog #wordtype").val(wordtype);
	view.find("#confirmSignPasswordDialog #additionalOfficeUserId").val(additionalOfficeUserId);

	view.find("#confirmSignPasswordDialog").popup("open");
}

function confirmSignPassword() {
	var view = $.mobile.activePage;
	var params = {};

	var password = view.find("#confirmSignPasswordDialog #password").val();
	var apiCode = view.find("#confirmSignPasswordDialog #signApiCode").val();
	var link = view.find("#confirmSignPasswordDialog #link").val();
	var applid = view.find("#confirmSignPasswordDialog #applid").val();
	var guid = view.find("#confirmSignPasswordDialog #guid").val();
	var participantapprtype = view.find("#confirmSignPasswordDialog #participantapprtype").val();
	var apprstatus = view.find("#confirmSignPasswordDialog #apprstatus").val();
	var attachcnt = view.find("#confirmSignPasswordDialog #attachcnt").val();
	var commentCk = view.find("#confirmSignPasswordDialog #commentCk").val();
	var postponeCk = view.find("#confirmSignPasswordDialog #postponeCk").val();
	var externalDocFlag = view.find("#confirmSignPasswordDialog #externalDocFlag").val();
	var summaryDocFlag = view.find("#confirmSignPasswordDialog #summaryDocFlag").val();
	var wordtype = view.find("#confirmSignPasswordDialog #wordtype").val();
	var additionalOfficeUserId = view.find("#confirmSignPasswordDialog #additionalOfficeUserId").val();
	var participantId = view.find("#confirmSignPasswordDialog #participantId").val();
	var linkdoc = view.find("#confirmSignPasswordDialog #linkdoc").val();

	params["pwd"] = password;

	GW_PROXY.invokeOpenAPI("org", "checksancpasswd", params, function(data) {
		if (data.status == "0") {
			showSignDetails(link, applid, guid, participantapprtype, apprstatus, attachcnt, 
					commentCk, postponeCk, externalDocFlag, summaryDocFlag, wordtype, 
					additionalOfficeUserId, participantId, GW_CONTROLLER_SIGN.linkdocIdx, "", "", "");
		} else {
			alert(MGW_RES.get("gw_msg_common_wrong_password"));
			return;
		}
	});
}

function closeSignPasswordDialog() {
	var view = $.mobile.activePage;
	view.find("#confirmSignPasswordDialog").popup("close");
}

function getApplId(type) {
	if (type == undefined || type == "") {
		return "";

	// 결재대기 (applid : 2010)
	} else if (type == "wait" || type == "waitlist") {
		return "2010";
		
	// 공람대기 (applid : 3020)
	} else if (type == "gongram" || type == "gongramwaitlist") {
		return "3020";

	// 공람완료 (applid : 3010)
	} else if (type == "gongramcomplete" || type == "gongramcompletelist") {
		return "3010";
		
	// 결재진행 (applid : 2020)
	} else if (type == "now" || type == "nowlist") {
		return "2020";
		
	// 접수대기 (applid : 5010)
	} else if (type == "receiptwait" || type == "receiptwaitlist"){
		return "5010";
	
	// 개인문서함[완료문서] (applid : 6020)
	} else if (type == "mycomplete" || type == "mycompletelist") {
		return "6020";

	// 개인문서함[결재한문서] (applid : 6022)
	} else if (type == "userprocessed" || type == "userprocessedlist") {
		return "6022";

	// 기록물 대장[완료함] (applid : 8010)
	} else if (type == "complete" || type == "completelist") {
		return "8010";
		
	// 발송처리함 (applid : 4010)
	} else if (type == "dispatch" || type == "dispatchlist") {
		return "4010";
		
	} else {
		return type;
	}
}function showMailMenu() {
	var controlFN = function(){
	};
	PAGE_CONTROLLER.showPage("mail_menu", controlFN);	
}

function showBoardMenu() {
	var controlFN = function(){
	};
	// 
	PAGE_CONTROLLER.showPage("board_menu", controlFN);	
}

function showOrgMenu() {
	var controlFN = function(){
	};
	// 
	PAGE_CONTROLLER.showPage("org_menu", controlFN);	
}

function showScheduleMenu(){
	var controlFN = function(){
	};
	if (GW_OpenAPI.schedule_type == "original")
		PAGE_CONTROLLER.showPage("sch6_menu", controlFN);
	else if(GW_OpenAPI.schedule_type == "tmanager2")
		PAGE_CONTROLLER.showPage("sch2_menu", controlFN);
	else
		PAGE_CONTROLLER.showPage("sch_menu", controlFN);
}

function showContactMenu() {
	var controlFN = function(){
	};
	// 
	PAGE_CONTROLLER.showPage("contact_menu", controlFN);
}

function showSignMenu() {
	var controlFN = function(){
	};
	PAGE_CONTROLLER.showPage("sign_menu_8", controlFN);
}

function showSettingsMenu() {
	var controlFN = function(){
	};
	if (GW_OpenAPI.settings_type == "settings1")
		PAGE_CONTROLLER.showPage("settings_menu", controlFN);
	else if (GW_OpenAPI.settings_type == "settings2")
		PAGE_CONTROLLER.showPage("settings2_menu", controlFN);
}

// 18.11.12 tkofs
function menu_show_all(){
	hideWebView();
	// 이전 페이지 모두 Clear
	PAGE_CONTROLLER.cleanViewStack();
	var controlFN = function(){};
	PAGE_CONTROLLER.showPage("menu_show_all", controlFN);
}
function getToHtml(toStr){
	if(toStr.indexOf(',')>-1){
		var mailTo = toStr.split(',');
		var mailToHtml = "";
		for(var to in mailTo){
			mailToHtml += "<a href=\"javascript:sendSMail('"+mailTo[to].trim().replace(/"/g, "&#34;").replace(/</gi, '&lt;').replace(/>/gi, '&gt;')+"');\" class='btn_user blue'>" +
					"<span>" + mailTo[to].trim().replace(/</gi, '&lt;').replace(/>/gi, '&gt;') + "</span><span class='viewUser'></span></a>"
		}
		return mailToHtml;
	}else{
		return "<a href=\"javascript:sendSMail('"+toStr.trim().replace(/"/g, "&#34;").replace(/</gi, '&lt;').replace(/>/gi, '&gt;')+"');\" class='btn_user blue'>" +
				"<span>" + toStr.trim().replace(/</gi, '&lt;').replace(/>/gi, '&gt;') + "</span><span class='viewUser'></span></a>";
	}
}

$.urlParam = function(name){
    var results = new RegExp('[\?]' + name + '=([^&#]*)').exec(window.location.href);
    return results[1] || 0;
}

function cancel_show_menu_all() {
	var frag = $.urlParam("frag");
	if (GWPlugin.usePlugin) { // APP, popup인 경우
		GWPlugin.closePopupViewer("", false, function(){
			var callBackFN = function(data){
				setSessionInfo(data);
				GWPlugin.notiLoadingCompleted();
			};
			GWPlugin.getSessionInfo(callBackFN, function(){
				alert("Error: fail to set sessioninfo.");
			});		
		});
	}
	else {
		PAGE_CONTROLLER.goBack();
	}
}