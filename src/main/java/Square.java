
public class Square {
    private float side;

    public Square(float side){
        this.side = side;
    }

    public float getSquare(){
        float result = side*side;
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
