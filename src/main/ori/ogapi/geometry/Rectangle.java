package ori.ogapi.geometry;

import java.util.LinkedList;

public class Rectangle extends AbstractShape {

	public Rectangle() {
		_origin = new Point();
		_end = new Point();
	}

	public Rectangle(Point origin, Point end) {
		set(origin,end);
	}

	public Rectangle(Point origin, int width, int height) {
		_origin = origin;
		_end = origin.clone();
		_end.x += width;
		_end.y += height;
	}

	public Rectangle(int x, int y, int width, int height) {
		_origin = new Point(x,y);
		_end = new Point(x+width,y+height);
	}

	@Override
	public Point center() {
		Point center = _origin.clone();
		center.plus(_end);
		center.x /= 2;
		center.y /= 2;
		return center;
	}

	@Override
	public void translate(Point v) {
		_origin.plus(v);
		_end.plus(v);
	}

////@Override
////public void setCenter(Point p) {
////	p.minus(this.center());
////	_origin.plus(p);
////	_end.plus(p);
////}

	public Point origin() {
		return _origin;
	}

	public Point end() {
		return _end;
	}

	public Point size() {
		return new Point(this.width(),this.height());
	}

	public int width() {
		return this.end().x-this.origin().x;
	}

	public int height() {
		return this.end().y-this.origin().y;
	}

	public void set(Point origin, Point end) {
		Point realOrigin = new Point(Math.min(origin.x,end.x),Math.min(origin.y,end.y));
		Point realEnd = new Point(Math.max(origin.x,end.x),Math.max(origin.y,end.y));
		_origin = realOrigin;
		_end = realEnd;
	}

	@Override
	public LinkedList<Point> points() {
		LinkedList<Point> res = new LinkedList<Point>();
		res.add(this.origin().clone());
		res.add(new Point(this.end().x,this.origin().y));
		res.add(this.end().clone());
		res.add(new Point(this.origin().x,this.end().y));
		return res;
	}
	
	@Override
	public Rectangle boundingShape() {
		return this;
	}

	@Override
	public Rectangle boundingRect() {
		return this;
	}

	@Override
	public boolean contains(Rectangle r) {
		if (r == null)
			return false;
		if (r == this)
			return true;
		return (this.contains(r.origin()) && this.contains(r.end()));
	}

	@Override
	public boolean contains(Point p) {
		if (p == null)
			return false;
		return ((p.x >= this.origin().x)
		     && (p.x <= this.end().x)
		     && (p.y >= this.origin().y)
			 && (p.y <= this.end().y));
	}

	@Override 
	public boolean equals(Shape s) {
		if (s == this)
			return true;
		if (s instanceof Rectangle)
			return this.equals((Rectangle)s);
		return false;
	}

	public boolean equals(Rectangle r) {
		return (r.origin().equals(this.origin()) && r.end().equals(this.end()));
	}

	@Override
	public Rectangle clone() {
		return new Rectangle(this.origin().clone(),this.end().clone());
	}

	@Override 
	public String toString() {
		return super.toString()+" {"+this.origin()+";"+this.end()+"}";
	}

	private Point _origin;
	private Point _end;

};

