package com.hs.mobile.gw.openapi.vo;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainBbsMenuInfoVO extends DefaultVO<JSONArray> {
    public List<MainBbsMenuInfoItemVO> mainBbsInfo = new ArrayList<MainBbsMenuInfoItemVO>();

    public MainBbsMenuInfoVO(JSONArray jarr) {
        super(jarr);
        if (jarr != null) {
            for (int i = 0; i < jarr.length(); ++i) {
                mainBbsInfo.add(new MainBbsMenuInfoItemVO(jarr.optJSONObject(i)));
            }
        }
    }
}