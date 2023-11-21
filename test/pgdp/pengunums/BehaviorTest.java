package pgdp.pengunums;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import de.tum.in.test.api.jupiter.Public;
import de.tum.in.test.api.jupiter.PublicTest;

@TestClassAnnotation
public class BehaviorTest {

	static AtomicInteger passedPenguNumPositiveBase = new AtomicInteger();

	static Stream<Arguments> penguNumPositiveBaseArguments() {
		return Stream.of(Arguments.of(0, 0), Arguments.of(1, 1), Arguments.of(2, 1));
	}

	@Public
	@DisplayName(value = "Test Indivdual - penguNumPositive - Base")
	@ParameterizedTest(name = "[{index}] Testing penguNumPositive({0})")
	@MethodSource("penguNumPositiveBaseArguments")
	@Order(1)
	void testPenguNumPositiveBase(int n, long expected) {
		assertEquals(expected, PenguNums.penguNumPositive(n));
		passedPenguNumPositiveBase.incrementAndGet();
	}

	@PublicTest
	@DisplayName(value = "Test Summary - penguNumPositive - Base")
	@Order(2)
	void testPenguNumPositiveBaseSummary() {
		if (penguNumPositiveBaseArguments().count() != passedPenguNumPositiveBase.get()) {
			fail("At least one of the required tests failed.");
		}
	}

	static AtomicInteger passedPenguNumPositive = new AtomicInteger();

	static Stream<Arguments> penguNumPositiveArguments() {
		return Stream.of(Arguments.of(17, 10609), Arguments.of(69, 612979045863284359L),
				Arguments.of(73, 7015254043203144209L));
	}

	@Public
	@DisplayName(value = "Test Indivdual - penguNumPositive")
	@ParameterizedTest(name = "[{index}] Testing penguNumPositive({0})")
	@MethodSource("penguNumPositiveArguments")
	@Order(3)
	void testPenguNumPositive(int n, long expected) {
		assertEquals(expected, PenguNums.penguNumPositive(n));
		passedPenguNumPositive.incrementAndGet();
	}

	@PublicTest
	@DisplayName(value = "Test Summary - penguNumPositive")
	@Order(4)
	void testPenguNumPositiveSummary() {
		if (penguNumPositiveArguments().count() != passedPenguNumPositive.get()) {
			fail("At least one of the required tests failed.");
		}
	}

	static AtomicInteger passedPenguNumNegativeBase = new AtomicInteger();

	static Stream<Arguments> penguNumNegativeBaseArguments() {
		return Stream.of(Arguments.of(-1, 1), Arguments.of(-2, -1), Arguments.of(-3, 2), Arguments.of(-16, -5768),
				Arguments.of(-17, 10609), Arguments.of(-69, 612979045863284359L),
				Arguments.of(-70, -1127444240280152749L), Arguments.of(-73, 7015254043203144209L));
	}

	@Public
	@DisplayName(value = "Test Indivdual - penguNumNegative - Base")
	@ParameterizedTest(name = "[{index}] Testing penguNumNegative({0})")
	@MethodSource("penguNumNegativeBaseArguments")
	@Order(5)
	void testPenguNumNegativeBase(int n, long expected) {
		assertThat(PenguNums.penguNumNegative(n), either(is(expected)).or(is(-expected)));
		passedPenguNumNegativeBase.incrementAndGet();
	}

	@PublicTest
	@DisplayName(value = "Test Summary - penguNumNegative - Base")
	@Order(6)
	void testPenguNumNegativeBaseSummary() {
		if (penguNumNegativeBaseArguments().count() != passedPenguNumNegativeBase.get()) {
			fail("At least one of the required tests failed.");
		}
	}

	static AtomicInteger passedPenguNumNegative = new AtomicInteger();

	static Stream<Arguments> penguNumNegativeArguments() {
		return Stream.of(Arguments.of(-1, 1), Arguments.of(-2, -1), Arguments.of(-3, 2), Arguments.of(-16, -5768),
				Arguments.of(-17, 10609), Arguments.of(-69, 612979045863284359L),
				Arguments.of(-70, -1127444240280152749L), Arguments.of(-73, 7015254043203144209L));
	}

	@Public
	@DisplayName(value = "Test Indivdual - penguNumNegative")
	@ParameterizedTest(name = "[{index}] Testing penguNumNegative({0}) - Base")
	@MethodSource("penguNumNegativeArguments")
	@Order(7)
	void testPenguNumNegative(int n, long expected) {
		assertEquals(expected, PenguNums.penguNumNegative(n));
		passedPenguNumNegative.incrementAndGet();
	}

	@PublicTest
	@DisplayName(value = "Test Summary - penguNumNegative")
	@Order(8)
	void testPenguNumNegativeSummary() {
		if (penguNumNegativeArguments().count() != passedPenguNumNegative.get()) {
			fail("At least one of the required tests failed.");
		}
	}

	static AtomicInteger passedPenguNum = new AtomicInteger();

