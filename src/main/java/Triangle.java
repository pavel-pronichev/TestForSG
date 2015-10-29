
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
        float result = (float)Math.sqrt(p*(p-a)*(p-b)*(p-c));
        result = round(result,2);
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
