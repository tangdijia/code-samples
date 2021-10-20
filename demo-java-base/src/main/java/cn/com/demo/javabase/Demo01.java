package cn.com.demo.javabase;

/**
 *
 */
public class Demo01 {

    public static void main(String[] args) {
        //java中，当你不声明的时候，默认小数都用double来表示；
        float a = (float) 0.1;
        System.out.println(a);

        //注意float是8位有效数字（包括小数掉前后），最后一位数字将会产生四舍五入
        float b = 1.32344435f;
        System.out.println(b);

        //位移运算符
        int c = 10;
        int d = c << 1;
        System.out.println(d);

    }
}
