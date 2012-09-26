package ori.ogapi.geometry;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import ori.ogapi.maths.Math;
import ori.ogapi.maths.Frac;
import ori.ogapi.maths.Fracs;

public class Shapes {

	/// EUCLIDAN
	public static int euclidian(Point p, Shape s) {
		if (s instanceof Circle)
			return euclidian(p,(Circle)s);
		if (s instanceof Segment)
			return euclidian(p,(Segment)s);
		
		List<Segment> segments = toSegmentList(s);
		int res = Integer.MAX_VALUE;
		int tmp;
		for (Segment seg : segments) {
			tmp = euclidian(p,seg);
			if (tmp < res)
				res = tmp;
		}
		return res;
	}

	public static int euclidian(Point p, Circle c) {
		int a = c.radius();
		int b = p.euclidian(c.center());
		return (a-b)*(a-b);
	}

	public static int euclidian(Point p, Segment s) {
		int s1 = p.euclidian(s.first());
		int s2 = p.euclidian(s.second());
		int b = s.first().euclidian(s.second());
		return (s1 * s2) / b;
	}

	/// COLLIDE 

	public static boolean collide(Shape s1, Shape s2) {
		if (s2.contains(s1))
			return true;
		if (s1.contains(s2))
			return true;

		if (s1 instanceof Segment)
			return collide((Segment)s1,s2);
		if (s2 instanceof Segment)
			return collide((Segment)s2,s1);
		if (s1 instanceof Circle)
			return collide((Circle)s1,s2);
		if (s2 instanceof Circle)
			return collide((Circle)s2,s1);

		for (Segment seg1 : toSegmentList(s1)) {
			for (Segment seg2 : toSegmentList(s2)) {
				if (collide(seg1,seg2))
					return true;
			}
		}
		return false;
	}

	public static boolean collide(Segment s1, Shape s2) {
		if (s2.contains(s1))
			return true;

		if (s2 instanceof Segment)
			return collide(s1,(Segment)s2);
		if (s2 instanceof Circle)
			return collide(s1,(Circle)s2);
		List<Segment> segments = toSegmentList(s2);
		for (Segment seg : segments) {
			if (collide(s1,seg))
				return true;
		}
		return false;
	}

	public static boolean collide(Segment s1, Segment s2) {
		Point vector = s1.vector();
		Point vector2 = s2.vector();
		if (vector.isColinearTo(vector2)) {
			Rectangle r1 = s1.boundingRect();
			Rectangle r2 = s2.boundingRect();
			return (r1.contains(s2.first()) || r1.contains(s2.second())
			     || r2.contains(s1.first()) || r2.contains(s1.second()));
		}
		Point v1,v2,v3,v4;
		int det1,det2,det3,det4;
		v1 = s2.first().clone();
		v2 = s2.second().clone();
		v3 = s1.first().clone();
		v4 = s1.second().clone();
		v1.minus(s1.first());
		v2.minus(s1.first());
		v3.minus(s2.first());
		v4.minus(s2.first());
		det1 = vector.det(v1);
		det2 = vector.det(v2);
		det3 = vector2.det(v3);
		det4 = vector2.det(v4);
		// det1 * det2 impossible => out of int
		return (((det1 * det2) <= 0) && ((det3 * det4) <= 0));
	}

	public static boolean collide(Segment s1, Circle c2) {
		long dx,dy,a,b,c,cx,cy;
		Point ds = s1.vector();
		dx = ds.x; dy = ds.y;
		cx = c2.center().x; cy = c2.center().y;
		a = dx * dx + dy * dy;
		b = 2 * (dx * (s1.first().x-c2.center().x) + dy * (s1.first().y - c2.center().y));
		c = cx * cx + cy * cy;
		c += s1.first().x * s1.first().x;
		c += s1.first().y * s1.first().y;
		c -= 2 * (cx * s1.first().x + cy * s1.first().y);
		c -= c2.radius() * c2.radius();

		long delta = b * b - 4 * a * c;
		if (delta < 0)
			return false;
		if (delta == 0) {
			float root = 0 - b;
			root /= (float)(2 * a);
			float x1,y1;
			x1 = s1.first().x + (dx * root);
			y1 = s1.first().y + (dy * root);
			return (((x1 >= s1.first().x) && (x1 <= s1.second().x))
			     && ((y1 >= s1.first().y) && (y1 <= s1.second().y)));
		}
		else {
			float root1,root2;
			root1 = 0 - b; root2 = 0 - b;
			root1 += Math.sqrt((int)delta);
			root2 -= Math.sqrt((int)delta);
			root1 /= (float)(2*a);
			root2 /= (float)(2*a);
			float x1,x2,y1,y2;
			x1 = s1.first().x + (dx * root1);
			y1 = s1.first().y + (dy * root1);
			x2 = s1.first().x + (dx * root2);
			y2 = s1.first().y + (dy * root2);
			return ((((x1 >= s1.first().x) && (x1 <= s1.second().x))
			      && ((y1 >= s1.first().y) && (y1 <= s1.second().y)))
				 || (((x2 >= s1.first().x) && (x2 <= s1.second().x))
				  && ((y2 >= s1.first().y) && (y2 <= s1.second().y))));
		}
	}

