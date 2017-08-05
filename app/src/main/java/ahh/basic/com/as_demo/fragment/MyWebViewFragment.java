package ahh.basic.com.as_demo.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ahh.basic.com.as_demo.R;

/**
 * Created by Administrator on 2017/7/31.
 */
public class MyWebViewFragment extends Fragment
{
    private WebView webView;

    private ProgressDialog dialog;

    private Context context;

    @Override
    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.webview, container, false);

        webView = (WebView) view.findViewById(R.id.webview);
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

        return view;
    }

    private void openDialog(int newProgress)
    {
        if (null == dialog)
        {
            dialog = new ProgressDialog(this.context);
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
}
