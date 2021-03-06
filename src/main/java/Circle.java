
public class Circle {
    private float diameter;

    public Circle(float diameter){
        this.diameter = diameter;
    }

    public float getSquare(){
        float result = (float)(Math.PI*diameter*diameter/4);
        result = round(result, 2);
        return result;
    }

    private float round(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }
}
