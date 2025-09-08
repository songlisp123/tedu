package com.cloudCar.demo.windowHandle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * A handler for displaying log records in a window.
 */
public class windowHandler extends StreamHandler
{
   private JFrame frame;
   private JToolBar jToolBar;
   private JTextArea output;
   private JMenuBar menuBar;

   public windowHandler()
   {
      /*
      创建一个顶级窗口
       */
      frame = new JFrame();
      menuBar = new JMenuBar();
      setLevel(Level.ALL);
      jToolBar = new JToolBar();
      jToolBar.add(new Myaction("蓝色", Color.BLUE));
      jToolBar.add(new Myaction("绿色", Color.GREEN));
      jToolBar.add(new Myaction("灰色", Color.GRAY));
      jToolBar.add(new Myaction("还原", new Color(30, 31, 34)));

      //设置主框架的位置大小
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
      int screenWidth = d.width;
      int screenHeight = d.height;

      //这是一个妈蛋的
      int x = (screenWidth - 800) / 2;
      int y = (screenHeight - 600) / 2;
      frame.setBounds(x, y, 800, 600);

//      jPanel = new JPanel();
      /*
      设置一个文本区域块
       */
      output = new JTextArea();
      /*
      设置文本区域块的颜色：
      setBackground：背景色
      setForeground：前景色
      setEditable：是否可编辑
       */
      output.setBackground(Color.black);
      output.setForeground(Color.cyan);
      output.setEditable(false);

      /*
      将输出面板安装在JScrollPane滚动滑块中
      当内容超出窗口时候，在窗口下侧或者右侧会自动生成
      一个滚动条！
       */
      frame.add(new JScrollPane(output));
      frame.add(jToolBar,BorderLayout.NORTH);
      //setFocusableWindowState:用来描述窗口的聚焦形态，一般来说，作为第二窗口，设置为假
      frame.setFocusableWindowState(false);
      frame.setVisible(true);
      frame.setJMenuBar(menuBar);

      /*
      设置输出流，改流会在文本区域面板上写入由日志记录器输入的
      文本信息
       */
      setOutputStream(new OutputStream()
         {
            public void write(int b)
            {
            } // not called

            public void write(byte[] b, int off, int len)
            {
               output.append(new String(b, off, len));
            }
         });
   }
   /*
     格式化并更新信息
     一些流程：如果没有输入流或者输入记录为null，安静返回
     然后调用过滤器过滤给定信息级别，最后调用格式化器格式化信息
     然后写入到当前的输出流，输出流调用write方法在屏幕上写入信息
     flush：刷新方法，刷新任何缓冲区的信息
    */
   public void publish(LogRecord record)
   {
      if (!frame.isVisible()) return;
      super.publish(record);
      flush();
   }

   private class Myaction extends AbstractAction {
      private String name;
      private Color c;

      public Myaction(String name ,Color c) {
         this.name = name;
         this.c = c;
         putValue(Action.NAME,name);
         putValue("color",c);
         putValue(Action.SHORT_DESCRIPTION,"将背景设置为%s".formatted(c));
      }
      @Override
      public void actionPerformed(ActionEvent e) {
         output.setBackground((Color) getValue("color"));
         int red = c.getRed();
         int green = c.getGreen();
         int blue = c.getBlue();
         Color c1 = new Color(255-red,255-green,255-blue);
         output.setForeground(c1);
      }
   }
}
