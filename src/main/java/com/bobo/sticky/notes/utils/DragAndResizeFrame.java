package com.bobo.sticky.notes.utils;

import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * 窗体可拖拽和调整窗体大小
 */
public class DragAndResizeFrame {

    // 调节窗体的偏移值，在窗体边缘的大概位置就可以调整窗体了
    private final static double RESIZE_OFFSET = 5.00;
    // 移动窗体的屏幕最下面的高度
    private final static double MOVE_MIX_HEIGHT = 80.00;
    // 移动窗体距离顶端的可点击高度
    private final static double MOVE_MAX_HEIGHT = 80.00;
    // 最小窗体大小
    private final static double MIN_WIDTH = 400.00;
    private final static double MIN_HEIGHT = 80.00;
    // 调节窗体的最大偏移值，上左移动时需要使用，防止抖动
    private final static double RESIZE_MAX_OFFSET = 8.00;

    private double x = 0.00;    // x位置
    private double y = 0.00;    // y 位置

    private double width = 0.00;    // 宽度
    private double height = 0.00;   // 高度
    private double xOffset = 0.00;  // 移动横坐标
    private double yOffset = 0.00;  // 移动纵坐标

    private boolean resizeRight;        // 是否处于右边界调整窗口状态
    private boolean resizeLeft;         // 是否处于左边界调整窗口状态
    private boolean resizeTop;          // 是否处于上边界调整窗口状态
    private boolean resizeBottom;       // 是否处于下边界调整窗口状态
    private boolean resizeTopRight;     // 是否处于上右边界调整窗口状态
    private boolean resizeTopLeft;      // 是否处于上左边界调整窗口状态
    private boolean resizeBottomRight;  // 是否处于下右边界调整窗口状态
    private boolean resizeBottomLeft;   // 是否处于下左边界调整窗口状态

    // 窗体，添加拖拽和调整大小的窗体
    private Stage stage;

    public DragAndResizeFrame(){}

    /**
     * 添加窗体
     * @param stage
     */
    public DragAndResizeFrame(Stage stage){
        this.stage = stage;

        // 添加x、y改变时的监听
        stage.xProperty().addListener(this :: xChanged);
        stage.yProperty().addListener(this :: yChanged);
        // 添加宽、高改变时监听
        stage.widthProperty().addListener(this :: widthChanged);
        stage.widthProperty().addListener(this :: heightChanged);
        
        // 设置鼠标点击事件
        stage.getScene().getRoot().setOnMousePressed(this :: mousePressed);
        // 设置鼠标移动事件
        stage.getScene().getRoot().setOnMouseMoved(this :: mouseMoved);
        // 设置鼠标拖拽事件
        stage.getScene().getRoot().setOnMouseDragged(this :: mouseDragged);
    }


    /**
     * x 属性改变时触发
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void xChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        if (newValue != null){
            x = newValue.doubleValue();
        }
    }

    /**
     * y 属性改变时触发
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void yChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        if (newValue != null){
            y = newValue.doubleValue();
        }
    }

    /**
     * width 属性改变时触发
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void widthChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        if (newValue != null){
            width = newValue.doubleValue();
        }
    }

    /**
     * hegith 属性改变时触发
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void heightChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        if (newValue != null){
            height = newValue.doubleValue();
        }
    }

    /**
     * 鼠标点击事件
     * 获取当前点击的x、y位置
     * @param mouseEvent
     */
    private void mousePressed(MouseEvent mouseEvent) {
        // 防止穿透
        mouseEvent.consume();
        // 获取鼠标点击在窗口中的位置
        // x轴鼠标在窗口中点击的位置
        if (mouseEvent.getSceneX() <= RESIZE_OFFSET || stage.getWidth() - mouseEvent.getSceneX() <= RESIZE_OFFSET){
            xOffset = 0;
        } else {
            xOffset = mouseEvent.getSceneX();
        }
        // y轴鼠标在窗口中点击的位置
        if (mouseEvent.getSceneY() >= MOVE_MAX_HEIGHT || mouseEvent.getSceneY() <= RESIZE_OFFSET){
            yOffset = 0;
        } else {
            yOffset = mouseEvent.getSceneY();
        }
    }

