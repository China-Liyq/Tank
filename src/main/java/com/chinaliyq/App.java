package com.chinaliyq;

<<<<<<< Updated upstream
=======
import com.chinaliyq.abstractfactory.view.MainController;
>>>>>>> Stashed changes
import com.chinaliyq.entity.Tank;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.PropertyMgr;
import com.chinaliyq.view.TankFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
<<<<<<< Updated upstream
=======
        new MainController().start();
//        baseVersion();
    }

    private static void baseVersion() {
        TankFrame tankFrame = new TankFrame();
>>>>>>> Stashed changes
        int initTankCount = Integer.parseInt((String) PropertyMgr.getValue("initTankCount"));
        TankFrame tankFrame = new TankFrame();


        for (int i = 0; i < initTankCount; i++) {
            tankFrame.tanks.add(new Tank(50 + i * 100, 150, Direction.DOWN, Group.BAD, tankFrame,true));
        }
//        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while (true){
            try {
                //窗口刷新
                Thread.sleep(25);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
