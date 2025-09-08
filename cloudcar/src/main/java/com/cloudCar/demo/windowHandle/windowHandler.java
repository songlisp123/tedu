package com.cloudCar.demo.windowHandle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class windowHandler extends StreamHandler {
    private JFrame jframe;
    private JTextArea jTextArea;
    private JToolBar jToolBar;
    private Jmenu jmenu;

    public windowHandler() {
        /*
        创建一个顶级窗口
         */
        jframe = new JFrame("输出控制台");
        jmenu = new Jmenu();
        setLevel(Level.ALL);
        jToolBar.add(new Myaction("蓝色", Color.BLUE));
        jToolBar.add(new Myaction("绿色", Color.GREEN));
        jToolBar.add(new Myaction("灰色", Color.GRAY));
        jToolBar.add(new Myaction("还原", new Color(30, 31, 34)));
        jTextArea = new JTextArea();
        jTextArea.setBackground(Color.black);
        jTextArea.setForeground(Color.cyan);
        jTextArea.setEditable(false);
        jframe.setSize(200, 200);
      /*
      将输出面板安装在JScrollPane滚动滑块中
      当内容超出窗口时候，在窗口下侧或者右侧会自动生成
      一个滚动条！
       */
        jframe.add(new JScrollPane(jTextArea));
        jframe.add(jToolBar,BorderLayout.NORTH);
        //setFocusableWindowState:用来描述窗口的聚焦形态，一般来说，作为第二窗口，设置为假
        jframe.setFocusableWindowState(false);
        jframe.setVisible(true);
        jframe.setJMenuBar(jmenu);
        setOutputStream(new OutputStream()
        {
            public void write(int b)
            {
            } // not called

            public void write(byte[] b, int off, int len)
            {
                jTextArea.append(new String(b, off, len));
            }
        });

    }

    public void publish(LogRecord record)
    {
        if (!jframe.isVisible()) return;
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
            jTextArea.setBackground((Color) getValue("color"));
            int red = c.getRed();
            int green = c.getGreen();
            int blue = c.getBlue();
            Color c1 = new Color(255-red,255-green,255-blue);
            jTextArea.setForeground(c1);
        }
    }
}
