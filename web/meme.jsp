<%-- 
    Document   : meme
    Created on : 03-Jul-2019, 15:12:40
    Author     : jokip
--%>

<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="me_me.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>MemeGram</title>

        <!-- Favicon -->
        <link href="/assets/img/zany.gif" rel="icon" type="image/png">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">

        <!-- Icons -->
        <link href="assets/vendor/nucleo/css/nucleo.css" rel="stylesheet">
        <link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">

        <!-- Argon CSS -->
        <link type="text/css" href="assets/css/argon.css" rel="stylesheet">
        <!-- Argon CSS -->
        <link type="text/css" href="assets/css/main.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Fredoka+One&display=swap" rel="stylesheet">

    </head>

    <body class="bg-gradient-defaultb" style="background-color:ghostwhite">

        <nav class="navbar navbar-expand-lg navbar-light sticky-top" style="background-color:ghostwhite">
            <div class="container">
                <a class="navbar-brand text-left" href="indexx.jsp"
                   style="font-family: 'Fredoka One', cursive; font-size: 28px">MemeGram</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-default"
                        aria-controls="navbar-default" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbar-default">
                    <div class="navbar-collapse-header">
                        <div class="row">
                            <div class="col-6 collapse-brand">
                                <a href="index.html">
                                    <img src="assets/img/brand/blue.png">
                                </a>
                            </div>
                            <div class="col-6 collapse-close">
                                <button type="button" class="navbar-toggler" data-toggle="collapse"
                                        data-target="#navbar-default" aria-controls="navbar-default" aria-expanded="false"
                                        aria-label="Toggle navigation">
                                    <span></span>
                                    <span></span>
                                </button>
                            </div>
                        </div>
                    </div>

                    <ul class="navbar-nav mr-lg-auto col-xs-6 ml-lg-auto">

                        <div class="col-lg-12">


                            <li class="nav-item">
                                <div class="form-group pt-3 row">

                                    <div class="input-group input-group-alternative input-group"
                                         style="width: 100%;width:380px">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="ni ni-zoom-split-in"></i></span>
                                        </div>

                                        <input class="form-control " id="searchf" placeholder="Search Memes" type="text"
                                               style="color: black" width="1000">

                                    </div>
                                </div>
                        </div>
                        </li>


                    </ul>
                </div>
                <ul class="navbar-nav ml-lg-auto">
                    <li class="nav-item">
                        <a class="btn btn-neutral" href="#">
                            <i class="ni ni-"></i>
                            Profile
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class=" btn btn-default" href="addmeme.html">
                            <i class="ni ni-fat-add"></i>
                            Add Meme
                        </a>
                    </li>

                </ul>

            </div>
        </div>
    </nav>

    <div class="row">
        <div class="col-2 pt-5 pl-5 ">

            <ul class="nav flex-column" style="position: fixed">
                <li class="nav-item">
                    <a class="nav-link active" href="indexx.jsp?cats=animals">🙉 Animals</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="indexx.jsp?cats=anime">😃 Anime</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="indexx.jsp?cats=basketball">🏀 Basketball</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="indexx.jsp?cats=car">🚗 Car</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="indexx.jsp?cats=comics">🕸 Comics</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="indexx.jsp?cats=gaming">🎮 Gaming</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="indexx.jsp?cats=history">⏳ History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="indexx.jsp?cats=music">🎶 Music</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="indexx.jsp?cats=politics">📰 Politics</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="indexx.jsp?cats=Science and Tech">⚗ Science & Tech</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="indexx.jsp?cats=football">️⚽️ Football</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="indexx.jsp?cats=Tv and Film">🎥 Tv & Film</a>
                </li>
            </ul>


        </div>
        <div class="col-10 ">

            <div class="row">
                <div class="col-6">
                    <div class="text-center pt-5">

                        <h1>
                            <%

                                PrintWriter outt = response.getWriter();
                                try {
                                    String[] filename;
                                    Connection con = DBConnection.DatabaseConnect();
                                    PreparedStatement st;
                                    ResultSet rs;
                                    st = con.prepareStatement("SELECT * FROM meme where Meme_Id='" + request.getParameter("id") + "'");
                                    //out.println(st);
                                    rs = st.executeQuery();
                                    String idxx;
                                    while (rs.next()) {
                                        String dir = rs.getString("Pic_Vid");
                                        idxx = rs.getString("Caption");
                                        String todoo = "<img style=\" height:480px;\"   src=\"images/" + dir + "\" >";

                                        out.println(todoo);
                                    }

                                    st.close();
                                    con.close();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            %>
                        </h1>
                        <br>

                        <div class="form-group">
                            
                        </div>
                    </div>
                </div>
                <div class="col-5">
                    <div class="pt-5">
                    <h2>Caption</h2>
                    <p> <%
                        try {
                            String[] filename;
                            Connection con = DBConnection.DatabaseConnect();
                            PreparedStatement st;
                            ResultSet rs;
                            st = con.prepareStatement("SELECT * FROM meme where Meme_Id='" + request.getParameter("id") + "'");
                            //out.pri+ntln(st);
                            rs = st.executeQuery();
                            String idxx;
                            while (rs.next()) {
                                String dir = rs.getString("Pic_Vid");
                                idxx = rs.getString("Caption");

                                out.println(idxx);
                            }

                            st.close();
                            con.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        %></p>
                    <br>
                    <br>
                    <h2>Comments</h2>
                     

                    <h3>
                    <%
                        try{
                        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\jokip\\Documents\\NetBeansProjects\\MemeG\\web\\comments\\"
                                + request.getParameter("id")+".txt"));
                        String line;
                        int i=0;
                        while ((line = in.readLine()) != null) {
                            if(line!="\n" && i!=0){
                            out.println("<p><h6> 👦 "+line+"</h6></p>");
                            }
                            i+=1;
                        }
                        in.close();
                        }catch (Exception e) { 
            out.println("<p><h6> 😟 No comments</h6></p>");
        }
                    %>
                    </h3>
                    <form method="post" action="./commentss" role="form">
<input type="hidden" name="custid" value="<%
                     out.print(request.getParameter("id"));
                     %>">
                        <div class="form-group mb-3">
                            <div class="input-group input-group-alternative">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="ni ni-send"></i></span>
                                </div>
                                <textarea name="comment" class="form-control form-control-alternative" id="Textarea1" rows="3" placeholder="Say someting about this post ..."></textarea>
                            </div>
                        </div>



                        <div class="text-center">
                            <button type="submit" class="btn btn-primary btn-block">Comment</button>
                        </div>
                        <br>

                    </form>
                        </div>

                </div>
            </div>





        </div>


    </div>

    <style>
        #searchf input[type=text] {
            width: 430px !important;
        }
    </style>


    <!-- Core -->
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/popper/popper.min.js"></script>
    <script src="assets/vendor/bootstrap/bootstrap.min.js"></script>

    <!-- Optional plugins -->
    <script src="assets/vendor/PLUGIN_FOLDER/PLUGIN_SCRIPT.js"></script>

    <!-- Theme JS -->
    <script src="assets/js/argon.js"></script>
    <script src="assets/js/main.js"></script>
</body>

</html>
