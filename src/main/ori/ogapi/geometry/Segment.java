package ori.ogapi.geometry;

import java.util.LinkedList;

public class Segment extends AbstractShape {

	public Segment(Point p1, Point p2) {
		_first = p1;
		_second = p2;
	}

	public Segment(int x1, int y1, int x2, int y2) {
		_first = new Point(x1,y1);
		_second = new Point(x2,y2);
	}

	@Override 
	public LinkedList<Point> points() {
		LinkedList<Point> res = new LinkedList<Point>();
		res.add(_first);
		res.add(_second);
		return res;
	}

////public void setCenter(Point p) {
////	p.minus(this.center());
////	_first.plus(p);
////	_second.plus(p);
////}

	@Override
	public void translate(Point v) {
		_first.plus(v);
		_second.plus(v);
	}

	@Override 
	public Segment boundingShape() {
		return this;
	}

	@Override 
	public Rectangle boundingRect() {
		return new Rectangle(_first,_second);
	}

	@Override 
	public boolean contains(Point p) {
		if (!(boundingRect().contains(p)))
			return false;
		// if in rect
		// then the vector first,second
		// and the vector first,p
		// must be on the same "line"
		Point o = this.vector();
		Point q = p.clone();
		q.minus(_first);
		return (o.det(q) == 0);
	}

	public Point first() {
		return _first;
	}

	public Point second() {
		return _second;
	}

	public Point vector() {
		Point v = _second.clone();
		v.minus(_first);
		return v;
	}

	public boolean isIntersection(Segment s) {
		return false; // TODO
	}

	public boolean isVertical() {
		return (_first.x == _second.x);
	}

	public boolean isHorizontal() {
		return (_first.y == _second.y);
	}

	public boolean isPoint() {
		return _first.equals(_second);
	}

	@Override
	public boolean equals(Shape s) {
		if (s == this)
			return true;
		if (s instanceof Segment)
			return this.equals((Segment)s);
		return false;
	}

	public boolean equals(Segment s) {
		return ((_first.equals(s.first()) && _second.equals(s.second()))
		     || (_second.equals(s.first()) && _first.equals(s.second())));
	}

	@Override
	public Segment clone() {
		return new Segment(_first.clone(),_second.clone());
	}

	@Override
	public String toString() {
		return(super.toString())+" {"+this.first()+";"+this.second()+"}";
	}

	private Point _first, _second;

};

