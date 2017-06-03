package edu.xupt.cs.se.servlet.manager;

import edu.xupt.cs.se.dao.LangDAO;
import edu.xupt.cs.se.dao.LevelDAO;
import edu.xupt.cs.se.dao.PlayDAO;
import edu.xupt.cs.se.dao.TypeDAO;
import edu.xupt.cs.se.idao.Imanager;
import edu.xupt.cs.se.model.Lang;
import edu.xupt.cs.se.model.Level;
import edu.xupt.cs.se.model.Play;
import edu.xupt.cs.se.model.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shinelon on 2017/6/1.
 */
@WebServlet(name = "ManagerPlayAddServlet", urlPatterns = "/managers/movie/add/")
public class ManagerPlayAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LangDAO langDAO = new LangDAO();
        LevelDAO levelDAO = new LevelDAO();
        TypeDAO typeDAO = new TypeDAO();
        if (null != request.getParameter("play_type")) {
            //数据提交,获取数据
            String str_type = request.getParameter("play_type");
            String str_level = request.getParameter("play_level");
            String str_lang = request.getParameter("play_lang");
            String name = request.getParameter("play_name");
            String image = request.getParameter("play_image_url");
            String introd = request.getParameter("play_introd");
            String str_score = request.getParameter("play_score");
            String str_length = request.getParameter("play_length");
            String str_price = request.getParameter("play_price");
            int type, level, lang, length;
            float score, price;

            if(name.equals("") || image.equals("") || introd.equals("")){
                ArrayList<Lang> langs = langDAO.getAllLang();
                ArrayList<Level> levels = levelDAO.getAllLevel();
                ArrayList<Type> types = typeDAO.getAllType();
                request.setAttribute("langs", langs);
                request.setAttribute("levels", levels);
                request.setAttribute("types", types);
                request.setAttribute("message", "您的输入不合法！");
                request.getRequestDispatcher("/manager/play_add.jsp").forward(request, response);
                return;
            }

            try {
                type = Integer.parseInt(str_type);
                level = Integer.parseInt(str_level);
                lang = Integer.parseInt(str_lang);
                length = Integer.parseInt(str_length);
                score = Float.parseFloat(str_score);
                price = Float.parseFloat(str_price);
            } catch (NumberFormatException e) {
                ArrayList<Lang> langs = langDAO.getAllLang();
                ArrayList<Level> levels = levelDAO.getAllLevel();
                ArrayList<Type> types = typeDAO.getAllType();
                request.setAttribute("langs", langs);
                request.setAttribute("levels", levels);
                request.setAttribute("types", types);
                request.setAttribute("message", "您的输入不合法！");
                request.getRequestDispatcher("/manager/play_add.jsp").forward(request, response);
                return;
            }
            Play play = new Play();
            PlayDAO playDAO = new PlayDAO();
            play.setName(name);
            play.setType_id(type);
            play.setLang_id(lang);
            play.setLevel_id(level);
            play.setIntrod(introd);
            play.setImage_url(image);
            play.setLength(length);
            play.setPrice(price);
            play.setScore(score);
            play.setStatus(0);
            if(playDAO.insert(play)){
                System.out.println(play.getName());
                response.sendRedirect("/managers/movie/");
            }else{
                ArrayList<Lang> langs = langDAO.getAllLang();
                ArrayList<Level> levels = levelDAO.getAllLevel();
                ArrayList<Type> types = typeDAO.getAllType();
                request.setAttribute("langs", langs);
                request.setAttribute("levels", levels);
                request.setAttribute("types", types);
                request.setAttribute("message", "插入失败！");
                request.getRequestDispatcher("/manager/play_add.jsp").forward(request, response);
            }


        } else {
            ArrayList<Lang> langs = langDAO.getAllLang();
            ArrayList<Level> levels = levelDAO.getAllLevel();
            ArrayList<Type> types = typeDAO.getAllType();
            request.setAttribute("langs", langs);
            request.setAttribute("levels", levels);
            request.setAttribute("types", types);
            request.getRequestDispatcher("/manager/play_add.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
