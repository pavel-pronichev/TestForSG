/**
 * Created by Хозяин on 28.10.2015.
 */
public class Triangle {
    private float a;
    private float b;
    private float c;

    public Triangle(float a, float b, float c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public float getSquare(){
        float p = (a + b + c)/2;

        return (float)Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

}
