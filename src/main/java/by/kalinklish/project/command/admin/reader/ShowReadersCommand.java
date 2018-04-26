package by.kalinklish.project.command.admin.reader;

import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.ReaderLogic;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowReadersCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ShowReadersCommand.class);
    private ReaderLogic readerLogic;

    public ShowReadersCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        List<User> listUser;

        try {
            listUser = readerLogic.getReaders();

            request.setAttribute(ParameterConstants.PARAM_READERS, listUser);
            page = JspPageConstants.ADMIN_READERS_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
