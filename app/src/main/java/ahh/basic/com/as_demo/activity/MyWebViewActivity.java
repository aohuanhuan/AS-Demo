package ahh.basic.com.as_demo.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ahh.basic.com.as_demo.R;

/**
 * Created by Administrator on 2017/7/28.
 */
public class MyWebViewActivity extends AppCompatActivity
{
    private WebView webView;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webView = (WebView) findViewById(R.id.webview);
        //加载本地的html文件，文件放置在assets文件夹中
        //webView.loadUrl("file:///android_asset/mywebview.html");
        String url = "http://www.imooc.com";
        webView.loadUrl(url);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });

        //设置网页的加载进度
        webView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress)
            {
                if (100 == newProgress)
                {
                    //加载完成
                    closeDialog();
                } else
                {
                    //打开加载进度progressBar
                    openDialog(newProgress);
                }
            }
        });
    }

    private void openDialog(int newProgress)
    {
        if (null == dialog)
        {
            dialog = new ProgressDialog(this);
            dialog.setTitle("网页正在加载中");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMessage("当前已加载:" + newProgress + "%.");
            dialog.setProgress(newProgress);

            dialog.show();
        } else
        {
            dialog.setMessage("当前已加载:" + newProgress + "%.");
            dialog.setProgress(newProgress);
        }
    }

    private void closeDialog()
    {
        if (null != dialog && dialog.isShowing())
        {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Toast.makeText(this, "你当前访问的地址是:" + webView.getUrl(), Toast.LENGTH_SHORT).show();
            if (webView.canGoBack())
            {
                webView.goBack();
                return true;
            } else
            {
                System.exit(0);
            }
        }
        return false;
    }
}