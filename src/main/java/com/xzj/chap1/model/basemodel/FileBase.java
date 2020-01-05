package com.xzj.chap1.model.basemodel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "basepath")
public class FileBase {
    private String txtfile;
    private String imgfile;

    public String getTxtfile() {
        return txtfile;
    }

    public void setTxtfile(String txtfile) {
        this.txtfile = txtfile;
    }

    public String getImgfile() {
        return imgfile;
    }

    public void setImgfile(String imgfile) {
        this.imgfile = imgfile;
    }
}