	static Stream<Arguments> penguNumArguments() {
		return Stream.of(Arguments.of(1, 1), Arguments.of(3, 2), Arguments.of(5, 7), Arguments.of(8, 44),
				Arguments.of(-1, 1), Arguments.of(-2, -1), Arguments.of(-3, 2), Arguments.of(-8, -44));
	}

	@Public
	@DisplayName(value = "Test Indivdual - penguNum")
	@ParameterizedTest(name = "[{index}] Testing penguNum({0})")
	@MethodSource("penguNumArguments")
	@Order(9)
	void testPenguNum(int n, long expected) {
		long result;
		boolean threwException = false;
		try {
			result = PenguNums.penguNum(n);
		} catch (Throwable ignored) {
			if (n > 0) {
				throw ignored;
			}

			result = expected + 1;
			threwException = true;
		}
		if (expected != result) {
			if (n < 0) {
				if (threwException) {
					assertThrows(Throwable.class, () -> PenguNums.penguNumNegative(n));
				} else {
					assertEquals(PenguNums.penguNumNegative(n), result);
				}
			} else {
				assertEquals(PenguNums.penguNumPositive(n), result);
			}
		}
		passedPenguNum.incrementAndGet();
	}

	private static boolean penguNumPositiveIsImplemented() {
		long res = PenguNums.penguNumPositive(0);
		for (int i = 1; i <= 74; i++) {
			if (res != PenguNums.penguNumPositive(i)) {
				return true;
			}
		}
		return false;
	}

	private static boolean penguNumNegativeIsImplemented() {
		try {
			long res = PenguNums.penguNumNegative(-1);
			for (int i = -2; -74 <= i; i--) {
				if (res != PenguNums.penguNumNegative(i)) {
					return true;
				}
			}
			return false;
		} catch (Throwable ignored) {
			return true;
		}
	}

	@PublicTest
	@DisplayName(value = "Test Summary - penguNum")
	@Order(10)
	void testPenguNumSummary() {
		if (penguNumArguments().count() != passedPenguNum.get()) {
			fail("At least one of the required tests failed.");
		}
		if (!penguNumPositiveIsImplemented() || !penguNumNegativeIsImplemented()) {
			fail("You did not implement one of the required methods.");
		}
	}

	static AtomicInteger passedPenguNumIndexPositive = new AtomicInteger();

	static Stream<Arguments> penguNumIndexPositiveArguments() {
		return Stream.of(Arguments.of(0, 0), Arguments.of(1, 1), Arguments.of(2, 3), Arguments.of(10609, 17),
				Arguments.of(612979045863284359L, 69), Arguments.of(7015254043203144209L, 73));
	}

	@Public
	@DisplayName(value = "Test Indivdual - penguNumIndex - Positive")
	@ParameterizedTest(name = "[{index}] Testing penguNumIndex({0})")
	@MethodSource("penguNumIndexPositiveArguments")
	@Order(11)
	void testPenguNumIndexPositive(long n, long expected) {
		assertEquals(expected, PenguNums.penguNumIndex(n));
		passedPenguNumIndexPositive.incrementAndGet();
	}

	@PublicTest
	@DisplayName(value = "Test Summary - penguNumIndex - Positive")
	@Order(12)
	void testPenguNumIndexPositiveSummary() {
		if (penguNumIndexPositiveArguments().count() != passedPenguNumIndexPositive.get()) {
			fail("At least one of the required tests failed.");
		}
	}

	static AtomicInteger passedPenguNumIndexNegative = new AtomicInteger();

	static Stream<Arguments> penguNumIndexNegativeArguments() {
		return Stream.of(Arguments.of(-5768, -16), Arguments.of(-1127444240280152749L, -70));
	}

	@Public
	@DisplayName(value = "Test Indivdual - penguNumIndex - Negative")
	@ParameterizedTest(name = "[{index}] Testing penguNumIndex({0})")
	@MethodSource("penguNumIndexNegativeArguments")
	@Order(13)
	void testPenguNumIndexNegative(long n, long expected) {
		assertEquals(expected, PenguNums.penguNumIndex(n));
		passedPenguNumIndexNegative.incrementAndGet();
	}

	@PublicTest
	@DisplayName(value = "Test Summary - penguNumIndex - Negative")
	@Order(14)
	void testPenguNumIndexSummary() {
		if (penguNumIndexNegativeArguments().count() != passedPenguNumIndexNegative.get()) {
			fail("At least one of the required tests failed.");
		}
	}

	static AtomicInteger passedIsPenguNumPositiveBase = new AtomicInteger();

	static Stream<Arguments> isPenguNumPositiveBaseArguments() {
		return Stream.of(Arguments.of(0), Arguments.of(1));
	}

	@Public
	@DisplayName(value = "Test Indivdual - isPenguNumPositive - Base")
	@ParameterizedTest(name = "[{index}] Testing isPenguNumPositive({0})")
	@MethodSource("isPenguNumPositiveBaseArguments")
	@Order(15)
	void testIsPenguNumPositiveBase(long n) {
		assertTrue(PenguNums.isPenguNumPositive(n));
		passedIsPenguNumPositiveBase.incrementAndGet();
	}

