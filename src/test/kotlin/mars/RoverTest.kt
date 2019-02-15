package mars

import org.junit.Test
import kotlin.test.assertEquals

class RoverTest {

    @Test
    fun `can create a mars rover`() {
        val rover = Rover(1, 2, Direction.NORTH)
        assertEquals(rover.x, 1)
        assertEquals(rover.y, 2)
        assertEquals(rover.dir, Direction.NORTH)
    }

    @Test
    fun `can find a direction from an input character`() {
        assertEquals(Direction.getDir("n"), Direction.NORTH)
        assertEquals(Direction.getDir("s"), Direction.SOUTH)
        assertEquals(Direction.getDir("e"), Direction.EAST)
        assertEquals(Direction.getDir("w"), Direction.WEST)
    }

    @Test
    fun `moving forward increments the y axis when facing north`() {
        val rover = moveForward(Rover(1, 1, Direction.NORTH))
        assertEquals(rover.x, 1)
        assertEquals(rover.y, 2)
    }

    @Test
    fun `moving forward increments the x axis when facing east`() {
        val rover = moveForward(Rover(1, 1, Direction.EAST))
        assertEquals(rover.x, 2)
        assertEquals(rover.y, 1)
    }

    @Test
    fun `moving forward decrements the y axis when facing south`() {
        val rover = moveForward(Rover(1, 1, Direction.SOUTH))
        assertEquals(rover.x, 1)
        assertEquals(rover.y, 0)
    }

    @Test
    fun `moving forward decrements the x axis when facing west`() {
        val rover = moveForward(Rover(1, 1, Direction.WEST))
        assertEquals(rover.x, 0)
        assertEquals(rover.y, 1)
    }

    @Test
    fun `turning left changes direction from north to west`() {
        val rover = turnLeft(Rover(1, 1, Direction.NORTH))
        assertEquals(rover.dir, Direction.WEST)
    }

    @Test
    fun `turning left changes direction from east to south`() {
        val rover = turnLeft(Rover(1, 1, Direction.EAST))
        assertEquals(rover.dir, Direction.NORTH)
    }

    @Test
    fun `turning right changes direction from north to east`() {
        val rover = turnRight(Rover(1, 1, Direction.NORTH))
        assertEquals(rover.dir, Direction.EAST)
    }

    @Test
    fun `turning right changes direction from west to north`() {
        val rover = turnRight(Rover(1, 1, Direction.WEST))
        assertEquals(rover.dir, Direction.NORTH)
    }

    @Test
    fun `moving backward decrements the y axis when facing north`() {
        val rover = moveBackward(Rover(1, 1, Direction.NORTH))
        assertEquals(rover.x, 1)
        assertEquals(rover.y, 0)
    }

    @Test
    fun `moving backward decrements the x axis when facing east`() {
        val rover = moveBackward(Rover(1, 1, Direction.EAST))
        assertEquals(rover.x, 0)
        assertEquals(rover.y, 1)
    }

    @Test
    fun `moving backward increments the x axis when facing west`() {
        val rover = moveBackward(Rover(1, 1, Direction.WEST))
        assertEquals(rover.x, 2)
        assertEquals(rover.y, 1)
    }

    @Test
    fun `process moves forward once`() {
        val rover = process(Rover(1, 1, Direction.NORTH), listOf("f"))
        assertEquals(rover.x, 1)
        assertEquals(rover.y, 2)
    }

    @Test
    fun `process moves forward and turns once`() {
        val rover = process(Rover(1, 1, Direction.NORTH), listOf("f", "r"))
        assertEquals(rover.x, 1)
        assertEquals(rover.y, 2)
        assertEquals(rover.dir, Direction.EAST)
    }

    @Test
    fun `process does a full circle and moves backward`() {
        val rover = process(Rover(1, 1, Direction.NORTH), listOf("l", "l", "l", "l", "b"))
        assertEquals(rover.x, 1)
        assertEquals(rover.y, 0)
        assertEquals(rover.dir, Direction.NORTH)
    }

    @Test
    fun `process ignores commands it does not recognise`() {
        val rover = process(Rover(1, 1, Direction.NORTH), listOf("l", "r", "g", "f", "f"))
        assertEquals(rover.x, 1)
        assertEquals(rover.y, 3)
        assertEquals(rover.dir, Direction.NORTH)
    }
}
