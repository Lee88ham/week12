package com.promineotech;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")

	void assertThatTwoPostiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {	
			assertThatThrownBy(()-> testDemo.addPositive(a,b)).isInstanceOf(IllegalArgumentException.class);
		}
	}
	static Stream<Arguments> argumentsForAddPositive() {
		return Stream.of(				
				arguments(-1, 5, 4, true),
				arguments(0, 5, 5, true),
				arguments(1, 0, 1, true),
				arguments(-1, 0, -1, true),
				arguments(5, 1, 6, false)
			);

	}
	
	void assertThatTwoNegativeNumversAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		
		if(!expectException) {
			assertThat(testDemo.addNegative(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(()-> testDemo.addNegative(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}
	static Stream<Arguments> argumentsforAddNegative(){
		return Stream.of(
				arguments(-1, 5, 4, true),
				arguments(0, 5, 5, true),
				arguments(1, 0, 1, true),
				arguments(-1, 0, -1, true),
				arguments(-5, -1, -6, false)
				);
	}

	@Test
	void assertThatPairsOfPositiveNumbersAreaAddedCorrectly() {
		
		assertThat(testDemo.addPositive(4,5)).isEqualTo(9);
		assertThat(testDemo.addPositive(40,50)).isEqualTo(90);
		
	}
	@Test
	void assertThatNumberSquaredIsCorrect() {
		
	
	TestDemo mockDemo = spy(testDemo);
	doReturn(5).when(mockDemo).getRandomInt();
	
	int fiveSquared = mockDemo.randomNumberSquared();
	assertThat(fiveSquared).isEqualTo(25);
	
}
}

