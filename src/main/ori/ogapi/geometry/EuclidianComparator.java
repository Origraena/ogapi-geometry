package ori.ogapi.geometry;

import java.util.Comparator;

public class EuclidianComparator implements Comparator<Shape> {

	public EuclidianComparator(Point origin) {
		_origin = origin;
	}

	public int compare(Shape s1, Shape s2) {
		return Shapes.euclidian(_origin,s1) - Shapes.euclidian(_origin,s2);
	}

	private Point _origin;

};

