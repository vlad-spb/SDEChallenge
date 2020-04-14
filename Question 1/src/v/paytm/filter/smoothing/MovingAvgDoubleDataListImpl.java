package v.paytm.filter.smoothing;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MovingAvgDoubleDataListImpl implements MovingAvgDataList<Double> {
	
	

	private double sum = 0d;
	
	private List<Double> data = null;
	
	// store all moving averages calculated
	private List<Double> movingAverageData = null;
	
	// the "sliding" window used to calculate the moving average
	// we will be removing elements from its head and adding at the end - like a queue
	private Queue<Double> windowData = null; 
	
	private int N = 4; // the sliding window size N = 4 by default
	
	private Double lastMovingAverage = null;
	
	/**
	 * 
	 * @param windowSize - the "sliding" window size
	 */
	public MovingAvgDoubleDataListImpl(int windowSize) {
		
		if(windowSize <= 0) {
			throw new IllegalArgumentException("The sliding window size cannot be negative or zero.");
		}
		this.N = windowSize;
		
		data = new LinkedList<Double>();
		// as mentioned in README, 
		// use the following line if random access 
		// is more important than insertion
		// data = new ArrayList(estimatedCapacity);

		movingAverageData = new LinkedList<Double>();
		
		windowData = new LinkedList<Double>();
		
	}
	

	/**
	 * Clears all internal data stored, resets moving average
	 */
	@Override
	public void clear() {
		
		// if internal data has been initialized - clear
		// and reset internal fields
		if(data != null) {
			data.clear();
			movingAverageData.clear();
			windowData.clear();
			sum = 0d;
			lastMovingAverage = null;
		}

	}
	
	
	/**
	 * Adds a new element, recalculates the moving average
	 * @param dNewElement - element to add 
	 */
	@Override
	public void add(Double dNewElement) {

		// add this element to internal data storage and to the "sliding" window
		data.add(dNewElement);
		windowData.add(dNewElement);
		
		if(data.size() <= N) {

			// the case when the data size is smaller than the window size N
			// still calculate the average
			sum = sum + dNewElement;
			lastMovingAverage = sum / windowData.size();
			
		} else {
			
			
			// calculate the moving average for N elements
			
			// the sliding window only changed by the oldest element and the newest element
			Double dFirstWindowElement = windowData.poll(); // get and remove the oldest element

			// new sum will be the previous one after removing the oldest and adding the newest element
			sum = sum - dFirstWindowElement + dNewElement;  
			lastMovingAverage = sum / N;
			
		}
		// store the calculated moving average
		movingAverageData.add(lastMovingAverage);
	}
	

	/**
	 * 
	 * @return moving average of the last N elements added, NULL in case of no data
	 */
	@Override
	public Double movingAverage() {
		
		Double result = null;
		if(movingAverageData.size() > 0) { 
			result = lastMovingAverage;
		}
		return result;
	}
	
	
	/**
	 * Returns moving average after adding n'th element 
	 * @param n - number of the element (zero - based)
	 * @return moving average after adding n'th element, NULL in case of invalid parameter
	 */
	@Override
	public Double movingAverageAt(int idx) {
		
		Double result = null;
		
		if(idx >= 0 && idx <= movingAverageData.size() - 1) {
		
			result = movingAverageData.get(idx);
		}
		return result;

	}
	
	
	/**
	 * returns a reference to internal source data as List<Double>
	 * @return a List<Double> interface to the source data 
	 */
	@Override	
	public List<Double> getData() {
		
		return data;
	
	}
	
	/**
	 * Returns a data element added at index n
	 * @param n - element index
	 * @return - data at the specified index n
	 */
	@Override	
	public Double getDataAt(int idx) {
		
		Double result =  null;
		if(idx >= 0 && idx <= data.size() - 1) {
			result = data.get(idx); 
		}
		return result;
	}
	
	/**
	 * 
	 * @return size of all data added
	 */
	@Override
	public int dataSize() {
		return data.size();
	}

	/**
	 * 
	 * @return List<Double> reference to stored moving averages
	 */
	@Override	
	public List<Double> getMovingAverageData() {
		
		return movingAverageData;
	}


}
