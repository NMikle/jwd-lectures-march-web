package com.epam.jwd.web.command;

public enum ShowMainPageCommand implements Command {
    INSTANCE;

    private static final CommandResponse SHOW_MAIN_PAGE = new CommandResponse() {
        @Override
        public String getPath() {
            return "/WEB-INF/jsp/main.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return SHOW_MAIN_PAGE;
    }
}
