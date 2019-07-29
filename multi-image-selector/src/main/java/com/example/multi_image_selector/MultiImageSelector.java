package com.example.multi_image_selector;

import android.os.Bundle;

public class MultiImageSelector {
    private static MultiImageSelector sSelector;
    public static MultiImageSelector newInstance() {

       if(sSelector==null)
       {
           synchronized (MultiImageSelector.class)
           {
               if(sSelector==null)
               {
                   sSelector = new MultiImageSelector();
               }
           }
       }
       return sSelector;
    }
}
