package google_interview;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*

Original iterator: { "foo", "foo", "foo", "bar" }
Aggregating iterator: { {"foo", 3}, {"bar", 1} }

Second example:

Original iterator: { "foo", "foo", "foo", "bar", "foo" }
Aggregating iterator: { {"foo", 3}, {"bar", 1}, {"foo", 1} }


*/
public final class AggregatingIterator implements Iterator<AggregatingIterator.Entry> {
  
    private final Iterator<String> iterator;
    private String nextValue = null;
    
    public AggregatingIterator(Iterator<String> input) {
      iterator = input;
      nextValue = iterator.hasNext() ? iterator.next() : null;
    }
  
    public Entry next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      String currentValue = nextValue;
      int count = 1;

      while (iterator.hasNext()) {
        String peekedValue = iterator.next();
        if (currentValue.equals(peekedValue)) {
          count++;
        } else {
          nextValue = peekedValue;
          return new Entry(currentValue, count);
        }
      }
             
      nextValue = null;
      return new Entry(currentValue, count);
    }
  
    public boolean hasNext() {
      return nextValue != null;
    }
  
    /** A String, count pair. */
    public static class Entry {
      public String value() {
        return value;
      }
  
      public int count() {
        return count;
      }
  
      public Entry(String value, int count) {
        this.value = value;
        this.count = count;
      }
  
      public boolean equals(Object other) {
        if (!(other instanceof Entry)) return false;
        Entry entry = (Entry) other;
        return this.value.equals(entry.value) && this.count == entry.count;
      }
  
      public String toString() {
        return "(" + this.value + ", " + this.count + ")";
      }
  
      private final String value;
      private final int count;
    }
  }