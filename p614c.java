import java.util.*;
import java.awt.geom.Line2D;

public class p614c {

    private static class Point {
        private double x;
        private double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double norm2() {
            return x * x + y * y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int xOrigin = scanner.nextInt();
        int yOrigin = scanner.nextInt();
        List<Point> points = new ArrayList<>();
		Point firstPoint = new Point(0,0);
        for (int i = 0; i < n; i++) {
            int xCurrent = scanner.nextInt() - xOrigin;
            int yCurrent = scanner.nextInt() - yOrigin;
            points.add(new Point(xCurrent, yCurrent));
        }
        System.out.println(getSnowCleared(points));
    }

    private static double getSnowCleared(List<Point> polygon) {
        double outer = Double.NEGATIVE_INFINITY;
        double inner = Double.POSITIVE_INFINITY;
        for(int i = 0; i < polygon.size(); i++){
            Point v = polygon.get(i);
            Point nv = polygon.get((i+1)%polygon.size());
            double norm2 = v.norm2();
            outer = Math.max(outer, norm2);
            inner = Math.min(inner, Line2D.ptSegDistSq(v.x,v.y,nv.x,nv.y,0,0));
        }
        return Math.PI * (outer - inner);
    }

}
