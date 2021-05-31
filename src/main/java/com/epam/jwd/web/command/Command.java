package com.epam.jwd.web.command;

public interface Command {

    CommandResponse execute(CommandRequest request);

    static Command withName(String name) {
        return AppCommand.of(name)
                .getCommand();
    }

}
