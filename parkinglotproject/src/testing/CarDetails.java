package testing;


public class CarDetails {
    String regNo;
    Colour colour;
    String slotNo;
    public enum Colour{
        BLUE,BLACK,RED,WHITE,GREEN
    }
    public CarDetails() {
        super();  
    }
    public CarDetails(String regNo,String colour,String slotNo){
        setColour(colour);
        setRegNo(regNo);
        setSlotNo(slotNo);
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setColour(String colour) {
        try{
        this.colour = Colour.valueOf(colour.toUpperCase());
        }catch (IllegalArgumentException ex){
            System.out.println("Please enter valid Colour");
            for(Colour c:Colour.values() ){
                System.out.println(c);

            }
        }
    }
    public Colour getColour() {
        return colour;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }

    public String getSlotNo() {
        return slotNo;
    }
}
