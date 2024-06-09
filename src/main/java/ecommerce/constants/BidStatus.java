package ecommerce.constants;

public enum BidStatus {
    DRAFTED(1),
    SUBMITTED(2),
    ACCEPTED(3),
    REJECTED(4);

    private int value;


    BidStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