	private static boolean isPenguNumPositiveIsImplemented() {
		try {
			boolean res = PenguNums.isPenguNumPositive(0);
			for (int i = 1; i <= 100; i++) {
				if (res != PenguNums.isPenguNumPositive(i)) {
					return true;
				}
			}
			return false;
		} catch (Throwable ignored) {
			return true;
		}
	}

	@PublicTest
	@DisplayName(value = "Test Summary - isPenguNumPositive - Base")
	@Order(16)
	void testIsPenguNumPositiveBaseSummary() {
		if (isPenguNumPositiveBaseArguments().count() != passedIsPenguNumPositiveBase.get()) {
			fail("At least one of the required tests failed.");
		}
		if (!isPenguNumPositiveIsImplemented()) {
			fail("isPenguNumPositive() always returns the same value.");
		}
	}

	static AtomicInteger passedIsPenguNumPositiveTrue = new AtomicInteger();

	static Stream<Arguments> isPenguNumPositiveTrueArguments() {
		return Stream.of(Arguments.of(2), Arguments.of(10609), Arguments.of(612979045863284359L),
				Arguments.of(7015254043203144209L));
	}

	@Public
	@DisplayName(value = "Test Indivdual - isPenguNumPositive - True")
	@ParameterizedTest(name = "[{index}] Testing isPenguNumPositive({0})")
	@MethodSource("isPenguNumPositiveTrueArguments")
	@Order(17)
	void testIsPenguNumPositiveTrue(long n) {
		assertTrue(PenguNums.isPenguNumPositive(n));
		passedIsPenguNumPositiveTrue.incrementAndGet();
	}

	@PublicTest
	@DisplayName(value = "Test Summary - isPenguNumPositive - True")
	@Order(18)
	void testIsPenguNumPositiveTrueSummary() {
		if (isPenguNumPositiveTrueArguments().count() != passedIsPenguNumPositiveTrue.get()) {
			fail("At least one of the required tests failed.");
		}
		if (!isPenguNumPositiveIsImplemented()) {
			fail("isPenguNumPositive() always returns the same value.");
		}
	}

	static AtomicInteger passedIsPenguNumPositiveFalse = new AtomicInteger();

	static Stream<Arguments> isPenguNumPositiveFalseArguments() {
		return Stream.of(Arguments.of(10608), Arguments.of(10610), Arguments.of(612979045863284358L),
				Arguments.of(612979045863284360L), Arguments.of(7015254043203144210L));
	}

	@Public
	@DisplayName(value = "Test Indivdual - isPenguNumPositive - False")
	@ParameterizedTest(name = "[{index}] Testing isPenguNumPositive({0})")
	@MethodSource("isPenguNumPositiveFalseArguments")
	@Order(19)
	void testIsPenguNumPositiveFalse(long n) {
		assertFalse(PenguNums.isPenguNumPositive(n));
		passedIsPenguNumPositiveFalse.incrementAndGet();
	}

	@PublicTest
	@DisplayName(value = "Test Summary - isPenguNumPositive - False")
	@Order(20)
	void testIsPenguNumPositiveFalseSummary() {
		if (isPenguNumPositiveFalseArguments().count() != passedIsPenguNumPositiveFalse.get()) {
			fail("At least one of the required tests failed.");
		}
		if (!isPenguNumPositiveIsImplemented()) {
			fail("isPenguNumPositive() always returns the same value.");
		}
	}

	static AtomicInteger passedIsPenguNum = new AtomicInteger();

	static Stream<Arguments> isPenguNumArguments() {
		return Stream.of(Arguments.of(-2, false), Arguments.of(-5768, true), Arguments.of(0, true),
				Arguments.of(1, true), Arguments.of(2, true), Arguments.of(10609, true),
				Arguments.of(612979045863284359L, true), Arguments.of(7015254043203144209L, true),
				Arguments.of(10608, false), Arguments.of(10610, false), Arguments.of(612979045863284358L, false),
				Arguments.of(612979045863284360L, false), Arguments.of(7015254043203144210L, false));
	}

	@Public
	@DisplayName(value = "Test Indivdual - isPenguNum")
	@ParameterizedTest(name = "[{index}] Testing isPenguNum({0})")
	@MethodSource("isPenguNumArguments")
	@Order(21)
	void testIsPenguNum(long n, boolean expected) {
		final var result = PenguNums.isPenguNum(n);
		if (n >= 0) {
			if (expected != result) {
				assertEquals(PenguNums.isPenguNumPositive(n), result);
			}
		} else {
			assertEquals(expected, result);
		}
		passedIsPenguNum.incrementAndGet();
	}

	@PublicTest
	@DisplayName(value = "Test Summary - isPenguNum")
	@Order(22)
	void testIsPenguNumSummary() {
		if (isPenguNumArguments().count() != passedIsPenguNum.get()) {
			fail("At least one of the required tests failed.");
		}
	}

	@PublicTest
	@DisplayName(value = "Test Indivdual - isPenguNum - Edge Case (-1)")
	void testIsPenguNumEdgeCase() {
		assertTrue(PenguNums.isPenguNum(-1));
	}
}