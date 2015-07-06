def QuickSort(A, setPivot):
	QuickSortHelper(A, setPivot, 0, len(A)-1)

def QuickSortHelper(A, setPivot, lo, hi):
	if setPivot == 0:
		pivot = lo
	elif setPivot == 1:
		pivot = (hi + lo) / 2
	else:
		pivot = hi
	
	if lo < hi:
		p = partition(A, pivot, lo, hi)
		QuickSortHelper(A, pivot, lo, p - 1)
		QuickSortHelper(A, pivot, p + 1, hi)

def partition(A, p, lo, hi):
	pivotIndex = p
	pivotValue = A[pivotIndex]
	swap(A, pivotIndex, hi)
	storeIndex = lo
	for i in range(lo, hi):
		if A[i] < pivotValue:
			swap(A, i, storeIndex)
			storeIndex += 1
	swap(A, storeIndex, hi)
	return storeIndex

def swap(A, s1, s2):
	temp = A[s1]
	A[s1] = A[s2]
	A[s2] = temp

