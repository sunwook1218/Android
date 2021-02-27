package com.hs.mobile.gw.plugin;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;

public class HMPPluginResult
  extends PluginResult
{
  public HMPPluginResult(PluginResult.Status status, boolean b)
  {
    super(status, b);
  }
  
  public HMPPluginResult(PluginResult.Status status, float f)
  {
    super(status, f);
  }
  
  public HMPPluginResult(PluginResult.Status status, int i)
  {
    super(status, i);
  }
  
  public HMPPluginResult(PluginResult.Status status, JSONArray message)
  {
    super(status, message);
  }
  
  public HMPPluginResult(PluginResult.Status status, JSONObject message)
  {
    super(status, message);
  }
  
  public HMPPluginResult(PluginResult.Status status, String message)
  {
    super(status, message);
  }
  
  public HMPPluginResult(PluginResult.Status status)
  {
    super(status);
  }
}
