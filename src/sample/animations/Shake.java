package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;


/*          ИНСТРУКЦИЯ
*  Класс создан для задания анимации при неправильном введении логина и пароля
* */
public class Shake {

    private TranslateTransition tt;

    public Shake(Node node){
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(-10f);
        tt.setByX(10f);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);
    }
    public void playAnimShake(){
        tt.playFromStart();
    }

}
