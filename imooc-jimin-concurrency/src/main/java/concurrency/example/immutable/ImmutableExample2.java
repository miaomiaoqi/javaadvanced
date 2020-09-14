package concurrency.example.immutable;


import com.google.common.collect.Maps;
import com.miaoqi.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * JavaAPI的不可变类
 *
 * @author miaoqi
 * @date 2018/11/10
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {
    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args){
        // 会抛出异常
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

}
