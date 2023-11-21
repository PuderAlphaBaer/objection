package pgdp.b1d;

import de.tum.in.test.api.jupiter.PublicTest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import de.tum.in.test.api.jupiter.Public;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestClassAnnotation
class BehaviorTest {

    static AtomicInteger passedIsOnOrInsideCircle = new AtomicInteger(0);
    static Stream<Arguments> isIOnOrInsideCircleArguments() {
        return Stream.of(
                Arguments.of(1, 0, 0, true),
                Arguments.of(5, 5, 0, true),
                Arguments.of(5, -3, 4, true),
                Arguments.of(12, 3, -4, true),
                Arguments.of(5, 0, 6, false),
                Arguments.of(20, 16, 13, false)
        );
    }


    @Public
    @DisplayName(value="Test Individual - isOnOrInsideCircle")
    @ParameterizedTest(name="[{index}] Testing isOnOrInsideCircle ({0}, {1}, {2})")
    @MethodSource("isIOnOrInsideCircleArguments")
    @Order(1)
    void testIsOnOrInsideCircleCase(int r, int x, int y, boolean expected) {
        boolean actual = Kreise.isOnOrInsideCircle(r, x, y);
        if (expected) {
            assertTrue(actual, "Expected (" + x + ", " + y + ") to be inside a circle with radius " + r + " but was not.");
        } else {
            assertFalse(actual, "Expected (" + x + ", " + y + ") to be outside a circle with radius " + r + " but was not.");
        }
        passedIsOnOrInsideCircle.incrementAndGet();
    }

    @PublicTest
    @DisplayName(value="Test Summary - isOnOrInsideCircle")
    @Order(2)
    void testIsOnOrInsideCircleSummary() {
        if (passedIsOnOrInsideCircle.get() != isIOnOrInsideCircleArguments().count()) {
            fail("At least one of the required tests to get points failed.");
        }
    }

    private boolean isOnOrInsideCirclePartiallyImplemented() {
        boolean returnedTrue = false;
        boolean returnedFalse = false;
        for(int i = -5; i < 6; i++) {
            for(int j = -5; j < 6; j++) {
                if (Kreise.isOnOrInsideCircle(3, i, j)){
                    returnedTrue = true;
                } else {
                    returnedFalse = true;
                }
            }
        }

        return returnedTrue && returnedFalse;
    }

    static AtomicInteger passedIsInRedArea = new AtomicInteger(0);
    static Stream<Arguments> isInRedAreaArguments() {
        return Stream.of(
                Arguments.of(0, 0, true),
                Arguments.of(0, 101, true),
                Arguments.of(-150, 30, true),
                Arguments.of(100, -100, true),
                Arguments.of(0, 100, true),
                Arguments.of(170, -170, false),
                Arguments.of(300, 0, false),
                Arguments.of(50, 50, false),
                Arguments.of(-50, -50, false)
        );
    }

    static AtomicInteger passedIsInRedAreaWithoutSquareSide = new AtomicInteger(0);

    static Stream<Arguments> isInRedAreaWithoutSquareSideArguments() {
        return Stream.of(
                Arguments.of(0, 101, true),
                Arguments.of(-150, 30, true),
                Arguments.of(170, -170, false),
                Arguments.of(300, 0, false),
                Arguments.of(50, 50, false),
                Arguments.of(-50, -50, false)
        );
    }

    @Public
    @DisplayName(value="Test Individual - isInRedArea")
    @ParameterizedTest(name="[{index}] Testing isInRedArea ({0}, {1})")
    @MethodSource("isInRedAreaArguments")
    @Order(3)
    void testIsInRedAreaCase(int x, int y, boolean expected) {
        boolean actual = Kreise.isInRedArea(x, y);

        if (actual != expected && isOnOrInsideCirclePartiallyImplemented()) {
            boolean isInSquare1 = 0 < x && x < 100 && 0 < y && y < 100;
            boolean isInSquare2 = -100 < x && x < 0 && -100 < y && y < 0;

            expected = Kreise.isOnOrInsideCircle(200, x, y) && !isInSquare1 && !isInSquare2;
        }

        if (expected) {
            assertTrue(actual, "Expected (" + x + ", " + y + ") to be inside the red area.");
        } else {
            assertFalse(actual, "Expected (" + x + ", " + y + ") to be outside the red area.");
        }
        passedIsInRedArea.incrementAndGet();
    }

    @PublicTest
    @DisplayName(value="Test Summary - isInRedArea")
    @Order(4)
    void testIsInRedAreaSummary() {
        if (passedIsInRedArea.get() != isInRedAreaArguments().count()) {
            fail("At least one of the required tests to get points failed.");
        }
    }

