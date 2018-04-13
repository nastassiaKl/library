package by.kalinklish.project.command.common;

import by.kalinklish.project.command.Command;
import by.kalinklish.project.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
