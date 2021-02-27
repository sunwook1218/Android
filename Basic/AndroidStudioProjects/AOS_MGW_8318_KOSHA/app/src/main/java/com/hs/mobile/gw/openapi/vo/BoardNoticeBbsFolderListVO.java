package com.hs.mobile.gw.openapi.vo;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import com.hs.mobile.gw.openapi.vo.BoardNoticeListVO.Item;

public class BoardNoticeBbsFolderListVO extends DefaultVO<JSONArray> {
    public List<Item> items;

    public BoardNoticeBbsFolderListVO(JSONArray json) {
        super(json);
        if (json != null) {
            items = new ArrayList<Item>();
            for (int i = 0; i < json.length(); ++i) {
                items.add(new Item(json.optJSONObject(i), true));
            }
        }
    }
}
