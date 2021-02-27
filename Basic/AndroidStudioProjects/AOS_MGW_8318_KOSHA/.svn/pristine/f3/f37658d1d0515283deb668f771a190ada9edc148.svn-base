package com.hs.mobile.gw.openapi.vo;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainBbsMenuInfoListVO extends DefaultVO<JSONArray> {
    public List<MainBbsMenuInfoListItemVO> bbsInfo = new ArrayList<MainBbsMenuInfoListItemVO>();

    public MainBbsMenuInfoListVO(JSONArray jarr) {
        super(jarr);
        if (jarr != null) {
            for (int i = 0; i < jarr.length(); ++i) {
                bbsInfo.add(new MainBbsMenuInfoListItemVO(jarr.optJSONObject(i)));
            }
        }
    }
}

