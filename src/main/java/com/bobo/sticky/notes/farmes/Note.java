package com.bobo.sticky.notes.farmes;

import com.bobo.sticky.notes.utils.DragAndResizeFrame;
import com.bobo.sticky.notes.utils.LogBoBo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * 一个便签窗体
 * @author bobo
 * @date 2022/1/15 21:00
 */
public class Note extends Stage {

    // 内容path
    private final static String NOTE_PATH = "/note.fxml";

    // 拖拽和调节窗口大小
    private DragAndResizeFrame dragAndResizeFrame;


    /**
     * 显示便签窗体
     * @throws IOException
     */
    public void showNote() throws IOException {
        LogBoBo.info(this.getClass(),"-- bobo -- showNote begin ==");

        // 设置标题
        this.setTitle("bobo note");

        // 设置最小界面
        this.setMinWidth(300);
        this.setMinHeight(300);

        // 设置无标题栏及边框
        this.initStyle(StageStyle.TRANSPARENT);

        // 设置内容
        this.setScene(new Scene(FXMLLoader.load(this.getClass().getResource(NOTE_PATH))));

        // 设置窗体可拖拽和调节大小
        dragAndResizeFrame = new DragAndResizeFrame(this);

        // 显示界面
        super.show();

        LogBoBo.info(this.getClass(),"-- bobo -- showNote  end ==");
    }




    @FXML
    private Button btnAddNewNote;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextArea taContent;

    @FXML
    private void addNewNode(MouseEvent mouseEvent) throws IOException {
        LogBoBo.info(this.getClass(), "-- bobo -- add New Node -- " + this.btnAddNewNote);
        LogBoBo.info(this.getClass(), "-- bobo -- btnAddNewNote.getLayoutX() = " + this.btnAddNewNote.getLayoutX());
        tfTitle.setText("bobo -- 标题");
        taContent.setText("-- bobo -- 内容");
        LogBoBo.info(this.getClass(), "-- bobo -- len = " + tfTitle.getText());
        Note newNote = new Note();
        LogBoBo.info(this.getClass(), "-- bobo -- this.x = " + mouseEvent.getScreenX());
        newNote.setX(mouseEvent.getScreenX() + 300.00);
        LogBoBo.info(this.getClass(), "-- bobo -- newNote.x = " + newNote.getX());
        newNote.showNote();
        ObservableList<Screen> screens = Screen.getScreens();
        LogBoBo.info(this.getClass(), "-- bobo -- screens.size() = " + screens.size());
    }

}
