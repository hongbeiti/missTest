package cn.utils.code;

import java.awt.image.BufferedImage;

public class CodeTest {  
    public static void main(String[] args) throws Exception {  
        String text = "http://www.xxx.com/"; // 二维码内容  
  
        // 生成二维码  
        //生成图片二维码存放目录  
        String targetPath = "/Users/zhihuawang/Downloads/codeTest/" + Utils.toStr();  
        //创建目录  
        Utils.makeDirs(targetPath);  
          
        int begin = 100;//code 开始数字  
        int end = 101;//code结束数字  
        for (int i = begin; i <= end; i++) {  
            //生成含日期的16位数字如20161214000001  
            String code = Utils.toStr() + Utils.formateNumber(i);  
            //获取二维码对象  
            BufferedImage image = Utils.toBufferedImage(text  
                    + "?payCode=" + code,240,240);  
            //生成含背景图+二维码的立牌的图  
            Utils.markImageByCode(image, "/Users/zhihuawang/Downloads/src.png",  
                    targetPath + "/" + code + ".png", 340, 160); // 340, 160
            //立牌的图加上code编号  
            Utils.pressText(code, targetPath + "/" + code + ".png", targetPath  
                    + "/" + code + ".png", 390, 417, 0.5f);  
        }  
        // 生成二维码  
    }  
}  