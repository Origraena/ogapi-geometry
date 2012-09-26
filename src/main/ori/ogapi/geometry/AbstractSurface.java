package ori.ogapi.geometry;

import ori.ogapi.util.Iterator;

public abstract class AbstractSurface<S extends Shape> implements Surface<S> {

	@Override public abstract boolean add(S s);
	@Override public abstract boolean remove(S s);
	@Override public abstract Iterator<S> iterator();

	@Override
	public boolean contains(S s) {
		for (S shape : iterator())
			if (s == shape)
				return true;
		return false;
	}

	@Override
	public Surface getIn(Shape s) {
		try {
			Surface<S> res = this.getClass().newInstance();
			for (S shape : this) {
				if (s.contains(shape))
					res.add(shape);
			}
			return res;
		}
		catch (Exception ex) {
			System.err.println("Error, exception :"+ex+"\n"+ex.getMessage());
			return null;
		}
	}

	@Override
	public Surface getPartlyIn(Shape s) {
		try {
			Surface<S> res = this.getClass().newInstance();
			for (S shape : this) {
				if (Shapes.collide(s,shape)) 
					res.add(shape);
			}
			return res;
		}
		catch (Exception ex) {
			System.err.println("Error, exception :"+ex+"\n"+ex.getMessage());
			return null;
		}
	}

	@Override
	public Surface getAt(Point p) {
		try {
			Surface<S> res = this.getClass().newInstance();
			for (S shape : this) {
				if (shape.center().equals(p))
					res.add(shape);
			}
			return res;
		}
		catch (Exception ex) {
			System.err.println("Error, exception :"+ex+"\n"+ex.getMessage());
			return null;
		}
	}

};

