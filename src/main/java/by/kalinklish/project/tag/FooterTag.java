package by.kalinklish.project.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        String footer = "<p align=\"left\"> &copy; 2018 Diana Radomskaya</p>";

        try {
            JspWriter out = pageContext.getOut();
            out.write(footer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

}
