package edu.mangement.constant;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 12:38 AM
 */
public class Constant {
    public static final String USER_INFO = "userInfo";
    public static final String MENU_SESSION = "menuSession";
    public static final String MSG_SUCCESS = "msgSuccess";
    public static final String MSG_ERROR = "msgError";
    public static void sessionProcessor(Model model, HttpSession session){
        if (session.getAttribute(Constant.MSG_SUCCESS) != null) {
            model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
            session.removeAttribute(Constant.MSG_SUCCESS);
        }
        if (session.getAttribute(Constant.MSG_ERROR) != null) {
            model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
            session.removeAttribute(Constant.MSG_ERROR);
        }
    }
}
