package dwl.consumer_producer;

/**
 * @author wenlong.ding
 * @date 2021/1/6 19:18
 */
public interface AbstractStorage {

    void consume(int num);

    void produce(int num);

}
