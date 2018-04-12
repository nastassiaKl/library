package by.kalinklish.project.controller;

import by.kalinklish.project.command.ActionFactory;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.command.common.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static by.kalinklish.project.constant.JspPageConstants.START_PAGE;

@MultipartConfig
@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private final static Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter(ParameterConstants.PARAM_COMMAND));
        Command command = commandOptional.orElse(new EmptyCommand());
        try {
            Router router = command.execute(request);
            if (router != null) {
                String page = router.getPagePath();
                if (router.getRoute() == Router.RouteType.FORWARD) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
                    requestDispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + page);
                }
            } else {
                request.getSession().invalidate();
                response.sendRedirect(START_PAGE);
            }
        } catch (CommandException e) {
            LOGGER.error(e);
        }
    }
}
