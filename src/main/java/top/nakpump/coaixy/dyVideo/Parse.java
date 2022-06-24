package top.nakpump.coaixy.dyVideo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Parse {
    private final String url;

    Parse(String url){
        this.url = url;
    }
    //获取VideoID
    private String getVideoID(String url) throws IOException {
        var document = Jsoup.connect(url).get();
        var result = document.location();
        result = getSubString(result,"/video/","?previous");
        return result;
    }
    //获取VID
    private String getVid() throws IOException {
        var api = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";
        api = api+getVideoID(this.url);
        var html = Jsoup.connect(api).ignoreContentType(true).execute().body();
        System.out.println(html);
        return getSubString(html, "\"vid\":\"", "\"");
    }
    //获取最终结果
    public String getTrueUrl() throws IOException {
        var document = Jsoup.connect("https://aweme.snssdk.com/aweme/v1/play/?video_id="+getVid()).ignoreContentType(true).execute();
        return document.url().toString();
    }



    //取文本中间
    private static String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }

}
