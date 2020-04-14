package v.paytm.filter.smoothing;

import java.util.List;

public interface MovingAvgDataList<T extends java.lang.Number> {
	
	
	/**
	 * Adds a new element, recalculates the moving average
	 * @param newElement - element to add 
	 */
	public void add(T newElement);
	
	/**
	 * 
	 * @return moving average of the last N elements added
	 */
	public T movingAverage();
	
	/**
	 * Returns moving average after adding n'th element 
	 * @param n - number of the element (zero - based)
	 * @return moving average after adding n'th element
	 */
	public T movingAverageAt(int n);
	
	/**
	 * returns a reference to internal source data as List<Double>
	 * @return a List<T> interface to the source data 
	 */
	public List<T> getData();
	
	/**
	 * Returns a data element added at index n
	 * @param n - element index
	 * @return - data at the specified index n
	 */
	public T getDataAt(int n);
	
	/**
	 * 
	 * @return size of all data added
	 */
	public int dataSize();
	
	/**
	 * 
	 * @return List<Double> reference to stored moving averages
	 */
	public List<T> getMovingAverageData();
	
	
	/**
	 * Clears all internal data stored, resets moving average
	 */
	public void clear();

}
