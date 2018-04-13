package by.kalinklish.project.command.common;

import by.kalinklish.project.constant.MessageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.constant.PropertyKeys;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.mail.generator.PasswordGenerator;
import by.kalinklish.project.mail.sender.MailSender;
import by.kalinklish.project.manager.MessageManager;
import by.kalinklish.project.command.Command;
import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.logic.ReaderLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ForgetPasswordCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ForgetPasswordCommand.class);
    private ReaderLogic readerLogic;

    public ForgetPasswordCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;

        try {
            int numberTicket = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_TICKET));
            String mail = request.getParameter(ParameterConstants.PARAM_MAIL);
            String password = PasswordGenerator.generatePassword();

            if (readerLogic.checkMailAndTicket(mail, numberTicket)) {
                readerLogic.forgotPassword(numberTicket, password);

                MailSender.sendMail(ParameterConstants.PARAM_TITTLE_MAIL, ParameterConstants.PARAM_NEW_PASSWORD_MAIL + password, mail);
                router.setRoute(Router.RouteType.REDIRECT);
                page = JspPageConstants.START_PAGE;
            } else {
                request.setAttribute(MessageConstants.MESSAGE_ERROR_FORGOT_PASSWORD, MessageManager.getLocale(locale).getMessage(PropertyKeys.FORGOT_PASSWORD_ERROR_MESSAGE));
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.FORGOT_PASSWORD_PAGE;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }


        router.setPagePath(page);
        return router;
    }
}
