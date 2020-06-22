# synchronized和字符串的坑
在使用synchronized时，我们要对资源加锁，但是如果资源是字符串的时候是否会引发并发问题呢？看下面的执行代码。
```
class Test01 implements Runnable{
    private static final String prefix = "vvs-";

    private String ip;

    public Test01(String ip){
        this.ip = ip;
    }

    @Override
    public void run() {
        String lock = buildLock();
        synchronized (lock){
            System.out.println("[" + Thread.currentThread().getName() + "]开始运行了");
            // 休眠5秒模拟脚本调用
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[" + Thread.currentThread().getName() + "]结束运行了");
        }
    }

    private String buildLock(){
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(ip);
        String lock = sb.toString();
        System.out.println("[" + Thread.currentThread().getName() + "]构建了锁[" + lock + "]");
        return lock;
    }
}
public class Main {

    public static void main(String args[]){
        Thread[] th = new Thread[5];
        for(int i = 0;i < 5; i++){
            th[i] = new Thread(new Test01("well"));
        }

        for(int i = 0;i < 5; i++){
            th[i].start();
        }
    }
}
```
最后的结果我们发现，五个线程依旧是并行执行，问题出在哪里呢？通过查看代码的几个关键位置后，我们发现可能是在字符串加锁时出现问题，所以我们直接查看String对象的hash值，发现不相同，所以是字符串每次都是new了一个新对象，这时我们再去看StringBuilder的toString源码，发现每次都new了一个新对象。
既然找到问题，我们就直接强制调用String的intern的本地方法，让字符串在常量池中获取值，如果常量池中存在就不需要再次创建了。
修改后的局部代码如下:
```java
@Override
private String buildLock(){
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(ip);
        String lock = sb.toString().intern();
        System.out.println("[" + Thread.currentThread().getName() + "]构建了锁[" + lock + "]");
        return lock;
    }
```
