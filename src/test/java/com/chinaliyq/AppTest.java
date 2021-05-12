package com.chinaliyq;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.chinaliyq.util.ResourceMgr;
import com.chinaliyq.util.Audio;
import com.chinaliyq.util.PropertyMgr;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }
    //测试加载图片资源
    @Test
    public void test() {
        try {
            BufferedImage image = ImageIO.read(new File("F:/GitHub/Tank/src/main/resources/images/10.gif"));
//            image = ImageIO.read(new File("F:/GitHub/Tank/src/images/10.gif"));
//            ClassLoader classLoader = App.class.get
            assertNotNull(image);
//            System.out.println(classLoader);
            URL resource2 = AppTest.class.getClassLoader().getResource("images/10.gif");
            URL resource3 = App.class.getClassLoader().getResource("");
            System.out.println(resource2);
            System.out.println(resource3);
            BufferedImage images = ImageIO.read(AppTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(images);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testAudio() {
        Audio audio = new Audio("audio/war1.wav");
        audio.loop();
    }

    @Test
    public void newTank() {
        BufferedImage bulletLeft = ResourceMgr.bulletLeft;
        assertNotNull(bulletLeft);
    }

    @Test
    public void propertyTest() {
        Object name = PropertyMgr.getValue("initTankCount");
        System.out.println(name);
    }
}
