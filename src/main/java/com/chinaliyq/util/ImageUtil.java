package com.chinaliyq.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: liyq
 * @Description: 改变图片的角度
 * @Date: 2021/5/9 - 20:27
 * @Version: 1.0
 **/
public class ImageUtil {
    public static BufferedImage rotateImage(BufferedImage bufferedImage,int degree){
        int with = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage image;
        Graphics2D graphics2D;
        (graphics2D = (image = new BufferedImage(with,height,type))
                .createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.rotate(Math.toRadians(degree), with / 2,height / 2);
        graphics2D.drawImage(bufferedImage,0,0,null);
        graphics2D.dispose();
        return image;

    }
}