	public static boolean collide(Circle c1, Shape s2) {
		if (c1.contains(s2))
			return true;
		if (s2.contains(c1))
			return true;

		if (s2 instanceof Circle)
			return collide(c1,(Circle)s2);
		if (s2 instanceof Segment)
			return collide((Segment)s2,c1);

		List<Segment> segments = toSegmentList(s2);
		for (Segment seg : segments) {
			if (collide(seg,c1))
				return true;
		}
		return false;
	}

	public static boolean collide(Circle c1, Circle c2) {
		return (c1.center().euclidian(c2.center()) 
		    <= ((c1.radius()+c2.radius())*(c1.radius()+c2.radius())));
	}


	// in collide points, if a shape is strictly contained inside the other then no collide
	// points are returned.
	// if there is an infinite number of points then a way to get them is provided: see
	// the method documentation for more details
	/**
	 * If there is no colliding point  the list will be empty.
	 * If the returned value is <code>null</code> it means one shape is
	 * contained inside the other.
	 */
	public static List<Point> collidePoints(Shape s1, Shape s2) {
		if (s2.contains(s1))
			return null;
		if (s1.contains(s2))
			return null;
		if (s1 instanceof Segment)
			return collidePoints((Segment)s1,s2);
		if (s2 instanceof Segment)
			return collidePoints((Segment)s2,s1);
		if (s1 instanceof Circle)
			return collidePoints((Circle)s1,s2);
		if (s2 instanceof Circle)
			return collidePoints((Circle)s2,s1);
		return collidePoints(toSegmentList(s1),toSegmentList(s2));
	}

	public static List<Point> collidePoints(List<Segment> s1, List<Segment> s2) {
		List<Point> result = new LinkedList<Point>();
		List<Point> tmp;
		// set comparator euclidian dist from s1
		for (Segment seg1 : s1) {
			for (Segment seg2 : s2) {
				tmp = collidePoints(seg1,seg2);
				if (tmp != null)
					result.addAll(tmp);
			}
		}
		return result;
	}

	public static List<Point> collidePoints(Segment s1, Shape s2) {
		if (s2 instanceof Segment)
			return collidePoints(s1,(Segment)s2);
		if (s2 instanceof Circle)
			return collidePoints(s1,(Circle)s2);
		List<Segment> list = new LinkedList<Segment>();
		list.add(s1);
		return collidePoints(list,toSegmentList(s2));
	}

	// TODO if aligned ?
	// only two points : first and last
	public static List<Point> collidePoints(Segment s1, Segment s2) {
		List<Point> result = new LinkedList<Point>();
		// TODO comparator
		int x1,y1,x2,y2,x3,y3,x4,y4;
		x1 = s1.first().x;
		y1 = s1.first().y;
		x2 = s1.second().x;
		y2 = s1.second().y;
		x3 = s2.first().x;
		y3 = s2.first().y;
		x4 = s2.second().x;
		y4 = s2.second().y;
		long denom,numa,numb;
		double ua,ub;
		denom = (y4-y3)*(x2-x1) - (x4-x3)*(y2-y1);
		numa = (x4-x3)*(y1-y3) - (y4-y3)*(x1-x3);
		numb = (x2-x1)*(y1-y3) - (y2-y1)*(x1-x3);
		if ((numa == 0) && (numb == 0) && (denom == 0)) {
			// lines are coincident
			result.add(new Point((x1+x2)/2,(y1+y2)/2)); // TODO
			return result;
		}
		if (denom == 0)
			return null;
		ua = (double)((double)numa / (double)denom);
		ub = (double)((double)numb / (double)denom);
		if ((ua < 0) || (ua > 1) || (ub < 0) || (ub > 1))
			return null;
		double x,y;
		x = x1 + ua * (x2 - x1);
		y = y1 + ua * (y2 - y1);
		result.add(new Point(x,y));
		return result;
	}

