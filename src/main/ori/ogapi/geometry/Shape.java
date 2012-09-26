package ori.ogapi.geometry;

public interface Shape {

	public Point center();
	public void translate(Point vector);
//	public void setCenter(Point p);
	public Iterable<Point> points();
	public Shape boundingShape();
	public Rectangle boundingRect();
//	public boolean contains(Iterable<Point> points);
	public boolean contains(Shape s);
	public boolean contains(Point p);
	@Override public boolean equals(Object o);
	public Shape clone();

};

