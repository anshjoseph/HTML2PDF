package com.example.pdfgen.PDF_Render.Tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.pdfgen.PDF_Render.Html;

public class SaveHtmlPDF {
    private PrintJob printJob;
    private Context context;
    private ViewGroup vg;
    private WebView webView;
    private String filename;
    private Html html;
    boolean loadingFinished = true;
    boolean redirect = false;

    public SaveHtmlPDF(Context context, ViewGroup vg, String filename,Html html) {
        this.context = context;
        this.vg = vg;
        this.webView = new WebView(context);
        webView.setVisibility(View.GONE);
        vg.addView(webView);
        this.filename = filename;
        this.html = html;
        this.webView.loadData(Base64.encodeToString(html.getHtmlBytes(),Base64.NO_PADDING), "text/html", "base64");
        this.webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                if (!loadingFinished) {
                    redirect = true;
                }

                loadingFinished = false;
                view.loadUrl(urlNewString);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                loadingFinished = false;
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(!redirect){
                    loadingFinished = true;
                    PrintTheWebPage(SaveHtmlPDF.this.webView);
                }

                if(loadingFinished && !redirect){
                    //HIDE LOADING IT HAS FINISHED
                } else{
                    redirect = false;
                }

            }
        });
    }




    private void PrintTheWebPage(WebView webView) {

        PrintManager printManager = (PrintManager)context.getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(this.filename);
        assert printManager != null;
        printJob = printManager.print(this.filename, printAdapter, new PrintAttributes.Builder().build());
    }
}
