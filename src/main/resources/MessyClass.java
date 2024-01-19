import GreatClass;

public class MessyClass {
    private GreatClass great;
    void messyJob(){
        great.value = 5;
        great.name = "Lol";
    }

    void superMessyJob(){
        int i = great.getValue();
        String s = great.getName();
        great.setName("lol");
        great.setName("duplicate");
        great.setName("notCounted");
        great.value = 5;
    }

    int testFun(){
        return 0;
    }

    void moreFun(){

    }

    void evenMoreFun(){
        great.test = 0;
        great.a = 1;
        great.b = 1;
        great.c = 1;
    }
}