package com.hs.mobile.gw.openapi.vo;

import android.util.Log;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.util.Debug;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BoardNoticeListVO extends DefaultVO<JSONObject> {
    public String version;
    public Channel channel;

    public BoardNoticeListVO(JSONObject json) {
        super(json);
        ViewModel.Log("::: " + json.toString());
        if (json != null) {
            try {
                version = json.optString("@version");
                channel = new Channel(json.optJSONObject("channel"));
                Debug.trace(json.toString(5));
            } catch (JSONException e) {
                Log.e("MGW", "= ERROR !!");
            }
        }
    }

    public class Channel {
        public String total;
        public String pagesize;
        public String auth_read;
        public String title;
        public String ANONBOARD;
        public String auth_write;
        public String auth_reply;
        public String FORM_ID;
        public String LIST_TYPE;
        public String pageno;
        public String auth_title;
        public String FORM_TYPE;
        public List<Item> item = null;

        public Channel(JSONObject json) {
            if (json != null) {
                total = json.optString("total");
                pagesize = json.optString("pagesize");
                auth_read = json.optString("auth_read");
                title = json.optString("title");
                ANONBOARD = json.optString("ANONBOARD");
                auth_write = json.optString("auth_write");
                auth_reply = json.optString("auth_reply");
                FORM_ID = json.optString("FORM_ID");
                LIST_TYPE = json.optString("LIST_TYPE");
                pageno = json.optString("pageno");
                auth_title = json.optString("auth_title");
                FORM_TYPE = json.optString("FORM_TYPE");

                JSONArray jarr = json.optJSONArray("item");
                if (jarr != null) {
                    item = new ArrayList<Item>();
                    for (int i = 0; i < jarr.length(); ++i) {
                        item.add(new Item(jarr.optJSONObject(i)));
                    }
                }else{
                    JSONObject jaObj = json.optJSONObject("item");
                    if(jaObj!=null){
                        item = new ArrayList<Item>();
                        item.add(new Item(jaObj));
                    }
                }
            }
        }
    }

    public static class Item {
        public String thread_seq;
        public String pubDate;
        public String preface_code;
        public String posterID;
        public String preface_name;
        public String status;
        public String link;
        public String bstatus;
        public String brdname;
        public String guid;
        public String brdid;
        public String author;
        public String title;
        public String commentcount;
        public String mtrlReadAuth;
        public String thread_depth;
        public String thread_id;
        public String attech_cnt;
        public String brdType;  // tkofs 메인 페이지 즐겨찾기 용도

        public Item(JSONObject json, boolean folder) {
            if(folder){
                this.brdType = json.optString("@brdType");
                this.title = json.optString("@text");
                this.brdid = json.optString("@id");
            }else{
                new Item(json);
            }
        }

        public Item(JSONObject json) {
            thread_seq = json.optString("thread_seq");
            pubDate = json.optString("pubDate");
            preface_code = json.optString("preface_code");
            posterID = json.optString("posterID");
            preface_name = json.optString("preface_name");
            status = json.optString("status");
            link = json.optString("link");
            bstatus = json.optString("bstatus");
            brdname = json.optString("brdname	");
            guid = json.optString("guid");
            brdid = json.optString("brdid");
            author = json.optString("author");
            title = json.optString("title");
            commentcount = json.optString("commentcount");
            mtrlReadAuth = json.optString("mtrlReadAuth");
            thread_depth = json.optString("thread_depth");
            thread_id = json.optString("thread_id");
            attech_cnt = json.optString("attech_cnt");
        }
    }
}
