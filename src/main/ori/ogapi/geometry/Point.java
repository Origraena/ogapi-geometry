package ori.ogapi.geometry;

public class Point implements Comparable<Point> {

	public int x,y;

	public Point() {
		this.x = 0;
		this.y = 0;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(float x, float y) {
		this.x = (int)x;
		this.y = (int)y;
	}

	public Point(double x, double y) {
		this.x = (int)x;
		this.y = (int)y;
	}

	public Point clone() {
		return new Point(this.x,this.y);
	}

	public void plus(Point p) {
		plus(p.x,p.y);
	}

	public void minus(Point p) {
		minus(p.x,p.y);
	}

	public void plus(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public void minus(int x, int y) {
		this.x -= x;
		this.y -= y;
	}

	public int euclidian(Point p) {
		Point vector = p.clone();
		vector.minus(this);
		return vector.scalar(vector);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof Point)
			return this.equals((Point)o);
		return false;
	}

	public boolean equals(Point p) {
		return (this.x == p.x) && (this.y == p.y);
	}

	@Override
	public int compareTo(Point p) {
		return (this.x == p.x) ? this.y - p.y : this.x - p.x;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

	// VECTORS 
	
	public int scalar(Point p) {
		return (this.x * p.x) + (this.y * p.y);
	}

	public int det(Point p) {
		return (this.x * p.y) - (this.y * p.x);
	}
	
	public boolean isOrthogonalTo(Point vector) {
		return (this.scalar(vector) == 0);
	}

	public boolean isColinearTo(Point vector) {
		return (this.det(vector) == 0);
	}


	// STATIC 

	public static boolean areAligned(Point p1, Point p2, Point p3) {
		Point v1,v2;
		v1 = p2.clone();
		v2 = p3.clone();
		v1.minus(p1);
		v2.minus(p2);
		return (v1.isColinearTo(v2));
	}

	public static boolean areAligned(Point p1, Point p2, Point p3, Point p4) {
		return (areAligned(p1,p2,p3) && areAligned(p1,p2,p4));
	}

};

