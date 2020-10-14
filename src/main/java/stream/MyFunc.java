package stream;

interface MyFunc<T> {
    boolean func(T vl, T v2);
}

class HighTemp {
    private int hTemp;

    HighTemp(int ht) {
        hTemp = ht;
    }

    boolean sameTemp(HighTemp ht2) {
        return hTemp == ht2.hTemp;
    }
}

class Demo {
    public static void main(String args[]) {
        MyFunc<HighTemp> myFunc = HighTemp::sameTemp;
        System.out.println(myFunc.func(new HighTemp(1), new HighTemp(2)));
    }
} 