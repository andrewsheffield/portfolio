
/** QuickSort Class requires an array on init.  This array will not be sorted until sortArray is invoked
*   When calling sortArray the coder will have to declare a range of indexes to be sorted
**/
class QuickSort (A: Array[Int]) {

	val array = A;

	def sortArray(i : Int, k : Int) : Array[Int] = {
  		if (i < k) {
    		var p = partition(i,k);
    		sortArray(i, p-1);
    		sortArray(p+1, k);
    	}
    	array;
	}

	def partition(left: Int, right: Int) : Int = {
    	var pivotIndex = choosePivot(left, right);
    	var pivotValue = array(pivotIndex);
    	swap(pivotIndex, right);
    	var storeIndex = left;
    	var i = 0;
    	for (i <- left until right) {
        	if (array(i) <= pivotValue) {
        		swap(i, storeIndex);
            	storeIndex = storeIndex + 1;
            }
        }
        swap(storeIndex, right);
    	storeIndex;
	}

	def swap(i : Int, j : Int) = {
		var temp = array(i);
		array(i) = array(j);
		array(j) = temp;
	}

    //This is the only method to be edited.  It determines what is used as the pivot.
    //The median has been shown to be the most efficient in most cases.
    def choosePivot(left: Int, right: Int) : Int = {
        var middle : Int = (left + right) / 2;
        middle;
    }
}

//Create an Unsorted array of Int
var array = Array(7,8,6,0,1,2,5,3,4,9);

println("Unsorted:\t" + array.mkString(", "));
var sort = new QuickSort(array);
var sortedArray = sort.sortArray(0, array.length-1);
println("Sorted:\t\t" + sortedArray.mkString(", "));