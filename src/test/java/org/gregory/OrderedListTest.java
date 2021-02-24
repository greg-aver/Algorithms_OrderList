package org.gregory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class OrderedListTest {
    OrderedList<Integer> listInt = new OrderedList<Integer>(true);
    OrderedList<Double> listDouble = new OrderedList<Double>(true);
    OrderedList<String> listString = new OrderedList<String>(true);

    @AfterEach
    void tearDown() {
        listString.clear(true);
        listDouble.clear(true);
        listString.clear(true);
    }

    @Test
    void compareInt() {
        assertThat(listInt.compare(1, 2), is(-1));
        assertThat(listInt.compare(2, 1), is(1));
        assertThat(listInt.compare(1, 1), is(0));
    }

    @Test
    void compareDouble() {
        assertThat(listDouble.compare(1.1, 2.2), is(-1));
        assertThat(listDouble.compare(2.2, 1.1), is(1));
        assertThat(listDouble.compare(1.1, 1.1), is(0));
    }

    @Test
    void compareString() {
        assertThat(listString.compare("123", "124"), is(-1));
        assertThat(listString.compare("125", "121"), is(1));
        assertThat(listString.compare("345", "345"), is(0));
    }

    @Test
    void compareStringGapBeginEnd() {
        assertThat(listString.compare("   123", "   124"), is(-1));
        assertThat(listString.compare("125   ", " 121          "), is(1));
        assertThat(listString.compare("  3 4 5  ", "  3 4 5  "), is(0));
    }

    @Test
    void compareStringFirst() {
        assertThat(listString.compare("1", "11"), is(-1));
        assertThat(listString.compare("1", "111"), is(-1));
        assertThat(listString.compare("12", "123"), is(-1));
        assertThat(listString.compare("123", "1234"), is(-1));
        assertThat(listString.compare("123456", "12345"), is(1));

        assertThat(listString.compare("  1", "  11"), is(-1));
        assertThat(listString.compare(" 1  ", " 111     "), is(-1));
        assertThat(listString.compare("         12 ", " 123  "), is(-1));
        assertThat(listString.compare("  123  ", "  1234          "), is(-1));
        assertThat(listString.compare("       123456", "12345  "), is(1));
    }

    @Test
    void addAscendingNotRepeatNumber() {
        listInt.add(1);
        listInt.add(7);
        listInt.add(8);
        listInt.add(3);
        listInt.add(5);

        OrderedList<Integer> listExpected = new OrderedList<Integer>(true);
        listExpected.add(1);
        listExpected.add(3);
        listExpected.add(5);
        listExpected.add(7);
        listExpected.add(8);

        assertThat(listInt, is(listExpected));

        ArrayList<Integer> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(1);
        arrayListExpected.add(3);
        arrayListExpected.add(5);
        arrayListExpected.add(7);
        arrayListExpected.add(8);

        assertThat(listInt.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void addAscendingRepeatNumber() {
        listInt.add(1);
        listInt.add(7);
        listInt.add(1);
        listInt.add(3);
        listInt.add(1);

        OrderedList<Integer> listExpected = new OrderedList<Integer>(true);
        listExpected.add(1);
        listExpected.add(3);
        listExpected.add(1);
        listExpected.add(1);
        listExpected.add(7);

        assertThat(listInt, is(listExpected));

        ArrayList<Integer> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(1);
        arrayListExpected.add(1);
        arrayListExpected.add(1);
        arrayListExpected.add(3);
        arrayListExpected.add(7);

        assertThat(listInt.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void addAscendingString() {
        listString.add("111");
        listString.add("123");
        listString.add("111");
        listString.add("1234");
        listString.add("111");

        OrderedList<String> listExpected = new OrderedList<String>(true);
        listExpected.add("1234");
        listExpected.add("111");
        listExpected.add("123");
        listExpected.add("111");
        listExpected.add("111");

        assertThat(listString, is(listExpected));

        ArrayList<String> arrayListExpected = new ArrayList<>();
        arrayListExpected.add("111");
        arrayListExpected.add("111");
        arrayListExpected.add("111");
        arrayListExpected.add("123");
        arrayListExpected.add("1234");

        assertThat(listString.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void addDouble() {
        listDouble.add(5.5);
        listDouble.add(2.2);
        listDouble.add(4.4);
        listDouble.add(3.3);
        listDouble.add(1.1);

        OrderedList<Double> listExpected = new OrderedList<Double>(true);
        listExpected.add(3.3);
        listExpected.add(5.5);
        listExpected.add(1.1);
        listExpected.add(4.4);
        listExpected.add(2.2);

        assertThat(listDouble, is(listExpected));
        assertThat(listDouble.count(), is(5));
        assertThat(listExpected.count(), is(5));

        ArrayList<Double> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(1.1);
        arrayListExpected.add(2.2);
        arrayListExpected.add(3.3);
        arrayListExpected.add(4.4);
        arrayListExpected.add(5.5);

        assertThat(listDouble.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteMiddle() {
        listDouble.add(5.5);
        listDouble.add(2.2);
        listDouble.add(4.4);
        listDouble.add(3.3);
        listDouble.add(1.1);

        listDouble.delete(3.3);

        OrderedList<Double> listExpected = new OrderedList<Double>(true);
        listExpected.add(5.5);
        listExpected.add(1.1);
        listExpected.add(4.4);
        listExpected.add(2.2);

        assertThat(listDouble, is(listExpected));
        assertThat(listDouble.count(), is(4));
        assertThat(listExpected.count(), is(4));

        ArrayList<Double> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(1.1);
        arrayListExpected.add(2.2);
        arrayListExpected.add(4.4);
        arrayListExpected.add(5.5);

        assertThat(listDouble.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteFront() {
        listDouble.add(5.5);
        listDouble.add(2.2);
        listDouble.add(4.4);
        listDouble.add(3.3);
        listDouble.add(1.1);

        listDouble.delete(1.1);

        OrderedList<Double> listExpected = new OrderedList<Double>(true);
        listExpected.add(3.3);
        listExpected.add(5.5);
        listExpected.add(4.4);
        listExpected.add(2.2);

        assertThat(listDouble, is(listExpected));
        assertThat(listDouble.count(), is(4));
        assertThat(listExpected.count(), is(4));

        ArrayList<Double> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(2.2);
        arrayListExpected.add(3.3);
        arrayListExpected.add(4.4);
        arrayListExpected.add(5.5);

        assertThat(listDouble.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteEnd() {
        listDouble.add(5.5);
        listDouble.add(2.2);
        listDouble.add(4.4);
        listDouble.add(3.3);
        listDouble.add(1.1);

        listDouble.delete(5.5);

        OrderedList<Double> listExpected = new OrderedList<Double>(true);
        listExpected.add(3.3);
        listExpected.add(1.1);
        listExpected.add(4.4);
        listExpected.add(2.2);

        assertThat(listDouble, is(listExpected));
        assertThat(listDouble.count(), is(4));
        assertThat(listExpected.count(), is(4));

        ArrayList<Double> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(1.1);
        arrayListExpected.add(2.2);
        arrayListExpected.add(3.3);
        arrayListExpected.add(4.4);

        assertThat(listDouble.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteString() {
        listString.add("123");
        listString.add("123");
        listString.add("1234");
        listString.add("123");
        listString.add("123");

        listString.delete("123");

        OrderedList<String> listExpected = new OrderedList<String>(true);
        listExpected.add("123");
        listExpected.add("1234");
        listExpected.add("123");
        listExpected.add("123");

        assertThat(listString, is(listExpected));
        assertThat(listString.count(), is(4));
        assertThat(listExpected.count(), is(4));

        ArrayList<String> arrayListExpected = new ArrayList<>();
        arrayListExpected.add("123");
        arrayListExpected.add("123");
        arrayListExpected.add("123");
        arrayListExpected.add("1234");

        assertThat(listString.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteStringNotFound() {
        listString.add("123");
        listString.add("123");
        listString.add("1234");
        listString.add("123");
        listString.add("123");

        listString.delete("12341");

        OrderedList<String> listExpected = new OrderedList<String>(true);
        listExpected.add("123");
        listExpected.add("1234");
        listExpected.add("123");
        listExpected.add("123");
        listExpected.add("123");

        assertThat(listString, is(listExpected));
        assertThat(listString.count(), is(5));
        assertThat(listExpected.count(), is(5));

        ArrayList<String> arrayListExpected = new ArrayList<>();
        arrayListExpected.add("123");
        arrayListExpected.add("123");
        arrayListExpected.add("123");
        arrayListExpected.add("123");
        arrayListExpected.add("1234");

        assertThat(listString.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void count() {
        assertThat(listString.count(), is(0));

        listString.add("111");
        assertThat(listString.count(), is(1));

        listString.add("123");
        assertThat(listString.count(), is(2));

        listString.add("111");
        assertThat(listString.count(), is(3));

        listString.add("1234");
        assertThat(listString.count(), is(4));

        listString.add("111");
        assertThat(listString.count(), is(5));
    }

    //DESCENDING

    @Test
    void addDescendingNotRepeatNumber() {
        listInt.clear(false);
        listInt.add(1);
        listInt.add(7);
        listInt.add(8);
        listInt.add(3);
        listInt.add(5);

        OrderedList<Integer> listExpected = new OrderedList<Integer>(false);
        listExpected.add(1);
        listExpected.add(8);
        listExpected.add(5);
        listExpected.add(3);
        listExpected.add(7);

        assertThat(listInt, is(listExpected));

        ArrayList<Integer> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(8);
        arrayListExpected.add(7);
        arrayListExpected.add(5);
        arrayListExpected.add(3);
        arrayListExpected.add(1);

        assertThat(listInt.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void addDescendingRepeatNumber() {
        listInt.clear(false);

        listInt.add(1);
        listInt.add(7);
        listInt.add(1);
        listInt.add(3);
        listInt.add(1);

        OrderedList<Integer> listExpected = new OrderedList<Integer>(false);
        listExpected.add(3);
        listExpected.add(1);
        listExpected.add(1);
        listExpected.add(7);
        listExpected.add(1);

        assertThat(listInt, is(listExpected));

        ArrayList<Integer> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(7);
        arrayListExpected.add(3);
        arrayListExpected.add(1);
        arrayListExpected.add(1);
        arrayListExpected.add(1);

        assertThat(listInt.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void addDescendingString() {
        listString.clear(false);

        listString.add("111");
        listString.add("123");
        listString.add("111");
        listString.add("1234");
        listString.add("111");

        OrderedList<String> listExpected = new OrderedList<String>(false);
        listExpected.add("123");
        listExpected.add("111");
        listExpected.add("1234");
        listExpected.add("111");
        listExpected.add("111");

        assertThat(listString, is(listExpected));

        ArrayList<String> arrayListExpected = new ArrayList<>();
        arrayListExpected.add("1234");
        arrayListExpected.add("123");
        arrayListExpected.add("111");
        arrayListExpected.add("111");
        arrayListExpected.add("111");

        assertThat(listString.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void addDescendingDouble() {
       listDouble.clear(false);

        listDouble.add(5.5);
        listDouble.add(2.2);
        listDouble.add(4.4);
        listDouble.add(3.3);
        listDouble.add(1.1);

        OrderedList<Double> listExpected = new OrderedList<Double>(false);
        listExpected.add(3.3);
        listExpected.add(5.5);
        listExpected.add(1.1);
        listExpected.add(4.4);
        listExpected.add(2.2);

        assertThat(listDouble, is(listExpected));
        assertThat(listDouble.count(), is(5));
        assertThat(listExpected.count(), is(5));

        ArrayList<Double> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(5.5);
        arrayListExpected.add(4.4);
        arrayListExpected.add(3.3);
        arrayListExpected.add(2.2);
        arrayListExpected.add(1.1);

        assertThat(listDouble.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteDescendingMiddle() {
        listDouble.clear(false);

        listDouble.add(5.5);
        listDouble.add(2.2);
        listDouble.add(4.4);
        listDouble.add(3.3);
        listDouble.add(1.1);

        listDouble.delete(3.3);

        OrderedList<Double> listExpected = new OrderedList<Double>(false);
        listExpected.add(5.5);
        listExpected.add(1.1);
        listExpected.add(4.4);
        listExpected.add(2.2);

        assertThat(listDouble, is(listExpected));
        assertThat(listDouble.count(), is(4));
        assertThat(listExpected.count(), is(4));

        ArrayList<Double> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(5.5);
        arrayListExpected.add(4.4);
        arrayListExpected.add(2.2);
        arrayListExpected.add(1.1);

        assertThat(listDouble.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteDescendingFront() {
        listDouble.clear(false);

        listDouble.add(5.5);
        listDouble.add(2.2);
        listDouble.add(4.4);
        listDouble.add(3.3);
        listDouble.add(1.1);
        listDouble.delete(1.1);

        OrderedList<Double> listExpected = new OrderedList<Double>(false);
        listExpected.add(3.3);
        listExpected.add(5.5);
        listExpected.add(4.4);
        listExpected.add(2.2);

        assertThat(listDouble, is(listExpected));
        assertThat(listDouble.count(), is(4));
        assertThat(listExpected.count(), is(4));

        ArrayList<Double> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(5.5);
        arrayListExpected.add(4.4);
        arrayListExpected.add(3.3);
        arrayListExpected.add(2.2);

        assertThat(listDouble.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteDescendingEnd() {
        listDouble.clear(false);

        listDouble.add(5.5);
        listDouble.add(2.2);
        listDouble.add(4.4);
        listDouble.add(3.3);
        listDouble.add(1.1);

        listDouble.delete(5.5);

        OrderedList<Double> listExpected = new OrderedList<Double>(false);
        listExpected.add(3.3);
        listExpected.add(1.1);
        listExpected.add(4.4);
        listExpected.add(2.2);

        assertThat(listDouble, is(listExpected));
        assertThat(listDouble.count(), is(4));
        assertThat(listExpected.count(), is(4));

        ArrayList<Double> arrayListExpected = new ArrayList<>();
        arrayListExpected.add(4.4);
        arrayListExpected.add(3.3);
        arrayListExpected.add(2.2);
        arrayListExpected.add(1.1);

        assertThat(listDouble.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteDescendingString() {
        listString.clear(false);

        listString.add("123");
        listString.add("123");
        listString.add("1234");
        listString.add("123");
        listString.add("123");

        listString.delete("123");

        OrderedList<String> listExpected = new OrderedList<String>(false);
        listExpected.add("123");
        listExpected.add("1234");
        listExpected.add("123");
        listExpected.add("123");

        assertThat(listString, is(listExpected));
        assertThat(listString.count(), is(4));
        assertThat(listExpected.count(), is(4));

        ArrayList<String> arrayListExpected = new ArrayList<>();
        arrayListExpected.add("1234");
        arrayListExpected.add("123");
        arrayListExpected.add("123");
        arrayListExpected.add("123");

        assertThat(listString.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteDescendingStringNotFound() {
        listString.clear(false);

        listString.add("123");
        listString.add("123");
        listString.add("1234");
        listString.add("123");
        listString.add("123");

        listString.delete("12341");

        OrderedList<String> listExpected = new OrderedList<String>(false);
        listExpected.add("123");
        listExpected.add("1234");
        listExpected.add("123");
        listExpected.add("123");
        listExpected.add("123");

        assertThat(listString, is(listExpected));
        assertThat(listString.count(), is(5));
        assertThat(listExpected.count(), is(5));

        ArrayList<String> arrayListExpected = new ArrayList<>();
        arrayListExpected.add("1234");
        arrayListExpected.add("123");
        arrayListExpected.add("123");
        arrayListExpected.add("123");
        arrayListExpected.add("123");

        assertThat(listString.getAllTElement(), is(arrayListExpected));
        assertThat(listExpected.getAllTElement(), is(arrayListExpected));
    }

    @Test
    void deleteNull() {
        listDouble.delete(1.1);
        assertThat(listDouble.count(), is(0));
        listDouble.add(1.1);
        listDouble.delete(1.1);
        assertThat(listDouble.count(), is(0));
    }

    @Test
    void countDescending() {
        listString.clear(false);

        assertThat(listString.count(), is(0));

        listString.add("111");
        assertThat(listString.count(), is(1));

        listString.add("123");
        assertThat(listString.count(), is(2));

        listString.add("111");
        assertThat(listString.count(), is(3));

        listString.add("1234");
        assertThat(listString.count(), is(4));

        listString.add("111");
        assertThat(listString.count(), is(5));
    }
}