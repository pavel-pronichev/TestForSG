/**
 * Created by Хозяин on 28.10.2015.
 */
public class Circle {
    private float diameter;

    public Circle(float diameter){
        this.diameter = diameter;
    }

    public float getSquare(){
        return (float)(Math.PI*diameter*diameter/4);
    }
}
