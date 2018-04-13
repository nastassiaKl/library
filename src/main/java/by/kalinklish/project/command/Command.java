package by.kalinklish.project.command;

import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