    /**
     * 鼠标移动事件
     * 设置鼠标状态，是否是调整窗体状态
     * @param mouseEvent
     */
    private void mouseMoved(MouseEvent mouseEvent) {
        // 防止穿透
        mouseEvent.consume();
        // 设置鼠标默认状态
        Cursor cursor = Cursor.DEFAULT;
        resizeTop = resizeBottom = resizeLeft = resizeRight = false;
        resizeTopLeft = resizeTopRight = resizeBottomLeft = resizeBottomRight = false;
        // 左下角、右下角、下边界调整窗体状态
        if (mouseEvent.getSceneY() >= stage.getHeight() - RESIZE_OFFSET){
            // 左下角调整窗体状态
            if (mouseEvent.getSceneX() <= RESIZE_OFFSET){
                resizeBottomLeft = true;
                cursor = Cursor.NE_RESIZE;
            }
            // 右下角调整窗体状态
            else if (mouseEvent.getSceneX() >= stage.getWidth() - RESIZE_OFFSET){
                resizeBottomRight = true;
                cursor = Cursor.SE_RESIZE;
            }
            // 下边界调整窗体状态
            else {
                resizeBottom = true;
                cursor = Cursor.S_RESIZE;
            }
        }
        // 左上角、右上角、上边界调整窗体状态
        else if (mouseEvent.getSceneY() <= RESIZE_OFFSET){
            // 左上角调整窗体状态
            if (mouseEvent.getSceneX() <= RESIZE_OFFSET){
                resizeTopLeft = true;
                cursor = Cursor.NW_RESIZE;
            }
            // 右上角调整窗体状态
            else if (mouseEvent.getSceneX() >= stage.getWidth() - RESIZE_OFFSET){
                resizeTopRight = true;
                cursor = Cursor.SW_RESIZE;
            }
            // 上边界调整窗体状态
            else {
                resizeTop = true;
                cursor = Cursor.N_RESIZE;
            }
        }
        // 右边界调整窗体状态
        else if (mouseEvent.getSceneX() >= stage.getWidth() - RESIZE_OFFSET){
            resizeRight = true;
            cursor = Cursor.E_RESIZE;
        }
        // 左边界调整窗体状态
        else if (mouseEvent.getSceneX() <= RESIZE_OFFSET){
            resizeLeft = true;
            cursor = Cursor.W_RESIZE;
        } else{
            cursor = Cursor.DEFAULT;
        }

        stage.getScene().getRoot().setCursor(cursor);
    }

    /**
     * 鼠标拖拽事件
     * 移动窗体位置或者调整窗体大小
     * @param mouseEvent
     */
    private void mouseDragged(MouseEvent mouseEvent) {
        // 防止穿透
        mouseEvent.consume();
        // 移动窗体位置
        movePosition(mouseEvent);
        // 改变窗体大小
        mouseResize(mouseEvent);
    }

    /**
     * 移动窗体位置
     * @param mouseEvent
     */
    private void movePosition(MouseEvent mouseEvent) {
        // 根据鼠标的横纵坐标移动dialog位置
        if (yOffset != 0 && xOffset != 0){
            // 设置x坐标位置
            stage.setX(mouseEvent.getScreenX() - xOffset);
            // 设置顶部位置，防止超出屏幕
            if (mouseEvent.getScreenY() - yOffset <= 0){
                stage.setY(0);
            }
            // 设置底部位置，防止超出屏幕
            else if (mouseEvent.getScreenY() + yOffset + MOVE_MIX_HEIGHT >= Screen.getPrimary().getVisualBounds().getHeight()){
                stage.setY(Screen.getPrimary().getVisualBounds().getHeight() - MOVE_MIX_HEIGHT);
            }
            else {
                stage.setY(mouseEvent.getScreenY() - yOffset);
            }
        }
    }

    /**
     * 重新设置窗体大小
     * @param mouseEvent
     */
    private void mouseResize(MouseEvent mouseEvent){
        // 鼠标在窗体中的位置
        double mx = mouseEvent.getSceneX();
        double my = mouseEvent.getSceneY();
        double nx = stage.getX();
        double ny = stage.getY();
        double nw = stage.getWidth();
        double nh = stage.getHeight();

        // 设置宽度
        if (resizeRight || resizeBottomRight || resizeTopRight){
            nw = mx;
        }
        // 设置高度
        if (resizeBottom || resizeBottomRight || resizeBottomLeft){
            nh = my;
        }
        // 设置y坐标
        if (resizeTop || resizeTopRight || resizeTopLeft){
            // 设置y轴鼠标移动的距离，不能太大，容易晃
            if (my < 0){
                my = my <= -1 * RESIZE_MAX_OFFSET ? -1 * RESIZE_MAX_OFFSET : my;
            } else {
                my = my >= RESIZE_MAX_OFFSET ? RESIZE_MAX_OFFSET : my;
            }
            // 设置y轴高度
            nh -= my;
            // 设置y轴位置
            ny += nh <= MIN_HEIGHT ? 0 : my;
        }
        // 设置x坐标
        if (resizeLeft || resizeTopLeft || resizeBottomLeft) {
            if (mx < 0){
                mx = mx <= -1 * RESIZE_MAX_OFFSET ? -1 * RESIZE_MAX_OFFSET : mx;
            } else {
                mx = mx >= RESIZE_MAX_OFFSET ? RESIZE_MAX_OFFSET : mx;
            }
            nw -= mx;
            nx += nw <= MIN_WIDTH ? 0 : mx;
        }

        // 设置最小值
        if (nw <= MIN_WIDTH) nw = MIN_WIDTH;
        if (nh <= MIN_HEIGHT) nh = MIN_HEIGHT;

        // 重新设置x、y、宽度、高度
        stage.setX(nx);
        stage.setY(ny);
        stage.setWidth(nw);
        stage.setHeight(nh);
    }


}
