package by.kalinklish.project.command.common;

import by.kalinklish.project.constant.*;
import by.kalinklish.project.exception.CommandException;
import by.kalinklish.project.logic.LibrarianLogic;
import by.kalinklish.project.manager.MessageManager;
import by.kalinklish.project.command.Command;
import by.radomskaya.project.constant.*;
import by.kalinklish.project.controller.Router;
import by.kalinklish.project.entity.User;
import by.kalinklish.project.exception.LogicException;
import by.kalinklish.project.logic.ReaderLogic;
import by.kalinklish.project.validation.InputParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;

public class RegistrationCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    private final int INITIAL_VALUE_OF_TICKET = 1000;
    private final int FINAL_VALUE_OF_TICKET = 10000;
    private ReaderLogic readerLogic;
    private LibrarianLogic librarianLogic;

    public RegistrationCommand(ReaderLogic readerLogic, LibrarianLogic librarianLogic) {
        this.readerLogic = readerLogic;
        this.librarianLogic = librarianLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        User user;
        String login = request.getParameter(ParameterConstants.PARAM_LOGIN);
        String roleUser = request.getParameter(ParameterConstants.PARAM_ROLE);

        try {
            user = setUserFromRequest(request);
            if (!readerLogic.checkLogin(login)) {
                switch (roleUser) {
                    case RoleType.LIBRARIAN:
                        librarianLogic.addLibrarian(user);
                        router.setRoute(Router.RouteType.REDIRECT);
                        page = JspPageConstants.START_PAGE;
                        break;
                    case RoleType.READER:
                        readerLogic.registrationReader(user);
                        router.setRoute(Router.RouteType.REDIRECT);
                        page = JspPageConstants.START_PAGE;
                        break;
                }
            } else {
                request.setAttribute(MessageConstants.MESSAGE_SAME_LOGIN, MessageManager.getLocale(locale).getMessage(PropertyKeys.SAME_LOGIN_MESSAGE));
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.REGISTRATION_PAGE;
            }

        } catch (ServletException | LogicException | IOException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);

        return router;
    }

    private User setUserFromRequest(HttpServletRequest request) throws IOException, ServletException {
        User user = new User();
        int numberTicket = generateNumberTicket();
        String surname = request.getParameter(ParameterConstants.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstants.PARAM_NAME);
        String middleName = request.getParameter(ParameterConstants.PARAM_MIDDLE_NAME);
        int age = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_AGE));
        String phoneNumber = request.getParameter(ParameterConstants.PARAM_PHONE);
        String mail = request.getParameter(ParameterConstants.PARAM_MAIL);
        String login = request.getParameter(ParameterConstants.PARAM_LOGIN);
        String password = request.getParameter(ParameterConstants.PARAM_PASSWORD);
        Part filePart = request.getPart(ParameterConstants.PARAM_PROFILE_PHOTO);
        String imageName = getImageName(filePart);
        if (imageName.equals(ParameterConstants.PARAM_EMPTY_PROFILE_PHOTO)) {
            user.setProfilePhoto(ParameterConstants.PARAM_DEFAULT_PROFILE_PHOTO);
        } else {
            user.setProfilePhoto(imageName);
        }

        if (InputParamValidator.isValidateUserData(surname, name, middleName, age, phoneNumber, mail, login, password)) {
            user.setNumberTicket(numberTicket);
            user.setSurname(surname);
            user.setName(name);
            user.setMiddleName(middleName);
            user.setAge(age);
            user.setPhoneNumber(phoneNumber);
            user.setMail(mail);
            user.setLogin(login);
            user.setPassword(password);
        }

        return user;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }

    private int generateNumberTicket() {
        int numberTicket = INITIAL_VALUE_OF_TICKET + (int)(Math.random() * FINAL_VALUE_OF_TICKET);
        return numberTicket;
    }
}