	public static List<Point> collidePoints(Segment s1, Circle c2) {
		long dx,dy,a,b,c,cx,cy;
		Point ds = s1.vector();
		dx = ds.x; dy = ds.y;
		cx = c2.center().x; cy = c2.center().y;
		a = dx * dx + dy * dy;
		b = 2 * (dx * (s1.first().x-c2.center().x) + dy * (s1.first().y - c2.center().y));
		c = cx * cx + cy * cy;
		c += s1.first().x * s1.first().x;
		c += s1.first().y * s1.first().y;
		c -= 2 * (cx * s1.first().x + cy * s1.first().y);
		c -= c2.radius() * c2.radius();

		long delta = b * b - 4 * a * c;
		if (delta < 0)
			return null;

		List<Point> result = new LinkedList<Point>();
		// TODO set comparator
		if (delta == 0) {
			float root = 0 - b;
			root /= (float)(2 * a);
			float x1,y1;
			x1 = s1.first().x + (dx * root);
			y1 = s1.first().y + (dy * root);
			if (((x1 >= s1.first().x) && (x1 <= s1.second().x))
			 && ((y1 >= s1.first().y) && (y1 <= s1.second().y)))
				result.add(new Point(x1,y1));
		}
		else {
			float root1,root2;
			root1 = 0 - b; root2 = 0 - b;
			root1 += Math.sqrt((int)delta);
			root2 -= Math.sqrt((int)delta);
			root1 /= (float)(2*a);
			root2 /= (float)(2*a);
			float x1,x2,y1,y2;
			x1 = s1.first().x + (dx * root1);
			y1 = s1.first().y + (dy * root1);
			x2 = s1.first().x + (dx * root2);
			y2 = s1.first().y + (dy * root2);
			if (((x1 >= s1.first().x) && (x1 <= s1.second().x))
			 && ((y1 >= s1.first().y) && (y1 <= s1.second().y)))
			  	result.add(new Point(x1,y1));
			if (((x2 >= s1.first().x) && (x2 <= s1.second().x))
			 && ((y2 >= s1.first().y) && (y2 <= s1.second().y)))
			 	result.add(new Point(x2,y2));
		}
		return result;
	}

	public static List<Point> collidePoints(Circle c1, Shape s2) {
		if (s2 instanceof Segment)
			return collidePoints((Segment)s2,c1);
		if (s2 instanceof Circle)
			return collidePoints(c1,(Circle)s2);

		List<Point> result = new LinkedList<Point>();
		// set comparator euclidian dist from s1
		for (Segment seg2 : toSegmentList(s2))
			result.addAll(collidePoints(seg2,c1));
		return result;
	}

	public static List<Point> collidePoints(Circle c1, Circle c2) {
		int d = c1.center().euclidian(c2.center());
		if (d >= ((c1.radius()+c2.radius())*(c1.radius()+c2.radius())))
			return null;	// no collide
		if (d < ((c1.radius()-c2.radius())*(c1.radius()-c2.radius())))
			return null;	// one circle is contained by the other
		if ((d == 0) && (c1.radius() == c2.radius()))
			return null;	// infinite number of solutions

		double a = c1.radius() * c1.radius();
		a -= c2.radius() * c2.radius();
		a += d;
		double d2 = Math.sqrt(d);
		a /= (double)(2 * d2);
		double x,y;
		x = c1.center().x + ((a / d2)*(c2.center().x - c1.center().x));
		y = c1.center().y + ((a / d2)*(c2.center().y - c1.center().y));
		double h = c1.radius() * c1.radius();
		h -= a * a;
		List<Point> result = new LinkedList<Point>();
		// TODO comparator
		if (h == 0)
			result.add(new Point(x,y));
		else {
			h = Math.sqrt((int)h);
			double x1,y1,x2,y2;
			x1 = x + (h * (c2.center().y - c1.center().y) / d2);
			y1 = y - (h * (c2.center().x - c1.center().x) / d2);
			x2 = x - (h * (c2.center().y - c1.center().y) / d2);
			y2 = y + (h * (c2.center().x - c1.center().x) / d2);
			result.add(new Point(x1,y1));
			result.add(new Point(x2,y2));
		}
		return result;
	}


	/// CONVERT

	public static List<Segment> toSegmentList(Shape s) {
		if (s instanceof Circle)
			return null;
		Point p,p1,p2;
		Iterator<Point> iterator = s.points().iterator();
		if (!(iterator.hasNext()))
			return null;
		p1 = iterator.next();
		p = p1;
		p2 = null;
		if (!(iterator.hasNext()))
			return null;
		List<Segment> list = new LinkedList<Segment>();
		while (iterator.hasNext()) {
			p2 = iterator.next();
			list.add(new Segment(p1,p2));
			p1 = p2;
		}
		list.add(new Segment(p2,p));
		return list;
	}

////public static void moveOf(Shape s, Point p) {
////	if (s instanceof Rectangle) {
////		Rectangle r = (Rectangle)s;
////		r.origin().plus(p);
////		r.end().plus(p);
////	}
////	else if (s instanceof Circle)
////		((Circle)s).center().plus(p);
////	else if (s instanceof Segment) {
////		Segment seg = (Segment)s;
////		seg.first().plus(p);
////		seg.second().plus(p);
////	}
////}

	/// MISC
	
	public static Point origin(Shape s) {
		return s.boundingRect().origin();
	}

	private Shapes() { }

};

