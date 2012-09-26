import ori.ogapi.geometry.*;
import org.junit.*;
import static org.junit.Assert.*;

public class SurfaceTest {

	@BeforeClass
	public static void initShapes() {
		_rectangles = new Rectangle[6];
		_circles = new Circle[4];
		_segments = new Segment[4];
	//	_complexes = new ComplexShape[1];
		
		_rectangles[0] = new Rectangle(0,0,23,9);
		_rectangles[1] = new Rectangle(4,2,5,5);
		_rectangles[2] = new Rectangle(6,3,10,2);
		_rectangles[3] = new Rectangle(1,7,2,1);
		_rectangles[4] = new Rectangle(6,5,5,1);
		_rectangles[5] = new Rectangle(6,5,10,1);

		_circles[0] = new Circle(12,4,1);
		_circles[1] = new Circle(12,4,2);
		_circles[2] = new Circle(12,4,3);
		_circles[3] = new Circle(19,7,1);

		_segments[0] = new Segment(9,2,9,7);
		_segments[1] = new Segment(0,4,20,4);
		_segments[2] = new Segment(0,0,23,9);
		_segments[3] = new Segment(0,4,4,4);
	}

	@Before
	public void initSurfaces() {
		_surfaces = new Surface[1];
		_surfaces[0] = new LinkedListSurface();
		for (int i = 0 ; i < 4 ; i++) {
			_surfaces[0].add(_rectangles[i]);
			_surfaces[0].add(_circles[i]);
		}
		_surfaces[0].add(_rectangles[4]);
		_surfaces[0].add(_rectangles[5]);
		_surfaces[0].add(_segments[0]);
		_surfaces[0].add(_segments[1]);
		_surfaces[0].add(_segments[2]);
		_surfaces[0].add(_segments[3]);
	}

	@Test
	public void getInRectangleTest() {
		Surface s;

		s = _surfaces[0].getIn(_rectangles[0]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertTrue(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertTrue(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertTrue(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_rectangles[1]);
		assertFalse(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_rectangles[2]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_rectangles[3]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertTrue(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_rectangles[4]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_rectangles[5]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

	}

	@Test
	public void getInCircleTest() {
		Surface s;

		s = _surfaces[0].getIn(_circles[0]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_circles[1]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_circles[2]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_circles[3]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertTrue(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

	}

	@Test
	public void getInSegmentTest() {
		Surface s;

		s = _surfaces[0].getIn(_segments[0]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_segments[1]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertTrue(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_segments[2]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getIn(_segments[3]);
		assertFalse(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertTrue(s.contains(_segments[3]));
	}

	@Test
	public void getPartlyInRectangleTest() {
		Surface s;

		s = _surfaces[0].getPartlyIn(_rectangles[0]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertTrue(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertTrue(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertTrue(s.contains(_segments[3]));

		s = _surfaces[0].getPartlyIn(_rectangles[1]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertTrue(s.contains(_segments[3]));

		s = _surfaces[0].getPartlyIn(_rectangles[2]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1])); // TODO
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getPartlyIn(_rectangles[3]);
		assertTrue(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertTrue(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getPartlyIn(_rectangles[4]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getPartlyIn(_rectangles[5]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

	}

	@Test
	public void getPartlyInCircleTest() {
		Surface s;

		s = _surfaces[0].getPartlyIn(_circles[0]);
		assertTrue(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getPartlyIn(_circles[1]);
		assertTrue(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getPartlyIn(_circles[2]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));


		s = _surfaces[0].getPartlyIn(_circles[3]);
		assertTrue(s.contains(_rectangles[0]));
		assertFalse(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertTrue(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertFalse(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

	}

	@Test
	public void getPartlyInSegmentTest() {
		Surface s;

		s = _surfaces[0].getPartlyIn(_segments[0]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertTrue(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));

		s = _surfaces[0].getPartlyIn(_segments[1]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertTrue(s.contains(_segments[3]));

		s = _surfaces[0].getPartlyIn(_segments[2]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertTrue(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertTrue(s.contains(_rectangles[5]));
		assertTrue(s.contains(_circles[0]));
		assertTrue(s.contains(_circles[1]));
		assertTrue(s.contains(_circles[2]));
		assertTrue(s.contains(_circles[3]));
		assertTrue(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertTrue(s.contains(_segments[2]));
		assertFalse(s.contains(_segments[3]));
		
		s = _surfaces[0].getPartlyIn(_segments[3]);
		assertTrue(s.contains(_rectangles[0]));
		assertTrue(s.contains(_rectangles[1]));
		assertFalse(s.contains(_rectangles[2]));
		assertFalse(s.contains(_rectangles[3]));
		assertFalse(s.contains(_rectangles[4]));
		assertFalse(s.contains(_rectangles[5]));
		assertFalse(s.contains(_circles[0]));
		assertFalse(s.contains(_circles[1]));
		assertFalse(s.contains(_circles[2]));
		assertFalse(s.contains(_circles[3]));
		assertFalse(s.contains(_segments[0]));
		assertTrue(s.contains(_segments[1]));
		assertFalse(s.contains(_segments[2]));
		assertTrue(s.contains(_segments[3]));

	}

	private static final int n = 4;
	private static Rectangle _rectangles[];
	private static Circle _circles[];
	private static Segment _segments[];
//	private static ComplexShape _complexes[];

	private Surface _surfaces[];

};

