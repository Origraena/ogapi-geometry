package ori.ogapi.geometry;

public class AdaptedShape implements Shape {

	public AdaptedShape(Shape s) {
		_shape = s;
	}

	public Shape shape() {
		return _shape;
	}

	public void setShape(Shape s) {
		_shape = s;
	}

	@Override 
	public Point center() {
		return _shape.center();
	}

	@Override
	public void translate(Point vector) {
		_shape.translate(vector);
	}

//	@Override 
//	public void setCenter(Point p) {
//		_shape.setCenter(p);
//	}

	@Override 
	public Iterable<Point> points() {
		return _shape.points();
	}

	@Override 
	public Shape boundingShape() {
		return _shape.boundingShape();
	}

	@Override 
	public Rectangle boundingRect() {
		return _shape.boundingRect();
	}

	@Override 
	public boolean contains(Shape s) {
		return _shape.contains(s);
	}

	@Override 
	public boolean contains(Point p) {
		return _shape.contains(p);
	}

	@Override 
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o instanceof AdaptedShape)
			return _shape.equals(((AdaptedShape)o).shape());
		if (o instanceof Shape)
			return _shape.equals(o);
		return false;
	}

	@Override
	public AdaptedShape clone() {
		return new AdaptedShape(_shape.clone());
	}

	private Shape _shape;

};

