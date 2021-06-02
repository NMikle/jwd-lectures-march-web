package com.epam.jwd.web.command.page;

import com.epam.jwd.web.command.Command;
import com.epam.jwd.web.command.CommandRequest;
import com.epam.jwd.web.command.CommandResponse;

public enum ShowErrorPage implements Command {
    INSTANCE;

    private static final CommandResponse ERROR_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return "/WEB-INF/jsp/error.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return ERROR_PAGE_RESPONSE;
    }
}
