package by.kalinklish.project.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SuccessMessageTag extends TagSupport {
    private String successMessage;

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuffer buffer = new StringBuffer();
            if(successMessage !=null && !successMessage.isEmpty()) {
                buffer.append("<div class=\"alert-success alert\">");
                buffer.append("<a href=\"#\" class=\"close\" data-dismiss=\"alert\">×</a>");
                buffer.append(this.successMessage);
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
