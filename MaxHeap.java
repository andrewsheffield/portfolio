import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap
{
   public ArrayList<Student> students;
   
   public MaxHeap(int capacity)
   {
      students = new ArrayList<>(capacity);
   }
      
   public MaxHeap(Collection<Student> collection)
   {
      students = new ArrayList<>(collection);
      for(int i = size()-1; i >= 0; i--)
      {
          students.get(i).setHeapIndex(i);
         maxHeapify(i);
      }
   }
   
   public Student getMax()
   {
      if(size() < 1)
      {
         throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
      }
      return students.get(0);
   }
   
   public Student extractMax()
   {
      Student value = getMax();
      students.set(0, students.get(size() - 1));
      students.remove(size()-1);
      maxHeapify(0);
      return value;
   }
   
   
   public void insert(Student elt)
   {
       students.add(elt);
       int i = students.size()-1;
       elt.setHeapIndex(i);
       while (students.get(i).compareTo(students.get(parent(i))) > 0)
       {
           swap(i, parent(i));
           i = parent(i);
       }

   }
   
   public void changeKey(Student s, double newGPA)
   {
        s.setGPA(newGPA);
        int i = s.heapIndex();
        while (students.get(i).compareTo(students.get(parent(i))) > 0)
       {
           swap(i, parent(i));
           i = parent(i);
       }
       maxHeapify(i);
   }

   public int parent(int index)
   {
      return (index - 1)/2;
   }
   
   public int left(int index)
   {
      return 2 * index + 1;
   }
   
   public int right(int index)
   {
      return 2 * index + 2;
   }
   
   public int size()
   {
      return students.size();
   }
   
   private void swap(int from, int to)
   {
      Student val = students.get(from);
      students.set(from,  students.get(to));
      students.set(to,  val);
      students.get(to).setHeapIndex(to);
      students.get(from).setHeapIndex(from);
   }
   
   private void maxHeapify(int index)
   {
      //students.get(index).setHeapIndex(index);
      int left = left(index);
      int right = right(index);
      int largest = index;
      if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0)
      {
         largest = left;
      }
      if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0)
      {
         largest = right;
      }
      if (largest != index)
      {
         swap(index, largest);
         maxHeapify(largest);
      }  
   } 
  
}
