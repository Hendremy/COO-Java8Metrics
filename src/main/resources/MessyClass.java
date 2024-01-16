public class MessyClass {
    private GreatClass great;
    void messyJob(){
        great.value = 5;
        great.name = "Lol";
    }

    void superMessyJob(){
        int i = great.getValue();
        String s = great.getName();
    }
}