package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(1, 1);
        String name = box.whatsThis();
        assertThat(name).containsIgnoringCase("unknown")
            .isEqualTo("Unknown object");
    }

    @Test
    void whenVertexIsFourThenNumberOfVerticesMustBeSame() {
        Box box = new Box(4, 1);
        assertThat(box.getNumberOfVertices()).isPositive()
            .isNotZero()
            .isEqualTo(4);
    }

    @Test
    void whenEdgeIsLessOrEqualToZeroThenNumberOfVerticesMustBeMinusOne() {
        Box box = new Box(4, 0);
        assertThat(box.getNumberOfVertices()).isNegative()
            .isEqualTo(-1);
    }

    @Test
    void whenVertexIsEightThenBoxMustExist() {
        Box box = new Box(8, 1);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenUnknownTypeThenBoxMustNotExist() {
        Box box = new Box(-1, 1);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenVertexIsZeroAndEdgeIsTreeThenAreaMustBeMoreZero() {
        Box box = new Box(0, 3);
        assertThat(box.getArea()).isEqualTo(113.1d, withPrecision(0.003d))
            .isCloseTo(113.1d, withPrecision(0.07d))
            .isCloseTo(113.1d, Percentage.withPercentage(1.0d))
            .isGreaterThan(113.0d)
            .isLessThan(113.2d);
    }

    @Test
    void whenUnknownTypeThenAreaMustBeZero() {
        Box box = new Box(2, 1);
        assertThat(box.getArea()).isZero()
            .isLessThan(1.0d);
    }
}