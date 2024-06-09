package ecommerce.constants;

public enum SessionStatus {
    OPENED(1),
    CHECKED_OUT(2),
    CLOSED(3);

    private int value;


    SessionStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
