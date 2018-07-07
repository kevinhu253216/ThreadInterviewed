
public class TT implements Runnable{
    int b = 100;

    public synchronized void m1() throws Exception{
        b = 1000;
        Thread.sleep(5000);//睡眠可以切换到m2
        System.out.println("(m1): b = " + b);
    }
    public synchronized void m2() throws Exception{ //output=>   (m1): b = 1000
  // public  void m2() throws Exception{  //output=>   (m1):b = 2000 虽然m1被锁定，但是变量b仍然可以访问
                                          //必须m2也可以加锁
        Thread.sleep(2000);
        b = 2000;
    }

    public void run(){
        try{
            m1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws Exception{
        TT tt = new TT();
        Thread t = new Thread(tt);
        t.start();
        tt.m2();
    }

}
