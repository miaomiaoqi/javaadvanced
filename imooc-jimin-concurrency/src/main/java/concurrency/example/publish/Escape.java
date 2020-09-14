package concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;

/**
 * 对象逸出
 *
 * @author miaoqi
 * @date 2018/11/7
 */
@Slf4j
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args){
        new Escape();
    }

}