    @Public
    @DisplayName(value="Test Individual - isInRedAreaWithoutSquareSide")
    @ParameterizedTest(name="[{index}] Testing isInRedAreaWithoutSquareSide ({0}, {1})")
    @MethodSource("isInRedAreaWithoutSquareSideArguments")
    @Order(5)
    void testIsInRedAreaWithoutSquareSideCase(int x, int y, boolean expected) {
        boolean actual = Kreise.isInRedArea(x, y);

        if (actual != expected && isOnOrInsideCirclePartiallyImplemented()) {
            boolean isInSquare1 = 0 < x && x < 100 && 0 < y && y < 100;
            boolean isInSquare2 = -100 < x && x < 0 && -100 < y && y < 0;

            expected = Kreise.isOnOrInsideCircle(200, x, y) && !isInSquare1 && !isInSquare2;
        }

        if (expected) {
            assertTrue(actual, "Expected (" + x + ", " + y + ") to be inside the red area.");
        } else {
            assertFalse(actual, "Expected (" + x + ", " + y + ") to be outside the red area.");
        }
        passedIsInRedAreaWithoutSquareSide.incrementAndGet();
    }

    @PublicTest
    @DisplayName(value="Test Summary - isInRedAreaWithoutSquareSide")
    @Order(6)
    void testIsInRedAreaWithoutSquareSideSummary() {
        if (passedIsInRedAreaWithoutSquareSide.get() != isInRedAreaWithoutSquareSideArguments().count()) {
            fail("At least one of the required tests to get points failed.");
        }
    }

    static AtomicInteger passedCountPointsOnOrInsideCircle = new AtomicInteger(0);
    static Stream<Arguments> countPointsOnOrInsideCircleArguments() {
        return Stream.of(
                Arguments.of(1, 5),
                Arguments.of(2, 13),
                Arguments.of(5, 81),
                Arguments.of(50, 7845)
        );
    }

    @Public
    @DisplayName(value="Test Individual - countPointsOnOrInsideCircle")
    @ParameterizedTest(name="[{index}] Testing countPointsOnOrInsideCircle ({0})")
    @MethodSource("countPointsOnOrInsideCircleArguments")
    @Order(7)
    void testCountPointsOnOrInsideCircleCase(int n, int expected) {
        int actual = Kreise.countPointsOnOrInsideCircle(n);

        if (actual != expected && isOnOrInsideCirclePartiallyImplemented()) {
            int count = 0;
            for (int x=-n; x<=n; x++) {
                for (int y=-n; y <=n; y++) {
                    if (Kreise.isOnOrInsideCircle(n, x, y)) {
                        count++;
                    }
                }
            }
            expected = count;
        }

        assertEquals(expected, actual, "The points on or inside the circle are not correct.");
        passedCountPointsOnOrInsideCircle.incrementAndGet();
    }

    @PublicTest
    @DisplayName(value="Test Summary - countPointsOnOrInsideCircle")
    @Order(8)
    void testCountPointsOnOrInsideCircleSummary() {
        if (passedCountPointsOnOrInsideCircle.get() != countPointsOnOrInsideCircleArguments().count()) {
            fail("At least one of the required tests to get points failed.");
        }
    }

    private boolean countPointsOnOrInsideCirclePartiallyImplemented() {
        for(int i = 1; i < 10; i++) {
            if (Kreise.countPointsOnOrInsideCircle(i) != 0){
                return true;
            }
        }

        return false;
    }

    static AtomicInteger passedApproximatePi = new AtomicInteger(0);
    static Stream<Arguments> approximatePiArguments() {
        return Stream.of(
                Arguments.of(1, 2.2222222222222223),
                Arguments.of(10, 2.875283446712018),
                Arguments.of(50, 3.0761690030389177),
                Arguments.of(100, 3.110517066409247)
        );
    }

    @Public
    @DisplayName(value="Test Individual - approximatePi")
    @ParameterizedTest(name="[{index}] Testing approximatePi ({0})")
    @MethodSource("approximatePiArguments")
    @Order(9)
    void testApproximatePiCase(int n, double expected) {
        double actual = Kreise.approximatePi(n);
        double allowedDelta = 0.0001;

        if (Math.abs(actual - expected) > allowedDelta && countPointsOnOrInsideCirclePartiallyImplemented()) {
            int nrRedPoints = Kreise.countPointsOnOrInsideCircle(n);
            int nrAllPoints = (2*n+1) * (2*n+1);

            expected = (double) nrRedPoints / (double) nrAllPoints * 4.0;
        }

        assertEquals(expected, actual, allowedDelta, "The aproximation for n=" + n + " is off.");
        passedApproximatePi.incrementAndGet();
    }

    @PublicTest
    @DisplayName(value="Test Summary - approximatePi")
    @Order(10)
    void testApproximatePiSummary() {
        if (passedApproximatePi.get() != approximatePiArguments().count()) {
            fail("At least one of the required tests to get points failed.");
        }
    }
}
