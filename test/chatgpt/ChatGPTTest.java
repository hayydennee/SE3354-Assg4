package chatgpt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import evosuite.Stack;

public class ChatGPTTest {

    private Stack stack;

    @BeforeEach
    void setUp() {
        stack = new Stack(3);
    }

    // --- Basic Push/Pop Functionality ---

    @Test
    void testPushAndPopSingleElement() {
        stack.push(10);
        assertEquals(10, stack.pop(), "Popped value should match pushed value");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping the only element");
    }

    @Test
    void testPushAndPopMultipleElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop(), "Stack should follow LIFO order");
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    // --- Peek Behavior ---

    @Test
    void testPeekDoesNotRemoveElement() {
        stack.push(42);
        int top = stack.peek();
        assertEquals(42, top);
        assertFalse(stack.isEmpty(), "Peek should not modify stack");
        assertEquals(42, stack.peek(), "Peek should return the same element repeatedly");
    }

    @Test
    void testPeekOnEmptyStackThrowsException() {
        assertThrows(IllegalStateException.class, () -> stack.peek(), "Peek should throw when stack is empty");
    }

    // --- Boundary Conditions ---

    @Test
    void testPushBeyondCapacityThrowsException() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertTrue(stack.isFull());
        assertThrows(IllegalStateException.class, () -> stack.push(4), "Pushing past capacity should throw exception");
    }

    @Test
    void testPopOnEmptyStackThrowsException() {
        assertThrows(IllegalStateException.class, () -> stack.pop(), "Popping from empty stack should throw exception");
    }

    // --- isEmpty and isFull Checks ---

    @Test
    void testIsEmptyInitially() {
        assertTrue(stack.isEmpty());
        assertFalse(stack.isFull());
    }

    @Test
    void testIsFullAfterPushingToCapacity() {
        stack.push(5);
        stack.push(10);
        stack.push(15);
        assertTrue(stack.isFull());
        assertFalse(stack.isEmpty());
    }

    // --- Size Method Tests ---

    @Test
    void testSizeIncreasesAndDecreasesCorrectly() {
        assertEquals(0, stack.size());
        stack.push(7);
        assertEquals(1, stack.size());
        stack.push(8);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }

    // --- Mixed Operations ---

    @Test
    void testInterleavedPushPopOperations() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(3);
        assertEquals(3, stack.peek());
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }
}
