package by.kalinklish.project.command.admin.reader;

import by.kalinklish.project.constant.MessageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.constant.PropertyKeys;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.ReaderLogic;
import by.kalinklish.project.manager.MessageManager;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteReaderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteReaderCommand.class);
    private ReaderLogic readerLogic;

    public DeleteReaderCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<User> listUsers;

        try {
            int idUser = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_READER));

            if (readerLogic.deleteReader(idUser)) {
                listUsers = readerLogic.getReaders();
                request.setAttribute(ParameterConstants.PARAM_READERS, listUsers);
                request.setAttribute(MessageConstants.MESSAGE_DELETE_READER, MessageManager.getLocale(locale).getMessage(PropertyKeys.DELETE_READER_MESSAGE));
                page = JspPageConstants.ADMIN_READERS_PAGE;
            } else {
                page = JspPageConstants.ADMIN_READERS_PAGE;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
