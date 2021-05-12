package com.chinaliyq.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/9 - 8:44
 * @Version: 1.0
 **/
public class ResourceMgr {
    public static BufferedImage goodTankLeft, goodTankUp, goodTankRight, goodTankDown;
    public static BufferedImage badTankLeft, badTankUp, badTankRight, badTankDown;
    public static BufferedImage bulletLeft, bulletUp, bulletRight, bulletDown;

    public static BufferedImage[] goodTankLefts = new BufferedImage[2];
    public static BufferedImage[] goodTankUps = new BufferedImage[2];
    public static BufferedImage[] goodTankRights = new BufferedImage[2];
    public static BufferedImage[] goodTankDowns = new BufferedImage[2];

    public static BufferedImage[] badTankLefts = new BufferedImage[2];
    public static BufferedImage[] badTankUps = new BufferedImage[2];
    public static BufferedImage[] badTankRights = new BufferedImage[2];
    public static BufferedImage[] badTankDowns = new BufferedImage[2];

    public static BufferedImage[] explodes = new BufferedImage[16];

    //加载顺序有误
//    public static BufferedImage loading(String path) throws IOException {
//        System.out.println(11);
//        return ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream(path));
//    }

    /**
     * 静态代码块
     */
    static {
        try {
            int index = 0;
            goodTankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankUps[index] = goodTankUp;
            goodTankLeft = ImageUtil.rotateImage(goodTankUp,-90);
            goodTankLefts[index] = goodTankLeft;
            goodTankRight = ImageUtil.rotateImage(goodTankUp,90);
            goodTankRights[index] = goodTankRight;
            goodTankDown = ImageUtil.rotateImage(goodTankUp,180);
            goodTankDowns[index] = goodTankDown;

            badTankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankUps[index] = badTankUp;
            badTankLeft = ImageUtil.rotateImage(badTankUp,-90);
            badTankLefts[index] = badTankLeft;
            badTankRight = ImageUtil.rotateImage(badTankUp,90);
            badTankRights[index] = badTankRight;
            badTankDown = ImageUtil.rotateImage(badTankUp,180);
            badTankDowns[index] = badTankDown;

            ++index;
            System.out.println(index);
            goodTankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
            goodTankUps[index] = goodTankUp;
            goodTankLeft = ImageUtil.rotateImage(goodTankUp,-90);
            goodTankLefts[index] = goodTankLeft;
            goodTankRight = ImageUtil.rotateImage(goodTankUp,90);
            goodTankRights[index] = goodTankRight;
            goodTankDown = ImageUtil.rotateImage(goodTankUp,180);
            goodTankDowns[index] = goodTankDown;

            badTankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
            badTankUps[index] = badTankUp;
            badTankLeft = ImageUtil.rotateImage(badTankUp,-90);
            badTankLefts[index] = badTankLeft;
            badTankRight = ImageUtil.rotateImage(badTankUp,90);
            badTankRights[index] = badTankRight;
            badTankDown = ImageUtil.rotateImage(badTankUp,180);
            badTankDowns[index] = badTankDown;

            badTankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankLeft = ImageUtil.rotateImage(badTankUp,-90);
            badTankRight = ImageUtil.rotateImage(badTankUp,90);
            badTankDown = ImageUtil.rotateImage(badTankUp,180);



            bulletUp =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletLeft = ImageUtil.rotateImage(bulletUp,-90);
            bulletRight = ImageUtil.rotateImage(bulletUp,90);
            bulletDown = ImageUtil.rotateImage(bulletUp,180);

            for (int i = 0; i < 16; i++) {
                explodes[i] =ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public BufferedImage load(String path) throws IOException {
//        BufferedImage image = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream(path));
//    }


}
