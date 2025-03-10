package google_interview;

import java.util.ArrayList;
import java.util.List;

public class AggregatingTest {
    public static void main(String[] args) {    
        testWithMultipleOccurrences();
        testWithSingleOccurrences();
        testWithEmptyList();
        testWithNoRepetitions();
    }

    private static void testWithMultipleOccurrences() {
        List<String> input = new ArrayList<>();
        input.add("foo");
        input.add("foo");
        input.add("foo");
        input.add("bar");
        input.add("foo");
        AggregatingIterator app = new AggregatingIterator(input.iterator());
        while (app.hasNext()) {
            AggregatingIterator.Entry entry = app.next();
            System.out.println(entry.value() + " " + entry.count());
        }
    }

    private static void testWithSingleOccurrences() {
        List<String> input = new ArrayList<>();
        input.add("foo");
        input.add("bar");
        input.add("baz");
        AggregatingIterator app = new AggregatingIterator(input.iterator());
        while (app.hasNext()) {
            AggregatingIterator.Entry entry = app.next();
            System.out.println(entry.value() + " " + entry.count());
        }
    }

    private static void testWithEmptyList() {
        List<String> input = new ArrayList<>();
        AggregatingIterator app = new AggregatingIterator(input.iterator());
        while (app.hasNext()) {
            AggregatingIterator.Entry entry = app.next();
            System.out.println(entry.value() + " " + entry.count());
        }
    }

    private static void testWithNoRepetitions() {
        List<String> input = new ArrayList<>();
        input.add("foo");
        input.add("bar");
        input.add("baz");
        input.add("qux");
        AggregatingIterator app = new AggregatingIterator(input.iterator());
        while (app.hasNext()) {
            AggregatingIterator.Entry entry = app.next();
            System.out.println(entry.value() + " " + entry.count());
        }
    }
}
