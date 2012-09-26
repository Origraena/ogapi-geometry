package ori.ogapi.geometry;

import java.util.LinkedList;

/// TODO
// 	segment du rectangle => meilleure detection de collision
//

public class Circle extends AbstractShape {

	public Circle() {
		_center = new Point();
		_radius = 0;
	}

	public Circle(Point center, int radius) {
		_center = center;
		setRadius(radius);
	}

	public Circle(int x, int y, int radius) {
		_center = new Point(x,y);
		setRadius(radius);
	}

	@Override
	public Point center() {
		return _center;
	}

	public void setCenter(Point c) {
		_center = c;
	}

	@Override
	public void translate(Point v) {
		_center.plus(v);
	}

	public int radius() {
		return _radius;
	}

	public void setRadius(int radius) {
		_radius = radius;
		if (_radius < 0)
			_radius = 0 - _radius;
	}

	/**
	 * Only returns four points : the more of each direction west,north,east,south.
	 */
	@Override
	public LinkedList<Point> points() {
		LinkedList<Point> res = new LinkedList<Point>();
		Point p;
		p = new Point(this.center().x-_radius,this.center().y);
		p = new Point(this.center().x,this.center().y-_radius);
		p =new Point(this.center().x+_radius,this.center().y);
		p =new Point(this.center().x,this.center().y+_radius);
		res.add(new Point(this.center().x-_radius,this.center().y));
		res.add(new Point(this.center().x,this.center().y-_radius));
		res.add(new Point(this.center().x+_radius,this.center().y));
		res.add(new Point(this.center().x,this.center().y+_radius));
		return res;
	}
	
	@Override
	public Circle boundingShape() {
		return this;
	}

	@Override
	public Rectangle boundingRect() {
		return new Rectangle(new Point(this.center().x-_radius,this.center().y-_radius),
		                     new Point(this.center().x+_radius,this.center().y+_radius));
	}

	@Override
	public boolean contains(Point p) {
		if (p == null)
			return false;
		return (this.center().euclidian(p) <= (_radius * _radius));
	}

	@Override 
	public boolean equals(Shape s) {
		if (s == this)
			return true;
		if (s instanceof Circle)
			return this.equals((Circle)s);
		return false;
	}

	public boolean equals(Circle c) {
		return (c.center().equals(this.center()) && (c.radius() == _radius));
	}

	@Override
	public Circle clone() {
		return new Circle(_center.clone(),_radius);
	}

	@Override
	public String toString() {
		return(super.toString())+" {"+this.center()+";"+this.radius()+"}";
	}

	private Point _center;
	private int _radius;

};

