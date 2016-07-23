package com.twt.service.wenjin.support;

import android.util.Log;

import com.twt.service.wenjin.bean.Attach;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dell on 2016/7/18.
 */
public class StringHelper {

    public static String replace(String input, Attach[] replace, String[] id){
        for (int i = 0; i < id.length; i++) {
            for (int j = 0; j < replace.length; j++) {
                if(id[i].equals(replace[j].id)){
                    input = input.replace("[attach]" + id[i] + "[/attach]", "<img src=\""+replace[j].attachment+"\">");
                    break;
                }
            }

//            Pattern r = Pattern.compile("\\[attach\\]"+id[i]+"\\[/attach\\]");
//            Matcher m = r.matcher("[attach]"+id[i]+"[/attach]");
//            m.replaceAll("<img src=\""+replace[i].attachment+"\">");
            Log.e("lqy", "id = "+ id[i] + "<img src=\""+replace[i].attachment+"\">");
        }
        return input;
    }
}
