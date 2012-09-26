package ori.ogapi.geometry;

public abstract class AbstractShape implements Shape {

	@Override public abstract Iterable<Point> points();
	@Override public abstract Shape boundingShape();
	@Override public abstract Rectangle boundingRect();
	@Override public abstract boolean contains(Point p);
	@Override public abstract AbstractShape clone();
	@Override public abstract void translate(Point vector);

	@Override
	public Point center() {
		Point sum = new Point(0,0);
		int cpt = 0;
		for (Point p : this.points()) {
			sum.plus(p);
			cpt++;
		}
		if (cpt > 0) {
			sum.x /= cpt;
			sum.y /= cpt;
		}
		return sum;
	}

	@Override
	public boolean contains(Shape s) {
		if (s == null)
			return false;
		if (this.contains(s.boundingRect()))
			return true;
		return this.contains(s.boundingShape().points());
	}

	public boolean contains(Rectangle r) {
		return (this.contains(r.points()));
	}

	public boolean contains(Iterable<Point> points) {
		for (Point p : points) {
			if (!(this.contains(p)))
				return false;
		}
		return true;
	}

	@Override 
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof Shape)
			return this.equals((Shape)o);
		return false;
	}

	public boolean equals(Shape s) {
		return (this.contains(s) && s.contains(this));
	}

};

