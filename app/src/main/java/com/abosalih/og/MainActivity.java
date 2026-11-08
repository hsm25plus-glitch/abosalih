package com.abosalih.og;
import android.app.Activity;import android.content.Intent;import android.net.Uri;import android.os.Bundle;import android.view.View;
import android.webkit.*;import android.graphics.Color;
public class MainActivity extends Activity{
 static final String HOME="https://hsm25plus-glitch.github.io/abosalih/";
 WebView w;ValueCallback<Uri[]> fileCb;
 @Override protected void onCreate(Bundle b){super.onCreate(b);
  w=new WebView(this);w.setBackgroundColor(Color.WHITE);setContentView(w);
  WebSettings s=w.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setDatabaseEnabled(true);
  s.setMediaPlaybackRequiresUserGesture(false);s.setLoadWithOverviewMode(true);s.setUseWideViewPort(true);s.setTextZoom(100);
  s.setCacheMode(WebSettings.LOAD_DEFAULT);s.setUserAgentString(s.getUserAgentString()+" AboSalihApp/1");
  w.setWebViewClient(new WebViewClient(){
   @Override public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest r){Uri u=r.getUrl();
    if("hsm25plus-glitch.github.io".equals(u.getHost()))return false;
    try{startActivity(new Intent(Intent.ACTION_VIEW,u));}catch(Exception e){}return true;}});
  w.setWebChromeClient(new WebChromeClient(){
   @Override public boolean onShowFileChooser(WebView v,ValueCallback<Uri[]> cb,FileChooserParams p){
    if(fileCb!=null)fileCb.onReceiveValue(null);fileCb=cb;
    try{startActivityForResult(p.createIntent(),7);}catch(Exception e){fileCb=null;return false;}return true;}});
  w.setDownloadListener((url,ua,cd,mime,len)->{try{startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));}catch(Exception e){}});
  if(b!=null)w.restoreState(b);else w.loadUrl(HOME);}
 @Override protected void onActivityResult(int rc,int res,Intent d){if(rc==7&&fileCb!=null){fileCb.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(res,d));fileCb=null;}else super.onActivityResult(rc,res,d);}
 @Override protected void onSaveInstanceState(Bundle o){super.onSaveInstanceState(o);w.saveState(o);}
 @Override public void onBackPressed(){if(w.canGoBack())w.goBack();else super.onBackPressed();}
 @Override protected void onPause(){super.onPause();w.onPause();CookieManager.getInstance().flush();}
 @Override protected void onResume(){super.onResume();w.onResume();}
}
