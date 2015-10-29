/**
 * Created by Хозяин on 28.10.2015.
 */
public class Rectangle {
    private float sideA;
    private float sideB;

    public Rectangle(float sideA, float sideB){
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public float getSquare(){
        return sideA*sideB;
    }

}
