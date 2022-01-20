package com.bobo.sticky.notes;

import com.bobo.sticky.notes.farmes.Note;
import com.bobo.sticky.notes.utils.LogBoBo;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 博博便签启动类
 * @author bobo
 * @date 2022/1/15 19:51
 */
public class StickyNoteApp extends Application {

    /**
     * 启动
     * @param args
     */
    public static void main(String[] args) {
        LogBoBo.info(StickyNoteApp.class,"-- bobo -- main start ==");
        launch(args);
        LogBoBo.info(StickyNoteApp.class,"-- bobo -- main end ==");
    }

    /**
     * 启动窗口
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        LogBoBo.info(this.getClass(),"-- bobo -- start begin ==");
        primaryStage.setTitle("博博便签");
        primaryStage.setWidth(300);
        primaryStage.setHeight(300);
        // primaryStage.show();
        Note note = new Note();
        note.showNote();
        LogBoBo.info(this.getClass(),"-- bobo -- start end ==");
    }
}
