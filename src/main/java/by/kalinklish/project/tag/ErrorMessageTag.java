package by.kalinklish.project.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ErrorMessageTag extends TagSupport {
    private String errorMessage;

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuffer buffer = new StringBuffer();
            if(errorMessage !=null && !errorMessage.isEmpty()) {
                buffer.append("<div class=\"alert-danger alert\">");
                buffer.append(this.errorMessage);
                buffer.append("</div>");
            }else {
                buffer.append("<br/>");
            }
            pageContext.getOut().print(buffer);
        } catch(IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }
        return SKIP_BODY;
    }
}
