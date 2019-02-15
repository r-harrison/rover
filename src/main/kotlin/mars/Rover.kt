package mars


data class Rover(val x: Int, val y: Int, val dir: Direction)


enum class Direction(val str: String, val forwardX: Int, val forwardY: Int) {
    NORTH("n", 0, 1),
    EAST("e", 1, 0),
    SOUTH("s", 0, -1),
    WEST("w", -1, 0);

    companion object {
        fun getDir(inputStr: String) = Direction.values().find { it.str == inputStr }

        fun leftOf(dir: Direction) = if (dir.ordinal == 0) WEST else Direction.values()[dir.ordinal - 1]

        fun rightOf(dir: Direction) = if (dir.ordinal == 3) NORTH else Direction.values()[dir.ordinal + 1]
    }
}


enum class Command(val str: String, val cmd: (Rover) -> Rover) {
    FORWARD("f", { rover -> moveForward(rover) }),
    BACKWARD("b", { rover -> moveBackward(rover) }),
    LEFT("l", { rover -> turnLeft(rover) }),
    RIGHT("r", { rover -> turnRight(rover) });

    companion object {
        fun getCmd(inputStr: String) = Command.values().find { it.str == inputStr }
    }
}

fun moveForward(rover: Rover): Rover {
    return Rover(
        x = rover.x + rover.dir.forwardX,
        y = rover.y + rover.dir.forwardY,
        dir = rover.dir
    )
}

fun moveBackward(rover: Rover): Rover {
    return Rover(
        x = rover.x - rover.dir.forwardX,
        y = rover.y - rover.dir.forwardY,
        dir = rover.dir
    )
}

fun turnLeft(rover: Rover): Rover {
    return Rover(
        x = rover.x,
        y = rover.y,
        dir = Direction.leftOf(rover.dir)
    )
}

fun turnRight(rover: Rover): Rover {
    return Rover(
        x = rover.x,
        y = rover.y,
        dir = Direction.rightOf(rover.dir)
    )
}

fun process(initial: Rover, commands: List<String>): Rover {
    return commands.fold(initial) { rover, cmd ->
        Command.getCmd(cmd)?.let { it.cmd(rover) } ?: rover
    }
}



