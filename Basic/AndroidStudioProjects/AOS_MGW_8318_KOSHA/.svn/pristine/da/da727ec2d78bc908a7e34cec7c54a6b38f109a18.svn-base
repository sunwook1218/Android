package com.hs.mobile.gw.fcm;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.hs.mobile.gw.MenuListHelper;

public final class DefaultMessage {

    @SerializedName("badge")
    private int badgeCount;

    @SerializedName("msg_title")
    private String title;

    @SerializedName("msg_category")
    private String category;

    @SerializedName("click_view_url")
    private LinkedTreeMap<String, Object> event;

    public int getBadgeCount() {
        return badgeCount;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getEvent() {
        if (event != null) {
            JsonElement jsonElement = new Gson().toJsonTree(event);
            if (jsonElement.isJsonObject()) {
                return jsonElement.getAsJsonObject().toString();
            }
        }
        return "";
    }

    public static class MailClickEvent {

        @SerializedName("box_type")
        private String boxType;

        @SerializedName("is_security")
        private boolean isSecurity;

        @SerializedName("link")
        private String link;

        @SerializedName("link2")
        private String link2;

        public String getBoxType() {
            return boxType;
        }

        public boolean isSecurity() {
            return isSecurity;
        }

        public String getLink() {
            return link;
        }

        public String getLink2() {
            return link2;
        }

        @Override
        public String toString() {
            return "box_type : " + boxType + "\n"
                    + "is_security : " + isSecurity + "\n"
                    + "link : " + link + "\n"
                    + "link2 : " + link2;
        }
    }

    public static class BoardClickEvent {

        @SerializedName("link")
        private String link;

        @SerializedName("comment_count")
        private int commentCount;

        @SerializedName("dept_id") // FIXME field 앞뒤로 공백이 붙어있음....
        private String departmentId;

        public String getLink() {
            return link;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public String getDepartmentId() {
            return departmentId;
        }

        @Override
        public String toString() {
            return "comment_count : " + commentCount + "\n"
                    + "dept_id : " + departmentId + "\n"
                    + "link : " + link;
        }
    }

    public static class ApprClickEvent {

        @SerializedName("word_type")
        private String wordType;

        @SerializedName("participant_id")
        private String participantId;

        @SerializedName("link")
        private String link;

        @SerializedName("participant_appr_type")
        private String participantApprType;

        @SerializedName("has_opinion")
        private boolean hasOpinion;

        @SerializedName("doc_type")
        private String docType;

        @SerializedName("external_doc")
        private String externalDoc;

        @SerializedName("box_type")
        private String boxType;

        @SerializedName("appr_id")
        private String apprId;

        @SerializedName("attach_count")
        private int attachCount;

        @SerializedName("summary_doc")
        private String summaryDoc;

        @SerializedName("enforce_type")
        private String enforceType;

        @SerializedName("is_security")
        private boolean isSecurity;

        @SerializedName("doc_view_auth_link")
        private String docViewAuthLink;

        @SerializedName("appr_status")
        private String apprStatus;

        @SerializedName("rec_doc_status")
        private String recDocStatus;

        @SerializedName("additional_office_user_id")
        private String additionalOfficeUserId;

        @SerializedName("exam_id")
        private String examId;

        public String getWordType() {
            return wordType;
        }

        public String getParticipantId() {
            return participantId;
        }

        public String getLink() {
            return link;
        }

        public String getParticipantApprType() {
            return participantApprType;
        }

        public boolean hasOpinion() {
            return hasOpinion;
        }

        public String getDocType() {
            return docType;
        }

        public String getExternalDoc() {
            return externalDoc;
        }

        public String getBoxType() {
            return boxType;
        }

        public String getApprId() {
            return apprId;
        }

        public int getAttachCount() {
            return attachCount;
        }

        public String getSummaryDoc() {
            return summaryDoc;
        }

        public String getEnforceType() {
            return enforceType;
        }

        public boolean isSecurity() {
            return isSecurity;
        }

        public String getDocViewAuthLink() {
            return docViewAuthLink;
        }

        public String getApprStatus() {
            return apprStatus;
        }

        public String getRecDocStatus() {
            return recDocStatus;
        }

        public String getAdditionalOfficeUserId() {
            return additionalOfficeUserId;
        }

        public String getExamId() {
            return examId;
        }

        @Override
        public String toString() {
            return "word_type : " + boxType + "\n"
                    + "participant_id : " + participantId + "\n"
                    + "link : " + link + "\n"
                    + "participant_appr_type : " + participantApprType + "\n"
                    + "has_opinion : " + hasOpinion + "\n"
                    + "doc_type : " + docType + "\n"
                    + "external_doc : " + externalDoc + "\n"
                    + "box_type : " + boxType + "\n"
                    + "appr_id : " + apprId + "\n"
                    + "attach_count : " + attachCount + "\n"
                    + "summary_doc : " + summaryDoc + "\n"
                    + "enforce_type : " + enforceType + "\n"
                    + "is_security : " + isSecurity + "\n"
                    + "doc_view_auth_link : " + docViewAuthLink + "\n"
                    + "appr_status : " + apprStatus + "\n"
                    + "rec_doc_status : " + recDocStatus + "\n"
                    + "additional_office_user_id : " + additionalOfficeUserId + "\n"
                    + "exam_id : " + examId;
        }
    }

    public static class SquarePlusClickEvent {

        @SerializedName("square_id")
        private String square_id;

        @SerializedName("contents_id")
        private String contents_id;

        public String getSquareId() {
            return square_id;
        }

        public void setSquareId(String square_id) {
            this.square_id = square_id;
        }

        public String getContentsId() {
            return contents_id;
        }

        public void setContentsId(String contents_id) {
            this.contents_id = contents_id;
        }

        @Override
        public String toString() {
            return "square_id : " + square_id + "\n"
                    + "contents_id : " + contents_id;
        }
    }
}
