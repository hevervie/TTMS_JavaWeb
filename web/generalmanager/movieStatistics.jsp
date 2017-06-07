<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="edu.xupt.cs.se.model.Bill" %>
<%@ page import="edu.xupt.cs.se.model.Play" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="edu.xupt.cs.se.dao.PlayDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <!-- 若您需要使用Kendo UI Professional，请联系版权人获得合法的授权或许可。 -->
    <!-- Bootstrap css -->
    <link href="https://o.qcloud.com/static_api/v3/assets/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <!-- kendo ui css -->
    <link href="https://o.qcloud.com/static_api/v3/assets/kendoui-2015.2.624/styles/kendo.common.min.css"
          rel="stylesheet">
    <link href="https://o.qcloud.com/static_api/v3/assets/kendoui-2015.2.624/styles/kendo.default.min.css"
          rel="stylesheet">
    <!-- font-awesome -->
    <link href="https://o.qcloud.com/static_api/v3/assets/fontawesome/css/font-awesome.css" rel="stylesheet">
    <!--蓝鲸提供的公用样式库 -->
    <link href="https://o.qcloud.com/static_api/v3/bk/css/bk.css?v=1.0.1" rel="stylesheet">
    <link href="https://o.qcloud.com/static_api/v3/bk/css/bk_pack.css" rel="stylesheet">
    <!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
    <script src="https://o.qcloud.com/static_api/v3/assets/js/jquery-1.10.2.min.js"></script>
    <!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
    <script src="https://o.qcloud.com/static_api/v3/assets/bootstrap-3.3.4/js/bootstrap.min.js"></script>
    <!-- 包括所有kendoui的js插件或者可以根据需要使用的js插件调用　-->
    <script src="https://o.qcloud.com/static_api/v3/assets/kendoui-2015.2.624/js/kendo.all.min.js"></script>
    <script src="https://o.qcloud.com/static_api/v3/bk/js/bk.js"></script>
    <!-- 数据埋点统计 -->
    <script src="https://o.qcloud.com/static_api/analysis.js"></script>
    <!-- 以下两个插件用于在IE8以及以下版本浏览器支持HTML5元素和媒体查询，如果不需要用可以移除 -->
    <!--[if lt IE 9]>
    <script src="https://o.qcloud.com/static_api/v3/assets/js/html5shiv.min.js"></script>
    <script src="https://o.qcloud.com/static_api/v3/assets/js/respond.min.js"></script><![endif]-->
</head>

