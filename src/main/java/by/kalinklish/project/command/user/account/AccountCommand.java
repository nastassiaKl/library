package by.kalinklish.project.command.user.account;

import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.ReaderLogic;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AccountCommand.class);
    private ReaderLogic readerLogic;

    public AccountCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        Router router = new Router();
        String page = null;
        User user;

        try {
            int numberTicket = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_TICKET));
            user = readerLogic.getReaderByTicket(numberTicket);

            session.setAttribute(ParameterConstants.PARAM_USER_DATA, user);
            page = JspPageConstants.USER_ACCOUNT_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
