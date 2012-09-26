package ori.ogapi.geometry;

import java.util.List;
import java.util.LinkedList;

public class ComplexShape extends AbstractShape {

	public ComplexShape() {
		_points = new LinkedList<Point>();
	}

	public ComplexShape(List<Point> points) {
		_points = points;
	}

	@Override 
	public List<Point> points() {
		return _points;
	}

////@Override
////public void setCenter(Point p) {
////	p.minus(this.center());
////	for (Point p2 : _points)
////		p2.plus(p);
////}

	@Override
	public void translate(Point v) {
		for (Point p : _points)
			p.plus(v);
	}

	public void addPoint(Point p) {
		_points.add(p);
	}

	public void removePoint(Point p) {
		_points.remove(p);
	}

	@Override
	public boolean contains(Point p) {
		if (p == null)
			return false;
		if (!(this.boundingRect().contains(p)))
			return false;
		ComplexShape boundingShape = this.boundingShape();
//		for (Point p : boundingShape.points())
//		doit être à gauche de tous les segments
		return true;// TODO
	}

	@Override 
	public ComplexShape boundingShape() {
		// TODO algo env convex
		return this;
	}

	@Override 
	public Rectangle boundingRect() {
		Point origin = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
		Point end = new Point(Integer.MIN_VALUE,Integer.MIN_VALUE);
		for (Point p : points()) {
			if (p.x < origin.x)
				origin.x = p.x;
			if (p.x > end.x)
				end.x = p.x;
			if (p.y < origin.y)
				origin.y = p.y;
			if (p.y > end.y)
				end.y = p.y;
		}
		return new Rectangle(origin,end);
	}

	@Override
	public ComplexShape clone() {
		ComplexShape c = new ComplexShape();
		for (Point p : _points)
			c.addPoint(p.clone());
		return c;
	}

	private List<Point> _points;

};