<body class="bg-white" data-bg-color="bg-white">
<div class="king-page-box">
    <div class="king-layout1-header">
        <nav>
            <div class="king-header2 navbar navbar-blue  f14">
                <div class="nav-container">
                    <div class="navbar-header">
                        <button class="pull-right visible-xs navbar-toggle collapsed navbar-toggle-sm" type="button"
                                data-toggle="collapse" data-target="#king-header2-navbar-collapse"><i
                                class="fa fa-fw fa-ellipsis-v"> </i></button>
                        <a class="navbar-brand" href="/generalmanager/"><span style="font-size: 24px">光影人生</span>-影院票务管理系统</a>
                    </div>
                    <div class="navbar-collapse collapse" id="king-header2-navbar-collapse">
                        <ul class="nav navbar-nav navbar-left hidden-sm"></ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i
                                        class="fa fa-user"></i> admin <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="javascript:;"><i class="fa fa-fw fa-user"></i> 用户</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a href="/logout"><i class="fa fa-fw fa-power-off"></i> 退出</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <div class="king-layout1-main clearfix">
        <!-- sidebar start -->
        <div class="king-layout1-sidebar" style="width:216px;">
            <nav style="height:100%;" class="   f14">
                <div class="king-vertical-nav4">
                    <div class="sidebar-inner">
                        <ul class="navi">
                            <li class="has_submenu current open active c-open">
                                <a href="javascript:void(0);"> <i class="fa fa-gear "> </i>
                                    <span>常用操作</span>
                                    <span class="pull-right"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul class="sub-menu" style="display: block;">
                                    <li><a href="/generalmanager/movieStatistics">票房统计<i
                                            class="fa fa-chevron-right pull-right"></i></a></li>
                                    <li><a href="/generalmanager/generalmanagerStatistic">财务统计<i
                                            class="fa fa-chevron-right pull-right"></i></a></li>
                                </ul>
                            </li>
                            <li class="has_submenu current open c-open">
                                <a href="javascript:void(0);"> <i class="fa fa-wrench "> </i>
                                    <span>系统管理</span>
                                    <span class="pull-right"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul class="sub-menu" style="display: block;">
                                    <li><a href="/generalmanager/employee_manager">人事管理<i
                                            class="fa fa-chevron-right pull-right"></i></a></li>
                                    <li><a href="/generalmanager/change_passwd">更改密码<i
                                            class="fa fa-chevron-right pull-right"></i></a></li>

                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <!-- sidebar end -->
        <!-- content start -->
        <div class="king-content-wrap">
            <div class="king-layout1-content" style="margin-left: 216px;">
                <!-- <div class="king-page-topbar pl20 pr20 {{ marginStyle }}"> -->
                <div class="king-page-topbar ml15">
                    <h3 class="king-topbar-title mb0">
                        <span></span>
                    </h3>
                    <ul class="breadcrumb king-breadcrumb pl0 bg-transparent">
                        <li><a href="/generalmanager/">首页</a></li>
                        <li>票房统计</li>
                    </ul>
                </div>
                <div class="panel panel-default mb0">
                    <div class="panel-heading"> 票房统计</div>
                    <div class="panel-body">
                        <table class="table mb0 pr15 ranger-box table-striped table-bordered table-header-bg table-out-bordered table-hover ">
                            <thead>
                            <tr>
                                <th style="width: 33.3%;">电影名</th>
                                <th style="width: 33.3%;">票数</th>
                                <th style="width: 33.3%;">总价</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) request.getAttribute("list");
                                ArrayList<Play> plays = (ArrayList<Play>) request.getAttribute("movie");
                                for (Map<String, Object> map : list) {
                                    Play play = (Play) map.get("movie");
                            %>
                            <tr>
                                <td style="width: 33.3%;"><%=play.getName()%>
                                </td>
                                <td style="width: 33.3%;"><%=map.get("ticket")%>
                                </td>
                                <td style="width: 33.3%;"><%=map.get("price")%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                        <form class="form-horizontal" method="post" action="/generalmanager/movieStatistics">
                            <section>
                                <div class="form-group clearfix">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <div class="bk-horizontal-line"></div>
                                    </div>
                                </div>
                            </section>
                            <div class="container-fluid m5 ">
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-group clearfix ">
                                            <label class="col-sm-3 control-label bk-lh30 pt0">影片名称：</label>
                                            <div class="col-sm-9">
                                                <select name="play" id="" class="form-control bk-valign-top">
                                                    <option value="0">全部</option>
                                                    <%
                                                        ArrayList<Play> ps = (ArrayList<Play>) request.getAttribute("movie");
                                                        for (Play play : ps) {
                                                    %>
                                                    <option value="<%=play.getId()%>"><%=play.getName()%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group clearfix ">
                                            <label class="col-sm-3 control-label bk-lh30 pt0">日期：</label>
                                            <div class="col-sm-9">
                                                <select name="time" class="form-control bk-valign-top">
                                                    <option value="all">全部</option>
                                                    <%
                                                        HashSet<String> time = (HashSet<String>) request.getAttribute("time");
                                                        for (String str : time) {
                                                    %>
                                                    <option value="<%=str%>"><%=str%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group clearfix">
                                            <div class="col-sm-9 col-sm-offset-3">
                                                <button type="submit" class="king-btn mr10  king-success">查询</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <section>
                            <div class="form-group clearfix">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <div class="bk-horizontal-line"></div>
                                </div>
                            </div>
                        </section>
                        <table class="table mb0 pr15 ranger-box table-striped table-bordered table-header-bg table-out-bordered table-hover ">
                            <thead>
                            <tr>
                                <th style="width: 16.6%;">#</th>
                                <th style="width: 16.6%;">票ID</th>
                                <th style="width: 16.6%;">电影ID</th>
                                <th style="width: 16.6%;">电影名</th>
                                <th style="width: 16.6%;">单价</th>
                                <th style="width: 16.6%;">时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                if (null != request.getAttribute("bills")){
                                    ArrayList<Bill> bills = (ArrayList<Bill>) request.getAttribute("bills");
                                    PlayDAO playDAO = new PlayDAO();
                                    for(int i=0;i<bills.size();i++){
                            %>
                            <tr>
                                <td style="width: 16.6%;"><%=i+1%></td>
                                <td style="width: 16.6%;"><%=bills.get(i).getTicket_id()%></td>
                                <td style="width: 16.6%;"><%=playDAO.getPlayByID(bills.get(i).getPlay_id()).getId()%></td>
                                <td style="width: 16.6%;"><%=playDAO.getPlayByID(bills.get(i).getPlay_id()).getName()%></td>
                                <td style="width: 16.6%;"><%=bills.get(i).getPrice()%></td>
                                <td style="width: 16.6%;"><%=bills.get(i).getSale_time()%></td>
                            </tr>
                            <%
                                }}
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- content end -->
    </div>
</div>
<script>
    $(function () {
        var li = $('.navi li');
        li.each(function () {
            $(this).click(function () {
                if ($(this).hasClass('c-open')) {
                    $(this).find('ul').slideUp(350);
                    $(this).removeClass('c-open');
                } else {
                    $(this).find('ul').slideDown(350);
                    $(this).addClass('c-open');
                }
            })
        });
    })
</script>
</body>

</html>