public class TestLruCache {
    public static void main(String[] args) {

        LeastRecentlyUsedCache lr = new LeastRecentlyUsedCache(5);
        System.out.println(lr.toString());
        lr.put(1, 1);
        lr.put(2, 2);
        System.out.println("Cache currently has room for " + lr.getMaxSize() + " more item(s)");
        System.out.println(lr.toString());
        lr.put(3, 3);
        lr.put(4, 4);
        lr.put(5, 5);
        System.out.println("Cache currently has room for " + lr.getMaxSize() + " more item(s)");
        System.out.println(lr.toString());
        Object val = lr.get(1);
        System.out.println("" + val);
        lr.put(6, 6);
        System.out.println("Cache currently has room for " + lr.getMaxSize() + " more item(s)");
        System.out.println(lr.toString());
        Object val2 = lr.get(2);

        if(val2 == null) System.out.println("Value Not Found");
        else System.out.println("Something went wrong");

        }
}