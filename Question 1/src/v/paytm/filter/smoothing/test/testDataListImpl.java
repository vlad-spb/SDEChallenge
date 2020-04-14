package v.paytm.filter.smoothing.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import v.paytm.filter.smoothing.MovingAvgDataList;
import v.paytm.filter.smoothing.MovingAvgDoubleDataListImpl;

class testDataListImpl {

	@Test
	void testMovingAvgDoubleDataList() {
		
		double[] data = {2d, 4d, 3.5d, 4d, 1d, 3.5d, 8d, 0d, 5d, 6d, 11d};

		final int N = 4;
		MovingAvgDataList<Double> dataList = new MovingAvgDoubleDataListImpl(N);

		// no data yet - should return null
		assertNull(dataList.movingAverage());
		
		// add one element first
		dataList.add(data[0]);
		assertEquals(2d, dataList.movingAverage());
		assertEquals(2d, dataList.movingAverageAt(0));

		// testing the case when the data set size is the same as N - the window size
		for(int k = 1; k <= 3; k++ ) {
			dataList.add(data[k]);
		}
		assertEquals(4, dataList.dataSize());
		assertEquals(3.375d, dataList.movingAverage());
		assertEquals(3d, dataList.movingAverageAt(1));
		assertEquals(3.5d, dataList.getDataAt(2));
		
		// adding remaining elements
		for(int k = 4; k <= data.length - 1; k++ ) {
			dataList.add(data[k]);
		}
		
		assertEquals(5.5d, dataList.movingAverage());
		assertEquals(3.375d, dataList.movingAverageAt(3));
		assertEquals(3.125d, dataList.movingAverageAt(4));
		assertEquals(3.125d, dataList.movingAverageAt(7));
		assertEquals(11, dataList.getData().size());
		assertEquals(11, dataList.getMovingAverageData().size());
		
		// clear all data
		dataList.clear();
		assertNull(dataList.movingAverage());
		assertEquals(0, dataList.dataSize());
		assertEquals(0, dataList.getData().size());
		
		
	}

}
